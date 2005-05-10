/*
 *  CartaExistenteException.java
 *
 */
package genesis.noticias;

/**
 *  Error que se produce al intentar a�adir una carta
 *  que ya existe.
 *
 *@author    Laura Garc�a
 */
public class NoticiaExistenteException extends Exception {

        private String titulo;


        /**
         *  Constructor de la clase
         *
         *@param  mensaje        Informaci�n sobre el error
         *@param  identificador  Identificador de la carta que se intent� a�adir
         */
        public NoticiaExistenteException(String mensaje, String titulo) {
                super(mensaje);
                this.titulo = titulo;
        }


        /**
         *  Obtiene el identificador de la carta que se intent� a�adir
         *
         *@return    Identificador
         */
        public String getTitulo() {
                return titulo;
        }
}
