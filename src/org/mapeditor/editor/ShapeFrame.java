package org.mapeditor.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import org.mapeditor.gui.GUI;

public class ShapeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public static BufferedImage selectedShapeImage;
	public static int selectedShapeOpcode = 1;
	public static int selectedShapeOpcode2 = 0;
	public static int selectedShapeRotation;
	private Map<Integer, Integer> list = new HashMap<Integer, Integer>();
	
	public ShapeFrame() {
		setTitle("JagShape");
		setLayout(new GridLayout(1, 2));
		setSize(600, 300);
		setMaximumSize(new Dimension(600, 300));
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		add(new JScrollPane(getShapePanel(buttonGroup)), BorderLayout.CENTER);

		pack();
		setVisible(true);
	}
	
	public JPanel getShapePanel(ButtonGroup buttonGroup) {
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		
		JPanel shapePanel = new JPanel(new GridLayout(0, 4, 5, 5));
		
		
		File[] shapeFiles = new File("res/tileshapes/").listFiles();
		int[] opcodes = new int[shapeFiles.length];
		for(int i = 0; i < shapeFiles.length; i++) {
			File file = shapeFiles[i];
			int opcode = Integer.parseInt(file.getName().substring(0, file.getName().lastIndexOf(".")));
			opcodes[i] = opcode;
		}
		Arrays.sort(opcodes);
		
		for(int i = 0; i < opcodes.length; i++) {
			list.put(opcodes[i], i);
		}
		
		for(final int opcode : opcodes) {
			try {
				BufferedImage img = ImageIO.read(new File("res/tileshapes/" + opcode + ".png"));
				
				for(int rot = 0; rot < 4; rot++) {
					final int rotation = rot;
					
					AffineTransform transform = new AffineTransform();
				    transform.rotate((rotation * 90) / 180.0 * Math.PI, img.getWidth()/2, img.getHeight()/2);
				    AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
				    final BufferedImage image = op.filter(img, null);
					
					//final BufferedImage image = img;
					final JToggleButton button = new JToggleButton() {
						private static final long serialVersionUID = 1L;

						@Override
						public void paintComponent(Graphics g) {
							super.paintComponent(g);
							
							/*AffineTransform at = new AffineTransform();
							at.translate(getWidth() / 2, getHeight() / 2);
							at.rotate((rot * 90) / 180.0 * Math.PI);
							at.translate(-image.getWidth()/2, -image.getHeight()/2);
							
							Graphics2D g2d = (Graphics2D) g;
				            g2d.drawImage(image, at, null);*/
							
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
							selectedShapeImage = image;
							System.out.println(opcode+":"+rotation+":"+list.get(opcode));
							selectedShapeOpcode = list.get(opcode) + 1;
							selectedShapeOpcode2 = opcode;
							selectedShapeRotation = rotation;
							GUI.shapeImage.repaint();
						}
					});
					button.setFocusable(false);
					buttonGroup.add(button);
					shapePanel.add(button);
				}
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
		
		JPanel infoPanel = new JPanel(new GridLayout(1, 2));
		JLabel overlay = new JLabel("Underlay", JLabel.CENTER);
		JLabel underlay = new JLabel("Overlay", JLabel.CENTER);
		overlay.setBackground(new Color(169, 169, 169));
		underlay.setBackground(new Color(234, 0, 255));
		overlay.setOpaque(true);
		underlay.setOpaque(true);
		overlay.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		underlay.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		infoPanel.add(overlay);
		infoPanel.add(underlay);
		
		panel.add(infoPanel, BorderLayout.NORTH);
		panel.add(shapePanel, BorderLayout.CENTER);
		return panel;
	}

}
