package org.mapeditor.client;

public class TranslatableString {
	
	private RSString[] array;

	public TranslatableString(String english) {
		array = new RSString[] { RSString.createRSString(english) };
	}
	
	public RSString getString(int language) {
		return array[language];
	}
	
}
