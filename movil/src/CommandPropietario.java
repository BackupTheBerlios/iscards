package CartasMovil;

import javax.microedition.lcdui.*;
import MazoMovil.*;

/**
 *  Clase que extiende a Command, con las ventajas que esto supone
 *  (notificaci�n autom�tica al CommandListener de la clase que lo contenga),
 *  y que adem�s a�ade informaci�n acerca un propietario
 *
 *@author    Genesis
 */
public class CommandPropietario extends Command {

	/**
	 *  Propietario que contiene este Command
	 */
	private String propietario;


	/**
	 *  Crea un nuevo objeto de la clase CommandCartas
	 *
	 *@param  s  Cadena con la que nombramos a este Command
	 *@param  c  el propietario que adjuntamos a este Command
	 */
	public CommandCartas(String s, String p) {
		super(s, Command.ITEM, 1);
		propietario=p;
	}


	/**
	 *  Accesor del atributo propietario;
	 *
	 *@return    El conjunto de cartas contenido en este objeto
	 */
	public String  getPropietario() {
		return propietario;
	}


	/**
	 *  Compara el objeto pasado como par�metro con este CommandCartas
	 *
	 *@param  o  Es el objeto con el que comparamos
	 *@return    true - si son iguales<br>
	 *      false - en caso contrario.
	 */
	public boolean equals(Object o) {
		return ((Command) o).getLabel().equals(this.getLabel());
	}

}
