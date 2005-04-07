package frameIntro;

import java.awt.event.*;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: actionAdapter del botón Editar barajas</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Rui Miguel
 * @version 1.0
 */

class FrameIntroGUI_botonEditar_actionAdapter implements ActionListener {
  FrameIntroGUI adaptee;

  FrameIntroGUI_botonEditar_actionAdapter(FrameIntroGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botonEditar_actionPerformed(e);
  }
}
