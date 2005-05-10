/*
 *  ArchivoCorruptoException.java
 *
 *
 */
package genesis.cartas;

/**
 *  Error que se produce al leer de un archivo .car que
 *  no contiene el formato adecuado.
 *
 *@author    Manuel Montenegro
 */
public class ArchivoCorruptoException extends Exception {
        /**
         *  Constructor de la excepci�n
         *
         *@param  mensaje  Descripci�n de la excepci�n
         */
        public ArchivoCorruptoException(String mensaje) {
                super(mensaje);
        }
}
