package org.mapeditor.client;

/* Class3_Sub3_Sub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class VarBit extends NodeSub {
	
    int setting;
    int end;
    int start;
    
    private final void readValues(RSByteBuffer stream, int opcode) {
    	if (opcode == 1) {
    		setting = stream.getUShort();
    		start = stream.getUByte();
    		end = stream.getUByte();
		}
    }
    
    final void readValues(RSByteBuffer stream) {
    	for (;;) {
    		int opcode = stream.getUByte();
    		if (opcode == 0)
    			break;
    		readValues(stream, opcode);
		}
    }
    
    static final VarBit forID(int id) {
		VarBit varBit = (VarBit) Client.varbitCache.get((long) id);
		if (varBit != null)
			return varBit;
		byte[] buffer = Client.varbitFetcher.getXteaBuffer(14, id);
		varBit = new VarBit();
		if (buffer != null)
			varBit.readValues(new RSByteBuffer(buffer));
		Client.varbitCache.put(varBit, (long) id);
		return varBit;
	}

	static final int getSettings(int id) {
		VarBit varbit = VarBit.forID(id);
		int start = varbit.start;
		int end = varbit.end;
		int setting = varbit.setting;
		int mask = Client.bitMasks[end - start];
		return mask & Client.variousSettings[setting] >> start;
	}
    
}
