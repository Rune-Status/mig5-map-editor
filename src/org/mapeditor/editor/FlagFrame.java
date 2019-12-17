package org.mapeditor.editor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;

public final class FlagFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static Flags selectedFlags;

	public FlagFrame() {
		setTitle("Flags");
		setLayout(new GridLayout(0, 1));
		
		ButtonGroup group = new ButtonGroup();
		for(final Flags flag : Flags.values()) {
			JButton button = new JButton(flag.getName());
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					selectedFlags = flag;
				}
			});
			
			group.add(button);
			add(button);
		}
		
		pack();
		setVisible(true);
	}
	
	enum Flags {
		UNWALKABLE(0x1, "Un walkable"),
		BRIDGE(0x2, "Bridge"),
		RESERVED_3(0x4, "0x4 reserved"),
		REMOVE_ROOF(0x8, "Remove upper level"),
		RESERVED_1(0x10, "0x10 reserved"),
		RESERVED_2(0x20, "0x20 reserved");
		
		
		private final int flag;
		private final String name;
		
		Flags(int flag, String name) {
			this.flag = flag;
			this.name = name;
		}
		
		public int getFlag() {
			return flag;
		}
		
		public String getName() {
			return name;
		}
		
	}

}
