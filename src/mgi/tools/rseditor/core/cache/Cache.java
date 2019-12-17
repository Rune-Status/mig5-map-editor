package mgi.tools.rseditor.core.cache;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;

import mgi.tools.rseditor.core.utilities.ByteBuffer;
import mgi.tools.rseditor.core.utilities.Whirlpool;

public class Cache {

	private static final String DATA_FILE = "main_file_cache.dat2";
	private static final String INDEX_FILE = "main_file_cache.idx";
	
	private FileStore[] stores;
	private FileStore store_255;
	private FileSystem[] fileSystems;

	
	private Cache(String path) {
		openFiles(path);
	}
	
	
	@SuppressWarnings("resource")
	private final void openFiles(String path) {
		try {
			String seperator = System.getProperty("file.separator","/");
			
			RandomAccessFile dataFile = new RandomAccessFile(path + seperator + DATA_FILE, "rw");
			RandomAccessFile referenceFile = new RandomAccessFile(path + seperator + INDEX_FILE + "255", "rw");
			store_255 = new FileStore(255, dataFile.getChannel(), referenceFile.getChannel(), 0x7a120);
			
			int numIndices = store_255.getFileCount();
			stores = new FileStore[numIndices];
			fileSystems = new FileSystem[numIndices];
			for (int i = 0; i < numIndices; i++) {
				RandomAccessFile indexFile = new RandomAccessFile(path + seperator + INDEX_FILE + i, "rw");
				stores[i] = new FileStore(i, dataFile.getChannel(), indexFile.getChannel(), 0xf4240);
				fileSystems[i] = new FileSystem(i, this);
			}		
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
	}
	
	/**
	 * Finishing all pending operations.
	 */
	public void flush() {
		if (fileSystems == null)
			throw new RuntimeException("Cache is closed.");
		for (int i = 0; i < fileSystems.length; i++)
			if (fileSystems[i].isLoaded())
				fileSystems[i].finish();
	}
	
	/**
	 * Finishing all pending operations then
	 * Closes cache and disposes all data.
	 * Calling close() on closed cache has no effect.
	 */
	public void close() {
		if (stores == null)
			return; // closed cache
		flush();
		for (int i = 0; i < stores.length; i++)
			stores[i].close();
		store_255.close();
		
		fileSystems = null;
		stores = null;
		store_255 = null;
	}
	
	/**
	 * Closes cache without finishing all pending operations.
	 */
	public void closeDiscard() {
		if (stores == null)
			return; // closed cache
		
		for (int i = 0; i < stores.length; i++)
			stores[i].close();
		store_255.close();
		
		fileSystems = null;
		stores = null;
		store_255 = null;
	}
	
	
	
	
	
	/**
	 * Opens cache at given path.
	 * @param path
	 * Folder where cache is located.
	 * @return
	 * Opened cache or null if something failed.
	 */
	public static Cache openCache(String path) {
		return new Cache(path);
	}
	
	
	
	/**
	 * Create's new cache on given path.
	 * @param path
	 * Folder where to create new cache.
	 * @param indicesCount
	 * Count of indices , can't be lower than 0 or higher than 254.
	 * @return
	 * Created cache or null if something failed.
	 */
	@SuppressWarnings("resource")
	public static Cache createNewCache(String path, int indicesCount) {
		if (indicesCount < 0 || indicesCount > 254)
			return null;
		
		File file = new File(path);
		if (file.isFile())
			return null;
		
		if (!file.exists())
			file.mkdirs();
		
		if (!file.isDirectory())
			return null;
		
		try {		
			String seperator = System.getProperty("file.separator","/");
			File data = new File(path + seperator + DATA_FILE);
			if (data.exists() || !data.createNewFile() || !data.canWrite() || !data.canRead())
				return null;	
			
			for (int i = 0; i < indicesCount; i++) {
				File index = new File(path + seperator + INDEX_FILE + i);
				if (index.exists() || !index.createNewFile() || !index.canWrite() || !data.canRead())
					return null;
			}
			
			File index255 = new File(path + seperator + INDEX_FILE + "255");
			if (index255.exists() || !index255.createNewFile() || !index255.canWrite() || !index255.canRead())
				return null;
			
			RandomAccessFile dataFile = new RandomAccessFile(path + seperator + DATA_FILE, "rw");
			RandomAccessFile referenceFile = new RandomAccessFile(path + seperator + INDEX_FILE + "255", "rw");
			FileStore store_255 = new FileStore(255, dataFile.getChannel(), referenceFile.getChannel(), 0x7a120);
			
			for (int i = 0; i < indicesCount; i++) {
				byte[] pdata = Helper.encodeFITContainer(new byte[] { 5, 0, 0, 0 } , 0); // empty fs (protocol 5, props 0, folders count - 0)
				store_255.put(i, new ByteBuffer(pdata), pdata.length);
			}
			
			dataFile.getChannel().close();
			referenceFile.getChannel().close();
			
			
			return new Cache(path);
		}
		catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	
	}
	
	
	/**
	 * Get's specific file store.
	 * Returns null if store is not available in this cache.
	 * Note:Using this method to get information store (idx255) results in 
	 * null return, use getInformationStore() instead.
	 */
	public FileStore getFilesStore(int id) {
		if (stores == null)
			throw new RuntimeException("Cache is closed.");
		if (id < 0 || id > stores.length)
			return null;
		return stores[id];
	}
	
	/**
	 * Get's specific file system.
	 * If it's not loaded then it loads it.
	 */
	public FileSystem getFilesSystem(int id) {
		if (fileSystems == null)
			throw new RuntimeException("Cache is closed.");
		if (id < 0 || id > fileSystems.length)
			return null;
		FileSystem system = fileSystems[id];
		if (!system.isLoaded())
			system.load();
		return system;
	}
	
	/**
	 * Get's information about files store.
	 */
	public FileStore getInformationStore() {
		if (store_255 == null)
			throw new RuntimeException("Cache is closed.");
		return store_255;
	}
	
	/**
	 * Get's count of indices in this cache.
	 */
	public int getIndicesCount() {
		if (stores == null)
			throw new RuntimeException("Cache is closed.");
		return stores.length;
	}
	

	/**
	 * Generate's information store descriptor (AKA update keys, 255_255 & such )
	 * Generated file does not contain update server header.
	 * flush() is called to ensure correct data.
	 */
	public ByteBuffer generateInformationStoreDescriptor(BigInteger exponent,BigInteger modulus) {
		if (fileSystems == null)
			throw new RuntimeException("Cache is closed.");
		for (int i = 0; i < fileSystems.length; i++)
			if (!fileSystems[i].isLoaded())
				fileSystems[i].load();
		flush();
		int indicesCount = getIndicesCount();
		ByteBuffer alloc = new ByteBuffer((1 + (indicesCount * 72) + 1 + 64) * 10);
		alloc.writeByte(indicesCount);
		for (int i = 0; i < indicesCount; i++) {
			alloc.writeInt(fileSystems[i].getCRC32());
			alloc.writeInt(fileSystems[i].getVersion());
			alloc.writeBytes(fileSystems[i].getDigest(), 0, 64);
		}
		byte[] selfDigest = Whirlpool.whirlpool(alloc.getBuffer(), 0, alloc.getPosition());
		ByteBuffer rsa = new ByteBuffer(65);
		rsa.writeByte(10);
		rsa.writeBytes(selfDigest, 0, 64);
		BigInteger data = new BigInteger(rsa.getBuffer());
		byte[] encrypted = data.modPow(exponent, modulus).toByteArray();
		alloc.writeBytes(encrypted, 0, encrypted.length);
		return Helper.encodeFITContainer(new ByteBuffer(alloc.toArray(0, alloc.getPosition())), 0, Helper.COMPRESSION_NONE);
	}
	
	/**
	 * Generate's information store descriptor (AKA update keys, 255_255 & such )
	 * Generated file does not contain update server header.
	 * flush() is called to ensure correct data.
	 */
	public ByteBuffer generateInformationStoreDescriptor() {
		if (fileSystems == null)
			throw new RuntimeException("Cache is closed.");
		for (int i = 0; i < fileSystems.length; i++)
			if (!fileSystems[i].isLoaded())
				fileSystems[i].load();
		flush();
		int indicesCount = getIndicesCount();
		ByteBuffer alloc = new ByteBuffer((1 + (indicesCount * 72) + 1 + 64) * 10);
		alloc.writeByte(indicesCount);
		for (int i = 0; i < indicesCount; i++) {
			alloc.writeInt(fileSystems[i].getCRC32());
			alloc.writeInt(fileSystems[i].getVersion());
			alloc.writeBytes(fileSystems[i].getDigest(), 0, 64);
		}
		byte[] selfDigest = Whirlpool.whirlpool(alloc.getBuffer(), 0, alloc.getPosition());
		ByteBuffer rsa = new ByteBuffer(65);
		rsa.writeByte(10);
		rsa.writeBytes(selfDigest, 0, 64);
		BigInteger data = new BigInteger(rsa.getBuffer());
		byte[] encrypted = data.toByteArray();
		alloc.writeBytes(encrypted, 0, encrypted.length);
		return Helper.encodeFITContainer(new ByteBuffer(alloc.toArray(0, alloc.getPosition())), 0, Helper.COMPRESSION_NONE);
	}
	
	@Override
	public void finalize() {
		if (stores != null) {
			System.err.println("mgi.tools.jagcached.cache:Cache not closed.");
			close();
		}
	}
	
}
