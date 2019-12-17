package org.mapeditor.client;

/* Class3_Sub3_Sub3_Sub5 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class AnimatedObject extends SceneModel
{
    private int anInt2056;
    private int cycle;
    private int frame;
    private int objectId;
    private Sequence sequence;
    private int anInt2064;
    private int direction;
    private int anInt2070;
    private int anInt2071;
    private int type;
    
    final Model getModel() {
    	if (sequence != null) {
    		int cycleDiff = Client.loopCycle - cycle;
    		if (cycleDiff > 100 && sequence.anInt1570 > 0)
    			cycleDiff = 100;
    		while (sequence.timer[frame] < cycleDiff) {
    			cycleDiff -= sequence.timer[frame];
    			frame++;
    			if (frame >= sequence.frames.length) {
    				frame -= sequence.anInt1570;
    				if (frame < 0 || (frame >= sequence.frames.length)) {
    					sequence = null;
    					break;
    				}
    			}
    		}
    		cycle = Client.loopCycle - cycleDiff;
    	}
    	ObjectDefinition objectDef = ObjectDefinition.forID(objectId);
    	if (objectDef.childrenIDs != null)
    		objectDef = objectDef.handleConfig();
    	if (objectDef == null)
    		return null;
    	return objectDef.method330(anInt2056, anInt2064, direction,
    			sequence, type,
    			frame, anInt2071, anInt2070);
    }
    
    AnimatedObject(int id, int type, int direction, int i_63_, int i_64_, int i_65_, int i_66_, int animationId, boolean bool) {
    	anInt2071 = i_66_;
    	anInt2056 = i_63_;
    	objectId = id;
    	anInt2070 = i_64_;
    	this.type = type;
    	this.direction = direction;
    	anInt2064 = i_65_;
    	if (animationId != -1) {
    		sequence = Sequence.forID(animationId);
    		cycle = Client.loopCycle - 1;
    		frame = 0;
    		if (bool && sequence.anInt1570 != -1) {
    			frame = (int) ((double) sequence.frames.length * Math.random());
    			cycle -= (int) (Math.random() * (double) sequence.timer[frame]);
    		}
   	 	}
    }
}
