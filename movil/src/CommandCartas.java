package CartasMovil;

import javax.microedition.lcdui.*;
import MazoMovil.*;

/**
 *  Clase que extiende a Command, con las ventajas que esto supone
 *  (notificación automática al CommandListener de la clase que lo contenga),
 *  y que además añade información acerca un conjunto de cartas
 *
 *@author    Genesis
 */
public class CommandCartas extends Command {

	/**
	 *  Conjunto de cartas que contiene este Command
	 */
	private CartaMovil[] cartas;


	/**
	 *  Crea nu nuevo objeto de la clase CommandCartas
	 *
	 *@param  s  Cadena con la que nombramos a este Command
	 *@param  c  Conjunto de cartas que adjuntamos a este Command
	 */
	public CommandCartas(String s, CartaMovil[] c) {
		super(s, Command.ITEM, 1);
		cartas = c;
	}


	/**
	 *  Accesor del atributo cartas
	 *
	 *@return    El conjunto de cartas contenido en este objeto
	 */
	public CartaMovil[] getCartas() {
		return cartas;
	}


	/**
	 *  Compara el objeto pasado como parámetro con este CommandCartas
	 *
	 *@param  o  Es el objeto con el que comparamos
	 *@return    true - si son iguales<br>
	 *      false - en caso contrario.
	 */
	public boolean equals(Object o) {
		return ((Command) o).getLabel().equals(this.getLabel());
	}

}
