package genesis.foro;

import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;
import java.lang.*;
import genesis.bd.*;

/**
 *  <p>
 *
 *  Title: MensajesBD
 *
 *  Description: Base de datos de todos los mensajes
 *
 *  Copyright: Copyright (c) Genesis
 *
 *  Company: Genesis
 *
 *  @author: Laura Díaz, Conchi Fernandez, Laura García, Inés González, Sergio Somovilla
 *  Revisado por: David B. Jenkins López.
 *
 *  @version    3.01
 */

public class MensajesBD {
        public ArrayList mensajes;

        public static MensajesBD elGestor = null;




        /**
         *  Gets the Mensaje attribute of the MensajesBD object
         *
         *@param  id  Identificador del mensaje
         *@return     The Mensaje value
         */
        public Mensaje getMensaje(int id) throws ConexionBDException{
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                Mensaje resultado;

                try {
                        // Obtenemos la conexión a la base de datos
                        connection = ConexionBD.getConexion();

                        // Creamos la sentencia SQL
                        preparedStatement = connection.prepareStatement(
                                        "SELECT idMensaje, nick, texto, fecha FROM mensajes WHERE idMensaje = ?");
                        // Cambiamos el ? por la id
                        preparedStatement.setInt(1, id);

                        // Ejecutamos la sentencia
                        resultSet = preparedStatement.executeQuery();

                        // Si se encontró un resultado
                        if (resultSet.next()) {
                                // Creamos una instancia de la clase Mensaje a partir
                                // de ese resultado
                                Mensaje mensaje = new Mensaje(
                                                resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),resultSet.getDate(4));
                                resultado = mensaje;
                        }
                        else {
                                resultado = null;
                        }
                        resultSet.close();
                        resultSet = null;
                        preparedStatement.close();
                        preparedStatement = null;
                        connection.close();
                        connection = null;
                        return resultado;
                }
                catch (SQLException e) {
                        // En caso de error en la sentencia SQL, liberamos la conexión
                        return null;
                }
                finally {
                  // Cerrar todo si es distinto de NULL
                  if (resultSet != null){
                    try{
                      resultSet.close();
                    }
                    catch(SQLException e) { ; }
                    resultSet = null;
                  }
                  if (preparedStatement != null){
                    try{
                      preparedStatement.close();
                    }
                    catch(SQLException e) { ; }
                    preparedStatement = null;
                  }
                  if (connection != null){
                    try{
                      connection.close();
                    }
                    catch(SQLException e) { ; }
                    connection = null;
                  }
                }
        }


        /**
         *  Gets the Mensajes attribute of the MensajesBD object
         *
         *@return    The Mensajes value
         */
        public ArrayList getMensajes(String indic) throws ConexionBDException{
          int indice = (int) new Integer(indic).intValue();
          mensajes = new ArrayList();
          Connection connection = null;
          PreparedStatement preparedStatement = null;
          ResultSet resultSet = null;

          try {
            // Obtenemos la conexión a la base de datos
            connection = ConexionBD.getConexion();
            // Creamos la sentencia SQL: Obtener todos los mensajes
            preparedStatement = connection.prepareStatement(
                "SELECT idMensaje, nick, texto, fecha FROM mensajes LIMIT ?,8");
            preparedStatement.setInt(1, indice);
            // Y la ejecutamos
            resultSet = preparedStatement.executeQuery();

            // Mientras haya resultados de la consulta
            while (resultSet.next()) {
              // Creamos un mensaje con el resultado actual
              Mensaje mensaje = new Mensaje(
                  resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),resultSet.getDate(4));
              // Y lo añadimos a la lista
              mensajes.add(mensaje);
            }
            resultSet.close();
            resultSet = null;
            preparedStatement.close();
            preparedStatement = null;
            connection.close();
            connection = null;
            return mensajes;
          }
          catch (SQLException e) {
            return null;
          }
          finally {
            // Cerrar todo si es distinto de NULL
            if (resultSet != null){
              try{
                resultSet.close();
              }
              catch(SQLException e) { ; }
              resultSet = null;
            }
            if (preparedStatement != null){
              try{
                preparedStatement.close();
              }
              catch(SQLException e) { ; }
              preparedStatement = null;
            }
            if (connection != null){
              try{
                connection.close();
              }
              catch(SQLException e) { ; }
              connection = null;
            }
          }
        }


        public ArrayList getMensajes2() throws ConexionBDException{
          mensajes = new ArrayList();
          Connection connection = null;
          PreparedStatement preparedStatement = null;
          ResultSet resultSet = null;

          try {
            // Obtenemos la conexión a la base de datos
            connection = ConexionBD.getConexion();
            // Creamos la sentencia SQL: Obtener todos los mensajes
            preparedStatement = connection.prepareStatement(
                "SELECT idMensaje, nick, texto, fecha FROM mensajes");
            // Y la ejecutamos
            resultSet = preparedStatement.executeQuery();

            // Mientras haya resultados de la consulta
            while (resultSet.next()) {
              // Creamos un mensaje con el resultado actual
              Mensaje mensaje = new Mensaje(
                  resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),resultSet.getDate(4));
              // Y lo añadimos a la lista
              mensajes.add(mensaje);
            }
            resultSet.close();
            resultSet = null;
            preparedStatement.close();
            preparedStatement = null;
            connection.close();
            connection = null;
            return mensajes;
          }
          catch (SQLException e) {
            return null;
          }
          finally {
           // Cerrar todo si es distinto de NULL
           if (resultSet != null){
             try{
               resultSet.close();
             }
             catch(SQLException e) { ; }
             resultSet = null;
           }
           if (preparedStatement != null){
             try{
               preparedStatement.close();
             }
             catch(SQLException e) { ; }
             preparedStatement = null;
           }
           if (connection != null){
             try{
               connection.close();
             }
             catch(SQLException e) { ; }
             connection = null;
           }
          }
        }

        /**
         *  Añade un mensaje a la tabla de mensajes devuelve 1 si se realizó
         *  correctamente, cualquier otro valor en caso de error.
         *
         *@param  mensaje  Mensaje que queremos insertar en la Base de Datos
         *@param  idTema   Identificador del tema al que pertenece el mensaje
         *@return          Description of the Returned Value
         */
        public int insertarMensaje(Mensaje mensaje, String idT) throws ConexionBDException{
          int idTema = (int) new Integer(idT).intValue();
          int rowsAffected = 0;
          int rowsAffected2 = 0;
          int rowsAffected3 = 0;
          Connection connection = null;
          PreparedStatement preparedStatement = null;
          PreparedStatement preparedStatement2 = null;
          PreparedStatement preparedStatement3 = null;

          try {
            // Obtenemos la conexión a la base de datos
            connection = ConexionBD.getConexion();


	    FiltroMensajes filtro = new FiltroMensajes();
	    String resultado = filtro.filtrar(mensaje.getTexto());


            // Creamos la sentencia SQL
            preparedStatement = connection.prepareStatement(
                "INSERT INTO mensajes (idMensaje, nick, texto, fecha)" +
                "VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, mensaje.getIdMensaje());
            preparedStatement.setString(2, mensaje.getNick());
            preparedStatement.setString(3, mensaje.getTexto());
            preparedStatement.setDate(4, mensaje.getFecha());

            preparedStatement3 = connection.prepareStatement(
                "UPDATE mensajes SET fecha = curdate() WHERE idMensaje =?");
            preparedStatement3.setInt(1, mensaje.getIdMensaje());

            preparedStatement2 = connection.prepareStatement(
                "INSERT INTO mensaje_tema (idMensaje, idTema)" +
                "VALUES (?, ?)");
            preparedStatement2.setInt(1, mensaje.getIdMensaje());
            preparedStatement2.setInt(2, idTema);

            // La ejecutamos y devolvemos el número de filas afectadas
            rowsAffected = preparedStatement.executeUpdate();
            rowsAffected2 = preparedStatement2.executeUpdate();
            rowsAffected3 = preparedStatement3.executeUpdate();

            preparedStatement.close();
            preparedStatement = null;
            preparedStatement2.close();
            preparedStatement2 = null;
            preparedStatement3.close();
            preparedStatement3 = null;
            connection.close();
            connection = null;

            return (rowsAffected + rowsAffected2 + rowsAffected3);
          }
          catch (SQLException e) {
            return -1;
          }
          finally {
           // Cerrar todo si es distinto de NULL
           if (preparedStatement != null){
             try{
               preparedStatement.close();
             }
             catch(SQLException e) { ; }
             preparedStatement = null;
           }
           if (preparedStatement2 != null){
             try{
               preparedStatement2.close();
             }
             catch(SQLException e) { ; }
             preparedStatement2 = null;
           }
           if (preparedStatement3 != null){
             try{
               preparedStatement3.close();
             }
             catch(SQLException e) { ; }
             preparedStatement3 = null;
           }
           if (connection != null){
             try{
               connection.close();
             }
             catch(SQLException e) { ; }
             connection = null;
           }
          }
        }


        /**
         *  Elimina un usuario de la tabla de usuarios
         *
         *@param  id    Identificador del mensaje que queremos eliminar
         *@return       1 si se realizó correctamente, cualquier otro valor en caso
         *      de error
         */
        public boolean borrarMensaje(int id) throws ConexionBDException, SQLException{
          int rowsAffected = 0;
          Connection connection = null;
          PreparedStatement preparedStatement = null;

          try {
            // Obtenemos la conexión a la base de datos
            connection = ConexionBD.getConexion();

            // Creamos la sentencia SQL y la ejecutamos, devolviendo
            // el número de filas de la tabla de usuarios afectadas
            preparedStatement = connection.prepareStatement(
                "DELETE FROM mensajes WHERE idMensaje = ?");
            preparedStatement.setInt(1, id);

            rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            preparedStatement = null;
            connection.close();
            connection = null;

            if (rowsAffected == 1)
	       return true;
	    else
	       return false;
          }
          finally {
           // Cerrar todo si es distinto de NULL
           if (preparedStatement != null){
             try{
               preparedStatement.close();
             }
             catch(SQLException e) { ; }
             preparedStatement = null;
           }
           if (connection != null){
             try{
               connection.close();
             }
             catch(SQLException e) { ; }
             connection = null;
           }
          }

        }

        /**
         *  Elimina un usuario de la tabla de usuarios
         *
         *@param  id    Identificador del mensaje que queremos eliminar
         *@return       1 si se realizó correctamente, cualquier otro valor en caso
         *      de error
         */
        public boolean modificarMensaje(Mensaje mensaje) throws ConexionBDException, SQLException{
          int rowsAffected = 0;
          Connection connection = null;
          PreparedStatement preparedStatement = null;

          try {
            // Obtenemos la conexión a la base de datos
            connection = ConexionBD.getConexion();

            // Creamos la sentencia SQL y la ejecutamos, devolviendo
            // el número de filas de la tabla de usuarios afectadas
            preparedStatement = connection.prepareStatement(
                "UPDATE mensajes SET nick=?, texto=?, fecha=? WHERE idMensaje = ?");
            preparedStatement.setString(1, mensaje.getNick());
	    preparedStatement.setString(2, mensaje.getTexto());
	    preparedStatement.setDate(3, mensaje.getFecha());
	    preparedStatement.setInt(4,mensaje.getIdMensaje());

            rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            preparedStatement = null;
            connection.close();
            connection = null;

            if (rowsAffected == 1)
	       return true;
	    else
	       return false;
          }
          finally {
           // Cerrar todo si es distinto de NULL
           if (preparedStatement != null){
             try{
               preparedStatement.close();
             }
             catch(SQLException e) { ; }
             preparedStatement = null;
           }
           if (connection != null){
             try{
               connection.close();
             }
             catch(SQLException e) { ; }
             connection = null;
           }
          }

        }


          public int getsize() throws ConexionBDException{
            ArrayList mens = this.getMensajes2();
            return mens.size();
          }

          /**
           *  Obtiene la instancia del gestor de cartas.
           *
           *@return    Gestor de Cartas
           */
          public static MensajesBD getGestorMensajes() {
            // Si aún no existe ningún gestor, lo creamos
            if (elGestor == null) {
              elGestor = new MensajesBD();
            }
            return elGestor;
            }
}
