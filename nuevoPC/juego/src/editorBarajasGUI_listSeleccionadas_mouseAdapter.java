//package editor;

import java.awt.event.*;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: mouseAdapter de la lista listSeleccionadas del editor de barajas</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Miguel Cayeiro
 * @version 1.0
 */

class editorBarajasGUI_listSeleccionadas_mouseAdapter extends MouseAdapter {
  editorBarajasGUI adaptee;

  editorBarajasGUI_listSeleccionadas_mouseAdapter(editorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.listSeleccionadas_mouseClicked(e);
  }
}
