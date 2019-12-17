package org.mapeditor.client;

/* Class3_Sub3_Sub3_Sub3 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class Model extends SceneModel
{
    public int[] face_render_priorities;
    int[] face_a;
    int anInt1960;
    public int texTriangleCount = 0;
    public int anInt1962;
    public int[] triangleColorsA;
    public int[] textured_face_c;
    public int[] triangleColorsB;
    public int[] triangleSkinValues;
    int[] verticesXCoordinate;
    int[] face_c;
    int[] face_b;
    Vertex[] aClass27Array1970;
    int[] verticesZCoordinate;
    public int[] face_color;
    public int[] vertexVSkins;
    int numberOfTriangleFaces = 0;
    public int[] face_alpha;
    public int[] triangleColorsC;
    public int[] textured_face_b;
    int numberOfVerticeCoordinates;
    boolean aBoolean1979;
    public int[][] triangleSkins;
    int anInt1981;
    int[] verticesYCoordinate;
    public int anInt1983 = 0;
    Vertex[] vertices;
    int[] face_render_type;
    public int[] textured_face_a;
    public int anInt1987;
    int anInt1988;
    public int[][] vertexSkins;
    public static Model aClass3_Sub3_Sub3_Sub3_1990
	= new Model();
    int diagonalLine;
    public static Model instance;
    public static int[] anIntArray1993 = new int[1];
    int anInt1994;
    public static int[] anIntArray1995;
    public static int[] anIntArray1996;
    public static boolean[] aBooleanArray1997;
    static int anInt1998;
    public static int vertexModY;
    public static int[] anIntArray2000;
    public static int[] anIntArray2001;
    public static int[] anIntArray2002;
    static int[] anIntArray2003;
    public static int[] anIntArray2004;
    public static boolean[] edgeRestricted;
    public static int[] anIntArray2006;
    public static int[] anIntArray2007;
    public static int[] anIntArray2008;
    public static int[] pallete;
    public static int[][] anIntArrayArray2010;
    static int[] sine;
    static boolean aBoolean2012;
    static int[] cosine;
    public static int[] anIntArray2014;
    static int anInt2015;
    public static int vertexModZ;
    public static int[] anIntArray2017;
    public static int[] anIntArray2018;
    static int anInt2019;
    public static int[] faceLists;
    public static int[] anIntArray2021;
    static int[] anIntArray2022;
    public static int[] anIntArray2023;
    public static int vertexModX;
    public static int[] anIntArray2025;
    public static int[][] anIntArrayArray2026;
    
    static {
	instance = new Model();
	anIntArray2000 = new int[10];
	anIntArray2003 = new int[1000];
	anIntArray2004 = Rasterizer.anIntArray2106;
	edgeRestricted = new boolean[4096];
	anIntArray2001 = new int[10];
	aBooleanArray1997 = new boolean[4096];
	anIntArray2002 = new int[12];
	anIntArray2006 = new int[4096];
	pallete = Rasterizer.palette;
	anIntArray1995 = new int[10];
	anIntArray2008 = new int[1500];
	anIntArray2014 = new int[4096];
	cosine = Rasterizer.cosineTable;
	anIntArray2007 = new int[128];
	aBoolean2012 = false;
	anInt1998 = 0;
	anIntArray2018 = new int[4096];
	anIntArray2021 = new int[2000];
	anIntArray2017 = new int[4096];
	faceLists = new int[12];
	sine = Rasterizer.sineTable;
	anIntArray1996 = new int[4096];
	anIntArrayArray2010 = new int[1500][512];
	anIntArray2023 = new int[1];
	anIntArray2025 = new int[2000];
	anInt2019 = 0;
	anInt2015 = 0;
	anIntArrayArray2026 = new int[12][2000];
	anIntArray2022 = new int[4096];
	int i = 0;
	int i_0_ = 248;
	while (i < 9)
	    anIntArray2007[i++] = 255;
	while (i < 16) {
	    anIntArray2007[i++] = i_0_;
	    i_0_ -= 8;
	}
	while (i < 32) {
	    anIntArray2007[i++] = i_0_;
	    i_0_ -= 4;
	}
	while (i < 64) {
	    anIntArray2007[i++] = i_0_;
	    i_0_ -= 2;
	}
	while (i < 128)
	    anIntArray2007[i++] = i_0_--;
    }
    
    final void skin() {
    	if (vertexVSkins != null) {
    		int[] is = new int[256];
    		int i = 0;
    		for (int i_1_ = 0; i_1_ < numberOfVerticeCoordinates; i_1_++) {
    			int i_2_ = vertexVSkins[i_1_];
    			is[i_2_]++;
    			if (i_2_ > i)
    				i = i_2_;
    		}
    		vertexSkins = new int[i + 1][];
    		for (int i_3_ = 0; i_3_ <= i; i_3_++) {
    			vertexSkins[i_3_] = new int[is[i_3_]];
    			is[i_3_] = 0;
    		}
    		for (int i_4_ = 0; i_4_ < numberOfVerticeCoordinates; i_4_++) {
    			int i_5_ = vertexVSkins[i_4_];
    			vertexSkins[i_5_][is[i_5_]++] = i_4_;
    		}
    		vertexVSkins = null;
    	}
    	if (triangleSkinValues != null) {
		    int[] is = new int[256];
		    int i = 0;
		    for (int i_6_ = 0; i_6_ < numberOfTriangleFaces; i_6_++) {
		    	int i_7_ = triangleSkinValues[i_6_];
		    	is[i_7_]++;
		    	if (i_7_ > i)
		    		i = i_7_;
		    }
		    triangleSkins = new int[i + 1][];
		    for (int i_8_ = 0; i_8_ <= i; i_8_++) {
		    	triangleSkins[i_8_] = new int[is[i_8_]];
		    	is[i_8_] = 0;
		    }
		    for (int i_9_ = 0; i_9_ < numberOfTriangleFaces; i_9_++) {
		    	int i_10_ = triangleSkinValues[i_9_];
		    	triangleSkins[i_10_][is[i_10_]++] = i_9_;
		    }
		    triangleSkinValues = null;
    	}	
    }
    
    final void rotate270() {
    	vertices = null;
    	anInt1962 = 0;
    	for (int i = 0; i < numberOfVerticeCoordinates; i++) {
    		int tempZVertices = verticesZCoordinate[i];
    		verticesZCoordinate[i] = verticesXCoordinate[i];
    		verticesXCoordinate[i] = -tempZVertices;
    	}
    }
    
    final void rotate180() {
    	vertices = null;
    	anInt1962 = 0;
    	for (int i = 0; i < numberOfVerticeCoordinates; i++) {
    		verticesXCoordinate[i] = -verticesXCoordinate[i];
    		verticesZCoordinate[i] = -verticesZCoordinate[i];
    	}
    }
    
    final void rotate90() {
    	vertices = null;
    	anInt1962 = 0;
    	for (int i = 0; i < numberOfVerticeCoordinates; i++) {
    		int tempXVertices = verticesXCoordinate[i];
    		verticesXCoordinate[i] = verticesZCoordinate[i];
    		verticesZCoordinate[i] = -tempXVertices;
    	}
    }
    
    public final void method126(boolean bool, boolean bool_13_, int i) {
    	if (anInt1981 < 1500) {
    		for (int i_14_ = 0; i_14_ < anInt1981; i_14_++)
    			anIntArray2008[i_14_] = 0;
    		for (int i_15_ = 0; i_15_ < numberOfTriangleFaces; i_15_++) {
    			if (face_render_type == null || (face_render_type[i_15_] != -1)) {
    				int i_16_ = face_a[i_15_];
    				int i_17_ = face_b[i_15_];
    				int i_18_ = face_c[i_15_];
    				int i_19_ = anIntArray2018[i_16_];
    				int i_20_ = anIntArray2018[i_17_];
    				int i_21_ = anIntArray2018[i_18_];
    				if (bool && (i_19_ == -5000 || i_20_ == -5000 || i_21_ == -5000)) {
    					int i_22_ = anIntArray2006[i_16_];
    					int i_23_ = anIntArray2006[i_17_];
    					int i_24_ = anIntArray2006[i_18_];
    					int i_25_ = anIntArray2014[i_16_];
    					int i_26_ = anIntArray2014[i_17_];
    					int i_27_ = anIntArray2014[i_18_];
    					int i_28_ = anIntArray1996[i_16_];
    					int i_29_ = anIntArray1996[i_17_];
    					int i_30_ = anIntArray1996[i_18_];
    					i_22_ -= i_23_;
    					i_24_ -= i_23_;
    					i_25_ -= i_26_;
    					i_27_ -= i_26_;
    					i_28_ -= i_29_;
    					i_30_ -= i_29_;
    					int i_31_ = i_25_ * i_30_ - i_28_ * i_27_;
    					int i_32_ = i_28_ * i_24_ - i_22_ * i_30_;
    					int i_33_ = i_22_ * i_27_ - i_25_ * i_24_;
    					if (i_23_ * i_31_ + i_26_ * i_32_ + i_29_ * i_33_ > 0) {
    						aBooleanArray1997[i_15_] = true;
    						int i_34_ = ((anIntArray2022[i_16_] + anIntArray2022[i_17_]
    								+ anIntArray2022[i_18_]) / 3 + anInt1960);
    						anIntArrayArray2010[i_34_][anIntArray2008[i_34_]++] = i_15_;
    					}
    				} else {
    					if (bool_13_ && method140(anInt2019, anInt2015,
    							anIntArray2017[i_16_],
    							anIntArray2017[i_17_],
    							anIntArray2017[i_18_], i_19_,
    							i_20_, i_21_)) {
    						anIntArray2003[anInt1998++] = i;
    						bool_13_ = false;
    					}
    					if (((i_19_ - i_20_) * (anIntArray2017[i_18_]
    							- anIntArray2017[i_17_])
    							- ((anIntArray2017[i_16_] - anIntArray2017[i_17_])
    									* (i_21_ - i_20_))) > 0) {
    						aBooleanArray1997[i_15_] = false;
    						if (i_19_ < 0 || i_20_ < 0 || i_21_ < 0
    								|| i_19_ > Rasterizer.endX
    								|| i_20_ > Rasterizer.endX
    								|| i_21_ > Rasterizer.endX)
    							edgeRestricted[i_15_] = true;
    						else
    							edgeRestricted[i_15_] = false;
    						int i_35_
    						= ((anIntArray2022[i_16_]
    								+ anIntArray2022[i_17_]
    										+ anIntArray2022[i_18_]) / 3
    										+ anInt1960);
    						anIntArrayArray2010[i_35_][anIntArray2008[i_35_]++] = i_15_;
    					}
    				}
    			}
    		}
    		if (face_render_priorities == null) {
    			for (int i_36_ = anInt1981 - 1; i_36_ >= 0; i_36_--) {
    				int i_37_ = anIntArray2008[i_36_];
    				if (i_37_ > 0) {
    					int[] is = anIntArrayArray2010[i_36_];
    					for (int i_38_ = 0; i_38_ < i_37_; i_38_++)
    						rasterize(is[i_38_]);
    				}
    			}
    		} else {
    			for (int i_39_ = 0; i_39_ < 12; i_39_++) {
    				faceLists[i_39_] = 0;
    				anIntArray2002[i_39_] = 0;
    			}
    			for (int i_40_ = anInt1981 - 1; i_40_ >= 0; i_40_--) {
    				int i_41_ = anIntArray2008[i_40_];
    				if (i_41_ > 0) {
    					int[] is = anIntArrayArray2010[i_40_];
    					for (int i_42_ = 0; i_42_ < i_41_; i_42_++) {
    						int i_43_ = is[i_42_];
    						int i_44_ = face_render_priorities[i_43_];
    						int i_45_ = faceLists[i_44_]++;
    						anIntArrayArray2026[i_44_][i_45_] = i_43_;
    						if (i_44_ < 10)
    							anIntArray2002[i_44_] += i_40_;
    						else if (i_44_ == 10)
    							anIntArray2021[i_45_] = i_40_;
    						else
    							anIntArray2025[i_45_] = i_40_;
    					}
    				}
    			}
    			int i_46_ = 0;
    			if (faceLists[1] > 0 || faceLists[2] > 0)
    				i_46_ = ((anIntArray2002[1] + anIntArray2002[2]) / (faceLists[1] + faceLists[2]));
    			int i_47_ = 0;
    			if (faceLists[3] > 0 || faceLists[4] > 0)
    				i_47_ = ((anIntArray2002[3] + anIntArray2002[4]) / (faceLists[3] + faceLists[4]));
    			int i_48_ = 0;
    			if (faceLists[6] > 0 || faceLists[8] > 0)
    				i_48_ = ((anIntArray2002[6] + anIntArray2002[8]) / (faceLists[6] + faceLists[8]));
    			int i_49_ = 0;
    			int i_50_ = faceLists[10];
    			int[] is = anIntArrayArray2026[10];
    			int[] is_51_ = anIntArray2021;
    			if (i_49_ == i_50_) {
    				i_49_ = 0;
    				i_50_ = faceLists[11];
    				is = anIntArrayArray2026[11];
    				is_51_ = anIntArray2025;
    			}
    			int i_52_;
    			if (i_49_ < i_50_)
    				i_52_ = is_51_[i_49_];
    			else
    				i_52_ = -1000;
    			for (int i_53_ = 0; i_53_ < 10; i_53_++) {
    				while (i_53_ == 0) {
    					if (i_52_ <= i_46_)
    						break;
    					rasterize(is[i_49_++]);
    					if (i_49_ == i_50_ && is != anIntArrayArray2026[11]) {
    						i_49_ = 0;
    						i_50_ = faceLists[11];
    						is = anIntArrayArray2026[11];
    						is_51_ = anIntArray2025;
    					}
    					if (i_49_ < i_50_)
    						i_52_ = is_51_[i_49_];
    					else
    						i_52_ = -1000;
    				}
    				while (i_53_ == 3) {
    					if (i_52_ <= i_47_)
    						break;
    					rasterize(is[i_49_++]);
    					if (i_49_ == i_50_ && is != anIntArrayArray2026[11]) {
    						i_49_ = 0;
    						i_50_ = faceLists[11];
    						is = anIntArrayArray2026[11];
    						is_51_ = anIntArray2025;
    					}
    					if (i_49_ < i_50_)
    						i_52_ = is_51_[i_49_];
    					else
    						i_52_ = -1000;
    				}
    				while (i_53_ == 5 && i_52_ > i_48_) {
    					rasterize(is[i_49_++]);
    					if (i_49_ == i_50_ && is != anIntArrayArray2026[11]) {
    						i_49_ = 0;
    						i_50_ = faceLists[11];
    						is = anIntArrayArray2026[11];
    						is_51_ = anIntArray2025;
    					}
    					if (i_49_ < i_50_)
    						i_52_ = is_51_[i_49_];
    					else
    						i_52_ = -1000;
    				}
    				int i_54_ = faceLists[i_53_];
    				int[] is_55_ = anIntArrayArray2026[i_53_];
    				for (int i_56_ = 0; i_56_ < i_54_; i_56_++)
    					rasterize(is_55_[i_56_]);
    			}
    			while (i_52_ != -1000) {
    				rasterize(is[i_49_++]);
    				if (i_49_ == i_50_ && is != anIntArrayArray2026[11]) {
    					i_49_ = 0;
    					is = anIntArrayArray2026[11];
    					i_50_ = faceLists[11];
    					is_51_ = anIntArray2025;
    				}
    				if (i_49_ < i_50_)
    					i_52_ = is_51_[i_49_];
    				else
    					i_52_ = -1000;
    			}
    		}
    	}
    }
    
    final Model method127(boolean bool) {
    	if (!bool && (anIntArray2023.length < numberOfTriangleFaces))
    		anIntArray2023 = new int[numberOfTriangleFaces + 100];
    	return method132(bool, instance, anIntArray2023);
    }
    
    final void move(int x, int y, int z) {
    	anInt1962 = 0;
    	for (int i_59_ = 0; i_59_ < numberOfVerticeCoordinates; i_59_++) {
    		verticesXCoordinate[i_59_] += x;
		    verticesYCoordinate[i_59_] += y;
		    verticesZCoordinate[i_59_] += z;
    	}
    }
    
    final void scale(int x, int y, int z) {
    	vertices = null;
    	anInt1962 = 0;
    	for (int id = 0; id < numberOfVerticeCoordinates; id++) {
    		verticesXCoordinate[id] = (verticesXCoordinate[id] * x / 128);
    		verticesYCoordinate[id] = (verticesYCoordinate[id] * y / 128);
    		verticesZCoordinate[id] = (verticesZCoordinate[id] * z / 128);
    	}
    }
    
    public final void method130() {
    	if (anInt1962 != 2) {
    		anInt1962 = 2;
    		diagonalLine = 0;
    		for (int vertexId = 0; vertexId < numberOfVerticeCoordinates; vertexId++) {
    			int vertexX = verticesXCoordinate[vertexId];
    			int vertexY = verticesYCoordinate[vertexId];
    			int vertexZ = verticesZCoordinate[vertexId];
    			int dist = vertexX * vertexX + vertexZ * vertexZ + vertexY * vertexY;
    			if (dist > diagonalLine)
    				diagonalLine = dist;
    		}
    		diagonalLine = (int) (Math.sqrt((double) diagonalLine) + 0.99);
    		anInt1960 = diagonalLine;
    		anInt1981 = (diagonalLine + diagonalLine);
    	}
    }
    
    public static final int method131(int color, int i_67_, int face) {
    	if ((face & 0x2) == 2) {
    		if (i_67_ < 0)
    			i_67_ = 0;
    		else if (i_67_ > 127)
    			i_67_ = 127;
    		i_67_ = anIntArray2007[i_67_];
    		return i_67_;
    	}
    	i_67_ = i_67_ * (color & 0x7f) >> 7;
    	if (i_67_ < 2)
    		i_67_ = 2;
    	else if (i_67_ > 126)
    		i_67_ = 126;
    	return (color & 0xff80) + i_67_;
    }
    
    public final Model method132(boolean bool, Model model, int[] is) {
    	model.numberOfVerticeCoordinates = numberOfVerticeCoordinates;
    	model.numberOfTriangleFaces = numberOfTriangleFaces;
    	model.texTriangleCount = texTriangleCount;
    	if (model.verticesXCoordinate == null || model.verticesXCoordinate.length < numberOfVerticeCoordinates) {
    		model.verticesXCoordinate = new int[numberOfVerticeCoordinates + 100];
    		model.verticesYCoordinate = new int[numberOfVerticeCoordinates + 100];
    		model.verticesZCoordinate = new int[numberOfVerticeCoordinates + 100];
    	}
    	for (int i = 0; i < numberOfVerticeCoordinates; i++) {
    		model.verticesXCoordinate[i] = verticesXCoordinate[i];
    		model.verticesYCoordinate[i] = verticesYCoordinate[i];
    		model.verticesZCoordinate[i] = verticesZCoordinate[i];
    	}
    	if (bool)
    		model.face_alpha = face_alpha;
    	else {
    		model.face_alpha = is;
    		if (face_alpha == null) {
    			for (int i = 0; i < numberOfTriangleFaces; i++)
    				model.face_alpha[i] = 0;
    		} else {
    			for (int i = 0; i < numberOfTriangleFaces; i++)
    				model.face_alpha[i] = face_alpha[i];
    		}
    	}
    	model.face_render_type = face_render_type;
    	model.face_color = face_color;
    	model.face_render_priorities = face_render_priorities;
    	model.anInt1983 = anInt1983;
    	model.triangleSkins = triangleSkins;
    	model.vertexSkins = vertexSkins;
    	model.face_a = face_a;
    	model.face_b = face_b;
    	model.face_c = face_c;
    	model.triangleColorsA = triangleColorsA;
    	model.triangleColorsB = triangleColorsB;
		model.triangleColorsC = triangleColorsC;
		model.textured_face_a = textured_face_a;
		model.textured_face_b = textured_face_b;
		model.textured_face_c = textured_face_c;
		model.anInt1962 = 0;
		return model;
    }
    
    final void method133() {
    	vertices = null;
    	anInt1962 = 0;
    	for (int i = 0; i < numberOfVerticeCoordinates; i++)
    		verticesZCoordinate[i] = -verticesZCoordinate[i];
    	for (int i = 0; i < numberOfTriangleFaces; i++) {
    		int i_70_ = face_a[i];
    		face_a[i] = face_c[i];
    		face_c[i] = i_70_;
		}
    }
    
    public final void method135(int i) {
    	int i_71_ = Rasterizer.rasterizeCenterX;
    	int i_72_ = Rasterizer.rasterizeCenterY;
    	int i_73_ = 0;
    	int i_74_ = face_a[i];
    	int i_75_ = face_b[i];
    	int i_76_ = face_c[i];
    	int i_77_ = anIntArray1996[i_74_];
    	int i_78_ = anIntArray1996[i_75_];
    	int i_79_ = anIntArray1996[i_76_];
		if (face_alpha == null)
			Rasterizer.alpha = 0;
		else
			Rasterizer.alpha = face_alpha[i];
		if (i_77_ >= 50) {
			anIntArray2001[i_73_] = anIntArray2018[i_74_];
			anIntArray2000[i_73_] = anIntArray2017[i_74_];
			anIntArray1995[i_73_++] = triangleColorsA[i];
		} else {
			int i_80_ = anIntArray2006[i_74_];
			int i_81_ = anIntArray2014[i_74_];
			int i_82_ = triangleColorsA[i];
			if (i_79_ >= 50) {
				int i_83_ = (50 - i_77_) * anIntArray2004[i_79_ - i_77_];
				anIntArray2001[i_73_] = i_71_ + (i_80_ + ((anIntArray2006[i_76_] - i_80_) * i_83_ >> 16) << 9) / 50;
				anIntArray2000[i_73_] = i_72_ + (i_81_ + ((anIntArray2014[i_76_] - i_81_) * i_83_ >> 16) << 9) / 50;
				anIntArray1995[i_73_++] = i_82_ + ((triangleColorsC[i] - i_82_) * i_83_ >> 16);
			}
			if (i_78_ >= 50) {
				int i_84_ = (50 - i_77_) * anIntArray2004[i_78_ - i_77_];
				anIntArray2001[i_73_] = i_71_ + (i_80_ + ((anIntArray2006[i_75_] - i_80_) * i_84_ >> 16) << 9) / 50;
				anIntArray2000[i_73_] = i_72_ + (i_81_ + ((anIntArray2014[i_75_] - i_81_) * i_84_ >> 16) << 9) / 50;
				anIntArray1995[i_73_++] = i_82_ + ((triangleColorsB[i] - i_82_) * i_84_ >> 16);
		    }
		}
		if (i_78_ >= 50) {
			anIntArray2001[i_73_] = anIntArray2018[i_75_];
			anIntArray2000[i_73_] = anIntArray2017[i_75_];
			anIntArray1995[i_73_++] = triangleColorsB[i];
		} else {
			int i_85_ = anIntArray2006[i_75_];
			int i_86_ = anIntArray2014[i_75_];
			int i_87_ = triangleColorsB[i];
			if (i_77_ >= 50) {
				int i_88_ = (50 - i_78_) * anIntArray2004[i_77_ - i_78_];
				anIntArray2001[i_73_] = i_71_ + (i_85_ + ((anIntArray2006[i_74_] - i_85_) * i_88_ >> 16) << 9) / 50;
				anIntArray2000[i_73_] = i_72_ + (i_86_ + ((anIntArray2014[i_74_] - i_86_) * i_88_ >> 16) << 9) / 50;
				anIntArray1995[i_73_++] = i_87_ + ((triangleColorsA[i] - i_87_) * i_88_ >> 16);
			}
			if (i_79_ >= 50) {
				int i_89_ = (50 - i_78_) * anIntArray2004[i_79_ - i_78_];
				anIntArray2001[i_73_] = i_71_ + (i_85_ + ((anIntArray2006[i_76_] - i_85_) * i_89_ >> 16) << 9) / 50;
				anIntArray2000[i_73_] = i_72_ + (i_86_ + ((anIntArray2014[i_76_] - i_86_) * i_89_ >> 16) << 9) / 50;
				anIntArray1995[i_73_++] = i_87_ + ((triangleColorsC[i] - i_87_) * i_89_ >> 16);
			}
		}
		if (i_79_ >= 50) {
			anIntArray2001[i_73_] = anIntArray2018[i_76_];
			anIntArray2000[i_73_] = anIntArray2017[i_76_];
			anIntArray1995[i_73_++] = triangleColorsC[i];
		} else {
			int i_90_ = anIntArray2006[i_76_];
			int i_91_ = anIntArray2014[i_76_];
			int i_92_ = triangleColorsC[i];
			if (i_78_ >= 50) {
				int i_93_ = (50 - i_79_) * anIntArray2004[i_78_ - i_79_];
				anIntArray2001[i_73_] = i_71_ + (i_90_ + ((anIntArray2006[i_75_] - i_90_) * i_93_ >> 16) << 9) / 50;
				anIntArray2000[i_73_] = i_72_ + (i_91_ + ((anIntArray2014[i_75_] - i_91_) * i_93_ >> 16) << 9) / 50;
				anIntArray1995[i_73_++] = i_92_ + ((triangleColorsB[i] - i_92_) * i_93_ >> 16);
			}
			if (i_77_ >= 50) {
				int i_94_ = (50 - i_79_) * anIntArray2004[i_77_ - i_79_];
				anIntArray2001[i_73_] = i_71_ + (i_90_ + ((anIntArray2006[i_74_] - i_90_) * i_94_ >> 16) << 9) / 50;
				anIntArray2000[i_73_] = i_72_ + (i_91_ + ((anIntArray2014[i_74_] - i_91_) * i_94_ >> 16) << 9) / 50;
				anIntArray1995[i_73_++] = i_92_ + ((triangleColorsA[i] - i_92_) * i_94_ >> 16);
			}
		}
		int i_95_ = anIntArray2001[0];
		int i_96_ = anIntArray2001[1];
		int i_97_ = anIntArray2001[2];
		int i_98_ = anIntArray2000[0];
		int i_99_ = anIntArray2000[1];
		int i_100_ = anIntArray2000[2];
		Rasterizer.edgeRestricted = false;
		if (i_73_ == 3) {
			if (i_95_ < 0 || i_96_ < 0 || i_97_ < 0
					|| i_95_ > Rasterizer.endX
					|| i_96_ > Rasterizer.endX
					|| i_97_ > Rasterizer.endX)
				Rasterizer.edgeRestricted = true;
			int face;
			if (face_render_type == null)
				face = 0;
			else
				face = face_render_type[i] & 0x3;
			if (face == 0)
				Rasterizer.drawShadedTriangle(i_98_, i_99_, i_100_, i_95_, i_96_, i_97_,
						anIntArray1995[0],
						anIntArray1995[1],
						anIntArray1995[2]);
			else if (face == 1)
				Rasterizer.drawPolygon(i_98_, i_99_, i_100_, i_95_, i_96_, i_97_,
						pallete[triangleColorsA[i]]);
			else if (face == 2) {
				int i_102_ = face_render_type[i] >> 2;
				int i_103_ = textured_face_a[i_102_];
				int i_104_ = textured_face_b[i_102_];
				int i_105_ = textured_face_c[i_102_];
				Rasterizer.drawTexturedTriangle(i_98_, i_99_, i_100_, i_95_, i_96_, i_97_,
						anIntArray1995[0], anIntArray1995[1], anIntArray1995[2],
						anIntArray2006[i_103_], anIntArray2006[i_104_],
						anIntArray2006[i_105_], anIntArray2014[i_103_],
						anIntArray2014[i_104_], anIntArray2014[i_105_],
						anIntArray1996[i_103_], anIntArray1996[i_104_],
						anIntArray1996[i_105_], face_color[i]);
			} else if (face == 3) {
				int i_106_ = face_render_type[i] >> 2;
				int i_107_ = textured_face_a[i_106_];
				int i_108_ = textured_face_b[i_106_];
				int i_109_ = textured_face_c[i_106_];
				Rasterizer.drawTexturedTriangle(i_98_, i_99_, i_100_, i_95_, i_96_, i_97_,
						triangleColorsA[i], triangleColorsA[i], triangleColorsA[i],
						anIntArray2006[i_107_], anIntArray2006[i_108_],
						anIntArray2006[i_109_], anIntArray2014[i_107_],
						anIntArray2014[i_108_], anIntArray2014[i_109_],
						anIntArray1996[i_107_], anIntArray1996[i_108_],
						anIntArray1996[i_109_], face_color[i]);
			}
		}
		if (i_73_ == 4) {
			if (i_95_ < 0 || i_96_ < 0 || i_97_ < 0
					|| i_95_ > Rasterizer.endX
					|| i_96_ > Rasterizer.endX
					|| i_97_ > Rasterizer.endX
					|| anIntArray2001[3] < 0
					|| anIntArray2001[3] > Rasterizer.endX)
				Rasterizer.edgeRestricted = true;
			int type;
			if (face_render_type == null)
				type = 0;
			else
				type = face_render_type[i] & 0x3;
			if (type == 0) {
				Rasterizer.drawShadedTriangle(i_98_, i_99_, i_100_, i_95_, i_96_, i_97_,
						anIntArray1995[0],
						anIntArray1995[1],
						anIntArray1995[2]);
				Rasterizer.drawShadedTriangle(i_98_, i_100_,
						anIntArray2000[3], i_95_,
						i_97_, anIntArray2001[3],
						anIntArray1995[0],
						anIntArray1995[2],
						anIntArray1995[3]);
			} else if (type == 1) {
				int i_111_ = pallete[triangleColorsA[i]];
				Rasterizer.drawPolygon(i_98_, i_99_, i_100_, i_95_, i_96_, i_97_, i_111_);
				Rasterizer.drawPolygon(i_98_, i_100_,
						anIntArray2000[3], i_95_,
						i_97_, anIntArray2001[3],
						i_111_);
			} else if (type == 2) {
				int i_112_ = face_render_type[i] >> 2;
				int i_113_ = textured_face_a[i_112_];
				int i_114_ = textured_face_b[i_112_];
				int i_115_ = textured_face_c[i_112_];
				Rasterizer.drawTexturedTriangle(i_98_, i_99_, i_100_, i_95_, i_96_, i_97_,
						anIntArray1995[0], anIntArray1995[1], anIntArray1995[2],
						anIntArray2006[i_113_], anIntArray2006[i_114_],
						anIntArray2006[i_115_], anIntArray2014[i_113_],
						anIntArray2014[i_114_], anIntArray2014[i_115_],
						anIntArray1996[i_113_], anIntArray1996[i_114_],
						anIntArray1996[i_115_], face_color[i]);
				Rasterizer.drawTexturedTriangle(i_98_, i_100_, anIntArray2000[3], i_95_, i_97_,
						anIntArray2001[3], anIntArray1995[0], anIntArray1995[2],
						anIntArray1995[3], anIntArray2006[i_113_],
						anIntArray2006[i_114_], anIntArray2006[i_115_],
						anIntArray2014[i_113_], anIntArray2014[i_114_],
						anIntArray2014[i_115_], anIntArray1996[i_113_],
						anIntArray1996[i_114_], anIntArray1996[i_115_],
						face_color[i]);
			} else if (type == 3) {
				int i_116_ = face_render_type[i] >> 2;
				int i_117_ = textured_face_a[i_116_];
				int i_118_ = textured_face_b[i_116_];
				int i_119_ = textured_face_c[i_116_];
				Rasterizer.drawTexturedTriangle(i_98_, i_99_, i_100_, i_95_, i_96_, i_97_,
						triangleColorsA[i], triangleColorsA[i], triangleColorsA[i],
						anIntArray2006[i_117_], anIntArray2006[i_118_],
						anIntArray2006[i_119_], anIntArray2014[i_117_],
						anIntArray2014[i_118_], anIntArray2014[i_119_],
						anIntArray1996[i_117_], anIntArray1996[i_118_],
						anIntArray1996[i_119_], face_color[i]);
				Rasterizer.drawTexturedTriangle(i_98_, i_100_, anIntArray2000[3], i_95_, i_97_,
						anIntArray2001[3], triangleColorsA[i], triangleColorsA[i],
						triangleColorsA[i], anIntArray2006[i_117_],
						anIntArray2006[i_118_], anIntArray2006[i_119_],
						anIntArray2014[i_117_], anIntArray2014[i_118_],
						anIntArray2014[i_119_], anIntArray1996[i_117_],
						anIntArray1996[i_118_], anIntArray1996[i_119_],
						face_color[i]);
			}
		}
    }
    
    public final int method136(Model model, int vertexId) {
    	int i_121_ = -1;
    	int vertexX = model.verticesXCoordinate[vertexId];
    	int vertexY = model.verticesYCoordinate[vertexId];
    	int vertexZ = model.verticesZCoordinate[vertexId];
    	for (int vId = 0; vId < numberOfVerticeCoordinates; vId++) {
    		if (vertexX == verticesXCoordinate[vId] && (vertexY == verticesYCoordinate[vId]) && vertexZ == (verticesZCoordinate[vId])) {
    			i_121_ = vId;
    			break;
    		}
    	}
    	if (i_121_ == -1) {
    		verticesXCoordinate[numberOfVerticeCoordinates] = vertexX;
    		verticesYCoordinate[numberOfVerticeCoordinates] = vertexY;
    		verticesZCoordinate[numberOfVerticeCoordinates] = vertexZ;
    		if (model.vertexVSkins != null)
    			vertexVSkins[numberOfVerticeCoordinates] = model.vertexVSkins[vertexId];
    		i_121_ = numberOfVerticeCoordinates++;
    	}
    	return i_121_;
    }
    
    final void preProcess(int lightness, int shading, int i_127_, int i_128_, int i_129_, boolean bool) {
    	try {
			int src_shad = (int) Math.sqrt((double) (i_127_ * i_127_ + i_128_ * i_128_ + i_129_ * i_129_));
			int dest_shad = shading * src_shad >> 8;
			if (triangleColorsA == null) {
				triangleColorsA = new int[numberOfTriangleFaces];
				triangleColorsB = new int[numberOfTriangleFaces];
				triangleColorsC = new int[numberOfTriangleFaces];
			}
			if (vertices == null) {
				vertices = new Vertex[numberOfVerticeCoordinates];
				for (int id = 0; id < numberOfVerticeCoordinates; id++)
					vertices[id] = new Vertex();
			}
			for (int triangleId = 0; triangleId < numberOfTriangleFaces; triangleId++) {
				int triA = face_a[triangleId];
				int triB = face_b[triangleId];
				int triC = face_c[triangleId];
				int i_137_ = (verticesXCoordinate[triB] - verticesXCoordinate[triA]);
				int i_138_ = (verticesYCoordinate[triB] - verticesYCoordinate[triA]);
				int i_139_ = (verticesZCoordinate[triB] - verticesZCoordinate[triA]);
				int i_140_ = (verticesXCoordinate[triC] - verticesXCoordinate[triA]);
				int i_141_ = (verticesYCoordinate[triC] - verticesYCoordinate[triA]);
				int i_142_ = (verticesZCoordinate[triC] - verticesZCoordinate[triA]);
				int i_143_ = i_138_ * i_142_ - i_141_ * i_139_;
				int i_144_ = i_139_ * i_140_ - i_142_ * i_137_;
				int i_145_;
				for (i_145_ = i_137_ * i_141_ - i_140_ * i_138_; (i_143_ > 8192 || i_144_ > 8192 || i_145_ > 8192 || i_143_ < -8192 || i_144_ < -8192 || i_145_ < -8192); i_145_ >>= 1) {
					i_143_ >>= 1;
					i_144_ >>= 1;
				}
				int i_146_ = (int) Math.sqrt((double) (i_143_ * i_143_ + i_144_ * i_144_ + i_145_ * i_145_));
				if (i_146_ <= 0)
					i_146_ = 1;
				i_143_ = i_143_ * 256 / i_146_;
				i_144_ = i_144_ * 256 / i_146_;
				i_145_ = i_145_ * 256 / i_146_;
				if (face_render_type == null || (face_render_type[triangleId] & 0x1) == 0) {
					Vertex class27 = vertices[triA];
					class27.anInt485 += i_143_;
					class27.anInt488 += i_144_;
					class27.anInt500 += i_145_;
					class27.anInt494++;
					class27 = vertices[triB];
					class27.anInt485 += i_143_;
					class27.anInt488 += i_144_;
					class27.anInt500 += i_145_;
					class27.anInt494++;
					class27 = vertices[triC];
					class27.anInt485 += i_143_;
					class27.anInt488 += i_144_;
					class27.anInt500 += i_145_;
					class27.anInt494++;
				} else {
					int i_147_ = lightness + (i_127_ * i_143_ + i_128_ * i_144_ + i_129_ * i_145_) / (dest_shad + dest_shad / 2);
					triangleColorsA[triangleId] = method131(face_color[triangleId], i_147_, (face_render_type[triangleId]));
				}
			}
			if (bool)
				method145(lightness, dest_shad, i_127_, i_128_, i_129_);
			else {
				aClass27Array1970 = new Vertex[numberOfVerticeCoordinates];
				for (int i_148_ = 0; i_148_ < numberOfVerticeCoordinates; i_148_++) {
					Vertex class27 = vertices[i_148_];
					Vertex class27_149_ = (aClass27Array1970[i_148_] = new Vertex());
					class27_149_.anInt485 = class27.anInt485;
					class27_149_.anInt488 = class27.anInt488;
					class27_149_.anInt500 = class27.anInt500;
					class27_149_.anInt494 = class27.anInt494;
				}
				anInt1987 = (lightness << 16) + (dest_shad & 0xffff);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    final void render(int direction, int sinePitch, int cosinePitch, int sineYaw, int cosineYaw,
    		int renderX, int renderZ, int renderY, int uid) {
    	if (anInt1962 != 1) {
    		method151();
    	}
    	int i_158_ = renderY * cosineYaw - renderX * sineYaw >> 16;
	    int i_159_ = renderZ * sinePitch + i_158_ * cosinePitch >> 16;
		int i_160_ = diagonalLine * cosinePitch >> 16;
		int i_161_ = i_159_ + i_160_;
		if (i_161_ > 0) { //TODO: Render distance, 50, 3500
			int i_162_ = renderY * sineYaw + renderX * cosineYaw >> 16;
		 	int i_163_  = i_162_ - diagonalLine << 9;
		 	if (i_163_ / i_161_ < Rasterizer.renderCenterX) {
		 		int i_164_ = i_162_ + diagonalLine << 9;
		 		if (i_164_ / i_161_ > Rasterizer.anInt2118) {
		 			int i_165_ = renderZ * cosinePitch - i_158_ * sinePitch >> 16;
		 			int i_166_ = (diagonalLine * sinePitch >> 16);
		 			int i_167_ = i_165_ + i_166_ << 9;
		 			if (i_167_ / i_161_ > Rasterizer.anInt2109) {
		 				int i_168_ = (i_166_ + (height * cosinePitch >> 16));
		 				int i_169_ = i_165_ - i_168_ << 9;
		 				if (i_169_ / i_161_ < Rasterizer.renderCenterY) {
		 					int i_170_ = i_160_ + ((height) * sinePitch >> 16);
		 					boolean bool = false;
		 					boolean bool_171_ = false;
		 					if (i_159_ - i_170_ <= 50)
		 						bool_171_ = true;
		 					boolean bool_172_ = bool_171_ || texTriangleCount > 0;
		 					boolean bool_173_ = false;
		 					if (uid > 0 && aBoolean2012) {
		 						int i_174_ = i_159_ - i_160_;
		 						if (i_174_ <= 50)
		 							i_174_ = 50;
		 						if (i_162_ > 0) {
		 							i_163_ /= i_161_;
		 							i_164_ /= i_174_;
		 						} else {
		 							i_164_ /= i_161_;
		 							i_163_ /= i_174_;
		 						}
		 						if (i_165_ > 0) {
		 							i_169_ /= i_161_;
		 							i_167_ /= i_174_;
		 						} else {
		 							i_167_ /= i_161_;
		 							i_169_ /= i_174_;
		 						}
		 						int i_175_ = (anInt2019 - Rasterizer.rasterizeCenterX);
		 						int i_176_ = (anInt2015 - Rasterizer.rasterizeCenterY);
		 						if (i_175_ > i_163_ && i_175_ < i_164_ && i_176_ > i_169_ && i_176_ < i_167_) {
		 							if (aBoolean1979)
		 								anIntArray2003[anInt1998++] = uid;
		 							else
		 								bool_173_ = true;
		 						}
		 					}
		 					int centerX = Rasterizer.rasterizeCenterX;
		 					int centerY = Rasterizer.rasterizeCenterY;
		 					int sin = 0;
		 					int cos = 0;
		 					if (direction != 0) {
		 						sin = sine[direction];
		 						cos = cosine[direction];
		 					}
		 					for (int vertextId = 0; vertextId < numberOfVerticeCoordinates; vertextId++) {
		 						int vertexX = verticesXCoordinate[vertextId];
		 						int vertexY = verticesYCoordinate[vertextId];
		 						int vertexZ = verticesZCoordinate[vertextId];
		 						if (direction != 0) {
		 							int i_185_ = (vertexZ * sin + vertexX * cos >> 16);
		 							vertexZ = (vertexZ * cos - vertexX * sin >> 16);
		 							vertexX = i_185_;
		 						}
		 						vertexX += renderX;
		 						vertexY += renderZ;
		 						vertexZ += renderY;
		 						int i_186_ = vertexZ * sineYaw + vertexX * cosineYaw >> 16;
		 						vertexZ = vertexZ * cosineYaw - vertexX * sineYaw >> 16;
		 						vertexX = i_186_;
		 						i_186_ = vertexY * cosinePitch - vertexZ * sinePitch >> 16;
		 						vertexZ = vertexY * sinePitch + vertexZ * cosinePitch >> 16;
		 						vertexY = i_186_;
		 						anIntArray2022[vertextId] = vertexZ - i_159_;
		 						if (vertexZ >= 50) {
		 							anIntArray2018[vertextId] = centerX + (vertexX << 9) / vertexZ;
		 							anIntArray2017[vertextId] = centerY + (vertexY << 9) / vertexZ;
		 						} else {
		 							anIntArray2018[vertextId] = -5000;
		 							bool = true;
		 						}
		 						if (bool_172_) {
		 							anIntArray2006[vertextId] = vertexX;
		 							anIntArray2014[vertextId] = vertexY;
		 							anIntArray1996[vertextId] = vertexZ;
		 						}
		 					}
		 					try {
		 						method126(bool, bool_173_, uid);
		 					} catch (Exception exception) {
		 						/* empty */
		 					}
		 				}
		 			}
		 		}
		 	}
		}
    }
    
    final void method138() {
    	if (anInt1962 != 3) {
    		anInt1962 = 3;
    		height = 0;
    		anInt1994 = 0;
    		diagonalLine = 999999;
    		anInt1981 = -999999;
    		anInt1960 = -99999;
    		anInt1988 = 99999;
    		for (int i = 0; i < numberOfVerticeCoordinates; i++) {
    			int i_187_ = verticesXCoordinate[i];
    			int i_188_ = verticesYCoordinate[i];
    			int i_189_ = verticesZCoordinate[i];
    			if (i_187_ < diagonalLine)
    				diagonalLine = i_187_;
    			if (i_187_ > anInt1981)
    				anInt1981 = i_187_;
    			if (i_189_ < anInt1988)
    				anInt1988 = i_189_;
    			if (i_189_ > anInt1960)
    				anInt1960 = i_189_;
    			if (-i_188_ > height)
    				height = -i_188_;
    			if (i_188_ > anInt1994)
    				anInt1994 = i_188_;
    		}
    	}	
    }
    
    final void method139(int i, int i_190_, int i_191_) {
	int i_192_ = anInt1987 >> 16;
	int i_193_ = anInt1987 << 16 >> 16;
	method145(i_192_, i_193_, i, i_190_, i_191_);
    }
    
    public final boolean method140(int i, int i_194_, int i_195_, int i_196_,
				    int i_197_, int i_198_, int i_199_,
				    int i_200_) {
	if (i_194_ < i_195_ && i_194_ < i_196_ && i_194_ < i_197_)
	    return false;
	if (i_194_ > i_195_ && i_194_ > i_196_ && i_194_ > i_197_)
	    return false;
	if (i < i_198_ && i < i_199_ && i < i_200_)
	    return false;
	if (i > i_198_ && i > i_199_ && i > i_200_)
	    return false;
	return true;
    }
    
    final void swapColors(int orig, int modified) {
    	for (int triangleId = 0; triangleId < numberOfTriangleFaces; triangleId++) {
    		if (face_color[triangleId] == orig)
				face_color[triangleId] = modified;
    	}
    }
    
    final void method142(AnimationLoader class3_sub3_sub10, int i,
    		AnimationLoader class3_sub3_sub10_203_, int i_204_,
    		int[] is) {
    	if (i != -1) {
    		if (is == null || i_204_ == -1)
    			animate(class3_sub3_sub10, i);
    		else {
    			vertices = null;
    			anInt1962 = 0;
    			Animation class29 = class3_sub3_sub10.animation[i];
    			Animation class29_205_ = class3_sub3_sub10_203_.animation[i_204_];
    			SkinList class3_sub6 = class29.skinList;
    			vertexModX = 0;
    			vertexModY = 0;
    			vertexModZ = 0;
    			int i_206_ = 0;
				int i_207_ = is[i_206_++];
				for (int i_208_ = 0; i_208_ < class29.arrayLen; i_208_++) {
					int i_209_;
					for (i_209_ = class29.ids[i_208_]; i_209_ > i_207_; i_207_ = is[i_206_++]) {
						/* empty */
					}
					if (i_209_ != i_207_ || class3_sub6.opcodes[i_209_] == 0)
						animate(class3_sub6.opcodes[i_209_], class3_sub6.skins[i_209_],
								class29.vertexX[i_208_], class29.vertexY[i_208_], class29.vertexZ[i_208_]);
				}
				vertexModX = 0;
				vertexModY = 0;
				vertexModZ = 0;
				i_206_ = 0;
				i_207_ = is[i_206_++];
				for (int i_210_ = 0; i_210_ < class29_205_.arrayLen; i_210_++) {
					int i_211_;
					for (i_211_ = class29_205_.ids[i_210_]; i_211_ > i_207_; i_207_ = is[i_206_++]) {
					/* empty */
					}
					if (i_211_ == i_207_ || (class3_sub6.opcodes[i_211_] == 0))
						animate(class3_sub6.opcodes[i_211_], class3_sub6.skins[i_211_],
								class29_205_.vertexX[i_210_], class29_205_.vertexY[i_210_], class29_205_.vertexZ[i_210_]);
				}
		    }
		}
    }
    
    public final void rasterize(int i) {
    	if (aBooleanArray1997[i])
    		method135(i);
    	else {
    		int i_212_ = face_a[i];
    		int i_213_ = face_b[i];
    		int i_214_ = face_c[i];
    		Rasterizer.edgeRestricted = edgeRestricted[i];
    		if (face_alpha == null)
    			Rasterizer.alpha = 0;
    		else
    			Rasterizer.alpha = face_alpha[i];
    		int face;
    		if (face_render_type == null)
    			face = 0;
    		else
    			face = face_render_type[i] & 0x3;
    		if (face == 0)
    			Rasterizer.drawShadedTriangle(anIntArray2017[i_212_],
    					anIntArray2017[i_213_],
    					anIntArray2017[i_214_],
    					anIntArray2018[i_212_],
    					anIntArray2018[i_213_],
    					anIntArray2018[i_214_],
    					triangleColorsA[i],
    					triangleColorsB[i],
    					triangleColorsC[i]);
    		else if (face == 1)
    			Rasterizer.drawPolygon(anIntArray2017[i_212_],
    					anIntArray2017[i_213_],
    					anIntArray2017[i_214_],
    					anIntArray2018[i_212_],
    					anIntArray2018[i_213_],
    					anIntArray2018[i_214_],
    					pallete[triangleColorsA[i]]);
    		else if (face == 2) {
    			int i_216_ = face_render_type[i] >> 2;
				int i_217_ = textured_face_a[i_216_];
				int i_218_ = textured_face_b[i_216_];
				int i_219_ = textured_face_c[i_216_];
				Rasterizer.drawTexturedTriangle(
						anIntArray2017[i_212_], anIntArray2017[i_213_],
						anIntArray2017[i_214_], anIntArray2018[i_212_],
						anIntArray2018[i_213_], anIntArray2018[i_214_],
						triangleColorsA[i], triangleColorsB[i], triangleColorsC[i],
						anIntArray2006[i_217_], anIntArray2006[i_218_],
						anIntArray2006[i_219_], anIntArray2014[i_217_],
						anIntArray2014[i_218_], anIntArray2014[i_219_],
						anIntArray1996[i_217_], anIntArray1996[i_218_],
						anIntArray1996[i_219_], face_color[i]);
    		} else if (face == 3) {
    			int i_220_ = face_render_type[i] >> 2;
    			int i_221_ = textured_face_a[i_220_];
    			int i_222_ = textured_face_b[i_220_];
    			int i_223_ = textured_face_c[i_220_];
    			Rasterizer.drawTexturedTriangle(
    					anIntArray2017[i_212_], anIntArray2017[i_213_],
    					anIntArray2017[i_214_], anIntArray2018[i_212_],
    					anIntArray2018[i_213_], anIntArray2018[i_214_],
    					triangleColorsA[i], triangleColorsA[i], triangleColorsA[i],
    					anIntArray2006[i_221_], anIntArray2006[i_222_],
    					anIntArray2006[i_223_], anIntArray2014[i_221_],
    					anIntArray2014[i_222_], anIntArray2014[i_223_],
    					anIntArray1996[i_221_], anIntArray1996[i_222_],
    					anIntArray1996[i_223_], face_color[i]);
    		}
    	}
    }
    
    final void method144(int i, int i_224_, int i_225_, int i_226_, int i_227_,
			 int i_228_, int i_229_) {
		if (anInt1962 != 2 && anInt1962 != 1)
		    method130();
		int i_230_ = Rasterizer.rasterizeCenterX;
		int i_231_ = Rasterizer.rasterizeCenterY;
		int i_232_ = sine[i];
		int i_233_ = cosine[i];
		int i_234_ = sine[i_224_];
		int i_235_ = cosine[i_224_];
		int i_236_ = sine[i_225_];
		int i_237_ = cosine[i_225_];
		int i_238_ = sine[i_226_];
		int i_239_ = cosine[i_226_];
		int i_240_ = i_228_ * i_238_ + i_229_ * i_239_ >> 16;
		for (int i_241_ = 0; i_241_ < numberOfVerticeCoordinates;
		     i_241_++) {
		    int i_242_ = verticesXCoordinate[i_241_];
		    int i_243_ = verticesYCoordinate[i_241_];
		    int i_244_ = verticesZCoordinate[i_241_];
		    if (i_225_ != 0) {
			int i_245_ = i_243_ * i_236_ + i_242_ * i_237_ >> 16;
			i_243_ = i_243_ * i_237_ - i_242_ * i_236_ >> 16;
			i_242_ = i_245_;
		    }
		    if (i != 0) {
			int i_246_ = i_243_ * i_233_ - i_244_ * i_232_ >> 16;
			i_244_ = i_243_ * i_232_ + i_244_ * i_233_ >> 16;
			i_243_ = i_246_;
		    }
		    if (i_224_ != 0) {
			int i_247_ = i_244_ * i_234_ + i_242_ * i_235_ >> 16;
			i_244_ = i_244_ * i_235_ - i_242_ * i_234_ >> 16;
			i_242_ = i_247_;
		    }
		    i_242_ += i_227_;
		    i_243_ += i_228_;
		    i_244_ += i_229_;
		    int i_248_ = i_243_ * i_239_ - i_244_ * i_238_ >> 16;
		    i_244_ = i_243_ * i_238_ + i_244_ * i_239_ >> 16;
		    i_243_ = i_248_;
		    anIntArray2022[i_241_] = i_244_ - i_240_;
		    anIntArray2018[i_241_] = i_230_ + (i_242_ << 9) / i_244_;
		    anIntArray2017[i_241_] = i_231_ + (i_243_ << 9) / i_244_;
		    if (texTriangleCount > 0) {
			anIntArray2006[i_241_] = i_242_;
			anIntArray2014[i_241_] = i_243_;
			anIntArray1996[i_241_] = i_244_;
		    }
		}
		try {
		    method126(false, false, 0);
		} catch (Exception exception) {
		    /* empty */
		}
    }
    
    public final void method145(int i, int i_249_, int i_250_, int i_251_, int i_252_) {
    	for (int id = 0; id < numberOfTriangleFaces; id++) {
		    int triA = face_a[id];
		    int triB = face_b[id];
		    int triC = face_c[id];
		    if (face_render_type == null) {
		    	int color = face_color[id];
		    	Vertex class27 = vertices[triA];
		    	int i_258_ = i + ((i_250_ * class27.anInt485
		    			+ i_251_ * class27.anInt488
		    			+ i_252_ * class27.anInt500)
		    			/ (i_249_ * class27.anInt494));
		    	triangleColorsA[id] = method131(color, i_258_, 0);
		    	class27 = vertices[triB];
		    	i_258_ = i + ((i_250_ * class27.anInt485
		    			+ i_251_ * class27.anInt488
		    			+ i_252_ * class27.anInt500)
		    			/ (i_249_ * class27.anInt494));
		    	triangleColorsB[id] = method131(color, i_258_, 0);
		    	class27 = vertices[triC];
		    	i_258_ = i + ((i_250_ * class27.anInt485
		    			+ i_251_ * class27.anInt488
		    			+ i_252_ * class27.anInt500)
		    			/ (i_249_ * class27.anInt494));
		    	triangleColorsC[id] = method131(color, i_258_, 0);
		    } else if ((face_render_type[id] & 0x1) == 0) {
		    	int i_259_ = face_color[id];
		    	int i_260_ = face_render_type[id];
		    	Vertex class27 = vertices[triA];
		    	int i_261_ = i + ((i_250_ * class27.anInt485
		    			+ i_251_ * class27.anInt488
		    			+ i_252_ * class27.anInt500)
		    			/ (i_249_ * class27.anInt494));
		    	triangleColorsA[id] = method131(i_259_, i_261_, i_260_);
		    	class27 = vertices[triB];
		    	i_261_ = i + ((i_250_ * class27.anInt485
		    			+ i_251_ * class27.anInt488
		    			+ i_252_ * class27.anInt500)
		    			/ (i_249_ * class27.anInt494));
		    	triangleColorsB[id] = method131(i_259_, i_261_, i_260_);
		    	class27 = vertices[triC];
		    	i_261_ = i + ((i_250_ * class27.anInt485
		    			+ i_251_ * class27.anInt488
		    			+ i_252_ * class27.anInt500)
		    			/ (i_249_ * class27.anInt494));
		    	triangleColorsC[id] = method131(i_259_, i_261_, i_260_);
		    }
    	}
		vertices = null;
		aClass27Array1970 = null;
		vertexVSkins = null;
		triangleSkinValues = null;
		if (face_render_type != null) {
			for (int i_262_ = 0; i_262_ < numberOfTriangleFaces; i_262_++) {
				if ((face_render_type[i_262_] & 0x2) == 2)
					return;
		    }
		}
		face_color = null;
    }
    
    public static void reset() {
		aClass3_Sub3_Sub3_Sub3_1990 = null;
		anIntArray1993 = null;
		instance = null;
		anIntArray2023 = null;
		edgeRestricted = null;
		aBooleanArray1997 = null;
		anIntArray2018 = null;
		anIntArray2017 = null;
		anIntArray2022 = null;
		anIntArray2006 = null;
		anIntArray2014 = null;
		anIntArray1996 = null;
		anIntArray2008 = null;
		anIntArrayArray2010 = null;
		faceLists = null;
		anIntArrayArray2026 = null;
		anIntArray2021 = null;
		anIntArray2025 = null;
		anIntArray2002 = null;
		anIntArray2001 = null;
		anIntArray2000 = null;
		anIntArray1995 = null;
		anIntArray2003 = null;
		sine = null;
		cosine = null;
		pallete = null;
		anIntArray2004 = null;
		anIntArray2007 = null;
    }
    
    final void animate(AnimationLoader loader, int frame) {
    	if (vertexSkins != null && frame != -1) {
    		vertices = null;
    		anInt1962 = 0;
    		Animation animation = loader.animation[frame];
    		SkinList skins = animation.skinList;
    		vertexModX = 0;
    		vertexModY = 0;
    		vertexModZ = 0;
    		for (int i_263_ = 0; i_263_ < animation.arrayLen; i_263_++) {
    			int i_264_ = animation.ids[i_263_];
    			animate(skins.opcodes[i_264_], skins.skins[i_264_], animation.vertexX[i_263_], animation.vertexY[i_263_], animation.vertexZ[i_263_]);
    		}
		}
    }
    
    static final Model get(FileSystem fetcher, int modelId, int index) {
    	byte[] buffer = fetcher.getXteaBuffer(modelId, index);
    	if (buffer == null)
    		return null;
    	return new Model(buffer);
    }
    
    final int method149() {
    	method151();
    	return diagonalLine;
    }
    
    final Model method150(boolean bool) {
    	if (!bool && (anIntArray1993.length < numberOfTriangleFaces))
    		anIntArray1993 = new int[numberOfTriangleFaces + 100];
    	return method132(bool, aClass3_Sub3_Sub3_Sub3_1990, anIntArray1993);
    }
    
    final void method151() {
    	if (anInt1962 != 1) {
    		anInt1962 = 1;
    		height = 0;
    		anInt1994 = 0;
    		diagonalLine = 0;
    		for (int vertexid = 0; vertexid < numberOfVerticeCoordinates; vertexid++) {
    			int vertexX = verticesXCoordinate[vertexid];
    			int vertexY = verticesYCoordinate[vertexid];
    			int vertexZ = verticesZCoordinate[vertexid];
				if (-vertexY > height)
					height = -vertexY;
				if (vertexY > anInt1994)
					anInt1994 = vertexY;
				int i_269_ = vertexX * vertexX + vertexZ * vertexZ;
				if (i_269_ > diagonalLine)
					diagonalLine = i_269_;
    		}
    		diagonalLine = (int) (Math.sqrt((double) diagonalLine) + 0.99);
    		anInt1960 = (int) (Math.sqrt((double) ((diagonalLine * diagonalLine) + (height * height))) + 0.99);
    		anInt1981 = anInt1960 + (int) (Math.sqrt((double) ((diagonalLine * diagonalLine) + (anInt1994 * anInt1994))) + 0.99);
    	}
    }
    
    public final void animate(int opcode, int[] skins, int verticeX, int verticeY, int verticeZ) {
		int skinCount = skins.length;
		if (opcode == 0) {
		    int modOff = 0;
		    vertexModX = 0;
		    vertexModY = 0;
		    vertexModZ = 0;
		    for (int i_275_ = 0; i_275_ < skinCount; i_275_++) {
		    	int vSkin = skins[i_275_];
		    	if (vSkin < vertexSkins.length) {
		    		int[] is_277_ = vertexSkins[vSkin];
		    		for (int i_278_ = 0; i_278_ < is_277_.length; i_278_++) {
		    			int i_279_ = is_277_[i_278_];
		    			vertexModX += verticesXCoordinate[i_279_];
		    			vertexModY += verticesYCoordinate[i_279_];
		    			vertexModZ += verticesZCoordinate[i_279_];
		    			modOff++;
		    		}
		    	}
		    }
		    if (modOff > 0) {
		    	vertexModX = vertexModX / modOff + verticeX;
		    	vertexModY = vertexModY / modOff + verticeY;
		    	vertexModZ = vertexModZ / modOff + verticeZ;
		    } else {
		    	vertexModX = verticeX;
		    	vertexModY = verticeY;
		    	vertexModZ = verticeZ;
		    }
		} else if (opcode == 1) {
			for (int i_280_ = 0; i_280_ < skinCount; i_280_++) {
				int i_281_ = skins[i_280_];
				if (i_281_ < vertexSkins.length) {
					int[] is_282_ = vertexSkins[i_281_];
					for (int i_283_ = 0; i_283_ < is_282_.length; i_283_++) {
						int i_284_ = is_282_[i_283_];
						verticesXCoordinate[i_284_] += verticeX;
						verticesYCoordinate[i_284_] += verticeY;
						verticesZCoordinate[i_284_] += verticeZ;
					}
				}
			}
		} else if (opcode == 2) {
			for (int i_285_ = 0; i_285_ < skinCount; i_285_++) {
				int i_286_ = skins[i_285_];
				if (i_286_ < vertexSkins.length) {
					int[] is_287_ = vertexSkins[i_286_];
					for (int i_288_ = 0; i_288_ < is_287_.length; i_288_++) {
						int i_289_ = is_287_[i_288_];
						verticesXCoordinate[i_289_] -= vertexModX;
						verticesYCoordinate[i_289_] -= vertexModY;
						verticesZCoordinate[i_289_] -= vertexModZ;
						int i_290_ = (verticeX & 0xff) * 8;
						int i_291_ = (verticeY & 0xff) * 8;
						int i_292_ = (verticeZ & 0xff) * 8;
						if (i_292_ != 0) {
							int i_293_ = sine[i_292_];
							int i_294_ = cosine[i_292_];
							int i_295_ = (verticesYCoordinate[i_289_] * i_293_ + verticesXCoordinate[i_289_] * i_294_) >> 16;
							verticesYCoordinate[i_289_] = (verticesYCoordinate[i_289_] * i_294_ - verticesXCoordinate[i_289_] * i_293_) >> 16;
							verticesXCoordinate[i_289_] = i_295_;
						}
						if (i_290_ != 0) {
							int i_296_ = sine[i_290_];
							int i_297_ = cosine[i_290_];
							int i_298_ = (((verticesYCoordinate[i_289_]) * i_297_ - (verticesZCoordinate[i_289_]) * i_296_) >> 16);
							verticesZCoordinate[i_289_] = ((verticesYCoordinate[i_289_]) * i_296_ + (verticesZCoordinate[i_289_]) * i_297_) >> 16;
		    				verticesYCoordinate[i_289_] = i_298_;
						}
						if (i_291_ != 0) {
							int i_299_ = sine[i_291_];
							int i_300_ = cosine[i_291_];
							int i_301_ = (((verticesZCoordinate[i_289_]) * i_299_ + (verticesXCoordinate[i_289_]) * i_300_) >> 16);
							verticesZCoordinate[i_289_] = ((verticesZCoordinate[i_289_]) * i_300_ - (verticesXCoordinate[i_289_]) * i_299_) >> 16;
	    					verticesXCoordinate[i_289_] = i_301_;
						}
						verticesXCoordinate[i_289_] += vertexModX;
						verticesYCoordinate[i_289_] += vertexModY;
						verticesZCoordinate[i_289_] += vertexModZ;
					}
				}
			}
		} else if (opcode == 3) {
			for (int i_302_ = 0; i_302_ < skinCount; i_302_++) {
				int i_303_ = skins[i_302_];
				if (i_303_ < vertexSkins.length) {
					int[] is_304_ = vertexSkins[i_303_];
					for (int i_305_ = 0; i_305_ < is_304_.length; i_305_++) {
						int i_306_ = is_304_[i_305_];
						verticesXCoordinate[i_306_] -= vertexModX;
						verticesYCoordinate[i_306_] -= vertexModY;
						verticesZCoordinate[i_306_] -= vertexModZ;
						verticesXCoordinate[i_306_] = (verticesXCoordinate[i_306_]) * verticeX / 128;
						verticesYCoordinate[i_306_] = (verticesYCoordinate[i_306_]) * verticeY / 128;
						verticesZCoordinate[i_306_] = (verticesZCoordinate[i_306_]) * verticeZ / 128;
						verticesXCoordinate[i_306_] += vertexModX;
						verticesYCoordinate[i_306_] += vertexModY;
						verticesZCoordinate[i_306_] += vertexModZ;
					}
				}
			}
		} else if (opcode == 5 && triangleSkins != null && face_alpha != null) {
			for (int skinId = 0; skinId < skinCount; skinId++) {
				int skin = skins[skinId];
				if (skin < triangleSkins.length) {
					int[] tSkins = triangleSkins[skin];
					for (int tSkinId = 0; tSkinId < tSkins.length; tSkinId++) {
						int tSkid = tSkins[tSkinId];
						face_alpha[tSkid] += verticeX * 8;
						if (face_alpha[tSkid] < 0)
							face_alpha[tSkid] = 0;
						if (face_alpha[tSkid] > 255)
							face_alpha[tSkid] = 255;
					}
				}
			}
		}
    }
    
    final void method153(int i) {
    	vertices = null;
    	anInt1962 = 0;
    	int i_312_ = sine[i];
    	int i_313_ = cosine[i];
    	for (int i_314_ = 0; i_314_ < numberOfVerticeCoordinates; i_314_++) {
    		int i_315_ = (((verticesYCoordinate[i_314_] * i_313_) - (verticesZCoordinate[i_314_] * i_312_)) >> 16);
    		verticesZCoordinate[i_314_] = ((verticesYCoordinate[i_314_] * i_312_) + (verticesZCoordinate[i_314_] * i_313_)) >> 16;
			verticesYCoordinate[i_314_] = i_315_;
    	}
    }
    
    public Model(byte[] var1) {
		if (var1[var1.length - 1] == -1 && var1[var1.length - 2] == -1) {
			decode600(var1);
		} else {
			decodeOld(var1);
		}
    }

	public final void decode600(byte[] bs) {
		RSByteBuffer buf1 = new RSByteBuffer(bs);
		RSByteBuffer buf2 = new RSByteBuffer(bs);
		RSByteBuffer buf3 = new RSByteBuffer(bs);
		RSByteBuffer buf4 = new RSByteBuffer(bs);
		RSByteBuffer buf5 = new RSByteBuffer(bs);
		RSByteBuffer buf6 = new RSByteBuffer(bs);
		RSByteBuffer buf7 = new RSByteBuffer(bs);
		buf1.index = bs.length - 23;
		int numVertices = buf1.getUShort();
		int numTriangles = buf1.getUShort();
		int numTextureTriangles = buf1.getUByte();
		int l1 = buf1.getUByte();
		boolean bool = (0x1 & l1) == 1;
		boolean is600Plus = (0x8 & l1) == 8;
		if (!is600Plus) {
			decodeNew(bs);
			return;
		}
		int newformat = 0;
		if (is600Plus) {
			buf1.index -= 7;
			newformat = buf1.getUByte();
			//scaling = (newformat > 13);
			buf1.index += 6;
		}
		int i2 = buf1.getUByte();
		int j2 = buf1.getUByte();
		int k2 = buf1.getUByte();
		int l2 = buf1.getUByte();
		int i3 = buf1.getUByte();
		int j3 = buf1.getUShort();
		int k3 = buf1.getUShort();
		int l3 = buf1.getUShort();
		int i4 = buf1.getUShort();
		int j4 = buf1.getUShort();
		int k4 = 0;
		int l4 = 0;
		int i5 = 0;
		byte[] O = null;
		if (numTextureTriangles > 0) {
			O = new byte[numTextureTriangles];
			buf1.index = 0;
			for (int i_80_ = 0; i_80_ < numTextureTriangles; i_80_++) {
				byte b = O[i_80_] = buf1.getByte();
				if (b == 0) {
					k4++;
				}
				if (b >= 1 && b <= 3) {
					l4++;
				}
				if (b == 2) {
					i5++;
				}
			}
		}
		int k5 = numTextureTriangles;
		int i_82_ = k5;
		k5 += numVertices;
		int i_83_ = k5;
		if (bool) {
			k5 += numTriangles;
		}
		if (l1 == 1) {
			k5 += numTriangles;
		}
		int i_84_ = k5;
		k5 += numTriangles;
		int i_85_ = k5;
		if (i2 == 255) {
			k5 += numTriangles;
		}
		int i_86_ = k5;
		if (k2 == 1) {
			k5 += numTriangles;
		}
		int i_87_ = k5;
		if (i3 == 1) {
			k5 += numVertices;
		}
		int i_88_ = k5;
		if (j2 == 1) {
			k5 += numTriangles;
		}
		int i_89_ = k5;
		k5 += i4;
		int i_90_ = k5;
		if (l2 == 1) {
			k5 += numTriangles * 2;
		}
		int i_91_ = k5;
		k5 += j4;
		int i_92_ = k5;
		k5 += numTriangles * 2;
		int i_93_ = k5;
		k5 += j3;
		int i_94_ = k5;
		k5 += k3;
		int i_95_ = k5;
		k5 += l3;
		int i_96_ = k5;
		k5 += k4 * 6;
		int i_97_ = k5;
		k5 += l4 * 6;
		int i_59_ = 6;
		if (newformat != 14) {
			if (newformat >= 15) {
				i_59_ = 9;
			}
		} else {
			i_59_ = 7;
		}
		int l9 = k5;
		k5 += l4 * i_59_;
		int i_99_ = k5;
		k5 += l4;
		int i_100_ = k5;
		k5 += l4;
		int i_101_ = k5;
		k5 += l4 + i5 * 2;
		numberOfVerticeCoordinates = numVertices;
		numberOfTriangleFaces = numTriangles;
		verticesXCoordinate = new int[numVertices];
		verticesYCoordinate = new int[numVertices];
		verticesZCoordinate = new int[numVertices];
		face_a = new int[numTriangles];
		face_b = new int[numTriangles];
		face_c = new int[numTriangles];//
		short[] D = null;
		byte[] x = null;
		if (i3 == 1) {
			vertexVSkins = new int[numVertices];
		}
		if (bool) {
			face_render_type = new int[numTriangles];
		}
		if (i2 == 255) {
			face_render_priorities = new int[numTriangles];
		}
		if (j2 == 1) {
			face_alpha = new int[numTriangles];
		}
		if (k2 == 1) {
			triangleSkinValues = new int[numTriangles];
		}
		if (l2 == 1) {
			D = new short[numTriangles];
		}
		if (l2 == 1 && numTextureTriangles > 0) {
			x = new byte[numTriangles];
		}
		face_color = new int[numTriangles];
		if (numTextureTriangles > 0) {
			textured_face_a = new int[numTextureTriangles];
			textured_face_b = new int[numTextureTriangles];
			textured_face_c = new int[numTextureTriangles];
		}
		buf1.index = i_82_;
		buf2.index = i_93_;
		buf3.index = i_94_;
		buf4.index = i_95_;
		buf5.index = i_87_;
		int i_102_ = 0;
		int i_103_ = 0;
		int i_104_ = 0;
		for (int i_105_ = 0; i_105_ < numVertices; i_105_++) {
			int i_106_ = buf1.getUByte();
			int i_107_ = 0;
			if ((i_106_ & 0x1) != 0) {
				i_107_ = buf2.getSmart();
			}
			int i_108_ = 0;
			if ((i_106_ & 0x2) != 0) {
				i_108_ = buf3.getSmart();
			}
			int i_109_ = 0;
			if ((i_106_ & 0x4) != 0) {
				i_109_ = buf4.getSmart();
			}
			verticesXCoordinate[i_105_] = i_102_ + i_107_;
			verticesYCoordinate[i_105_] = i_103_ + i_108_;
			verticesZCoordinate[i_105_] = i_104_ + i_109_;
			i_102_ = verticesXCoordinate[i_105_];
			i_103_ = verticesYCoordinate[i_105_];
			i_104_ = verticesZCoordinate[i_105_];
			if (i3 == 1) {
				vertexVSkins[i_105_] = buf5.getUByte();
			}
		}
		buf1.index = i_92_;
		buf2.index = i_83_;
		buf3.index = i_85_;
		buf4.index = i_88_;
		buf5.index = i_86_;
		buf6.index = i_90_;
		buf7.index = i_91_;
		for (int i_110_ = 0; i_110_ < numTriangles; i_110_++) {
			face_color[i_110_] = (short) buf1.getUShort();
			if (l1 == 1) {
				face_render_type[i_110_] = buf2.getByte();
				if (face_render_type[i_110_] == 2) {
					face_color[i_110_] = 65535;
				}
				face_render_type[i_110_] = 0;
			}
			if (i2 == 255) {
				face_render_priorities[i_110_] = buf3.getByte();
			}
			if (j2 == 1) {
				face_alpha[i_110_] = buf4.getByte();
			}
			if (k2 == 1) {
				triangleSkinValues[i_110_] = buf5.getUByte();
			}
			if (l2 == 1) {
				buf6.getUShort();
			}
			if (x != null) {
				if (D[i_110_] != -1) {
					buf7.getUByte();
				}
			}
		}
		buf1.index = i_89_;
		buf2.index = i_84_;
		int i_111_ = 0;
		int i_112_ = 0;
		int i_113_ = 0;
		int i_114_ = 0;
		for (int i_115_ = 0; i_115_ < numTriangles; i_115_++) {
			int i_116_ = buf2.getUByte();
			if (i_116_ == 1) {
				i_111_ = buf1.getSmart() + i_114_;
				i_114_ = i_111_;
				i_112_ = buf1.getSmart() + i_114_;
				i_114_ = i_112_;
				i_113_ = buf1.getSmart() + i_114_;
				i_114_ = i_113_;
				face_a[i_115_] = i_111_;
				face_b[i_115_] = i_112_;
				face_c[i_115_] = i_113_;
			}
			if (i_116_ == 2) {
				i_112_ = i_113_;
				i_113_ = buf1.getSmart() + i_114_;
				i_114_ = i_113_;
				face_a[i_115_] = i_111_;
				face_b[i_115_] = i_112_;
				face_c[i_115_] = i_113_;
			}
			if (i_116_ == 3) {
				i_111_ = i_113_;
				i_113_ = buf1.getSmart() + i_114_;
				i_114_ = i_113_;
				face_a[i_115_] = i_111_;
				face_b[i_115_] = i_112_;
				face_c[i_115_] = i_113_;
			}
			if (i_116_ == 4) {
				int i_117_ = i_111_;
				i_111_ = i_112_;
				i_112_ = i_117_;
				i_113_ = buf1.getSmart() + i_114_;
				i_114_ = i_113_;
				face_a[i_115_] = i_111_;
				face_b[i_115_] = i_112_;
				face_c[i_115_] = i_113_;
			}
		}
		buf1.index = i_96_;
		buf2.index = i_97_;
		buf3.index = l9;
		buf4.index = i_99_;
		buf5.index = i_100_;
		buf6.index = i_101_;
		for (int i_118_ = 0; i_118_ < numTextureTriangles; i_118_++) {
			int i_119_ = O[i_118_] & 0xff;
			if (i_119_ == 0) {
				textured_face_a[i_118_] = (short) buf1.getUShort();
				textured_face_b[i_118_] = (short) buf1.getUShort();
				textured_face_c[i_118_] = (short) buf1.getUShort();
			}
			if (i_119_ == 1) {
				textured_face_a[i_118_] = (short) buf2.getUShort();
				textured_face_b[i_118_] = (short) buf2.getUShort();
				textured_face_c[i_118_] = (short) buf2.getUShort();
				if (newformat < 15) {
					buf3.getUShort();

					if (newformat >= 14) {
						buf3.getTriByte();
					} else {
						buf3.getUShort();
					}

					buf3.getUShort();
				} else {
					buf3.getTriByte();
					buf3.getTriByte();
					buf3.getTriByte();
				}
				buf4.getByte();
				buf5.getByte();
				buf6.getByte();
			}
			if (i_119_ == 2) {
				textured_face_a[i_118_] = (short) buf2.getUShort();
				textured_face_b[i_118_] = (short) buf2.getUShort();
				textured_face_c[i_118_] = (short) buf2.getUShort();
				if (newformat >= 15) {
					buf3.getTriByte();
					buf3.getTriByte();
					buf3.getTriByte();
				} else {
					buf3.getUShort();
					if (newformat < 14) {
						buf3.getUShort();
					} else {
						buf3.getTriByte();
					}
					buf3.getUShort();
				}
				buf4.getByte();
				buf5.getByte();
				buf6.getByte();
				buf6.getByte();
				buf6.getByte();
			}
			if (i_119_ == 3) {
				textured_face_a[i_118_] = (short) buf2.getUShort();
				textured_face_b[i_118_] = (short) buf2.getUShort();
				textured_face_c[i_118_] = (short) buf2.getUShort();
				if (newformat < 15) {
					buf3.getUShort();
					if (newformat < 14) {
						buf3.getUShort();
					} else {
						buf3.getTriByte();
					}
					buf3.getUShort();
				} else {
					buf3.getTriByte();
					buf3.getTriByte();
					buf3.getTriByte();
				}
				buf4.getByte();
				buf5.getByte();
				buf6.getByte();
			}
		}
		if(newformat > 13)
			scale2(32, 32, 32);
		//filterTriangles(modelDef);

	}

	public void scale2(int x, int y, int z) {
		int i1;
		for(i1 = 0; i1 < numberOfVerticeCoordinates; ++i1) {
			verticesXCoordinate[i1] = verticesXCoordinate[i1] * x >> 7;
			verticesYCoordinate[i1] = verticesYCoordinate[i1] * y >> 7;
			verticesZCoordinate[i1] = verticesZCoordinate[i1] * z >> 7;
		}

	}
    
	private final void decodeNew(byte[] var1) {
		RSByteBuffer var2 = new RSByteBuffer(var1);
		RSByteBuffer var3 = new RSByteBuffer(var1);
		RSByteBuffer var4 = new RSByteBuffer(var1);
		RSByteBuffer var5 = new RSByteBuffer(var1);
		RSByteBuffer var6 = new RSByteBuffer(var1);
		RSByteBuffer var7 = new RSByteBuffer(var1);
		RSByteBuffer var8 = new RSByteBuffer(var1);
		var2.index = var1.length - 23;
		int numVertices = var2.getUShort();
		int var10 = var2.getUShort();
		int var11 = var2.getUByte();
		int var12 = var2.getUByte();
		boolean var13 = (var12 & 1) == 1;
		boolean var14 = (var12 & 2) == 2;
		int var15 = var2.getUByte();
		int var16 = var2.getUByte();
		int var17 = var2.getUByte();
		int var18 = var2.getUByte();
		int var19 = var2.getUByte();
		int var20 = var2.getUShort();
		int var21 = var2.getUShort();
		int var22 = var2.getUShort();
		int var23 = var2.getUShort();
		int var24 = var2.getUShort();
		int var25 = 0;
		int var26 = 0;
		int var27 = 0;
		int var28;
		byte[] aByteArray2857 = null;
		if (var11 > 0) {
			aByteArray2857 = new byte[var11];
			var2.index = 0;
			for (var28 = 0; var28 < var11; ++var28) {
				byte var29 = aByteArray2857[var28] = var2.getByte();
				if (var29 == 0) {
					++var25;
				}
				if (var29 >= 1 && var29 <= 3) {
					++var26;
				}
				if (var29 == 2) {
					++var27;
				}
			}
		}
		var28 = var11 + numVertices;
		int var30 = var28;
		if (var13) {
			var28 += var10;
		}

		int var31 = var28;
		var28 += var10;
		int var32 = var28;
		if (var15 == 255) {
			var28 += var10;
		}

		int var33 = var28;
		if (var17 == 1) {
			var28 += var10;
		}

		int var34 = var28;
		if (var19 == 1) {
			var28 += numVertices;
		}

		int var35 = var28;
		if (var16 == 1) {
			var28 += var10;
		}

		int var36 = var28;
		var28 += var23;
		int var37 = var28;
		if (var18 == 1) {
			var28 += var10 * 2;
		}

		int var38 = var28;
		var28 += var24;
		int var39 = var28;
		var28 += var10 * 2;
		int var40 = var28;
		var28 += var20;
		int var41 = var28;
		var28 += var21;
		int var42 = var28;
		var28 += var22;
		int var43 = var28;
		var28 += var25 * 6;
		int var44 = var28;
		var28 += var26 * 6;
		int var45 = var28;
		var28 += var26 * 6;
		int var46 = var28;
		var28 += var26;
		int var47 = var28;
		var28 += var26;
		int var48 = var28;
		var28 += var26 + var27 * 2;
		numberOfVerticeCoordinates = numVertices;
		numberOfTriangleFaces = var10;
		verticesXCoordinate = new int[numVertices];
		verticesYCoordinate = new int[numVertices];
		verticesZCoordinate = new int[numVertices];
		face_a = new int[var10];
		face_b = new int[var10];
		face_c = new int[var10];
		if (var19 == 1) {
			vertexVSkins = new int[numVertices];
		}

		if (var13) {
			face_render_type = new int[var10];
		}

		if (var15 == 255) {
			face_render_priorities = new int[var10];
		}

		if (var16 == 1) {
			face_alpha = new int[var10];
		}

		if (var17 == 1) {
			triangleSkinValues = new int[var10];
		}

		short[] triangleTextureIds = null;

		if (var18 == 1) {
			triangleTextureIds = new short[var10];
		}

		byte[] aByteArray2866 = null;
		if (var18 == 1 && var11 > 0) {
			aByteArray2866 = new byte[var10];
		}

		face_color = new int[var10];
		if (var11 > 0) {
			textured_face_a = new int[var11];
			textured_face_b = new int[var11];
			textured_face_c = new int[var11];
		}

		var2.index = var11;
		var3.index = var40;
		var4.index = var41;
		var5.index = var42;
		var6.index = var34;
		int var50 = 0;
		int var51 = 0;
		int var52 = 0;

		int var55;
		int var54;
		int var53;
		int var57;
		int var56;
		for (var53 = 0; var53 < numVertices; ++var53) {
			var54 = var2.getUByte();
			var55 = 0;
			if ((var54 & 1) != 0) {
				var55 = var3.getSmart();
			}

			var56 = 0;
			if ((var54 & 2) != 0) {
				var56 = var4.getSmart();
			}

			var57 = 0;
			if ((var54 & 4) != 0) {
				var57 = var5.getSmart();
			}

			verticesXCoordinate[var53] = var50 + var55;
			verticesYCoordinate[var53] = var51 + var56;
			verticesZCoordinate[var53] = var52 + var57;
			var50 = verticesXCoordinate[var53];
			var51 = verticesYCoordinate[var53];
			var52 = verticesZCoordinate[var53];
			if (var19 == 1) {
				vertexVSkins[var53] = var6.getUByte();
			}
		}

		var2.index = var39;
		var3.index = var30;
		var4.index = var32;
		var5.index = var35;
		var6.index = var33;
		var7.index = var37;
		var8.index = var38;

		for (var53 = 0; var53 < var10; ++var53) {
			face_color[var53] = (short) var2.getUShort();
			if (var13) {
				face_render_type[var53] = var3.getByte();
				if (face_render_type[var53] == 2) {
					face_color[var53] = 65535;
				}
				face_render_type[var53] = 0;
			}

			if (var15 == 255) {
				face_render_priorities[var53] = var4.getByte();
			}

			if (var16 == 1) {
				face_alpha[var53] = var5.getByte();
			}

			if (var17 == 1) {
				triangleSkinValues[var53] = var6.getUByte();
			}

			if (var18 == 1) {
				triangleTextureIds[var53] = (short) (var7.getUShort() - 1);
			}

			if (aByteArray2866 != null && triangleTextureIds[var53] != -1) {
				aByteArray2866[var53] = (byte) (var8.getUByte() - 1);
			}
		}

		var2.index = var36;
		var3.index = var31;
		var53 = 0;
		var54 = 0;
		var55 = 0;
		var56 = 0;

		int var58;
		for (var57 = 0; var57 < var10; ++var57) {
			var58 = var3.getUByte();
			if (var58 == 1) {
				var53 = var2.getSmart() + var56;
				var54 = var2.getSmart() + var53;
				var55 = var2.getSmart() + var54;
				var56 = var55;
				face_a[var57] = var53;
				face_b[var57] = var54;
				face_c[var57] = var55;
			}

			if (var58 == 2) {
				var54 = var55;
				var55 = var2.getSmart() + var56;
				var56 = var55;
				face_a[var57] = var53;
				face_b[var57] = var54;
				face_c[var57] = var55;
			}

			if (var58 == 3) {
				var53 = var55;
				var55 = var2.getSmart() + var56;
				var56 = var55;
				face_a[var57] = var53;
				face_b[var57] = var54;
				face_c[var57] = var55;
			}

			if (var58 == 4) {
				int var59 = var53;
				var53 = var54;
				var54 = var59;
				var55 = var2.getSmart() + var56;
				var56 = var55;
				face_a[var57] = var53;
				face_b[var57] = var59;
				face_c[var57] = var55;
			}
		}

		var2.index = var43;
		var3.index = var44;
		var4.index = var45;
		var5.index = var46;
		var6.index = var47;
		var7.index = var48;

		for (var57 = 0; var57 < var11; ++var57) {
			var58 = aByteArray2857[var57] & 255;
			if (var58 == 0) {
				textured_face_a[var57] = (short) var2.getUShort();
				textured_face_b[var57] = (short) var2.getUShort();
				textured_face_c[var57] = (short) var2.getUShort();
			}

			if (var58 == 1) {
				textured_face_a[var57] = (short) var3.getUShort();
				textured_face_b[var57] = (short) var3.getUShort();
				textured_face_c[var57] = (short) var3.getUShort();
				var4.getUShort();
				var4.getUShort();
				var4.getUShort();
				var5.getByte();
				var6.getByte();
				var7.getByte();
			}

			if (var58 == 2) {
				textured_face_a[var57] = (short) var3.getUShort();
				textured_face_b[var57] = (short) var3.getUShort();
				textured_face_c[var57] = (short) var3.getUShort();
				var4.getUShort();
				var4.getUShort();
				var4.getUShort();
				var5.getByte();
				var6.getByte();
				var7.getByte();
				var7.getByte();
				var7.getByte();
			}

			if (var58 == 3) {
				textured_face_a[var57] = (short) var3.getUShort();
				textured_face_b[var57] = (short) var3.getUShort();
				textured_face_c[var57] = (short) var3.getUShort();
				var4.getUShort();
				var4.getUShort();
				var4.getUShort();
				var5.getByte();
				var6.getByte();
				var7.getByte();
			}
		}

		if (var14) {
			var2.index = var28;
			var57 = var2.getUByte();
			if (var57 > 0) {
				var2.index += 4 * var57;
			}

			var58 = var2.getUByte();
			if (var58 > 0) {
				var2.index += 4 * var58;
			}
		}

	}
    
    public void decodeOld(byte[] is) {
    	aBoolean1979 = false;
    	numberOfVerticeCoordinates = 0;
    	RSByteBuffer class3_sub12 = new RSByteBuffer(is);
    	RSByteBuffer class3_sub12_316_ = new RSByteBuffer(is);
		RSByteBuffer class3_sub12_317_ = new RSByteBuffer(is);
		RSByteBuffer class3_sub12_318_ = new RSByteBuffer(is);
		RSByteBuffer class3_sub12_319_ = new RSByteBuffer(is);
		class3_sub12.index = is.length - 18;
		int i = class3_sub12.getUShort();
		int i_320_ = class3_sub12.getUShort();
		int texCount = class3_sub12.getUByte();
		int i_322_ = class3_sub12.getUByte();
		int i_323_ = class3_sub12.getUByte();
		int i_324_ = class3_sub12.getUByte();
		int i_325_ = class3_sub12.getUByte();
		int i_326_ = class3_sub12.getUByte();
		int i_327_ = class3_sub12.getUShort();
		int i_328_ = class3_sub12.getUShort();
		int i_329_ = class3_sub12.getUShort();
		int i_330_ = class3_sub12.getUShort();
		int i_331_ = 0;
		int i_332_ = i_331_;
		i_331_ += i;
		int i_333_ = i_331_;
		i_331_ += i_320_;
		int i_334_ = i_331_;
		if (i_323_ == 255)
			i_331_ += i_320_;
		else
			i_334_ = -i_323_ - 1;
		int i_335_ = i_331_;
		if (i_325_ == 1)
		    i_331_ += i_320_;
		else
		    i_335_ = -1;
		int i_336_ = i_331_;
		if (i_322_ == 1)
		    i_331_ += i_320_;
		else
		    i_336_ = -1;
		int i_337_ = i_331_;
		if (i_326_ == 1)
		    i_331_ += i;
		else
		    i_337_ = -1;
		int i_338_ = i_331_;
		if (i_324_ == 1)
		    i_331_ += i_320_;
		else
			i_338_ = -1;
		int i_339_ = i_331_;
		i_331_ += i_330_;
		int i_340_ = i_331_;
		i_331_ += i_320_ * 2;
		int i_341_ = i_331_;
		i_331_ += texCount * 6;
		int i_342_ = i_331_;
		i_331_ += i_327_;
		int i_343_ = i_331_;
		i_331_ += i_328_;
		int i_344_ = i_331_;
		i_331_ += i_329_;
		numberOfVerticeCoordinates = i;
		numberOfTriangleFaces = i_320_;
		texTriangleCount = texCount;
		verticesXCoordinate = new int[i];
		verticesYCoordinate = new int[i];
		verticesZCoordinate = new int[i];
		face_a = new int[i_320_];
		face_b = new int[i_320_];
		face_c = new int[i_320_];
		textured_face_a = new int[texCount];
		textured_face_b = new int[texCount];
		textured_face_c = new int[texCount];
		if (i_337_ >= 0)
		    vertexVSkins = new int[i];
		if (i_336_ >= 0)
		    face_render_type = new int[i_320_];
		if (i_334_ >= 0)
		    face_render_priorities = new int[i_320_];
		else
		    anInt1983 = -i_334_ - 1;
		if (i_338_ >= 0)
		    face_alpha = new int[i_320_];
		if (i_335_ >= 0)
		    triangleSkinValues = new int[i_320_];
		face_color = new int[i_320_];
		class3_sub12.index = i_332_;
		class3_sub12_316_.index = i_342_;
		class3_sub12_317_.index = i_343_;
		class3_sub12_318_.index = i_344_;
		class3_sub12_319_.index = i_337_;
		int i_345_ = 0;
		int i_346_ = 0;
		int i_347_ = 0;
		for (int i_348_ = 0; i_348_ < i; i_348_++) {
		    int i_349_ = class3_sub12.getUByte();
		    int i_350_ = 0;
		    if ((i_349_ & 0x1) != 0)
		    	i_350_ = class3_sub12_316_.getSmart();
		    int i_351_ = 0;
		    if ((i_349_ & 0x2) != 0)
		    	i_351_ = class3_sub12_317_.getSmart();
		    int i_352_ = 0;
		    if ((i_349_ & 0x4) != 0)
		    	i_352_ = class3_sub12_318_.getSmart();
		    verticesXCoordinate[i_348_] = i_345_ + i_350_;
		    verticesYCoordinate[i_348_] = i_346_ + i_351_;
		    verticesZCoordinate[i_348_] = i_347_ + i_352_;
		    i_345_ = verticesXCoordinate[i_348_];
		    i_346_ = verticesYCoordinate[i_348_];
		    i_347_ = verticesZCoordinate[i_348_];
		    if (vertexVSkins != null)
		    	vertexVSkins[i_348_] = class3_sub12_319_.getUByte();
		}
		class3_sub12.index = i_340_;
		class3_sub12_316_.index = i_336_;
		class3_sub12_317_.index = i_334_;
		class3_sub12_318_.index = i_338_;
		class3_sub12_319_.index = i_335_;
		for (int i_353_ = 0; i_353_ < i_320_; i_353_++) {
			face_color[i_353_] = class3_sub12.getUShort();
			if (face_render_type != null)
				face_render_type[i_353_] = class3_sub12_316_.getUByte();
			if (face_render_priorities != null)
				face_render_priorities[i_353_] = class3_sub12_317_.getUByte();
			if (face_alpha != null)
				face_alpha[i_353_] = class3_sub12_318_.getUByte();
			if (triangleSkinValues != null)
				triangleSkinValues[i_353_] = class3_sub12_319_.getUByte();
		}
		class3_sub12.index = i_339_;
		class3_sub12_316_.index = i_333_;
		int i_354_ = 0;
		int i_355_ = 0;
		int i_356_ = 0;
		int i_357_ = 0;
		for (int i_358_ = 0; i_358_ < i_320_; i_358_++) {
			int i_359_ = class3_sub12_316_.getUByte();
		    if (i_359_ == 1) {
		    	i_354_ = class3_sub12.getSmart() + i_357_;
				i_357_ = i_354_;
				i_355_ = class3_sub12.getSmart() + i_357_;
				i_357_ = i_355_;
				i_356_ = class3_sub12.getSmart() + i_357_;
				i_357_ = i_356_;
				face_a[i_358_] = i_354_;
				face_b[i_358_] = i_355_;
				face_c[i_358_] = i_356_;
		    }
		    if (i_359_ == 2) {
				i_355_ = i_356_;
				i_356_ = class3_sub12.getSmart() + i_357_;
				i_357_ = i_356_;
				face_a[i_358_] = i_354_;
				face_b[i_358_] = i_355_;
				face_c[i_358_] = i_356_;
		    }
		    if (i_359_ == 3) {
				i_354_ = i_356_;
				i_356_ = class3_sub12.getSmart() + i_357_;
				i_357_ = i_356_;
				face_a[i_358_] = i_354_;
				face_b[i_358_] = i_355_;
				face_c[i_358_] = i_356_;
		    }
		    if (i_359_ == 4) {
				int i_360_ = i_354_;
				i_354_ = i_355_;
				i_355_ = i_360_;
				i_356_ = class3_sub12.getSmart() + i_357_;
				i_357_ = i_356_;
				face_a[i_358_] = i_354_;
				face_b[i_358_] = i_355_;
				face_c[i_358_] = i_356_;
		    }
		}
		class3_sub12.index = i_341_;
		for (int i_361_ = 0; i_361_ < texCount; i_361_++) {
		    textured_face_a[i_361_] = class3_sub12.getUShort();
		    textured_face_b[i_361_] = class3_sub12.getUShort();
		    textured_face_c[i_361_] = class3_sub12.getUShort();
		}
    }
    
    public Model() {
    	aBoolean1979 = false;
    	numberOfVerticeCoordinates = 0;
    }
    
    Model(Model[] models, int modelCount) {//merges object_model_ids
    	aBoolean1979 = false;
    	numberOfVerticeCoordinates = 0;
    	boolean bool = false;
    	boolean bool_362_ = false;
    	boolean bool_363_ = false;
    	boolean bool_364_ = false;
    	numberOfVerticeCoordinates = 0;
    	numberOfTriangleFaces = 0;
    	texTriangleCount = 0;
    	anInt1983 = -1;
    	for (int id = 0; id < modelCount; id++) {
    		Model model = models[id];
    		if (model != null) {
    			numberOfVerticeCoordinates += model.numberOfVerticeCoordinates;
    			numberOfTriangleFaces += model.numberOfTriangleFaces;
    			texTriangleCount += model.texTriangleCount;
    			bool = (bool | (model.face_render_type) != null);
    			if (model.face_render_priorities != null)
    				bool_362_ = true;
    			else {
    				if (anInt1983 == -1)
    					anInt1983 = model.anInt1983;
    				if (anInt1983 != model.anInt1983)
    					bool_362_ = true;
    			}
    			bool_363_ = (bool_363_ | model.face_alpha != null);
    			bool_364_ = (bool_364_ | model.triangleSkinValues != null);
    		}
    	}
    	verticesXCoordinate = new int[numberOfVerticeCoordinates];
    	verticesYCoordinate = new int[numberOfVerticeCoordinates];
    	verticesZCoordinate = new int[numberOfVerticeCoordinates];
    	vertexVSkins = new int[numberOfVerticeCoordinates];
    	face_a = new int[numberOfTriangleFaces];
    	face_b = new int[numberOfTriangleFaces];
    	face_c = new int[numberOfTriangleFaces];
    	textured_face_a = new int[texTriangleCount];
    	textured_face_b = new int[texTriangleCount];
    	textured_face_c = new int[texTriangleCount];
    	if (bool)
    		face_render_type = new int[numberOfTriangleFaces];
    	if (bool_362_)
    		face_render_priorities = new int[numberOfTriangleFaces];
    	if (bool_363_)
    		face_alpha = new int[numberOfTriangleFaces];
    	if (bool_364_)
    		triangleSkinValues = new int[numberOfTriangleFaces];
    	face_color = new int[numberOfTriangleFaces];
    	numberOfVerticeCoordinates = 0;
    	numberOfTriangleFaces = 0;
    	texTriangleCount = 0;
    	int i_367_ = 0;
    	for (int modelId = 0; modelId < modelCount; modelId++) {
    		Model model = models[modelId];
    		if (model != null) {
    			for (int triangleId = 0; triangleId < model.numberOfTriangleFaces; triangleId++) {
    				if (bool) {
    					if (model.face_render_type == null)
    						face_render_type[numberOfTriangleFaces] = 0;
    					else {
    						int face = model.face_render_type[triangleId];
    						if ((face & 0x2) == 2)
    							face += i_367_ << 2;
    						face_render_type[numberOfTriangleFaces] = face;
    					}
    				}
    				if (bool_362_) {
    					if (model.face_render_priorities == null)
    						face_render_priorities[numberOfTriangleFaces] = model.anInt1983;
    					else
    						face_render_priorities[numberOfTriangleFaces] = model.face_render_priorities[triangleId];
    				}
    				if (bool_363_) {
    					if (model.face_alpha == null)
    						face_alpha[numberOfTriangleFaces] = 0;
    					else
    						face_alpha[numberOfTriangleFaces] = model.face_alpha[triangleId];
    				}
    				if (bool_364_ && model.triangleSkinValues != null)
    					triangleSkinValues[numberOfTriangleFaces] = model.triangleSkinValues[triangleId];
    				face_color[numberOfTriangleFaces] = model.face_color[triangleId];
    				face_a[numberOfTriangleFaces] = method136(model, model.face_a[triangleId]);
    				face_b[numberOfTriangleFaces] = method136(model, model.face_b[triangleId]);
    				face_c[numberOfTriangleFaces] = method136(model, model.face_c[triangleId]);
    				numberOfTriangleFaces++;
    			}
    			for (int triangleId = 0; triangleId < model.texTriangleCount; triangleId++) {
    				textured_face_a[texTriangleCount] = method136(model, model.textured_face_a[triangleId]);
    				textured_face_b[texTriangleCount] = method136(model, model.textured_face_b[triangleId]);
    				textured_face_c[texTriangleCount] = method136(model, model.textured_face_c[triangleId]);
    				texTriangleCount++;
    			}
    			i_367_ += model.texTriangleCount;
    		}
    	}
    }
    
    Model(Model[] models, int i, boolean bool) {
    	aBoolean1979 = false;
    	numberOfVerticeCoordinates = 0;
    	boolean bool_373_ = false;
    	boolean bool_374_ = false;
    	boolean bool_375_ = false;
    	boolean bool_376_ = false;
    	numberOfVerticeCoordinates = 0;
    	numberOfTriangleFaces = 0;
    	texTriangleCount = 0;
		anInt1983 = -1;
		for (int i_377_ = 0; i_377_ < i; i_377_++) {
			Model model = models[i_377_];
			if (model != null) {
				numberOfVerticeCoordinates += model.numberOfVerticeCoordinates;
				numberOfTriangleFaces += model.numberOfTriangleFaces;
				texTriangleCount += model.texTriangleCount;
				bool_373_ = bool_373_ | model.face_render_type != null;
				if (model.face_render_priorities != null)
					bool_374_ = true;
				else {
					if (anInt1983 == -1)
						anInt1983 = model.anInt1983;
					if (anInt1983 != model.anInt1983)
						bool_374_ = true;
				}
				bool_375_ = (bool_375_ | model.face_alpha != null);
				bool_376_ = (bool_376_ | model.face_color != null);
			}
		}
		verticesXCoordinate = new int[numberOfVerticeCoordinates];
		verticesYCoordinate = new int[numberOfVerticeCoordinates];
		verticesZCoordinate = new int[numberOfVerticeCoordinates];
		face_a = new int[numberOfTriangleFaces];
		face_b = new int[numberOfTriangleFaces];
		face_c = new int[numberOfTriangleFaces];
		triangleColorsA = new int[numberOfTriangleFaces];
		triangleColorsB = new int[numberOfTriangleFaces];
		triangleColorsC = new int[numberOfTriangleFaces];
		textured_face_a = new int[texTriangleCount];
		textured_face_b = new int[texTriangleCount];
		textured_face_c = new int[texTriangleCount];
		if (bool_373_)
			face_render_type = new int[numberOfTriangleFaces];
		if (bool_374_)
			face_render_priorities = new int[numberOfTriangleFaces];
		if (bool_375_)
			face_alpha = new int[numberOfTriangleFaces];
		if (bool_376_)
			face_color = new int[numberOfTriangleFaces];
		numberOfVerticeCoordinates = 0;
		numberOfTriangleFaces = 0;
		texTriangleCount = 0;
		int i_379_ = 0;
		for (int i_380_ = 0; i_380_ < i; i_380_++) {
			Model model = models[i_380_];
			if (model != null) {
				int i_382_ = numberOfVerticeCoordinates;
				for (int i_383_ = 0; i_383_ < model.numberOfVerticeCoordinates; i_383_++) {
					verticesXCoordinate[numberOfVerticeCoordinates] = model.verticesXCoordinate[i_383_];
					verticesYCoordinate[numberOfVerticeCoordinates] = model.verticesYCoordinate[i_383_];
					verticesZCoordinate[numberOfVerticeCoordinates] = model.verticesZCoordinate[i_383_];
					numberOfVerticeCoordinates++;
				}
				for (int i_384_ = 0; i_384_ < model.numberOfTriangleFaces; i_384_++) {
					face_a[numberOfTriangleFaces] = model.face_a[i_384_] + i_382_;
					face_b[numberOfTriangleFaces] = model.face_b[i_384_] + i_382_;
					face_c[numberOfTriangleFaces] = model.face_c[i_384_] + i_382_;
					triangleColorsA[numberOfTriangleFaces] = model.triangleColorsA[i_384_];
					triangleColorsB[numberOfTriangleFaces] = model.triangleColorsB[i_384_];
					triangleColorsC[numberOfTriangleFaces] = model.triangleColorsC[i_384_];
					if (bool_373_) {
						if (model.face_render_type == null)
							face_render_type[numberOfTriangleFaces] = 0;
						else {
							int i_385_ = model.face_render_type[i_384_];
							if ((i_385_ & 0x2) == 2)
								i_385_ += i_379_ << 2;
							face_render_type[numberOfTriangleFaces] = i_385_;
						}
					}
					if (bool_374_) {
						if (model.face_render_priorities == null)
							face_render_priorities[numberOfTriangleFaces] = model.anInt1983;
						else
							face_render_priorities[numberOfTriangleFaces] = model.face_render_priorities[i_384_];
					}
					if (bool_375_) {
						if (model.face_alpha == null)
							face_alpha[numberOfTriangleFaces] = 0;
						else
							face_alpha[numberOfTriangleFaces] = model.face_alpha[i_384_];
					}
					if (bool_376_ && model.face_color != null)
						face_color[numberOfTriangleFaces] = model.face_color[i_384_];
					numberOfTriangleFaces++;
				}
				for (int i_386_ = 0; i_386_ < model.texTriangleCount; i_386_++) {
					textured_face_a[texTriangleCount] = (model.textured_face_a[i_386_] + i_382_);
					textured_face_b[texTriangleCount] = (model.textured_face_b[i_386_] + i_382_);
					textured_face_c[texTriangleCount] = (model.textured_face_c[i_386_] + i_382_);
					texTriangleCount++;
				}
				i_379_ += model.texTriangleCount;
		   }
		}
    }
    
    Model(Model model, boolean bool, boolean bool_388_, boolean bool_389_) {
    	aBoolean1979 = false;
    	numberOfVerticeCoordinates = 0;
    	numberOfVerticeCoordinates = model.numberOfVerticeCoordinates;
    	numberOfTriangleFaces = model.numberOfTriangleFaces;
    	texTriangleCount = model.texTriangleCount;
    	if (bool) {
    		verticesXCoordinate = model.verticesXCoordinate;
    		verticesYCoordinate = model.verticesYCoordinate;
    		verticesZCoordinate = model.verticesZCoordinate;
    	} else {
    		verticesXCoordinate = new int[numberOfVerticeCoordinates];
    		verticesYCoordinate = new int[numberOfVerticeCoordinates];
    		verticesZCoordinate = new int[numberOfVerticeCoordinates];
    		for (int vertexId = 0; vertexId < numberOfVerticeCoordinates; vertexId++) {
    			verticesXCoordinate[vertexId] = model.verticesXCoordinate[vertexId];
    			verticesYCoordinate[vertexId] = model.verticesYCoordinate[vertexId];
    			verticesZCoordinate[vertexId] = model.verticesZCoordinate[vertexId];
    		}
    	}
    	if (bool_388_)
    		face_color = model.face_color;
    	else {
    		face_color = new int[numberOfTriangleFaces];
    		for (int i = 0; i < numberOfTriangleFaces; i++)
    			face_color[i] = model.face_color[i];
    	}
		if (bool_389_)
		    face_alpha = model.face_alpha;
		else {
		    face_alpha = new int[numberOfTriangleFaces];
		    if (model.face_alpha == null) {
		    	for (int i = 0; i < numberOfTriangleFaces; i++)
		    		face_alpha[i] = 0;
		    } else {
		    	for (int i = 0; i < numberOfTriangleFaces; i++)
		    		face_alpha[i] = model.face_alpha[i];
		    }
		}
		vertexVSkins = model.vertexVSkins;
		triangleSkinValues = model.triangleSkinValues;
		face_render_type = model.face_render_type;
		face_a = model.face_a;
		face_b = model.face_b;
		face_c = model.face_c;
		face_render_priorities = model.face_render_priorities;
		anInt1983 = model.anInt1983;
		textured_face_a = model.textured_face_a;
		textured_face_b = model.textured_face_b;
		textured_face_c = model.textured_face_c;
    }
    
    Model(Model model, boolean bool, boolean bool_391_) {
    	aBoolean1979 = false;
    	numberOfVerticeCoordinates = 0;
		numberOfVerticeCoordinates = model.numberOfVerticeCoordinates;
		numberOfTriangleFaces = model.numberOfTriangleFaces;
		texTriangleCount = model.texTriangleCount;
		if (bool) {
			verticesYCoordinate = new int[numberOfVerticeCoordinates];
			for (int i = 0; i < numberOfVerticeCoordinates; i++)
				verticesYCoordinate[i] = model.verticesYCoordinate[i];
		} else
			verticesYCoordinate = model.verticesYCoordinate;
		if (bool_391_) {
			triangleColorsA = new int[numberOfTriangleFaces];
			triangleColorsB = new int[numberOfTriangleFaces];
			triangleColorsC = new int[numberOfTriangleFaces];
			for (int i = 0; i < numberOfTriangleFaces; i++) {
				triangleColorsA[i] = model.triangleColorsA[i];
				triangleColorsB[i] = model.triangleColorsB[i];
				triangleColorsC[i] = model.triangleColorsC[i];
			}
			face_render_type = new int[numberOfTriangleFaces];
			if (model.face_render_type == null) {
				for (int i = 0; i < numberOfTriangleFaces; i++)
					face_render_type[i] = 0;
			} else {
				for (int i = 0; i < numberOfTriangleFaces; i++)
					face_render_type[i] = model.face_render_type[i];
			}
			vertices = new Vertex[numberOfVerticeCoordinates];
			for (int i = 0; i < numberOfVerticeCoordinates; i++) {
				Vertex class27 = (vertices[i] = new Vertex());
				Vertex class27_392_ = model.vertices[i];
				class27.anInt485 = class27_392_.anInt485;
				class27.anInt488 = class27_392_.anInt488;
				class27.anInt500 = class27_392_.anInt500;
				class27.anInt494 = class27_392_.anInt494;
			}
			aClass27Array1970 = model.aClass27Array1970;
		} else {
			triangleColorsA = model.triangleColorsA;
			triangleColorsB = model.triangleColorsB;
			triangleColorsC = model.triangleColorsC;
			face_render_type = model.face_render_type;
		}
		verticesXCoordinate = model.verticesXCoordinate;
		verticesZCoordinate = model.verticesZCoordinate;
		face_color = model.face_color;
		face_alpha = model.face_alpha;
		face_render_priorities = model.face_render_priorities;
		anInt1983 = model.anInt1983;
		face_a = model.face_a;
		face_b = model.face_b;
		face_c = model.face_c;
		textured_face_a = model.textured_face_a;
		textured_face_b = model.textured_face_b;
		textured_face_c = model.textured_face_c;
		anInt1987 = model.anInt1987;
    }
}
