/**
 *  Este paquete contiene las clases necesarias para gestionar la información
 *  de una baraja almacanada en un dispositivo móvil
 */
package MazoMovil;

import java.util.Hashtable;
import java.util.Enumeration;
import java.lang.Integer;

/**
 *  Esta clase maneja un conjunto de cartas del tipo CartaMovil y permite la
 *  sincronización y la coherencia de los datos almacenados en registros
 *  persistentes y los manejados en la aplicación
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
	 *  Es la versión de esta baraja. Corresponde a la versión de la carta más
	 *  moderna
	 */
	private int version;

	/**
	 *  Este atributo guarda el número de cartas que hay en la baraja (no el
	 *  número de cartas distintas)
	 */
	private int numeroCartas;

	/**
	 *  Este atributo contiene la raza de la baraja. Este valor es importante
	 *  debido a que no todas las cartas pueden estar en todas las barajas.
	 */
	private String raza;

	/**
	 *  Es el propietario de la baraja. Sirve para identificar la baraja a la
	 *  hora de enviar/recibir cartas, ya sea de/a otro móvil o a un PC
	 */
	private String propietario;


	/**
	 *  Es el contructor de la nueva baraja.<br>
	 *  Según el valor del parámetro creará una baraja vacía o una con los
	 *  valores almacenados en los registros persistentes. Esto es debido a que
	 *  si la aplicación está intentando recibir los datos desde el PC, no habrá
	 *  ninguna carta almacenada en los registros persistentes
	 *
	 *@param  vacia        Indica si debe crear una baraja vacía o formada por
	 *      las cartas almacenadas en el dispositivo móvil
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
				//Aquí pediria al gestor de RMS que leyera toda la baraja
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
	 *  Este método es el accesor del atributo numeroCartas
	 *
	 *@return    el número de cartas esta baraja
	 */
	public int getNumeroCartas() {
		return numeroCartas;
	}


	/**
	 *  Accesor del atributo version
	 *
	 *@return    Devuelve la versión de baraja
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
	 *  Busca la carta indicada por el parámetro
	 *
	 *@param  codigo  Es el código de identificación de la carta buscada
	 *@return         Devuelve la carta que se buscaba en el caso de estar en la
	 *      baraja y null en casso contrario
	 */
	public CartaMovil buscaCarta(int codigo) {
		return ((CartaMovil) baraja.get(new Integer(codigo)));
	}


	/**
	 *  Añade una nueva carta a la baraja y a los registros presistentes en caso
	 *  de que así se indique.<br>
	 *  Si una carta igual ya está en la baraja aumenta la cardinalidad de la
	 *  carta pero no añade otro objeto a la Hashtable (más que nada porque no se
	 *  puede ;-p)
	 *
	 *@param  nueva    Es la carta que se va a añadir a la baraja
	 *@param  refleja  Indica si este cambio en la baraja debe reflejarse en el
	 *      almacenamiento RMS o no
	 *@return          true - si ha podido añadirse<br>
	 *      false - en caso contrario
	 */
	public boolean añadeCarta(CartaMovil nueva, boolean refleja) {
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
			System.out.println("Error al añadir la carta en añadeCarta");
			e.printStackTrace();
			return false;
		}
	}


	/**
	 *  Quita la carta especificada de la baraja y los registros persistentes
	 *  teniendo en cuenta que puede estar repetida
	 *
	 *@param  codigo   El código de la carta a eliminar
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
	 *  Devuelve una enumeración (el equivalente de J2ME al iterator de Java) de
	 *  las cartas de la baraja. <br>
	 *  Si la carta está repetida solo devuelve un objeto CartaMovil con el
	 *  atributo cantidad al valor apropiado.
	 *
	 *@return    todas las cartas de la baraja. Si están repetidas <code>cartas.getCantidad()>1</code>
	 */
	public Enumeration enumeraCartas() {
		return baraja.elements();
	}


	/**
	 *  Método que se encarga de guardar los datos de la baraja en la memoria del
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
