//package editor;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: Interfaz gráfico de la clase Editor de Barajas</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Miguel Cayeiro
 * @version 1.0
 */

abstract public class editorBarajasGUI extends JFrame{
  private JSplitPane jSplitPane1 = new JSplitPane();
  private JSplitPane jSplitPane2 = new JSplitPane();
  private JPanel jPanel1 = new JPanel();
  private JLabel labelRaza = new JLabel();
  private JLabel labelNumeroCartas = new JLabel();
  private JLabel labelCartasSelec = new JLabel();
  private JLabel labelCartas = new JLabel();
  protected JLabel labelImagen = new JLabel();
  protected JTextField textoNumeroCartas = new JTextField();
  protected JTextField textoRaza = new JTextField();
  protected JComboBox comboNombreCarta = new JComboBox();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JScrollPane jScrollPane2 = new JScrollPane();
  protected JList listaSeleccionadas = new JList();
  protected JList listaDisponibles = new JList();

  //Variables pertenecientes al menu
  private JMenuBar jMenuBar1 = new JMenuBar();
  private JMenu jMenuFile = new JMenu();
  private JMenuItem jMenuFileCargar = new JMenuItem();
  protected JMenuItem jMenuFileGuardar = new JMenuItem();
  private JMenuItem jMenuFileGuardarComo = new JMenuItem();
  private JMenuItem jMenuFileExit = new JMenuItem();
  private JMenu jMenuHelp = new JMenu();
  private JMenuItem jMenuHelpAyuda = new JMenuItem();
  private JMenuItem jMenuHelpAbout = new JMenuItem();


  public editorBarajasGUI() {	
	//dibujamos el cursor
 	ImageIcon cursor = new ImageIcon("./imagenes/cursores/puntero.gif");
	Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(0,0), "img");
    this.setCursor(puntero);

    try {
      jbInit();
      crearMenu();

      //Centrar la ventana
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension frameSize = this.getSize();
      if (frameSize.height > screenSize.height) {
        frameSize.height = screenSize.height;
      }
      if (frameSize.width > screenSize.width) {
        frameSize.width = screenSize.width;
      }
      this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
      this.setVisible(true);

    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  
  
  private void jbInit() throws Exception {

    this.setUndecorated(true);

    this.setEnabled(true);
    this.setLocale(java.util.Locale.getDefault());
    this.setResizable(false);
    this.setSize(new Dimension(775, 540));
    this.setState(Frame.NORMAL);

    jPanel1.setLayout(null);
    jPanel1.setBackground(Color.lightGray);

    labelRaza.setBounds(new Rectangle(15, 10, 215, 40));
    labelRaza.setText("Raza de la Baraja:");
    labelRaza.setFont(new java.awt.Font("Serif", 3, 25));
    textoRaza.setFont(new java.awt.Font("Serif", 3, 25));
    textoRaza.setEditable(false);
    textoRaza.setText("");
    textoRaza.setHorizontalAlignment(SwingConstants.CENTER);
    textoRaza.setBounds(new Rectangle(230, 10, 200, 40));

    labelNumeroCartas.setFont(new java.awt.Font("Serif", 3, 20));
    labelNumeroCartas.setBounds(new Rectangle(15, 65, 290, 30));
    labelNumeroCartas.setText("Numero de Cartas en la Baraja:");
    textoNumeroCartas.setFont(new java.awt.Font("Serif", 3, 20));
    textoNumeroCartas.setEditable(false);
    textoNumeroCartas.setHorizontalAlignment(SwingConstants.CENTER);
    textoNumeroCartas.setBounds(new Rectangle(310, 65, 120, 30));

    labelCartasSelec.setText("Cartas Seleccionadas:");
    labelCartasSelec.setBounds(new Rectangle(15, 110, 200, 27));
    labelCartasSelec.setFont(new java.awt.Font("Serif", 3, 20));
    listaSeleccionadas.addMouseListener(new editorBarajasGUI_listSeleccionadas_mouseAdapter(this));
    jScrollPane1.setBounds(new Rectangle(15, 140, 196, 327));
    jScrollPane1.getViewport().add(listaSeleccionadas, null);

    labelCartas.setFont(new java.awt.Font("Serif", 3, 20));
    labelCartas.setText("Cartas Disponibles:");
    labelCartas.setBounds(new Rectangle(235, 110, 200, 27));
    listaDisponibles.addMouseListener(new editorBarajasGUI_listDisponibles_mouseAdapter(this));
    jScrollPane2.setBounds(new Rectangle(235, 140, 196, 327));
    jScrollPane2.getViewport().add(listaDisponibles, null);

    labelImagen.setFont(new java.awt.Font("Serif", 3, 15));
    labelImagen.setHorizontalAlignment(SwingConstants.CENTER);
    comboNombreCarta.addActionListener(new editorBarajasGUI_jComboNombreCarta_actionAdapter(this));
    comboNombreCarta.setFont(new java.awt.Font("Serif", 3, 20));

    jPanel1.add(textoNumeroCartas, null);
    jPanel1.add(textoRaza, null);
    jPanel1.add(jScrollPane1, null);
    jPanel1.add(jScrollPane2, null);
    jPanel1.add(labelRaza, null);
    jPanel1.add(labelNumeroCartas, null);
    jPanel1.add(labelCartas, null);
    jPanel1.add(labelCartasSelec, null);

    jSplitPane1.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
    jSplitPane1.setBackground(Color.lightGray);
    jSplitPane1.add(jSplitPane2, JSplitPane.RIGHT);
    jSplitPane1.add(jPanel1, JSplitPane.LEFT);
    jSplitPane1.setDividerLocation(450);

    jSplitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jSplitPane2.setBackground(Color.lightGray);
    jSplitPane2.add(comboNombreCarta, JSplitPane.TOP);
    jSplitPane2.add(labelImagen, JSplitPane.BOTTOM);
    jSplitPane2.setDividerLocation(35);
    jSplitPane1.setEnabled(false);
    jSplitPane2.setEnabled(false);

    this.getContentPane().add(jSplitPane1, BorderLayout.CENTER);
  }

  /**
   * Función que crea el menu de la pantalla del editor
   */
  private void crearMenu() {
    //menu desplegable Archivo
    jMenuFile.setText("Archivo");
    jMenuFileCargar.setText("Cargar Baraja");
    jMenuFileCargar.addActionListener(new editorBarajasGUI_jMenuFileCargar_actionAdapter(this));
    jMenuFileGuardar.setText("Guardar Baraja");
    jMenuFileGuardar.addActionListener(new editorBarajasGUI_jMenuFileGuardar_actionAdapter(this));
    jMenuFileGuardarComo.setText("Guardar Baraja Como ..");
    jMenuFileGuardarComo.addActionListener(new editorBarajasGUI_jMenuFileGuardarComo_actionAdapter(this));
    jMenuFileExit.setText("Salir");
    jMenuFileExit.addActionListener(new editorBarajasGUI_jMenuFileExit_actionAdapter(this));
    jMenuFile.add(jMenuFileCargar);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuFileGuardar);
    jMenuFile.add(jMenuFileGuardarComo);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuFileExit);
    //menu desplegable Ayuda
    jMenuHelp.setText("Ayudas");
    jMenuHelpAyuda.setText("Ayuda");
    jMenuHelpAyuda.addActionListener(new editorBarajasGUI_jMenuHelpAyuda_actionAdapter(this));
    jMenuHelpAbout.setText("Acerca de ..");
    jMenuHelpAbout.addActionListener(new editorBarajasGUI_jMenuHelpAbout_actionAdapter(this));
    jMenuHelp.add(jMenuHelpAyuda);
    jMenuHelp.add(jMenuHelpAbout);
    //añadimos al menu los menus desplegables
    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jMenuHelp);
    //añadimos el menu a la pantalla
    this.setJMenuBar(jMenuBar1);
  }

  /**
   *
   * @param e
   */
  abstract void jComboNombreCarta_actionPerformed(ActionEvent e);

  /**
   *
   * @param e
   */
  abstract void listSeleccionadas_mouseClicked(MouseEvent e);

  /**
   *
   * @param e
   */
  abstract void listDisponibles_mouseClicked(MouseEvent e);

  /**
   *
   * @param e
   */
  abstract void jMenuFileCargar_actionPerformed(ActionEvent e);

  /**
   *
   * @param e
   */
  abstract void jMenuFileGuardar_actionPerformed(ActionEvent e);

  /**
   *
   * @param e
   */
  abstract void jMenuFileGuardarComo_actionPerformed(ActionEvent e);

  /**
   *
   * @param e
   */
  abstract void jMenuFileExit_actionPerformed(ActionEvent e);

  /**
   *
   * @param e
   */
  abstract void jMenuHelpAyuda_actionPerformed(ActionEvent e);

  /**
   *
   * @param e
   */
  abstract void jMenuHelpAbout_actionPerformed(ActionEvent e);

}