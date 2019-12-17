package org.mapeditor.client;

/* Class28 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.io.EOFException;
import java.io.IOException;

final class CacheFile
{
    private int maxLength = 65000;
    private int storeId;
    private SeekableFile indexFile;
    private SeekableFile dataFile = null;
    
    public final String toString() {
    	return "Cache:" + storeId;
    }

    private final boolean put(byte[] buffer, int index, int len, boolean exists) {
    	synchronized (dataFile) {
    		try {
    			int sector;
    			if (!exists) {
    				sector = (int) ((dataFile.length() + 519L) / 520L);
    				if (sector == 0)
    					sector = 1;
    			} else {
    				if ((long) (index * 6 + 6) > indexFile.length())
    					return false;
    				indexFile.seek((long) (index * 6));
    				indexFile.read(Client.buffer, 0, 6);
    				sector = ((Client.buffer[5] & 0xff) + ((Client.buffer[3] & 0xff) << 16) + (Client.buffer[4] << 8 & 0xff00));
    				if (sector <= 0 || ((long) sector > dataFile.length() / 520L))
    					return false;
    			}
    			Client.buffer[0] = (byte) (len >> 16);
    			int written = 0;
    			Client.buffer[1] = (byte) (len >> 8);
    			int total = 0;
    			Client.buffer[2] = (byte) len;
    			Client.buffer[3] = (byte) (sector >> 16);
    			Client.buffer[4] = (byte) (sector >> 8);
    			Client.buffer[5] = (byte) sector;
    			indexFile.seek((long) (index * 6));
    			indexFile.write(Client.buffer, 0, 6);
    			int currentFile;
    			for (/**/; len > written; written += currentFile) {
    				int nextSector = 0;
    				if (exists) {
    					dataFile.seek((long) (sector * 520));
    					try {
    						dataFile.read(Client.buffer, 0, 8);
    					} catch (EOFException eofexception) {
    						break;
    					}
    					nextSector = ((Client.buffer[6] & 0xff) + (((Client.buffer[5] & 0xff) << 8) + (Client.buffer[4] << 16 & 0xff0000)));
    					currentFile = (((Client.buffer[0] & 0xff) << 8) + (Client.buffer[1] & 0xff));
    					int currentCache = Client.buffer[7] & 0xff;
    					int currentPart = ((Client.buffer[3] & 0xff) + (Client.buffer[2] << 8 & 0xff00));
    					if (index != currentFile || currentPart != total || storeId != currentCache)
    						return false;
    					if (nextSector < 0 || (dataFile.length() / 520L < (long) nextSector))
    						return false;
    				}
    				if (nextSector == 0) {
    					nextSector = (int) ((dataFile.length() + 519L) / 520L);
    					exists = false;
    					if (nextSector == 0)
    						nextSector++;
    					if (sector == nextSector)
    						nextSector++;
    				}
    				Client.buffer[0] = (byte) (index >> 8);
    				if (len - written <= 512)
    					nextSector = 0;
    				Client.buffer[1] = (byte) index;
    				Client.buffer[2] = (byte) (total >> 8);
    				currentFile = -written + len;
    				if (currentFile > 512)
    					currentFile = 512;
    				Client.buffer[3] = (byte) total;
    				Client.buffer[4] = (byte) (nextSector >> 16);
    				Client.buffer[5] = (byte) (nextSector >> 8);
    				total++;
    				Client.buffer[6] = (byte) nextSector;
    				Client.buffer[7] = (byte) storeId;
    				dataFile.seek((long) (sector * 520));
    				sector = nextSector;
    				dataFile.write(Client.buffer, 0, 8);
    				dataFile.write(buffer, written, currentFile);
    			}
    		} catch (IOException ioexception) {
    			return false;
    		}
    		return true;
    	}
    }
    
    final byte[] get(int index) {
    	synchronized (dataFile) {
    		byte[] buf;
    		try {
    			if ((long) (index * 6 + 6) > indexFile.length())
    				return null;
    			indexFile.seek((long) (index * 6));
    			indexFile.read(Client.buffer, 0, 6);
    			int sector = (((Client.buffer[3] & 0xff) << 16) + (Client.buffer[4] << 8 & 0xff00) + (Client.buffer[5] & 0xff));
    			int fileSize = (((Client.buffer[1] & 0xff) << 8) + ((Client.buffer[0] << 16 & 0xff0000) + (Client.buffer[2] & 0xff)));
    			if (fileSize < 0 || maxLength < fileSize)
    				return null;
    			if (sector <= 0 || dataFile.length() / 520L < (long) sector)
    				return null;
    			int read = 0;
    			byte[] tempBuf = new byte[fileSize];
    			int total = 0;
    			while (fileSize > read) {
    				if (sector == 0)
    					return null;
    				dataFile.seek((long) (sector * 520));
    				int unread = fileSize - read;
    				if (unread > 512)
    					unread = 512;
    				dataFile.read(Client.buffer, 0, unread + 8);
    				int nextSector = ((Client.buffer[6] & 0xff) + ((Client.buffer[5] << 8 & 0xff00) + ((Client.buffer[4] & 0xff) << 16)));
    				int currentPart = ((Client.buffer[3] & 0xff) + ((Client.buffer[2] & 0xff) << 8));
    				int currentFile = (((Client.buffer[0] & 0xff) << 8) + (Client.buffer[1] & 0xff));
    				int currentCache = Client.buffer[7] & 0xff;
    				if (index != currentFile || currentPart != total || storeId != currentCache)
    					return null;
    				if (nextSector < 0 || ((long) nextSector > dataFile.length() / 520L))
    					return null;
    				sector = nextSector;
    				for (int id = 0; id < unread; id++)
    					tempBuf[read++] = Client.buffer[id + 8];
    				total++;
    			}
    			buf = tempBuf;
    		} catch (IOException ioexception) {
    			return null;
    		}
    		return buf;
    	}
    }
    
    final boolean put(byte[] buffer, int index, int len) {
    	synchronized (dataFile) {
    		if (len < 0 || len > maxLength)
    			throw new IllegalArgumentException();
    		boolean exists = put(buffer, index, len, true);
    		if (!exists)
    			exists = put(buffer, index, len, false);
    		boolean exists_ = exists;
    		return exists_;
    	}
    }
    
    CacheFile(SeekableFile main, SeekableFile index, int store, int max) {
    	indexFile = null;
    	dataFile = main;
    	maxLength = max;
    	indexFile = index;
    	storeId = store;
    }
}
