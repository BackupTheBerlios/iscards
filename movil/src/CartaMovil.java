package MazoMovil;

/**
 *  Esta clase representa las cartas que manejamos en el dispositivo móvil
 *
 *@author    Genesis
 */

public class CartaMovil {
	/**
	 *  Codigo de identificación de la carta
	 */
	private int codigoID;
	/**
	 *  Version de la baraja a la que pertenece esta carta
	 */
	private int versionBaraja;
	/**
	 *  Información completa de la carta. Detallamos la información que contiene
	 *  cada indice del array:<br>
	 *  [0] Nombre<br>
	 *  [1] Raza<br>
	 *  [2] Ataque<br>
	 *  [3] Defensa<br>
	 *  [4] Características<br>
	 *  [5] Historia<br>
	 *  [6] Nivel<br>
	 *  [7] Manás necesarios
	 */
	private String[] informacion;

	/**
	 *  Indica cuantas cartas de este tipo están en la baraja. Actualizado
	 *  automáticamente al añadir o eliminar la carta de la baraja
	 */
	private int cantidad;


	/**
	 *  Crea una carta nueva de acuerdo a la información de los parámetros
	 *
	 *@param  cod   Es el código de identificación de la carta a crear
	 *@param  ver   Es la versión de la baraja de la carta a crear
	 *@param  info  Es la información completa de la carta a crear. Consultar la
	 *      definición del atributo informacion para conocer la información que
	 *      contienen sus diferentes índices
	 */
	public CartaMovil(int cod, int ver, String[] info) {
		codigoID = cod;
		versionBaraja = ver;
		cantidad = 1;
		informacion = new String[info.length];
		for (int i = 0; i < info.length; i++) {
			informacion[i] = info[i];
		}
	}


	/**
	 *  Accesor del atributo codigoID
	 *
	 *@return    Devuelve el codigo de identificación de la carta.
	 */
	public int getCodigoID() {
		return codigoID;
	}


	/**
	 *  Accesor del atributo versiónBaraja
	 *
	 *@return    Devuelve la versión de baraja de la carta.
	 */
	public int getversionBaraja() {
		return versionBaraja;
	}


	/**
	 *  Accesor del atributo cantidad
	 *
	 *@return    Devuelve cuántas cartas como esta hay en la baraja.
	 */
	public int getCantidad() {
		return cantidad;
	}


	/**
	 *  Devuelve el valor que corresponde al nivel de la carta
	 *
	 *@return    el nivel al que pertenece la carta.
	 */
	public String getNivel() {
		return informacion[6];
	}


	/**
	 *  Devuelve el valor que corresponde a la raza de la carta
	 *
	 *@return    la raza a la que pertenece la carta.
	 */
	public String getRaza() {
		return informacion[1];
	}


	/**
	 *  Devuelve la información completa de la carta
	 *
	 *@return    todos los datos que se tienen de la carta
	 */
	public String[] getInfoCompleta() {
		return informacion;
	}


	/**
	 *  Aumenta en uno la cardinalidad de la carta
	 */
	public void aumentaNumero() {
		cantidad++;
	}


	/**
	 *  Disminuye en uno la cardinalidad de la carta
	 *
	 *@return    true - si con esta eliminación desaparece esta carta de la
	 *      baraja<br>
	 *      false - si aún está presente
	 */
	public boolean disminuyeNumero() {
		if (cantidad == 1) {
			return true;
		}
		else {
			cantidad--;
			return false;
		}
	}


	/**
	 *  Genera el texto completo de la carta para la vista en modo extendido
	 *
	 *@return    La información de la carta formateada para mostrarla al usuario
	 *      en formato extendido
	 */
	public String toString() {
		return ("Nombre: " + informacion[0] +
				"\nRaza: " + informacion[1] +
				"\nAtaque/Defensa: " + informacion[2] + "/" + informacion[3] +
				"\nTexto descriptivo: " + informacion[4] +
				"\nTexto mitológico: " + informacion[5] +
				"\nNivel: " + informacion[6] +
				"\nManás necesarios: " + informacion[7]
				);
	}


	/**
	 *  Genera el texto de la carta para la vista en modo compacto
	 *
	 *@return    La información de la carta formateada para mostrarla al usuario
	 *      en formato compacto
	 */
	public String toShortString() {
		return (informacion[0] +
				".\nRaza: " + informacion[1] +
				".\nAtaque/Defensa/Manás: " + informacion[2] + "/" + informacion[3] + "/" + informacion[7]);
	}


	/**
	 *  Compara esta carta con la pasada como parámetro
	 *
	 *@param  o  Es la carta a comparar
	 *@return    true si son la misma carta y false en caso contrario
	 */
	public boolean equals(Object o) {
		return (((CartaMovil) o).getCodigoID() == codigoID);
	}
}
