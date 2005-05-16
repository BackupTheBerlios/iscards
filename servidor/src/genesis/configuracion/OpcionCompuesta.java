package genesis.configuracion;

import java.util.*;
/**
 * Esta clase representa a una opci�n que a su vez contiene una o m�s opciones,
 * que a su vez pueden ser simples o compuestas (y por tanto implementan a la
 * interfaz Opcion).
 *
 * Los objetos de esta clase representan los nodos internos del �rbol de 
 * opciones
 *
 *@author     David B. Jenkins L�pez
 */

public class OpcionCompuesta implements Opcion {
	// conjunto de opciones que contiene esta opci�n compuesta
	private Collection /* <Opcion> */opciones;
	// nombre identificador de la opci�n
	private String nombre;
	// descripci�n de esta opci�n
	private String descripcion;


	/**
	 *  Constructor de la clase. Inicializa los atributos
	 *
	 *@param  opciones     Conjunto de opciones hijas
	 *@param  nombre       Nombre identificador de la opci�n
	 *@param  descripcion  Descripci�n de la opci�n
	 */
	public OpcionCompuesta(Collection opciones, String nombre, String descripcion) {
		this.opciones = opciones;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}


	/**
	 *  Obtiene el nombre identificador de la opci�n
	 *
	 *@return    Una cadena que contiene el nombre identificador
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 *  Obtiene una descripci�n de la opci�n y sus posibles valores.
	 *
	 *@return    Un String conteniendo la descripci�n
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 *  Obtiene un Collection con todas las opciones hijas de la opci�n que
	 * recibe el mensaje.
	 *
	 *@return    Conjunto de opciones hijas
	 */
	public Collection getOpciones() {
		return opciones;
	}


	/**
	 *  Devuelve <tt>true</tt> si la opci�n es compuesta (esto es, es un
	 * conjunto de opciones). Devuelve <tt>false</tt> en caso contrario.
	 * <b>En este caso siempre devolver� true</b>
	 * 
	 *
	 *@return    un valor indicando si la opci�n que recibe el mensaje es
	 *          compuesta o no
	 */
	 public boolean getCompuesta() {
		return true;
	}
}
