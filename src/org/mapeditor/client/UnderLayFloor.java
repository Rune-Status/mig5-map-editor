package org.mapeditor.client;

/* Class3_Sub3_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public final class UnderLayFloor extends NodeSub
{
    public int anInt1420;
    int anInt1421;
    int anInt1422;
    public int hue;
    public int anInt1426 = 0;
    boolean aBoolean1429;
    public int saturation;
    public int textureId;
    int anInt1434;
    public int lightness;
    
    private final void readValues(RSByteBuffer stream, int opcode) {
    	if (opcode == 1) {
    		anInt1426 = stream.getTriByte();
    	}
    	if (opcode == 2) {
    		textureId = stream.getUByte();
    	}
    	if (opcode == 5) {
			aBoolean1429 = false;
    	}
    	if (opcode == 7) {
    		anInt1420 = stream.getTriByte();
    	}
    }

	private final void parseOpcode(RSByteBuffer buffer, int opcode) {
		if (opcode != 1) {
			if (opcode == 2) {
				textureId = buffer.getUByte();
			} else if (opcode == 3) {
				textureId = buffer.getUShort();
				if ((textureId ^ 0xffffffff) == -65536) {
					textureId = -1;
				}
			} else if (opcode == 5) {
				aBoolean1429 = false;
			} else if (opcode == 7) {
				anInt1420 = buffer.getTriByte();
			} else if (opcode != 8) {
				if (opcode == 9) {
					textureId = buffer.getUShort();
				} else if (opcode == 11) { //New opcodes
					textureId = buffer.getUByte();
				} else if (opcode == 12) { //New opcodes
					boolean bool12 = true;
				} else if (opcode == 13) {
					buffer.getTriByte();
				} else if (opcode == 14) {
					buffer.getUByte();
				} else {
					System.out.println("[Overlay] Missing value " + opcode);
				}
			} else {
				;
			}
		} else {
			anInt1426 = buffer.getTriByte();
		}
	}
    
    final void method81() {
    	if (anInt1420 != -1) {
    		method85(anInt1420);
    		anInt1421 = lightness;
    		anInt1422 = hue;
    		anInt1434 = saturation;
    	}
    	method85(anInt1426);
    }
    
    final void readValues(RSByteBuffer stream) {
    	for (;;) {
    		int opcode = stream.getUByte();
    		if (opcode == 0)
    			break;
    		parseOpcode(stream, opcode);
    	}
    }
    
    private final void method85(int color) {
    	double red = (double) ((color & 0xfff4a0) >> 16) / 256.0;
    	double green = (double) (color >> 8 & 0xff) / 256.0;
    	double blue = (double) (color & 0xff) / 256.0;
    	double red_ = red;
    	double d_34_ = 0.0;
    	double d_35_ = red;
    	double d_36_ = 0.0;
    	if (red_ > green)
    		red_ = green;
    	if (red_ > blue)
    		red_ = blue;
    	if (green > d_35_)
    		d_35_ = green;
    	if (d_35_ < blue)
    		d_35_ = blue;
		double d_37_ = (red_ + d_35_) / 2.0;
		if (red_ != d_35_) {
			if (red == d_35_)
				d_36_ = (green - blue) / (-red_ + d_35_);
			else if (green != d_35_) {
				if (blue == d_35_)
					d_36_ = (red - green) / (d_35_ - red_) + 4.0;
			} else
				d_36_ = (-red + blue) / (d_35_ - red_) + 2.0;
			if (d_37_ < 0.5)
				d_34_ = (d_35_ - red_) / (d_35_ + red_);
			if (d_37_ >= 0.5)
				d_34_ = (d_35_ - red_) / (-red_ + (2.0 - d_35_));
		}
		d_36_ /= 6.0;
		hue = (int) (d_36_ * 256.0);
		lightness = (int) (d_37_ * 256.0);
		saturation = (int) (d_34_ * 256.0);
		if (lightness >= 0) {
			if (lightness > 255)
				lightness = 255;
		} else
			lightness = 0;
		if (saturation < 0)
			saturation = 0;
		else if (saturation > 255)
			saturation = 255;
    }
    
    public static final UnderLayFloor forID(int id) {
		UnderLayFloor underlay = (UnderLayFloor) Client.underlayCache.get((long) id);
		if (underlay != null)
			return underlay;
		byte[] buffer = Client.underlayFetcher.getXteaBuffer(4, id);
		underlay = new UnderLayFloor();
		if (buffer != null)
			underlay.readValues(new RSByteBuffer(buffer));
		underlay.method81();
		Client.underlayCache.put(underlay, (long) id);
		return underlay;
	}

	public UnderLayFloor() {
		anInt1420 = -1;
		aBoolean1429 = true;
		textureId = -1;
    }
}
