package genesis.configuracion;


public class OpcionNoEncontradaException extends Exception {
	private String opcion;
	
	public OpcionNoEncontradaException(String opcion) {
		super("Opción no encontrada: " + opcion);
		this.opcion = opcion;
		
	}
	
	public String getOpcion() {
		return opcion;
	}
}