package org.mapeditor.client;

/* RuntimeException_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

@SuppressWarnings("serial")
final class RuntimeException_Sub1 extends RuntimeException {
	
	Throwable throwable;
    String errorMessage;
    
    RuntimeException_Sub1(Throwable throwable, String string) {
		errorMessage = string;
		this.throwable = throwable;
    }
}
