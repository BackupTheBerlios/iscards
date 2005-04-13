package eventos;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Bordas
 * @version 1.0
 */

public abstract class Eventos{
  //atributos de todo evento
  /*el tipo de evento lo determinara su nombre, dejo esto por si fuera util
  tenerlos clasificados de otro modo en "bajada" "ataque" "defensa"*/
  protected String TipoEvento;
  //metodos accesores
  public String getTipoEvento(){
    return TipoEvento;
  }
}