package org.mapeditor.client;

/* Class1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class LookupTable {
	
    private int[] identTable;
    
    final int lookupIdentifier(int i) {
    	int i_25_ = identTable.length - 2;
    	int i_26_ = i << 1 & i_25_;
    	for (;;) {
    		int i_27_ = identTable[i_26_];
    		if (i_27_ == i)
    			return identTable[i_26_ + 1];
    		if (i_27_ == -1)
    			return -1;
    		i_26_ = i_25_ & i_26_ + 2;
    	}
    }
    
    LookupTable(int[] is) {
    	int i;
    	for (i = 1; i <= is.length + (is.length >> 1); i <<= 1) {
    		/* empty */
    	}
    	identTable = new int[i + i];
    	for (int i_29_ = 0; i_29_ < i + i; i_29_++)
    		identTable[i_29_] = -1;
    	for (int i_30_ = 0; i_30_ < is.length; i_30_++) {
    		int i_31_;
    		for (i_31_ = i - 1 & is[i_30_]; identTable[i_31_ + 1 + i_31_] != -1; i_31_ = i_31_ + 1 & i - 1) {
    			/* empty */
    		}
    		identTable[i_31_ + i_31_] = is[i_30_];
    		identTable[i_31_ + i_31_ + 1] = i_30_;
    	}
    }
    
}
