package org.mapeditor.client;

/* Class3_Sub3 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

class NodeSub extends Node {
	
    NodeSub nextSub;
    NodeSub previousSub;
    
    final void unlinkSub() {
    	if (previousSub != null) {
    		previousSub.nextSub = nextSub;
    		nextSub.previousSub = previousSub;
    		previousSub = null;
    		nextSub = null;
    	}
    }
    
    public NodeSub() {
	/* empty */
    }
}
