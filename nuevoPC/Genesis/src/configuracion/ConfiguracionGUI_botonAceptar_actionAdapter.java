package configuracion;

import java.awt.event.*;

/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: actionAdapter del botón Aceptar del frame de Configuración de
 *  la partida</p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Rui Miguel Alonso
 *@version    1.0
 */

class ConfiguracionGUI_botonAceptar_actionAdapter implements ActionListener {
	ConfiguracionGUI adaptee;


	/**
	 *  Constructor for the ConfiguracionGUI_botonAceptar_actionAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	ConfiguracionGUI_botonAceptar_actionAdapter(ConfiguracionGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void actionPerformed(ActionEvent e) {
		adaptee.botonAceptar_actionPerformed(e);
	}
}
