package org.mapeditor.client;

/* Class3_Sub3_Sub8 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class NpcDefinition extends NodeSub {
	
    int turn90CWAnimation;
    int varbitId;
    int varpId;
    int[] childrenIDs;
    private int width = 128;
    int turnValue;
    int headIcon;
    RSString[] actions;
    boolean canRightClick;
    private int[] model;
    int idleAnimation;
    boolean render;
    int turn180Animation;
    boolean displayOnMinimap;
    private int[] originalModelColors;
    private int shading;
    int id;
    int combatLevel;
    RSString name;
    int turn90CCAnimation;
    private int lightness;
    private int height;
    int size;
    int walkAnimation;
    private int[] headModels;
    private int[] modifiedModelColor;
    
    final Model method204(Sequence class3_sub3_sub9, Sequence class3_sub3_sub9_0_, int i, int i_2_) {
    	if (childrenIDs != null) {
    		NpcDefinition npcDef = handleNpcConfig();
    		if (npcDef == null)
    			return null;
    		return npcDef.method204(class3_sub3_sub9, class3_sub3_sub9_0_, i, i_2_);
    	}
    	Model cachedModel = (Model) Client.npcModelCache.get((long) id);
    	if (cachedModel == null) {
    		boolean stop = false;
    		for (int len = 0; model.length > len; len++) {
    			if (!Client.npcModelFetcher.cached(model[len], -1, 0))
    				stop = true;
    		}
    		if (stop)
    			return null;
    		Model[] models = new Model[model.length];
    		for (int len = 0; len < model.length; len++)
    			models[len] = Model.get(Client.npcModelFetcher, model[len], 0);
    		if (models.length == 1)
    			cachedModel = models[0];
    		else
    			cachedModel = new Model(models, models.length);
    		if (originalModelColors != null) {
    			for (int len = 0; len < originalModelColors.length; len++)
    				cachedModel.swapColors(originalModelColors[len], modifiedModelColor[len]);
    		}
    		cachedModel.skin();
    		cachedModel.preProcess(lightness + 64, shading + 850, -30, -50, -30, true);
    		Client.npcModelCache.put(cachedModel, (long) id);
    	}
    	Model class3_sub3_sub3_sub3_7_;
    	if (class3_sub3_sub9_0_ == null || class3_sub3_sub9 == null) {
    		if (class3_sub3_sub9_0_ == null) {
    			if (class3_sub3_sub9 != null)
    				class3_sub3_sub3_sub3_7_ = class3_sub3_sub9.method217(cachedModel, i);
    			else
    				class3_sub3_sub3_sub3_7_ = cachedModel.method150(true);
    		} else
    			class3_sub3_sub3_sub3_7_ = class3_sub3_sub9_0_.method217(cachedModel, i_2_);
    	} else
    		class3_sub3_sub3_sub3_7_ = class3_sub3_sub9_0_.method221(cachedModel, class3_sub3_sub9, i_2_, i);
    	if (width != 128 || height != 128)
    		class3_sub3_sub3_sub3_7_.scale(width, height, width);
    	return class3_sub3_sub3_sub3_7_;
    }
    
    final void readValues(RSByteBuffer stream) {
		for (;;) {
			int opcode = stream.getUByte();
			if (opcode == 0)
				break;
			readValues(stream, opcode);
		}
    }
    
    final boolean configExist() {
    	if (childrenIDs == null)
    		return true;
    	int i_9_ = -1;
    	if (varbitId != -1)
    		i_9_ = VarBit.getSettings(varbitId);
    	else if (varpId != -1)
    		i_9_ = Client.variousSettings[varpId];
    	if (i_9_ < 0
    			|| childrenIDs.length <= i_9_
    			|| childrenIDs[i_9_] == -1)
    		return false;
    	return true;
    }
    
    final NpcDefinition handleNpcConfig() {
		int i_12_ = -1;
		if (varbitId != -1)
		    i_12_ = VarBit.getSettings(varbitId);
		else if (varpId != -1)
		    i_12_ = Client.variousSettings[varpId];
		if (i_12_ < 0
		    || i_12_ >= childrenIDs.length
		    || childrenIDs[i_12_] == -1)
		    return null;
		return NpcDefinition.forID(childrenIDs[i_12_]);
    }
    
    final Model getHeadModel() {
    	if (childrenIDs != null) {
    		NpcDefinition npc = handleNpcConfig();
    		if (npc == null)
    			return null;
    		return npc.getHeadModel();
    	}
    	if (model == null)
    		return null;
    	boolean stop = false;
    	for (int len = 0; len < model.length; len++) {
    		if (!Client.npcModelFetcher.cached(model[len], -1, 0))
    			stop = true;
    	}
    	if (stop)
    		return null;
    	Model[] headmodels = new Model[model.length];
    	for (int len = 0; model.length > len; len++)
    		headmodels[len] = Model.get(Client.npcModelFetcher, model[len], 0);
    	Model headmodel;
    	if (headmodels.length != 1)
    		headmodel = new Model(headmodels, headmodels.length);
    	else
    		headmodel = headmodels[0];
    	if (originalModelColors != null) {
    		for (int len = 0; originalModelColors.length > len; len++)
    			headmodel.swapColors(originalModelColors[len], modifiedModelColor[len]);
    	}
    	return headmodel;
    }
    
    private final void readValues(RSByteBuffer stream, int opcode) {
    	if (opcode == 1) {
    		int count = stream.getUByte();
    		model = new int[count];
    		for (int id = 0; id < count; id++)
    			model[id] = stream.getUShort();
    	}
    	if (opcode == 2) {
    		name = stream.getString();
    	}
    	if (opcode == 12) {
    		size = stream.getUByte();
    	}
    	if (opcode == 13) {
    		idleAnimation = stream.getUShort();
    	}
    	if (opcode == 14) {
    		walkAnimation = stream.getUShort();
    	}
    	if (opcode == 17) {
			walkAnimation = stream.getUShort();
			turn180Animation = stream.getUShort();
			turn90CWAnimation = stream.getUShort();
			turn90CCAnimation = stream.getUShort();
    	}
    	if (opcode >= 30 && opcode < 35) {
    		actions[opcode - 30] = stream.getString();
    		if (actions[opcode - 30].equalsIgnoreCase(Client.hidden))
    			actions[opcode - 30] = null;
		}
    	if (opcode == 40) {
			int count = stream.getUByte();
			modifiedModelColor = new int[count];
		    originalModelColors = new int[count];
		    for (int id = 0; id < count; id++) {
		    	originalModelColors[id] = stream.getUShort();
				modifiedModelColor[id] = stream.getUShort();
		    }
		}
    	if (opcode == 60) {
			int count = stream.getUByte();
			headModels = new int[count];
			for (int id = 0; count > id; id++)
			    headModels[id] = stream.getUShort();
    	}
    	if (opcode == 93) {
    		displayOnMinimap = false;
    	}
    	if (opcode == 95) {
    		combatLevel = stream.getUShort();
    	}
    	if (opcode == 97) {
    		width = stream.getUShort();
    	}
    	if (opcode == 98) {
    		height = stream.getUShort();
    	}
    	if (opcode == 99) {
    		render = true;
    	}
    	if (opcode == 100) {
    		lightness = stream.getByte();
    	}
    	if (opcode == 101) {
    		shading  = stream.getByte() * 5;
    	}
    	if (opcode == 102) {
    		headIcon = stream.getUShort();
    	}
    	if (opcode == 103) {
    		turnValue = stream.getUShort();
    	}
    	if (opcode == 106) {
    		varbitId = stream.getUShort();
    		if (varbitId == 65535)
    			varbitId = -1;
    		varpId = stream.getUShort();
    		if (varpId == 65535)
    			varpId = -1;
    		int len = stream.getUByte();
    		childrenIDs = new int[len + 1];
    		for (int id = 0; len >= id; id++) {
    			childrenIDs[id] = stream.getUShort();
    			if (childrenIDs[id] == 65535)
    				childrenIDs[id] = -1;
    		}
    	}
    	if (opcode == 107) {
    		canRightClick = false;
    	}
    }
    
    static final NpcDefinition forID(int id) {
		NpcDefinition npcDef = (NpcDefinition) Client.npcDefCache.get((long) id);
		if (npcDef != null)
			return npcDef;
		byte[] buffer = Client.npcFetcher.getXteaBuffer(9, id);
		npcDef = new NpcDefinition();
		npcDef.id = id;
		if (buffer != null)
			npcDef.readValues(new RSByteBuffer(buffer));
		Client.npcDefCache.put(npcDef, (long) id);
		return npcDef;
	}
    
    static int[] someOffsets;
    
	private static final Sprite getImage(Model itemModel) {
		Sprite img = new Sprite(500, 500);
		int i_7 = Rasterizer.rasterizeCenterX;
		int i_8 = Rasterizer.rasterizeCenterY;
		int[] canPixels = Graphics2D.pixels;
		int canWidth = Graphics2D.width;
		int canHeight = Graphics2D.height;
		someOffsets = Rasterizer.initOffsets(someOffsets);

		Rasterizer.colorRestricted = false;
		Graphics2D.init2dCanvas(img.pixels, 500, 500);
		Graphics2D.fillRect(0, 0, 500, 500, 0);
		Rasterizer.method296();

		int yRotate = 1;
		int zoom = 500;
		int i_12 = ((Rasterizer.sineTable[yRotate] * zoom) >> 16);
		int y = ((Rasterizer.cosineTable[yRotate] * zoom) >> 16);

		itemModel.preProcess(64, 768, -50, -10, -50, true);
		itemModel.aBoolean1979 = true;
		itemModel.method144(0, 0, 0, yRotate, 0, i_12, y);
		Graphics2D.init2dCanvas(canPixels, canWidth, canHeight);
		Rasterizer.rasterizeCenterX = i_7;
		Rasterizer.rasterizeCenterY = i_8;
		Rasterizer.colorRestricted = true;
		img.trimHeight = 500;
		return img;
	}
	
	public static void dumpNpcImages() {
		for(int i = 0; i < 5000; i++) { 
			NpcDefinition npc = NpcDefinition.forID(i);
			if(npc == null)
				continue;
			Model model = npc.getHeadModel();
			if(model == null)
				continue;
			Sprite sprite = getImage(model);
			sprite.dumpImage(i+"");
		}
	}
    
    public NpcDefinition() {
		varpId = -1;
		canRightClick = true;
		idleAnimation = -1;
		actions = new RSString[5];
		render = false;
		turn90CWAnimation = -1;
		varbitId = -1;
		shading = 0;
		lightness = 0;
		combatLevel = -1;
		headIcon = -1;
		height = 128;
		turnValue = 32;
		walkAnimation = -1;
		displayOnMinimap = true;
		size = 1;
		turn180Animation = -1;
		name = Client.nullString;
		turn90CCAnimation = -1;
    }
}
