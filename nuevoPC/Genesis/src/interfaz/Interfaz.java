

package interfaz;

import cartas.*;
import motorJuego.*;
import usuario.*;
import comunicacion.*;
import padrePaneles.*;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.awt.event.*;
import java.util.Vector;


/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: Clase que implementa al componente gráfico que representa a
 *  una carta en el juego</p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Tony
 *@version    1.0
 */

public class Interfaz extends PadrePaneles {
	JPanel panelChat = new JPanel();
	//JPanel panelMarcador = new JPanel();
	JPanel panelEstado = new JPanel();
	JPanel panelSuperior = new JPanel();
	JPanel juegoMesaOp = new JPanel();
	JPanel juegoMesaJug = new JPanel();
	JPanel juegoManoJug = new JPanel();

	JButton botonSalir = new JButton();
	JButton botonExplorador = new JButton();
	JButton botonAceptarExplorador = new JButton();
	JButton botonPasarTurno = new JButton();
	JButton botonChatPatino = new JButton(new ImageIcon("../imagenes/conectate.jpg"));
	JLabel jLabel1 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JLabel jLabel5 = new JLabel();
	JLabel jLabel6 = new JLabel();
	JLabel jLabel7 = new JLabel();
	JLabel jLabel8 = new JLabel();
	JLabel jLabel10 = new JLabel();
	JLabel jLabel11 = new JLabel();
	JLabel labelTablero = new JLabel();
	JLabel textoEstado = new JLabel();
	JLabel turno = new JLabel();
	JLabel manas = new JLabel();
	JLabel manasGastados = new JLabel();

	String avatar = "../imagenes/Escudo_Genesis.jpg";
	ImageIcon avatarIcon = new ImageIcon(avatar);
	ImageIcon avatarIcon2 = new ImageIcon(avatar);
	int cont;
	//variables que almacenan la dimension de la pantalla
	int altoPantalla = (int) Toolkit.getDefaultToolkit().getScreenSize().
			getHeight();
	int anchoPantalla = (int) Toolkit.getDefaultToolkit().getScreenSize().
			getWidth();
	int unidadAlto = altoPantalla / 40;
	int unidadAncho = anchoPantalla / 100;

	private Dibujo mEne, mMia, mMano;
	private JFrame padre;
	private JTabbedPane jTabbedPane1 = new JTabbedPane();
	private JPanel panelJuego = new JPanel();
	private Vector lista1, lista2, lista3;
	private CMazo mazo;
	private CPartida partida;
	private Usuario usuario;

	private char tipo;
	private Marcador marcador;
	private PanelOpciones panelOpciones;

	private MiTextArea areaEscritura = new MiTextArea("../imagenes/chat/fondo.jpg");
	private MiTextArea areaCharla = new MiTextArea("../imagenes/chat/fondo.jpg");

	private GestorUsuarios gestorUsuarios;
	private Controlador controlador;
	private GUI ventana;

	//son los nicks de chat. es necesario que esten en la interfaz
	private String miNickdeInterfaz;
	private String nickDelOponenteDeInterfaz;

        private JScrollPane panelEscritura;
        private JScrollPane panelCharla;



	/**
	 *  Creates a new instance of Interfaz
	 *
	 *@param  tipo     Description of Parameter
	 *@param  partida  Description of Parameter
	 *@param  p        Description of Parameter
	 *@param  usu      Description of Parameter
	 */
	public Interfaz(char tipo, CPartida partida, JFrame p, Usuario usu) {
		//super("Interfaz");
		getContentPane().setLayout(null);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.padre = p;
		this.partida = partida;
		this.partida.setInterfaz(this);
		this.usuario = usu;

		if (partida.getMazo() == null) {
			System.out.println("Esto esta vacio");
		}
		else {
			System.out.println("OK");
		}
		setUndecorated(true);
		marcador = new Marcador(partida);
		panelOpciones = new PanelOpciones(tipo, this, marcador,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 20,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 50);

		panelOpciones.setVisible(true);

		try {
			cambia_skin(tipo);
			jbInit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		setVisible(true);

		lista1 = partida.getMesa().getJugador2().getVectorCriaturas();
		//mesa enemiga
		lista2 = partida.getMesa().getJugador1().getVectorCriaturas();
		//mi mesa
		lista3 = partida.getMano().getCartas();
		//mi mano

		Dimension dimensiones = Toolkit.getDefaultToolkit().getScreenSize();
		double h = dimensiones.getHeight();
		double w = dimensiones.getWidth() - 20;

		mEne = new Dibujo(new Rectangle(0, 0, (int) w, (int) h / 3), lista1, this, false, tipo, partida.getMazo(), partida.getCementerio());
		// getContentPane().add(dib);
		labelTablero.add(mEne);

		mMia = new Dibujo(new Rectangle(0, (int) h / 3, (int) w, (int) h / 3), lista2, this, false, tipo, partida.getMazo(), partida.getCementerio());
		// getContentPane().add(dib2);
		labelTablero.add(mMia);

		mMano = new Dibujo(new Rectangle(0, (int) (2 * (h / 3)), (int) w, ((int) h / 3) - 60), lista3, this, true, tipo, partida.getMazo(), partida.getCementerio());
		// getContentPane().add(dib2);
		labelTablero.add(mMano);
	}


	/**
	 *  Fija el atributo nickDeInterfaz que usare en el chat
	 *
	 *@param  n  el nick que uso en el chat
	 */
	public void setmiNickdeInterfaz(String n) {
		miNickdeInterfaz = n;
	}


	/**
	 *  fija el atributo nickDeInterfaz que usa en el chat mi oponente
	 *
	 *@param  n  el nick que usa mi oponente en el chat
	 */
	public void setnickDelOponenteInterfaz(String n) {
		nickDelOponenteDeInterfaz = n;
	}


	/**
	 *  Sets the TextoTurno attribute of the Interfaz object
	 *
	 *@param  texto  The new TextoTurno value
	 */
	public void setTextoTurno(String texto) {
		this.turno.setText(texto);
	}


	/**
	 *  Sets the TextoManas attribute of the Interfaz object
	 *
	 *@param  texto  The new TextoManas value
	 */
	public void setTextoManas(String texto) {
		this.manas.setText(texto);
	}


	/**
	 *  Sets the TextoManasGastados attribute of the Interfaz object
	 *
	 *@param  texto  The new TextoManasGastados value
	 */
	public void setTextoManasGastados(String texto) {
		this.manasGastados.setText(texto);
	}


	/**
	 *  Gets the Partida attribute of the Interfaz object
	 *
	 *@return    The Partida value
	 */
	public CPartida getPartida() {
		return partida;
	}


	/**
	 *  Gets the AreaEscritura attribute of the Interfaz object
	 *
	 *@return    The AreaEscritura value
	 */
	public MiTextArea getAreaEscritura() {
		return areaEscritura;
	}


	/**
	 *  Gets the AreaCharla attribute of the Interfaz object
	 *
	 *@return    The AreaCharla value
	 */
	public MiTextArea getAreaCharla() {
		return areaCharla;
	}


	/**
	 *  devuelve el atributo nickDeInterfaz que uso en chat
	 *
	 *@return    el nick que uso en el chat
	 */
	public String getmiNickdeInterfaz() {
		return miNickdeInterfaz;
	}


	/**
	 *  devuelve el atributo nickDeInterfaz que usa en el chat mi oponente
	 *
	 *@return    nickDeInterfaz que usa en el chat mi oponente
	 */
	public String getnickDelOponenteDeInterfaz() {
		return nickDelOponenteDeInterfaz;
	}


	/**
	 *  Gets the GestorUsuarios attribute of the Interfaz object
	 *
	 *@return    The GestorUsuarios value
	 */
	public GestorUsuarios getGestorUsuarios() {
		return gestorUsuarios;
	}


	/**
	 *  Gets the Controlador attribute of the Interfaz object
	 *
	 *@return    The Controlador value
	 */
	public Controlador getControlador() {
		return controlador;
	}


	/**
	 *  Gets the Ventana attribute of the Interfaz object
	 *
	 *@return    The Ventana value
	 */
	public GUI getVentana() {
		return ventana;
	}


	/**
	 *  Description of the Method
	 */
	public void habilitaPanel() {

          this.botonSalir.setEnabled(true);
          this.panelOpciones.setEnabled(true);
         this.panelSuperior.setEnabled(true);
         this.panelJuego.setEnabled(true);
          this.botonExplorador.setEnabled(true);
          this.botonAceptarExplorador.setEnabled(true);
          this.botonPasarTurno.setEnabled(true);
          this.botonChatPatino.setEnabled(true);
          this.jLabel1.setEnabled(true);
          this.jLabel3.setEnabled(true);
          this.jLabel4.setEnabled(true);
          this.jLabel5.setEnabled(true);
          this.jLabel6.setEnabled(true);
          this.jLabel7.setEnabled(true);
          this.jLabel8.setEnabled(true);
          this.jLabel10.setEnabled(true);
          this.jLabel11.setEnabled(true);
          this.labelTablero.setEnabled(true);
          this.textoEstado.setEnabled(true);
          this.turno.setEnabled(true);
          this.manas.setEnabled(true);
          this.manasGastados.setEnabled(true);
          this.jTabbedPane1.setEnabled(true);
          this.panelEscritura.getHorizontalScrollBar().setEnabled(true);
         this.panelEscritura.getVerticalScrollBar().setEnabled(true);
         this.panelCharla.getHorizontalScrollBar().setEnabled(true);
         this.panelCharla.getVerticalScrollBar().setEnabled(true);
         this.areaCharla.setEnabled(true);
         this.areaEscritura.setEnabled(true);

	}


	public void inhabilitaPanel() {

          this.botonSalir.setEnabled(false);
          this.panelOpciones.setEnabled(false);
          this.panelSuperior.setEnabled(false);
          this.panelJuego.setEnabled(false);
          this.botonExplorador.setEnabled(false);
          this.botonAceptarExplorador.setEnabled(false);
          this.botonPasarTurno.setEnabled(false);
          this.botonChatPatino.setEnabled(false);
          this.jLabel1.setEnabled(false);
          this.jLabel3.setEnabled(false);
          this.jLabel4.setEnabled(false);
          this.jLabel5.setEnabled(false);
          this.jLabel6.setEnabled(false);
          this.jLabel7.setEnabled(false);
          this.jLabel8.setEnabled(false);
          this.jLabel10.setEnabled(false);
          this.jLabel11.setEnabled(false);
          this.labelTablero.setEnabled(false);
          this.textoEstado.setEnabled(false);
          this.turno.setEnabled(false);
          this.manas.setEnabled(false);
          this.manasGastados.setEnabled(false);
          this.jTabbedPane1.setEnabled(false);
          this.panelEscritura.getHorizontalScrollBar().setEnabled(false);
          this.panelEscritura.getVerticalScrollBar().setEnabled(false);
          this.panelCharla.getHorizontalScrollBar().setEnabled(false);
          this.panelCharla.getVerticalScrollBar().setEnabled(false);
          this.areaCharla.setEnabled(false);
          this.areaEscritura.setEnabled(false);
	}


	//Escribe lo pasado por parametro en el label de Estado
	/**
	 *  Description of the Method
	 *
	 *@param  cadena  Description of Parameter
	 */
	public void ponTextoEstado(String cadena) {

		textoEstado.setText("COMENTARIOS: " + cadena);
		textoEstado.setForeground(Color.WHITE);
		textoEstado.setFont(new java.awt.Font("Serif", 3, 25));

	}


	/**
	 *  Description of the Method
	 */
	public void baja() {

		for (int i = 0; i < lista3.size(); i++) {
			if (lista3.size() != 0) {
				if (((CACarta) lista3.get(i)).isBajada()) {
					lista2.add(lista3.get(i));
				}
			}
		}
		repaint();
	}


	/**
	 *  Método que actualiza la pantalla cuando se pulsa intro en el area de
	 *  escritura del chat
	 */
	public void actualizar() {
		StringBuffer pruebaBuffer = new StringBuffer(areaEscritura.getText());
		if (cont == 0) {
			controlador.enviarPrivadoAServidor(miNickdeInterfaz, nickDelOponenteDeInterfaz, pruebaBuffer.toString());
			areaEscritura.setText("");
			cont = 1;
		}
		else {
			//bug! borrar solo el primer caracter en el caso de que sea un intro!!!
			pruebaBuffer.deleteCharAt(0);
			//     areaCharla.append("<" + miNickdeInterfaz+ ">" + pruebaBuffer.toString() +"\n");
			controlador.enviarPrivadoAServidor(miNickdeInterfaz, nickDelOponenteDeInterfaz, pruebaBuffer.toString());
			areaEscritura.setText("");
		}

	}


	/**
	 *  el servidor de chat envia un texto que sera mostrado en la ventana de
	 *  chat. da igual quien mande el texto. siempre usa este metodo.
	 *
	 *@param  textoEnemigo  Description of Parameter
	 */
	public void actualizaPorEnemigo(String textoEnemigo) {
		areaCharla.append(textoEnemigo + "\n");
	}


	/**
	 *  BOTON PARA CREAR EL CHAT PATINHO
	 *
	 *@param  e  click del raton
	 */
	/*void botonChatPatino_mouseClicked(MouseEvent e) {
		preguntaJuegoRed(this);
		//	this.dispose();
	}
*/

	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonChatPatino_mouseEntered(MouseEvent e) {
		botonChatPatino.setForeground(Color.white);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonChatPatino_mouseExited(MouseEvent e) {
		botonChatPatino.setForeground(Color.orange);
	}



	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */



	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonSalir_mouseEntered(MouseEvent e) {
		botonSalir.setForeground(Color.white);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonSalir_mouseExited(MouseEvent e) {
		botonSalir.setForeground(Color.orange);
	}



	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonPasarTurno_mouseEntered(MouseEvent e) {
		botonPasarTurno.setForeground(Color.blue);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonPasarTurno_mouseExited(MouseEvent e) {
		botonPasarTurno.setForeground(Color.green);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void BotonExplorador_mouseClicked(MouseEvent e) {
		//Cuando se pulsa el boton, aparece un browser
		JLabel jLabel8 = new JLabel();
		jLabel8.setBounds(550, 150, 200, 300);
		jLabel8.setBackground(Color.BLUE);
		JFileChooser navegador = new JFileChooser("c:/");
		navegador.setSize(400, 300);
		Container parent = botonAceptarExplorador.getParent();
		int choice = navegador.showSaveDialog(parent);
		if (choice == JFileChooser.APPROVE_OPTION) {
			String filename = navegador.getSelectedFile().getAbsolutePath();
			//introduzco una imagen en el chat
			ImageIcon avatarIcon = new ImageIcon(filename);
			//redimensionar la imagen si es muy grande
			ImageIcon avatarIconAux = new ImageIcon(filename);
			if (avatarIcon.getIconWidth() > jLabel10.getWidth()) {
				avatarIconAux = new ImageIcon(
						avatarIcon.getImage().getScaledInstance(
						jLabel10.getWidth(),
						-1, Image.SCALE_DEFAULT));
				avatarIcon = avatarIconAux;
			}
			jLabel10.setIcon(avatarIcon);
			repaint();
		}
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void BotonExplorador_mouseEntered(MouseEvent e) {
		botonExplorador.setForeground(Color.white);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void BotonExplorador_mouseExited(MouseEvent e) {
		botonExplorador.setForeground(Color.yellow);
	}


//Adapta el fondo y puntero del raton al tipo de raza elegido
	/**
	 *  Description of the Method
	 *
	 *@param  tipo  Description of Parameter
	 */
	private void cambia_skin(char tipo) {

		ImageIcon cursor;
		switch (tipo) {
			case 'A':
			{
				//skin de angeles
				ImageIcon imagenSkin = new ImageIcon("../imagenes/fondos/fondo_Ángeles.jpg");
				labelTablero.setIcon(imagenSkin);

				cursor = new ImageIcon("../imagenes/cursores/ángeles.gif");

				break;
			}
			case 'D':
			{
				//skin de demonios
				ImageIcon imagenSkin = new ImageIcon("../imagenes/fondos/fondo_Demonios.jpg");
				labelTablero.setIcon(imagenSkin);

				cursor = new ImageIcon("../imagenes/cursores/demonios.gif");

				break;
			}
			case 'H':
			{
				//skin de humanos
				ImageIcon imagenSkin = new ImageIcon("../imagenes/fondos/fondo_Humanos.jpg");
				labelTablero.setIcon(imagenSkin);

				cursor = new ImageIcon("../imagenes/cursores/humanos.gif");

				break;
			}
			case 'I':
			{
				//skin de inmortales
				ImageIcon imagenSkin = new ImageIcon("../imagenes/fondos/fondo_Inmortales.jpg");
				labelTablero.setIcon(imagenSkin);

				cursor = new ImageIcon("../imagenes/cursores/inmortales.gif");
				break;
			}
			default:
			{
				cursor = new ImageIcon();
				break;
			}
		}

		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@exception  Exception  Description of Exception
	 */
	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		jTabbedPane1.setForeground(Color.black);
		jTabbedPane1.setBounds(new Rectangle(5, 5, 5, 5));

		this.getContentPane().setLayout(null);

		panelJuego.setLayout(null);
		panelJuego.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.getContentPane().setBackground(Color.black);
//    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setFont(new java.awt.Font("Dialog", 3, 15));

		juegoMesaOp.setBounds(new Rectangle(0, 0,
				(int) panelJuego.getSize().getWidth(),
				(int) panelJuego.getSize().getHeight() / 3));
		juegoMesaOp.setOpaque(false);
		juegoMesaOp.setLayout(null);

		juegoMesaJug.setMinimumSize(new Dimension(0, 0));
		juegoMesaJug.setBounds(new Rectangle(0, (int) panelJuego.getSize().getHeight() / 3,
				(int) panelJuego.getSize().getWidth(),
				(int) panelJuego.getSize().getHeight() / 3));

		juegoMesaJug.setOpaque(false);
		juegoMesaJug.setLayout(null);

		juegoManoJug.setBounds(new Rectangle(0, 2 * (int) panelJuego.getSize().getHeight() / 3,
				(int) panelJuego.getSize().getWidth(),
				(int) panelJuego.getSize().getHeight() / 3));

		juegoManoJug.setOpaque(false);

		juegoManoJug.setLayout(null);

		botonChatPatino.addActionListener(new Interfaz_botonChatPatino_actionAdapter(this));
    botonSalir.addActionListener(new Interfaz_botonSalir_actionAdapter(this));
    botonPasarTurno.addActionListener(new Interfaz_botonPasarTurno_actionAdapter(this));
    this.getContentPane().add(panelEstado, null);
		this.getContentPane().add(jTabbedPane1, null);
		this.getContentPane().add(panelSuperior, null);

		//modificacion de skins segun el tipo de raza

		labelTablero.setBounds(new Rectangle(new Point(0, 0), Toolkit.getDefaultToolkit().getScreenSize()));

//***********************************************************
//******CHAT DE MARIA
//**************************************
		//fondos de madera de chat y marcador
		ImageIcon imagen = new ImageIcon("../imagenes/fondos/fondo.jpg");
		jLabel1.setIcon(imagen);
		jLabel1.setBounds(new Rectangle(new Point(0, 0), Toolkit.getDefaultToolkit().getScreenSize()));

		jLabel3.setIcon(imagen);
		jLabel3.setBounds(new Rectangle(new Point(0, 0), Toolkit.getDefaultToolkit().getScreenSize()));

		panelChat.setLayout(null);
		panelChat.setSize(Toolkit.getDefaultToolkit().getScreenSize());

		//***************************************************************
		//**********ZONA DE NUESTRO AVATAR*******************************
		//***************************************************************
		//inicializar el botón que cambia el avatar
		botonExplorador.setText("Cambiar Imagen");
		botonExplorador.setBounds(new Rectangle(unidadAncho * 76,
				unidadAlto * 34,
				unidadAncho * 20,
				unidadAlto * 2));
		botonExplorador.setFont(new java.awt.Font("Lucida Bright", Font.ITALIC,
				12));
		botonExplorador.setForeground(Color.green);
		botonExplorador.setBorderPainted(false);
		botonExplorador.setContentAreaFilled(false);
		//oyente del boton explorador
		botonExplorador.addMouseListener(new fondo_BotonExplorador_mouseAdapter(this));

		//inicializar la etiqueta donde esta el avatar
		jLabel11.setBounds(unidadAncho * 76, unidadAlto * 11, unidadAncho * 20,
				unidadAlto * 12);
		//redimensionar la imagen si es muy grande
		ImageIcon avatarIconAux = new ImageIcon();
		if (avatarIcon.getIconWidth() > jLabel11.getWidth()) {
			avatarIconAux = new ImageIcon(
					avatarIcon.getImage().getScaledInstance(jLabel11.getWidth(),
					-1, Image.SCALE_DEFAULT));
			avatarIcon = avatarIconAux;
		}
		//asignar la imagen a la etiqueta
		jLabel11.setIcon(avatarIcon);
		repaint();

		//***************************************************************
		//**********ZONA DEL AVATAR DEL CONTRARIO************************
		//***************************************************************
		//inicializar la etiqueta donde esta el avatar
		jLabel10.setBounds(unidadAncho * 79, unidadAlto * 25, unidadAncho * 14,
				unidadAlto * 9);
		//redimensionar la imagen si es muy grande
		ImageIcon avatarIconAux2 = new ImageIcon();
		if (avatarIcon.getIconWidth() > jLabel10.getWidth()) {
			avatarIconAux2 = new ImageIcon(
					avatarIcon.getImage().getScaledInstance(jLabel10.getWidth(),
					-1, Image.SCALE_DEFAULT));
			avatarIcon = avatarIconAux2;
		}
		//asignar la imagen a la etiqueta
		jLabel10.setIcon(avatarIcon);
		repaint();

		//inicializar el area de escritura
		areaEscritura.setBounds(unidadAncho * 0, unidadAlto * 0,
				unidadAncho * 60, unidadAlto * 4);
		//activar la opcion de partir las lineas si son muy largas
		areaEscritura.setLineWrap(true);
		//parte por los limites de palabras
		areaEscritura.setWrapStyleWord(true);
		//introducir el textArea en un scrollPane
		panelEscritura = new JScrollPane(areaEscritura);
		//panelEscritura.setVerticalScrollBarPolicy(
		//JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panelEscritura.setBounds(unidadAncho * 8, unidadAlto * 31,
				unidadAncho * 64, unidadAlto * 4);
		areaEscritura.addKeyListener(new Oyente(this));

		//***************************************************************
		//**********ZONA CHARLA DE USUARIOS******************************
		//***************************************************************
		//inicializa area de charla
		areaCharla.setBounds(unidadAncho * 0, unidadAlto * 0,
				unidadAncho * 60, unidadAlto * 17);
		//el area de charla no puede ser editado por el usuario
		areaCharla.setEditable(false);
		//activar la opcion de partir las lineas si son muy largas
		areaCharla.setLineWrap(true);
		//parte por los limites de palabras
		areaCharla.setWrapStyleWord(true);
		//inicializar el scrollPane
		panelCharla = new JScrollPane(areaCharla);
		//la barra baja automaticamente
		panelCharla.setAutoscrolls(true);
		panelCharla.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//panelCharla.setHorizontalScrollBarPolicy(
		//    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelCharla.setForeground(Color.black);
		//inicializar esquina inferior derecha
		JLabel etiquetaAux = new JLabel();
		ImageIcon iconoAux = new ImageIcon("../imagenes/chat/fondo.jpg");
		etiquetaAux.setIcon(iconoAux);
		panelCharla.setCorner(JScrollPane.LOWER_RIGHT_CORNER,
				etiquetaAux);
		//inicializar limites del panel de charla
		panelCharla.setBounds(unidadAncho * 8, unidadAlto * 11,
				unidadAncho * 64, unidadAlto * 19);
		panelCharla.setBorder(BorderFactory.createEtchedBorder(Color.black,
				Color.black));

		panelChat.add(botonExplorador, null);
		panelChat.add(jLabel10);
		panelChat.add(jLabel11);
		panelChat.add(panelCharla, BorderLayout.CENTER);
		panelChat.add(panelEscritura);
		panelChat.add(botonChatPatino);
//***********************************************************
//******CHAT DE MARIA
//**************************************


		//panelMarcador.setLayout(null);
		//panelMarcador.setSize(Toolkit.getDefaultToolkit().getScreenSize());

//*********************************
		botonPasarTurno.setBackground(Color.black);
		//botonSalir.setBounds(new Rectangle(100, 250, 100,50 ));
		botonPasarTurno.setBounds(new Rectangle(400, 0, 200, 25));
		botonPasarTurno.setFont(new java.awt.Font("Serif", 3, 25));
		botonPasarTurno.setForeground(Color.green);
		botonPasarTurno.setBorderPainted(false);
		botonPasarTurno.setContentAreaFilled(false);
		botonPasarTurno.setText("Pasar Turno");
		botonPasarTurno.addMouseListener(new Fondo_botonPasarTurno_mouseAdapter(this));

//**********************************

		botonSalir.setBackground(Color.black);
		//botonSalir.setBounds(new Rectangle(100, 250, 100,50 ));
		botonSalir.setBounds(new Rectangle(600, 0, 100, 25));
		botonSalir.setFont(new java.awt.Font("Serif", 3, 25));
		botonSalir.setForeground(Color.orange);
		botonSalir.setBorderPainted(false);
		botonSalir.setContentAreaFilled(false);
		botonSalir.setText("Salir");
		botonSalir.addMouseListener(new Fondo_botonSalir_mouseAdapter(this));
//**********************************
		turno.setBackground(Color.black);
		turno.setBounds(new Rectangle(0, 0, 400, 25));
		turno.setFont(new java.awt.Font("Serif", 3, 25));
		turno.setForeground(Color.red);
		turno.setText("Turno de bajada del jugador1");
//**********************************
		manas.setBackground(Color.black);
		manas.setBounds(new Rectangle(500, 0, 300, 25));
		manas.setFont(new java.awt.Font("Serif", 3, 25));
		manas.setForeground(Color.blue);

		manasGastados.setBackground(Color.black);
		manasGastados.setBounds(new Rectangle(750, 0, 300, 25));
		manasGastados.setFont(new java.awt.Font("Serif", 3, 25));
		manasGastados.setForeground(Color.red);
		manasGastados.setText("Mana Gastado " + this.partida.getMesa().getJugador1().getManaUsado());
//**********************************

		//Ojo!! prueba boton patino
		botonChatPatino.addMouseListener(new Fondo_botonChatPatino_mouseAdapter(this));
		botonChatPatino.setBounds(new Rectangle(unidadAncho * 76,
				unidadAlto * 5,
				150,
				50));
		botonChatPatino.setVisible(true);
		botonChatPatino.setBackground(Color.darkGray);
		botonChatPatino.setForeground(Color.red);
                botonChatPatino.setBorder(null);
		//botonChatPatino.setText("Iniciar");
//*************


		panelSuperior.setBounds(new Rectangle((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 700, 0,
				800, 25));
		panelSuperior.setLayout(null);
		panelSuperior.setBackground(Color.BLACK);

		panelEstado.setBounds(new Rectangle(0, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 30,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 30));
		panelEstado.setLayout(null);
		panelEstado.setBackground(Color.BLACK);

		ponTextoEstado(" ¡¡Bienvenido a Génesis!!");

		textoEstado.setBounds(new Rectangle(0, 0,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 500, 25));

		jTabbedPane1.add(panelJuego, "JUEGO");

		panelOpciones.setLayout(null);
		//DIANA
		panelOpciones.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		//DIANA
		jTabbedPane1.add(panelOpciones, "OPCIONES");
		//DIANA

		// jTabbedPane1.add(panelMarcador,    "MARCADOR");
		jTabbedPane1.add(panelChat, "CONEXIÓN");

		jTabbedPane1.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 20,
				(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 35);
		jTabbedPane1.setBackground(Color.white);

		panelJuego.add(juegoMesaOp, null);
		panelJuego.add(juegoMesaJug, null);
		panelJuego.add(juegoManoJug, null);
		panelJuego.add(labelTablero, null);

		panelChat.add(jLabel3, null);
		panelChat.add(jLabel4, null);
		panelChat.add(jLabel7, null);

		panelSuperior.add(botonSalir, null);
		panelSuperior.add(botonPasarTurno, null);
		panelSuperior.add(turno, null);
		panelEstado.add(textoEstado, null);
		panelEstado.add(manas, null);
		panelEstado.add(manasGastados, null);

		// panelMarcador.add(jLabel6, null);
		//panelMarcador.add(jLabel5, null);
		//panelMarcador.add(jLabel1, null);
//    this.getContentPane().add(panelSuperior, null);




	}


	/**
	 *  Description of the Method
	 *
	 *@param  tablero  Description of Parameter
	 */
	private void preguntaJuegoRed(Interfaz tablero) {
		//si estamos en juego en red, mostramos login
		boolean juegoRed = true;
		if (juegoRed) {
			gestorUsuarios = new GestorUsuarios();
			controlador = new Controlador(gestorUsuarios, usuario, this, tablero);
                        this.inhabilitaPanel();
                        this.repaint();
                        this.getContentPane().add(new GUI(controlador, this),0);
			//ventana = new GUI(controlador, this);
			//bug! con esto quizas sea posible crear chat en monojugador. anadir atribito
			//en clase interfaz para preguntar si es multijugador o no.
			//tablero.setJuegoRed(true);
			//this.usuario.setNombreUsuario(controlador.getUsuario().getNombreUsuario());
		}
	}

  void botonChatPatino_actionPerformed(ActionEvent e) {
    preguntaJuegoRed(this);
  }

  public void botonSalir_actionPerformed(ActionEvent e) {
		System.gc();
		System.runFinalization();
		padre.setEnabled(true);
		padre.show();
		
		this.dispose();
		this.getPartida().getHilo().stop();
		padre.show();
  }

  void botonPasarTurno_actionPerformed(ActionEvent e) {
    if (getPartida().getTurnoPartida() == 5) {
                        ponTextoEstado("Asocie defensores a todos los atacantes");
                }
                else {
                        this.getPartida().pasaTurnoPartida("jugador1");
                }

  }

}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class Fondo_botonChatPatino_mouseAdapter extends java.awt.event.MouseAdapter {


	Interfaz adaptee;


	/**
	 *  Constructor for the Fondo_botonChatPatino_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	Fondo_botonChatPatino_mouseAdapter(Interfaz adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	/*public void mouseClicked(MouseEvent e) {
		adaptee.botonChatPatino_mouseClicked(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botonChatPatino_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botonChatPatino_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class Fondo_botonSalir_mouseAdapter extends java.awt.event.MouseAdapter {


	Interfaz adaptee;


	/**
	 *  Constructor for the Fondo_botonSalir_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	Fondo_botonSalir_mouseAdapter(Interfaz adaptee) {
		this.adaptee = adaptee;
	}




	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botonSalir_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botonSalir_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class Fondo_botonPasarTurno_mouseAdapter extends java.awt.event.MouseAdapter {


	Interfaz adaptee;


	/**
	 *  Constructor for the Fondo_botonPasarTurno_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	Fondo_botonPasarTurno_mouseAdapter(Interfaz adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botonPasarTurno_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botonPasarTurno_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class fondo_BotonExplorador_mouseAdapter extends java.awt.event.MouseAdapter {


	Interfaz adaptee;


	/**
	 *  Constructor for the fondo_BotonExplorador_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	fondo_BotonExplorador_mouseAdapter(Interfaz adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseClicked(MouseEvent e) {
		adaptee.BotonExplorador_mouseClicked(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.BotonExplorador_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.BotonExplorador_mouseExited(e);
	}
}

/**
 *  Clase para escribir en el areaCharla cuando pulsamos intro
 *
 *@author    Chris Seguin
 */
class Oyente extends KeyAdapter {


	Interfaz miFondo;
	int cont;


	/**
	 *  Constructor for the Oyente object
	 *
	 *@param  f  la interfaz donde esta el boton propietario de este oyente
	 */
	public Oyente(Interfaz f) {
		miFondo = f;
	}


	/**
	 *  acciones que ocurren cuando se pulsa intro en el area de texto del chat
	 *
	 *@param  e  evento
	 */
	public void keyPressed(KeyEvent e) {
		try {
			if ((e.getKeyCode() == KeyEvent.VK_ENTER) && (!(miFondo.getAreaEscritura().getText()).equals(""))) {
				// miFondo.activaPrivado = true;
				miFondo.actualizar();
			}
		}
		catch (Exception e1) {
			System.err.println("error en keypressed de Interfaz.java");
			e1.printStackTrace();
		}
	}
}

//Clase que permite poner una imagen como fondo de un JTextArea
/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class MiTextArea extends JTextArea {


	private ImageIcon im;


	/**
	 *  Constructor for the MiTextArea object
	 *
	 *@param  imagen  Description of Parameter
	 */
	public MiTextArea(String imagen) {
		im = new ImageIcon(imagen);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  g  Description of Parameter
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
				0.3F);
		g2.setComposite(ac);
		g2.clipRect(0, 0, this.getWidth(), this.getHeight());
		g2.scale(2, 2);
		g2.drawImage(im.getImage(), 0, 0, im.getIconWidth(), im.getIconHeight(), null);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  g  Description of Parameter
	 */
	public void update(Graphics g) {
		super.update(g);
	}
}

//Clase que permite poner una imagen como fondo de un JLabel
/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class MiJLabel extends JLabel {


	private ImageIcon im;


	/**
	 *  Constructor for the MiJLabel object
	 *
	 *@param  imagen  Description of Parameter
	 */
	public MiJLabel(String imagen) {
		im = new ImageIcon(imagen);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  g  Description of Parameter
	 */
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
				0.3F);
		g2.setComposite(ac);
		g2.clipRect(0, 0, this.getWidth(), this.getHeight());
		g2.scale(2, 2);
		g2.drawImage(im.getImage(), 0, 0, im.getIconWidth(), im.getIconHeight(), null);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  g  Description of Parameter
	 */
	public void update(Graphics g) {
		super.update(g);
	}
}

class Interfaz_botonChatPatino_actionAdapter implements java.awt.event.ActionListener {
  Interfaz adaptee;

  Interfaz_botonChatPatino_actionAdapter(Interfaz adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botonChatPatino_actionPerformed(e);
  }
}

class Interfaz_botonSalir_actionAdapter implements java.awt.event.ActionListener {
  Interfaz adaptee;

  Interfaz_botonSalir_actionAdapter(Interfaz adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botonSalir_actionPerformed(e);
  }
}

class Interfaz_botonPasarTurno_actionAdapter implements java.awt.event.ActionListener {
  Interfaz adaptee;

  Interfaz_botonPasarTurno_actionAdapter(Interfaz adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botonPasarTurno_actionPerformed(e);
  }
}
