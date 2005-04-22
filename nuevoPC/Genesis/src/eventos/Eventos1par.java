package eventos;

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
 *@author     Bordas
 *@version    1.0
 */

public abstract class Eventos1par extends Eventos {
	//atributos nuevos para eventos que necesitan como informacion 1 par
	/**
	 *  Description of the Field
	 */
	protected String Codigo;
	/**
	 *  Description of the Field
	 */
	protected String Posicion;


	//metodos accesores
	/**
	 *  Gets the Codigo attribute of the Eventos1par object
	 *
	 *@return    The Codigo value
	 */
	public String getCodigo() {
		return Codigo;
	}


	/**
	 *  Gets the Posicion attribute of the Eventos1par object
	 *
	 *@return    The Posicion value
	 */
	public String getPosicion() {
		return Posicion;
	}

}
