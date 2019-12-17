package org.mapeditor.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import org.mapeditor.client.Client;
import org.mapeditor.client.IndexedSprite;
import org.mapeditor.client.OverLayFloor;
import org.mapeditor.client.Rasterizer;
import org.mapeditor.client.UnderLayFloor;
import org.mapeditor.fs.CustomFileSystem;
import org.mapeditor.gui.GUI;

@SuppressWarnings("serial")
public class FloorFrame extends JFrame {
	
	public static int selectedOverlayId;
	public static BufferedImage selectedOverlayImage;
	public static int selectedUnderlayId;
	public static BufferedImage selectedUnderlayImage;
	
	public FloorFrame() {
		setTitle("JagSelector");
		setLayout(new GridLayout(1, 2));
		setSize(600, 300);
		setMaximumSize(new Dimension(600, 300));
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		add(new JScrollPane(getOverlayPanel(buttonGroup)), BorderLayout.WEST);
		try {
			add(new JScrollPane(getUnderlayPanel(buttonGroup)), BorderLayout.EAST);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setVisible(true);
	}
	
	public JPanel getOverlayPanel(ButtonGroup buttonGroup) {
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel floorPanel = new JPanel(new GridLayout(0, 10, 5, 5));
		
		int cacheType = 2, cacheFile = 1;
		int size = CustomFileSystem.cache.getFilesSystem(cacheType).findFolderByID(cacheFile).getFiles().length;
		
		for(int i = 0; i < size; i++) {
			final int id = i;
			final OverLayFloor floor = OverLayFloor.forID(id);
			if(floor.groundColor == 0) 
				continue;
			BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics g = img.getGraphics();
			g.setColor(new Color(floor.groundColor));
			g.fillRect(0, 0, getWidth(), getHeight());
			final BufferedImage image = img;
			
			final JToggleButton button = new JToggleButton() {
				private static final long serialVersionUID = 1L;

				@Override
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					
					g.drawImage(image, 0, 0, null);
					
					if(isSelected()) {
						g.setColor(Color.RED);
						g.drawRect(1, 1, getWidth() - 4, getHeight() - 4);
						g.drawRect(2, 2, getWidth() - 4, getHeight() - 4);
					}
				}
				
				@Override public Dimension getSize() { return new Dimension(32, 32); }
				@Override public Dimension getPreferredSize() { return getSize(); }
				@Override public Dimension getMaximumSize() { return getSize(); }
			};
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedOverlayId = id;
					selectedOverlayImage = image;
					GUI.overlayImage2.repaint();
					GUI.overlayImage.repaint();
				}
			});
			button.setFocusable(false);
			buttonGroup.add(button);
			floorPanel.add(button);
		}
		panel.add(new JLabel("Overlays:"), BorderLayout.NORTH);
		panel.add(floorPanel, BorderLayout.CENTER);
		return panel;
	}
	
	public JPanel getUnderlayPanel(ButtonGroup buttonGroup) throws IOException {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel floorPanel = new JPanel(new GridLayout(0, 10, 5, 5));
		
		int cacheType = 2, cacheFile = 4;
		int size = CustomFileSystem.cache.getFilesSystem(cacheType).findFolderByID(cacheFile).getFiles().length;
		
		for(int i = 0; i < size; i++) {
			final int id = i;
			final UnderLayFloor floor = UnderLayFloor.forID(id);
			
			BufferedImage img = null;
			if(floor.textureId != -1 && floor.textureId < 51) {
				IndexedSprite sprite = Client.getTexture(Client.spriteWorker, Rasterizer.textureInterface.getTexture(floor.textureId));
				sprite.resize();
				img = sprite.toImage();
			} else {
				img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
				Graphics g = img.getGraphics();
				g.setColor(new Color(floor.anInt1420 != -1 ? floor.anInt1420 : floor.anInt1426));
				g.fillRect(0, 0, getWidth(), getHeight());
			}
			
			final BufferedImage image = img;
			final JToggleButton button = new JToggleButton() {
				private static final long serialVersionUID = 1L;

				@Override
				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawImage(image, 0, 0, null);
					if(isSelected()) {
						g.setColor(Color.RED);
						g.drawRect(1, 1, getWidth() - 4, getHeight() - 4);
						g.drawRect(2, 2, getWidth() - 4, getHeight() - 4);
					}
				}
				
				@Override public Dimension getSize() { return new Dimension(32, 32); }
				@Override public Dimension getPreferredSize() { return getSize(); }
				@Override public Dimension getMaximumSize() { return getSize(); }
			};
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedUnderlayId = id;
					selectedUnderlayImage = image;
					GUI.underlayImage.repaint();
				}
			});
			button.setFocusable(false);
			buttonGroup.add(button);
			floorPanel.add(button);
		}
		
		panel.add(new JLabel("Underlays:"), BorderLayout.NORTH);
		panel.add(floorPanel, BorderLayout.CENTER);
		return panel;
	}

}
