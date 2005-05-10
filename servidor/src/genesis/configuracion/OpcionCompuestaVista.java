package genesis.configuracion;

import java.io.Serializable;

public class OpcionCompuestaVista implements Serializable {
	private String descripcion;
	private String nombre;
	private String enlace;
	
	public OpcionCompuestaVista(String descripcion, String nombre, String enlace) {
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.enlace = enlace;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getEnlace() {
		return enlace;
	}
	
	public String toString() {
		return "Opcion compuesta: " + nombre;
	}
}