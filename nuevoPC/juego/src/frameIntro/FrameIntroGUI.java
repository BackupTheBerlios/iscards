package frameIntro;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: Interfaz Gráfico del frame de inicio</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Rui Miguel
 * @version 1.0
 */

abstract public class FrameIntroGUI extends JFrame {

  JPanel contentPane;
  private JPanel panelPrincipal = new JPanel();
  private JPanel panelBotones = new JPanel();
  private JButton boton1Jugador = new JButton();
  private JButton botonJuegoRed = new JButton();
  private JButton botonEditar = new JButton();
  private JButton botonDemo = new JButton();
  private JButton botonReglas = new JButton();
  private JButton botonAyuda = new JButton();
  private JButton botonRecibir = new JButton();
  private JButton botonEnviar = new JButton();
  private JButton botonDescargaSobre = new JButton();
  private JButton botonSalir = new JButton();
  private JLabel labelFondo = new JLabel();
  private JLabel labelDibujo = new JLabel();
  protected int alto=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
  protected int ancho=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();


  /**
   * Constructora de la clase
   */
  public FrameIntroGUI() {
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Función de inicialización de los componentes
   * @throws java.lang.Exception
   */
  private void jbInit() throws Exception {
	//dibujamos el cursor
 	ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
	Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

    //adaptamos el frame del tablero al tamaño máximo de la pantalla (con los SplitPane no se puede hacer de momento porque se desajusta)
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.setSize(screenSize.width, screenSize.height);

    contentPane = (JPanel)this.getContentPane();
    contentPane.setLayout(null);

    boton1Jugador.setBorder(null);
    botonJuegoRed.setBorder(null);
    botonDemo.setBorder(null);
    botonEditar.setBorder(null);
    botonReglas.setBorder(null);
    botonAyuda.setBorder(null);
    botonRecibir.setBorder(null);
    botonEnviar.setBorder(null);
    botonSalir.setBorder(null);

    labelDibujo.setBackground(Color.red);
    botonDescargaSobre.addMouseListener(new botonDescargaSobre_mouseAdapter(this));
    botonDescargaSobre.setIcon(new ImageIcon("../imagenes/DescargaSobre.jpg"));
    botonDescargaSobre.setBounds(new java.awt.Rectangle(0, 0, 0, 0));
    botonDescargaSobre.setBackground(Color.black);
    botonDescargaSobre.setBorder(null);
    contentPane.add(panelBotones, null);
    contentPane.add(panelPrincipal, null);

    //quitamos la barra de titulo
    this.setUndecorated(true);
    this.setResizable(false);

    ImageIcon dibPrincipal = new ImageIcon ("../imagenes/DibujoPortada.gif");
    labelDibujo.setIcon(dibPrincipal);
    labelDibujo.setBounds(new Rectangle((int)(ancho/10),(int) (alto/3.5), 8*(ancho/10), (alto/2)));

    ImageIcon fondo = new ImageIcon("../imagenes/fondos/fondo.jpg");
    labelFondo.setIcon(fondo);
    labelFondo.setBounds(new Rectangle(new Point(0,0),Toolkit.getDefaultToolkit().getScreenSize()));




    panelPrincipal.setLayout(null);
    panelPrincipal.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    panelPrincipal.add(labelDibujo, null);
    panelPrincipal.add(labelFondo, null);



	//añadimos los botones

    boton1Jugador.setBounds(new Rectangle(ancho/9, 0, (int)(ancho/6.85), (int)(alto/27)));
    boton1Jugador.setIcon(new ImageIcon("../imagenes/botonUnJugador.jpg"));
    boton1Jugador.addActionListener(new FrameIntroGUI_boton1Jugador_actionAdapter(this));
    boton1Jugador.addMouseListener(new boton1Jugador_mouseAdapter(this));


    botonJuegoRed.setBounds(new Rectangle(8*(ancho/49)+(ancho/9), 0, (int)(ancho/6.85), (int)(alto/27) ));
    botonJuegoRed.setIcon(new ImageIcon("../imagenes/botonJuegoEnRed.jpg"));
    botonJuegoRed.addActionListener(new FrameIntroGUI_botonJuegoRed_actionAdapter(this));
    botonJuegoRed.addMouseListener(new botonJuegoRed_mouseAdapter(this));


    botonDemo.setBounds(new Rectangle(16*(ancho/49)+(ancho/9), 0, (int)(ancho/6.85), (int)(alto/27) ));
    botonDemo.setIcon(new ImageIcon("../imagenes/botonDemo.jpg"));
    botonDemo.addActionListener(new FrameIntroGUI_botonDemo_actionAdapter(this));
    botonDemo.addMouseListener(new botonDemo_mouseAdapter(this));


    botonEditar.setBounds(new Rectangle(24*(ancho/49)+(ancho/9), 0, (int)(ancho/6.85), (int)(alto/27) ));
    botonEditar.setIcon(new ImageIcon("../imagenes/botonEditar.jpg"));
    botonEditar.addActionListener(new FrameIntroGUI_botonEditar_actionAdapter(this));
    botonEditar.addMouseListener(new botonEditar_mouseAdapter(this));


    botonReglas.setBounds(new Rectangle(32*(ancho/49)+(ancho/9), 0,(int)(ancho/6.85), (int)(alto/27) ));
    botonReglas.setIcon(new ImageIcon("../imagenes/botonReglas.jpg"));
    botonReglas.addActionListener(new FrameIntroGUI_botonReglas_actionAdapter(this));
    botonReglas.addMouseListener(new botonReglas_mouseAdapter(this));


    botonAyuda.setBounds(new Rectangle(ancho/9+50,(alto/20), (int)(ancho/6.85), (int)(alto/27) ));
    botonAyuda.setIcon(new ImageIcon("../imagenes/botonAyuda.jpg"));
    botonAyuda.addActionListener(new FrameIntroGUI_botonAyuda_actionAdapter(this));
    botonAyuda.addMouseListener(new botonAyuda_mouseAdapter(this));


    botonRecibir.setBounds(new Rectangle(8*(ancho/49)+(ancho/9)+50, alto/20, (int)(ancho/6.85), (int)(alto/27) ));
    botonRecibir.setIcon(new ImageIcon("../imagenes/botonRecibir.jpg"));
    botonRecibir.addActionListener(new FrameIntroGUI_botonRecibir_actionAdapter(this));
    botonRecibir.addMouseListener(new botonRecibir_mouseAdapter(this));


    botonEnviar.setBounds(new Rectangle(16*(ancho/49)+(ancho/9)+50, alto/20, (int)(ancho/6.85), (int)(alto/27) ));
    botonEnviar.setIcon(new ImageIcon("../imagenes/botonEnviar.jpg"));
    botonEnviar.addActionListener(new FrameIntroGUI_botonEnviar_actionAdapter(this));
    botonEnviar.addMouseListener(new botonEnviar_mouseAdapter(this));

    botonDescargaSobre.setBounds(24*(ancho/49)+(ancho/9)+50,alto/20,(int)(ancho/5.5), (int)(alto/27));
    botonDescargaSobre.setIcon(new ImageIcon("../imagenes/DescargaSobre.jpg"));
    botonDescargaSobre.addMouseListener(new botonDescargaSobre_mouseAdapter(this));


    botonSalir.setBounds(new Rectangle(16*(ancho/49)+(ancho/9), alto/10, (int)(ancho/6.85), (int)(alto/27)));
    botonSalir.setIcon(new ImageIcon("../imagenes/botonSalir.jpg"));
    botonSalir.addActionListener(new FrameIntroGUI_botonSalir_actionAdapter(this));
    botonSalir.addMouseListener(new botonSalir_mouseAdapter(this));

    panelBotones.setBounds(new Rectangle(0,(int)(3.2*(alto/4)), (int)ancho, (int)(alto-(3.2*(alto/4)))));
    panelBotones.setLayout(null);
    panelBotones.setBackground(Color.BLACK);

    panelBotones.setOpaque(false);
    panelBotones.setVisible(true);

    panelBotones.add(boton1Jugador, null);
    panelBotones.add(botonJuegoRed, null);
    panelBotones.add(botonDemo, null);
    panelBotones.add(botonEditar, null);
    panelBotones.add(botonReglas, null);
    panelBotones.add(botonAyuda, null);
    panelBotones.add(botonRecibir, null);
    panelBotones.add(botonEnviar, null);
    panelBotones.add(botonDescargaSobre,null);
    panelBotones.add(botonSalir, null);

 }

 void botonDescargaSobre_mouseEntered(MouseEvent e) {
   ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
   Image image = cursor.getImage();
   Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
   this.setCursor(puntero);

         //boton1Jugador.setForeground(Color.white);
 }

 void botonDescargaSobre_mouseExited(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

   // boton1Jugador.setForeground(Color.orange);
  }



  void boton1Jugador_mouseEntered(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  	//boton1Jugador.setForeground(Color.white);
  }

  void boton1Jugador_mouseExited(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

   // boton1Jugador.setForeground(Color.orange);
  }

  void botonJuegoRed_mouseEntered(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botonJuegoRed_mouseExited(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
   Image image = cursor.getImage();
   Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
   this.setCursor(puntero);

  }

  void botonDemo_mouseEntered(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botonDemo_mouseExited(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botonAyuda_mouseEntered(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botonAyuda_mouseExited(MouseEvent e) {
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

  void botonReglas_mouseEntered(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botonReglas_mouseExited(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
   Image image = cursor.getImage();
   Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
   this.setCursor(puntero);

  }

  void botonRecibir_mouseEntered(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botonRecibir_mouseExited(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
   Image image = cursor.getImage();
   Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
   this.setCursor(puntero);

  }

  void botonEnviar_mouseEntered(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botonEnviar_mouseExited(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
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

  void botonSalir_mouseExited(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
   Image image = cursor.getImage();
   Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
   this.setCursor(puntero);

  }





  /**
   * Función abstracta para controlar el action performed del botón 1 Jugador
   * @param e
   */
  abstract void boton1Jugador_actionPerformed(ActionEvent e);

  /**
   * Función abstracta para controlar el action performed del botón Juego Red
   * @param e
   */
  abstract void botonJuegoRed_actionPerformed(ActionEvent e);

  /**
   * Función abstracta para controlar el action performed del botón Demo
   * @param e
   */
  abstract void botonDemo_actionPerformed(ActionEvent e);

  /**
   * Función abstracta para controlar el action performed del botón Ayuda
   * @param e
   */
  abstract void botonAyuda_actionPerformed(ActionEvent e);

  /**
   * Función abstracta para controlar el action performed del botón Editar
   * @param e
   */
  abstract void botonEditar_actionPerformed(ActionEvent e);

  /**
   * Función abstracta para controlar el action performed del botón Reglas
   * @param e
   */
  abstract void botonReglas_actionPerformed(ActionEvent e);


  abstract void botonEnviar_actionPerformed(ActionEvent e);


  abstract void botonRecibir_actionPerformed(ActionEvent e);

  /**
   * Función abstracta para controlar el action performed del botón Salir
   * @param e
   */
  abstract void botonSalir_actionPerformed(ActionEvent e);
}


//**************************************************
//*************************************************
//**************************************************

class boton1Jugador_mouseAdapter extends java.awt.event.MouseAdapter {
  FrameIntroGUI adaptee;

  boton1Jugador_mouseAdapter(FrameIntroGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.boton1Jugador_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.boton1Jugador_mouseExited(e);
  }
}

class botonJuegoRed_mouseAdapter extends java.awt.event.MouseAdapter {
  FrameIntroGUI adaptee;

  botonJuegoRed_mouseAdapter(FrameIntroGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonJuegoRed_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonJuegoRed_mouseExited(e);
  }
}

class botonDemo_mouseAdapter extends java.awt.event.MouseAdapter {
  FrameIntroGUI adaptee;

  botonDemo_mouseAdapter(FrameIntroGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonDemo_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonDemo_mouseExited(e);
  }
}

class botonEditar_mouseAdapter extends java.awt.event.MouseAdapter {
  FrameIntroGUI adaptee;

  botonEditar_mouseAdapter(FrameIntroGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonEditar_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonEditar_mouseExited(e);
  }
}

class botonAyuda_mouseAdapter extends java.awt.event.MouseAdapter {
  FrameIntroGUI adaptee;

  botonAyuda_mouseAdapter(FrameIntroGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonAyuda_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonAyuda_mouseExited(e);
  }
}

class botonReglas_mouseAdapter extends java.awt.event.MouseAdapter {
  FrameIntroGUI adaptee;

  botonReglas_mouseAdapter(FrameIntroGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonReglas_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonReglas_mouseExited(e);
  }
}

class botonRecibir_mouseAdapter extends java.awt.event.MouseAdapter {
  FrameIntroGUI adaptee;

  botonRecibir_mouseAdapter(FrameIntroGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonRecibir_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonRecibir_mouseExited(e);
  }
}

class botonEnviar_mouseAdapter extends java.awt.event.MouseAdapter {
  FrameIntroGUI adaptee;

  botonEnviar_mouseAdapter(FrameIntroGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonEnviar_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonEnviar_mouseExited(e);
  }
}

class botonSalir_mouseAdapter extends java.awt.event.MouseAdapter {
  FrameIntroGUI adaptee;

  botonSalir_mouseAdapter(FrameIntroGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonSalir_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonSalir_mouseExited(e);
  }


}

class botonDescargaSobre_mouseAdapter extends java.awt.event.MouseAdapter {
  FrameIntroGUI adaptee;

  botonDescargaSobre_mouseAdapter(FrameIntroGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonDescargaSobre_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonDescargaSobre_mouseExited(e);
  }
}
