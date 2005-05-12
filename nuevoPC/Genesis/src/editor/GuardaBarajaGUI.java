package editor;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import padrePaneles.*;


/**
 * <p>Título: </p>
 * <p>Descripción: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Empresa: </p>
 * @author sin atribuir
 * @version 1.0
 */

abstract public class GuardaBarajaGUI extends Container {

  protected int alto=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
  protected int ancho=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
  JLabel labelFondo = new JLabel();
  JButton botonCancelar = new JButton();
  JButton botonAceptar = new JButton();
  JTextField textNombre = new JTextField();




  public GuardaBarajaGUI() {

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


      this.setSize(ancho,alto);
      this.setLocation(0,0);

      //limites de componentes
      labelFondo.setBounds(new Rectangle((int)(ancho/3.5), 2*(alto/6), ancho/2, alto/4));
      botonCancelar.setBounds(new Rectangle((int)(2.75*(ancho/5)), (int)(2*(alto/4)), (int)(ancho/10.2), alto/25));
      botonCancelar.setBorder(null);
      botonAceptar.setBounds(new Rectangle((int)(2.15*(ancho/5)), (int)(2*(alto/4)), (int)(ancho/10.2), alto/25));
      botonAceptar.setBorder(null);
      textNombre.setFont(new java.awt.Font("Serif", 3, 15));
      textNombre.setBounds(new Rectangle((int)(2.2*(ancho/5)), (int)(1.82*(alto/4)), ancho/5, alto/25));



      //imagenes de componentes
       labelFondo.setIcon(new ImageIcon("../imagenes/NombreBaraja.jpg"));
       botonCancelar.setIcon(new ImageIcon("../imagenes/botoncancelar.jpg"));
       botonCancelar.addMouseListener(new GuardaBarajaGUI_botonCancelar_mouseAdapter(this));
       botonAceptar.setIcon(new ImageIcon("../imagenes/botonaceptar.jpg"));
       botonAceptar.addMouseListener(new GuardaBarajaGUI_botonAceptar_mouseAdapter(this));


      //agregar componentes al panel
      this.add(botonCancelar, null);
      this.add(botonAceptar, null);
      this.add(textNombre, null);
      this.add(labelFondo, null);


      //acciones de botones
      botonAceptar.addActionListener(new GuardaBarajaGUI_botonAceptar_actionAdapter(this));
      botonCancelar.addActionListener(new GuardaBarajaGUI_botonCancelar_actionAdapter(this));

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

void botonCancelar_mouseExited(MouseEvent e) {
  ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
  Image image = cursor.getImage();
  Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
  this.setCursor(puntero);

}

}

class GuardaBarajaGUI_botonAceptar_actionAdapter implements java.awt.event.ActionListener {
private GuardaBarajaGUI adaptee;

GuardaBarajaGUI_botonAceptar_actionAdapter(GuardaBarajaGUI adaptee) {
  this.adaptee = adaptee;
}
public void actionPerformed(ActionEvent e) {
  adaptee.botonAceptar_actionPerformed(e);
}
}

class GuardaBarajaGUI_botonCancelar_actionAdapter implements java.awt.event.ActionListener {
private GuardaBarajaGUI adaptee;

GuardaBarajaGUI_botonCancelar_actionAdapter(GuardaBarajaGUI adaptee) {
  this.adaptee = adaptee;
}
public void actionPerformed(ActionEvent e) {
  adaptee.botonCancelar_actionPerformed(e);
}
}

class GuardaBarajaGUI_botonCancelar_mouseAdapter extends java.awt.event.MouseAdapter {
GuardaBarajaGUI adaptee;

GuardaBarajaGUI_botonCancelar_mouseAdapter(GuardaBarajaGUI adaptee) {
  this.adaptee = adaptee;
}
public void mouseEntered(MouseEvent e) {
  adaptee.botonCancelar_mouseEntered(e);
}
public void mouseExited(MouseEvent e) {
  adaptee.botonCancelar_mouseExited(e);
}
}

class GuardaBarajaGUI_botonAceptar_mouseAdapter extends java.awt.event.MouseAdapter {
GuardaBarajaGUI adaptee;

GuardaBarajaGUI_botonAceptar_mouseAdapter(GuardaBarajaGUI adaptee) {
  this.adaptee = adaptee;
}
public void mouseEntered(MouseEvent e) {
  adaptee.botonAceptar_mouseEntered(e);
}
public void mouseExited(MouseEvent e) {
  adaptee.botonAceptar_mouseExited(e);
}
}




