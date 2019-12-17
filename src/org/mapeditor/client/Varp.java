package org.mapeditor.client;

/* Class3_Sub3_Sub7 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class Varp extends NodeSub {
	
    int anInt1520 = 0;
    
    final void readValues(RSByteBuffer stream) {
    	for (;;) {
    		int opcode = stream.getUByte();
    		if (opcode == 0)
    			break;
    		readValues(stream, opcode);
	    }
    }
    
    public Varp() {
    	/* empty */
    }
    
    private final void readValues(RSByteBuffer stream, int opcode) {
    	if (opcode == 5)
    		anInt1520 = stream.getUShort();
    }

	static final Varp forID(int id) {
		Varp varp = (Varp) Client.varpCache.get((long) id);
		if (varp != null)
			return varp;
		byte[] buffer = Client.varpFetcher.getXteaBuffer(16, id);
		varp = new Varp();
		if (buffer != null)
			varp.readValues(new RSByteBuffer(buffer));
		Client.varpCache.put(varp, (long) id);
		return varp;
	}
}
