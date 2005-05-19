/*
 * UsuariosBD.java
 *
 * Created on 8 de diciembre de 2004, 15:46
 */
package genesis.usuarios;

import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;
import genesis.bd.*;
/**
 *
 * @author  Javi
 * Revisado por: David B. Jenkins López.
 */
public class UsuariosBD {

  private ArrayList usuarios;
  private static UsuariosBD elGestor;

  public static UsuariosBD getGestorUsuarios() {
    // Si aún no existe ningún gestor, lo creamos
    if (elGestor == null) {
      elGestor = new UsuariosBD();
    }
    return elGestor;
  }

  public Usuario getUsuario (String nick) throws ConexionBDException, SQLException{
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Usuario resultado;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();

      preparedStatement = connection.prepareStatement ("SELECT nick, password,nombre," +
          "  email, sexo, puntos, avatar FROM usuarios WHERE nick = ?");
      preparedStatement.setString (1, nick);
      resultSet = preparedStatement.executeQuery ();
      if (resultSet.next ()) {
        Usuario usuario = new Usuario (
            resultSet.getString (1), resultSet.getString (2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getFloat(6), resultSet.getString(7) );
        preparedStatement.close ();
        resultado = usuario;
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

  public int getNPaquetes (String nick) throws ConexionBDException, SQLException{
    int npaq;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    int resultado;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();

      preparedStatement = connection.prepareStatement ("SELECT npaquetes FROM usuarios WHERE nick = ?");
      preparedStatement.setString(1, nick);
      resultSet = preparedStatement.executeQuery ();
      if (resultSet.next ()){
        npaq = resultSet.getInt(1);
        preparedStatement.close();
        resultado = npaq;
      }
      else{
        resultado = 0;
      }
      resultSet.close();
      resultSet = null;
      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;

      return resultado;
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

  public int incremNPaquetes (String nick, int npaq) throws ConexionBDException, SQLException{
    int rowsAffected = 0;
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();

      preparedStatement = connection.prepareStatement("UPDATE usuarios SET npaquetes = npaquetes + ? WHERE nick = ?");
      preparedStatement.setInt(1,npaq);
      preparedStatement.setString(2,nick);
      rowsAffected = preparedStatement.executeUpdate ();

      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;

      return rowsAffected;
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

  public int decrementarPuntos(String nick, int puntos) throws ConexionBDException, SQLException{
    int rowsAffected = 0;
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();

      preparedStatement = connection.prepareStatement("UPDATE usuarios SET puntos = puntos - ? WHERE nick = ?");
      preparedStatement.setInt(1,puntos);
      preparedStatement.setString(2,nick);
      rowsAffected = preparedStatement.executeUpdate ();

      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;
      return rowsAffected;
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

  public int insertarUsuario (Usuario usuario) throws ConexionBDException, SQLException{
    int rowsAffected = 0;
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();

      preparedStatement = connection.prepareStatement ("INSERT INTO usuarios (nick, password, nombre, email, sexo, puntos, avatar) VALUES (?, ?, ?, ?, ?, ?, ?)");
      preparedStatement.setString (1, usuario.getNick());
      preparedStatement.setString (2, usuario.getPassword());
      preparedStatement.setString (3, usuario.getNombre());
      preparedStatement.setString (4, usuario.getEmail());
      preparedStatement.setString (5, usuario.getSexo());
      preparedStatement.setFloat (6, usuario.getPuntos());
      preparedStatement.setString (7, usuario.getAvatar());
      rowsAffected = preparedStatement.executeUpdate ();

      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;
      return rowsAffected;
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


  public int borrarUsuario (String nick) throws ConexionBDException, SQLException{
    int rowsAffected = 0;
    Connection connection = null;
    PreparedStatement preparedStatement = null;


    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();
      preparedStatement = connection.prepareStatement ("DELETE FROM usuarios WHERE nick = ?");
      preparedStatement.setString (1, nick);
      rowsAffected = preparedStatement.executeUpdate ();

      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;
      return rowsAffected;
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


  public int modificarUsuario (Usuario usuario) throws ConexionBDException, SQLException{
    int rowsAffected = 0;
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();

      preparedStatement =
          connection.prepareStatement ("UPDATE usuarios SET password=?, nombre=?, email=?, sexo=?, avatar=? WHERE nick =?");
      preparedStatement.setString (1, usuario.getPassword());
      preparedStatement.setString (2, usuario.getNombre());
      preparedStatement.setString (3, usuario.getEmail());
      preparedStatement.setString (4, usuario.getSexo());
      // -------------
      preparedStatement.setString (5, usuario.getNick());
      preparedStatement.setString (6, usuario.getAvatar());
      rowsAffected = preparedStatement.executeUpdate ();

      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;
      return rowsAffected;
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

  public ArrayList getUsuarios () throws ConexionBDException, SQLException{
    usuarios = new ArrayList ();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();

      preparedStatement = connection.prepareStatement ("SELECT nick, password, nombre, email, sexo, puntos, avatar FROM usuarios");
      resultSet = preparedStatement.executeQuery ();
      while (resultSet.next ()) {
        Usuario usuario = new Usuario (
            resultSet.getString (1), resultSet.getString (2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getFloat(6), resultSet.getString(7) );
        usuarios.add (usuario);
      }

      resultSet.close();
      resultSet = null;
      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;

      return usuarios;
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

  public ArrayList getRanking() throws ConexionBDException, SQLException{
    usuarios = new ArrayList();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();

      preparedStatement = connection.prepareStatement ("SELECT nick, password, nombre, email, sexo, puntos, avatar FROM usuarios ORDER BY puntos DESC");
      resultSet = preparedStatement.executeQuery ();
      while (resultSet.next ()) {
        Usuario usuario = new Usuario (
            resultSet.getString (1), resultSet.getString (2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getFloat(6), resultSet.getString(7) );
        usuarios.add (usuario);
      }

      resultSet.close();
      resultSet = null;
      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;

      return usuarios;
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

