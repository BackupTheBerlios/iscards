/**
 *  Este paquete contiene las clases necesarias para gestionar la informaci�n
 *  de una baraja almacanada en un dispositivo m�vil
 */
package MazoMovil;

import java.util.Hashtable;
import java.util.Enumeration;
import java.lang.Integer;

/**
 *  Esta clase maneja un conjunto de cartas del tipo CartaMovil y permite la
 *  sincronizaci�n y la coherencia de los datos almacenados en registros
 *  persistentes y los manejados en la aplicaci�n
 *
 *@author    Genesis
 */
public class BarajaMovil {

	/**
	 *  Es el conjunto de las cartas que forman la baraja
	 */
	private Hashtable baraja;

	/**
	 *  Es el encargado de leer, y actualizar los registros persistentes
	 */
	private GestorRMS gestor;

	/**
	 *  Es la versi�n de esta baraja. Corresponde a la versi�n de la carta m�s
	 *  moderna
	 */
	private int version;

	/**
	 *  Este atributo guarda el n�mero de cartas que hay en la baraja (no el
	 *  n�mero de cartas distintas)
	 */
	private int numeroCartas;

	/**
	 *  Este atributo contiene la raza de la baraja. Este valor es importante
	 *  debido a que no todas las cartas pueden estar en todas las barajas.
	 */
	private String raza;

	/**
	 *  Es el propietario de la baraja. Sirve para identificar la baraja a la
	 *  hora de enviar/recibir cartas, ya sea de/a otro m�vil o a un PC
	 */
	private String propietario;


	/**
	 *  Es el contructor de la nueva baraja.<br>
	 *  Seg�n el valor del par�metro crear� una baraja vac�a o una con los
	 *  valores almacenados en los registros persistentes. Esto es debido a que
	 *  si la aplicaci�n est� intentando recibir los datos desde el PC, no habr�
	 *  ninguna carta almacenada en los registros persistentes
	 *
	 *@param  vacia        Indica si debe crear una baraja vac�a o formada por
	 *      las cartas almacenadas en el dispositivo m�vil
	 *@param  raza         Description of Parameter
	 *@param  propietario  Description of Parameter
	 */
	public BarajaMovil(boolean vacia, String raza, String propietario) {
		gestor = new GestorRMS();
		version = -1;
		if (!vacia) {
			try {
				numeroCartas = gestor.leerNumeroCartas();
				baraja = gestor.leerBaraja();
				//Aqu� pediria al gestor de RMS que leyera toda la baraja
				version = gestor.leerVersion();
				this.raza = gestor.leerRaza();
				this.propietario = gestor.leerPropietario();
			}
			catch (Exception e) {
				System.out.println("Error al leer las cartas en el constructor de BarajaMovil");
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}
		else {
			baraja = new Hashtable();
			this.raza = raza;
			this.propietario = propietario;
		}
	}


	/**
	 *  Este m�todo es el accesor del atributo numeroCartas
	 *
	 *@return    el n�mero de cartas esta baraja
	 */
	public int getNumeroCartas() {
		return numeroCartas;
	}


	/**
	 *  Accesor del atributo version
	 *
	 *@return    Devuelve la versi�n de baraja
	 */
	public int getversionBaraja() {
		return version;
	}


	/**
	 *  Accesor del atributo raza
	 *
	 *@return    Devuelve la raza de la baraja
	 */
	public String getRaza() {
		return raza;
	}


	/**
	 *  Accesor del atributo propietario
	 *
	 *@return    Devuelve el propietario de la baraja
	 */
	public String getPropietario() {
		return propietario;
	}


	/**
	 *  Busca la carta indicada por el par�metro
	 *
	 *@param  codigo  Es el c�digo de identificaci�n de la carta buscada
	 *@return         Devuelve la carta que se buscaba en el caso de estar en la
	 *      baraja y null en casso contrario
	 */
	public CartaMovil buscaCarta(int codigo) {
		return ((CartaMovil) baraja.get(new Integer(codigo)));
	}


	/**
	 *  A�ade una nueva carta a la baraja y a los registros presistentes en caso
	 *  de que as� se indique.<br>
	 *  Si una carta igual ya est� en la baraja aumenta la cardinalidad de la
	 *  carta pero no a�ade otro objeto a la Hashtable (m�s que nada porque no se
	 *  puede ;-p)
	 *
	 *@param  nueva    Es la carta que se va a a�adir a la baraja
	 *@param  refleja  Indica si este cambio en la baraja debe reflejarse en el
	 *      almacenamiento RMS o no
	 *@return          true - si ha podido a�adirse<br>
	 *      false - en caso contrario
	 */
	public boolean a�adeCarta(CartaMovil nueva, boolean refleja) {
		try {
			Integer codigo = new Integer(nueva.getCodigoID());
			if (baraja.containsKey(codigo)) {
				((CartaMovil) baraja.get(codigo)).aumentaNumero();
				numeroCartas++;
				if (refleja) {
					gestor.guardarBaraja(this);
				}
				return true;
			}
			else {
				if ((nueva.getRaza().equals(raza)) || (nueva.getNivel().equals("3"))) {
					baraja.put(new Integer(nueva.getCodigoID()), nueva);
					if (nueva.getversionBaraja() > version) {
						version = nueva.getversionBaraja();
					}
					numeroCartas++;
					if (refleja) {
						gestor.guardarBaraja(this);
					}
					return true;
				}
				else {
					System.out.println("no acepta la carta " + nueva.toShortString() + "\nla raza de la baraja es " + raza + " y la de la carta " + nueva.getRaza());
					return false;
				}
			}
		}
		catch (Exception e) {
			System.out.println("Error al a�adir la carta en a�adeCarta");
			e.printStackTrace();
			return false;
		}
	}


	/**
	 *  Quita la carta especificada de la baraja y los registros persistentes
	 *  teniendo en cuenta que puede estar repetida
	 *
	 *@param  codigo   El c�digo de la carta a eliminar
	 *@param  refleja  Description of Parameter
	 */
	public void eliminaCarta(int codigo, boolean refleja) {
		try {
			Integer cod = new Integer(codigo);
			if (((CartaMovil) baraja.get(cod)).disminuyeNumero()) {
				baraja.remove(new Integer(codigo));
			}
			numeroCartas--;
			if (refleja) {
				gestor.guardarBaraja(this);
			}
		}
		catch (Exception e) {
			System.out.println("Error al eliminar la carta en eliminaCarta");
			e.printStackTrace();
		}
	}


	/**
	 *  Devuelve una enumeraci�n (el equivalente de J2ME al iterator de Java) de
	 *  las cartas de la baraja. <br>
	 *  Si la carta est� repetida solo devuelve un objeto CartaMovil con el
	 *  atributo cantidad al valor apropiado.
	 *
	 *@return    todas las cartas de la baraja. Si est�n repetidas <code>cartas.getCantidad()>1</code>
	 */
	public Enumeration enumeraCartas() {
		return baraja.elements();
	}


	/**
	 *  M�todo que se encarga de guardar los datos de la baraja en la memoria del
	 *  movil
	 */
	public void guardaRMS() {
		try {
			gestor.guardarBaraja(this);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
