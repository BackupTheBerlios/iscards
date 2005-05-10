package genesis.noticias;

/*
 *  Gestors.java
 *
 */


import genesis.bd.*;
import java.util.*;
import javax.sql.*;
import java.sql.*;
import java.sql.Date;

/**
 * Realiza diversas operaciones con la tabla de noticias de la
 * base de datos de Génesis. Al contrario que los demás gestores,
 * no hay ninguna variable de entorno global que contenga un objeto
 * de esta clase. La única instancia de esta clase se accede a
 * través del método estático <tt>getGestorNoticias()</tt> de
 * esta misma clase.
 *
 *@author   Laura García
 * Revisado por: David B. Jenkins López.
 */
public class GestorNoticias {

  private static GestorNoticias elGestor;

  /**
   * Constructor por defecto
   */
  public GestorNoticias() {
  }


  public Noticia getMedianteId(int id) 
      throws ConexionBDException, SQLException, NoticiaNoExistenteException {
    Noticia result = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();
      
      preparedStatement = connection.prepareStatement("SELECT id,titulo,tipo,contenido,fecha FROM noticias WHERE id = ?");
      preparedStatement.setInt(1,id);
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
	 result = resultSetANoticia(resultSet);
      } else {
	 throw new NoticiaNoExistenteException("No existe la noticia con ID=" + id, id);
      }
      resultSet.close();
      resultSet = null;
      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;
      return result;
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
   * Obtiene el conjunto de noticias existente en la base de datos.
   * *@return   La lista de noticias
   *@exception  ConexionBDException  Error al conectarse a la base de datos
   *@exception  SQLException         Error en la sentencia SQL
   */
  public ArrayList getNoticias(String indice)
      throws ConexionBDException, SQLException {
      int ind = (int) new Integer(indice).intValue();
     ArrayList result = new ArrayList();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();
      
      preparedStatement = connection.prepareStatement("SELECT * from noticias ORDER BY fecha DESC LIMIT " + ind + ",3");
//      preparedStatement.setInt(1,ind);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        result.add(resultSetANoticia(resultSet));
      }
      resultSet.close();
      resultSet = null;
      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;
      return result;
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
   * Obtiene el conjunto de noticias existente en la base de datos.
   * *@return   La lista de noticias
   *@exception  ConexionBDException  Error al conectarse a la base de datos
   *@exception  SQLException         Error en la sentencia SQL
   */
  public ArrayList getNoticias(int indice, int cantidad)
      throws ConexionBDException, SQLException {
     ArrayList result = new ArrayList();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();
      
      preparedStatement = connection.prepareStatement("SELECT * from noticias ORDER BY fecha DESC LIMIT " + indice + "," + cantidad);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        result.add(resultSetANoticia(resultSet));
      }
      resultSet.close();
      resultSet = null;
      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;
      return result;
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
  public ArrayList getNoticias2()
        throws ConexionBDException, SQLException {

       ArrayList result = new ArrayList();
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      ResultSet resultSet = null;

      try {
        // Obtenemos la conexión a la base de datos
        connection = ConexionBD.getConexion();

        preparedStatement = connection.prepareStatement("SELECT * from noticias ORDER BY fecha DESC");
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
          result.add(resultSetANoticia(resultSet));
        }
        resultSet.close();
        resultSet = null;
        preparedStatement.close();
        preparedStatement = null;
        connection.close();
        connection = null;
        return result;
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
   * Añade una noticia a la base de datos
   *
   *@param  noticia                      Noticia a añadir
   *@exception  ConexionBDException      Error de conexión a la base de datos
   *@exception  SQLException             Error en la sentencia SQL
   *@exception  NoticiaExistenteException  Error: noticia existente
   */
  public void añadirNoticia(Noticia noticia)
      throws NoticiaExistenteException, ConexionBDException, SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();
      preparedStatement =
          connection.prepareStatement("INSERT INTO noticias " +
          "(titulo, tipo, contenido, fecha) VALUES " +
          "(?,?,?,?)");

      preparedStatement.setString(1, noticia.getTitulo());
      preparedStatement.setString(2, noticia.getTipo());
      preparedStatement.setString(3, noticia.getContenido());
      preparedStatement.setDate(4, noticia.getFecha());

      // Si hubo más de una fila afectada
      if (preparedStatement.executeUpdate() != 1) {
        // La inserción habrá fallado
        throw new NoticiaExistenteException("Ya existe la noticia: " + noticia.getTitulo(), noticia.getTitulo());
      }
      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;
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
   *  Actualiza la noticia cuyo titulo se encuentra en el
   *  objeto noticia pasado como parámetro con la información también
   *  contenida en dicho objeto
   *
   *@param  noticia                       Titulo de la noticia a buscar
   *					y nueva información de ésta.
   *@exception  ConexionBDException        Error al conectarse a la base de datos
   *@exception  SQLException               Error en la sentencia SQL
   *@exception  noticiaNoExistenteException  Error: la noticia no existe
   */
  public void actualizarNoticia(Noticia noticia)
      throws NoticiaNoExistenteException, ConexionBDException, SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();

      preparedStatement =
          connection.prepareStatement("UPDATE noticias SET " +
          "tipo=?, contenido=?, fecha=?, titulo=? WHERE id=?");

      preparedStatement.setString(1, noticia.getTipo());
      preparedStatement.setString(2, noticia.getContenido());
      preparedStatement.setDate(3, noticia.getFecha());
      preparedStatement.setString(4, noticia.getTitulo());
      preparedStatement.setInt(5, noticia.getId());

      if (preparedStatement.executeUpdate() != 1) {
        throw new NoticiaNoExistenteException("No existe la noticia: " + noticia.getTitulo(), noticia.getId());
      }
      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;
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

  
  public void borrarNoticia(int id)
      throws NoticiaNoExistenteException, ConexionBDException, SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();

      preparedStatement =
          connection.prepareStatement("DELETE FROM noticias WHERE id = ?");

      preparedStatement.setInt(1, id);

      if (preparedStatement.executeUpdate() != 1) {
        throw new NoticiaNoExistenteException("No existe la noticia: " + id, id);
      }
      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;
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


  public int getNumeroNoticias() 
      throws ConexionBDException, SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    int result = -1;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();
      
      preparedStatement = connection.prepareStatement("SELECT COUNT(id) FROM noticias");
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
	 result = resultSet.getInt(1);
      } 
      resultSet.close();
      resultSet = null;
      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;
      return result;
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
   *  Convierte el resultado actual al que apunta el ResultSet
   *  en un objeto de la clase Noticia
   *
   *@param  resultSet                ResultSet que apunta a una fila
   *			 de la tabla de noticias de la base de datos.
   *@return                   Objeto noticia con la información contenida
   *			 en dicha fila
   *@exception  SQLException  Error al obtener los elementos de la fila
   */
  private Noticia resultSetANoticia(ResultSet resultSet)
      throws SQLException {

    int id = resultSet.getInt("id");
    String titulo = resultSet.getString("titulo");
    String tipo = resultSet.getString("tipo");
    String contenido = resultSet.getString("contenido");
    Date fecha = resultSet.getDate("fecha");
    return new Noticia(id,titulo,tipo, contenido,fecha);
  }



  /**
   *  Obtiene la instancia del gestor de noticias.
   *
   *@return    Gestor de Noticias
   */
  public static GestorNoticias getGestorNoticias() {
    // Si aún no existe ningún gestor, lo creamos
    if (elGestor == null) {
      elGestor = new GestorNoticias();
    }
    return elGestor;
  }

}



