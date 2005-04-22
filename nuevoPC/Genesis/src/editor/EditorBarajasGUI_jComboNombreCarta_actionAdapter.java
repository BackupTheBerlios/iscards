package editor;

import java.awt.event.*;

/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: mouseAdapter del combo "Nombre Carta" del editor de barajas
 *  </p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Miguel Cayeiro
 *@version    1.0
 */

class EditorBarajasGUI_jComboNombreCarta_actionAdapter implements java.awt.event.ActionListener {
	EditorBarajasGUI adaptee;


	/**
	 *  Constructor for the EditorBarajasGUI_jComboNombreCarta_actionAdapter
	 *  object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	EditorBarajasGUI_jComboNombreCarta_actionAdapter(EditorBarajasGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void actionPerformed(ActionEvent e) {
		adaptee.jComboNombreCarta_actionPerformed(e);
	}
}
