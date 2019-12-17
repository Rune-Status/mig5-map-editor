package org.mapeditor.client;

/* Class3_Sub3_Sub5 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public final class OverLayFloor extends NodeSub {
	
    public int groundLightness;
    public int groundSaturation;
    public int groundHue;
    public int alpha;
    public int groundColor = 0;
    
    final void readValues(RSByteBuffer stream) {
    	for (;;) {
    		int opcode = stream.getUByte();
    		if (opcode == 0)
    			break;
    		readValues(stream, opcode);
    	}
    }
    
    private final void calculateHSL(int color) {
		double red = (double) ((color & 0xff8ab1) >> 16) / 256.0;
		double green = (double) ((color & 0xff80) >> 8) / 256.0;
		double blue = (double) (color & 0xff) / 256.0;
		double max = red;
		double hue = 0.0;
		double min = red;
		if (green < min)
		    min = green;
		if (green > max)
		    max = green;
		if (blue < min)
		    min = blue;
		if (blue > max)
		    max = blue;
		double sat = 0.0;
		double delta = (min + max) / 2.0;
		groundLightness = (int) (delta * 256.0);
		if (min != max) {
			if (max == red)
				hue = (-blue + green) / (max - min);
			else if (green != max) {
				if (blue == max)
					hue = (-green + red) / (-min + max) + 4.0;
			} else
				hue = (-red + blue) / (-min + max) + 2.0;
			if (delta < 0.5)
				sat = (-min + max) / (min + max);
			if (delta >= 0.5)
				sat = (max - min) / (-min + (-max + 2.0));
		}
		if (groundLightness >= 0) {
			if (groundLightness > 255)
				groundLightness = 255;
		} else
			groundLightness = 0;
		groundSaturation = (int) (sat * 256.0);
		if (delta > 0.5)
		    alpha = (int) ((1.0 - delta) * sat * 512.0);
		else
		    alpha = (int) (sat * delta * 512.0);
		if (groundSaturation < 0)
		    groundSaturation = 0;
		else if (groundSaturation > 255)
		    groundSaturation = 255;
		if (alpha < 1)
		    alpha = 1;
		hue /= 6.0;
		groundHue = (int) (hue * (double) alpha);
    }
    
    private final void readValues(RSByteBuffer buffer, int opcode) {
		if (opcode == 1) {
			groundColor = buffer.getTriByte();
			calculateHSL(groundColor);
		} else if (opcode != 2) {
			if (opcode == 3) {
				buffer.getUShort();
			}
		} else {
			buffer.getUShort();
		}
    }
    
    final void calculateHSL() {
    	calculateHSL(groundColor);
    }
    
    public static final OverLayFloor forID(int id) {
		OverLayFloor overlay = (OverLayFloor) Client.overlayCache.get((long) id);
		if (overlay != null)
			return overlay;
		byte[] buffer = Client.overlayFetcher.getXteaBuffer(1, id);
		overlay = new OverLayFloor();
		if (buffer != null)
			overlay.readValues(new RSByteBuffer(buffer));
		overlay.calculateHSL();
		Client.overlayCache.put(overlay, (long) id);
		return overlay;
	}

	public OverLayFloor() {
	/* empty */
    }
}
