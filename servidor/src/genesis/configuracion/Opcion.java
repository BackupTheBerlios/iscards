package genesis.configuracion;

/**
 *
 * Interfaz com�n para todas las opciones de configuraci�n de la Web.
 * Tendremos dos tipos de opciones:
 *
 *<ul>
 *	<li><b>Opciones simples</b>: Aquellas que contienen un nombre, un tipo
 *     y un valor. No contienen en s� mismas otras opciones</li>
 *  <li><b>Opciones compuestas</b>: Aquellas que contienen un conjunto de
 *    opciones, que a su vez podr�n ser tambi�n compuestas, creando as� una
 *    estructura de �rbol
 *</ul>
 *
 * Cada opci�n tendr� un nombre que la identifique un�vocamente entre todas
 * sus opciones "hermanas" (aquellas que son hijas de la misma opci�n compuesta
 * que �sta).
 *
 *@author     David B. Jenkins L�pez
 */

public interface Opcion {
	/**
	 *  Obtiene el nombre identificador de la opci�n
	 *
	 *@return    Una cadena que contiene el nombre identificador
	 */
	String getNombre();


	/**
	 *  Obtiene una descripci�n de la opci�n y sus posibles valores.
	 *
	 *@return    Un String conteniendo la descripci�n
	 */
	String getDescripcion();


	/**
	 *  Devuelve <tt>true</tt> si la opci�n es compuesta (esto es, es un
	 * conjunto de opciones). Devuelve <tt>false</tt> en caso contrario.
	 * 
	 *
	 *@return    un valor indicando si la opci�n que recibe el mensaje es
	 *          compuesta o no
	 */
	boolean getCompuesta();
}
