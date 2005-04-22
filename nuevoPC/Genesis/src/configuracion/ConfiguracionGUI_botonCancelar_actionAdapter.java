package configuracion;

import java.awt.event.*;

/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: actionAdapter del botón Cancelar del frame de Configuración
 *  de la partida</p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Rui Miguel Alonso
 *@version    1.0
 */

class ConfiguracionGUI_botonCancelar_actionAdapter implements ActionListener {
	ConfiguracionGUI adaptee;


	/**
	 *  Constructor for the ConfiguracionGUI_botonCancelar_actionAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	ConfiguracionGUI_botonCancelar_actionAdapter(ConfiguracionGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void actionPerformed(ActionEvent e) {
		adaptee.botonCancelar_actionPerformed(e);
	}
}
