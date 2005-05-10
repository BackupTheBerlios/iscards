/*
 *  ConexionBDException.java
 *
 */
package genesis.bd;

/**
 *  Representa una excepci�n que se produce cuando no se
 *  ha podido conectar a la base de datos de G�nesis.
 *
 *@author    Chris Seguin
 */
public class ConexionBDException extends Exception {
        /**
         * Constructor de la excepci�n
         *
         *@param  mensaje Mensaje con informaci�n sobre la excepci�n
         */
        public ConexionBDException(String mensaje) {
                super(mensaje);
        }
}
