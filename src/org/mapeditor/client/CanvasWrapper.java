package org.mapeditor.client;

/* Canvas_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.*;

class CanvasWrapper extends Canvas {
	
	private static final long serialVersionUID = -2451230347133686425L;
	private Component component;
    
    public final void paint(Graphics graphics) {
    	component.paint(graphics);
    }
    
    CanvasWrapper(Component component) {
    	this.component = component;
    }
    
    public final void update(Graphics graphics) {
    	component.update(graphics);
    }
}
