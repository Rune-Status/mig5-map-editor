package org.mapeditor.client;

/* Class54 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

public abstract class GraphicsBuffer {
	
    Image image;
    int width;
    int[] pixels;
    int height;
    
    abstract void init(Component component, int width, int height, boolean isRasterPremultiplied);
    
    abstract void drawGraphics(Graphics graphics, int x, int y);
    
    protected GraphicsBuffer() {
	/* empty */
    }
    
    final void initDrawingArea() {
    	Graphics2D.init2dCanvas(pixels, width, height);
    }
}
