//package editor;

import java.awt.event.*;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: mouseAdapter del menu "Cargar" del editor de barajas</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Miguel Cayeiro
 * @version 1.0
 */

class editorBarajasGUI_jMenuFileCargar_actionAdapter implements ActionListener {
  private editorBarajasGUI adaptee;

  editorBarajasGUI_jMenuFileCargar_actionAdapter(editorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuFileCargar_actionPerformed(e);
  }
}
