package editor;

import java.awt.event.*;

/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: mouseAdapter de la lista listSeleccionadas del editor de
 *  barajas</p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Miguel Cayeiro
 *@version    1.0
 */

class EditorBarajasGUI_listSeleccionadas_mouseAdapter extends MouseAdapter {
	EditorBarajasGUI adaptee;


	/**
	 *  Constructor for the EditorBarajasGUI_listSeleccionadas_mouseAdapter
	 *  object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	EditorBarajasGUI_listSeleccionadas_mouseAdapter(EditorBarajasGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseClicked(MouseEvent e) {
		adaptee.listSeleccionadas_mouseClicked(e);
	}
}
