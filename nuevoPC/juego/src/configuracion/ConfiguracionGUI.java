package configuracion;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: Interfaz Gráfico del frame de Configuración de la partida</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Rui Miguel Alonso
 * @version 1.0
 */

 abstract public class ConfiguracionGUI extends JFrame{


   JPanel jPanel1 = new JPanel();
   JLabel jLabel1 = new JLabel();
   JLabel jLabel2 = new JLabel();
   JButton botonEditar = new JButton();
   JButton botonAceptar = new JButton();
   JButton botonCancelar = new JButton();
   JScrollPane jScrollPane1 = new JScrollPane();
   JList listBarajas = new JList();
   protected int alto=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
   protected int ancho=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();


   /**
    * Constructora de la clase
    */
   public ConfiguracionGUI() {

    //dibujamos el cursor
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(0,0), "img");
    this.setCursor(puntero);

     try {
       jbInit();
     }
     catch (Exception e) {
       e.printStackTrace();
     }
   }

   /**
    * Función de inicialización del frame Configuración
    * @throws java.lang.Exception
    */
   private void jbInit() throws Exception {
     this.setResizable(false);
     this.setUndecorated(true);
     this.setSize(new Dimension(ancho, alto));
     this.getContentPane().setLayout(null);


     jLabel1.setBackground(Color.black);
     jLabel1.setIcon(new ImageIcon("../imagenes/fondos/fondoBarajas.jpg"));
     jLabel1.setBounds(0,0,ancho,alto);

     //creamos los botones
     botonEditar.setBackground(Color.black);
    botonEditar.setBorder(null);
     botonEditar.setBounds(4*(ancho/7), (int) (2.5*(alto/10)), (int)(ancho/6.85), (int)(alto/27));
     botonEditar.setIcon(new ImageIcon("../imagenes/botonEditar.jpg"));
    botonEditar.addMouseListener(new ConfiguracionGUI_botonEditar_mouseAdapter(this));
     botonEditar.addActionListener(new ConfiguracionGUI_botonEditar_actionAdapter(this));

     botonAceptar.setBackground(Color.black);
     botonAceptar.setBounds(new Rectangle(4*(ancho/7), 2*(alto/3), (int)(ancho/6.85), (int)(alto/27)));
    botonAceptar.setBorder(null);
     botonAceptar.setIcon(new ImageIcon("../imagenes/aceptar.jpg"));
    botonAceptar.addMouseListener(new ConfiguracionGUI_botonAceptar_mouseAdapter(this));
     botonAceptar.addActionListener(new ConfiguracionGUI_botonAceptar_actionAdapter(this));

     botonCancelar.setBackground(Color.black);
     botonCancelar.setBounds(new Rectangle(4*(ancho/7), 3*(alto/4), (int)(ancho/6.85), (int)(alto/27)));
    botonCancelar.setBorder(null);
     botonCancelar.setIcon(new ImageIcon("../imagenes/cancelar.jpg"));
    botonCancelar.addMouseListener(new ConfiguracionGUI_botonCancelar_mouseAdapter(this));
     botonCancelar.addActionListener(new ConfiguracionGUI_botonCancelar_actionAdapter(this));

     jPanel1.setLayout(null);
     jPanel1.setBackground(Color.black);
     jPanel1.setBounds(0,0,ancho,alto);




     listBarajas.setFont(new java.awt.Font("Serif", 3, 20));
    listBarajas.setBorder(BorderFactory.createLoweredBevelBorder());
     listBarajas.setBackground(Color.gray);

     jScrollPane1.setBounds((int)(3*(ancho/17)),(int) (2.5*(alto/10)), ancho/3,(int) (alto/1.8));
     jScrollPane1.setBorder(BorderFactory.createLoweredBevelBorder());
    jScrollPane1.getViewport().add(listBarajas, null);


     jPanel1.add(botonCancelar, null);
     jPanel1.add(botonAceptar, null);
     jPanel1.add(botonEditar, null);
     jPanel1.add(jScrollPane1, null);

     jPanel1.add(jLabel1, null);


     this.getContentPane().add(jPanel1, null);
  }

  /**
   * Función abstracta para controlar el mouse clicked de la lista de barajas
   * @param e
   */
  abstract void listBarajas_mouseClicked(MouseEvent e);

  /**
   * Función abstracta para controlar el action event del botón Editar
   * @param e
   */
  abstract void botonEditar_actionPerformed(ActionEvent e);

  /**
   * Función abstracta para controlar el action event del botón Aceptar
   * @param e
   */
  abstract void botonAceptar_actionPerformed(ActionEvent e);

  /**
   * Función abstracta para controlar el action event del botón Cancelar
   * @param e
   */
  abstract void botonCancelar_actionPerformed(ActionEvent e);

  void botonCancelar_mouseEntered(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
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

  void botonEditar_mouseEntered(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
   Image image = cursor.getImage();
   Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
   this.setCursor(puntero);

  }

  void botonEditar_mouseExited(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }






}

class ConfiguracionGUI_botonCancelar_mouseAdapter extends java.awt.event.MouseAdapter {
  ConfiguracionGUI adaptee;

  ConfiguracionGUI_botonCancelar_mouseAdapter(ConfiguracionGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonCancelar_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonCancelar_mouseExited(e);
  }
}

class ConfiguracionGUI_botonAceptar_mouseAdapter extends java.awt.event.MouseAdapter {
  ConfiguracionGUI adaptee;

  ConfiguracionGUI_botonAceptar_mouseAdapter(ConfiguracionGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonAceptar_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonAceptar_mouseExited(e);
  }
}

class ConfiguracionGUI_botonEditar_mouseAdapter extends java.awt.event.MouseAdapter {
  ConfiguracionGUI adaptee;

  ConfiguracionGUI_botonEditar_mouseAdapter(ConfiguracionGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonEditar_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonEditar_mouseExited(e);
  }
}