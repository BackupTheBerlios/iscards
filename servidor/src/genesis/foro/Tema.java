package genesis.foro;

import java.sql.Date;

/**
 *
 *  Implementación de la clase que describe los temas del
 *  foro 
 *  Copyright: Copyright (c) Genesis 
 *
 *  @author: Laura Díaz, Conchi Fernandez, Laura García, Inés González, Sergio Somovilla
 */

public class Tema {
        /**
	 * El tema puede recibir mensajes
	 */
        public static final int TEMA_ABIERTO = 0;
	/**
	 * El tema no puede recibir mensajes
	 */
	public static final int TEMA_CERRADO = 1;
   
        // Nº identificador del tema
	private int idTema;
	// Título del tema
	private String titulo;
	// Estado del tema (TEMA_ABIERTO, TEMA_CERRADO)
	private int estado;
	// Fecha de creación del tema
	private Date fecha;
	// Nº de visitas del tema
	private int cont_visitas;


	/**
	 *  Constructor de la clase
	 *
	 *@param  id      Identificador del tema
	 *@param  titulo  Titulo del tema
	 *@param  cont    Contador de visitas del tema
	 */
	public Tema(int id, String titulo, int cont) {
		this.idTema = id;
		this.titulo = titulo;
		this.estado = 0;
		this.fecha = new Date(0000, 00, 00);
		this.cont_visitas = cont;
	}


	/**
	 *  Constructor de la clase. Inicializa los atributos de un objeto de la
	 *  clase Tema.
	 *
	 *@param  id      Nº identificador del tema   
	 *@param  titulo  Título descriptivo del tema
	 *@param  estado  Estado del tema (TEMA_ABIERTO, TEMA_CERRADO)
	 *@param  fecha   Fecha de creación del tema
	 *@param  cont    Contador de visitas que ha recibido el tema
	 */
	public Tema(int id, String titulo, int estado, Date fecha, int cont) {
		this.idTema = id;
		this.titulo = titulo;
		this.estado = estado;
		this.fecha = fecha;
		this.cont_visitas = cont;
	}


	/**
	 * OBSOLETO:
	 * 
	 *  Gets the Cont_visitas attribute of the Tema object
	 *
	 */
	public void setCont_visitas() {
		this.cont_visitas = cont_visitas + 1;
	}

	public void aumentaContVisitas() {
		this.cont_visitas = cont_visitas + 1;
	}


	/**
	 *  Devuelve un valor numérico que identifica unívocamente a cada tema
	 *  del foro.
	 *
	 *@return    Identificador del tema
	 */
	public int getIdTema() {
		return idTema;
	}


	/**
	 * OBSOLETO: ¿Para qué sirve esto?
	 * 
	 *  Gets the IdTema attribute of the Tema object
	 *
	 *@return    The IdTema value
	 */
	public String getIdTemaString() {
		String idS;
		idS = new Integer(this.idTema).toString();
		return idS;
	}


	/**
	 *  Devuelve el título del tema
	 *
	 *@return    Una cadena conteniendo el título del tema
	 */
	public String getTitulo() {
		return titulo;
	}


	/**
	 *  Devuelve el estado del tema:
	 *  <ul>
	 *     <li><b>Tema.TEMA_ABIERTO</b>: Se pueden escribir mensajes en el tema</b></li>
	 *     <li><b>Tema.TEMA_CERRADO</b>: NO se pueden escribir mensajes en el tema</b></li>
	 *  </ul>
	 *
	 *@return    The Estado value
	 */
	public int getEstado() {
		return estado;
	}


	/**
	 *  Devuelve el valor de la fecha de creación del tema
	 *
	 *@return    The Fecha value
	 */
	public Date getFecha() {
		return fecha;
	}


	/**
	 *
	 * OBSOLETO: ¿Esto para qué sirve?
	 * 
	 *  Gets the FechaString attribute of the Tema object
	 *
	 *@return    The FechaString value
	 */
	public String getFechaString() {
		String f;
		f = fecha.toString();
		return f;
	}


	/**
	 *  OBSOLETO: Reemplazar por getContVisitas(), para
	 *  que la nomenclatura sea más uniforme (ois que bien
	 *  hablo :D)
	 *
	 *@return    The Cont_visitas value
	 */
	public int getCont_visitas() {
		return cont_visitas;
	}

	/**
	 * Obtiene el número de visitas del tema
	 *
	 *@return    Número de visitas
	 */
	public int getContVisitas() {
		return cont_visitas;
	}
	
}
