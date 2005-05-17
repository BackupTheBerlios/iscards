package genesis.configuracion;

import java.io.Serializable;

/**
 *  Contiene los elementos que conforman la página de confirmación que aparece
 * cuando el usuario establecelos valores de una serie de opciones (las que
 * pertenecen a una misma opción simple).
 *
 * Un informe va asociado a una determinada opción simple y consta de tres partes:
 * <ul>
 *	 <li><b>Nombre:</b> Nombre de la opción modificada</li>
 *   <li><b>Error:</b> Booleano que indica si el nuevo valor introducido en la
 *             opción es o no correcto.</li>
 *   <li><b>Descripción del tipo:</b> En caso de que el valor no se haya introducido
 *            correctamente, aparecerá la descripción del tipo de la opción para
 *            que se muestre un texto que indique los posibles valores válidos</li>
 * </ul>
 * 
 *
 *@author    Manuel Montenegro
 */
public class InformeRespuesta implements Serializable {
	private String nombre;
	private boolean error;
	private String descripcionTipo;


	/**
	 *  Constructora de la clase
	 *
	 *@param  nombre           Nombre de la opción
	 *@param  error            Si el valor de la opción se introdujo correctamente
	 *@param  descripcionTipo  Texto que indica el conjunto de valores que pueden
	 *                          introducirse en la opción
	 */
	public InformeRespuesta(String nombre, boolean error, String descripcionTipo) {
		this.nombre = nombre;
		this.error = error;
		this.descripcionTipo = descripcionTipo;
	}


	/**
	 *  Obtiene el Nombre de la opción modificada
	 *
	 *@return    Nombre de la opción modificada
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 *  Acceso al atributo error de el objeto InformeRespuesta
	 *
	 *@return    <tt>true</tt> si hubo un error en la introducción del nuevo valor, 
	 *             <tt>false</tt> en caso contrario
	 */
	public boolean getError() {
		return error;
	}


	/**
	 *  Obtiene la descripción del tipo de la opción
	 *
	 *@return    String que indica los posibles valores válidos que puede tomar
	 *           esta opción simple
	 */
	public String getDescripcionTipo() {
		return descripcionTipo;
	}

}
