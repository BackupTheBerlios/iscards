//package editor;

import java.awt.event.*;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: mouseAdapter del combo "Nombre Carta" del editor de barajas</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Miguel Cayeiro
 * @version 1.0
 */

class editorBarajasGUI_jComboNombreCarta_actionAdapter implements java.awt.event.ActionListener {
  editorBarajasGUI adaptee;

  editorBarajasGUI_jComboNombreCarta_actionAdapter(editorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jComboNombreCarta_actionPerformed(e);
  }
}
