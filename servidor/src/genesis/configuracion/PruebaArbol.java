package genesis.configuracion;

import genesis.configuracion.*;
import java.util.Vector;

/**
 * Clase que genera un �rbol de muestra. Utilizada con fines de depuraci�n, y
 * en caso en el que el archivo XML no se encuentre.
 *
 *@author     David B. Jenkins L�pez
 *@version    1.0
 */

public class PruebaArbol {

	private Opcion raiz;


	/**
	 *  Constructora de la clase. Genera el �rbol, cuya ra�z se puede obtener
	 * a trav�s del m�todo getRaiz()
	 */
	public PruebaArbol() {
		// genero un arbolito como deber�a ser
		Vector opciones = new Vector();
		Vector opciones2 = new Vector();

		opciones2.add(new OpcionSimple("opcionSimple1", "soy la opcion simple izquierda", new Integer(1), "Integer"));
		opciones2.add(new OpcionSimple("opcionSimple2", "soy la opcion simple derecha", new Integer(2), "String"));

		opciones.add(new OpcionCompuesta(opciones2, "opcionCompuesta1", "soy la opcion compuesta de la raiz"));
		opciones.add(new OpcionSimple("opcionSimple3", "soy la opcion simple de la raiz", new Integer(3), "Integer"));

		raiz = new OpcionCompuesta(opciones, "raiz", "soy la raiz");
	}


	/**
	 *  Obtiene la ra�z del �rbol de prueba. A partir de la ra�z pueden obtenerse
	 * las dem�s opciones
	 *
	 *@return    Valor de la ra�z
	 */
	public Opcion getRaiz() {
		return raiz;
	}

}
