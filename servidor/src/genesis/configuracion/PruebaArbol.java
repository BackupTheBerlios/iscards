package genesis.configuracion;

import genesis.configuracion.*;
import java.util.Vector;

/**
 * Clase que genera un árbol de muestra. Utilizada con fines de depuración, y
 * en caso en el que el archivo XML no se encuentre.
 *
 *@author     David B. Jenkins López
 *@version    1.0
 */

public class PruebaArbol {

	private Opcion raiz;


	/**
	 *  Constructora de la clase. Genera el árbol, cuya raíz se puede obtener
	 * a través del método getRaiz()
	 */
	public PruebaArbol() {
		// genero un arbolito como debería ser
		Vector opciones = new Vector();
		Vector opciones2 = new Vector();

		opciones2.add(new OpcionSimple("opcionSimple1", "soy la opcion simple izquierda", new Integer(1), "Integer"));
		opciones2.add(new OpcionSimple("opcionSimple2", "soy la opcion simple derecha", new Integer(2), "String"));

		opciones.add(new OpcionCompuesta(opciones2, "opcionCompuesta1", "soy la opcion compuesta de la raiz"));
		opciones.add(new OpcionSimple("opcionSimple3", "soy la opcion simple de la raiz", new Integer(3), "Integer"));

		raiz = new OpcionCompuesta(opciones, "raiz", "soy la raiz");
	}


	/**
	 *  Obtiene la raíz del árbol de prueba. A partir de la raíz pueden obtenerse
	 * las demás opciones
	 *
	 *@return    Valor de la raíz
	 */
	public Opcion getRaiz() {
		return raiz;
	}

}
