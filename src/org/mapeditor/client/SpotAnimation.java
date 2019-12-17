package org.mapeditor.client;

/* Class3_Sub3_Sub6 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class SpotAnimation extends NodeSub {
	
    private int scaleX;
    int animationId;
    int id;
    private int shading = 0;
    private int scaleY;
    private int modelId;
    private int[] originalModelColors;
    private int[] modifiedModelColors;
    private int direction;
    private int lightness;
    
    final Model getModel(int frameId) {
    	Model cachedModel = (Model) Client.spotAnimModelCache.get((long) id);
    	if (cachedModel == null) {
    		cachedModel = Model.get(Client.spotAnimationModelFetcher, modelId, 0);
    		if (cachedModel == null)
    			return null;
    		for (int id = 0; id < 6; id++) {
    			if (originalModelColors[0] != 0)
    				cachedModel.swapColors(originalModelColors[id], modifiedModelColors[id]);
    		}
    		cachedModel.skin();
    		cachedModel.preProcess(lightness + 64, shading + 850, -30, -50, -30, true);
    		Client.spotAnimModelCache.put(cachedModel, (long) id);
    	}
    	Model model;
    	if (animationId != -1 && frameId != -1)
    		model = Sequence.forID(animationId).animateSpotModel(cachedModel, frameId);
    	else
    		model = cachedModel.method127(true);
    	if (scaleX != 128 || scaleY != 128)
    		model.scale(scaleX, scaleY, scaleX);
    	if (direction != 0) {
    		if (direction == 90)
    			model.rotate90();
    		if (direction == 180) {
    			model.rotate90();
    			model.rotate90();
    		}
    		if (direction == 270) {
    			model.rotate90();
    			model.rotate90();
    			model.rotate90();
    		}
    	}
    	return model;
    }
    
    final void readValues(RSByteBuffer stream) {
    	for (;;) {
    		int opcode = stream.getUByte();
    		if (opcode == 0)
    			break;
    		readValues(stream, opcode);
    	}
    }
    
    private final void readValues(RSByteBuffer stream, int opcode) {
    	if (opcode == 1)
    		modelId = stream.getUShort();
    	else if (opcode == 2)
    		animationId = stream.getUShort();
    	else if (opcode == 4)
    		scaleX = stream.getUShort();
    	else if (opcode == 5)
    		scaleY = stream.getUShort();
    	else if (opcode == 6)
    		direction = stream.getUShort();
    	else if (opcode == 7)
    		lightness = stream.getUByte();
    	else if (opcode == 8)
    		shading = stream.getUByte();
    	else if (opcode >= 40 && opcode < 50)
    		originalModelColors[opcode - 40] = stream.getUShort();
    	else if (opcode >= 50 && opcode < 60)
    		modifiedModelColors[opcode - 50] = stream.getUShort();
    }
    
    static final SpotAnimation forID(int id) {
		SpotAnimation spotAnim = (SpotAnimation) Client.spotAnimCache.get((long) id);
		if (spotAnim != null)
			return spotAnim;
		byte[] buffer = Client.spotAnimationFetcher.getXteaBuffer(13, id);
		spotAnim = new SpotAnimation();
		spotAnim.id = id;
		if (buffer != null)
			spotAnim.readValues(new RSByteBuffer(buffer));
		Client.spotAnimCache.put(spotAnim, (long) id);
		return spotAnim;
	}

	public SpotAnimation() {
    	scaleX = 128;
    	scaleY = 128;
    	animationId = -1;
		originalModelColors = new int[6];
		direction = 0;
		modifiedModelColors = new int[6];
		lightness = 0;
    }
}
