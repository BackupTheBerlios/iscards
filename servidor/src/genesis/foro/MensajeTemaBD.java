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
 *  Title: MensajeTemaBD
 *
 *  Description: Base de Datos de la relacion de los mensajes con sus temas correspondientes
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

public class MensajeTemaBD {
        private ArrayList mensajesTemas;
        public static MensajeTemaBD elGestor = null;

        public static MensajeTemaBD getGestorMensajeTema() {
                   // Si aún no existe ningún gestor, lo creamos
               if (elGestor == null) {
                       elGestor = new MensajeTemaBD();
               }
               return elGestor;
  }




        /**
         *  Gets the Mensajes attribute of the MensajeTemaBD object
         *
         *@param  idTema  Identificador del tema
         *@return         The Mensajes value
         */
        public ArrayList getMensajes(String idT) throws ConexionBDException{
          int idTema = (int) new Integer(idT).intValue();
          mensajesTemas = new ArrayList();
          Connection connection = null;
          PreparedStatement preparedStatement = null;
          PreparedStatement preparedStatement2 = null;
          ResultSet resultSet = null;
          ResultSet resultSet2 = null;

          try {
            // Obtenemos la conexión a la base de datos
            connection = ConexionBD.getConexion();

            // Creamos la sentencia SQL
            preparedStatement = connection.prepareStatement(
                "SELECT idMensaje FROM mensaje_tema WHERE idTema = ?");
            // Cambiamos el ? por la id
            preparedStatement.setInt(1, idTema);
            // Y la ejecutamos
            resultSet = preparedStatement.executeQuery();
            // Mientras haya resultados de la consulta
            while (resultSet.next()) {
              preparedStatement2 = connection.prepareStatement(
                  "SELECT nick, texto, fecha FROM mensajes WHERE idMensaje = ?");
              // Cambiamos el ? por la id
              preparedStatement2.setInt(1, resultSet.getInt(1));
              // Y la ejecutamos
              resultSet2 = preparedStatement2.executeQuery();
              while (resultSet2.next()){
                // Creamos un mensaje con el resultado actual
                int idm = resultSet.getInt(1);
                Mensaje mensaje = new Mensaje(
                    idm, resultSet2.getString(1), resultSet2.getString(2),resultSet2.getDate(3));
                // Y lo añadimos a la lista
                mensajesTemas.add(mensaje);
              }
            }
/*            resultSet2.close();
            resultSet2 = null;
            resultSet.close();
            resultSet = null;
            preparedStatement2.close();
            preparedStatement2 = null;
            preparedStatement.close();
            preparedStatement = null;*/
            connection.close();
            connection = null;
            return mensajesTemas;
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
            if (resultSet2 != null){
             try{
               resultSet2.close();
             }
             catch(SQLException e) { ; }
             resultSet2 = null;
            }
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
                 *  Gets the Mensajes attribute of the MensajeTemaBD object
                 *
                 *@param  idTema  Identificador del tema
                 *@return         The Mensajes value
                 */
                public ArrayList getMensajes2(String idT,String ind) throws ConexionBDException, SQLException{
                  int idTema = (int) new Integer(idT).intValue();
                  int indice = (int) new Integer(ind).intValue();
                  mensajesTemas = new ArrayList();
                  Connection connection = null;
                  PreparedStatement preparedStatement = null;
                  PreparedStatement preparedStatement2 = null;
                  ResultSet resultSet = null;
                  ResultSet resultSet2 = null;

                  try {
                    // Obtenemos la conexión a la base de datos
                    connection = ConexionBD.getConexion();

                    // Creamos la sentencia SQL
                    preparedStatement = connection.prepareStatement(
                        "SELECT idMensaje FROM mensaje_tema WHERE idTema = ? LIMIT "+indice+",8");
                    // Cambiamos el ? por la id
                    preparedStatement.setInt(1, idTema);
//                    preparedStatement.setInt(2, indice);
                    // Y la ejecutamos
                    resultSet = preparedStatement.executeQuery();
                    // Mientras haya resultados de la consulta
                    while (resultSet.next()) {
                      preparedStatement2 = connection.prepareStatement(
                          "SELECT nick, texto, fecha FROM mensajes WHERE idMensaje = ?");
                      // Cambiamos el ? por la id
                      preparedStatement2.setInt(1, resultSet.getInt(1));
                      // Y la ejecutamos
                      resultSet2 = preparedStatement2.executeQuery();
                      while (resultSet2.next()){
                        // Creamos un mensaje con el resultado actual
                        Mensaje mensaje = new Mensaje(
                            resultSet.getInt(1), resultSet2.getString(1), resultSet2.getString(2),resultSet2.getDate(3));
                        // Y lo añadimos a la lista
                        mensajesTemas.add(mensaje);
                      }
                    }
/*                    resultSet.close();
                    resultSet = null;
                    resultSet2.close();
                    resultSet2 = null;
                    preparedStatement.close();
                    preparedStatement = null;
                    preparedStatement2.close();
                    preparedStatement2 = null;*/
                    connection.close();
                    connection = null;
                    return mensajesTemas;
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
                    if (resultSet2 != null){
                     try{
                       resultSet2.close();
                     }
                     catch(SQLException e) { ; }
                     resultSet2 = null;
                    }
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
         *  Gets the Tema attribute of the MensajeTemaBD object
         *
         *@param  id  Identificador del mensaje
         *@return     The Tema value
         */
        public int getTema(int id) throws ConexionBDException{
          Connection connection = null;
          PreparedStatement preparedStatement = null;
          ResultSet resultSet = null;
          int resultado;

          try {
            // Obtenemos la conexión a la base de datos
            connection = ConexionBD.getConexion();

            // Creamos la sentencia SQL
            preparedStatement = connection.prepareStatement(
                "SELECT idTema FROM mensaje_tema WHERE idMensaje = ?");
            // Cambiamos el ? por la id
            preparedStatement.setInt(1, id);

            // Ejecutamos la sentencia
            resultSet = preparedStatement.executeQuery();

            // Si se encontró un resultado
            if (resultSet.next()) {
              // Creamos una instancia de la clase Mensaje a partir
              // de ese resultado

              // Liberamos la conexión y devolvemos el resultado
              preparedStatement.close();
              resultado = resultSet.getInt(2);
            }
            else {
              resultado = -1;
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
            return -1;
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
}
