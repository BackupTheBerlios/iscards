package cartas;

import java.util.LinkedList;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: Clase para representar el mazo de cartas del que robará un usuario para jugar</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Tony
 * @version 1.0
 */

public class CMazo {

  /**
   * Variable que guardará las cartas en una lista
   */
  private LinkedList listaCartas;

  /**
   * Variable con el tamaño del mazo
   */
  private int tamano;

  /**
   * Constructora de la clase CMazo con el mazo de las cartas
   */
  public CMazo() {
    listaCartas = new LinkedList();
    tamano = 0;
  }

  /**
   * Función que devuelve la lista de las cartas del mazo
   * @return lista de las cartas
   */
  public LinkedList getCartas(){
    return listaCartas;
  }

  /**
   * Función que añade una carta al mazo
   * @param c carta añadida
   */
  public void anadeCarta(CACarta c){
    this.getCartas().add(c);
    tamano++;
  }

  /**
   * Función que roba una carta de la lista
   * @return
   */
  public Object robaCarta(){
    tamano--;
    if (listaCartas.size()!=0)
    
     return listaCartas.removeLast();
    //mazo terminado
    else return null;
  }
  
  public int getTamano(){
  	return tamano;
  }
}
