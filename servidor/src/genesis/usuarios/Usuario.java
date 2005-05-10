/*
 *  Usuario.java
 *
 *  Created on 8 de diciembre de 2004, 15:48
 */
package genesis.usuarios;

/**
 * Esta clase agrupa toda la información de un determinado usuario.
 * Contiene las siguientes propiedades:
 * <ul>
 *    <li> Nick de acceso al usuario: único para cada uno </li>
 *    <li> Contraseña de acceso al usuario</li>
 *    <li> Nombre completo del usuario </li>
 *    <li> Email </li>
 *    <li> Sexo </li>
 *    <li> Puntos </li>
 *    <li> Fecha de alta </li>
 * </ul>
 *
 * @author   Javi
 */
public class Usuario {
        private String nick;
        private String password;
        private String nombre;
        private String email;
        private String sexo;
        private String fechaAlta;
        //private String avatar;
        private float puntos;


        /**
         *  Constructor de un objeto de la clase
         *
         *@param  nick   Nick de acceso
         *@param  psw    Contraseña
         *@param  nombre Nombre
         *@param  mail   Dirección de correo
         *@param  sex    Sexo ('H' ó 'M')
         */
        public Usuario(String nick, String psw, String nombre, String mail, String sex/*, String avatar*/) {
                this.nick = nick;
                this.password = psw;
                this.nombre = nombre;
                this.email = mail;
                this.sexo = sex;
//                this.avatar = avatar;
                this.puntos = 0;
        }


        /**
         *  Constructor de un objeto de la clase
         *
         *@param  nick   Nick de acceso
         *@param  psw    Contraseña
         *@param  nombre Nombre
         *@param  mail   Dirección de correo
         *@param  sex    Sexo ('H' ó 'M')
         *@param  puntos Puntuación inicial
         */
        public Usuario(String nick, String psw, String nombre, String mail, String sex, float puntos/*, String avatar*/) {
                this.nick = nick;
                this.password = psw;
                this.nombre = nombre;
                this.email = mail;
                this.sexo = sex;
//                this.avatar = avatar;
                this.puntos = puntos;
        }


        /**
         *  Obtiene el atributo  Nick
         *
         *@return    El nick
         */
        public String getNick() {
                return this.nick;
        }


        /**
         *  Obtiene el atributo  Nombre
         *
         *@return    El nombre
         */
        public String getNombre() {
                return this.nombre;
        }


        /**
         *  Obtiene el atributo  Password
         *
         *@return    La contraseña del usuario
         */
        public String getPassword() {
                return this.password;
        }


        /**
         *  Obtiene el atributo  Email
         *
         *@return    La dirección de correo del usuario
         */
        public String getEmail() {
                return this.email;
        }


        /**
         *  Obtiene el atributo  Sexo
         *
         *@return    El sexo del usuario ('H' o 'M')
         */
        public String getSexo() {
                return this.sexo;
        }

/*        public String getAvatar() {
          return this.avatar;
        }*/

        /**
         *  Obtiene el atributo  Puntos
         *
         *@return    Los puntos del usuario
         */
        public float getPuntos() {
                return this.puntos;
        }


        /**
         *  Obtiene el atributo  FechaAlta
         *
         *@return    La fecha de alta del usuario
         */
        public String getFechaAlta() {
                return this.fechaAlta;
        }

}
