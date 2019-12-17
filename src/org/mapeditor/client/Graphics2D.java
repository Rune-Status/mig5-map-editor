package org.mapeditor.client;

/* Class3_Sub3_Sub12 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class Graphics2D extends NodeSub {
	
    static int bottomY = 0;
    static int height;
    static int bottomX = 0;
    static int topY = 0;
    static int topX = 0;
    static int[] pixels;
    static int width;
    
    static final void drawRect(int x, int y, int width, int height, int color) {
    	drawHorizontalLine(x, y, width, color);
		drawHorizontalLine(x, y + height - 1, width, color);
		drawVerticalLine(x, y, height, color);
		drawVerticalLine(x + width - 1, y, height, color);
    }
    
    static final void drawRect(int x, int y, int width, int height, int color, int alpha) {
    	drawHorizontalLine(x, y, width, color, alpha);
    	drawHorizontalLine(x, y + height - 1, width, color, alpha);
    	if (height >= 3) {
    		drawVerticalLine(x, y + 1, height - 2, color, alpha);
    		drawVerticalLine(x + width - 1, y + 1, height - 2, color, alpha);
    	}
    }
    
    public static final void resetpixels() {
    	int len = 0;
    	int total = width * height - 7;
    	while (len < total) {
    		pixels[len++] = 0;
    		pixels[len++] = 0;
    		pixels[len++] = 0;
    		pixels[len++] = 0;
    		pixels[len++] = 0;
    		pixels[len++] = 0;
    		pixels[len++] = 0;
    		pixels[len++] = 0;
    	}
    	total += 7;
    	while (len < total)
    		pixels[len++] = 0;
    }
    
    private static final void drawHorizontalLine(int x, int y, int w, int color, int alpha) {
		if (y >= topY && y < bottomY) {
		    if (x < topX) {
		    	w -= topX - x;
		    	x = topX;
		    }
		    if (x + w > bottomX)
		    	w = bottomX - x;
		    int srcAlpha = 256 - alpha;
		    int destRed = (color >> 16 & 0xff) * alpha;
		    int destGreen = (color >> 8 & 0xff) * alpha;
		    int destBlue = (color & 0xff) * alpha;
		    int step = x + y * width;
		    for (int offX = 0; offX < w; offX++) {
		    	int srcRed = (pixels[step] >> 16 & 0xff) * srcAlpha;
		    	int srcGreen = (pixels[step] >> 8 & 0xff) * srcAlpha;
		    	int stcBlue = (pixels[step] & 0xff) * srcAlpha;
		    	int srcColor = ((destRed + srcRed >> 8 << 16) + (destGreen + srcGreen >> 8 << 8) + (destBlue + stcBlue >> 8));
		    	pixels[step++] = srcColor;
		    }
		}
    }
    
    static final void drawHorizontalLine(int x, int y, int w, int color) {
    	if (y >= topY && y < bottomY) {
		    if (x < topX) {
		    	w -= topX - x;
		    	x = topX;
		    }
		    if (x + w > bottomX)
		    	w = bottomX - x;
		    int step = x + y * width;
		    for (int offX = 0; offX < w; offX++)
		    	pixels[step + offX] = color;
		}
    }
    
    private static final void drawVerticalLine(int x, int y, int height, int color, int alpha) {
		if (x >= topX && x < bottomX) {
			if (y < topY) {
				height -= topY - y;
				y = topY;
			}
		    if (y + height > bottomY)
		    	height = bottomY - y;
		    int srcAlpha = 256 - alpha;
		    int destRed = (color >> 16 & 0xff) * alpha;
		    int destGreen = (color >> 8 & 0xff) * alpha;
		    int destBlue = (color & 0xff) * alpha;
		    int pixel = x + y * width;
		    for (int offY = 0; offY < height; offY++) {
		    	int srcRed = (pixels[pixel] >> 16 & 0xff) * srcAlpha;
		    	int srcGreen = (pixels[pixel] >> 8 & 0xff) * srcAlpha;
		    	int srcBlue = (pixels[pixel] & 0xff) * srcAlpha;
		    	int srcColor = ((destRed + srcRed >> 8 << 16) + (destGreen + srcGreen >> 8 << 8) + (destBlue + srcBlue >> 8));
		    	pixels[pixel] = srcColor;
		    	pixel += width;
		    }
		}
    }
    
    static final void init2dCanvas(int[] pixels_, int width_, int height_) {
    	pixels = pixels_;
    	width = width_;
    	height = height_;
    	setBounds(0, 0, width_, height_);
    }
    
    static final void setBounds(int x, int y, int w, int h) {
    	if (x < 0)
    		x = 0;
    	if (y < 0)
    		y = 0;
    	if (w > width)
    		w = width;
    	if (h > height)
		    h = height;
    	topX = x;
    	topY = y;
    	bottomX = w;
		bottomY = h;
    }
    
    static final void fillRect(int x, int y, int w, int h, int color) {
		if (x < topX) {
			w -= topX - x;
			x = topX;
		}
		if (y < topY) {
		    h -= topY - y;
		    y = topY;
		}
		if (x + w > bottomX)
		    w = bottomX - x;
		if (y + h > bottomY)
		    h = bottomY - y;
		int difference = width - w;
		int step = x + y * width;
		for (int offY = -h; offY < 0; offY++) {
			for (int offX = -w; offX < 0; offX++)
				pixels[step++] = color;
			step += difference;
		}
    }
    
    static final void resetBounds() {
    	topX = 0;
    	topY = 0;
    	bottomX = width;
    	bottomY = height;
    }
    
    public static void resetPixels() {
    	pixels = null;
    }
    
    public Graphics2D() {
	/* empty */
    }
    
    static final void fillRect(int x, int y, int width_, int height_, int color, int alpha) {
    	if (x < topX) {
    		width_ -= topX - x;
    		x = topX;
    	}
    	if (y < topY) {
    		height_ -= topY - y;
    		y = topY;
    	}
    	if (x + width_ > bottomX)
    		width_ = bottomX - x;
    	if (y + height_ > bottomY)
    		height_ = bottomY - y;
    	int srcAlpha = 256 - alpha;
    	int destRed = (color >> 16 & 0xff) * alpha;
    	int destGreen = (color >> 8 & 0xff) * alpha;
    	int destBlue = (color & 0xff) * alpha;
    	int step = width - width_;
    	int off = x + y * width;
    	for (int offY = 0; offY < height_; offY++) {
    		for (int offX = -width_; offX < 0; offX++) {
    			int srcRed = (pixels[off] >> 16 & 0xff) * srcAlpha;
    			int srcGreen = (pixels[off] >> 8 & 0xff) * srcAlpha;
    			int srcBlue = (pixels[off] & 0xff) * srcAlpha;
    			int destColor = ((destRed + srcRed >> 8 << 16) + (destGreen + srcGreen >> 8 << 8) + (destBlue + srcBlue >> 8));
    			pixels[off++] = destColor;
    		}
    		off += step;
    	}
    }
    
    static final void drawVerticalLine(int x, int y, int height, int color) {
    	if (x >= topX && x < bottomX) {
    		if (y < topY) {
    			height -= topY - y;
    			y = topY;
    		}
    		if (y + height > bottomY)
    			height = bottomY - y;
    		int step = x + y * width;
    		for (int offY = 0; offY < height; offY++)
    			pixels[step + offY * width] = color;
    	}
    }
}
