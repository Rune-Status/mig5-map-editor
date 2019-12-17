package org.mapeditor.client;

/* Class30 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class Queue {
	
    private NodeSub head = new NodeSub();
    
    final void method671(NodeSub node) {
    	if (node.previousSub != null)
    		node.unlinkSub();
    	node.nextSub = head.nextSub;
    	node.previousSub = head;
    	node.previousSub.nextSub = node;
	    node.nextSub.previousSub = node;
    }
    
    final NodeSub method672(int i) {
    	NodeSub node = head.nextSub;
    	if (head == node)
    		return null;
    	return node;
    }
    
    final void insertBack(NodeSub node) {
    	if (node.previousSub != null)
    		node.unlinkSub();
    	node.previousSub = head.previousSub;
    	node.nextSub = head;
    	node.previousSub.nextSub = node;
		node.nextSub.previousSub = node;
    }
    
    final NodeSub popFront() {
    	NodeSub node = head.nextSub;
    	if (head == node)
    		return null;
    	node.unlinkSub();
    	return node;
    }
    
    public Queue() {
    	head.previousSub = head;
    	head.nextSub = head;
    }
}
