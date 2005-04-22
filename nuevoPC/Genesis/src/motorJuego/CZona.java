package motorJuego;

import java.util.Vector;
/**
 *  <p>
 *
 *  T�tulo: GENESIS</p> <p>
 *
 *  Descripci�n: Juego de cartas de Rol</p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Tony y Rui Miguel
 *@version    1.0
 */

public class CZona {

	/**
	 *  Vector con las criaturas en la zona de la mesa del jugador
	 */
	Vector vectorCriaturas;

	/**
	 *  Vector con los hechizos en la zona de la mesa del jugador
	 */
	Vector vectorHechizos;

	/**
	 *  Vector con los conjuros en la zona de la mesa del jugador
	 */
	Vector vectorConjuros;

	/**
	 *  Variable para almacenar el mana usado
	 */
	int manaUsado;

	/**
	 *  Variable para almacenar el mana disponible
	 */
	int manaDisponible;


	/**
	 *  Constructora de la clase
	 */
	public CZona() {
		vectorCriaturas = new Vector();
		vectorHechizos = new Vector();
		vectorConjuros = new Vector();
		manaDisponible = 0;
		manaUsado = 0;
	}


	/**
	 *  Funci�n que modifica el vector de las criaturas
	 *
	 *@param  vC
	 */
	public void setVectorCriaturas(Vector vC) {
		vectorCriaturas = vC;
	}


	/**
	 *  Funci�n que modifica el vector de los hechizos
	 *
	 *@param  vH  The new VectorHechizos value
	 */
	public void setVectorHechizos(Vector vH) {
		vectorHechizos = vH;
	}


	/**
	 *  Funci�n que modifica el vector de los conjuros
	 *
	 *@param  vCon
	 */
	public void setVectorConjuros(Vector vCon) {
		vectorConjuros = vCon;
	}


	/**
	 *  Funci�n que modifica el mana disponible
	 *
	 *@param  m
	 */
	public void setManaDisponible(int m) {
		manaDisponible = m;
	}


	/**
	 *  Funci�n que modifica el mana usado
	 *
	 *@param  m
	 */
	public void setManaUsado(int m) {
		manaUsado = m;
	}


	/**
	 *  Funci�n que devuelve el vector de las criaturas
	 *
	 *@return
	 */
	public Vector getVectorCriaturas() {
		return vectorCriaturas;
	}


	/**
	 *  Funci�n que devuelve el vector de los hechizos
	 *
	 *@return
	 */
	public Vector getVectorHechizos() {
		return vectorHechizos;
	}


	/**
	 *  Funci�n que devuelve el vector de los conjuros
	 *
	 *@return
	 */
	public Vector getVectorConjuros() {
		return vectorConjuros;
	}


	/**
	 *  Funci�n que devuelve el mana disponible
	 *
	 *@return
	 */
	public int getManaDisponible() {
		return manaDisponible;
	}


	/**
	 *  Funci�n que devuelve el mana usado
	 *
	 *@return
	 */
	public int getManaUsado() {
		return manaUsado;
	}
}
