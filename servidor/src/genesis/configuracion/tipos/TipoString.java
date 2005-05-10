package genesis.configuracion.tipos;

import genesis.configuracion.tipos.TipoOpcion;

public class TipoString implements TipoOpcion {
	
	/**
	 * Method getDescripcion
	 *
	 *
	 * @return
	 *
	 */
	public String getDescripcion() {
		return "Puede introducirse cualquier valor";
	}

	/**
	 * Method esValido
	 *
	 *
	 * @param valor
	 *
	 * @return
	 *
	 */
	public boolean esValido(String valor) {
		return true;
	}	
	
	public Object fromString(String valor) {
		return valor;
	}
}
