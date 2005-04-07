package configuracion;

import java.awt.event.*;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: mouseAdapter de la lista de barajas cargadas del frame de Configuración de la partida</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Rui Miguel Alonso
 * @version 1.0
 */

class ConfiguracionGUI_listBarajas_mouseAdapter extends MouseAdapter {
  ConfiguracionGUI adaptee;

  ConfiguracionGUI_listBarajas_mouseAdapter(ConfiguracionGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.listBarajas_mouseClicked(e);
  }
}
