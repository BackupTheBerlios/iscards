package login;


import coleccion.Coleccion;
import frameIntro.FrameIntroImp;
import usuario.Usuario;
import panelesInfo.*;
import audio.*;

import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.*;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: Implementación del interfaz gráfico de la solicitud del registro del usuario</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Rui Miguel Alonso
 * @version 1.0
 */

public class LoginImp extends LoginGUI {

  /**
   * Variable para almacenar la colección de todas las cartas del juego
   */
  private Coleccion coleccion;

  /**
   * dlm con la lista de las barajas disponibles para jugar
   */
  javax.swing.DefaultListModel dlmUsuariosRegistrados;
  
  private static GestorAudio gestor;

  /**
   * Constructora de la clase
   */
  public LoginImp() {
    try{
	 GestorAudio ga=new GestorAudio("opciones", "inicia Fmod");
	 gestor=new GestorAudio("musica fondo", "starsign.mp3");
	 
	 
      //creamos el fichero de usuarios si no estaba
      File archivoNuevo = new File("../documentos/usuarios.usu");
      archivoNuevo.createNewFile();
      coleccion = new Coleccion();
      dlmUsuariosRegistrados = new javax.swing.DefaultListModel();
      this.listaUsuarios.setModel(dlmUsuariosRegistrados);
      //leemos el fichero con los usuarios para mostrarlo
      FileInputStream archivoUsuarios = new FileInputStream("../documentos/usuarios.usu");
      int numeroDeBytesALeer = archivoUsuarios.read();
      while (numeroDeBytesALeer >= 0) { // i==-1 es fin de fichero
        //añadimos todos los usuarios registrados al dlm y los mostramos
        String nombreUsuario = descodificar(leerFrase(numeroDeBytesALeer, archivoUsuarios));
        dlmUsuariosRegistrados.addElement(nombreUsuario);
        numeroDeBytesALeer = archivoUsuarios.read();
      }
      archivoUsuarios.close();
    }
    catch (Exception error) {
      //mostramos con un JOptionPane el error producido
      JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error",
                                    JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Función que devuelve la lista de los usuarios registrados
   * @return lista de los usuarios registrados
   */
  public DefaultListModel getLista(){
    return dlmUsuariosRegistrados;
  }

  /**
   * Función que lee de un archivo una cantidad de carácteres pedida
   * @param longitud cantidad de carácteres que hay que leer
   * @param archivo archivo del que hay que leer
   * @return String con los datos leidos
   * @throws java.lang.Exception
   */
  private byte[] leerFrase(int longitud, FileInputStream archivo) throws Exception{
    int bytes;
    int i=0;
    byte[] frase=new byte[longitud];
    while(i<longitud){
      bytes= archivo.read();
      if (bytes==-1) //i==-1 es fin de fichero
        throw new Exception("La base de datos esta incompleta");
      frase[i]=(byte)bytes;
      i++;
    }
    return frase;
  }

  /**
   * Función que descodifica los bytes leidos
   * @param fraseBytes bytes leidos
   * @return String frase descodificada
   */
  private String descodificar(byte[] fraseBytes){
    String frase = "";
    int i=0;
    while (i<fraseBytes.length){
      char a;
      if(fraseBytes[i]<0)
        //carácter espepecial (ñ, á, é, í, ó, ú, ...)
        a = (char) (256 + fraseBytes[i] + 2);
      else
        a = (char)(fraseBytes[i] + 2);
      frase=frase+a;
      i++;
    }
    return frase;
  }

  /**
   * Función que codifica los bytes que se quieren grabar
   * @param fraseBytes bytes que se desean grabar
   * @return String frase codificada
   */
  private String codificar(byte[] fraseBytes){
    String frase = "";
    int i=0;
    while (i<fraseBytes.length){
      char a;
      if(fraseBytes[i]<0)
        //carácter espepecial (ñ, á, é, í, ó, ú, ...)
        a = (char) (256 + fraseBytes[i] - 2);
      else
        a = (char) (fraseBytes[i] - 2);
      frase=frase+a;
      i++;
    }
    return frase;
  }

  /**
   * Función que controla la acción al pulsar el botón Nuevo Usuario
   * @param e
   */
  public void botonNuevo_actionPerformed(ActionEvent e){
    //pide el nombre del nuevo usuario y lo creamos, inicializando a la vez sus archivos .conf
    this.inhabilitaPanel();
    this.repaint();
    this.getContentPane().add(new SolicitarNombreImp(this, coleccion),0);

  }

  /**
   * Función que controla la acción al pulsar el boton Cargar Usuario
   * @param e
   */
  public void botonCargar_actionPerformed(ActionEvent e){
    try{
      FileOutputStream archivoUsuarios = new FileOutputStream("../documentos/usuarios.usu");
      int usuarioSelec = listaUsuarios.getSelectedIndex();
      //creamos el tablero del juego según el usuario seleccionado
      if(usuarioSelec!=-1){
        //guardamos el archivo con los usuarios registrados, por si hemos hecho modificaciones
        int i=0;
        while (i<dlmUsuariosRegistrados.size()){
          String nombreAux=(String)dlmUsuariosRegistrados.elementAt(i);
          archivoUsuarios.write(nombreAux.length());
          archivoUsuarios.write(codificar(nombreAux.getBytes()).getBytes());
          i++;
        }
        archivoUsuarios.close();
        //carga el usuario seleccionado en la list
        String nombre=(String)dlmUsuariosRegistrados.elementAt(usuarioSelec);
        
        
        Usuario usuario = new Usuario(nombre, coleccion, false);
        //mostramos la pantalla principal del juego        
        LoginImp.setGestorAudio(new GestorAudio("efecto","thunder.wav"));
        FrameIntroImp intro = new FrameIntroImp(usuario, coleccion);
        intro.show();
        this.dispose();
      }
      else{

        this.inhabilitaPanel();
        this.repaint();
        this.getContentPane().add(new PanelGenerico("../imagenes/panelesInfo/SeleccionaUsuario.jpg",this),0);

      }
    }
    catch (Exception error) {
      //mostramos con un JOptionPane el error producido
      JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Función que controla la acción al pulsar el botón Borrar Usuario
   * @param e
   */
  public void botonBorrar_actionPerformed(ActionEvent e){
    int usuarioSelec= listaUsuarios.getSelectedIndex();

    if (usuarioSelec!=-1){
    	//borra el usuario seleccionado en la list
        String nom = (String)dlmUsuariosRegistrados.get(usuarioSelec);
        dlmUsuariosRegistrados.removeElementAt(usuarioSelec);
        //hacemos que el usuario se elimine
        Usuario usuario = new Usuario(nom, coleccion, true);
    }
    else{
    	//mensaje que recuerda que hay q seleccionar un usuario antes.
       	this.inhabilitaPanel();
       	this.repaint();
       	this.getContentPane().add(new PanelGenerico("../imagenes/panelesInfo/SeleccionaUsuario.jpg",this),0);
    }

  }

  /**
   * Función que controla el evento de cerrar la ventana
   * @param e
   */
  public void botonSalir_actionPerformed(ActionEvent e){
    this.inhabilitaPanel();
    this.repaint();
    this.getContentPane().add(new PanelSalir(this),0);
  }
  
  public static void setGestorAudio(GestorAudio g){
  	gestor=g;
  }

  public static GestorAudio getGestorAudio(){
  	return gestor;
  }
  
}