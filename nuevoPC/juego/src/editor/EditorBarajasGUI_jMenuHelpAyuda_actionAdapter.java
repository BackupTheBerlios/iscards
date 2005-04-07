package editor;

import java.awt.event.*;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: mouseAdapter del menu "Ayuda" del editor de barajas</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Miguel Cayeiro
 * @version 1.0
 */

class EditorBarajasGUI_jMenuHelpAyuda_actionAdapter implements ActionListener {
  private EditorBarajasGUI adaptee;

  EditorBarajasGUI_jMenuHelpAyuda_actionAdapter(EditorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuHelpAyuda_actionPerformed(e);
  }
}
