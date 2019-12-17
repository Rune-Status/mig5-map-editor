package org.mapeditor.gui;

import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class GUIUtils {
	
	public static JMenuItem createMenuItem(String name, JMenu menu, ActionListener listener) {
		JMenuItem item = new JMenuItem(name);
		item.addActionListener(listener);
		menu.add(item);
		return item;
	}
	
	public static void createMenuRadioButtons(JMenu menu, ActionListener listener, String... names) {
		ButtonGroup group = new ButtonGroup();
		for(String name : names) {
			JRadioButtonMenuItem item = new JRadioButtonMenuItem(name);
			item.addActionListener(listener);
			menu.add(item);
			group.add(item);
		}
	}

}
