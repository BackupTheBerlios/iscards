package genesis.configuracion.arbol;

import genesis.configuracion.*;
import java.util.*;

public class NodoVista {
	private OpcionCompuesta opcion;
	private Collection hijos;
	private String enlace;
	private boolean abierto;
	private int nivel;
	
	public NodoVista(OpcionCompuesta opcion, String enlace, int nivel) {
		hijos = new ArrayList();
		
		this.opcion = opcion;
		this.enlace = enlace;
		this.nivel = nivel;
		
		Iterator it = opcion.getOpciones().iterator();
		while (it.hasNext()) {
			Opcion actual = (Opcion) it.next();
			if (actual.getCompuesta()) {
				NodoVista nuevoHijo = new NodoVista(
						(OpcionCompuesta)actual,
						enlace + "/" + actual.getNombre(),
						nivel + 1
					);
				hijos.add(nuevoHijo);
			}
		}
		abierto = false;
		
	}
	
	public void abrir() {
		abierto = true;
	}
	
	public void cerrar() {
		abierto = false;
	}
	
	public String getNombre() {
		return opcion.getNombre();
	}
	
	public NodoVista buscarHijo(String nombre) {
		Iterator it = hijos.iterator();
		while (it.hasNext()) {
			NodoVista actual = (NodoVista) it.next();
			if (actual.getNombre().equals(nombre)) {
				return actual;
			}
		}
		return null;
	}
	
	public String toHTML() {
		StringBuffer result = new StringBuffer("");
		for (int i = 0; i < nivel; i++) {
			result.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		}
		if (hijos.size() != 0) {
			if (abierto) {
				result.append("<a href=\"CerrarNodo?path=" +
					enlace + "\">");
				result.append("<img src=\"/genesis/admin/images/menos.jpg\" border=\"0\"/>");
				result.append("</a>&nbsp;&nbsp;");
				
			} else {
				result.append("<a href=\"AbrirNodo?path=" +
					enlace + "\">");
				result.append("<img src=\"/genesis/admin/images/mas.jpg\" border=\"0\"/>");
				result.append("</a>&nbsp;&nbsp;");
				
			}
		} else {
				result.append("<img src=\"/genesis/admin/images/blanco.jpg\" border=\"0\"/>&nbsp;&nbsp;");
		}
		result.append("<a href=\"MostrarOpcionCompuesta?path=" +
			enlace + "\" target=\"principal\">" + opcion.getNombre()+ "</a><br/>");
		if (abierto) {
			Iterator it = hijos.iterator();
			while (it.hasNext()) {
				NodoVista actual = (NodoVista)it.next();
				result.append(actual.toHTML());
			}
		}
		return result.toString();
	}
	
}
