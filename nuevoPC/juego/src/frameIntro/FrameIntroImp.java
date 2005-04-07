package frameIntro;


import coleccion.Coleccion;
import usuario.Usuario;
import configuracion.ConfiguracionImp;
import editor.EditorBarajasImp;

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

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: Implementación del interfaz gráfico del frame de inicio</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Rui Miguel Alonso
 * @version 1.0
 */

public class FrameIntroImp extends FrameIntroGUI {

  /**
   * coleccion de cartas del juego "coleccion.car" donde están todas las cartas disponibles durante el juego
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

  /**
   * Función actionPerformed del botón 1 Jugador
   * @param e
   */
  void boton1Jugador_actionPerformed(ActionEvent e) {
    //mostramos el frame de Configuración de la partida
    ConfiguracionImp conf = new ConfiguracionImp(this, coleccion, usuario);
    conf.show();
  }

  /**
   * Función actionPerformed del botón Juego en Red
   * @param e
   */
  void botonJuegoRed_actionPerformed(ActionEvent e) {
    //Activamos el chat
//    GestorUsuarios gestorUsuarios = new GestorUsuarios();
    //Controlador controlador = new Controlador(gestorUsuarios, usuario);
    //GUI ventana = new GUI(controlador,this);
  }

  /**
   * Función actionPerformed del botón Demo
   * @param e
   */
  void botonDemo_actionPerformed(ActionEvent e) {
    //mostramos el frame de Configuración de la partida
    ConfiguracionImp conf = new ConfiguracionImp(this, coleccion, usuario);
    conf.show();

//****************************
//LA DEMO SERÍA CON LA IA, AL NO ESTAR AÚN PONEMOS QUE HAGA LO MISMO QUE 1JUGADOR
//****************************

/*    CMazo mazoDemo = new CMazo();
    //seleccionamos de que tipo queremos que sea nuestra baraja
    ArrayList arrayRazas = new ArrayList();
    arrayRazas.add(0, new String("Ángeles"));
    arrayRazas.add(1, new String("Demonios"));
    arrayRazas.add(2, new String("Humanos"));
    arrayRazas.add(3, new String("Inmortales"));
    int raza = JOptionPane.showOptionDialog(this, "Elige la raza de tu baraja", "Raza",
                                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                            null, arrayRazas.toArray(), arrayRazas.get(0));
    //mostramos el frame del Tablero de juego
    switch(raza){
    	case 0:{
         Interfaz tablero = new Interfaz('A',mazoDemo, this);
    	}break;
    	
    	case 1:{
         Interfaz tablero = new Interfaz('D',mazoDemo, this);
    	}break;
    	
    	case 2:{
         Interfaz tablero = new Interfaz('H',mazoDemo, this);
    	}break;
    	
    	case 3:{
         Interfaz tablero = new Interfaz('I',mazoDemo, this);
    	}break;
  	}
  */
  }

  /**
   * Función actionPerformed del botón Editar barajas
   * @param e
   */
  void botonEditar_actionPerformed(ActionEvent e) {
    //seleccionamos de que tipo queremos que sea nuestra baraja
    ArrayList arrayRazas = new ArrayList();
    arrayRazas.add(0, new String("Ángeles"));
    arrayRazas.add(1, new String("Demonios"));
    arrayRazas.add(2, new String("Humanos"));
    arrayRazas.add(3, new String("Inmortales"));
    int raza = JOptionPane.showOptionDialog(this, "Elige la raza de tu baraja", "Raza",
                                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                            null, arrayRazas.toArray(), arrayRazas.get(0));
    //mostramos el frame del Editor de barajas
    if (raza>=0){
      EditorBarajasImp editor = new EditorBarajasImp(this,coleccion, usuario, raza);
 	  editor.show();
    }
  }

  /**
   * Función actionPerformed del botón mostrar Ayuda
   * @param e
   */
  void botonAyuda_actionPerformed(ActionEvent e) {
    //creamos el pane para mostrar el archivo
    JEditorPane ayuda = new JEditorPane();
    ImageIcon escudo = new ImageIcon("../imagenes/Escudo_Genesis.jpg");
    //cargamos el archivo de ayuda
    try{
      ayuda.setPage("file:../documentos/ayuda.txt");
      ayuda.setEditable(false);
      JTextPane texto = new JTextPane();
      texto.setEditable(false);
      texto.setText(ayuda.getText());
      JPanel panelAyuda = new JPanel();
      panelAyuda.add(texto);
      JScrollPane scrollAyuda = new JScrollPane(panelAyuda);
      scrollAyuda.setPreferredSize(new Dimension(600,320));
      JOptionPane.showMessageDialog(this, scrollAyuda, "AYUDA", JOptionPane.INFORMATION_MESSAGE,escudo);
    }
    catch(Exception ex){
      //mostramos con un JOptionPane el error producido
      JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE,escudo);
    }
  }

  /**
   * Función actionPerformed del botón mostrar Reglas
   * @param e
   */
  void botonReglas_actionPerformed(ActionEvent e) {
    //creamos el pane para mostrar el archivo
    JEditorPane reglas = new JEditorPane();
    ImageIcon escudo = new ImageIcon("../imagenes/Escudo_Genesis.jpg");
    //cargamos el archivo de reglas
    try{
      reglas.setPage("file:../documentos/reglas.txt");
      reglas.setEditable(false);
      JTextPane texto = new JTextPane();
      texto.setEditable(false);
      texto.setText(reglas.getText());
      JPanel panelReglas = new JPanel();
      panelReglas.add(texto);
      panelReglas.setSize(500,500);
      JScrollPane scrollReglas = new JScrollPane(panelReglas);
      scrollReglas.setPreferredSize(new Dimension(700,500));
      JOptionPane.showMessageDialog(this, scrollReglas, "REGLAS version_1.0", JOptionPane.INFORMATION_MESSAGE,escudo);
    }
    catch(Exception ex){
      //mostramos con un JOptionPane el error producido
      JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Función actionPerformed del botón Salir
   * @param e
   */
  void botonSalir_actionPerformed(ActionEvent e) {
    //solicitamos confirmación para salir
    int salir = JOptionPane.showConfirmDialog(this, "Desea salir?", "GENESIS version_1.0",
                                              JOptionPane.YES_NO_OPTION,
                                              JOptionPane.QUESTION_MESSAGE);
    switch (salir) {
      case JOptionPane.YES_OPTION:
        System.exit(0);
      case JOptionPane.NO_OPTION:
        ;
    }
  }
  
  void botonEnviar_actionPerformed(ActionEvent e) {
//  	ServidorBTPC serv=new ServidorBTPC(coleccion,usuario);
//  	serv.iniciaServidor();
  }

  void botonRecibir_actionPerformed(ActionEvent e) {
//  	ClienteBTPC cliente=new ClienteBTPC(coleccion,usuario);
  }

  
}