package frameIntro;

import java.awt.event.*;

/**
 *  <p>
 *
 *  T�tulo: GENESIS</p> <p>
 *
 *  Descripci�n: actionAdapter del bot�n mostrar Ayuda</p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Rui Miguel
 *@version    1.0
 */

class FrameIntroGUI_botonAyuda_actionAdapter implements ActionListener {
	FrameIntroGUI adaptee;


	/**
	 *  Constructor for the FrameIntroGUI_botonAyuda_actionAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	FrameIntroGUI_botonAyuda_actionAdapter(FrameIntroGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void actionPerformed(ActionEvent e) {
		adaptee.botonAyuda_actionPerformed(e);
	}
}
