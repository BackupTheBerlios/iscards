package genesis.configuracion;

import genesis.configuracion.*;
import java.util.Vector;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author David B. Jenkins López
 * @version 1.0
 */

public class PruebaArbol {

  private Opcion raiz;

  public PruebaArbol() { // genero un arbolito como debería ser
    Vector opciones = new Vector();
    Vector opciones2 = new Vector();

    opciones2.add(new OpcionSimple("opcionSimple1", "soy la opcion simple izquierda", new Integer(1), "Integer"));
    opciones2.add(new OpcionSimple("opcionSimple2", "soy la opcion simple derecha", new Integer(2), "String"));

    opciones.add(new OpcionCompuesta(opciones2,"opcionCompuesta1","soy la opcion compuesta de la raiz"));
    opciones.add(new OpcionSimple("opcionSimple3","soy la opcion simple de la raiz", new Integer(3), "Integer"));


    raiz = new OpcionCompuesta(opciones, "raiz","soy la raiz");
  }

  public Opcion getRaiz(){
    return raiz;
  }

}