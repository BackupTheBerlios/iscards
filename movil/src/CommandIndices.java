package CartasMovil;

import javax.microedition.lcdui.*;

/**
 *  Clase que extiende a Command, con las ventajas que esto supone
 *  (notificación automática al CommandListener de la clase que lo contenga),
 *  y que además añade información acerca un conjunto de enteros
 *
 *@author    Genesis
 */
public class CommandIndices extends Command {

	/**
	 *  Conjunto de enteros que contiene este Command
	 */
	private int[] claves;


	/**
	 *  Crea nu nuevo objeto de la clase CommandCartas
	 *
	 *@param  s  Cadena con la que nombramos a este Command
	 *@param  c  Conjunto de enteros que adjuntamos a este Command
	 */
	public CommandIndices(String s, int[] c) {
		super(s, Command.ITEM, 1);
		claves = c;
	}


	/**
	 *  Accesor del atributo claves
	 *
	 *@return    El conjunto de enteros contenido en este objeto
	 */
	public int[] getClaves() {
		return claves;
	}


	/**
	 *  Compara el objeto pasado como parámetro con este CommandIndices
	 *
	 *@param  o  Es el objeto con el que comparamos
	 *@return    true - si son iguales<br>
	 *      false - en caso contrario.
	 */
	public boolean equals(Object o) {
		return ((Command) o).getLabel().equals(this.getLabel());
	}

}
