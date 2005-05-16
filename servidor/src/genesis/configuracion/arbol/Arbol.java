package genesis.configuracion.arbol;

import genesis.configuracion.arbol.*;
import genesis.configuracion.*;

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
public class Arbol {
	private NodoVista raiz;


	/**
	 *  Constructor for the Arbol object
	 *
	 *@param  oc  Description of Parameter
	 */
	public Arbol(OpcionCompuesta oc) {
		raiz = new NodoVista(oc, "", 0);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  path  Description of Parameter
	 *@return       Description of the Returned Value
	 */
	public NodoVista buscarNodo(String path) {
		NodoVista actual = raiz;

		if (path.equals("") || path.equals("/")) {
			return raiz;
		}

		// Quitamos los espacios de los bordes y el '/' inicial
		path = path.trim();
		if (path.indexOf('/') == 0) {
			path = path.substring(1, path.length());
		}

		int indice;

		while ((indice = path.indexOf('/')) != -1) {
			String nombre = path.substring(0, indice);
			actual = actual.buscarHijo(nombre);
			if (actual == null) {
				return null;
			}
			path = path.substring(indice + 1, path.length());
		}

		actual = actual.buscarHijo(path);

		return actual;
	}


	/**
	 *  Description of the Method
	 *
	 *@return    Description of the Returned Value
	 */
	public String toHTML() {
		return raiz.toHTML();
	}
	/*
	 *  public static void main(String[] args) {
	 *  GestorOpciones go = GestorOpciones.getGestorOpciones();
	 *  Arbol a = new Arbol((OpcionCompuesta)go.getRaiz());
	 *  System.out.println(a.buscarNodo("/opcionCompuesta1").getNombre());
	 *  }
	 */
}
