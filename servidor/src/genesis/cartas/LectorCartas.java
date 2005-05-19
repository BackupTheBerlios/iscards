/*
 *  LectorCartas.java
 */
package genesis.cartas;

import java.io.*;

/**
 * Obtiene un conjunto de cartas a partir
 * de un flujo de entrada que contiene la información
 * de dichas cartas codificada.
 *
 *@author   Manuel Montenegro
 */
public class LectorCartas {
        private InputStream entrada;
        private Carta cartaActual;


        /**
         *  Constructor de la clase.
         *
         *@param  entrada  Flujo de entrada
         */
        public LectorCartas(InputStream entrada) {
                this.entrada = entrada;
                this.cartaActual = null;
        }


        /**
         *  Devuelve la siguiente carta a partir del flujo que se obtuvo
         *
         *
         *@return                               Siguiente carta del InputStream,
         *					null, si ya no hay más.
         *
         *@exception  IOException               Error al leer del InputStream
         *@exception  ArchivoCorruptoException  El formato del InputStream no es válido
         */
        public Carta siguienteCarta()
                         throws ArchivoCorruptoException, IOException {
                int longitud;

                // Todos los campos tienen la misma forma: Longitud del campo
                // y el contenido del campo codificado.
                longitud = entrada.read();

                if (longitud == -1) {
                        return null;
                }

                String codigo = descodificar(leerFrase(longitud));

                longitud = entrada.read();
                String raza = descodificar(leerFrase(longitud));

                longitud = entrada.read();
                String tipo = descodificar(leerFrase(longitud));

                longitud = entrada.read();
                String nombre = descodificar(leerFrase(longitud));

                Integer nivel = new Integer(entrada.read());

                longitud = entrada.read();
                String puntosEnLetra = descodificar(leerFrase(longitud));
                Integer puntos = new Integer(puntosEnLetra);

                Integer ataque = new Integer(entrada.read());
                Integer defensa = new Integer(entrada.read());
                Integer coste = new Integer(entrada.read());
                Integer vida = new Integer(entrada.read());

                longitud = entrada.read();
                longitud = (new Integer(descodificar(leerFrase(longitud)))).intValue();
                String descripcionMovil = descodificar(leerFrase(longitud));

                longitud = entrada.read();
                longitud = (new Integer(descodificar(leerFrase(longitud)))).intValue();
                String habilidades = descodificar(leerFrase(longitud));

                return new Carta(codigo, raza, tipo, nombre, nivel, puntos, ataque, defensa,
                                coste, vida, descripcionMovil, habilidades);
        }


        /**
         *  Lee un conjunto de bytes del InputStream asociado a este
         *  lector de cartas.
         *
         *@param  longitud                      Nº de bytes a leer
         *@return                               Array con los bytes leídos
         *@exception  ArchivoCorruptoException  Error en el formato del archivo
         *@exception  IOException               Error al leer del InputStream
         */
        private byte[] leerFrase(int longitud)
                         throws ArchivoCorruptoException, IOException {
                int byteActual;
                int contador = 0;

                byte[] resultado = new byte[longitud];
                while (contador < longitud) {
                        byteActual = entrada.read();
                        if (byteActual < 0) {
                                throw new ArchivoCorruptoException("leerFrase: El archivo está incompleto");
                        }
                        resultado[contador] = (byte) byteActual;
                        contador++;
                }
                return resultado;
        }


        /**
         *  Convierte un conjunto de bytes codificado en un String
         *
         *@param  fraseBytes  Conjunto de bytes de entrada
         *@return             String descodificado
         */
        private String descodificar(byte[] fraseBytes) {
                StringBuffer resultado = new StringBuffer();
                for (int i = 0; i < fraseBytes.length; i++) {
                        char a;
                        if (fraseBytes[i] < 0) {
                                a = (char) (256 + fraseBytes[i] + 2);
                        }
                        else {
                                a = (char) (fraseBytes[i] + 2);
                        }
                        resultado.append(a);
                }
                return resultado.toString();
        }
}
