package ia_cartas;
import jess.*;
import java.util.*;
import cartas.*;
import motorJuego.*;
import eventos.*;

/**
 *  <p>
 *
 *  Title: </p> <p>
 *
 *  Description: </p> <p>
 *
 *  Copyright: Copyright (c) 2005</p> <p>
 *
 *  Company: </p>
 *
 *@author     unascribed
 *@version    1.0
 */

public class Inteligencia {
	/*
	 *  atributos de principal
	 */
	private Rete rete;


	/*
	 *  constructor de la inteligencia
	 */
	/**
	 *  Constructor for the Inteligencia object
	 */
	public Inteligencia() {
		rete = new Rete();
	}


	/**
	 *  Gets the Rete attribute of the Inteligencia object
	 *
	 *@return    The Rete value
	 */
	public Rete getRete() {
		return rete;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  p                  Description of Parameter
	 *@param  turno              Description of Parameter
	 *@param  nivelInteligencia  Description of Parameter
	 *@param  atacantesTratados  Description of Parameter
	 *@param  turnoDefensa       Description of Parameter
	 *@return                    Description of the Returned Value
	 *@exception  Exception      Description of Exception
	 */
	public Vector Soluciona(CPartida p, int turno,Vector atacantesTratados, int turnoDefensa) throws Exception {
		if (turno == 3) {
			Bajar b = new Bajar(p, rete);
			//devolvera un vector con eventos de bajada
			Vector bajar = b.eventos();
			Vector bajar2 = ordenaSolucionPorIndice(bajar);
			/*
			 *  anadimos al final un evento para el cambio del turno despues del
			 *  vector con los eventos de las soluciones
			 */
			bajar2.addElement(new EventoCambioTurno());
			return bajar2;
		}
		else if (turno == 4) {
			Atacar a = new Atacar(p, rete);
			//devolvera un vector con eventos de ataque
			Vector atacar = a.eventos();
			/*
			 *  anadimos al final un evento para el cambio del turno despues del
			 *  vector con los eventos de las soluciones
			 */
			atacar.addElement(new EventoCambioTurno());
			return atacar;
		}
		else if (turno == 5) {
			Defender d = new Defender(p);
			//devolvera un vector con eventos de defensa
			Vector defender = d.eventos(atacantesTratados, turnoDefensa);
			defender.addElement(new EventoCambioTurno());
			/*
			 *  anadimos al final un evento para el cambio del turno despues del
			 *  vector con los eventos de las soluciones
			 */
			return defender;
		}
		else {
			return null;
		}
	}


	/*
	 *  solo tenemos que ordenar en la bajada para que no swe pierdan los indices
	 *  en los vectores
	 */
	/**
	 *  Description of the Method
	 *
	 *@param  ve  Description of Parameter
	 *@return     Description of the Returned Value
	 */
	private Vector ordenaSolucionPorIndice(Vector ve) {
		Vector v = new Vector();
		/*
		 *  los indices siempre estan colocados al reves asi que solo hay que
		 *  darles la vuelta
		 */
		for (int i = ve.size() - 1; i >= 0; i--) {
			v.addElement(ve.elementAt(i));
		}
		return v;
	}
}
