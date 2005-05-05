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

public class EventoBajada extends Eventos1par {

	//constructor,los atributos estan heredados
	/**
	 *  Constructor for the EventoBajada object
	 *
	 *@param  cod  Description of Parameter
	 *@param  pos  Description of Parameter
	 */
	public EventoBajada(String cod, String pos) {
		TipoEvento = "bajada";
		Codigo = cod;
		Posicion = pos;
	}

	public String toString(){
		return (TipoEvento+"ç"+Codigo+"ç"+Posicion);
	}
	
}
