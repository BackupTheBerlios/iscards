package editor;

import cartas.CACarta;
import coleccion.Coleccion;
import usuario.Usuario;
import panelesInfo.*;

import java.util.LinkedList;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.Enumeration;
import javax.swing.*;

/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: Implementación del interfaz gráfico del Editor de barajas del
 *  jugador</p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Miguel Cayeiro
 *@version    1.0
 */
public class EditorBarajasImp extends EditorBarajasGUI {

	private javax.swing.DefaultListModel dlmCartasSeleccionadas = null;
	private javax.swing.DefaultListModel dlmCartasDisponibles = null;

	private JFrame padre;

	private Coleccion coleccion;

	/**
	 *  tabla Hash con todas las cartas seleccionadas por el usuario para
	 *  personalizar una baraja .BAR
	 */
	private Hashtable tablaCartasSeleccionadas;

	/**
	 *  tabla Hash con todas las cartas disponibles por el usuario para
	 *  personalizar una baraja .BAR
	 */
	private Hashtable tablaCartasDisponibles;

	/**
	 *  tabla Hash con todas las cartas disponibles por el usuario para
	 *  personalizar una baraja .BAR
	 */
	private LinkedList listaCartasComboBox;

	/**
	 *  usuario para jugar y acceder a sus barajas únicamente
	 */
	private Usuario usuario;

	/**
	 *  raza seleccionada para crear su baraja
	 */
	private String raza;

	/**
	 *  nombre del archivo del cual se ha cargado la baraja
	 */
	private String nombreArchivoBaraja;

	/**
	 *  numero de cartas seleccionadas en la baraja del jugador
	 */
	private int numCartas;


	/**
	 *  Constructora de la clase
	 *
	 *@param  padre      Description of Parameter
	 *@param  coleccion  Description of Parameter
	 *@param  usuario    Description of Parameter
	 *@param  numRaza    Description of Parameter
	 */
	public EditorBarajasImp(JFrame padre, Coleccion coleccion, Usuario usuario, int numRaza) {
		this.usuario = usuario;
		if (numRaza == 0) {
			raza = "Ángeles";
		}
		else if (numRaza == 1) {
			raza = "Demonios";
		}
		else if (numRaza == 2) {
			raza = "Humanos";
		}
		else {
			raza = "Inmortales";
		}
		this.textoRaza.setText(raza);
		this.padre = padre;
		this.padre.setEnabled(false);
		this.coleccion = coleccion;

		this.numCartas = 0;
		this.textoNumeroCartas.setText(new Integer(numCartas).toString());

		//asignamos los ListModel a las listas de cartas del editor
		this.dlmCartasSeleccionadas = new javax.swing.DefaultListModel();
		this.dlmCartasDisponibles = new javax.swing.DefaultListModel();

		//mostramos la lista de disponibles y seleccionadas
		this.listaDisponibles.setModel(dlmCartasDisponibles);
		this.listaSeleccionadas.setModel(dlmCartasSeleccionadas);

		//creamos las tablas de las cartas contenidas en las listas
		this.tablaCartasSeleccionadas = new Hashtable();
		this.tablaCartasDisponibles = (Hashtable) usuario.getTablaCartasDisponibles().clone();

		//cargamos las cartas del usuario
		cargarColeccionUsuario();
		this.nombreArchivoBaraja = "_" + usuario.getNombreUsuario();
		this.textoBarajaCargada.setText(nombreArchivoBaraja.substring(0, nombreArchivoBaraja.length() - usuario.getNombreUsuario().length() - 1));
		// this.botGuardar.setEnabled(false);
	}


	/**
	 *  Constructora de la clase
	 *
	 *@param  padre      Description of Parameter
	 *@param  coleccion  Description of Parameter
	 *@param  usuario    Description of Parameter
	 *@param  baraja     Description of Parameter
	 */
	public EditorBarajasImp(JFrame padre, Coleccion coleccion, Usuario usuario,
			String baraja) {
		this.usuario = usuario;
		this.padre = padre;
		this.padre.setEnabled(false);
		this.coleccion = coleccion;

		this.numCartas = 0;
		this.textoNumeroCartas.setText(new Integer(numCartas).toString());

		//asignamos los ListModel a las listas de cartas del editor
		this.dlmCartasSeleccionadas = new javax.swing.DefaultListModel();
		this.dlmCartasDisponibles = new javax.swing.DefaultListModel();

		//mostramos la lista de disponibles y seleccionadas
		this.listaDisponibles.setModel(dlmCartasDisponibles);
		this.listaSeleccionadas.setModel(dlmCartasSeleccionadas);

		//creamos las tablas de las cartas contenidas en las listas
		this.tablaCartasSeleccionadas = new Hashtable();
		this.tablaCartasDisponibles = (Hashtable) usuario.getTablaCartasDisponibles().clone();

		//cargamos las cartas del usuario
		cargarColeccionUsuario();
		this.nombreArchivoBaraja = "_" + usuario.getNombreUsuario();
		this.textoBarajaCargada.setText(nombreArchivoBaraja.substring(0, nombreArchivoBaraja.length() - usuario.getNombreUsuario().length() - 1));
		this.botGuardar.setEnabled(false);

		cargarBarajaUsuario(baraja);
	}


	/**
	 *  Función para controlar el evento de cambiar el comboBox con los nombres
	 *  de las cartas y mostrar dicha carta, para facilitar la elección
	 *
	 *@param  e
	 */
	void jComboNombreCarta_actionPerformed(ActionEvent e) {
		String codigoNombre = (String) this.comboNombreCarta.getSelectedItem();
		String codigo = codigoNombre.substring(0, codigoNombre.indexOf("-") - 1);
		String nombre = codigoNombre.substring(codigoNombre.indexOf("-") + 2);
		try {
			/*
			 *  muestra la imagen de la carta si esta existe
			 *  la dirección sera:
			 *  ../Cartas/"Raza"/"Tipo"/"nombre de la carta"
			 */
			CACarta carta = coleccion.pedirCarta(codigo);
			String direccionCarta = "../Cartas/" + carta.getIdRaza() + "/" + carta.getIdTipo();
			direccionCarta = direccionCarta + "s/" + carta.getNombre() + ".jpg";
			ImageIcon icono = new ImageIcon(direccionCarta);
			if (icono.getIconHeight() > 0 && icono.getIconWidth() > 0) {
				//se muestra la imagen
				this.labelImagen.setIcon(icono);
			}
			else {
				//la imagen no esta disponible, se muestra la parte de atras de la carta (la raza)
				direccionCarta = "../Cartas/" + carta.getIdRaza() + "/" + carta.getIdRaza() +
						"_no_disponible.jpg";
				this.labelImagen.setIcon(new ImageIcon(direccionCarta));
			}
		}
		catch (Exception error) {
			//la carta no esta disponible, se muestra la parte de atras de la carta (la raza seleccionada)
			String direccionCarta = "../Cartas/" + raza + "/" + raza + ".jpg";
			this.labelImagen.setIcon(new ImageIcon(direccionCarta));
			//mostramos con un JOptionPane el error producido
			JOptionPane.showMessageDialog(this, error.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}


	/**
	 *  Función para controlar el evento del mouse en la lista de cartas
	 *  disponibles
	 *
	 *@param  e
	 */
	void listDisponibles_mouseClicked(MouseEvent e) {
		int indice = listaDisponibles.getSelectedIndex();
		try {
			if (indice != -1) {
				String cartaSelecAux = (String) dlmCartasDisponibles.elementAt(indice);
				//hay que leer el nombre de la carta correctamente
				String cartaSelec = cartaSelecAux.substring(cartaSelecAux.indexOf("-") + 2,
						cartaSelecAux.indexOf("(") - 1);
				String codigo = coleccion.pedirCodigo(cartaSelec);
				CACarta carta = coleccion.pedirCarta(codigo);

				int numeroCartasDisp = ((Integer) tablaCartasDisponibles.get(cartaSelec)).intValue();

				if (tablaCartasSeleccionadas.containsKey(cartaSelec)) {
					//controlamos que se respetan los limites de cartas de Nivel 1,2 y 3
					int nivel = carta.getNivel();
					int numeroCartasSel = ((Integer) tablaCartasSeleccionadas.get(cartaSelec)).intValue();

					if (nivel == 1) {
						JOptionPane.showMessageDialog(new JOptionPane(),
								"Solo puedes tener 1 carta de Nivel 1", "Limite sobrepasado",
								JOptionPane.ERROR_MESSAGE);
					}
					if (nivel == 2 && ((Integer) tablaCartasSeleccionadas.get(cartaSelec)).
							intValue() < 3) {
						//actualizamos en la lista de Disponibles la carta del indice
						dlmCartasDisponibles.removeElement(cartaSelecAux);
						numeroCartasDisp = numeroCartasDisp - 1;
						if (numeroCartasDisp != 0) {
							tablaCartasDisponibles.put(cartaSelec, new Integer(numeroCartasDisp));
							dlmCartasDisponibles.addElement(carta.getCodigo() + " - " + cartaSelec +
									" (" + numeroCartasDisp + ")");
						}
						else {
							//si se quedan 0 repeticiones de la carta seleccionada la eliminamos de la tabla
							tablaCartasDisponibles.remove(cartaSelec);
						}
						//se mira en la lista de cartas seleccionadas la carta seleccionada
						dlmCartasSeleccionadas.removeElement(codigo + " - " + cartaSelec +
								" (" + numeroCartasSel + ")");
						numeroCartasSel = numeroCartasSel + 1;
						//añadimos la carta al dlm y tabla de Seleccionadas
						dlmCartasSeleccionadas.addElement(codigo + " - " + cartaSelec +
								" (" + numeroCartasSel + ")");
						tablaCartasSeleccionadas.put(cartaSelec, new Integer(numeroCartasSel));
						numCartas++;
						this.textoNumeroCartas.setText(new Integer(numCartas).toString());
					}
					else if (nivel == 2) {
						JOptionPane.showMessageDialog(new JOptionPane(),
								"Solo puedes tener 3 cartas de Nivel 2", "Limite sobrepasado",
								JOptionPane.ERROR_MESSAGE);
					}
					if (nivel == 3 && ((Integer) tablaCartasSeleccionadas.get(cartaSelec)).
							intValue() < 5) {
						//actualizamos en la lista de Disponibles la carta del indice
						dlmCartasDisponibles.removeElement(cartaSelecAux);
						numeroCartasDisp = numeroCartasDisp - 1;
						if (numeroCartasDisp != 0) {
							tablaCartasDisponibles.put(cartaSelec, new Integer(numeroCartasDisp));
							dlmCartasDisponibles.addElement(carta.getCodigo() + " - " + cartaSelec +
									" (" + numeroCartasDisp + ")");
						}
						else {
							//si se quedan 0 repeticiones de la carta seleccionada la eliminamos de la tabla
							tablaCartasDisponibles.remove(cartaSelec);
						}
						//se mira en la lista de cartas seleccionadas la carta seleccionada
						dlmCartasSeleccionadas.removeElement(codigo + " - " + cartaSelec +
								" (" + numeroCartasSel + ")");
						numeroCartasSel = numeroCartasSel + 1;
						//añadimos la carta al dlm y tabla de Seleccionadas
						dlmCartasSeleccionadas.addElement(codigo + " - " + cartaSelec +
								" (" + numeroCartasSel + ")");
						tablaCartasSeleccionadas.put(cartaSelec, new Integer(numeroCartasSel));
						numCartas++;
						this.textoNumeroCartas.setText(new Integer(numCartas).toString());
					}
					else if (nivel == 3) {
						JOptionPane.showMessageDialog(new JOptionPane(), "Solo puedes tener 5 carta de Nivel 3", "Limite sobrepasado",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					//si la carta no se encontraba entre las Seleccionadas
					//actualizamos en la lista de Disponibles la carta del indice
					dlmCartasDisponibles.removeElement(cartaSelecAux);
					numeroCartasDisp = numeroCartasDisp - 1;
					if (numeroCartasDisp != 0) {
						tablaCartasDisponibles.put(cartaSelec, new Integer(numeroCartasDisp));
						dlmCartasDisponibles.addElement(carta.getCodigo() + " - " + cartaSelec +
								" (" + numeroCartasDisp + ")");
					}
					else {
						//si se quedan 0 repeticiones de la carta seleccionada la eliminamos de la tabla
						tablaCartasDisponibles.remove(cartaSelec);
					}
					//añadimos la carta al dlm y tabla de Seleccionadas
					dlmCartasSeleccionadas.addElement(codigo + " - " + cartaSelec + " (1)");
					tablaCartasSeleccionadas.put(cartaSelec, new Integer(1));
					numCartas++;
					this.textoNumeroCartas.setText(new Integer(numCartas).toString());
				}
				//ordenamos la lista de las cartas disponibles
				Object[] l = ordenaListaCartas(dlmCartasDisponibles.toArray());
				dlmCartasDisponibles.clear();
				int posicion = 0;
				while (posicion < l.length) {
					this.dlmCartasDisponibles.addElement(l[posicion]);
					posicion++;
				}
				//ordenamos la lista de las cartas seleccionadas
				l = ordenaListaCartas(dlmCartasSeleccionadas.toArray());
				dlmCartasSeleccionadas.clear();
				posicion = 0;
				while (posicion < l.length) {
					this.dlmCartasSeleccionadas.addElement(l[posicion]);
					posicion++;
				}
			}
		}
		catch (Exception error) {
			//mostramos con un JOptionPane el error producido
			JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}


	/**
	 *  Función para controlar el evento del mouse en la lista de cartas
	 *  seleccionadas
	 *
	 *@param  e
	 */
	void listSeleccionadas_mouseClicked(MouseEvent e) {
		//observamos el indice seleccionado
		int indice = listaSeleccionadas.getSelectedIndex();
		try {
			if (indice != -1) {
				String cartaSelecAux = (String) dlmCartasSeleccionadas.elementAt(indice);
				//hay que leer el nombre de la carta correctamente
				String cartaSelec = cartaSelecAux.substring(cartaSelecAux.indexOf("-") + 2,
						cartaSelecAux.indexOf("(") - 1);
				String codigo = coleccion.pedirCodigo(cartaSelec);
				//en la tabla tendremos que guardar (carta,num_repetic)
				int numeroCartasSel = ((Integer) tablaCartasSeleccionadas.get(cartaSelec)).intValue();
				dlmCartasSeleccionadas.removeElement(cartaSelecAux);
				numeroCartasSel = numeroCartasSel - 1;
				if (numeroCartasSel != 0) {
					dlmCartasSeleccionadas.addElement(codigo + " - " + cartaSelec +
							" (" + numeroCartasSel + ")");
					tablaCartasSeleccionadas.put(cartaSelec, new Integer(numeroCartasSel));
				}
				else {
					//si se quedan 0 repeticiones de la carta seleccionada la eliminamos
					tablaCartasSeleccionadas.remove(cartaSelec);
				}

				//añadimos la carta a la tabla de las disponibles
				if (tablaCartasDisponibles.containsKey(cartaSelec)) {
					//se mira en la lista de cartas disponibles la carta seleccionada
					int numeroCartasDisp = ((Integer) tablaCartasDisponibles.get(cartaSelec)).intValue();
					dlmCartasDisponibles.removeElement(codigo + " - " + cartaSelec +
							" (" + numeroCartasDisp + ")");
					numeroCartasDisp = numeroCartasDisp + 1;
					//añadimos la carta al dlm y tabla de Seleccionadas
					dlmCartasDisponibles.addElement(codigo + " - " + cartaSelec +
							" (" + numeroCartasDisp + ")");
					tablaCartasDisponibles.put(cartaSelec, new Integer(numeroCartasDisp));
				}
				else {
					//si la carta no se encontraba entre las disponibles
					dlmCartasDisponibles.addElement(codigo + " - " + cartaSelec + " (1)");
					tablaCartasDisponibles.put(cartaSelec, new Integer(1));
				}
				numCartas--;
				this.textoNumeroCartas.setText(new Integer(numCartas).toString());
				//ordenamos la lista de las cartas disponibles
				Object[] l = ordenaListaCartas(dlmCartasDisponibles.toArray());
				dlmCartasDisponibles.clear();
				int posicion = 0;
				while (posicion < l.length) {
					this.dlmCartasDisponibles.addElement(l[posicion]);
					posicion++;
				}
				//ordenamos la lista de las cartas seleccionadas
				l = ordenaListaCartas(dlmCartasSeleccionadas.toArray());
				dlmCartasSeleccionadas.clear();
				posicion = 0;
				while (posicion < l.length) {
					this.dlmCartasSeleccionadas.addElement(l[posicion]);
					posicion++;
				}
			}
		}
		catch (Exception error) {
			//mostramos con un JOptionPane el error producido
			JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}


	/**
	 *  Función actionPerfomed que controla cuando se pulsa el botón de
	 *  jMenuFileCargar
	 *
	 *@param  e
	 */
	void botCargar_mouseClicked(MouseEvent e) {
		Object[] barajasArray = ordenaListaBarajas(usuario.getListaBarajas().toArray());
		for (int i = 0; i < barajasArray.length; i++) {
			int hasta = ((String) barajasArray[i]).length() - usuario.getNombreUsuario().length() - 1;
			barajasArray[i] = ((String) barajasArray[i]).substring(0, hasta);
		}
		if (barajasArray.length != 0) {
			Object opcion = JOptionPane.showInputDialog(this,
					"Selecciona una de tus barajas",
					"Petición de la baraja",
					JOptionPane.QUESTION_MESSAGE,
					null, barajasArray, barajasArray[0]);
			if (opcion != null) {
				cargarBarajaUsuario((String) opcion + "_" + usuario.getNombreUsuario());
			}
		}
	}


	/**
	 *  Función actionPerfomed que controla cuando se pulsa el botón de
	 *  jMenuFileGuardar
	 *
	 *@param  e
	 */
	void botGuardar_mouseClicked(MouseEvent e) {
		if (this.botGuardar.isEnabled()) {
			//tenemos que guardar barajas con un minimo de 40 cartas
			if (numCartas >= 40) {
				//guardamos la baraja en .bar
				guardarBarajaUsuario(nombreArchivoBaraja);
				//guardamos la tabla de disponibles en usuario y en .rep
				guardarColeccionUsuario();
				//guardamos el nombre de la baraja en la lista de barajas del usuario y en .conf
				guardarListaBarajasUsuario(nombreArchivoBaraja);
			}
			else {
				this.repaint();
				this.inhabilitaPanel();

				this.getContentPane().add(new PanelGenerico("../imagenes/panelesInfo/MinimoCartas.jpg", this), 0);
				repaint();
			}
		}
	}


	/**
	 *  Función actionPerfomed que controla cuando se pulsa el botón de
	 *  jMenuFileGuardarComo
	 *
	 *@param  e
	 */
	void botGuardarComo_mouseClicked(MouseEvent e) {
		//tenemos que guardar barajas con un minimo de 40 cartas
		if (numCartas >= 40) {
			//usamos un filtro para guardar las barajas
			JFileChooser fichero = new JFileChooser("../barajas");
			FiltroURL filtro = new FiltroURL("_" + usuario.getNombreUsuario() + ".bar");
			fichero.setFileFilter(filtro);
			int valor = fichero.showSaveDialog(this);
			if (valor == JFileChooser.APPROVE_OPTION) {
				String nombreFichero = (fichero.getSelectedFile()).getName();
				if (nombreFichero.endsWith(".bar")) {
					nombreFichero = nombreFichero.substring(0, nombreFichero.length() - 4);
				}
				if (!nombreFichero.endsWith("_" + usuario.getNombreUsuario())) {
					nombreFichero = nombreFichero + "_" + usuario.getNombreUsuario();
				}
				if (!nombreFichero.equals("_" + usuario.getNombreUsuario())) {
					//guardamos la baraja en .bar
					guardarBarajaUsuario(nombreFichero);
					//guardamos la tabla de disponibles en usuario y en .rep
					guardarColeccionUsuario();
					//guardamos el nombre de la baraja en la lista de barajas del usuario y en .conf
					guardarListaBarajasUsuario(nombreFichero);
				}
				else {
					JOptionPane.showMessageDialog(new JOptionPane(),
							"No se ha salvado la baraja, el nombre no es aceptado",
							"AVISO", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		else {
			this.repaint();
			this.inhabilitaPanel();

			this.getContentPane().add(new PanelGenerico("../imagenes/panelesInfo/MinimoCartas.jpg", this), 0);
			repaint();
		}
	}


	/**
	 *  Función actionPerfomed que controla cuando se pulsa el botón de
	 *  jMenuFileExit
	 *
	 *@param  e
	 */
	void botSalir_mouseClicked(MouseEvent e) {
		this.hide();
		padre.setEnabled(true);
		padre.show();
	}


	/**
	 *  Función actionPerfomed que controla cuando se pulsa el botón de
	 *  jMenuHelpAyuda
	 *
	 *@param  e
	 */
	void botAyuda_mouseClicked(MouseEvent e) {
	}


	/**
	 *  Función actionPerfomed que controla cuando se pulsa el botón de
	 *  jMenuHelpAbout
	 *
	 *@param  e
	 */
	void botAcerca_mouseClicked(MouseEvent e) {
		JOptionPane.showMessageDialog(this, "Editor de las barajas de las cartas para Génesis.\n" +
				"Copyright (c) 2005\n" +
				"Version 2.0\n" +
				"Por: Miguel Cayeiro Garcia",
				"Acerca de...",
				JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon("../imagenes/Escudo_Genesis_pequeño.jpg"));
	}


	/**
	 *@param  e
	 */
	void this_windowClosing(WindowEvent e) {
		padre.setEnabled(true);
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
	 *
	 *@param  lista  Description of Parameter
	 *@return        Description of the Returned Value
	 */
	private Object[] ordenaListaCartas(Object[] lista) {
		boolean hayCambio = true;
		while (hayCambio) {
			hayCambio = false;
			int i = 0;
			while (i < lista.length - 1) {
				String codigoNombre1 = (String) lista[i];
				if (codigoNombre1.indexOf("(") >= 0) {

					//para quitar en los dlm el numero de cartas
					codigoNombre1 = codigoNombre1.substring(0,
							codigoNombre1.indexOf("(") - 1);
				}
				String codigo1 = codigoNombre1.substring(0,
						codigoNombre1.indexOf("-") - 1);
				String nombre1 = codigoNombre1.substring(codigoNombre1.indexOf("-") + 2);

				String codigoNombre2 = ((String) lista[i + 1]);
				if (codigoNombre2.indexOf("(") >= 0) {

					//para quitar en los dlm el numero de cartas
					codigoNombre2 = codigoNombre2.substring(0,
							codigoNombre2.indexOf("(") - 1);
				}
				String codigo2 = codigoNombre2.substring(0,
						codigoNombre2.indexOf("-") - 1);
				String nombre2 = codigoNombre2.substring(codigoNombre2.indexOf("-") + 2);
				//comparar las razas
				if (codigo1.charAt(0) > codigo2.charAt(0)) {
					String codigoNombre = (String) lista[i];
					lista[i] = (String) lista[i + 1];
					lista[i + 1] = codigoNombre;
					hayCambio = true;
				}
				else if ((codigo1.charAt(0) == codigo2.charAt(0)) &&
						(codigo1.length() > codigo2.length())) {
					String codigoNombre = (String) lista[i];
					lista[i] = (String) lista[i + 1];
					lista[i + 1] = codigoNombre;
					hayCambio = true;
				}
				else if ((codigo1.charAt(0) == codigo2.charAt(0)) &&
						(codigo1.length() == codigo2.length())) {
					int j = 1;
					while (codigo1.charAt(j) == codigo2.charAt(j)) {
						j++;
					}
					if (j < codigo1.length() &&
							(j == codigo2.length() || codigo1.charAt(j) > codigo2.charAt(j))) {
						String codigoNombre = (String) lista[i];
						lista[i] = (String) lista[i + 1];
						lista[i + 1] = codigoNombre;
						hayCambio = true;
					}
				}
				i++;
			}
		}
		return lista;
	}


	/**
	 *  Función que carga la colección de cartas del usuario del archivo
	 *  "usuario.rep"
	 */
	private void cargarColeccionUsuario() {
		//añadir las cartas disponibles para la baraja
		try {
			listaCartasComboBox = new LinkedList();
			dlmCartasDisponibles.clear();
			Enumeration nombresCartasKeys;
			nombresCartasKeys = tablaCartasDisponibles.keys();
			while (nombresCartasKeys.hasMoreElements()) {
				String nomCarta = (String) nombresCartasKeys.nextElement();
				Integer cantidad = (Integer) tablaCartasDisponibles.get(nomCarta);

				String codigoCarta = coleccion.pedirCodigo(nomCarta);
				String razaCartaDisponible = coleccion.pedirCarta(codigoCarta).getIdRaza();
				int nivelCartaDisponible = coleccion.pedirCarta(codigoCarta).getNivel();
				//cargamos solo las cartas de la raza seleccionada y los de Nivel3 de las demás razas
				if ((nivelCartaDisponible == 3) || (razaCartaDisponible.equals(raza))) {
					tablaCartasDisponibles.put(nomCarta, cantidad);
					listaCartasComboBox.add(codigoCarta + " - " + nomCarta);
					dlmCartasDisponibles.addElement(codigoCarta + " - " + nomCarta + " (" + cantidad + ")");
				}
			}
			//ordenamos la lista de las cartas disponibles
			Object[] l = ordenaListaCartas(dlmCartasDisponibles.toArray());
			dlmCartasDisponibles.clear();
			int posicion = 0;
			while (posicion < l.length) {
				this.dlmCartasDisponibles.addElement(l[posicion]);
				posicion++;
			}
			//ordenamos el combobox
			l = ordenaListaCartas(listaCartasComboBox.toArray());
			this.comboNombreCarta.removeAllItems();
			posicion = 0;
			while (posicion < l.length) {
				this.comboNombreCarta.addItem(l[posicion]);
				posicion++;
			}
		}
		catch (Exception error) {
			//mostramos con un JOptionPane el error producido
			//JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error cargando las cartas",
			// JOptionPane.ERROR_MESSAGE);
		}
	}


	/**
	 *  Función que carga la baraja personalizada del usuario del archivo
	 *  "baraja.bar"
	 *
	 *@param  fichBaraja  archivo con la baraja personalizada para cargar
	 */
	private void cargarBarajaUsuario(String fichBaraja) {
		//añadir la coleccion
		try {
			FileInputStream archivo1 = new FileInputStream("../barajas/" + fichBaraja + ".bar");
			//inicializamos el numero de cartas de la baraja
			numCartas = 0;
			//variable para controlar los bytes que se deben leer
			int numeroDeBytesALeer = archivo1.read();
			//cargamos el nombre de usuario y version de las cartas
			String nombreUsuario = descodificar(leerFrase(numeroDeBytesALeer, archivo1));
			int version = archivo1.read();
			if (nombreUsuario.equals(usuario.getNombreUsuario()) &&
					version <= coleccion.getVersion()) {
				//cargamos de que raza es la baraja que estamos cargando
				numeroDeBytesALeer = archivo1.read();
				String nombreRaza = descodificar(leerFrase(numeroDeBytesALeer, archivo1));
				raza = nombreRaza;
				this.textoRaza.setText(raza);
				tablaCartasDisponibles = (Hashtable) usuario.getTablaCartasDisponibles().clone();
				cargarColeccionUsuario();
				numeroDeBytesALeer = archivo1.read();
				dlmCartasSeleccionadas.clear();
				tablaCartasSeleccionadas.clear();
				while (numeroDeBytesALeer >= 0) {
					// i==-1 es fin de fichero
					String cantidad = descodificar(leerFrase(numeroDeBytesALeer, archivo1));
					numeroDeBytesALeer = archivo1.read();
					String codigoCarta = descodificar(leerFrase(numeroDeBytesALeer, archivo1));
					String nombre = coleccion.pedirCarta(codigoCarta).getNombre();
					Integer rep = new Integer(cantidad);
					tablaCartasSeleccionadas.put(nombre, rep);

					listaCartasComboBox.remove(codigoCarta + " - " + nombre);
					listaCartasComboBox.add(codigoCarta + " - " + nombre);

					numCartas = numCartas + rep.intValue();
					numeroDeBytesALeer = archivo1.read();
				}
				//recorremos la tabla de la baraja cargada y lo añadimos a la lista de cartas seleccionadas
				Enumeration nombresCartasKeys;
				String nomCartaValue;
				nombresCartasKeys = tablaCartasSeleccionadas.keys();
				//escribimos en el dlm de Seleccionadas todas las cartas cargadas de la baraja
				while (nombresCartasKeys.hasMoreElements()) {
					nomCartaValue = (String) nombresCartasKeys.nextElement();
					int numeroCartasSel = ((Integer) tablaCartasSeleccionadas.get(nomCartaValue)).intValue();
					String codigo = coleccion.pedirCodigo(nomCartaValue);
					dlmCartasSeleccionadas.addElement(codigo + " - " + nomCartaValue +
							" (" + numeroCartasSel + ")");
				}
				this.textoNumeroCartas.setText(new Integer(numCartas).toString());
				nombreArchivoBaraja = fichBaraja;
				this.textoBarajaCargada.setText(nombreArchivoBaraja.substring(0, nombreArchivoBaraja.length() - usuario.getNombreUsuario().length() - 1));
				this.botGuardar.setEnabled(true);
			}
			else if (!nombreUsuario.equals(usuario.getNombreUsuario())) {
				throw new Exception("Esta baraja no pertenece al usuario: " + usuario.getNombreUsuario());
			}
			else if (version > coleccion.getVersion()) {
				//mostramos con un JOptionPane el aviso de actualizar la coleccion
				JOptionPane.showMessageDialog(new JOptionPane(),
						"La baraja tiene una version más moderna que la coleccion\n" +
						"Actualice el programa. \"Coleccion.car\"", "AVISO",
						JOptionPane.INFORMATION_MESSAGE);
			}
			archivo1.close();
			//ordenamos el combobox
			Object[] l = ordenaListaCartas(listaCartasComboBox.toArray());
			this.comboNombreCarta.removeAllItems();
			int posicion = 0;
			while (posicion < l.length) {
				this.comboNombreCarta.addItem(l[posicion]);
				posicion++;
			}
			//ordenamos la lista de las cartas seleccionadas
			l = ordenaListaCartas(dlmCartasSeleccionadas.toArray());
			dlmCartasSeleccionadas.clear();
			posicion = 0;
			while (posicion < l.length) {
				this.dlmCartasSeleccionadas.addElement(l[posicion]);
				posicion++;
			}
		}
		catch (Exception error) {
			//mostramos con un JOptionPane el error producido
			JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
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
				String nombre1 = nombreUsuario1.substring(0, nombreUsuario1.indexOf("_"));

				String nombreUsuario2 = ((String) lista[i + 1]);
				String nombre2 = nombreUsuario2.substring(0, nombreUsuario2.indexOf("_"));
				int j = 0;
				while (j < nombre1.length() && j < nombre2.length() &&
						nombre1.charAt(j) == nombre2.charAt(j)) {
					j++;
				}
				if (j == nombre2.length()) {
					lista[i] = nombreUsuario2;
					lista[i + 1] = nombreUsuario1;
					hayCambio = true;
				}
				else if (j < nombre1.length() && nombre1.charAt(j) > nombre2.charAt(j)) {
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
	 *@param  fichBaraja  Description of Parameter
	 */
	private void guardarBarajaUsuario(String fichBaraja) {
		try {
			//miramos si tenemos que borrar el archivo cargado (origen)
			if (!nombreArchivoBaraja.equals(fichBaraja)) {
				File ficheroABorrar = new File("../barajas/" + nombreArchivoBaraja + ".bar");
				ficheroABorrar.delete();
				//miramos si tenemos que cargar el archivo donde se guardara (destino) porque existe
				if (usuario.getListaBarajas().contains(fichBaraja)) {
					//existe una baraja del usuario con ese nombre y no esta cargada
					//hay que cargar todas sus cartas en disponibles para su uso

					FileInputStream archivo1 = new FileInputStream("../barajas/" + fichBaraja + ".bar");
					//variable para controlar los bytes que se deben leer
					int numeroDeBytesALeer = archivo1.read();
					//cargamos el nombre de usuario
					String nombreUsuario = descodificar(leerFrase(numeroDeBytesALeer, archivo1));
					archivo1.read();
					if (nombreUsuario.equals(usuario.getNombreUsuario())) {
						numeroDeBytesALeer = archivo1.read();
						leerFrase(numeroDeBytesALeer, archivo1);
						numeroDeBytesALeer = archivo1.read();
						//añadimos las cartas a las repetidas
						while (numeroDeBytesALeer >= 0) {
							// i==-1 es fin de fichero
							String cantidad = descodificar(leerFrase(numeroDeBytesALeer, archivo1));
							numeroDeBytesALeer = archivo1.read();
							String codigoCarta = descodificar(leerFrase(numeroDeBytesALeer, archivo1));
							String nombre = coleccion.pedirCarta(codigoCarta).getNombre();
							int rep = new Integer(cantidad).intValue();
							Integer repetidas;
							if (tablaCartasDisponibles.containsKey(nombre)) {
								int repDisponibles = ((Integer)
										tablaCartasDisponibles.get(nombre)).intValue();
								repetidas = new Integer(rep + repDisponibles);
							}
							else {
								repetidas = new Integer(rep);
							}
							tablaCartasDisponibles.put(nombre, repetidas);
							numeroDeBytesALeer = archivo1.read();
						}
						cargarColeccionUsuario();
					}
					else {
						throw new Exception("El archivo destino ya existía y era de otro usuario");
					}
					archivo1.close();
				}
			}
			FileOutputStream archivo1 = new FileOutputStream("../barajas/" + fichBaraja + ".bar");
			//guardamos el nombre del usuario y la version de las cartas
			archivo1.write(usuario.getNombreUsuario().length());
			archivo1.write(codificar(usuario.getNombreUsuario().getBytes()).getBytes());
			archivo1.write(coleccion.getVersion());
			//guardamos el nombre de la raza principal de la baraja que guardamos
			archivo1.write(raza.length());
			archivo1.write(codificar(raza.getBytes()).getBytes());
			//pedimos los codigos de todas las cartas de la colección
			Enumeration nombresCartasKeys = tablaCartasSeleccionadas.keys();
			//escribimos la baraja en el archivo
			while (nombresCartasKeys.hasMoreElements()) {
				String nombreCarta = (String) nombresCartasKeys.nextElement();
				String codigo = coleccion.pedirCodigo(nombreCarta);
				Integer cantidad = (Integer) tablaCartasSeleccionadas.get(nombreCarta);
				String cantidadLetra = cantidad.toString();
				archivo1.write(cantidadLetra.length());
				archivo1.write(codificar(cantidadLetra.getBytes()).getBytes());
				archivo1.write(codigo.length());
				archivo1.write(codificar(codigo.getBytes()).getBytes());
			}
			this.botGuardar.setEnabled(true);
			archivo1.close();
		}
		catch (Exception error) {
			//mostramos con un JOptionPane el error producido
			JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error Baraja",
					JOptionPane.ERROR_MESSAGE);
		}
	}


	/**
	 */
	private void guardarColeccionUsuario() {
		try {
			usuario.setTablaCartasDisponibles((Hashtable) tablaCartasDisponibles.clone());
			FileOutputStream archivo1 = new FileOutputStream("../documentos/" + usuario.getNombreUsuario() + ".rep");
			//guardamos el nombre del usuario
			archivo1.write(usuario.getNombreUsuario().length());
			archivo1.write(codificar(usuario.getNombreUsuario().getBytes()).getBytes());
			//pedimos los codigos de todas las cartas de la colección
			Enumeration nombresCartasKeys = tablaCartasDisponibles.keys();
			//guardamos las cartas en el archivo
			while (nombresCartasKeys.hasMoreElements()) {
				String nombreCarta = (String) nombresCartasKeys.nextElement();
				String codigo = coleccion.pedirCodigo(nombreCarta);
				Integer cantidad = (Integer) tablaCartasDisponibles.get(nombreCarta);
				String cantidadLetra = cantidad.toString();
				archivo1.write(cantidadLetra.length());
				archivo1.write(codificar(cantidadLetra.getBytes()).getBytes());
				archivo1.write(codigo.length());
				archivo1.write(codificar(codigo.getBytes()).getBytes());
			}
			archivo1.close();
		}
		catch (Exception error) {
			//mostramos con un JOptionPane el error producido
			JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error Coleccion",
					JOptionPane.ERROR_MESSAGE);
		}
	}


	/**
	 *  Función que añade la baraja guardada a la lista de las barajas del
	 *  usuario
	 *
	 *@param  fichBaraja  Description of Parameter
	 */
	private void guardarListaBarajasUsuario(String fichBaraja) {
		try {
			//guardamos tambien el nombre de la baraja en la lista, evitamos que se guarden barajas con el mismo nombre
			if (usuario.getListaBarajas().contains(nombreArchivoBaraja)) {
				usuario.getListaBarajas().remove(nombreArchivoBaraja);
			}
			if (!usuario.getListaBarajas().contains(fichBaraja)) {
				usuario.getListaBarajas().add(fichBaraja);
			}
			nombreArchivoBaraja = fichBaraja;
			this.textoBarajaCargada.setText(nombreArchivoBaraja.substring(0, nombreArchivoBaraja.length() - usuario.getNombreUsuario().length() - 1));

			FileOutputStream archivoConfig = new FileOutputStream("../documentos/" + usuario.getNombreUsuario() + ".conf");
			//guardamos la lista de las barajas con la version de las cartas y la raza de la baraja
			archivoConfig.write(usuario.getNombreUsuario().length());
			archivoConfig.write(codificar(usuario.getNombreUsuario().getBytes()).getBytes());
			archivoConfig.write(usuario.getVersionSobres());
			int i = 0;
			while (i < usuario.getListaBarajas().size()) {
				String baraja = (String) usuario.getListaBarajas().get(i);
				archivoConfig.write(baraja.length());
				archivoConfig.write(codificar(baraja.getBytes()).getBytes());
				i++;
			}
			archivoConfig.close();
		}
		catch (Exception error) {
			//mostramos con un JOptionPane el error producido
			JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error Lista",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
