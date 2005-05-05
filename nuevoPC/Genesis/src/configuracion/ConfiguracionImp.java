package configuracion;

import comunicacion.*;
import coleccion.Coleccion;
import cartas.*;
import usuario.Usuario;
import editor.EditorBarajasImp;
import interfaz.*;
import panelesInfo.*;
import motorJuego.*;
import padrePaneles.*;

import java.util.LinkedList;
import java.awt.event.*;
import java.io.FileInputStream;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Hashtable;


/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
public class ConfiguracionImp extends ConfiguracionGUI {



	/**
	 *  Description of the Field
	 */
	public JFrame padre;

	/**
	 *  dlm con la lista de las barajas disponibles para jugar
	 */
	javax.swing.DefaultListModel dlmBarajasDisponibles;

	private EditorBarajasImp editor;

	/**
	 *  coleccion.car con la colección de todas las cartas del juego
	 */
	private Coleccion coleccion;

	/**
	 *  Mazo con las cartas del jugador durante el juego
	 */
	private CMazo mazoCartasJuego;

	/**
	 *  tabla Hash con las cartas disponibles de la baraja
	 */
	private Hashtable tablaCartasBaraja;

	/**
	 *  usuario registrado en el juego
	 */
	private Usuario usuario;

	/**
	 *  Indice de la baraja seleccionada para jugar
	 */
	private int barajaInd;

	/**
	 *  raza de la carta 0=Angeles, 1=Demonios, 2=Humanos, 3=Inmortales
	 */
	private int raza;

	/**
	 *  version de las cartas de las barajas del usuario
	 */
	private int version;

	private boolean juegoRed;
	
	private Partida partida;


	/**
	 *  Constructora de la clase
	 *
	 *@param  p       Frame padre del frame Configuración
	 *@param  c       Description of Parameter
	 *@param  usu     Description of Parameter
	 *@param  juegoR  true si venimos de pulsar el boton "juego en red" de frameIntro.
	 *                false en otro caso
	 */
	public ConfiguracionImp(JFrame p, Coleccion c, Usuario usu, boolean juegoR) {
		juegoRed = juegoR;
		padre = p;
		coleccion = c;
		usuario = usu;
		tablaCartasBaraja = new Hashtable();
		padre.setEnabled(false);
		dlmBarajasDisponibles = new javax.swing.DefaultListModel();
		//mostramos las barajas disponibles para el usuario
		for (int i = 0; i < usuario.getListaBarajas().size(); i++) {
			int hasta = ((String) usuario.getListaBarajas().get(i)).length() - usuario.getNombreUsuario().length() - 1;
			dlmBarajasDisponibles.addElement(((String) usuario.getListaBarajas().get(i)).substring(0, hasta));
		}
		//ordenamos la lista de las barajas
		Object[] l = ordenaListaBarajas(dlmBarajasDisponibles.toArray());
		dlmBarajasDisponibles.clear();
		int posicion = 0;
		while (posicion < l.length) {
			this.dlmBarajasDisponibles.addElement(l[posicion]);
			posicion++;
		}
		this.listBarajas.setModel(dlmBarajasDisponibles);
		
	}


	/**
	 *  Gets the Padre attribute of the ConfiguracionImp object
	 *
	 *@return    The Padre value
	 */
	public JFrame getPadre() {
		return padre;
	}


	/**
	 *  Función para controlar la baraja seleccionada con la que se jugará
	 *
	 *@param  e
	 */
	void listBarajas_mouseClicked(MouseEvent e) {
		//observamos el indice seleccionado
//    barajaInd=listBarajas.getSelectedIndex();
//    try{
//      if (barajaInd!=-1)
//        String barajaSelec = (String)dlmBarajasDisponibles.elementAt(barajaInd);
//    }
//    catch (Exception error) {
//      //mostramos con un JOptionPane el error producido
//      JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error",
//                                    JOptionPane.ERROR_MESSAGE);
//    }
	}


	/**
	 *  Función actionPerformed del botón Editar barajas
	 *
	 *@param  e
	 */
	void botonEditar_actionPerformed(ActionEvent e) {
		try {
			barajaInd = listBarajas.getSelectedIndex();
			//creamos el tablero del juego según la baraja seleccionada
			if (barajaInd != -1) {
				String barajaSelec = (String) dlmBarajasDisponibles.elementAt(barajaInd) + "_" + usuario.getNombreUsuario();
				//mostramos el frame del Editor de barajas cargando la baraja elegida
				EditorBarajasImp editor = new EditorBarajasImp(this, coleccion, usuario, barajaSelec);
				editor.show();
			}

			else {

				this.inhabilitaPanel();
				this.repaint();
				this.getContentPane().add(new PanelGenerico("../imagenes/panelesInfo/BarajaPrimero.jpg", this), 0);

			}
		}
		catch (Exception error) {
			//mostramos con un JOptionPane el error producido
			/*
			 *  JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error",
			 *  JOptionPane.ERROR_MESSAGE);
			 */
			EligeRazaGUI panelE = new EligeRazaGUI(coleccion, usuario,this);
			panelE.show();

		}
	}


	/**
	 *  Función actionPerformed del botón Aceptar
	 *
	 */
	/**
	 *  Función actionPerformed del botón Aceptar
	 *
	 *@param  e
	 */
	void botonAceptar_actionPerformed(ActionEvent e) {
		if (!juegoRed){
			try {
				barajaInd = listBarajas.getSelectedIndex();
				//creamos el tablero del juego según la baraja seleccionada
				if (barajaInd != -1) {
					String barajaSelec = (String) dlmBarajasDisponibles.elementAt(barajaInd) + "_" + usuario.getNombreUsuario();
	
					//cargamos la raza
					cargarRazaSelec(barajaSelec);
	
					
					//creamos la partida con los mazos de ambos jugadores
					partida = new CPartida(barajaSelec,barajaSelec, coleccion);
	
					//creamos el tablero del juego con el mazo de cartas de la raza
					switch (raza) {
						case 0:
						{
							Interfaz tablero = new Interfaz('A', partida, padre, usuario);
							partida.setInterfaz(tablero);
							this.dispose();
						}
							break;
						case 1:
						{
							Interfaz tablero = new Interfaz('D', partida, padre, usuario);
							partida.setInterfaz(tablero);
	
							this.dispose();
						}
							break;
						case 2:
						{
							Interfaz tablero = new Interfaz('H', partida, padre, usuario);
							partida.setInterfaz(tablero);
	
							this.dispose();
						}
							break;
						case 3:
						{
							Interfaz tablero = new Interfaz('I', partida, padre, usuario);
							partida.setInterfaz(tablero);
	
							this.dispose();
						}
							break;
					}
				}
				else {
					this.inhabilitaPanel();
					this.repaint();
					this.getContentPane().add(new PanelGenerico("../imagenes/panelesInfo/BarajaPrimero.jpg", this), 0);
				}
	
				//se tiene que seleccionar una baraja previamente de la lista de barajas
				//JOptionPane.showMessageDialog(this, "Tienes que elegir una baraja antes!!", "Baraja", JOptionPane.INFORMATION_MESSAGE);
			}
			catch (Exception error) {
				//mostramos con un JOptionPane el error producido
				JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		//si estamos en juego en red, mostramos login
		else {
			Interfaz tablero=null;
			try {
				barajaInd = listBarajas.getSelectedIndex();
				//creamos el tablero del juego según la baraja seleccionada
				if (barajaInd != -1) {
					String barajaSelec = (String) dlmBarajasDisponibles.elementAt(barajaInd) + "_" + usuario.getNombreUsuario();
	
					//cargamos la raza
					cargarRazaSelec(barajaSelec);

					GestorUsuarios gestorUsuarios = new GestorUsuarios();
					Controlador controlador = new Controlador(gestorUsuarios, usuario,this,tablero);
                    this.inhabilitaPanel();
                    this.repaint();
                    this.getContentPane().add(new GUI(controlador, tablero));
                    
					
	
					
					//creamos la partida con los mazos de ambos jugadores
					partida = new CPartidaRed(barajaSelec, coleccion,controlador);
	
					//creamos el tablero del juego con el mazo de cartas de la raza
					switch (raza) {
						case 0:
						{
							tablero = new Interfaz('A', partida, padre, usuario);
							partida.setInterfaz(tablero);
							this.dispose();
							tablero.iniciaJuegoRed(tablero);
						}
							break;
						case 1:
						{
							tablero = new Interfaz('D', partida, padre, usuario);
							partida.setInterfaz(tablero);
							tablero.iniciaJuegoRed(tablero);
							this.dispose();
						}
							break;
						case 2:
						{
							tablero = new Interfaz('H', partida, padre, usuario);
							partida.setInterfaz(tablero);
							tablero.iniciaJuegoRed(tablero);
							this.dispose();
						}
							break;
						case 3:
						{
							tablero = new Interfaz('I', partida, padre, usuario);
							partida.setInterfaz(tablero);
							tablero.iniciaJuegoRed(tablero);
							this.dispose();
						}
							break;
					}
					
				}
				else {
					this.inhabilitaPanel();
					this.repaint();
					this.getContentPane().add(new PanelGenerico("../imagenes/panelesInfo/BarajaPrimero.jpg", this), 0);
				}
	
				//se tiene que seleccionar una baraja previamente de la lista de barajas
				//JOptionPane.showMessageDialog(this, "Tienes que elegir una baraja antes!!", "Baraja", JOptionPane.INFORMATION_MESSAGE);
			}
			catch (Exception error) {
				error.printStackTrace();
				//mostramos con un JOptionPane el error producido
				JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		}
	}


	/**
	 *  Función actionPerformed del botón Cancelar
	 *
	 *@param  e
	 */
	void botonCancelar_actionPerformed(ActionEvent e) {
		padre.setEnabled(true);
		padre.show();

		this.dispose();
	}


	/**
	 *  Description of the Method
	 *
	 *@param  lista  Description of Parameter
	 *@return        Description of the Returned Value
	 */
	private Object[] ordenaListaBarajas(Object[] lista) {
		boolean hayCambio = true;
		while (hayCambio) {
			hayCambio = false;
			int i = 0;
			while (i < lista.length - 1) {
				String nombreUsuario1 = (String) lista[i];
				String nombreUsuario2 = ((String) lista[i + 1]);
				int j = 0;
				while (j < nombreUsuario1.length() && j < nombreUsuario2.length() &&
						nombreUsuario1.charAt(j) == nombreUsuario2.charAt(j)) {
					j++;
				}
				if (j == nombreUsuario2.length()) {
					lista[i] = nombreUsuario2;
					lista[i + 1] = nombreUsuario1;
					hayCambio = true;
				}
				else if (j < nombreUsuario1.length() && nombreUsuario1.charAt(j) > nombreUsuario2.charAt(j)) {
					String codigoNombre = (String) lista[i];
					lista[i] = (String) lista[i + 1];
					lista[i + 1] = codigoNombre;
					hayCambio = true;
				}
				i++;
			}
		}
		return lista;
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
	 *  Función que carga una baraja determinada del usuario
	 *
	 *@param  fichBaraja
	 */
	private void cargarRazaSelec(String fichBaraja) {
		try {
			FileInputStream archivo1 = new FileInputStream("../barajas/" + fichBaraja + ".bar");
			//variable para controlar los bytes que se deben leer
			int numeroDeBytesALeer = archivo1.read();
			//esto no sería necesario ya que el nombre ya lo sabemos, se puede controlar que coincidan por si hay error
			String nombreUsuario = descodificar(leerFrase(numeroDeBytesALeer, archivo1));
			version = archivo1.read();
			//leemos el nombre de la raza
			numeroDeBytesALeer = archivo1.read();
			String razaNom = descodificar(leerFrase(numeroDeBytesALeer, archivo1));
			if (razaNom.equals("Ángeles")) {
				raza = 0;
			}
			else if (razaNom.equals("Demonios")) {
				raza = 1;
			}
			else if (razaNom.equals("Humanos")) {
				raza = 2;
			}
			else if (razaNom.equals("Inmortales")) {
				raza = 3;
			}
			archivo1.close();
		}
		catch (Exception error) {
			//mostramos con un JOptionPane el error producido
			JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error cargar raza",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public Partida getPartida(){
		return partida;
	}
	
	public void setPartida(Partida p){
		partida=p;
	}

//  void this_windowClosing(WindowEvent e) {
	//padre.setEnabled(true);
	//}
}
