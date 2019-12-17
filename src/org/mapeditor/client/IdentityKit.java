package org.mapeditor.client;

/* Class3_Sub3_Sub11 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class IdentityKit extends NodeSub
{
    int partId = -1;
    private int[] originalModelColors = new int[6];
    private int[] modifiedModelColors = new int[6];
    boolean noInterface = false;
    private int[] headModels = { -1, -1, -1, -1, -1 };
    private int[] models;
    
    final boolean headModelExist() {
    	boolean exist = true;
    	for (int id = 0; id < 5; id++) {
    		if (headModels[id] != -1 && !Client.iKitModelFetcher.cached(headModels[id], -1, 0))
    			exist = false;
    	}
    	return exist;
    }
    
    private final void readValues(RSByteBuffer stream, int opcode) {
    	if (opcode == 1) {
    		partId = stream.getUByte();
    	}
    	if (opcode == 2) {
    		int len = stream.getUByte();
    		models = new int[len];
    		for (int id = 0; len > id; id++)
    			models[id] = stream.getUShort();
    	}
    	if (opcode == 3) {
    		noInterface = true;
    	}
    	if (opcode >= 40 && opcode < 50) {
    		originalModelColors[opcode - 40] = stream.getUShort();
    	}
    	if (opcode >= 50 && opcode < 60) {
    		modifiedModelColors[opcode - 50] = stream.getUShort();
    	}
		if (opcode >= 60 && opcode < 70) {
			headModels[opcode - 60] = stream.getUShort();
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
    
    final Model swapHeadColors() {
    	Model[] models = new Model[5];
    	int count = 0;
    	for (int id = 0; id < 5; id++) {
    		if (headModels[id] != -1)
    			models[count++] = Model.get(Client.iKitModelFetcher, headModels[id], 0);
    	}
    	Model model = new Model(models, count);
    	for (int id = 0; id < 6; id++) {
    		if (originalModelColors[id] == 0)
    			break;
    		model.swapColors(originalModelColors[id], modifiedModelColors[id]);
    	}
    	return model;
    }
    
    final Model swapColors() {
    	if (models == null)
    		return null;
    	Model[] models = new Model[this.models.length];
    	for (int id = 0; this.models.length > id; id++)
    		models[id] = Model.get(Client.iKitModelFetcher, this.models[id], 0);
    	Model model;
    	if (models.length == 1)
    		model = models[0];
    	else
    		model = new Model(models, models.length);
    	for (int id = 0; id < 6; id++) {
    		if (originalModelColors[id] == 0)
    			break;
    		model.swapColors(originalModelColors[id], modifiedModelColors[id]);
    	}
		return model;
    }
    
    final boolean modelsExist() {
    	if (models == null)
    		return true;
    	boolean exist = true;
    	for (int id = 0; id < models.length; id++) {
    		if (!Client.iKitModelFetcher.cached(models[id], -1, 0))
    			exist = false;
    	}
    	return exist;
    }
    
    static final IdentityKit forID(int id) {
		IdentityKit ikit = (IdentityKit) Client.iKitCache.get((long) id);
		if (ikit != null)
			return ikit;
		byte[] buffer = Client.iKitFetcher.getXteaBuffer(3, id);
		ikit = new IdentityKit();
		if (buffer != null)
			ikit.readValues(new RSByteBuffer(buffer));
		Client.iKitCache.put(ikit, (long) id);
		return ikit;
	}

	public IdentityKit() {
	/* empty */
    }
}
