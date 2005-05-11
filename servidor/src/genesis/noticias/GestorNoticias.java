package genesis.noticias;

import genesis.bd.*;
import java.util.*;
import javax.sql.*;
import java.sql.*;
import java.sql.Date;

/**
 *  Realiza diversas operaciones con la tabla de noticias de la base de datos
 *  de G�nesis. La �nica instancia de esta clase se accede a trav�s del 
 *  m�todo est�tico <tt>getGestorNoticias()</tt> de esta misma clase.
 *
 *@author    Laura Garc�a 
 * Revisado por: David B. Jenkins L�pez, Manuel Montenegro.
 */
public class GestorNoticias {
   
        // �nica instancia del gestor de noticias.
	private static GestorNoticias elGestor;



	/**
	 *  Obtiene una noticia a trav�s de su identificador 
	 *
	 *@param  id                               Identificador de la noticia que se desea
	 *					      buscar. Si la noticia no existe se lanza una excepci�n
	 *@return                                  Noticia correspondiente a ese identificador
	 *@exception  ConexionBDException          No se pudo obtener la conexi�n a la base de datos
	 *@exception  SQLException                 Error interno de la base de datos
	 *@exception  NoticiaNoExistenteException  La noticia no existe.
	 */
	public Noticia getMedianteId(int id)
			 throws ConexionBDException, NoticiaNoExistenteException, SQLException {
		Noticia result = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Obtenemos la conexi�n a la base de datos
			connection = ConexionBD.getConexion();

		        // Creamos la consulta y la ejecutamos
			preparedStatement = connection.prepareStatement("SELECT id,titulo,tipo,contenido,fecha FROM noticias WHERE id = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			
			if (resultSet.next()) {
			     // Si hay resultados, obtenemos la noticia a partir del ResultSet
				result = resultSetANoticia(resultSet);
			}
			else {
			     // Si no hay resultados, lanzamos la excepci�n
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
	 * OBSOLETO: Se mantiene temporalmente por compatibilidad con los JSPs
	 *  Sustituido por Collection getNoticias(int indice, int longitud)
	 * 
	 *  Obtiene el conjunto de noticias existente en la base de datos.
	 *
	 *@param  indice                   Description of Parameter
	 *@return                          The Noticias value
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
			// Obtenemos la conexi�n a la base de datos
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
	 * Obtiene un determinado n�mero de noticias de la base de datos
	 *
	 *@param  indice                   Indice de la primera noticia a la que se quiere acceder
	 *@param  cantidad                 N�mero de noticias a recuperar
	 *@return                          Una lista de noticias
	 *@exception  ConexionBDException  Error al conectarse a la base de datos
	 *@exception  SQLException         Error interno de la base de datos.
	 */
	public Collection getNoticias(int indice, int cantidad)
			 throws ConexionBDException, SQLException {
		ArrayList result = new ArrayList();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Obtenemos la conexi�n a la base de datos
			connection = ConexionBD.getConexion();

			// Creamos la consulta y la ejecutamos
			preparedStatement = connection.prepareStatement("SELECT * from noticias ORDER BY fecha DESC LIMIT " + indice + "," + cantidad);
			resultSet = preparedStatement.executeQuery();

			// Guardamos los resultados en el ArrayList
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
	 * OBSOLETO: �ste m�todo se utiliza para obtener el n�mero de noticias
	 * y es reemplazado por getNumeroNoticias()
	 * 
	 *  Gets the Noticias2 attribute of the GestorNoticias object
	 *
	 *@return                          The Noticias2 value
	 *@exception  ConexionBDException  Description of Exception
	 *@exception  SQLException         Description of Exception
	 */
	public ArrayList getNoticias2()
			 throws ConexionBDException, SQLException {

		ArrayList result = new ArrayList();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Obtenemos la conexi�n a la base de datos
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
	 *  Obtiene el n�mero de noticias que hay en la base de datos
	 *
	 *@return                          N� de noticias
	 *@exception  ConexionBDException  Error al conectarse a la base de datos
	 *@exception  SQLException         Error interno de la base de datos.
	 */
	public int getNumeroNoticias()
			 throws ConexionBDException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int result = -1;

		try {
			// Obtenemos la conexi�n a la base de datos
			connection = ConexionBD.getConexion();

			// Creamos la consulta SQL y la ejecutamos
			preparedStatement = connection.prepareStatement("SELECT COUNT(id) FROM noticias");
			resultSet = preparedStatement.executeQuery();

			// Si devuelve resultados (lo cual deber�a ocurrir siempre), los recuperamos
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
	 *  A�ade una noticia a la base de datos. El identificador lo asignar�
	 *  autom�ticamente MySQL.
	 *
	 *@param  noticia                        Noticia a a�adir. El identificador <b>NO</b> se utilizar�
	 *@exception  ConexionBDException        Error de conexi�n a la base de datos
	 *@exception  SQLException               Error interno de la base de datos
	 *@exception  NoticiaExistenteException  Error: noticia existente
	 */
	public void a�adirNoticia(Noticia noticia)
			 throws ConexionBDException, NoticiaExistenteException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Obtenemos la conexi�n a la base de datos
			connection = ConexionBD.getConexion();
			
			// Creamos la sentencia SQL y la ejecutamos
			preparedStatement =
					connection.prepareStatement("INSERT INTO noticias " +
					"(titulo, tipo, contenido, fecha) VALUES " +
					"(?,?,?,?)");

			preparedStatement.setString(1, noticia.getTitulo());
			preparedStatement.setString(2, noticia.getTipo());
			preparedStatement.setString(3, noticia.getContenido());
			preparedStatement.setDate(4, noticia.getFecha());

			// Si hubo menos de una fila afectada
			if (preparedStatement.executeUpdate() != 1) {
				// La inserci�n habr� fallado
				throw new NoticiaExistenteException("Ya existe la noticia: " + noticia.getTitulo(), noticia.getId());
			}
			preparedStatement.close();
			preparedStatement = null;
			connection.close();
			connection = null;
		}
		finally {
			// Cerrar todo si es distinto de NULL
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
	 *  Actualiza la noticia cuyo identificador se encuentra en el objeto noticia pasado
	 *  como par�metro con la informaci�n tambi�n contenida en dicho objeto
	 *
	 *@param  noticia                          Identificador de la noticia a buscar y
	 *                                          nueva informaci�n de �sta.
	 *@exception  ConexionBDException          Error al conectarse a la base de datos
	 *@exception  SQLException                 Error interno en la base de datos
	 *@exception  NoticiaNoExistenteException  La noticia no existe
	 */
	public void actualizarNoticia(Noticia noticia)
			 throws ConexionBDException, NoticiaNoExistenteException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Obtenemos la conexi�n a la base de datos
			connection = ConexionBD.getConexion();

			// Creamos la consulta y la ejecutamos
			preparedStatement =
					connection.prepareStatement("UPDATE noticias SET " +
					"tipo=?, contenido=?, fecha=?, titulo=? WHERE id=?");

			preparedStatement.setString(1, noticia.getTipo());
			preparedStatement.setString(2, noticia.getContenido());
			preparedStatement.setDate(3, noticia.getFecha());
			preparedStatement.setString(4, noticia.getTitulo());
			preparedStatement.setInt(5, noticia.getId());

			// Si se ha actualizado m�s o menos de una fila
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
	 *  Borra la noticia especificada por un identificador
	 *
	 *@param  id                               Identificador de la noticia a borrar
	 *@exception  NoticiaNoExistenteException  La noticia no existe
	 *@exception  ConexionBDException          Error al conectarse a la base de datos 
	 *@exception  SQLException                 Error interno de MySQL
	 */
	public void borrarNoticia(int id)
			 throws ConexionBDException, NoticiaNoExistenteException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Obtenemos la conexi�n a la base de datos
			connection = ConexionBD.getConexion();

			// Creamos la consulta y la ejecutamos
			preparedStatement =
					connection.prepareStatement("DELETE FROM noticias WHERE id = ?");

			preparedStatement.setInt(1, id);

			// Si se han actualizado cero filas, es que la noticia no exist�a
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
	 *  Convierte el resultado actual al que apunta el ResultSet en un objeto de
	 *  la clase Noticia
	 *
	 *@param  resultSet         ResultSet que apunta a una fila de la tabla de
	 *                            noticias de la base de datos.
	 *@return                   Objeto noticia con la informaci�n contenida en
	 *                            dicha fila
	 *@exception  SQLException  Error al obtener los elementos de la fila
	 */
	private Noticia resultSetANoticia(ResultSet resultSet)
			 throws SQLException {

		int id = resultSet.getInt("id");
		String titulo = resultSet.getString("titulo");
		String tipo = resultSet.getString("tipo");
		String contenido = resultSet.getString("contenido");
		Date fecha = resultSet.getDate("fecha");
		return new Noticia(id, titulo, tipo, contenido, fecha);
	}



	/**
	 *  Obtiene la instancia del gestor de noticias.
	 *
	 *@return	La �nica instancia del gestor de noticias
	 */
	public static GestorNoticias getGestorNoticias() {
		// Si a�n no existe ning�n gestor, lo creamos
		if (elGestor == null) {
			elGestor = new GestorNoticias();
		}
		return elGestor;
	}

}


