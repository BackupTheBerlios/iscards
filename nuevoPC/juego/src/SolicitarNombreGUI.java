

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
  private JPanel jPanel1 = new JPanel();
  private GridBagLayout gridBagLayout1 = new GridBagLayout();
  private JSplitPane jSplitPane1 = new JSplitPane();
  private JSplitPane jSplitPane2 = new JSplitPane();
  private JLabel labelNombre = new JLabel();
  protected JTextField textNombre = new JTextField();
  private JSplitPane jSplitPane3 = new JSplitPane();
  private JButton botonAceptar = new JButton();
  private JButton botonCancelar = new JButton();

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
    this.setSize(new Dimension(426,110));

    this.setUndecorated(true);

    jPanel1.setLayout(gridBagLayout1);
    jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
    //añadimos el action listener al botón Aceptar
    labelNombre.setBackground(Color.lightGray);
    labelNombre.setFont(new java.awt.Font("Serif", 3, 25));
    labelNombre.setOpaque(true);
    labelNombre.setText("Nombre de Usuario:");
    textNombre.setFont(new java.awt.Font("Dialog", 0, 20));
    
    botonAceptar.setFont(new java.awt.Font("Serif", 3, 20));
    botonAceptar.setText("Aceptar");
    botonAceptar.setBackground(Color.lightGray);
    botonAceptar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        botonAceptar_actionPerformed(e);
      }
    });
    
    botonCancelar.setFont(new java.awt.Font("Serif", 3, 20));
    botonCancelar.setText("Cancelar");
    botonCancelar.setBackground(Color.lightGray); 
    botonCancelar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        botonCancelar_actionPerformed(e);
      }
    });
    
    this.getContentPane().add(jPanel1, BorderLayout.WEST);
    this.getContentPane().add(jSplitPane1, BorderLayout.CENTER);
    
    jSplitPane1.add(jSplitPane2, JSplitPane.BOTTOM);
    jSplitPane1.add(labelNombre, JSplitPane.TOP);
    jSplitPane2.add(textNombre, JSplitPane.LEFT);
    jSplitPane2.add(jSplitPane3, JSplitPane.RIGHT);
    jSplitPane3.add(botonAceptar, JSplitPane.TOP);
    jSplitPane3.add(botonCancelar, JSplitPane.BOTTOM);
    jSplitPane1.setDividerLocation(40);
    jSplitPane2.setDividerLocation(200);
    jSplitPane1.setEnabled(false);
    jSplitPane2.setEnabled(false);
    jSplitPane3.setDividerLocation(100);
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

