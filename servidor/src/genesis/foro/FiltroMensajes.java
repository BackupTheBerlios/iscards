package genesis.foro;

import java.util.regex.*;
import java.util.*;

/**
 *  Esta clase permite eliminar los tags no deseados de los mensajes que se
 *  escriben en el foro. Su m�todo principal es <tt>filtrarTag</tt> que dada
 *  una cadena, devuelve otra cadena igual que la de entrada pero sin los tags
 *  no permitidos. El conjunto de tags y atributos que se permiten se
 *  especifican mediante los m�todos <tt>anadirTagValido</tt> y <tt>
 *  anadirAtributoValido</tt> .
 *
 *@author    Manuel Montenegro
 */
public class FiltroMensajes {
	// Tabla Hash cuya clave es un String con el nombre del tag y el valor es
	// un conjunto de atributos v�lidos para ese tag.
	private HashMap tagsValidos;

	// Indica las transformaciones que se har�n a cada car�cter
	private HashMap cambioChar;


	/**
	 *  Crea un nuevo filtro de mensajes, que no admite ning�n tag y ning�n
	 *  atributo
	 */
	public FiltroMensajes() {
		tagsValidos = new HashMap();
		cambioChar = new HashMap();

		cambioChar.put("<", "&lt;");
		cambioChar.put(">", "&gt;");
		cambioChar.put("&", "&amp;");
		cambioChar.put("\"", "&quot;");
		cambioChar.put("�", "&aacute;");
		cambioChar.put("�", "&eacute;");
		cambioChar.put("�", "&iacute;");
		cambioChar.put("�", "&oacute;");
		cambioChar.put("�", "&uacute;");
		cambioChar.put("�", "&agrave;");
		cambioChar.put("�", "&egrave;");
		cambioChar.put("�", "&igrave;");
		cambioChar.put("�", "&ograve;");
		cambioChar.put("�", "&ugrave;");
		cambioChar.put("�", "&Aacute;");
		cambioChar.put("�", "&Eacute;");
		cambioChar.put("�", "&Iacute;");
		cambioChar.put("�", "&Oacute;");
		cambioChar.put("�", "&Uacute;");
		cambioChar.put("�", "&Agrave;");
		cambioChar.put("�", "&Egrave;");
		cambioChar.put("�", "&Igrave;");
		cambioChar.put("�", "&Ograve;");
		cambioChar.put("�", "&Ugrave;");
		cambioChar.put("\n", "<br/>");
		cambioChar.put("\r", "");
		cambioChar.put("�", "&auml;");
		cambioChar.put("�", "&euml;");
		cambioChar.put("�", "&iuml;");
		cambioChar.put("�", "&ouml;");
		cambioChar.put("�", "&uuml;");
		cambioChar.put("�", "&Auml;");
		cambioChar.put("�", "&Euml;");
		cambioChar.put("�", "&Iuml;");
		cambioChar.put("�", "&Ouml;");
		cambioChar.put("�", "&Uuml;");
		cambioChar.put("�", "&ntilde;");
		cambioChar.put("�", "&Ntilde;");
	}


	/**
	 *  A�ade el tag especificado a la lista de tags permitidos. <b>No</b> se
	 *  admitir� ning�n atributo de este tag mientras no se indique mediante el
	 *  m�todo <tt>anadirAtributoValido</tt> .
	 *
	 *@param  tag  Tag a permitir (sin los &lt; &gt;). Es indiferente el uso de
	 *      may�sculas y min�sculas.
	 */
	public void anadirTagValido(String tag) {
		tagsValidos.put(tag.toLowerCase(), new HashSet());
	}


	/**
	 *  A�ade un atributo a la lista de atributos permitidos para un determinado
	 *  tag.
	 *
	 *@param  tag       Tag sobre el que a�adir el atributo permitido. Si no
	 *      existe en la lista de tags permitidos se a�adir� a dicha lista. No se
	 *      distingue entre may�sculas y min�sculas
	 *@param  atributo  El atributo a a�adir. No se distingue entre may�sculas y
	 *      min�sculas
	 */
	public void anadirAtributoValido(String tag, String atributo) {
		HashSet conjuntoAtributos = (HashSet) tagsValidos.get(tag.toLowerCase());
		if (conjuntoAtributos == null) {
			conjuntoAtributos = new HashSet();
			tagsValidos.put(tag.toLowerCase(), conjuntoAtributos);
		}
		conjuntoAtributos.add(atributo.toLowerCase());
	}


	/**
	 *  Devuelve una cadena similar a la de entrada pero transformando los
	 *  caracteres extra�os (&aacute;, &eacute;, ...) en entidades HTML
	 *  (&amp;aacute;, &amp;eacute;, ...) Tambi�n elimina los tags y atributos no
	 *  deseados.
	 *
	 *@param  texto  Cadena original
	 *@return        Cadena transformada
	 */
	public String filtrar(String texto) {
		Pattern patronTexto = Pattern.compile("<[^>]*>");
		Matcher m = patronTexto.matcher(texto);
		boolean algunTag = false;

		StringBuffer result = new StringBuffer();
		int posicion = 0;

		while (m.find()) {
			String textoNormal = texto.substring(posicion, m.start());
			result.append(filtrarTextoNormal(textoNormal));
			result.append(filtrarTag(m.group()));
			posicion = m.end();
			algunTag = true;
		}

		if (!algunTag) {
			result.append(filtrarTextoNormal(texto));
		}
		else {
			String textoNormal = texto.substring(posicion, texto.length());
			result.append(filtrarTextoNormal(textoNormal));
		}

		return result.toString();
	}


	/**
	 *  Transforma los caracteres raros de texto normal (sin tags) en entidades
	 *  HTML
	 *
	 *@param  texto  Description of Parameter
	 *@return        Description of the Returned Value
	 */
	private String filtrarTextoNormal(String texto) {
		StringBuffer result = new StringBuffer();
		int longitud = texto.length();
		for (int i = 0; i < longitud; i++) {
			String caracter = String.valueOf(texto.charAt(i));
			String sust = (String) cambioChar.get(caracter);
			if (sust == null) {
				result.append(caracter);
			}
			else {
				result.append(sust);
			}
		}
		return result.toString();
	}


	/**
	 *  Elimina los atributos no deseados de una lista de atributos de la forma:
	 *  atrib1=valor1 atrib2=valor2
	 *
	 *@param  conjunto  Description of Parameter
	 *@param  texto     Description of Parameter
	 *@return           Description of the Returned Value
	 */
	private String filtrarListaAtributos(HashSet conjunto, String texto) {
		StringBuffer result = new StringBuffer();

		Pattern patronAtributo = Pattern.compile(
				"(\\w+)\\s*(=\\s*((\"[^\"]*\")|([^\"]\\S*)))?");
		Matcher m = patronAtributo.matcher(texto);
		while (m.find()) {
			String nombreAtributo = m.group(1).toLowerCase();
			if (conjunto.contains(nombreAtributo)) {
				result.append(" " + m.group());
			}
		}

		return result.toString();
	}


	/**
	 *  Si el tag correspondiente no pertenece a la lista de tags v�lidos,
	 *  devuelve la cadena vac�a. Si pertenece, devuelve la informaci�n de
	 *  entrada pero con los atributos no permitidos eliminados.
	 *
	 *@param  texto  Description of Parameter
	 *@return        Description of the Returned Value
	 */
	private String filtrarTag(String texto) {
		StringBuffer result = new StringBuffer();

		Pattern patronTag = Pattern.compile(
				"^<\\s*(/?)\\s*(\\S+)\\s*([^>]*)>$");
		Matcher m = patronTag.matcher(texto);

		if (m.find()) {
			String nombreTag = m.group(2).toLowerCase();
			HashSet conjuntoAtributos = (HashSet) tagsValidos.get(nombreTag);
			if (conjuntoAtributos != null) {
				String listaAtributos = m.group(3);
				String nuevaListaAtributos = filtrarListaAtributos(conjuntoAtributos, listaAtributos);
				result.append("<" + m.group(1) + nombreTag +
						nuevaListaAtributos + ">");
			}
			else {
				result.append(filtrarTextoNormal(m.group(0)));
			}
		}

		return result.toString();
	}
}
