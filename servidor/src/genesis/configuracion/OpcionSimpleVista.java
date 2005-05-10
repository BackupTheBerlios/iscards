package genesis.configuracion;

import java.io.Serializable;
import genesis.configuracion.tipos.TipoOpcion;

public class OpcionSimpleVista implements Serializable {
	private String descripcion;
	private String nombre;
	private String tipo;
	private String valor;
	
	public OpcionSimpleVista(String descripcion, String nombre, String tipo,
		String valor) {
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.tipo = tipo;
		this.valor = valor;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public String getDescripcionTipo() {
		TipoOpcion tipo = GestorOpciones.getGestorOpciones().getTipo(this.tipo);
		if (tipo != null) {
			return tipo.getDescripcion();
		} else {
			return "<No se conoce nada sobre este tipo>";
		}
	}
	
	public String getValor() {
		return valor;
	}
	
	public String toString() {
		return "Opcion Simple: " + nombre + " (" + tipo + ")";
	}
}