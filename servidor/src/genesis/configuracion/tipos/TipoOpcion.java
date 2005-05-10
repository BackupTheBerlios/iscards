package genesis.configuracion.tipos;


public interface TipoOpcion {
	
	/**
	 * Method getDescripcion
	 *
	 *
	 * @return
	 *
	 */
	public String getDescripcion();

	/**
	 * Method esValido
	 *
	 *
	 * @param valor
	 *
	 * @return
	 *
	 */
	public boolean esValido(String valor);	
	
	/**
	 *
	 *
	 *
	 */
	public Object fromString(String valor);
}
