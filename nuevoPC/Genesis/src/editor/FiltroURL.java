package editor;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: Filtro para cargar y guardar archivos</p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@version    1.0
 */

/**
 *  Clase que usamos para crear un filtro de texto a la hora de cargar un
 *  archivo URL para la ayuda
 *
 *@author    Chris Seguin
 *@see       javax.swing.filechooser.FileFilter
 *@see       java.io.File
 */
class FiltroURL extends FileFilter {

	private String filtro;


	/**
	 *  Constructor for the FiltroURL object
	 */
	public FiltroURL() {
		super();
	}


	/**
	 *  Constructora de la clase, que llama a la constructora de la clase de la
	 *  que hereda
	 *
	 *@param  filtro  Description of Parameter
	 */
	public FiltroURL(String filtro) {
		this.filtro = filtro;
	}


	/**
	 *  Función que nos dice una breve descripción del tipo de los ficheros que
	 *  filtramos
	 *
	 *@return    String con la descripción
	 */
	public String getDescription() {
		return "barajas";
	}


	/**
	 *  Redefinición de la función accept, para poder filtrar los archivos de
	 *  tipo "*.bar"
	 *
	 *@param  f  nombre del fichero que queremos cargar
	 *@return    un booleano que indica si el fichero f cumple nuestro filtro y
	 *      por tanto podemos cargarlo
	 *@see       javax.swing.filechooser.FileFilter#accept(File f)
	 */
	public boolean accept(File f) {
		String nombre = f.getName();
		try {
			//restringimos los nombre a "*.bar"
			return nombre.endsWith(filtro);
		}
		//controlamos con excepciones que no haya problemas al cargar el fichero devolviendo false
		catch (StringIndexOutOfBoundsException e) {
			return false;
		}
	}
}
