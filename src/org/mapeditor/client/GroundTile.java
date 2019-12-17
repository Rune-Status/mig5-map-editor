package org.mapeditor.client;

/* Class3_Sub11 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public final class GroundTile extends Node {
	
    public InteractiveObject[] interactiveObjects = new InteractiveObject[5];
    public int z;
    public boolean aBoolean1260;
    public boolean aBoolean1261;
    public WallObject wallObject;
    public int anInt1263;
    public int interactiveObjectCount;
    public boolean aBoolean1266;
    public DecoratedTile decoratedTile;
    public int anInt1268;
    public int anInt1269;
    public CompoundTile shapedTile;
    public GroundDecoration groundDecoration;
    public int anInt1272;
    public int y;
    public int[] anIntArray1274;
    public WallDecoration wallDecoration;
    public int anInt1280;
    public GroundTile bridgeTile;
    public int x;
    public int anInt1286 = 0;
    public int anInt1288;
    
    public GroundTile(int z, int x, int y) {
    	anIntArray1274 = new int[5];
		this.x = x;
		this.y = y;
		this.anInt1269 = this.z = z;
    }
}
