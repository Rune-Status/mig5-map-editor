package org.mapeditor.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

public class BufferUtils {
	
	public static byte[] toArray(ByteBuffer buffer) {
		byte[] payload = new byte[buffer.remaining()];
		buffer.get(payload);
		return payload;
	}
	
	public static byte[] imageToByteArray(BufferedImage image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "png", baos);
			baos.flush();
			byte[] payload = baos.toByteArray();
			baos.close();
			return payload;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
