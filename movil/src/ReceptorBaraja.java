package CartasMovil;

import MazoMovil.*;
import javax.microedition.lcdui.*;

/**
 *  Clase que se encarga de dar al usuario un interfaz y la funcionalidad
 *  necesaria para iniciar una recepci�n de cartas por Bluetooth,
 *  diferenciando si quiere recibir de un PC o de un m�vil.
 *
 *@author    Genesis
 */
public class ReceptorBaraja extends List {
	/**
	 *  Es el onjeto que va a escuchar los eventos que se produzcan en este
	 *  objeto. (el MIDlet)
	 */
	private CommandListener padre;
	/**
	 *  Es la baraja a la que vamos a a�adir las cartas que recibamos por
	 *  Bluetooth
	 */
	private BarajaMovil baraja;
	/**
	 *  Es el commando que pide la vuelta al men� principal del MIDlet
	 */
	private Command volver = new Command("Volver", Command.EXIT, 1);
	/**
	 *  Es el Display donde se muestra este List
	 */
	private Display display;
	/**
	 *  Es el Cliente de Bluetooth que va a recibir las cartas de otro
	 *  dispositivo
	 */
	private ClienteBT cliente;
	/**
	 *  Es el comando que indica al MIDlet que ha terminado la recepci�n de las
	 *  cartas.
	 */
	public static Command FINRECEPCION = new Command("FINRECEPCION", Command.ITEM, 1);



	/**
	 *  Construye un nuevo objeto de la clase ReceptorBaraja
	 *
	 *@param  padre    Es el objeto que va a escuchar los eventos que se
	 *      produzcan en este objeto
	 *@param  display  Es el display donde mostraremos este List.
	 */
	public ReceptorBaraja(CommandListener padre, Display display) {
		super("G�nesis", List.IMPLICIT);
		setFitPolicy(Choice.TEXT_WRAP_ON);
		this.padre = padre;
		this.display = display;
		setCommandListener(padre);
		addCommand(volver);
		append("Recibir de un m�vil", null);
		append("Recibir de un PC", null);
		baraja = new BarajaMovil(false, null, null);
	}


	/**
	 *  Gets the Baraja attribute of the ReceptorBaraja object
	 *
	 *@return    The Baraja value
	 */
	public BarajaMovil getBaraja() {
		return baraja;
	}


	/**
	 *  Gets the Cliente attribute of the ReceptorBaraja object
	 *
	 *@return    The Cliente value
	 */
	public ClienteBT getCliente() {
		return cliente;
	}


	/**
	 *  Inicia la recepci�n de cartas de otro dispositivo.
	 */
	public void recibir() {
		cliente = new ClienteBT(padre, display, baraja.getPropietario());
	}


	/**
	 *  A�ade las cartas que le es posible a�adir a su baraja.
	 *
	 *@param  cartas  Es el array que contiene las cartas que se han recibido.
	 */
	public void a�adeYConfirma(CartaMovil[] cartas) {
		int i;
		int a�adidas;
		int[] claves = new int[cartas.length];
		a�adidas = 0;
		for (i = 0; i < cartas.length; i++) {
			if (baraja.a�adeCarta(cartas[i], false)) {
				claves[a�adidas] = cartas[i].getCodigoID();
				a�adidas++;
			}
		}
		baraja.guardaRMS();
		int[] aux = new int[a�adidas];
		for (i = 0; i < a�adidas; i++) {
			aux[i] = claves[i];
		}
		try {
			cliente.enviaConfirmadas(aux);
			cliente.cierraConexion();
			padre.commandAction(FINRECEPCION, display.getCurrent());
		}
		catch (Exception e) {
			Alert alerta = new Alert("ERROR EN LA APLICACION", "Se ha producido el siguiente error en la aplicaci�n" + e.getMessage(), null, AlertType.ERROR);
			alerta.setTimeout(Alert.FOREVER);
			display.setCurrent(alerta, display.getCurrent());
			padre.commandAction(FINRECEPCION, display.getCurrent());
		}
	}

}
