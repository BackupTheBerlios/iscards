package configuracion;

import java.awt.event.*;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: actionAdapter del botón Cancelar del frame de Configuración de la partida</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Rui Miguel Alonso
 * @version 1.0
 */

class ConfiguracionGUI_botonCancelar_actionAdapter implements ActionListener {
  ConfiguracionGUI adaptee;

  ConfiguracionGUI_botonCancelar_actionAdapter(ConfiguracionGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botonCancelar_actionPerformed(e);
  }
}
