package genesis.foro;

/**
 *  <p>
 *
 *  Title: MensajeTema
 *
 *  Description: Implementa la clase que relaciona un mensaje con el tema al que pertenece
 *
 *  Copyright: Copyright (c) Genesis
 *
 *  Company: Genesis
 *
 *  @author: Laura Díaz, Conchi Fernandez, Laura García, Inés González, Sergio Somovilla
 *
 *  @version    3.01
 */

public class MensajeTema {
        private int idMensaje;
        private int idTema;


        /**
         *  Constructor for the MensajeTema object
         *
         *@param  idM  Identificador del mensaje
         *@param  idT  Identificador del tema
         */
        public MensajeTema(int idM, int idT) {
                this.idMensaje = idM;
                this.idTema = idT;
        }


        /**
         *  Gets the IdMensaje attribute of the MensajeTema object
         *
         *@return    The IdMensaje value
         */
        public int getIdMensaje() {
                return idMensaje;
        }


        /**
         *  Gets the IdTema attribute of the MensajeTema object
         *
         *@return    The IdTema value
         */
        public int getIdTema() {
                return idTema;
        }

}
