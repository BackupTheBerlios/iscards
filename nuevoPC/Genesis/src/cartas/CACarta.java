package cartas;

import interfaz.*;
/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: Clase abstracta que extenderan todos los objetos de tipo
 *  carta</p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Tony
 *@version    1.0
 */

public abstract class CACarta implements Cloneable {
	/**
	 *  nivel de la carta 0 - 2000
	 */
	protected int nivel;

	/**
	 *  coste en manas de la carta
	 */
	protected int coste;

	/**
	 *  valor en puntos de la carta
	 */
	protected int puntos;

	/**
	 *  codigo de la carta
	 */
	protected String codigo;

	/**
	 */
	protected String dirD;
	//direccion de la foto (esto sobraría, en principio vale con la variable imagen)

	/**
	 *  nombre de la carta
	 */
	protected String nombre;

	/**
	 *  nombre de la raza a la que pertenece la carta (Angeles, Demonios,
	 *  Humanos, Inmortales)
	 */
	protected String idRaza;

	/**
	 *  nombre del tipo al que pertenece la carta (criatura, conjuro, hechizo)
	 */
	protected String idTipo;

	/**
	 *  texto con comentarios acerca de la carta
	 */
	protected String comentarios;

	/**
	 *  texto ¿codificado? con las habilidades de la carta
	 */
	protected String habilidades;

	/**
	 *  ruta de la imagen de la carta
	 */
	protected String imagen;

	/**
	 *  true = enderezada ; false = girada
	 */
	protected boolean estado = true;

	/**
	 *  mano = true y mesa= false
	 */
	protected boolean bajada = false;

	protected Carta grafico;


	/**
	 *  Sets the Estado attribute of the CACarta object
	 *
	 *@param  est  The new Estado value
	 */
	public void setEstado(boolean est) {		
		estado = est;
	}


	/**
	 *  Sets the Grafico attribute of the CACarta object
	 *
	 *@param  g  The new Grafico value
	 */
	public void setGrafico(Carta g) {
		grafico = g;
	}


	/**
	 *@return
	 */
	public int getNivel() {
		return nivel;
	}


	/**
	 *@return
	 */
	public int getCoste() {
		return coste;
	}


	/**
	 *@return
	 */
	public int getPuntos() {
		return puntos;
	}


	/**
	 *@return
	 */
	public String getCodigo() {
		return codigo;
	}


	/**
	 *@return
	 */
	public String getDir() {
		return dirD;
	}


	/**
	 *@return
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 *@return
	 */
	public String getIdRaza() {
		return idRaza;
	}


	/**
	 *@return
	 */
	public String getIdTipo() {
		return idTipo;
	}


	/**
	 *@return
	 */
	public String getComentarios() {
		return comentarios;
	}


	/**
	 *@return
	 */
	public String getHabilidades() {
		return habilidades;
	}


	/**
	 *@return
	 */
	public String getImagen() {
		return imagen;
	}


	/**
	 *@return
	 */
	public boolean getEstado() {
		return estado;
	}


	/**
	 *  Gets the Bajada attribute of the CACarta object
	 *
	 *@return    The Bajada value
	 */
	public boolean isBajada() {
		return bajada;
	}


	/**
	 *  Gets the Grafico attribute of the CACarta object
	 *
	 *@return    The Grafico value
	 */
	public Carta getGrafico() {

		return grafico;
	}


	/**
	 *  Description of the Method
	 */
	public void baja() {
		bajada = true;
	}


	/**
	 *  Description of the Method
	 *
	 *@return    Description of the Returned Value
	 */
	public CACarta dameUnaCopia() {
		try {
			return (CACarta) (this.clone());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}


	/**
	 *  función que ejecuta la acción de cada carta
	 *
	 *@return
	 */
	public abstract boolean ejecuta();


	/**
	 *  funicion que devuleve los atributos de una carta en formato de texto
	 *  semejante a clips para poder procesarla mediante la inteligencia
	 *  artificial
	 *
	 *@return
	 */
	public abstract String dame_clips();

}
