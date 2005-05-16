package genesis.configuracion;

import java.util.*;
/**
 * Esta clase representa a una opción que a su vez contiene una o más opciones,
 * que a su vez pueden ser simples o compuestas (y por tanto implementan a la
 * interfaz Opcion).
 *
 * Los objetos de esta clase representan los nodos internos del árbol de 
 * opciones
 *
 *@author     David B. Jenkins López
 */

public class OpcionCompuesta implements Opcion {
	// conjunto de opciones que contiene esta opción compuesta
	private Collection /* <Opcion> */opciones;
	// nombre identificador de la opción
	private String nombre;
	// descripción de esta opción
	private String descripcion;


	/**
	 *  Constructor de la clase. Inicializa los atributos
	 *
	 *@param  opciones     Conjunto de opciones hijas
	 *@param  nombre       Nombre identificador de la opción
	 *@param  descripcion  Descripción de la opción
	 */
	public OpcionCompuesta(Collection opciones, String nombre, String descripcion) {
		this.opciones = opciones;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}


	/**
	 *  Obtiene el nombre identificador de la opción
	 *
	 *@return    Una cadena que contiene el nombre identificador
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 *  Obtiene una descripción de la opción y sus posibles valores.
	 *
	 *@return    Un String conteniendo la descripción
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 *  Obtiene un Collection con todas las opciones hijas de la opción que
	 * recibe el mensaje.
	 *
	 *@return    Conjunto de opciones hijas
	 */
	public Collection getOpciones() {
		return opciones;
	}


	/**
	 *  Devuelve <tt>true</tt> si la opción es compuesta (esto es, es un
	 * conjunto de opciones). Devuelve <tt>false</tt> en caso contrario.
	 * <b>En este caso siempre devolverá true</b>
	 * 
	 *
	 *@return    un valor indicando si la opción que recibe el mensaje es
	 *          compuesta o no
	 */
	 public boolean getCompuesta() {
		return true;
	}
}
