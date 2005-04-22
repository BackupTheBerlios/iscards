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
public class PanelSalir extends Container {
	/*
	 *  private JPanel jPanel1 = new JPanel();
	 *  private JLabel panelSalirFondo = new JLabel();
	 *  private JLabel jLabel1 = new JLabel();
	 *  private JLabel jLabel2 = new JLabel();
	 *  private JButton jButton1 = new JButton("SI");
	 *  private JButton jButton2 = new JButton("NO");
	 */
	/**
	 *  Description of the Field
	 */
	protected int alto = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	/**
	 *  Description of the Field
	 */
	protected int ancho = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	JLabel labelFondo = new JLabel();
	JButton botonCancelar = new JButton();
	JButton botonAceptar = new JButton();
	PadrePaneles panelPadre;



	//  JTextField textNombre = new JTextField();



// int resActual=Toolkit.getDefaultToolkit().getScreenResolution();
// int resProg=1000;

	/**
	 *  Constructor for the PanelSalir object
	 *
	 *@param  padre  Description of Parameter
	 */
	public PanelSalir(PadrePaneles padre) {
		try {

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
		System.exit(0);
	}


	/**
	 *  Función abstracta para el action performed del botón Cancelar
	 *
	 *@param  e
	 */
	void botonCancelar_actionPerformed(ActionEvent e) {

//     this.hide();
		this.setVisible(false);
		panelPadre.habilitaPanel();

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
	 *@param  e  Description of Parameter
	 */
	void botonCancelar_mouseEntered(MouseEvent e) {
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
	void botonCancelar_mouseExited(MouseEvent e) {
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
		botonCancelar.setBounds(new Rectangle((int) (2.7 * (ancho / 5)), (int) (1.9 * (alto / 4)), (int) (ancho / 10.2), alto / 25));
		botonCancelar.setBorder(null);
		//botonAceptar.setBounds(new Rectangle(0, 0, 100, 30));
		botonAceptar.setBounds(new Rectangle((int) (2.15 * (ancho / 5)), (int) (1.9 * (alto / 4)), (int) (ancho / 10.2), alto / 25));
		botonAceptar.setBorder(null);

		//imagenes de componentes
		labelFondo.setIcon(new ImageIcon("../imagenes/panelSalir.jpg"));
		botonCancelar.setIcon(new ImageIcon("../imagenes/botoncancelar.jpg"));
		botonCancelar.addMouseListener(new PanelSalir_botonCancelar_mouseAdapter(this));
		botonAceptar.setIcon(new ImageIcon("../imagenes/botonaceptar.jpg"));
		botonAceptar.addMouseListener(new PanelSalir_botonAceptar_mouseAdapter(this));

		//agregar componentes al panel
		this.add(botonCancelar, null);
		this.add(botonAceptar, null);
		this.add(labelFondo, null);
		this.setBackground(SystemColor.menuText);

		//acciones de botones
		botonAceptar.addActionListener(new PanelSalir_botonAceptar_actionAdapter(this));
		botonCancelar.addActionListener(new PanelSalir_botonCancelar_actionAdapter(this));


	}

}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class PanelSalir_botonAceptar_actionAdapter implements java.awt.event.ActionListener {


	private PanelSalir adaptee;


	/**
	 *  Constructor for the PanelSalir_botonAceptar_actionAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	PanelSalir_botonAceptar_actionAdapter(PanelSalir adaptee) {
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
class PanelSalir_botonCancelar_actionAdapter implements java.awt.event.ActionListener {


	private PanelSalir adaptee;


	/**
	 *  Constructor for the PanelSalir_botonCancelar_actionAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	PanelSalir_botonCancelar_actionAdapter(PanelSalir adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void actionPerformed(ActionEvent e) {
		adaptee.botonCancelar_actionPerformed(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class PanelSalir_botonAceptar_mouseAdapter extends java.awt.event.MouseAdapter {


	PanelSalir adaptee;


	/**
	 *  Constructor for the PanelSalir_botonAceptar_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	PanelSalir_botonAceptar_mouseAdapter(PanelSalir adaptee) {
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

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class PanelSalir_botonCancelar_mouseAdapter extends java.awt.event.MouseAdapter {


	PanelSalir adaptee;


	/**
	 *  Constructor for the PanelSalir_botonCancelar_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	PanelSalir_botonCancelar_mouseAdapter(PanelSalir adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botonCancelar_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botonCancelar_mouseExited(e);
	}
}
