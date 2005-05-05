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

public class EventoDefensa extends Eventos1par {
	//atributos añadidos, para manejar 2 pares
	private String Codigo2;
	private String Posicion2;
	private int turnoDeDefensa;


	//constructor
	/**
	 *  Constructor for the EventoDefensa object
	 *
	 *@param  cod    Description of Parameter
	 *@param  pos    Description of Parameter
	 *@param  cod2   Description of Parameter
	 *@param  pos2   Description of Parameter
	 *@param  turno  Description of Parameter
	 */
	public EventoDefensa(String cod, String pos, String cod2, String pos2, int turno) {
		TipoEvento = "defensa";
		Codigo = cod;
		Posicion = pos;
		Codigo2 = cod2;
		Posicion2 = pos2;
		turnoDeDefensa = turno;
	}

	//metodos accesores

	/**
	 *  Gets the Codigo2 attribute of the EventoDefensa object
	 *
	 *@return    The Codigo2 value
	 */
	public String getCodigo2() {
		return Codigo2;
	}


	/**
	 *  Gets the Posicion2 attribute of the EventoDefensa object
	 *
	 *@return    The Posicion2 value
	 */
	public String getPosicion2() {
		return Posicion2;
	}


	/**
	 *  Gets the TurnoDeDefensa attribute of the EventoDefensa object
	 *
	 *@return    The TurnoDeDefensa value
	 */
	public int getTurnoDeDefensa() {
		return turnoDeDefensa;
	}
	
	public String toString(){
		return (TipoEvento+"ç"+Codigo+"ç"+Posicion+"ç"+Codigo2+"ç"+Posicion2+"ç"+turnoDeDefensa);
	}
}
