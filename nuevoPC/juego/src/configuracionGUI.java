//package configuracion;


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

 abstract public class configuracionGUI extends JFrame{

   JPanel jPanel1 = new JPanel();
   JLabel jLabel1 = new JLabel();
   JButton botonEditar = new JButton();
   JButton botonAceptar = new JButton();
   JButton botonCancelar = new JButton();
   BorderLayout borderLayout1 = new BorderLayout();
   GridBagLayout gridBagLayout1 = new GridBagLayout();
   JScrollPane jScrollPane1 = new JScrollPane();
   JList listBarajas = new JList();

   /**
    * Constructora de la clase
    */
   public configuracionGUI() {
 
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
    * Función de inicialización del frame Configuración
    * @throws java.lang.Exception
    */
   private void jbInit() throws Exception {
     this.setResizable(false);
     this.setUndecorated(true);
     this.setSize(new Dimension(483, 422));
     this.getContentPane().setLayout(borderLayout1);
     

     jLabel1.setBackground(Color.lightGray);
     jLabel1.setFont(new java.awt.Font("Serif", 3, 25));
     jLabel1.setOpaque(true);
     jLabel1.setText("BARAJAS GUARDADAS");
     
     //creamos los botones
     botonEditar.setBackground(Color.lightGray);
     botonEditar.setFont(new java.awt.Font("Serif", 3, 20));
     botonEditar.setText("Editar Baraja");
     botonEditar.addActionListener(new configuracionGUI_botonEditar_actionAdapter(this));
     botonAceptar.setBackground(Color.lightGray);
     botonAceptar.setFont(new java.awt.Font("Serif", 3, 20));
     botonAceptar.setText("Aceptar");
     botonAceptar.addActionListener(new configuracionGUI_botonAceptar_actionAdapter(this));
     botonCancelar.setBackground(Color.lightGray);
     botonCancelar.setFont(new java.awt.Font("Serif", 3, 20));
     botonCancelar.setText("Cancelar");
     botonCancelar.addActionListener(new configuracionGUI_botonCancelar_actionAdapter(this));

     jPanel1.setLayout(gridBagLayout1);
     jPanel1.setBackground(Color.lightGray);
     jPanel1.add(jLabel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
         ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(12, 3, 14, 0), 0, 14));
     jPanel1.add(botonCancelar, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
         ,GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(17, 2, 1, 1), 0, 14));
     jPanel1.add(botonAceptar, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
         ,GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(4, 0, 1, 0), 10, 13));
     jPanel1.add(botonEditar, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
         ,GridBagConstraints.SOUTH, GridBagConstraints.NONE, new Insets(137, 15, 1, 3), 18, 14));
     jPanel1.add(jScrollPane1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
         ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 209, 226));
     listBarajas.setFont(new java.awt.Font("Serif", 3, 20));  
     jScrollPane1.getViewport().add(listBarajas, null);
    
     this.getContentPane().add(jPanel1, BorderLayout.CENTER);
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

}