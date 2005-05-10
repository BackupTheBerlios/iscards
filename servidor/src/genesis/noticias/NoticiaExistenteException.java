/*
 *  CartaExistenteException.java
 *
 */
package genesis.noticias;

/**
 *  Error que se produce al intentar añadir una carta
 *  que ya existe.
 *
 *@author    Laura García
 */
public class NoticiaExistenteException extends Exception {

        private String titulo;


        /**
         *  Constructor de la clase
         *
         *@param  mensaje        Información sobre el error
         *@param  identificador  Identificador de la carta que se intentó añadir
         */
        public NoticiaExistenteException(String mensaje, String titulo) {
                super(mensaje);
                this.titulo = titulo;
        }


        /**
         *  Obtiene el identificador de la carta que se intentó añadir
         *
         *@return    Identificador
         */
        public String getTitulo() {
                return titulo;
        }
}
