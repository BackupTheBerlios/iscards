package frameIntro;

import java.awt.event.*;

/**
 * <p>T�tulo: GENESIS</p>
 * <p>Descripci�n: actionAdapter del bot�n Demo</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Rui Miguel
 * @version 1.0
 */

class FrameIntroGUI_botonDemo_actionAdapter implements ActionListener {
  FrameIntroGUI adaptee;

  FrameIntroGUI_botonDemo_actionAdapter(FrameIntroGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botonDemo_actionPerformed(e);
  }
}
