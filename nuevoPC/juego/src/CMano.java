//package cartas;

import java.util.Vector;
import java.lang.Math;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: Clase para representar la mano de cartas con las que jugará un usuario durante la partida</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Tony
 * @version 1.0
 */

public class CMano {

  /**
   * Variable Vector con todas las cartas
   */
  private Vector vectorCartas;

  /**
   * Constructora de la clase CMano (defecto)
   */
  public CMano(){
    vectorCartas = new Vector();
  }

  /**
   * Contructora con un vector de cartas como parametro
   * @param v vector de cartas
   */
  public CMano(Vector v){
    vectorCartas = v;
  }

  /**
   * Función que devuelve el vector de las cartas de la mano
   * @return vector de las cartas
   */
  public Vector getCartas(){
    return vectorCartas;
  }

  /**
   * Función que roba hasta completar 8 cartas en la mano de un mazo
   * @param mazo mazo de juego
   */
  public void roba(CMazo mazo){
    while (this.getCartas().size() < 8){
      Object carta = mazo.robaCarta();
      if (carta != null){
        this.getCartas().add(carta);
      }
    }
  }
}
