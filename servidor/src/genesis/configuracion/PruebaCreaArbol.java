package genesis.configuracion;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.apache.xerces.dom.*;

import java.util.Vector;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author David B. Jenkins López
 * @version 1.0
 */

public class PruebaCreaArbol {

  public PruebaCreaArbol() {
  }
  public static void main(String[] args) throws Exception {
    DocumentBuilderFactory p = DocumentBuilderFactory.newInstance();
    DocumentBuilder b = p.newDocumentBuilder();
    Document doc = b.parse("C:\\hlocal\\Practica XML FINAL\\pruebaCreaArbol.xml");

    CreaArbol arbol = new CreaArbol(doc);

                 /* COMPROBACION */
    System.out.println("Comprobamos el árbol: ");
    OpcionCompuesta raiz = arbol.getRaiz();  // cojemos la raiz
    System.out.println("\t Mi nombre es: " + raiz.getNombre() + " y mi descripcion: " + raiz.getDescripcion());

    // opcion compuesta de la raiz
    OpcionCompuesta op1_raiz = (OpcionCompuesta) ((Vector)raiz.getOpciones()).elementAt(0);
    System.out.println("\t Mi nombre es: " + op1_raiz.getNombre() + " y mi descripcion: " + op1_raiz.getDescripcion());

    // opcion simple de la raiz
    OpcionSimple op2_raiz = (OpcionSimple) ((Vector)raiz.getOpciones()).elementAt(1);
    System.out.println("\t Mi nombre es: " + op2_raiz.getNombre() + " y mi descripcion: " + op2_raiz.getDescripcion());

    // primera opcion simple de la opcion compuesta de la raiz
    OpcionSimple op1_hijo_raiz = (OpcionSimple) ((Vector)op1_raiz.getOpciones()).elementAt(0);
    System.out.println("\t Mi nombre es: " + op1_hijo_raiz.getNombre() + " y mi descripcion: " + op1_hijo_raiz.getDescripcion());

    // segunda opcion simple de la opcion compuesta de la raiz
    OpcionSimple op2_hijo_raiz = (OpcionSimple) ((Vector)op1_raiz.getOpciones()).elementAt(1);
    System.out.println("\t Mi nombre es: " + op2_hijo_raiz.getNombre() + " y mi descripcion: " + op2_hijo_raiz.getDescripcion());
  }
}