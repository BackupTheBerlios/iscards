package editor;

import java.awt.event.*;

/**
 * <p>T�tulo: GENESIS</p>
 * <p>Descripci�n: mouseAdapter del combo "Nombre Carta" del editor de barajas</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Miguel Cayeiro
 * @version 1.0
 */

class EditorBarajasGUI_jComboNombreCarta_actionAdapter implements java.awt.event.ActionListener {
  EditorBarajasGUI adaptee;

  EditorBarajasGUI_jComboNombreCarta_actionAdapter(EditorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jComboNombreCarta_actionPerformed(e);
  }
}
