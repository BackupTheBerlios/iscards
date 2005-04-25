package comunicacion;

import java.util.Vector;

/**
 *  Clase en la que se gestionan todos los usuarios
 *
 *@author     Manuel Domingo Mora, Jesús Patiño y Francisco Javier Arellano
 *@version    2.0, revisada y mejorada por Enrique Martorano
 */

public class GestorUsuarios {
	/**
	 *  Vector de usuarios registrados en el chat
	 */
	private Vector usersConectados = new Vector();

	/**
	 *  Vector de usuarios conectados
	 */
	private Vector usersJugando = new Vector();


	/**
	 *  Procedimiento para asignar el vector de usuarios
	 *
	 *@param  uReg  Vector de usuarios registrados
	 */
	public void setVectorUsuarios(Vector uReg) {
		usersConectados = uReg;
	}


	/**
	 *  Procedimiento para asignar el vector de usuarios en juego
	 *
	 *@param  uJug  The new VectorUsuariosJugando value
	 */
	public void setVectorUsuariosJugando(Vector uJug) {
		usersJugando = uJug;
	}


	/**
	 *  Funcion para obtener el vector de usuarios
	 *
	 *@return    Vector de usuarios registrados
	 */
	public Vector getUsers() {
		return usersConectados;
	}


	/**
	 *  Funcion para obtener el vector de usuarios jugando
	 *
	 *@return    Vector de usuarios jugando
	 */
	public Vector getUsersJugando() {
		return usersJugando;
	}


	/**
	 *  Funcion para registrar un usuario
	 *
	 *@param  nick  Nombre del usuario
	 *@return       boolean Verdadero si el usuario ha sido registrado
	 */
	public boolean registrarUser(String nick) {
		if (!(usersConectados.contains(nick))) {
			usersConectados.add(nick);
			return true;
		}
		return false;
	}


	/**
	 *  Funcion para añadir un usuario a la partida
	 *
	 *@param  nick  Nombre del usuario
	 */
	public void addUserJugando(String nick) {
		usersJugando.add(nick);
	}


	/**
	 *  Funcion para comprobar que un usuario esta conectado
	 *
	 *@param  nick  Nombre del usuario
	 *@return       boolean Verdadero si el usuario esta conectado
	 */
	public boolean existeUser(String nick) {
		if (usersConectados.contains(nick)) {
			return true;
		}
		else {
			return false;
		}
	}


	/**
	 *  Borra el nick de la sala especificada
	 *
	 *@param  n  Nick a borrar
	 */
	public synchronized void removeUser(String n) {
		usersConectados.remove(n);
	}


	/**
	 *  Procedimiento para borrar un usuario del vector de usuarios registrados
	 *  en juego
	 *
	 *@param  nick  nombre del usuario a borrar
	 */
	public synchronized void removeUserJugando(String nick) {
		usersJugando.remove(nick);
	}

}
