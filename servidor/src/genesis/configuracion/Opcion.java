package genesis.configuracion;

/**
 *
 * Interfaz común para todas las opciones de configuración de la Web.
 * Tendremos dos tipos de opciones:
 *
 *<ul>
 *	<li><b>Opciones simples</b>: Aquellas que contienen un nombre, un tipo
 *     y un valor. No contienen en sí mismas otras opciones</li>
 *  <li><b>Opciones compuestas</b>: Aquellas que contienen un conjunto de
 *    opciones, que a su vez podrán ser también compuestas, creando así una
 *    estructura de árbol
 *</ul>
 *
 * Cada opción tendrá un nombre que la identifique unívocamente entre todas
 * sus opciones "hermanas" (aquellas que son hijas de la misma opción compuesta
 * que ésta).
 *
 *@author     David B. Jenkins López
 */

public interface Opcion {
	/**
	 *  Obtiene el nombre identificador de la opción
	 *
	 *@return    Una cadena que contiene el nombre identificador
	 */
	String getNombre();


	/**
	 *  Obtiene una descripción de la opción y sus posibles valores.
	 *
	 *@return    Un String conteniendo la descripción
	 */
	String getDescripcion();


	/**
	 *  Devuelve <tt>true</tt> si la opción es compuesta (esto es, es un
	 * conjunto de opciones). Devuelve <tt>false</tt> en caso contrario.
	 * 
	 *
	 *@return    un valor indicando si la opción que recibe el mensaje es
	 *          compuesta o no
	 */
	boolean getCompuesta();
}
