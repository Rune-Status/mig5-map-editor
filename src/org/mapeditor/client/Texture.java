package org.mapeditor.client;

/* Class3_Sub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public final class Texture extends Node {
	
    int textureId;
    boolean transparent;
    int type;
    private int speed;
    private int[] anIntArray1164;
    private static int[] anIntArray1165;
    private int[] anIntArray1166;
    private int[] anIntArray1167;
    public int[] textureSprites;
    boolean aBoolean1169 = false;
    int[] anIntArray1170;
    
    final void dispose() {
    	anIntArray1170 = null;
    }
    
    final void animateTexture(int lagg) {
    	if (anIntArray1170 != null) {
    		if (type == 1 || type == 3) {
    			if (anIntArray1165 == null || (anIntArray1165.length < anIntArray1170.length))
    				anIntArray1165 = new int[anIntArray1170.length];
    			int somet;
    			if (anIntArray1170.length == 16384)
    				somet = 64;
    			else
    				somet = 128;
    			int i_1_ = anIntArray1170.length / 4;
    			int direction = somet * lagg * speed;
    			int i_3_ = i_1_ - 1;
    			if (type == 1) {
    				direction = -direction;
    			}
    			for (int i_4_ = 0; i_4_ < i_1_; i_4_++) {
    				int i_5_ = i_4_ + direction & i_3_;
    				anIntArray1165[i_4_] = anIntArray1170[i_5_];
    				anIntArray1165[i_4_ + i_1_] = anIntArray1170[i_5_ + i_1_];
    				anIntArray1165[i_4_ + i_1_ + i_1_] = (anIntArray1170[i_5_ + i_1_ + i_1_]);
    				anIntArray1165[i_4_ + i_1_ + i_1_ + i_1_] = (anIntArray1170[i_5_ + i_1_ + i_1_ + i_1_]);
    			}
    			int[] is = anIntArray1170;
    			anIntArray1170 = anIntArray1165;
    			anIntArray1165 = is;
    		}
    		if (type == 2 || type == 4) {
    			if (anIntArray1165 == null || (anIntArray1165.length < anIntArray1170.length))
    				anIntArray1165 = new int[anIntArray1170.length];
    			int somet;
    			if (anIntArray1170.length == 16384)
    				somet = 64;
    			else
			    	somet = 128;
    			int i_7_ = anIntArray1170.length / 4;
    			int i_8_ = lagg * speed;
    			int i_9_ = somet - 1;
    			if (type == 2)
    				i_8_ = -i_8_;
    			for (int i_10_ = 0; i_10_ < i_7_; i_10_ += somet) {
    				for (int i_11_ = 0; i_11_ < somet; i_11_++) {
    					int i_12_ = i_10_ + i_11_;
    					int i_13_ = i_10_ + (i_11_ + i_8_ & i_9_);
    					anIntArray1165[i_12_] = anIntArray1170[i_13_];
    					anIntArray1165[i_12_ + i_7_] = (anIntArray1170[i_13_ + i_7_]);
    					anIntArray1165[i_12_ + i_7_ + i_7_] = (anIntArray1170[i_13_ + i_7_ + i_7_]);
    					anIntArray1165[i_12_ + i_7_ + i_7_ + i_7_] = (anIntArray1170[i_13_ + i_7_ + i_7_ + i_7_]);
    				}
    			}
    			int[] is = anIntArray1170;
    			anIntArray1170 = anIntArray1165;
    			anIntArray1165 = is;
    		}
    	}
    }
    
    Texture(RSByteBuffer class3_sub12) {
		textureId = class3_sub12.getUShort();
		transparent = class3_sub12.getUByte() == 1;
		int length = class3_sub12.getUByte();
		if (length < 1 || length > 4)
		    throw new RuntimeException();
		textureSprites = new int[length];
		for (int i_14_ = 0; i_14_ < length; i_14_++)
		    textureSprites[i_14_] = class3_sub12.getUShort();
		if (length > 1) {
		    anIntArray1164 = new int[length - 1];
		    for (int i_15_ = 0; i_15_ < length - 1; i_15_++) {
		    	anIntArray1164[i_15_] = class3_sub12.getUByte();
		    }
		}
		if (length > 1) {
		    anIntArray1166 = new int[length - 1];
		    for (int i_16_ = 0; i_16_ < length - 1; i_16_++) {
		    	anIntArray1166[i_16_] = class3_sub12.getUByte();
		    }
		}
		anIntArray1167 = new int[length];
		for (int i_17_ = 0; i_17_ < length; i_17_++) {
		    anIntArray1167[i_17_] = class3_sub12.getInt();
		}
		type = class3_sub12.getUByte();
		speed = class3_sub12.getUByte();
		anIntArray1170 = null;
    }
    
    final boolean method69(double brightness, int i, FileSystem spriteFetcher) {
    	for (int i_18_ = 0; i_18_ < textureSprites.length; i_18_++) {
    		if (spriteFetcher.method589(textureSprites[i_18_]) == null)
    			return false;
    	}
    	int i_19_ = i * i;
    	anIntArray1170 = new int[i_19_ * 4];
		for (int i_20_ = 0; i_20_ < textureSprites.length; i_20_++) {
			IndexedSprite textureSprite = Client.getTexture(spriteFetcher, textureSprites[i_20_]);
			textureSprite.resize();
			byte[] pixels = textureSprite.pixels;
			int[] pallete = textureSprite.pallete;
			int i_22_ = anIntArray1167[i_20_];
			if ((i_22_ & ~0xffffff) == 50331648) {
				int i_23_ = i_22_ & 0xff00ff;
				int i_24_ = i_22_ >> 8 & 0xff;
				for (int i_25_ = 0; i_25_ < pallete.length; i_25_++) {
					int i_26_ = pallete[i_25_];
					if ((i_26_ & 0xffff) == i_26_ >> 8) {
						i_26_ &= 0xff;
						pallete[i_25_] = (i_23_ * i_26_ >> 8 & 0xff00ff | i_24_ * i_26_ & 0xff00);
					}
				}
			}
			for (int i_27_ = 0; i_27_ < pallete.length; i_27_++)
				pallete[i_27_] = Rasterizer.modifyBrightness(pallete[i_27_], brightness);
			int i_28_;
			if (i_20_ == 0)
				i_28_ = 0;
			else
				i_28_ = anIntArray1164[i_20_ - 1];
			if (i_28_ == 0) {
				if (textureSprite.width == i) {
					for (int i_29_ = 0; i_29_ < i_19_; i_29_++)
						anIntArray1170[i_29_] = pallete[pixels[i_29_] & 0xff];
				} else if ((textureSprite.width) == 64 && i == 128) {
					int i_30_ = 0;
					for (int i_31_ = 0; i_31_ < i; i_31_++) {
						for (int i_32_ = 0; i_32_ < i; i_32_++)
							anIntArray1170[i_30_++] = pallete[(pixels[(i_32_ >> 1) + (i_31_ >> 1 << 6)] & 0xff)];
					}
				} else if ((textureSprite.width) == 128 && i == 64) {
					int i_33_ = 0;
					for (int i_34_ = 0; i_34_ < i; i_34_++) {
						for (int i_35_ = 0; i_35_ < i; i_35_++)
							anIntArray1170[i_33_++] = pallete[(pixels[(i_35_ << 1) + (i_34_ << 1 << 7)] & 0xff)];
					}
				} else {
					//System.out.println(i + ", " + textureSprite.width);
					//throw new RuntimeException();
					//TODO add 128 34
				}
			}
		}
		for (int i_36_ = 0; i_36_ < i_19_; i_36_++) {
			anIntArray1170[i_36_] &= 0xf8f8ff;
			int i_37_ = anIntArray1170[i_36_];
			anIntArray1170[i_36_ + i_19_] = i_37_ - (i_37_ >>> 3) & 0xf8f8ff;
			anIntArray1170[i_36_ + i_19_ + i_19_] = i_37_ - (i_37_ >>> 2) & 0xf8f8ff;
			anIntArray1170[i_36_ + i_19_ + i_19_ + i_19_] = i_37_ - (i_37_ >>> 2) - (i_37_ >>> 3) & 0xf8f8ff;
		}
		return true;
    }
    
    public static void reset() {
    	anIntArray1165 = null;
    }
}
