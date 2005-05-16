package genesis.configuracion;

import java.io.Serializable;
import genesis.configuracion.tipos.TipoOpcion;

/**
 *  Agrupa los campos necesarios para la visualización de una opción simple:
 * Estos son: 
 * <ul>
 *	 <li>Su descripción</li>
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
	 *@param  descripcion  Descripción de la opción
	 *@param  nombre       Nombre identificador de la opción
	 *@param  tipo         Tipo de la opción
	 *@param  valor        Valor de la opción
	 */
	public OpcionSimpleVista(String descripcion, String nombre, String tipo,
			String valor) {
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.tipo = tipo;
		this.valor = valor;
	}


	/**
	 *  Obtiene el atributo 'descripción'
	 *
	 *@return    Descripción de la opción
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 *  Acceso al atributo 'nombre'
	 *
	 *@return    Nombre identificador de la opción
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 *  Acceso al atributo 'tipo' de la opción
	 *
	 *@return    String identificando al tipo de la opción
	 */
	public String getTipo() {
		return tipo;
	}


	/**
	 *  Obtiene una descripción de los valores que puede aceptar el tipo 
	 * de la opción.
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
	 *  Acceso al valor de la opción
	 *
	 *@return    Representación en un String del valor de la opción
	 */
	public String getValor() {
		return valor;
	}


	/**
	 * 
	 *  Representación de la vista en forma de cadena. Es de la forma:
	 *<tt>Opción Simple: <i>Nombre de la opción</i>(<i>Tipo</i>)</tt>
	 *
	 *
	 *@return    Representación en forma de cadena.
	 */
	public String toString() {
		return "Opcion Simple: " + nombre + " (" + tipo + ")";
	}
}
