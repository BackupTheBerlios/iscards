package motorJuego;

import cartas.*;

import java.util.Vector;

/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: Juego de cartas de Rol</p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Tony y Rui Miguel
 *@version    1.0
 */

public class CMesa {

	/**
	 *  zona de la mesa para el jugador
	 */
	CZona jugador1;

	/**
	 *  zona de la mesa para el contrario
	 */
	CZona jugador2;


	/**
	 *  Constructora de la clase
	 */
	public CMesa() {

		//creamos las zonas de juego del jugador y del contrario
		jugador1 = new CZona();
		jugador2 = new CZona();
	}


	/**
	 *  Función que modifica la zona del jugador
	 *
	 *@param  zona  zona del jugador
	 */
	public void setJugador1(CZona zona) {
		jugador1 = zona;
	}


	/**
	 *  Función que modifica la zona del contrario
	 *
	 *@param  zona  zona del contrario
	 */
	public void setJugador2(CZona zona) {
		jugador2 = zona;
	}


	/**
	 *  Función que devuelve la zona del jugador
	 *
	 *@return    zona del jugador
	 */
	public CZona getJugador1() {
		return jugador1;
	}


	/**
	 *  Función que devuelve la zona del contrario
	 *
	 *@return    zona del contrario
	 */
	public CZona getJugador2() {
		return jugador2;
	}
}
