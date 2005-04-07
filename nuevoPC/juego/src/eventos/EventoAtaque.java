package eventos;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class EventoAtaque extends Eventos{

  //constructor,los atributos estan heredados
  public EventoAtaque(String cod,String pos) {
    TipoEvento = "ataque";
    Codigo = cod;
    Posicion = pos;
  }
}