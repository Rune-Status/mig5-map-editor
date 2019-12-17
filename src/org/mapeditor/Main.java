package org.mapeditor;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.mapeditor.gui.GUI;
import org.mapeditor.gui.GUI;
import org.mapeditor.util.Utils;

public class Main {
	
	public static final int REVISION = 474;
	
	private static GUI gui;
	
	public static void main(String[] args) {
		new Main();
	}
	
	private Main() {
		Utils.loadNames("./data/locnames.txt");
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch(Exception e) {
					e.printStackTrace();
				}
				
				gui = new GUI();
			}
		});
	}
	
	public static GUI getGUI() {
		return gui;
	}

}
