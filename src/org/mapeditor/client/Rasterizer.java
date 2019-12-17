package org.mapeditor.client;

import java.awt.Color;

/* Class3_Sub3_Sub12_Sub3 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public final class Rasterizer extends Graphics2D {
	
    public static int alpha;
    static int[] anIntArray2106 = new int[2048];
    static int endX;
    static int rasterizeCenterY;
    static int anInt2109;
    static boolean edgeRestricted;
    static int[] sineTable = new int[2048];
    static boolean colorRestricted;
    static int rasterizeCenterX;
    static int renderCenterX;
    static int endY;
    public static int[] lineOffsets;
    static int[] cosineTable;
    static int anInt2118;
    private static int[] gradientFactors;
    private static boolean transparent;
    private static boolean lowMem;
    public static TextureInterface textureInterface;
    static int renderCenterY;
    public static int[] palette;
    
    static {
		edgeRestricted = false;
		cosineTable = new int[2048];
		gradientFactors = new int[512];
		colorRestricted = true;
		alpha = 0;
		transparent = false;
		palette = new int[65536];
		lowMem = false;
		for (int i = 1; i < 512; i++)
		    gradientFactors[i] = 32768 / i;
		for (int i = 1; i < 2048; i++)
		    anIntArray2106[i] = 65536 / i;
		for (int i = 0; i < 2048; i++) {
		    sineTable[i]
			= (int) (Math.sin((double) i * 0.0030679615) * 65536.0);
		    cosineTable[i]
			= (int) (Math.cos((double) i * 0.0030679615) * 65536.0);
		}
    }
    
    static final void method284(int startX_, int startY_) {
		int offset = lineOffsets[0];
		int width = offset / Graphics2D.width;
		int height = offset - width * Graphics2D.width;
		rasterizeCenterX = startX_ - height;
		rasterizeCenterY = startY_ - width;
		anInt2118 = -rasterizeCenterX;
		renderCenterX = endX - rasterizeCenterX;
		anInt2109 = -rasterizeCenterY;
		renderCenterY = endY - rasterizeCenterY;
    }
    
    static final void generatePalette(double brightness) {
    	int paletteId = 0;
    	for (int hsId = 0; hsId < 512; hsId++) {
    		double hue = (double) (hsId >> 3) / 64.0 + 0.0078125;
    		double saturation = (double) (hsId & 0x7) / 8.0 + 0.0625;
    		for (int lId = 0; lId < 128; lId++) {
    			double lightness = (double) lId / 128.0;
    			double r = lightness;
    			double g = lightness;
    			double b = lightness;
    			if (saturation != 0.0) {
    				
    				double q;
    				if (lightness < 0.5)
    					q = lightness * (saturation + 1.0);
    				else
    					q = lightness + saturation - lightness * saturation;
    				
    				double p = lightness * 2.0 - q;
    				
    				double redHue = hue + 0.3333333333333333;
    				if (redHue > 1.0)
    					redHue--;
    				
    				double greenHue = hue;
    				
    				double blueHue = hue - 0.3333333333333333;
    				if (blueHue < 0.0)
    					blueHue++;
    				
    				//Red starts here
    				if (redHue * 6.0 < 1.0)
    					r = p + (q - p) * 6.0 * redHue;
    				else if (redHue * 2.0 < 1.0)
    					r = q;
    				else if (redHue * 3.0 < 2.0)
    					r = p + ((q - p) * (0.6666666666666666 - redHue) * 6.0);
    				else
    					r = p;
    				//Green starts here
    				if (greenHue * 6.0 < 1.0)
    					g = p + (q - p) * 6.0 * greenHue;
    				else if (greenHue * 2.0 < 1.0)
    					g = q;
    				else if (greenHue * 3.0 < 2.0)
    					g = p + ((q - p) * (0.6666666666666666 - greenHue) * 6.0);
    				else
    					g = p;
    				//Blue stats here
    				if (blueHue * 6.0 < 1.0)
    					b = p + (q - p) * 6.0 * blueHue;
    				else if (blueHue * 2.0 < 1.0)
    					b = q;
    				else if (blueHue * 3.0 < 2.0)
    					b = p + ((q - p) * (0.6666666666666666 - blueHue) * 6.0);
    				else
    					b = p;
    			}
    			int red = (int) (r * 256.0);
    			int green = (int) (g * 256.0);
    			int blue = (int) (b * 256.0);
    			int rgb = (red << 16) + (green << 8) + blue;
    			rgb = modifyBrightness(rgb, brightness);
    			if (rgb == 0)
    				rgb = 1;
    			palette[paletteId++] = rgb;
    		}
		}
    }
    
    private static final void drawScanLine(int[] dest, int destOff, int color, int loops, int startX, int endX_) {
		if (edgeRestricted) {
			if (endX_ > endX)
				endX_ = endX;
			if (startX < 0)
				startX = 0;
		}
		if (startX < endX_) {
			destOff += startX;
			loops = endX_ - startX >> 2;
			if (alpha == 0) {
				while (--loops >= 0) {
					dest[destOff++] = color;
					dest[destOff++] = color;
					dest[destOff++] = color;
					dest[destOff++] = color;
				}
				loops = endX_ - startX & 0x3;
				while (--loops >= 0)
					dest[destOff++] = color;
			} else {
				int i_25_ = alpha;
				int i_26_ = 256 - alpha;
				color = (((color & 0xff00ff) * i_26_ >> 8 & 0xff00ff) + ((color & 0xff00) * i_26_ >> 8 & 0xff00));
				while (--loops >= 0) {
					dest[destOff++] = (color + ((dest[destOff] & 0xff00ff) * i_25_ >> 8 & 0xff00ff) + ((dest[destOff] & 0xff00) * i_25_ >> 8 & 0xff00));
					dest[destOff++] = (color + ((dest[destOff] & 0xff00ff) * i_25_ >> 8 & 0xff00ff) + ((dest[destOff] & 0xff00) * i_25_ >> 8 & 0xff00));
					dest[destOff++] = (color + ((dest[destOff] & 0xff00ff) * i_25_ >> 8 & 0xff00ff) + ((dest[destOff] & 0xff00) * i_25_ >> 8 & 0xff00));
					dest[destOff++] = (color + ((dest[destOff] & 0xff00ff) * i_25_ >> 8 & 0xff00ff) + ((dest[destOff] & 0xff00) * i_25_ >> 8 & 0xff00));
				}	
				loops = endX_ - startX & 0x3;
				while (--loops >= 0)
					dest[destOff++] = (color + ((dest[destOff] & 0xff00ff) * i_25_ >> 8 & 0xff00ff) + ((dest[destOff] & 0xff00) * i_25_ >> 8 & 0xff00));
			}
		}
    }
    
    static final int[] getLineOffsets() {
    	return lineOffsets;
    }
    
    static final int[] initOffsets(int[] offset) {
    	return initOffsets(Graphics2D.topX, Graphics2D.topY, Graphics2D.bottomX, Graphics2D.bottomY, offset);
    }
    
    static final void drawTexturedTriangle(int i, int i_27_, int i_28_, int i_29_,
				int i_30_, int i_31_, int i_32_, int i_33_,
				int i_34_, int i_35_, int i_36_, int i_37_,
				int i_38_, int i_39_, int i_40_, int i_41_,
				int i_42_, int i_43_, int texture) {
		int[] is = textureInterface.method7(texture);
	if (is == null) {
	    int i_45_ = textureInterface.getTextureId(texture);
	    drawShadedTriangle(i, i_27_, i_28_, i_29_, i_30_, i_31_,
		      method293(i_45_, i_32_), method293(i_45_, i_33_),
		      method293(i_45_, i_34_));
	} else {
	    lowMem = textureInterface.isLowMem();
	    transparent = textureInterface.isTransparent(texture);
	    i_36_ = i_35_ - i_36_;
	    i_39_ = i_38_ - i_39_;
	    i_42_ = i_41_ - i_42_;
	    i_37_ -= i_35_;
	    i_40_ -= i_38_;
	    i_43_ -= i_41_;
	    int i_46_ = i_37_ * i_38_ - i_40_ * i_35_ << 14;
	    int i_47_ = i_40_ * i_41_ - i_43_ * i_38_ << 8;
	    int i_48_ = i_43_ * i_35_ - i_37_ * i_41_ << 5;
	    int i_49_ = i_36_ * i_38_ - i_39_ * i_35_ << 14;
	    int i_50_ = i_39_ * i_41_ - i_42_ * i_38_ << 8;
	    int i_51_ = i_42_ * i_35_ - i_36_ * i_41_ << 5;
	    int i_52_ = i_39_ * i_37_ - i_36_ * i_40_ << 14;
	    int i_53_ = i_42_ * i_40_ - i_39_ * i_43_ << 8;
	    int i_54_ = i_36_ * i_43_ - i_42_ * i_37_ << 5;
	    int i_55_ = 0;
	    int i_56_ = 0;
	    if (i_27_ != i) {
		i_55_ = (i_30_ - i_29_ << 16) / (i_27_ - i);
		i_56_ = (i_33_ - i_32_ << 16) / (i_27_ - i);
	    }
	    int i_57_ = 0;
	    int i_58_ = 0;
	    if (i_28_ != i_27_) {
		i_57_ = (i_31_ - i_30_ << 16) / (i_28_ - i_27_);
		i_58_ = (i_34_ - i_33_ << 16) / (i_28_ - i_27_);
	    }
	    int i_59_ = 0;
	    int i_60_ = 0;
	    if (i_28_ != i) {
		i_59_ = (i_29_ - i_31_ << 16) / (i - i_28_);
		i_60_ = (i_32_ - i_34_ << 16) / (i - i_28_);
	    }
	    if (i <= i_27_ && i <= i_28_) {
		if (i < endY) {
		    if (i_27_ > endY)
			i_27_ = endY;
		    if (i_28_ > endY)
			i_28_ = endY;
		    if (i_27_ < i_28_) {
			i_31_ = i_29_ <<= 16;
			i_34_ = i_32_ <<= 16;
			if (i < 0) {
			    i_31_ -= i_59_ * i;
			    i_29_ -= i_55_ * i;
			    i_34_ -= i_60_ * i;
			    i_32_ -= i_56_ * i;
			    i = 0;
			}
			i_30_ <<= 16;
			i_33_ <<= 16;
			if (i_27_ < 0) {
			    i_30_ -= i_57_ * i_27_;
			    i_33_ -= i_58_ * i_27_;
			    i_27_ = 0;
			}
			int i_61_ = i - rasterizeCenterY;
			i_46_ += i_48_ * i_61_;
			i_49_ += i_51_ * i_61_;
			i_52_ += i_54_ * i_61_;
			if (i != i_27_ && i_59_ < i_55_
			    || i == i_27_ && i_59_ > i_57_) {
			    i_28_ -= i_27_;
			    i_27_ -= i;
			    i = lineOffsets[i];
			    while (--i_27_ >= 0) {
				drawTexturedLine(Graphics2D.pixels, is,
					  0, 0, i, i_31_ >> 16, i_29_ >> 16,
					  i_34_ >> 8, i_32_ >> 8, i_46_, i_49_,
					  i_52_, i_47_, i_50_, i_53_);
				i_31_ += i_59_;
				i_29_ += i_55_;
				i_34_ += i_60_;
				i_32_ += i_56_;
				i += Graphics2D.width;
				i_46_ += i_48_;
				i_49_ += i_51_;
				i_52_ += i_54_;
			    }
			    while (--i_28_ >= 0) {
				drawTexturedLine(Graphics2D.pixels, is,
					  0, 0, i, i_31_ >> 16, i_30_ >> 16,
					  i_34_ >> 8, i_33_ >> 8, i_46_, i_49_,
					  i_52_, i_47_, i_50_, i_53_);
				i_31_ += i_59_;
				i_30_ += i_57_;
				i_34_ += i_60_;
				i_33_ += i_58_;
				i += Graphics2D.width;
				i_46_ += i_48_;
				i_49_ += i_51_;
				i_52_ += i_54_;
			    }
			} else {
			    i_28_ -= i_27_;
			    i_27_ -= i;
			    i = lineOffsets[i];
			    while (--i_27_ >= 0) {
				drawTexturedLine(Graphics2D.pixels, is,
					  0, 0, i, i_29_ >> 16, i_31_ >> 16,
					  i_32_ >> 8, i_34_ >> 8, i_46_, i_49_,
					  i_52_, i_47_, i_50_, i_53_);
				i_31_ += i_59_;
				i_29_ += i_55_;
				i_34_ += i_60_;
				i_32_ += i_56_;
				i += Graphics2D.width;
				i_46_ += i_48_;
				i_49_ += i_51_;
				i_52_ += i_54_;
			    }
			    while (--i_28_ >= 0) {
				drawTexturedLine(Graphics2D.pixels, is,
					  0, 0, i, i_30_ >> 16, i_31_ >> 16,
					  i_33_ >> 8, i_34_ >> 8, i_46_, i_49_,
					  i_52_, i_47_, i_50_, i_53_);
				i_31_ += i_59_;
				i_30_ += i_57_;
				i_34_ += i_60_;
				i_33_ += i_58_;
				i += Graphics2D.width;
				i_46_ += i_48_;
				i_49_ += i_51_;
				i_52_ += i_54_;
			    }
			}
		    } else {
			i_30_ = i_29_ <<= 16;
			i_33_ = i_32_ <<= 16;
			if (i < 0) {
			    i_30_ -= i_59_ * i;
			    i_29_ -= i_55_ * i;
			    i_33_ -= i_60_ * i;
			    i_32_ -= i_56_ * i;
			    i = 0;
			}
			i_31_ <<= 16;
			i_34_ <<= 16;
			if (i_28_ < 0) {
			    i_31_ -= i_57_ * i_28_;
			    i_34_ -= i_58_ * i_28_;
			    i_28_ = 0;
			}
			int i_62_ = i - rasterizeCenterY;
			i_46_ += i_48_ * i_62_;
			i_49_ += i_51_ * i_62_;
			i_52_ += i_54_ * i_62_;
			if (i != i_28_ && i_59_ < i_55_
			    || i == i_28_ && i_57_ > i_55_) {
			    i_27_ -= i_28_;
			    i_28_ -= i;
			    i = lineOffsets[i];
			    while (--i_28_ >= 0) {
				drawTexturedLine(Graphics2D.pixels, is,
					  0, 0, i, i_30_ >> 16, i_29_ >> 16,
					  i_33_ >> 8, i_32_ >> 8, i_46_, i_49_,
					  i_52_, i_47_, i_50_, i_53_);
				i_30_ += i_59_;
				i_29_ += i_55_;
				i_33_ += i_60_;
				i_32_ += i_56_;
				i += Graphics2D.width;
				i_46_ += i_48_;
				i_49_ += i_51_;
				i_52_ += i_54_;
			    }
			    while (--i_27_ >= 0) {
				drawTexturedLine(Graphics2D.pixels, is,
					  0, 0, i, i_31_ >> 16, i_29_ >> 16,
					  i_34_ >> 8, i_32_ >> 8, i_46_, i_49_,
					  i_52_, i_47_, i_50_, i_53_);
				i_31_ += i_57_;
				i_29_ += i_55_;
				i_34_ += i_58_;
				i_32_ += i_56_;
				i += Graphics2D.width;
				i_46_ += i_48_;
				i_49_ += i_51_;
				i_52_ += i_54_;
			    }
			} else {
			    i_27_ -= i_28_;
			    i_28_ -= i;
			    i = lineOffsets[i];
			    while (--i_28_ >= 0) {
				drawTexturedLine(Graphics2D.pixels, is,
					  0, 0, i, i_29_ >> 16, i_30_ >> 16,
					  i_32_ >> 8, i_33_ >> 8, i_46_, i_49_,
					  i_52_, i_47_, i_50_, i_53_);
				i_30_ += i_59_;
				i_29_ += i_55_;
				i_33_ += i_60_;
				i_32_ += i_56_;
				i += Graphics2D.width;
				i_46_ += i_48_;
				i_49_ += i_51_;
				i_52_ += i_54_;
			    }
			    while (--i_27_ >= 0) {
				drawTexturedLine(Graphics2D.pixels, is,
					  0, 0, i, i_29_ >> 16, i_31_ >> 16,
					  i_32_ >> 8, i_34_ >> 8, i_46_, i_49_,
					  i_52_, i_47_, i_50_, i_53_);
				i_31_ += i_57_;
				i_29_ += i_55_;
				i_34_ += i_58_;
				i_32_ += i_56_;
				i += Graphics2D.width;
				i_46_ += i_48_;
				i_49_ += i_51_;
				i_52_ += i_54_;
			    }
			}
		    }
		}
	    } else if (i_27_ <= i_28_) {
		if (i_27_ < endY) {
		    if (i_28_ > endY)
			i_28_ = endY;
		    if (i > endY)
			i = endY;
		    if (i_28_ < i) {
			i_29_ = i_30_ <<= 16;
			i_32_ = i_33_ <<= 16;
			if (i_27_ < 0) {
			    i_29_ -= i_55_ * i_27_;
			    i_30_ -= i_57_ * i_27_;
			    i_32_ -= i_56_ * i_27_;
			    i_33_ -= i_58_ * i_27_;
			    i_27_ = 0;
			}
			i_31_ <<= 16;
			i_34_ <<= 16;
			if (i_28_ < 0) {
			    i_31_ -= i_59_ * i_28_;
			    i_34_ -= i_60_ * i_28_;
			    i_28_ = 0;
			}
			int i_63_ = i_27_ - rasterizeCenterY;
			i_46_ += i_48_ * i_63_;
			i_49_ += i_51_ * i_63_;
			i_52_ += i_54_ * i_63_;
			if (i_27_ != i_28_ && i_55_ < i_57_
			    || i_27_ == i_28_ && i_55_ > i_59_) {
			    i -= i_28_;
			    i_28_ -= i_27_;
			    i_27_ = lineOffsets[i_27_];
			    while (--i_28_ >= 0) {
				drawTexturedLine(Graphics2D.pixels, is,
					  0, 0, i_27_, i_29_ >> 16,
					  i_30_ >> 16, i_32_ >> 8, i_33_ >> 8,
					  i_46_, i_49_, i_52_, i_47_, i_50_,
					  i_53_);
				i_29_ += i_55_;
				i_30_ += i_57_;
				i_32_ += i_56_;
				i_33_ += i_58_;
				i_27_ += Graphics2D.width;
				i_46_ += i_48_;
				i_49_ += i_51_;
				i_52_ += i_54_;
			    }
			    while (--i >= 0) {
				drawTexturedLine(Graphics2D.pixels, is,
					  0, 0, i_27_, i_29_ >> 16,
					  i_31_ >> 16, i_32_ >> 8, i_34_ >> 8,
					  i_46_, i_49_, i_52_, i_47_, i_50_,
					  i_53_);
				i_29_ += i_55_;
				i_31_ += i_59_;
				i_32_ += i_56_;
				i_34_ += i_60_;
				i_27_ += Graphics2D.width;
				i_46_ += i_48_;
				i_49_ += i_51_;
				i_52_ += i_54_;
			    }
			} else {
			    i -= i_28_;
			    i_28_ -= i_27_;
			    i_27_ = lineOffsets[i_27_];
			    while (--i_28_ >= 0) {
				drawTexturedLine(Graphics2D.pixels, is,
					  0, 0, i_27_, i_30_ >> 16,
					  i_29_ >> 16, i_33_ >> 8, i_32_ >> 8,
					  i_46_, i_49_, i_52_, i_47_, i_50_,
					  i_53_);
				i_29_ += i_55_;
				i_30_ += i_57_;
				i_32_ += i_56_;
				i_33_ += i_58_;
				i_27_ += Graphics2D.width;
				i_46_ += i_48_;
				i_49_ += i_51_;
				i_52_ += i_54_;
			    }
			    while (--i >= 0) {
				drawTexturedLine(Graphics2D.pixels, is,
					  0, 0, i_27_, i_31_ >> 16,
					  i_29_ >> 16, i_34_ >> 8, i_32_ >> 8,
					  i_46_, i_49_, i_52_, i_47_, i_50_,
					  i_53_);
				i_29_ += i_55_;
				i_31_ += i_59_;
				i_32_ += i_56_;
				i_34_ += i_60_;
				i_27_ += Graphics2D.width;
				i_46_ += i_48_;
				i_49_ += i_51_;
				i_52_ += i_54_;
			    }
			}
		    } else {
			i_31_ = i_30_ <<= 16;
			i_34_ = i_33_ <<= 16;
			if (i_27_ < 0) {
			    i_31_ -= i_55_ * i_27_;
			    i_30_ -= i_57_ * i_27_;
			    i_34_ -= i_56_ * i_27_;
			    i_33_ -= i_58_ * i_27_;
			    i_27_ = 0;
			}
			i_29_ <<= 16;
			i_32_ <<= 16;
			if (i < 0) {
			    i_29_ -= i_59_ * i;
			    i_32_ -= i_60_ * i;
			    i = 0;
			}
			int i_64_ = i_27_ - rasterizeCenterY;
			i_46_ += i_48_ * i_64_;
			i_49_ += i_51_ * i_64_;
			i_52_ += i_54_ * i_64_;
			if (i_55_ < i_57_) {
			    i_28_ -= i;
			    i -= i_27_;
			    i_27_ = lineOffsets[i_27_];
			    while (--i >= 0) {
				drawTexturedLine(Graphics2D.pixels, is,
					  0, 0, i_27_, i_31_ >> 16,
					  i_30_ >> 16, i_34_ >> 8, i_33_ >> 8,
					  i_46_, i_49_, i_52_, i_47_, i_50_,
					  i_53_);
				i_31_ += i_55_;
				i_30_ += i_57_;
				i_34_ += i_56_;
				i_33_ += i_58_;
				i_27_ += Graphics2D.width;
				i_46_ += i_48_;
				i_49_ += i_51_;
				i_52_ += i_54_;
			    }
			    while (--i_28_ >= 0) {
				drawTexturedLine(Graphics2D.pixels, is,
					  0, 0, i_27_, i_29_ >> 16,
					  i_30_ >> 16, i_32_ >> 8, i_33_ >> 8,
					  i_46_, i_49_, i_52_, i_47_, i_50_,
					  i_53_);
				i_29_ += i_59_;
				i_30_ += i_57_;
				i_32_ += i_60_;
				i_33_ += i_58_;
				i_27_ += Graphics2D.width;
				i_46_ += i_48_;
				i_49_ += i_51_;
				i_52_ += i_54_;
			    }
			} else {
			    i_28_ -= i;
			    i -= i_27_;
			    i_27_ = lineOffsets[i_27_];
			    while (--i >= 0) {
				drawTexturedLine(Graphics2D.pixels, is,
					  0, 0, i_27_, i_30_ >> 16,
					  i_31_ >> 16, i_33_ >> 8, i_34_ >> 8,
					  i_46_, i_49_, i_52_, i_47_, i_50_,
					  i_53_);
				i_31_ += i_55_;
				i_30_ += i_57_;
				i_34_ += i_56_;
				i_33_ += i_58_;
				i_27_ += Graphics2D.width;
				i_46_ += i_48_;
				i_49_ += i_51_;
				i_52_ += i_54_;
			    }
			    while (--i_28_ >= 0) {
				drawTexturedLine(Graphics2D.pixels, is,
					  0, 0, i_27_, i_30_ >> 16,
					  i_29_ >> 16, i_33_ >> 8, i_32_ >> 8,
					  i_46_, i_49_, i_52_, i_47_, i_50_,
					  i_53_);
				i_29_ += i_59_;
				i_30_ += i_57_;
				i_32_ += i_60_;
				i_33_ += i_58_;
				i_27_ += Graphics2D.width;
				i_46_ += i_48_;
				i_49_ += i_51_;
				i_52_ += i_54_;
			    }
			}
		    }
		}
	    } else if (i_28_ < endY) {
		if (i > endY)
		    i = endY;
		if (i_27_ > endY)
		    i_27_ = endY;
		if (i < i_27_) {
		    i_30_ = i_31_ <<= 16;
		    i_33_ = i_34_ <<= 16;
		    if (i_28_ < 0) {
			i_30_ -= i_57_ * i_28_;
			i_31_ -= i_59_ * i_28_;
			i_33_ -= i_58_ * i_28_;
			i_34_ -= i_60_ * i_28_;
			i_28_ = 0;
		    }
		    i_29_ <<= 16;
		    i_32_ <<= 16;
		    if (i < 0) {
			i_29_ -= i_55_ * i;
			i_32_ -= i_56_ * i;
			i = 0;
		    }
		    int i_65_ = i_28_ - rasterizeCenterY;
		    i_46_ += i_48_ * i_65_;
		    i_49_ += i_51_ * i_65_;
		    i_52_ += i_54_ * i_65_;
		    if (i_57_ < i_59_) {
			i_27_ -= i;
			i -= i_28_;
			i_28_ = lineOffsets[i_28_];
			while (--i >= 0) {
			    drawTexturedLine(Graphics2D.pixels, is, 0,
				      0, i_28_, i_30_ >> 16, i_31_ >> 16,
				      i_33_ >> 8, i_34_ >> 8, i_46_, i_49_,
				      i_52_, i_47_, i_50_, i_53_);
			    i_30_ += i_57_;
			    i_31_ += i_59_;
			    i_33_ += i_58_;
			    i_34_ += i_60_;
			    i_28_ += Graphics2D.width;
			    i_46_ += i_48_;
			    i_49_ += i_51_;
			    i_52_ += i_54_;
			}
			while (--i_27_ >= 0) {
			    drawTexturedLine(Graphics2D.pixels, is, 0,
				      0, i_28_, i_30_ >> 16, i_29_ >> 16,
				      i_33_ >> 8, i_32_ >> 8, i_46_, i_49_,
				      i_52_, i_47_, i_50_, i_53_);
			    i_30_ += i_57_;
			    i_29_ += i_55_;
			    i_33_ += i_58_;
			    i_32_ += i_56_;
			    i_28_ += Graphics2D.width;
			    i_46_ += i_48_;
			    i_49_ += i_51_;
			    i_52_ += i_54_;
			}
		    } else {
			i_27_ -= i;
			i -= i_28_;
			i_28_ = lineOffsets[i_28_];
			while (--i >= 0) {
			    drawTexturedLine(Graphics2D.pixels, is, 0,
				      0, i_28_, i_31_ >> 16, i_30_ >> 16,
				      i_34_ >> 8, i_33_ >> 8, i_46_, i_49_,
				      i_52_, i_47_, i_50_, i_53_);
			    i_30_ += i_57_;
			    i_31_ += i_59_;
			    i_33_ += i_58_;
			    i_34_ += i_60_;
			    i_28_ += Graphics2D.width;
			    i_46_ += i_48_;
			    i_49_ += i_51_;
			    i_52_ += i_54_;
			}
			while (--i_27_ >= 0) {
			    drawTexturedLine(Graphics2D.pixels, is, 0,
				      0, i_28_, i_29_ >> 16, i_30_ >> 16,
				      i_32_ >> 8, i_33_ >> 8, i_46_, i_49_,
				      i_52_, i_47_, i_50_, i_53_);
			    i_30_ += i_57_;
			    i_29_ += i_55_;
			    i_33_ += i_58_;
			    i_32_ += i_56_;
			    i_28_ += Graphics2D.width;
			    i_46_ += i_48_;
			    i_49_ += i_51_;
			    i_52_ += i_54_;
			}
		    }
		} else {
		    i_29_ = i_31_ <<= 16;
		    i_32_ = i_34_ <<= 16;
		    if (i_28_ < 0) {
			i_29_ -= i_57_ * i_28_;
			i_31_ -= i_59_ * i_28_;
			i_32_ -= i_58_ * i_28_;
			i_34_ -= i_60_ * i_28_;
			i_28_ = 0;
		    }
		    i_30_ <<= 16;
		    i_33_ <<= 16;
		    if (i_27_ < 0) {
			i_30_ -= i_55_ * i_27_;
			i_33_ -= i_56_ * i_27_;
			i_27_ = 0;
		    }
		    int i_66_ = i_28_ - rasterizeCenterY;
		    i_46_ += i_48_ * i_66_;
		    i_49_ += i_51_ * i_66_;
		    i_52_ += i_54_ * i_66_;
		    if (i_57_ < i_59_) {
			i -= i_27_;
			i_27_ -= i_28_;
			i_28_ = lineOffsets[i_28_];
			while (--i_27_ >= 0) {
			    drawTexturedLine(Graphics2D.pixels, is, 0,
				      0, i_28_, i_29_ >> 16, i_31_ >> 16,
				      i_32_ >> 8, i_34_ >> 8, i_46_, i_49_,
				      i_52_, i_47_, i_50_, i_53_);
			    i_29_ += i_57_;
			    i_31_ += i_59_;
			    i_32_ += i_58_;
			    i_34_ += i_60_;
			    i_28_ += Graphics2D.width;
			    i_46_ += i_48_;
			    i_49_ += i_51_;
			    i_52_ += i_54_;
			}
			while (--i >= 0) {
			    drawTexturedLine(Graphics2D.pixels, is, 0,
				      0, i_28_, i_30_ >> 16, i_31_ >> 16,
				      i_33_ >> 8, i_34_ >> 8, i_46_, i_49_,
				      i_52_, i_47_, i_50_, i_53_);
			    i_30_ += i_55_;
			    i_31_ += i_59_;
			    i_33_ += i_56_;
			    i_34_ += i_60_;
			    i_28_ += Graphics2D.width;
			    i_46_ += i_48_;
			    i_49_ += i_51_;
			    i_52_ += i_54_;
			}
		    } else {
			i -= i_27_;
			i_27_ -= i_28_;
			i_28_ = lineOffsets[i_28_];
			while (--i_27_ >= 0) {
			    drawTexturedLine(Graphics2D.pixels, is, 0,
				      0, i_28_, i_31_ >> 16, i_29_ >> 16,
				      i_34_ >> 8, i_32_ >> 8, i_46_, i_49_,
				      i_52_, i_47_, i_50_, i_53_);
			    i_29_ += i_57_;
			    i_31_ += i_59_;
			    i_32_ += i_58_;
			    i_34_ += i_60_;
			    i_28_ += Graphics2D.width;
			    i_46_ += i_48_;
			    i_49_ += i_51_;
			    i_52_ += i_54_;
			}
			while (--i >= 0) {
			    drawTexturedLine(Graphics2D.pixels, is, 0,
				      0, i_28_, i_31_ >> 16, i_30_ >> 16,
				      i_34_ >> 8, i_33_ >> 8, i_46_, i_49_,
				      i_52_, i_47_, i_50_, i_53_);
			    i_30_ += i_55_;
			    i_31_ += i_59_;
			    i_33_ += i_56_;
			    i_34_ += i_60_;
			    i_28_ += Graphics2D.width;
			    i_46_ += i_48_;
			    i_49_ += i_51_;
			    i_52_ += i_54_;
			}
		    }
		}
	    }
	}
    }
    
    static final void drawShadedTriangle(int i, int i_67_, int i_68_, int i_69_,
    		int i_70_, int i_71_, int i_72_, int i_73_,
    		int i_74_) {
    	int i_75_ = 0;
    	int i_76_ = 0;
    	if (i_67_ != i) {
    		i_75_ = (i_70_ - i_69_ << 16) / (i_67_ - i);
    		i_76_ = (i_73_ - i_72_ << 15) / (i_67_ - i);
    	}
    	int i_77_ = 0;
    	int i_78_ = 0;
    	if (i_68_ != i_67_) {
    		i_77_ = (i_71_ - i_70_ << 16) / (i_68_ - i_67_);
    		i_78_ = (i_74_ - i_73_ << 15) / (i_68_ - i_67_);
    	}
    	int i_79_ = 0;
    	int i_80_ = 0;
    	if (i_68_ != i) {
    		i_79_ = (i_69_ - i_71_ << 16) / (i - i_68_);
    		i_80_ = (i_72_ - i_74_ << 15) / (i - i_68_);
    	}
    	if (i <= i_67_ && i <= i_68_) {
    		if (i < endY) {
    			if (i_67_ > endY)
    				i_67_ = endY;
    			if (i_68_ > endY)
    				i_68_ = endY;
    			if (i_67_ < i_68_) {
    				i_71_ = i_69_ <<= 16;
    				i_74_ = i_72_ <<= 15;
    				if (i < 0) {
    					i_71_ -= i_79_ * i;
    					i_69_ -= i_75_ * i;
    					i_74_ -= i_80_ * i;
    					i_72_ -= i_76_ * i;
    					i = 0;
    				}
    				i_70_ <<= 16;
    				i_73_ <<= 15;
    				if (i_67_ < 0) {
    					i_70_ -= i_77_ * i_67_;
    					i_73_ -= i_78_ * i_67_;
    					i_67_ = 0;
    				}
				    if (i != i_67_ && i_79_ < i_75_ || i == i_67_ && i_79_ > i_77_) {
				    	i_68_ -= i_67_;
				    	i_67_ -= i;
				    	i = lineOffsets[i];
				    	while (--i_67_ >= 0) {
				    		drawShadedLine(Graphics2D.pixels, i, 0, 0, i_71_ >> 16, i_69_ >> 16, i_74_ >> 7, i_72_ >> 7);
				    		i_71_ += i_79_;
				    		i_69_ += i_75_;
				    		i_74_ += i_80_;
				    		i_72_ += i_76_;
				    		i += Graphics2D.width;
				    	}
				    	while (--i_68_ >= 0) {
				    		drawShadedLine(Graphics2D.pixels, i, 0, 0, i_71_ >> 16, i_70_ >> 16, i_74_ >> 7, i_73_ >> 7);
				    		i_71_ += i_79_;
				    		i_70_ += i_77_;
				    		i_74_ += i_80_;
				    		i_73_ += i_78_;
				    		i += Graphics2D.width;
				    	}
				    } else {
				    	i_68_ -= i_67_;
				    	i_67_ -= i;
				    	i = lineOffsets[i];
				    	while (--i_67_ >= 0) {
				    		drawShadedLine(Graphics2D.pixels, i, 0, 0, i_69_ >> 16, i_71_ >> 16, i_72_ >> 7, i_74_ >> 7);
				    		i_71_ += i_79_;
				    		i_69_ += i_75_;
				    		i_74_ += i_80_;
				    		i_72_ += i_76_;
				    		i += Graphics2D.width;
				    	}
				    	while (--i_68_ >= 0) {
				    		drawShadedLine(Graphics2D.pixels, i, 0, 0, i_70_ >> 16, i_71_ >> 16, i_73_ >> 7, i_74_ >> 7);
				    		i_71_ += i_79_;
				    		i_70_ += i_77_;
				    		i_74_ += i_80_;
				    		i_73_ += i_78_;
				    		i += Graphics2D.width;
				    	}
				    }
    			} else {
    				i_70_ = i_69_ <<= 16;
    				i_73_ = i_72_ <<= 15;
    				if (i < 0) {
    					i_70_ -= i_79_ * i;
    					i_69_ -= i_75_ * i;
    					i_73_ -= i_80_ * i;
    					i_72_ -= i_76_ * i;
    					i = 0;
    				}
    				i_71_ <<= 16;
    				i_74_ <<= 15;
    				if (i_68_ < 0) {
    					i_71_ -= i_77_ * i_68_;
    					i_74_ -= i_78_ * i_68_;
    					i_68_ = 0;
    				}
    				if (i != i_68_ && i_79_ < i_75_ || i == i_68_ && i_77_ > i_75_) {
    					i_67_ -= i_68_;
    					i_68_ -= i;
    					i = lineOffsets[i];
    					while (--i_68_ >= 0) {
    						drawShadedLine(Graphics2D.pixels, i, 0, 0, i_70_ >> 16, i_69_ >> 16, i_73_ >> 7, i_72_ >> 7);
    						i_70_ += i_79_;
    						i_69_ += i_75_;
    						i_73_ += i_80_;
    						i_72_ += i_76_;
    						i += Graphics2D.width;
    					}
    					while (--i_67_ >= 0) {
    						drawShadedLine(Graphics2D.pixels, i, 0, 0, i_71_ >> 16, i_69_ >> 16, i_74_ >> 7, i_72_ >> 7);
    						i_71_ += i_77_;
    						i_69_ += i_75_;
    						i_74_ += i_78_;
    						i_72_ += i_76_;
    						i += Graphics2D.width;
    					}
    				} else {
    					i_67_ -= i_68_;
    					i_68_ -= i;
    					i = lineOffsets[i];
    					while (--i_68_ >= 0) {
    						drawShadedLine(Graphics2D.pixels, i, 0, 0, i_69_ >> 16, i_70_ >> 16, i_72_ >> 7, i_73_ >> 7);
    						i_70_ += i_79_;
    						i_69_ += i_75_;
    						i_73_ += i_80_;
    						i_72_ += i_76_;
    						i += Graphics2D.width;
    					}
    					while (--i_67_ >= 0) {
    						drawShadedLine(Graphics2D.pixels, i, 0, 0, i_69_ >> 16, i_71_ >> 16, i_72_ >> 7, i_74_ >> 7);
    						i_71_ += i_77_;
    						i_69_ += i_75_;
    						i_74_ += i_78_;
    						i_72_ += i_76_;
    						i += Graphics2D.width;
    					}
    				}
    			}
    		}
    	} else if (i_67_ <= i_68_) {
    		if (i_67_ < endY) {
    			if (i_68_ > endY)
    				i_68_ = endY;
    			if (i > endY)
    				i = endY;
    			if (i_68_ < i) {
    				i_69_ = i_70_ <<= 16;
    				i_72_ = i_73_ <<= 15;
    				if (i_67_ < 0) {
    					i_69_ -= i_75_ * i_67_;
    					i_70_ -= i_77_ * i_67_;
    					i_72_ -= i_76_ * i_67_;
    					i_73_ -= i_78_ * i_67_;
    					i_67_ = 0;
    				}
    				i_71_ <<= 16;
    				i_74_ <<= 15;
    				if (i_68_ < 0) {
    					i_71_ -= i_79_ * i_68_;
    					i_74_ -= i_80_ * i_68_;
    					i_68_ = 0;
    				}
    				if (i_67_ != i_68_ && i_75_ < i_77_ || i_67_ == i_68_ && i_75_ > i_79_) {
    					i -= i_68_;
    					i_68_ -= i_67_;
    					i_67_ = lineOffsets[i_67_];
    					while (--i_68_ >= 0) {
    						drawShadedLine(Graphics2D.pixels, i_67_, 0, 0, i_69_ >> 16, i_70_ >> 16, i_72_ >> 7, i_73_ >> 7);
    						i_69_ += i_75_;
    						i_70_ += i_77_;
    						i_72_ += i_76_;
    						i_73_ += i_78_;
    						i_67_ += Graphics2D.width;
    					}
    					while (--i >= 0) {
    						drawShadedLine(Graphics2D.pixels, i_67_, 0, 0, i_69_ >> 16, i_71_ >> 16, i_72_ >> 7, i_74_ >> 7);
    						i_69_ += i_75_;
    						i_71_ += i_79_;
    						i_72_ += i_76_;
    						i_74_ += i_80_;
    						i_67_ += Graphics2D.width;
    					}
    				} else {
    					i -= i_68_;
    					i_68_ -= i_67_;
    					i_67_ = lineOffsets[i_67_];
    					while (--i_68_ >= 0) {
    						drawShadedLine(Graphics2D.pixels, i_67_, 0, 0, i_70_ >> 16, i_69_ >> 16, i_73_ >> 7, i_72_ >> 7);
    						i_69_ += i_75_;
    						i_70_ += i_77_;
    						i_72_ += i_76_;
    						i_73_ += i_78_;
    						i_67_ += Graphics2D.width;
    					}
    					while (--i >= 0) {
    						drawShadedLine(Graphics2D.pixels, i_67_, 0, 0, i_71_ >> 16, i_69_ >> 16, i_74_ >> 7, i_72_ >> 7);
    						i_69_ += i_75_;
    						i_71_ += i_79_;
    						i_72_ += i_76_;
    						i_74_ += i_80_;
    						i_67_ += Graphics2D.width;
    					}
    				}
    			} else {
    				i_71_ = i_70_ <<= 16;
    				i_74_ = i_73_ <<= 15;
    				if (i_67_ < 0) {
    					i_71_ -= i_75_ * i_67_;
    					i_70_ -= i_77_ * i_67_;
    					i_74_ -= i_76_ * i_67_;
    					i_73_ -= i_78_ * i_67_;
    					i_67_ = 0;
    				}
    				i_69_ <<= 16;
    				i_72_ <<= 15;
    				if (i < 0) {
    					i_69_ -= i_79_ * i;
    					i_72_ -= i_80_ * i;
    					i = 0;
    				}
    				if (i_75_ < i_77_) {
    					i_68_ -= i;
    					i -= i_67_;
    					i_67_ = lineOffsets[i_67_];
    					while (--i >= 0) {
    						drawShadedLine(Graphics2D.pixels, i_67_, 0, 0, i_71_ >> 16, i_70_ >> 16, i_74_ >> 7, i_73_ >> 7);
    						i_71_ += i_75_;
    						i_70_ += i_77_;
    						i_74_ += i_76_;
    						i_73_ += i_78_;
    						i_67_ += Graphics2D.width;
    					}
    					while (--i_68_ >= 0) {
    						drawShadedLine(Graphics2D.pixels, i_67_, 0, 0, i_69_ >> 16, i_70_ >> 16, i_72_ >> 7, i_73_ >> 7);
    						i_69_ += i_79_;
    						i_70_ += i_77_;
    						i_72_ += i_80_;
    						i_73_ += i_78_;
    						i_67_ += Graphics2D.width;
    					}
    				} else {
    					i_68_ -= i;
    					i -= i_67_;
    					i_67_ = lineOffsets[i_67_];
    					while (--i >= 0) {
    						drawShadedLine(Graphics2D.pixels, i_67_, 0, 0, i_70_ >> 16, i_71_ >> 16, i_73_ >> 7, i_74_ >> 7);
    						i_71_ += i_75_;
    						i_70_ += i_77_;
    						i_74_ += i_76_;
    						i_73_ += i_78_;
    						i_67_ += Graphics2D.width;
    					}
    					while (--i_68_ >= 0) {
    						drawShadedLine(Graphics2D.pixels, i_67_, 0, 0, i_70_ >> 16, i_69_ >> 16, i_73_ >> 7, i_72_ >> 7);
    						i_69_ += i_79_;
    						i_70_ += i_77_;
    						i_72_ += i_80_;
    						i_73_ += i_78_;
    						i_67_ += Graphics2D.width;
    					}
    				}
    			}
    		}
    	} else if (i_68_ < endY) {
    		if (i > endY)
    			i = endY;
    		if (i_67_ > endY)
    			i_67_ = endY;
    		if (i < i_67_) {
    			i_70_ = i_71_ <<= 16;
    			i_73_ = i_74_ <<= 15;
    			if (i_68_ < 0) {
    				i_70_ -= i_77_ * i_68_;
    				i_71_ -= i_79_ * i_68_;
    				i_73_ -= i_78_ * i_68_;
    				i_74_ -= i_80_ * i_68_;
    				i_68_ = 0;
    			}
    			i_69_ <<= 16;
    			i_72_ <<= 15;
    			if (i < 0) {
    				i_69_ -= i_75_ * i;
    				i_72_ -= i_76_ * i;
    				i = 0;
    			}
    			if (i_77_ < i_79_) {
    				i_67_ -= i;
    				i -= i_68_;
    				i_68_ = lineOffsets[i_68_];
    				while (--i >= 0) {
    					drawShadedLine(Graphics2D.pixels, i_68_, 0, 0, i_70_ >> 16, i_71_ >> 16, i_73_ >> 7, i_74_ >> 7);
    					i_70_ += i_77_;
    					i_71_ += i_79_;
    					i_73_ += i_78_;
    					i_74_ += i_80_;
    					i_68_ += Graphics2D.width;
    				}
    				while (--i_67_ >= 0) {
    					drawShadedLine(Graphics2D.pixels, i_68_, 0, 0, i_70_ >> 16, i_69_ >> 16, i_73_ >> 7, i_72_ >> 7);
    					i_70_ += i_77_;
    					i_69_ += i_75_;
    					i_73_ += i_78_;
    					i_72_ += i_76_;
    					i_68_ += Graphics2D.width;
    				}
    			} else {
    				i_67_ -= i;
    				i -= i_68_;
    				i_68_ = lineOffsets[i_68_];
    				while (--i >= 0) {
    					drawShadedLine(Graphics2D.pixels, i_68_, 0, 0, i_71_ >> 16, i_70_ >> 16, i_74_ >> 7, i_73_ >> 7);
    					i_70_ += i_77_;
    					i_73_ += i_78_;
    					i_71_ += i_79_;
    					i_74_ += i_80_;
    					i_68_ += Graphics2D.width;
    				}
    				while (--i_67_ >= 0) {
    					drawShadedLine(Graphics2D.pixels, i_68_, 0, 0, i_69_ >> 16, i_70_ >> 16, i_72_ >> 7, i_73_ >> 7);
    					i_70_ += i_77_;
    					i_69_ += i_75_;
    					i_73_ += i_78_;
    					i_72_ += i_76_;
    					i_68_ += Graphics2D.width;
    				}
    			}
    		} else {
    			i_69_ = i_71_ <<= 16;
    			i_72_ = i_74_ <<= 15;
    			if (i_68_ < 0) {
    				i_69_ -= i_77_ * i_68_;
    				i_71_ -= i_79_ * i_68_;
    				i_72_ -= i_78_ * i_68_;
    				i_74_ -= i_80_ * i_68_;
    				i_68_ = 0;
    			}
    			i_70_ <<= 16;
    			i_73_ <<= 15;
    			if (i_67_ < 0) {
    				i_70_ -= i_75_ * i_67_;
    				i_73_ -= i_76_ * i_67_;
    				i_67_ = 0;
    			}
    			if (i_77_ < i_79_) {
    				i -= i_67_;
    				i_67_ -= i_68_;
    				i_68_ = lineOffsets[i_68_];
    				while (--i_67_ >= 0) {
    					drawShadedLine(Graphics2D.pixels, i_68_, 0, 0, i_69_ >> 16, i_71_ >> 16, i_72_ >> 7, i_74_ >> 7);
    					i_69_ += i_77_;
    					i_71_ += i_79_;
    					i_72_ += i_78_;
    					i_74_ += i_80_;
    					i_68_ += Graphics2D.width;
    				}
    				while (--i >= 0) {
    					drawShadedLine(Graphics2D.pixels, i_68_, 0, 0, i_70_ >> 16, i_71_ >> 16, i_73_ >> 7, i_74_ >> 7);
    					i_70_ += i_75_;
    					i_71_ += i_79_;
    					i_73_ += i_76_;
    					i_74_ += i_80_;
    					i_68_ += Graphics2D.width;
    				}
    			} else {
    				i -= i_67_;
    				i_67_ -= i_68_;
    				i_68_ = lineOffsets[i_68_];
    				while (--i_67_ >= 0) {
    					drawShadedLine(Graphics2D.pixels, i_68_, 0, 0, i_71_ >> 16, i_69_ >> 16, i_74_ >> 7, i_72_ >> 7);
    					i_69_ += i_77_;
    					i_71_ += i_79_;
    					i_72_ += i_78_;
    					i_74_ += i_80_;
    					i_68_ += Graphics2D.width;
    				}
    				while (--i >= 0) {
    					drawShadedLine(Graphics2D.pixels, i_68_, 0, 0, i_71_ >> 16, i_70_ >> 16, i_74_ >> 7, i_73_ >> 7);
    					i_70_ += i_75_;
    					i_71_ += i_79_;
    					i_73_ += i_76_;
    					i_74_ += i_80_;
    					i_68_ += Graphics2D.width;
    				}
    			}
    		}
    	}
    }
   
    private static final int[] initOffsets(int topX, int topY, int bottomX, int bottomY, int[] offsets) {
    	endX = bottomX - topX;
    	endY = bottomY - topY;
    	if (offsets == null) {
    		int y = endY;
    		if (y == 0)
    			y++;
    		lineOffsets = new int[y];
    		for (int id = 0; id < y; id++)
    			lineOffsets[id] = (topY + id) * Graphics2D.width + topX;
    	} else
    		lineOffsets = offsets;
    	method296();
    	return lineOffsets;
    }
    
    static final void drawPolygon(int destOff, int loops, int i_87_, int i_88_,
    		int i_89_, int i_90_, int color) {
    	//System.out.println(loops);
    	int i_92_ = 0;
    	if (loops != destOff)
    		i_92_ = (i_89_ - i_88_ << 16) / (loops - destOff);
    	int i_93_ = 0;
    	if (i_87_ != loops)
    		i_93_ = (i_90_ - i_89_ << 16) / (i_87_ - loops);
    	int i_94_ = 0;
    	if (i_87_ != destOff)
    		i_94_ = (i_88_ - i_90_ << 16) / (destOff - i_87_);
    	if (destOff <= loops && destOff <= i_87_) {
    		if (destOff < endY) {
    			if (loops > endY)
    				loops = endY;
    			if (i_87_ > endY)
    				i_87_ = endY;
    			if (loops < i_87_) {
    				i_90_ = i_88_ <<= 16;
    				if (destOff < 0) {
    					i_90_ -= i_94_ * destOff;
    					i_88_ -= i_92_ * destOff;
    					destOff = 0;
    				}
    				i_89_ <<= 16;
    				if (loops < 0) {
    					i_89_ -= i_93_ * loops;
    					loops = 0;
    				}
    				if (destOff != loops && i_94_ < i_92_
    						|| destOff == loops && i_94_ > i_93_) {
    					i_87_ -= loops;
    					loops -= destOff;
    					destOff = lineOffsets[destOff];
    					while (--loops >= 0) {//TODO
    						drawScanLine(Graphics2D.pixels, destOff,
    								color, 0, i_90_ >> 16, i_88_ >> 16);
    						i_90_ += i_94_;
    						i_88_ += i_92_;
    						destOff += Graphics2D.width;
    					}
			while (--i_87_ >= 0) {
			    drawScanLine(Graphics2D.pixels, destOff,
				      color, 0, i_90_ >> 16, i_89_ >> 16);
			    i_90_ += i_94_;
			    i_89_ += i_93_;
			    destOff += Graphics2D.width;
			}
		    } else {
			i_87_ -= loops;
			loops -= destOff;
			destOff = lineOffsets[destOff];
			while (--loops >= 0) {
			    drawScanLine(Graphics2D.pixels, destOff,
				      color, 0, i_88_ >> 16, i_90_ >> 16);
			    i_90_ += i_94_;
			    i_88_ += i_92_;
			    destOff += Graphics2D.width;
			}
			while (--i_87_ >= 0) {
			    drawScanLine(Graphics2D.pixels, destOff,
				      color, 0, i_89_ >> 16, i_90_ >> 16);
			    i_90_ += i_94_;
			    i_89_ += i_93_;
			    destOff += Graphics2D.width;
			}
		    }
		} else {
		    i_89_ = i_88_ <<= 16;
		    if (destOff < 0) {
			i_89_ -= i_94_ * destOff;
			i_88_ -= i_92_ * destOff;
			destOff = 0;
		    }
		    i_90_ <<= 16;
		    if (i_87_ < 0) {
			i_90_ -= i_93_ * i_87_;
			i_87_ = 0;
		    }
		    if (destOff != i_87_ && i_94_ < i_92_
			|| destOff == i_87_ && i_93_ > i_92_) {
			loops -= i_87_;
			i_87_ -= destOff;
			destOff = lineOffsets[destOff];
			while (--i_87_ >= 0) {
			    drawScanLine(Graphics2D.pixels, destOff,
				      color, 0, i_89_ >> 16, i_88_ >> 16);
			    i_89_ += i_94_;
			    i_88_ += i_92_;
			    destOff += Graphics2D.width;
			}
			while (--loops >= 0) {
			    drawScanLine(Graphics2D.pixels, destOff,
				      color, 0, i_90_ >> 16, i_88_ >> 16);
			    i_90_ += i_93_;
			    i_88_ += i_92_;
			    destOff += Graphics2D.width;
			}
		    } else {
			loops -= i_87_;
			i_87_ -= destOff;
			destOff = lineOffsets[destOff];
			while (--i_87_ >= 0) {
			    drawScanLine(Graphics2D.pixels, destOff,
				      color, 0, i_88_ >> 16, i_89_ >> 16);
			    i_89_ += i_94_;
			    i_88_ += i_92_;
			    destOff += Graphics2D.width;
			}
			while (--loops >= 0) {
			    drawScanLine(Graphics2D.pixels, destOff,
				      color, 0, i_88_ >> 16, i_90_ >> 16);
			    i_90_ += i_93_;
			    i_88_ += i_92_;
			    destOff += Graphics2D.width;
			}
		    }
		}
	    }
	} else if (loops <= i_87_) {
	    if (loops < endY) {
		if (i_87_ > endY)
		    i_87_ = endY;
		if (destOff > endY)
		    destOff = endY;
		if (i_87_ < destOff) {
		    i_88_ = i_89_ <<= 16;
		    if (loops < 0) {
			i_88_ -= i_92_ * loops;
			i_89_ -= i_93_ * loops;
			loops = 0;
		    }
		    i_90_ <<= 16;
		    if (i_87_ < 0) {
			i_90_ -= i_94_ * i_87_;
			i_87_ = 0;
		    }
		    if (loops != i_87_ && i_92_ < i_93_
			|| loops == i_87_ && i_92_ > i_94_) {
			destOff -= i_87_;
			i_87_ -= loops;
			loops = lineOffsets[loops];
			while (--i_87_ >= 0) {
			    drawScanLine(Graphics2D.pixels, loops,
				      color, 0, i_88_ >> 16, i_89_ >> 16);
			    i_88_ += i_92_;
			    i_89_ += i_93_;
			    loops += Graphics2D.width;
			}
			while (--destOff >= 0) {
			    drawScanLine(Graphics2D.pixels, loops,
				      color, 0, i_88_ >> 16, i_90_ >> 16);
			    i_88_ += i_92_;
			    i_90_ += i_94_;
			    loops += Graphics2D.width;
			}
		    } else {
			destOff -= i_87_;
			i_87_ -= loops;
			loops = lineOffsets[loops];
			while (--i_87_ >= 0) {
			    drawScanLine(Graphics2D.pixels, loops,
				      color, 0, i_89_ >> 16, i_88_ >> 16);
			    i_88_ += i_92_;
			    i_89_ += i_93_;
			    loops += Graphics2D.width;
			}
			while (--destOff >= 0) {
			    drawScanLine(Graphics2D.pixels, loops,
				      color, 0, i_90_ >> 16, i_88_ >> 16);
			    i_88_ += i_92_;
			    i_90_ += i_94_;
			    loops += Graphics2D.width;
			}
		    }
		} else {
		    i_90_ = i_89_ <<= 16;
		    if (loops < 0) {
			i_90_ -= i_92_ * loops;
			i_89_ -= i_93_ * loops;
			loops = 0;
		    }
		    i_88_ <<= 16;
		    if (destOff < 0) {
			i_88_ -= i_94_ * destOff;
			destOff = 0;
		    }
		    if (i_92_ < i_93_) {
			i_87_ -= destOff;
			destOff -= loops;
			loops = lineOffsets[loops];
			while (--destOff >= 0) {
			    drawScanLine(Graphics2D.pixels, loops,
				      color, 0, i_90_ >> 16, i_89_ >> 16);
			    i_90_ += i_92_;
			    i_89_ += i_93_;
			    loops += Graphics2D.width;
			}
			while (--i_87_ >= 0) {
			    drawScanLine(Graphics2D.pixels, loops,
				      color, 0, i_88_ >> 16, i_89_ >> 16);
			    i_88_ += i_94_;
			    i_89_ += i_93_;
			    loops += Graphics2D.width;
			}
		    } else {
			i_87_ -= destOff;
			destOff -= loops;
			loops = lineOffsets[loops];
			while (--destOff >= 0) {
			    drawScanLine(Graphics2D.pixels, loops,
				      color, 0, i_89_ >> 16, i_90_ >> 16);
			    i_90_ += i_92_;
			    i_89_ += i_93_;
			    loops += Graphics2D.width;
			}
			while (--i_87_ >= 0) {
			    drawScanLine(Graphics2D.pixels, loops,
				      color, 0, i_89_ >> 16, i_88_ >> 16);
			    i_88_ += i_94_;
			    i_89_ += i_93_;
			    loops += Graphics2D.width;
			}
		    }
		}
	    }
	} else if (i_87_ < endY) {
	    if (destOff > endY)
		destOff = endY;
	    if (loops > endY)
		loops = endY;
	    if (destOff < loops) {
		i_89_ = i_90_ <<= 16;
		if (i_87_ < 0) {
		    i_89_ -= i_93_ * i_87_;
		    i_90_ -= i_94_ * i_87_;
		    i_87_ = 0;
		}
		i_88_ <<= 16;
		if (destOff < 0) {
		    i_88_ -= i_92_ * destOff;
		    destOff = 0;
		}
		if (i_93_ < i_94_) {
		    loops -= destOff;
		    destOff -= i_87_;
		    i_87_ = lineOffsets[i_87_];
		    while (--destOff >= 0) {
			drawScanLine(Graphics2D.pixels, i_87_,
				  color, 0, i_89_ >> 16, i_90_ >> 16);
			i_89_ += i_93_;
			i_90_ += i_94_;
			i_87_ += Graphics2D.width;
		    }
		    while (--loops >= 0) {
			drawScanLine(Graphics2D.pixels, i_87_,
				  color, 0, i_89_ >> 16, i_88_ >> 16);
			i_89_ += i_93_;
			i_88_ += i_92_;
			i_87_ += Graphics2D.width;
		    }
		} else {
		    loops -= destOff;
		    destOff -= i_87_;
		    i_87_ = lineOffsets[i_87_];
		    while (--destOff >= 0) {
			drawScanLine(Graphics2D.pixels, i_87_,
				  color, 0, i_90_ >> 16, i_89_ >> 16);
			i_89_ += i_93_;
			i_90_ += i_94_;
			i_87_ += Graphics2D.width;
		    }
		    while (--loops >= 0) {
			drawScanLine(Graphics2D.pixels, i_87_,
				  color, 0, i_88_ >> 16, i_89_ >> 16);
			i_89_ += i_93_;
			i_88_ += i_92_;
			i_87_ += Graphics2D.width;
		    }
		}
	    } else {
		i_88_ = i_90_ <<= 16;
		if (i_87_ < 0) {
		    i_88_ -= i_93_ * i_87_;
		    i_90_ -= i_94_ * i_87_;
		    i_87_ = 0;
		}
		i_89_ <<= 16;
		if (loops < 0) {
		    i_89_ -= i_92_ * loops;
		    loops = 0;
		}
		if (i_93_ < i_94_) {
		    destOff -= loops;
		    loops -= i_87_;
		    i_87_ = lineOffsets[i_87_];
		    while (--loops >= 0) {
			drawScanLine(Graphics2D.pixels, i_87_,
				  color, 0, i_88_ >> 16, i_90_ >> 16);
			i_88_ += i_93_;
			i_90_ += i_94_;
			i_87_ += Graphics2D.width;
		    }
		    while (--destOff >= 0) {
			drawScanLine(Graphics2D.pixels, i_87_,
				  color, 0, i_89_ >> 16, i_90_ >> 16);
			i_89_ += i_92_;
			i_90_ += i_94_;
			i_87_ += Graphics2D.width;
		    }
		} else {
		    destOff -= loops;
		    loops -= i_87_;
		    i_87_ = lineOffsets[i_87_];
		    while (--loops >= 0) {
			drawScanLine(Graphics2D.pixels, i_87_,
				  color, 0, i_90_ >> 16, i_88_ >> 16);
			i_88_ += i_93_;
			i_90_ += i_94_;
			i_87_ += Graphics2D.width;
		    }
		    while (--destOff >= 0) {
			drawScanLine(Graphics2D.pixels, i_87_,
				  color, 0, i_90_ >> 16, i_89_ >> 16);
			i_89_ += i_92_;
			i_90_ += i_94_;
			i_87_ += Graphics2D.width;
		    }
		}
	    }
	}
    }
    
    private static final int method293(int i, int i_95_) {
	i_95_ = (127 - i_95_) * (i & 0x7f) >> 7;
	if (i_95_ < 2)
	    i_95_ = 2;
	else if (i_95_ > 126)
	    i_95_ = 126;
	return (i & 0xff80) + i_95_;
    }
    
    static final int modifyBrightness(int i, double d) {
		double d_96_ = (double) (i >> 16) / 256.0;
		double d_97_ = (double) (i >> 8 & 0xff) / 256.0;
		double d_98_ = (double) (i & 0xff) / 256.0;
		d_96_ = Math.pow(d_96_, d);
		d_97_ = Math.pow(d_97_, d);
		d_98_ = Math.pow(d_98_, d);
		int i_99_ = (int) (d_96_ * 256.0);
		int i_100_ = (int) (d_97_ * 256.0);
		int i_101_ = (int) (d_98_ * 256.0);
		return (i_99_ << 16) + (i_100_ << 8) + i_101_;
    }
    
    private static final void drawTexturedLine(int[] dest, int[] texture, int alpha, int a, int destOff, int startX, int endX_, int colorIndex, int gradient, int i_109_, int i_110_, int i_111_, int i_112_, int i_113_, int i_114_) {
    	if (startX < endX_) {
    		int loops;
		    int step;
		    if (edgeRestricted) {
		    	step = (gradient - colorIndex) / (endX_ - startX);
		    	if (endX_ > endX)
		    		endX_ = endX;
		    	if (startX < 0) {
		    		colorIndex -= startX * step;
		    		startX = 0;
		    	}
		    	if (startX >= endX_)
		    		return;
		    	loops = endX_ - startX >> 3;
				step <<= 12;
				colorIndex <<= 9;
		    } else {
		    	if (endX_ - startX > 7) {
		    		loops = endX_ - startX >> 3;
					step = (gradient - colorIndex) * gradientFactors[loops] >> 6;
		    	} else {
		    		loops = 0;
		    		step = 0;
		    	}
		    	colorIndex <<= 9;
		    }
		    destOff += startX;
		    if (lowMem) {
		    	int i_117_ = 0;
		    	int i_118_ = 0;
		    	int i_119_ = startX - rasterizeCenterX;
		    	i_109_ += (i_112_ >> 3) * i_119_;
		    	i_110_ += (i_113_ >> 3) * i_119_;
		    	i_111_ += (i_114_ >> 3) * i_119_;
		    	int i_120_ = i_111_ >> 12;
		    	if (i_120_ != 0) {
		    		alpha = i_109_ / i_120_;
		    		a = i_110_ / i_120_;
		    		if (alpha < 0)
		    			alpha = 0;
		    		else if (alpha > 4032)
		    			alpha = 4032;
		    	}
		    	i_109_ += i_112_;
		    	i_110_ += i_113_;
		    	i_111_ += i_114_;
		    	i_120_ = i_111_ >> 12;
		    	if (i_120_ != 0) {
		    		i_117_ = i_109_ / i_120_;
		    		i_118_ = i_110_ / i_120_;
		    		if (i_117_ < 7)
		    			i_117_ = 7;
		    		else if (i_117_ > 4032)
		    			i_117_ = 4032;
		    	}
		    	int i_121_ = i_117_ - alpha >> 3;
		    	int i_122_ = i_118_ - a >> 3;
		    	alpha += (colorIndex & 0x600000) >> 3;
		    	int i_123_ = colorIndex >> 23;
		    	if (transparent) {
		    		while (loops-- > 0) {
		    			dest[destOff++] = texture[(a & 0xfc0) + (alpha >> 6)] >>> i_123_;
		    			alpha += i_121_;
		    			a += i_122_;
		    			dest[destOff++] = texture[(a & 0xfc0) + (alpha >> 6)] >>> i_123_;
		    			alpha += i_121_;
		    			a += i_122_;
		    			dest[destOff++] = texture[(a & 0xfc0) + (alpha >> 6)] >>> i_123_;
		    			alpha += i_121_;
		    			a += i_122_;
		    			dest[destOff++] = texture[(a & 0xfc0) + (alpha >> 6)] >>> i_123_;
		    			alpha += i_121_;
		    			a += i_122_;
		    			dest[destOff++] = texture[(a & 0xfc0) + (alpha >> 6)] >>> i_123_;
		    			alpha += i_121_;
		    			a += i_122_;
		    			dest[destOff++] = texture[(a & 0xfc0) + (alpha >> 6)] >>> i_123_;
		    			alpha += i_121_;
		    			a += i_122_;
		    			dest[destOff++] = texture[(a & 0xfc0) + (alpha >> 6)] >>> i_123_;
		    			alpha += i_121_;
		    			a += i_122_;
		    			dest[destOff++] = texture[(a & 0xfc0) + (alpha >> 6)] >>> i_123_;
		    			alpha = i_117_;
		    			a = i_118_;
		    			i_109_ += i_112_;
		    			i_110_ += i_113_;
						i_111_ += i_114_;
						i_120_ = i_111_ >> 12;
						if (i_120_ != 0) {
							i_117_ = i_109_ / i_120_;
							i_118_ = i_110_ / i_120_;
							if (i_117_ < 7)
								i_117_ = 7;
							else if (i_117_ > 4032)
								i_117_ = 4032;
						}
						i_121_ = i_117_ - alpha >> 3;
						i_122_ = i_118_ - a >> 3;
						colorIndex += step;
						alpha += (colorIndex & 0x600000) >> 3;
						i_123_ = colorIndex >> 23;
		    		}
		    		loops = endX_ - startX & 0x7;
		    		while (loops-- > 0) {
		    			dest[destOff++] = texture[(a & 0xfc0) + (alpha >> 6)] >>> i_123_;
		    			alpha += i_121_;
		    			a += i_122_;
		    		}
		    	} else {
		    		while (loops-- > 0) {
		    			int i_124_;
		    			if ((i_124_ = texture[(a & 0xfc0) + (alpha >> 6)] >>> i_123_) != 0)
		    				dest[destOff] = i_124_;
		    			destOff++;
		    			alpha += i_121_;
		    			a += i_122_;
		    			if ((i_124_ = texture[(a & 0xfc0) + (alpha >> 6)] >>> i_123_) != 0)
		    				dest[destOff] = i_124_;
		    			destOff++;
		    			alpha += i_121_;
		    			a += i_122_;
		    			if ((i_124_ = texture[(a & 0xfc0) + (alpha >> 6)] >>> i_123_) != 0)
		    				dest[destOff] = i_124_;
		    			destOff++;
		    			alpha += i_121_;
		    			a += i_122_;
		    			if ((i_124_ = texture[(a & 0xfc0) + (alpha >> 6)] >>> i_123_) != 0)
		    				dest[destOff] = i_124_;
		    			destOff++;
		    			alpha += i_121_;
		    			a += i_122_;
		    			if ((i_124_ = texture[(a & 0xfc0) + (alpha >> 6)] >>> i_123_) != 0)
		    				dest[destOff] = i_124_;
		    			destOff++;
		    			alpha += i_121_;
		    			a += i_122_;
		    			if ((i_124_ = texture[(a & 0xfc0) + (alpha >> 6)] >>> i_123_) != 0)
		    				dest[destOff] = i_124_;
		    			destOff++;
		    			alpha += i_121_;
		    			a += i_122_;
		    			if ((i_124_ = texture[(a & 0xfc0) + (alpha >> 6)] >>> i_123_) != 0)
		    				dest[destOff] = i_124_;
		    			destOff++;
		    			alpha += i_121_;
		    			a += i_122_;
		    			if ((i_124_ = texture[(a & 0xfc0) + (alpha >> 6)] >>> i_123_) != 0)
		    				dest[destOff] = i_124_;
		    			destOff++;
		    			alpha = i_117_;
		    			a = i_118_;
		    			i_109_ += i_112_;
		    			i_110_ += i_113_;
		    			i_111_ += i_114_;
		    			i_120_ = i_111_ >> 12;
		    			if (i_120_ != 0) {
		    				i_117_ = i_109_ / i_120_;
		    				i_118_ = i_110_ / i_120_;
		    				if (i_117_ < 7)
		    					i_117_ = 7;
		    				else if (i_117_ > 4032)
		    					i_117_ = 4032;
		    			}
		    			i_121_ = i_117_ - alpha >> 3;
		    			i_122_ = i_118_ - a >> 3;
		    			colorIndex += step;
		    			alpha += (colorIndex & 0x600000) >> 3;
		    			i_123_ = colorIndex >> 23;
		    		}
		    		loops = endX_ - startX & 0x7;
		    		while (loops-- > 0) {
		    			int i_125_;
		    			if ((i_125_ = texture[(a & 0xfc0) + (alpha >> 6)] >>> i_123_) != 0)
					    dest[destOff] = i_125_;
		    			destOff++;
		    			alpha += i_121_;
		    			a += i_122_;
		    		}
		    	}
		    } else {
		    	int i_126_ = 0;
		    	int i_127_ = 0;
		    	int i_128_ = startX - rasterizeCenterX;
		    	i_109_ += (i_112_ >> 3) * i_128_;
		    	i_110_ += (i_113_ >> 3) * i_128_;
		    	i_111_ += (i_114_ >> 3) * i_128_;
		    	int i_129_ = i_111_ >> 14;
		    	if (i_129_ != 0) {
		    		alpha = i_109_ / i_129_;
		    		a = i_110_ / i_129_;
		    		if (alpha < 0)
		    			alpha = 0;
		    		else if (alpha > 16256)
		    			alpha = 16256;
		    	}
		    	i_109_ += i_112_;
		    	i_110_ += i_113_;
		    	i_111_ += i_114_;
		    	i_129_ = i_111_ >> 14;
		    	if (i_129_ != 0) {
		    		i_126_ = i_109_ / i_129_;
		    		i_127_ = i_110_ / i_129_;
		    		if (i_126_ < 7)
		    			i_126_ = 7;
		    		else if (i_126_ > 16256)
		    			i_126_ = 16256;
		    	}
		    	int i_130_ = i_126_ - alpha >> 3;
		    	int i_131_ = i_127_ - a >> 3;
		    	alpha += colorIndex & 0x600000;
		    	int i_132_ = colorIndex >> 23;
		    	if (transparent) {
		    		while (loops-- > 0) {
		    			dest[destOff++] = texture[(a & 0x3f80) + (alpha >> 7)] >>> i_132_;
		    			alpha += i_130_;
						a += i_131_;
						dest[destOff++] = texture[(a & 0x3f80) + (alpha >> 7)] >>> i_132_;
						alpha += i_130_;
						a += i_131_;
						dest[destOff++] = texture[(a & 0x3f80) + (alpha >> 7)] >>> i_132_;
						alpha += i_130_;
						a += i_131_;
						dest[destOff++] = texture[(a & 0x3f80) + (alpha >> 7)] >>> i_132_;
						alpha += i_130_;
						a += i_131_;
						dest[destOff++] = texture[(a & 0x3f80) + (alpha >> 7)] >>> i_132_;
						alpha += i_130_;
						a += i_131_;
						dest[destOff++] = texture[(a & 0x3f80) + (alpha >> 7)] >>> i_132_;
						alpha += i_130_;
						a += i_131_;
						dest[destOff++] = texture[(a & 0x3f80) + (alpha >> 7)] >>> i_132_;
						alpha += i_130_;
						a += i_131_;
						dest[destOff++] = texture[(a & 0x3f80) + (alpha >> 7)] >>> i_132_;
						alpha = i_126_;
						a = i_127_;
						i_109_ += i_112_;
						i_110_ += i_113_;
						i_111_ += i_114_;
						i_129_ = i_111_ >> 14;
						if (i_129_ != 0) {
							i_126_ = i_109_ / i_129_;
							i_127_ = i_110_ / i_129_;
							if (i_126_ < 7)
								i_126_ = 7;
							else if (i_126_ > 16256)
								i_126_ = 16256;
						}
						i_130_ = i_126_ - alpha >> 3;
						i_131_ = i_127_ - a >> 3;
						colorIndex += step;
						alpha += colorIndex & 0x600000;
						i_132_ = colorIndex >> 23;
		    		}
		    		loops = endX_ - startX & 0x7;
		    		while (loops-- > 0) {
		    			dest[destOff++] = texture[(a & 0x3f80) + (alpha >> 7)] >>> i_132_;
		    			alpha += i_130_;
		    			a += i_131_;
		    		}
		    	} else {
		    		while (loops-- > 0) {
		    			int i_133_;
		    			if ((i_133_ = (texture[(a & 0x3f80) + (alpha >> 7)] >>> i_132_)) != 0)
		    				dest[destOff] = i_133_;
		    			destOff++;
		    			alpha += i_130_;
		    			a += i_131_;
		    			if ((i_133_ = (texture[(a & 0x3f80) + (alpha >> 7)] >>> i_132_)) != 0)
		    				dest[destOff] = i_133_;
		    			destOff++;
		    			alpha += i_130_;
		    			a += i_131_;
		    			if ((i_133_ = (texture[(a & 0x3f80) + (alpha >> 7)] >>> i_132_)) != 0)
		    				dest[destOff] = i_133_;
		    			destOff++;
		    			alpha += i_130_;
		    			a += i_131_;
		    			if ((i_133_ = (texture[(a & 0x3f80) + (alpha >> 7)] >>> i_132_)) != 0)
		    				dest[destOff] = i_133_;
		    			destOff++;
		    			alpha += i_130_;
		    			a += i_131_;
		    			if ((i_133_ = (texture[(a & 0x3f80) + (alpha >> 7)] >>> i_132_)) != 0)
		    				dest[destOff] = i_133_;
		    			destOff++;
		    			alpha += i_130_;
		    			a += i_131_;
		    			if ((i_133_ = (texture[(a & 0x3f80) + (alpha >> 7)] >>> i_132_)) != 0)
		    				dest[destOff] = i_133_;
		    			destOff++;
		    			alpha += i_130_;
		    			a += i_131_;
		    			if ((i_133_ = (texture[(a & 0x3f80) + (alpha >> 7)] >>> i_132_)) != 0)
		    				dest[destOff] = i_133_;
		    			destOff++;
		    			alpha += i_130_;
		    			a += i_131_;
		    			if ((i_133_ = (texture[(a & 0x3f80) + (alpha >> 7)] >>> i_132_)) != 0)
		    				dest[destOff] = i_133_;
		    			destOff++;
		    			alpha = i_126_;
		    			a = i_127_;
		    			i_109_ += i_112_;
		    			i_110_ += i_113_;
		    			i_111_ += i_114_;
		    			i_129_ = i_111_ >> 14;
		    			if (i_129_ != 0) {
		    				i_126_ = i_109_ / i_129_;
		    				i_127_ = i_110_ / i_129_;
		    				if (i_126_ < 7)
		    					i_126_ = 7;
		    				else if (i_126_ > 16256)
		    					i_126_ = 16256;
		    			}
		    			i_130_ = i_126_ - alpha >> 3;
		    			i_131_ = i_127_ - a >> 3;
		    			colorIndex += step;
		    			alpha += colorIndex & 0x600000;
		    			i_132_ = colorIndex >> 23;
		    		}
		    		loops = endX_ - startX & 0x7;
		    		while (loops-- > 0) {
		    			int i_134_;
		    			if ((i_134_ = (texture[(a & 0x3f80) + (alpha >> 7)] >>> i_132_)) != 0)
		    				dest[destOff] = i_134_;
		    			destOff++;
		    			alpha += i_130_;
		    			a += i_131_;
		    		}
		    	}
		    }
    	}
    }
    
    static final void method296() {
		rasterizeCenterX = endX / 2;
		rasterizeCenterY = endY / 2;
		anInt2118 = -rasterizeCenterX;
		renderCenterX = endX - rasterizeCenterX;
		anInt2109 = -rasterizeCenterY;
		renderCenterY = endY - rasterizeCenterY;
    }
    
    private static final void drawShadedLine(int[] dest, int destOff, int color,
					int loops, int startX, int endX_,
					int colorIndex, int gradient) {
    	try {
			if (colorRestricted) {
				int step;
				if (edgeRestricted) {
					if (endX_ - startX > 3)
						step = (gradient - colorIndex) / (endX_ - startX);
					else
						step = 0;
					if (endX_ > endX)
						endX_ = endX;
					if (startX < 0) {
						colorIndex -= startX * step;
						startX = 0;
					}
					if (startX >= endX_)
						return;
					destOff += startX;
					loops = endX_ - startX >> 2;
					step <<= 2;
				} else {
					if (startX >= endX_)
						return;
					destOff += startX;
					loops = endX_ - startX >> 2;
					if (loops > 0)
						step = (gradient - colorIndex) * gradientFactors[loops] >> 15;
					else
						step = 0;
				}
				if (alpha == 0) {
					while (--loops >= 0) {
						color = palette[colorIndex >> 8];
						colorIndex += step;
						dest[destOff++] = color;
						dest[destOff++] = color;
						dest[destOff++] = color;
						dest[destOff++] = color;
					}
					loops = endX_ - startX & 0x3;
					if (loops > 0) {
						color = palette[colorIndex >> 8];
						do
							dest[destOff++] = color;
						while (--loops > 0);
					}
				} else {
					int destAlpha = alpha;
					int srcAlpha = 256 - alpha;
					while (--loops >= 0) {
						color = palette[colorIndex >> 8];
						colorIndex += step;
						color = (((color & 0xff00ff) * srcAlpha >> 8 & 0xff00ff) + ((color & 0xff00) * srcAlpha >> 8 & 0xff00));
						dest[destOff++] = (color + ((dest[destOff] & 0xff00ff) * destAlpha >> 8 & 0xff00ff) + ((dest[destOff] & 0xff00) * destAlpha >> 8 & 0xff00));
						dest[destOff++] = (color + ((dest[destOff] & 0xff00ff) * destAlpha >> 8 & 0xff00ff) + ((dest[destOff] & 0xff00) * destAlpha >> 8 & 0xff00));
						dest[destOff++] = (color + ((dest[destOff] & 0xff00ff) * destAlpha >> 8 & 0xff00ff) + ((dest[destOff] & 0xff00) * destAlpha >> 8 & 0xff00));
						dest[destOff++] = (color + ((dest[destOff] & 0xff00ff) * destAlpha >> 8 & 0xff00ff) + ((dest[destOff] & 0xff00) * destAlpha >> 8 & 0xff00));
					}
					loops = endX_ - startX & 0x3;
					if (loops > 0) {
						color = palette[colorIndex >> 8];
						color = (((color & 0xff00ff) * srcAlpha >> 8 & 0xff00ff) + ((color & 0xff00) * srcAlpha >> 8 & 0xff00));
						do
							dest[destOff++] = (color + ((dest[destOff] & 0xff00ff) * destAlpha >> 8 & 0xff00ff) + ((dest[destOff] & 0xff00) * destAlpha >> 8 & 0xff00));
						while (--loops > 0);
					}
				}
			} else if (startX < endX_) {
				int step = (gradient - colorIndex) / (endX_ - startX);
				if (edgeRestricted) {
					if (endX_ > endX)
						endX_ = endX;
					if (startX < 0) {
						colorIndex -= startX * step;
						startX = 0;
					}
					if (startX >= endX_)
						return;
				}
				destOff += startX;
				loops = endX_ - startX;
				if (alpha == 0) {
					do {
						dest[destOff++] = palette[colorIndex >> 8];
						colorIndex += step;
					} while (--loops > 0);
				} else {
					int destAlpha = alpha;
					int srcAlpha = 256 - alpha;
					do {
						color = palette[colorIndex >> 8];
						colorIndex += step;
						color = (((color & 0xff00ff) * srcAlpha >> 8 & 0xff00ff) + ((color & 0xff00) * srcAlpha >> 8 & 0xff00));
						dest[destOff++] = (color + ((dest[destOff] & 0xff00ff) * destAlpha >> 8 & 0xff00ff) + ((dest[destOff] & 0xff00) * destAlpha >> 8 & 0xff00));
					} while (--loops > 0);
				}
			}
		} catch (Exception e) {
			System.out.println("dont try that again");
		}
    }
    
    static final void setTexture(TextureInterface interface3) {
    	textureInterface = interface3;
    }
    
    public static void reset() {
    	lineOffsets = null;
    	palette = null;
    	textureInterface = null;
    	gradientFactors = null;
    	anIntArray2106 = null;
    	sineTable = null;
    	cosineTable = null;
    }
}
