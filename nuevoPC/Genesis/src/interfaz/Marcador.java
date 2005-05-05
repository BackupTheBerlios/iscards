package interfaz;
import cartas.*;
import motorJuego.*;
import java.util.Vector;
import java.util.LinkedList;
//***************CLASE QUE CALCULA LAS ESTADISTICAS*********************//
/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
public class Marcador {
	Partida partida;


	/**
	 *  Constructor for the Marcador object
	 *
	 *@param  par  Description of Parameter
	 */
	public Marcador(Partida par) {
		partida = par;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  nuevaPartida  Description of Parameter
	 */
	public void actualizaMarcador(Partida nuevaPartida) {
		partida = nuevaPartida;
	}


	/**
	 *  Metodo que devuelve los puntos de ataque que tiene el jugador en la mesa
	 *
	 *@return    Description of the Returned Value
	 */
	public int puntosAtaqueJugadorMesa() {
		CZona jugador1 = (CZona) partida.getMesa().getJugador1();
		Vector criaturasJugador1 = jugador1.getVectorCriaturas();
		int longitud = criaturasJugador1.size();
		int ataque = 0;
		CCriatura criatura;
		for (int i = 0; i < longitud; i++) {
			criatura = (CCriatura) criaturasJugador1.get(i);
			ataque += criatura.getAtaque();
		}
		return ataque;
	}


	/**
	 *  Metodo que devuelve los puntos de ataque que tiene el jugador en la mano
	 *
	 *@return    Description of the Returned Value
	 */
	public int puntosAtaqueJugadorMano() {
		Partida jugador1 = partida;
		Vector manoJugador1 = jugador1.getMano().getCartas();
		int longitud = manoJugador1.size();
		int ataque = 0;
		CCriatura criatura;
		CACarta carta;
		for (int i = 0; i < longitud; i++) {
			carta = (CACarta) manoJugador1.get(i);
			if (carta instanceof CCriatura) {
				criatura = (CCriatura) carta;
				ataque += criatura.getAtaque();
			}
		}
		return ataque;
	}


	/**
	 *  Metodo que devuelve los puntos de ataque que hay en el cementerio
	 *
	 *@return    Description of the Returned Value
	 */
	public int puntosAtaqueJugadorCementerio() {
		LinkedList cartasCem = partida.getCementerio().getCartas();
		int longitud = cartasCem.size();
		int ataque = 0;
		CCriatura criatura;
		CACarta carta;
		for (int i = 0; i < longitud; i++) {
			carta = (CACarta) cartasCem.get(i);
			if (carta instanceof CCriatura) {
				criatura = (CCriatura) carta;
				ataque += criatura.getAtaque();
			}
		}
		return ataque;
	}


	/**
	 *  Metodo que devuelve los puntos de ataque que tiene el jugador en el mazo
	 *
	 *@return    Description of the Returned Value
	 */
	public int puntosAtaqueJugadorMazo() {
		LinkedList cartasMazo = partida.getMazo().getCartas();
		int longitud = cartasMazo.size();
		int ataque = 0;
		CACarta carta;
		//recorre todo el mazo y cuando encuentra un criatura suma los puntos de ataque que tiene
		for (int i = 0; i < longitud; i++) {
			carta = (CACarta) cartasMazo.get(i);
			if (carta instanceof CCriatura) {
				CCriatura criatura = (CCriatura) carta;
				ataque += criatura.getAtaque();
			}
		}
		return ataque;
	}


	/**
	 *  Metodo que devuelve los puntos de defensa que tiene el jugador en el mazo
	 *
	 *@return    Description of the Returned Value
	 */
	public int puntosDefensaJugadorMazo() {
		LinkedList cartasMazo = partida.getMazo().getCartas();
		int longitud = cartasMazo.size();
		int defensa = 0;
		CACarta carta;
		//recorre todo el mazo y cuando encuentra un criatura suma los puntos de defensa que tiene
		for (int i = 0; i < longitud; i++) {
			carta = (CACarta) cartasMazo.get(i);
			if (carta instanceof CCriatura) {
				CCriatura criatura = (CCriatura) carta;
				defensa += criatura.getDefensa();
			}
		}
		return defensa;
	}


	/**
	 *  Metodo que devuelve los puntos de defensa que tiene el jugador en la mano
	 *
	 *@return    Description of the Returned Value
	 */
	public int puntosDefensaJugadorMano() {
		Partida jugador1 = partida;
		Vector manoJugador1 = jugador1.getMano().getCartas();
		int longitud = manoJugador1.size();
		int defensa = 0;
		CCriatura criatura;
		CACarta carta;
		for (int i = 0; i < longitud; i++) {
			carta = (CACarta) manoJugador1.get(i);
			if (carta instanceof CCriatura) {
				criatura = (CCriatura) carta;
				defensa += criatura.getDefensa();
			}
		}
		return defensa;
	}


	/**
	 *  Metodo que devuelve los puntos de defensa que hay en el cementerio
	 *
	 *@return    Description of the Returned Value
	 */
	public int puntosDefensaJugadorCementerio() {
		LinkedList cartasCem = partida.getCementerio().getCartas();
		int longitud = cartasCem.size();
		int defensa = 0;
		CCriatura criatura;
		CACarta carta;
		for (int i = 0; i < longitud; i++) {
			carta = (CACarta) cartasCem.get(i);
			if (carta instanceof CCriatura) {
				criatura = (CCriatura) carta;
				defensa += criatura.getDefensa();
			}
		}
		return defensa;
	}


	/**
	 *  * Metodo que devuelve los puntos de defensa que tiene el jugador en la
	 *  mesa
	 *
	 *@return    Description of the Returned Value
	 */
	public int puntosDefensaJugadorMesa() {
		CZona jugador1 = (CZona) partida.getMesa().getJugador1();
		Vector criaturasJugador1 = jugador1.getVectorCriaturas();
		int longitud = criaturasJugador1.size();
		int defensa = 0;
		CCriatura criatura;
		for (int i = 0; i < longitud; i++) {
			criatura = (CCriatura) criaturasJugador1.get(i);
			defensa += criatura.getDefensa();
		}
		return defensa;
	}


	/**
	 *  Metodo que devuelve los puntos de ataque que tiene el contrincante en la
	 *  mesa
	 *
	 *@return    Description of the Returned Value
	 */

	public int puntosAtaqueContrincanteMesa() {
		CZona jugador2 = (CZona) partida.getMesa().getJugador2();
		Vector criaturasJugador2 = jugador2.getVectorCriaturas();
		int longitud = criaturasJugador2.size();
		int ataque = 0;
		CCriatura criatura;
		for (int i = 0; i < longitud; i++) {
			criatura = (CCriatura) criaturasJugador2.get(i);
			ataque += criatura.getAtaque();
		}
		return ataque;
	}


	/**
	 *  Metodo que devuelve los puntos de defensa que tiene el contrincante en la
	 *  mesa
	 *
	 *@return    Description of the Returned Value
	 */
	public int puntosDefensaContrincanteMesa() {
		CZona jugador2 = (CZona) partida.getMesa().getJugador2();
		Vector criaturasJugador2 = jugador2.getVectorCriaturas();
		int longitud = criaturasJugador2.size();
		int defensa = 0;
		CCriatura criatura;
		for (int i = 0; i < longitud; i++) {
			criatura = (CCriatura) criaturasJugador2.get(i);
			defensa += criatura.getDefensa();
		}
		return defensa;
	}


	/**
	 *  Metodo que devuelve el precio que deberia pagar el jugador para bajar
	 *  toda su mano
	 *
	 *@return    Description of the Returned Value
	 */
	public int precioManoJugador() {
		Vector cartasMano = partida.getMano().getCartas();
		int longitud = cartasMano.size();
		int precio = 0;
		CACarta carta;
		//recorre la mano sumando el precio de cada carta
		for (int i = 0; i < longitud; i++) {
			carta = (CACarta) cartasMano.get(i);
			precio += carta.getCoste();
		}
		return precio;
	}


	/**
	 *  Metodo que devuelve el numero de cartas de cada tipo que hay en el mazo
	 *
	 *@return    Description of the Returned Value
	 */
	public terna cuentaTipoCartasMazo() {
		LinkedList cartasMazo = partida.getMazo().getCartas();
		int longitud = cartasMazo.size();
		int criaturas = 0;
		int hechizos = 0;
		int conjuros = 0;
		CACarta carta;
		for (int i = 0; i < longitud; i++) {
			carta = (CACarta) cartasMazo.get(i);
			if (carta instanceof CCriatura) {
				criaturas++;
			}
			else if (carta instanceof CHechizo) {
				hechizos++;
			}
			else if (carta instanceof CConjuro) {
				conjuros++;
			}
		}
		return new terna(criaturas, hechizos, conjuros);
	}


	/**
	 *  Metodo que devuelve el numero de cartas de cada tipo que hay en el
	 *  cementerio
	 *
	 *@return    Description of the Returned Value
	 */
	public terna cuentaTipoCartasCementerio() {
		LinkedList cartasCem = partida.getCementerio().getCartas();
		int longitud = cartasCem.size();
		int criaturas = 0;
		int hechizos = 0;
		int conjuros = 0;
		CACarta carta;
		for (int i = 0; i < longitud; i++) {
			carta = (CACarta) cartasCem.get(i);
			if (carta instanceof CCriatura) {
				criaturas++;
			}
			else if (carta instanceof CHechizo) {
				hechizos++;
			}
			else if (carta instanceof CConjuro) {
				conjuros++;
			}
		}
		return new terna(criaturas, hechizos, conjuros);
	}

}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class terna {


	int cri, he, con;


	/**
	 *  Constructor for the terna object
	 *
	 *@param  x  Description of Parameter
	 *@param  y  Description of Parameter
	 *@param  z  Description of Parameter
	 */
	public terna(int x, int y, int z) {
		cri = x;
		he = y;
		con = z;
	}
}
