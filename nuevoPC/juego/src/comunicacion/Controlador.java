package comunicacion;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

/**
 * Clase para enlazar la vista y el modelo de la aplicaci�n
 * @author Manuel Domingo Mora, Jes�s Pati�o y Francisco Javier Arellano
 * @version 2.0
 */


public class Controlador {

  /**
   * GestorUsuarios del programa (Modelo)
   */
  private GestorUsuarios gestorUsuarios;

  /**
   * ArrayList con las ventanas privadas que estan actualmente abiertas
   */
  private ArrayList ventanasPrivados = new ArrayList();

  /**
   * Nombre del usuario con el que me he conectado
   */
  private String nomOtroUser;

  /**
   * Cola de eventos
   */
  private LinkedList colaEventos;

  /**
   * Socket de conexi�n de cliente
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

  public Controlador(GestorUsuarios u) {
    gestorUsuarios = u;
    colaEventos=new LinkedList();
  }

  /**
   * A�ade un nuevo usuario al servidor
   * @return boolean True si se a a�adido correctamente y false en otro caso
   */
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
        //conectados el usuario no se vea a s� mismo puesto q con �l mismo no puede hablar
      if(!nick.equals((userAux.get(i))))
        if (!userAuxJugando.contains(userAux.get(i)))
        	listNicks.addElement(userAux.get(i)+" - LIBRE");
        else
        	listNicks.addElement(userAux.get(i)+" - JUGANDO");
    return listNicks;
  }

  /**
   * Borra un usuario del servidor
   * @param nick Nick del usuario a borrar
   */
  public void borrarUser(String nick) {
    gestorUsuarios.removeUser(nick);
    gestorUsuarios.removeUserJugando(nick);
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
      JOptionPane.showMessageDialog(null, "El servidor est� caido.",
                                    "Error en la conexi�n",
                                    JOptionPane.ERROR_MESSAGE);
      //System.exit(-1);
    }
  }

  /**
   * Funcion para ejecutar Comunicacion en otra hebra de ejecuci�n
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
   * A�ade una ventana de privado al vector de ventanas privadas
   * @param ventPriv Ventana a a�adir
   */
  public synchronized void addPrivado(VentanaPrivada ventPriv) {
  	    ventanasPrivados.add(ventPriv);
  	    nomOtroUser=ventPriv.getNameOtherUser();
  }

  /**
   * Abre desde el servidor la ventana privada correspondiente.
   * @param nomUsuario nombre del usuario que soy
   * @param nomUsuarioPrivado nombre del otro usuario con el que quiero hacer la conversaci�n privada
   */
  public void activarPrivado(String nomUsuario, String nomUsuarioPrivado) {
    salida.println("NP" + "#" + nomUsuario + "#" + nomUsuarioPrivado);
    salida.flush();
  }



  /**
   * Desactiva desde el servidor la ventana privada correspondiente.
   * @param nomUsuario nombre del usuario que soy
   * @param nomUsuarioPrivado nombre del otro usuario con el que quiero hacer la conversaci�n privada
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
   * A�ade un evento recibido a la cola de eventos
   * @param String Evento a a�adir
   */
  public void addEventoACola(String evento)
  {
  	colaEventos.add(evento);
  }
  
  /**
   *Envia un Evento en forma de string
   *@param evento string a enviar
   */
  public void enviarEvento(String evento) {
    salida.println("ES" + "#" + nomOtroUser + "#" +evento);
    salida.flush();
  }
  
  /**
   * Consigue un Evento en forma de string
   * @return String Evento capturado, null si no existen eventos en cola
   */    
  public String getEvento()
  	{
  		String resultado;
  		if (!colaEventos.isEmpty())
  		{
  			resultado=colaEventos.getFirst().toString();
  			colaEventos.removeFirst();
  			return resultado;
  		}else
  		{
  			return null;
  		}
	}

}
