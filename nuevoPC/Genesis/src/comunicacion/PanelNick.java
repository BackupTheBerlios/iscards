package comunicacion;

import javax.swing.*;
import java.awt.event.*;
//import javax.swing.*;
import java.awt.*;


/**
 *  Clase que construye y configura el panel de petición de login
 *
 *@author     Manuel Domingo Mora, Jesús Patiño y Francisco Javier Arellano
 *@version    2.0
 */

public abstract class PanelNick extends Container {
	/**
	 *  Campo de texto donde se introduce el nick
	 */
//	private JTextField nick;

	/**
	 *  Etiqueta de texto
	 */
	//private JLabel lnick;

	/**
	 *  Campo de texto donde se introduce la clave
	 */
//	private JPasswordField password;

	/**
	 *  Label de campo de texto donde se introduce la clave
	 */
	//private JPasswordField lpassword;


        protected int alto=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        protected int ancho=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        JLabel labelFondo = new JLabel();
        JButton botonCancelar = new JButton();
        JButton botonAceptar = new JButton();
        protected JTextField textNombre = new JTextField();
        protected JPasswordField textContra =new JPasswordField();




	/**
	 *  Constructor y configurador del cuadro de dialogo
	 */
	public PanelNick() {

          //dibujamos el cursor
          ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
          Image image = cursor.getImage();
          Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(0,0), "img");
          this.setCursor(puntero);

          try {
            jbInit();
          }
          catch(Exception e) {
            e.printStackTrace();
          }
	}


  private void jbInit() throws Exception {
       // this.setResizable(false);
   this.setSize(ancho,alto);
   this.setLocation(0,0);

  // this.setUndecorated(true);
  // this.getContentPane().setLayout(null);

   //limites de componentes
   labelFondo.setBounds(new Rectangle((int)(ancho/5), 2*(alto/6), (int)(ancho/1.5), alto/4));
   botonCancelar.setBounds(new Rectangle((int)(2.9*(ancho/5)), (int)(2*(alto/4)), (int)(ancho/10.2), alto/25));
   botonCancelar.setBorder(null);
   //botonAceptar.setBounds(new Rectangle(0, 0, 100, 30));
   botonAceptar.setBounds(new Rectangle((int)(2.15*(ancho/5)), (int)(2*(alto/4)), (int)(ancho/10.2), alto/25));
   botonAceptar.setBorder(null);
   textNombre.setFont(new java.awt.Font("Serif", 3, 15));
   textNombre.setBounds(new Rectangle((int)(2.5*(ancho/5)), (int)(1.5*(alto/4)), ancho/5, alto/25));
    textNombre.addKeyListener(new PanelNick_textNombre_keyAdapter(this));

    textContra.setFont(new java.awt.Font("Serif", 3, 15));
   textContra.setBounds(new Rectangle((int)(2.9*(ancho/5)), (int)(1.65*(alto/4))+20, ancho/8, alto/25));



   //imagenes de componentes
    labelFondo.setIcon(new ImageIcon("../imagenes/introduceNick.jpg"));
    botonCancelar.setIcon(new ImageIcon("../imagenes/botoncancelar.jpg"));
   botonCancelar.addMouseListener(new PanelNick_botonCancelar_mouseAdapter(this));
    botonAceptar.setIcon(new ImageIcon("../imagenes/botonaceptar.jpg"));
   botonAceptar.addMouseListener(new PanelNick_botonAceptar_mouseAdapter(this));


   //agregar componentes al panel
   this.add(textContra,null);
   this.add(botonCancelar, null);
   this.add(botonAceptar, null);
   this.add(textNombre, null);
   this.add(labelFondo, null);


   //acciones de botones
   botonAceptar.addActionListener(new PanelNick_botonAceptar_actionAdapter(this));
   botonCancelar.addActionListener(new PanelNick_botonCancelar_actionAdapter(this));

}


/**
 * Función abstracta para el action performed del botón Aceptar
 * @param e
 */
abstract void botonAceptar_actionPerformed(ActionEvent e);

/**
 * Función abstracta para el action performed del botón Cancelar
 * @param e
 */
abstract void botonCancelar_actionPerformed(ActionEvent e);

void botonCancelar_mouseEntered(MouseEvent e) {
  ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
 Image image = cursor.getImage();
 Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
 this.setCursor(puntero);

}

void botonAceptar_mouseEntered(MouseEvent e) {
  ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
  Image image = cursor.getImage();
  Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
  this.setCursor(puntero);

}

void botonAceptar_mouseExited(MouseEvent e) {
  ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
  Image image = cursor.getImage();
  Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
  this.setCursor(puntero);

}

 abstract void textNombre_keyPressed(KeyEvent e);

void botonCancelar_mouseExited(MouseEvent e) {
  ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
  Image image = cursor.getImage();
  Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
  this.setCursor(puntero);

}

}

class PanelNick_botonAceptar_actionAdapter implements java.awt.event.ActionListener {
private PanelNick adaptee;

PanelNick_botonAceptar_actionAdapter(PanelNick adaptee) {
  this.adaptee = adaptee;
}
public void actionPerformed(ActionEvent e) {
  adaptee.botonAceptar_actionPerformed(e);
}
}

class PanelNick_botonCancelar_actionAdapter implements java.awt.event.ActionListener {
private PanelNick adaptee;

PanelNick_botonCancelar_actionAdapter(PanelNick adaptee) {
  this.adaptee = adaptee;
}
public void actionPerformed(ActionEvent e) {
  adaptee.botonCancelar_actionPerformed(e);
}
}

class PanelNick_botonCancelar_mouseAdapter extends java.awt.event.MouseAdapter {
PanelNick adaptee;

PanelNick_botonCancelar_mouseAdapter(PanelNick adaptee) {
  this.adaptee = adaptee;
}
public void mouseEntered(MouseEvent e) {
  adaptee.botonCancelar_mouseEntered(e);
}
public void mouseExited(MouseEvent e) {
  adaptee.botonCancelar_mouseExited(e);
}
}

class PanelNick_botonAceptar_mouseAdapter extends java.awt.event.MouseAdapter {
PanelNick adaptee;

PanelNick_botonAceptar_mouseAdapter(PanelNick adaptee) {
  this.adaptee = adaptee;
}
public void mouseEntered(MouseEvent e) {
  adaptee.botonAceptar_mouseEntered(e);
}
public void mouseExited(MouseEvent e) {
  adaptee.botonAceptar_mouseExited(e);
}
}

class PanelNick_textNombre_keyAdapter extends java.awt.event.KeyAdapter {
  PanelNick adaptee;

  PanelNick_textNombre_keyAdapter(PanelNick adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.textNombre_keyPressed(e);
  }
}






