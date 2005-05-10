/*
 *  ConexiónBD.java
 *
 */
package genesis.bd;


import javax.sql.*;
import javax.naming.*;

import java.sql.*;

/**
 * Esta clase contiene un único método estático, <tt>getConexion()</tt>,
 * que crea una conexión a la base de datos de Génesis y la devuelve.
 *
 *
 *@author    Manuel Montenegro
 */
public class ConexionBD {
	
        /**
         * Obtiene una conexión a la base de datos de Génesis
         *
         *@return                          La conexión a la base de datos
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
