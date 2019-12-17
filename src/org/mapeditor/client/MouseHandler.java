package org.mapeditor.client;

/* Class4 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

public final class MouseHandler implements MouseListener, MouseMotionListener {
    
    public final synchronized void mouseDragged(MouseEvent mouseevent) {
		if (Client.mouseHandler != null) {
		    Client.mouseIdleTime = 0;
		    Client.currentMouseX = mouseevent.getX();
		    Client.currentMouseY = mouseevent.getY();
		    if(Client.wheelDragEnabled) {
		    	int mX = mouseevent.getX();
		    	int mY = mouseevent.getY();
		    	Client.wheelRotateYaw = Client.wheelMouseX != -1 ? Client.wheelMouseX - mX : -1;
		    	Client.wheelRotatePitch = Client.wheelMouseX != -1 ? Client.wheelMouseY - mY : -1;
			    Client.wheelMouseX = mX;
			    Client.wheelMouseY = mY;
		    }
		}
    }
    
    public final synchronized void mouseEntered(MouseEvent mouseevent) {
    	if (Client.mouseHandler != null) {
    		Client.mouseIdleTime = 0;
		    Client.currentMouseX = mouseevent.getX();
		    Client.currentMouseY = mouseevent.getY();
    	}
    }
    
    public final synchronized void mouseExited(MouseEvent mouseevent) {
		if (Client.mouseHandler != null) {
		    Client.mouseIdleTime = 0;
		    Client.currentMouseX = -1;
		    Client.currentMouseY = -1;
		    Client.wheelDragEnabled = false;
		    Client.wheelMouseX = -1;
		    Client.wheelMouseX = -1;
		}
    }
    
    public final synchronized void mouseMoved(MouseEvent mouseevent) {
		if (Client.mouseHandler != null) {
		    Client.mouseIdleTime = 0;
		    Client.currentMouseX = mouseevent.getX();
		    Client.currentMouseY = mouseevent.getY();
		}
    }
    
    public final synchronized void mouseReleased(MouseEvent mouseevent) {
		if (Client.mouseHandler != null) {
		    Client.mouseIdleTime = 0;
		    Client.currenMouseAction2 = 0;
		    Client.wheelDragEnabled = false;
		    Client.wheelMouseX = -1;
		    Client.wheelMouseX = -1;
		}
		mouseDown1 = false;
		mouseDown2 = false;
		if (mouseevent.isPopupTrigger())
		    mouseevent.consume();
    }
    
    public final synchronized void mousePressed(MouseEvent mouseevent) {
		if (Client.mouseHandler != null) {
		    Client.mouseIdleTime = 0;

		    if(!(Client.wheelDragEnabled = mouseevent.getButton() == MouseEvent.BUTTON2)) {
			    Client.currentClickX = mouseevent.getX();
			    Client.currentClickY = mouseevent.getY();
			    Client.currentClickTime = System.currentTimeMillis();
			    if (!mouseevent.isMetaDown()) {
			    	mouseDown1 = true;
			    	Client.currenMouseAction = 1;
			    	Client.currenMouseAction2 = 1;
			    } else {
			    	mouseDown2 = true;
			    	Client.currenMouseAction = 2;
			    	Client.currenMouseAction2 = 2;
			    }
		    }
		}
    }
    
    public static boolean mouseDown1 = false;
    public static boolean mouseDown2 = false;
    
    
    public final void mouseClicked(MouseEvent mouseevent) {
		if (mouseevent.isPopupTrigger())
		    mouseevent.consume();
    }
}