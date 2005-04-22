package frameIntro;

import java.awt.event.*;

/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: actionAdapter del botón Juego en Red</p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Rui Miguel
 *@version    1.0
 */

class FrameIntroGUI_botonJuegoRed_actionAdapter implements ActionListener {
	FrameIntroGUI adaptee;


	/**
	 *  Constructor for the FrameIntroGUI_botonJuegoRed_actionAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	FrameIntroGUI_botonJuegoRed_actionAdapter(FrameIntroGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void actionPerformed(ActionEvent e) {
		adaptee.botonJuegoRed_actionPerformed(e);
	}
}
