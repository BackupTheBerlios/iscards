
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

abstract public class frameIntroGUI extends JFrame {

  JPanel contentPane;
  private JPanel panelPrincipal = new JPanel();
  JPanel panelBotones = new JPanel();
  JButton boton1Jugador = new JButton();
  JButton botonJuegoRed = new JButton();
  JButton botonEditar = new JButton();
  JButton botonDemo = new JButton();
  JButton botonReglas = new JButton();
  JButton botonAyuda = new JButton();
  JButton botonRecibir = new JButton();
  JButton botonEnviar = new JButton();
  JButton botonSalir = new JButton();
  JLabel labelFondo = new JLabel();

  /**
   * Constructora de la clase
   */
  public frameIntroGUI() {
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
 	ImageIcon cursor = new ImageIcon("./imagenes/cursores/puntero.gif");
	Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(0,0), "img");
    this.setCursor(puntero);

    //adaptamos el frame del tablero al tamaño máximo de la pantalla (con los SplitPane no se puede hacer de momento porque se desajusta)
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.setSize(screenSize.width, screenSize.height);

    contentPane = (JPanel)this.getContentPane();
    contentPane.setLayout(null);

	contentPane.add(panelBotones, null);
    contentPane.add(panelPrincipal, null);

	//quitamos la barra de titulo
    this.setUndecorated(true);
    this.setResizable(false);

 	ImageIcon fondo = new ImageIcon("./imagenes/fondos/fondo.jpg");
 	labelFondo.setIcon(fondo);
    labelFondo.setBounds(new Rectangle(new Point(0,0),Toolkit.getDefaultToolkit().getScreenSize()));

    panelPrincipal.setLayout(null);
    panelPrincipal.setSize(Toolkit.getDefaultToolkit().getScreenSize());
	panelPrincipal.add(labelFondo, null);

	//añadimos los botones
    boton1Jugador.setBackground(Color.black);
    boton1Jugador.setBounds(new Rectangle(150, 0, 150,25 ));
    boton1Jugador.setFont(new java.awt.Font("Serif", 3, 25));
    boton1Jugador.setForeground(Color.orange);
//    boton1Jugador.setBorderPainted(false);
//    boton1Jugador.setContentAreaFilled(false);
    boton1Jugador.setText("1 Jugador");
    boton1Jugador.addActionListener(new frameIntroGUI_boton1Jugador_actionAdapter(this));
    boton1Jugador.addMouseListener(new boton1Jugador_mouseAdapter(this));

    botonJuegoRed.setBackground(Color.black);
    botonJuegoRed.setBounds(new Rectangle(310, 0, 180,25 ));
    botonJuegoRed.setFont(new java.awt.Font("Serif", 3, 25));
    botonJuegoRed.setForeground(Color.orange);
//    botonJuegoRed.setBorderPainted(false);
//    botonJuegoRed.setContentAreaFilled(false);
    botonJuegoRed.setText("Juego en Red");
    botonJuegoRed.addActionListener(new frameIntroGUI_botonJuegoRed_actionAdapter(this));
    botonJuegoRed.addMouseListener(new botonJuegoRed_mouseAdapter(this));

    botonDemo.setBackground(Color.black);
    botonDemo.setBounds(new Rectangle(500, 0, 100,25 ));
    botonDemo.setFont(new java.awt.Font("Serif", 3, 25));
    botonDemo.setForeground(Color.orange);
//    botonDemo.setBorderPainted(false);
//    botonDemo.setContentAreaFilled(false);
    botonDemo.setText("Demo");
    botonDemo.addActionListener(new frameIntroGUI_botonDemo_actionAdapter(this));
    botonDemo.addMouseListener(new botonDemo_mouseAdapter(this));

    botonEditar.setBackground(Color.black);
    botonEditar.setBounds(new Rectangle(610, 0, 200,25 ));
    botonEditar.setFont(new java.awt.Font("Serif", 3, 25));
    botonEditar.setForeground(Color.orange);
//    botonEditar.setBorderPainted(false);
//    botonEditar.setContentAreaFilled(false);
    botonEditar.setText("Editar barajas");
    botonEditar.addActionListener(new frameIntroGUI_botonEditar_actionAdapter(this));
    botonEditar.addMouseListener(new botonEditar_mouseAdapter(this));

    botonReglas.setBackground(Color.black);
    botonReglas.setBounds(new Rectangle(200, 40, 150,25 ));
    botonReglas.setFont(new java.awt.Font("Serif", 3, 25));
    botonReglas.setForeground(Color.orange);
//    botonReglas.setBorderPainted(false);
//    botonReglas.setContentAreaFilled(false);
    botonReglas.setText("Reglas");
    botonReglas.addActionListener(new frameIntroGUI_botonReglas_actionAdapter(this));
    botonReglas.addMouseListener(new botonReglas_mouseAdapter(this));

    botonAyuda.setBackground(Color.black);
    botonAyuda.setBounds(new Rectangle(360, 40, 150,25 ));
    botonAyuda.setFont(new java.awt.Font("Serif", 3, 25));
    botonAyuda.setForeground(Color.orange);
//    botonAyuda.setBorderPainted(false);
//    botonAyuda.setContentAreaFilled(false);
    botonAyuda.setText("Ayuda");
    botonAyuda.addActionListener(new frameIntroGUI_botonAyuda_actionAdapter(this));
    botonAyuda.addMouseListener(new botonAyuda_mouseAdapter(this));

    botonRecibir.setBackground(Color.black);
    botonRecibir.setBounds(new Rectangle(520, 40, 150,25 ));
    botonRecibir.setFont(new java.awt.Font("Serif", 3, 25));
    botonRecibir.setForeground(Color.orange);
//    botonRecibir.setBorderPainted(false);
//    botonRecibir.setContentAreaFilled(false);
    botonRecibir.setText("Recibir");
    botonRecibir.addActionListener(new frameIntroGUI_botonRecibir_actionAdapter(this));
    botonRecibir.addMouseListener(new botonRecibir_mouseAdapter(this));

    botonEnviar.setBackground(Color.black);
    botonEnviar.setBounds(new Rectangle(680, 40, 150,25 ));
    botonEnviar.setFont(new java.awt.Font("Serif", 3, 25));
    botonEnviar.setForeground(Color.orange);
//    botonEnviar.setBorderPainted(false);
//    botonEnviar.setContentAreaFilled(false);
    botonEnviar.setText("Enviar");
    botonEnviar.addActionListener(new frameIntroGUI_botonEnviar_actionAdapter(this));
    botonEnviar.addMouseListener(new botonEnviar_mouseAdapter(this));

    botonSalir.setBackground(Color.black);
    botonSalir.setBounds(new Rectangle(450, 80, 120,25 ));
    botonSalir.setFont(new java.awt.Font("Serif", 3, 25));
    botonSalir.setForeground(Color.orange);
//    botonSalir.setBorderPainted(false);
//    botonSalir.setContentAreaFilled(false);
    botonSalir.setText("Salir");
    botonSalir.addActionListener(new frameIntroGUI_botonSalir_actionAdapter(this));
    botonSalir.addMouseListener(new botonSalir_mouseAdapter(this));

    panelBotones.setBounds(new Rectangle(0,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-150, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-100, 200));
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
    panelBotones.add(botonSalir, null);

 }

  void boton1Jugador_mouseEntered(MouseEvent e) {
  	boton1Jugador.setForeground(Color.white);
  }

  void boton1Jugador_mouseExited(MouseEvent e) {
    boton1Jugador.setForeground(Color.orange);
  }

  void botonJuegoRed_mouseEntered(MouseEvent e) {
  	botonJuegoRed.setForeground(Color.white);
  }

  void botonJuegoRed_mouseExited(MouseEvent e) {
    botonJuegoRed.setForeground(Color.orange);
  }

  void botonDemo_mouseEntered(MouseEvent e) {
    botonDemo.setForeground(Color.white);
  }

  void botonDemo_mouseExited(MouseEvent e) {
    botonDemo.setForeground(Color.orange);
  }

  void botonAyuda_mouseEntered(MouseEvent e) {
    botonAyuda.setForeground(Color.white);
  }

  void botonAyuda_mouseExited(MouseEvent e) {
    botonAyuda.setForeground(Color.orange);
  }

  void botonEditar_mouseEntered(MouseEvent e) {
    botonEditar.setForeground(Color.white);
  }

  void botonEditar_mouseExited(MouseEvent e) {
    botonEditar.setForeground(Color.orange);
  }

  void botonReglas_mouseEntered(MouseEvent e) {
    botonReglas.setForeground(Color.white);
  }

  void botonReglas_mouseExited(MouseEvent e) {
    botonReglas.setForeground(Color.orange);
  }

  void botonRecibir_mouseEntered(MouseEvent e) {
    botonRecibir.setForeground(Color.white);
  }

  void botonRecibir_mouseExited(MouseEvent e) {
    botonRecibir.setForeground(Color.orange);
  }

  void botonEnviar_mouseEntered(MouseEvent e) {
    botonEnviar.setForeground(Color.white);
  }

  void botonEnviar_mouseExited(MouseEvent e) {
    botonEnviar.setForeground(Color.orange);
  }

  void botonSalir_mouseEntered(MouseEvent e) {
    botonSalir.setForeground(Color.white);
  }

  void botonSalir_mouseExited(MouseEvent e) {
    botonSalir.setForeground(Color.orange);
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
  frameIntroGUI adaptee;

  boton1Jugador_mouseAdapter(frameIntroGUI adaptee) {
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
  frameIntroGUI adaptee;

  botonJuegoRed_mouseAdapter(frameIntroGUI adaptee) {
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
  frameIntroGUI adaptee;

  botonDemo_mouseAdapter(frameIntroGUI adaptee) {
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
  frameIntroGUI adaptee;

  botonEditar_mouseAdapter(frameIntroGUI adaptee) {
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
  frameIntroGUI adaptee;

  botonAyuda_mouseAdapter(frameIntroGUI adaptee) {
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
  frameIntroGUI adaptee;

  botonReglas_mouseAdapter(frameIntroGUI adaptee) {
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
  frameIntroGUI adaptee;

  botonRecibir_mouseAdapter(frameIntroGUI adaptee) {
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
  frameIntroGUI adaptee;

  botonEnviar_mouseAdapter(frameIntroGUI adaptee) {
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
  frameIntroGUI adaptee;

  botonSalir_mouseAdapter(frameIntroGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonSalir_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonSalir_mouseExited(e);
  }
}
