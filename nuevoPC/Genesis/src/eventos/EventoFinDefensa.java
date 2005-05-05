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

public class EventoFinDefensa extends Eventos {

	//constructor,los atributos estan heredados
	/**
	 *  Constructor for the EventoCambioTurno object
	 */
	public EventoFinDefensa() {
		TipoEvento = "findefensa";
	}

	public String toString(){
		return (TipoEvento);
	}
	
}
