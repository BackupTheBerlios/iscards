package genesis.foro;

import java.sql.Date;

/**
 *  <p>
 *
 *  Title: Mensaje
 *
 *  Description: Implementa la clase mensaje que define los diferentes mensajes que van a
 * aparecer en el foro
 *
 *  Copyright: Copyright (c) Genesis
 *
 *  Company: Genesis/Servidor
 *
 *@author    Laura Díaz, Conchi Fernandez, Laura García, Inés González, Sergio Somovilla
 *@version    3.01
 */

public class Mensaje {

        private int idMensaje;
        private String nick;
        private String texto;
        private Date fecha;


        /**
         *  Constructor
         */
        public Mensaje() {
        }


        /**
         *  Constructor for the Mensaje object
         *
         *@param  id      Identificacion de cada mensaje
         *@param  nick    Identificacion del autor del mensaje
         *@param  titulo  Titulo del mensaje
         *@param  texto   Texto del mensaje
         *@param  fecha   Fecha de creacion del mensaje
         */
        public Mensaje(int id, String nick, String texto) {
                this.idMensaje = id;
                this.nick = nick;
               /* System.out.println(texto);
                FiltroMensajes filtro_mens = new FiltroMensajes();
                filtro_mens.anadirTagValido("b");
                filtro_mens.anadirTagValido("i");
		filtro_mens.anadirAtributoValido("img", "src");
                String tex_filtrado = filtro_mens.filtrar(texto);
                this.texto = tex_filtrado;*/
                this.texto=texto;
//                System.out.println("Bu");
//                System.out.println(tex_filtrado);
                this.fecha = new Date(2005,03,14);
        }

        public Mensaje(int id, String nick, String texto,Date fecha) {
                       this.idMensaje = id;
                       this.nick = nick;
                       this.texto=texto;
                       this.fecha = fecha;
               }



        /**
         *  Gets the IdMensaje attribute of the Mensaje object
         *
         *@return    The IdMensaje value
         */
        public int getIdMensaje() {
                return idMensaje;
        }


        /**
         *  Gets the Nick attribute of the Mensaje object
         *
         *@return    The Nick value
         */
        public String getNick() {
                return nick;
        }


        /**
         *  Gets the Texto attribute of the Mensaje object
         *
         *@return    The Texto value
         */
        public String getTexto() {
                return texto;
        }


        /**
         *  Gets the Fecha attribute of the Mensaje object
         *
         *@return    The Fecha value
         */
        public Date getFecha() {
                return fecha;
        }

        public String getFechaString() {
                return fecha.toString();
        }
}
