package org.mapeditor.client;

/* Class3_Sub3_Sub3_Sub1_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class Npc extends Entity {
	
    NpcDefinition npcDefinition;
    
    final boolean isVisible() {
		if (npcDefinition == null)
		    return false;
		return true;
    }
    
    final Model getModel() {
    	if (npcDefinition == null)
    		return null;
    	Sequence class3_sub3_sub9 = ((currentAnimationId != -1 && animationDelay == 0) ? Sequence.forID(currentAnimationId) : null);
    	Sequence class3_sub3_sub9_8_ = ((currentAnimation != -1 && ((idleAnimation != currentAnimation) || class3_sub3_sub9 == null)) ? Sequence.forID(currentAnimation) : null);
    	Model npcModel = (npcDefinition.method204(class3_sub3_sub9_8_, class3_sub3_sub9, anInt1870, currentAnimationFrameId));
    	if (npcModel == null)
    		return null;
    	npcModel.method151();
    	height = npcModel.height;
    	if (currentGraphic != -1 && currentGraphicFrame != -1) {
    		Model graphicModel = SpotAnimation.forID(currentGraphic).getModel(currentGraphicFrame);
    		if (graphicModel != null) {
    			Model[] models = { npcModel, graphicModel };
    			graphicModel.move(0, -currentGraphicHeight, 0);
    			npcModel = new Model(models, 2, true);
    		}
    	}
    	if (npcDefinition.size == 1)
    		npcModel.aBoolean1979 = true;
    	return npcModel;
    }
}
