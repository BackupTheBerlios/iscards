
import java.awt.event.*;

/**
 * <p>T�tulo: GENESIS</p>
 * <p>Descripci�n: actionAdapter del bot�n mostrar Recibir del movil</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Rui Miguel
 * @version 1.0
 */

class frameIntroGUI_botonRecibir_actionAdapter implements ActionListener {
  frameIntroGUI adaptee;

  frameIntroGUI_botonRecibir_actionAdapter(frameIntroGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botonRecibir_actionPerformed(e);
  }
}
