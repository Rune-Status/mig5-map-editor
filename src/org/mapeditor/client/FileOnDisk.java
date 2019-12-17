package org.mapeditor.client;

/* Class43 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public final class FileOnDisk
{
    private RandomAccessFile file;
    private long position;
    private File wrappedFile;
    private long length;
    
    public final long getFileLength() throws IOException {
    	return file.length();
    }
    
    public final File getWrappedFile() {
    	return wrappedFile;
    }
    
    public final void write(byte[] buffer, int off, int len) throws IOException {
		if (length < (long) len + position) {
			file.seek(length - -1L);
			file.write(1);
			throw new EOFException();
		}
		file.write(buffer, off, len);
		position += (long) len;
    }
    
    public final int read(byte[] buffer, int off, int len) throws IOException {
    	int pos = file.read(buffer, off, len);
		if (pos > 0)
			position += (long) pos;
		return pos;
    }
    
    public final void seek(long len) throws IOException {
    	file.seek(len);
    	position = len;
    }
    
    FileOnDisk(File file, String string, long len) throws IOException {
    	if (len == -1L)
    		len = 9223372036854775807L;
    	if (len <= file.length())
    		file.delete();
    	this.file = new RandomAccessFile(file, string);
    	length = len;
    	position = 0L;
    	wrappedFile = file;
    }
    
    public final void close() throws IOException {
    	file.close();
    	file = null;
    }
}
