package org.mapeditor.client;

/* Class46 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class BZip2Decompressor
{
    private static BZip2BlockEntry entry = new BZip2BlockEntry();
    
    private static final void method771(BZip2BlockEntry bzip) {
	byte i = bzip.aByte890;
	int i_0_ = bzip.anInt889;
	int i_1_ = bzip.anInt896;
	int i_2_ = bzip.anInt925;
	int[] is = Client.anIntArray679;
	int i_3_ = bzip.anInt912;
	byte[] is_4_ = bzip.aByteArray894;
	int i_5_ = bzip.anInt902;
	int i_6_ = bzip.anInt908;
	int i_7_ = i_6_;
	int i_8_ = bzip.anInt923 + 1;
    while_6_:
	for (;;) {
	    if (i_0_ > 0) {
		for (;;) {
		    if (i_6_ == 0)
			break while_6_;
		    if (i_0_ == 1)
			break;
		    is_4_[i_5_] = i;
		    i_0_--;
		    i_5_++;
		    i_6_--;
		}
		if (i_6_ == 0) {
		    i_0_ = 1;
		    break;
		}
		is_4_[i_5_] = i;
		i_5_++;
		i_6_--;
	    }
	    boolean bool = true;
	    while (bool) {
		bool = false;
		if (i_1_ == i_8_) {
		    i_0_ = 0;
		    break while_6_;
		}
		i = (byte) i_2_;
		i_3_ = is[i_3_];
		int i_9_ = (byte) (i_3_ & 0xff);
		i_3_ >>= 8;
		i_1_++;
		if (i_9_ != i_2_) {
		    i_2_ = i_9_;
		    if (i_6_ == 0) {
			i_0_ = 1;
			break while_6_;
		    }
		    is_4_[i_5_] = i;
		    i_5_++;
		    i_6_--;
		    bool = true;
		} else if (i_1_ == i_8_) {
		    if (i_6_ == 0) {
			i_0_ = 1;
			break while_6_;
		    }
		    is_4_[i_5_] = i;
		    i_5_++;
		    i_6_--;
		    bool = true;
		}
	    }
	    i_0_ = 2;
	    i_3_ = is[i_3_];
	    int i_10_ = (byte) (i_3_ & 0xff);
	    i_3_ >>= 8;
	    if (++i_1_ != i_8_) {
		if (i_10_ != i_2_)
		    i_2_ = i_10_;
		else {
		    i_0_ = 3;
		    i_3_ = is[i_3_];
		    i_10_ = (byte) (i_3_ & 0xff);
		    i_3_ >>= 8;
		    if (++i_1_ != i_8_) {
			if (i_10_ != i_2_)
			    i_2_ = i_10_;
			else {
			    i_3_ = is[i_3_];
			    i_10_ = (byte) (i_3_ & 0xff);
			    i_3_ >>= 8;
			    i_1_++;
			    i_0_ = (i_10_ & 0xff) + 4;
			    i_3_ = is[i_3_];
			    i_2_ = (byte) (i_3_ & 0xff);
			    i_3_ >>= 8;
			    i_1_++;
			}
		    }
		}
	    }
	}
	bzip.anInt887 += i_7_ - i_6_;
	bzip.aByte890 = i;
	bzip.anInt889 = i_0_;
	bzip.anInt896 = i_1_;
	bzip.anInt925 = i_2_;
	Client.anIntArray679 = is;
	bzip.anInt912 = i_3_;
	bzip.aByteArray894 = is_4_;
	bzip.anInt902 = i_5_;
	bzip.anInt908 = i_6_;
    }
    
    private static final void method772(BZip2BlockEntry bzip) {
	int i = 0;
	int[] is = null;
	int[] is_29_ = null;
	int[] is_30_ = null;
	bzip.anInt911 = 1;
	if (Client.anIntArray679 == null)
	    Client.anIntArray679
		= new int[bzip.anInt911 * 100000];
	boolean bool_31_ = true;
	while (bool_31_) {
	    byte i_32_ = method774(bzip);
	    if (i_32_ == 23)
		break;
	    i_32_ = method774(bzip);
	    i_32_ = method774(bzip);
	    i_32_ = method774(bzip);
	    i_32_ = method774(bzip);
	    i_32_ = method774(bzip);
	    i_32_ = method774(bzip);
	    i_32_ = method774(bzip);
	    i_32_ = method774(bzip);
	    i_32_ = method774(bzip);
	    i_32_ = method777(bzip);
	    bzip.anInt921 = 0;
	    int i_33_ = method774(bzip);
	    bzip.anInt921
		= bzip.anInt921 << 8 | i_33_ & 0xff;
	    i_33_ = method774(bzip);
	    bzip.anInt921
		= bzip.anInt921 << 8 | i_33_ & 0xff;
	    i_33_ = method774(bzip);
	    bzip.anInt921
		= bzip.anInt921 << 8 | i_33_ & 0xff;
	    for (int i_34_ = 0; i_34_ < 16; i_34_++) {
		i_32_ = method777(bzip);
		if (i_32_ == 1)
		    bzip.aBooleanArray920[i_34_] = true;
		else
		    bzip.aBooleanArray920[i_34_] = false;
	    }
	    for (int i_35_ = 0; i_35_ < 256; i_35_++)
		bzip.aBooleanArray885[i_35_] = false;
	    for (int i_36_ = 0; i_36_ < 16; i_36_++) {
		if (bzip.aBooleanArray920[i_36_]) {
		    for (int i_37_ = 0; i_37_ < 16; i_37_++) {
			i_32_ = method777(bzip);
			if (i_32_ == 1)
			    bzip.aBooleanArray885[(i_36_ * 16
								  + i_37_)]
				= true;
		    }
		}
	    }
	    method778(bzip);
	    int i_38_ = bzip.anInt905 + 2;
	    int i_39_ = method779(3, bzip);
	    int i_40_ = method779(15, bzip);
	    for (int i_41_ = 0; i_41_ < i_40_; i_41_++) {
		int i_42_ = 0;
		for (;;) {
		    i_32_ = method777(bzip);
		    if (i_32_ == 0)
			break;
		    i_42_++;
		}
		bzip.aByteArray886[i_41_] = (byte) i_42_;
	    }
	    byte[] is_43_ = new byte[6];
	    for (byte i_44_ = 0; i_44_ < i_39_; i_44_++)
		is_43_[i_44_] = i_44_;
	    for (int i_45_ = 0; i_45_ < i_40_; i_45_++) {
		byte i_46_ = bzip.aByteArray886[i_45_];
		byte i_47_ = is_43_[i_46_];
		for (/**/; i_46_ > 0; i_46_--)
		    is_43_[i_46_] = is_43_[i_46_ - 1];
		is_43_[0] = i_47_;
		bzip.aByteArray893[i_45_] = i_47_;
	    }
	    for (int i_48_ = 0; i_48_ < i_39_; i_48_++) {
		int i_49_ = method779(5, bzip);
		for (int i_50_ = 0; i_50_ < i_38_; i_50_++) {
		    for (;;) {
			i_32_ = method777(bzip);
			if (i_32_ == 0)
			    break;
			i_32_ = method777(bzip);
			if (i_32_ == 0)
			    i_49_++;
			else
			    i_49_--;
		    }
		    bzip.aByteArrayArray899[i_48_][i_50_]
			= (byte) i_49_;
		}
	    }
	    for (int i_51_ = 0; i_51_ < i_39_; i_51_++) {
		int i_52_ = 32;
		byte i_53_ = 0;
		for (int i_54_ = 0; i_54_ < i_38_; i_54_++) {
		    if (bzip.aByteArrayArray899[i_51_][i_54_]
			> i_53_)
			i_53_ = (bzip.aByteArrayArray899[i_51_]
				 [i_54_]);
		    if (bzip.aByteArrayArray899[i_51_][i_54_]
			< i_52_)
			i_52_ = (bzip.aByteArrayArray899[i_51_]
				 [i_54_]);
		}
		method775(bzip.anIntArrayArray918[i_51_],
			  bzip.anIntArrayArray914[i_51_],
			  bzip.anIntArrayArray919[i_51_],
			  bzip.aByteArrayArray899[i_51_], i_52_,
			  i_53_, i_38_);
		bzip.anIntArray909[i_51_] = i_52_;
	    }
	    int i_55_ = bzip.anInt905 + 1;
	    int i_56_ = -1;
	    int i_57_ = 0;
	    for (int i_58_ = 0; i_58_ <= 255; i_58_++)
		bzip.anIntArray898[i_58_] = 0;
	    int i_59_ = 4095;
	    for (int i_60_ = 15; i_60_ >= 0; i_60_--) {
		for (int i_61_ = 15; i_61_ >= 0; i_61_--) {
		    bzip.aByteArray903[i_59_]
			= (byte) (i_60_ * 16 + i_61_);
		    i_59_--;
		}
		bzip.anIntArray901[i_60_] = i_59_ + 1;
	    }
	    int i_62_ = 0;
	    if (i_57_ == 0) {
		i_56_++;
		i_57_ = 50;
		byte i_63_ = bzip.aByteArray893[i_56_];
		i = bzip.anIntArray909[i_63_];
		is = bzip.anIntArrayArray918[i_63_];
		is_30_ = bzip.anIntArrayArray919[i_63_];
		is_29_ = bzip.anIntArrayArray914[i_63_];
	    }
	    i_57_--;
	    int i_64_ = i;
	    int i_65_;
	    int i_66_;
	    for (i_66_ = method779(i_64_, bzip); i_66_ > is[i_64_];
		 i_66_ = i_66_ << 1 | i_65_) {
		i_64_++;
		i_65_ = method777(bzip);
	    }
	    int i_67_ = is_30_[i_66_ - is_29_[i_64_]];
	    while (i_67_ != i_55_) {
		if (i_67_ == 0 || i_67_ == 1) {
		    int i_68_ = -1;
		    int i_69_ = 1;
		    do {
			if (i_67_ == 0)
			    i_68_ += i_69_;
			else if (i_67_ == 1)
			    i_68_ += i_69_ * 2;
			i_69_ *= 2;
			if (i_57_ == 0) {
			    i_56_++;
			    i_57_ = 50;
			    byte i_70_
				= bzip.aByteArray893[i_56_];
			    i = bzip.anIntArray909[i_70_];
			    is = bzip.anIntArrayArray918[i_70_];
			    is_30_ = (bzip.anIntArrayArray919
				      [i_70_]);
			    is_29_ = (bzip.anIntArrayArray914
				      [i_70_]);
			}
			i_57_--;
			i_64_ = i;
			for (i_66_ = method779(i_64_, bzip);
			     i_66_ > is[i_64_]; i_66_ = i_66_ << 1 | i_65_) {
			    i_64_++;
			    i_65_ = method777(bzip);
			}
			i_67_ = is_30_[i_66_ - is_29_[i_64_]];
		    } while (i_67_ == 0 || i_67_ == 1);
		    i_68_++;
		    i_33_
			= (bzip.aByteArray906
			   [(bzip.aByteArray903
			     [bzip.anIntArray901[0]]) & 0xff]);
		    bzip.anIntArray898[i_33_ & 0xff] += i_68_;
		    for (/**/; i_68_ > 0; i_68_--) {
			Client.anIntArray679[i_62_] = i_33_ & 0xff;
			i_62_++;
		    }
		} else {
		    int i_71_ = i_67_ - 1;
		    if (i_71_ < 16) {
			int i_72_ = bzip.anIntArray901[0];
			i_32_
			    = bzip.aByteArray903[i_72_ + i_71_];
			for (/**/; i_71_ > 3; i_71_ -= 4) {
			    int i_73_ = i_72_ + i_71_;
			    bzip.aByteArray903[i_73_]
				= bzip.aByteArray903[i_73_ - 1];
			    bzip.aByteArray903[i_73_ - 1]
				= bzip.aByteArray903[i_73_ - 2];
			    bzip.aByteArray903[i_73_ - 2]
				= bzip.aByteArray903[i_73_ - 3];
			    bzip.aByteArray903[i_73_ - 3]
				= bzip.aByteArray903[i_73_ - 4];
			}
			for (/**/; i_71_ > 0; i_71_--)
			    bzip.aByteArray903[i_72_ + i_71_]
				= (bzip.aByteArray903
				   [i_72_ + i_71_ - 1]);
			bzip.aByteArray903[i_72_] = i_32_;
		    } else {
			int i_74_ = i_71_ / 16;
			int i_75_ = i_71_ % 16;
			int i_76_
			    = bzip.anIntArray901[i_74_] + i_75_;
			i_32_ = bzip.aByteArray903[i_76_];
			for (/**/;
			     i_76_ > bzip.anIntArray901[i_74_];
			     i_76_--)
			    bzip.aByteArray903[i_76_]
				= bzip.aByteArray903[i_76_ - 1];
			bzip.anIntArray901[i_74_]++;
			for (/**/; i_74_ > 0; i_74_--) {
			    bzip.anIntArray901[i_74_]--;
			    bzip.aByteArray903
				[bzip.anIntArray901[i_74_]]
				= (bzip.aByteArray903
				   [(bzip.anIntArray901
				     [i_74_ - 1]) + 16 - 1]);
			}
			bzip.anIntArray901[0]--;
			bzip.aByteArray903[(bzip
							   .anIntArray901[0])]
			    = i_32_;
			if (bzip.anIntArray901[0] == 0) {
			    int i_77_ = 4095;
			    for (int i_78_ = 15; i_78_ >= 0; i_78_--) {
				for (int i_79_ = 15; i_79_ >= 0; i_79_--) {
				    bzip.aByteArray903[i_77_]
					= (bzip.aByteArray903
					   [(bzip.anIntArray901
					     [i_78_]) + i_79_]);
				    i_77_--;
				}
				bzip.anIntArray901[i_78_]
				    = i_77_ + 1;
			    }
			}
		    }
		    bzip.anIntArray898
			[(bzip.aByteArray906[i_32_ & 0xff]
			  & 0xff)]++;
		    Client.anIntArray679[i_62_]
			= (bzip.aByteArray906[i_32_ & 0xff]
			   & 0xff);
		    i_62_++;
		    if (i_57_ == 0) {
			i_56_++;
			i_57_ = 50;
			byte i_80_ = bzip.aByteArray893[i_56_];
			i = bzip.anIntArray909[i_80_];
			is = bzip.anIntArrayArray918[i_80_];
			is_30_ = bzip.anIntArrayArray919[i_80_];
			is_29_ = bzip.anIntArrayArray914[i_80_];
		    }
		    i_57_--;
		    i_64_ = i;
		    for (i_66_ = method779(i_64_, bzip); i_66_ > is[i_64_];
			 i_66_ = i_66_ << 1 | i_65_) {
			i_64_++;
			i_65_ = method777(bzip);
		    }
		    i_67_ = is_30_[i_66_ - is_29_[i_64_]];
		}
	    }
	    bzip.anInt889 = 0;
	    bzip.aByte890 = (byte) 0;
	    bzip.anIntArray922[0] = 0;
	    for (int i_81_ = 1; i_81_ <= 256; i_81_++)
		bzip.anIntArray922[i_81_]
		    = bzip.anIntArray898[i_81_ - 1];
	    for (int i_82_ = 1; i_82_ <= 256; i_82_++)
		bzip.anIntArray922[i_82_]
		    += bzip.anIntArray922[i_82_ - 1];
	    for (int i_83_ = 0; i_83_ < i_62_; i_83_++) {
		i_33_ = (byte) (Client.anIntArray679[i_83_] & 0xff);
		Client.anIntArray679[(bzip.anIntArray922
				       [i_33_ & 0xff])]
		    |= i_83_ << 8;
		bzip.anIntArray922[i_33_ & 0xff]++;
	    }
	    bzip.anInt912
		= Client.anIntArray679[bzip.anInt921] >> 8;
	    bzip.anInt896 = 0;
	    bzip.anInt912
		= Client.anIntArray679[bzip.anInt912];
	    bzip.anInt925
		= (byte) (bzip.anInt912 & 0xff);
	    bzip.anInt912 >>= 8;
	    bzip.anInt896++;
	    bzip.anInt923 = i_62_;
	    method771(bzip);
	    if ((bzip.anInt896
		 == bzip.anInt923 + 1)
		&& bzip.anInt889 == 0)
		bool_31_ = true;
	    else
		bool_31_ = false;
	}
    }
    
    public static void reset() {
	entry = null;
    }
    
    private static final byte method774(BZip2BlockEntry bzip) {
	return (byte) method779(8, bzip);
    }
    
    private static final void method775(int[] is, int[] is_84_, int[] is_85_,
					byte[] is_86_, int i, int i_87_,
					int i_88_) {
	int i_89_ = 0;
	for (int i_90_ = i; i_90_ <= i_87_; i_90_++) {
	    for (int i_91_ = 0; i_91_ < i_88_; i_91_++) {
		if (is_86_[i_91_] == i_90_) {
		    is_85_[i_89_] = i_91_;
		    i_89_++;
		}
	    }
	}
	for (int i_92_ = 0; i_92_ < 23; i_92_++)
	    is_84_[i_92_] = 0;
	for (int i_93_ = 0; i_93_ < i_88_; i_93_++)
	    is_84_[is_86_[i_93_] + 1]++;
	for (int i_94_ = 1; i_94_ < 23; i_94_++)
	    is_84_[i_94_] += is_84_[i_94_ - 1];
	for (int i_95_ = 0; i_95_ < 23; i_95_++)
	    is[i_95_] = 0;
	int i_96_ = 0;
	for (int i_97_ = i; i_97_ <= i_87_; i_97_++) {
	    i_96_ += is_84_[i_97_ + 1] - is_84_[i_97_];
	    is[i_97_] = i_96_ - 1;
	    i_96_ <<= 1;
	}
	for (int i_98_ = i + 1; i_98_ <= i_87_; i_98_++)
	    is_84_[i_98_] = (is[i_98_ - 1] + 1 << 1) - is_84_[i_98_];
    }
    
    static final int decompress(byte[] is, int i, byte[] is_99_, int i_100_,
			       int i_101_) {
	synchronized (entry) {
	    entry.aByteArray904 = is_99_;
	    entry.anInt888 = i_101_;
	    entry.aByteArray894 = is;
	    entry.anInt902 = 0;
	    entry.anInt908 = i;
	    entry.anInt917 = 0;
	    entry.anInt915 = 0;
	    entry.anInt900 = 0;
	    entry.anInt887 = 0;
	    method772(entry);
	    i -= entry.anInt908;
	    entry.aByteArray904 = null;
	    entry.aByteArray894 = null;
	    int i_102_ = i;
	    return i_102_;
	}
    }
    
    private static final byte method777(BZip2BlockEntry bzip) {
	return (byte) method779(1, bzip);
    }
    
    private static final void method778(BZip2BlockEntry bzip) {
	bzip.anInt905 = 0;
	for (int i = 0; i < 256; i++) {
	    if (bzip.aBooleanArray885[i]) {
		bzip.aByteArray906[bzip.anInt905]
		    = (byte) i;
		bzip.anInt905++;
	    }
	}
    }
    
    private static final int method779(int i, BZip2BlockEntry bzip) {
	int i_103_;
	for (;;) {
	    if (bzip.anInt917 >= i) {
		int i_104_ = ((bzip.anInt915
			       >> bzip.anInt917 - i)
			      & (1 << i) - 1);
		bzip.anInt917 -= i;
		i_103_ = i_104_;
		break;
	    }
	    bzip.anInt915
		= (bzip.anInt915 << 8
		   | (bzip.aByteArray904
		      [bzip.anInt888]) & 0xff);
	    bzip.anInt917 += 8;
	    bzip.anInt888++;
	    bzip.anInt900++;
	}
	return i_103_;
    }
}
