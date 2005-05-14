package genesis.foro;

import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;
import genesis.bd.*;

/**
 *  Proporciona operaciones sobre la tabla de mensajes de la base de datos.
 *  
 *  @author: Laura D�az, Conchi Fernandez, Laura Garc�a, In�s Gonz�lez, Sergio Somovilla 
 *
 *  Revisado por: David B. Jenkins L�pez, Manuel Montenegro
 */

public class MensajesBD {

	// �nica instancia del gestor de mensajes
	private static MensajesBD elGestor = null;


	/**
	 *  Obtiene un mensaje a partir de su identificador. Devuelve NULL si no hay
	 *  ning�n mensaje correspondiente al identificador proporcionado
	 *
	 *@param  id                       N� identificador del mensaje
	 *@return                          Mensaje correspondiente a ese identificador
	 *@exception  ConexionBDException  Error al obtener la conexi�n a la base de datos
	 *@exception  SQLException         Error interno de MySQL
	 */
	public Mensaje getMensaje(int id) throws ConexionBDException, SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Mensaje resultado = null;

		try {
			// Obtenemos la conexi�n a la base de datos
			connection = ConexionBD.getConexion();

			// Creamos la sentencia SQL
			preparedStatement = connection.prepareStatement(
					"SELECT idMensaje, nick, texto, fecha,idTema FROM mensajes WHERE idMensaje = ?");
			// Cambiamos el ? por la id
			preparedStatement.setInt(1, id);

			// Ejecutamos la sentencia
			resultSet = preparedStatement.executeQuery();

			// Si se encontr� un resultado
			if (resultSet.next()) {
				// Creamos una instancia de la clase Mensaje a partir
				// de ese resultado
				Mensaje mensaje = new Mensaje(
						resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4),
						resultSet.getInt(5));
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
	 * OBSOLETO: Reemplazado por getMensajes(int indice, int cantidad)
	 * 
	 *  Gets the Mensajes attribute of the MensajesBD object
	 *
	 *@param  indic                    Description of Parameter
	 *@return                          The Mensajes value
	 */
	public ArrayList getMensajes(String indic) throws ConexionBDException {
		int indice = (int) new Integer(indic).intValue();
		ArrayList mensajes = new ArrayList();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Obtenemos la conexi�n a la base de datos
			connection = ConexionBD.getConexion();
			// Creamos la sentencia SQL: Obtener todos los mensajes
			preparedStatement = connection.prepareStatement(
					"SELECT idMensaje, nick, texto, fecha, idTema FROM mensajes LIMIT ?,8");
			preparedStatement.setInt(1, indice);
			// Y la ejecutamos
			resultSet = preparedStatement.executeQuery();

			// Mientras haya resultados de la consulta
			while (resultSet.next()) {
				// Creamos un mensaje con el resultado actual
				Mensaje mensaje = new Mensaje(
						resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4),
						resultSet.getInt(5));
				// Y lo a�adimos a la lista
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
	 *  Obtiene una determinada cantidad de mensajes indicada por el par�metro
	 *  cantidad, empezando a contar por el mensaje indicado por el par�metro
	 *  indice.
	 *
	 *@param      indice               Primer mensaje a recuperar (0 es el primero)
	 *@param      cantidad		   N� de mensajes a recuperar
	 *@param      tema                 Tema al que pertenecen los mensajes
	 *@return                          Collection con los mensajes
	 *@exception  ConexionBDException  Error al obtener la conexi�n a la base de datos
	 *@exception  SQLException         Error interno de MySQL
	 */
	public Collection getMensajes(int tema, int indice, int cantidad) throws ConexionBDException, SQLException {
		Collection mensajes = new ArrayList();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Obtenemos la conexi�n a la base de datos
			connection = ConexionBD.getConexion();
			
			// Creamos la sentencia SQL: Obtener todos los mensajes
			preparedStatement = connection.prepareStatement(
					"SELECT idMensaje, nick, texto, fecha, idTema FROM mensajes WHERE idTema = ? ORDER BY fecha ASC LIMIT " + indice + "," + cantidad);
			preparedStatement.setInt(1, tema);
			
			// Y la ejecutamos
			resultSet = preparedStatement.executeQuery();

			// Mientras haya resultados de la consulta
			while (resultSet.next()) {
				// Creamos un mensaje con el resultado actual
				Mensaje mensaje = new Mensaje(
						resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), 
						resultSet.getInt(5));
				// Y lo a�adimos a la lista
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
	 * OBSOLETO: Este m�todo en realidad s�lo se usa para obtener el n�mero
	 * de mensajes. Para eso est� getSize()
	 * 
	 *  Gets the Mensajes2 attribute of the MensajesBD object
	 *
	 *@return                          The Mensajes2 value
	 */
	public ArrayList getMensajes2() throws ConexionBDException {
		ArrayList mensajes = new ArrayList();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Obtenemos la conexi�n a la base de datos
			connection = ConexionBD.getConexion();
			// Creamos la sentencia SQL: Obtener todos los mensajes
			preparedStatement = connection.prepareStatement(
					"SELECT idMensaje, nick, texto, fecha, tema FROM mensajes");
			// Y la ejecutamos
			resultSet = preparedStatement.executeQuery();

			// Mientras haya resultados de la consulta
			while (resultSet.next()) {
				// Creamos un mensaje con el resultado actual
				Mensaje mensaje = new Mensaje(
						resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4), resultSet.getInt(5));
				// Y lo a�adimos a la lista
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
	 * OBSOLETO: Cambiado por getSize()
	 * 
	 *  Description of the Method
	 *
	 *@return                          Description of the Returned Value
	 */
	public int getsize() throws ConexionBDException {
		ArrayList mens = this.getMensajes2();
		return mens.size();
	}


	/**
	 * Obtiene el n�mero total de mensajes de un determinado tema
	 *
	 *@param      tema                 Tema sobre el que contar los mensajes
	 *@return			   N� de entradas en la base de datos
	 *@exception  ConexionBDException  No se pudo obtener la conexi�n a la base de datos
	 *@exception  SQLException         Error interno del sistema gestor de bases de datos.
	 *
	 */
	public int getSize(int tema) throws ConexionBDException, SQLException {
		int result = -1;
	        Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Obtenemos la conexi�n a la base de datos
			connection = ConexionBD.getConexion();
			// Creamos la sentencia SQL: Obtener el n�mero de mensajes
			preparedStatement = connection.prepareStatement(
					"SELECT COUNT(*) FROM mensajes WHERE idTema=?");
			preparedStatement.setInt(1,tema);
			
			// Y la ejecutamos
			resultSet = preparedStatement.executeQuery();

			// La sentencia SIEMPRE deber�a devolver un resultado, luego esta
			// condici�n siempre se deber�a cumplir
			if (resultSet.next()) {
			        // Obtenemos el �nico resultado
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
	 *OBSOLETO: Cambiado por insertarMensaje(Mensaje). El idTema ya
	 * se incluye dentro del mensaje
	 * 
	 *  A�ade un mensaje a la tabla de mensajes devuelve 1 si se realiz�
	 *  correctamente, cualquier otro valor en caso de error.
	 *
	 *@param  mensaje                  Mensaje que queremos insertar en la Base
	 *      de Datos
	 *@param  idT                      Description of Parameter
	 *@return                          Description of the Returned Value
	 */
	public int insertarMensaje(Mensaje mensaje, String idT) throws ConexionBDException {
		int idTema = (int) new Integer(idT).intValue();
		int rowsAffected = 0;
		int rowsAffected2 = 0;
		int rowsAffected3 = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;

		try {
			// Obtenemos la conexi�n a la base de datos
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

			// La ejecutamos y devolvemos el n�mero de filas afectadas
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
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				}
				catch (SQLException e) {
					;
				}
				preparedStatement = null;
			}
			if (preparedStatement2 != null) {
				try {
					preparedStatement2.close();
				}
				catch (SQLException e) {
					;
				}
				preparedStatement2 = null;
			}
			if (preparedStatement3 != null) {
				try {
					preparedStatement3.close();
				}
				catch (SQLException e) {
					;
				}
				preparedStatement3 = null;
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
	 * 
	 *  A�ade un mensaje a la tabla de mensajes, dentro de un determinado tema
	 *
	 *@param  mensaje                  Mensaje que queremos insertar en la Base de Datos
	 *@return                          Valor booleano indicando si tuvo �xito o no 
	 *@exception  ConexionBDException  Error al obtener la conexi�n a la base de datos
	 *@exception  SQLException         Error interno de MySQL
	 */
	public boolean insertarMensaje(Mensaje mensaje) throws ConexionBDException, SQLException {
		int rowsAffected = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Obtenemos la conexi�n a la base de datos
			connection = ConexionBD.getConexion();

			// Creamos la sentencia SQL
			preparedStatement = connection.prepareStatement(
					"INSERT INTO mensajes (idMensaje, nick, texto, fecha, idTema)" +
					"VALUES (?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, mensaje.getIdMensaje());
			preparedStatement.setString(2, mensaje.getNick());
			preparedStatement.setString(3, mensaje.getTexto());
			preparedStatement.setDate(4, mensaje.getFecha());
			preparedStatement.setInt(5, mensaje.getIdTema());

			// La ejecutamos y devolvemos el n�mero de filas afectadas
			rowsAffected = preparedStatement.executeUpdate();

			preparedStatement.close();
			preparedStatement = null;
			connection.close();
			connection = null;

			return (rowsAffected == 1);
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
	 *  Elimina un mensaje de la tabla de mensajes
	 *
	 *@param  id                       Identificador del mensaje que queremos eliminar
	 *@return                          Booleano que indica si la operaci�n tuvo �xito o no
	 *@exception  ConexionBDException  Error al obtener la conexi�n a la base de datos
	 *@exception  SQLException         Error interno de MySQL
	 */
	public boolean borrarMensaje(int id) throws ConexionBDException, SQLException {
		int rowsAffected = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Obtenemos la conexi�n a la base de datos
			connection = ConexionBD.getConexion();

			// Creamos la sentencia SQL y la ejecutamos, devolviendo
			// el n�mero de filas de la tabla de usuarios afectadas
			preparedStatement = connection.prepareStatement(
					"DELETE FROM mensajes WHERE idMensaje = ?");
			preparedStatement.setInt(1, id);

			rowsAffected = preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = null;
			connection.close();
			connection = null;

			return (rowsAffected == 1);
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
	 *  Modifica un determinado mensaje. En el par�metro se indican tanto el 
	 *  identificador del mensaje a modificar como la nueva informaci�n del
	 *  mensaje.
	 *
	 *  <b>NOTA: </b>Los mensajes no se pueden pasar de un tema a otro, por lo que
	 *  el atributo idTema se ignorar�
	 *
	 *@param  mensaje                  Mensaje a modificar y nueva informaci�n
	 *@return                          Valor booleano indicando si tuvo �xito o no
	 *@exception  ConexionBDException  Error al obtener la conexi�n a la base de datos
	 *@exception  SQLException         Error interno de MySQL
	 */
	public boolean modificarMensaje(Mensaje mensaje) throws ConexionBDException, SQLException {
		int rowsAffected = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Obtenemos la conexi�n a la base de datos
			connection = ConexionBD.getConexion();

			// Creamos la sentencia SQL y la ejecutamos, devolviendo
			// el n�mero de filas de la tabla de usuarios afectadas
			preparedStatement = connection.prepareStatement(
					"UPDATE mensajes SET nick=?, texto=?, fecha=? WHERE idMensaje = ?");
			preparedStatement.setString(1, mensaje.getNick());
			preparedStatement.setString(2, mensaje.getTexto());
			preparedStatement.setDate(3, mensaje.getFecha());
			preparedStatement.setInt(4, mensaje.getIdMensaje());

			rowsAffected = preparedStatement.executeUpdate();
			preparedStatement.close();
			preparedStatement = null;
			connection.close();
			connection = null;

			return (rowsAffected == 1);
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
	 *  Obtiene la �nica instancia del gestor de mensajes
	 *
	 *@return    Gestor de Mensajes
	 */
	public static MensajesBD getGestorMensajes() {
		// Si a�n no existe ning�n gestor, lo creamos
		if (elGestor == null) {
			elGestor = new MensajesBD();
		}
		return elGestor;
	}
}
