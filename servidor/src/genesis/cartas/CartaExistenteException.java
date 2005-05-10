/*
 *  CartaExistenteException.java
 *
 */
package genesis.cartas;

/**
 *  Error que se produce al intentar a�adir una carta
 *  que ya existe.
 *
 *@author    Manuel Montenegro
 */
public class CartaExistenteException extends Exception {

        private String identificador;


        /**
         *  Constructor de la clase
         *
         *@param  mensaje        Informaci�n sobre el error
         *@param  identificador  Identificador de la carta que se intent� a�adir
         */
        public CartaExistenteException(String mensaje, String identificador) {
                super(mensaje);
                this.identificador = identificador;
        }


        /**
         *  Obtiene el identificador de la carta que se intent� a�adir
         *
         *@return    Identificador
         */
        public String getIdentificador() {
                return identificador;
        }
}
