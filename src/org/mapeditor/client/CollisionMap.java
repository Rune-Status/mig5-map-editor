package org.mapeditor.client;

/* Class59 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

final class CollisionMap {
	
    int[][] collisionFlags;
    private int baseY;
    private int baseX;
    private int offsetY;
    private int offsetX;
    
    final void addClipping(int x, int y, int sizeX, int sizeY, int direction, boolean walkable) {
    	x -= offsetX;
    	y -= offsetY;
    	if (direction == 1 || direction == 3) {
    		int oldX = sizeX;
    		sizeX = sizeY;
    		sizeY = oldX;
    	}
    	int shift = 256;
    	if (walkable)
    		shift += 131072;
    	for (int offX = x; offX < x + sizeX; offX++) {
    		if (offX >= 0 && offX < baseX) {
    			for (int offY = y; offY < sizeY + y; offY++) {
    				if (offY >= 0 && baseY > offY)
    					addClip(offX, offY, shift);
    			}
    		}
    	}
    }
    
    final void removeClipping(int x, int y, int sizeX, int sizeY, int direction, boolean walkable) {
    	x -= offsetX;
    	y -= offsetY;
    	if (direction == 1 || direction == 3) {
    		int oldX = sizeX;
    		sizeX = sizeY;
    		sizeY = oldX;
    	}
    	int shift = 256;
    	if (walkable)
    		shift += 131072;
    	for (int offX = x; x + sizeX > offX; offX++) {
    		if (offX >= 0 && baseX > offX) {
    			for (int offY = y; y + sizeY > offY; offY++) {
    				if (offY >= 0 && baseY > offY)
    					removeClip(offX, offY, shift);
    			}
    		}
    	}
    }
    
    final void removeGroundObjectsClip(int x, int y) {
    	x -= offsetX;
    	y -= offsetY;
    	collisionFlags[x][y] &= 14680063;
    }
    
    final void addGroundObjectsClip(int x, int y) {
    	y -= offsetY;
    	x -= offsetX;
    	collisionFlags[x][y] |= 2097152;
    }
    
    private final void addClip(int x, int y, int shift) {
    	collisionFlags[x][y] |= shift;
    }
    
    private final void removeClip(int x, int y, int shift) {
    	collisionFlags[x][y] &= 16777215 - shift;
    }
    
    final boolean reachedDistance(int destY, int xSize, int ySize, int absY, int absX, int destX, int flag) {
    	int maxX = xSize + (destX - 1);
    	int maxY = ySize - 1 + destY;
    	if (destX <= absX && absX <= maxX && absY >= destY && absY <= maxY)
    		return true;
    	if (destX - 1 == absX && absY >= destY && maxY >= absY
    			&& ((collisionFlags[absX - offsetX][absY - offsetY]) & 0x8) == 0
    			&& (flag & 0x8) == 0)
    		return true;
    	if (absX == maxX + 1 && destY <= absY && maxY >= absY
    			&& ((collisionFlags[absX - offsetX][absY - offsetY]) & 0x80) == 0
    			&& (flag & 0x2) == 0)
    		return true;
    	if (absY == destY - 1 && absX >= destX && maxX >= absX
    			&& ((collisionFlags[absX - offsetX][absY - offsetY]) & 0x2) == 0
    			&& (flag & 0x4) == 0)
    		return true;
    	if (absY == maxY + 1 && destX <= absX && maxX >= absX
    			&& ((collisionFlags[absX - offsetX][absY - offsetY]) & 0x20) == 0
    			&& (flag & 0x1) == 0)
    		return true;
    	return false;
    }
    
    final boolean reachedWall(int absY, int direction, int absX, int type, int destX, int destY) {
    	if (destX == absX && destY == absY)
    		return true;
    	absX -= offsetX;
    	destX -= offsetX;
    	destY -= offsetY;
    	absY -= offsetY;
    	if (type == 0) {
    		if (direction == 0) {
    			if (destX - 1 == absX && destY == absY)
    				return true;
    			if (absX == destX && destY + 1 == absY && (collisionFlags[absX][absY] & 0x1280120) == 0)
    				return true;
    			if (absX == destX && absY == destY - 1 && (collisionFlags[absX][absY] & 0x1280102) == 0)
    				return true;
    		} else if (direction == 1) {
    			if (absX == destX && destY + 1 == absY)
    				return true;
    			if (destX - 1 == absX && destY == absY && (collisionFlags[absX][absY] & 0x1280108) == 0)
    				return true;
    			if (absX == destX + 1 && absY == destY && (collisionFlags[absX][absY] & 0x1280180) == 0)
    				return true;
    		} else if (direction == 2) {
    			if (absX == destX + 1 && absY == destY)
    				return true;
    			if (destX == absX && absY == destY + 1 && (collisionFlags[absX][absY] & 0x1280120) == 0)
    				return true;
    			if (absX == destX && destY - 1 == absY && (collisionFlags[absX][absY] & 0x1280102) == 0)
    				return true;
    		} else if (direction == 3) {
    			if (destX == absX && destY - 1 == absY)
    				return true;
    			if (absX == destX - 1 && absY == destY && (collisionFlags[absX][absY] & 0x1280108) == 0)
    				return true;
    			if (destX + 1 == absX && absY == destY && (collisionFlags[absX][absY] & 0x1280180) == 0)
    				return true;
    		}
    	}
    	if (type == 2) {
    		if (direction == 0) {
    			if (destX - 1 == absX && destY == absY)
    				return true;
    			if (destX == absX && destY + 1 == absY)
    				return true;
    			if (destX + 1 == absX && absY == destY && (collisionFlags[absX][absY] & 0x1280180) == 0)
    				return true;
    			if (absX == destX && destY - 1 == absY && (collisionFlags[absX][absY] & 0x1280102) == 0)
    				return true;
		    } else if (direction == 1) {
		    	if (destX - 1 == absX && absY == destY && (collisionFlags[absX][absY] & 0x1280108) == 0)
				    return true;
				if (absX == destX && absY == destY + 1)
				    return true;
				if (absX == destX + 1 && destY == absY)
					return true;
				if (destX == absX && absY == destY - 1 && (collisionFlags[absX][absY] & 0x1280102) == 0)
				    return true;
		    } else if (direction == 2) {
		    	if (destX - 1 == absX && destY == absY && (collisionFlags[absX][absY] & 0x1280108) == 0)
		    		return true;
		    	if (destX == absX && destY + 1 == absY && (collisionFlags[absX][absY] & 0x1280120) == 0)
		    		return true;
		    	if (destX + 1 == absX && destY == absY)
		    		return true;
		    	if (absX == destX && absY == destY - 1)
		    		return true;
		    } else if (direction == 3) {
		    	if (destX - 1 == absX && absY == destY)
		    		return true;
		    	if (absX == destX && absY == destY + 1 && ((collisionFlags[absX][absY]) & 0x1280120) == 0)
		    		return true;
		    	if (absX == destX + 1 && destY == absY && ((collisionFlags[absX][absY]) & 0x1280180) == 0)
		    		return true;
		    	if (destX == absX && absY == destY - 1)
		    		return true;
		    }
    	}
		if (type == 9) {
			if (destX == absX && destY + 1 == absY && ((collisionFlags[absX][absY] & 0x20) == 0))
				return true;
			if (destX == absX && destY - 1 == absY && ((collisionFlags[absX][absY] & 0x2) == 0))
				return true;
			if (absX == destX - 1 && absY == destY && ((collisionFlags[absX][absY] & 0x8) == 0))
				return true;
			if (destX + 1 == absX && destY == absY && ((collisionFlags[absX][absY] & 0x80) == 0))
				return true;
		}
		return false;
    }
    
    final boolean reachedDiagonalWall(int direction, int absX, int destX, int destY, int absY, int type) {
    	if (absX == destX && absY == destY)
    		return true;
    	absY -= offsetY;
    	destX -= offsetX;
    	destY -= offsetY;
    	absX -= offsetX;
		if (type == 6 || type == 7) {
			if (type == 7)
				direction = direction + 2 & 0x3;
			if (direction == 0) {
				if (destX + 1 == absX && destY == absY && (collisionFlags[absX][absY] & 0x80) == 0)
					return true;
				if (absX == destX && absY == destY - 1 && (collisionFlags[absX][absY] & 0x2) == 0)
					return true;
			} else if (direction == 1) {
				if (destX - 1 == absX && destY == absY && (collisionFlags[absX][absY] & 0x8) == 0)
					return true;
				if (destX == absX && destY - 1 == absY && (collisionFlags[absX][absY] & 0x2) == 0)
					return true;
			} else if (direction == 2) {
				if (destX - 1 == absX && absY == destY && ((collisionFlags[absX][absY]) & 0x8) == 0)
					return true;
				if (destX == absX && destY + 1 == absY && ((collisionFlags[absX][absY]) & 0x20) == 0)
					return true;
			} else if (direction == 3) {
				if (absX == destX + 1 && destY == absY && ((collisionFlags[absX][absY]) & 0x80) == 0)
					return true;
				if (absX == destX && destY + 1 == absY && ((collisionFlags[absX][absY]) & 0x20) == 0)
					return true;
			}
		}
		if (type == 8) {
			if (absX == destX && destY + 1 == absY && ((collisionFlags[absX][absY] & 0x20) == 0))
				return true;
			if (absX == destX && absY == destY - 1 && ((collisionFlags[absX][absY] & 0x2) == 0))
				return true;
			if (destX - 1 == absX && absY == destY && ((collisionFlags[absX][absY] & 0x8) == 0))
				return true;
			if (absX == destX + 1 && absY == destY && ((collisionFlags[absX][absY] & 0x80) == 0))
				return true;
		}
		return false;
    }
    
    final void removeClipping(boolean walkable, int x, int direction, int type, int y) {
    	x -= offsetX;
    	y -= offsetY;
    	if (type == 0) {
    		if (direction == 0) {
    			removeClip(x, y, 128);
    			removeClip(x - 1, y, 8);
    		}
    		if (direction == 1) {
    			removeClip(x, y, 2);
    			removeClip(x, y + 1, 32);
		    }
		    if (direction == 2) {
		    	removeClip(x, y, 8);
		    	removeClip(x + 1, y, 128);
		    }
		    if (direction == 3) {
		    	removeClip(x, y, 32);
		    	removeClip(x, y - 1, 2);
		    }
    	}
    	if (type == 1 || type == 3) {
    		if (direction == 0) {
    			removeClip(x, y, 1);
    			removeClip(x - 1, y + 1, 16);
    		}
    		if (direction == 1) {
    			removeClip(x, y, 4);
    			removeClip(x + 1, y + 1, 64);
    		}
    		if (direction == 2) {
    			removeClip(x, y, 16);
    			removeClip(x + 1, y - 1, 1);
    		}
    		if (direction == 3) {
    			removeClip(x, y, 64);
    			removeClip(x - 1, y - 1, 4);
    		}
    	}
    	if (type == 2) {
    		if (direction == 0) {
    			removeClip(x, y, 130);
    			removeClip(x - 1, y, 8);
    			removeClip(x, y + 1, 32);
    		}
    		if (direction == 1) {
    			removeClip(x, y, 10);
    			removeClip(x, y + 1, 32);
    			removeClip(x + 1, y, 128);
    		}
    		if (direction == 2) {
    			removeClip(x, y, 40);
    			removeClip(x + 1, y, 128);
    			removeClip(x, y - 1, 2);
    		}
    		if (direction == 3) {
    			removeClip(x, y, 160);
    			removeClip(x, y - 1, 2);
				removeClip(x - 1, y, 8);
    		}
    	}
    	if (walkable) {
    		if (type == 0) {
    			if (direction == 0) {
    				removeClip(x, y, 65536);
    				removeClip(x - 1, y, 4096);
    			}
    			if (direction == 1) {
    				removeClip(x, y, 1024);
    				removeClip(x, y + 1, 16384);
    			}
    			if (direction == 2) {
    				removeClip(x, y, 4096);
    				removeClip(x + 1, y, 65536);
    			}
    			if (direction == 3) {
    				removeClip(x, y, 16384);
    				removeClip(x, y - 1, 1024);
    			}
    		}
    		if (type == 1 || type == 3) {
    			if (direction == 0) {
    				removeClip(x, y, 512);
    				removeClip(x - 1, y + 1, 8192);
    			}
    			if (direction == 1) {
    				removeClip(x, y, 2048);
    				removeClip(x + 1, y + 1, 32768);
    			}
    			if (direction == 2) {
    				removeClip(x, y, 8192);
    				removeClip(x + 1, y - 1, 512);
    			}
    			if (direction == 3) {
    				removeClip(x, y, 32768);
    				removeClip(x - 1, y - 1, 2048);
    			}
    		}
    		if (type == 2) {
    			if (direction == 0) {
    				removeClip(x, y, 66560);
    				removeClip(x - 1, y, 4096);
    				removeClip(x, y + 1, 16384);
    			}
    			if (direction == 1) {
    				removeClip(x, y, 5120);
    				removeClip(x, y + 1, 16384);
    				removeClip(x + 1, y, 65536);
    			}
    			if (direction == 2) {
    				removeClip(x, y, 20480);
    				removeClip(x + 1, y, 65536);
    				removeClip(x, y - 1, 1024);
    			}
    			if (direction == 3) {
    				removeClip(x, y, 81920);
    				removeClip(x, y - 1, 1024);
    				removeClip(x - 1, y, 4096);
    			}
    		}
    	}
    }
    
    final void resetFlags() {
    	for (int x = 0; baseX > x; x++) {
    		for (int y = 0; y < baseY; y++) {
    			if (x == 0 || y == 0 || x == baseX - 1 || y == baseY - 1)
    				collisionFlags[x][y] = 16777215;
    			else
    				collisionFlags[x][y] = 16777216;
    		}
    	}
    }
    
    final void addClipping(int type, int x, int y, boolean walkable, int direction) {
    	x -= offsetX;
    	y -= offsetY;
    	if (type == 0) {
		    if (direction == 0) {
		    	addClip(x, y, 128);
		    	addClip(x - 1, y, 8);
		    }
		    if (direction == 1) {
		    	addClip(x, y, 2);
		    	addClip(x, y + 1, 32);
		    }
		    if (direction == 2) {
		    	addClip(x, y, 8);
		    	addClip(x + 1, y, 128);
		    }
		    if (direction == 3) {
		    	addClip(x, y, 32);
		    	addClip(x, y - 1, 2);
		    }
    	}
		if (type == 1 || type == 3) {
		    if (direction == 0) {
				addClip(x, y, 1);
				addClip(x - 1, y + 1, 16);
		    }
		    if (direction == 1) {
		    	addClip(x, y, 4);
		    	addClip(x + 1, y + 1, 64);
		    }
		    if (direction == 2) {
		    	addClip(x, y, 16);
		    	addClip(x + 1, y - 1, 1);
		    }
		    if (direction == 3) {
		    	addClip(x, y, 64);
		    	addClip(x - 1, y - 1, 4);
		    }
		}
		if (type == 2) {
		    if (direction == 0) {
		    	addClip(x, y, 130);
		    	addClip(x - 1, y, 8);
		    	addClip(x, y + 1, 32);
		    }
		    if (direction == 1) {
		    	addClip(x, y, 10);
		    	addClip(x, y + 1, 32);
		    	addClip(x + 1, y, 128);
		    }
		    if (direction == 2) {
		    	addClip(x, y, 40);
		    	addClip(x + 1, y, 128);
		    	addClip(x, y - 1, 2);
		    }
		    if (direction == 3) {
		    	addClip(x, y, 160);
				addClip(x, y - 1, 2);
				addClip(x - 1, y, 8);
		    }
		}
		if (walkable) {
			if (type == 0) {
				if (direction == 0) {
					addClip(x, y, 65536);
					addClip(x - 1, y, 4096);
				}
				if (direction == 1) {
					addClip(x, y, 1024);
					addClip(x, y + 1, 16384);
				}
				if (direction == 2) {
					addClip(x, y, 4096);
					addClip(x + 1, y, 65536);
				}
				if (direction == 3) {
					addClip(x, y, 16384);
					addClip(x, y - 1, 1024);
				}
			}
			if (type == 1 || type == 3) {
				if (direction == 0) {
					addClip(x, y, 512);
					addClip(x - 1, y + 1, 8192);
				}
				if (direction == 1) {
					addClip(x, y, 2048);
					addClip(x + 1, y + 1, 32768);
				}
				if (direction == 2) {
					addClip(x, y, 8192);
					addClip(x + 1, y - 1, 512);
				}
				if (direction == 3) {
					addClip(x, y, 32768);
					addClip(x - 1, y - 1, 2048);
				}
			}
			if (type == 2) {
				if (direction == 0) {
					addClip(x, y, 66560);
					addClip(x - 1, y, 4096);
					addClip(x, y + 1, 16384);
				}
				if (direction == 1) {
					addClip(x, y, 5120);
					addClip(x, y + 1, 16384);
					addClip(x + 1, y, 65536);
				}
				if (direction == 2) {
					addClip(x, y, 20480);
					addClip(x + 1, y, 65536);
					addClip(x, y - 1, 1024);
				}
				if (direction == 3) {
					addClip(x, y, 81920);
					addClip(x, y - 1, 1024);
					addClip(x - 1, y, 4096);
				}
		    }
		}
    }
    
    CollisionMap(int x, int y) {
		baseX = x;
		baseY = y;
		collisionFlags = new int[baseX][baseY];
		offsetY = 0;
		offsetX = 0;
		resetFlags();
    }
}
