package genesis.foro;

import genesis.bd.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;
import javax.naming.*;

/**
 *  
 *  Proporciona operaciones sobre la tabla de temas de la base de datos.
 *    
 *  @author: Laura Díaz, Conchi Fernandez, Laura García, Inés González, Sergio Somovilla 
 *
 *  Revisado por: David B. Jenkins López, Manuel Montenegro.
 *
 */

public class TemasBD {
	private static TemasBD elGestor;


	/**
	 * OBSOLETO: El idTema es un entero. Sustituido por getTema(int)
	 * 
	 *  Gets the Tema attribute of the TemasBD object
	 *
	 *@param  idt                      Description of Parameter
	 *@return                          The Tema value
	 *@exception  ConexionBDException  Description of Exception
	 *@exception  SQLException         Description of Exception
	 */
	public Tema getTema(String idt) throws ConexionBDException, SQLException {
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
						resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getDate(4), resultSet.getInt(5));

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
	 *  Obtiene un tema a partir de su nº identificador. Si el tema
	 *  no existe se devuelve null. 
	 *
	 *@param      idt                  Identificador del tema a buscar   
	 *@return                          Tema del foro correspondiente a ese identificador
	 *@exception  ConexionBDException  Error al obtener la conexión a la base de datos
	 *@exception  SQLException         Error interno de MySQL
	 */
	public Tema getTema(int id) throws ConexionBDException, SQLException {
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
						resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getDate(4), resultSet.getInt(5));

				resultSet.close();
				resultSet = null;
				preparedStatement.close();
				preparedStatement = null;
				connection.close();
				connection = null;
				return tema;
			}
			else {
				resultSet.close();
				resultSet = null;
				preparedStatement.close();
				preparedStatement = null;
				connection.close();
				connection = null;
				return null;
			}
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
	 * OBSOLETO: Cambiado por getTemas(int indice, int cantidad)
	 * 
	 *  Gets the Temas attribute of the TemasBD object
	 *
	 *@param  indic                    Description of Parameter
	 *@return                          The Temas value
	 *@exception  ConexionBDException  Description of Exception
	 *@exception  SQLException         Description of Exception
	 */
	public ArrayList getTemas(String indic) throws ConexionBDException, SQLException {
		int indice = (int) new Integer(indic).intValue();
		ArrayList temas = new ArrayList();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Obtenemos la conexión a la base de datos
			connection = ConexionBD.getConexion();

			// Creamos la sentencia SQL: Obtener todos los mensajes
			preparedStatement = connection.prepareStatement(
					"SELECT idTema, titulo, estado, fecha, cont_visitas FROM temas LIMIT " + indice + ",8");
//      preparedStatement.setInt(1, indice);
			// Y la ejecutamos
			resultSet = preparedStatement.executeQuery();

			// Mientras haya resultados de la consulta
			while (resultSet.next()) {
				// Creamos un mensaje con el resultado actual
				Tema tema = new Tema(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getDate(4), resultSet.getInt(5));
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
	 *  Obtiene un determinado número de temas indicado por el parámetro
	 *  cantidad de la base de datos, empezando a contar por el tema que
	 *  se indica en la variable indice
	 *
	 *@param      indic                Tema por el que empezamos (0 es el primer tema)
	 *@param      cantidad             Nº de temas a recuperar
	 *@return      Collection con los temas solicitados                     
	 *@exception  ConexionBDException  No se pudo obtener la conexión a la base de datos
	 *@exception  SQLException         Error interno del sistema gestor de bases de datos.
	 */
	public Collection getTemas(int indice, int cantidad) throws ConexionBDException, SQLException {
		Collection temas = new ArrayList();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Obtenemos la conexión a la base de datos
			connection = ConexionBD.getConexion();

			// Creamos la sentencia SQL: Obtener todos los mensajes
			preparedStatement = connection.prepareStatement(
					"SELECT idTema, titulo, estado, fecha, cont_visitas FROM temas ORDER BY fecha DESC LIMIT " + indice + "," + cantidad);
			// Y la ejecutamos
			resultSet = preparedStatement.executeQuery();

			// Mientras haya resultados de la consulta
			while (resultSet.next()) {
				// Creamos un mensaje con el resultado actual
				Tema tema = new Tema(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getDate(4), resultSet.getInt(5));
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
	 * OBSOLETO: Esto se utilizaba para obtener el número total de temas;
	 * el método getSize() lo hace de un modo más eficiente
	 *  
	 * 
	 *  Gets the Temas2 attribute of the TemasBD object
	 *
	 *@return                          The Temas2 value
	 *@exception  ConexionBDException  Description of Exception
	 *@exception  SQLException         Description of Exception
	 */
	public ArrayList getTemas2() throws ConexionBDException, SQLException {
		ArrayList temas = new ArrayList();
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
				Tema tema = new Tema(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getDate(4), resultSet.getInt(5));

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
	 * OBSOLETO: Cambiado por getSize(), para ser más consistente con el resto
	 * 
	 *  Description of the Method
	 *
	 *@return                          Description of the Returned Value
	 *@exception  ConexionBDException  Description of Exception
	 *@exception  SQLException         Description of Exception
	 */
	public int getsize() throws ConexionBDException, SQLException {
		Collection tem = this.getTemas2();
		return tem.size();
	}

	/**
	 * Obtiene el número total de temas de la base de datos
	 *
	 *@return			  Nº de entradas en la base de datos
	 *@exception  ConexionBDException  No se pudo obtener la conexión a la base de datos
	 *@exception  SQLException         Error interno del sistema gestor de bases de datos.
	 *
	 */
	public int getSize() throws ConexionBDException, SQLException {
		int result = -1;
	        Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Obtenemos la conexión a la base de datos
			connection = ConexionBD.getConexion();
			// Creamos la sentencia SQL: Obtener el número de temas
			preparedStatement = connection.prepareStatement(
					"SELECT COUNT(*) FROM temas");
			// Y la ejecutamos
			resultSet = preparedStatement.executeQuery();

			// La sentencia SIEMPRE debería devolver un resultado, luego esta
			// condición siempre se debería cumplir
			if (resultSet.next()) {
			        // Obtenemos el único resultado
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
	 *  Añade un nuevo tema a la base de datos.  
	 *
	 *@param  tema                     Tema que queremos insertar, el identificador se ignorará
	 *@return                          ID del tema añadido, o -1 si hubo un error 
	 *@exception  ConexionBDException  Error al obtener la conexión a la base de datos
	 *@exception  SQLException         Error interno de MySQL
	 */
	public int insertarTema(Tema tema) throws ConexionBDException, SQLException {
		int rowsAffected = 0;
		int result = -1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
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
			
			// La ejecutamos y devolvemos el número de filas afectadas
			rowsAffected = preparedStatement.executeUpdate();
			
			preparedStatement.close();
			preparedStatement = null;

			// Obtenemos el ID de la última fila añadida
			preparedStatement = connection.prepareStatement("SELECT LAST_INSERT_ID()");
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
			   result = resultSet.getInt(1);
			} 
			
			preparedStatement.close();
			preparedStatement = null;
			connection.close();
			connection = null;

			return result;
		}
		finally {
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
	 *  Elimina un tema de la base de datos. Por el ON DELETE CASCADE
	 *  de la definición de las tablas, el sistema gestor de bases de datos
	 *  debería encargarse de borrar los mensajes asociados.
	 *
	 *@param  id                       Identificador del tema a borrar
	 *@return                          Valor booleano que indica si la operación tuvo éxito o no
	 *@exception  ConexionBDException  Error al obtener la conexión a la base de datos
	 *@exception  SQLException         Error interno de MySQL
	 */
	public boolean borrarTema(int id) throws ConexionBDException, SQLException {
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
	 *  Modifica el tema correspondiente al identificador del objeto de la
	 *  clase Tema pasado como parámetro. Los nuevos datos del tema también se
	 *  indican en este objeto 
	 *
	 *@param  tema                     Tema que queremos modificar, y los nuevos datos
	 *@return			   Valor booleano que indica si la operación tuvo éxito o no                     
	 *@exception  ConexionBDException  Error al obtener la conexión a la base de datos
	 *@exception  SQLException         Error interno de MySQL
	 */
	public boolean modificarTema(Tema tema) throws ConexionBDException, SQLException {
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
	 *  Cambia el estado de un tema a TEMA_ABIERTO 
	 *
	 *@param  idTema                   Identificador del tema que se quiere abrir
	 *@return                          Booleano indicando si tuvo éxito o no
	 *@exception  ConexionBDException  Error al obtener la conexión a la base de datos
	 *@exception  SQLException         Error interno de MySQL
	 */
	public boolean abrirTema(int idTema) throws ConexionBDException, SQLException {
		int rowsAffected = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Obtenemos la conexión a la base de datos
			connection = ConexionBD.getConexion();

			// Igual que los anteriores métodos
			preparedStatement = connection.prepareStatement(
					"UPDATE temas SET estado=? WHERE idTema = ?");
			preparedStatement.setInt(1, Tema.TEMA_ABIERTO);
			preparedStatement.setInt(2, idTema);

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
	 *  Cambia el estado de un tema a TEMA_CERRADO 
	 *
	 *@param  idTema                   Identificador del tema que se quiere cerrar
	 *@return                          Booleano indicando si tuvo éxito o no
	 *@exception  ConexionBDException  Error al obtener la conexión a la base de datos
	 *@exception  SQLException         Error interno de MySQL
	 */
	public boolean cerrarTema(int idTema) throws ConexionBDException, SQLException {
		int rowsAffected = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Obtenemos la conexión a la base de datos
			connection = ConexionBD.getConexion();

			// Igual que los anteriores métodos
			preparedStatement = connection.prepareStatement(
					"UPDATE temas SET estado=? WHERE idTema = ?");
			preparedStatement.setInt(1, Tema.TEMA_CERRADO);
			preparedStatement.setInt(2, idTema);

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

        
	public int getNumMensajesTema(int idTema) 
				 throws ConexionBDException, SQLException {
		int result = -1;
	        Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Obtenemos la conexión a la base de datos
			connection = ConexionBD.getConexion();
			// Creamos la sentencia SQL: Obtener el número de mensajes del tema idTema
			preparedStatement = connection.prepareStatement(
					"SELECT COUNT(*) FROM mensajes WHERE idTema = ?");
			preparedStatement.setInt(1, idTema);
			
			// Y la ejecutamos
			resultSet = preparedStatement.executeQuery();

			// La sentencia SIEMPRE debería devolver un resultado, luego esta
			// condición siempre se debería cumplir
			if (resultSet.next()) {
			        // Obtenemos el único resultado
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
	 *  Devuelve la única instancia de TemasBD 
	 *
	 *@return   Instancia de TemasBD
	 */
	public static TemasBD getGestorTemas() {
		// Si aún no existe ningún gestor, lo creamos
		if (elGestor == null) {
			elGestor = new TemasBD();
		}
		return elGestor;
	}
}
