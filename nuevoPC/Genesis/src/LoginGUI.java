
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import panelesInfo.*;
import padrePaneles.*;


/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: Interfaz gráfico para el JFrame que solicita el registro del usuario en el juego</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Rui Miguel Alonso
 * @version 1.0
 */

abstract public class LoginGUI extends PadrePaneles {

    private JPanel contentPane;
   protected JPanel panelppal= new JPanel();
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

    contentPane = (JPanel)this.getContentPane();
    contentPane.setLayout(null);


    //dimensiones y colocacion de los elementos
    usuReg.setBounds(new Rectangle(ancho/5, alto/6, ancho/3, alto/3));
    labelFondo.setBounds(0,0,(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                         (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
    botonSalir.setBackground(Color.black);
    botonSalir.setBounds(new Rectangle((int)(2.15*(ancho/5)), (int)(3.75*(alto/4)), ancho/7, (int)(alto/27)));
    botonSalir.setBorder(null);
    botonBorrar.setBackground(Color.black);
    botonBorrar.setBounds(new Rectangle(ancho/6, (int)(3.5*(alto/4)), (int)(0.98*(ancho/5)), (int)(alto/27)));
    botonBorrar.setBorder(null);
    botonNuevo.setBackground(Color.black);
    botonNuevo.setBounds(new Rectangle(2*(ancho/5), (int)(3.5*(alto/4)), (int)(0.98*(ancho/5)), (int)(alto/27)));
    botonNuevo.setBorder(null);
    botonCargar.setBackground(Color.black);
    botonCargar.setBounds(new Rectangle((int)(3.8*(ancho/6)), (int)(3.5*(alto/4)),(int)(0.98*(ancho/5)), (int)(alto/27)));
    botonCargar.setBorder(null);

    panelppal.setBounds(0,0,ancho,alto);
    panelppal.setLayout(null);



    //insercion de imagenes
    labelFondo.setIcon(new ImageIcon("../imagenes/fondos/fondo.jpg"));
    labelFondo.setOpaque(true);
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

    bruja.setBounds(new Rectangle((int)(ancho/5.2), (int)(alto/2.57),(int) (ancho/1.5),(int) (alto/2.4)));
    bruja.setBackground(Color.black);
    bruja.setIcon(new ImageIcon("../imagenes/brujaInicio.jpg"));



    //añadimos los elementos al panel
    jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    jScrollPane1.getViewport().setBackground(Color.black);
    jScrollPane1.setBounds(new Rectangle(ancho/5,(int)(alto/2.53) , (int)(ancho/3.4),(int) (alto/2.5)));
    listaUsuarios.setBackground(Color.lightGray);
    listaUsuarios.setFont(new java.awt.Font("SansSerif", 2, 20));
    listaUsuarios.setBorder(BorderFactory.createLoweredBevelBorder());
    listaUsuarios.setDebugGraphicsOptions(0);
    listaUsuarios.setOpaque(true);


   jScrollPane1.getViewport().add(listaUsuarios, null);

   panelppal.add(jScrollPane1,null);
   panelppal.add(botonCargar,null);
   panelppal.add(botonNuevo,null);
   panelppal.add(botonBorrar,null);
   panelppal.add(usuReg,null);
   panelppal.add(botonSalir,null);
   panelppal.add(bruja,null);
   panelppal.add(labelFondo, null);
   contentPane.add(panelppal);

/*   this.getContentPane().add(jScrollPane1, null);
    this.getContentPane().add(botonCargar, null);
    this.getContentPane().add(botonNuevo, null);
    this.getContentPane().add(botonBorrar, null);
    this.getContentPane().add(botonSalir, null);

    this.getContentPane().add(usuReg, null);

    this.getContentPane().add(usuReg, null);
    this.getContentPane().add(bruja,null);
    this.getContentPane().add(labelFondo, null);
*/
  }


  public void inhabilitaPanel(){

  //  this.getContentPane().setEnabled(false);
    jScrollPane1.getVerticalScrollBar().setEnabled(false);
    usuReg.setEnabled(false);
    bruja.setEnabled(false);
   // panelppal.setEnabled(false);
    botonBorrar.setEnabled(false);
    botonCargar.setEnabled(false);
    botonNuevo.setEnabled(false);
    botonSalir.setEnabled(false);
    listaUsuarios.setEnabled(false);
    jScrollPane1.setEnabled(false);
  //  jScrollPane1.enableInputMethods(false);
    labelFondo.setEnabled(false);



  }


  public void habilitaPanel(){

     // this.getContentPane().setEnabled(true);
      jScrollPane1.getVerticalScrollBar().setEnabled(true);
      this.panelppal.setEnabled(true);
      labelFondo.setEnabled(true);
      usuReg.setEnabled(true);
      bruja.setEnabled(true);
     // panelppal.setEnabled(true);
      botonBorrar.setEnabled(true);
      botonCargar.setEnabled(true);
      botonNuevo.setEnabled(true);
      botonSalir.setEnabled(true);
      jScrollPane1.setEnabled(true);
      listaUsuarios.setEnabled(true);



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

