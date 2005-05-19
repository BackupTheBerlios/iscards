package cartas;

/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: Clase que implementa a los objetos de tipo carta Hechizo</p>
 *  <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Tony
 *@version    1.0
 */

public class CHechizo extends CACarta{

	/**
	 *  Es el efecto del hechizo
	 */
	protected int idHab;


	/**
	 *  Constructora de la clase Carta Hechizo
	 *
	 *@param  niv
	 *@param  cost
	 *@param  punt
	 *@param  nom
	 *@param  cod
	 *@param  idR     Description of Parameter
	 *@param  idT     Description of Parameter
	 *@param  coment
	 *@param  hab
	 *@param  b       Description of Parameter
	 */
	public CHechizo(int niv, int cost, int punt, String nom, String cod, String idR, String idT, String coment, String hab, boolean b) {

		nivel = niv;
		coste = cost;
		puntos = punt;
		codigo = cod;
		nombre = nom;
		idRaza = idR;
		idTipo = idT;
		comentarios = coment;
		habilidades = hab;
		imagen = "../Cartas/" + idRaza + "/Hechizos/" + nombre + ".jpg";
//    estado=true;
		bajada = b;
	}


	/**
	 *  Constructora de la clase con otros parámetros
	 *
	 *@param  tipo
	 */
	public CHechizo(int tipo) {
		idHab = tipo;
	}


	/**
	 *  Función que ejecuta las propiedades del hechizo en cuestión según su
	 *  habilidad
	 *
	 *@return    exito de la ejecución
	 */
	public boolean ejecuta() {
//    estado = !estado;
		return false;
	}


	/**
	 *  Función que devuleve los atributos de una carta de tipo hechizo en
	 *  formato de texto semejante a clips para poder procesarla mediante la
	 *  inteligencia artificial
	 *
	 *@return
	 */
	public String dame_clips(String Jugador) {
		String j = " (jugador PC)";
		String a = " (ataque 0)";
		String d = " (defensa 0)";
		String v = " (vidas 0)";
		String c = " (coste " + this.getCoste() + ")";
		String p = " (puntos " + this.getPuntos() + ")";
		String t = " (tipo hechizo)";
		String e;
		if (this.getEstado() == false) {
			e = " (estado enderezado)";
		}
		else {
			e = " (estado girada)";
		}
		String cod = " (codigo " + this.getCodigo() + ")";
		String lugar;
		if (this.isBajada() == false) {
			lugar = " (lugar mano)";
		}
		else {
			lugar = " (lugar mesa)";
		}
		//confirmar si el orden importa en clips
		return ("(carta" + j + a + d + v + c + p + t + e + cod + lugar + ")");
	}

}
