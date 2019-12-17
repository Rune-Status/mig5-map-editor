package org.mapeditor.client;

/* Class41 - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

final class KeyboardHandler implements KeyListener, FocusListener {
	
    static {
    	keyCodes
	    = (new int[]
	       { -1, -1, -1, -1, -1, -1, -1, -1, 85, 80, 84, -1, 91, -1, -1,
		 -1, 81, 82, 86, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1,
		 -1, 83, 104, 105, 103, 102, 96, 98, 97, 99, -1, -1, -1, -1,
		 -1, -1, -1, 25, 16, 17, 18, 19, 20, 21, 22, 23, 24, -1, -1,
		 -1, -1, -1, -1, -1, 48, 68, 66, 50, 34, 51, 52, 53, 39, 54,
		 55, 56, 70, 69, 40, 41, 32, 35, 49, 36, 38, 67, 33, 65, 37,
		 64, -1, -1, -1, -1, -1, 228, 231, 227, 233, 224, 219, 225,
		 230, 226, 232, 89, 87, -1, 88, 229, 90, 1, 2, 3, 4, 5, 6, 7,
		 8, 9, 10, 11, 12, -1, -1, -1, 101, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, 100, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
		 -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 });
    }

	static int[] keyCodes;
    
    public KeyboardHandler() {
    	/* empty */
    }
    
    public final void keyTyped(KeyEvent keyevent) {
    	keyevent.consume();
    }
    
    public final synchronized void focusLost(FocusEvent focusevent) {
    	if (Client.keyboardHandler != null)
    		Client.currentKeyCode = -1;
    }
    
    public final synchronized void keyPressed(KeyEvent keyevent) {
		if (Client.keyboardHandler != null) {
			Client.keyboardIdleTime = 0;
			int keyCode = keyevent.getKeyCode();
			if (keyCode >= 0 && keyCode < KeyboardHandler.keyCodes.length) {
				keyCode = KeyboardHandler.keyCodes[keyCode];
				if ((keyCode & 0x80) != 0)
					keyCode = -1;
			} else
				keyCode = -1;
			int keyChar;
			if (keyCode == 85 || keyCode == 80 || keyCode == 84 || keyCode == 0 || keyCode == 101)
				keyChar = -1;
			else
				keyChar = Client.getKeyChar(keyevent);
			if (Client.currentKeyCode >= 0 && keyCode >= 0) {
				Client.keyCodeCache[Client.currentKeyCode] = keyCode;
				Client.currentKeyCode = Client.currentKeyCode + 1 & 0x7f;
				if (Client.currentKeyCode == Client.anInt290)
					Client.currentKeyCode = -1;
			}
			if (keyCode >= 0 || keyChar >= 0) {
				int i_1_ = Client.anInt1361 + 1 & 0x7f;
				if (i_1_ != Client.keyCacheLen) {
					Client.pressedKeyCodeCache[Client.anInt1361] = keyCode;
					Client.pressedKeyCharCache[Client.anInt1361] = keyChar;
					Client.anInt1361 = i_1_;
				}
			}
		}
		keyevent.consume();
    }
    
    public final synchronized void keyReleased(KeyEvent keyevent) {
		if (Client.keyboardHandler != null) {
			Client.keyboardIdleTime = 0;
			int i = keyevent.getKeyCode();
			if (i < 0 || i >= KeyboardHandler.keyCodes.length)
				i = -1;
		    else
		    	i = KeyboardHandler.keyCodes[i] & ~0x80;
			if (Client.currentKeyCode >= 0 && i >= 0) {
				Client.keyCodeCache[Client.currentKeyCode] = i ^ 0xffffffff;
				Client.currentKeyCode = Client.currentKeyCode + 1 & 0x7f;
				if (Client.anInt290 == Client.currentKeyCode)
					Client.currentKeyCode = -1;
			}
		}
		keyevent.consume();
    }
    
    public final void focusGained(FocusEvent focusevent) {
	/* empty */
    }
}
