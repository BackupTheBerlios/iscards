package panelesInfo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import padrePaneles.*;

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
public class PanelGenerico extends Container {

	/**
	 *  Description of the Field
	 */
	protected int alto = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	/**
	 *  Description of the Field
	 */
	protected int ancho = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	JLabel labelFondo = new JLabel();

	JButton botonAceptar = new JButton();
	PadrePaneles panelPadre;
	String Imagen;


	/**
	 *  Constructor for the PanelGenerico object
	 *
	 *@param  imagen  Description of Parameter
	 *@param  padre   Description of Parameter
	 */
	public PanelGenerico(String imagen, PadrePaneles padre) {
		try {

			Imagen = imagen;
			panelPadre = padre;
			ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
			Image image = cursor.getImage();
			Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "img");
			this.setCursor(puntero);

			jbInit();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	/*
	 *  Función abstracta para el action performed del botón Aceptar
	 *  @param e
	 */
	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonAceptar_actionPerformed(ActionEvent e) {

		this.setVisible(false);
		if (panelPadre != null) {
			panelPadre.habilitaPanel();
		}
	}



	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonAceptar_mouseEntered(MouseEvent e) {
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
	void botonAceptar_mouseExited(MouseEvent e) {
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

		this.setLayout(null);
		this.setSize(700, 500);
		this.setLocation(0, 0);

		//limites de componentes
		labelFondo.setBounds(new Rectangle((int) (ancho / 3.5), 2 * (alto / 6), ancho / 2, alto / 4));

		botonAceptar.setBounds(new Rectangle((int) (2.4 * (ancho / 5)), (int) (1.9 * (alto / 4)), (int) (ancho / 10.2), alto / 25));
		botonAceptar.setBorder(null);

		//imagenes de componentes
		labelFondo.setIcon(new ImageIcon(Imagen));

		botonAceptar.setIcon(new ImageIcon("../imagenes/botonaceptar.jpg"));
		botonAceptar.addMouseListener(new PanelGenerico_botonAceptar_mouseAdapter(this));

		//agregar componentes al panel

		this.add(botonAceptar, null);
		this.add(labelFondo, null);
		this.setBackground(SystemColor.menuText);

		//acciones de botones
		botonAceptar.addActionListener(new PanelGenerico_botonAceptar_actionAdapter(this));


	}



}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class PanelGenerico_botonAceptar_actionAdapter implements java.awt.event.ActionListener {


	private PanelGenerico adaptee;


	/**
	 *  Constructor for the PanelGenerico_botonAceptar_actionAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	PanelGenerico_botonAceptar_actionAdapter(PanelGenerico adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void actionPerformed(ActionEvent e) {
		adaptee.botonAceptar_actionPerformed(e);
	}
}


/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class PanelGenerico_botonAceptar_mouseAdapter extends java.awt.event.MouseAdapter {


	PanelGenerico adaptee;


	/**
	 *  Constructor for the PanelGenerico_botonAceptar_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	PanelGenerico_botonAceptar_mouseAdapter(PanelGenerico adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botonAceptar_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botonAceptar_mouseExited(e);
	}
}

