package org.mapeditor.client;

/* Class14 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class ArrayUtils {
	
    static final void arraycopy(byte[] src, int srcOff, byte[] dest, int destOff, int destLen) {
    	if (src == dest) {
    		if (srcOff == destOff)
    			return;
    		if (destOff > srcOff && destOff < srcOff + destLen) {
    			destLen--;
    			srcOff += destLen;
    			destOff += destLen;
    			destLen = srcOff - destLen;
    			destLen += 7;
    			while (srcOff >= destLen) {
    				dest[destOff--] = src[srcOff--];
    				dest[destOff--] = src[srcOff--];
    				dest[destOff--] = src[srcOff--];
    				dest[destOff--] = src[srcOff--];
    				dest[destOff--] = src[srcOff--];
    				dest[destOff--] = src[srcOff--];
    				dest[destOff--] = src[srcOff--];
    				dest[destOff--] = src[srcOff--];
    			}
    			destLen -= 7;
    			while (srcOff >= destLen)
    				dest[destOff--] = src[srcOff--];
    			return;
    		}
    	}
    	destLen += srcOff;
    	destLen -= 7;
		while (srcOff < destLen) {
		    dest[destOff++] = src[srcOff++];
		    dest[destOff++] = src[srcOff++];
		    dest[destOff++] = src[srcOff++];
		    dest[destOff++] = src[srcOff++];
		    dest[destOff++] = src[srcOff++];
		    dest[destOff++] = src[srcOff++];
		    dest[destOff++] = src[srcOff++];
		    dest[destOff++] = src[srcOff++];
		}
		destLen += 7;
		while (srcOff < destLen)
		    dest[destOff++] = src[srcOff++];
    }
    
}
