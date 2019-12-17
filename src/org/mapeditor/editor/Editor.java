package org.mapeditor.editor;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.mapeditor.client.Client;
import org.mapeditor.client.CompoundTile;
import org.mapeditor.client.DecoratedTile;
import org.mapeditor.client.GameObject;
import org.mapeditor.client.GroundTile;
import org.mapeditor.client.MouseHandler;
import org.mapeditor.client.ObjectDefinition;
import org.mapeditor.client.OverLayFloor;
import org.mapeditor.client.Rasterizer;
import org.mapeditor.client.UnderLayFloor;
import org.mapeditor.gui.GUI;

/**
 * Keys: - 81: Shift - 82: Control
 * 
 * 
 * */
public class Editor {

	public static boolean disableObjects = false;
	public static boolean disableTileShadow;
	public static boolean disableTileBlending;
	public static int editedHeight = 0;
	public static EditingMode mode = EditingMode.OVERLAY;
	public static HeightMode heightMode = HeightMode.PLAIN;
	public static boolean focused;
	public static int selectedLocationId;
	public static int selectedLocationType;
	public static int rotation = 0;
	public static boolean copied;
	public static List<GameObject> copiedList = new ArrayList<GameObject>();

	public static void hoverTile(int x, int y, boolean other) {
		if(mode == EditingMode.OBJECTS) {
			if (Client.heldKeys[81]) {
				for(int xOff = 0; xOff < Brush.size; xOff++) {
					for(int yOff = -Brush.size; yOff < 0; yOff++) {
						int posX = x + xOff;
						int posY = y + yOff + 1;
						if (MouseHandler.mouseDown1) {
							ObjectDefinition objDef = ObjectDefinition.forID(Editor.selectedLocationId);
							int type = 10;
							if(selectedLocationType != -1) {
								type = selectedLocationType;
							} else if(objDef != null)
								if (objDef.modelTypes != null) {
									type = objDef.modelTypes[0];
								}
							//System.out.println("Id: "+selectedLocationId+", Type: "+type+", Height: "+Client.height+", X: "+posX+", Y: "+posY);
							Client.method1006(posX, Client.collisionMaps[editedHeight], Editor.rotation, Editor.selectedLocationId,
									Client.height, Client.sceneGraph, type, posY);
							addObject(Editor.selectedLocationId, posX, posY, Client.height, type, Editor.rotation);
						} else if (MouseHandler.mouseDown2) {
							Client.method483(posX, posY, Client.height);
							removeObject(posX, posY, Client.height);
						}
					}
				}
			}
		} else {
			if (MouseHandler.mouseDown1) {
				clickTile(x, y, true);
			} else if (MouseHandler.mouseDown2) {
				clickTile(x, y, false);
			}
			Rasterizer.alpha = 100;
		}
	}

	public static void clickTile(int posX, int posY, boolean primaryClick) {
		for(int xOff = 0; xOff < Brush.size; xOff++) {
			for(int yOff = -Brush.size; yOff < 0; yOff++) {
				int x = posX + xOff;
				int y = posY + yOff + 1;
				if(x < 0 || y < 0 || x > 103 || y > 103)
					return;
				int z = Editor.editedHeight;
				//System.out.println("clicked on " + x + ", " + y);
		
				if (Client.heldKeys[82]) { // control down, lift height
				}
				if (Client.heldKeys[86]) { // alt
				}
				if (Client.heldKeys[81]) {
					/*int i_49_ = (Client.tileBrightness[x][y]);
					int i_51_ = (Client.tileBrightness[x][y + 1]);
					int i_52_ = (Client.tileBrightness[x + 1][y + 1]);
					int i_55_ = (Client.tileBrightness[x + 1][y]);*/
					int i_46_ = (Client.tileHeight[z][x + 1][y]);
					int i_50_ = (Client.tileHeight[z][x][y]);
					int i_47_ = (Client.tileHeight[z][x + 1][y + 1]);
					int i_48_ = (Client.tileHeight[z][x][y + 1]);
					int id = 0;
					int mod = -1;
					
					if (mode == EditingMode.SETTINGS) {
						if(Client.sceneGraph.groundTiles[z][x][y] == null) {
							Client.sceneGraph.groundTiles[z][x][y] = new GroundTile(z, x, y);
						}
						int flag = (Integer) GUI.tileFlag.getValue();

						if(flag != 50) {
							Client.tileSettings[z][x][y] = (byte) flag;
						} else {
							System.out.println(Client.tileSettings[z][x][y]);
						}
					} else if (mode == EditingMode.HEIGHT) {
						if(heightMode == HeightMode.PLAIN) {
							int lower = (Integer) GUI.heightPower.getValue();

							if(lower != 3000) {
								Client.tileHeight[z][x][y] = lower;
							} else {
								System.out.println(Client.tileHeight[z][x][y]);
								//Client.tileHeight[z][x][y] += primaryClick ? -lower : lower;
							}
							Client.heightMod[z][x][y] = false;
						} else if(heightMode == HeightMode.INTERPOLATED) {
							System.out.println("here");
							int power = (Integer) GUI.heightPower.getValue();
							
							if(posX != x && posY != y) {
								int i_21_ = (int) Math.abs(Math.sqrt(Math.abs(Client.tileSettings[z][x][y])) - Math.sqrt(Math.abs(Client.tileHeight[z][x + 1][y])));
								int i_22_ = (int) Math.abs(Math.sqrt(Math.abs(Client.tileSettings[z][x][y])) - Math.sqrt(Math.abs(Client.tileHeight[z][x][y + 1])));
								System.out.println(i_21_+":"+i_22_);
								
								//int heightdelta1 = Client.tileHeight[z][posX][posY]
								
								int i_23_ = (int) Math.sqrt((double) (i_21_ * i_21_) + (i_22_ * i_22_));
								System.out.println(i_23_);
								//power = i_23_;
							}
							
							//System.out.println(power);
							
							//Client.tileHeight[z][x][y] += primaryClick ? -power : power;
							//Client.heightMod[z][x][y] = false;
						}
					} else if (mode == EditingMode.OVERLAY) {
						if(Client.sceneGraph.groundTiles[z][x][y] == null) {
							Client.sceneGraph.groundTiles[z][x][y] = new GroundTile(z, x, y);
						}
						if(!primaryClick) {
							Client.sceneGraph.groundTiles[z][x][y].shapedTile = null;
							Client.sceneGraph.groundTiles[z][x][y].decoratedTile = null;
							Client.overlayIds[z][x][y] = 0;
							Client.underlayIds[z][x][y] = 0;
						} else {
							OverLayFloor flo = OverLayFloor.forID(FloorFrame.selectedOverlayId);
							id = FloorFrame.selectedOverlayId;
							mod = Client.method1005(flo.groundHue * 256 / flo.alpha, flo.groundSaturation, flo.groundLightness);
							
							DecoratedTile class21 = new DecoratedTile(mod, mod, mod, mod, -1, 0xFFFFFF, false);
							Client.sceneGraph.groundTiles[z][x][y].shapedTile = null;
							Client.sceneGraph.groundTiles[z][x][y].decoratedTile = class21;
							Client.overlayIds[z][x][y] = (byte) (id + 1);
							Client.underlayIds[z][x][y] = 0;
						}
						
					} else if (mode == EditingMode.UNDERLAY) {
						/*if(!primaryClick) {
							tile = Client.sceneGraph.groundTiles[z][x][y].shapedTile;
							overlayId = Client.overlayIds[z][x][y];
							underlayShape = Client.underlayShapes[z][x][y];
							underlayRotation = Client.underlayRotations[z][x][y];
							System.out.println("tile copied to clipboard");
						} else {
							if(Client.sceneGraph.groundTiles[z][x][y] == null) {
								Client.sceneGraph.groundTiles[z][x][y] = new GroundTile(z, x, y);
							}
							Client.sceneGraph.groundTiles[z][x][y].shapedTile = tile;
							Client.overlayIds[z][x][y] = overlayId;
							Client.underlayIds[z][x][y] = (byte) FloorFrame.selectedUnderlayId;
							Client.underlayShapes[z][x][y] = underlayShape;
							Client.underlayRotations[z][x][y] = underlayRotation;
						}*/
						//System.out.println(Client.sceneGraph.groundTiles[z][x][y].shapedTile);
						if(Client.sceneGraph.groundTiles[z][x][y] == null) {
							Client.sceneGraph.groundTiles[z][x][y] = new GroundTile(z, x, y);
						}
						if(!primaryClick) {
							Client.sceneGraph.groundTiles[z][x][y].shapedTile = null;
							Client.sceneGraph.groundTiles[z][x][y].decoratedTile = null;
							Client.overlayIds[z][x][y] = 0;
							Client.underlayIds[z][x][y] = 0;
						} else {
							OverLayFloor flo = OverLayFloor.forID(FloorFrame.selectedOverlayId);
							UnderLayFloor flo2 = UnderLayFloor.forID(FloorFrame.selectedUnderlayId);
							id = FloorFrame.selectedUnderlayId;

							int color = (Client.method1005(
									flo.groundHue * 256 / flo.alpha, flo.groundSaturation, flo.groundLightness));
							int color2 = (Client.method1005(
									flo2.hue,
									flo2.saturation,
									flo2.lightness));

							//System.out.println(Client.method207(i_49_, mod)+":"+Client.method77(i_49_, color));

							CompoundTile class38 = new CompoundTile(ShapeFrame.selectedShapeOpcode, ShapeFrame.selectedShapeRotation, flo2.textureId,
									x, y, i_50_, i_46_, i_47_, i_48_, color, color, color, color, color2, color2, color2, color2, 0xFFFFFF, 0xFFFFFF);

							Client.sceneGraph.groundTiles[z][x][y].decoratedTile = null;
							Client.sceneGraph.groundTiles[z][x][y].shapedTile = class38;

							Client.overlayIds[z][x][y] = (byte) (ShapeFrame.selectedShapeOpcode >= 1 ? FloorFrame.selectedOverlayId + 1 : 0);
							Client.underlayIds[z][x][y] = (byte) (id + 1);
							Client.underlayShapes[z][x][y] = (byte) (ShapeFrame.selectedShapeOpcode - 1);
							Client.underlayRotations[z][x][y] = (byte) ShapeFrame.selectedShapeRotation;
						}
					}
					// System.out.println((Client.sceneGraph.groundTiles[z][x][y].decoratedTile==null)+":"+(Client.sceneGraph.groundTiles[z][x][y].compoundTile
					// == null));
				}
			}
		}
	}

	static CompoundTile tile;
	static byte overlayId;
	static byte underlayShape;
	static byte underlayRotation;
	
	public static void removeObject(int x, int y, int z) {
		Enumeration<Long> it = Client.objects.keys();

		while (it.hasMoreElements()) {
		  Long entry = it.nextElement();

		  GameObject obj = Client.objects.get(entry);
		  if(obj.x == x && obj.y == y && obj.z == z) {
			  Client.objects.remove(entry);
			  System.out.println("Removed loc on "+x+","+y);
		  }
		}
	}

	public static int getObjectId(int x, int y, int z) {
		int loc = 0;
		loc |= y & 0x3f;
		loc |= (x & 0x3f) << 6;
		loc |= (z & 0x3) << 12;
		Enumeration<Long> it = Client.objects.keys();

		while (it.hasMoreElements()) {
			Long entry = it.nextElement();

			GameObject obj = Client.objects.get(entry);
			if(obj.getLocation() == loc) {
				return obj.getId();
			}
		}
		return -1;
	}
	
	public static void addCopy(int x, int y, int z, int startX, int startY) {
		int loc = 0;
		loc |= y & 0x3f;
		loc |= (x & 0x3f) << 6;
		loc |= (z & 0x3) << 12;
		Enumeration<Long> it = Client.objects.keys();

		while (it.hasMoreElements()) {
		  Long entry = it.nextElement();

		  GameObject obj = Client.objects.get(entry);
		  if(obj.getLocation() == loc) {
			  obj.x = Math.abs(x - startX);
			  obj.y = Math.abs(y - startY);
			  copiedList.add(obj);
		  }
		}
	}
	
	public static void addObject(int id, int x, int y, int z, int type, int rotation) {
		GameObject obj = new GameObject(id, x, y, z, type, rotation);
		Client.objects.put(obj.hash(), obj);
	}
	
	public enum EditingMode {
		SETTINGS,
		HEIGHT,
		OVERLAY,
		UNDERLAY,
		OBJECTS;
	}
	
	public enum HeightMode {
		PLAIN,
		INTERPOLATED;
	}

}