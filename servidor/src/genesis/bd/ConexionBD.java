/*
 *  Conexi�nBD.java
 *
 */
package genesis.bd;


import javax.sql.*;
import javax.naming.*;

import java.sql.*;

/**
 * Esta clase contiene un �nico m�todo est�tico, <tt>getConexion()</tt>,
 * que crea una conexi�n a la base de datos de G�nesis y la devuelve.
 *
 *
 *@author    Manuel Montenegro
 */
public class ConexionBD {
	
        /**
         * Obtiene una conexi�n a la base de datos de G�nesis
         *
         *@return                          La conexi�n a la base de datos
         *@exception  ConexionBDException  No se pudo conectar a la base de datos.
         */
        public static Connection getConexion() throws ConexionBDException {
                Connection resultado;
                try {
                        InitialContext initialContext = new InitialContext();
                        Context envContext = (Context) initialContext.lookup("java:comp/env");
                        DataSource dataSource = (DataSource) envContext.lookup("jdbc/genesis");
                        resultado = dataSource.getConnection();
                }
                catch (Exception e) {
                        throw new ConexionBDException("No se pudo abrir la base de datos: " + e.getMessage());
                }
                return resultado;
        }
}
