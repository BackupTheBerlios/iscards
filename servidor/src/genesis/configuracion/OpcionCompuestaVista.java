package genesis.configuracion;

import java.io.Serializable;

/**
 *  Agrupa los elementos necesarios para mostrar una opción compuesta
 * en un JSP.
 *
 *@author    Manuel Montenegro
 */
public class OpcionCompuestaVista implements Serializable {
	private String descripcion;
	private String nombre;
	private String enlace;


	/**
	 *  Constructor de la clase. Inicializa los atributos de la opción
	 *
	 *@param  descripcion  Descripción de la opción
	 *@param  nombre       Identificador de la opción compuesta
	 *@param  enlace       Página en la que acabaremos si hacemos click en
	 *                     esta opción
	 */
	public OpcionCompuestaVista(String descripcion, String nombre, String enlace) {
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.enlace = enlace;
	}


	/**
	 *  Obtiene la descripción de la opción
	 *
	 *@return    Descripción de la opción
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 *  Obtiene el nombre de la opción
	 *
	 *@return    Identificador de la opción 
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 *  Obtiene el hipervínculo de la opción a la página en la que acabamos
	 * si pulsamos en el enlace
	 *
	 *@return    La URL destino
	 */
	public String getEnlace() {
		return enlace;
	}


	/**
	 *  Obtiene una representación en cadena de esta vista. Es de la forma:
	 *<tt>Opción compuesta: <i>Nombre de la opción</i></tt>
	 *
	 *@return    Representación en un String de el objeto que recibe el mensaje
	 */
	public String toString() {
		return "Opcion compuesta: " + nombre;
	}
}
