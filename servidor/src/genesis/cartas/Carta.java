/*
 *  Carta.java
 *  ----------
 *
 *  Cambios:
 *    16/01/2004 - Manuel Montenegro
 *      Métodos de acceso hechos públicos
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
         *@param  codigo      código de la carta
         *@param  raza        raza de la carta
         *@param  tipo        tipo de la carta
         *@param  nombre      nombre de la carta
         *@param  nivel       nivel que posee la carta
         *@param  puntos      puntos que tiene la carta
         *@param  ataque      habilidad para atacar que posee la carta
         *@param  defensa     habilidad para defenderse que posee la carta
         *@param  coste       coste de invocación de la carta
         *@param  vida        vidas que posee la carta
         *@param  descripMov  descripción de la carta en el movil
         *@param  descripHab  descripción de las habilidades extras qeu posee la
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
         *  Función que devuelve el código de la carta
         *
         *@return    String código de la carta
         */
        public String getCodigo() {
                return this.codigo;
        }


        /**
         *  Función que devuelve la raza de la carta
         *
         *@return    String raza de la carta
         */
        public String getRaza() {
                return this.raza;
        }


        /**
         *  Función que devuelve el tipo de la carta
         *
         *@return    String tipo de la carta
         */
        public String getTipo() {
                return this.tipo;
        }


        /**
         *  Función que devuelve el nombre de la carta
         *
         *@return    String nombre de la carta
         */
        public String getNombre() {
                return this.nombre;
        }


        /**
         *  Función que devuelve el nombre de la carta
         *
         *@return    Integer nombre de la carta
         */
        public Integer getNivel() {
                return this.nivel;
        }


        /**
         *  Función que devuelve los puntos de la carta
         *
         *@return    Integer puntos de la carta
         */
        public Integer getPuntos() {
                return this.puntos;
        }


        /**
         *  Función que devuelve el ataque de la carta
         *
         *@return    Integer ataque de la carta
         */
        public Integer getAtaque() {
                return this.ataque;
        }


        /**
         *  Función que devuelve la defensa de la carta
         *
         *@return    Integer defensa de la carta
         */
        public Integer getDefensa() {
                return this.defensa;
        }


        /**
         *  Función que devuelve el coste de la carta
         *
         *@return    Integer coste de la carta
         */
        public Integer getCoste() {
                return this.coste;
        }


        /**
         *  Función que devuelve las vidas de la carta
         *
         *@return    Integer vidas de la carta
         */
        public Integer getVida() {
                return this.vida;
        }


        /**
         *  Función que devuelve la descripción que la carta mostrará en el movil
         *
         *@return    String descripción que la carta mostrará en el movil
         */
        public String getDescripMov() {
                return this.descripMov;
        }


        /**
         *  Función que devuelve descripción de las habilidades extras de la carta
         *
         *@return    String descripción de las habilidades extras de la carta
         */
        public String getDescripHab() {
                return this.descripHab;
        }
}
