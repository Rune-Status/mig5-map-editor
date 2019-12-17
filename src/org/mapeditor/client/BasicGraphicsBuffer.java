package org.mapeditor.client;

/* Class54_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.DirectColorModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.Hashtable;

public final class BasicGraphicsBuffer extends GraphicsBuffer {
	
    private Component component;
    
    final void init(Component component, int w, int h, boolean isRasterPremultiplied) {
    	pixels = new int[w * h + 1];
    	height = h;
    	width = w;
    	DataBufferInt databufferint = new DataBufferInt(pixels, pixels.length);
		DirectColorModel directcolormodel = new DirectColorModel(32, 16711680, 65280, 255);
		WritableRaster writableraster = Raster.createWritableRaster((directcolormodel.createCompatibleSampleModel(width, height)), databufferint, null);
		image = new BufferedImage(directcolormodel, writableraster, isRasterPremultiplied, new Hashtable());
		this.component = component;
		initDrawingArea();
    }
    
    final void drawGraphics(Graphics graphics, int x, int y) {
	    graphics.drawImage(image, x, y, component);
    }
}
