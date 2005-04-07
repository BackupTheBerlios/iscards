package motorJuego;

import java.util.Vector;
/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: Juego de cartas de Rol</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Tony y Rui Miguel
 * @version 1.0
 */

public class CZona {

  /**
   * Vector con las criaturas en la zona de la mesa del jugador
   */
  Vector vectorCriaturas;

  /**
   * Vector con los hechizos en la zona de la mesa del jugador
   */
  Vector vectorHechizos;

  /**
   * Vector con los conjuros en la zona de la mesa del jugador
   */
  Vector vectorConjuros;

  /**
   * Variable para almacenar el mana usado
   */
  int manaUsado;

  /**
   * Variable para almacenar el mana disponible
   */
  int manaDisponible;

  /**
   * Constructora de la clase
   * @param criaturas vector de criaturas
   * @param hechizos vector de hechizos
   * @param conjuros vector de conjuros
   * @param manaDisp mana disponible
   */
  public CZona(Vector criaturas, Vector hechizos, Vector conjuros, int manaDisp){
    vectorCriaturas = criaturas;
    vectorHechizos = hechizos;
    vectorConjuros = conjuros;
    manaDisponible = manaDisp;
    manaUsado = 0;
  }

  /**
   * Función que devuelve el vector de las criaturas
   * @return
   */
  public Vector getVectorCriaturas(){
    return vectorCriaturas;
  }

  /**
   * Función que devuelve el vector de los hechizos
   * @return
   */
  public Vector getVectorHechizos(){
    return vectorHechizos;
  }

  /**
   * Función que devuelve el vector de los conjuros
   * @return
   */
  public Vector getVectorConjuros(){
    return vectorConjuros;
  }

  /**
   * Función que devuelve el mana disponible
   * @return
   */
  public int getManaDisponible(){
    return manaDisponible;
  }

  /**
   * Función que devuelve el mana usado
   * @return
   */
  public int getManaUsado(){
    return manaUsado;
  }

  /**
   * Función que modifica el vector de las criaturas
   * @param vC
   */
  public void setVectorCriaturas(Vector vC){
    vectorCriaturas = vC;
  }

  /**
   * Función que modifica el vector de los hechizos
   * @param vC
   */
  public void setVectorHechizos(Vector vH){
    vectorHechizos = vH;
  }

  /**
   * Función que modifica el vector de los conjuros
   * @param vCon
   */
  public void setVectorConjuros(Vector vCon){
    vectorConjuros = vCon;
  }

  /**
   * Función que modifica el mana disponible
   * @param m
   */
  public void setManaDisponible(int m){
    manaDisponible = m;
  }

  /**
   * Función que modifica el mana usado
   * @param m
   */
  public void getManaUsado(int m){
    manaUsado = m;
  }
}