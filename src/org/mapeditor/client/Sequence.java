package org.mapeditor.client;

import org.mapeditor.Main;

/* Class3_Sub3_Sub9 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class Sequence extends NodeSub {
	
    int shieldDisplayed;
    int speedupType;
    private int[] anIntArray1565;
    int priority = 5;
    int walkProperties;
    int anInt1570;
    int resetCycle = 99;
    int[] frames;
    private int[] interfaceFrames;
    int resetInPlay = 2;
    int[] timer;
    int weaponDisplayed;
    boolean aBoolean1593;
    
    final Model method217(Model model, int frame) {
    	frame = frames[frame];
    	AnimationLoader class3_sub3_sub10 = AnimationLoader.forID(frame >> 16);
    	frame &= 0xffff;
    	if (class3_sub3_sub10 == null)
    		return model.method150(true);
    	Model class3_sub3_sub3_sub3_1_ = model.method150(!class3_sub3_sub10.reloadModel(frame));
    	class3_sub3_sub3_sub3_1_.animate(class3_sub3_sub10, frame);
		return class3_sub3_sub3_sub3_1_;
    }
    
    final Model animateObjectModel(Model model, int direction, int frame) {
    	frame = frames[frame];
    	AnimationLoader class3_sub3_sub10 = AnimationLoader.forID(frame >> 16);
    	frame &= 0xffff;
    	if (class3_sub3_sub10 == null)
    		return model.method150(true);
    	Model model_ = model.method150(!class3_sub3_sub10.reloadModel(frame));
    	direction &= 0x3;
    	if (direction == 1)
    		model_.rotate270();
    	else if (direction == 2)
    		model_.rotate180();
    	else if (direction == 3)
    		model_.rotate90();
    	model_.animate(class3_sub3_sub10, frame);
    	if (direction == 1)
    		model_.rotate90();
    	else if (direction == 2)
    		model_.rotate180();
    	else if (direction == 3)
    		model_.rotate270();
    	return model_;
    }
    
    private final void readValues(RSByteBuffer stream, int opcode) {
    	if (opcode == 1) {
    		int len = Main.REVISION >= 474 ? stream.getUShort() : stream.getUByte();
    		timer = new int[len];
    		for (int id = 0; len > id; id++)
    			timer[id] = stream.getUShort();
    		frames = new int[len];
    		for (int id = 0; len > id; id++)
    			frames[id] = stream.getUShort();
    		for (int id = 0; id < len; id++)
    			frames[id] = ((stream.getUShort() << 16) + frames[id]);
    	}
    	if (opcode == 2) {
    		anInt1570 = stream.getUShort();
    	}
    	if (opcode == 3) {
    		int i_9_ = stream.getUByte();
    		anIntArray1565 = new int[i_9_ + 1];
    		for (int i_10_ = 0; i_10_ < i_9_; i_10_++)
    			anIntArray1565[i_10_] = stream.getUByte();
    		anIntArray1565[i_9_] = 9999999;
    	}
    	if (opcode == 4) {
    		aBoolean1593 = true;
    	}
    	if (opcode == 5) {
    	    priority = stream.getUByte();
    	}
	    if (opcode == 6) {
			shieldDisplayed = stream.getUShort();
	    }
		if (opcode == 7) {
		    weaponDisplayed = stream.getUShort();
		}
		if (opcode == 8) {
			resetCycle = stream.getUByte();
		}
		if (opcode == 9) {
			speedupType = stream.getUByte();
		}
		if (opcode == 10) {
		    walkProperties = stream.getUByte();
		}
		if (opcode == 11) {
			resetInPlay = stream.getUByte();
		}
     	if (opcode == 12) {
     		int len = stream.getUByte();
     		interfaceFrames = new int[len];
     		for (int id = 0; id < len; id++)
     			interfaceFrames[id] = stream.getUShort();
     		for (int id = 0; id < len; id++)
     			interfaceFrames[id] = ((stream.getUShort() << 16) + interfaceFrames[id]);
     	}
     	if (opcode == 13 && Main.REVISION >= 474) {
			int len = stream.getUByte();
			int [] anIntArray2757 = new int[len];
			for (int i_9_ = 0; i_9_ < len; i_9_++)
			    anIntArray2757[i_9_] = stream.getTriByte();
     	}
    }
    
    final Model method221(Model class3_sub3_sub3_sub3, Sequence class3_sub3_sub9_16_, int frame, int i_14_) {
    	frame = frames[frame];
    	AnimationLoader class3_sub3_sub10 = AnimationLoader.forID(frame >> 16);
    	frame &= 0xffff;
    	if (class3_sub3_sub10 == null)
    		return class3_sub3_sub9_16_.method217(class3_sub3_sub3_sub3, i_14_);
    	i_14_ = class3_sub3_sub9_16_.frames[i_14_];
    	AnimationLoader class3_sub3_sub10_17_ = AnimationLoader.forID(i_14_ >> 16);
    	i_14_ &= 0xffff;
    	if (class3_sub3_sub10_17_ == null) {
    		Model class3_sub3_sub3_sub3_18_ = class3_sub3_sub3_sub3.method150(!class3_sub3_sub10.reloadModel(frame));
    		class3_sub3_sub3_sub3_18_.animate(class3_sub3_sub10, frame);
    		return class3_sub3_sub3_sub3_18_;
		}
    	Model class3_sub3_sub3_sub3_19_ = class3_sub3_sub3_sub3.method150(!class3_sub3_sub10.reloadModel(frame) & !class3_sub3_sub10_17_.reloadModel(i_14_));
    	class3_sub3_sub3_sub3_19_.method142(class3_sub3_sub10, frame, class3_sub3_sub10_17_, i_14_, anIntArray1565);
    	return class3_sub3_sub3_sub3_19_;
    }
    
    final void readValues(RSByteBuffer stream) {
    	for (;;) {
    		int opcode = stream.getUByte();
    		if (opcode == 0)
    			break;
    		readValues(stream, opcode);
    	}
    }
    
    final Model animateInterfaceModel(Model class3_sub3_sub3_sub3, int i) {
    	int i_29_ = frames[i];
    	AnimationLoader class3_sub3_sub10 = AnimationLoader.forID(i_29_ >> 16);
    	i_29_ &= 0xffff;
    	if (class3_sub3_sub10 == null)
    		return class3_sub3_sub3_sub3.method150(true);
    	int i_30_ = 0;
    	AnimationLoader class3_sub3_sub10_32_ = null;
    	if (interfaceFrames != null && i < interfaceFrames.length) {
    		i_30_ = interfaceFrames[i];
    		class3_sub3_sub10_32_ = AnimationLoader.forID(i_30_ >> 16);
    		i_30_ &= 0xffff;
    	}
    	if (class3_sub3_sub10_32_ == null || i_30_ == 65535) {
    		Model class3_sub3_sub3_sub3_33_ = class3_sub3_sub3_sub3.method150(!class3_sub3_sub10.reloadModel(i_29_));
    		class3_sub3_sub3_sub3_33_.animate(class3_sub3_sub10, i_29_);
    		return class3_sub3_sub3_sub3_33_;
    	}
    	Model class3_sub3_sub3_sub3_34_ = class3_sub3_sub3_sub3.method150(!class3_sub3_sub10.reloadModel(i_29_) & !class3_sub3_sub10_32_.reloadModel(i_30_));
    	class3_sub3_sub3_sub3_34_.animate(class3_sub3_sub10, i_29_);
    	class3_sub3_sub3_sub3_34_.animate(class3_sub3_sub10_32_, i_30_);
    	return class3_sub3_sub3_sub3_34_;
    }
    
    final void applyProperties() {
    	if (walkProperties == -1) {
    		if (anIntArray1565 == null)
    			walkProperties = 0;
    		else
    			walkProperties = 2;
    	}
    	if (speedupType == -1) {
    		if (anIntArray1565 != null)
    			speedupType = 2;
    		else
    			speedupType = 0;
    	}
    }
    
    final Model animateSpotModel(Model model, int frame) {
    	frame = frames[frame];
    	AnimationLoader class3_sub3_sub10 = AnimationLoader.forID(frame >> 16);
    	frame &= 0xffff;
    	if (class3_sub3_sub10 == null)
    		return model.method127(true);
    	Model class3_sub3_sub3_sub3_37_ = model.method127(!class3_sub3_sub10.reloadModel(frame));
    	class3_sub3_sub3_sub3_37_.animate(class3_sub3_sub10, frame);
    	return class3_sub3_sub3_sub3_37_;
    }
    
    static final Sequence forID(int id) {
		Sequence sequence = (Sequence) Client.sequenceCache.get((long) id);
		if (sequence != null)
		    return sequence;
		byte[] buffer = Client.sequenceFetcher.getXteaBuffer(12, id);
		sequence = new Sequence();
		if (buffer != null)
		    sequence.readValues(new RSByteBuffer(buffer));
		sequence.applyProperties();
		Client.sequenceCache.put(sequence, (long) id);
		return sequence;
	}

	public Sequence() {
		speedupType = -1;
		shieldDisplayed = -1;
		anInt1570 = -1;
		weaponDisplayed = -1;
		walkProperties = -1;
		aBoolean1593 = false;
    }
}
