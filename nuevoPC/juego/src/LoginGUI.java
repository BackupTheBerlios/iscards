
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: Interfaz gráfico para el JFrame que solicita el registro del usuario en el juego</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Rui Miguel Alonso
 * @version 1.0
 */

abstract public class LoginGUI extends JFrame {

  private JLabel labelFondo = new JLabel();
  private JLabel usuReg= new JLabel();
  private JButton botonCargar = new JButton();
  private JButton botonNuevo = new JButton();
  private JButton botonBorrar = new JButton();
  private JButton botonSalir = new JButton();
  private JLabel bruja =new JLabel();

  protected int alto=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
  protected int ancho=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
  JScrollPane jScrollPane1 = new JScrollPane();
  JList listaUsuarios = new JList();

  /**
   * Constructora de la clase
   */
  public LoginGUI() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);

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
   * Función que inicializa y crea los componentes del JFrame
   * @throws java.lang.Exception
   */
  private void jbInit() throws Exception {

    this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    this.setResizable(false);
    this.setUndecorated(true);
    this.getContentPane().setLayout(null);

    //dimensiones y colocacion de los elementos
    usuReg.setBounds(new Rectangle(ancho/5, alto/6, ancho/3, alto/3));
    labelFondo.setBounds(0,0,(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                         (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
    botonSalir.setBackground(Color.black);
    botonSalir.setBounds(new Rectangle((int)(2.15*(ancho/5)), (int)(3.75*(alto/4)), ancho/7, (int)(alto/27)));
    botonSalir.setBorder(null);
    botonBorrar.setBackground(Color.black);
    botonBorrar.setBounds(new Rectangle(ancho/5, (int)(3.5*(alto/4)), ancho/5, (int)(alto/27)));
    botonBorrar.setBorder(null);
    botonNuevo.setBackground(Color.black);
    botonNuevo.setBounds(new Rectangle(2*(ancho/5), (int)(3.5*(alto/4)), ancho/5, (int)(alto/27)));
    botonNuevo.setBorder(null);
    botonCargar.setBackground(Color.black);
    botonCargar.setBounds(new Rectangle(3*(ancho/5), (int)(3.5*(alto/4)), ancho/5, (int)(alto/27)));
    botonCargar.setBorder(null);



    //insercion de imagenes
    labelFondo.setIcon(new ImageIcon("../imagenes/fondos/fondo.jpg"));
    labelFondo.setOpaque(false);
    usuReg.setIcon(new ImageIcon("../imagenes/LoguinGUI/UsuRegistrados.jpg"));
    botonSalir.setIcon(new ImageIcon("../imagenes/LoguinGUI/botonSalir.jpg"));
    botonSalir.addMouseListener(new LoginGUI_botonSalir_mouseAdapter(this));
    botonSalir.addActionListener(new LoginGUI_botonSalir_actionAdapter(this));

    botonBorrar.setIcon(new ImageIcon("../imagenes/LoguinGUI/BorrarUsuario.jpg"));
    botonBorrar.addMouseListener(new LoginGUI_botonBorrar_mouseAdapter(this));
    botonBorrar.addActionListener(new LoginGUI_botonBorrar_actionAdapter(this));

    botonNuevo.setIcon(new ImageIcon("../imagenes/LoguinGUI/nuevoUsuario.jpg"));
    botonNuevo.addMouseListener(new LoginGUI_botonNuevo_mouseAdapter(this));
    botonNuevo.addActionListener(new LoginGUI_botonNuevo_actionAdapter(this));

    botonCargar.setIcon(new ImageIcon("../imagenes/LoguinGUI/CargarUsuario.jpg"));
    botonCargar.addMouseListener(new LoginGUI_botonCargar_mouseAdapter(this));
    botonCargar.addActionListener(new LoginGUI_botonCargar_actionAdapter(this));
//620,320
    bruja.setBounds(new Rectangle((int)(ancho/5.2), (int)(alto/2.57),(int) (ancho/1.5),(int) (alto/2.4)));
    bruja.setBackground(Color.black);
    bruja.setIcon(new ImageIcon("../imagenes/brujaInicio.jpg"));



    //añadimos los elementos al panel
    jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    jScrollPane1.getViewport().setBackground(Color.black);
    jScrollPane1.setBounds(new Rectangle(ancho/5,(int)(alto/2.53) , (int)(ancho/3.4),(int) (alto/2.5)));
    listaUsuarios.setBackground(Color.lightGray);
    listaUsuarios.setFont(new java.awt.Font("SansSerif", 2, 20));
    listaUsuarios.setBorder(BorderFactory.createLoweredBevelBorder());
    listaUsuarios.setDebugGraphicsOptions(0);
    listaUsuarios.setOpaque(true);
    //listaUsuarios.setPreferredSize(new Dimension((int)(ancho/3.5),alto/3));
  //  listaUsuarios.setMaximumSize(new Dimension(ancho,alto));

   jScrollPane1.getViewport().add(listaUsuarios, null);
   this.getContentPane().add(jScrollPane1, null);
    this.getContentPane().add(botonCargar, null);
    this.getContentPane().add(botonNuevo, null);
    this.getContentPane().add(botonBorrar, null);
    this.getContentPane().add(botonSalir, null);

    this.getContentPane().add(usuReg, null);

    this.getContentPane().add(usuReg, null);
    this.getContentPane().add(bruja,null);
    this.getContentPane().add(labelFondo, null);
  }

  /**
   * Función abstracta para el action performed del botón Cargar Usuario
   * @param e
   */
  abstract void botonCargar_actionPerformed(ActionEvent e);

  /**
   * Función abstracta para el action performed del botón Borrar Usuario
   * @param e
   */
  abstract void botonBorrar_actionPerformed(ActionEvent e);

  /**
   * Función abstracta para el action performed del botón Nuevo Usuario
   * @param e
   */
  abstract void botonNuevo_actionPerformed(ActionEvent e);

  /**
   * Función abstracta para el mouse clicked de la lista de usuarios
   * @param e
   */
  abstract void listaUsuarios_mouseClicked(MouseEvent e);

  /**
   * Función abstracta para el action performed del botón Salir
   *
   */
  abstract void botonSalir_actionPerformed(ActionEvent e);

  void botonCargar_mouseEntered(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);
  }

  void botonNuevo_mouseEntered(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botonBorrar_mouseEntered(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botonSalir_mouseEntered(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botonCargar_mouseExited(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botonNuevo_mouseExited(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botonBorrar_mouseExited(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botonSalir_mouseExited(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }


}

//***********************************************************************
//***********************************************************************
//***********************************************************************


class LoginGUI_botonNuevo_actionAdapter implements java.awt.event.ActionListener {
  private LoginGUI adaptee;

  LoginGUI_botonNuevo_actionAdapter(LoginGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botonNuevo_actionPerformed(e);
  }
}

class LoginGUI_botonCargar_actionAdapter implements java.awt.event.ActionListener {
  private LoginGUI adaptee;

  LoginGUI_botonCargar_actionAdapter(LoginGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botonCargar_actionPerformed(e);
  }
}

class LoginGUI_botonBorrar_actionAdapter implements java.awt.event.ActionListener {
  private LoginGUI adaptee;

  LoginGUI_botonBorrar_actionAdapter(LoginGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botonBorrar_actionPerformed(e);
  }
}

class LoginGUI_botonSalir_actionAdapter implements java.awt.event.ActionListener {
  private LoginGUI adaptee;

  LoginGUI_botonSalir_actionAdapter(LoginGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botonSalir_actionPerformed(e);
  }

}

class LoginGUI_botonCargar_mouseAdapter extends java.awt.event.MouseAdapter {
  LoginGUI adaptee;

  LoginGUI_botonCargar_mouseAdapter(LoginGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonCargar_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonCargar_mouseExited(e);
  }
}

class LoginGUI_botonNuevo_mouseAdapter extends java.awt.event.MouseAdapter {
  LoginGUI adaptee;

  LoginGUI_botonNuevo_mouseAdapter(LoginGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonNuevo_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonNuevo_mouseExited(e);
  }
}

class LoginGUI_botonBorrar_mouseAdapter extends java.awt.event.MouseAdapter {
  LoginGUI adaptee;

  LoginGUI_botonBorrar_mouseAdapter(LoginGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonBorrar_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonBorrar_mouseExited(e);
  }
}

class LoginGUI_botonSalir_mouseAdapter extends java.awt.event.MouseAdapter {
  LoginGUI adaptee;

  LoginGUI_botonSalir_mouseAdapter(LoginGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonSalir_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonSalir_mouseExited(e);
  }
}

