package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;




abstract public class EditorBarajasGUI extends JFrame{

  protected JLabel labelImagen = new JLabel();
  protected JComboBox comboNombreCarta = new JComboBox();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JScrollPane jScrollPane2 = new JScrollPane();
  protected JList listaSeleccionadas = new JList();
  protected JList listaDisponibles = new JList();
  protected int alto=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
  protected int ancho=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
  private JLabel labelFondo =new JLabel();
  protected JLabel textoNumeroCartas = new JLabel();
  protected JLabel textoRaza = new JLabel();
  private JButton botCargar= new JButton();
  protected JButton botGuardar=new JButton();
  private JButton botGuardarComo=new JButton();
  private JButton botSalir=new JButton();
  private JButton botAyuda=new JButton();
  private JButton botAcerca=new JButton();
  protected JLabel textoBarajaCargada = new JLabel();


  public EditorBarajasGUI() {
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

    this.setUndecorated(true);
    this.setSize(ancho,alto);

    labelFondo.setBounds(0,0,ancho,alto);
    labelFondo.setIcon(new ImageIcon("../imagenes/EditorBarajasGUI/fondoEditorBarajas.jpg"));

    botCargar.setIcon(new ImageIcon("../imagenes/EditorBarajasGUI/CargarBaraja.jpg"));
    botCargar.addMouseListener(new EditorBarajasGUI_botCargar_mouseAdapter(this));
    botGuardar.setIcon(new ImageIcon("../imagenes/EditorBarajasGUI/GuardarBaraja.jpg"));
    botGuardar.addMouseListener(new EditorBarajasGUI_botGuardar_mouseAdapter(this));
    botGuardarComo.setIcon(new ImageIcon("../imagenes/EditorBarajasGUI/GuardarBarajaComo.jpg"));
    botGuardarComo.addMouseListener(new EditorBarajasGUI_botGuardarComo_mouseAdapter(this));
    botSalir.setIcon(new ImageIcon("../imagenes/EditorBarajasGUI/Salir.jpg"));
    botSalir.addMouseListener(new EditorBarajasGUI_botSalir_mouseAdapter(this));
    botAyuda.setIcon(new ImageIcon("../imagenes/EditorBarajasGUI/Ayuda.jpg"));
    botAyuda.addMouseListener(new EditorBarajasGUI_botAyuda_mouseAdapter(this));
    botAcerca.setIcon(new ImageIcon("../imagenes/EditorBarajasGUI/AcercaDe.jpg"));
    botAcerca.addMouseMotionListener(new EditorBarajasGUI_botAcerca_mouseMotionAdapter(this));
    botAcerca.addMouseListener(new EditorBarajasGUI_botAcerca_mouseAdapter(this));
/*
    botCargar.setBounds(new Rectangle(ancho/6, 6*(alto/7), (int)(ancho/6.85), (int)(alto/27)));
    botGuardar.setBounds(new Rectangle((int)(4.5*(ancho/7)),6*(alto/7), (int)(ancho/6.85), (int)(alto/27) ));
    botGuardarComo.setBounds(new Rectangle((int)(1.9*(ancho/5)),6*(alto/7), (int)(ancho/5), (int)(alto/27) ));
    botAyuda.setBounds(new Rectangle(ancho/6, (int) (6.35*(alto/7)), (int)(ancho/6.85), (int)(alto/27) ));
    botAcerca.setBounds(new Rectangle((int)(ancho/2.5), (int) (6.35*(alto/7)),(int)(ancho/6.85), (int)(alto/27) ));
    botSalir.setBounds(new Rectangle((int)(4.5*(ancho/7)),(int) (6.35*(alto/7)), (int)(ancho/6.85), (int)(alto/27) ));


*/

   botCargar.setBounds(new Rectangle(ancho/6, (int)(6.09*(alto/7)), (int)(ancho/6.85), (int)(alto/27)));
    botCargar.setBorder(null);
   botGuardar.setBounds(new Rectangle((int)(4.5*(ancho/7)),(int)(6.09*(alto/7)), (int)(ancho/6.85), (int)(alto/27) ));
    botGuardar.setBorder(null);
   botGuardarComo.setBounds(new Rectangle((int)(1.9*(ancho/5)),(int)(6.09*(alto/7)), (int)(ancho/5.2), (int)(alto/27) ));
    botGuardarComo.setBorder(null);
   botAyuda.setBounds(new Rectangle(ancho/6, (int) (6.44*(alto/7)), (int)(ancho/6.85), (int)(alto/27) ));
    botAyuda.setBorder(null);
   botAcerca.setBounds(new Rectangle((int)(ancho/2.5), (int) (6.44*(alto/7)),(int)(ancho/6.85), (int)(alto/27) ));
    botAcerca.setBorder(null);
   botSalir.setBounds(new Rectangle((int)(4.5*(ancho/7)),(int) (6.44*(alto/7)), (int)(ancho/6.85), (int)(alto/27) ));
    botSalir.setBorder(null);

   textoRaza.setBounds(2*(ancho/7),alto/7,ancho/8,alto/20);
   textoRaza.setFont(new java.awt.Font("Serif", 3, 22));
   textoRaza.setBackground(Color.gray);
   textoRaza.setOpaque(true);

   textoNumeroCartas.setBounds(2*(ancho/7),alto/5,ancho/15,alto/20);
   textoNumeroCartas.setFont(new java.awt.Font("Serif", 3, 22));
   textoNumeroCartas.setBackground(Color.gray);
   textoNumeroCartas.setOpaque(true);


   listaSeleccionadas.addMouseListener(new EditorBarajasGUI_listSeleccionadas_mouseAdapter(this));
   listaSeleccionadas.setBackground(Color.gray);
   jScrollPane1.setBounds(new Rectangle((int)(ancho/10.3), (int)(alto/2.5), ancho/5, alto/3));
   jScrollPane1.getViewport().add(listaSeleccionadas, null);


   listaDisponibles.addMouseListener(new EditorBarajasGUI_listDisponibles_mouseAdapter(this));
   listaDisponibles.setBackground(Color.gray);
   jScrollPane2.setBounds(new Rectangle((int)(3.75*(ancho/10.3)), (int)(alto/2.5), ancho/5, alto/3));
   jScrollPane2.getViewport().add(listaDisponibles, null);

   labelImagen.setFont(new java.awt.Font("Serif", 3, 15));
   labelImagen.setBounds((int)(4.22*(ancho/7)),(int)(0.95*(alto/5)),(int)((ancho/3.29)),(int)(alto/1.72));
   labelImagen.setBackground(Color.DARK_GRAY);
   labelImagen.setOpaque(true);

   comboNombreCarta.addActionListener(new EditorBarajasGUI_jComboNombreCarta_actionAdapter(this));
   comboNombreCarta.setFont(new java.awt.Font("Serif", 3, 20));
   comboNombreCarta.setBounds((int)(4.3*(ancho/7)),alto/7,(int)((ancho/3.5)),alto/25);

   textoBarajaCargada.setFont(new java.awt.Font("Serif", 3, 20));
   textoBarajaCargada.setBounds((int)(ancho/2.25),(int)(4.8*(alto/6)),ancho/5,alto/22);
   textoBarajaCargada.setBackground(Color.gray);
   //Quitaremos el opaque cuando sepamos q la etiqueta esta bien ahi
   textoBarajaCargada.setOpaque(true);


    this.getContentPane().add(textoBarajaCargada,null);
    this.getContentPane().add(comboNombreCarta,null);
    this.getContentPane().add(labelImagen,null);
    this.getContentPane().add(jScrollPane2, null);
    this.getContentPane().add(jScrollPane1, null);
    this.getContentPane().add(textoNumeroCartas,null);
    this.getContentPane().add(textoRaza,null);
    this.getContentPane().add(botCargar,null);
    this.getContentPane().add(botGuardar,null);
    this.getContentPane().add(botGuardarComo,null);
    this.getContentPane().add(botAyuda,null);
    this.getContentPane().add(botAcerca,null);
    this.getContentPane().add(botSalir,null);

    this.getContentPane().add(labelFondo,null);
  /*  this.setEnabled(true);
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
  */}

  /**
   * Función que crea el menu de la pantalla del editor
   */
  private void crearMenu() {
    //menu desplegable Archivo
   /* jMenuFile.setText("Archivo");
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
  */}

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

  abstract void botCargar_mouseClicked(MouseEvent e);

  abstract void botGuardar_mouseClicked(MouseEvent e) ;

  abstract void botGuardarComo_mouseClicked(MouseEvent e);

  abstract void botAyuda_mouseClicked(MouseEvent e) ;

  abstract void botSalir_mouseClicked(MouseEvent e);

  abstract void botAcerca_mouseClicked(MouseEvent e);

  void botCargar_mouseEntered(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botGuardar_mouseEntered(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botGuardarComo_mouseEntered(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botAyuda_mouseEntered(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botAcerca_mouseEntered(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botSalir_mouseEntered(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botSalir_mouseExited(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botAcerca_mouseExited(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botAyuda_mouseExited(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botGuardarComo_mouseExited(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botGuardar_mouseExited(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

  void botCargar_mouseExited(MouseEvent e) {
    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
    Image image = cursor.getImage();
    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
    this.setCursor(puntero);

  }

}

class EditorBarajasGUI_botCargar_mouseAdapter extends java.awt.event.MouseAdapter {
  EditorBarajasGUI adaptee;

  EditorBarajasGUI_botCargar_mouseAdapter(EditorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.botCargar_mouseClicked(e);
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botCargar_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botCargar_mouseExited(e);
  }
}

class EditorBarajasGUI_botGuardar_mouseAdapter extends java.awt.event.MouseAdapter {
  EditorBarajasGUI adaptee;

  EditorBarajasGUI_botGuardar_mouseAdapter(EditorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.botGuardar_mouseClicked(e);
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botGuardar_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botGuardar_mouseExited(e);
  }
}

class EditorBarajasGUI_botGuardarComo_mouseAdapter extends java.awt.event.MouseAdapter {
  EditorBarajasGUI adaptee;

  EditorBarajasGUI_botGuardarComo_mouseAdapter(EditorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.botGuardarComo_mouseClicked(e);
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botGuardarComo_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botGuardarComo_mouseExited(e);
  }
}

class EditorBarajasGUI_botAyuda_mouseAdapter extends java.awt.event.MouseAdapter {
  EditorBarajasGUI adaptee;

  EditorBarajasGUI_botAyuda_mouseAdapter(EditorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.botAyuda_mouseClicked(e);
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botAyuda_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botAyuda_mouseExited(e);
  }
}

class EditorBarajasGUI_botSalir_mouseAdapter extends java.awt.event.MouseAdapter {
  EditorBarajasGUI adaptee;

  EditorBarajasGUI_botSalir_mouseAdapter(EditorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.botSalir_mouseClicked(e);
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botSalir_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botSalir_mouseExited(e);
  }
}

class EditorBarajasGUI_botAcerca_mouseAdapter extends java.awt.event.MouseAdapter {
  EditorBarajasGUI adaptee;

  EditorBarajasGUI_botAcerca_mouseAdapter(EditorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.botAcerca_mouseClicked(e);
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botAcerca_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botAcerca_mouseExited(e);
  }
}

class EditorBarajasGUI_botAcerca_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {
  EditorBarajasGUI adaptee;

  EditorBarajasGUI_botAcerca_mouseMotionAdapter(EditorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }

}