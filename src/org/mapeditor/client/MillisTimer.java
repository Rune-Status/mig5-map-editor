package org.mapeditor.client;

/* Class58_Sub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class MillisTimer extends Timer {
	
    private int anInt1400;
    private int pos;
    private int sleepTimer;
    private long[] cache = new long[10];
    private long time;
    private int anInt1414;
    
    final void start() {
		anInt1414 = 256;
		sleepTimer = 1;
		anInt1400 = 0;
		time = System.currentTimeMillis();
		for (int i = 0; i < 10; i++)
		    cache[i] = time;
    }
    
    final void reset() {
    	for (int id = 0; id < 10; id++)
    		cache[id] = 0L;
    }
    
    final int sleep(int sleetTimer, int i_4_) {
    	int i_5_ = anInt1414;
		anInt1414 = 300;
		int i_6_ = sleepTimer;
		sleepTimer = 1;
		time = System.currentTimeMillis();
		if (cache[pos] != 0L) {
			if (cache[pos] < time)
				anInt1414 = (int) ((long) (i_4_ * 2560) / (time - cache[pos]));
		} else {
		    anInt1414 = i_5_;
		    sleepTimer = i_6_;
		}
		if (anInt1414 < 25)
		    anInt1414 = 25;
		if (anInt1414 > 256) {
		    anInt1414 = 256;
		    sleepTimer = (int) ((long) i_4_ - (time - cache[pos]) / 10L);
		}
		if (sleepTimer > i_4_)
		    sleepTimer = i_4_;
		cache[pos] = time;
		pos = (pos + 1) % 10;
		if (sleepTimer > 1) {
			for (int i_7_ = 0; i_7_ < 10; i_7_++) {
				if (cache[i_7_] != 0L)
					cache[i_7_] = cache[i_7_] + (long) sleepTimer;
			}
		}
		int i_8_ = 0;
		if (sleetTimer > sleepTimer)
			sleepTimer = sleetTimer;
		Client.sleep((long) sleepTimer);
		while (anInt1400 < 256) {
		    anInt1400 += anInt1414;
		    i_8_++;
		}
		anInt1400 &= 0xff;
		return i_8_;
    }
    
    MillisTimer() {
    	start();
    }
}