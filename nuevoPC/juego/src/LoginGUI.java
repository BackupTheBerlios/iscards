
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
  private JPanel jPanel1 = new JPanel();
  private GridBagLayout gridBagLayout1 = new GridBagLayout();
  private JSplitPane jSplitPane1 = new JSplitPane();
  private JSplitPane jSplitPane2 = new JSplitPane();
  private JSplitPane jSplitPane3 = new JSplitPane();
  private JSplitPane jSplitPane4 = new JSplitPane();
  private JSplitPane jSplitPane5 = new JSplitPane();
  private JLabel jLabel1 = new JLabel();
  private JButton botonCargar = new JButton();
  private JButton botonNuevo = new JButton();
  private JScrollPane jScrollPane1 = new JScrollPane();
  protected JList listaUsuarios = new JList();
  private JSplitPane jSplitPane6 = new JSplitPane();
  private JButton botonBorrar = new JButton();
  private JButton botonSalir = new JButton();
   private JButton jButton1 = new JButton();

  /**
   * Constructora de la clase
   */
  public LoginGUI() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);

	//dibujamos el cursor
 	ImageIcon cursor = new ImageIcon("./imagenes/cursores/puntero.gif");
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
    this.setResizable(false);
    this.setUndecorated(true);
    this.setSize(new Dimension(591, 578));
    this.setLocale(java.util.Locale.getDefault());

    jPanel1.setLayout(gridBagLayout1);
    jPanel1.setBackground(Color.lightGray);

    jSplitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jSplitPane3.setOrientation(JSplitPane.VERTICAL_SPLIT);

    jSplitPane3.setBackground(Color.lightGray);
    jSplitPane3.setOpaque(false);
	jSplitPane4.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jSplitPane5.setOrientation(JSplitPane.VERTICAL_SPLIT);
	jSplitPane6.setOrientation(JSplitPane.VERTICAL_SPLIT);

    jLabel1.setBackground(Color.lightGray);
    jLabel1.setFont(new java.awt.Font("Serif", 3, 25));
    jLabel1.setForeground(Color.black);
    jLabel1.setOpaque(true);
    jLabel1.setText("Usuarios Registrados");
    listaUsuarios.setFont(new java.awt.Font("Dialog", 0, 20));

    //creamos los botones y les añadimos los action listener
    botonCargar.setBackground(Color.lightGray);
    botonCargar.setFont(new java.awt.Font("Serif", 3, 25));
    botonCargar.setText("Cargar usuario");
    botonCargar.addActionListener(new LoginGUI_botonCargar_actionAdapter(this));
    botonNuevo.setBackground(Color.lightGray);
    botonNuevo.setFont(new java.awt.Font("Serif", 3, 25));
    botonNuevo.setText("Nuevo usuario");
    botonNuevo.addActionListener(new LoginGUI_botonNuevo_actionAdapter(this));
    botonBorrar.setBackground(Color.lightGray);
    botonBorrar.setFont(new java.awt.Font("Serif", 3, 25));
    botonBorrar.setText("Borrar usuario");
    botonBorrar.addActionListener(new LoginGUI_botonBorrar_actionAdapter(this));
    botonSalir.setBackground(Color.lightGray);
    botonSalir.setFont(new java.awt.Font("Serif", 3, 25));
    botonSalir.setText("Salir");
    botonSalir.addActionListener(new LoginGUI_botonSalir_actionAdapter(this));

    jButton1.setBackground(Color.lightGray);

    jButton1.setEnabled(false);


    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jSplitPane1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,GridBagConstraints.CENTER,
        GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 133, 326));

    jSplitPane1.add(jSplitPane2, JSplitPane.LEFT);
    jSplitPane2.add(jLabel1, JSplitPane.TOP);
    jSplitPane2.add(jScrollPane1, JSplitPane.BOTTOM);
    jSplitPane1.add(jSplitPane3, JSplitPane.RIGHT);
    jSplitPane3.add(jSplitPane4, JSplitPane.LEFT);
    jSplitPane4.add(jSplitPane5, JSplitPane.TOP);
    jSplitPane5.add(botonCargar, JSplitPane.BOTTOM);
    jSplitPane5.add(botonNuevo, JSplitPane.TOP);
    jSplitPane4.add(jSplitPane6, JSplitPane.BOTTOM);
    jSplitPane6.add(botonBorrar, JSplitPane.TOP);
    jSplitPane6.add(botonSalir, JSplitPane.BOTTOM);

    jSplitPane3.add(jButton1, JSplitPane.RIGHT);
    jScrollPane1.getViewport().add(listaUsuarios, null);

    jSplitPane1.setDividerLocation(300);
    jSplitPane2.setDividerLocation(50);
    jSplitPane3.setDividerLocation(210);
    jSplitPane4.setDividerLocation(100);
    jSplitPane5.setDividerLocation(50);
    jSplitPane1.setEnabled(false);
    jSplitPane2.setEnabled(false);
    jSplitPane3.setEnabled(false);
    jSplitPane4.setEnabled(false);
    jSplitPane5.setEnabled(false);
    jSplitPane6.setDividerLocation(50);
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

