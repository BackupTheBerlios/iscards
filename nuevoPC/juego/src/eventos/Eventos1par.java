package eventos;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author Bordas
 * @version 1.0
 */
 
public abstract class Eventos1par extends Eventos{
  //atributos nuevos para eventos que necesitan como informacion 1 par
  protected String Codigo;
  protected String Posicion;

  //metodos accesores
  public String getCodigo(){
    return Codigo;
  }
  public String getPosicion(){
    return Posicion;
  }

}