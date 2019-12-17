package org.mapeditor.client;

/* Class19 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public final class Cache {
	
    private NodeSub emptySub = new NodeSub();
    private int srcCapacity;
    private Queue recent = new Queue();
    private int capacity;
    private HashTable cache;
    
    public final void remove(long hash) {
    	NodeSub nodeSub = (NodeSub) cache.get(hash);
    	if (nodeSub != null) {
    		nodeSub.unlink();
    		nodeSub.unlinkSub();
    		capacity++;
    	}
    }
    
    final NodeSub get(long hash) {
    	NodeSub nodeSub = (NodeSub) cache.get(hash);
    	if (nodeSub != null)
   	 		recent.insertBack(nodeSub);
    	return nodeSub;
    }
    
    public final NodeSub getFirst() {
    	NodeSub nodeSub = (NodeSub) cache.getFirst();
    	if (nodeSub != null)
   	 		recent.insertBack(nodeSub);
    	return nodeSub;
    }
    
    final void clear() {
    	for (;;) {
    		NodeSub nodeSub = recent.popFront();
    		if (nodeSub == null)
    			break;
    		nodeSub.unlink();
    		nodeSub.unlinkSub();
    	}
    	capacity = srcCapacity;
    }
    
    public final void put(NodeSub nodeSub, long hash) {
    	if (capacity != 0)
    		capacity--;
    	else {
    		NodeSub sub = recent.popFront();
    		sub.unlink();
    		sub.unlinkSub();
    		if (sub == emptySub) {
    			sub = recent.popFront();
    			sub.unlink();
    			sub.unlinkSub();
    		}
    	}
    	cache.put(nodeSub, hash);
    	recent.insertBack(nodeSub);
    }
    
    Cache(int count) {
    	capacity = count;
    	srcCapacity = count;
    	int size;
    	for (size = 1; size + size < count; size += size) {
    		
    	}
    	cache = new HashTable(size);
    }

	public NodeSub getNext() {
    	NodeSub nodeSub = (NodeSub) cache.getNext();
    	if (nodeSub != null)
   	 		recent.insertBack(nodeSub);
    	return nodeSub;
	}
}
