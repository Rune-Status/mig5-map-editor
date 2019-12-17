package org.mapeditor.client;

/* Class3_Sub3_Sub12_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import java.util.Hashtable;

public final class Sprite extends Graphics2D {
	
	int trimWidth;
	int offsetY;
	int trimHeight;
	int[] pixels;
	int height;
	int offsetX;
	int width;

	final void drawSprite(int x, int y) {
		x += offsetX;
		y += offsetY;
		int destOff = x + y * Graphics2D.width;
		int srcOff = 0;
		int h = height;
		int w = width;
		int destStep = Graphics2D.width - w;
		int srcStep = 0;
		if (y < Graphics2D.topY) {
			int topMargin = Graphics2D.topY - y;
			h -= topMargin;
			y = Graphics2D.topY;
			srcOff += topMargin * w;
			destOff += topMargin * Graphics2D.width;
		}
		if (y + h > Graphics2D.bottomY)
			h -= y + h - Graphics2D.bottomY;
		if (x < Graphics2D.topX) {
			int leftMargin = Graphics2D.topX - x;
			w -= leftMargin;
			x = Graphics2D.topX;
			srcOff += leftMargin;
			destOff += leftMargin;
			srcStep += leftMargin;
			destStep += leftMargin;
		}
		if (x + w > Graphics2D.bottomX) {
			int rightMargin = x + w - Graphics2D.bottomX;
			w -= rightMargin;
			srcStep += rightMargin;
			destStep += rightMargin;
		}
		if (w > 0 && h > 0)
			pushPixels(Graphics2D.pixels, pixels, 0, srcOff, destOff, w, h,
					destStep, srcStep);
	}

	final void drawIndexedSprite(IndexedSprite indexedSprite, int x, int y) {
		x += offsetX;
		y += offsetY;
		int destOff = x + y * Graphics2D.width;
		int srcOff = 0;
		int h = height;
		int w = width;
		int destStep = Graphics2D.width - w;
		int srcStep = 0;
		if (y < Graphics2D.topY) {
			int topMargin = Graphics2D.topY - y;
			h -= topMargin;
			y = Graphics2D.topY;
			srcOff += topMargin * w;
			destOff += topMargin * Graphics2D.width;
		}
		if (y + h > Graphics2D.bottomY)
			h -= y + h - Graphics2D.bottomY;
		if (x < Graphics2D.topX) {
			int leftMargin = Graphics2D.topX - x;
			w -= leftMargin;
			x = Graphics2D.topX;
			srcOff += leftMargin;
			destOff += leftMargin;
			srcStep += leftMargin;
			destStep += leftMargin;
		}
		if (x + w > Graphics2D.bottomX) {
			int rightMargin = x + w - Graphics2D.bottomX;
			w -= rightMargin;
			srcStep += rightMargin;
			destStep += rightMargin;
		}
		if (w > 0 && h > 0)
			pushPixels(Graphics2D.pixels, pixels, 0, srcOff, destOff, w, h, destStep, srcStep, indexedSprite.pixels);
	}

	private static final void copyPixels(int[] dest, int[] src, int srcOff,
			int destOff, int width, int height, int destStep, int srcStep) {
		for (int offY = -height; offY < 0; offY++) {
			int len = destOff + width - 3;
			while (destOff < len) {
				dest[destOff++] = src[srcOff++];
				dest[destOff++] = src[srcOff++];
				dest[destOff++] = src[srcOff++];
				dest[destOff++] = src[srcOff++];
			}
			len += 3;
			while (destOff < len)
				dest[destOff++] = src[srcOff++];
			destOff += destStep;
			srcOff += srcStep;
		}
	}

	final void drawFlippedSprite(int x, int y) {
		x += offsetX;
		y += offsetY;
		int destOff = x + y * Graphics2D.width;
		int srcOff = 0;
		int h = height;
		int w = width;
		int destStep = Graphics2D.width - w;
		int srcStep = 0;
		if (y < Graphics2D.topY) {
			int topMargin = Graphics2D.topY - y;
			h -= topMargin;
			y = Graphics2D.topY;
			srcOff += topMargin * w;
			destOff += topMargin * Graphics2D.width;
		}
		if (y + h > Graphics2D.bottomY)
			h -= y + h - Graphics2D.bottomY;
		if (x < Graphics2D.topX) {
			int leftMargin = Graphics2D.topX - x;
			w -= leftMargin;
			x = Graphics2D.topX;
			srcOff += leftMargin;
			destOff += leftMargin;
			srcStep += leftMargin;
			destStep += leftMargin;
		}
		if (x + w > Graphics2D.bottomX) {
			int rightMargin = x + w - Graphics2D.bottomX;
			w -= rightMargin;
			srcStep += rightMargin;
			destStep += rightMargin;
		}
		if (w > 0 && h > 0)
			copyPixels(Graphics2D.pixels, pixels, srcOff, destOff, w, h,
					destStep, srcStep);
	}

	final void init() {
		Graphics2D.init2dCanvas(pixels, width, height);
	}

	private static final void pushPixels(int[] dest, int[] src, int color,
			int srcOff, int destOff, int width, int height, int destStep, int srcStep) {
		int quarterX = -(width >> 2);
		width = -(width & 0x3);
		for (int offY = -height; offY < 0; offY++) {
			for (int offX = quarterX; offX < 0; offX++) {
				color = src[srcOff++];
				if (color != 0)
					dest[destOff++] = color;
				else
					destOff++;
				color = src[srcOff++];
				if (color != 0)
					dest[destOff++] = color;
				else
					destOff++;
				color = src[srcOff++];
				if (color != 0)
					dest[destOff++] = color;
				else
					destOff++;
				color = src[srcOff++];
				if (color != 0)
					dest[destOff++] = color;
				else
					destOff++;
			}
			for (int offX = width; offX < 0; offX++) {
				color = src[srcOff++];
				if (color != 0)
					dest[destOff++] = color;
				else
					destOff++;
			}
			destOff += destStep;
			srcOff += srcStep;
		}
	}

	final void drawShapedSprite(int i, int i_49_, int width, int height, int i_52_,
			int i_53_, double angle, int zoom) {
		try {
			int i_55_ = -width / 2;
			int i_56_ = -height / 2;
			int sinStep = (int) (Math.sin(angle) * 65536.0);
			int cosStep = (int) (Math.cos(angle) * 65536.0);
			sinStep = sinStep * zoom >> 8;
			cosStep = cosStep * zoom >> 8;
			int sin = (i_52_ << 16) + (i_56_ * sinStep + i_55_ * cosStep);
			int cos = (i_53_ << 16) + (i_56_ * cosStep - i_55_ * sinStep);
			int i_61_ = i + i_49_ * Graphics2D.width;
			for (i_49_ = 0; i_49_ < height; i_49_++) {
				int i_62_ = i_61_;
				int i_63_ = sin;
				int i_64_ = cos;
				for (i = -width; i < 0; i++) {
					int i_65_ = (pixels[((i_63_ >> 16) + (i_64_ >> 16)
							* width)]);
					if (i_65_ != 0)
						Graphics2D.pixels[i_62_++] = i_65_;
					else
						i_62_++;
					i_63_ += cosStep;
					i_64_ -= sinStep;
				}
				sin += sinStep;
				cos += cosStep;
				i_61_ += Graphics2D.width;
			}
		} catch (Exception exception) {
			/* empty */
		}
	}

	private static final void pushPixels(int[] destPixels, int[] srcPixels, int color,
			int srcOff, int destOff, int width, int height, int destStep, int srcStep,
			int fadeAlpha) {
		int destAlpha = 256 - fadeAlpha;
		for (int offY = -height; offY < 0; offY++) {
			for (int offX = -width; offX < 0; offX++) {
				color = srcPixels[srcOff++];
				if (color != 0) {
					int destColor = destPixels[destOff];
					destPixels[destOff++] = (((color & 0xff00ff) * fadeAlpha + (destColor & 0xff00ff)
							* destAlpha & ~0xff00ff) + ((color & 0xff00) * fadeAlpha
							+ (destColor & 0xff00) * destAlpha & 0xff0000)) >> 8;
				} else
					destOff++;
			}
			destOff += destStep;
			srcOff += srcStep;
		}
	}

	final void drawSprite(int x, int y, int alpha) {
		x += offsetX;
		y += offsetY;
		int destOff = x + y * Graphics2D.width;
		int srcOff = 0;
		int h = height;
		int w = width;
		int destStep = Graphics2D.width - w;
		int srcStep = 0;
		if (y < Graphics2D.topY) {
			int topMargin = Graphics2D.topY - y;
			h -= topMargin;
			y = Graphics2D.topY;
			srcOff += topMargin * w;
			destOff += topMargin * Graphics2D.width;
		}
		if (y + h > Graphics2D.bottomY)
			h -= y + h - Graphics2D.bottomY;
		if (x < Graphics2D.topX) {
			int leftMargin = Graphics2D.topX - x;
			w -= leftMargin;
			x = Graphics2D.topX;
			srcOff += leftMargin;
			destOff += leftMargin;
			srcStep += leftMargin;
			destStep += leftMargin;
		}
		if (x + w > Graphics2D.bottomX) {
			int rightMargin = x + w - Graphics2D.bottomX;
			w -= rightMargin;
			srcStep += rightMargin;
			destStep += rightMargin;
		}
		if (w > 0 && h > 0)
			pushPixels(Graphics2D.pixels, pixels, 0, srcOff, destOff, w, h,
					destStep, srcStep, alpha);
	}

	private static final void pushPixels(int[] dest, int[] src, int color,
			int srcOffset, int destOffset, int width, int height, int destStep, int srcStep,
			byte[] pixels) {
		int quarterX = -(width >> 2);
		width = -(width & 0x3);
		for (int offY = -height; offY < 0; offY++) {
			for (int offX = quarterX; offX < 0; offX++) {
				color = src[srcOffset++];
				if (color != 0 && pixels[destOffset] == 0)
					dest[destOffset++] = color;
				else
					destOffset++;
				color = src[srcOffset++];
				if (color != 0 && pixels[destOffset] == 0)
					dest[destOffset++] = color;
				else
					destOffset++;
				color = src[srcOffset++];
				if (color != 0 && pixels[destOffset] == 0)
					dest[destOffset++] = color;
				else
					destOffset++;
				color = src[srcOffset++];
				if (color != 0 && pixels[destOffset] == 0)
					dest[destOffset++] = color;
				else
					destOffset++;
			}
			for (int offX = width; offX < 0; offX++) {
				color = src[srcOffset++];
				if (color != 0 && pixels[destOffset] == 0)
					dest[destOffset++] = color;
				else
					destOffset++;
			}
			destOffset += destStep;
			srcOffset += srcStep;
		}
	}

	final void resize() {
		if ((width != trimWidth) || (height != trimHeight)) {
			int[] pixels_ = new int[(trimWidth * trimHeight)];
			for (int offY = 0; offY < height; offY++) {
				for (int offX = 0; offX < width; offX++)
					pixels_[(((offY + offsetY) * trimWidth) + (offX + offsetX))] = (pixels[(offY * width + offX)]);
			}
			pixels = pixels_;
			width = trimWidth;
			height = trimHeight;
			offsetX = 0;
			offsetY = 0;
		}
	}

	final void drawShapedSprite(int w, int h, int i_103_, int i_104_,
			int x, int y, int yaw, int zoom, int[] src,
			int[] dest) {
		try {
			int i_110_ = -i_103_ / 2;
			int i_111_ = -i_104_ / 2;
			int sineStep = (int) (Math.sin((double) yaw / 326.11) * 65536.0);
			int cosStep = (int) (Math.cos((double) yaw / 326.11) * 65536.0);
			sineStep = sineStep * zoom >> 8;
			cosStep = cosStep * zoom >> 8;
			int sine = (x << 16) + (i_111_ * sineStep + i_110_ * cosStep);
			int cos = (y << 16) + (i_111_ * cosStep - i_110_ * sineStep);
			int i_116_ = w + h * Graphics2D.width;
			for (h = 0; h < i_104_; h++) {
				int i_117_ = src[h];
				int i_118_ = i_116_ + i_117_;
				int i_119_ = sine + cosStep * i_117_;
				int i_120_ = cos - sineStep * i_117_;
				for (w = -dest[h]; w < 0; w++) {
					Graphics2D.pixels[i_118_++] = (pixels[((i_119_ >> 16) + (i_120_ >> 16) * width)]);
					i_119_ += cosStep;
					i_120_ -= sineStep;
				}
				sine += sineStep;
				cos += cosStep;
				i_116_ += Graphics2D.width;
			}
		} catch (Exception exception) {
			/* empty */
		}
	}

	final void adjustColors(int redOff, int greenOff, int blueOff) {
		for (int len = 0; len < pixels.length; len++) {
			int color = pixels[len];
			if (color != 0) {
				int red = color >> 16 & 0xff;
				red += redOff;
				if (red < 1)
					red = 1;
				else if (red > 255)
					red = 255;
				int green = color >> 8 & 0xff;
				green += greenOff;
				if (green < 1)
					green = 1;
				else if (green > 255)
					green = 255;
				int blue = color & 0xff;
				blue += blueOff;
				if (blue < 1)
					blue = 1;
				else if (blue > 255)
					blue = 255;
				pixels[len] = (red << 16) + (green << 8) + blue;
			}
		}
	}

	Sprite() {
		/* empty */
	}

	Sprite(int w, int h) {
		pixels = new int[w * h];
		width = trimWidth = w;
		height = trimHeight = h;
		offsetX = offsetY = 0;
	}

	public Sprite(Component component, byte[] buffer) {
		try {
			Image image = Toolkit.getDefaultToolkit().createImage(buffer);
			MediaTracker mediatracker = new MediaTracker(component);
			mediatracker.addImage(image, 0);
			mediatracker.waitForAll();
			width = image.getWidth(component);
			height = image.getHeight(component);
			trimWidth = width;
			trimHeight = height;
			offsetX = 0;
			offsetY = 0;
			pixels = new int[width * height];
			PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, width, height, pixels, 0, width);
			pixelgrabber.grabPixels();
		} catch (InterruptedException interruptedexception) {
			/* empty */
		}
	}
	
	public void dumpImage(String name) {
		dumpImage("sprites/", name, false);
	}

	public void dumpImage(String directory, String name) {
		dumpImage(directory, name, false);
	}

	public void dumpImage(String directory, String name, boolean transparency) {
		try {
			java.awt.image.DirectColorModel model = new java.awt.image.DirectColorModel(24,
					0xFF0000, 0x00FF00, 0x0000FF);
			int[] bandmasks = new int[] { model.getRedMask(), model.getGreenMask(), model.getBlueMask() };
			int w = width;
			int h = height;
			int[] pix = pixels;
			if (transparency)
				for (int i = 0; i < pix.length; i++)
					if (pix[i] == 0)
						pix[i] = 0xFFFFFF;
			java.awt.image.DataBufferInt buffer = new java.awt.image.DataBufferInt(pix, pix.length);
			java.awt.image.WritableRaster raster = java.awt.image.Raster.createPackedRaster(buffer,
					w, h, w, bandmasks, null);
			java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(model, raster, true, new Hashtable<Object, Object>());
			javax.imageio.ImageIO.write(img, "png", new java.io.File("c:/" + directory + name + ".png"));
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
}
