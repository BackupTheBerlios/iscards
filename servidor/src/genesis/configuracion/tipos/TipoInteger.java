package genesis.configuracion.tipos;

import genesis.configuracion.tipos.TipoOpcion;

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
public class TipoInteger implements TipoOpcion {

	/**
	 *  Method getDescripcion
	 *
	 *@return
	 */
	public String getDescripcion() {
		return "Debe introducirse un valor entero, positivo o negativo";
	}


	/**
	 *  Method esValido
	 *
	 *@param  valor
	 *@return
	 */
	public boolean esValido(String valor) {
		try {
			Integer.parseInt(valor);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}


	/**
	 *  Description of the Method
	 *
	 *@param  valor  Description of Parameter
	 *@return        Description of the Returned Value
	 */
	public Object fromString(String valor) {
		try {
			return new Integer(valor);
		}
		catch (NumberFormatException e) {
			return new Integer(0);
		}
	}

}
