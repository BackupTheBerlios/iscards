package genesis.configuracion;

import java.io.Serializable;

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
public class InformeRespuesta implements Serializable {
	private String nombre;
	private boolean error;
	private String descripcionTipo;


	/**
	 *  Constructor for the InformeRespuesta object
	 *
	 *@param  nombre           Description of Parameter
	 *@param  error            Description of Parameter
	 *@param  descripcionTipo  Description of Parameter
	 */
	public InformeRespuesta(String nombre, boolean error, String descripcionTipo) {
		this.nombre = nombre;
		this.error = error;
		this.descripcionTipo = descripcionTipo;
	}


	/**
	 *  Gets the Nombre attribute of the InformeRespuesta object
	 *
	 *@return    The Nombre value
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 *  Gets the Error attribute of the InformeRespuesta object
	 *
	 *@return    The Error value
	 */
	public boolean getError() {
		return error;
	}


	/**
	 *  Gets the DescripcionTipo attribute of the InformeRespuesta object
	 *
	 *@return    The DescripcionTipo value
	 */
	public String getDescripcionTipo() {
		return descripcionTipo;
	}

}
