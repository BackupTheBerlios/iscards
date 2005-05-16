package genesis.configuracion;

/**
 *
 * Contiene los campos de una opci�n simple: Un tipo y un valor asociado. 
 * Este tipo de opciones corresponden a las hojas del �rbol de opciones.
 *
 *@author     David B. Jenkins L�pez
 */

public class OpcionSimple implements Opcion {
	// nombre identificador de la opci�n
	private String nombre;
	// descripci�n de la opci�n
	private String descripcion;
	// valor asociado a la opci�n
	private Object valor;
	// cadena que contiene el tipo de los posibles valores asociados
	// a la opci�n
	private String tipo;

	// Cadenas para indicar los distintos tipos
	public static final String TIPO_INTEGER = "Integer";
	public static final String TIPO_STRING = "String";


	/**
	 *  Constructora de la clase. Inicializa los miembros.
	 *
	 *@param  nombre       nombre identificador de la opci�n
	 *@param  descripcion  cadena explicativa del funcionamiento de la opci�n
	 *@param  valor        valor que tiene la opci�n
	 *@param  tipo         etiqueta que indica los posibles valores que puede
	 *                    tomar el valor de la opci�n.
	 */
	public OpcionSimple(String nombre, String descripcion, Object valor, String tipo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.valor = valor;
		this.tipo = tipo;
	}


	/**
	 *  Establece el valor asociado a la opci�n
	 *
	 *@param  nuevoValor  Nuevo valor de la opci�n
	 */
	public void setValor(Object nuevoValor) {
		valor = nuevoValor;
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
	 *  Devuelve <tt>true</tt> si la opci�n es compuesta (esto es, es un
	 * conjunto de opciones). Devuelve <tt>false</tt> en caso contrario.
	 * <b>En este caso se devolver� siempre false</b>
	 *
	 *@return    un valor indicando si la opci�n que recibe el mensaje es
	 *          compuesta o no
	 */
	public boolean getCompuesta() {
		return false;
	}


	/**
	 *  Obtiene el valor actual asociado a la opci�n
	 *
	 *@return    valor actual de la opci�n
	 */
	public Object getValor() {
		return valor;
	}


	/**
	 *  Obtiene el tipo del valor de la opci�n
	 *
	 *@return    Un String que contiene el nombre del tipo datos de la opci�n.
	 */
	public String getTipo() {
		return tipo;
	}

}
