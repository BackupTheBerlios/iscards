/*
 *  CartaNoExistenteException.java
 *
 */
package genesis.cartas;

/**
 * Error que se produce cuando se intenta acceder a una carta
 * que no existe,
 *
 *@author    Manuel Montenegro
 */
public class CartaNoExistenteException extends Exception {

        private String identificador;


        /**
         *  Constructor for the CartaNoExistenteException object
         *
         *@param  mensaje        Información de la excepción
         *@param  identificador  Identificador de la carta a la que se intentó acceder
         */
        public CartaNoExistenteException(String mensaje, String identificador) {
                super(mensaje);
                this.identificador = identificador;
        }


        /**
         *  Obtiene el identificador de la carta que se intentó acceder y no existía
         *
         *@return    Identificador
         */
        public String getIdentificador() {
                return identificador;
        }
}
