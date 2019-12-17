package org.mapeditor.client;

/* Class18_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class CacheFileWorker extends FileSystem {
	
	private boolean requestOnDemand = false;
	private CacheFile cacheFile;
	private volatile boolean[] validFile;
	private int anInt1338;
	private CacheFile referenceFile;
	private int ondemandCrc = -1;
	private int index;
	private volatile boolean aBoolean1343 = false;
	
	private final void method594() {
		validFile = new boolean[entryBuffers.length];
		for (int i_0_ = 0; i_0_ < validFile.length; i_0_++)
			validFile[i_0_] = false;
		if (cacheFile == null)
			aBoolean1343 = true;
		else {
			ondemandCrc = -1;
			for (int i_1_ = 0; i_1_ < validFile.length; i_1_++) {
				if (childSize[i_1_] > 0) {
					Client.method816(i_1_, cacheFile, this, 1);
					ondemandCrc = i_1_;
				}
			}
			if (ondemandCrc == -1)
				aBoolean1343 = true;
		}
	}

	private final int method595(int i_2_) {
		if (entryBuffers[i_2_] != null)
			return 100;
		if (validFile[i_2_])
			return 100;
		return Client.method117(index, i_2_);
	}

	final void method569(boolean bool, int i) {
		if (bool)
			method594();
		Client.method366(i, index);
	}

	final void method596(byte[] buffer, boolean requestNeeded, int i_3_, CacheFile class28) {
		if (referenceFile == class28) {
			if (aBoolean1343)
				throw new RuntimeException();
			if (buffer == null)
				Client.method164(true, this, anInt1338, (byte) 0,
						255, index);
			else {
				Client.aCRC32_377.reset();
				Client.aCRC32_377.update(buffer, 0, buffer.length);
				int i_4_ = (int) Client.aCRC32_377.getValue();
				//if (anInt1338 != i_4_)
				//	Client.method164(true, this, anInt1338, (byte) 0,
				//			255, index);
				//else {
					unpack(buffer);
					method594();
				//}
			}
		} else {
			if (!requestNeeded && i_3_ == ondemandCrc)
				aBoolean1343 = true;
			if (buffer == null || buffer.length <= 2) {
				validFile[i_3_] = false;
				if (requestOnDemand || requestNeeded)
					Client.method164(requestNeeded, this, crc[i_3_], (byte) 2, index, i_3_);
			} else {
				Client.aCRC32_377.reset();
				Client.aCRC32_377.update(buffer, 0, buffer.length - 2);
				int i_5_ = (int) Client.aCRC32_377.getValue();
				int i_6_ = ((buffer[buffer.length - 1] & 0xff) + ((buffer[buffer.length - 2] & 0xff) << 8));
				//if (i_5_ != crc[i_3_] || revision[i_3_] != i_6_) {
				//	validFile[i_3_] = false;
				//	if (requestOnDemand || requestNeeded)
				//		Client.method164(requestNeeded, this, (crc[i_3_]), (byte) 2, index, i_3_);
				//} else {
					validFile[i_3_] = true;
					if (requestNeeded)
						entryBuffers[i_3_] = buffer;
				//}
			}
		}
	}

	CacheFileWorker(CacheFile class28, CacheFile class28_7_, int i, boolean clearEntryBuffer,
			boolean clearChildBuffer, boolean onDemand) {
		super(clearEntryBuffer, clearChildBuffer);
		cacheFile = class28;
		requestOnDemand = onDemand;
		referenceFile = class28_7_;
		index = i;
		Client.method682(this, index);
	}

	final void setIndexCrc(int i_10_) {
		anInt1338 = i_10_;
		//if (referenceFile != null)
			Client.method75(index, this, referenceFile);
		//else
		//	Client.method164(true, this, anInt1338, (byte) 0,
		//			255, index);
	}

	final void method585(int i_11_) {
		if (cacheFile == null || validFile == null
				|| !validFile[i_11_])
			Client.method164(true, this, (crc[i_11_]),
					(byte) 2, index,
					i_11_);
		else
			Client.method75(i_11_, this, cacheFile);
	}

	final int method600() {
		if (aBoolean1343)
			return 100;
		if (entryBuffers != null)
			return 99;
		int i_114_ = Client.method117(255, index);
		if (i_114_ >= 100)
			i_114_ = 99;
		return i_114_;
	}

	final int method601() {
		int i_115_ = 0;
		int i_116_ = 0;
		for (int i_117_ = 0; i_117_ < entryBuffers.length; i_117_++) {
			if (childSize[i_117_] > 0) {
				i_115_ += 100;
				i_116_ += method595(i_117_);
			}
		}
		if (i_115_ == 0)
			return 100;
		int i_118_ = i_116_ * 100 / i_115_;
		return i_118_;
	}

	final void method602(byte[] buffer, int i, boolean bool, boolean bool_119_) {
		if (bool) {
			if (aBoolean1343)
				throw new RuntimeException();
			if (referenceFile != null)
				Client.method37(referenceFile, buffer, index);
			unpack(buffer);
			method594();
		} else {
			buffer[buffer.length - 2] = (byte) (revision[i] >> 8);
			buffer[buffer.length - 1] = (byte) revision[i];
			if (cacheFile != null) {
				Client.method37(cacheFile, buffer, i);
				validFile[i] = true;
			}
			if (bool_119_)
				entryBuffers[i] = buffer;
		}
	}
}
