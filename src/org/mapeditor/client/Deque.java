package org.mapeditor.client;

/* Class60 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class Deque {
	
    Node head = new Node();
    private Node current;
    
    final Node getBack() {
    	Node node = head.previous;
    	if (node == head) {
    		current = null;
    		return null;
    	}
    	current = node.previous;
    	return node;
    }
    
    final Node getFront() {
    	Node node = head.next;
    	if (node == head) {
    		current = null;
    		return null;
    	}
    	current = node.next;
    	return node;
    }
    
    final Node popLast() {
		Node node = head.previous;
		if (node == head)
		    return null;
		node.unlink();
		return node;
    }
    
    final void insertFront(Node node) {
    	if (node.previous != null)
    		node.unlink();
    	node.next = head.next;
    	node.previous = head;
    	node.previous.next = node;
    	node.next.previous = node;
    }
    
    final void insertBack(Node node) {
    	if (node.previous != null)
		    node.unlink();
    	node.next = head;
    	node.previous = head.previous;
	    node.previous.next = node;
	    node.next.previous = node;
    }
    
    final void method894(Node class3, Node class3_27_) {
    	if (class3.previous != null)
    		class3.unlink();
    	class3.next = class3_27_;
    	class3.previous = class3_27_.previous;
    	class3.previous.next = class3;
		class3.next.previous = class3;
    }
    
    final Node getNext() {
    	Node node = current;
    	if (head == node) {
    		current = null;
    		return null;
    	}
    	current = node.next;
    	return node;
    }
    
    final void clear() {
    	for (;;) {
    		Node node = head.next;
    		if (node == head)
    			break;
    		node.unlink();
    	}
    }
    
    final Node popFront() {
    	Node node = head.next;
    	if (node == head)
    		return null;
    	node.unlink();
    	return node;
    }
    
    final Node getPrevious() {
    	Node node = current;
    	if (head == node) {
    		current = null;
    		return null;
    	}
    	current = node.previous;
    	return node;
    }
    
    public Deque() {
    	head.next = head;
    	head.previous = head;
    }
}
