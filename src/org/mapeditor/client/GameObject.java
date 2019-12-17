package org.mapeditor.client;

public class GameObject {
	
	public long hash() {
		long uid = (long) (x | y << 7
				| type << 14 | rotation << 20 | z << 25 | 0x40000000);
		uid |= (long) id << 32;
		return uid;
	}

	public int id, x, y, z, location, data, rotation, type;

	public GameObject(int id, int x, int y, int z, int type, int rotation) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
		this.type = type;
		this.rotation = rotation;
		int location = 0;
		location |= y & 0x3f;
		location |= (x & 0x3f) << 6;
		location |= (z & 0x3) << 12;
		this.location = location;
		int data = 0;
		data |= rotation & 0x3;
		data |= type << 2;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public int getLocation() {
		return location;
	}

	public int getData() {
		return data;
	}
	
	public void refreshLoc() {
		int location = 0;
		location |= y & 0x3f;
		location |= (x & 0x3f) << 6;
		location |= (z & 0x3) << 12;
		this.location = location;
	}
}
