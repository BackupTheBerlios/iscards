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



public class CreaArbol {

  private OpcionCompuesta raiz;

  /**
   * @param raiz del documento XML
   * @return me devuelve la lista de opciones del la raiz del arbol
   */
  private void fa(Element r, Vector opciones) {
    // obtenemos la lista de hijos de r
    NodeList lista = r.getChildNodes();

    // procesamos los hijos
    for (int i = 1; i < lista.getLength(); i = i + 2) {
      Node nodo = lista.item(i);  // obtenemos el hijo i
      Element nuevaRaiz = (Element) nodo;
      String tipoOpcion = nuevaRaiz.getTagName();
      // vemos que tipo de opcion es (simple o compuesta)
      if (tipoOpcion.equals("opcionSimple")){  // una OpcionSimple solo tiene como hijos la descripcion
      	// formamos la OpcionSimple. Es un nodo terminal => se acaba la recursion en esta rama
        String nombre = nuevaRaiz.getAttribute("nombre");
        String tipo = nuevaRaiz.getAttribute("tipo");
        String valor = nuevaRaiz.getAttribute("valor");
               /* obtenemos la descripcion de la opcion simple */
        NodeList listaHijos = nuevaRaiz.getChildNodes();    // obtengo los hijos de la nueva raiz
        // accedemos al primer hijo que es la descripción
        Element elementoDescripcion = (Element) listaHijos.item(1);
        // obtenemos la descripcion de la raiz
    	String descripcion = ((Text) elementoDescripcion.getFirstChild()).getData();
        //añado la opcion al vector
        opciones.add(new OpcionSimple(nombre, descripcion, (Object) valor, tipo));
      } else if (tipoOpcion.equals("opcionCompuesta")){
        // formamos la OpcionCompuesta. Es un nodo no terminal => se utiliza recursion para obtener la
        // lista de opciones de esta opcionCompuesta.
        String nombre = nuevaRaiz.getAttribute("nombre");
        NodeList listaHijos = nuevaRaiz.getChildNodes();    // obtengo los hijos de la nueva raiz
    	// accedemos al primer hijo que es la descripción. El resto de hijos serán las demás Opciones.
    	Element elementoDescripcion = (Element) listaHijos.item(1);
    	// obtenemos la descripcion de la raiz
    	String descripcion = ((Text) elementoDescripcion.getFirstChild()).getData();
        Vector opcionesNuevaOpcion = new Vector();  // creamos un nuevo vector para la nueva OpcionCompuesta

      	// obtenemos la lista de opciones de esta OpcionCompuesta
        fa(nuevaRaiz, opcionesNuevaOpcion);
        // añadimos la opcionCompuesta formada a la lista de opciones de esa OpcionCompuesta
        opciones.add(new OpcionCompuesta(opcionesNuevaOpcion, nombre, descripcion));
     }
   }
  }


  /**
   *  Forma el árbol dado un documento XML
   * @param documentoXML: el documento de entrada
   * @return: el arbol formado
   */
  private OpcionCompuesta formaArbol(Document documentoXML) {
    Element r;
    boolean consigueDescripcion = false;
    Element nuevaRaiz;

    r = documentoXML.getDocumentElement(); // extraigo la raiz del documento
    // formo la Opcion que es la raiz de todo el arbol. Siempre es una opcion compuesta
    String nombre = r.getAttribute("nombre");       // extraemos el atributo nombre de la raiz
    NodeList listaHijos = r.getChildNodes();        // obtengo los hijos de la raiz

    // accedemos al primer hijo de la raiz (posicion 1 del array, puesto que hay que
    // tener en cuenta los separadores), que es la descripción. El resto de hijos serán las
    // demás Opciones.
    Element elementoDescripcion = (Element) listaHijos.item(1);
    /* Si hacemos "String nombre = elementoDescripcion.getTagName();" --> nombre valdrá "descripcion" */
    // obtenemos la descripcion de la raiz
    String descripcion = ((Text) elementoDescripcion.getFirstChild()).getData();

    // Se utiliza recursion para obtener la lista de opciones de la raiz
    Vector opciones = new Vector();

    // recursivamente calculamos las opciones de la raíz
    fa(r, opciones);
    return ((new OpcionCompuesta(opciones, nombre, descripcion))); //devolvemos la Opcion raiz del arbol
  }


  public CreaArbol(Document documentoXML) {
    raiz = formaArbol(documentoXML);
  }

  public OpcionCompuesta getRaiz(){
    return raiz;
  }

}