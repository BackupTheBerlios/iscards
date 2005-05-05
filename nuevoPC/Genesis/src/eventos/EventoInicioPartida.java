package eventos;
public class EventoInicioPartida extends Eventos{	

	private boolean empezar;

	public EventoInicioPartida(boolean em) {
		TipoEvento = "iniciopartida";
		empezar = em;
	}
	
	public boolean getTurnoComienzo(){
		return empezar;
	}

	public String toString(){
		return (TipoEvento+"ç"+empezar);
	}
}