
import java.awt.event.*;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: actionAdapter del botón 1 Jugador</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Rui Miguel
 * @version 1.0
 */

class frameIntroGUI_boton1Jugador_actionAdapter implements ActionListener {
  frameIntroGUI adaptee;

  frameIntroGUI_boton1Jugador_actionAdapter(frameIntroGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.boton1Jugador_actionPerformed(e);
  }
}
