package org.mapeditor.client;

/* Class3_Sub3_Sub12_Sub4 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.util.Random;

final class RSFont extends Graphics2D {
	
    private int[] charYOffset;
    private static RSString[] messages;
    private static RSString whi;
    int trimHeight = 0;
    private Random random;
    private static RSString yel;
    private static RSString gr2;
    private static RSString or3;
    private static RSString bla;
    private static RSString cya;
    private static RSString lre;
    private static RSString blu;
    private static RSString dbl;
    private static RSString dre;
    private static RSString or1;
    private static RSString str;
    private static RSString gre;
    private static RSString red;
    private static RSString gr1;
    private static RSString gr3;
    private byte[][] charPixels = new byte[256][];
    private static RSString or2;
    private int[] charWidths;
    private static RSString mag;
    private int[] charHeights;
    private int anInt2150;
    private boolean strikethrough;
    private int anInt2152;
    
    static {
    	bla = RSString.createRSString("bla");
    	yel = RSString.createRSString("yel");
		whi = RSString.createRSString("whi");
		messages = new RSString[100];
		dbl = RSString.createRSString("dbl");
		gr1 = RSString.createRSString("gr1");
		cya = RSString.createRSString("cya");
		or3 = RSString.createRSString("or3");
		gre = RSString.createRSString("gre");
		lre = RSString.createRSString("lre");
		or1 = RSString.createRSString("or1");
		or2 = RSString.createRSString("or2");
		blu = RSString.createRSString("blu");
		str = RSString.createRSString("str");
		gr3 = RSString.createRSString("gr3");
		mag = RSString.createRSString("mag");
		dre = RSString.createRSString("dre");
		gr2 = RSString.createRSString("gr2");
		red = RSString.createRSString("red");
    }
    
    private final void method300(RSString text, int i, int i_0_, int i_1_, boolean bool, int i_2_) {
		text = text.trim();
		int i_3_ = 0;
		for (int i_4_ = 0; i_4_ < text.length(); i_4_++) {
		    if (text.charAt(i_4_) == 32)
			i_3_++;
		}
		int i_5_ = 0;
		int i_6_ = 0;
		if (i_3_ > 0)
		    i_5_ = (i_2_ - getStringWidthColors(text)) * 256 / i_3_;
		strikethrough = false;
		int i_7_ = i;
		i_0_ -= trimHeight;
		for (int i_8_ = 0; i_8_ < text.length; i_8_++) {
		    if (text.buffer[i_8_] == 64
			&& i_8_ + 4 < text.length
			&& text.buffer[i_8_ + 4] == 64) {
			int i_9_
			    = getTextEffects(text.substring(i_8_ + 1, i_8_ + 4));
			if (i_9_ != -1)
			    i_1_ = i_9_;
			i_8_ += 4;
		    } else {
			int i_10_ = text.buffer[i_8_] & 0xff;
			if (i_10_ != 32) {
			    if (bool)
				drawChar(charPixels[i_10_], i + 1,
					  i_0_ + charYOffset[i_10_] + 1,
					  charWidths[i_10_], charHeights[i_10_],
					  0);
			    drawChar(charPixels[i_10_], i,
				      i_0_ + charYOffset[i_10_],
				      charWidths[i_10_], charHeights[i_10_],
				      i_1_);
			}
			i += charWidths[i_10_];
			if (i_10_ == 32) {
			    i_6_ += i_5_;
			    i += i_6_ / 256;
			    i_6_ &= 0xff;
			}
		    }
		}
		if (strikethrough)
		    Graphics2D.drawHorizontalLine
			(i_7_, i_0_ + (int) ((double) (trimHeight)
					     * 0.7), i - i_7_, 8388608);
    }
    
    final void drawWaveText2(RSString string, int x, int y, int color, int cycle) {
    	if (string != null) {
    		x -= getStringWidth(string) / 2;
    		y -= trimHeight;
    		for (int id = 0; id < string.length(); id++) {
    			int c = string.buffer[id] & 0xff;
    			if (c != 32)
    				drawChar(charPixels[c], x + (int) (Math.sin((double) id / 5.0 + (double) cycle / 5.0) * 5.0),
    						(y + charYOffset[c] + (int) (Math.sin((double) id / 3.0 + (double) cycle / 5.0) * 5.0)),
    						charWidths[c], charHeights[c], color);
    			x += charWidths[c];
    		}
    	}
    }
    
    public static void reset() {
		red = null;
		gre = null;
		blu = null;
		yel = null;
		cya = null;
		mag = null;
		whi = null;
		bla = null;
		lre = null;
		dre = null;
		dbl = null;
		or1 = null;
		or2 = null;
		or3 = null;
		gr1 = null;
		gr2 = null;
		gr3 = null;
		str = null;
		messages = null;
    }
    
    private final void pushPixels(int[] dest, byte[] src, int color, int srcOff,
				 int destOff, int width, int height, int destStep,
				 int srcStep) {
		int quarterX = -(width >> 2);
		width = -(width & 0x3);
		for (int offY = -height; offY < 0; offY++) {
			for (int offX = quarterX; offX < 0; offX++) {
				if (src[srcOff++] != 0)
					dest[destOff++] = color;
				else
					destOff++;
				if (src[srcOff++] != 0)
					dest[destOff++] = color;
				else
					destOff++;
				if (src[srcOff++] != 0)
					dest[destOff++] = color;
				else
					destOff++;
				if (src[srcOff++] != 0)
					dest[destOff++] = color;
				else
					destOff++;
			}
			for (int offX = width; offX < 0; offX++) {
				if (src[srcOff++] != 0)
					dest[destOff++] = color;
				else
					destOff++;
		    }
		    destOff += destStep;
		    srcOff += srcStep;
		}
    }
    
    final void drawShakeText(RSString text, int w, int h, int i_28_,
			 int i_29_, int startHeight) {
		if (text != null) {
			double bounceHeight = 7.0 - (double) startHeight / 8.0;
			if (bounceHeight < 0.0)
				bounceHeight = 0.0;
			w -= getStringWidth(text) / 2;
			h -= trimHeight;
			for (int len = 0; len < text.length(); len++) {
				int c = text.buffer[len] & 0xff;
				if (c != 32)
					drawChar(charPixels[c], w,
							(h + charYOffset[c]
							                        + (int) (Math.sin((double) len / 1.5
							                        		+ (double) i_29_)
							                        		* bounceHeight)),
							                        		charWidths[c], charHeights[c],
							                        		i_28_);
				w += charWidths[c];
			}
		}
    }
    
    final int getStringWidthColors(RSString string) {
    	if (string == null)
    		return 0;
    	int width = 0;
    	for (int c = 0; c < string.length; c++) {
    		if (string.buffer[c] == 64 && c + 4 < string.length && string.buffer[c + 4] == 64)
    			c += 4;
	 	   else
	 		   width += charWidths[string.buffer[c] & 0xff];
    	}
    	return width;
    }
    
    private final void method306(byte[] buffer, int x, int y, int w,
				 int h, int color, int seed) {
		int destOff = x + y * Graphics2D.width;
		int destStep = Graphics2D.width - w;
		int srcStep = 0;
		int srcOff = 0;
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
		    method317(Graphics2D.pixels, buffer, color, srcOff,
			      destOff, w, h, destStep, srcStep, seed);
    }
    
    final void drawCenteredColoredShadedString(RSString class63, int i, int i_46_, int i_47_, boolean bool) {
    	drawString(class63, i - getStringWidthColors(class63) / 2, i_46_, i_47_, bool);
    }
    
    final void drawCenteredString(RSString class63, int i, int i_48_, int i_49_) {
    	drawString(class63, i - getStringWidth(class63) / 2, i_48_, i_49_);
    }
    
    private final void drawChar(byte[] buffer, int x, int y, int w,
				 int h, int color) {
		int destOff = x + y * Graphics2D.width;
		int destStep = Graphics2D.width - w;
		int srcStep = 0;
		int srcOff = 0;
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
		    pushPixels(Graphics2D.pixels, buffer, color, srcOff,
			      destOff, w, h, destStep, srcStep);
    }
    
    final void drawInterfaceText(RSString text, int x, int y, int width,
			 int height, int color, boolean shaded, int alignmentType,
			 int i_66_, int i_67_) {
		if (text != null) {
			int i_68_ = 0;
			int i_69_ = 0;
		    RSString class63_70_ = RSString.method848(100);
		    int i_71_ = -1;
		    int i_72_ = 0;
		    RSString class63_73_ = null;
		    if (i_67_ == 0)
		    	i_67_ = anInt2152;
		    boolean bool_74_ = true;
		    if (height < anInt2152 + anInt2150 + i_67_ && height < i_67_ + i_67_)
		    	bool_74_ = false;
		    int i_75_ = 0;
		    int i_76_ = text.length();
		    for (int i_77_ = 0; i_77_ < i_76_; i_77_++) {
		    	int i_78_ = text.charAt(i_77_);
		    	if (i_78_ == 64 && i_77_ + 4 < i_76_ && text.charAt(i_77_ + 4) == 64) {
		    		class63_73_ = text.substring(i_77_, i_77_ + 5);
		    		class63_70_.method918(class63_73_);
		    		i_77_ += 4;
		    	} else if (i_78_ == 92 && i_77_ + 1 < i_76_ && text.charAt(i_77_ + 1) == 110) {
		    		class63_73_ = null;
		    		messages[i_75_++] = class63_70_.substring(i_69_, class63_70_.length()).trim();
		    		i_69_ = class63_70_.length();
		    		i_68_ = 0;
		    		i_71_ = -1;
		    		i_77_++;
		    	} else {
		    		class63_70_.method923(i_78_);
		    		i_68_ += getStringWidth(i_78_);
		    		if (i_78_ == 32 || i_78_ == 45) {
		    			i_71_ = class63_70_.length();
		    			i_72_ = i_68_;
		    		}
		    		if (bool_74_ && i_68_ > width && i_71_ >= 0) {
		    			messages[i_75_++] = class63_70_.substring(i_69_, i_71_).trim();
		    			i_69_ = i_71_;
		    			i_71_ = -1;
		    			i_68_ -= i_72_;
		    			if (class63_73_ != null && i_69_ > 4) {
		    				i_69_ -= 5;
		    				class63_70_.method914(class63_73_, i_69_);
		    			}
		    		}
		    	}
		    }
		    if (class63_70_.length() > i_69_)
		    	messages[i_75_++] = class63_70_.substring(i_69_, class63_70_.length()).trim();
		    if (i_66_ == 3 && i_75_ == 1)
		    	i_66_ = 1;
		    int i_79_;
		    if (i_66_ == 0)
		    	i_79_ = y + anInt2152;
		    else if (i_66_ == 1)
		    	i_79_ = y + anInt2152 + (height - anInt2152 - anInt2150 - (i_75_ - 1) * i_67_) / 2;
		    else if (i_66_ == 2)
		    	i_79_ = y + height - anInt2150 - (i_75_ - 1) * i_67_;
		    else {
		    	int i_80_ = ((height - anInt2152 - anInt2150 - (i_75_ - 1) * i_67_) / (i_75_ + 1));
		    	if (i_80_ < 0)
		    		i_80_ = 0;
		    	i_79_ = y + anInt2152 + i_80_;
		    	i_67_ += i_80_;
		    }
		    for (int i_81_ = 0; i_81_ < i_75_; i_81_++) {
		    	if (alignmentType == 0)//text----
		    		drawString(messages[i_81_], x, i_79_, color, shaded);
		    	else if (alignmentType == 1)//--text--
		    		drawCenteredColoredShadedString(messages[i_81_], x + width / 2, i_79_, color, shaded);
		    	else if (alignmentType == 2)//----text
		    		method319(messages[i_81_], x + width, i_79_, color, shaded);
		    	else if (i_81_ == i_75_ - 1)
		    		drawString(messages[i_81_], x, i_79_, color, shaded);
		    	else
		    		method300(messages[i_81_], x, i_79_, color, shaded, width);
		    	i_79_ += i_67_;
		    }
		}
    }
    
    private final int getTextEffects(RSString effect) {
		if (effect.equals(red))
		    return 16711680;
		if (effect.equals(gre))
		    return 65280;
		if (effect.equals(blu))
		    return 255;
		if (effect.equals(yel))
		    return 16776960;
		if (effect.equals(cya))
		    return 65535;
		if (effect.equals(mag))
		    return 16711935;
		if (effect.equals(whi))
		    return 16777215;
		if (effect.equals(bla))
		    return 0;
		if (effect.equals(lre))
		    return 16748608;
		if (effect.equals(dre))
		    return 8388608;
		if (effect.equals(dbl))
		    return 128;
		if (effect.equals(or1))
		    return 16756736;
		if (effect.equals(or2))
		    return 16740352;
		if (effect.equals(or3))
		    return 16723968;
		if (effect.equals(gr1))
		    return 12648192;
		if (effect.equals(gr2))
		    return 8453888;
		if (effect.equals(gr3))
		    return 4259584;
		if (effect.equals(str))
		    strikethrough = true;
		return -1;
    }
    
    final void drawString(RSString text, int x, int y, int color) {
		if (text != null) {
			y -= trimHeight;
			for (int len = 0; len < text.length; len++) {
				int c = text.buffer[len] & 0xff;
				if (c != 32)
					drawChar(charPixels[c], x,
							y + charYOffset[c],
							charWidths[c], charHeights[c], color);
				x += charWidths[c];
		    }
		}
    }
    
    final int getStringWidth(RSString string) {
    	if (string == null)
    		return 0;
    	int w = 0;
    	for (int id = 0; id < string.length; id++)
    		w += charWidths[string.buffer[id] & 0xff];
    	return w;
    }
    
    final void drawWaveText(RSString class63, int i, int i_87_, int i_88_, int i_89_) {
    	if (class63 != null) {
    		i -= getStringWidth(class63) / 2;
    		i_87_ -= trimHeight;
    		for (int i_90_ = 0; i_90_ < class63.length; i_90_++) {
    			int i_91_ = class63.buffer[i_90_] & 0xff;
    			if (i_91_ != 32)
    				drawChar(charPixels[i_91_], i,(i_87_ + charYOffset[i_91_]
    				                                                                + (int) (Math.sin((double) i_90_ / 2.0
    				                                                                		+ (double) i_89_ / 5.0)
    				                                                                		* 5.0)),
    				                                                                		charWidths[i_91_], charHeights[i_91_],
    				                                                                		i_88_);
    			i += charWidths[i_91_];
    		}
    	}
    }
    
    final void drawBasicString(RSString string, int x, int y, int color) {
    	drawString(string, x - getStringWidth(string), y, color);
    }
    
    final int getStringWidth(int i) {
    	return charWidths[i & 0xff];
    }
    
    private final void method317(int[] pixels, byte[] buffer, int color, int srcOff,
				 int destOff, int width, int height, int destStep,
				 int srcStep, int seed) {
    	color = ((color & 0xff00ff) * seed & ~0xff00ff) + ((color & 0xff00) * seed & 0xff0000) >> 8;
    	seed = 256 - seed;
    	for (int i_102_ = -height; i_102_ < 0; i_102_++) {
    		for (int i_103_ = -width; i_103_ < 0; i_103_++) {
    			if (buffer[srcOff++] != 0) {
    				int i_104_ = pixels[destOff];
    				pixels[destOff++] = ((((i_104_ & 0xff00ff) * seed & ~0xff00ff) + ((i_104_ & 0xff00) * seed & 0xff0000)) >> 8) + color;
    			} else
    				destOff++;
    		}
    		destOff += destStep;
    		srcOff += srcStep;
    	}
    }
    
    final void drawString(RSString text, int x, int y, int color,
			 boolean shaded) {
    	strikethrough = false;
    	int lineX = x;
    	if (text != null) {
    		y -= trimHeight;
    		for (int id = 0; id < text.length; id++) {
    			if (text.buffer[id] == 64 && id + 4 < text.length && text.buffer[id + 4] == 64) {
    				int colorEffect = getTextEffects(text.substring(id + 1, id + 4));
    				if (colorEffect != -1)
    					color = colorEffect;
    				id += 4;
    			} else {
    				int c = text.buffer[id] & 0xff;
    				if (c != 32) {
    					if (shaded)
    						drawChar(charPixels[c], x + 1, y + charYOffset[c] + 1, charWidths[c], charHeights[c], 0);
    					drawChar(charPixels[c], x, y + charYOffset[c], charWidths[c], charHeights[c], color);
    				}
    				x += charWidths[c];
    			}
    		}
    		if (strikethrough)
    			Graphics2D.drawHorizontalLine(lineX, y + (int) ((double) trimHeight * 0.7), x - lineX, 8388608);
    	}
    }
    
    final void method320(RSString string, int x, int y, int color,
    		boolean shaded, int seed) {//tool tips uses(used) this
    	if (string != null) {
    		random.setSeed((long) seed);
    		int nextInt = (random.nextInt() & 0x1f) + 192;
    		y -= trimHeight;
    		for (int id = 0; id < string.length; id++) {
    			if (string.buffer[id] == 64 && id + 4 < string.length && string.buffer[id + 4] == 64) {
    				int colorEffect = getTextEffects(string.substring(id + 1, id + 4));
    				if (colorEffect != -1)
    					color = colorEffect;
    				id += 4;
    			} else {
    				int c = string.buffer[id] & 0xff;
    				if (c != 32) {
    					if (shaded)
    						method306(charPixels[c], x + 1, y + charYOffset[c] + 1, charWidths[c], charHeights[c], 0, 192);
    					method306(charPixels[c], x, y + charYOffset[c], charWidths[c], charHeights[c], color, nextInt);
    				}
    				x += charWidths[c];
    				if ((random.nextInt() & 0x3) == 0)
    					x++;
    			}
    		}
    	}
    }
    
    final void method319(RSString class63, int i, int i_111_, int i_112_, boolean bool) {
    	drawString(class63, i - getStringWidthColors(class63), i_111_, i_112_, bool);
    }
    
    RSFont(int[] yOffsets, int[] widths, int[] heights,
			   int[] pallete, byte[][] pixels) {
		random = new Random();
		strikethrough = false;
		charYOffset = yOffsets;
		charWidths = widths;
		charHeights = heights;
		byte i = 0;
		for (int i_124_ = 1; i_124_ < pallete.length; i_124_++) {
		    if (pallete[i_124_] == 1)
		    	i = (byte) i_124_;
		}
		charPixels = pixels;
		int i_125_ = 2147483647;
		int i_126_ = -2147483648;
		for (int i_127_ = 0; i_127_ < 256; i_127_++) {
			if (charYOffset[i_127_] < i_125_)
				i_125_ = charYOffset[i_127_];
			if (charYOffset[i_127_] + charHeights[i_127_] > i_126_)
				i_126_ = charYOffset[i_127_] + charHeights[i_127_];
			byte[] is_128_ = charPixels[i_127_];
			int i_129_ = is_128_.length;
		    for (int i_130_ = 0; i_130_ < i_129_; i_130_++)
		    	is_128_[i_130_] = is_128_[i_130_] == i ? (byte) 0 : (byte) 1;
		}
		trimHeight = charYOffset[32] + charHeights[32];
		anInt2152 = trimHeight - i_125_;
		anInt2150 = i_126_ - trimHeight;
    }
}
