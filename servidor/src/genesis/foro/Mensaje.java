package genesis.foro;

import java.sql.Date;

/**
 *
 *  Implementación de la clase mensaje que define los atributos de los
 *  diferentes mensajes que van a aparecer en el foro 
 *
 *@author     Laura Díaz, Conchi Fernandez, Laura García, Inés González, Sergio Somovilla
 */

public class Mensaje {

        // Nº identificador del mensaje
	private int idMensaje;
	// Nick del autor que escribió el mensaje
	private String nick;
	// Contenido del mensaje
	private String texto;
	// Fecha de creación del mensaje
	private Date fecha;
	// Tema al que pertenece el mensaje
	private int idTema;


	/**
	 * OBSOLETO: ¿?
	 * 
	 *  Constructor
	 */
	public Mensaje() {
	}


	/**
	 * OBSOLETO: El problema de la fecha ya está solucionado, no
	 * hace falta poner la fecha de pega... :)
	 *  
	 *  Crea un nuevo mensaje con los atributos dados 
	 *
	 *@param  id      Identificador del mensaje
	 *@param  nick    Identificacion del autor del mensaje
	 *@param  texto   Texto del mensaje
	 */
	public Mensaje(int id, String nick, String texto) {
		this.idMensaje = id;
		this.nick = nick;
		this.texto = texto;
		this.fecha = new Date(2005, 03, 14);
	}


	/**
	 *  Constructor de la clase. Inicializa los atributos que
	 *  conforman el objeto
	 *
	 *@param  id     Identificador del mensaje
	 *@param  nick   String que identifica al autor del mensaje
	 *@param  texto  Contenido del mensaje
	 *@param  fecha  Fecha de creación del mensaje
	 *@param  idTema Identificador del tema donde se creó este mensaje
	 */
	public Mensaje(int id, String nick, String texto, Date fecha, int idTema) {
		this.idMensaje = id;
		this.nick = nick;
		this.texto = texto;
		this.fecha = fecha;
		this.idTema = idTema;
	}



	/**
	 *  Método de acceso al identificador del mensaje
	 *
	 *@return    Nº que identifica unívocamente al mensaje
	 */
	public int getIdMensaje() {
		return idMensaje;
	}


	/**
	 *  Acceso al autor del mensaje
	 *
	 *@return    El nick que identifica al autor del mensaje
	 */
	public String getNick() {
		return nick;
	}


	/**
	 *  Acceso al contenido del mensaje
	 *
	 *@return    Texto con todo el contenido del mensaje
	 */
	public String getTexto() {
		return texto;
	}


	/**
	 *  Obtiene la fecha de creación del mensaje 
	 *
	 *@return    Fecha de creación del mensaje
	 */
	public Date getFecha() {
		return fecha;
	}


	/**
	 *  Obtiene la fecha de creación del mensaje en una cadena
	 *  con el formato <tt>DD-MM-AAAA</tt>
	 *  
	 *@return   La fecha de creación del mensaje.
	 */
	public String getFechaString() {
		return fecha.getDay() + "/" + fecha.getMonth() + "/" + fecha.getYear();
	}

	/**
	 *  Obtiene el identificador del tema que contiene el mensaje 
	 *
	 *@return    Identificador del tema
	 */
	public int getIdTema() {
		return idTema;
	}

}
