package org.mapeditor.client;

/* Class3_Sub3_Sub3_Sub1 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

abstract class Entity extends SceneModel {
	
	int turn90CWAnimation;
	int lastUpdate;
	int walkQueueLocationIndex;
	int turnDirection;
	int startCycle;
	int currentGraphicCycle;
	int turnValue;
	int anInt1866 = 0;
	int[] walkQueueY;
	int faceY;
	int currentGraphicFrame;
	int anInt1870;
	int resetCycle;
	int hitCycle;
	int runAnimation;
	RSString textSpoken = null;
	int turnAnimation = -1;
	int directionDegrees;
	int idleAnimation;
	int newX;
	int y;
	int oldY;
	int currentAnimationFrameTime;
	int direction;
	int faceX;
	int x;
	int currentGraphic;
	int interactingEntityIndex;
	int maxHealth;
	int turn180Animation;
	int textCycle;
	int[] hitValues;
	int oldX;
	int turn90CCAnimation;
	int[] walkQueueX;
	int textColor;
	int walkAnimation;
	int currentHealth;
	boolean[] runningFlags;
	int currentGraphicHeight;
	int anInt1906;
	int[] hitMarkTypes;
	int anInt1908;
	int currentAnimation;
	int animationDelay;
	int[] hitCycles;
	int moveCycle;
	boolean aBoolean1914;
	int currentGraphicFrameTime;
	int height;
	int currentAnimationFrameId;
	int size;
	int newY;
	int textEffects;
	int currentAnimationId;

	final void move(int direction, boolean running) {
		int y = walkQueueY[0];
		int x = walkQueueX[0];
		if (currentAnimationId != -1 && Sequence.forID(currentAnimationId).walkProperties == 1)
			currentAnimationId = -1;
		if (direction == 0) {
			y++;
			x--;
		}
		if (direction == 1)
			y++;
		if (direction == 2) {
			y++;
			x++;
		}
		if (walkQueueLocationIndex < 9)
			walkQueueLocationIndex++;
		if (direction == 3)
			x--;
		if (direction == 4)
			x++;
		if (direction == 5) {
			y--;
			x--;
		}
		if (direction == 6)
			y--;
		for (int id = walkQueueLocationIndex; id > 0; id--) {
			walkQueueX[id] = walkQueueX[id - 1];
			walkQueueY[id] = walkQueueY[id - 1];
			runningFlags[id] = runningFlags[id - 1];
		}
		if (direction == 7) {
			x++;
			y--;
		}
		walkQueueX[0] = x;
		walkQueueY[0] = y;
		runningFlags[0] = running;
	}

	final void resetWalkingQueue() {
		anInt1906 = 0;
		walkQueueLocationIndex = 0;
	}

	final void addHit(int damage, int type, int cycle) {
		for (int id = 0; id < 4; id++) {
			if (hitCycles[id] <= cycle) {
				hitValues[id] = damage;
				hitMarkTypes[id] = type;
				hitCycles[id] = cycle + 70;
				break;
			}
		}
	}

	boolean isVisible() {
		return false;
	}

	final void updatePosition(int x, int y, boolean stop) {
		if (currentAnimationId != -1 && Sequence.forID(currentAnimationId).walkProperties == 1)
			currentAnimationId = -1;
		if (!stop) {
			int posY = y - walkQueueY[0];
			int posX = x - walkQueueX[0];
			if (posX >= -8 && posX <= 8 && posY >= -8 && posY <= 8) {
				if (walkQueueLocationIndex < 9)
					walkQueueLocationIndex++;
				for (int id = walkQueueLocationIndex; id > 0; id--) {
					walkQueueX[id] = (walkQueueX[id - 1]);
					walkQueueY[id] = (walkQueueY[id - 1]);
					runningFlags[id] = (runningFlags[id - 1]);
				}
				walkQueueX[0] = x;
				walkQueueY[0] = y;
				runningFlags[0] = false;
				return;
			}
		}
		anInt1866 = 0;
		walkQueueLocationIndex = 0;
		anInt1906 = 0;
		walkQueueX[0] = x;
		walkQueueY[0] = y;
		this.x = (size * 64 + walkQueueX[0] * 128);
		this.y = (walkQueueY[0] * 128 + size * 64);
	}

	Entity() {
		resetCycle = 0;
		turnValue = 32;
		currentGraphicFrame = 0;
		hitCycle = -1000;
		runAnimation = -1;
		anInt1870 = 0;
		walkQueueLocationIndex = 0;
		hitValues = new int[4];
		turn90CCAnimation = -1;
		interactingEntityIndex = -1;
		idleAnimation = -1;
		currentGraphic = -1;
		currentAnimation = -1;
		anInt1908 = 0;
		currentGraphicFrameTime = 0;
		walkQueueX = new int[10];
		textCycle = 100;
		anInt1906 = 0;
		currentAnimationFrameId = 0;
		textColor = 0;
		runningFlags = new boolean[10];
		aBoolean1914 = false;
		height = 200;
		hitCycles = new int[4];
		size = 1;
		turn180Animation = -1;
		hitMarkTypes = new int[4];
		faceY = 0;
		faceX = 0;
		currentAnimationFrameTime = 0;
		lastUpdate = 0;
		textEffects = 0;
		animationDelay = 0;
		walkAnimation = -1;
		walkQueueY = new int[10];
		currentAnimationId = -1;
		turn90CWAnimation = -1;
	}
}