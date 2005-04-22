package panelesInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import coleccion.Coleccion;
import usuario.Usuario;
import editor.*;
import padrePaneles.*;


/**
 *  <p>
 *
 *  Título: </p> <p>
 *
 *  Descripción: </p> <p>
 *
 *  Copyright: Copyright (c) 2005</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     sin atribuir
 *@version    1.0
 */

public class EligeRazaGUI extends JFrame {

	/**
	 *  Description of the Field
	 */
	protected int alto = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	/**
	 *  Description of the Field
	 */
	protected int ancho = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

	private JLabel labelFondo = new JLabel();
	private JButton botAngeles = new JButton(new ImageIcon("../imagenes/EligeRaza/botonangeles.jpg"));
	private JButton botDemonios = new JButton(new ImageIcon("../imagenes/EligeRaza/botondemonios.jpg"));
	private JButton botHumanos = new JButton(new ImageIcon("../imagenes/EligeRaza/botonhumanos.jpg"));
	private JButton botInmortales = new JButton(new ImageIcon("../imagenes/EligeRaza/botoninmortales.jpg"));
	private int eleccion;
	private Coleccion colec;
	private Usuario usu;
	private JFrame padre;


	/**
	 *  Constructor for the EligeRazaGUI object
	 *
	 *@param  coleccion  Description of Parameter
	 *@param  usuario    Description of Parameter
	 */
	public EligeRazaGUI(Coleccion coleccion, Usuario usuario,JFrame padre) {
		try {
			this.padre=padre;
			padre.setEnabled(false);
			this.setUndecorated(true);
			this.setBounds(0, 0, ancho, alto);
			colec = coleccion;
			usu = usuario;
			jbInit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botAngeles_mouseClicked(MouseEvent e) {
		EditorBarajasImp editor = new EditorBarajasImp(padre, colec, usu, 0);
		editor.show();
		this.hide();
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botDemonios_mouseClicked(MouseEvent e) {
		EditorBarajasImp editor = new EditorBarajasImp(padre, colec, usu, 1);
		editor.show();
		this.hide();

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botHumanos_mouseClicked(MouseEvent e) {
		EditorBarajasImp editor = new EditorBarajasImp(padre, colec, usu, 2);
		editor.show();
		this.hide();
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botInmortales_mouseClicked(MouseEvent e) {
		EditorBarajasImp editor = new EditorBarajasImp(padre, colec, usu, 3);
		editor.show();
		this.hide();
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botAngeles_mouseEntered(MouseEvent e) {
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
	void botAngeles_mouseExited(MouseEvent e) {
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
	void botDemonios_mouseEntered(MouseEvent e) {
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
	void botDemonios_mouseExited(MouseEvent e) {
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
	void botHumanos_mouseEntered(MouseEvent e) {
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
	void botHumanos_mouseExited(MouseEvent e) {
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
	void botInmortales_mouseEntered(MouseEvent e) {
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
	void botInmortales_mouseExited(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@exception  Exception  Description of Exception
	 */
	private void jbInit() throws Exception {

		labelFondo.setBackground(Color.black);

		labelFondo.setIcon(new ImageIcon("../imagenes/EligeRaza/fondoEligeRaza.jpg"));
		labelFondo.setOpaque(true);
		labelFondo.setBounds(new Rectangle(0, 0, ancho, alto));

		botAngeles.setBounds(ancho / 7, 3 * (alto / 7), (int) (ancho / 3.7), (int) (alto / 16.5));

		botDemonios.setBounds(3 * (ancho / 5), 3 * (alto / 7), (int) (ancho / 3.7), (int) (alto / 16.5));

		botHumanos.setBounds(ancho / 7, 6 * (alto / 7), (int) (ancho / 3.7), (int) (alto / 16.5));

		botInmortales.setBounds(3 * (ancho / 5), 6 * (alto / 7), (int) (ancho / 3.7), (int) (alto / 16.5));


		this.getContentPane().setLayout(null);
		this.getContentPane().setLayout(null);

		botAngeles.setBorder(null);
		botAngeles.addMouseListener(new EligeRazaGUI_botAngeles_mouseAdapter(this));

		botDemonios.addMouseListener(new EligeRazaGUI_botDemonios_mouseAdapter(this));
		botDemonios.setBorder(null);

		botHumanos.setBorder(null);
		botHumanos.addMouseListener(new EligeRazaGUI_botHumanos_mouseAdapter(this));

		botInmortales.setBorder(null);
		botInmortales.addMouseListener(new EligeRazaGUI_botInmortales_mouseAdapter(this));
		this.getContentPane().setLayout(null);
		this.getContentPane().add(botAngeles, null);
		this.getContentPane().add(botDemonios, null);
		this.getContentPane().add(botHumanos, null);
		this.getContentPane().add(botInmortales, null);

		this.getContentPane().add(labelFondo, null);

	}





}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class EligeRazaGUI_botAngeles_mouseAdapter extends java.awt.event.MouseAdapter {


	EligeRazaGUI adaptee;


	/**
	 *  Constructor for the EligeRazaGUI_botAngeles_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	EligeRazaGUI_botAngeles_mouseAdapter(EligeRazaGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseClicked(MouseEvent e) {
		adaptee.botAngeles_mouseClicked(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botAngeles_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botAngeles_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class EligeRazaGUI_botDemonios_mouseAdapter extends java.awt.event.MouseAdapter {


	EligeRazaGUI adaptee;


	/**
	 *  Constructor for the EligeRazaGUI_botDemonios_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	EligeRazaGUI_botDemonios_mouseAdapter(EligeRazaGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseClicked(MouseEvent e) {
		adaptee.botDemonios_mouseClicked(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botDemonios_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botDemonios_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class EligeRazaGUI_botHumanos_mouseAdapter extends java.awt.event.MouseAdapter {


	EligeRazaGUI adaptee;


	/**
	 *  Constructor for the EligeRazaGUI_botHumanos_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	EligeRazaGUI_botHumanos_mouseAdapter(EligeRazaGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseClicked(MouseEvent e) {
		adaptee.botHumanos_mouseClicked(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botHumanos_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botHumanos_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class EligeRazaGUI_botInmortales_mouseAdapter extends java.awt.event.MouseAdapter {


	EligeRazaGUI adaptee;


	/**
	 *  Constructor for the EligeRazaGUI_botInmortales_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	EligeRazaGUI_botInmortales_mouseAdapter(EligeRazaGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseClicked(MouseEvent e) {
		adaptee.botInmortales_mouseClicked(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botInmortales_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botInmortales_mouseExited(e);
	}
}
