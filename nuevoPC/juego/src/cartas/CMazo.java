package cartas;

import java.util.LinkedList;

/**
 * <p>T�tulo: GENESIS</p>
 * <p>Descripci�n: Clase para representar el mazo de cartas del que robar� un usuario para jugar</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Tony
 * @version 1.0
 */

public class CMazo {

  /**
   * Variable que guardar� las cartas en una lista
   */
  private LinkedList listaCartas;

  /**
   * Variable con el tama�o del mazo
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
   * Funci�n que devuelve la lista de las cartas del mazo
   * @return lista de las cartas
   */
  public LinkedList getCartas(){
    return listaCartas;
  }

  /**
   * Funci�n que a�ade una carta al mazo
   * @param c carta a�adida
   */
  public void anadeCarta(CACarta c){
    this.getCartas().add(c);
    tamano++;
  }

  /**
   * Funci�n que roba una carta de la lista
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
