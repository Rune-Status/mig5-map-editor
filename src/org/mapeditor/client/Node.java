package org.mapeditor.client;

/* Class3 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

class Node {
	
    Node previous;
    long hash;
    Node next;
    
    public Node() {
    	/* empty */
    }
    
    final void unlink() {
    	if (previous != null) {
    		previous.next = next;
    		next.previous = previous;
    		next = null;
    		previous = null;
    	}
    }
}
