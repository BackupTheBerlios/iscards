package genesis.configuracion;

import java.io.Serializable;

/**
 * <p> Agrupa los elementos que forman parte de cada enlace en la barra superior
 * de la herramienta de administración: </p>
 * 
 * <p>Ej: <tt>Génesis > Foro > Mensajes</tt></p>
 *  
 * Cada uno de estos elementos es una instancia de la clase OpcionEnlace,
 * que contiene el nombre de la opción y la URL a la que redirige.
 *
 *
 *@author    Manuel Montenegro.
 */
public class OpcionEnlace implements Serializable {

	private String opcion;
	private String enlace;


	/**
	 *  Constructora de la clase. Inicializa los campos a la cadena vacía
	 */
	public OpcionEnlace() {
		opcion = "";
		enlace = "";
	}


	/**
	 *  Constructora de la clase. Inicializa los atributos
	 *
	 *@param  opcion  Identificador de la opción
	 *@param  enlace  URL de destino
	 */
	public OpcionEnlace(String opcion, String enlace) {
		this.opcion = opcion;
		this.enlace = enlace;
	}


	/**
	 *  Obtiene el nombre identificador de la opción
	 *
	 *@return    Nombre de la opción
	 */
	public String getOpcion() {
		return opcion;
	}


	/**
	 *  Obtiene la URL a la que redirige el enlace.
	 *
	 *@return    URL destino
	 */
	public String getEnlace() {
		return enlace;
	}


	/**
	 *  Obtiene una representación en cadena del OpcionEnlace
	 *
	 *@return    String de la forma: <tt><i>id. opcion</i> -> <i>enlace</i></tt>
	 */
	public String toString() {
		return opcion + " -> " + enlace;
	}

}
