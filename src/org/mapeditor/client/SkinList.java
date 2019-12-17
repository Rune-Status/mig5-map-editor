package org.mapeditor.client;

/* Class3_Sub6 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class SkinList extends Node {
	
	int[] opcodes;
	int fileId;
	int[][] skins;
	private int length;

	SkinList(int id, byte[] buffer_) {
		fileId = id;
		RSByteBuffer buffer = new RSByteBuffer(buffer_);
		length = buffer.getUByte();
		skins = new int[length][];
		opcodes = new int[length];
		for (int i_11_ = 0; length > i_11_; i_11_++)
			opcodes[i_11_] = buffer.getUByte();
		for (int i_12_ = 0; length > i_12_; i_12_++)
			skins[i_12_] = new int[buffer.getUByte()];
		for (int i_13_ = 0; i_13_ < length; i_13_++) {
			for (int i_14_ = 0; i_14_ < skins[i_13_].length; i_14_++)
				skins[i_13_][i_14_] = buffer.getUByte();
		}
	}
}
