package org.mapeditor.client;

/* Class29 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class Animation
{
    int[] ids;
    SkinList skinList = null;
    private static int[] privateVertexX = new int[500];
    private static int[] privateVertexZ = new int[500];;
    int[] vertexX;
    int[] vertexZ;
    boolean reloadModel;
    int arrayLen = -1;
    private static int[] privateIds = new int[500];
    private static int[] privateVertexY = new int[500];
    int[] vertexY;
    
    public static void reset() {
		privateIds = null;
		privateVertexX = null;
		privateVertexY = null;
		privateVertexZ = null;
    }
    
    Animation(SkinList skinlist, byte[] buffer) {
		reloadModel = false;
		skinList = skinlist;
		RSByteBuffer class3_sub12 = new RSByteBuffer(buffer);
		RSByteBuffer vertexBuffer = new RSByteBuffer(buffer);
		class3_sub12.index = 2;
		int loopCount = class3_sub12.getUByte();
		int i_1_ = -1;
		int len = 0;
		vertexBuffer.index = class3_sub12.index + loopCount;
		for (int id = 0; id < loopCount; id++) {
			int opcode = class3_sub12.getUByte();
			if (opcode > 0) {
				if (skinList.opcodes[id] != 0) {
					for (int id_ = id - 1; id_ > i_1_; id_--) {
						if (skinList.opcodes[id_] == 0) {
							privateIds[len] = id_;
							privateVertexX[len] = 0;
							privateVertexY[len] = 0;
							privateVertexZ[len] = 0;
							len++;
							break;
						}
					}
				}
				privateIds[len] = id;
				int vertex = 0;
				if (skinList.opcodes[id] == 3)
					vertex = 128;
				if ((opcode & 0x1) != 0)
					privateVertexX[len] = vertexBuffer.getSmart();
				else
				    privateVertexX[len] = vertex;
				if ((opcode & 0x2) != 0)
					privateVertexY[len] = vertexBuffer.getSmart();
				else
					privateVertexY[len] = vertex;
				if ((opcode & 0x4) != 0)
					privateVertexZ[len] = vertexBuffer.getSmart();
				else
					privateVertexZ[len] = vertex;
				i_1_ = id;
				len++;
				if (skinList.opcodes[id] == 5)
					reloadModel = true;
			}
		}
		if (vertexBuffer.index != buffer.length)
			throw new RuntimeException();
		arrayLen = len;
		ids = new int[len];
		vertexX = new int[len];
		vertexY = new int[len];
		vertexZ = new int[len];
		for (int id = 0; id < len; id++) {
		    ids[id] = privateIds[id];
		    vertexX[id] = privateVertexX[id];
		    vertexY[id] = privateVertexY[id];
		    vertexZ[id] = privateVertexZ[id];
		}
    }
}
