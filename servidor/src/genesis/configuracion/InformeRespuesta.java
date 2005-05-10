package genesis.configuracion;

import java.io.Serializable;

public class InformeRespuesta implements Serializable {
	private String nombre;
	private boolean error;
	private String descripcionTipo;
	
	public InformeRespuesta(String nombre, boolean error, String descripcionTipo) {
		this.nombre = nombre;
		this.error = error;
		this.descripcionTipo = descripcionTipo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public boolean getError() {
		return error;
	}
	
	public String getDescripcionTipo() {
		return descripcionTipo;
	}
	
}