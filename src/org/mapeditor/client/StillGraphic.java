package org.mapeditor.client;

/* Class3_Sub3_Sub3_Sub4 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class StillGraphic extends SceneModel
{
    int x;
    int cycle;
    private int anInt2032 = 0;
    int y;
    private int id;
    int height;
    boolean aBoolean2042 = false;
    private Sequence sequence;
    private int currentFrame = 0;
    int z;
    
    final Model getModel() {
    	SpotAnimation spotAnim = SpotAnimation.forID(id);
   	 	Model model;
   	 	if (!aBoolean2042)
   	 		model = spotAnim.getModel(currentFrame);
   	 	else
   	 		model = spotAnim.getModel(-1);
   	 	if (model == null)
   	 		return null;
   	 	return model;
    }
    
    final void method157(int i) {
    	if (!aBoolean2042) {
    		anInt2032 += i;
    		while (sequence.timer[currentFrame] < anInt2032) {
    			anInt2032 -= sequence.timer[currentFrame];
    			currentFrame++;
    			if (currentFrame >= sequence.frames.length) {
    				aBoolean2042 = true;
    				break;
    			}
    		}
    	}
    }
    
    StillGraphic(int gfxId, int z, int x, int y, int gfxZ, int delay, int loopCycle) {
		id = gfxId;
		height = gfxZ;
		this.z = z;
		cycle = delay + loopCycle;
		this.x = x;
		this.y = y;
		int animationId = SpotAnimation.forID(id).animationId;
		if (animationId != -1) {
		    aBoolean2042 = false;
		    sequence = Sequence.forID(animationId);
		} else
		    aBoolean2042 = true;
    }
}
