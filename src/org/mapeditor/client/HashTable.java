package org.mapeditor.client;

/* Class40 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public final class HashTable
{
    private long hash;
    private int size;
    private Node next;
    private Node[] cache;
    private Node lastIterated;
    private int current = 0;
    
    final Node pool() {
    	if (next == null)
    		return null;
    	for (Node currentNode = cache[(int) (hash & (long) (size - 1))]; next != currentNode; next = next.next) {
    		if (next.hash == hash) {
    			Node nextNode = next;
    			next = next.next;
    			return nextNode;
    		}
		}
		next = null;
		return null;
    }
    
    public final Node getNext() {
    	if (current > 0 && cache[current - 1] != lastIterated) {
    		Node node = lastIterated;
    		lastIterated = node.next;
    		return node;
    	}
    	while (size > current) {
    		Node node = cache[current++].next;
    		if (node != cache[current - 1]) {
    			lastIterated = node.next;
    			return node;
    		}
    	}
    	return null;
    }
    
    final Node get(long hash) {
    	this.hash = hash;
    	Node previous = cache[(int) ((long) (size - 1) & hash)];
    	for (next = previous.next; previous != next; next = next.next) {
    		if (next.hash == hash) {
    			Node next_ = next;
    			next = next.next;
    			return next_;
    		}
    	}
    	next = null;
    	return null;
    }
    
    public final Node getFirst() {
    	current = 0;
    	return getNext();
    }
    
    public final void put(Node node, long hash) {
    	if (node.previous != null)
    		node.unlink();
    	Node previous = cache[(int) (hash & (long) (size - 1))];
    	node.previous = previous.previous;
    	node.hash = hash;
    	node.next = previous;
    	node.previous.next = node;
    	node.next.previous = node;
    }
    
    HashTable(int size) {
    	cache = new Node[size];
    	this.size = size;
    	for (int id = 0; size > id; id++) {
    		Node node = cache[id] = new Node();
    		node.next = node;
    		node.previous = node;
    	}
    }
}