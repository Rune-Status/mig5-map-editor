package org.mapeditor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;

import org.mapeditor.client.Client;
import org.mapeditor.client.DecoratedTile;
import org.mapeditor.client.GameObject;
import org.mapeditor.client.GroundTile;
import org.mapeditor.client.ObjectDefinition;
import org.mapeditor.editor.Brush;
import org.mapeditor.editor.Editor;
import org.mapeditor.editor.Editor.EditingMode;
import org.mapeditor.editor.Editor.HeightMode;
import org.mapeditor.editor.FloorFrame;
import org.mapeditor.editor.ShapeFrame;
import org.mapeditor.fs.Repacker;
import org.mapeditor.util.JSpinField;
import org.mapeditor.util.SpringUtilities;
import org.mapeditor.util.Utils;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;

import java.awt.Canvas;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import javax.swing.event.ChangeListener;

import javax.swing.JComboBox;

public class GUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JCheckBoxMenuItem chckbxmntmDisableObjects;
	public static String title = "Map editor";
	public static JPanel minimapPanel;
	public static JButton btnSelectOverlay;
	public static JButton selectShapeButton;
	public static JButton selectTileButton;
	public static JPanel overlayImage;
	public static JPanel overlayImage2;
	public static JPanel shapeImage;
	public static JPanel underlayImage;
	private JRadioButton rdbtnOverlay;
	private JRadioButton rdbtnUnderlay;
	private JRadioButton rdbtnHeight;
	private JRadioButton rdbtnSettings;
	private JRadioButton rdbtnObjects;
	private JPanel overlayPanel;
	private JPanel underlayPanel;
	private JPanel heightPanel;
	private JPanel settingsPanel;
	private JPanel locsPanel;
	private JTextField textField;
	private JList<String> list;
	public static Canvas objectRenderCanvas;
	public static JSpinner heightPower;
	public static JSpinner tileFlag;
	public static JTabbedPane layeredPane;
	private JRadioButton rdbtnInterpolatedMode;
	private JRadioButton rdbtnPlainMode;
	private JComboBox<Integer> comboBox;

	/**
	 * Create the frame.
	 */
	public GUI() {
		super(title);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1331, 715);
		setLocation(5, 5);
		setJMenuBar(getToolbar());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 930, 662);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		Client.worldId = 0;
		Client.modeWhere = 0;
		Client.modeWhat = 0;
		Client.highMem();
		Client client = new Client(panel);
		try {
			int sizeX = panel.getWidth();
			int sizeY = panel.getHeight();
			client.initFrame(InetAddress.getLocalHost(), sizeX, sizeY, 414, 12, Client.modeWhat + 32);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		panel.add(client, BorderLayout.CENTER);
		
		client.requestFocus();
		client.requestFocusInWindow();
		client.setVisible(true);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(936, 0, 389, 365);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		layeredPane = new JTabbedPane();
		layeredPane.setFocusable(false);
		final JPanel panel_3 = new JPanel();
		panel_3.setVisible(false);
		layeredPane.addTab("Mini map", panel_3);
		panel_3.setLayout(null);
		
		minimapPanel = new JPanel();
		minimapPanel.setBounds(0, 0, 384, 337);
		panel_3.add(minimapPanel);
		minimapPanel.setLayout(null);
		final JPanel searchPanel = new JPanel();
		layeredPane.addTab("Search", searchPanel);
		searchPanel.setLayout(null);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String input = textField.getText();
	    		Vector<String> data = new Vector<String>();
		    	if (input == null || input.length() == 0) {
	    			for (int id = 0; id < Client.totalObjects; id++) {
	    				ObjectDefinition def = ObjectDefinition.forID(id);
	    				String result = def.name.toString().toLowerCase();
	    				data.add(id + " - " + result);
	    			}
		    		Client.totalResults = 0;
		    	} else {
		    		String searchName = input;
		    		int foundResults = 0;
		    		String[] results = new String[100];
		    		for (;;) {
		    			int regex = searchName.indexOf(32);
		    			if (regex == -1) {
		    				searchName = searchName.trim();
		    				if (searchName.length() > 0)
		    					results[foundResults++] = searchName.toLowerCase();
		    				break;
		    			}
		    			String space = searchName.substring(0, regex).trim();
		    			if (space.length() > 0)
		    				results[foundResults++] = space.toLowerCase();
		    			searchName = searchName.substring(regex + 1);
		    		}
		    		Client.totalResults = 0;
		    		synchronized(Client.lock) {
		    		while_8_:
		    			for (int id = 0; id < Client.totalObjects; id++) {
		    				ObjectDefinition def = ObjectDefinition.forID(id);
		    				String result = def.name.toString().toLowerCase();
		    				for (int id_ = 0; id_ < foundResults; id_++) {
		    					if (result.indexOf(results[id_]) == -1)
		    						continue while_8_;
		    				}
		    				data.add(id + " - " + result);
		    				Client.totalResults++;
		    			}
		    		}
		    	}
	    		list.setListData(data);
			}
		});
		textField.setBounds(10, 11, 127, 20);
		searchPanel.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 127, 287);
		searchPanel.add(scrollPane);
		
		list = new JList<String>();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(!arg0.getValueIsAdjusting()) {
					String value = list.getSelectedValue();
					if(value != null) {
						Editor.selectedLocationId = Integer.parseInt(value.substring(0, value.indexOf("-") - 1));
						refreshTypeComboBox();
					}
				}
			}
		});
		scrollPane.setViewportView(list);
		
		objectRenderCanvas = new Canvas();
		objectRenderCanvas.setBounds(143, 11, 231, 319);
		searchPanel.add(objectRenderCanvas);
		panel_1.add(layeredPane, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(936, 366, 389, 296);
		contentPane.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane layeredPane_1 = new JTabbedPane();
		panel_2.add(layeredPane_1, BorderLayout.CENTER);
		layeredPane_1.setFocusable(false);
		
		layeredPane_1.addTab("Elements", getSideOptionsPanel());
		layeredPane_1.addTab("Options", getSideToolsPanel());
		JPanel elementPanel = new JPanel();
		layeredPane_1.addTab("Tools", elementPanel);
		elementPanel.setLayout(null);
		
		rdbtnOverlay = new JRadioButton("Overlay");
		rdbtnOverlay.setSelected(false);
		rdbtnOverlay.addActionListener(this);
		rdbtnOverlay.setBounds(24, 32, 63, 23);
		elementPanel.add(rdbtnOverlay);
		
		rdbtnUnderlay = new JRadioButton("Underlay");
		rdbtnUnderlay.addActionListener(this);
		rdbtnUnderlay.setBounds(89, 32, 69, 23);
		elementPanel.add(rdbtnUnderlay);
		
		rdbtnHeight = new JRadioButton("Height");
		rdbtnHeight.addActionListener(this);
		rdbtnHeight.setBounds(160, 32, 63, 23);
		elementPanel.add(rdbtnHeight);
		
		rdbtnSettings = new JRadioButton("Settings");
		rdbtnSettings.addActionListener(this);
		rdbtnSettings.setBounds(225, 32, 69, 23);
		elementPanel.add(rdbtnSettings);
		
		rdbtnObjects = new JRadioButton("Locations");
		rdbtnObjects.setBounds(296, 32, 109, 23);
		rdbtnObjects.addActionListener(this);
		elementPanel.add(rdbtnObjects);
		
		JLabel lblSelectEditingMode = new JLabel("Select editing mode");
		lblSelectEditingMode.setBounds(145, 11, 115, 14);
		elementPanel.add(lblSelectEditingMode);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 62, 364, 2);
		elementPanel.add(separator);
		
		overlayPanel = new JPanel();
		overlayPanel.setLayout(null);
		overlayPanel.setVisible(false);
		
		underlayPanel = new JPanel();
		underlayPanel.setBounds(0, 62, 384, 206);
		elementPanel.add(underlayPanel);
		underlayPanel.setLayout(null);
		
		underlayImage = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if(FloorFrame.selectedUnderlayImage != null) {
					g.drawImage(FloorFrame.selectedUnderlayImage, 0, 0, null);
				}
			}
			@Override public Dimension getSize() {return new Dimension(32, 32);}
			@Override public Dimension getMaximumSize() { return new Dimension(32, 32); }
			@Override public Dimension getMinimumSize() { return new Dimension(32, 32); }
			@Override public Dimension getPreferredSize() { return new Dimension(32, 32); }
		};
		underlayImage.setBounds(10, 30, 32, 32);
		underlayPanel.add(underlayImage);
		
		selectTileButton = new JButton("Select tiles");
		selectTileButton.setEnabled(false);
		selectTileButton.setBounds(10, 71, 93, 23);
		underlayPanel.add(selectTileButton);
		
		JLabel lblNewLabel = new JLabel("Selected tiles: ");
		lblNewLabel.setBounds(10, 11, 81, 14);
		underlayPanel.add(lblNewLabel);
		
		JLabel selectedShapeLabel = new JLabel("Selected shape:");
		selectedShapeLabel.setBounds(118, 11, 81, 14);
		underlayPanel.add(selectedShapeLabel);
		
		shapeImage = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if(ShapeFrame.selectedShapeImage != null) {
					g.drawImage(ShapeFrame.selectedShapeImage, 0, 0, null);
				}
			}
			@Override public Dimension getSize() {return new Dimension(32, 32);}
			@Override public Dimension getMaximumSize() { return new Dimension(32, 32); }
			@Override public Dimension getMinimumSize() { return new Dimension(32, 32); }
			@Override public Dimension getPreferredSize() { return new Dimension(32, 32); }
		};
		shapeImage.setBounds(137, 29, 32, 32);
		underlayPanel.add(shapeImage);
		
		selectShapeButton = new JButton("Select shape");
		selectShapeButton.setEnabled(false);
		selectShapeButton.setBounds(115, 72, 93, 23);
		underlayPanel.add(selectShapeButton);
		
		overlayImage2 = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if(FloorFrame.selectedOverlayImage != null) {
					g.drawImage(FloorFrame.selectedOverlayImage, 0, 0, null);
				}
			}
			@Override public Dimension getSize() {return new Dimension(32, 32);}
			@Override public Dimension getMaximumSize() { return new Dimension(32, 32); }
			@Override public Dimension getMinimumSize() { return new Dimension(32, 32); }
			@Override public Dimension getPreferredSize() { return new Dimension(32, 32); }
		};
		overlayImage2.setBounds(47, 29, 32, 32);
		underlayPanel.add(overlayImage2);
		selectShapeButton.addActionListener(this);
		selectTileButton.addActionListener(this);
		overlayPanel.setBounds(0, 62, 384, 206);
		elementPanel.add(overlayPanel);
		overlayPanel.setLayout(null);
		
		overlayImage = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				if(FloorFrame.selectedOverlayImage != null) {
					g.drawImage(FloorFrame.selectedOverlayImage, 0, 0, null);
				}
			}
			@Override public Dimension getSize() {return new Dimension(32, 32);}
			@Override public Dimension getMaximumSize() { return new Dimension(32, 32); }
			@Override public Dimension getMinimumSize() { return new Dimension(32, 32); }
			@Override public Dimension getPreferredSize() { return new Dimension(32, 32); }
		};
		overlayImage.setBounds(34, 36, 32, 32);
		overlayPanel.add(overlayImage);
		
		JLabel lblNewLabel_1 = new JLabel("Selected overlay");
		lblNewLabel_1.setBounds(10, 11, 93, 14);
		overlayPanel.add(lblNewLabel_1);
		
		btnSelectOverlay = new JButton("Select tiles");
		btnSelectOverlay.setEnabled(false);
		btnSelectOverlay.addActionListener(this);
		btnSelectOverlay.setBounds(10, 82, 89, 23);
		overlayPanel.add(btnSelectOverlay);
		
		heightPanel = new JPanel();
		heightPanel.setBounds(0, 62, 384, 206);
		heightPanel.setVisible(false);
		heightPanel.setLayout(null);
		elementPanel.add(heightPanel);
		
		heightPower = new JSpinner();
		heightPower.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				SpinnerNumberModel model = (SpinnerNumberModel) heightPower.getModel();
				int value = model.getNumber().intValue();
				heightPower.setValue(value);
			}
		});
		heightPower.setBounds(50, 10, 86, 20);
		heightPanel.add(heightPower);
		
		JLabel lblPower = new JLabel("Power: ");
		lblPower.setBounds(10, 11, 46, 14);
		heightPanel.add(lblPower);
		
		rdbtnPlainMode = new JRadioButton("Plain mode");
		rdbtnPlainMode.addActionListener(this);
		rdbtnPlainMode.setSelected(true);
		rdbtnPlainMode.setBounds(10, 35, 109, 23);
		heightPanel.add(rdbtnPlainMode);
		
		rdbtnInterpolatedMode = new JRadioButton("Interpolated mode");
		rdbtnInterpolatedMode.addActionListener(this);
		rdbtnInterpolatedMode.setBounds(10, 66, 125, 23);
		heightPanel.add(rdbtnInterpolatedMode);



		settingsPanel = new JPanel();
		settingsPanel.setBounds(0, 62, 384, 206);
		settingsPanel.setVisible(false);
		settingsPanel.setLayout(null);
		elementPanel.add(settingsPanel);

		tileFlag = new JSpinner();
		tileFlag.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				SpinnerNumberModel model = (SpinnerNumberModel) tileFlag.getModel();
				int value = model.getNumber().intValue();
				tileFlag.setValue(value);
			}
		});
		tileFlag.setBounds(50, 10, 86, 20);
		settingsPanel.add(tileFlag);

		JLabel flagLabel = new JLabel("Flag: ");
		flagLabel.setBounds(10, 11, 46, 14);
		settingsPanel.add(flagLabel);



		
		locsPanel = new JPanel();
		locsPanel.setVisible(false);
		locsPanel.setBounds(0, 62, 384, 206);
		elementPanel.add(locsPanel);
		locsPanel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Rotate cw");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(10, 5, 81, 23);
		locsPanel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Rotate ccw");
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setBounds(105, 5, 87, 23);
		locsPanel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Name selected");
		btnNewButton_3.addActionListener(this);
		btnNewButton_3.setBounds(200, 5, 105, 23);
		locsPanel.add(btnNewButton_3);
		
		comboBox = new JComboBox<Integer>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox<Integer> cb = (JComboBox<Integer>) arg0.getSource();
				if(cb.getSelectedItem() != null) {
			        int type = (Integer) cb.getSelectedItem();
			        Editor.selectedLocationType = type;
			        System.out.println("selected "+type+" loc type");
				} else {
					Editor.selectedLocationType = -1;
				}
			}
		});
		comboBox.setBounds(85, 35, 60, 20);
		locsPanel.add(comboBox);
		
		JLabel lblSelectType = new JLabel("Select type:");
		lblSelectType.setBounds(10, 35, 80, 20);
		locsPanel.add(lblSelectType);
		JPanel panel_4 = new JPanel();
		layeredPane_1.addTab("Information", panel_4);
	}
	
	private JMenuBar getToolbar() {
		JMenuBar toolbar = new JMenuBar();
		JMenu menu = new JMenu("File");
		toolbar.add(menu);
		
		JMenu mnExport = new JMenu("Export");
		menu.add(mnExport);
		
		JMenuItem mntmCache = new JMenuItem("Cache");
		mntmCache.addActionListener(this);
		mnExport.add(mntmCache);
		
		JMenuItem mntmFile = new JMenuItem("File");
		mntmFile.addActionListener(this);
		mnExport.add(mntmFile);
		
		JSeparator separator = new JSeparator();
		menu.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		menu.add(mntmExit);
		
		toolbar.add(getEditMenu());
		toolbar.add(getModeMenu());
		JMenu map = new JMenu("Map");
		toolbar.add(map);
		
		chckbxmntmDisableObjects = new JCheckBoxMenuItem("Disable Objects");
		chckbxmntmDisableObjects.addActionListener(this);
		map.add(chckbxmntmDisableObjects);
		
		JCheckBoxMenuItem checkbox1 = new JCheckBoxMenuItem("Disable tile shadows");
		checkbox1.addActionListener(this);
		map.add(checkbox1);
		
		JCheckBoxMenuItem checkbox2 = new JCheckBoxMenuItem("Disable tile blending");
		checkbox2.addActionListener(this);
		map.add(checkbox2);
		
		JMenuItem mntmViewRegion = new JMenuItem("View region");
		mntmViewRegion.addActionListener(this);
		map.add(mntmViewRegion);
		
		JSeparator separator_1 = new JSeparator();
		map.add(separator_1);
		
		JMenuItem mntmCreateBlankSpace = new JMenuItem("Create blank space");
		mntmCreateBlankSpace.addActionListener(this);
		map.add(mntmCreateBlankSpace);
		toolbar.add(new JMenu("3D Controls"));
		JMenu options = new JMenu("Options");
		
		JCheckBoxMenuItem checkbox3 = new JCheckBoxMenuItem("Show fps");
		checkbox3.setSelected(true);
		checkbox3.addActionListener(this);
		options.add(checkbox3);
		
		toolbar.add(options);
		
		JCheckBoxMenuItem chckbxmntmDebugLocations = new JCheckBoxMenuItem("Debug locations");
		chckbxmntmDebugLocations.addActionListener(this);
		options.add(chckbxmntmDebugLocations);
		toolbar.add(new JMenu("Help"));
		
		JButton gc = new JButton("GC");
		toolbar.add(Box.createHorizontalGlue());
		toolbar.add(gc);
		gc.addActionListener(this);
		return toolbar;
	}
	
	public JMenu getEditMenu() {
		JMenu menu = new JMenu("Edit");
		return menu;
	}
	
	public JMenu getModeMenu() {
		JMenu menu = new JMenu("Mode");
		
		JMenu brushes = new JMenu("Brush");
		GUIUtils.createMenuRadioButtons(brushes, this, "1x1", "2x2","3x3","10x10", "64x64", "Custom size");
		
		
		menu.add(brushes);
		
		JMenuItem mntmCopy = new JMenuItem("Copy");
		mntmCopy.addActionListener(this);
		menu.add(mntmCopy);
		return menu;
	}
	
	public JPanel getSideToolsPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JButton btnRebuildMap = new JButton("Rebuild map");
		btnRebuildMap.addActionListener(this);
		btnRebuildMap.setToolTipText("Rebuilds edited map");
		btnRebuildMap.setBounds(10, 11, 102, 23);
		panel.add(btnRebuildMap);
		
		JButton btnNewButton = new JButton("Create blank space");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(122, 11, 126, 23);
		panel.add(btnNewButton);
		return panel;
	}
	
	public JPanel getSideOptionsPanel() {
		JPanel panel = new JPanel(new SpringLayout());
		
		panel.add(new JLabel("Height:"));
		panel.add(new JSpinField(0, 3) {
			private static final long serialVersionUID = 1L;

			@Override
			public void stateChanged(ChangeEvent e) {
				SpinnerNumberModel model = (SpinnerNumberModel) spinner.getModel();
				int value = model.getNumber().intValue();
				if(value >= 0 && value <= 3) {
					setValue(value);	
					Editor.editedHeight = value;
					Client.height = value;
				}
			}
		});
		
		int fakeRows = 7;
		
		for(int i = 0; i < fakeRows * 2; i++) {
			panel.add(new JLabel(""));
		}
		
		SpringUtilities.makeGrid(panel, fakeRows + 1, 2, 5, 5, 2, 2);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		System.out.println(action);
		if(action.equals("GC")) {
			System.gc();
		}
		if(action.equals("Name selected")) {
			String name = JOptionPane.showInputDialog(null, "Enter new name: ");
			Utils.writeName(Editor.selectedLocationId + ":" + name, "./data/locnames.txt");
		}
		if(action.equals("Copy") || action.equals("Paste")) {
			if(!Editor.copied) {
				String[] info = JOptionPane.showInputDialog(null,
						  "Enter start coordinates: x,y",
						  JOptionPane.QUESTION_MESSAGE).split(",");
				int startX = Integer.parseInt(info[0]);
				int startY = Integer.parseInt(info[1]);
				
				String[] info2 = JOptionPane.showInputDialog(null,
						  "Enter end coordinates: x,y",
						  JOptionPane.QUESTION_MESSAGE).split(",");
				int endX = Integer.parseInt(info2[0]);
				int endY = Integer.parseInt(info2[1]);
				
				Editor.copiedList.clear();
				
				//for(int z = 0; z < 4; z++) {
					for(int x = startX; x <= endX; x++) {
						for(int y = startY; y <= endY; y++) {
							Editor.addCopy(x, y, 0, startX, startY);
						}
					}
				//}
				
				System.out.println("size2="+Editor.copiedList.size());
				
				
				if(Editor.copiedList.size() > 0) {
					((JMenuItem)e.getSource()).setText("Paste");
					Editor.copied = true;
				}
				return;
			} else if(Editor.copied) {
				
				String[] info = JOptionPane.showInputDialog(null,
						  "Enter start coordinates: x,y",
						  JOptionPane.QUESTION_MESSAGE).split(",");
				int startX = Integer.parseInt(info[0]);
				int startY = Integer.parseInt(info[1]);
				
				for(int i = 0; i < Editor.copiedList.size(); i++) {
					GameObject obj = Editor.copiedList.get(i);
					obj.x += startX;
					obj.y += startY;
					Editor.addObject(obj.id, obj.x, obj.y, obj.z, obj.type, obj.rotation);
					Client.method1006(obj.x, Client.collisionMaps[Client.height], obj.rotation, obj.id,
							Client.height, Client.sceneGraph, obj.type, obj.y);
				}
				
				((JMenuItem)e.getSource()).setText("Copy");
				Editor.copied = false;
				return;
			}
		}
		if(action.equals("Interpolated mode")) {
			rdbtnInterpolatedMode.setSelected(true);
			rdbtnPlainMode.setSelected(false);
			Editor.heightMode = HeightMode.INTERPOLATED;
		}
		if(action.equals("Plain mode")) {
			rdbtnInterpolatedMode.setSelected(false);
			rdbtnPlainMode.setSelected(true);
			Editor.heightMode = HeightMode.PLAIN;
		}
		if(action.equals("Rotate ccw")) {
			Editor.rotation--;
			if(Editor.rotation < 0)
				Editor.rotation = 3;
		}
		if(action.equals("Rotate cw")) {
			Editor.rotation++;
			if(Editor.rotation > 3)
				Editor.rotation = 0;
		}
		if(action.equals("Settings")) {
			Editor.mode = EditingMode.SETTINGS;
			/**
			 * Buttons
			 */
			rdbtnOverlay.setSelected(false);
			rdbtnHeight.setSelected(false);
			rdbtnUnderlay.setSelected(false);
			rdbtnSettings.setSelected(true);
			rdbtnObjects.setSelected(false);
			/**
			 * Panels
			 */
			overlayPanel.setVisible(false);
			underlayPanel.setVisible(false);
			heightPanel.setVisible(false);
			settingsPanel.setVisible(true);
			locsPanel.setVisible(false);
		}
		if(action.equals("Underlay")) {
			Editor.mode = EditingMode.UNDERLAY;
			/**
			 * Buttons
			 */
			rdbtnOverlay.setSelected(false);
			rdbtnUnderlay.setSelected(true);
			rdbtnHeight.setSelected(false);
			rdbtnSettings.setSelected(false);
			rdbtnObjects.setSelected(false);
			/**
			 * Panels
			 */
			overlayPanel.setVisible(false);
			underlayPanel.setVisible(true);
			heightPanel.setVisible(false);
			settingsPanel.setVisible(false);
			locsPanel.setVisible(false);
		}
		if(action.equals("Overlay")) {
			Editor.mode = EditingMode.OVERLAY;
			/**
			 * Buttons
			 */
			rdbtnOverlay.setSelected(true);
			rdbtnUnderlay.setSelected(false);
			rdbtnHeight.setSelected(false);
			rdbtnSettings.setSelected(false);
			rdbtnObjects.setSelected(false);
			/**
			 * Panels
			 */
			overlayPanel.setVisible(true);
			underlayPanel.setVisible(false);
			heightPanel.setVisible(false);
			settingsPanel.setVisible(false);
			locsPanel.setVisible(false);
		}
		if(action.equals("Height")) {
			Editor.mode = EditingMode.HEIGHT;
			/**
			 * Buttons
			 */
			rdbtnOverlay.setSelected(false);
			rdbtnUnderlay.setSelected(false);
			rdbtnHeight.setSelected(true);
			rdbtnSettings.setSelected(false);
			rdbtnObjects.setSelected(false);
			/**
			 * Panels
			 */
			overlayPanel.setVisible(false);
			underlayPanel.setVisible(false);
			heightPanel.setVisible(true);
			settingsPanel.setVisible(false);
			locsPanel.setVisible(false);
		}
		if(action.equals("Locations")) {
			Editor.mode = EditingMode.OBJECTS;
			/**
			 * Buttons
			 */
			rdbtnOverlay.setSelected(false);
			rdbtnUnderlay.setSelected(false);
			rdbtnHeight.setSelected(false);
			rdbtnSettings.setSelected(false);
			rdbtnObjects.setSelected(true);
			/**
			 * Panels
			 */
			overlayPanel.setVisible(false);
			underlayPanel.setVisible(false);
			heightPanel.setVisible(false);
			settingsPanel.setVisible(false);
			locsPanel.setVisible(true);
		}
		if(action.equals("Disable Objects")) {
			Editor.disableObjects = !Editor.disableObjects;
		}
		if(action.equals("View region")) {
			String[] info = JOptionPane.showInputDialog(null,
					  "Enter coordinates: x,y",
					  JOptionPane.QUESTION_MESSAGE).split(",");
			Client.mapX = (Integer.parseInt(info[0]) / (Integer.parseInt(info[0]) > 1000 ? 64 : 1));
			Client.mapY = (Integer.parseInt(info[1]) / (Integer.parseInt(info[1]) > 1000 ? 64 : 1));
			Client.refreshMap();
			Client.setGameState(25);
		}
		if(action.equals("Create blank space")) {
			int z = Client.height;
			for(int x = 0; x < 64; x++) {
				for(int y = 0; y < 64; y++) {
					if(Client.sceneGraph.groundTiles[z][x][y] == null) {
						Client.sceneGraph.groundTiles[z][x][y] = new GroundTile(z, x, y);
					}
					Client.sceneGraph.groundTiles[z][x][y].aBoolean1266 = true;
					if(Client.sceneGraph.groundTiles[z][x][y].decoratedTile == null && Client.sceneGraph.groundTiles[z][x][y].shapedTile == null) {
						int color = Client.method1005(360 * 256 / 1, 100, 100);
						Client.sceneGraph.groundTiles[z][x][y].decoratedTile = new DecoratedTile(color,color,color,color,-1,0,false);
						Client.sceneGraph.groundTiles[z][x][y].shapedTile = null;
					}
				}
			}
		}
		if(action.equals("Select tiles")) {
			new FloorFrame();
		}
		if(action.equals("Select shape")) {
			new ShapeFrame();
		}
		if(action.equals("Debug locations")) {
			Client.locationToggle = !Client.locationToggle;
		}
		if(action.equals("Show fps")) {
			Client.showFps = !Client.showFps;
		}
		if(action.equals("Cache")) {
			for(int z = 0; z < 4; z++) {
				for(int x = 0; x < 104; x++) {
					for(int y = 0; y < 104; y++) {
						//Client.sceneGraph.groundTiles[z][x][y] = new GroundTile(z, x, y);
						//Client.sceneGraph.groundTiles[z][x][y].decoratedTile = new DecoratedTile(0,0,0,0,0,0,false);
						//Client.tileBrightness[x][y] = 1;
						//Client.tileHeight[z][x][y] = 1;
					}
				}
			}
		}
		if(action.equals("File")) {
			try {
				ByteArrayOutputStream bout = new ByteArrayOutputStream();
				DataOutputStream os  = new DataOutputStream(bout);
				for(int z = 0; z < 4; z++) {
					for(int x = 0; x < 64; x++) {
						for(int y = 0; y < 64; y++) {
							if(Client.overlayIds[z][x][y] != 0) {
								os.writeByte(Client.overlayIds[z][x][y] + 81);
							}
							if(Client.tileSettings[z][x][y] != 0) {
								os.writeByte(Client.tileSettings[z][x][y] + 49);
							}
							if(Client.underlayIds[z][x][y] != 0) {
								int shape = Client.underlayShapes[z][x][y];
								int rotation = Client.underlayRotations[z][x][y];
								int tot = (shape * 4) + ((rotation & 3) + 2);
								
								os.writeByte(tot & 0xFF);
								os.writeByte(Client.underlayIds[z][x][y] & 0xFF);
								
							}
							if(!Client.heightMod[z][x][y]) {
								os.writeByte(1);
								if(z == 0) 
									os.writeByte(-Client.tileHeight[0][x][y] / 8);
								else 
									os.writeByte((Client.tileHeight[z - 1][x][y] - Client.tileHeight[z][x][y]) / 8);
							} else
								os.writeByte(0);
							
						}
					}	
				}
				String fileName = Client.squareX+"_"+Client.squareY;
				byte[] abyte0 = bout.toByteArray();
				(new File((new File("./maps/m"+fileName+".new")).getParent())).mkdirs();
	            FileOutputStream fileoutputstream = new FileOutputStream("./maps/m"+fileName+".new");
	            fileoutputstream.write(abyte0, 0, abyte0.length);
	            fileoutputstream.close();
	            Repacker.repack();
			} catch(Exception ea) {
				ea.printStackTrace();
			}
		}
		if(action.equals("Rebuild map")) {
			Client.refreshMap();
			Client.setGameState(25);
		}
		if(action.equals("Disable tile shadows")) {
			Editor.disableTileShadow = !Editor.disableTileShadow;
			Client.refreshMap();
			Client.setGameState(25);
		}
		if(action.equals("Disable tile blending")) {
			Editor.disableTileBlending = !Editor.disableTileBlending;
			Client.refreshMap();
			Client.setGameState(25);
		}
		if(action.equals("1x1")) {
			Brush.setSize(1);
		}
		if(action.equals("2x2")) {
			Brush.setSize(2);
		}
		if(action.equals("3x3")) {
			Brush.setSize(3);
		}
		if(action.equals("10x10")) {
			Brush.setSize(10);
		}
		if(action.equals("64x64")) {
			Brush.setSize(64);
		}
		if(action.startsWith("Custom size")) {
			String value = JOptionPane.showInputDialog("Enter size");
			try {
				int size = Integer.parseInt(value);
				
				((JRadioButtonMenuItem)e.getSource()).setText("Custom size (" + size + "x" + size + ")");
				Brush.setSize(size);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}

	}
	
	public void refreshTypeComboBox() {
		if(Editor.selectedLocationId != -1) {
			comboBox.removeAllItems();
			ObjectDefinition loc = ObjectDefinition.forID(Editor.selectedLocationId);
			if(loc != null) {
				if(loc.modelTypes == null)
					comboBox.addItem(10);
				else
					for(int i = 0; i < loc.modelTypes.length; i++)
						comboBox.addItem(loc.modelTypes[i]);
			}
		}
	}
	
	public JPanel getVerticalPanel(String label, JPanel icon, ActionListener listener) {
		GridLayout layout = new GridLayout();
		JPanel panel = new JPanel(layout);
		int columns = 3;
		if(icon != null) {
			columns++;
		}
		JButton button = new JButton("Select");
		button.addActionListener(listener);
		button.setName(label);
		
		layout.setColumns(columns);
		panel.add(new JLabel(label));
		panel.add(icon == null ? new JLabel("") : icon);
		panel.add(new JLabel(""));
		panel.add(button);
		return panel;
	}
}
