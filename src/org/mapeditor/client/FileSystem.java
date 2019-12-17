package org.mapeditor.client;

/* Class18 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

abstract class FileSystem {
	
	int[] crc;
	private int[][] childEntries;
	int[] revision;
	private int[] entry;
	private byte[][][] childBuffers;
	private int[][] childHashNames;
	int[] childSize;
	int indexCrc;
	private boolean clearChildBuffer;
	private int indexesLength;
	private LookupTable[] lookupTables;
	byte[][] entryBuffers;
	private LookupTable lookupTable;
	private boolean clearEntryBuffer;
	private int[] hashName;

	final byte[] getXteaBuffer(int file, int index) {
		return getXteaBuffer(index, null, file);
	}

	final byte[] getXteaBuffer(int file, int index, int[] keys) {
		return getXteaBuffer(index, keys, file);
	}

	final void method567(RSString class63) {
		class63 = class63.toLowerCase();
		int i_2_ = lookupTable.lookupIdentifier(class63.getIntHash());
		if (i_2_ >= 0)
			method569(false, i_2_);
	}

	void method569(boolean bool, int i) {
		if (bool)
			clearChildBuffer = true;
	}

	final boolean method570() {
		boolean bool = true;
		for (int i_4_ = 0; i_4_ < entry.length; i_4_++) {
			int i_5_ = entry[i_4_];
			if (entryBuffers[i_5_] == null) {
				method585(i_5_);
				if (entryBuffers[i_5_] == null)
					bool = false;
			}
		}
		return bool;
	}

	private final boolean method571(int[] keys, int file) {
		if (entryBuffers[file] == null)
			return false;
		int[] children = childEntries[file];
		int size = childSize[file];
		boolean prepared = true;
		byte[][] childs = childBuffers[file];
		for (int id = 0; id < size; id++) {
			if (childs[children[id]] == null) {
				prepared = false;
				break;
			}
		}
		if (prepared)
			return true;
		byte[] unwrapped;
		if (keys == null || keys[0] == 0 && keys[1] == 0 && keys[2] == 0
				&& keys[3] == 0) {
			unwrapped = entryBuffers[file];
		} else {
			unwrapped = new byte[entryBuffers[file].length];
			ArrayUtils.arraycopy(entryBuffers[file], 0, unwrapped, 0, unwrapped.length);
			RSByteBuffer stream = new RSByteBuffer(unwrapped);
			stream.decodeXTEA(keys, 5, stream.buf.length);
		}
		byte[] uncompressed = null;
		try {
			uncompressed = Client.unpack(unwrapped);
		} catch(Exception e) {
		    throw new RuntimeException(
		       ("T3 - " + (keys != null) + "," + file + "," + keys.length + ","
		    		   + Client.method231(unwrapped, unwrapped.length) + ","
		    		   + Client.method231(unwrapped, unwrapped.length - 2)
		    		   + "," + crc[file] + "," + indexCrc));
		}
		if (clearEntryBuffer)
			entryBuffers[file] = null;
		if (size <= 1)
			childs[children[0]] = uncompressed;
		else {
			int length = uncompressed.length;
			int verification = uncompressed[--length] & 0xff;
			length -= size * verification * 4;
			RSByteBuffer buffer = new RSByteBuffer(uncompressed);
			buffer.index = length;
			int[] outOffset = new int[size];
			for (int i = 0; i < verification; i++) {
				int offset = 0;
				for (int count = 0; size > count; count++) {
					offset += buffer.getInt();
					outOffset[count] += offset;
				}
			}
			for (int i_19_ = 0; i_19_ < size; i_19_++) {
				if (childs[children[i_19_]] == null)
					childs[children[i_19_]] = new byte[outOffset[i_19_]];
				outOffset[i_19_] = 0;
			}
			buffer.index = length;
			int readPos = 0;
			for (int i = 0; i < verification; i++) {
				int offset = 0;
				for (int id = 0; size > id; id++) {
					offset += buffer.getInt();
					ArrayUtils.arraycopy(uncompressed, readPos, childs[children[id]],
							outOffset[id], offset);
					readPos += offset;
					outOffset[id] += offset;
				}
			}
		}
		return true;
	}

	final byte[] getBuffer(int i, int i_25_) {
		if (i_25_ < 0 || childBuffers.length <= i_25_
				|| childBuffers[i_25_] == null || i < 0
				|| i >= childBuffers[i_25_].length)
			return null;
		if (childBuffers[i_25_][i] == null) {
			boolean bool = method571(null, i_25_);
			if (!bool) {
				method585(i_25_);
				bool = method571(null, i_25_);
				if (!bool)
					return null;
			}
		}
		byte[] is = childBuffers[i_25_][i];
		return is;
	}

	final byte[] method573(int i_26_) {
		if (childBuffers.length == 1)
			return getXteaBuffer(0, i_26_);
		if (childBuffers[i_26_].length == 1)
			return getXteaBuffer(i_26_, 0);
		throw new RuntimeException();
	}

	final byte[] getXteaBuffer(int index, int[] keys, int file) {
		if (file < 0 || file >= childBuffers.length
				|| childBuffers[file] == null || index < 0
				|| childBuffers[file].length <= index)
			return null;
		if (childBuffers[file][index] == null) {
			boolean bool = method571(keys, file);
			if (!bool) {
				method585(file);
				bool = method571(keys, file);
				if (!bool)
					return null;
			}
		}
		byte[] is_29_ = childBuffers[file][index];
		if (clearChildBuffer)
			childBuffers[file][index] = null;
		return is_29_;
	}

	final void unpack(byte[] buffer_) {
		indexCrc = Client.method231(buffer_, buffer_.length);
		RSByteBuffer buffer = new RSByteBuffer(Client.unpack(buffer_));
		int type = buffer.getUByte();
		if (type != 5 && type != 6)
			throw new RuntimeException("Incorrect JS5 protocol number: " + type);
		if (type >= 6)
			buffer.getInt();
		//if (type == 5) {
			int filesNamed = buffer.getUByte();
			indexesLength = buffer.getUShort();
			entry = new int[indexesLength];
			int offset = 0;
			int lastIndex = -1;
			for (int index = 0; indexesLength > index; index++) {
				entry[index] = offset += buffer.getUShort();
				if (lastIndex < entry[index])
					lastIndex = entry[index];
			}
			entryBuffers = new byte[lastIndex + 1][];
			revision = new int[lastIndex + 1];
			childBuffers = new byte[lastIndex + 1][][];
			childEntries = new int[lastIndex + 1][];
			crc = new int[lastIndex + 1];
			childSize = new int[lastIndex + 1];
			if (filesNamed != 0) {
				hashName = new int[lastIndex + 1];
				for (int i_34_ = 0; i_34_ < indexesLength; i_34_++) {
					hashName[entry[i_34_]] = buffer.getInt();
				}
				lookupTable = new LookupTable(hashName);
			}
			for (int i_35_ = 0; i_35_ < indexesLength; i_35_++)
				crc[entry[i_35_]] = buffer.getInt();
			for (int i_36_ = 0; indexesLength > i_36_; i_36_++)
				revision[entry[i_36_]] = buffer.getInt();
			for (int i_37_ = 0; i_37_ < indexesLength; i_37_++)
				childSize[entry[i_37_]] = buffer.getUShort();
			for (int i_38_ = 0; indexesLength > i_38_; i_38_++) {
				offset = 0;
				int i_39_ = entry[i_38_];
				int i_40_ = childSize[i_39_];
				childEntries[i_39_] = new int[i_40_];
				int i_41_ = -1;
				for (int i_42_ = 0; i_40_ > i_42_; i_42_++) {
					int i_43_ = (childEntries[i_39_][i_42_] = offset += buffer.getUShort());
					if (i_41_ < i_43_)
						i_41_ = i_43_;
				}
				childBuffers[i_39_] = new byte[i_41_ + 1][];
			}
			if (filesNamed != 0) {
				lookupTables = new LookupTable[lastIndex + 1];
				childHashNames = new int[lastIndex + 1][];
				for (int i_44_ = 0; indexesLength > i_44_; i_44_++) {
					int i_45_ = entry[i_44_];
					int i_46_ = childSize[i_45_];
					childHashNames[i_45_] = new int[childBuffers[i_45_].length];
					for (int i_47_ = 0; i_46_ > i_47_; i_47_++) {
						childHashNames[i_45_][(childEntries[i_45_][i_47_])] = buffer.getInt();
					}
					lookupTables[i_45_] = new LookupTable(childHashNames[i_45_]);
				}
			}
		//}
	}

	final byte[] getFileByName(RSString class63, RSString class63_65_) {
		class63 = class63.toLowerCase();
		class63_65_ = class63_65_.toLowerCase();
		int i_66_ = lookupTable.lookupIdentifier(class63.getIntHash());
		int i_67_ = lookupTables[i_66_].lookupIdentifier(class63_65_.getIntHash());
		return getXteaBuffer(i_66_, i_67_);
	}

	final void clear() {
		for (int i_69_ = 0; childBuffers.length > i_69_; i_69_++) {
			if (childBuffers[i_69_] != null) {
				for (int i_70_ = 0; childBuffers[i_69_].length > i_70_; i_70_++)
					childBuffers[i_69_][i_70_] = null;
			}
		}
	}

	final int getChildCount(int i) {
		return childBuffers[i].length;
	}

	final boolean cached(int i, int i_71_, int i_72_) {
		if ((i ^ 0xffffffff) > i_71_ || childBuffers.length <= i
				|| childBuffers[i] == null || i_72_ < 0
				|| childBuffers[i].length <= i_72_)
			return false;
		if (childBuffers[i][i_72_] != null)
			return true;
		if (entryBuffers[i] != null)
			return true;
		method585(i);
		if (entryBuffers[i] != null)
			return true;
		return false;
	}

	final boolean allFilesComplete(int i_73_) {
		if (entryBuffers[i_73_] != null)
			return true;
		method585(i_73_);
		if (entryBuffers[i_73_] != null)
			return true;
		return false;
	}

	final void clearChildBuffers(int i) {
		for (int i_75_ = 0; childBuffers[i].length > i_75_; i_75_++)
			childBuffers[i][i_75_] = null;
	}

	final int getHash(RSString name) {
		name = name.toLowerCase();
		return lookupTable.lookupIdentifier(name.getIntHash());
	}

	void method585(int i_76_) {
	}

	final int[] getChildEntries(int id) {
		return childEntries[id];
	}

	final boolean cached(RSString class63, RSString class63_78_) {
		class63 = class63.toLowerCase();
		class63_78_ = class63_78_.toLowerCase();
		int i_79_ = lookupTable.lookupIdentifier(class63.getIntHash());
		int i_80_ = lookupTables[i_79_].lookupIdentifier(class63_78_.getIntHash());
		return cached(i_79_, -1, i_80_);
	}

	final int getBufferLen() {
		return childBuffers.length;
	}

	final byte[] method589(int i) {
		if (childBuffers.length == 1)
			return getBuffer(i, 0);
		if (childBuffers[i].length == 1)
			return getBuffer(0, i);
		throw new RuntimeException();
	}

	final int getHash(RSString name, int file) {
		name = name.toLowerCase();
		return lookupTables[file].lookupIdentifier(name.getIntHash());
	}

	FileSystem(boolean clear1, boolean clear2) {
		clearEntryBuffer = clear1;
		clearChildBuffer = clear2;
	}
}
