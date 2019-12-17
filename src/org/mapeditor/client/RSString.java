package org.mapeditor.client;

/* Class63 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.UnsupportedEncodingException;

public final class RSString implements StringInterface {
	
    int length;
    private int hashCode;
    private boolean aBoolean1107 = true;
    byte[] buffer;
    static Class RSStringClass;
    
    final long toLong() {
		long l = 0L;
		for (int i_0_ = 0; i_0_ < length && i_0_ < 12; i_0_++) {
			l *= 37L;
			byte i_1_ = buffer[i_0_];
			if (i_1_ >= 65 && i_1_ <= 90)
				l += (long) (i_1_ - 64);
			else if (i_1_ < 97 || i_1_ > 122) {
				if (i_1_ >= 48 && i_1_ <= 57)
					l += (long) (i_1_ - 48 + 27);
			} else
				l += (long) (i_1_ + 1 - 97);
		}
		for (/**/; l % 37L == 0L && l != 0L; l /= 37L) {
		    /* empty */
		}
		return l;
    }
    
    public final int hashCode() {
    	return getIntHash();
    }
    
    private final boolean method912(int i) {
    	if (i < 1 || i > 36)
    		i = 10;
    	boolean bool = false;
    	boolean bool_4_ = false;
    	int i_5_ = 0;
    	for (int i_6_ = 0; i_6_ < length; i_6_++) {
    		int i_7_ = buffer[i_6_] & 0xff;
    		if (i_6_ == 0) {
    			if (i_7_ == 45) {
    				bool_4_ = true;
    				continue;
    			}
    			if (i_7_ == 43)
    				continue;
    		}
    		if (i_7_ < 48 || i_7_ > 57) {
    			if (i_7_ >= 65 && i_7_ <= 90)
    				i_7_ -= 55;
    			else if (i_7_ >= 97 && i_7_ <= 122)
    				i_7_ -= 87;
    			else
    				return false;
    		} else
    			i_7_ -= 48;
    		if (i_7_ >= i)
    			return false;
    		if (bool_4_)
    			i_7_ = -i_7_;
    		int i_8_ = i_5_ * i + i_7_;
    		if (i_8_ / i != i_5_)
    			return false;
    		bool = true;
    		i_5_ = i_8_;
    	}
    	return bool;
    }
    
    final boolean endsWith(RSString string) {
    	if (string.length > length)
    		return false;
    	int i_10_ = -string.length + length;
    	for (int i_11_ = 0; i_11_ < string.length; i_11_++) {
    		if (string.buffer[i_11_] != buffer[i_10_ + i_11_])
    			return false;
    	}
    	return true;
    }
    
    final RSString method914(RSString string, int i) {
	if (!aBoolean1107)
	    throw new IllegalArgumentException();
	if (i > length)
	    throw new IllegalArgumentException();
	hashCode = 0;
	if (buffer.length
	    < i + string.length) {
	    int i_13_;
	    for (i_13_ = 1; i_13_ < i + string.length;
		 i_13_ += i_13_) {
		/* empty */
	    }
	    byte[] is = new byte[i_13_];
	    ArrayUtils.arraycopy(buffer, 0, is, 0,
			      length);
	    buffer = is;
	}
	ArrayUtils.arraycopy(string.buffer, 0,
			  buffer, i,
			  string.length);
	if (i + string.length > length)
	    length = i + string.length;
	return this;
    }
    
    final RSString cacheString() {
    	long hash = getLongHash();
    	synchronized (RSStringClass != null ? RSStringClass : (RSStringClass = initClass("org.mapeditor.client.RSString"))) {
    		if (Client.rsStringCache == null)
    			Client.rsStringCache = new HashTable(4096);
    		else {
    			for (RSStringCache cache = (RSStringCache) Client.rsStringCache.get(hash); cache != null; cache = (RSStringCache) Client.rsStringCache.pool()) {
    				if (equals(cache.rsString)) {
    					RSString rsstring = cache.rsString;
    					return rsstring;
    				}
    			}
    		}
    		RSStringCache rsstringCache = new RSStringCache();
    		rsstringCache.rsString = this;
    		aBoolean1107 = false;
    		Client.rsStringCache.put(rsstringCache, hash);
    	}
		return this;
    }
    
    final RSString method916() {
    	RSString instance = new RSString();
    	instance.buffer = new byte[12];
    	int count = 0;
    	instance.length = 0;
    	for (int id = 0; length > id; id++) {
    		if (buffer[id] < 65 || buffer[id] > 90) {
    			if ((buffer[id] < 97 || buffer[id] > 122) && (buffer[id] < 4 || buffer[id] > 57)) {
    				if (count > 0)
    					instance.buffer[count++] = (byte) 95;
    			} else {
    				instance.buffer[count++] = buffer[id];
    				instance.length = count;
    			}
    		} else {
    			instance.buffer[count++] = (byte) (buffer[id] - 65 + 97);
    			instance.length = count;
    		}
    		if (count == 12)
    			break;
    	}
    	return instance;
    }
    
    final RSString method918(RSString string) {
    	if (!aBoolean1107)
    		throw new IllegalArgumentException();
    	hashCode = 0;
    	if (string.length + length > buffer.length) {
    		int i_21_;
    		for (i_21_ = 1; ((length + string.length) > i_21_); i_21_ += i_21_) {
    			/* empty */
    		}
    		byte[] is = new byte[i_21_];
    		ArrayUtils.arraycopy(buffer, 0, is, 0, length);
    		buffer = is;
    	}
    	ArrayUtils.arraycopy(string.buffer, 0, buffer, length, string.length);
    	length += string.length;
    	return this;
    }
    
    final boolean equalsIgnoreCase(RSString string) {
    	if (string == null)
    		return false;
    	if (string.length != length)
    		return false;
    	for (int id = 0; length > id; id++) {
    		byte thisChar = buffer[id];
    		if (thisChar >= 65 && thisChar <= 90 || thisChar >= -64 && thisChar <= -34 && thisChar != -41)
    			thisChar += 32;
    		byte otherChar = string.buffer[id];
    		if (otherChar >= 65 && otherChar <= 90 || otherChar >= -64 && otherChar <= -34 && otherChar != -41)
    			otherChar += 32;
    		if (otherChar != thisChar)
    			return false;
    	}
    	return true;
    }
    
    final void drawString(Graphics graphics, int x, int y) {
    	String str;
  	 	try {
  	 		str = new String(buffer, 0, length, "ISO-8859-1");
  	 	} catch (UnsupportedEncodingException unsupportedencodingexception) {
  	 		str = new String(buffer, 0, length);
  	 	}
  	 	graphics.drawString(str, x, y);
    }
    
    final int length() {
    	return length;
    }
    
    final int indexOf(int i) {
    	return method940(i);
    }
    
    final RSString method923(int i_29_) {
    	if (!aBoolean1107)
    		throw new IllegalArgumentException();
    	hashCode = 0;
    	if (buffer.length == length) {
    		int i_30_;
    		for (i_30_ = 1; i_30_ <= length; i_30_ += i_30_) {
    			/* empty */
    		}
    		byte[] is = new byte[i_30_];
    		ArrayUtils.arraycopy(buffer, 0, is, 0, length);
    		buffer = is;
    	}
    	buffer[length++] = (byte) i_29_;
    	return this;
    }
    
    final int getWidth(FontMetrics fontmetrics) {
    	String string;
    	try {
    		string = new String(buffer, 0, length, "ISO-8859-1");
    	} catch (UnsupportedEncodingException unsupportedencodingexception) {
    		string = new String(buffer, 0, length);
    	}
    	return fontmetrics.stringWidth(string);
    }
    
    private final int method925(int i, RSString string, int i_32_) {
    	int[] is = new int[string.length];
    	int[] is_33_ = new int[256];
    	int[] is_34_ = new int[string.length];
    	for (int i_35_ = 0; is_33_.length > i_35_; i_35_++)
    		is_33_[i_35_] = string.length;
    	for (int i_36_ = 1; i_36_ <= string.length; i_36_++) {
    		is[i_36_ - 1] = (string.length << 1) - i_36_;
    		is_33_[string.buffer[i_36_ - 1] & 255] = string.length - i_36_;
	}
	int i_37_ = string.length - i;
	for (int i_38_ = string.length; i_38_ > 0;
	     i_38_--) {
	    is_34_[i_38_ - 1] = i_37_;
	    for (/**/;
		 (string.length >= i_37_
		  && (string.buffer[i_38_ - 1]
		      != string.buffer[i_37_ - 1]));
		 i_37_ = is_34_[i_37_ - 1]) {
		if (-i_38_ + string.length
		    <= is[i_37_ - 1])
		    is[i_37_ - 1] = string.length - i_38_;
	    }
	    i_37_--;
	}
	int i_39_ = i_37_;
	i_37_ = string.length - i_39_ + 1;
	int i_40_ = 1;
	int i_41_ = 0;
	int i_42_ = 1;
	while (i_37_ >= i_42_) {
	    is_34_[i_42_ - 1] = i_41_;
	    for (/**/;
		 (i_41_ >= 1
		  && (string.buffer[i_41_ - 1]
		      != string.buffer[i_42_ - 1]));
		 i_41_ = is_34_[i_41_ - 1]) {
		/* empty */
	    }
	    i_42_++;
	    i_41_++;
	}
	while (string.length > i_39_) {
	    for (int i_43_ = i_40_; i_39_ >= i_43_; i_43_++) {
		if (is[i_43_ - 1]
		    >= -i_43_ + i_39_ + string.length)
		    is[i_43_ - 1]
			= string.length + i_39_ - i_43_;
	    }
	    i_40_ = i_39_ + 1;
	    i_39_ = -is_34_[i_37_ - 1] + i_37_ + i_39_;
	    i_37_ = is_34_[i_37_ - 1];
	}
	int i_44_;
	for (int i_45_ = i_32_ + (string.length - 1);
	     i_45_ < length;
	     i_45_ += Math.max(is_33_[(buffer[i_45_]
				       & 0xff)],
			       is[i_44_])) {
	    for (i_44_ = string.length - 1;
		 (i_44_ >= 0
		  && (buffer[i_45_]
		      == string.buffer[i_44_]));
		 i_44_--)
		i_45_--;
	    if (i_44_ == -1)
		return i_45_ + 1;
	}
	return -1;
    }
    
    final int charAt(int i) {
    	return buffer[i] & 0xff;
    }
    
    final boolean startsWith(RSString string) {
    	if (length < string.length)
    		return false;
    	for (int id = 0; id < string.length; id++) {
    		if (string.buffer[id] != buffer[id])
    			return false;
    	}
    	return true;
    }
    
    final int getIntHash() {
    	int hash = 0;
    	for (int len = 0; length > len; len++)
    		hash = ((hash << 5) - hash + (buffer[len] & 0xff));
    	return hash;
    }
    
    private final long getLongHash() {
    	long hash = 0L;
    	for (int len = 0; length > len; len++)
    		hash = ((long) (buffer[len] & 0xff) + ((hash << 5) - hash));
    	return hash;
    }
    
    final int indexOf(RSString str) {
    	return method925(-1, str, 0);
    }
    
    final RSString method931() {
    	if (!aBoolean1107)
    		throw new IllegalArgumentException();
    	hashCode = 0;
    	if (buffer.length != length) {
    		byte[] is = new byte[length];
    		ArrayUtils.arraycopy(buffer, 0, is, 0, length);
    		buffer = is;
    	}
    	return this;
    }
    
    final int arraycopy(byte[] is, int i, int i_51_, int i_53_) {
    	ArrayUtils.arraycopy(buffer, i, is, i_53_, i_51_ - i);
    	return i_51_ - i;
    }
    
    final boolean equals(RSString string) {
    	if (string == null)
    		return false;
    	if (length != string.length)
    		return false;
    	if (!aBoolean1107 || !string.aBoolean1107) {
    		if (hashCode == 0) {
    			hashCode = getIntHash();
    			if (hashCode == 0)
    				hashCode = 1;
    		}
    		if (string.hashCode == 0) {
    			string.hashCode = string.getIntHash();
    			if (string.hashCode == 0)
    				string.hashCode = 1;
    		}
    		if (string.hashCode != hashCode)
    			return false;
    	}
    	for (int i_55_ = 0; i_55_ < length; i_55_++) {
    		if (buffer[i_55_] != string.buffer[i_55_])
    			return false;
    	}
    	return true;
    }
    
    final RSString trim() {
	int i = length;
	int i_56_;
	for (i_56_ = 0; i_56_ < length; i_56_++) {
	    if (buffer[i_56_] < 0)
		break;
	    if (buffer[i_56_] > 32)
		break;
	}
	for (/**/; (i_56_ < i && buffer[i - 1] >= 0
		    && buffer[i - 1] <= 32); i--) {
	    /* empty */
	}
	if (i_56_ == 0 && length == i)
	    return this;
	RSString instance = new RSString();
	instance.length = i - i_56_;
	instance.buffer
	    = new byte[instance.length];
	for (int i_58_ = 0; instance.length > i_58_; i_58_++)
	    instance.buffer[i_58_]
		= buffer[i_58_ + i_56_];
	return instance;
    }
    
    final RSString substring(int beginIndex, int endIndex) {
	RSString instance = new RSString();
	instance.length = endIndex - beginIndex;
	instance.buffer = new byte[endIndex - beginIndex];
	ArrayUtils.arraycopy(buffer, beginIndex,
			  instance.buffer, 0,
			  instance.length);
	return instance;
    }
    
    final RSString add(int c) {
    	RSString instance = new RSString();
    	instance.buffer = new byte[length + 1];
    	instance.length = length + 1;
    	ArrayUtils.arraycopy(buffer, 0, instance.buffer, 0, length);
    	instance.buffer[length] = (byte) c;
    	return instance;
    }
    
    final RSString toLowerCase() {
    	RSString instance = new RSString();
    	instance.length = length;
    	instance.buffer = new byte[length];
    	for (int len = 0; length > len; len++) {
    		byte c = buffer[len];
    		if (c >= 65 && c <= 90 || c >= -64 && c <= -34 && c != -41)
    			c += 32;
    		instance.buffer[len] = c;
    	}
    	return instance;
    }
    
    final byte[] toByteArray() {
    	byte[] is = new byte[length];
    	ArrayUtils.arraycopy(buffer, 0, is, 0, length);
    	return is;
    }
    
    //public final boolean equals(Object object) {
    //	throw new RuntimeException();
    //}//TODO we dont need this
    
    private final int method940(int i) {
	byte i_73_ = (byte) i;
	for (int i_74_ = 0; i_74_ < length; i_74_++) {
	    if (i_73_ == buffer[i_74_])
		return i_74_;
	}
	return -1;
    }
    
    private final int method941(int i) {
    	if (i < 1 || i > 36)
    		i = 10;
    	boolean bool = false;
    	boolean bool_76_ = false;
    	int i_77_ = 0;
    	for (int i_78_ = 0; length > i_78_; i_78_++) {
    		int i_79_ = buffer[i_78_] & 0xff;
    		if (i_78_ == 0) {
    			if (i_79_ == 45) {
    				bool = true;
    				continue;
    			}
    			if (i_79_ == 43)
    				continue;
    		}
    		if (i_79_ >= 48 && i_79_ <= 57)
    			i_79_ -= 48;
    		else if (i_79_ >= 65 && i_79_ <= 90)
    			i_79_ -= 55;
    		else if (i_79_ >= 97 && i_79_ <= 122)
    			i_79_ -= 87;
    		else
    			throw new NumberFormatException();
    		if (i <= i_79_)
    			throw new NumberFormatException();
    		if (bool)
    			i_79_ = -i_79_;
    		int i_80_ = i_77_ * i + i_79_;
    		if (i_80_ / i != i_77_)
    			throw new NumberFormatException();
    		bool_76_ = true;
    		i_77_ = i_80_;
    	}
    	if (!bool_76_)
    		throw new NumberFormatException();
		return i_77_;
    }
    
    final RSString upperCase() {
    	boolean bool = true;
    	RSString instance = new RSString();
  	 	instance.length = length;
  	 	instance.buffer = new byte[length];
  	 	for (int id = 0; length > id; id++) {
  	 		byte character = buffer[id];
  	 		if (character == 95) {
  	 			bool = true;
  	 			instance.buffer[id] = (byte) 32;
  	 		} else if (character >= 97 && character <= 122 && bool) {
  	 			instance.buffer[id] = (byte) (character - 32);
  	 			bool = false;
  	 		} else {
  	 			instance.buffer[id] = character;
  	 			bool = false;
  	 		}
  	 	}
  	 	return instance;
    }
    
    public final String toString() {
    	return new String(buffer);
    }
    
    final RSString censor() {
    	RSString instance = new RSString();
    	instance.length = length;
    	instance.buffer = new byte[length];
    	for (int id = 0; length > id; id++)
    		instance.buffer[id] = (byte) 42;
    	return instance;
    }
    
    final RSString substring(int beginIndex) {
    	return substring(beginIndex, length);
    }
    
    final RSString format() {
		boolean bool = true;
		RSString istance = new RSString();
		istance.length = length;
		istance.buffer = new byte[length];
		for (int id = 0; id < length; id++) {
			byte character = buffer[id];
			if ((character < 97 || character > 122) && (character < -32 || character > -2 || character == -9)) {
				if (character >= 65 && character <= 90 || character >= -64 && character <= -34 && character != -41) {
					if (!bool)
						character += 32;
					bool = false;
				}
			} else {
				if (bool)
					character -= 32;
				bool = false;
			}
			if (character == 46 || character == 33 || character == 63)
				bool = true;
			istance.buffer[id] = character;
		}
		return istance;
    }
    
    final boolean method946() {
    	return method912(10);
    }
    
    final int toInt() {
    	return method941(10);
    }
    
    static final RSString merge(RSString[] strings, int off, int len) {
		int mergedLength = 0;
		for (int id = 0; id < len; id++) {
			if (strings[id + off] == null)
				strings[off + id] = Client.nullString;
			mergedLength += strings[off + id].length;
		}
		int i_5_ = 0;
		byte[] mergedBuffer = new byte[mergedLength];
		for (int i_6_ = 0; i_6_ < len; i_6_++) {
			RSString class63 = strings[off + i_6_];
			ArrayUtils.arraycopy(class63.buffer, 0, mergedBuffer, i_5_, class63.length);
			i_5_ += class63.length;
		}
		RSString class63 = new RSString();
		class63.length = mergedLength;
		class63.buffer = mergedBuffer;
		return class63;
	}

	static final RSString method848(int i_1_) {
		RSString instance = new RSString();
		instance.buffer = new byte[i_1_];
		instance.length = 0;
		return instance;
	}

	static final RSString valueOf(int i) {
		return method650(false, 10, i);
	}

	static final RSString[] constructNumbers(RSString[] actions) {
		RSString[] actionWithNumbers = new RSString[5];
		for (int id = 0; id < 5; id++) {
			actionWithNumbers[id] = Client.linkRSStrings(
					new RSString[] {
							RSString.valueOf(id),
							Client.colonWithSpace
					});
			if (actions != null && actions[id] != null)
				actionWithNumbers[id] = Client.linkRSStrings(
						new RSString[] {
								actionWithNumbers[id],
								actions[id]
						});
		}
		return actionWithNumbers;
	}

	static final RSString method650(boolean bool, int i, int i_25_) {
		int i_26_ = 1;
		if (i < 1 || i > 36)
		    i = 10;
		for (int i_27_ = i_25_ / i; i_27_ != 0; i_27_ /= i)
		    i_26_++;
		int i_28_ = i_26_;
		if (i_25_ < 0 || bool)
		    i_28_++;
		byte[] is = new byte[i_28_];
		if (i_25_ >= 0) {
		    if (bool)
			is[0] = (byte) 43;
		} else
		    is[0] = (byte) 45;
		for (int i_29_ = 0; i_26_ > i_29_; i_29_++) {
		    int i_30_ = i_25_ % i;
		    i_25_ /= i;
		    if (i_30_ < 0)
			i_30_ = -i_30_;
		    if (i_30_ > 9)
			i_30_ += 39;
		    is[i_28_ - (i_29_ + 1)] = (byte) (i_30_ + 48);
		}
		RSString instance = new RSString();
		instance.buffer = is;
		instance.length = i_28_;
		return instance;
	}

	static final RSString toString(long hash) {
		if (hash <= 0L || hash >= 6582952005840035281L)
			return null;
		if (hash % 37L == 0L)
			return null;
		int i_6_ = 0;
		long l_7_ = hash;
		while (l_7_ != 0L) {
			l_7_ /= 37L;
			i_6_++;
		}
		byte[] is = new byte[i_6_];
		while (hash != 0L) {
			long l_8_ = hash;
			hash /= 37L;
			is[--i_6_] = Client.validChars[(int) (-(hash * 37L) + l_8_)];
		}
		RSString instance = new RSString();
		instance.buffer = is;
		instance.length = is.length;
		return instance;
	}

	static final RSString createRSString(String string) {
		byte[] bytes = string.getBytes();
		int off = 0;
		int len = bytes.length;
		RSString instance = new RSString();
		instance.buffer = new byte[len];
		while (off < len) {
			int i_11_ = bytes[off++] & 0xff;
			//if (i_11_ <= 45 && i_11_ >= 40) {
			//	if (len <= off)
			//		break;
			//	int i_12_ = bytes[off++] & 0xff;
			//	instance.buffer[instance.length++] = (byte) (i_12_ - 48 + (i_11_ * 43 - 1720));
			//} else
				instance.buffer[instance.length++] = (byte) i_11_;
		}
		instance.method931();
		return instance.cacheString();
	}

	static Class initClass(String string) {
		Class var_class;
		try {
			var_class = Class.forName(string);
		} catch (ClassNotFoundException classnotfoundexception) {
			throw new NoClassDefFoundError(classnotfoundexception.getMessage());
		}
		return var_class;
    }
}
