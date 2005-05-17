package genesis.configuracion;

import java.io.Serializable;

/**
 *  Contiene los elementos que conforman la p�gina de confirmaci�n que aparece
 * cuando el usuario establecelos valores de una serie de opciones (las que
 * pertenecen a una misma opci�n simple).
 *
 * Un informe va asociado a una determinada opci�n simple y consta de tres partes:
 * <ul>
 *	 <li><b>Nombre:</b> Nombre de la opci�n modificada</li>
 *   <li><b>Error:</b> Booleano que indica si el nuevo valor introducido en la
 *             opci�n es o no correcto.</li>
 *   <li><b>Descripci�n del tipo:</b> En caso de que el valor no se haya introducido
 *            correctamente, aparecer� la descripci�n del tipo de la opci�n para
 *            que se muestre un texto que indique los posibles valores v�lidos</li>
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
	 *@param  nombre           Nombre de la opci�n
	 *@param  error            Si el valor de la opci�n se introdujo correctamente
	 *@param  descripcionTipo  Texto que indica el conjunto de valores que pueden
	 *                          introducirse en la opci�n
	 */
	public InformeRespuesta(String nombre, boolean error, String descripcionTipo) {
		this.nombre = nombre;
		this.error = error;
		this.descripcionTipo = descripcionTipo;
	}


	/**
	 *  Obtiene el Nombre de la opci�n modificada
	 *
	 *@return    Nombre de la opci�n modificada
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 *  Acceso al atributo error de el objeto InformeRespuesta
	 *
	 *@return    <tt>true</tt> si hubo un error en la introducci�n del nuevo valor, 
	 *             <tt>false</tt> en caso contrario
	 */
	public boolean getError() {
		return error;
	}


	/**
	 *  Obtiene la descripci�n del tipo de la opci�n
	 *
	 *@return    String que indica los posibles valores v�lidos que puede tomar
	 *           esta opci�n simple
	 */
	public String getDescripcionTipo() {
		return descripcionTipo;
	}

}
