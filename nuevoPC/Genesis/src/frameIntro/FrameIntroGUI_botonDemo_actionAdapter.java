package frameIntro;

import java.awt.event.*;

/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: actionAdapter del botón Demo</p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Rui Miguel
 *@version    1.0
 */

class FrameIntroGUI_botonDemo_actionAdapter implements ActionListener {
	FrameIntroGUI adaptee;


	/**
	 *  Constructor for the FrameIntroGUI_botonDemo_actionAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	FrameIntroGUI_botonDemo_actionAdapter(FrameIntroGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void actionPerformed(ActionEvent e) {
		adaptee.botonDemo_actionPerformed(e);
	}
}
