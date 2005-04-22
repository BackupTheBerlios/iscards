package cartas;

import eventos.*;

import java.awt.*;

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
public class CCriatura extends CACarta {

	/*
	 *  nuevos atributos añadidos a la clase
	 */
	private int vida;
	private int ataque;
	private int defensa;
	private Color color;
	
	private final int ataqueInicial;
	private final int defensaInicial;

	/**
	 *  Constructor for the CCriatura object
	 *
	 *@param  niv     Description of Parameter
	 *@param  cost    Description of Parameter
	 *@param  punt    Description of Parameter
	 *@param  at      Description of Parameter
	 *@param  def     Description of Parameter
	 *@param  cod     Description of Parameter
	 *@param  nom     Description of Parameter
	 *@param  idR     Description of Parameter
	 *@param  idT     Description of Parameter
	 *@param  coment  Description of Parameter
	 *@param  hab     Description of Parameter
	 *@param  b       Description of Parameter
	 */
	public CCriatura(int niv, int cost, int punt, int at, int def, String cod, String nom, String idR, String idT, String coment, String hab, boolean b) {
		nivel = niv;
		coste = cost;
		puntos = punt;
		ataque = at;
		ataqueInicial=at;
		defensa = def;
		defensaInicial=def;
		codigo = cod;
		nombre = nom;
		idRaza = idR;
		idTipo = idT;
		comentarios = coment;
		habilidades = hab;
		imagen = "../Cartas/" + idRaza + "/Criaturas/" + nombre + ".jpg";
		//estado=true;
		bajada = b;
		/*
		 *  la vida es complementaria al nivel
		 */
		if (nivel == 3) {
			vida = 1;
		}
		else if (nivel == 2) {
			vida = 2;
		}
		else {
			vida = 3;
		}
		//el color no lo inicializamos
	}


	/**
	 *  Función que cambia la vida de la carta
	 *
	 *@param  v  valor nuevo de la vida
	 */
	public void setVida(int v) {
		vida = v;
	}


	/**
	 *  Función que cambia el ataque
	 *
	 *@param  at  valor nuevo de ataque
	 */
	public void setAtaque(int at) {
		ataque = at;
	}


	/**
	 *  Función que cambia el valor de la defensa
	 *
	 *@param  def  valor nuevo de la defensa
	 */
	public void setDefensa(int def) {
		defensa = def;
	}


	/**
	 *  Función que cambia el color
	 *
	 *@param  c    The new Color value
	 */
	public void setColor(Color c) {
		color = c;
		grafico.repaint();
	}


	/**
	 *  Gets the Dir attribute of the CCriatura object
	 *
	 *@return    The Dir value
	 */
	public String getDir() {
		return dirD;
	}


	/**
	 *  Función que devuelve la vida
	 *
	 *@return    vida de la criatura
	 */
	public int getVida() {
		return vida;
	}


	/**
	 *  Función que devuelve el ataque
	 *
	 *@return    puntuación de ataque
	 */
	public int getAtaque() {
		return ataque;
	}


	/**
	 *  Función que devuelve la defensa
	 *
	 *@return    puntuación de defensa
	 */
	public int getDefensa() {
		return defensa;
	}

	/**
	 *  Función que devuelve el ataque inicial
	 *
	 *@return    puntuación de ataque inicial
	 */
	public int getAtaqueInicial() {
		return ataqueInicial;
	}


	/**
	 *  Función que devuelve la defensa inicial
	 *
	 *@return    puntuación de defensa inicial
	 */
	public int getDefensaInicial() {
		return defensaInicial;
	}

	/**
	 *  Función que devuelve el color
	 *
	 *@return    color de asociacion
	 */
	public Color getColor() {
		return color;
	}


	/**
	 *  Description of the Method
	 *
	 *@return    Description of the Returned Value
	 */
	public boolean ejecuta() {
		//puede tener hab especiales ************************
		return true;
	}





	/**
	 *  Description of the Method
	 *
	 *@param  posicionCarta  Description of Parameter
	 */
	public void ataca(int posicionCarta) {
		EventoAtaque e = new EventoAtaque(getCodigo(), String.valueOf(posicionCarta));
	}


	/**
	 *  Description of the Method
	 *
	 *@param  posicionCarta           Description of Parameter
	 *@param  cartaContrario          Description of Parameter
	 *@param  posicionCartaContrario  Description of Parameter
	 */
	public void defiende(int posicionCarta, CACarta cartaContrario, int posicionCartaContrario) {
		EventoDefensa e = new EventoDefensa(getCodigo(), String.valueOf(posicionCarta),
				cartaContrario.getCodigo(),
				String.valueOf(posicionCartaContrario), 0);
		//////////se modifican los colores de las dos cartas
		/*
		 *  carta.setColor();
		 *  cartaContrario.setColor();
		 */
	}


	/**
	 *  Description of the Method
	 *
	 *@param  daño  Description of Parameter
	 *@return       Description of the Returned Value
	 */
	public boolean restaVida(int daño) {
		vida = vida - daño;
		if (vida <= 0) {
			return false;
		}
		//ha muerto
		else {
			return true;
		}
		//aun vive
	}


	/**
	 *  Description of the Method
	 *
	 *@return    Description of the Returned Value
	 */
	public String dame_clips() {
		String j = " (jugador PC)";
		String a = " (ataque " + this.getAtaque() + ")";
		String d = " (defensa " + this.getDefensa() + ")";
		String v = " (vidas " + this.getVida() + ")";
		String c = " (coste " + this.getCoste() + ")";
		String p = " (puntos " + this.getPuntos() + ")";
		String t = " (tipo criatura)";
		String e;
		if (this.getEstado()) {
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
		/*
		 *  confirmar si el orden importa en clips
		 */
		return ("(carta" + j + a + d + v + c + p + t + e + cod + lugar + ")");
	}
}
