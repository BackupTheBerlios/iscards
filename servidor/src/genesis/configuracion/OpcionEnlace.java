package genesis.configuracion;

import java.io.Serializable;

public class OpcionEnlace implements Serializable {

	private String opcion;
	private String enlace;
	
	public OpcionEnlace() {
		opcion = ""; enlace = "";
	}
	
	public OpcionEnlace(String opcion, String enlace) {
		this.opcion = opcion;
		this.enlace = enlace;
	}	
	
	
	public String getOpcion() {
		return opcion;
	}
	
	public String getEnlace() {
		return enlace;
	}
	
	public String toString() {
		return opcion + " -> " + enlace;
	}

}