/*
 *  GestorCartas.java
 *
 */
package genesis.cartas;

import genesis.bd.*;
import java.util.*;
import javax.sql.*;
import java.sql.*;

/**
 * Realiza diversas operaciones con la tabla de cartas de la
 * base de datos de Génesis. Al contrario que los demás gestores,
 * no hay ninguna variable de entorno global que contenga un objeto
 * de esta clase. La única instancia de esta clase se accede a
 * través del método estático <tt>getGestorCartas()</tt> de
 * esta misma clase.
 *
 *@author   Manuel Montenegro
 * Revisado por: David B. Jenkins López.
 */
public class GestorCartas {

  private static GestorCartas elGestor;


	/**
	 * Obtiene un conjunto de cartas mediante su nivel. Se devolverán
	 * todas las cartas cuyo nivel sea el indicado
	 *
	 *@param  entero                  nivel de las cartas a devolver
         *
	 *@return                          La lista de cartas que se busca
	 *@exception  ConexionBDException  Error al conectarse a la base de datos
	 *@exception  SQLException         Error en la sentencia SQL
	 */
	public ArrayList getCartasPorNivel(int nivel)
			 throws ConexionBDException, SQLException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;


		ArrayList result = new ArrayList();

		try {
		   	con = ConexionBD.getConexion();

			st = con.prepareStatement("SELECT * from cartas WHERE nivel = ? ");
			st.setInt(1, nivel);

			rs = st.executeQuery();

			while (rs.next()) {
				result.add(rs.getString("id_carta"));
			}

			rs.close();
			rs = null;

			st.close();
			st = null;

			con.close();
			con = null;

		}
		finally {
			if (rs != null) try {rs.close();} catch (SQLException e) {;}
			if (st != null) try {st.close();} catch (SQLException e) {;}
			if (con != null) try {con.close();} catch (SQLException e) {;}
		}
		return result;
	}
  /**
   * Obtiene un conjunto de cartas mediante su nombre. Se devolverán
   * todas las cartas cuyo nombre contenga todas las palabras pasadas
   * como parámetro
   *
   *@param  cadena                   Conjunto de palabras separadas por
   *			       espacios, tabuladores o fines de línea.
   *@return                          La lista de cartas que contienen todas
   *			       las palabras de la entrada
   *@exception  ConexionBDException  Error al conectarse a la base de datos
   *@exception  SQLException         Error en la sentencia SQL
   */
  public ArrayList getMedianteNombre(String cadena)
      throws ConexionBDException, SQLException {
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    // Dividiremos la cadena de entrada en tokens, separados por
    // espacios, tabuladores o fines de línea.
    StringTokenizer tokenizer = new StringTokenizer(cadena);
    ArrayList result = new ArrayList();

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();

      // A partir de las palabras creamos una cadena de búsqueda
      // con todos los huecos rellenados con %, que en SQL significan
      // 'cualquier grupo de carácteres'
      //
      // Ej: Si la entrada es "ardid aprendiz"
      // La cadena que se pasará a MySQL es:
      //    "%ardid%aprendiz%"
      StringBuffer cadenaBusqueda = new StringBuffer("%");
      while (tokenizer.hasMoreTokens()) {
        cadenaBusqueda.append(tokenizer.nextToken() + "%");
      }
      preparedStatement =
          connection.prepareStatement("SELECT * from cartas WHERE nombre LIKE ? ORDER BY nombre");
      preparedStatement.setString(1, cadenaBusqueda.toString());
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        result.add(resultSetACarta(resultSet));
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
    *  Obtiene el id de una carta de la base de datos a partir de su nombre
    *
    *@param  nombre                   El nombre de la carta a buscar
    *@return                          Si se encontró, devuelve el id; si no
    *				  devuelve null
    *@exception  ConexionBDException  Error al conectarse a la base de datos
    *@exception  SQLException         Error en la sentencia SQL
    */
   public String getIDMedianteNombre(String nombre)
       throws ConexionBDException, SQLException {
     Connection connection = null;
     PreparedStatement preparedStatement = null;
     ResultSet resultSet = null;
     String resultado;

     try {
       // Obtenemos la conexión a la base de datos
       connection = ConexionBD.getConexion();
       // Creamos la sentencia SQL
       preparedStatement =
           connection.prepareStatement("SELECT id_carta from cartas WHERE nombre = ?");
       preparedStatement.setString(1, nombre);
       // y la ejecutamos
       resultSet = preparedStatement.executeQuery();

       if (resultSet.next()) {
         // Si hubo un resultado, obtenemos el id de la carta a partir
         // del resultSet y la devolvemos
         resultado = resultSet.getString(1);
       }
       else {
         // Si no, devolvemos null
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

   /**
       *  Obtiene el id de una carta de la base de datos a partir de su nombre
       *
       *@param  nombre                   El nombre de la carta a buscar
       *@return                          Si se encontró, devuelve el id; si no
       *				  devuelve null
       *@exception  ConexionBDException  Error al conectarse a la base de datos
       *@exception  SQLException         Error en la sentencia SQL
       */
      public String getRazaTipoMedianteNombre(String nombre)
          throws ConexionBDException, SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String resultado;

        try {
          // Obtenemos la conexión a la base de datos
          connection = ConexionBD.getConexion();
          // Creamos la sentencia SQL
          preparedStatement =
              connection.prepareStatement("SELECT raza,tipo from cartas WHERE nombre = ?");
          preparedStatement.setString(1, nombre);
          // y la ejecutamos
          resultSet = preparedStatement.executeQuery();

          if (resultSet.next()) {
            // Si hubo un resultado, obtenemos el id de la carta a partir
            // del resultSet y la devolvemos
            resultado = resultSet.getString(1)+"/"+resultSet.getString(2)+"/";
          }
          else {
            // Si no, devolvemos null
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



  /**
   *  Obtiene una carta de la base de datos a partir de su identificador
   *
   *@param  id                       El identificador de la carta a buscar
   *@return                          Si se encontró, devuelve la carta; si no
   *				  devuelve null
   *@exception  ConexionBDException  Error al conectarse a la base de datos
   *@exception  SQLException         Error en la sentencia SQL
   */
  public Carta getMedianteID(String id)
      throws ConexionBDException, SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Carta resultado;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();
      // Creamos la sentencia SQL
      preparedStatement =
          connection.prepareStatement("SELECT * from cartas WHERE id_carta = ?");
      preparedStatement.setString(1, id);
      // y la ejecutamos
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        // Si hubo un resultado, creamos la carta a partir
        // del resultSet y la devolvemos
        resultado = resultSetACarta(resultSet);
      }
      else {
        // Si no, devolvemos null
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


  /**
   *  Obtiene una tabla Hash con todos los pares &lt;ID, Nombre&gt;
   *  de todas las cartas de una determinada raza
   *
   *@param  raza                     Cadena con el nombre de la raza
   *@return                          Tabla Hash con todos los identificadores
   *			       y los nombres de las cartas cuya raza es la
   *			       especificada com parámetro
   *@exception  ConexionBDException  Error de conexión a la base de datos
   *@exception  SQLException         Error en la sentencia SQL
   */
  public HashMap getIDs(String raza)
      throws ConexionBDException, SQLException {

    HashMap result = new HashMap();

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();

      // Creamos la sentencia SQL
      preparedStatement =
          connection.prepareStatement("SELECT id_carta,nombre FROM cartas WHERE raza = ?");
      preparedStatement.setString(1, raza);

      // Y la ejecutamos
      resultSet = preparedStatement.executeQuery();

      // Mientras haya resultados los añadimos a la tabla hash
      while (resultSet.next()) {
        String id = resultSet.getString("id_carta");
        String nombre = resultSet.getString("nombre");
        result.put(id, nombre);
      }
      resultSet.close();
      resultSet = null;
      preparedStatement.close();
      preparedStatement = null;
      connection.close();
      connection = null;

      return result;
    }
    catch (SQLException e) {
      throw e;
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
   * Añade una carta a la base de datos
   *
   *@param  carta                        Carta a añadir
   *@exception  ConexionBDException      Error de conexión a la base de datos
   *@exception  SQLException             Error en la sentencia SQL
   *@exception  CartaExistenteException  Error: carta existente
   */
  public void añadirCarta(Carta carta)
      throws CartaExistenteException, ConexionBDException, SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();

      preparedStatement =
          connection.prepareStatement("INSERT INTO cartas " +
          "(id_carta, nombre, raza, tipo, ataque, defensa, coste, puntos, " +
          "nivel, vida, habilidades, desc_movil) VALUES " +
          "(?,?,?,?,?,?,?,?,?,?,?,?)");

      preparedStatement.setString(1, carta.getCodigo());
      preparedStatement.setString(2, carta.getNombre());
      preparedStatement.setString(3, carta.getRaza());
      preparedStatement.setString(4, carta.getTipo());
      preparedStatement.setInt(5, carta.getAtaque().intValue());
      preparedStatement.setInt(6, carta.getDefensa().intValue());
      preparedStatement.setInt(7, carta.getCoste().intValue());
      preparedStatement.setInt(8, carta.getPuntos().intValue());
      preparedStatement.setInt(9, carta.getNivel().intValue());
      preparedStatement.setInt(10, carta.getVida().intValue());
      preparedStatement.setString(11, carta.getDescripHab());
      preparedStatement.setString(12, carta.getDescripMov());

      // Si hubo más de una fila afectada
      if (preparedStatement.executeUpdate() != 1) {
        // La inserción habrá fallado
        throw new CartaExistenteException("Ya existe la carta: " + carta.getCodigo(), carta.getCodigo());
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
   *  Actualiza la carta cuyo identificador se encuentra en el
   *  objeto carta pasado como parámetro con la información también
   *  contenida en dicho objeto
   *
   *@param  carta                          Identificador de la carta a buscar
   *					y nueva información de ésta.
   *@exception  ConexionBDException        Error al conectarse a la base de datos
   *@exception  SQLException               Error en la sentencia SQL
   *@exception  CartaNoExistenteException  Error: la carta no existe
   */
  public void actualizarCarta(Carta carta)
      throws CartaNoExistenteException, ConexionBDException, SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      // Obtenemos la conexión a la base de datos
      connection = ConexionBD.getConexion();
      preparedStatement =
          connection.prepareStatement("UPDATE cartas SET " +
          "nombre=?, raza=?, tipo=?, ataque=?, defensa=?, coste=?, puntos=?, " +
          "nivel=?, vida=?, habilidades=?, desc_movil=? WHERE id_carta=?");

      preparedStatement.setString(1, carta.getNombre());
      preparedStatement.setString(2, carta.getRaza());
      preparedStatement.setString(3, carta.getTipo());
      preparedStatement.setInt(4, carta.getAtaque().intValue());
      preparedStatement.setInt(5, carta.getDefensa().intValue());
      preparedStatement.setInt(6, carta.getCoste().intValue());
      preparedStatement.setInt(7, carta.getPuntos().intValue());
      preparedStatement.setInt(8, carta.getNivel().intValue());
      preparedStatement.setInt(9, carta.getVida().intValue());
      preparedStatement.setString(10, carta.getDescripHab());
      preparedStatement.setString(11, carta.getDescripMov());
      preparedStatement.setString(12, carta.getCodigo());

      if (preparedStatement.executeUpdate() != 1) {
        throw new CartaNoExistenteException("No existe la carta: " + carta.getCodigo(), carta.getCodigo());
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
   *  Convierte el resultado actual al que apunta el ResultSet
   *  en un objeto de la clase Carta
   *
   *@param  resultSet                ResultSet que apunta a una fila
   *			 de la tabla de cartas de la base de datos.
   *@return                   Objeto carta con la información contenida
   *			 en dicha fila
   *@exception  SQLException  Error al obtener los elementos de la fila
   */
  private Carta resultSetACarta(ResultSet resultSet)
      throws SQLException {

    String id = resultSet.getString("id_carta");
    String nombre = resultSet.getString("nombre");
    String raza = resultSet.getString("raza");
    String tipo = resultSet.getString("tipo");
    Integer ataque = new Integer(resultSet.getInt("ataque"));
    Integer defensa = new Integer(resultSet.getInt("defensa"));
    Integer coste = new Integer(resultSet.getInt("coste"));
    Integer puntos = new Integer(resultSet.getInt("puntos"));
    Integer nivel = new Integer(resultSet.getInt("nivel"));
    Integer vida = new Integer(resultSet.getInt("vida"));
    String habilidades = resultSet.getString("habilidades");
    String desc_movil = resultSet.getString("desc_movil");
    return new Carta(id, raza, tipo, nombre, nivel, puntos, ataque,
                     defensa, coste, vida, desc_movil, habilidades);
  }

  public ArrayList getNombres() throws ConexionBDException {
                  ArrayList cartas = new ArrayList();
                  Connection connection = null;
                  PreparedStatement preparedStatement = null;
                  ResultSet resultSet = null;

                  try {
                          // Obtenemos la conexión a la base de datos
                          connection = ConexionBD.getConexion();
                          // Creamos la sentencia SQL: Obtener todos los mensajes
                          preparedStatement = connection.prepareStatement(
                                          "SELECT nombre FROM cartas");
                          // Y la ejecutamos
                          resultSet = preparedStatement.executeQuery();

                          // Mientras haya resultados de la consulta
                          while (resultSet.next()) {

                                  // Y lo añadimos a la lista
                                  cartas.add(resultSet.getString(1));
                          }
                          resultSet.close();
                          resultSet = null;
                          preparedStatement.close();
                          preparedStatement = null;
                          connection.close();
                          connection = null;
                          return cartas;
                  }
                  catch (SQLException e) {
                          return null;
                  }
                  finally {
                          // Cerrar todo si es distinto de NULL
                          if (resultSet != null) {
                                  try {
                                          resultSet.close();
                                  }
                                  catch (SQLException e) {
                                          ;
                                  }
                                  resultSet = null;
                          }
                          if (preparedStatement != null) {
                                  try {
                                          preparedStatement.close();
                                  }
                                  catch (SQLException e) {
                                          ;
                                  }
                                  preparedStatement = null;
                          }
                          if (connection != null) {
                                  try {
                                          connection.close();
                                  }
                                  catch (SQLException e) {
                                          ;
                                  }
                                  connection = null;
                          }
                  }
          }


  /**
   *  Obtiene la instancia del gestor de cartas.
   *
   *@return    Gestor de Cartas
   */
  public static GestorCartas getGestorCartas() {
    // Si aún no existe ningún gestor, lo creamos
    if (elGestor == null) {
      elGestor = new GestorCartas();
    }
    return elGestor;
  }

}
