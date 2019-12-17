package org.mapeditor.client;
 
import org.mapeditor.util.Utils;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
 
/* Class3_Sub3_Sub14 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
 
public final class ObjectDefinition extends NodeSub {
 
    private int varbitId = -1;
    private int moveZ;
    private int shading;
    private int moveX = 0;
    int[] childrenIDs;
    public RSString name;
    int mapSceneSprite;
    boolean aBoolean1648;
    int id;
    boolean isSolid;
    int sizeX = 1;
    int hasActions;
    boolean isWalkable = true;
    boolean aBoolean1654;
    private int scaleX;
    private int[] originalModelColors;
    int varpId;
    RSString[] actions;
    boolean aBoolean1660;
    int animationId;
    private int[] modifiedModelColors;
    int groundDecorationSprite;
    int sizeY;
    boolean aBoolean1665;
    boolean aBoolean1666;
    int walkToFlag;
    private int moveY;
    int anInt1671;
    private int scaleY;
    int anInt1673;
    boolean aBoolean1674;
    public int[] modelTypes;
    private int scaleZ;
    boolean aBoolean1677;
    public int[][] object_model_ids;
    private int lightness;
 
    final ObjectDefinition handleConfig() {
    	int i = -1;
    	if (varbitId == -1) {
    		i = VarBit.getSettings(varbitId);
    	} else if (varpId != -1)
    		i = Client.variousSettings[varpId];
    	if (i < 0 || i >= childrenIDs.length || childrenIDs[i] == -1)
		   return null;
    	return ObjectDefinition.forID(childrenIDs[i]);
    }
 
    final void readValues(RSByteBuffer stream) {
    	for (;;) {
    		int opcode = stream.getUByte();
    		if (opcode == 0)
    			break;
    		readValues(stream, opcode);
    	}
    }
 
    final Model method330(int i, int i_5_, int direction, Sequence sequence, int type, int i_9_, int i_10_, int i_11_) {
    	long hash;
    	if (modelTypes == null)
    		hash = (long) ((id << 10) + direction);
    	else
    		hash = (long) ((type << 3) + (id << 10) + direction);
    	Model model = ((Model) Client.aClass19_2186.get(hash));
    	if (model == null) {
    		model = method339(type, true, direction, true);
    		if (model == null)
    			return null;
    		Client.aClass19_2186.put(model, hash);
    	}
    	if (sequence == null && !aBoolean1654)
    		return model;
    	if (sequence != null)
    		model = sequence.animateObjectModel(model, direction, i_9_);
    	else
    		model = model.method150(true);
    	if (aBoolean1654) {
    		int i_13_ = (i_10_ + (i_11_ + (i + i_5_))) / 4;
    		for (int i_14_ = 0; model.numberOfVerticeCoordinates > i_14_; i_14_++) {
    			int i_15_ = model.verticesZCoordinate[i_14_];
    			int i_16_ = model.verticesXCoordinate[i_14_];
    			int i_17_ = (i_16_ + 64) * (i_11_ - i) / 128 + i;
    			int i_18_ = i_10_ + (-i_10_ + i_5_) * (i_16_ + 64) / 128;
    			int i_19_ = (i_15_ + 64) * (-i_17_ + i_18_) / 128 + i_17_;
    			model.verticesYCoordinate[i_14_] += i_19_ - i_13_;
    		}
    	}
    	return model;
    }
 
    final void method332() {
    	if (hasActions == -1) {
    		hasActions = 0;
    		if (object_model_ids != null && (modelTypes == null || modelTypes[0] == 10))
    			hasActions = 1;
		    for (int id = 0; id < 5; id++) {
		    	if (actions[id] != null)
		    		hasActions = 1;
		    }
    	}
    	if (anInt1671 == -1)
    		anInt1671 = isSolid ? 1 : 0;
    }
 
    private final void readValues(RSByteBuffer buffer, int opcode) {
    	if (opcode == 1) {
			int length = buffer.getUByte();
			object_model_ids = new int[length][];
			for (int i_1_ = 0; (length ^ 0xffffffff) < (i_1_ ^ 0xffffffff); i_1_++) {
				int length1 = buffer.getUByte();
				object_model_ids[i_1_] = new int[length1];
				for(int x1 = 0; x1 < length1; x1++) {
					object_model_ids[i_1_][x1] = buffer.getUShort();
				}
			}
		} else if (opcode == 2) {
    		name = buffer.getString();
    	} else if (opcode == 5) {
			int i_13_ = buffer.getUByte();
			modelTypes = new int[i_13_];
			for (int i_14_ = 0; i_13_ > i_14_; i_14_++)
				modelTypes[i_14_] = buffer.getUByte();
    	} else if (opcode == 14) {
    		sizeX = buffer.getUByte();
    	} else if (opcode == 15) {
    		sizeY = buffer.getUByte();
    	} else if (opcode == 17) {
		    isSolid = false;
		} else if (opcode == 18) {
		    isWalkable = false;
    	} else if (opcode == 19) {
		    hasActions = buffer.getUByte();
    	} else if (opcode == 21) {
		    aBoolean1654 = true;
    	} else if (opcode == 22) {
		    aBoolean1648 = true;
    	} else if (opcode == 23) {
    		aBoolean1677 = true;
    	} else if (opcode == 24) {
			animationId = buffer.getUShort();
			if (animationId == 65535) {
			    animationId = -1;
		    }
			animationId = -1;
    	} else if (opcode == 28) {
		    anInt1673 = buffer.getUByte();//x offset for walls/wallsdecoration
    	} else if (opcode == 29) {
			lightness = buffer.getByte();
    	} else if (opcode == 39) {
    		shading = buffer.getByte() * 5;
    	} else if (opcode >= 30 && opcode < 35) {
    		actions[opcode - 30] = buffer.getString();
    		if (actions[opcode - 30].equalsIgnoreCase(Client.hidden))
    			actions[opcode - 30] = null;
    	} else if (opcode == 40) {
    		int i_34_ = buffer.getUByte();
    		modifiedModelColors = new int[i_34_];
    		originalModelColors = new int[i_34_];
    		for (int i_35_ = 0; i_34_ > i_35_; i_35_++) {
    			originalModelColors[i_35_] = buffer.getUShort();
    			modifiedModelColors[i_35_] = buffer.getUShort();
    		}
    	} else if (opcode == 41) {
    		int i_34_ = buffer.getUByte();
    		int[] modifiedModelColors = new int[i_34_];
    		int[] originalModelColors = new int[i_34_];
    		for (int i_35_ = 0; i_34_ > i_35_; i_35_++) {
    			originalModelColors[i_35_] = buffer.getUShort();
    			modifiedModelColors[i_35_] = buffer.getUShort();
    		}
    	} else if (opcode == 60) {
    		buffer.getUShort();
    	} else if (opcode == 62) {
		    aBoolean1660 = true;
    	} else if (opcode == 64) {
			aBoolean1666 = false;
    	} else if (opcode == 65) {
    		scaleX = buffer.getUShort();
		} else if (opcode == 66) {
			scaleY = buffer.getUShort();
		} else if (opcode == 67) {
			scaleZ = buffer.getUShort();
		} else if (opcode == 68) {
			buffer.getUShort();
		} else if (opcode == 69) {
			walkToFlag = buffer.getUByte();
		} else if (opcode == 70) {
			moveX = buffer.getShort();
		} else if (opcode == 71) {
			moveY = buffer.getShort();
		} else if (opcode == 72) {
			moveZ = buffer.getShort();
		} else if (opcode == 73) {
			aBoolean1665 = true;
		} else if (opcode == 74) {
			aBoolean1674 = true;
		} else if (opcode == 75) {
			anInt1671 = buffer.getUByte();
		} else if (opcode == 77 || opcode == 92) {
			varbitId = buffer.getUShort();
			if (varbitId == 65535)
				varbitId = -1;
			varpId = buffer.getUShort();
			if (varpId == 65535)
				varpId = -1;
 
			int defaultId = -1;
			if (opcode == 92) {
				defaultId = buffer.getUShort();
				if (defaultId == 0xffff) {
					defaultId = -1;
				}
			}
 
			int len = buffer.getUByte();
			childrenIDs = new int[len + 2];
			for (int id = 0; (id <= len); id++) {
				childrenIDs[id] = buffer.getUShort();
				if (childrenIDs[id] == 65535)
					childrenIDs[id] = -1;
			}
			childrenIDs[len + 1] = defaultId;
		} else if (opcode == 78) {
			buffer.getUShort();
			buffer.getUByte();
		} else if (opcode == 79) {
			buffer.getUShort();
			buffer.getUShort();
			buffer.getUByte();
			int var4 = buffer.getUByte();
			for (int var5 = 0; ~var4 < ~var5; ++var5) {
				buffer.getUShort();
			}
		} else if (opcode == 81) {
			int code81 = buffer.getUByte();
		} else if (opcode == 89) {
		} else if (opcode == 90) {
		} else if (opcode == 91) {
		} else if (opcode == 93) {
			buffer.getUShort();
		} else if (opcode == 94) {
		} else if (opcode == 95) {
		}
    }
 
    final Model method336(int i_38_, int i_39_, int i_40_, int i_41_, int i_42_, int i_43_) {
    	long l;
    	if (modelTypes == null)
    		l = (long) ((id << 10) + i_40_);
    	else
    		l = (long) ((i_41_ << 3) + (id << 10) + i_40_);
    	Model class3_sub3_sub3_sub3 = (Model) Client.aClass19_638.get(l);
    	if (class3_sub3_sub3_sub3 == null) {
    		class3_sub3_sub3_sub3 = method339(i_41_, !aBoolean1648, i_40_, false);
    		if (class3_sub3_sub3_sub3 == null)
    			return null;
    		Client.aClass19_638.put(class3_sub3_sub3_sub3, l);
    	}
    	if (aBoolean1654 || aBoolean1648)
    		class3_sub3_sub3_sub3 = new Model(class3_sub3_sub3_sub3, aBoolean1654, aBoolean1648);
    	if (aBoolean1654) {
    		int i_44_ = (i_39_ + i_43_ + (i_42_ + i_38_)) / 4;
    		for (int len = 0; len < class3_sub3_sub3_sub3.numberOfVerticeCoordinates; len++) {
    			int verZ = class3_sub3_sub3_sub3.verticesZCoordinate[len];
    			int verX = class3_sub3_sub3_sub3.verticesXCoordinate[len];
    			int i_48_ = i_38_ + (i_42_ - i_38_) * (verX + 64) / 128;
    			int i_49_ = (verX + 64) * (i_43_ - i_39_) / 128 + i_39_;
    			int i_50_ = i_48_ + (i_49_ - i_48_) * (verZ + 64) / 128;
    			class3_sub3_sub3_sub3.verticesYCoordinate[len] += i_50_ - i_44_;
    		}
    	}
    	return class3_sub3_sub3_sub3;
    }
 
    final boolean method337(int type) {
    	if (modelTypes == null) {
    		if (object_model_ids == null)
    			return true;
    		if (type != 10)
    			return true;
    		boolean bool = true;
    		for (int id = 0; id < object_model_ids.length; id++) {
				int childCount = object_model_ids[id].length;
				for (int i1 = 0; i1 != childCount; ++i1)
					bool &= Client.objectModelsFetcher.cached(object_model_ids[id][i1] & 0xffff, -1, 0);
			}
    		return bool;
    	}
    	boolean flag = true;
		for (int id = 0; id < modelTypes.length; id++) {
		    if (type == modelTypes[id]) {
				int childCount = object_model_ids[id].length;
				for (int i1 = 0; i1 != childCount; ++i1)
					flag &= Client.objectModelsFetcher.cached(object_model_ids[id][i1] & 0xffff, -1, 0);
			}
		}
		return flag;
    }
 
    final boolean modelCached() {
    	if (object_model_ids == null)
    		return true;
    	boolean cached = true;
    	for (int id = 0; object_model_ids.length > id; id++) {
			int childCount = object_model_ids[id].length;
			for (int i1 = 0; i1 != childCount; ++i1)
				cached &= Client.objectModelsFetcher.cached(object_model_ids[id][i1] & 0xffff, -1, 0);
		}
    	return cached;
    }
 
    public final Model method339(int type, boolean bool_55_, int rotation, boolean skin) {
    	Model def = null;
    	if (modelTypes != null) {
    		int currentIndex = -1;
    		for (int index = 0; modelTypes.length > index; index++) {
    			if (modelTypes[index] == type) {
    				currentIndex = index;
    				break;
    			}
    		}
    		if (currentIndex == -1)
    			return null;
    		boolean bool_60_ = aBoolean1660 ^ rotation > 3;
			int typeAmount = object_model_ids[currentIndex].length;
			for (int x = 0; x != typeAmount; x++) {
				int id = object_model_ids[currentIndex][x];
				if (bool_60_)
					id += 65536;
				def = ((Model) Client.aClass19_178.get((long) id));
				if (def == null) {
					def = Model.get((Client.objectModelsFetcher), id & 0xffff, 0);
					if (def == null)
						return null;
					if (bool_60_)
						def.method133();
					Client.aClass19_178.put(def, (long) id);
				}
				if (typeAmount > 1)
					Client.modelBuffer1[x] = def;
			}
			if (typeAmount > 1)
				def = new Model(Client.modelBuffer1, typeAmount);
    	} else {
    		if (type != 10)
    			return null;
    		if (object_model_ids == null)
    			return null;
    		boolean bool_62_ = aBoolean1660 ^ rotation > 3;
    		int modelCount = object_model_ids.length;
    		for (int index = 0; index < modelCount; index++) {
				int subModelId = object_model_ids[index].length;
				for(int i1 = 0; i1 != subModelId; ++i1) {
					int i_65_ = object_model_ids[index][i1];
					if (bool_62_)
						i_65_ += 65536;
					def = ((Model) Client.aClass19_178.get((long) i_65_));
					if (def == null) {
						def = Model.get((Client.objectModelsFetcher), i_65_ & 0xffff, 0);
						if (def == null)
							return null;
						if (bool_62_)
							def.method133();
						Client.aClass19_178.put(def, (long) i_65_);
					}
					if (subModelId > 1) {
						Client.modelBuffer2[i1] = def;
					}
				}
				if (subModelId > 1)
					def = new Model(Client.modelBuffer2, subModelId);

    			if (modelCount > 1)
    				Client.modelBuffer1[index] = def;
    		}
    		if (modelCount > 1)
    			def = (new Model(Client.modelBuffer1, modelCount));
    	}
    	boolean scale;
    	if (scaleX != 128 || scaleY != 128 || scaleZ != 128)
    		scale = true;
    	else
    		scale = false;
    	boolean move;
    	if (moveX != 0 || moveY != 0 || moveZ != 0)
    		move = true;
    	else
    		move = false;
    	Model class3_sub3_sub3_sub3_68_ = new Model(def, rotation == 0 && !scale && !move, originalModelColors == null, true);
    	rotation &= 0x3;
    	if (rotation == 1)
    		class3_sub3_sub3_sub3_68_.rotate90();
    	else if (rotation == 2)
    		class3_sub3_sub3_sub3_68_.rotate180();
    	else if (rotation == 3)
    		class3_sub3_sub3_sub3_68_.rotate270();
    	if (originalModelColors != null) {
    		for (int i_69_ = 0; originalModelColors.length > i_69_; i_69_++)
    			class3_sub3_sub3_sub3_68_.swapColors(originalModelColors[i_69_], modifiedModelColors[i_69_]);
    	}
    	if (scale)
    		class3_sub3_sub3_sub3_68_.scale(scaleX, scaleY, scaleZ);
    	if (move)
    		class3_sub3_sub3_sub3_68_.move(moveX, moveY, moveZ);
    	if (skin)
    		class3_sub3_sub3_sub3_68_.skin();
    	class3_sub3_sub3_sub3_68_.preProcess(lightness + 64, shading * 5 + 768, -50, -10, -50, bool_55_);
    	return class3_sub3_sub3_sub3_68_;
    }
 
    public synchronized static final ObjectDefinition forID(int id) {
		ObjectDefinition objectDef = (ObjectDefinition) Client.objectDefCache.get((long) id);
		if (objectDef != null)
			return objectDef;
		byte[] buffer = ReadFile("./639objects/" + id +".dat");
		objectDef = new ObjectDefinition();
		objectDef.id = id;
		if (buffer != null)
			objectDef.readValues(new RSByteBuffer(buffer));
		objectDef.method332();
		if (objectDef.aBoolean1674) {
			objectDef.isSolid = false;
			objectDef.isWalkable = false;
		}
		if(Utils.loc_names.get(id) != null)
			objectDef.name = RSString.createRSString(Utils.loc_names.get(id));
		Client.objectDefCache.put(objectDef, (long) id);
		return objectDef;
	}

	public static final byte[] ReadFile(String s) {
		try {
			byte abyte0[];
			File file = new File(s);
			int i = (int) file.length();
			abyte0 = new byte[i];
			DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new FileInputStream(s)));
			datainputstream.readFully(abyte0, 0, i);
			datainputstream.close();
			return abyte0;
		} catch (Exception e) {
			System.out.println("Exception: " + new StringBuilder().append("Read Error: ").append(s).toString());
			return null;
		}
	}
 
	public ObjectDefinition() {
		mapSceneSprite = -1;
		scaleX = 128;
		animationId = -1;
		name = Client.nullString;
		aBoolean1654 = false;
		groundDecorationSprite = -1;
		aBoolean1660 = false;
		isSolid = true;
		aBoolean1665 = false;
		aBoolean1666 = true;
		hasActions = -1;
		moveZ = 0;
		scaleY = 128;
		shading = 0;
		varpId = -1;
		aBoolean1648 = false;
		sizeY = 1;
		aBoolean1674 = false;
		anInt1671 = -1;
		walkToFlag = 0;
		actions = new RSString[5];
		moveY = 0;
		scaleZ = 128;
		anInt1673 = 16;
		aBoolean1677 = false;
		lightness = 0;
    }
}
