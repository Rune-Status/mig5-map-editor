package org.mapeditor.fs;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import java.nio.ByteBuffer;

import org.mapeditor.client.Client;
import org.mapeditor.client.GameObject;

public class Repacker {

	public static void repack() {
		try {
			List<GameObject> objects = new LinkedList<GameObject>();
			Iterator<Long> it = Client.objects.keySet().iterator();

			while (it.hasNext()) {
			  Long entry = it.next();

			  objects.add(Client.objects.get(entry));
			}
			int MAX_OBJECT_ID = 0;
			for (GameObject object : objects) {
				if (MAX_OBJECT_ID < object.getId()) {
					MAX_OBJECT_ID = object.getId();
				}
			}
			Map<Integer, LinkedList<GameObject>> OBJECT_GROUPS = new HashMap<Integer, LinkedList<GameObject>>();
			for (GameObject object : objects) {
				if (OBJECT_GROUPS.containsKey(object.getId())) {
					LinkedList<GameObject> list = OBJECT_GROUPS.get(object
							.getId());
					list.add(object);
				} else {
					OBJECT_GROUPS.put(object.getId(),
							new LinkedList<GameObject>());
					LinkedList<GameObject> list = OBJECT_GROUPS.get(object
							.getId());
					list.add(object);
				}
			}
			LinkedList<Object[]> sorted_map = new LinkedList<Object[]>();
			for (int id = 0; id < MAX_OBJECT_ID + 1; id++) {
				if (OBJECT_GROUPS.containsKey(id)) {
					LinkedList<GameObject> list = OBJECT_GROUPS.get(id);
					Map<Integer, Integer> OBJECT_COORDS = new HashMap<Integer, Integer>();
					LinkedList<int[]> OBJECT_SORTED = new LinkedList<int[]>();
					int MAX_COORDS = 0;
					for (GameObject object : list) {
						OBJECT_COORDS.put(object.getLocation(),
								object.getData());
						if (MAX_COORDS < object.getLocation()) {
							MAX_COORDS = object.getLocation();
						}
					}
					for (int coord = 0; coord < MAX_COORDS + 1; coord++) {
						if (OBJECT_COORDS.containsKey(coord)) {
							OBJECT_SORTED.add(new int[] { coord,
									OBJECT_COORDS.get(coord) });
						}
					}
					sorted_map.add(new Object[] { id, OBJECT_SORTED });
				}
			}
			Buffer buffer = new Buffer();
			int prev_id = -1;
			while (!sorted_map.isEmpty()) {
				Object[] object = sorted_map.remove(0);
				int id_current = (Integer) object[0];
				LinkedList<int[]> coords = (LinkedList<int[]>) object[1];
				int id = id_current - prev_id;
				prev_id = id_current;
				buffer.putSmart(id);
				int prev_coord = 0;
				while (!coords.isEmpty()) {
					int[] coord = coords.remove(0);
					int loc = (coord[0] - prev_coord) + 1;
					prev_coord = coord[0];
					buffer.putSmart(loc);
					buffer.put((byte) coord[1]);
				}
				buffer.putSmart(0);
			}
			buffer.putSmart(0);
			String fileName = Client.squareX+"_"+Client.squareY;
			RandomAccessFile raf = new RandomAccessFile(new File("./maps/l"+fileName+".new"), "rw");
			byte[] data = new byte[buffer.getBuffer().position()];
			((ByteBuffer) buffer.getBuffer().flip()).get(data);
			raf.write(data);
			raf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class Buffer {

		private ByteBuffer buffer;

		public Buffer() {
			buffer = ByteBuffer.allocateDirect(500000);
		}

		public Buffer(byte[] data) {
			buffer = ByteBuffer.wrap(data);
		}

		public byte get() {
			return buffer.get();
		}

		public int getShort() {
			return buffer.getShort();
		}

		public int getSmart() {
			int val = buffer.get() & 0xff;
			buffer.position(buffer.position() - 1);
			if (val < 128) {
				return (buffer.get() & 0xff);
			}
			return -32768 + (buffer.getShort() & 0xffff);
		}

		public void put(byte b) {
			buffer.put(b);
		}

		public void putShort(short s) {
			buffer.putShort(s);
		}

		public void putSmart(int val) {
			if (val >= 128) {
				putShort((short) (val + 32768));
			} else {
				put((byte) val);
			}
		}

		public ByteBuffer getBuffer() {
			return buffer;
		}
	}
}