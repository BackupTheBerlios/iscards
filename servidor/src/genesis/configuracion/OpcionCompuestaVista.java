package genesis.configuracion;

import java.io.Serializable;

/**
 *  Agrupa los elementos necesarios para mostrar una opci�n compuesta
 * en un JSP.
 *
 *@author    Manuel Montenegro
 */
public class OpcionCompuestaVista implements Serializable {
	private String descripcion;
	private String nombre;
	private String enlace;


	/**
	 *  Constructor de la clase. Inicializa los atributos de la opci�n
	 *
	 *@param  descripcion  Descripci�n de la opci�n
	 *@param  nombre       Identificador de la opci�n compuesta
	 *@param  enlace       P�gina en la que acabaremos si hacemos click en
	 *                     esta opci�n
	 */
	public OpcionCompuestaVista(String descripcion, String nombre, String enlace) {
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.enlace = enlace;
	}


	/**
	 *  Obtiene la descripci�n de la opci�n
	 *
	 *@return    Descripci�n de la opci�n
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 *  Obtiene el nombre de la opci�n
	 *
	 *@return    Identificador de la opci�n 
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 *  Obtiene el hiperv�nculo de la opci�n a la p�gina en la que acabamos
	 * si pulsamos en el enlace
	 *
	 *@return    La URL destino
	 */
	public String getEnlace() {
		return enlace;
	}


	/**
	 *  Obtiene una representaci�n en cadena de esta vista. Es de la forma:
	 *<tt>Opci�n compuesta: <i>Nombre de la opci�n</i></tt>
	 *
	 *@return    Representaci�n en un String de el objeto que recibe el mensaje
	 */
	public String toString() {
		return "Opcion compuesta: " + nombre;
	}
}
