package genesis.foro;

import java.util.regex.*;
import java.util.*;

/**
 *  Esta clase permite eliminar los tags no deseados de los mensajes que se
 *  escriben en el foro. Su método principal es <tt>filtrarTag</tt> que dada
 *  una cadena, devuelve otra cadena igual que la de entrada pero sin los tags
 *  no permitidos. El conjunto de tags y atributos que se permiten se
 *  especifican mediante los métodos <tt>anadirTagValido</tt> y <tt>
 *  anadirAtributoValido</tt> .
 *
 *@author    Manuel Montenegro
 */
public class FiltroMensajes {
	// Tabla Hash cuya clave es un String con el nombre del tag y el valor es
	// un conjunto de atributos válidos para ese tag.
	private HashMap tagsValidos;

	// Indica las transformaciones que se harán a cada carácter
	private HashMap cambioChar;


	/**
	 *  Crea un nuevo filtro de mensajes, que no admite ningún tag y ningún
	 *  atributo
	 */
	public FiltroMensajes() {
		tagsValidos = new HashMap();
		cambioChar = new HashMap();

		cambioChar.put("<", "&lt;");
		cambioChar.put(">", "&gt;");
		cambioChar.put("&", "&amp;");
		cambioChar.put("\"", "&quot;");
		cambioChar.put("á", "&aacute;");
		cambioChar.put("é", "&eacute;");
		cambioChar.put("í", "&iacute;");
		cambioChar.put("ó", "&oacute;");
		cambioChar.put("ú", "&uacute;");
		cambioChar.put("à", "&agrave;");
		cambioChar.put("è", "&egrave;");
		cambioChar.put("ì", "&igrave;");
		cambioChar.put("ò", "&ograve;");
		cambioChar.put("ù", "&ugrave;");
		cambioChar.put("Á", "&Aacute;");
		cambioChar.put("É", "&Eacute;");
		cambioChar.put("Í", "&Iacute;");
		cambioChar.put("Ó", "&Oacute;");
		cambioChar.put("Ú", "&Uacute;");
		cambioChar.put("À", "&Agrave;");
		cambioChar.put("È", "&Egrave;");
		cambioChar.put("Ì", "&Igrave;");
		cambioChar.put("Ò", "&Ograve;");
		cambioChar.put("Ù", "&Ugrave;");
		cambioChar.put("\n", "<br/>");
		cambioChar.put("\r", "");
		cambioChar.put("ä", "&auml;");
		cambioChar.put("ë", "&euml;");
		cambioChar.put("ï", "&iuml;");
		cambioChar.put("ö", "&ouml;");
		cambioChar.put("ü", "&uuml;");
		cambioChar.put("Ä", "&Auml;");
		cambioChar.put("Ë", "&Euml;");
		cambioChar.put("Ï", "&Iuml;");
		cambioChar.put("Ö", "&Ouml;");
		cambioChar.put("Ü", "&Uuml;");
		cambioChar.put("ñ", "&ntilde;");
		cambioChar.put("Ñ", "&Ntilde;");
	}


	/**
	 *  Añade el tag especificado a la lista de tags permitidos. <b>No</b> se
	 *  admitirá ningún atributo de este tag mientras no se indique mediante el
	 *  método <tt>anadirAtributoValido</tt> .
	 *
	 *@param  tag  Tag a permitir (sin los &lt; &gt;). Es indiferente el uso de
	 *      mayúsculas y minúsculas.
	 */
	public void anadirTagValido(String tag) {
		tagsValidos.put(tag.toLowerCase(), new HashSet());
	}


	/**
	 *  Añade un atributo a la lista de atributos permitidos para un determinado
	 *  tag.
	 *
	 *@param  tag       Tag sobre el que añadir el atributo permitido. Si no
	 *      existe en la lista de tags permitidos se añadirá a dicha lista. No se
	 *      distingue entre mayúsculas y minúsculas
	 *@param  atributo  El atributo a añadir. No se distingue entre mayúsculas y
	 *      minúsculas
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
	 *  caracteres extraños (&aacute;, &eacute;, ...) en entidades HTML
	 *  (&amp;aacute;, &amp;eacute;, ...) También elimina los tags y atributos no
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
	 *  Si el tag correspondiente no pertenece a la lista de tags válidos,
	 *  devuelve la cadena vacía. Si pertenece, devuelve la información de
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
