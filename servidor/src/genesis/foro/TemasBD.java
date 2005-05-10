package genesis.foro;

import genesis.bd.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;

/**
 *  <p>
 *
 *  Title: TemasBD
 *
 *  Description: Base de Datos de todos los temas
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

public class TemasBD {
  private static TemasBD elGestor;
  public ArrayList temas;

  public static TemasBD getGestorTemas() {
             // Si aún no existe ningún gestor, lo creamos
         if (elGestor == null) {
                 elGestor = new TemasBD();
         }
         return elGestor;
  }


  /**
   *  Gets the Tema attribute of the TemasBD object
   *
   *@param  id  Identificador del tema
   *@return     The Tema value
   */
  public Tema getTema(String idt) throws ConexionBDException, SQLException{
    int id = (int) new Integer(idt).intValue();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();

      // Creamos la sentencia SQL
      preparedStatement = connection.prepareStatement(
          "SELECT idTema, titulo, estado, fecha, cont_visitas FROM temas WHERE idTema = ?");
      // Cambiamos el ? por la id
      preparedStatement.setInt(1, id);

      // Ejecutamos la sentencia
      resultSet = preparedStatement.executeQuery();

      // Si se encontró un resultado
      if (resultSet.next()) {
        // Creamos una instancia de la clase Mensaje a partir
        // de ese resultado
        Tema tema = new Tema(
            resultSet.getInt(1), resultSet.getString(2),resultSet.getInt(3),resultSet.getDate(4), resultSet.getInt(5));

        resultSet.close();
        resultSet = null;
        preparedStatement.close();
        preparedStatement = null;
        connection.close();
        connection = null;
        return tema;
      }
      else {
        return null;
      }
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
   *  Gets the Temas attribute of the TemasBD object
   *
   *@return    The Temas value
   */
  public ArrayList getTemas(String indic) throws ConexionBDException, SQLException{
    int indice = (int) new Integer(indic).intValue();
    temas = new ArrayList();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();

      // Creamos la sentencia SQL: Obtener todos los mensajes
      preparedStatement = connection.prepareStatement(
          "SELECT idTema, titulo, estado, fecha, cont_visitas FROM temas LIMIT "+indice+",8");
//      preparedStatement.setInt(1, indice);
      // Y la ejecutamos
      resultSet = preparedStatement.executeQuery();

      // Mientras haya resultados de la consulta
      while (resultSet.next()) {
        // Creamos un mensaje con el resultado actual
        Tema tema = new Tema(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),resultSet.getDate(4), resultSet.getInt(5));
        // Y lo añadimos a la lista
        temas.add(tema);
      }
      resultSet.close();
      resultSet = null;
      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;
      return temas;
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

  public ArrayList getTemas2() throws ConexionBDException, SQLException{
    temas = new ArrayList();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();
      // Creamos la sentencia SQL: Obtener todos los mensajes
      preparedStatement = connection.prepareStatement(
          "SELECT idTema, titulo, estado, fecha, cont_visitas FROM temas");
      // Y la ejecutamos
      resultSet = preparedStatement.executeQuery();

      // Mientras haya resultados de la consulta
      while (resultSet.next()) {
        // Creamos un mensaje con el resultado actual
        Tema tema = new Tema(resultSet.getInt(1), resultSet.getString(2),resultSet.getInt(3), resultSet.getDate(4), resultSet.getInt(5));

	System.out.println("Añadiendo temas: " + tema.getTitulo());
        // Y lo añadimos a la lista
        temas.add(tema);
      }
      resultSet.close();
      resultSet = null;
      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;
      return temas;
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
   *  Description of the Method
   *
   *@param  tema  Tema que queremos insertar
   *@return       Description of the Returned Value
   */
  public int insertarTema(Tema tema) throws ConexionBDException, SQLException{
    int rowsAffected = 0;
    int rowsAffected2 = 0;
    String s="sysdate";
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    PreparedStatement preparedStatement2 = null;
    ResultSet resultSet = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();
      // Creamos la sentencia SQL
      preparedStatement = connection.prepareStatement(
          "INSERT INTO temas (idTema, titulo, estado, fecha, cont_visitas)" +
          "VALUES (?, ?, ?, ?, ?)");
      preparedStatement.setInt(1, tema.getIdTema());
      preparedStatement.setString(2, tema.getTitulo());
      preparedStatement.setInt(3, tema.getEstado());
      preparedStatement.setDate(4, tema.getFecha());
      preparedStatement.setInt(5, tema.getCont_visitas());

/*      preparedStatement2 = connection.prepareStatement(
          "UPDATE temas SET fecha = ? WHERE idTema =?");
      preparedStatement2.setString(1,"2005-04-10");
      preparedStatement2.setInt(2, tema.getIdTema());
*/
      preparedStatement2 = connection.prepareStatement(
          "UPDATE temas SET fecha = curdate() WHERE idTema =?");
      //preparedStatement2.setString(1,"2005-04-10");
      preparedStatement2.setInt(1, tema.getIdTema());




      // La ejecutamos y devolvemos el número de filas afectadas
      rowsAffected = preparedStatement.executeUpdate();
      rowsAffected2= preparedStatement2.executeUpdate();
//      resultSet.close();
      resultSet = null;
      preparedStatement.close();
      preparedStatement = null;
      preparedStatement2.close();
      preparedStatement2 = null;
      connection.close();
      connection = null;

      return (rowsAffected + rowsAffected2);
    }
    catch (SQLException e) {
      return 0;
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
   *  Description of the Method
   *
   *@param  id  Identificador del tema que queremos borrar
   *@return     Description of the Returned Value
   */
  public boolean borrarTema(int id) throws ConexionBDException, SQLException{
    int rowsAffected = 0;
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();

      // Creamos la sentencia SQL y la ejecutamos, devolviendo
      // el número de filas de la tabla de usuarios afectadas
      preparedStatement = connection.prepareStatement
      ("DELETE FROM temas WHERE idTema = ?");
      preparedStatement.setInt(1, id);
      rowsAffected = preparedStatement.executeUpdate();

      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;

      if (rowsAffected == 1) {
         return true;
      } else {
	 return false;
      }
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


// Método sólo accesible por el administrador
/**
 *  Description of the Method
 *
 *@param  tema  Tema que queremos modificar
 *@return       Description of the Returned Value
 */
  public int modificarTema(Tema tema) throws ConexionBDException, SQLException{
    int rowsAffected = 0;
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();

      // Igual que los anteriores métodos
      preparedStatement = connection.prepareStatement(
          "UPDATE temas SET titulo=?, estado=?, fecha=?, cont_visitas=? WHERE idTema =?");
      preparedStatement.setInt(5, tema.getIdTema());
      preparedStatement.setString(1, tema.getTitulo());
      preparedStatement.setInt(2, tema.getEstado());
      preparedStatement.setDate(3, tema.getFecha());
      preparedStatement.setInt(4, tema.getCont_visitas());

      rowsAffected = preparedStatement.executeUpdate();
      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;
      return rowsAffected;
    }
    catch (SQLException e) {
      return 0;
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
 *  Description of the Method
 *
 *@param  tema  Tema que queremos modificar
 *@return       Description of the Returned Value
 */
  public boolean abrirTema(int idTema) throws ConexionBDException, SQLException{
    int rowsAffected = 0;
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();

      // Igual que los anteriores métodos
      preparedStatement = connection.prepareStatement(
          "UPDATE temas SET estado=0 WHERE idTema = ?");
      preparedStatement.setInt(1, idTema);

      rowsAffected = preparedStatement.executeUpdate();
      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;
      if (rowsAffected == 1) {
	 return true;
      } else {
	 return false;
      }
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
 *  Description of the Method
 *
 *@param  tema  Tema que queremos modificar
 *@return       Description of the Returned Value
 */
  public boolean cerrarTema(int idTema) throws ConexionBDException, SQLException{
    int rowsAffected = 0;
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();

      // Igual que los anteriores métodos
      preparedStatement = connection.prepareStatement(
          "UPDATE temas SET estado=1 WHERE idTema = ?");
      preparedStatement.setInt(1, idTema);

      rowsAffected = preparedStatement.executeUpdate();
      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;
      if (rowsAffected == 1) {
	 return true;
      } else {
	 return false;
      }
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


           public int getsize()throws ConexionBDException, SQLException{
               Collection tem =this.getTemas2();
               return tem.size();
              }
}
