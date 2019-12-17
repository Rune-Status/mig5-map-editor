package org.mapeditor.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Utils {
	
	public static Map<Integer, String> loc_names = new HashMap<Integer, String>();
	
	public static void writeName(String name, String path) {
		try {
			File file = new File(path);
			if(file.exists()) {
				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
				try {
					bw.write(name);
					bw.newLine();
				} finally {
					bw.close();
				}
			}
		} catch (Exception var3) {
			var3.printStackTrace();
		}
		loadNames("./data/locnames.txt");
	}
	
	public static void loadNames(String path) {
		loc_names.clear();
		try {
			File file = new File(path);
			if(file.exists()) {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				
				String line = "";
				try {
					while((line = reader.readLine()) != null) {
						String[] split = line.split(":");
						loc_names.put(Integer.parseInt(split[0]), split[1]);
					}
				} finally {
					reader.close();
				}
			}
		} catch (Exception var3) {
			var3.printStackTrace();
		}
	}
	
	public static JPanel addTitledBorderPanel(String title) {
		TitledBorder border = BorderFactory.createTitledBorder(title);
		border.setTitleJustification(TitledBorder.LEFT);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(border);
		
		return panel;
	}
	
	public static int[] rgbToHsl(int red, int green, int blue) {
		int saturation = 0;
		int lightness = 0;
		int huee = 0;
		int alpha = 0;
		
		
		double max = red;
		double hue = 0.0;
		double min = red;
		if (green < min)
		    min = green;
		if (green > max)
		    max = green;
		if (blue < min)
		    min = blue;
		if (blue > max)
		    max = blue;
		double sat = 0.0;
		double delta = (min + max) / 2.0;
		lightness = (int) (delta * 256.0);
		if (min != max) {
			if (max == red)
				hue = (-blue + green) / (max - min);
			else if (green != max) {
				if (blue == max)
					hue = (-green + red) / (-min + max) + 4.0;
			} else
				hue = (-red + blue) / (-min + max) + 2.0;
			if (delta < 0.5)
				sat = (-min + max) / (min + max);
			if (delta >= 0.5)
				sat = (max - min) / (-min + (-max + 2.0));
		}
		if (lightness >= 0) {
			if (lightness > 255)
				lightness = 255;
		} else
			lightness = 0;
		saturation = (int) (sat * 256.0);
		if (delta > 0.5)
			alpha = (int) ((1.0 - delta) * sat * 512.0);
		else
			alpha = (int) (sat * delta * 512.0);
		if (saturation < 0)
		    saturation = 0;
		else if (saturation > 255)
		    saturation = 255;
		if (alpha < 1)
			alpha = 1;
		hue /= 6.0;
		huee = (int) (hue * (double) alpha);
		
		return new int[] { huee, saturation, lightness, alpha };
	}

}
