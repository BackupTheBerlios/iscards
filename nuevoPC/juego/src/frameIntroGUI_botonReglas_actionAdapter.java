
import java.awt.event.*;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: actionAdapter del botón mostrar Reglas</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Rui Miguel
 * @version 1.0
 */

class frameIntroGUI_botonReglas_actionAdapter implements ActionListener {
  frameIntroGUI adaptee;

  frameIntroGUI_botonReglas_actionAdapter(frameIntroGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botonReglas_actionPerformed(e);
  }
}
