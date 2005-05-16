package genesis.configuracion;

/**
 *  Excepci�n que se genera cuando se intenta acceder a una opci�n
 * que no existe en el �rbol de opciones.
 *
 *@author    Manuel Montenegro
 */
public class OpcionNoEncontradaException extends Exception {
	private String opcion;


	/**
	 *  Constructor de la clase. Recibe como par�metro el nombre de la  opci�n que 
	 * se intent� encontrar, pero no se encontr�.
	 *
	 *@param  opcion  Opci�n no encontrada
	 */
	public OpcionNoEncontradaException(String opcion) {
		super("Opci�n no encontrada: " + opcion);
		this.opcion = opcion;

	}


	/**
	 *  Devuelve el nombre de la opci�n que se intent� buscar
	 *
	 *@return    Nombre de la opci�n no encontrada
	 */
	public String getOpcion() {
		return opcion;
	}
}
