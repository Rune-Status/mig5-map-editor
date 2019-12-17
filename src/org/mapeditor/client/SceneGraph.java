
package org.mapeditor.client;

import org.mapeditor.editor.Brush;
import org.mapeditor.editor.Editor;

/* Class64 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public final class SceneGraph {
	
	public static int fowSize = 100;
	public static int tileMouseOverX = -1;
	public static int tileMouseOverY = -1;

	private static int sineYaw;
	private static CullingCluster[][] cullingClusters;
	private static int renderX;
	private static int[] anIntArray1027;
	private static int renderY;
	private static int anInt1029 = 4;
	private static int anInt1030;
	private static int anInt1031;
	static boolean lowMem = true;
	private static int cosinePitch;
	private int interactiveObjectCachePos = 0;
	private static int anInt1035 = 0;
	private static boolean aBoolean1036;
	private static int anInt1037;
	static int tileClickX;
	private static int cosineYaw;
	private int mapSizeZ;
	public GroundTile[][][] groundTiles;
	private int mapSizeY;
	private static int anInt1043;
	private static int sinePitch;
	static int tileClickY;
	private static int[] anIntArray1046;
	private static int anInt1047;
	private static int anInt1048;
	private static int anInt1049;
	private static CullingCluster[] aClass39Array1050;
	private static int anInt1051;
	private static int[] anIntArray1052;
	private int visibleZ = 0;
	private int[][][] heightMap;
	private int mapSizeX;
	private int[][][] anIntArrayArrayArray1056;
	private static int[] cullingClusterPointer;
	private static int[] anIntArray1058;
	private static int anInt1059;
	private static InteractiveObject[] interactiveObjects;
	private static int renderZ;
	private static int anInt1062;
	private static int anInt1063;
	private InteractiveObject[] interactiveObjectCache = new InteractiveObject[5000];
	private static Deque tiles;
	private int[][] anIntArrayArray1066 = { new int[16],
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1 },
			{ 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 },
			{ 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1 },
			{ 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1 } };
	private int[][] anIntArrayArray1067;
	private int anInt1068 = 0;
	private static int anInt1069;
	private static int anInt1070;
	private static int[] anIntArray1071;
	private static int[] anIntArray1072;
	private static int[] anIntArray1073;
	private static boolean[][] aBooleanArrayArray1074;
	private static int[] anIntArray1075;
	private static int anInt1076;
	private static int[] anIntArray1077;
	private static int anInt1078;
	private static int anInt1079;
	private int[] anIntArray1080;
	private static boolean[][][][] aBooleanArrayArrayArrayArray1081;
	private int[] anIntArray1082;
	private static int[] anIntArray1083;
	private static int anInt1084;
	private static int[] anIntArray1085;

	static {
		anInt1030 = 0;
		anIntArray1046 = new int[] { 53, -53, -53, 53 };
		tileClickX = -1;
		aClass39Array1050 = new CullingCluster[500];
		anInt1049 = 0;
		cullingClusters = new CullingCluster[anInt1029][500];
		anIntArray1027 = new int[] { -45, 45, 45, -45 };
		cullingClusterPointer = new int[anInt1029];
		interactiveObjects = new InteractiveObject[100];
		anIntArray1058 = new int[] { 45, 45, -45, -45 };
		anInt1059 = 0;
		tileClickY = -1;
		anInt1051 = 0;
		aBoolean1036 = false;
		anIntArray1052 = new int[] { -53, -53, 53, 53 };
		tiles = new Deque();
		anIntArray1073 = new int[] { 0, 0, 2, 0, 0, 2, 1, 1, 0 };
		anIntArray1075 = new int[] { 19, 55, 38, 155, 255, 110, 137, 205, 76 };
		anIntArray1071 = new int[] { 2, 0, 0, 2, 0, 0, 0, 4, 4 };
		aBooleanArrayArrayArrayArray1081 = new boolean[8][32][(fowSize*2)+1][(fowSize*2)+1];
		anIntArray1072 = new int[] { 160, 192, 80, 96, 0, 144, 80, 48, 160 };
		anIntArray1085 = new int[] { 1, 1, 0, 0, 0, 8, 0, 0, 8 };
		anIntArray1077 = new int[] { 0, 4, 4, 8, 0, 0, 8, 0, 0 };
		anIntArray1083 = new int[] { 76, 8, 137, 4, 0, 1, 38, 2, 19 };
	}

	final void method949(int i, int i_0_, int i_1_, int i_2_) {
		GroundTile groundTile = groundTiles[i][i_0_][i_1_];
		if (groundTile != null)
			groundTiles[i][i_0_][i_1_].anInt1263 = i_2_;
	}

	public static void reset() {
		interactiveObjects = null;
		anIntArray1046 = null;
		anIntArray1052 = null;
		anIntArray1027 = null;
		anIntArray1058 = null;
		cullingClusterPointer = null;
		cullingClusters = null;
		aClass39Array1050 = null;
		tiles = null;
		anIntArray1075 = null;
		anIntArray1072 = null;
		anIntArray1083 = null;
		anIntArray1073 = null;
		anIntArray1071 = null;
		anIntArray1077 = null;
		anIntArray1085 = null;
		aBooleanArrayArrayArrayArray1081 = null;
		aBooleanArrayArray1074 = null;
	}

	private final boolean method951(int i, int i_3_, int i_4_, int i_5_) {
		if (!method953(i, i_3_, i_4_))
			return false;
		int i_6_ = i_3_ << 7;
		int i_7_ = i_4_ << 7;
		int i_8_ = heightMap[i][i_3_][i_4_] - 1;
		int i_9_ = i_8_ - 120;
		int i_10_ = i_8_ - 230;
		int i_11_ = i_8_ - 238;
		if (i_5_ < 16) {
			if (i_5_ == 1) {
				if (i_6_ > renderX) {
					if (!method995(i_6_, i_8_, i_7_))
						return false;
					if (!method995(i_6_, i_8_, i_7_ + 128))
						return false;
				}
				if (i > 0) {
					if (!method995(i_6_, i_9_, i_7_))
						return false;
					if (!method995(i_6_, i_9_, i_7_ + 128))
						return false;
				}
				if (!method995(i_6_, i_10_, i_7_))
					return false;
				if (!method995(i_6_, i_10_, i_7_ + 128))
					return false;
				return true;
			}
			if (i_5_ == 2) {
				if (i_7_ < renderY) {
					if (!method995(i_6_, i_8_, i_7_ + 128))
						return false;
					if (!method995(i_6_ + 128, i_8_, i_7_ + 128))
						return false;
				}
				if (i > 0) {
					if (!method995(i_6_, i_9_, i_7_ + 128))
						return false;
					if (!method995(i_6_ + 128, i_9_, i_7_ + 128))
						return false;
				}
				if (!method995(i_6_, i_10_, i_7_ + 128))
					return false;
				if (!method995(i_6_ + 128, i_10_, i_7_ + 128))
					return false;
				return true;
			}
			if (i_5_ == 4) {
				if (i_6_ < renderX) {
					if (!method995(i_6_ + 128, i_8_, i_7_))
						return false;
					if (!method995(i_6_ + 128, i_8_, i_7_ + 128))
						return false;
				}
				if (i > 0) {
					if (!method995(i_6_ + 128, i_9_, i_7_))
						return false;
					if (!method995(i_6_ + 128, i_9_, i_7_ + 128))
						return false;
				}
				if (!method995(i_6_ + 128, i_10_, i_7_))
					return false;
				if (!method995(i_6_ + 128, i_10_, i_7_ + 128))
					return false;
				return true;
			}
			if (i_5_ == 8) {
				if (i_7_ > renderY) {
					if (!method995(i_6_, i_8_, i_7_))
						return false;
					if (!method995(i_6_ + 128, i_8_, i_7_))
						return false;
				}
				if (i > 0) {
					if (!method995(i_6_, i_9_, i_7_))
						return false;
					if (!method995(i_6_ + 128, i_9_, i_7_))
						return false;
				}
				if (!method995(i_6_, i_10_, i_7_))
					return false;
				if (!method995(i_6_ + 128, i_10_, i_7_))
					return false;
				return true;
			}
		}
		if (!method995(i_6_ + 64, i_11_, i_7_ + 64))
			return false;
		if (i_5_ == 16) {
			if (!method995(i_6_, i_10_, i_7_ + 128))
				return false;
			return true;
		}
		if (i_5_ == 32) {
			if (!method995(i_6_ + 128, i_10_, i_7_ + 128))
				return false;
			return true;
		}
		if (i_5_ == 64) {
			if (!method995(i_6_ + 128, i_10_, i_7_))
				return false;
			return true;
		}
		if (i_5_ == 128) {
			if (!method995(i_6_, i_10_, i_7_))
				return false;
			return true;
		}
		return true;
	}

	final void drawMinimapPixels(int[] pixels, int offset, int z, int x, int y) {
		GroundTile groundTile = groundTiles[z][x][y];
		if (groundTile != null) {
			DecoratedTile tile = groundTile.decoratedTile;
			if (tile != null) {
				int color = tile.minimapColor;
				if (color != 0) {
					for (int loops = 0; loops < 4; loops++) {
						pixels[offset] = color;
						pixels[offset + 1] = color;
						pixels[offset + 2] = color;
						pixels[offset + 3] = color;
						offset += 512;
					}
				}
			} else {
				CompoundTile class38 = groundTile.shapedTile;
				if (class38 != null) {
					int i_18_ = class38.shape;
					int i_19_ = class38.rotation;
					int color2 = class38.anInt665;
					int color = class38.anInt654;
					int[] is_22_ = anIntArrayArray1066[i_18_];
					int[] is_23_ = anIntArrayArray1067[i_19_];
					int i_24_ = 0;
					if (color2 != 0) {
						for (int i_25_ = 0; i_25_ < 4; i_25_++) {
							pixels[offset] = is_22_[is_23_[i_24_++]] == 0 ? color2 : color;
							pixels[offset + 1] = is_22_[is_23_[i_24_++]] == 0 ? color2 : color;
							pixels[offset + 2] = is_22_[is_23_[i_24_++]] == 0 ? color2 : color;
							pixels[offset + 3] = is_22_[is_23_[i_24_++]] == 0 ? color2 : color;
							offset += 512;
						}
					} else {
						for (int i_26_ = 0; i_26_ < 4; i_26_++) {
							if (is_22_[is_23_[i_24_++]] != 0)
								pixels[offset] = color;
							if (is_22_[is_23_[i_24_++]] != 0)
								pixels[offset + 1] = color;
							if (is_22_[is_23_[i_24_++]] != 0)
								pixels[offset + 2] = color;
							if (is_22_[is_23_[i_24_++]] != 0)
								pixels[offset + 3] = color;
							offset += 512;
						}
					}
				}
			}
		}
	}

	private final boolean method953(int localZ, int localX, int localY) {
		int i_29_ = anIntArrayArrayArray1056[localZ][localX][localY];
		if (i_29_ == -anInt1037)
			return false;
		if (i_29_ == anInt1037)
			return true;
		int absX = localX << 7;
		int absY = localY << 7;
		if (method995(absX + 1, heightMap[localZ][localX][localY], absY + 1)
				&& method995(absX + 128 - 1,
						heightMap[localZ][localX + 1][localY], absY + 1)
				&& method995(absX + 128 - 1,
						heightMap[localZ][localX + 1][localY + 1],
						absY + 128 - 1)
				&& method995(absX + 1, heightMap[localZ][localX][localY + 1],
						absY + 128 - 1)) {
			anIntArrayArrayArray1056[localZ][localX][localY] = anInt1037;
			return true;
		}
		anIntArrayArrayArray1056[localZ][localX][localY] = -anInt1037;
		return false;
	}

	final int getWallDecorationHash(int x, int y, int z) {
		GroundTile groundTile = groundTiles[z][x][y];
		if (groundTile == null || groundTile.wallDecoration == null)
			return 0;
		return groundTile.wallDecoration.hash;
	}

	private final boolean method955(int x, int y, int z, int i_36_) {
		if (!method953(z, x, y))
			return false;
		int i_37_ = x << 7;
		int i_38_ = y << 7;
		if (method995(i_37_ + 1, heightMap[z][x][y] - i_36_, i_38_ + 1)
				&& method995(i_37_ + 128 - 1, heightMap[z][x + 1][y] - i_36_,
						i_38_ + 1)
				&& method995(i_37_ + 128 - 1,
						(heightMap[z][x + 1][y + 1] - i_36_), i_38_ + 128 - 1)
				&& method995(i_37_ + 1, heightMap[z][x][y + 1] - i_36_,
						i_38_ + 128 - 1))
			return true;
		return false;
	}

	private final void method956(Model class3_sub3_sub3_sub3, int i, int i_39_,
			int i_40_, int i_41_, int i_42_) {
		boolean bool = true;
		int i_43_ = i_39_;
		int i_44_ = i_39_ + i_41_;
		int i_45_ = i_40_ - 1;
		int i_46_ = i_40_ + i_42_;
		for (int i_47_ = i; i_47_ <= i + 1; i_47_++) {
			if (i_47_ != mapSizeZ) {
				for (int i_48_ = i_43_; i_48_ <= i_44_; i_48_++) {
					if (i_48_ >= 0 && i_48_ < mapSizeX) {
						for (int i_49_ = i_45_; i_49_ <= i_46_; i_49_++) {
							if (i_49_ >= 0
									&& i_49_ < mapSizeY
									&& (!bool || i_48_ >= i_44_
											|| i_49_ >= i_46_ || i_49_ < i_40_
											&& i_48_ != i_39_)) {
								GroundTile groundTile = (groundTiles[i_47_][i_48_][i_49_]);
								if (groundTile != null) {
									int i_50_ = (((heightMap[i_47_][i_48_][i_49_])
											+ (heightMap[i_47_][i_48_ + 1][i_49_])
											+ (heightMap[i_47_][i_48_][i_49_ + 1]) + (heightMap[i_47_][i_48_ + 1][i_49_ + 1])) / 4 - ((heightMap[i][i_39_][i_40_])
											+ (heightMap[i][i_39_ + 1][i_40_])
											+ (heightMap[i][i_39_][i_40_ + 1]) + (heightMap[i][i_39_ + 1][i_40_ + 1])) / 4);
									WallObject class47 = (groundTile.wallObject);
									if (class47 != null) {
										if ((class47.aClass3_Sub3_Sub3_807) instanceof Model) {
											Model class3_sub3_sub3_sub3_51_ = ((Model) (class47.aClass3_Sub3_Sub3_807));
											if ((class3_sub3_sub3_sub3_51_.vertices) != null)
												method970(
														class3_sub3_sub3_sub3,
														class3_sub3_sub3_sub3_51_,
														((i_48_ - i_39_) * 128 + (1 - i_41_) * 64),
														i_50_,
														((i_49_ - i_40_) * 128 + (1 - i_42_) * 64),
														bool);
										}
										if ((class47.aClass3_Sub3_Sub3_812) instanceof Model) {
											Model class3_sub3_sub3_sub3_52_ = ((Model) (class47.aClass3_Sub3_Sub3_812));
											if (class3_sub3_sub3_sub3_52_.vertices != null)
												method970(
														class3_sub3_sub3_sub3,
														class3_sub3_sub3_sub3_52_,
														((i_48_ - i_39_) * 128 + (1 - i_41_) * 64),
														i_50_,
														((i_49_ - i_40_) * 128 + (1 - i_42_) * 64),
														bool);
										}
									}
									for (int i_53_ = 0; i_53_ < (groundTile.interactiveObjectCount); i_53_++) {
										InteractiveObject class61 = (groundTile.interactiveObjects[i_53_]);
										if (class61 != null
												&& ((class61.sceneModel) instanceof Model)) {
											Model class3_sub3_sub3_sub3_54_ = ((Model) (class61.sceneModel));
											if ((class3_sub3_sub3_sub3_54_.vertices) != null) {
												int i_55_ = ((class61.anInt1001)
														- (class61.x) + 1);
												int i_56_ = ((class61.anInt998)
														- (class61.y) + 1);
												method970(
														class3_sub3_sub3_sub3,
														class3_sub3_sub3_sub3_54_,
														(((class61.x) - i_39_) * 128 + (i_55_ - i_41_) * 64),
														i_50_,
														(((class61.y) - i_40_) * 128 + (i_56_ - i_42_) * 64),
														bool);
											}
										}
									}
								}
							}
						}
					}
				}
				i_43_--;
				bool = false;
			}
		}
	}

	final GroundDecoration getGroundDecoration(int x, int y, int z) {
		GroundTile groundTile = groundTiles[z][x][y];
		if (groundTile == null || groundTile.groundDecoration == null)
			return null;
		return groundTile.groundDecoration;
	}

	static final void method958(int[] is, int i, int i_59_, int i_60_, int i_61_) {
		anInt1079 = 0;
		anInt1076 = 0;
		anInt1084 = i_60_;
		anInt1070 = i_61_;
		anInt1078 = i_60_ / 2;
		anInt1069 = i_61_ / 2;
		boolean[][][][] bools = new boolean[9][32][((fowSize+2)*2)-1][((fowSize+2)*2)-1];
		for (int i_62_ = 128; i_62_ <= 384; i_62_ += 32) {
			for (int i_63_ = 0; i_63_ < 2048; i_63_ += 64) {
				sinePitch = Model.sine[i_62_];
				cosinePitch = Model.cosine[i_62_];
				sineYaw = Model.sine[i_63_];
				cosineYaw = Model.cosine[i_63_];
				int i_64_ = (i_62_ - 128) / 32;
				int i_65_ = i_63_ / 64;
				for (int i_66_ = -26; i_66_ <= 26; i_66_++) {
					for (int i_67_ = -26; i_67_ <= 26; i_67_++) {
						int i_68_ = i_66_ * 128;
						int i_69_ = i_67_ * 128;
						boolean bool = false;
						for (int i_70_ = -i; i_70_ <= i_59_; i_70_ += 128) {
							if (method959(i_68_, is[i_64_] + i_70_, i_69_)) {
								bool = true;
								break;
							}
						}
						bools[i_64_][i_65_][i_66_ + fowSize + 1][i_67_ + fowSize + 1] = bool;
					}
				}
			}
		}
		for (int i_71_ = 0; i_71_ < 8; i_71_++) {
			for (int i_72_ = 0; i_72_ < 32; i_72_++) {
				for (int i_73_ = -fowSize; i_73_ < fowSize; i_73_++) {
					for (int i_74_ = -fowSize; i_74_ < fowSize; i_74_++) {
						boolean bool = false;
						while_9_: for (int i_75_ = -1; i_75_ <= 1; i_75_++) {
							for (int i_76_ = -1; i_76_ <= 1; i_76_++) {
								if (bools[i_71_][i_72_][i_73_ + i_75_ + fowSize + 1][i_74_
										+ i_76_ + fowSize + 1]) {
									bool = true;
									break while_9_;
								}
								if (bools[i_71_][(i_72_ + 1) % 31][i_73_
										+ i_75_ + fowSize + 1][i_74_ + i_76_ + fowSize
										+ 1]) {
									bool = true;
									break while_9_;
								}
								if (bools[i_71_ + 1][i_72_][i_73_ + i_75_ + fowSize
										+ 1][i_74_ + i_76_ + fowSize + 1]) {
									bool = true;
									break while_9_;
								}
								if (bools[i_71_ + 1][(i_72_ + 1) % 31][i_73_
										+ i_75_ + fowSize + 1][i_74_ + i_76_ + fowSize
										+ 1]) {
									bool = true;
									break while_9_;
								}
							}
						}
						aBooleanArrayArrayArrayArray1081[i_71_][i_72_][i_73_ + fowSize][i_74_ + fowSize] = bool;
					}
				}
			}
		}
	}

	private static final boolean method959(int i, int i_77_, int i_78_) {
		int i_79_ = i_78_ * sineYaw + i * cosineYaw >> 16;
		int i_80_ = i_78_ * cosineYaw - i * sineYaw >> 16;
		int i_81_ = i_77_ * sinePitch + i_80_ * cosinePitch >> 16;
		int i_82_ = i_77_ * cosinePitch - i_80_ * sinePitch >> 16;
		if (i_81_ < 50 || i_81_ > 3500)
			return false;
		int i_83_ = anInt1078 + (i_79_ << 9) / i_81_;
		int i_84_ = anInt1069 + (i_82_ << 9) / i_81_;
		if (i_83_ < anInt1079 || i_83_ > anInt1084 || i_84_ < anInt1076
				|| i_84_ > anInt1070)
			return false;
		return true;
	}

	private static final int method960(int i, int i_85_) {
		if(!Editor.disableTileShadow) {
			i_85_ = (127 - i_85_) * (i & 0x7f) >> 7;
			if (i_85_ < 2)
				i_85_ = 2;
			else if (i_85_ > 126)
				i_85_ = 126;
			return (i & 0xff80) + i_85_;
		} else {
			return i;
		}
	}

	final void resetWallDecoration(int x, int y, int z) {
		GroundTile groundTile = groundTiles[z][x][y];
		if (groundTile != null)
			groundTile.wallDecoration = null;
	}

	final boolean render(int height, int x, int y, int tileHeight, int i_91_,
			SceneModel class3_sub3_sub3, int direction, int index, boolean bool) {
		if (class3_sub3_sub3 == null)
			return true;
		//i_91_ = offset?
		int i_94_ = x - i_91_;
		int i_95_ = y - i_91_;
		int i_96_ = x + i_91_;
		int i_97_ = y + i_91_;
		if (bool) {
			if (direction > 640 && direction < 1408) {
				i_97_ += 128;
			}
			if (direction > 1152 && direction < 1920) {
				i_96_ += 128;
			}
			if (direction > 1664 || direction < 384) {
				i_95_ -= 128;
			}
			if (direction > 128 && direction < 896) {
				i_94_ -= 128;
			}
		}
		i_94_ /= 128;
		i_95_ /= 128;
		i_96_ /= 128;
		i_97_ /= 128;
		return addInteractiveObject(height, i_94_, i_95_, i_96_ - i_94_ + 1, i_97_ - i_95_ + 1,
				x, y, tileHeight, class3_sub3_sub3, direction, true, index, 0);
	}

	final void method963(int camX, int camZ, int camY, int pitch, int yaw,
			int height) {
		if (camX < 0)
			camX = 0;
		else if (camX >= mapSizeX * 128)
			camX = mapSizeX * 128 - 1;
		if (camY < 0)
			camY = 0;
		else if (camY >= mapSizeY * 128)
			camY = mapSizeY * 128 - 1;
		anInt1037++;
		sinePitch = Model.sine[pitch];
		cosinePitch = Model.cosine[pitch];
		sineYaw = Model.sine[yaw];
		cosineYaw = Model.cosine[yaw];
		//aBooleanArrayArray1074 = (aBooleanArrayArrayArrayArray1081[(pitch - 128) / 32][yaw / 64]);
		renderX = camX;
		renderZ = camZ;
		renderY = camY;
		anInt1047 = camX / 128;
		anInt1063 = camY / 128;
		anInt1051 = height;
		anInt1048 = anInt1047 - fowSize;
		if (anInt1048 < 0)
			anInt1048 = 0;
		anInt1062 = anInt1063 - fowSize;
		if (anInt1062 < 0)
			anInt1062 = 0;
		anInt1031 = anInt1047 + fowSize;
		if (anInt1031 > mapSizeX)
			anInt1031 = mapSizeX;
		anInt1043 = anInt1063 + fowSize;
		if (anInt1043 > mapSizeY)
			anInt1043 = mapSizeY;
		//method980();
		anInt1059 = 0;
		for (int z = visibleZ; z < mapSizeZ; z++) {
			GroundTile[][] class3_sub11s = groundTiles[z];
			for (int i_104_ = anInt1048; i_104_ < anInt1031; i_104_++) {
				for (int i_105_ = anInt1062; i_105_ < anInt1043; i_105_++) {
					GroundTile groundTile = class3_sub11s[i_104_][i_105_];
					if (groundTile != null) {
						if (groundTile.anInt1263 > height) {
							groundTile.aBoolean1260 = false;
							groundTile.aBoolean1266 = false;
							groundTile.anInt1288 = 0;
						} else {
							//System.out.println("true");
							groundTile.aBoolean1260 = true;
							groundTile.aBoolean1266 = true;
							if (groundTile.interactiveObjectCount > 0)
								groundTile.aBoolean1261 = true;
							else
								groundTile.aBoolean1261 = false;
							anInt1059++;
						}
					}
				}
			}
		}
		for (int i_106_ = visibleZ; i_106_ < mapSizeZ; i_106_++) {
			GroundTile[][] class3_sub11s = groundTiles[i_106_];
			for (int i_107_ = -fowSize; i_107_ <= 0; i_107_++) {
				int i_108_ = anInt1047 + i_107_;
				int i_109_ = anInt1047 - i_107_;
				if (i_108_ >= anInt1048 || i_109_ < anInt1031) {
					for (int i_110_ = -fowSize; i_110_ <= 0; i_110_++) {
						int i_111_ = anInt1063 + i_110_;
						int i_112_ = anInt1063 - i_110_;
						if (i_108_ >= anInt1048) {
							if (i_111_ >= anInt1062) {
								GroundTile groundTile = class3_sub11s[i_108_][i_111_];
								if (groundTile != null
										&& (groundTile.aBoolean1260)) {
									method967(groundTile, true);
								}
							}
							if (i_112_ < anInt1043) {
								GroundTile groundTile = class3_sub11s[i_108_][i_112_];
								if (groundTile != null
										&& (groundTile.aBoolean1260))
									method967(groundTile, true);
							}
						}
						if (i_109_ < anInt1031) {
							if (i_111_ >= anInt1062) {
								GroundTile groundTile = class3_sub11s[i_109_][i_111_];
								if (groundTile != null
										&& (groundTile.aBoolean1260))
									method967(groundTile, true);
							}
							if (i_112_ < anInt1043) {
								GroundTile groundTile = class3_sub11s[i_109_][i_112_];
								if (groundTile != null
										&& (groundTile.aBoolean1260))
									method967(groundTile, true);
							}
						}
						if (anInt1059 == 0) {
							aBoolean1036 = false; //TODO:
							return;
						}
					}
				}
			}
		}
		for (int i_113_ = visibleZ; i_113_ < mapSizeZ; i_113_++) {
			GroundTile[][] class3_sub11s = groundTiles[i_113_];
			for (int i_114_ = -fowSize; i_114_ <= 0; i_114_++) {
				int i_115_ = anInt1047 + i_114_;
				int i_116_ = anInt1047 - i_114_;
				if (i_115_ >= anInt1048 || i_116_ < anInt1031) {
					for (int i_117_ = -fowSize; i_117_ <= 0; i_117_++) {
						int i_118_ = anInt1063 + i_117_;
						int i_119_ = anInt1063 - i_117_;
						if (i_115_ >= anInt1048) {
							if (i_118_ >= anInt1062) {
								GroundTile groundTile = class3_sub11s[i_115_][i_118_];
								if (groundTile != null
										&& (groundTile.aBoolean1260))
									method967(groundTile, false);
							}
							if (i_119_ < anInt1043) {
								GroundTile groundTile = class3_sub11s[i_115_][i_119_];
								if (groundTile != null
										&& (groundTile.aBoolean1260))
									method967(groundTile, false);
							}
						}
						if (i_116_ < anInt1031) {
							if (i_118_ >= anInt1062) {
								GroundTile groundTile = class3_sub11s[i_116_][i_118_];
								if (groundTile != null
										&& (groundTile.aBoolean1260))
									method967(groundTile, false);
							}
							if (i_119_ < anInt1043) {
								GroundTile groundTile = class3_sub11s[i_116_][i_119_];
								if (groundTile != null
										&& (groundTile.aBoolean1260))
									method967(groundTile, false);
							}
						}
						if (anInt1059 == 0) {
							aBoolean1036 = false;
							return;
						}
					}
				}
			}
		}
		aBoolean1036 = false;
	}

	static final void method964(int i, int i_120_, int i_121_, int i_122_,
			int i_123_, int i_124_, int i_125_, int i_126_) {
		CullingCluster class39 = new CullingCluster();
		class39.anInt691 = i_121_ / 128;
		class39.anInt687 = i_122_ / 128;
		class39.anInt678 = i_123_ / 128;
		class39.anInt677 = i_124_ / 128;
		class39.anInt698 = i_120_;
		class39.anInt699 = i_121_;
		class39.anInt674 = i_122_;
		class39.anInt673 = i_123_;
		class39.anInt676 = i_124_;
		class39.anInt694 = i_125_;
		class39.anInt681 = i_126_;
		cullingClusters[i][cullingClusterPointer[i]++] = class39;
	}

	final WallObject getWallObject(int x, int y, int z) {
		GroundTile groundTile = groundTiles[z][x][y];
		if (groundTile == null)
			return null;
		return groundTile.wallObject;
	}

	final boolean addInteractiveObject(int i, int i_129_, int i_130_, int i_131_,
			int i_132_, int i_133_, SceneModel class3_sub3_sub3, int i_134_,
			int i_135_, int i_136_) {
		if (class3_sub3_sub3 == null)
			return true;
		int i_137_ = i_129_ * 128 + i_132_ * 64;
		int i_138_ = i_130_ * 128 + i_133_ * 64;
		return addInteractiveObject(i, i_129_, i_130_, i_132_, i_133_, i_137_, i_138_,
				i_131_, class3_sub3_sub3, i_134_, false, i_135_, i_136_);
	}

	private final void method967(GroundTile groundTile, boolean bool) {
		tiles.insertBack(groundTile);
		while_12_: for (;;) {
			GroundTile class3_sub11_139_ = (GroundTile) tiles
					.popFront();
			if (class3_sub11_139_ == null) {
				break;
			}
			if (class3_sub11_139_.aBoolean1266) {
				int i = class3_sub11_139_.x;
				int i_140_ = class3_sub11_139_.y;
				int i_141_ = class3_sub11_139_.z;
				int i_142_ = class3_sub11_139_.anInt1269;
				GroundTile[][] class3_sub11s = groundTiles[i_141_];
				if (class3_sub11_139_.aBoolean1260) {
					if (bool) {
						if (i_141_ > 0) {
							GroundTile class3_sub11_143_ = (groundTiles[i_141_ - 1][i][i_140_]);
							if (class3_sub11_143_ != null
									&& (class3_sub11_143_.aBoolean1266)) {
								continue;
							}
						}
						if (i <= anInt1047 && i > anInt1048) {
							GroundTile class3_sub11_144_ = class3_sub11s[i - 1][i_140_];
							if (class3_sub11_144_ != null
									&& (class3_sub11_144_.aBoolean1266)
									&& ((class3_sub11_144_.aBoolean1260) || ((class3_sub11_139_.anInt1286) & 0x1) == 0))
								continue;
						}
						if (i >= anInt1047 && i < anInt1031 - 1) {
							GroundTile class3_sub11_145_ = class3_sub11s[i + 1][i_140_];
							if (class3_sub11_145_ != null
									&& (class3_sub11_145_.aBoolean1266)
									&& ((class3_sub11_145_.aBoolean1260) || ((class3_sub11_139_.anInt1286) & 0x4) == 0))
								continue;
						}
						if (i_140_ <= anInt1063 && i_140_ > anInt1062) {
							GroundTile class3_sub11_146_ = class3_sub11s[i][i_140_ - 1];
							if (class3_sub11_146_ != null
									&& (class3_sub11_146_.aBoolean1266)
									&& ((class3_sub11_146_.aBoolean1260) || ((class3_sub11_139_.anInt1286) & 0x8) == 0))
								continue;
						}
						if (i_140_ >= anInt1063 && i_140_ < anInt1043 - 1) {
							GroundTile class3_sub11_147_ = class3_sub11s[i][i_140_ + 1];
							if (class3_sub11_147_ != null
									&& (class3_sub11_147_.aBoolean1266)
									&& ((class3_sub11_147_.aBoolean1260) || ((class3_sub11_139_.anInt1286) & 0x2) == 0))
								continue;
						}
					} else
						bool = true;
					class3_sub11_139_.aBoolean1260 = false;
					if (class3_sub11_139_.bridgeTile != null) {
						GroundTile class3_sub11_148_ = class3_sub11_139_.bridgeTile;
						if (class3_sub11_148_.decoratedTile != null) {
							if (!method953(0, i, i_140_)) {
								int size = Brush.size;
								boolean ignore = false;
								for(int sizeX = 0; sizeX < size; sizeX++) {
									for(int sizeY = 0; sizeY < size; sizeY++) {
										ignore = ((i == tileMouseOverX + sizeX) && (i_140_ == tileMouseOverY - sizeY)) || ((i == tileMouseOverX + sizeX) && (i_140_ == tileMouseOverY)) || ((i == tileMouseOverX) && (i_140_ == tileMouseOverY - sizeY));
									}
								}
								method983(
										class3_sub11_148_.decoratedTile,
										0, sinePitch, cosinePitch, sineYaw,
										cosineYaw, i, i_140_, ignore);
							}
						} else if (class3_sub11_148_.shapedTile != null
								&& !method953(0, i, i_140_))
							method986(
									class3_sub11_148_.shapedTile,
									sinePitch, cosinePitch, sineYaw, cosineYaw,
									i, i_140_, false);
						WallObject class47 = class3_sub11_148_.wallObject;
						if (class47 != null && !Editor.disableObjects)
							class47.aClass3_Sub3_Sub3_807
									.render(0, sinePitch, cosinePitch, sineYaw,
											cosineYaw,
											class47.renderX
													- renderX,
											class47.renderZ
													- renderZ,
											class47.renderY
													- renderY,
											class47.hash);
						for (int i_149_ = 0; i_149_ < class3_sub11_148_.interactiveObjectCount; i_149_++) {
							InteractiveObject class61 = class3_sub11_148_.interactiveObjects[i_149_];
							if (class61 != null && !Editor.disableObjects)
								class61.sceneModel
										.render(class61.direction,
												sinePitch,
												cosinePitch,
												sineYaw,
												cosineYaw,
												class61.renderX
														- renderX,
												class61.renderZ
														- renderZ,
												class61.renderY
														- renderY,
												class61.hash);
						}
					}
					boolean renderGrounDecoration = false;
					//System.out.println(class3_sub11_139_.decoratedTile == null);
					if (class3_sub11_139_.decoratedTile != null) {
						if (!method953(i_142_, i, i_140_)) {
							renderGrounDecoration = true;
							int size = Brush.size;
							boolean ignore = false;
							for(int sizeX = 0; sizeX < size; sizeX++) {
								for(int sizeY = 0; sizeY < size; sizeY++) {
									ignore = ((i == tileMouseOverX + sizeX) && (i_140_ == tileMouseOverY - sizeY)) || ((i == tileMouseOverX + sizeX) && (i_140_ == tileMouseOverY)) || ((i == tileMouseOverX) && (i_140_ == tileMouseOverY - sizeY));
								}
							}
							//System.out.println(ignore+":"+tileMouseOverX+":"+tileMouseOverY);
							method983(
									(class3_sub11_139_.decoratedTile),
									i_142_, sinePitch, cosinePitch, sineYaw,
									cosineYaw, i, i_140_, ignore);
						}
					} else if ((class3_sub11_139_.shapedTile) != null
							&& !method953(i_142_, i, i_140_)) {
						renderGrounDecoration = true;
						method986(
								(class3_sub11_139_.shapedTile),
								sinePitch, cosinePitch, sineYaw, cosineYaw, i,
								i_140_, false);
					}
					int i_151_ = 0;
					int i_152_ = 0;
					WallObject class47 = class3_sub11_139_.wallObject;
					WallDecoration class62 = class3_sub11_139_.wallDecoration;
					if (class47 != null || class62 != null) {
						if (anInt1047 == i)
							i_151_++;
						else if (anInt1047 < i)
							i_151_ += 2;
						if (anInt1063 == i_140_)
							i_151_ += 3;
						else if (anInt1063 > i_140_)
							i_151_ += 6;
						i_152_ = anIntArray1075[i_151_];
						class3_sub11_139_.anInt1272 = anIntArray1083[i_151_];
					}
					if (class47 != null) {
						if ((class47.renderPriority & anIntArray1072[i_151_]) != 0) {
							if (class47.renderPriority == 16) {
								class3_sub11_139_.anInt1288 = 3;
								class3_sub11_139_.anInt1280 = anIntArray1073[i_151_];
								class3_sub11_139_.anInt1268 = 3 - (class3_sub11_139_.anInt1280);
							} else if (class47.renderPriority == 32) {
								class3_sub11_139_.anInt1288 = 6;
								class3_sub11_139_.anInt1280 = anIntArray1071[i_151_];
								class3_sub11_139_.anInt1268 = 6 - (class3_sub11_139_.anInt1280);
							} else if (class47.renderPriority == 64) {
								class3_sub11_139_.anInt1288 = 12;
								class3_sub11_139_.anInt1280 = anIntArray1077[i_151_];
								class3_sub11_139_.anInt1268 = 12 - (class3_sub11_139_.anInt1280);
							} else {
								class3_sub11_139_.anInt1288 = 9;
								class3_sub11_139_.anInt1280 = anIntArray1085[i_151_];
								class3_sub11_139_.anInt1268 = 9 - (class3_sub11_139_.anInt1280);
							}
						} else
							class3_sub11_139_.anInt1288 = 0;
						if ((class47.renderPriority & i_152_) != 0 && !method951(i_142_, i, i_140_, class47.renderPriority) && !Editor.disableObjects)
							class47.aClass3_Sub3_Sub3_807
									.render(0, sinePitch, cosinePitch, sineYaw,
											cosineYaw,
											class47.renderX
													- renderX,
											class47.renderZ
													- renderZ,
											class47.renderY
													- renderY,
											class47.hash);
						if ((class47.anInt810 & i_152_) != 0
								&& !method951(i_142_, i, i_140_,
										class47.anInt810) && !Editor.disableObjects)
							class47.aClass3_Sub3_Sub3_812
									.render(0, sinePitch, cosinePitch, sineYaw,
											cosineYaw,
											class47.renderX
													- renderX,
											class47.renderZ
													- renderZ,
											class47.renderY
													- renderY,
											class47.hash);
					}
					if (class62 != null && !method955(i, i_140_, i_142_, class62.sceneModel.height)) {
						if ((class62.anInt1014 & i_152_) != 0 && !Editor.disableObjects)
							class62.sceneModel.render(class62.rotation,
											sinePitch,
											cosinePitch,
											sineYaw,
											cosineYaw,
											class62.renderX
													- renderX,
											class62.renderZ
													- renderZ,
											class62.renderY
													- renderY,
											class62.hash);
						else if ((class62.anInt1014 & 0x300) != 0) {
							int i_153_ = class62.renderX
									- renderX;
							int i_154_ = class62.renderZ
									- renderZ;
							int i_155_ = class62.renderY
									- renderY;
							int i_156_ = class62.rotation;
							int i_157_;
							if (i_156_ == 1 || i_156_ == 2)
								i_157_ = -i_153_;
							else
								i_157_ = i_153_;
							int i_158_;
							if (i_156_ == 2 || i_156_ == 3)
								i_158_ = -i_155_;
							else
								i_158_ = i_155_;
							if ((class62.anInt1014 & 0x100) != 0
									&& i_158_ < i_157_ && !Editor.disableObjects) {
								int i_159_ = i_153_ + anIntArray1046[i_156_];
								int i_160_ = i_155_ + anIntArray1052[i_156_];
								class62.sceneModel
										.render(i_156_ * 512 + 256, sinePitch,
												cosinePitch, sineYaw,
												cosineYaw, i_159_, i_154_,
												i_160_,
												class62.hash);
							}
							if ((class62.anInt1014 & 0x200) != 0
									&& i_158_ > i_157_ && !Editor.disableObjects) {
								int i_161_ = i_153_ + anIntArray1027[i_156_];
								int i_162_ = i_155_ + anIntArray1058[i_156_];
								class62.sceneModel
										.render(i_156_ * 512 + 1280 & 0x7ff,
												sinePitch, cosinePitch,
												sineYaw, cosineYaw, i_161_,
												i_154_, i_162_,
												class62.hash);
							}
						}
					}
					if (renderGrounDecoration) {
						GroundDecoration class36 = class3_sub11_139_.groundDecoration;
						if (class36 != null && !Editor.disableObjects)
							class36.sceneModel
									.render(0,
											sinePitch,
											cosinePitch,
											sineYaw,
											cosineYaw,
											class36.renderX
													- renderX,
											class36.renderZ
													- renderZ,
											class36.renderY
													- renderY,
											class36.hash);
					}
					int i_163_ = class3_sub11_139_.anInt1286;
					if (i_163_ != 0) {
						if (i < anInt1047 && (i_163_ & 0x4) != 0) {
							GroundTile class3_sub11_164_ = class3_sub11s[i + 1][i_140_];
							if (class3_sub11_164_ != null
									&& (class3_sub11_164_.aBoolean1266))
								tiles.insertBack(class3_sub11_164_);
						}
						if (i_140_ < anInt1063 && (i_163_ & 0x2) != 0) {
							GroundTile class3_sub11_165_ = class3_sub11s[i][i_140_ + 1];
							if (class3_sub11_165_ != null
									&& (class3_sub11_165_.aBoolean1266))
								tiles.insertBack(class3_sub11_165_);
						}
						if (i > anInt1047 && (i_163_ & 0x1) != 0) {
							GroundTile class3_sub11_166_ = class3_sub11s[i - 1][i_140_];
							if (class3_sub11_166_ != null
									&& (class3_sub11_166_.aBoolean1266))
								tiles.insertBack(class3_sub11_166_);
						}
						if (i_140_ > anInt1063 && (i_163_ & 0x8) != 0) {
							GroundTile class3_sub11_167_ = class3_sub11s[i][i_140_ - 1];
							if (class3_sub11_167_ != null
									&& (class3_sub11_167_.aBoolean1266))
								tiles.insertBack(class3_sub11_167_);
						}
					}
				}
				if (class3_sub11_139_.anInt1288 != 0) {
					boolean bool_168_ = true;
					for (int i_169_ = 0; i_169_ < class3_sub11_139_.interactiveObjectCount; i_169_++) {
						if ((class3_sub11_139_.interactiveObjects[i_169_].anInt1000 != anInt1037)
								&& (((class3_sub11_139_.anIntArray1274[i_169_]) & (class3_sub11_139_.anInt1288)) == (class3_sub11_139_.anInt1280))) {
							bool_168_ = false;
							break;
						}
					}
					if (bool_168_) {
						WallObject class47 = class3_sub11_139_.wallObject;
						if (!method951(i_142_, i, i_140_,
								class47.renderPriority) && !Editor.disableObjects)
							class47.aClass3_Sub3_Sub3_807
									.render(0, sinePitch, cosinePitch, sineYaw,
											cosineYaw,
											class47.renderX
													- renderX,
											class47.renderZ
													- renderZ,
											class47.renderY
													- renderY,
											class47.hash);
						class3_sub11_139_.anInt1288 = 0;
					}
				}
				do {
					if (class3_sub11_139_.aBoolean1261) {
						try {
							int i_170_ = class3_sub11_139_.interactiveObjectCount;
							class3_sub11_139_.aBoolean1261 = false;
							int i_171_ = 0;
							while_11_: for (int i_172_ = 0; i_172_ < i_170_; i_172_++) {
								InteractiveObject class61 = (class3_sub11_139_.interactiveObjects[i_172_]);
								if (class61.anInt1000 != anInt1037) {
									for (int i_173_ = class61.x; (i_173_ <= class61.anInt1001); i_173_++) {
										for (int i_174_ = (class61.y); (i_174_ <= class61.anInt998); i_174_++) {
											GroundTile class3_sub11_175_ = (class3_sub11s[i_173_][i_174_]);
											if (class3_sub11_175_.aBoolean1260) {
												class3_sub11_139_.aBoolean1261 = true;
												continue while_11_;
											}
											if (class3_sub11_175_.anInt1288 != 0) {
												int i_176_ = 0;
												if (i_173_ > (class61.x))
													i_176_++;
												if (i_173_ < (class61.anInt1001))
													i_176_ += 4;
												if (i_174_ > (class61.y))
													i_176_ += 8;
												if (i_174_ < (class61.anInt998))
													i_176_ += 2;
												if ((i_176_ & (class3_sub11_175_.anInt1288)) == (class3_sub11_139_.anInt1268)) {
													class3_sub11_139_.aBoolean1261 = true;
													continue while_11_;
												}
											}
										}
									}
									interactiveObjects[i_171_++] = class61;
									int i_177_ = (anInt1047 - class61.x);
									int i_178_ = (class61.anInt1001 - anInt1047);
									if (i_178_ > i_177_)
										i_177_ = i_178_;
									int i_179_ = (anInt1063 - class61.y);
									int i_180_ = (class61.anInt998 - anInt1063);
									if (i_180_ > i_179_)
										class61.anInt994 = i_177_
												+ i_180_;
									else
										class61.anInt994 = i_177_
												+ i_179_;
								}
							}
							while (i_171_ > 0) {
								int i_181_ = -50;
								int i_182_ = -1;
								for (int i_183_ = 0; i_183_ < i_171_; i_183_++) {
									InteractiveObject class61 = interactiveObjects[i_183_];
									if (class61.anInt1000 != anInt1037) {
										if (class61.anInt994 > i_181_) {
											i_181_ = class61.anInt994;
											i_182_ = i_183_;
										} else if (class61.anInt994 == i_181_) {
											int i_184_ = (class61.renderX - renderX);
											int i_185_ = (class61.renderY - renderY);
											int i_186_ = ((interactiveObjects[i_182_].renderX) - renderX);
											int i_187_ = ((interactiveObjects[i_182_].renderY) - renderY);
											if ((i_184_ * i_184_ + i_185_
													* i_185_) > (i_186_
													* i_186_ + i_187_ * i_187_))
												i_182_ = i_183_;
										}
									}
								}
								if (i_182_ == -1)
									break;
								InteractiveObject class61 = interactiveObjects[i_182_];
								class61.anInt1000 = anInt1037;
								if (!method999(
										i_142_,
										class61.x,
										class61.anInt1001,
										class61.y,
										class61.anInt998,
										class61.sceneModel.height) && !Editor.disableObjects)
									class61.sceneModel
											.render(class61.direction,
													sinePitch,
													cosinePitch,
													sineYaw,
													cosineYaw,
													(class61.renderX - renderX),
													(class61.renderZ - renderZ),
													(class61.renderY - renderY),
													class61.hash);
								for (int i_188_ = class61.x; i_188_ <= class61.anInt1001; i_188_++) {
									for (int i_189_ = class61.y; (i_189_ <= class61.anInt998); i_189_++) {
										GroundTile class3_sub11_190_ = class3_sub11s[i_188_][i_189_];
										if (class3_sub11_190_.anInt1288 != 0)
											tiles
													.insertBack(class3_sub11_190_);
										else if ((i_188_ != i || i_189_ != i_140_)
												&& (class3_sub11_190_.aBoolean1266))
											tiles
													.insertBack(class3_sub11_190_);
									}
								}
							}
							if (!class3_sub11_139_.aBoolean1261)
								break;
						} catch (Exception exception) {
							class3_sub11_139_.aBoolean1261 = false;
							break;
						}
						continue while_12_;
					}
				} while (false);
				if (class3_sub11_139_.aBoolean1266
						&& class3_sub11_139_.anInt1288 == 0) {
					if (i <= anInt1047 && i > anInt1048) {
						GroundTile class3_sub11_191_ = class3_sub11s[i - 1][i_140_];
						if (class3_sub11_191_ != null
								&& class3_sub11_191_.aBoolean1266)
							continue;
					}
					if (i >= anInt1047 && i < anInt1031 - 1) {
						GroundTile class3_sub11_192_ = class3_sub11s[i + 1][i_140_];
						if (class3_sub11_192_ != null
								&& class3_sub11_192_.aBoolean1266)
							continue;
					}
					if (i_140_ <= anInt1063 && i_140_ > anInt1062) {
						GroundTile class3_sub11_193_ = class3_sub11s[i][i_140_ - 1];
						if (class3_sub11_193_ != null
								&& class3_sub11_193_.aBoolean1266)
							continue;
					}
					if (i_140_ >= anInt1063 && i_140_ < anInt1043 - 1) {
						GroundTile class3_sub11_194_ = class3_sub11s[i][i_140_ + 1];
						if (class3_sub11_194_ != null
								&& class3_sub11_194_.aBoolean1266)
							continue;
					}
					class3_sub11_139_.aBoolean1266 = false;
					anInt1059--;
					if (class3_sub11_139_.anInt1272 != 0) {
						WallDecoration class62 = class3_sub11_139_.wallDecoration;
						if (class62 != null
								&& !method955(
										i,
										i_140_,
										i_142_,
										class62.sceneModel.height)) {
							if ((class62.anInt1014 & (class3_sub11_139_.anInt1272)) != 0 && !Editor.disableObjects)
								class62.sceneModel
										.render(class62.rotation,
												sinePitch,
												cosinePitch,
												sineYaw,
												cosineYaw,
												class62.renderX
														- renderX,
												class62.renderZ
														- renderZ,
												class62.renderY
														- renderY,
												class62.hash);
							else if ((class62.anInt1014 & 0x300) != 0) {
								int i_195_ = (class62.renderX - renderX);
								int i_196_ = (class62.renderZ - renderZ);
								int i_197_ = (class62.renderY - renderY);
								int i_198_ = class62.rotation;
								int i_199_;
								if (i_198_ == 1 || i_198_ == 2)
									i_199_ = -i_195_;
								else
									i_199_ = i_195_;
								int i_200_;
								if (i_198_ == 2 || i_198_ == 3)
									i_200_ = -i_197_;
								else
									i_200_ = i_197_;
								if (((class62.anInt1014 & 0x100) != 0)
										&& i_200_ >= i_199_ && !Editor.disableObjects) {
									int i_201_ = i_195_
											+ anIntArray1046[i_198_];
									int i_202_ = i_197_
											+ anIntArray1052[i_198_];
									class62.sceneModel
											.render(i_198_ * 512 + 256,
													sinePitch,
													cosinePitch,
													sineYaw,
													cosineYaw,
													i_201_,
													i_196_,
													i_202_,
													class62.hash);
								}
								if (((class62.anInt1014 & 0x200) != 0)
										&& i_200_ <= i_199_ && !Editor.disableObjects) {
									int i_203_ = i_195_
											+ anIntArray1027[i_198_];
									int i_204_ = i_197_
											+ anIntArray1058[i_198_];
									class62.sceneModel
											.render(i_198_ * 512 + 1280 & 0x7ff,
													sinePitch,
													cosinePitch,
													sineYaw,
													cosineYaw,
													i_203_,
													i_196_,
													i_204_,
													class62.hash);
								}
							}
						}
						WallObject class47 = class3_sub11_139_.wallObject;
						if (class47 != null) {
							if ((class47.anInt810 & (class3_sub11_139_.anInt1272)) != 0
									&& !method951(i_142_, i, i_140_,
											class47.anInt810) && !Editor.disableObjects)
								class47.aClass3_Sub3_Sub3_812
										.render(0, sinePitch, cosinePitch,
												sineYaw, cosineYaw,
												class47.renderX
														- renderX,
												class47.renderZ
														- renderZ,
												class47.renderY
														- renderY,
												class47.hash);
							if ((class47.renderPriority & (class3_sub11_139_.anInt1272)) != 0
									&& !method951(i_142_, i, i_140_,
											class47.renderPriority) && !Editor.disableObjects)
								class47.aClass3_Sub3_Sub3_807
										.render(0, sinePitch, cosinePitch,
												sineYaw, cosineYaw,
												class47.renderX
														- renderX,
												class47.renderZ
														- renderZ,
												class47.renderY
														- renderY,
												class47.hash);
						}
					}
					if (i_141_ < mapSizeZ - 1) {
						GroundTile class3_sub11_205_ = (groundTiles[i_141_ + 1][i][i_140_]);
						if (class3_sub11_205_ != null
								&& class3_sub11_205_.aBoolean1266)
							tiles.insertBack(class3_sub11_205_);
					}
					if (i < anInt1047) {
						GroundTile class3_sub11_206_ = class3_sub11s[i + 1][i_140_];
						if (class3_sub11_206_ != null
								&& class3_sub11_206_.aBoolean1266)
							tiles.insertBack(class3_sub11_206_);
					}
					if (i_140_ < anInt1063) {
						GroundTile class3_sub11_207_ = class3_sub11s[i][i_140_ + 1];
						if (class3_sub11_207_ != null
								&& class3_sub11_207_.aBoolean1266)
							tiles.insertBack(class3_sub11_207_);
					}
					if (i > anInt1047) {
						GroundTile class3_sub11_208_ = class3_sub11s[i - 1][i_140_];
						if (class3_sub11_208_ != null
								&& class3_sub11_208_.aBoolean1266)
							tiles.insertBack(class3_sub11_208_);
					}
					if (i_140_ > anInt1063) {
						GroundTile class3_sub11_209_ = class3_sub11s[i][i_140_ - 1];
						if (class3_sub11_209_ != null
								&& class3_sub11_209_.aBoolean1266)
							tiles.insertBack(class3_sub11_209_);
					}
				}
			}
		}
	}

	final WallDecoration getWallDecoration(int x, int y, int z) {
		GroundTile groundTile = groundTiles[z][x][y];
		if (groundTile == null)
			return null;
		return groundTile.wallDecoration;
	}

	private final void method970(Model class3_sub3_sub3_sub3,
			Model class3_sub3_sub3_sub3_220_, int i, int i_221_, int i_222_,
			boolean bool) {
		class3_sub3_sub3_sub3_220_.method138();
		anInt1068++;
		int i_223_ = 0;
		int[] is = (class3_sub3_sub3_sub3_220_.verticesXCoordinate);
		int i_224_ = class3_sub3_sub3_sub3_220_.numberOfVerticeCoordinates;
		for (int i_225_ = 0; (i_225_ < class3_sub3_sub3_sub3.numberOfVerticeCoordinates); i_225_++) {
			Vertex class27 = (class3_sub3_sub3_sub3.vertices[i_225_]);
			Vertex class27_226_ = (class3_sub3_sub3_sub3.aClass27Array1970[i_225_]);
			if (class27_226_.anInt494 != 0) {
				int i_227_ = ((class3_sub3_sub3_sub3.verticesYCoordinate[i_225_]) - i_221_);
				if (i_227_ <= (class3_sub3_sub3_sub3_220_.anInt1994)) {
					int i_228_ = ((class3_sub3_sub3_sub3.verticesXCoordinate[i_225_]) - i);
					if (i_228_ >= class3_sub3_sub3_sub3_220_.diagonalLine
							&& i_228_ <= class3_sub3_sub3_sub3_220_.anInt1981) {
						int i_229_ = ((class3_sub3_sub3_sub3.verticesZCoordinate[i_225_]) - i_222_);
						if (i_229_ >= class3_sub3_sub3_sub3_220_.anInt1988
								&& i_229_ <= (class3_sub3_sub3_sub3_220_.anInt1960)) {
							for (int i_230_ = 0; i_230_ < i_224_; i_230_++) {
								Vertex class27_231_ = (class3_sub3_sub3_sub3_220_.vertices[i_230_]);
								Vertex class27_232_ = (class3_sub3_sub3_sub3_220_.aClass27Array1970[i_230_]);
								if (i_228_ == is[i_230_]
										&& i_229_ == (class3_sub3_sub3_sub3_220_.verticesZCoordinate[i_230_])
										&& i_227_ == (class3_sub3_sub3_sub3_220_.verticesYCoordinate[i_230_])
										&& (class27_232_.anInt494 != 0)) {
									class27.anInt485 += class27_232_.anInt485;
									class27.anInt488 += class27_232_.anInt488;
									class27.anInt500 += class27_232_.anInt500;
									class27.anInt494 += class27_232_.anInt494;
									class27_231_.anInt485 += class27_226_.anInt485;
									class27_231_.anInt488 += class27_226_.anInt488;
									class27_231_.anInt500 += class27_226_.anInt500;
									class27_231_.anInt494 += class27_226_.anInt494;
									i_223_++;
									anIntArray1080[i_225_] = anInt1068;
									anIntArray1082[i_230_] = anInt1068;
								}
							}
						}
					}
				}
			}
		}
		if (i_223_ >= 3 && bool) {
			for (int i_233_ = 0; (i_233_ < class3_sub3_sub3_sub3.numberOfTriangleFaces); i_233_++) {
				if ((anIntArray1080[(class3_sub3_sub3_sub3.face_a[i_233_])]) == anInt1068
						&& (anIntArray1080[(class3_sub3_sub3_sub3.face_b[i_233_])]) == anInt1068
						&& (anIntArray1080[(class3_sub3_sub3_sub3.face_c[i_233_])]) == anInt1068)
					class3_sub3_sub3_sub3.face_render_type[i_233_] = -1;
			}
			for (int i_234_ = 0; i_234_ < (class3_sub3_sub3_sub3_220_.numberOfTriangleFaces); i_234_++) {
				if ((anIntArray1082[(class3_sub3_sub3_sub3_220_.face_a[i_234_])]) == anInt1068
						&& (anIntArray1082[(class3_sub3_sub3_sub3_220_.face_b[i_234_])]) == anInt1068
						&& (anIntArray1082[(class3_sub3_sub3_sub3_220_.face_c[i_234_])]) == anInt1068)
					class3_sub3_sub3_sub3_220_.face_render_type[i_234_] = -1;
			}
		}
	}

	final int getWallObjectHash(int x, int y, int z) {
		GroundTile groundTile = groundTiles[z][x][y];
		if (groundTile == null || groundTile.wallObject == null)
			return 0;
		return groundTile.wallObject.hash;
	}

	final void resetWallObject(int x, int y, int z) {
		GroundTile groundTile = groundTiles[z][x][y];
		if (groundTile != null)
			groundTile.wallObject = null;
	}

	final int getUID(int z, int x, int y, int hash) {
		GroundTile groundTile = groundTiles[z][x][y];
		if (groundTile == null)
			return -1;
		if (groundTile.wallObject != null && groundTile.wallObject.hash == hash)
			return groundTile.wallObject.uid & 0xff;
		if (groundTile.wallDecoration != null
				&& groundTile.wallDecoration.hash == hash)
			return groundTile.wallDecoration.uid & 0xff;
		if (groundTile.groundDecoration != null
				&& groundTile.groundDecoration.hash == hash)
			return groundTile.groundDecoration.uid & 0xff;
		for (int i_242_ = 0; i_242_ < groundTile.interactiveObjectCount; i_242_++) {
			if (groundTile.interactiveObjects[i_242_].hash == hash)
				return groundTile.interactiveObjects[i_242_].uid & 0xff;
		}
		return -1;
	}

	final boolean method974(int i, int i_243_, int i_244_, int i_245_,
			int i_246_, SceneModel class3_sub3_sub3, int i_247_, int i_248_,
			int i_249_, int i_250_, int i_251_, int i_252_) {
		if (class3_sub3_sub3 == null)
			return true;
		return addInteractiveObject(i, i_249_, i_250_, i_251_ - i_249_ + 1, i_252_
				- i_250_ + 1, i_243_, i_244_, i_245_, class3_sub3_sub3, i_247_,
				true, i_248_, 0);
	}

	final void addWallObject(int z, int x, int y, int i_255_,
			SceneModel class3_sub3_sub3, SceneModel class3_sub3_sub3_256_,
			int i_257_, int i_258_, int i_259_, int i_260_) {
		if (class3_sub3_sub3 != null || class3_sub3_sub3_256_ != null) {
			WallObject class47 = new WallObject();
			class47.hash = i_259_;
			class47.uid = i_260_;
			class47.renderX = x * 128 + 64;
			class47.renderY = y * 128 + 64;
			class47.renderZ = i_255_;
			class47.aClass3_Sub3_Sub3_807 = class3_sub3_sub3;
			class47.aClass3_Sub3_Sub3_812 = class3_sub3_sub3_256_;
			class47.renderPriority = i_257_;
			class47.anInt810 = i_258_;
			for (int z_ = z; z_ >= 0; z_--) {
				if (groundTiles[z_][x][y] == null)
					groundTiles[z_][x][y] = new GroundTile(
							z_, x, y);
			}
			groundTiles[z][x][y].wallObject = class47;
		}
	}

	final void resetGroundDecoration(int x, int y, int z) {
		GroundTile groundTile = groundTiles[z][x][y];
		if (groundTile != null)
			groundTile.groundDecoration = null;
	}

	final void resetInteractiveObject() {
		for (int i = 0; i < interactiveObjectCachePos; i++) {
			InteractiveObject class61 = interactiveObjectCache[i];
			method984(class61);
			interactiveObjectCache[i] = null;
		}
		interactiveObjectCachePos = 0;
	}

	final void method978(int i, int i_264_) {
		GroundTile groundTile = groundTiles[0][i][i_264_];
		for (int i_265_ = 0; i_265_ < 3; i_265_++) {
			GroundTile class3_sub11_266_ = (groundTiles[i_265_][i][i_264_] = groundTiles[i_265_ + 1][i][i_264_]);
			if (class3_sub11_266_ != null) {
				class3_sub11_266_.z--;
				for (int i_267_ = 0; i_267_ < class3_sub11_266_.interactiveObjectCount; i_267_++) {
					InteractiveObject class61 = (class3_sub11_266_.interactiveObjects[i_267_]);
					if ((class61.hash >> 29 & 0x3) == 2
							&& class61.x == i
							&& class61.y == i_264_)
						class61.anInt987--;
				}
			}
		}
		if (groundTiles[0][i][i_264_] == null)
			groundTiles[0][i][i_264_] = new GroundTile(0, i, i_264_);
		groundTiles[0][i][i_264_].bridgeTile = groundTile;
		groundTiles[3][i][i_264_] = null;
	}

	final void method979(int x, int y, int z) {
		GroundTile groundTile = groundTiles[z][x][y];
		if (groundTile != null) {
			for (int id = 0; id < groundTile.interactiveObjectCount; id++) {
				InteractiveObject class61 = groundTile.interactiveObjects[id];
				if ((class61.hash >> 29 & 0x3) == 3 && class61.x == x
						&& class61.y == y) {
					method984(class61);
					break;
				}
			}
		}
	}

	private final void method980() {
		int i = cullingClusterPointer[anInt1051];
		CullingCluster[] class39s = cullingClusters[anInt1051];
		anInt1035 = 0;
		for (int i_271_ = 0; i_271_ < i; i_271_++) {
			CullingCluster class39 = class39s[i_271_];
			if (class39.anInt698 == 1) {
				int i_272_ = class39.anInt691 - anInt1047 + fowSize;
				if (i_272_ >= 0 && i_272_ <= 50) {
					int i_273_ = class39.anInt678 - anInt1063 + fowSize;
					if (i_273_ < 0)
						i_273_ = 0;
					int i_274_ = class39.anInt677 - anInt1063 + fowSize;
					if (i_274_ > 50)
						i_274_ = 50;
					boolean bool = true;
					if (bool) {
						int i_275_ = renderX - class39.anInt699;
						if (i_275_ > 32)
							class39.anInt685 = 1;
						else {
							if (i_275_ >= -32)
								continue;
							class39.anInt685 = 2;
							i_275_ = -i_275_;
						}
						class39.anInt675 = ((class39.anInt673
								- renderY << 8) / i_275_);
						class39.anInt695 = ((class39.anInt676
								- renderY << 8) / i_275_);
						class39.anInt688 = ((class39.anInt694
								- renderZ << 8) / i_275_);
						class39.anInt696 = ((class39.anInt681
								- renderZ << 8) / i_275_);
						aClass39Array1050[anInt1035++] = class39;
					}
				}
			} else if (class39.anInt698 == 2) {
				int i_276_ = class39.anInt678 - anInt1063 + fowSize;
				if (i_276_ >= 0 && i_276_ <= 50) {
					int i_277_ = class39.anInt691 - anInt1047 + fowSize;
					if (i_277_ < 0)
						i_277_ = 0;
					int i_278_ = class39.anInt687 - anInt1047 + fowSize;
					if (i_278_ > 50)
						i_278_ = 50;
					boolean bool = true;
					if (bool) {
						int i_279_ = renderY - class39.anInt673;
						if (i_279_ > 32)
							class39.anInt685 = 3;
						else {
							if (i_279_ >= -32)
								continue;
							class39.anInt685 = 4;
							i_279_ = -i_279_;
						}
						class39.anInt693 = ((class39.anInt699
								- renderX << 8) / i_279_);
						class39.anInt683 = ((class39.anInt674
								- renderX << 8) / i_279_);
						class39.anInt688 = ((class39.anInt694
								- renderZ << 8) / i_279_);
						class39.anInt696 = ((class39.anInt681
								- renderZ << 8) / i_279_);
						aClass39Array1050[anInt1035++] = class39;
					}
				}
			} else if (class39.anInt698 == 4) {
				int i_280_ = class39.anInt694 - renderZ;
				if (i_280_ > 128) {
					int i_281_ = class39.anInt678 - anInt1063 + fowSize;
					if (i_281_ < 0)
						i_281_ = 0;
					int i_282_ = class39.anInt677 - anInt1063 + fowSize;
					if (i_282_ > 50)
						i_282_ = 50;
					if (i_281_ <= i_282_) {
						int i_283_ = class39.anInt691 - anInt1047
								+ fowSize;
						if (i_283_ < 0)
							i_283_ = 0;
						int i_284_ = class39.anInt687 - anInt1047
								+ fowSize;
						if (i_284_ > 50)
							i_284_ = 50;
						boolean bool = true;
						if (bool) {
							class39.anInt685 = 5;
							class39.anInt693 = (class39.anInt699
									- renderX << 8)
									/ i_280_;
							class39.anInt683 = (class39.anInt674
									- renderX << 8)
									/ i_280_;
							class39.anInt675 = (class39.anInt673
									- renderY << 8)
									/ i_280_;
							class39.anInt695 = (class39.anInt676
									- renderY << 8)
									/ i_280_;
							aClass39Array1050[anInt1035++] = class39;
						}
					}
				}
			}
		}
	}

	final void addGroundDecoration(int z, int x, int y, int i_289_,
			SceneModel sceneModel, int hash, int uid) {
		if (sceneModel != null) {
			GroundDecoration class36 = new GroundDecoration();
			class36.sceneModel = sceneModel;
			class36.renderX = x * 128 + 64;
			class36.renderY = y * 128 + 64;
			class36.renderZ = i_289_;
			class36.hash = hash;
			class36.uid = uid;
			if (groundTiles[z][x][y] == null)
				groundTiles[z][x][y] = new GroundTile(z, x, y);
			groundTiles[z][x][y].groundDecoration = class36;
		}
	}

	private final boolean addInteractiveObject(int i, int x, int y, int i_294_,
			int i_295_, int i_296_, int i_297_, int i_298_,
			SceneModel class3_sub3_sub3, int i_299_, boolean cache, int i_300_,
			int i_301_) {
		for (int i_302_ = x; i_302_ < x + i_294_; i_302_++) {
			for (int i_303_ = y; i_303_ < y + i_295_; i_303_++) {
				if (i_302_ < 0 || i_303_ < 0 || i_302_ >= mapSizeX
						|| i_303_ >= mapSizeY)
					return false;
				GroundTile groundTile = groundTiles[i][i_302_][i_303_];
				if (groundTile != null && groundTile.interactiveObjectCount >= 5)
					return false;
			}
		}
		InteractiveObject class61 = new InteractiveObject();
		class61.hash = i_300_;
		class61.uid = i_301_;
		class61.anInt987 = i;
		class61.renderX = i_296_;
		class61.renderY = i_297_;
		class61.renderZ = i_298_;
		class61.sceneModel = class3_sub3_sub3;
		class61.direction = i_299_;
		class61.x = x;
		class61.y = y;
		class61.anInt1001 = x + i_294_ - 1;
		class61.anInt998 = y + i_295_ - 1;
		for (int i_304_ = x; i_304_ < x + i_294_; i_304_++) {
			for (int i_305_ = y; i_305_ < y + i_295_; i_305_++) {
				int i_306_ = 0;
				if (i_304_ > x)
					i_306_++;
				if (i_304_ < x + i_294_ - 1)
					i_306_ += 4;
				if (i_305_ > y)
					i_306_ += 8;
				if (i_305_ < y + i_295_ - 1)
					i_306_ += 2;
				for (int i_307_ = i; i_307_ >= 0; i_307_--) {
					if ((groundTiles[i_307_][i_304_][i_305_]) == null)
						groundTiles[i_307_][i_304_][i_305_] = new GroundTile(
								i_307_, i_304_, i_305_);
				}
				GroundTile groundTile = groundTiles[i][i_304_][i_305_];
				groundTile.interactiveObjects[groundTile.interactiveObjectCount] = class61;
				groundTile.anIntArray1274[groundTile.interactiveObjectCount] = i_306_;
				groundTile.anInt1286 |= i_306_;
				groundTile.interactiveObjectCount++;
			}
		}
		if (cache)
			interactiveObjectCache[interactiveObjectCachePos++] = class61;
		return true;
	}

	private final void method983(DecoratedTile tile, int z, int sinePitch,
			int cosinePitch, int sineYaw, int cosineYaw, int x, int y, boolean ignore) {
		int i_315_;
		int i_314_ = i_315_ = (x << 7) - renderX;
		int i_317_;
		int i_316_ = i_317_ = (y << 7) - renderY;
		int i_319_;
		int i_318_ = i_319_ = i_314_ + 128;
		int i_321_;
		int i_320_ = i_321_ = i_316_ + 128;
		int i_322_ = heightMap[z][x][y] - renderZ;
		int i_323_ = heightMap[z][x + 1][y] - renderZ;
		int i_324_ = heightMap[z][x + 1][y + 1] - renderZ;
		int i_325_ = heightMap[z][x][y + 1] - renderZ;
		int i_326_ = i_316_ * sineYaw + i_314_ * cosineYaw >> 16;
		i_316_ = i_316_ * cosineYaw - i_314_ * sineYaw >> 16;
		i_314_ = i_326_;
		i_326_ = i_322_ * cosinePitch - i_316_ * sinePitch >> 16;
		i_316_ = i_322_ * sinePitch + i_316_ * cosinePitch >> 16;
		i_322_ = i_326_;
		if (i_316_ >= 50) {
			i_326_ = i_317_ * sineYaw + i_318_ * cosineYaw >> 16;
			i_317_ = i_317_ * cosineYaw - i_318_ * sineYaw >> 16;
			i_318_ = i_326_;
			i_326_ = i_323_ * cosinePitch - i_317_ * sinePitch >> 16;
			i_317_ = i_323_ * sinePitch + i_317_ * cosinePitch >> 16;
			i_323_ = i_326_;
			if (i_317_ >= 50) {
				i_326_ = i_320_ * sineYaw + i_319_ * cosineYaw >> 16;
				i_320_ = i_320_ * cosineYaw - i_319_ * sineYaw >> 16;
				i_319_ = i_326_;
				i_326_ = i_324_ * cosinePitch - i_320_ * sinePitch >> 16;
				i_320_ = i_324_ * sinePitch + i_320_ * cosinePitch >> 16;
				i_324_ = i_326_;
				if (i_320_ >= 50) {
					i_326_ = i_321_ * sineYaw + i_315_ * cosineYaw >> 16;
					i_321_ = i_321_ * cosineYaw - i_315_ * sineYaw >> 16;
					i_315_ = i_326_;
					i_326_ = i_325_ * cosinePitch - i_321_ * sinePitch >> 16;
					i_321_ = i_325_ * sinePitch + i_321_ * cosinePitch >> 16;
					i_325_ = i_326_;
					if (i_321_ >= 50) {
						int i_327_ = (Rasterizer.rasterizeCenterX + (i_314_ << 9)
								/ i_316_);
						int i_328_ = (Rasterizer.rasterizeCenterY + (i_322_ << 9)
								/ i_316_);
						int i_329_ = (Rasterizer.rasterizeCenterX + (i_318_ << 9)
								/ i_317_);
						int i_330_ = (Rasterizer.rasterizeCenterY + (i_323_ << 9)
								/ i_317_);
						int i_331_ = (Rasterizer.rasterizeCenterX + (i_319_ << 9)
								/ i_320_);
						int i_332_ = (Rasterizer.rasterizeCenterY + (i_324_ << 9)
								/ i_320_);
						int i_333_ = (Rasterizer.rasterizeCenterX + (i_315_ << 9)
								/ i_321_);
						int i_334_ = (Rasterizer.rasterizeCenterY + (i_325_ << 9)
								/ i_321_);
						Rasterizer.alpha = 0;
						if (((i_331_ - i_333_) * (i_330_ - i_334_) - (i_332_ - i_334_)
								* (i_329_ - i_333_)) > 0) {
								Rasterizer.edgeRestricted = false;
							if (i_331_ < 0 || i_333_ < 0 || i_329_ < 0
									|| i_331_ > Rasterizer.endX
									|| i_333_ > Rasterizer.endX
									|| i_329_ > Rasterizer.endX)
								Rasterizer.edgeRestricted = true;
							
							if (aBoolean1036
									&& method989(anInt1049, anInt1030, i_332_,
											i_334_, i_330_, i_331_, i_333_,
											i_329_)) {
								tileClickX = x;
								tileClickY = y;
							}
							
							if (ignore || method989(Client.lastMouseX, Client.lastMouseY, i_332_,
											i_334_, i_330_, i_331_, i_333_,
											i_329_)) {
								//System.out.println(ignore);
								//System.out.println("1 "+Client.lastMouseX+":"+Client.lastMouseY+":"+i_332_+":"+i_334_+":"+i_330_+":"+i_331_+":"+i_333_+":"+i_329_);
								if(!ignore) {
									tileMouseOverX = x;
									tileMouseOverY = y;
								}
								Editor.hoverTile(tileMouseOverX, tileMouseOverY, false);
							}
							
							if (tile.texture == -1 || tile.texture > 51) {
								if (z > 0 && tile.anInt380 == 23593394) {
									Rasterizer.alpha = 100;
								}
								if (tile.anInt374 != 12345678)
									Rasterizer.drawShadedTriangle(i_332_, i_334_,
											i_330_, i_331_, i_333_, i_329_,
											tile.anInt374,
											tile.anInt375,
											tile.anInt381);
							} else if (!lowMem) {
								if (tile.aBoolean383)
									Rasterizer.drawTexturedTriangle(i_332_, i_334_,
											i_330_, i_331_, i_333_, i_329_,
											tile.anInt374,
											tile.anInt375,
											tile.anInt381,
											i_314_, i_318_, i_315_, i_322_,
											i_323_, i_325_, i_316_, i_317_,
											i_321_,
											tile.texture);
								else
									Rasterizer.drawTexturedTriangle(i_332_, i_334_,
											i_330_, i_331_, i_333_, i_329_,
											tile.anInt374,
											tile.anInt375,
											tile.anInt381,
											i_319_, i_315_, i_318_, i_324_,
											i_325_, i_323_, i_320_, i_321_,
											i_317_,
											tile.texture);
							} else {
								int i_335_ = (Rasterizer.textureInterface
										.getTextureId(tile.texture));
								Rasterizer
										.drawShadedTriangle(
												i_332_,
												i_334_,
												i_330_,
												i_331_,
												i_333_,
												i_329_,
												method960(
														i_335_,
														tile.anInt374),
												method960(
														i_335_,
														tile.anInt375),
												method960(
														i_335_,
														tile.anInt381));
							}
						}
						if (((i_327_ - i_329_) * (i_334_ - i_330_) - (i_328_ - i_330_)
								* (i_333_ - i_329_)) > 0) {
							Rasterizer.edgeRestricted = false;
							if (i_327_ < 0 || i_329_ < 0 || i_333_ < 0
									|| i_327_ > Rasterizer.endX
									|| i_329_ > Rasterizer.endX
									|| i_333_ > Rasterizer.endX)
								Rasterizer.edgeRestricted = true;
							if (aBoolean1036
									&& method989(anInt1049, anInt1030, i_328_,
											i_330_, i_334_, i_327_, i_329_,
											i_333_)) {
								tileClickX = x;
								tileClickY = y;
							}
							
							if (ignore || method989(Client.lastMouseX, Client.lastMouseY, i_332_,
											i_334_, i_330_, i_331_, i_333_,
											i_329_)) {
								//System.out.println("2 "+Client.lastMouseX+":"+Client.lastMouseY+":"+i_332_+":"+i_334_+":"+i_330_+":"+i_331_+":"+i_333_+":"+i_329_);
								if(!ignore) {
									tileMouseOverX = x;
									tileMouseOverY = y;
								}
								Editor.hoverTile(tileMouseOverX, tileMouseOverY, false);
							}
							if (tile.texture == -1 || tile.texture > 51) {
								if (tile.anInt380 != 12345678)
									Rasterizer.drawShadedTriangle(i_328_, i_330_,
											i_334_, i_327_, i_329_, i_333_,
											tile.anInt380,
											tile.anInt381,
											tile.anInt375);
							} else if (!lowMem)
								Rasterizer.drawTexturedTriangle(i_328_, i_330_, i_334_,
										i_327_, i_329_, i_333_,
										tile.anInt380,
										tile.anInt381,
										tile.anInt375,
										i_314_, i_318_, i_315_, i_322_, i_323_,
										i_325_, i_316_, i_317_, i_321_,
										tile.texture);
							else {
								int i_336_ = (Rasterizer.textureInterface
										.getTextureId(tile.texture));
								Rasterizer
										.drawShadedTriangle(
												i_328_,
												i_330_,
												i_334_,
												i_327_,
												i_329_,
												i_333_,
												method960(
														i_336_,
														tile.anInt380),
												method960(
														i_336_,
														tile.anInt381),
												method960(
														i_336_,
														tile.anInt375));
							}
						}
					}
				}
			}
		}
	}

	private final void method984(InteractiveObject class61) {
		for (int i = class61.x; i <= class61.anInt1001; i++) {
			for (int i_337_ = class61.y; i_337_ <= class61.anInt998; i_337_++) {
				GroundTile groundTile = (groundTiles[class61.anInt987][i][i_337_]);
				if (groundTile != null) {
					for (int i_338_ = 0; i_338_ < groundTile.interactiveObjectCount; i_338_++) {
						if ((groundTile.interactiveObjects[i_338_]) == class61) {
							groundTile.interactiveObjectCount--;
							for (int i_339_ = i_338_; (i_339_ < groundTile.interactiveObjectCount); i_339_++) {
								groundTile.interactiveObjects[i_339_] = (groundTile.interactiveObjects[i_339_ + 1]);
								groundTile.anIntArray1274[i_339_] = (groundTile.anIntArray1274[i_339_ + 1]);
							}
							groundTile.interactiveObjects[groundTile.interactiveObjectCount] = null;
							break;
						}
					}
					groundTile.anInt1286 = 0;
					for (int i_340_ = 0; i_340_ < groundTile.interactiveObjectCount; i_340_++)
						groundTile.anInt1286 |= (groundTile.anIntArray1274[i_340_]);
				}
			}
		}
	}

	final void method985(int i, int i_341_, int i_342_) {
		for (int z = 0; z < mapSizeZ; z++) {
			for (int x = 0; x < mapSizeX; x++) {
				for (int y = 0; y < mapSizeY; y++) {
					GroundTile groundTile = (groundTiles[z][x][y]);
					if (groundTile != null) {
						WallObject wall = groundTile.wallObject;
						if (wall != null && (wall.aClass3_Sub3_Sub3_807 instanceof Model)) {
							Model wallModel = (Model) wall.aClass3_Sub3_Sub3_807;
							if (wallModel.vertices != null) {
								method956(wallModel, z, x, y, 1, 1);
								if (wall.aClass3_Sub3_Sub3_812 instanceof Model) {
									Model class3_sub3_sub3_sub3_346_ = ((Model) (wall.aClass3_Sub3_Sub3_812));
									if (class3_sub3_sub3_sub3_346_.vertices != null) {
										method956(class3_sub3_sub3_sub3_346_, z, x, y, 1, 1);
										method970(wallModel, class3_sub3_sub3_sub3_346_, 0, 0, 0, false);
										class3_sub3_sub3_sub3_346_.method139(i, i_341_, i_342_);
									}
								}
								wallModel.method139(i, i_341_, i_342_);
							}
						}
						for (int i_347_ = 0; i_347_ < groundTile.interactiveObjectCount; i_347_++) {
							InteractiveObject class61 = (groundTile.interactiveObjects[i_347_]);
							if (class61 != null
									&& (class61.sceneModel instanceof Model)) {
								Model class3_sub3_sub3_sub3 = ((Model) (class61.sceneModel));
								if (class3_sub3_sub3_sub3.vertices != null) {
									method956(
											class3_sub3_sub3_sub3,
											z,
											x,
											y,
											(class61.anInt1001
													- class61.x + 1),
											(class61.anInt998
													- class61.y + 1));
									class3_sub3_sub3_sub3.method139(i, i_341_,
											i_342_);
								}
							}
						}
						GroundDecoration class36 = groundTile.groundDecoration;
						if (class36 != null
								&& (class36.sceneModel instanceof Model)) {
							Model class3_sub3_sub3_sub3 = ((Model) class36.sceneModel);
							if (class3_sub3_sub3_sub3.vertices != null) {
								method994(class3_sub3_sub3_sub3, z,
										x, y);
								class3_sub3_sub3_sub3.method139(i, i_341_,
										i_342_);
							}
						}
					}
				}
			}
		}
	}

	private final void method986(CompoundTile class38, int i, int i_348_,
			int i_349_, int i_350_, int i_351_, int i_352_, boolean ignore) {
		int i_353_ = class38.anIntArray652.length;
		for (int i_354_ = 0; i_354_ < i_353_; i_354_++) {
			int i_355_ = class38.anIntArray652[i_354_]
					- renderX;
			int i_356_ = class38.anIntArray663[i_354_]
					- renderZ;
			int i_357_ = class38.anIntArray658[i_354_]
					- renderY;
			int i_358_ = i_357_ * i_349_ + i_355_ * i_350_ >> 16;
			i_357_ = i_357_ * i_350_ - i_355_ * i_349_ >> 16;
			i_355_ = i_358_;
			i_358_ = i_356_ * i_348_ - i_357_ * i >> 16;
			i_357_ = i_356_ * i + i_357_ * i_348_ >> 16;
			i_356_ = i_358_;
			if (i_357_ < 50)
				return;
			if (class38.textures != null) {
				CompoundTile.anIntArray671[i_354_] = i_355_;
				CompoundTile.anIntArray662[i_354_] = i_356_;
				CompoundTile.anIntArray655[i_354_] = i_357_;
			}
			CompoundTile.anIntArray657[i_354_] = Rasterizer.rasterizeCenterX
					+ (i_355_ << 9) / i_357_;
			CompoundTile.anIntArray670[i_354_] = Rasterizer.rasterizeCenterY
					+ (i_356_ << 9) / i_357_;
		}
		Rasterizer.alpha = 0;
		i_353_ = class38.anIntArray656.length;
		for (int i_359_ = 0; i_359_ < i_353_; i_359_++) {
			int i_360_ = class38.anIntArray656[i_359_];
			int i_361_ = class38.anIntArray659[i_359_];
			int i_362_ = class38.anIntArray660[i_359_];
			int i_363_ = CompoundTile.anIntArray657[i_360_];
			int i_364_ = CompoundTile.anIntArray657[i_361_];
			int i_365_ = CompoundTile.anIntArray657[i_362_];
			int i_366_ = CompoundTile.anIntArray670[i_360_];
			int i_367_ = CompoundTile.anIntArray670[i_361_];
			int i_368_ = CompoundTile.anIntArray670[i_362_];
			if (((i_363_ - i_364_) * (i_368_ - i_367_) - (i_366_ - i_367_)
					* (i_365_ - i_364_)) > 0) {
				Rasterizer.edgeRestricted = false;
				if (i_363_ < 0 || i_364_ < 0 || i_365_ < 0
						|| i_363_ > Rasterizer.endX || i_364_ > Rasterizer.endX
						|| i_365_ > Rasterizer.endX)
					Rasterizer.edgeRestricted = true;
				if (aBoolean1036
						&& method989(anInt1049, anInt1030, i_366_, i_367_,
								i_368_, i_363_, i_364_, i_365_)) {
					tileClickX = i_351_;
					tileClickY = i_352_;
				}
				
				if (method989(Client.lastMouseX, Client.lastMouseY, i_366_, i_367_,
						i_368_, i_363_, i_364_, i_365_)) {
					tileMouseOverX = i_351_;
					tileMouseOverY = i_352_;
					Editor.hoverTile(tileMouseOverX, tileMouseOverY, true);
				}
				if (class38.textures == null || class38.textures[i_359_] == -1 || class38.textures[i_359_] > 51) {
					if (class38.anIntArray668[i_359_] != 12345678)
						Rasterizer.drawShadedTriangle(i_366_, i_367_, i_368_, i_363_,
								i_364_, i_365_,
								class38.anIntArray668[i_359_],
								class38.anIntArray664[i_359_],
								class38.anIntArray651[i_359_]);
				} else if (!lowMem) {
					if (class38.aBoolean666)
						Rasterizer.drawTexturedTriangle(i_366_, i_367_, i_368_, i_363_,
								i_364_, i_365_,
								class38.anIntArray668[i_359_],
								class38.anIntArray664[i_359_],
								class38.anIntArray651[i_359_],
								CompoundTile.anIntArray671[0],
								CompoundTile.anIntArray671[1],
								CompoundTile.anIntArray671[3],
								CompoundTile.anIntArray662[0],
								CompoundTile.anIntArray662[1],
								CompoundTile.anIntArray662[3],
								CompoundTile.anIntArray655[0],
								CompoundTile.anIntArray655[1],
								CompoundTile.anIntArray655[3],
								class38.textures[i_359_]);
					else
						Rasterizer.drawTexturedTriangle(i_366_, i_367_, i_368_, i_363_,
								i_364_, i_365_,
								class38.anIntArray668[i_359_],
								class38.anIntArray664[i_359_],
								class38.anIntArray651[i_359_],
								CompoundTile.anIntArray671[i_360_],
								CompoundTile.anIntArray671[i_361_],
								CompoundTile.anIntArray671[i_362_],
								CompoundTile.anIntArray662[i_360_],
								CompoundTile.anIntArray662[i_361_],
								CompoundTile.anIntArray662[i_362_],
								CompoundTile.anIntArray655[i_360_],
								CompoundTile.anIntArray655[i_361_],
								CompoundTile.anIntArray655[i_362_],
								class38.textures[i_359_]);
				} else {
					int i_369_ = (Rasterizer.textureInterface
							.getTextureId(class38.textures[i_359_]));
					Rasterizer
							.drawShadedTriangle(
									i_366_,
									i_367_,
									i_368_,
									i_363_,
									i_364_,
									i_365_,
									method960(
											i_369_,
											class38.anIntArray668[i_359_]),
									method960(
											i_369_,
											class38.anIntArray664[i_359_]),
									method960(
											i_369_,
											class38.anIntArray651[i_359_]));
				}
			}
		}
	}

	public final void dispose() {
		for (int z = 0; z < mapSizeZ; z++) {
			for (int x = 0; x < mapSizeX; x++) {
				for (int y = 0; y < mapSizeY; y++)
					groundTiles[z][x][y] = null;
			}
		}
		for (int i = 0; i < anInt1029; i++) {
			for (int i_372_ = 0; i_372_ < cullingClusterPointer[i]; i_372_++)
				cullingClusters[i][i_372_] = null;
			cullingClusterPointer[i] = 0;
		}
		for (int i = 0; i < interactiveObjectCachePos; i++)
			interactiveObjectCache[i] = null;
		interactiveObjectCachePos = 0;
		for (int i = 0; i < interactiveObjects.length; i++)
			interactiveObjects[i] = null;
	}

	final void method988(int z, int x, int y, int texture, int shape,
			int rotation, int i_378_, int i_379_, int i_380_, int i_381_,
			int i_382_, int i_383_, int i_385_, int i_384_, int i_386_,
			int i_387_, int i_388_, int i_389_, int color1, int color2) {
		if (shape == 0) {
			DecoratedTile class21 = new DecoratedTile(i_382_, i_383_, i_385_,
					i_384_, -1, color1, false);
			for (int i_392_ = z; i_392_ >= 0; i_392_--) {
				if (groundTiles[i_392_][x][y] == null)
					groundTiles[i_392_][x][y] = new GroundTile(
							i_392_, x, y);
			}
			groundTiles[z][x][y].decoratedTile = class21;
		} else if (shape == 1) {
			DecoratedTile class21 = new DecoratedTile(i_386_, i_387_, i_389_,
					i_388_, texture, color2, (i_378_ == i_379_
							&& i_378_ == i_380_ && i_378_ == i_381_));
			for (int i_393_ = z; i_393_ >= 0; i_393_--) {
				if (groundTiles[i_393_][x][y] == null)
					groundTiles[i_393_][x][y] = new GroundTile(
							i_393_, x, y);
			}
			groundTiles[z][x][y].decoratedTile = class21;
		} else {
			CompoundTile class38 = new CompoundTile(shape, rotation, texture,
					x, y, i_378_, i_379_, i_380_, i_381_, i_382_,
					i_383_, i_384_, i_385_, i_386_, i_387_, i_388_, i_389_,
					color1, color2);
			for (int i_394_ = z; i_394_ >= 0; i_394_--) {
				if (groundTiles[i_394_][x][y] == null)
					groundTiles[i_394_][x][y] = new GroundTile(
							i_394_, x, y);
			}
			groundTiles[z][x][y].shapedTile = class38;
		}
	}

	private final boolean method989(int mouseX, int mouseY, int i_396_, int i_397_,
			int i_398_, int i_399_, int i_400_, int i_401_) {
		if (mouseY < i_396_ && mouseY < i_397_ && mouseY < i_398_)
			return false;
		if (mouseY > i_396_ && mouseY > i_397_ && mouseY > i_398_)
			return false;
		if (mouseX < i_399_ && mouseX < i_400_ && mouseX < i_401_)
			return false;
		if (mouseX > i_399_ && mouseX > i_400_ && mouseX > i_401_)
			return false;
		int i_402_ = ((mouseY - i_396_) * (i_400_ - i_399_) - (mouseX - i_399_)
				* (i_397_ - i_396_));
		int i_403_ = ((mouseY - i_398_) * (i_399_ - i_401_) - (mouseX - i_401_)
				* (i_396_ - i_398_));
		int i_404_ = ((mouseY - i_397_) * (i_401_ - i_400_) - (mouseX - i_400_)
				* (i_398_ - i_397_));
		if (i_402_ * i_404_ > 0 && i_404_ * i_403_ > 0)
			return true;
		return false;
	}

	final void method990(int z, int x, int y, int i_407_) {
		GroundTile groundTile = groundTiles[z][x][y];
		if (groundTile != null) {
			WallDecoration wallDecoration = groundTile.wallDecoration;
			if (wallDecoration != null) {
				int i_408_ = x * 128 + 64;
				int i_409_ = y * 128 + 64;
				wallDecoration.renderX = i_408_ + ((wallDecoration.renderX - i_408_) * i_407_ / 16);
				wallDecoration.renderY = i_409_ + ((wallDecoration.renderY - i_409_) * i_407_ / 16);
			}
		}
	}

	final void addWallDecoration(int z, int x, int y, int height,
			SceneModel sceneModel, int i_413_, int i_414_, int offsetX,
			int offsetY, int hash, int i_418_) {
		if (sceneModel != null) {
			WallDecoration wallDecoration = new WallDecoration();
			wallDecoration.hash = hash;
			wallDecoration.uid = i_418_;//TODO refactor wall decorations
			wallDecoration.renderX = x * 128 + 64 + offsetX;
			wallDecoration.renderY = y * 128 + 64 + offsetY;
			wallDecoration.renderZ = height;
			wallDecoration.sceneModel = sceneModel;
			wallDecoration.anInt1014 = i_413_;
			wallDecoration.rotation = i_414_;
			for (int z_ = z; z_ >= 0; z_--) {
				if (groundTiles[z_][x][y] == null)
					groundTiles[z_][x][y] = new GroundTile(z_, x, y);
			}
			groundTiles[z][x][y].wallDecoration = wallDecoration;
		}
	}

	final void createTiles(int z) {
		visibleZ = z;
		for (int x = 0; x < mapSizeX; x++) {
			for (int y = 0; y < mapSizeY; y++) {
				if (groundTiles[z][x][y] == null)
					groundTiles[z][x][y] = new GroundTile(z, x, y);
			}
		}
	}

	final int getGroundDecorationHash(int x, int y, int z) {
		GroundTile groundTile = groundTiles[z][x][y];
		if (groundTile == null || groundTile.groundDecoration == null)
			return 0;
		return groundTile.groundDecoration.hash;
	}

	private final void method994(Model class3_sub3_sub3_sub3, int i,
			int i_424_, int i_425_) {
		if (i_424_ < mapSizeX) {
			GroundTile groundTile = groundTiles[i][i_424_ + 1][i_425_];
			if (groundTile != null
					&& groundTile.groundDecoration != null
					&& (groundTile.groundDecoration.sceneModel) instanceof Model) {
				Model class3_sub3_sub3_sub3_426_ = (Model) groundTile.groundDecoration.sceneModel;
				if ((class3_sub3_sub3_sub3_426_.vertices) != null)
					method970(class3_sub3_sub3_sub3,
							class3_sub3_sub3_sub3_426_, 128, 0, 0, true);
			}
		}
		if (i_425_ < mapSizeX) {
			GroundTile groundTile = groundTiles[i][i_424_][i_425_ + 1];
			if (groundTile != null
					&& groundTile.groundDecoration != null
					&& (groundTile.groundDecoration.sceneModel) instanceof Model) {
				Model class3_sub3_sub3_sub3_427_ = (Model) groundTile.groundDecoration.sceneModel;
				if ((class3_sub3_sub3_sub3_427_.vertices) != null)
					method970(class3_sub3_sub3_sub3,
							class3_sub3_sub3_sub3_427_, 0, 0, 128, true);
			}
		}
		if (i_424_ < mapSizeX && i_425_ < mapSizeY) {
			GroundTile groundTile = groundTiles[i][i_424_ + 1][i_425_ + 1];
			if (groundTile != null
					&& groundTile.groundDecoration != null
					&& (groundTile.groundDecoration.sceneModel) instanceof Model) {
				Model class3_sub3_sub3_sub3_428_ = (Model) groundTile.groundDecoration.sceneModel;
				if (class3_sub3_sub3_sub3_428_.vertices != null)
					method970(class3_sub3_sub3_sub3,
							class3_sub3_sub3_sub3_428_, 128, 0, 128, true);
			}
		}
		if (i_424_ < mapSizeX && i_425_ > 0) {
			GroundTile groundTile = groundTiles[i][i_424_ + 1][i_425_ - 1];
			if (groundTile != null
					&& groundTile.groundDecoration != null
					&& (groundTile.groundDecoration.sceneModel) instanceof Model) {
				Model class3_sub3_sub3_sub3_429_ = (Model) groundTile.groundDecoration.sceneModel;
				if (class3_sub3_sub3_sub3_429_.vertices != null)
					method970(class3_sub3_sub3_sub3,
							class3_sub3_sub3_sub3_429_, 128, 0, -128, true);
			}
		}
	}

	private final boolean method995(int i, int i_430_, int i_431_) {
		for (int i_432_ = 0; i_432_ < anInt1035; i_432_++) {
			CullingCluster class39 = aClass39Array1050[i_432_];
			if (class39.anInt685 == 1) {
				int i_433_ = class39.anInt699 - i;
				if (i_433_ > 0) {
					int i_434_ = (class39.anInt673 + (class39.anInt675
							* i_433_ >> 8));
					int i_435_ = (class39.anInt676 + (class39.anInt695
							* i_433_ >> 8));
					int i_436_ = (class39.anInt694 + (class39.anInt688
							* i_433_ >> 8));
					int i_437_ = (class39.anInt681 + (class39.anInt696
							* i_433_ >> 8));
					if (i_431_ >= i_434_ && i_431_ <= i_435_
							&& i_430_ >= i_436_ && i_430_ <= i_437_)
						return true;
				}
			} else if (class39.anInt685 == 2) {
				int i_438_ = i - class39.anInt699;
				if (i_438_ > 0) {
					int i_439_ = (class39.anInt673 + (class39.anInt675
							* i_438_ >> 8));
					int i_440_ = (class39.anInt676 + (class39.anInt695
							* i_438_ >> 8));
					int i_441_ = (class39.anInt694 + (class39.anInt688
							* i_438_ >> 8));
					int i_442_ = (class39.anInt681 + (class39.anInt696
							* i_438_ >> 8));
					if (i_431_ >= i_439_ && i_431_ <= i_440_
							&& i_430_ >= i_441_ && i_430_ <= i_442_)
						return true;
				}
			} else if (class39.anInt685 == 3) {
				int i_443_ = class39.anInt673 - i_431_;
				if (i_443_ > 0) {
					int i_444_ = (class39.anInt699 + (class39.anInt693
							* i_443_ >> 8));
					int i_445_ = (class39.anInt674 + (class39.anInt683
							* i_443_ >> 8));
					int i_446_ = (class39.anInt694 + (class39.anInt688
							* i_443_ >> 8));
					int i_447_ = (class39.anInt681 + (class39.anInt696
							* i_443_ >> 8));
					if (i >= i_444_ && i <= i_445_ && i_430_ >= i_446_
							&& i_430_ <= i_447_)
						return true;
				}
			} else if (class39.anInt685 == 4) {
				int i_448_ = i_431_ - class39.anInt673;
				if (i_448_ > 0) {
					int i_449_ = (class39.anInt699 + (class39.anInt693
							* i_448_ >> 8));
					int i_450_ = (class39.anInt674 + (class39.anInt683
							* i_448_ >> 8));
					int i_451_ = (class39.anInt694 + (class39.anInt688
							* i_448_ >> 8));
					int i_452_ = (class39.anInt681 + (class39.anInt696
							* i_448_ >> 8));
					if (i >= i_449_ && i <= i_450_ && i_430_ >= i_451_
							&& i_430_ <= i_452_)
						return true;
				}
			} else if (class39.anInt685 == 5) {
				int i_453_ = i_430_ - class39.anInt694;
				if (i_453_ > 0) {
					int i_454_ = (class39.anInt699 + (class39.anInt693
							* i_453_ >> 8));
					int i_455_ = (class39.anInt674 + (class39.anInt683
							* i_453_ >> 8));
					int i_456_ = (class39.anInt673 + (class39.anInt675
							* i_453_ >> 8));
					int i_457_ = (class39.anInt676 + (class39.anInt695
							* i_453_ >> 8));
					if (i >= i_454_ && i <= i_455_ && i_431_ >= i_456_
							&& i_431_ <= i_457_)
						return true;
				}
			}
		}
		return false;
	}

	final void method996(int i, int i_458_) {
		aBoolean1036 = true;
		anInt1049 = i;
		anInt1030 = i_458_;
		tileClickX = -1;
		tileClickY = -1;
		
		//System.out.println("method996: " + i + ", " + i_458_);
	}

	final InteractiveObject getInteractiveObject(int x, int y, int z) {
		GroundTile groundTile = groundTiles[z][x][y];
		if (groundTile == null)
			return null;
		for (int i_463_ = 0; i_463_ < groundTile.interactiveObjectCount; i_463_++) {
			InteractiveObject object = groundTile.interactiveObjects[i_463_];
			if ((object.hash >> 29 & 0x3) == 2 && object.x == x
					&& object.y == y)
				return object;
		}
		return null;
	}

	SceneGraph(int z, int x, int y, int[][][] heightmap) {
		anIntArrayArray1067 = (new int[][] {
				{ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 },
				{ 12, 8, 4, 0, 13, 9, 5, 1, 14, 10, 6, 2, 15, 11, 7, 3 },
				{ 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 },
				{ 3, 7, 11, 15, 2, 6, 10, 14, 1, 5, 9, 13, 0, 4, 8, 12 } });
		anIntArray1080 = new int[10000];
		anIntArray1082 = new int[10000];
		mapSizeZ = z;
		mapSizeX = x;
		mapSizeY = y;
		groundTiles = new GroundTile[z][x][y];
		anIntArrayArrayArray1056 = new int[z][x + 1][y + 1];
		heightMap = heightmap;
		dispose();
	}

	private final boolean method999(int i, int i_466_, int i_467_, int i_468_,
			int i_469_, int i_470_) {
		if (i_466_ == i_467_ && i_468_ == i_469_) {
			if (!method953(i, i_466_, i_468_))
				return false;
			int i_471_ = i_466_ << 7;
			int i_472_ = i_468_ << 7;
			if (method995(i_471_ + 1, heightMap[i][i_466_][i_468_] - i_470_,
					i_472_ + 1)
					&& method995(i_471_ + 128 - 1,
							(heightMap[i][i_466_ + 1][i_468_] - i_470_),
							i_472_ + 1)
					&& method995(i_471_ + 128 - 1,
							(heightMap[i][i_466_ + 1][i_468_ + 1]) - i_470_,
							i_472_ + 128 - 1)
					&& method995(i_471_ + 1,
							(heightMap[i][i_466_][i_468_ + 1] - i_470_),
							i_472_ + 128 - 1))
				return true;
			return false;
		}
		for (int i_473_ = i_466_; i_473_ <= i_467_; i_473_++) {
			for (int i_474_ = i_468_; i_474_ <= i_469_; i_474_++) {
				if (anIntArrayArrayArray1056[i][i_473_][i_474_] == -anInt1037)
					return false;
			}
		}
		int i_475_ = (i_466_ << 7) + 1;
		int i_476_ = (i_468_ << 7) + 2;
		int i_477_ = heightMap[i][i_466_][i_468_] - i_470_;
		if (!method995(i_475_, i_477_, i_476_))
			return false;
		int i_478_ = (i_467_ << 7) - 1;
		if (!method995(i_478_, i_477_, i_476_))
			return false;
		int i_479_ = (i_469_ << 7) - 1;
		if (!method995(i_475_, i_477_, i_479_))
			return false;
		if (!method995(i_478_, i_477_, i_479_))
			return false;
		return true;
	}

	final int getInteractiveObjectHash(int x, int y, int z) {
		GroundTile groundTile = groundTiles[z][x][y];
		if (groundTile == null) {
			return 0;
		}
		for (int id = 0; id < groundTile.interactiveObjectCount; id++) {
			InteractiveObject object = groundTile.interactiveObjects[id];
			//System.out.println(object.hash >> 29 & 0x3);
			if ((object.hash >> 29 & 0x3) == 3 && object.x == x && object.y == y) {
				//System.out.println(object.hash);
				return object.hash;
			}
		}
		//System.out.println("null interactive");
		return 0;
	}
}
