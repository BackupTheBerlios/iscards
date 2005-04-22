package panelesInfo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


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

public class AyudaGUI extends JFrame {

	/**
	 *  Description of the Field
	 */
	protected int alto = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	/**
	 *  Description of the Field
	 */
	protected int ancho = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();

	private JPanel panel2 = new JPanel();
	private JLabel labelDibujo = new JLabel();
	private JButton botonAcepta = new JButton();
	private JPanel panelAyuda = new JPanel();
	private JEditorPane ayuda = new JEditorPane();
	private JTextPane texto = new JTextPane();


	/**
	 *  Constructor for the AyudaGUI object
	 */
	public AyudaGUI() {

		try {
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
	void botonAcepta_mouseEntered(MouseEvent e) {

		ImageIcon cursor = new ImageIcon("./imagenes/cursores/punteroAct.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonAcepta_mouseExited(MouseEvent e) {

		ImageIcon cursor = new ImageIcon("./imagenes/cursores/puntero.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonAcepta_mouseClicked(MouseEvent e) {
		this.hide();
	}


	/**
	 *  Description of the Method
	 *
	 *@exception  Exception  Description of Exception
	 */
	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setBounds(0, 0, ancho, alto);
		panel2.setLayout(null);
		panel2.setBounds(0, 0, ancho, alto);
		panel2.setBackground(Color.BLACK);

		labelDibujo.setBounds(ancho / 100, 0, ancho, alto);
		labelDibujo.setIcon(new ImageIcon("./imagenes/fondos/fondo2.jpg"));

		botonAcepta.setIcon(new ImageIcon("./imagenes/botonaceptar.jpg"));
		botonAcepta.addMouseListener(new botonAcepta_mouseAdapter(this));
		botonAcepta.setBounds((int) (3.5 * (ancho / 8)), 9 * (alto / 10), (int) (ancho / 10.2), alto / 25);
		botonAcepta.setBackground(Color.black);
		//   botonAcepta.addMouseAdapter(new PanelAyuda_this_mouseAdapter(this));
		botonAcepta.addMouseListener(new botonAcepta_mouseAdapter(this));

		try {
			//muestra de la ayuda

			ayuda.setPage("file:./documentos/ayuda.txt");
			ayuda.setEditable(false);

			texto.setBackground(Color.gray);
			texto.setFont(new java.awt.Font("Serif", 3, 15));
			texto.setEditable(false);
			texto.setText(ayuda.getText());

			panelAyuda.setSize((int) (7.5 * (ancho / 10)), (int) (7.9 * (alto / 10)));
			panelAyuda.add(texto);

			JScrollPane scrollAyuda = new JScrollPane(panelAyuda);
			scrollAyuda.setPreferredSize(new Dimension(ancho / 2, 2 * (alto / 3)));
			scrollAyuda.setBounds((int) (ancho / 8.5), (int) (alto / 6.5), (int) (7.7 * (ancho / 10)), (int) (7 * (alto / 10)));
			panel2.add(scrollAyuda);

		}

		catch (Exception ex) {
			//mostramos con un JOptionPane el error producido
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE, null);
		}

// this.addMouseListener(new PanelAyuda_this_mouseAdapter(this));
		panel2.add(botonAcepta);
		panel2.add(labelDibujo, null);
		panel2.setLayout(null);
		this.getContentPane().setLayout(null);
		this.getContentPane().add(panel2, null);
		this.setSize(ancho, alto);
		this.setUndecorated(true);

	}



}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class botonAcepta_mouseAdapter extends java.awt.event.MouseAdapter {


	AyudaGUI adaptee;


	/**
	 *  Constructor for the botonAcepta_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	botonAcepta_mouseAdapter(AyudaGUI adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botonAcepta_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botonAcepta_mouseExited(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseClicked(MouseEvent e) {
		adaptee.botonAcepta_mouseClicked(e);
	}
}


