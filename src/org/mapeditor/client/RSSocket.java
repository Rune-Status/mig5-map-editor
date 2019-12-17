package org.mapeditor.client;

/* Class16 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

final class RSSocket implements Runnable {
	
    private boolean closed = false;
    private boolean IOError = false;
    private java.net.Socket socket;
    private InputStream inputStream;
    private byte[] buffer;
    private int writeIndex = 0;
    private OutputStream outputStream;
    private Signlink signlink;
    private SignlinkNode socketThread;
    private int buffIndex = 0;
    
    final int available() throws IOException {
    	if (closed)
    		return 0;
    	return inputStream.available();
    }
    
    protected final void finalize() {
    	close();
    }
    
    public final void run() {
    	try {
    		for (;;) {
    			int off;
    			int len;
    			synchronized (this) {
    				if (writeIndex == buffIndex) {
    					if (closed)
    						break;
    					try {
    						this.wait();
    					} catch (InterruptedException interruptedexception) {
    						/* empty */
    					}
    				}
    				off = writeIndex;
    				if (buffIndex >= writeIndex)
    					len = buffIndex - writeIndex;
    				else
    					len = 5000 - writeIndex;
    			}
    			if (len > 0) {
    				try {
    					outputStream.write(buffer, off, len);
    				} catch (IOException ioexception) {
    					IOError = true;
    				}
    				writeIndex = (len + writeIndex) % 5000;
    				try {
    					if (writeIndex == buffIndex)
    						outputStream.flush();
    				} catch (IOException ioexception) {
    					IOError = true;
    				}
    			}
    		}
    		try {
    			if (inputStream != null)
    				inputStream.close();
    			if (outputStream != null)
    				outputStream.close();
    			if (socket != null)
    				socket.close();
    		} catch (IOException ioexception) {
    			/* empty */
    		}
    		buffer = null;
    	} catch (Exception exception) {
    		Client.throwError(exception, null);
    	}
    }
    
    final void read(byte[] buffer, int off, int len) throws IOException {
    	if (!closed) {
    		while (len > 0) {
    			int avail = inputStream.read(buffer, off, len);
    			if (avail <= 0)
    				throw new EOFException();
    			len -= avail;
    			off += avail;
    		}
    	}
    }
    
    final void close() {
    	if (!closed) {
    		synchronized (this) {
    			closed = true;
    			this.notifyAll();
    		}
    		if (socketThread != null) {
    			while (socketThread.status == 0)
    				Client.sleep(1L);
    			if (socketThread.status == 1) {
    				try {
    					((Thread) socketThread.value).join();
    				} catch (InterruptedException interruptedexception) {
    					/* empty */
    				}
    			}
    		}
    		socketThread = null;
    	}
    }
    
    final int read() throws IOException {
    	if (closed)
    		return 0;
    	return inputStream.read();
    }
    
    final void write(byte[] buf, int off, int len) throws IOException {
    	if (!closed) {
    		if (IOError) {
    			IOError = false;
    			throw new IOException();
    		}
    		if (buffer == null)
    			buffer = new byte[5000];
    		synchronized (this) {
    			for (int id = 0; id < len; id++) {
    				buffer[buffIndex] = buf[id + off];
    				buffIndex = (buffIndex + 1) % 5000;
    				if (buffIndex == (writeIndex + 4900) % 5000)
    					throw new IOException();
    			}
    			if (socketThread == null)
    				socketThread = signlink.startThread(this, 3);
    			this.notifyAll();
    		}
    	}
    }
    
    RSSocket(java.net.Socket socket, Signlink signlink) throws IOException {
    	this.socket = socket;
    	this.signlink = signlink;
    	socket.setSoTimeout(30000);
    	socket.setTcpNoDelay(true);
    	inputStream = socket.getInputStream();
    	outputStream = socket.getOutputStream();
    }
}
