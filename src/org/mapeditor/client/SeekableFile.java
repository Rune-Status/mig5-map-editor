package org.mapeditor.client;

/* Class44 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.io.EOFException;
import java.io.IOException;

final class SeekableFile {
	
    private byte[] aByteArray757;
    private long aLong759 = -1L;
    private FileOnDisk aClass43_761;
    private long aLong762;
    private int anInt763 = 0;
    private byte[] aByteArray764;
    private long position;
    private long fileLength;
    private long aLong771;
    private int anInt772;
    private long aLong773 = -1L;
    
    private final void method747() throws IOException {
    	anInt772 = 0;
    	if (position != aLong771) {
    		aClass43_761.seek(position);
    		aLong771 = position;
    	}
    	aLong759 = position;
    	for(int i_0_; aByteArray764.length > anInt772; anInt772 += i_0_) {
    		i_0_ = aClass43_761.read(aByteArray764, anInt772, aByteArray764.length - anInt772);
    		if (i_0_ == -1)
    			break;
    		aLong771 += (long) i_0_;
	    }
    }
    
    final void close() throws IOException {
	    method754();
		aClass43_761.close();
    }
    
    final void seek(long l) {
    	if (l >= 0L)
    		position = l;
    }
    
    private final void method754() throws IOException {
	    if (aLong773 != -1L) {
		if (aLong773 != aLong771) {
		    aClass43_761.seek(aLong773);
		    aLong771 = aLong773;
		}
		long l = -1L;
		long l_21_ = -1L;
		aClass43_761.write(aByteArray757, 0, anInt763);
		if ((long) anInt763 + aLong773 > aLong759
		    && (aLong759 + (long) anInt772
			>= (long) anInt763 + aLong773))
		    l_21_ = (long) anInt763 + aLong773;
		else if (aLong773 < aLong759 - -(long) anInt772
			 && (aLong773 + (long) anInt763
			     >= aLong759 + (long) anInt772))
		    l_21_ = (long) anInt772 + aLong759;
		aLong771 += (long) anInt763;
		if (aLong762 < aLong771)
		    aLong762 = aLong771;
		if (aLong773 >= aLong759
		    && aLong773 < aLong759 + (long) anInt772)
		    l = aLong773;
		else if (aLong773 <= aLong759
			 && aLong759 < aLong773 - -(long) anInt763)
		    l = aLong759;
		if (l > -1L && l < l_21_) {
		    int i_22_ = (int) (-l + l_21_);
		    ArrayUtils.arraycopy(aByteArray757, (int) (l - aLong773),
				      aByteArray764, (int) (l - aLong759),
				      i_22_);
		}
		anInt763 = 0;
		aLong773 = -1L;
	    }
    }
    
    final void write(byte[] buffer, int off, int len) throws IOException {
		try {
		    if (fileLength < position + (long) len)
			fileLength = (long) len + position;
		    if (aLong773 != -1L
			&& (aLong773 > position
			    || aLong773 - -(long) anInt763 < position))
			method754();
		    if (aLong773 != -1L
			&& (aLong773 + (long) aByteArray757.length
			    < position - -(long) len)) {
			int i_24_ = (int) (aLong773
					   + (-position
					      + (long) aByteArray757.length));
			ArrayUtils.arraycopy(buffer, off, aByteArray757,
					  (int) (-aLong773 + position), i_24_);
			len -= i_24_;
			position += (long) i_24_;
			off += i_24_;
			anInt763 = aByteArray757.length;
			method754();
		    }
		    if (aByteArray757.length < len) {
			if (position != aLong771) {
			    aClass43_761.seek(position);
			    aLong771 = position;
			}
			aClass43_761.write(buffer, off, len);
			aLong771 += (long) len;
			long l = -1L;
			if (aLong762 < aLong771)
			    aLong762 = aLong771;
			if (position >= aLong759
			    && aLong759 - -(long) anInt772 > position)
			    l = position;
			else if (aLong759 >= position
				 && aLong759 < position - -(long) len)
			    l = aLong759;
			long l_25_ = -1L;
			if ((long) len + position <= aLong759
			    || (aLong759 - -(long) anInt772
				< position - -(long) len)) {
			    if ((long) anInt772 + aLong759 > position
				&& ((long) anInt772 + aLong759
				    <= (long) len + position))
				l_25_ = aLong759 - -(long) anInt772;
			} else
			    l_25_ = position - -(long) len;
			if (l > -1L && l_25_ > l) {
			    int i_26_ = (int) (l_25_ - l);
			    ArrayUtils.arraycopy(buffer,
					      (int) (-position
						     + ((long) off + l)),
					      aByteArray764,
					      (int) (-aLong759 + l), i_26_);
			}
			position += (long) len;
		    } else if (len > 0) {
			if (aLong773 == -1L)
			    aLong773 = position;
			ArrayUtils.arraycopy(buffer, off, aByteArray757,
					  (int) (-aLong773 + position), len);
			position += (long) len;
			if (position - aLong773 > (long) anInt763)
			    anInt763 = (int) (-aLong773 + position);
		    }
		} catch (IOException ioexception) {
		    aLong771 = -1L;
		    throw ioexception;
		}
    }
    
    final void read(byte[] buffer, int off, int len)
	throws IOException {
	    try {
		if (len + off > buffer.length)
		    throw new ArrayIndexOutOfBoundsException(-buffer.length
							     + (off + len));
		if (aLong773 != -1L && position >= aLong773
		    && (long) len + position <= (long) anInt763 + aLong773) {
		    ArrayUtils.arraycopy(aByteArray757,
				      (int) (-aLong773 + position), buffer, off,
				      len);
		    position += (long) len;
		    return;
		}
		int i_30_ = off;
		long l = position;
		int i_31_ = len;
		if (position >= aLong759
		    && (long) anInt772 + aLong759 > position) {
		    int i_32_
			= (int) (-position - -aLong759 + (long) anInt772);
		    if (len < i_32_)
			i_32_ = len;
		    len -= i_32_;
		    ArrayUtils.arraycopy(aByteArray764,
				      (int) (position + -aLong759), buffer, off,
				      i_32_);
		    off += i_32_;
		    position += (long) i_32_;
		}
		if (len > aByteArray764.length) {
		    aClass43_761.seek(position);
		    aLong771 = position;
		    while (len > 0) {
			int i_33_ = aClass43_761.read(buffer, off, len);
			if (i_33_ == -1)
			    break;
			off += i_33_;
			len -= i_33_;
			aLong771 += (long) i_33_;
			position += (long) i_33_;
		    }
		} else if (len > 0) {
		    method747();
		    int i_34_ = len;
		    if (anInt772 < i_34_)
			i_34_ = anInt772;
		    ArrayUtils.arraycopy(aByteArray764, 0, buffer, off, i_34_);
		    position += (long) i_34_;
		    len -= i_34_;
		    off += i_34_;
		}
		if (aLong773 != -1L) {
		    if (aLong773 > position && len > 0) {
			int i_35_ = (int) (-position + aLong773) + off;
			if (len + off < i_35_)
			    i_35_ = off + len;
			while (off < i_35_) {
			    len--;
			    buffer[off++] = (byte) 0;
			    position++;
			}
		    }
		    long l_36_ = -1L;
		    long l_37_ = -1L;
		    if (l > aLong773 || l - -(long) i_31_ <= aLong773) {
			if (l >= aLong773 && (long) anInt763 + aLong773 > l)
			    l_36_ = l;
		    } else
			l_36_ = aLong773;
		    if (l < aLong773 + (long) anInt763
			&& aLong773 + (long) anInt763 <= l + (long) i_31_)
			l_37_ = (long) anInt763 + aLong773;
		    else if (aLong773 < (long) i_31_ + l
			     && aLong773 + (long) anInt763 >= l + (long) i_31_)
			l_37_ = l - -(long) i_31_;
		    if (l_36_ > -1L && l_36_ < l_37_) {
			int i_38_ = (int) (l_37_ - l_36_);
			ArrayUtils.arraycopy(aByteArray757,
					  (int) (-aLong773 + l_36_), buffer,
					  (int) (l_36_ + -l) + i_30_, i_38_);
			if (l_37_ > position) {
			    len -= -position + l_37_;
			    position = l_37_;
			}
		    }
		}
	    } catch (IOException ioexception) {
		aLong771 = -1L;
		throw ioexception;
	    }
	    if (len > 0)
		throw new EOFException();
    }
    
    final long length() {
    	return fileLength;
    }
    
    SeekableFile(FileOnDisk class43, int i, int i_39_) throws IOException {
	    aClass43_761 = class43;
	    fileLength = aLong762 = class43.getFileLength();
	    aByteArray757 = new byte[i_39_];
	    aByteArray764 = new byte[i];
	    position = 0L;
    }
}

