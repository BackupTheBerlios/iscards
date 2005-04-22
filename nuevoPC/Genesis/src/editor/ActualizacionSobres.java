package editor;

import coleccion.Coleccion;
import usuario.Usuario;

import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;
import javax.swing.*;


/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
public class ActualizacionSobres {

	private Usuario usuario;
	private Coleccion coleccion;
	private JFrame padre;
	private Hashtable tablaCartasDisponibles;



	/**
	 *  Constructor for the ActualizacionSobres object
	 *
	 *@param  padre      Description of Parameter
	 *@param  coleccion  Description of Parameter
	 *@param  usuario    Description of Parameter
	 */
	public ActualizacionSobres(JFrame padre, Coleccion coleccion, Usuario usuario) {
		this.usuario = usuario;
		this.padre = padre;
		this.padre.setEnabled(false);
		this.coleccion = coleccion;

		this.tablaCartasDisponibles = usuario.getTablaCartasDisponibles();
		añadirSobre();

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
	 *  Description of the Method
	 */
	private void añadirSobre() {
		//añadir las cartas del sobre
		//usamos un filtro para guardar las barajas
		JFileChooser fichero = new JFileChooser("../barajas");
		FiltroURL filtro = new FiltroURL("_" + usuario.getNombreUsuario() + ".paq");
		fichero.setFileFilter(filtro);
		int valor = fichero.showOpenDialog(this.padre);
		if (valor == JFileChooser.APPROVE_OPTION) {
			String nombreFichero = (fichero.getSelectedFile()).getName();
			if (nombreFichero.endsWith(".paq")) {
				nombreFichero = nombreFichero.substring(0, nombreFichero.length() - 4);
			}
			if (!nombreFichero.endsWith("_" + usuario.getNombreUsuario())) {
				nombreFichero = nombreFichero + "_" + usuario.getNombreUsuario();
			}
			if (!nombreFichero.equals("_" + usuario.getNombreUsuario())) {
				try {

					FileInputStream archivo1 = new FileInputStream("../barajas/" + nombreFichero + ".paq");

					//variable para controlar los bytes que se deben leer
					int numeroDeBytesALeer = archivo1.read();
					//cargamos el nombre de usuario y version de las cartas
					String nombreUsuario = descodificar(leerFrase(numeroDeBytesALeer, archivo1));
					numeroDeBytesALeer = archivo1.read();
					String numeroVersion = descodificar(leerFrase(numeroDeBytesALeer, archivo1));
					int version = (new Integer(numeroVersion)).intValue();
					//*******

					///leer las cartas del sobre y añadilas a la tabla




					//*******
					usuario.setTablaCartasDisponibles(tablaCartasDisponibles);
					usuario.setVersionSobres(version);
					archivo1.close();
				}
				catch (Exception error) {
					//mostramos con un JOptionPane el error producido
					JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error Baraja",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			File archivoABorrar = new File("../barajas/" + nombreFichero + ".paq");
			archivoABorrar.delete();
		}
		this.padre.setEnabled(true);
	}
}
