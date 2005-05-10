/*
 *  Carta.java
 *  ----------
 *
 *  Cambios:
 *    16/01/2004 - Manuel Montenegro
 *      M�todos de acceso hechos p�blicos
 *
 */
package genesis.cartas;

/**
 *  Clase que usamos para las cartas
 *
 *@author
 *
 */
public class Carta {
        /**
         *  Variables que posee una carta
         */
        private String codigo, raza, tipo, nombre, descripMov, descripHab;
        private Integer nivel, puntos, ataque, defensa, coste, vida;


        /**
         *  Constructora de la clase Carta
         *
         *@param  codigo      c�digo de la carta
         *@param  raza        raza de la carta
         *@param  tipo        tipo de la carta
         *@param  nombre      nombre de la carta
         *@param  nivel       nivel que posee la carta
         *@param  puntos      puntos que tiene la carta
         *@param  ataque      habilidad para atacar que posee la carta
         *@param  defensa     habilidad para defenderse que posee la carta
         *@param  coste       coste de invocaci�n de la carta
         *@param  vida        vidas que posee la carta
         *@param  descripMov  descripci�n de la carta en el movil
         *@param  descripHab  descripci�n de las habilidades extras qeu posee la
         *      carta
         */
        public Carta(String codigo, String raza, String tipo, String nombre, Integer nivel,
                        Integer puntos, Integer ataque, Integer defensa, Integer coste,
                        Integer vida, String descripMov, String descripHab) {
                this.codigo = codigo;
                this.raza = raza;
                this.tipo = tipo;
                this.nombre = nombre;
                this.nivel = nivel;
                this.puntos = puntos;
                this.ataque = ataque;
                this.defensa = defensa;
                this.coste = coste;
                this.vida = vida;
                this.descripMov = descripMov;
                this.descripHab = descripHab;
        }


        /**
         *  Funci�n que devuelve el c�digo de la carta
         *
         *@return    String c�digo de la carta
         */
        public String getCodigo() {
                return this.codigo;
        }


        /**
         *  Funci�n que devuelve la raza de la carta
         *
         *@return    String raza de la carta
         */
        public String getRaza() {
                return this.raza;
        }


        /**
         *  Funci�n que devuelve el tipo de la carta
         *
         *@return    String tipo de la carta
         */
        public String getTipo() {
                return this.tipo;
        }


        /**
         *  Funci�n que devuelve el nombre de la carta
         *
         *@return    String nombre de la carta
         */
        public String getNombre() {
                return this.nombre;
        }


        /**
         *  Funci�n que devuelve el nombre de la carta
         *
         *@return    Integer nombre de la carta
         */
        public Integer getNivel() {
                return this.nivel;
        }


        /**
         *  Funci�n que devuelve los puntos de la carta
         *
         *@return    Integer puntos de la carta
         */
        public Integer getPuntos() {
                return this.puntos;
        }


        /**
         *  Funci�n que devuelve el ataque de la carta
         *
         *@return    Integer ataque de la carta
         */
        public Integer getAtaque() {
                return this.ataque;
        }


        /**
         *  Funci�n que devuelve la defensa de la carta
         *
         *@return    Integer defensa de la carta
         */
        public Integer getDefensa() {
                return this.defensa;
        }


        /**
         *  Funci�n que devuelve el coste de la carta
         *
         *@return    Integer coste de la carta
         */
        public Integer getCoste() {
                return this.coste;
        }


        /**
         *  Funci�n que devuelve las vidas de la carta
         *
         *@return    Integer vidas de la carta
         */
        public Integer getVida() {
                return this.vida;
        }


        /**
         *  Funci�n que devuelve la descripci�n que la carta mostrar� en el movil
         *
         *@return    String descripci�n que la carta mostrar� en el movil
         */
        public String getDescripMov() {
                return this.descripMov;
        }


        /**
         *  Funci�n que devuelve descripci�n de las habilidades extras de la carta
         *
         *@return    String descripci�n de las habilidades extras de la carta
         */
        public String getDescripHab() {
                return this.descripHab;
        }
}
