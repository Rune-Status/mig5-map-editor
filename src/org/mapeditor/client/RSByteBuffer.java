package org.mapeditor.client;

/* Class3_Sub12 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.math.BigInteger;

class RSByteBuffer extends Node {
	
    int index;
    byte[] buf;
    
    final int getUByteS() {
    	return (128 - (buf[index++]) & 0xff);
    }
    
    final int getLEShort() {
    	index += 2;
    	int i = ((((buf[index - 1]) & 0xff) << 8) + ((buf[index - 2]) & 0xff));
    	if (i > 32767)
    		i -= 65536;
    	return i;
    }
    
    final int getUByte() {
    	return ((buf[index++]) & 0xff);
    }
    
    final int getInt() {
    	index += 4;
    	return (((buf[index - 1]) & 0xff)
    		 + (((buf[index - 2]) & 0xff) << 8)
    		 + (((buf[index - 4]) & 0xff) << 24)
    		 + (((buf[index - 3]) & 0xff) << 16));
    }
    
    final void putByteC(int i) {
    	buf[index++] = (byte) -i;
    }
    
    final int getInt1() {
    	index += 4;
    	return (((buf[index - 2]) & 0xff) << 24)
    		 + (((buf[index - 1]) & 0xff) << 16)
    		 + (((buf[index - 4]) & 0xff) << 8)
		     + (((buf[index - 3]) & 0xff));
    }
    
    final int getInt2() {
    	index += 4;
    	return (((buf[index - 2]) & 0xff)
    		 + (((buf[index - 1]) & 0xff) << 8)
    		 + (((buf[index - 3]) & 0xff) << 24)
    		 + (((buf[index - 4]) & 0xff) << 16));
    }
    
    final void putString(RSString string) {
    	RSByteBuffer stream = this;
    	int i_7_ = stream.index;
    	int i_8_ = string.arraycopy(buf, 0, string.length(), index);
    	stream.index = i_8_ + i_7_;
    	buf[index++] = (byte) 0;
    }
    
    final int getShort() {
    	index += 2;
    	int i_9_ = ((((buf[index - 2]) & 0xff) << 8) + ((buf[index - 1]) & 0xff));
    	if (i_9_ > 32767)
    		i_9_ -= 65536;
    	return i_9_;
    }
    
    final void getBytes(byte[] buffer, int off, int len) {
    	for (int id = off; len + off > id; id++)
    		buffer[id] = (buf[index++]);
    }
    
    final int getULEShort() {
    	index += 2;
    	return (((buf[index - 2]) & 0xff) + ((buf[index - 1]) << 8 & 0xff00));
    }
    
    final int getSmart() {
    	int i_13_ = ((buf[index]) & 0xff);
    	if (i_13_ < 128)
    		return getUByte() - 64;
    	return getUShort() - 49152;
    }
    
    final int method428() {
    	int i_14_ = 0;
    	int i_15_;
    	for (i_15_ = (buf[index++]); i_15_ < 0; i_15_ = (buf[index++]))
    		i_14_ = (i_15_ & 0x7f | i_14_) << 7;
    	return i_15_ | i_14_;
    }
    
    final int getLEShortA() {
    	index += 2;
    	int i_16_ = (((buf[index - 2]) - 128 & 0xff) + (((buf [index - 1]) & 0xff) << 8));
    	if (i_16_ > 32767)
    		i_16_ -= 65536;
    	return i_16_;
    }
    
    final void putLong(long l) {
    	buf[index++] = (byte) (int) (l >> 56);
    	buf[index++] = (byte) (int) (l >> 48);
		buf[index++] = (byte) (int) (l >> 40);
		buf[index++] = (byte) (int) (l >> 32);
		buf[index++] = (byte) (int) (l >> 24);
		buf[index++] = (byte) (int) (l >> 16);
		buf[index++] = (byte) (int) (l >> 8);
		buf[index++] = (byte) (int) l;
    }
    
    final void putByte(int i) {
    	buf[index++] = (byte) i;
    }
    
    final int getLEInt() {
    	index += 4;
    	return (((buf[index - 4]) & 0xff)
    		 + (((buf[index - 2]) & 0xff) << 16)
    		 + (((buf[index - 1]) & 0xff) << 24)
    		 + (((buf[index - 3]) & 0xff) << 8));
    }
    
    final void decodeXTEA(int[] buf, int off, int len) {
    	int i_19_ = index;
    	index = off;
    	int i_20_ = (len - off) / 8;
    	for (int i_21_ = 0; i_21_ < i_20_; i_21_++) {
    		int i_22_ = getInt();
    		int i_23_ = -957401312;
    		int i_24_ = -1640531527;
    		int i_25_ = getInt();
    		int i_26_ = 32;
    		while (i_26_-- > 0) {
    			i_25_ -= (i_23_ + buf[(i_23_ & 0x1bbf) >>> 11] ^ i_22_ + (i_22_ << 4 ^ i_22_ >>> 5));
    			i_23_ -= i_24_;
    			i_22_ -= (i_25_ + (i_25_ << 4 ^ i_25_ >>> 5) ^ i_23_ + buf[i_23_ & 0x3]);
		    }
    		index -= 8;
    		putInt(i_22_);
    		putInt(i_25_);
    	}
    	index = i_19_;
    }
    
    final void putLEShort(int i) {
    	buf[index++] = (byte) i;
    	buf[index++] = (byte) (i >> 8);
    }
    
    final void putLEInt(int i_5_) {
    	buf[index++] = (byte) i_5_;
	    buf[index++] = (byte) (i_5_ >> 8);
	    buf[index++] = (byte) (i_5_ >> 16);
	    buf[index++] = (byte) (i_5_ >> 24);
    }
    
    final void putInt1(int i_47_) {
    	buf[index++] = (byte) (i_47_ >> 8);
    	buf[index++] = (byte) i_47_;
    	buf[index++] = (byte) (i_47_ >> 24);
    	buf[index++] = (byte) (i_47_ >> 16);
    }
    
    final void putInt2(int i_28_) {
		buf[index++] = (byte) (i_28_ >> 16);
	    buf[index++] = (byte) (i_28_ >> 24);
	    buf[index++] = (byte) i_28_;
	    buf[index++] = (byte) (i_28_ >> 8);
    }
    
    final byte getByte() {
    	return (buf[index++]);
    }
    
    final int getULEShortA() {
    	index += 2;
    	return (((buf[index - 2]) - 128 & 0xff) + (((buf[index - 1]) & 0xff) << 8));
    }
    
    final byte getByteA() {
    	return (byte) ((buf[index++]) - 128);
    }
    
    final int getUByteA() {
    	return ((buf[index++]) - 128 & 0xff);
    }
    
    final int getUSmart() {
    	int i = buf[index] & 0xff;
    	if (i >= 128)
    		return getUShort() - 32768;
    	return getUByte();
    }
    
    final void putByteA(int i) {
    	buf[index++] = (byte) (i + 128);
    }
    
    final int getTriByte() {
    	index += 3;
    	return (((buf[index - 3]) & 0xff) << 16)
		     + (((buf[index - 2]) & 0xff) << 8)
		      + ((buf[index - 1]) & 0xff);
    }
    
    final void getBytesA(byte[] buffer, int off, int len) {
    	for (int i_31_ = off; len + off > i_31_; i_31_++)
    		buffer[i_31_] = (byte) (buf[index++] - 128);
    }
    
    final void putShortA(int i) {
    	buf[index++] = (byte) (i >> 8);
    	buf[index++] = (byte) (i + 128);
    }
    
    final int getUShortA() {
    	index += 2;
    	return ((((buf[index - 2]) & 0xff) << 8) + ((buf[index - 1]) - 128 & 0xff));
    }
    
    final byte getByteS() {
    	return (byte) (-(buf[index++])+ 128);
    }
    
    final int getUByteC() {
    	return (-(buf[index++]) & 0xff);
    }
    
    final long getLong() {
    	long l = (long) getInt() & 0xffffffffL;
		long l_32_ = (long) getInt() & 0xffffffffL;
		return l_32_ + (l << 32);
    }
    
    final int getTriByte2() {
    	index += 3;
    	return (((buf[index - 2]) & 0xff) << 16)
		     + (((buf[index - 3]) & 0xff) << 8)
		      + ((buf[index - 1]) & 0xff);
    }
    
    final void putLEShortA(int i) {
    	buf[index++] = (byte) (i + 128);
    	buf[index++] = (byte) (i >> 8);
    }
    
    final void putSizeByte(int i_33_) {
    	buf[(index - i_33_ + -1)] = (byte) i_33_;
    }
    
    final void applyRSA(BigInteger biginteger, BigInteger biginteger_34_) {
		int i_35_ = index;
		index = 0;
		byte[] is = new byte[i_35_];
		getBytes(is, 0, i_35_);
		BigInteger biginteger_36_ = new BigInteger(is);
		BigInteger biginteger_37_ = biginteger_36_.modPow(biginteger_34_, biginteger);
		byte[] is_38_ = biginteger_37_.toByteArray();
		index = 0;
		putByte(is_38_.length);
		putBytes(is_38_, 0, is_38_.length);
    }
    
    final RSString getString() {
    	int i = index;
    	while ((buf[index++]) != 0) {
    		/* empty */
    	}
    	return Client.createRSString(buf,i, (-i + index - 1));
    }
    
    final void putShort(int i_48_) {
    	buf[index++] = (byte) (i_48_ >> 8);
    	buf[index++] = (byte) i_48_;
    }
    
    final void putByteS(int i) {
    	buf[index++] = (byte) (128 - i);
    }
    
    RSByteBuffer(int i) {
    	buf = Client.getPooledBuffer(i);
		index = 0;
    }
    
    final void putSmart(int i) {
    	if (i >= 0 && i < 128)
    		putByte(i);
    	else {
    		if (i >= 0 && i < 32768)
    			putShort(i + 32768);
    		else
    			throw new IllegalArgumentException();
    	}
    }
    
    RSByteBuffer(byte[] is) {
    	buf = is;
    	index = 0;
    }
    
    final void putInt(int i) {
    	buf[index++] = (byte) (i >> 24);
    	buf[index++] = (byte) (i >> 16);
    	buf[index++] = (byte) (i >> 8);
    	buf[index++] = (byte) i;
    }
    
    final void putBytes(byte[] buffer, int off, int len) {
    	for (int i_51_ = off; i_51_ < len + off; i_51_++)
    		buf[index++] = buffer[i_51_];
    }
    
    final void putTriByte(int i) {
    	buf[index++] = (byte) (i >> 16);
    	buf[index++] = (byte) (i >> 8);
    	buf[index++] = (byte) i;
    }
    
    final int getUShort() {
    	index += 2;
    	return ((((buf[index - 2]) & 0xff) << 8)
		       + ((buf[index - 1]) & 0xff));
    }
    
    final int method467(int i_53_) {
    	int i_54_ = Client.method181(i_53_, index, buf);
    	putInt(i_54_);
		return i_54_;
    }
}
