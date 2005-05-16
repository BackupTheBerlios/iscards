package genesis.configuracion.tipos;

import genesis.configuracion.tipos.TipoOpcion;

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
public class TipoString implements TipoOpcion {

	/**
	 *  Method getDescripcion
	 *
	 *@return
	 */
	public String getDescripcion() {
		return "Puede introducirse cualquier valor";
	}


	/**
	 *  Method esValido
	 *
	 *@param  valor
	 *@return
	 */
	public boolean esValido(String valor) {
		return true;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  valor  Description of Parameter
	 *@return        Description of the Returned Value
	 */
	public Object fromString(String valor) {
		return valor;
	}
}
