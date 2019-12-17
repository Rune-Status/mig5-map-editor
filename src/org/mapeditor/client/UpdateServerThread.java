package org.mapeditor.client;

/* Class20 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class UpdateServerThread implements Runnable {
    
	public final void run() {
		try {
			for (;;) {
				UpdateServerNode class3_sub10;
				synchronized (Client.updateServerList) {
					class3_sub10 = (UpdateServerNode) Client.updateServerList.getFront();
				}
				if (class3_sub10 == null) {
					Client.sleep(100L);
					synchronized (Client.anObject821) {
						if (Client.anInt1465 <= 1) {
							Client.anInt1465 = 0;
							Client.anObject821.notifyAll();
						} else {
							Client.anInt1465--;
							continue;
						}
						break;
					}
				}
				do {
					if (class3_sub10.anInt1246 != 0) {
						if (class3_sub10.anInt1246 != 1)
							break;
						class3_sub10.data = class3_sub10.cache.get((int) class3_sub10.hash);
						synchronized (Client.updateServerList) {
							Client.aClass60_2164.insertBack(class3_sub10);
							break;
						}
					}
					class3_sub10.cache.put(class3_sub10.data, (int) class3_sub10.hash, class3_sub10.data.length);
					synchronized (Client.updateServerList) {
						class3_sub10.unlink();
					}
				} while (false);
				synchronized (Client.anObject821) {
					if (Client.anInt1465 <= 1) {
						Client.anInt1465 = 0;
						Client.anObject821.notifyAll();
						break;
					}
					Client.anInt1465 = 600;
				}
			}
		} catch (Exception exception) {
			Client.throwError(exception, null);
		}
    }
}
