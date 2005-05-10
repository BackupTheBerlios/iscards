package genesis.configuracion;

import java.util.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author David B. Jenkins López
 * @version 1.0
 */

public class OpcionCompuesta implements Opcion{
  private Collection opciones;
  private String nombre;
  private String descripcion;


  public OpcionCompuesta(Collection opciones, String nombre, String descripcion) {
    this.opciones = opciones;
    this.nombre = nombre;
    this.descripcion = descripcion;
  }

  public String getNombre(){
    return nombre;
  }

  public String getDescripcion(){
    return descripcion;
  }

  public Collection getOpciones() {
    return opciones;
  }

  public boolean getCompuesta(){
    return true;
  }
}