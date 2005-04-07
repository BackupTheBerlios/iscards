package eventos;

public abstract class Eventos{
  //atributos de todo evento
  /*el tipo de evento lo determinara su nombre, dejo esto por si fuera util
  tenerlos clasificados de otro modo en "bajada" "ataque" "defensa"*/
  protected String TipoEvento;
  protected String Codigo;
  protected String Posicion;

  //metodos accesores
  public String getTipoEvento(){
    return TipoEvento;
  }
  public String getCodigo(){
    return Codigo;
  }
  public String getPosicion(){
    return Posicion;
  }

}