package org.mapeditor.client;

/* Class3_Sub3_Sub3_Sub1_Sub2 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class Player extends Entity {
    int anInt2170;
    int skullIcon = -1;
    int anInt2172;
    int tileHeight;
    int anInt2174;
    int anInt2175;
    int skillLevel = 0;
    int anInt2177;
    RSString name;
    int anInt2179;
    PlayerAppearance appearance;
    int combatLevel;
    int prayerIcon;
    int anInt2183;
    int anInt2184;
    boolean isIdle;
    int team;
    Model aClass3_Sub3_Sub3_Sub3_2188;
    int anInt2189;
    
    final Model getModel() {
    	if (appearance == null)
    		return null;
    	Sequence normalAnimation = (currentAnimationId != -1 && animationDelay == 0) ? Sequence.forID(currentAnimationId) : null ;
    	Sequence idle = ((currentAnimation != -1 && !isIdle && ((idleAnimation != currentAnimation) || normalAnimation == null)) ? Sequence.forID(currentAnimation) : null);
    	Model playerModel = appearance.method695(idle, normalAnimation, anInt1870, currentAnimationFrameId);
    	if (playerModel == null)
    		return null;
    	Model shadowModel = appearance.shadowModel(idle != null? idle : normalAnimation, tileHeight, 0, 1, false, 240, playerModel, 160, this.y, this.x, this.aBoolean1914, 0, idle != null ? this.anInt1870 : this.currentAnimationFrameId, 0);
    	playerModel.method151();
    	height = playerModel.height;
    	if (!isIdle && currentGraphic != -1 && currentGraphicFrame != -1) {
    		Model spotanimModel = SpotAnimation.forID(currentGraphic).getModel(currentGraphicFrame);
    		if (spotanimModel != null) {
    			spotanimModel.move(0, -currentGraphicHeight, 0);
    			Model[] models = { playerModel, spotanimModel, shadowModel };
    			playerModel = new Model(models, 2, true);
		    }
    	}
    	if (!isIdle && (aClass3_Sub3_Sub3_Sub3_2188 != null)) {
    		System.out.println("here: "+anInt2175+":"+anInt2184+":"+Client.loopCycle);
    		if (anInt2184 <= Client.loopCycle) {
    			aClass3_Sub3_Sub3_Sub3_2188 = null;
        		System.out.println("everyday im nullin");
    		}
    		if ((anInt2175 <= Client.loopCycle) && (anInt2184 > Client.loopCycle)) {
        		System.out.println("starting process");
    			Model class3_sub3_sub3_sub3_12_= aClass3_Sub3_Sub3_Sub3_2188;
    			class3_sub3_sub3_sub3_12_.move((anInt2179 - x), (anInt2189 - tileHeight), (anInt2174 - y));
    			Model[] class3_sub3_sub3_sub3s = { playerModel, class3_sub3_sub3_sub3_12_ };
    			if (turnDirection == 512) {
    				class3_sub3_sub3_sub3_12_.rotate90();
    				class3_sub3_sub3_sub3_12_.rotate90();
    				class3_sub3_sub3_sub3_12_.rotate90();
    			} else if (turnDirection == 1024) {
    				class3_sub3_sub3_sub3_12_.rotate90();
    				class3_sub3_sub3_sub3_12_.rotate90();
    			} else if (turnDirection == 1536) {
    				class3_sub3_sub3_sub3_12_.rotate90();
    			}
    			playerModel = new Model(class3_sub3_sub3_sub3s, 2, true);
    			if (turnDirection == 512) {
    				class3_sub3_sub3_sub3_12_.rotate90();
    			} else if (turnDirection == 1024) {
					class3_sub3_sub3_sub3_12_.rotate90();
					class3_sub3_sub3_sub3_12_.rotate90();
    			} else if (turnDirection == 1536) {
    				class3_sub3_sub3_sub3_12_.rotate90();
    				class3_sub3_sub3_sub3_12_.rotate90();
    				class3_sub3_sub3_sub3_12_.rotate90();
    			}
    			class3_sub3_sub3_sub3_12_.move((x - anInt2179), (tileHeight - anInt2189), (y - anInt2174));
    		}
		}
    	playerModel.aBoolean1979 = true;
    	return playerModel;
    }
    
    final boolean isVisible() {
		if (appearance == null)
		    return false;
		return true;
    }
    
    final void updatePlayer(RSByteBuffer stream) {
    	stream.index = 0;
    	int info = stream.getUByte();
    	boolean gender = (info & 0x1) != 0;
    	prayerIcon = stream.getByte();
    	int[] equipmentIds = new int[12];
    	int npcId = -1;
    	skullIcon = stream.getByte();
    	team = 0;
    	for (int id = 0; id < 12; id++) {
    		int equipId = stream.getUByte();
    		if (equipId == 0)
    			equipmentIds[id] = 0;
    		else {
    			int off = stream.getUByte();
    			equipmentIds[id] = (equipId << 8) + off;
    			if (id == 0 && equipmentIds[0] == 65535) {
    				npcId = stream.getUShort();
    				break;
    			}
    		}
    	}
    	int[] colors = new int[5];
    	for (int id = 0; id < 5; id++) {
    		int color = stream.getUByte();
    		if (color < 0 || Client.characterColors[id].length <= color)
    			color = 0;
    		colors[id] = color;
    	}
    	idleAnimation = stream.getUShort();
    	if (idleAnimation == 65535)
    		idleAnimation = -1;
    	turnAnimation = stream.getUShort();
    	if (turnAnimation == 65535)
    		turnAnimation = -1;
		walkAnimation = stream.getUShort();
		if (walkAnimation == 65535)
			walkAnimation = -1;
		turn180Animation = stream.getUShort();
		if (turn180Animation == 65535)
			turn180Animation = -1;
		turn90CWAnimation = stream.getUShort();
		if (turn90CWAnimation == 65535)
			turn90CWAnimation = -1;
		turn90CCAnimation = stream.getUShort();
		if (turn90CCAnimation == 65535)
			turn90CCAnimation = -1;
		runAnimation = stream.getUShort();
		if (runAnimation == 65535)
			runAnimation = -1;
		name = RSString.toString(stream.getLong()).upperCase();
		combatLevel = stream.getUByte();
		skillLevel = stream.getUShort();
		if (appearance == null)
			appearance = new PlayerAppearance();
		appearance.setAppearance(colors, npcId, gender, equipmentIds);
    }
    
    Player() {
		anInt2175 = 0;
		combatLevel = 0;
		prayerIcon = -1;
		isIdle = false;
		team = 0;
		anInt2184 = 0;
    }
}
