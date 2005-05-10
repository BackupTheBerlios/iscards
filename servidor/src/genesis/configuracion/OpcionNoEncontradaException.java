package genesis.configuracion;


public class OpcionNoEncontradaException extends Exception {
	private String opcion;
	
	public OpcionNoEncontradaException(String opcion) {
		super("Opci�n no encontrada: " + opcion);
		this.opcion = opcion;
		
	}
	
	public String getOpcion() {
		return opcion;
	}
}