package eventos;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Bordas
 * @version 1.0
 */

public class EventoDefensa extends Eventos1par{
  //atributos añadidos, para manejar 2 pares
  private String Codigo2;
  private String Posicion2;

  //constructor
  public EventoDefensa(String cod,String pos,String cod2,String pos2) {
    TipoEvento = "defensa";
    Codigo = cod;
    Posicion = pos;
    Codigo2 = cod2;
    Posicion2 = pos2;
  }
  //metodos accesores
  public String getCodigo2(){
    return Codigo2;
  }
  public String getPosicion2(){
    return Posicion2;
  }
}