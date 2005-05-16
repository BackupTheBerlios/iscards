package genesis.configuracion.tipos;

/**
 *  Description of the Interface
 *
 *@author    Chris Seguin
 */
public interface TipoOpcion {

	/**
	 *  Method getDescripcion
	 *
	 *@return
	 */
	public String getDescripcion();


	/**
	 *  Method esValido
	 *
	 *@param  valor
	 *@return
	 */
	public boolean esValido(String valor);


	/**
	 *@param  valor  Description of Parameter
	 *@return        Description of the Returned Value
	 */
	public Object fromString(String valor);
}
