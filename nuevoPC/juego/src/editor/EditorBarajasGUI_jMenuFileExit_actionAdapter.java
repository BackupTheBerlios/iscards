package editor;

import java.awt.event.*;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: mouseAdapter del menu "Salir" del editor de barajas</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Miguel Cayeiro
 * @version 1.0
 */

class EditorBarajasGUI_jMenuFileExit_actionAdapter implements ActionListener {
  private EditorBarajasGUI adaptee;

  EditorBarajasGUI_jMenuFileExit_actionAdapter(EditorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuFileExit_actionPerformed(e);
  }
}