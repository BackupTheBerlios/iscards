package genesis.configuracion;

/**
 *
 * Contiene los campos de una opción simple: Un tipo y un valor asociado. 
 * Este tipo de opciones corresponden a las hojas del árbol de opciones.
 *
 *@author     David B. Jenkins López
 */

public class OpcionSimple implements Opcion {
	// nombre identificador de la opción
	private String nombre;
	// descripción de la opción
	private String descripcion;
	// valor asociado a la opción
	private Object valor;
	// cadena que contiene el tipo de los posibles valores asociados
	// a la opción
	private String tipo;

	// Cadenas para indicar los distintos tipos
	public static final String TIPO_INTEGER = "Integer";
	public static final String TIPO_STRING = "String";


	/**
	 *  Constructora de la clase. Inicializa los miembros.
	 *
	 *@param  nombre       nombre identificador de la opción
	 *@param  descripcion  cadena explicativa del funcionamiento de la opción
	 *@param  valor        valor que tiene la opción
	 *@param  tipo         etiqueta que indica los posibles valores que puede
	 *                    tomar el valor de la opción.
	 */
	public OpcionSimple(String nombre, String descripcion, Object valor, String tipo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.valor = valor;
		this.tipo = tipo;
	}


	/**
	 *  Establece el valor asociado a la opción
	 *
	 *@param  nuevoValor  Nuevo valor de la opción
	 */
	public void setValor(Object nuevoValor) {
		valor = nuevoValor;
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
	 *  Devuelve <tt>true</tt> si la opción es compuesta (esto es, es un
	 * conjunto de opciones). Devuelve <tt>false</tt> en caso contrario.
	 * <b>En este caso se devolverá siempre false</b>
	 *
	 *@return    un valor indicando si la opción que recibe el mensaje es
	 *          compuesta o no
	 */
	public boolean getCompuesta() {
		return false;
	}


	/**
	 *  Obtiene el valor actual asociado a la opción
	 *
	 *@return    valor actual de la opción
	 */
	public Object getValor() {
		return valor;
	}


	/**
	 *  Obtiene el tipo del valor de la opción
	 *
	 *@return    Un String que contiene el nombre del tipo datos de la opción.
	 */
	public String getTipo() {
		return tipo;
	}

}
