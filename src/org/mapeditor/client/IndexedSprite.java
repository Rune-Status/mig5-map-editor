package org.mapeditor.client;

import java.awt.image.BufferedImage;
import java.util.Hashtable;

/* Class3_Sub3_Sub12_Sub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public final class IndexedSprite extends Graphics2D {

	int trimHeight;
	int height;
	int offsetY;
	int trimWidth;
	int offsetX;
	int[] pallete;
	public byte[] pixels;
	int width;

	final void adjustColors(int redOff, int greenOff, int blueOff) {
		for (int id = 0; id < pallete.length; id++) {
			int red = (pallete[id] >> 16 & 0xff);
			red += redOff;
			if (red < 0)
				red = 0;
			else if (red > 255)
				red = 255;
			int green = (pallete[id] >> 8 & 0xff);
			green += greenOff;
			if (green < 0)
				green = 0;
			else if (green > 255)
				green = 255;
			int blue = pallete[id] & 0xff;
			blue += blueOff;
			if (blue < 0)
				blue = 0;
			else if (blue > 255)
				blue = 255;
			pallete[id] = (red << 16) + (green << 8) + blue;
		}
	}

	final IndexedSprite createIndexedSprite() {
		IndexedSprite sprite = new IndexedSprite(width, height, pallete.length);
		sprite.trimWidth = trimWidth;
		sprite.trimHeight = trimHeight;
		sprite.offsetX = offsetX;
		sprite.offsetY = offsetY;
		int len = pixels.length;
		for (int id = 0; id < len; id++)
			sprite.pixels[id] = pixels[id];
		len = pallete.length;
		for (int id = 0; id < len; id++)
			sprite.pallete[id] = pallete[id];
		return sprite;
	}

	final void flipVertical() {
		byte[] pixels_ = new byte[(width * height)];
		int off = 0;
		for (int offY = height - 1; offY >= 0; offY--) {
			for (int offX = 0; offX < width; offX++)
				pixels_[off++] = (pixels[offX + offY * (width)]);
		}
		pixels = pixels_;
		offsetY = (trimHeight - height - offsetY);
	}

	final void flipHorizontal() {
		byte[] pixels_ = new byte[(width * height)];
		int off = 0;
		for (int offY = 0; offY < height; offY++) {
			for (int offX = width - 1; offX >= 0; offX--)
				pixels_[off++] = (pixels[offX + offY * width]);
		}
		pixels = pixels_;
		offsetX = (trimWidth - width - offsetX);
	}

	private static final void pushPixels(int[] dest, byte[] src, int[] palette,
			int srcOff, int destOff, int width, int height, int destStep,
			int srcStep) {
		int quarterX = -(width >> 2);
		width = -(width & 0x3);
		for (int offY = -height; offY < 0; offY++) {
			for (int offX = quarterX; offX < 0; offX++) {
				int color = src[srcOff++];
				if (color != 0)
					dest[destOff++] = palette[color & 0xff];
				else
					destOff++;
				color = src[srcOff++];
				if (color != 0)
					dest[destOff++] = palette[color & 0xff];
				else
					destOff++;
				color = src[srcOff++];
				if (color != 0)
					dest[destOff++] = palette[color & 0xff];
				else
					destOff++;
				color = src[srcOff++];
				if (color != 0)
					dest[destOff++] = palette[color & 0xff];
				else
					destOff++;
			}
			for (int offX = width; offX < 0; offX++) {
				int color = src[srcOff++];
				if (color != 0)
					dest[destOff++] = palette[color & 0xff];
				else
					destOff++;
			}
			destOff += destStep;
			srcOff += srcStep;
		}
	}

	final void drawIndexedSprite(int x, int y) {
		x += offsetX;
		y += offsetY;
		int destOff = x + y * Graphics2D.width;
		int srcOff = 0;
		int height = this.height;
		int width = this.width;
		int destStep = Graphics2D.width - width;
		int srcStep = 0;
		if (y < Graphics2D.topY) {
			int topMargin = Graphics2D.topY - y;
			height -= topMargin;
			y = Graphics2D.topY;
			srcOff += topMargin * width;
			destOff += topMargin * Graphics2D.width;
		}
		if (y + height > Graphics2D.bottomY)
			height -= y + height - Graphics2D.bottomY;
		if (x < Graphics2D.topX) {
			int leftMargin = Graphics2D.topX - x;
			width -= leftMargin;
			x = Graphics2D.topX;
			srcOff += leftMargin;
			destOff += leftMargin;
			srcStep += leftMargin;
			destStep += leftMargin;
		}
		if (x + width > Graphics2D.bottomX) {
			int rightMargin = x + width - Graphics2D.bottomX;
			width -= rightMargin;
			srcStep += rightMargin;
			destStep += rightMargin;
		}
		if (width > 0 && height > 0)
			pushPixels(Graphics2D.pixels, pixels, pallete, srcOff, destOff,
					width, height, destStep, srcStep);
	}

	public final void resize() {
		if ((width != trimWidth) || (height != trimHeight)) {
			byte[] pixels_ = new byte[trimWidth * trimHeight];
			int off = 0;
			for (int offY = 0; offY < height; offY++) {
				for (int offX = 0; offX < width; offX++)
					pixels_[(offX + offsetX + ((offY + offsetY) * trimWidth))] = pixels[off++];
			}
			pixels = pixels_;
			width = trimWidth;
			height = trimHeight;
			offsetX = 0;
			offsetY = 0;
		}
	}

	public BufferedImage toImage() {
		java.awt.image.DirectColorModel model = new java.awt.image.DirectColorModel(24,
				0xFF0000, 0x00FF00, 0x0000FF);
		int[] bandmasks = new int[] { model.getRedMask(), model.getGreenMask(), model.getBlueMask() };
		int w = width;
		int h = height;
		int[] pix = new int[w*h];
		for(int i = 0; i < pix.length; i++) {
			pix[i] = pallete[pixels[i] & 0xff];
		}
		java.awt.image.DataBufferInt buffer = new java.awt.image.DataBufferInt(pix, pix.length);
		java.awt.image.WritableRaster raster = java.awt.image.Raster.createPackedRaster(buffer,
				w, h, w, bandmasks, null);
		java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(model, raster, true, new Hashtable<Object, Object>());
		return img;
	}

	IndexedSprite() {
		/* empty */
	}

	private IndexedSprite(int w, int h, int palleteLen) {
		trimWidth = width = w;
		trimHeight = height = h;
		offsetX = offsetY = 0;
		pixels = new byte[w * h];
		pallete = new int[palleteLen];
	}
}
