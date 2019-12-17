package org.mapeditor.client;

/* Applet_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.applet.Applet;
import java.applet.AppletContext;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.InetAddress;
import java.net.URL;

public abstract class GameShell extends Applet
		implements Runnable, FocusListener, WindowListener {
	
    private boolean errorThrown = false;
    
    public final void focusLost(FocusEvent focusevent) {
    	Client.appletFocused = false;
    }
    
    public final URL getCodeBase() {
    	if (Client.clientFrame != null)
    		return null;
    	if (Client.gameSignlink != null
    			&& Client.gameSignlink.thisApplet != this)
    		return Client.gameSignlink.thisApplet.getCodeBase();
    	return super.getCodeBase();
    }
    
    abstract void close();
    
    final synchronized void addCanvas() {
    	Container container;
    	if (Client.clientFrame == null)
		    container = Client.gameSignlink.thisApplet;
		else
			container = Client.clientFrame;
    	if (Client.canvas != null) {
    		Client.canvas.removeFocusListener(this);
    		container.remove(Client.canvas);
	    }
    	Client.canvas = new CanvasWrapper(this);
    	container.add(Client.canvas);
    	Client.canvas.setSize(Client.sizeX, Client.sizeY);
    	Client.canvas.setVisible(true);
    	if (Client.clientFrame == null)
    		Client.canvas.setLocation(0, 0);
    	else {
    		Insets insets = Client.clientFrame.getInsets();
    		Client.canvas.setLocation(insets.left, insets.top);
    	}
    	//Client.canvas.addFocusListener(this);
    	//Client.canvas.requestFocus();
	    Client.redrawGameframe = true;
	    Client.redrawCanvas = false;
	    Client.appletLauchTimer = System.currentTimeMillis();
    }
    
    public final void initFrame(InetAddress inetaddress, int x, int y,
			int rev, int indexes, int modeWhat) {
    	try {
    		Client.sizeY = y;
    		Client.currentScreen = this;
    		Client.revision = rev;
    		Client.sizeX = x;
    		Client.clientFrame.setVisible(true);
    		Insets insets = Client.clientFrame.getInsets();
    		Client.clientFrame.setSize((insets.right + insets.left + x), (y + insets.top + insets.bottom));
    		Client.errorSignlink = Client.gameSignlink = new Signlink(true, null, inetaddress, modeWhat, indexes);
    		Client.gameSignlink.startThread(this, 1);
    	} catch (Exception exception) {
    		Client.throwError(exception, null);
    	}
    }
    
    public final void focusGained(FocusEvent focusevent) {
    	Client.appletFocused = true;
    	Client.redrawGameframe = true;
    }
    
    public final synchronized void paint(Graphics graphics) {
    	if (this == Client.currentScreen && !Client.destroyed) {
    		Client.redrawGameframe = true;
    		if (Signlink.version != null
    				&& Signlink.version.startsWith("1.5")
    				&& (System.currentTimeMillis() - Client.appletLauchTimer > 1000L)) {
    			Rectangle rectangle = graphics.getClipBounds();
    			if (rectangle != null)
    				System.out.println("Rect: "+rectangle.x+","+rectangle.y+","+rectangle.width+","+rectangle.height);
    			else
    				System.out.println("Rect: null");
    			if (rectangle == null
    					|| (Client.sizeX <= rectangle.width
    							&& Client.sizeY <= rectangle.height)) {
    				Client.redrawCanvas = true;
    				System.out.println("redraw");
    			}
    		}
    	}
    }
    
    public final void windowDeactivated(WindowEvent windowevent) {
    	/* empty */
    }
    
    public final void windowOpened(WindowEvent windowevent) {
    	/* empty */
    }
    
    public final void start() {
    	if (Client.currentScreen == this && !Client.destroyed)
    		Client.processStopTime = 0L;
    }
    
    public final void stop() {
    	if (this == Client.currentScreen && !Client.destroyed)
    		Client.processStopTime = System.currentTimeMillis() + 4000L;
    }
    
    public final void windowDeiconified(WindowEvent windowevent) {
	/* empty */
    }
    
    public final void windowClosed(WindowEvent windowevent) {
	/* empty */
    }
    
    final void error(String string) {
    	if (!errorThrown) {
    		errorThrown = true;
    		System.out.println("error_game_" + string);
    		try {
    			getAppletContext().showDocument(new URL(getCodeBase(), "error_game_" + string + ".ws"));
		    } catch (Exception exception) {
		    	/* empty */
		    }
		}
    }
    
    public final void windowClosing(WindowEvent windowevent) {
    	destroy();
    }
    
    private final void processGameLoop() {
		long newTime = System.currentTimeMillis();
		long oldTime = Client.gameLoopTime[Client.gameLoopLength];
		if (oldTime == (long) 0) {
		    /* empty */
		}
		Client.gameLoopTime[Client.gameLoopLength] = newTime;
		Client.gameLoopLength = Client.gameLoopLength + 1 & 0x1f;
		synchronized (this) {
		    Client.awtFocus = Client.appletFocused;
		}
		gameLoop();
    }
    
    public final void update(Graphics graphics) {
    	paint(graphics);
    }
    
    abstract void processDrawing();
    
    public abstract void init();
    
    public static final void constructSignlink(Signlink signlink) {
    	Client.errorSignlink = Client.gameSignlink = signlink;
    }
    
    public final String getParameter(String param) {
    	if (Client.clientFrame != null)
    		return null;
    	if (Client.gameSignlink != null
    			&& Client.gameSignlink.thisApplet != this)
    		return Client.gameSignlink.thisApplet.getParameter(param);
    	return super.getParameter(param);
    }
    
    final boolean validHost() {
		String host = getDocumentBase().getHost().toLowerCase();
		if (host.endsWith("jagex.com"))
		    return true;
		if (host.endsWith("runescape.com"))
		    return true;
		if (host.endsWith("127.0.0.1"))
		    return true;
		for (/**/;
		     (host.length() > 0 && host.charAt(host.length() - 1) >= '0'
		      && host.charAt(host.length() - 1) <= '9');
		     host = host.substring(0, host.length() - 1)) {
		    /* empty */
		}
		if (host.endsWith("192.168.1."))
		    return true;
		error("invalidhost");
		return false;
    }
    
    abstract void constructSettings();
    
    public final void run() {
    	try {
    		if (Signlink.vendor != null) {
    			String vendor = Signlink.vendor.toLowerCase();
    			if (vendor.indexOf("sun") == -1 && vendor.indexOf("apple") == -1) {
    				if (vendor.indexOf("ibm") != -1 && (Signlink.version == null || Signlink.version.equals("1.4.2"))) {
    					error("wrongjava");
    					return;
    				}
    			} else {
    				String version = Signlink.version;
    				if (version.equals("1.1") || version.startsWith("1.1.") || version.equals("1.2")
    						|| version.startsWith("1.2.")) {
    					error("wrongjava");
    					return;
    				}
    				Client.sleepModifier1 = 5;
    			}
    		}
    		addCanvas();
    		Client.fullscreenGraphics = Client.constructGraphicsBuffer(Client.canvas, Client.sizeX, Client.sizeY);
    		constructSettings();
    		Client.screenTimer = Client.constructTimer();
    		Client.screenTimer.start();
    		while (Client.processStopTime == 0L || (System.currentTimeMillis() < Client.processStopTime)) {
    			Client.gameLoopCount = Client.screenTimer.sleep(Client.sleepModifier1, Client.sleepModifier2);
    			for (int i = 0; i < Client.gameLoopCount; i++)
    				processGameLoop();
    			refreshCanvas();
    		}
    	} catch (Exception exception) {
    		Client.throwError(exception, null);
    		error("crash");
    	}
    	destroyApplet();
    }
    
    final void initApplet(int x, int y, int revision, int modeWhat) {
		do {
			try {
				if (Client.currentScreen != null)
					error("alreadyloaded");
				else {
					Client.currentScreen = this;
					Client.revision = revision;
					Client.sizeX = x;
					Client.sizeY = y;
					if (Client.gameSignlink == null)
						Client.errorSignlink = Client.gameSignlink = new Signlink(false, this, InetAddress.getByName(getCodeBase().getHost()), modeWhat, 0);
					Client.gameSignlink.startThread(this, 1);
				}
			} catch (Exception exception) {
				Client.throwError(exception, null);
				error("crash");
				break;
			}
			break;
		} while (false);
    }
    
    abstract void gameLoop();
    
    abstract void resetFields();
    
    public final URL getDocumentBase() {
		if (Client.clientFrame != null)
		    return null;
		if (Client.gameSignlink != null
		    && Client.gameSignlink.thisApplet != this)
		    return Client.gameSignlink.thisApplet.getDocumentBase();
		return super.getDocumentBase();
    }
    
    private final synchronized void destroyApplet() {
		if (!Client.destroyed) {
		    Client.destroyed = true;
		    try {
		    	Client.canvas.removeFocusListener(this);
		    } catch (Exception exception) {
			/* empty */
		    }
		    try {
		    	close();
		    } catch (Exception exception) {
			/* empty */
		    }
		    if (Client.clientFrame != null) {
		    	try {
		    		System.exit(0);
				} catch (Throwable throwable) {
					/* empty */
				}
		    }
		    if (Client.gameSignlink != null) {
		    	try {
		    		Client.gameSignlink.method765();
		    	} catch (Exception exception) {
		    		/* empty */
		    	}
		    }
		    resetFields();
		}
    }
    
    public final void windowActivated(WindowEvent windowevent) {
    	/* empty */
    }
    
    private final void refreshCanvas() {
    	long newTime = System.currentTimeMillis();
    	long oldTime = Client.fpsCache[Client.fpsCacheLength];
    	Client.fpsCache[Client.fpsCacheLength] = newTime;
    	Client.fpsCacheLength = Client.fpsCacheLength + 1 & 0x1f;
    	if (oldTime != 0L && oldTime < newTime) {
    		int timeDiff = (int) (newTime - oldTime);
    		Client.fps = ((timeDiff >> 1) + 32000) / timeDiff;
    	}
    	if (Client.canvasRefreshCycle++ > 50) {
    		Client.canvasRefreshCycle -= 50;
    		Client.redrawGameframe = true;
    		Client.canvas.setSize(Client.sizeX, Client.sizeY);
    		Client.canvas.setVisible(true);
    		if (Client.clientFrame == null)
    			Client.canvas.setLocation(0, 0);
    		else {
    			Insets insets = Client.clientFrame.getInsets();
    			Client.canvas.setLocation(insets.left, insets.top);
    		}
    	}
    	processDrawing();
    }
    
    public final AppletContext getAppletContext() {
    	if (Client.clientFrame != null)
    		return null;
    	if (Client.gameSignlink != null
    			&& this != Client.gameSignlink.thisApplet)
    		return Client.gameSignlink.thisApplet.getAppletContext();
		return super.getAppletContext();
    }
    
    public final void destroy() {
		if (Client.currentScreen == this && !Client.destroyed) {
		    Client.processStopTime = System.currentTimeMillis();
		    Client.sleep(5000L);
		    Client.errorSignlink = null;
		    destroyApplet();
		}
    }
    
    public final void windowIconified(WindowEvent windowevent) {
    	/* empty */
    }
}