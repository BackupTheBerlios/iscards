//package editor;

import java.awt.event.*;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: mouseAdapter de la lista listDisponibles del editor de barajas</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Miguel Cayeiro
 * @version 1.0
 */

class editorBarajasGUI_listDisponibles_mouseAdapter extends MouseAdapter {
  editorBarajasGUI adaptee;

  editorBarajasGUI_listDisponibles_mouseAdapter(editorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.listDisponibles_mouseClicked(e);
  }
}
