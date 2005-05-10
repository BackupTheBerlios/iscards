package genesis.configuracion;

import java.util.*;
import genesis.configuracion.tipos.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

public class GestorOpciones {
	private Opcion raiz;
	private HashMap tipos;
	private static GestorOpciones elGestor = null;
	
	private GestorOpciones() {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse("C:\\WebGenesis\\webapps\\genesis\\WEB-INF\\opciones.xml");
			CreaArbol ca = new CreaArbol(doc);
			raiz = ca.getRaiz();
		} catch (Exception e) {
			e.printStackTrace();
			raiz = new PruebaArbol().getRaiz();
		}
		
		
		
		tipos = new HashMap();
		tipos.put("Integer", new TipoInteger());
		tipos.put("String", new TipoString());
	}
	
	public TipoOpcion getTipo(String tipo) {
		Object tipoOpcion = tipos.get(tipo);
		if (tipoOpcion == null) {
			return null;
		} else {
			return (TipoOpcion) tipoOpcion;
		}
	}
	
	public Opcion getRaiz() {
		return raiz;
	}
	
	private Opcion _getOpcionPorNombre(Opcion raiz, String camino) {
		int index = camino.indexOf('/');
		String subcadena;
		
		System.out.println("Busco: " + camino);
		
		if (index == -1) {
			subcadena = camino;	
		} else {
			subcadena = camino.substring(0, index);
		}
		
		System.out.println(index);
		// Buscamos la opción que queremos
		Iterator it = ((OpcionCompuesta)raiz).getOpciones().iterator();
		
		Opcion seleccionada = null;
		
		while (it.hasNext()) {
			Opcion actual = (Opcion)it.next();
			System.out.println("Recorriendo: " + actual.getNombre());
			if (actual.getNombre().equals(subcadena))
				seleccionada = actual;
		}
		
		
		if (index == -1) {
			return seleccionada;
		} else if (seleccionada != null){
			
			return this._getOpcionPorNombre(seleccionada, 
				camino.substring(index + 1, camino.length()));
		} else {
			return null;
		}
	}
	
	public Opcion getOpcionPorNombre(String camino) {
		if (camino.indexOf('/') == 0) {
			camino = camino.substring(1,camino.length());
		}
		if (camino.equals("")) {
			return raiz;
		} else {
			return this._getOpcionPorNombre(raiz, camino);		
		}
	}
	
	public static Collection descomponerOpcion(String path) 
	{
		LinkedList result = new LinkedList();
		
		path = path.trim();
		
		while (!path.equals("") && !path.equals("/")) {
			int indice = path.lastIndexOf('/');
			

			if (indice == -1) {
				result.addFirst(new OpcionEnlace(path,path));
				path = "";
			} else {
				String opcion = path.substring(indice + 1, path.length());
				result.addFirst(new OpcionEnlace(opcion, path));
				path = path.substring(0, indice).trim();
			}
		}
		return result;
	}
	
	
	public static GestorOpciones getGestorOpciones() {
		if (elGestor == null) {
			elGestor = new GestorOpciones();
		}
		
		return elGestor;
	}
	
	public static void main(String[] args) {
		Opcion opc = GestorOpciones.getGestorOpciones().getOpcionPorNombre(
			"opcionCompuesta1/opcionSimple1");
		System.out.println((opc == null) ? "No sé ;)" : opc.getDescripcion());
	}
}
