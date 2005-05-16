package genesis.configuracion;

/**
 *  Excepción que se genera cuando se intenta acceder a una opción
 * que no existe en el árbol de opciones.
 *
 *@author    Manuel Montenegro
 */
public class OpcionNoEncontradaException extends Exception {
	private String opcion;


	/**
	 *  Constructor de la clase. Recibe como parámetro el nombre de la  opción que 
	 * se intentó encontrar, pero no se encontró.
	 *
	 *@param  opcion  Opción no encontrada
	 */
	public OpcionNoEncontradaException(String opcion) {
		super("Opción no encontrada: " + opcion);
		this.opcion = opcion;

	}


	/**
	 *  Devuelve el nombre de la opción que se intentó buscar
	 *
	 *@return    Nombre de la opción no encontrada
	 */
	public String getOpcion() {
		return opcion;
	}
}
