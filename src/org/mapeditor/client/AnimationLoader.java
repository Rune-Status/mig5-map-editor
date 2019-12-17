package org.mapeditor.client;

/* Class3_Sub3_Sub10 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class AnimationLoader extends NodeSub {
	
	Animation[] animation;
	
	final boolean reloadModel(int id) {
		return animation[id].reloadModel;
	}

	static final AnimationLoader forID(int id) {
		AnimationLoader loader = ((AnimationLoader) Client.animationCache.get((long) id));
		if (loader != null)
			return loader;
		loader = Client.initAnimationLoader(Client.skeletonFetcher, Client.skinsFetcher, false, id);
		if (loader != null)
			Client.animationCache.put(loader, (long) id);
		return loader;
	}

	AnimationLoader(FileSystem skeletonFetcher, FileSystem skinFetcher, int id, boolean swap) {
		Deque deque = new Deque();
		int len = skeletonFetcher.getChildCount(id);
		animation = new Animation[len];
		int[] entries = skeletonFetcher.getChildEntries(id);
		for (int entry = 0; entries.length > entry; entry++) {
			byte[] skeletonBuffer = skeletonFetcher.getXteaBuffer(id, entries[entry]);
			int index = skeletonBuffer[0] << 8 & 0xff00 | skeletonBuffer[1] & 0xff;
			SkinList class3_sub6 = (SkinList) deque.getFront();
			SkinList class3_sub6_70_ = null;
			for (/**/; class3_sub6 != null; class3_sub6 = (SkinList) deque.getNext()) {
				if (class3_sub6.fileId == index) {
					class3_sub6_70_ = class3_sub6;
					break;
				}
			}
			if (class3_sub6_70_ == null) {
				byte[] skinBuffer;
				if (swap)
					skinBuffer = skinFetcher.getBuffer(index, 0);
				else
					skinBuffer = skinFetcher.getBuffer(0, index);
				class3_sub6_70_ = new SkinList(index, skinBuffer);
				deque.insertBack(class3_sub6_70_);
			}
			animation[entries[entry]] = new Animation(class3_sub6_70_, skeletonBuffer);
		}
	}
}
