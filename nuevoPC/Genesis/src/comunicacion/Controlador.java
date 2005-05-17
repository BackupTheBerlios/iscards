package comunicacion;

import interfaz.*;
import usuario.*;
import configuracion.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

/**
 *  Clase para enlazar la vista y el modelo de la aplicación
 *
 *@author     Manuel Domingo Mora, Jesús Patiño y Francisco Javier Arellano
 *@version    2.0
 */

public class Controlador {

	/**
	 *  Socket de conexión de cliente
	 */
	Socket sCliente = null;

	/**
	 *  GestorUsuarios del programa (Modelo)
	 */
	private GestorUsuarios gestorUsuarios;

	private Usuario usuario;

	private JFrame padre;
	/**
	 *  ArrayList con las ventanas privadas que estan actualmente abiertas
	 */
	private ArrayList ventanasPrivados = new ArrayList();

	/**
	 *  Nombre del usuario con el que me he conectado
	 */
	private String nomOtroUser;

	private String miNickdeControlador;

        private String miContraseñadeControlador;

	private Interfaz inter;

	/**
	 *  Flujo de salida
	 */
	private PrintWriter salida;

	/**
	 *  Buffer de lectura
	 */
	private ObjectInputStream entrada;

	/**
	 *  Atributo comunicacion creado por kike, antes era solo un objeto en el
	 *  metodo ejecutarcomunicacion de esta misma clase
	 */
	private Comunicacion comunicacion;

	/**
	 *  Constructor for the Controlador object
	 *
	 *@param  u         Description of Parameter
	 *@param  usu       Description of Parameter
	 *@param  p         el padre desde el que se llama a esta clase
	 *@param  interfaz  la interfaz padre desde la que se llama a esta clase
         *
	 */

     public Controlador(GestorUsuarios u, Usuario usu, JFrame p,Interfaz interfaz) {
		inter = interfaz;
		gestorUsuarios = u;
		usuario = usu;
		padre = p;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  n  Description of Parameter
	 */
	public void setmiNickdeControlador(String n) {
		miNickdeControlador = n;
	}

        public void setmiContraseñadeControlador(String c){
          miContraseñadeControlador=c;

        }


	/**
	 *  Description of the Method
	 *
	 *@param  n  Description of Parameter
	 *@return    Description of the Returned Value
	 */
	public String getmiNickdeControlador(String n) {
		return miNickdeControlador;
	}


	/**
	 *  Gets the Interfaz attribute of the Controlador object
	 *
	 *@return    The Interfaz value
	 */
	public Interfaz getInterfaz() {
		return inter;
	}


	/**
	 *  Gets the Usuario attribute of the Controlador object
	 *
	 *@return    The Usuario value
	 */
	public Usuario getUsuario() {
		return usuario;
	}


	/**
	 *  Gets the VentanasPrivados attribute of the Controlador object
	 *
	 *@return    The VentanasPrivados value
	 */
	public ArrayList getVentanasPrivados() {
		return ventanasPrivados;
	}


	/**
	 *  Gets the Controlador attribute of the Controlador object
	 *
	 *@return    The Controlador value
	 */
	public Controlador getControlador() {

          return this;
	}


	/**
	 *  Obtiene la lista de los nick de los usuarios conectados
	 *
	 *@param  nick  nick con el qe no puede hablar (él mismo)
	 *@return       DefaultListModel lista de los nicks de los usuarios
	 *      conectados
	 */
	public DefaultListModel getNicks(String nick) {
		DefaultListModel listNicks = new DefaultListModel();
		Vector userAux = gestorUsuarios.getUsers();
		Vector userAuxJugando = gestorUsuarios.getUsersJugando();
		//Vector userAuxNoJugando = userAux.removeAll((Collection)userAuxJugando);
		//listNicks = (DefaultListModel)userAuxNoJugando;
		for (int i = 0; i < userAux.size(); i++) {
			//Modificado para que en la lista de usuarios
			//conectados el usuario no se vea a sí mismo puesto q con él mismo no puede hablar
			if (!nick.equals((userAux.get(i)))) {
				if (!userAuxJugando.contains(userAux.get(i))) {
					listNicks.addElement(userAux.get(i) + " - LIBRE");
				}
				else {
					listNicks.addElement(userAux.get(i) + " - JUGANDO");
				}
			}
		}
		return listNicks;
	}


	/**
	 *  getSocket
	 *
	 *@return    Object
	 */
	public synchronized Socket getSocket() {
		return sCliente;
	}


	/**
	 *  Funcion que me devuelve un ArrayList con las ventanas privadas que hay
	 *  actualmente abiertas
	 *
	 *@return    ArrayList de ventanas de privadas
	 */
	public synchronized ArrayList getPrivados() {
		return ventanasPrivados;
	}


	/**
	 *  Funcion que me devuelve la clase GestorUsuarios
	 *
	 *@return    GestorUsuarios
	 */
	public synchronized GestorUsuarios getGestorUsuarios() {
		return gestorUsuarios;
	}



	/**
	 *  Añade un nuevo usuario al servidor
	 *
	 *@param  nick  Description of Parameter
	 *@return       boolean True si se ha añadido correctamente y false en otro
	 *      caso
	 */
	public boolean aniadirUser(String nick) {
          if (gestorUsuarios.registrarUser(nick)) {
                   salida.println("NU" + "#" + nick + "#"+ this.miContraseñadeControlador);
                   salida.flush();

                   return true;
           }
           else {
                   return false;
           }


	}


	/**
	 *  Borra un usuario del servidor
	 *
	 *@param  nick  Nick del usuario a borrar
	 */
	public void borrarUser(String nick) {
		gestorUsuarios.removeUser(nick);
		gestorUsuarios.removeUserJugando(nick);
		if (salida!=null){
			salida.println("DU" + "#" + nick);
			salida.flush();
		}
	}


	/**
	 *  Conecta el programa al servidor de chat
	 */
	public void conectar() {
		try {

			//Creamos el socket en el puerto
			//InetAddress address = InetAddress.getLocalHost();
			InetAddress address = InetAddress.getByName("Pto0615");
			sCliente = new Socket(address, 4999);

			Vector uReg = new Vector();
			//vector con los Usuarios registrados
			Vector uJug = new Vector();

			salida = new PrintWriter(sCliente.getOutputStream(), true);
			entrada = new ObjectInputStream(sCliente.getInputStream());

			//Consigo los usuarios existentes en el servidor
			//y los almaceno en mi vector de usuarios
			uReg = (Vector) entrada.readObject();
			gestorUsuarios.setVectorUsuarios(uReg);

			//Consigo los usuarios que estan jugando en este momento
			//y los almaceno en mi vector de usuarios en juego
			uJug = (Vector) entrada.readObject();
			gestorUsuarios.setVectorUsuariosJugando(uJug);

		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "El servidor está caido. en controlador.java",
					"Error en la conexión",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();

			//el juego no debe salir si el servidor se cae!!
			//System.exit(-1);

			//bug!!! modificar lo siguiente para que en vez de ir
			//al padre vaya al abuelo!!!
			padre.setEnabled(true);
			padre.show();

		}
	}


	/**
	 *  Funcion para ejecutar Comunicacion en otra hebra de ejecución
	 *
	 *@param  ventPrinc  Ventana principal del programa
	 */
	//public void ejecutarComunicacion(VentanaPrincipal ventPrinc) {
	public void ejecutarComunicacion(VentanaPrincipal ventPrinc) {

		//comunicacion=new Comunicacion(this, ventPrinc).start();
		//posible bug!! si el chat de patino deja de funcionar, volver a poner esta linea
		new Comunicacion(this, ventPrinc).start();
	}


	/**
	 *  Funcion para desconectar a un usuario
	 *
	 *@param  nomUsuario  nombre del usuario a desconectar
	 */
	public void desconectar(String nomUsuario) {

		if (salida!=null){
			salida.println("DI" + nomUsuario);
			salida.flush();
		}
	}


	/**
	 *  Añade una ventana de privado al vector de ventanas privadas
	 *
	 *@param  ventPriv  Ventana a añadir
	 */
	public synchronized void addPrivado(VentanaPrivada ventPriv) {
		ventanasPrivados.add(ventPriv);
		nomOtroUser = ventPriv.getNameOtherUser();
	}


	/**
	 *  Abre desde el servidor la ventana privada correspondiente.
	 *
	 *@param  nomUsuario         nombre del usuario que soy
	 *@param  nomUsuarioPrivado  nombre del otro usuario con el que quiero hacer
	 *      la conversación privada
	 */
	public void activarPrivado(String nomUsuario, String nomUsuarioPrivado) {
		nomOtroUser=nomUsuarioPrivado;
		salida.println("NP" + "#" + nomUsuario + "#" + nomUsuarioPrivado);
		salida.flush();
	}



	/**
	 *  Desactiva desde el servidor la ventana privada correspondiente.
	 *
	 *@param  nomUsuario         nombre del usuario que soy
	 *@param  nomUsuarioPrivado  nombre del otro usuario con el que quiero hacer
	 *      la conversación privada
	 */
	public void desactivarPrivado(String nomUsuario,
			String nomUsuarioPrivado) {
		salida.println("DP" + "#" + nomUsuarioPrivado + "#" + nomUsuario);
		salida.flush();
	}


	/**
	 *  Borra un privado del vector de privados de un determinado usuario, ademas
	 *  de cerrar la ventana
	 *
	 *@param  user  Usuario del que se desea eliminar el privado
	 */
	public synchronized void removePrivado(String user) {
		for (int i = 0; i < ventanasPrivados.size(); i++) {
			if (((VentanaPrivada) ventanasPrivados.get(i)).getNameUser().equals(user)) {
				((JFrame) ventanasPrivados.get(i)).dispose();
				ventanasPrivados.remove(i);
			}
		}
	}


	/**
	 *  Envia un nuevo mensaje privado al servidor
	 *
	 *@param  nomUsuario   nombre del usuario que soy
	 *@param  nomOtroUser  nombre del otro usuario con el que estoy conversando
	 *      en privado
	 *@param  mens         mensaje a enviar a los demas usuarios
	 */
	public void enviarPrivadoAServidor(String nomUsuario, String nomOtroUser,
			String mens) {
		salida.println("MP" + "#" + nomOtroUser + "#" + nomUsuario + "#" + mens);
		salida.flush();
	}


	/**
	 *  Envia un Evento en forma de string
	 *
	 *@param  evento  string a enviar
	 */
	public void enviarEvento(String evento) {
		salida.println("ES" + "#" + nomOtroUser + "#" + evento);
		salida.flush();
	}

	public void setInterfaz(Interfaz i){
		inter=i;
	}

}
