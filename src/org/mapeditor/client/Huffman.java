package org.mapeditor.client;

/* Class6 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class Huffman {
	
    private byte[] bitSizes;
    private int[] chatMasks;
    private int[] decryptKeys;
    
    final int textUnpack(byte[] packedData, int size, byte[] is_8_, int i_9_, int i_10_) {
    	if (size == 0)
    		return 0;
    	int keyIndex = 0;
    	size += i_10_;
    	int offset = i_9_;
    	for (;;) {
    		byte textByte = packedData[offset];
    		if (textByte < 0)
    			keyIndex = decryptKeys[keyIndex];
    		else
    			keyIndex++;
    		int charId;
    		if ((charId = decryptKeys[keyIndex]) < 0) {
    			is_8_[i_10_++] = (byte) (charId ^ 0xffffffff);
    			if (i_10_ >= size)
    				break;
    			keyIndex = 0;
    		}
    		if ((textByte & 0x40) == 0)
    			keyIndex++;
		    else
		    	keyIndex = decryptKeys[keyIndex];
    		if ((charId = decryptKeys[keyIndex]) < 0) {
    			is_8_[i_10_++] = (byte) (charId ^ 0xffffffff);
    			if (size <= i_10_)
    				break;
    			keyIndex = 0;
		    }
		    if ((textByte & 0x20) != 0)
		    	keyIndex = decryptKeys[keyIndex];
		    else
		    	keyIndex++;
		    if ((charId = decryptKeys[keyIndex]) < 0) {
		    	is_8_[i_10_++] = (byte) (charId ^ 0xffffffff);
		    	if (size <= i_10_)
		    		break;
		    	keyIndex = 0;
		    }
		    if ((textByte & 0x10) != 0)
		    	keyIndex = decryptKeys[keyIndex];
		    else
		    	keyIndex++;
		    if ((charId = decryptKeys[keyIndex]) < 0) {
		    	is_8_[i_10_++] = (byte) (charId ^ 0xffffffff);
		    	if (i_10_ >= size)
		    		break;
		    	keyIndex = 0;
		    }
		    if ((textByte & 0x8) != 0)
		    	keyIndex = decryptKeys[keyIndex];
		    else
		    	keyIndex++;
		    if ((charId = decryptKeys[keyIndex]) < 0) {
		    	is_8_[i_10_++] = (byte) (charId ^ 0xffffffff);
		    	if (i_10_ >= size)
		    		break;
		    	keyIndex = 0;
		    }
		    if ((textByte & 0x4) == 0)
		    	keyIndex++;
		    else
		    	keyIndex = decryptKeys[keyIndex];
		    if ((charId = decryptKeys[keyIndex]) < 0) {
		    	is_8_[i_10_++] = (byte) (charId ^ 0xffffffff);
		    	if (size <= i_10_)
		    		break;
		    	keyIndex = 0;
		    }
		    if ((textByte & 0x2) != 0)
		    	keyIndex = decryptKeys[keyIndex];
		    else
		    	keyIndex++;
		    if ((charId = decryptKeys[keyIndex]) < 0) {
		    	is_8_[i_10_++] = (byte) (charId ^ 0xffffffff);
		    	if (size <= i_10_)
		    		break;
		    	keyIndex = 0;
		    }
		    if ((textByte & 0x1) != 0)
		    	keyIndex = decryptKeys[keyIndex];
		    else
		    	keyIndex++;
		    if ((charId = decryptKeys[keyIndex]) < 0) {
		    	is_8_[i_10_++] = (byte) (charId ^ 0xffffffff);
		    	if (i_10_ >= size)
		    		break;
		    	keyIndex = 0;
		    }
		    offset++;
    	}
    	return -i_9_ + (offset + 1);
    }
    
    final int textPack(byte[] packedData, int srcOffset, byte[] is_19_, int bit, int length) {
    	int key = 0;
    	length += srcOffset;
    	int bitPosition = bit << 3;
    	for (/**/; srcOffset < length; srcOffset++) {
    		int codeWord = is_19_[srcOffset] & 0xff;
    		int mask = chatMasks[codeWord];
    		int size = bitSizes[codeWord];
    		if (size == 0)
    			throw new RuntimeException("No codeword for data value " + codeWord);
    		int bitOffset = bitPosition & 0x7;
    		key &= -bitOffset >> 31;
    		int destOffset = bitPosition >> 3;
    		int byteSize = (bitOffset - 1 + size >> 3) + destOffset;
    		bitOffset += 24;
    		packedData[destOffset] = (byte) (key |= mask >>> bitOffset);
    		if (byteSize > destOffset) {
    			bitOffset -= 8;
    			destOffset++;
    			packedData[destOffset] = (byte) (key = mask >>> bitOffset);
    			if (destOffset < byteSize) {
    				destOffset++;
    				bitOffset -= 8;
    				packedData[destOffset] = (byte) (key = mask >>> bitOffset);
    				if (destOffset < byteSize) {
    					destOffset++;
    					bitOffset -= 8;
    					packedData[destOffset] = (byte) (key = mask >>> bitOffset);
    					if (destOffset < byteSize) {
    						bitOffset -= 8;
    						destOffset++;
    						packedData[destOffset] = (byte) (key = mask << -bitOffset);
    					}
    				}
    			}
    		}
    		bitPosition += size;
    	}
    	return (bitPosition + 7 >> 3) - bit;
    }
    
    Huffman(byte[] is) {
    	int i = is.length;
    	bitSizes = is;
		int[] is_30_ = new int[33];
		chatMasks = new int[i];
		int i_31_ = 0;
		decryptKeys = new int[8];
		for (int i_32_ = 0; i > i_32_; i_32_++) {
			int i_33_ = is[i_32_];
			if (i_33_ != 0) {
				int i_34_ = 1 << -i_33_ + 32;
				int i_35_ = is_30_[i_33_];
				chatMasks[i_32_] = i_35_;
				int i_36_;
				if ((i_35_ & i_34_) != 0)
					i_36_ = is_30_[i_33_ - 1];
				else {
					i_36_ = i_34_ | i_35_;
					for (int i_37_ = i_33_ - 1; i_37_ >= 1; i_37_--) {
						int i_38_ = is_30_[i_37_];
						if (i_38_ != i_35_)
							break;
						int i_39_ = 1 << -i_37_ + 32;
						if ((i_38_ & i_39_) == 0)
							is_30_[i_37_] = i_39_ | i_38_;
						else {
							is_30_[i_37_] = is_30_[i_37_ - 1];
							break;
						}
					}
				}
				is_30_[i_33_] = i_36_;
				for (int i_40_ = i_33_ + 1; i_40_ <= 32; i_40_++) {
					if (i_35_ == is_30_[i_40_])
						is_30_[i_40_] = i_36_;
				}
				int i_41_ = 0;
				for (int i_42_ = 0; i_42_ < i_33_; i_42_++) {
					int i_43_ = -2147483648 >>> i_42_;
					if ((i_43_ & i_35_) != 0) {
						if (decryptKeys[i_41_] == 0)
							decryptKeys[i_41_] = i_31_;
						i_41_ = decryptKeys[i_41_];
					} else
						i_41_++;
					if (decryptKeys.length <= i_41_) {
						int[] is_44_ = new int[decryptKeys.length * 2];
						for (int i_45_ = 0; i_45_ < decryptKeys.length; i_45_++)
							is_44_[i_45_] = decryptKeys[i_45_];
						decryptKeys = is_44_;
					}
					i_43_ >>>= 1;
				}
				if (i_41_ >= i_31_)
					i_31_ = i_41_ + 1;
				decryptKeys[i_41_] = i_32_ ^ 0xffffffff;
			}
		}
    }
}
