package genesis.configuracion;

import java.io.Serializable;
import genesis.configuracion.tipos.TipoOpcion;

/**
 *  Agrupa los campos necesarios para la visualizaci�n de una opci�n simple:
 * Estos son: 
 * <ul>
 *	 <li>Su descripci�n</li>
 *   <li>Su nombre</li>
 *   <li>Su tipo</li>
 *   <li>Su valor</li>
 * </ul>
 *
 *@author    Manuel Montenegro
 */
public class OpcionSimpleVista implements Serializable {
	private String descripcion;
	private String nombre;
	private String tipo;
	private String valor;


	/**
	 *  Constructor de la clase. Inicializa los cuatro atributos
	 *
	 *@param  descripcion  Descripci�n de la opci�n
	 *@param  nombre       Nombre identificador de la opci�n
	 *@param  tipo         Tipo de la opci�n
	 *@param  valor        Valor de la opci�n
	 */
	public OpcionSimpleVista(String descripcion, String nombre, String tipo,
			String valor) {
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.tipo = tipo;
		this.valor = valor;
	}


	/**
	 *  Obtiene el atributo 'descripci�n'
	 *
	 *@return    Descripci�n de la opci�n
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 *  Acceso al atributo 'nombre'
	 *
	 *@return    Nombre identificador de la opci�n
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 *  Acceso al atributo 'tipo' de la opci�n
	 *
	 *@return    String identificando al tipo de la opci�n
	 */
	public String getTipo() {
		return tipo;
	}


	/**
	 *  Obtiene una descripci�n de los valores que puede aceptar el tipo 
	 * de la opci�n.
	 *
	 *@return    The DescripcionTipo value
	 */
	public String getDescripcionTipo() {
		TipoOpcion tipo = GestorOpciones.getGestorOpciones().getTipo(this.tipo);
		if (tipo != null) {
			return tipo.getDescripcion();
		}
		else {
			return "<No se conoce nada sobre este tipo>";
		}
	}


	/**
	 *  Acceso al valor de la opci�n
	 *
	 *@return    Representaci�n en un String del valor de la opci�n
	 */
	public String getValor() {
		return valor;
	}


	/**
	 * 
	 *  Representaci�n de la vista en forma de cadena. Es de la forma:
	 *<tt>Opci�n Simple: <i>Nombre de la opci�n</i>(<i>Tipo</i>)</tt>
	 *
	 *
	 *@return    Representaci�n en forma de cadena.
	 */
	public String toString() {
		return "Opcion Simple: " + nombre + " (" + tipo + ")";
	}
}
