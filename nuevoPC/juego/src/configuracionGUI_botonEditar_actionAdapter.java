//package configuracion;

import java.awt.event.*;

/**
 * <p>T�tulo: GENESIS</p>
 * <p>Descripci�n: actionAdapter del bot�n Editar baraja del frame de Configuraci�n de la partida</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Rui Miguel Alonso
 * @version 1.0
 */

class configuracionGUI_botonEditar_actionAdapter implements ActionListener {
  configuracionGUI adaptee;

  configuracionGUI_botonEditar_actionAdapter(configuracionGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botonEditar_actionPerformed(e);
  }
}
