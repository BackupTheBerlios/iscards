package genesis.configuracion;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author David B. Jenkins López
 * @version 1.0
 */

public class OpcionSimple implements Opcion{
  private String nombre;
  private String descripcion;
  private Object valor;
  private String tipo;
  
  private static final String TIPO_INTEGER = "Integer";
  private static final String TIPO_STRING = "String";
  

  public OpcionSimple(String nombre, String descripcion, Object valor, String tipo) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.valor = valor;
    this.tipo = tipo;
  }

  public String getNombre(){
    return nombre;
  }

  public String getDescripcion(){
    return descripcion;
  }

  public boolean getCompuesta(){
    return false;
  }

  public Object getValor(){
    return valor;
  }
  
  public void setValor(Object nuevoValor) {
  	valor = nuevoValor;
  }

  public String getTipo(){
    return tipo;
  }

}