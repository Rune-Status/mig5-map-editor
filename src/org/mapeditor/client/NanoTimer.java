package org.mapeditor.client;

/* Class58_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class NanoTimer extends Timer {
	
    private long time;
    
    final int sleep(int sleepTimer, int i_1_) {
		long sleep = (long) sleepTimer * 1000000L;
		long diff = time - System.nanoTime();
		int count = 0;
		if (diff < sleep)
		    diff = sleep;
		Client.sleep(diff / 1000000L);
		long l_4_;
		for (l_4_ = System.nanoTime(); count < 10 && (count < 1 || l_4_ > time); time += (long) i_1_ * 1000000L)
			count++;
		if (time < l_4_)
			time = l_4_;
		//System.out.println(count);
		return count;
    }
    
    NanoTimer() {
    	start();
    }
    
    final void start() {
    	time = System.nanoTime();
    }
    
    final void reset() {
    	start();
    }
}