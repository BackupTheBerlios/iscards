package eventos;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Bordas
 * @version 1.0
 */

public class EventoBajada extends Eventos1par{

  //constructor,los atributos estan heredados
  public EventoBajada(String cod,String pos) {
    TipoEvento = "bajada";
    Codigo = cod;
    Posicion = pos;
  }
}