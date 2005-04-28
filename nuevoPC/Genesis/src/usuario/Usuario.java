package usuario;

import coleccion.*;

import java.util.LinkedList;
import java.util.Hashtable;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import javax.swing.*;

/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: Clase para representar a un usuario en el juego</p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Rui Miguel Alonso
 *@version    1.0
 */

public class Usuario {

	/**
	 *  Nombre del usuario
	 */
	private String nombreUsuario;

	/**
	 *  Lista de las barajas disponibles para el usuario
	 */
	private LinkedList listaBarajas;

	/**
	 */
	private int versionSobres;

	/**
	 *  Coleccion de todas las cartas
	 */
	private Coleccion coleccion;

	/**
	 *  tabla Hash con todas las cartas libres disponibles por el usuario
	 *  (aquellas que no están seleccionadas para personalizar una baraja)
	 */
	private Hashtable tablaCartasDisponibles;


	/**
	 *  Constructora de la clase
	 *
	 *@param  nombre  nombre del usuario
	 *@param  col     colección de cartas del juego
	 *@param  borra   esta variable nos dice si el usuario se va a borrar, por lo
	 *      que deberiamos quitar todos sus archivos
	 */
	public Usuario(String nombre, Coleccion col, boolean borra) {
		//si se quiere borrar al usuario
		if (borra == true) {
			borraUsuario(nombre);
		}
		else {
			try {
				nombreUsuario = nombre;
				coleccion = col;
				//creamos los archivos de barajas (.conf) y de cartas disponibles (.rep) del usuario
				File archivoNuevoConf = new File("../documentos/" + this.nombreUsuario + ".conf");
				File archivoNuevoRep = new File("../documentos/" + this.nombreUsuario + ".rep");
				archivoNuevoConf.createNewFile();
				archivoNuevoRep.createNewFile();
				listaBarajas = new LinkedList();
				tablaCartasDisponibles = new Hashtable();
				//cargamos los archivos de cartas y barajas del usuario
				cargarNombresBarajas();
				cargarColeccionUsuario();
			}
			catch (Exception error) {
				//mostramos con un JOptionPane el error producido
				JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error Usuario", JOptionPane.ERROR_MESSAGE);
			}
		}
	}


	/**
	 *  Sets the VersionSobres attribute of the Usuario object
	 *
	 *@param  version  The new VersionSobres value
	 */
	public void setVersionSobres(int version) {
		this.versionSobres = version;
	}


	/**
	 *  Sets the TablaCartasDisponibles attribute of the Usuario object
	 *
	 *@param  tabla  The new TablaCartasDisponibles value
	 */
	public void setTablaCartasDisponibles(Hashtable tabla) {
		this.tablaCartasDisponibles = tabla;
	}


	/**
	 *  Función que devuelve el nombre del usuario
	 *
	 *@return    nombre
	 */
	public String getNombreUsuario() {
		return this.nombreUsuario;
	}


	/**
	 *  Función que devuelve la lista de las barajas del usuario
	 *
	 *@return    lista de barajas del usuario
	 */
	public LinkedList getListaBarajas() {
		return this.listaBarajas;
	}


	/**
	 *  Función que devuelve la versión del sobre
	 *
	 *@return    versión del sobre
	 */
	public int getVersionSobres() {
		return this.versionSobres;
	}


	/**
	 *  Función que devuelve la tabla de las cartas disponibles
	 *
	 *@return    tabla cartas disponibles
	 */
	public Hashtable getTablaCartasDisponibles() {
		return this.tablaCartasDisponibles;
	}


	/**
	 *  Función que crea uan colección por defecto para un nuevo usuario
	 */
	public void creaColeccionUsuarioDefecto() {
		//añadir la coleccion
		try {
			//abrimos el archivo "porDefecto.rep" y se lo pasamos al usuario
			FileInputStream archivoDefecto = new FileInputStream("../documentos/porDefecto.rep");
			//continuamos una colección antigua luego hay que borrar lo que tenemos ahora mismo
			int numeroDeBytesALeer = archivoDefecto.read();
			//variable para controlar los bytes que se deben leer
			//creamos el ".rep" inicial del jugador nuevo
			FileOutputStream archivoNuevo = new FileOutputStream("../documentos/" + this.nombreUsuario + ".rep");
			//guardamos el nombre del usuario
			archivoNuevo.write(this.nombreUsuario.length());
			archivoNuevo.write(codificar(this.nombreUsuario.getBytes()).getBytes());
			//leemos el archivo por defecto y lo guardamos en el nuevo archivo
			while (numeroDeBytesALeer >= 0) {
				// i==-1 es fin de fichero
				archivoNuevo.write(numeroDeBytesALeer);
				numeroDeBytesALeer = archivoDefecto.read();
			}
			archivoDefecto.close();
			archivoNuevo.close();
		}
		catch (Exception error) {
			//mostramos con un JOptionPane el error producido
			JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error cargando las cartas",
					JOptionPane.ERROR_MESSAGE);
		}
	}


	/**
	 *  Función que elimina los arhchivos de un usuario
	 *
	 *@param  nombre  Description of Parameter
	 */
	private void borraUsuario(String nombre) {
		try {
			//si el usuario se quiere borrar se eliminan sus barajas
			FileInputStream archivoConfig = new FileInputStream("../documentos/" + nombre + ".conf");
			int numeroDeBytesALeer = archivoConfig.read();
			//variable para controlar los bytes que se deben leer
			//si el fichero no está vacio lo leemos
			if (numeroDeBytesALeer != -1) {
				leerFrase(numeroDeBytesALeer, archivoConfig);
				//cargamos la version de las cartas
				int version = archivoConfig.read();
				numeroDeBytesALeer = archivoConfig.read();
				while (numeroDeBytesALeer >= 0) {
					// i==-1 es fin de fichero
					String nomBaraja = descodificar(leerFrase(numeroDeBytesALeer, archivoConfig));
					File archivoBorradoBar = new File("../barajas/" + nomBaraja + ".bar");
					archivoBorradoBar.delete();
					numeroDeBytesALeer = archivoConfig.read();
				}
			}
			archivoConfig.close();
			//si el usuario se quiere borrar se eliminan sus ficheros
			File archivoBorradoConf = new File("../documentos/" + nombre + ".conf");
			File archivoBorradoRep = new File("../documentos/" + nombre + ".rep");
			archivoBorradoConf.delete();
			archivoBorradoRep.delete();
		}
		catch (Exception error) {
			//mostramos con un JOptionPane el error producido
			JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error borrando", JOptionPane.ERROR_MESSAGE);
		}
	}


	/**
	 *  Función que lee de un archivo una cantidad de carácteres pedida
	 *
	 *@param  longitud              cantidad de carácteres que hay que leer
	 *@param  archivo               archivo del que hay que leer
	 *@return                       String con los datos leidos
	 *@exception  Exception         Description of Exception
	 *@throws  java.lang.Exception
	 */
	private byte[] leerFrase(int longitud, FileInputStream archivo) throws Exception {
		int bytes;
		int i = 0;
		byte[] frase = new byte[longitud];
		while (i < longitud) {
			bytes = archivo.read();
			if (bytes == -1) {
				//i==-1 es fin de fichero
				throw new Exception("La base de datos esta incompleta");
			}
			frase[i] = (byte) bytes;
			i++;
		}
		return frase;
	}


	/**
	 *  Función que descodifica los bytes leidos
	 *
	 *@param  fraseBytes  bytes leidos
	 *@return             String frase descodificada
	 */
	private String descodificar(byte[] fraseBytes) {
		String frase = "";
		int i = 0;
		while (i < fraseBytes.length) {
			char a;
			if (fraseBytes[i] < 0) {
				//carácter espepecial (ñ, á, é, í, ó, ú, ...)
				a = (char) (256 + fraseBytes[i] + 2);
			}
			else {
				a = (char) (fraseBytes[i] + 2);
			}
			frase = frase + a;
			i++;
		}
		return frase;
	}


	/**
	 *  Función que codifica los bytes que se quieren grabar
	 *
	 *@param  fraseBytes  bytes que se desean grabar
	 *@return             String frase codificada
	 */
	private String codificar(byte[] fraseBytes) {
		String frase = "";
		int i = 0;
		while (i < fraseBytes.length) {
			char a;
			if (fraseBytes[i] < 0) {
				//carácter espepecial (ñ, á, é, í, ó, ú, ...)
				a = (char) (256 + fraseBytes[i] - 2);
			}
			else {
				a = (char) (fraseBytes[i] - 2);
			}
			frase = frase + a;
			i++;
		}
		return frase;
	}


	/**
	 *  Función que carga todas las barajas personalizadas disponibles para el
	 *  usuario del archivo "usuario.conf"
	 */
	private void cargarNombresBarajas() {
		try {
			FileInputStream archivoConfig = new FileInputStream("../documentos/" + this.nombreUsuario + ".conf");
			int numeroDeBytesALeer = archivoConfig.read();
			//variable para controlar los bytes que se deben leer
			//si el fichero no está vacio lo leemos
			if (numeroDeBytesALeer != -1) {
				String nombre = descodificar(leerFrase(numeroDeBytesALeer, archivoConfig));
				//cargamos la version de las cartas
				int version = archivoConfig.read();
				numeroDeBytesALeer = archivoConfig.read();
				while (numeroDeBytesALeer >= 0) {
					// i==-1 es fin de fichero
					String nomBaraja = descodificar(leerFrase(numeroDeBytesALeer, archivoConfig));
					//si la baraja no está repetida la guardamos
					if (!this.listaBarajas.contains(nomBaraja)) {
						this.listaBarajas.add(nomBaraja);
					}
					numeroDeBytesALeer = archivoConfig.read();
				}
			}
			archivoConfig.close();
		}
		//mostramos con un JOptionPane el error producido
		catch (Exception error) {
			JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error cargando nombres barajas",
					JOptionPane.ERROR_MESSAGE);
		}
	}


	/**
	 *  Función que carga todas las cartas de la colección disponibles para el
	 *  usuario del archivo "usuario.rep"
	 */
	private void cargarColeccionUsuario() {
		//añadir la coleccion
		try {
			FileInputStream archivoRepetidas = new FileInputStream("../documentos/" + this.nombreUsuario + ".rep");
			//continuamos una colección antigua luego hay que borrar lo que tenemos ahora mismo
			int numeroDeBytesALeer = archivoRepetidas.read();
			//variable para controlar los bytes que se deben leer
			//si el fichero está vacio no lo leemos
			if (numeroDeBytesALeer != -1) {
				String nombreUsuario = descodificar(leerFrase(numeroDeBytesALeer, archivoRepetidas));
				numeroDeBytesALeer = archivoRepetidas.read();
				this.tablaCartasDisponibles.clear();
				while (numeroDeBytesALeer >= 0) {
					// i==-1 es fin de fichero
					//los numeros de letras que hay que leer de descripMov también está codificado
					String cantidad = descodificar(leerFrase(numeroDeBytesALeer, archivoRepetidas));
					numeroDeBytesALeer = archivoRepetidas.read();
					String codigoCarta = descodificar(leerFrase(numeroDeBytesALeer, archivoRepetidas));
					String nombre = coleccion.pedirCarta(codigoCarta).getNombre();
					this.tablaCartasDisponibles.put(nombre, new Integer(cantidad));
					numeroDeBytesALeer = archivoRepetidas.read();
				}
			}
			archivoRepetidas.close();
		}
		catch (Exception error) {
			//mostramos con un JOptionPane el error producido
			JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error cargando las cartas",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Coleccion getColeccion(){
		return coleccion;
	}
}
