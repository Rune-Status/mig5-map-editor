package org.mapeditor.client;

/* Class3_Sub3_Sub3 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

abstract class SceneModel extends NodeSub {
	
    int height = 1000;
    
    Model getModel() {
    	return null;
    }
    
    void render(int i, int i_2_, int i_3_, int i_4_, int i_5_, int i_6_,
			  int i_7_, int i_8_, int i_9_) {
    	Model model = getModel();
    	if (model != null) {
    		height = model.height;
    		model.render(i, i_2_, i_3_, i_4_, i_5_, i_6_, i_7_, i_8_, i_9_);
    	}
    }
    
    public SceneModel() {
	/* empty */
    }
}
