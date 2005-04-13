

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: Interfaz gráfico para el JFrame de inscripción de un nuevo usuario</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Rui Miguel Alonso
 * @version 1.0
 */

abstract public class SolicitarNombreGUI extends JFrame {
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

    this.setResizable(false);
    this.getContentPane().setLayout(null);
    this.setSize(ancho,alto);
    this.setLocation(0,0);

    this.setUndecorated(true);
    this.getContentPane().setLayout(null);

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
     botonAceptar.setIcon(new ImageIcon("../imagenes/botonaceptar.jpg"));


    //agregar componentes al panel
    this.getContentPane().add(botonCancelar, null);
    this.getContentPane().add(botonAceptar, null);
    this.getContentPane().add(textNombre, null);
    this.getContentPane().add(labelFondo, null);
    this.getContentPane().setBackground(SystemColor.menuText);

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
