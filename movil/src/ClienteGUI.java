package CartasMovil;

import MazoMovil.*;
import javax.microedition.lcdui.*;
import javax.bluetooth.RemoteDevice;
import java.util.Vector;

/**
 *  Clase que se ocupa de mostrar una interfaz gr�fica al usuario cuando se
 *  est� ejecutando c�digo del ClienteBT
 *
 *@author    Genesis
 */
public class ClienteGUI extends List implements CommandListener {

	/**
	 *  Cliente de Bluetooth del que este objeto es interfaz gr�fica
	 */
	private ClienteBT cliente;
	/**
	 *  Objeto que se encarga de escuchar los eventos que env�a este objeto
	 */
	private CommandListener padre;
	/**
	 *  Display sobre el que se muestra esta interfaz gr�fica.
	 */
	private Display display;
	/**
	 *  Bot�n que se acciona cuando el usuario quiere buscar dispositivos.
	 */
	private Command buscar = new Command("Buscar", Command.SCREEN, 1);
	/**
	 *  Bot�n ordena volver atr�s
	 */
	public static final Command volver = new Command("Volver", Command.BACK, 1);


	/**
	 *  Constructor de la clase ClienteGUI. Prepara una primera pantalla para ser
	 *  mostrada cuando el ClienteBT lo quiera
	 *
	 *@param  padre    Es el objeto que escuche los eventos que env�a el
	 *      ClienteGUI
	 *@param  c        Es el objeto del que esta clase es interfaz gr�fica
	 *@param  display  es el display donde ser� mostrada esta GUI
	 */
	public ClienteGUI(CommandListener padre, ClienteBT c, Display display) {
		super("Elija dispositivo", List.IMPLICIT);
		setFitPolicy(Choice.TEXT_WRAP_ON);
		this.padre = padre;
		this.display = display;
		cliente = c;
		addCommand(volver);
		setCommandListener(this);
	}


	/**
	 *  Muestra un mensaje avisando de que no se ha encontrado ning�n dispositivo
	 */
	public void ningunDisp() {
		deleteAll();
		removeCommand(buscar);
		addCommand(buscar);
		setTitle("B�squeda finalizada");
		append("Ning�n dispositivo encontrado", null);
		append("Pulse de nuevo buscar", null);
	}


	/**
	 *  Muestra los dispositivos que se han encontrado.
	 *
	 *@param  dispositivos  Son los dispositivos encontrados que hay que mostrar.
	 */
	public void mostrarDispositivos(Vector dispositivos) {
		//TODO deber�a mostrar el boton buscar por si quiere volver a buscar sin tener que volver atras
		deleteAll();
		removeCommand(buscar);
		setTitle("Dispositivos Encontrados");
		try {
			for (int i = 0; i < dispositivos.size(); i++) {
				append(((RemoteDevice) dispositivos.elementAt(i)).getFriendlyName(true), null);
			}
		}
		catch (Exception e) {
			mostrarAlarma(0, display, e);
		}
	}


	/**
	 *  Muestra un mensaje de aviso del tipo que se le especifique
	 *
	 *@param  tipo  Es el tipo de mensaje a mostrar.<br>
	 *      0 representa una excepci�n gen�rica<br>
	 *      1 representa un intento de conexi�n fallido debido a que el servidor
	 *      no ha aceptado nuestra petici�n<br>
	 *      2 representa un error al escoger el dispositivo con el que
	 *      conectarnos.
	 *@param  d     Display sobre el que mostrar el mensaje
	 *@param  ex    Es la excepci�n que ha provocado el mensaje de error (en caso
	 *      de que se muestre un mensaje debido a una excepci�n)
	 */
	public void mostrarAlarma(int tipo, Display d, Exception ex) {
		Alert alerta = null;
		if (tipo == 0) {
			alerta = new Alert("ERROR EN LA APLICACION", "Se ha producido el siguiente error en la aplicaci�n:\n" + ex.getMessage(), null, AlertType.ERROR);
		}
		else if (tipo == 1) {
			alerta = new Alert("PETICI�N RECHAZADA", "El usuario el que queria recibir cartas ha rechazado su conexi�n", null, AlertType.INFO);
		}
		else if (tipo == 2) {
			alerta = new Alert("IMPLOSIBLE CONEXI�N", "Este dispositivo no ofrece la posibilidad de intercambiar cartas con usted", null, AlertType.INFO);
		}
		alerta.setTimeout(Alert.FOREVER);
		display.setCurrent(alerta, this);
	}


	/**
	 *  M�todo que trata las acciones a ejecutar al producirse un evento
	 *
	 *@param  c  Es el evento que se ha producido (o bot�n que se ha pulsado)
	 *@param  d  Es la pantalla en la que se ha producido el evento
	 */
	public void commandAction(Command c, Displayable d) {
		if (c.equals(this.SELECT_COMMAND)) {
			cliente.conecta(getSelectedIndex());
		}
		else if (c.equals(volver)) {
			padre.commandAction(volver, this);
		}
		else if (c.equals(buscar)) {
			cliente.buscaDisp();
		}
	}
}
