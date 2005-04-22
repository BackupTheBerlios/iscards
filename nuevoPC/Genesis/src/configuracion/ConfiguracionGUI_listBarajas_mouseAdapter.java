package configuracion;

import java.awt.event.*;

/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: mouseAdapter de la lista de barajas cargadas del frame de
 *  Configuración de la partida</p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Rui Miguel Alonso
 *@version    1.0
 */

class ConfiguracionGUI_listBarajas_mouseAdapter extends MouseAdapter {
	ConfiguracionGUI adaptee;


	/**
	 *  Constructor for the ConfiguracionGUI_listBarajas_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	ConfiguracionGUI_listBarajas_mouseAdapter(ConfiguracionGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseClicked(MouseEvent e) {
		adaptee.listBarajas_mouseClicked(e);
	}
}
