package org.mapeditor.client;

/* Class57 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public final class TextureLoader implements TextureInterface {
	
    private Deque textureCache = new Deque();
    private Texture[] textures;
    private double brightness;
    private int thirty;
    private int disposeTimer = 0;
    private FileSystem spriteFetcher;
    private int lowMem = 128;
    
    final void animateTexture(int lagg) {
    	for (int id = 0; id < textures.length; id++) {
    		Texture animatedTexture = textures[id];
    		if (animatedTexture != null && animatedTexture.type != 0 && animatedTexture.aBoolean1169) {
    			animatedTexture.animateTexture(lagg);
    			animatedTexture.aBoolean1169 = false;
    		}
    	}
    }
    
    final void modifyBrightness(double modifier) {
    	brightness = modifier;
    	resetTextures();
    }
    
    final void resetTextures() {
    	for (int id = 0; textures.length > id; id++) {
    		if (textures[id] != null)
    			textures[id].dispose();
    	}
    	textureCache = new Deque();
    	disposeTimer = thirty;
    }
    
    public final int getTextureId(int id) {
		if (textures[id] == null)
		    return 0;
		return textures[id].textureId;
    }
    
    public final int getTexture(int id) {
		if (textures[id] == null)
		    return 0;
		return textures[id].textureSprites[0];
    }
    
    public final boolean isTransparent(int id) {
    	return textures[id].transparent;
    }
    
    public final boolean isLowMem() {
    	if (lowMem != 64)
    		return false;
		return true;
    }
    
    public final int[] method7(int id) {
		Texture texture = textures[id];
		if (texture != null) {
			if (texture.anIntArray1170 != null) {
				textureCache.insertFront(texture);
				texture.aBoolean1169 = true;
				return texture.anIntArray1170;
			}
			boolean bool = texture.method69(brightness, lowMem, spriteFetcher);
			if (bool) {
				if (disposeTimer != 0)
					disposeTimer--;
				else {
					Texture last = (Texture) textureCache.popLast();
					last.dispose();
				}
				textureCache.insertFront(texture);
				texture.aBoolean1169 = true;
				return texture.anIntArray1170;
			}
		}
		return null;
    }
    
    TextureLoader(FileSystem textureFetcher, FileSystem spriteFetcher, int i, double modifier, int isLowMem) {
    	//TODO: Temp loading 414 textures
    	this.spriteFetcher = spriteFetcher;
    	brightness = 1.0;
    	brightness = modifier;
    	thirty = i;
    	lowMem = isLowMem;
    	disposeTimer = thirty;

    	brightness = 1.0;
    	brightness = modifier;
    	thirty = i;
    	lowMem = isLowMem;
    	this.spriteFetcher = spriteFetcher;
    	disposeTimer = thirty;
    	int[] entry = textureFetcher.getChildEntries(0);
    	int len = entry.length;
    	textures = new Texture[textureFetcher.getChildCount(0)];
    	for (int id = 0; id < len; id++) {
    		RSByteBuffer stream = new RSByteBuffer(textureFetcher.getXteaBuffer(0, entry[id]));
    		textures[entry[id]] = new Texture(stream);
    	}
    }
}
