package org.mapeditor.client;

/* Class38 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public final class CompoundTile
{
    int[] anIntArray651;
    int[] anIntArray652;
    private static int[][] anIntArrayArray653
	= { { 1, 3, 5, 7 }, { 1, 3, 5, 7 }, { 1, 3, 5, 7 }, { 1, 3, 5, 7, 6 },
	    { 1, 3, 5, 7, 6 }, { 1, 3, 5, 7, 6 }, { 1, 3, 5, 7, 6 },
	    { 1, 3, 5, 7, 2, 6 }, { 1, 3, 5, 7, 2, 8 }, { 1, 3, 5, 7, 2, 8 },
	    { 1, 3, 5, 7, 11, 12 }, { 1, 3, 5, 7, 11, 12 },
	    { 1, 3, 5, 7, 13, 14 } };
    int anInt654;
    static int[] anIntArray655 = new int[6];
    int[] anIntArray656;
    static int[] anIntArray657 = new int[6];
    int[] anIntArray658;
    int[] anIntArray659;
    int[] anIntArray660;
    int rotation;
    static int[] anIntArray662;
    int[] anIntArray663;
    int[] anIntArray664;
    int anInt665;
    boolean aBoolean666 = true;
    int shape;
    int[] anIntArray668;
    private static int[][] anIntArrayArray669
	= { { 0, 1, 2, 3, 0, 0, 1, 3 }, { 1, 1, 2, 3, 1, 0, 1, 3 },
	    { 0, 1, 2, 3, 1, 0, 1, 3 }, { 0, 0, 1, 2, 0, 0, 2, 4, 1, 0, 4, 3 },
	    { 0, 0, 1, 4, 0, 0, 4, 3, 1, 1, 2, 4 },
	    { 0, 0, 4, 3, 1, 0, 1, 2, 1, 0, 2, 4 },
	    { 0, 1, 2, 4, 1, 0, 1, 4, 1, 0, 4, 3 },
	    { 0, 4, 1, 2, 0, 4, 2, 5, 1, 0, 4, 5, 1, 0, 5, 3 },
	    { 0, 4, 1, 2, 0, 4, 2, 3, 0, 4, 3, 5, 1, 0, 4, 5 },
	    { 0, 0, 4, 5, 1, 4, 1, 2, 1, 4, 2, 3, 1, 4, 3, 5 },
	    { 0, 0, 1, 5, 0, 1, 4, 5, 0, 1, 2, 4, 1, 0, 5, 3, 1, 5, 4, 3, 1, 4,
	      2, 3 },
	    { 1, 0, 1, 5, 1, 1, 4, 5, 1, 1, 2, 4, 0, 0, 5, 3, 0, 5, 4, 3, 0, 4,
	      2, 3 },
	    { 1, 0, 5, 4, 1, 0, 1, 5, 0, 0, 4, 3, 0, 4, 5, 3, 0, 5, 2, 3, 0, 1,
	      2, 5 } };
    static int[] anIntArray670 = new int[6];
    static int[] anIntArray671;
    int[] textures;
    
    static {
	anIntArray662 = new int[6];
	anIntArray671 = new int[6];
    }
    
    public static void reset() {
	anIntArray657 = null;
	anIntArray670 = null;
	anIntArray671 = null;
	anIntArray662 = null;
	anIntArray655 = null;
	anIntArrayArray653 = null;
	anIntArrayArray669 = null;
    }
    
    public CompoundTile(int i, int i_0_, int texture, int i_2_, int i_3_, int i_4_, int i_5_,
	    int i_6_, int i_7_, int i_8_, int i_9_, int i_10_, int i_11_,
	    int i_12_, int i_13_, int i_14_, int i_15_, int i_16_, int i_17_) {
	if (i_4_ != i_5_ || i_4_ != i_6_ || i_4_ != i_7_)
	    aBoolean666 = false;
	shape = i;
	rotation = i_0_;
	anInt665 = i_16_;
	anInt654 = i_17_;
	int i_18_ = 128;
	int i_19_ = i_18_ / 2;
	int i_20_ = i_18_ / 4;
	int i_21_ = i_18_ * 3 / 4;
	int[] is = anIntArrayArray653[i];
	int i_22_ = is.length;
	anIntArray652 = new int[i_22_];
	anIntArray663 = new int[i_22_];
	anIntArray658 = new int[i_22_];
	int[] is_23_ = new int[i_22_];
	int[] is_24_ = new int[i_22_];
	int i_25_ = i_2_ * i_18_;
	int i_26_ = i_3_ * i_18_;
	for (int i_27_ = 0; i_27_ < i_22_; i_27_++) {
	    int i_28_ = is[i_27_];
	    if ((i_28_ & 0x1) == 0 && i_28_ <= 8)
		i_28_ = (i_28_ - i_0_ - i_0_ - 1 & 0x7) + 1;
	    if (i_28_ > 8 && i_28_ <= 12)
		i_28_ = (i_28_ - 9 - i_0_ & 0x3) + 9;
	    if (i_28_ > 12 && i_28_ <= 16)
		i_28_ = (i_28_ - 13 - i_0_ & 0x3) + 13;
	    int i_29_;
	    int i_30_;
	    int i_31_;
	    int i_32_;
	    int i_33_;
	    if (i_28_ == 1) {
		i_29_ = i_25_;
		i_30_ = i_26_;
		i_31_ = i_4_;
		i_32_ = i_8_;
		i_33_ = i_12_;
	    } else if (i_28_ == 2) {
		i_29_ = i_25_ + i_19_;
		i_30_ = i_26_;
		i_31_ = i_4_ + i_5_ >> 1;
		i_32_ = i_8_ + i_9_ >> 1;
		i_33_ = i_12_ + i_13_ >> 1;
	    } else if (i_28_ == 3) {
		i_29_ = i_25_ + i_18_;
		i_30_ = i_26_;
		i_31_ = i_5_;
		i_32_ = i_9_;
		i_33_ = i_13_;
	    } else if (i_28_ == 4) {
		i_29_ = i_25_ + i_18_;
		i_30_ = i_26_ + i_19_;
		i_31_ = i_5_ + i_6_ >> 1;
		i_32_ = i_9_ + i_10_ >> 1;
		i_33_ = i_13_ + i_14_ >> 1;
	    } else if (i_28_ == 5) {
		i_29_ = i_25_ + i_18_;
		i_30_ = i_26_ + i_18_;
		i_31_ = i_6_;
		i_32_ = i_10_;
		i_33_ = i_14_;
	    } else if (i_28_ == 6) {
		i_29_ = i_25_ + i_19_;
		i_30_ = i_26_ + i_18_;
		i_31_ = i_6_ + i_7_ >> 1;
		i_32_ = i_10_ + i_11_ >> 1;
		i_33_ = i_14_ + i_15_ >> 1;
	    } else if (i_28_ == 7) {
		i_29_ = i_25_;
		i_30_ = i_26_ + i_18_;
		i_31_ = i_7_;
		i_32_ = i_11_;
		i_33_ = i_15_;
	    } else if (i_28_ == 8) {
		i_29_ = i_25_;
		i_30_ = i_26_ + i_19_;
		i_31_ = i_7_ + i_4_ >> 1;
		i_32_ = i_11_ + i_8_ >> 1;
		i_33_ = i_15_ + i_12_ >> 1;
	    } else if (i_28_ == 9) {
		i_29_ = i_25_ + i_19_;
		i_30_ = i_26_ + i_20_;
		i_31_ = i_4_ + i_5_ >> 1;
		i_32_ = i_8_ + i_9_ >> 1;
		i_33_ = i_12_ + i_13_ >> 1;
	    } else if (i_28_ == 10) {
		i_29_ = i_25_ + i_21_;
		i_30_ = i_26_ + i_19_;
		i_31_ = i_5_ + i_6_ >> 1;
		i_32_ = i_9_ + i_10_ >> 1;
		i_33_ = i_13_ + i_14_ >> 1;
	    } else if (i_28_ == 11) {
		i_29_ = i_25_ + i_19_;
		i_30_ = i_26_ + i_21_;
		i_31_ = i_6_ + i_7_ >> 1;
		i_32_ = i_10_ + i_11_ >> 1;
		i_33_ = i_14_ + i_15_ >> 1;
	    } else if (i_28_ == 12) {
		i_29_ = i_25_ + i_20_;
		i_30_ = i_26_ + i_19_;
		i_31_ = i_7_ + i_4_ >> 1;
		i_32_ = i_11_ + i_8_ >> 1;
		i_33_ = i_15_ + i_12_ >> 1;
	    } else if (i_28_ == 13) {
		i_29_ = i_25_ + i_20_;
		i_30_ = i_26_ + i_20_;
		i_31_ = i_4_;
		i_32_ = i_8_;
		i_33_ = i_12_;
	    } else if (i_28_ == 14) {
		i_29_ = i_25_ + i_21_;
		i_30_ = i_26_ + i_20_;
		i_31_ = i_5_;
		i_32_ = i_9_;
		i_33_ = i_13_;
	    } else if (i_28_ == 15) {
		i_29_ = i_25_ + i_21_;
		i_30_ = i_26_ + i_21_;
		i_31_ = i_6_;
		i_32_ = i_10_;
		i_33_ = i_14_;
	    } else {
		i_29_ = i_25_ + i_20_;
		i_30_ = i_26_ + i_21_;
		i_31_ = i_7_;
		i_32_ = i_11_;
		i_33_ = i_15_;
	    }
	    anIntArray652[i_27_] = i_29_;
	    anIntArray663[i_27_] = i_31_;
	    anIntArray658[i_27_] = i_30_;
	    is_23_[i_27_] = i_32_;
	    is_24_[i_27_] = i_33_;
	}
	int[] is_34_ = anIntArrayArray669[i];
	int len = is_34_.length / 4;
	anIntArray656 = new int[len];
	anIntArray659 = new int[len];
	anIntArray660 = new int[len];
	anIntArray668 = new int[len];
	anIntArray664 = new int[len];
	anIntArray651 = new int[len];
	if (texture != -1)
	    textures = new int[len];
	int i_36_ = 0;
	for (int i_37_ = 0; i_37_ < len; i_37_++) {
	    int i_38_ = is_34_[i_36_];
	    int i_39_ = is_34_[i_36_ + 1];
	    int i_40_ = is_34_[i_36_ + 2];
	    int i_41_ = is_34_[i_36_ + 3];
	    i_36_ += 4;
	    if (i_39_ < 4)
		i_39_ = i_39_ - i_0_ & 0x3;
	    if (i_40_ < 4)
		i_40_ = i_40_ - i_0_ & 0x3;
	    if (i_41_ < 4)
		i_41_ = i_41_ - i_0_ & 0x3;
	    anIntArray656[i_37_] = i_39_;
	    anIntArray659[i_37_] = i_40_;
	    anIntArray660[i_37_] = i_41_;
	    if (i_38_ == 0) {
		anIntArray668[i_37_] = is_23_[i_39_];
		anIntArray664[i_37_] = is_23_[i_40_];
		anIntArray651[i_37_] = is_23_[i_41_];
		if (textures != null)
		    textures[i_37_] = -1;
	    } else {
		anIntArray668[i_37_] = is_24_[i_39_];
		anIntArray664[i_37_] = is_24_[i_40_];
		anIntArray651[i_37_] = is_24_[i_41_];
		if (textures != null)
		    textures[i_37_] = texture;
	    }
	}
	int i_42_ = i_4_;
	int i_43_ = i_5_;
	if (i_5_ < i_42_)
	    i_42_ = i_5_;
	if (i_5_ > i_43_)
	    i_43_ = i_5_;
	if (i_6_ < i_42_)
	    i_42_ = i_6_;
	if (i_6_ > i_43_)
	    i_43_ = i_6_;
	if (i_7_ < i_42_)
	    i_42_ = i_7_;
	if (i_7_ > i_43_)
	    i_43_ = i_7_;
	i_42_ /= 14;
	i_43_ /= 14;
    }
}
