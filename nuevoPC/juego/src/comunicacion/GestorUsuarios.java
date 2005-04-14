package comunicacion;

import java.util.Vector;

/**
* Clase del Gestor de Usuarios
* @author Manuel Domingo Mora Martinez
* @version 1.0
*/

public class GestorUsuarios
{
        /**
        * Vector de usuarios registrados en el chat
        */
        private Vector usersConectados = new Vector();

        /**
        * Vector de usuarios conectados
        */
        private Vector usersJugando = new Vector();

        /**
        * Procedimiento para asignar el vector de usuarios
        * @param uReg Vector de usuarios registrados
        */
        public void setVectorUsuarios(Vector uReg)
        {
                usersConectados=uReg;
        }

        /**
        * Procedimiento para asignar el vector de usuarios en juego
        * @param uReg Vector de usuarios en juego
        */
		public void setVectorUsuariosJugando(Vector uJug)
		{
			usersJugando=uJug;
		}
		
        /**
        * Funcion para registrar un usuario
        * @param nick Nombre del usuario
        * @return boolean Verdadero si el usuario ha sido registrado
        */
        public boolean registrarUser(String nick)
        {
                if(!usersConectados.contains(nick))
                {
                        usersConectados.add(nick);
                        return true;
                }
                return false;
        }
        
        /**
        * Funcion para añadir un usuario a la partida
        * @param nick Nombre del usuario
        */
        public void addUserJugando(String nick)
        {
              usersJugando.add(nick);
        }

        /**
         * Funcion para comprobar que un usuario esta conectado
         * @param nick Nombre del usuario
         * @return boolean Verdadero si el usuario esta conectado
         */
        public boolean existeUser(String nick) {
          if (usersConectados.contains(nick))
            return true;
          else
            return false;
        }

        /**
        * Funcion para obtener el vector de usuarios
        * @return Vector de usuarios registrados
        */
        public Vector getUsers()
        {
                return usersConectados;
        }
	
        /**
        * Funcion para obtener el vector de usuarios jugando
        * @return Vector de usuarios jugando
        */
		public Vector getUsersJugando()
		{
			return usersJugando;
		}
		
        /**
        * Borra el nick de la sala especificada
        * @param n Nick a borrar
        */
        public synchronized void removeUser(String n)
        {
                usersConectados.remove(n);
        }

}
