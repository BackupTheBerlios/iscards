/*
 *  ConexionBDException.java
 *
 */
package genesis.bd;

/**
 *  Representa una excepción que se produce cuando no se
 *  ha podido conectar a la base de datos de Génesis.
 *
 *@author    Chris Seguin
 */
public class ConexionBDException extends Exception {
        /**
         * Constructor de la excepción
         *
         *@param  mensaje Mensaje con información sobre la excepción
         */
        public ConexionBDException(String mensaje) {
                super(mensaje);
        }
}
