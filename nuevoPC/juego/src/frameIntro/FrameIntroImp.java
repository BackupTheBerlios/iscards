package frameIntro;

import coleccion.*;
import usuario.*;
import configuracion.*;
import editor.*;
import panelesInfo.*;
import comunicacion.*;

import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * <p>T�tulo: GENESIS</p>
 * <p>Descripci�n: Implementaci�n del interfaz gr�fico del frame de inicio</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Rui Miguel Alonso
 * @version 1.0
 */

public class FrameIntroImp extends FrameIntroGUI {

  int ancho=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
  int alto=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();


  /**
   * coleccion de cartas del juego "coleccion.car" donde est�n todas las cartas disponibles durante el juego
   */
  private Coleccion coleccion;

  /**
   * usuario registrado en el juego para la partida
   */
  private Usuario usuario;

  /**
   * Constructora de la clase
   * @param usu usuario que ha iniciado la partida
   * @param col coleccion de las cartas
   */
  public FrameIntroImp(Usuario usu, Coleccion col) {
    coleccion = col;
    usuario = usu;
  }

  public FrameIntroImp() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
 
  private void jbInit() throws Exception {
    this.getContentPane().setBackground(SystemColor.controlText);
  }

  /**
   * Funci�n actionPerformed del bot�n 1 Jugador
   * @param e
   */
  void boton1Jugador_actionPerformed(ActionEvent e) {
    //mostramos el frame de Configuraci�n de la partida
    ConfiguracionImp conf = new ConfiguracionImp(this, coleccion, usuario, false);
    conf.show();
  }

  /**
   * Funci�n actionPerformed del bot�n Juego en Red
   * @param e
   */

  void botonJuegoRed_actionPerformed(ActionEvent e) {
  	
  	//mostramos el frame de Configuraci�n de la partida, que sera distinto del de un jugador
  	try{
    	ConfiguracionImp conf2 = new ConfiguracionImp(this, coleccion, usuario,true);
    	conf2.show();
	}catch(Exception e2){e2.printStackTrace();}
	
    //Activamos el chat
 /*   GestorUsuarios gestorUsuarios = new GestorUsuarios();
    Controlador controlador = new Controlador(gestorUsuarios, usuario);
    GUI ventana = new GUI(controlador,this);*/
  }

  /**
   * Funci�n actionPerformed del bot�n Demo
   * @param e
   */
  void botonDemo_actionPerformed(ActionEvent e) {
    //mostramos el frame de Configuraci�n de la partida
    ConfiguracionImp conf = new ConfiguracionImp(this, coleccion, usuario, false);
    conf.show();
  }

  /**
   * Funci�n actionPerformed del bot�n Editar barajas
   * @param e
   */
  void botonEditar_actionPerformed(ActionEvent e) {

    EligeRazaGUI panelE= new EligeRazaGUI(coleccion,usuario);
    panelE.show();
}

  /**
   * Funci�n actionPerformed del bot�n mostrar Ayuda
   * @param e
   */
  void botonAyuda_actionPerformed(ActionEvent e) {
    //creamos el pane para mostrar el archivo

  /**
   * Funci�n que abre el navegador para visitar la pagina del servidor de internet
   * @param e
   */
      Runtime r = Runtime.getRuntime();
      Process p = null;
      try {
        p = r.exec("EXPLORER file://C:/hlocal/Genesis1.9/documentos/genesis.htm");
      }
      catch (Exception q){
         JOptionPane.showMessageDialog(null, q.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
      }
/*
    AyudaGUI panelA = new AyudaGUI();
    panelA.setSize(ancho,alto);
    panelA.show();
*/
  }

  /**
   * Funci�n actionPerformed del bot�n mostrar Reglas
   * @param e
   */
  void botonReglas_actionPerformed(ActionEvent e) {
      Runtime r = Runtime.getRuntime();
      Process p = null;
      try {
        p = r.exec("EXPLORER file://C:/hlocal/Genesis1.9/documentos/genesis.htm");
      }
      catch (Exception q){
         JOptionPane.showMessageDialog(null, q.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
      }
/*
     PanelReglas panelR =new PanelReglas();
     panelR.show();
*/
}

  /**
   * Funci�n actionPerformed del bot�n Salir
   * @param e
   */
  void botonSalir_actionPerformed(ActionEvent e) {
    PanelSalir panel=new PanelSalir();
    panel.show();
  }

  void botonEnviar_actionPerformed(ActionEvent e) {
//  	ServidorBTPC serv=new ServidorBTPC(coleccion,usuario);
//  	serv.iniciaServidor();
  }

  void botonRecibir_actionPerformed(ActionEvent e) {
//  	ClienteBTPC cliente=new ClienteBTPC(coleccion,usuario);
  }


  void botonAcepta_actionPerformed(ActionEvent e){
    this.hide();
  };

  void botonAcepta_mouseEntered(MouseEvent e) {

  }

  void botonAcepta_mouseExited(MouseEvent e) {


  }

}


class botonAcepta_mouseAdapter extends java.awt.event.MouseAdapter {
  FrameIntroImp adaptee;

  botonAcepta_mouseAdapter(FrameIntroImp adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonAcepta_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonAcepta_mouseExited(e);
  }
}



class frameIntroImp_botonAcepta_actionAdapter implements ActionListener {
  FrameIntroImp adaptee;

  frameIntroImp_botonAcepta_actionAdapter(FrameIntroImp adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botonAcepta_actionPerformed(e);
  }
}
