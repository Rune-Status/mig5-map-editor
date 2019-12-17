package org.mapeditor.client;

/* Class34 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class PlayerAppearance {
	
    private long equipmentHash;
    private int[] equipmentIds;
    private int npcId;
    boolean isFemale;
    private long cachedModelhash;
    private int[] colors;
    
    final void changeColors(boolean bool, int id) {
    	int color = colors[id];
    	if (!bool) {
    		if (--color < 0)
    			color = Client.characterColors[id].length - 1;
    	} else if (++color >= Client.characterColors[id].length)
    		color = 0;
    	colors[id] = color;
    	updateEquipment();
    }
    
    final Model method695(Sequence idleAnimation, Sequence currentAnimation, int i, int currentFrame) {
		if (npcId != -1)
			return NpcDefinition.forID(npcId).method204(idleAnimation, currentAnimation, i, currentFrame);
		long hash = equipmentHash;
		int[] equipment = equipmentIds;
		if (currentAnimation != null && (currentAnimation.shieldDisplayed >= 0 || currentAnimation.weaponDisplayed >= 0)) {
			equipment = new int[12];
			for (int i_18_ = 0; i_18_ < 12; i_18_++)
				equipment[i_18_] = equipmentIds[i_18_];
			if (currentAnimation.shieldDisplayed >= 0) {
				hash += (long) ((currentAnimation.shieldDisplayed) - equipmentIds[5] << 8);
				equipment[5] = currentAnimation.shieldDisplayed;
			}
			if (currentAnimation.weaponDisplayed >= 0) {
				hash += (long) ((currentAnimation.weaponDisplayed) - equipmentIds[3] << 16);
				equipment[3] = currentAnimation.weaponDisplayed;
			}
		}
		Model cachedModel = ((Model) Client.playerAppearanceCache.get(hash));
		if (cachedModel == null) {
			boolean bool_19_ = false;
			for (int i_20_ = 0; i_20_ < 12; i_20_++) {
				int i_21_ = equipment[i_20_];
				if (i_21_ >= 256 && i_21_ < 512 && !IdentityKit.forID(i_21_ - 256).modelsExist())
					bool_19_ = true;
			}
			if (bool_19_) {
				if (cachedModelhash != -1L)
					cachedModel = ((Model) Client.playerAppearanceCache.get(cachedModelhash));
				if (cachedModel == null)
					return null;
			}
			if (cachedModel == null) {
				Model[] characterModels = new Model[12];
				int current = 0;
				for (int id = 0; id < 12; id++) {
					int equipId = equipment[id];
					if (equipId >= 256 && equipId < 512) {
						Model appearanceModel = IdentityKit.forID(equipId - 256).swapColors();
						if (appearanceModel != null)
							characterModels[current++] = appearanceModel;
					}
				}
				cachedModel = new Model(characterModels, current);
				for (int id = 0; id < 5; id++) {
					if (colors[id] != 0) {
						cachedModel.swapColors(Client.characterColors[id][0],(Client.characterColors[id][colors[id]]));
						if (id == 1)
							cachedModel.swapColors(Client.headColors[0], Client.headColors[colors[id]]);
					}
				}
				cachedModel.skin();
				cachedModel.preProcess(64, 850, -30, -50, -30, true);
				Client.playerAppearanceCache.put(cachedModel, hash);
				cachedModelhash = hash;
			}
		}
		Model animatedModel;
		if (currentAnimation != null || idleAnimation != null) {
			if (currentAnimation != null && idleAnimation != null)
				animatedModel = currentAnimation.method221(cachedModel, idleAnimation, currentFrame, i);
			else if (currentAnimation == null)
				animatedModel = idleAnimation.method217(cachedModel, i);
			else
				animatedModel = currentAnimation.method217(cachedModel, currentFrame);
		} else
			return cachedModel;
		return animatedModel;
    }
    
    final Model method696() {
    	if (npcId != -1)
    		return NpcDefinition.forID(npcId).getHeadModel();
    	boolean stop = false;
    	for (int id = 0; id < 12; id++) {
    		int equipId = equipmentIds[id];
    		if (equipId >= 256 && equipId < 512 && !IdentityKit.forID(equipId - 256).headModelExist())
    			stop = true;
    	}
    	if (stop)
    		return null;
    	int current = 0;
    	Model[] characterModels = new Model[12];
		for (int id = 0; id < 12; id++) {
			int equipId = equipmentIds[id];
			if (equipId >= 256 && equipId < 512) {
				Model appearanceModel = IdentityKit.forID(equipId - 256).swapHeadColors();
				if (appearanceModel != null)
					characterModels[current++] = appearanceModel;
			}
		}
		Model completeHeadModel = new Model(characterModels, current);
		for (int id = 0; id < 5; id++) {
			if (colors[id] != 0) {
				completeHeadModel.swapColors(Client.characterColors[id][0], Client.characterColors[id][colors[id]]);
				if (id == 1)
					completeHeadModel.swapColors(Client.headColors[0], Client.headColors[colors[id]]);
			}
		}
		return completeHeadModel;
    }
    
    private final void updateEquipment() {
    	long currentHash = equipmentHash;
    	int shield = equipmentIds[5];
    	int hands = equipmentIds[9];
    	equipmentIds[5] = hands;
    	equipmentIds[9] = shield;
    	equipmentHash = 0L;
    	for (int id = 0; id < 12; id++) {
    		equipmentHash <<= 4;
    		if (equipmentIds[id] >= 256)
    			equipmentHash += (long) (equipmentIds[id] - 256);
    	}
    	if (equipmentIds[0] >= 256)
    		equipmentHash += (long) (equipmentIds[0] - 256 >> 4);
    	if (equipmentIds[1] >= 256)
    		equipmentHash += (long) (equipmentIds[1] - 256 >> 8);
    	for (int i_38_ = 0; i_38_ < 5; i_38_++) {
    		equipmentHash <<= 3;
    		equipmentHash += (long) colors[i_38_];
    	}
    	equipmentHash <<= 1;
    	PlayerAppearance instance = this;
    	instance.equipmentHash = instance.equipmentHash + (long) (!isFemale ? 0 : 1);
    	equipmentIds[5] = shield;
    	equipmentIds[9] = hands;
    	if (currentHash != 0L && equipmentHash != currentHash)
    		Client.playerAppearanceCache.remove(currentHash);
    }
    
    final void changeGender(boolean female) {
    	if (isFemale != female)
    		setAppearance(colors, -1, female, null);
    }
    
    final void changeAppearance(boolean bool, int i, boolean bool_127_) {
    	if (i != 1 || !isFemale) {
    		int i_128_ = equipmentIds[Client.anIntArray743[i]];
    		if (i_128_ != 0) {
    			i_128_ -= 256;
    			for (;;) {
    				if (!bool_127_) {
    					if (--i_128_ < 0)
    						i_128_ = Client.iKitLen - 1;
    				} else if (++i_128_ >= Client.iKitLen)
    					i_128_ = 0;
    				IdentityKit ikit = IdentityKit.forID(i_128_);
    				if (ikit != null
    						&& !ikit.noInterface) {
    					int i_129_ = i;
    					int i_130_;
    					int i_131_;
    					do {
    						int i_132_ = !isFemale ? 0 : 7;
    						i_130_ = i_129_ + i_132_;
    						i_131_ = ikit.partId;
    						break;
    					} while (false);
    					if (i_130_ == i_131_)
    						break;
    				}
    			}
    			if (!bool) {
    				equipmentIds[Client.anIntArray743[i]] = i_128_ + 256;
    				updateEquipment();
    			}
    		}
		}
    }
    
    final void sendDesignInfo(RSByteBuffer stream) {
    	stream.putByte(!isFemale ? 0 : 1);
    	for (int id = 0; id < 7; id++) {
    		int equipIds = equipmentIds[Client.anIntArray743[id]];
    		if (equipIds == 0)
    			stream.putByte(-1);
    		else
    			stream.putByte(equipIds - 256);
    	}
    	for (int id = 0; id < 5; id++)
    		stream.putByte(colors[id]);
    }
    
    final int method703() {
    	if (npcId == -1)
    		return (equipmentIds[1] + ((equipmentIds[11] << 5) + ((equipmentIds[8] << 10) + (equipmentIds[0] << 15) + (colors[4] << 20) + (colors[0] << 25))));
    	return (NpcDefinition.forID(npcId).id) + 305419896;
    }
    
    final void setAppearance(int[] colors, int npcId, boolean isFemale, int[] equipment) {
    	if (equipment == null) {
    		equipment = new int[12];
    		for (int id = 0; id < 7; id++) {
    			for (int ikitId = 0; ikitId < Client.iKitLen; ikitId++) {
    				IdentityKit ikit = IdentityKit.forID(ikitId);
    				if (ikit != null && !ikit.noInterface) {
    					int i_141_ = id;
    					int i_142_;
    					int i_143_;
    					do {
    						int i_144_ = isFemale ? 7 : 0;
    						i_142_ = i_141_ + i_144_;
    						i_143_ = ikit.partId;
    						break;
    					} while (false);
    					if (i_142_ == i_143_) {
    						equipment[Client.anIntArray743[id]] = ikitId + 256;
    						break;
    					}
    				}
    			}
    		}
    	}
    	this.colors = colors;
    	this.isFemale = isFemale;
    	equipmentIds = equipment;
    	this.npcId = npcId;
    	updateEquipment();
    }
    
    public PlayerAppearance() {
    	/* empty */
    }

	public Model shadowModel(Sequence var0, int var1, int var2, int var3, boolean var4, int var5, Model var6, int var7, int var8, int var9, boolean var10, int var11, int var12, int var13) {
		// TODO Auto-generated method stub
		return null;
	}
}
