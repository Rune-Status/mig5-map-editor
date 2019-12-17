package org.mapeditor.client;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheel implements MouseWheelListener {

   public int rotation = 0;

   public int get() {
      int rot = this.rotation;
      this.rotation = 0;
      return rot;
   }

   public void mouseWheelMoved(MouseWheelEvent var1) {
      this.rotation += var1.getWheelRotation();
   }
   
}
