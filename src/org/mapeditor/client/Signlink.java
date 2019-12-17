package org.mapeditor.client;

/* Class45 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.applet.Applet;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

public class Signlink implements Runnable {
	
    private String privateParentPath = null;
    public static String vendor;
    public FileOnDisk[] indexFile;
    public static String version;
    private SignlinkNode next;
    public FileOnDisk crcFile;
    private Thread thread;
    public FileOnDisk cacheFile;
    public String publicCachePath;
    private static String userHome;
    private String privateCachePath;
    private SignlinkNode current;
    public String publicParentPath = null;
    private Runnable_Impl1 aRunnable_Impl1_789;
    public Applet thisApplet;
    private InetAddress address;
    public static Method traversalKey;
    public static int three = 3;
    public int UID;
    private boolean closed;
    
    public final SignlinkNode method758(URL url) {
		return putNode(url, 4, 0);
    }
    
    public final Runnable_Impl1 method759() {
    	return aRunnable_Impl1_789;
    }
    
    public final SignlinkNode collectClassFieldInfo(String string, Class var_class) {
    	return putNode(new Object[] { var_class, string }, 10, 0);
    }
    
    public final SignlinkNode method761(int i_1_) {
    	return putNode(null, 3, i_1_);
    }
    
    public final SignlinkNode collectClassMethodInfo(Class[] var_classes, String string, Class var_class) {
    	return putNode(new Object[] { var_class, string, var_classes }, 9, 0);
    }
    
    private final SignlinkNode putNode(Object objectData, int type, int intData) {
    	SignlinkNode node = new SignlinkNode();
    	node.objData = objectData;
    	node.intData = intData;
		node.type = type;
		synchronized (this) {
			if (next != null) {
				next.prev = node;
				next = node;
			} else
				next = current = node;
			this.notify();
		}
		return node;
    }
    
    private final void buildPath(int modeWhat) {
    	if (modeWhat < 32 || modeWhat > 34)
    		modeWhat = 32;
    	String[] strings= { "./cache/" };
    	for (int i_8_ = 0; i_8_ < strings.length; i_8_++) {
    		try {
    			String string_9_ = strings[i_8_];
    			if (string_9_.length() > 0) {
    				File file = new File(string_9_);
    				if (!file.exists())
    					continue;
    			}
    			File file = new File(string_9_);
    			if (!file.exists() && !file.mkdir())
    				continue;
    			publicParentPath = privateParentPath = file.getParent() + "/";
    			publicCachePath = privateCachePath = file.getPath() + "/";
    			System.out.println(publicCachePath);
    		} catch (Exception exception) {
    			continue;
    		}
    		return;
    	}
    	throw new RuntimeException();
    }
    
    public final void method765() {
    	System.out.println("method765 signlink");
    	synchronized (this) {
    		closed = true;
    		this.notifyAll();
    	}
    	try {
    		thread.join();
    	} catch (InterruptedException interruptedexception) {
    		/* empty */
    	}
    	if (aRunnable_Impl1_789 != null)
    		aRunnable_Impl1_789.close();
    	if (cacheFile != null) {
    		try {
    			cacheFile.close();
    		} catch (IOException ioexception) {
    			/* empty */
	 	   }
    	}
    	if (crcFile != null) {
    		try {
    			crcFile.close();
    		} catch (IOException ioexception) {
    			/* empty */
    		}
    	}
    	if (indexFile != null) {
    		for (int id = 0; id < indexFile.length; id++) {
    			if (indexFile[id] != null) {
    				try {
    					indexFile[id].close();
    				} catch (IOException ioexception) {
    					/* empty */
    				}
    			}
    		}
    	}
    }
    
    public final SignlinkNode method766() {
    	return null;
    }
    
    public final SignlinkNode startThread(Runnable runnable, int priority) {
    	return putNode(runnable, 2, priority);
    }
    
    private final void handleUID() {
    	try {
    		File uidFile = new File(privateParentPath + "uid.dat");
    		if (!uidFile.exists() || uidFile.length() < 4L) {
    			DataOutputStream dataoutputstream = new DataOutputStream(new FileOutputStream(privateParentPath + "uid.dat"));
    			dataoutputstream.writeInt((int) (Math.random() * 9.9999999E7));
    			dataoutputstream.close();
		    }
    	} catch (Exception exception) {
    		/* empty */
		}
   	 	try {
   	 		DataInputStream datainputstream = new DataInputStream(new FileInputStream(privateParentPath + "uid.dat"));
   	 		UID = datainputstream.readInt() + 1;
   	 		datainputstream.close();
   	 	} catch (Exception exception) {
   	 		/* empty */
   	 	}
    }
    
    public final void run() {
    	for (;;) {
    		SignlinkNode node;
    		synchronized (this) {
    			for (;;) {
    				if (closed)
    					return;
    				if (current != null) {
    					node = current;
    					current = current.prev;
    					if (current == null)
    						next = null;
    					break;
    				}
    				try {
    					this.wait();
    				} catch (InterruptedException interruptedexception) {
    					/* empty */
    				}
    			}
    		}
    		try {
				int type = node.type;
				if (type == 1) {
					node.value = new Socket(address, node.intData);
				} else if (type == 2) {
					Thread thread = new Thread((Runnable) node.objData);
					thread.setDaemon(true);
					thread.start();
					thread.setPriority(node.intData);
					node.value = thread;
				} else if (type == 3) { //Custom node added
				    String string = ((((Integer) node.value) >> 24) & 0xff) + "." +
				    				((((Integer) node.value) >> 16) & 0xff) + "." +
				    				((((Integer) node.value) >> 8) & 0xFF) + "." +
				    				((((Integer) node.value)));
				    node.value = InetAddress.getByName(string).getHostName();
				} else if (type == 4) {
					node.value = new DataInputStream(((URL) node.objData).openStream());
    			} else if (type == 9) {
					Object[] objects = (Object[]) node.objData;
					node.value = ((Class) objects[0]).getDeclaredMethod(((String) objects[1]), (Class[]) objects[2]);
				} else if (type == 10) {
				    Object[] objects = (Object[]) node.objData;
				    node.value = ((Class) objects[0]).getDeclaredField((String) objects[1]);
				}
				node.status = 1;
    		} catch (Exception exception) {
    			node.status = 2;
    		}
		}
    }
    
    public final SignlinkNode startConnection(int port) {
    	return putNode(null, 1, port);
    }
    
    public Signlink(boolean loadCache, Applet applet, InetAddress inetaddress, int modeWhat, int indexes) throws IOException {
    	cacheFile = null;
    	current = null;
    	publicCachePath = null;
    	crcFile = null;
    	next = null;
    	thisApplet = null;
    	UID = 0;
    	privateCachePath = null;
		closed = false;
		vendor = "Unknown";
		thisApplet = applet;
		address = inetaddress;
		version = "1.1";
		try {
			vendor = System.getProperty("java.vendor");
		    version = System.getProperty("java.version");
		    userHome = System.getProperty("user.home");
		    if (userHome != null) {
		    	String home = userHome;
		    	StringBuilder stringbuilder = new StringBuilder(home);
		    	userHome = stringbuilder.append("/").toString();
		    }
		} catch (Exception exception) {
			/* empty */
		}
		try {
			if (applet == null)
				traversalKey = (Class.forName("java.awt.Component").getDeclaredMethod("setFocusTraversalKeysEnabled", new Class[] { Boolean.TYPE }));
			else
				traversalKey = (applet.getClass().getMethod("setFocusTraversalKeysEnabled", new Class[] { Boolean.TYPE }));
		} catch (Exception exception) {
		    /* empty */
		}
		if (loadCache) {
			buildPath(modeWhat);
			cacheFile = new FileOnDisk(new File(privateCachePath + "main_file_cache.dat2"), "rw", -1L);
			indexFile = new FileOnDisk[indexes];
			for (int idx = 0; idx < indexes; idx++)
				indexFile[idx] = new FileOnDisk(new File(privateCachePath + "main_file_cache.idx" + idx), "rw", 1048576L);
			crcFile = new FileOnDisk(new File(privateCachePath + "main_file_cache.idx255"), "rw", 1048576L);
			handleUID();
		}
		closed = false;
		thread = new Thread(this);
		thread.setPriority(10);
		thread.setDaemon(true);
		thread.start();
    }
}
