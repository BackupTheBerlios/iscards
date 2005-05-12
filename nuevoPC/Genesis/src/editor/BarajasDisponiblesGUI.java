package editor;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import padrePaneles.*;


/**
 * <p>Título: </p>
 * <p>Descripción: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Empresa: </p>
 * @author sin atribuir
 * @version 1.0
 */

abstract public class BarajasDisponiblesGUI extends Container{

  protected int alto=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
  protected int ancho=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
  private JLabel labelFondo = new JLabel();
  private JButton botonCancelar = new JButton();
  private JButton botonAceptar = new JButton();
  protected JList listaBarajas = new JList();
  protected DefaultListModel dlmBarajas;
  protected JScrollPane scroll = new JScrollPane();



  public BarajasDisponiblesGUI() {

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

     this.setSize(ancho,alto);
     this.setLocation(0,0);


     //límites de componentes
     labelFondo.setBounds(ancho/4,alto/10,ancho/2,8*(alto/10));
     botonCancelar.setBounds(new Rectangle((int)(2.3*(ancho/5)), (int)(3*(alto/4)), (int)(ancho/10.2), alto/25));
     botonCancelar.setBorder(null);
     botonAceptar.setBounds(new Rectangle((int)(1.65*(ancho/5)), (int)(3*(alto/4)), (int)(ancho/10.2), alto/25));
     botonAceptar.setBorder(null);



     listaBarajas.setBackground(Color.gray);
     scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
     scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
     scroll.setBounds(new Rectangle((int) (1.61*(ancho / 5)), (int) (alto / 2.6), (int)(1.23*(ancho / 5)), (int)(alto / 3.1)));

     dlmBarajas =new javax.swing.DefaultListModel();
     this.listaBarajas.setModel(dlmBarajas);
     scroll.getViewport().add(listaBarajas, null);





     //imagenes de componentes
      labelFondo.setIcon(new ImageIcon("../imagenes/BarajasDisponibles.jpg"));
      botonCancelar.setIcon(new ImageIcon("../imagenes/botoncancelar.jpg"));
      botonCancelar.addMouseListener(new BarajasDisponiblesGUI_botonCancelar_mouseAdapter(this));
      botonAceptar.setIcon(new ImageIcon("../imagenes/botonaceptar.jpg"));
      botonAceptar.addMouseListener(new BarajasDisponiblesGUI_botonAceptar_mouseAdapter(this));


     //agregar componentes al panel
     this.add(scroll);
     this.add(botonCancelar, null);
     this.add(botonAceptar, null);
     this.add(labelFondo, null);


     //acciones de botones
     botonAceptar.addActionListener(new BarajasDisponiblesGUI_botonAceptar_actionAdapter(this));
     botonCancelar.addActionListener(new BarajasDisponiblesGUI_botonCancelar_actionAdapter(this));

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

void botonCancelar_mouseEntered(MouseEvent e) {
 ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
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

void botonCancelar_mouseExited(MouseEvent e) {
 ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
 Image image = cursor.getImage();
 Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
 this.setCursor(puntero);

}

}

class BarajasDisponiblesGUI_botonAceptar_actionAdapter implements java.awt.event.ActionListener {
private BarajasDisponiblesGUI adaptee;

BarajasDisponiblesGUI_botonAceptar_actionAdapter(BarajasDisponiblesGUI adaptee) {
 this.adaptee = adaptee;
}
public void actionPerformed(ActionEvent e) {
 adaptee.botonAceptar_actionPerformed(e);
}
}

class BarajasDisponiblesGUI_botonCancelar_actionAdapter implements java.awt.event.ActionListener {
private BarajasDisponiblesGUI adaptee;

BarajasDisponiblesGUI_botonCancelar_actionAdapter(BarajasDisponiblesGUI adaptee) {
 this.adaptee = adaptee;
}
public void actionPerformed(ActionEvent e) {
 adaptee.botonCancelar_actionPerformed(e);
}
}

class BarajasDisponiblesGUI_botonCancelar_mouseAdapter extends java.awt.event.MouseAdapter {
BarajasDisponiblesGUI adaptee;

BarajasDisponiblesGUI_botonCancelar_mouseAdapter(BarajasDisponiblesGUI adaptee) {
 this.adaptee = adaptee;
}
public void mouseEntered(MouseEvent e) {
 adaptee.botonCancelar_mouseEntered(e);
}
public void mouseExited(MouseEvent e) {
 adaptee.botonCancelar_mouseExited(e);
}
}

class BarajasDisponiblesGUI_botonAceptar_mouseAdapter extends java.awt.event.MouseAdapter {
BarajasDisponiblesGUI adaptee;

BarajasDisponiblesGUI_botonAceptar_mouseAdapter(BarajasDisponiblesGUI adaptee) {
 this.adaptee = adaptee;
}
public void mouseEntered(MouseEvent e) {
 adaptee.botonAceptar_mouseEntered(e);
}
public void mouseExited(MouseEvent e) {
 adaptee.botonAceptar_mouseExited(e);
}
}










