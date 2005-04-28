package login;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;



abstract public class SolicitarNombreGUI extends Container {


  protected int alto=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
  protected int ancho=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
  JLabel labelFondo = new JLabel();
  JButton botonCancelar = new JButton();
  JButton botonAceptar = new JButton();
  JTextField textNombre = new JTextField();


  /**
   * Constructora de la clase
   */
  public SolicitarNombreGUI() {

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

  /**
   * Función que inicializa y crea todos los componentes del JFrame
   * @throws java.lang.Exception
   */
  private void jbInit() throws Exception {

   // this.setResizable(false);
    this.setSize(ancho,alto);
    this.setLocation(0,0);

   // this.setUndecorated(true);
   // this.getContentPane().setLayout(null);

    //limites de componentes
    labelFondo.setBounds(new Rectangle((int)(ancho/3.5), 2*(alto/6), ancho/2, alto/4));
    botonCancelar.setBounds(new Rectangle((int)(2.7*(ancho/5)), (int)(1.9*(alto/4)), (int)(ancho/10.2), alto/25));
    botonCancelar.setBorder(null);
    //botonAceptar.setBounds(new Rectangle(0, 0, 100, 30));
    botonAceptar.setBounds(new Rectangle((int)(2.15*(ancho/5)), (int)(1.9*(alto/4)), (int)(ancho/10.2), alto/25));
    botonAceptar.setBorder(null);
    textNombre.setFont(new java.awt.Font("Serif", 3, 15));
    textNombre.setBounds(new Rectangle((int)(2.58*(ancho/5)), (int)(1.7*(alto/4)), ancho/10, alto/25));



    //imagenes de componentes
     labelFondo.setIcon(new ImageIcon("../imagenes/panelNuevoUsu.jpg"));
     botonCancelar.setIcon(new ImageIcon("../imagenes/botoncancelar.jpg"));
    botonCancelar.addMouseListener(new SolicitarNombreGUI_botonCancelar_mouseAdapter(this));
     botonAceptar.setIcon(new ImageIcon("../imagenes/botonaceptar.jpg"));
    botonAceptar.addMouseListener(new SolicitarNombreGUI_botonAceptar_mouseAdapter(this));


    //agregar componentes al panel
    this.add(botonCancelar, null);
    this.add(botonAceptar, null);
    this.add(textNombre, null);
    this.add(labelFondo, null);


    //acciones de botones
    botonAceptar.addActionListener(new SolicitarNombreGUI_botonAceptar_actionAdapter(this));
    botonCancelar.addActionListener(new SolicitarNombreGUI_botonCancelar_actionAdapter(this));

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

class SolicitarNombreGUI_botonAceptar_actionAdapter implements java.awt.event.ActionListener {
  private SolicitarNombreGUI adaptee;

  SolicitarNombreGUI_botonAceptar_actionAdapter(SolicitarNombreGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botonAceptar_actionPerformed(e);
  }
}

class SolicitarNombreGUI_botonCancelar_actionAdapter implements java.awt.event.ActionListener {
  private SolicitarNombreGUI adaptee;

  SolicitarNombreGUI_botonCancelar_actionAdapter(SolicitarNombreGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botonCancelar_actionPerformed(e);
  }
}

class SolicitarNombreGUI_botonCancelar_mouseAdapter extends java.awt.event.MouseAdapter {
  SolicitarNombreGUI adaptee;

  SolicitarNombreGUI_botonCancelar_mouseAdapter(SolicitarNombreGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonCancelar_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonCancelar_mouseExited(e);
  }
}

class SolicitarNombreGUI_botonAceptar_mouseAdapter extends java.awt.event.MouseAdapter {
  SolicitarNombreGUI adaptee;

  SolicitarNombreGUI_botonAceptar_mouseAdapter(SolicitarNombreGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonAceptar_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonAceptar_mouseExited(e);
  }
}




