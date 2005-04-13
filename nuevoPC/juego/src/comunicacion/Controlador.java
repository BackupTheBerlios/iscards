package comunicacion;

import usuario.*;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class Controlador {

  /**
   * GestorUsuarios del programa (Modelo)
   */
  private GestorUsuarios gestorUsuarios;

	private Usuario usuario;
	
  /**
   * ArrayList con las ventanas privadas que estan actualmente abiertas
   */
  private ArrayList ventanasPrivados = new ArrayList();

  /**
   * Nombre del usuario con el que me he conectado
   */
  private String nomOtroUser;

  /**
   * Socket de conexión de cliente
   */
  Socket sCliente = null;
  /**
   * Flujo de salida
   */
  private PrintWriter salida;

  /**
   * Buffer de lectura
   */
  private ObjectInputStream entrada;

  public Controlador(GestorUsuarios u, Usuario usu) {
    gestorUsuarios = u;
    usuario = usu;
  }

  public boolean aniadirUser(String nick) {
    if (gestorUsuarios.registrarUser(nick)){
      salida.println("NU" + "#" + nick);
      salida.flush();
      return true;
    }
    else
      return false;
  }


  /**
   * Obtiene la lista de los nick de los usuarios conectados
   * @return DefaultListModel lista de los nicks de los usuarios conectados
   */
  public DefaultListModel getNicks(String nick) {
    DefaultListModel listNicks = new DefaultListModel();
    Vector userAux = gestorUsuarios.getUsers();
    Vector userAuxJugando = gestorUsuarios.getUsersJugando();
    //Vector userAuxNoJugando = userAux.removeAll((Collection)userAuxJugando);
    //listNicks = (DefaultListModel)userAuxNoJugando;
    for (int i = 0; i < userAux.size(); i++)
        //Modificado para que en la lista de usuarios
        //conectados el usuario no se vea a sí mismo puesto q con él mismo no puede hablar
      if(!nick.equals((userAux.get(i))))
        if (!userAuxJugando.contains(userAux.get(i)))
        	listNicks.addElement(userAux.get(i)+" - LIBRE");
        else
        	listNicks.addElement(userAux.get(i)+" - JUGANDO");
    return listNicks;
  }

  public void borrarUser(String nick) {
    gestorUsuarios.removeUser(nick);
    salida.println("DU" + "#" + nick);
    salida.flush();
  }

  /**
   * Conecta el programa al servidor de chat
   */
  public void conectar() {
    try {

      //Creamos el socket en el puerto
      InetAddress address = InetAddress.getLocalHost();//.getByName("W2000");
      sCliente = new Socket(address, 4999);
      System.out.println("Conectado a " +
                         sCliente.getInetAddress().getHostName());

      Vector uReg = new Vector();//vector con los Usuarios registrados
	  Vector uJug = new Vector();
	  
      salida = new PrintWriter(sCliente.getOutputStream(), true);
      entrada = new ObjectInputStream(sCliente.getInputStream());

      //Consiguo los usuarios existentes en el servidor
      //y los almaceno en mi vector de usuarios
      uReg = (Vector) entrada.readObject();
      gestorUsuarios.setVectorUsuarios(uReg);
      
      //Consiguo los usuarios que estan jugando en este momento
      //y los almaceno en mi vector de usuarios en juego
      uJug = (Vector) entrada.readObject();
      gestorUsuarios.setVectorUsuariosJugando(uJug);

    }
    catch (Exception e) {
      JOptionPane.showMessageDialog(null, "El servidor está caido.",
                                    "Error en la conexión",
                                    JOptionPane.ERROR_MESSAGE);
      System.exit(0);
    }
  }

  /**
   * Funcion para ejecutar Comunicacion en otra hebra de ejecución
   * @param ventPrinc Ventana principal del programa
   */
  public void ejecutarComunicacion(VentanaPrincipal ventPrinc) {
    new Comunicacion(this, ventPrinc).start();
  }

  /**
   * Funcion para desconectar a un usuario
   * @param nomUsuario nombre del usuario a desconectar
   */
  public void desconectar(String nomUsuario) {
    salida.println("DI" + nomUsuario);
    salida.flush();
    System.out.println("Desconectado");
  }

  /**
   * getSocket
   *
   * @return Object
   */
  public synchronized Socket getSocket() {
    return sCliente;
  }

  /**
   * Añade una ventana de privado al vector de ventanas privadas
   * @param ventPriv Ventana a añadir
   */
  public synchronized void addPrivado(VentanaPrivada ventPriv) {
  	    ventanasPrivados.add(ventPriv);
  	    nomOtroUser=ventPriv.getNameOtherUser();
  }

  /**
   * Abre desde el servidor la ventana privada correspondiente.
   * @param nomUsuario nombre del usuario que soy
   * @param nomUsuarioPrivado nombre del otro usuario con el que quiero hacer la conversación privada
   */
  public void activarPrivado(String nomUsuario, String nomUsuarioPrivado) {
    salida.println("NP" + "#" + nomUsuario + "#" + nomUsuarioPrivado);
    salida.flush();
  }



  /**
   * Desactiva desde el servidor la ventana privada correspondiente.
   * @param nomUsuario nombre del usuario que soy
   * @param nomUsuarioPrivado nombre del otro usuario con el que quiero hacer la conversación privada
   */
  public void desactivarPrivado(String nomUsuario,
                                String nomUsuarioPrivado) {
    salida.println("DP" + "#" + nomUsuarioPrivado + "#" + nomUsuario);
    salida.flush();
  }

  /**
   * Borra un privado del vector de privados de un determinado usuario, ademas de cerrar la ventana
   * @param user Usuario del que se desea eliminar el privado
   */
  public synchronized void removePrivado(String user) {
    for (int i = 0; i < ventanasPrivados.size(); i++) {
      if ( ( (VentanaPrivada) ventanasPrivados.get(i)).getNameUser().equals(
          user)) {
        ( (JFrame) ventanasPrivados.get(i)).dispose();
        ventanasPrivados.remove(i);
      }
    }
  }

  /**
   * Funcion que me devuelve un ArrayList con las ventanas privadas que hay actualmente abiertas
   * @return ArrayList de ventanas de privadas
   */
  public synchronized ArrayList getPrivados() {
    return ventanasPrivados;
  }

  /**
   * Funcion que me devuelve la clase GestorUsuarios
   * @return GestorUsuarios
   */
  public synchronized GestorUsuarios getGestorUsuarios() {
    return gestorUsuarios;
  }

  /**
   * Envia un nuevo mensaje privado al servidor
   * @param nomUsuario nombre del usuario que soy
   * @param nomOtroUser nombre del otro usuario con el que estoy conversando en privado
   * @param mens mensaje a enviar a los demas usuarios
   */
  public void enviarPrivadoAServidor(String nomUsuario, String nomOtroUser,
                                     String mens) {
    salida.println("MP" + "#" + nomOtroUser + "#" + nomUsuario + "#" + mens);
    salida.flush();
  }
  /**
   *Envia un Evento en forma de string
   *@param evento string a enviar
   */
  public void enviarEvento(String evento) {
    salida.println("ES" + "#" + nomOtroUser + "#" +evento);
    salida.flush();
  }
  
}
