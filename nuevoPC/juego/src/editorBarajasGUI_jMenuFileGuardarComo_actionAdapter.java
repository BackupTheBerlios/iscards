//package editor;

import java.awt.event.*;

/**
 * <p>T�tulo: GENESIS</p>
 * <p>Descripci�n: mouseAdapter del menu "Guardar como" del editor de barajas</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Miguel Cayeiro
 * @version 1.0
 */

class editorBarajasGUI_jMenuFileGuardarComo_actionAdapter implements ActionListener {
  private editorBarajasGUI adaptee;

  editorBarajasGUI_jMenuFileGuardarComo_actionAdapter(editorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuFileGuardarComo_actionPerformed(e);
  }
}
