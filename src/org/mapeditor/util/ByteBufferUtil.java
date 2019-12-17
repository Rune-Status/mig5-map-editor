package org.mapeditor.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * A utility class which contains {@link java.nio.ByteBuffer}-related methods.
 * @author Graham
 */
public final class ByteBufferUtil {

	/**
	 * Reads an unsigned tri byte from the specified buffer.
	 * @param buffer The buffer.
	 * @return The tri byte.
	 */
	public static int readUnsignedTriByte(ByteBuffer buffer) {
		return ((buffer.get() & 0xFF) << 16) | ((buffer.get() & 0xFF) << 8) | (buffer.get() & 0xFF);
	}
	
	public static void putTriByte(ByteBuffer buffer, int value) {
		buffer.put((byte) (value >> 16));
		buffer.putShort((short) value);
	}
	
	public static void putTriByte(DataOutputStream os, int value) throws IOException {
		os.writeByte(value >> 16);
		os.writeShort(value);
	}

	/**
	 * Reads a string from the specified buffer.
	 * @param buffer The buffer.
	 * @return The string.
	 */
	public static String readString(ByteBuffer buffer) {
		StringBuilder bldr = new StringBuilder();
		char c;
		while ((c = (char) buffer.get()) != 0) {
			bldr.append(c);
		}
		return bldr.toString();
	}

	/**
	 * Reads a smart int from the specified buffer.
	 * @param buffer The Buffer.
	 * @return The smart int.
	 */
	public static int readSmart(ByteBuffer buffer) {
		int position = buffer.position();
		int peek = buffer.get() & 0xFF;
		buffer.position(position);
		if (peek < 128) {
			return buffer.get() & 0xFF;
		} else {
			return (buffer.getShort() & 0xFFFF) - 32768;
		}
	}
	
	public static int readSmart2(ByteBuffer buffer) {
        int value = 0;
        int i;
        for (i = readSmart(buffer); i == 32767; i = readSmart(buffer))
            value += 32767;
        value += i;
        return value;
    }
	
	public static void putSmart(ByteBuffer buffer, int value) {
		if(value < 128) {
			buffer.put((byte) value);
		} else {
			buffer.putShort((short) (value + 32768));
		}
	}
	
	public static void putSmart(DataOutputStream os, int value) throws IOException {
		if(value >= 0 && value < 128) {
			os.writeByte(value);
		} else {
			os.writeShort(value + 32768);
		}
	}

	/**
	 * Default private constructor to prevent instantiation.
	 */
	private ByteBufferUtil() {

	}

}
