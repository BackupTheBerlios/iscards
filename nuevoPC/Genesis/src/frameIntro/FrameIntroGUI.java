package frameIntro;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;
import padrePaneles.*;
import panelesInfo.*;

/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: Interfaz Gráfico del frame de inicio</p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Rui Miguel
 *@version    1.0
 */

public abstract class FrameIntroGUI extends PadrePaneles {
	/**
	 *  Description of the Field
	 */
	protected int alto = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	/**
	 *  Description of the Field
	 */
	protected int ancho = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

	JPanel contentPane;
	private JPanel panelPrincipal = new JPanel();
	private JPanel panelBotones = new JPanel();
	private JButton boton1Jugador = new JButton();
	private JButton botonJuegoRed = new JButton();
	private JButton botonEditar = new JButton();
	private JButton botonDemo = new JButton();
	private JButton botonReglas = new JButton();
	private JButton botonAyuda = new JButton();
	private JButton botonRecibir = new JButton();
	private JButton botonEnviar = new JButton();
	private JButton botonDescargaSobre = new JButton();
	private JButton botonSalir = new JButton();
	private JLabel labelFondo = new JLabel();
	private JLabel labelDibujo = new JLabel();


	/**
	 *  Constructora de la clase
	 */
	public FrameIntroGUI() {
		try {
			jbInit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 *  Description of the Method
	 */
	public void habilitaPanel() {
		// contentPane.setEnabled(true);
		panelPrincipal.setEnabled(true);
		panelBotones.setEnabled(true);
		boton1Jugador.setEnabled(true);
		botonJuegoRed.setEnabled(true);
		botonEditar.setEnabled(true);
		botonDemo.setEnabled(true);
		botonReglas.setEnabled(true);
		botonAyuda.setEnabled(true);
		botonRecibir.setEnabled(true);
		botonEnviar.setEnabled(true);
		botonDescargaSobre.setEnabled(true);
		botonSalir.setEnabled(true);
		labelFondo.setEnabled(true);
		labelDibujo.setEnabled(true);

	}


	/**
	 *  Description of the Method
	 */
	public void inhabilitaPanel() {
		// contentPane.setEnabled(false);
		panelPrincipal.setEnabled(false);
		panelBotones.setEnabled(false);
		boton1Jugador.setEnabled(false);
		botonJuegoRed.setEnabled(false);
		botonEditar.setEnabled(false);
		botonDemo.setEnabled(false);
		botonReglas.setEnabled(false);
		botonAyuda.setEnabled(false);
		botonRecibir.setEnabled(false);
		botonEnviar.setEnabled(false);
		botonDescargaSobre.setEnabled(false);
		botonSalir.setEnabled(false);
		labelFondo.setEnabled(false);
		labelDibujo.setEnabled(false);

	}



	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonDescargaSobre_mouseEntered(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

		//boton1Jugador.setForeground(Color.white);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonDescargaSobre_mouseExited(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

		// boton1Jugador.setForeground(Color.orange);
	}



	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void boton1Jugador_mouseEntered(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

		//boton1Jugador.setForeground(Color.white);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void boton1Jugador_mouseExited(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

		// boton1Jugador.setForeground(Color.orange);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonJuegoRed_mouseEntered(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonJuegoRed_mouseExited(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonDemo_mouseEntered(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonDemo_mouseExited(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonAyuda_mouseEntered(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonAyuda_mouseExited(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonEditar_mouseEntered(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonEditar_mouseExited(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonReglas_mouseEntered(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonReglas_mouseExited(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonRecibir_mouseEntered(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonRecibir_mouseExited(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonEnviar_mouseEntered(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonEnviar_mouseExited(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonSalir_mouseEntered(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonSalir_mouseExited(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	abstract void boton1Jugador_actionPerformed(ActionEvent e);


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	abstract void botonJuegoRed_actionPerformed(ActionEvent e);


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	abstract void botonDemo_actionPerformed(ActionEvent e);


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	abstract void botonAyuda_actionPerformed(ActionEvent e);


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	abstract void botonEditar_actionPerformed(ActionEvent e);


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	abstract void botonReglas_actionPerformed(ActionEvent e);


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	abstract void botonEnviar_actionPerformed(ActionEvent e);


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	abstract void botonRecibir_actionPerformed(ActionEvent e);


	/**
	 *  Función abstracta para controlar el action performed del botón Salir
	 *
	 *@param  e
	 */
	void botonSalir_actionPerformed(ActionEvent e) {
		inhabilitaPanel();
		System.gc();
		this.repaint();
		PanelSalir panelsalir = new PanelSalir(this);
		this.getContentPane().add(panelsalir, 0);
	}


	/**
	 *  Función de inicialización de los componentes
	 *
	 *@exception  Exception         Description of Exception
	 *@throws  java.lang.Exception
	 */
	private void jbInit() throws Exception {
		//dibujamos el cursor
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

		//adaptamos el frame del tablero al tamaño máximo de la pantalla (con los SplitPane no se puede hacer de momento porque se desajusta)
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height);

		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(null);

		boton1Jugador.setBorder(null);
		botonJuegoRed.setBorder(null);
		botonDemo.setBorder(null);
		botonEditar.setBorder(null);
		botonReglas.setBorder(null);
		botonAyuda.setBorder(null);
		botonRecibir.setBorder(null);
		botonEnviar.setBorder(null);
		botonSalir.setBorder(null);

		labelDibujo.setBackground(Color.red);
		botonDescargaSobre.addMouseListener(new botonDescargaSobre_mouseAdapter(this));
		botonDescargaSobre.setIcon(new ImageIcon("../imagenes/DescargaSobre.jpg"));
		botonDescargaSobre.setBounds(new java.awt.Rectangle(0, 0, 0, 0));
		botonDescargaSobre.setBackground(Color.black);
		botonDescargaSobre.setBorder(null);
		contentPane.add(panelBotones, null);
		contentPane.add(panelPrincipal, null);

		//quitamos la barra de titulo
		this.setUndecorated(true);
		this.setResizable(false);

		ImageIcon dibPrincipal = new ImageIcon("../imagenes/DibujoPortada.gif");
		labelDibujo.setIcon(dibPrincipal);
		labelDibujo.setBounds(new Rectangle((int) (ancho / 10), (int) (alto / 3.5), 8 * (ancho / 10), (alto / 2)));

		ImageIcon fondo = new ImageIcon("../imagenes/fondos/fondo.jpg");
		labelFondo.setIcon(fondo);
		labelFondo.setBounds(new Rectangle(new Point(0, 0), Toolkit.getDefaultToolkit().getScreenSize()));


		panelPrincipal.setLayout(null);
		panelPrincipal.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		panelPrincipal.add(labelDibujo, null);
		panelPrincipal.add(labelFondo, null);


		//añadimos los botones

		boton1Jugador.setBounds(new Rectangle(ancho / 9, 0, (int) (ancho / 6.85), (int) (alto / 27)));
		boton1Jugador.setIcon(new ImageIcon("../imagenes/botonUnJugador.jpg"));
		boton1Jugador.addActionListener(new FrameIntroGUI_boton1Jugador_actionAdapter(this));
		boton1Jugador.addMouseListener(new boton1Jugador_mouseAdapter(this));

		botonJuegoRed.setBounds(new Rectangle(8 * (ancho / 49) + (ancho / 9), 0, (int) (ancho / 6.85), (int) (alto / 27)));
		botonJuegoRed.setIcon(new ImageIcon("../imagenes/botonJuegoEnRed.jpg"));
		botonJuegoRed.addActionListener(new FrameIntroGUI_botonJuegoRed_actionAdapter(this));
		botonJuegoRed.addMouseListener(new botonJuegoRed_mouseAdapter(this));

		botonDemo.setBounds(new Rectangle(16 * (ancho / 49) + (ancho / 9), 0, (int) (ancho / 6.85), (int) (alto / 27)));
		botonDemo.setIcon(new ImageIcon("../imagenes/botonDemo.jpg"));
		botonDemo.addActionListener(new FrameIntroGUI_botonDemo_actionAdapter(this));
		botonDemo.addMouseListener(new botonDemo_mouseAdapter(this));

		botonEditar.setBounds(new Rectangle(24 * (ancho / 49) + (ancho / 9), 0, (int) (ancho / 6.85), (int) (alto / 27)));
		botonEditar.setIcon(new ImageIcon("../imagenes/botonEditar.jpg"));
		botonEditar.addActionListener(new FrameIntroGUI_botonEditar_actionAdapter(this));
		botonEditar.addMouseListener(new botonEditar_mouseAdapter(this));

		botonReglas.setBounds(new Rectangle(32 * (ancho / 49) + (ancho / 9), 0, (int) (ancho / 6.85), (int) (alto / 27)));
		botonReglas.setIcon(new ImageIcon("../imagenes/botonReglas.jpg"));
		botonReglas.addActionListener(new FrameIntroGUI_botonReglas_actionAdapter(this));
		botonReglas.addMouseListener(new botonReglas_mouseAdapter(this));

		botonAyuda.setBounds(new Rectangle(ancho / 9 + 50, (alto / 20), (int) (ancho / 6.85), (int) (alto / 27)));
		botonAyuda.setIcon(new ImageIcon("../imagenes/botonAyuda.jpg"));
		botonAyuda.addActionListener(new FrameIntroGUI_botonAyuda_actionAdapter(this));
		botonAyuda.addMouseListener(new botonAyuda_mouseAdapter(this));

		botonRecibir.setBounds(new Rectangle(8 * (ancho / 49) + (ancho / 9) + 50, alto / 20, (int) (ancho / 6.85), (int) (alto / 27)));
		botonRecibir.setIcon(new ImageIcon("../imagenes/botonRecibir.jpg"));
		botonRecibir.addActionListener(new FrameIntroGUI_botonRecibir_actionAdapter(this));
		botonRecibir.addMouseListener(new botonRecibir_mouseAdapter(this));

		botonEnviar.setBounds(new Rectangle(16 * (ancho / 49) + (ancho / 9) + 50, alto / 20, (int) (ancho / 6.85), (int) (alto / 27)));
		botonEnviar.setIcon(new ImageIcon("../imagenes/botonEnviar.jpg"));
		botonEnviar.addActionListener(new FrameIntroGUI_botonEnviar_actionAdapter(this));
		botonEnviar.addMouseListener(new botonEnviar_mouseAdapter(this));

		botonDescargaSobre.setBounds(24 * (ancho / 49) + (ancho / 9) + 50, alto / 20, (int) (ancho / 5.5), (int) (alto / 27));
		botonDescargaSobre.setIcon(new ImageIcon("../imagenes/DescargaSobre.jpg"));
		botonDescargaSobre.addMouseListener(new botonDescargaSobre_mouseAdapter(this));

		botonSalir.setBounds(new Rectangle(16 * (ancho / 49) + (ancho / 9), alto / 10, (int) (ancho / 6.85), (int) (alto / 27)));
		botonSalir.setIcon(new ImageIcon("../imagenes/botonSalir.jpg"));
		botonSalir.addActionListener(new FrameIntroGUI_botonSalir_actionAdapter(this));
		botonSalir.addMouseListener(new botonSalir_mouseAdapter(this));

		panelBotones.setBounds(new Rectangle(0, (int) (3.2 * (alto / 4)), (int) ancho, (int) (alto - (3.2 * (alto / 4)))));
		panelBotones.setLayout(null);
		panelBotones.setBackground(Color.BLACK);

		panelBotones.setOpaque(false);
		panelBotones.setVisible(true);

		panelBotones.add(boton1Jugador, null);
		panelBotones.add(botonJuegoRed, null);
		panelBotones.add(botonDemo, null);
		panelBotones.add(botonEditar, null);
		panelBotones.add(botonReglas, null);
		panelBotones.add(botonAyuda, null);
		panelBotones.add(botonRecibir, null);
		panelBotones.add(botonEnviar, null);
		panelBotones.add(botonDescargaSobre, null);
		panelBotones.add(botonSalir, null);

	}
}

//**************************************************
//*************************************************
//**************************************************

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class boton1Jugador_mouseAdapter extends java.awt.event.MouseAdapter {


	FrameIntroGUI adaptee;


	/**
	 *  Constructor for the boton1Jugador_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	boton1Jugador_mouseAdapter(FrameIntroGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.boton1Jugador_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.boton1Jugador_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class botonJuegoRed_mouseAdapter extends java.awt.event.MouseAdapter {


	FrameIntroGUI adaptee;


	/**
	 *  Constructor for the botonJuegoRed_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	botonJuegoRed_mouseAdapter(FrameIntroGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botonJuegoRed_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botonJuegoRed_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class botonDemo_mouseAdapter extends java.awt.event.MouseAdapter {


	FrameIntroGUI adaptee;


	/**
	 *  Constructor for the botonDemo_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	botonDemo_mouseAdapter(FrameIntroGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botonDemo_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botonDemo_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class botonEditar_mouseAdapter extends java.awt.event.MouseAdapter {


	FrameIntroGUI adaptee;


	/**
	 *  Constructor for the botonEditar_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	botonEditar_mouseAdapter(FrameIntroGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botonEditar_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botonEditar_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class botonAyuda_mouseAdapter extends java.awt.event.MouseAdapter {


	FrameIntroGUI adaptee;


	/**
	 *  Constructor for the botonAyuda_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	botonAyuda_mouseAdapter(FrameIntroGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botonAyuda_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botonAyuda_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class botonReglas_mouseAdapter extends java.awt.event.MouseAdapter {


	FrameIntroGUI adaptee;


	/**
	 *  Constructor for the botonReglas_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	botonReglas_mouseAdapter(FrameIntroGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botonReglas_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botonReglas_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class botonRecibir_mouseAdapter extends java.awt.event.MouseAdapter {


	FrameIntroGUI adaptee;


	/**
	 *  Constructor for the botonRecibir_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	botonRecibir_mouseAdapter(FrameIntroGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botonRecibir_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botonRecibir_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class botonEnviar_mouseAdapter extends java.awt.event.MouseAdapter {


	FrameIntroGUI adaptee;


	/**
	 *  Constructor for the botonEnviar_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	botonEnviar_mouseAdapter(FrameIntroGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botonEnviar_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botonEnviar_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class botonSalir_mouseAdapter extends java.awt.event.MouseAdapter {


	FrameIntroGUI adaptee;


	/**
	 *  Constructor for the botonSalir_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	botonSalir_mouseAdapter(FrameIntroGUI adaptee) {
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
class botonDescargaSobre_mouseAdapter extends java.awt.event.MouseAdapter {


	FrameIntroGUI adaptee;


	/**
	 *  Constructor for the botonDescargaSobre_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	botonDescargaSobre_mouseAdapter(FrameIntroGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botonDescargaSobre_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botonDescargaSobre_mouseExited(e);
	}
}
