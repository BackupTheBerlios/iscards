package CartasMovil;

import MazoMovil.*;
import javax.microedition.lcdui.*;
import javax.bluetooth.RemoteDevice;
import java.util.Vector;

/**
 *  Clase que se ocupa de mostrar una interfaz gráfica al usuario cuando se
 *  está ejecutando código del ClienteBT
 *
 *@author    Genesis
 */
public class ClienteGUI extends List implements CommandListener {

	/**
	 *  Cliente de Bluetooth del que este objeto es interfaz gráfica
	 */
	private ClienteBT cliente;
	/**
	 *  Objeto que se encarga de escuchar los eventos que envía este objeto
	 */
	private CommandListener padre;
	/**
	 *  Display sobre el que se muestra esta interfaz gráfica.
	 */
	private Display display;
	/**
	 *  Botón que se acciona cuando el usuario quiere buscar dispositivos.
	 */
	private Command buscar = new Command("Buscar", Command.SCREEN, 1);
	/**
	 *  Botón ordena volver atrás
	 */
	public static final Command volver = new Command("Volver", Command.BACK, 1);


	/**
	 *  Constructor de la clase ClienteGUI. Prepara una primera pantalla para ser
	 *  mostrada cuando el ClienteBT lo quiera
	 *
	 *@param  padre    Es el objeto que escuche los eventos que envía el
	 *      ClienteGUI
	 *@param  c        Es el objeto del que esta clase es interfaz gráfica
	 *@param  display  es el display donde será mostrada esta GUI
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
	 *  Muestra un mensaje avisando de que no se ha encontrado ningún dispositivo
	 */
	public void ningunDisp() {
		deleteAll();
		removeCommand(buscar);
		addCommand(buscar);
		setTitle("Búsqueda finalizada");
		append("Ningún dispositivo encontrado", null);
		append("Pulse de nuevo buscar", null);
	}


	/**
	 *  Muestra los dispositivos que se han encontrado.
	 *
	 *@param  dispositivos  Son los dispositivos encontrados que hay que mostrar.
	 */
	public void mostrarDispositivos(Vector dispositivos) {
		//TODO debería mostrar el boton buscar por si quiere volver a buscar sin tener que volver atras
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
	 *      0 representa una excepción genérica<br>
	 *      1 representa un intento de conexión fallido debido a que el servidor
	 *      no ha aceptado nuestra petición<br>
	 *      2 representa un error al escoger el dispositivo con el que
	 *      conectarnos.
	 *@param  d     Display sobre el que mostrar el mensaje
	 *@param  ex    Es la excepción que ha provocado el mensaje de error (en caso
	 *      de que se muestre un mensaje debido a una excepción)
	 */
	public void mostrarAlarma(int tipo, Display d, Exception ex) {
		Alert alerta = null;
		if (tipo == 0) {
			alerta = new Alert("ERROR EN LA APLICACION", "Se ha producido el siguiente error en la aplicación:\n" + ex.getMessage(), null, AlertType.ERROR);
		}
		else if (tipo == 1) {
			alerta = new Alert("PETICIÓN RECHAZADA", "El usuario el que queria recibir cartas ha rechazado su conexión", null, AlertType.INFO);
		}
		else if (tipo == 2) {
			alerta = new Alert("IMPLOSIBLE CONEXIÓN", "Este dispositivo no ofrece la posibilidad de intercambiar cartas con usted", null, AlertType.INFO);
		}
		alerta.setTimeout(Alert.FOREVER);
		display.setCurrent(alerta, this);
	}


	/**
	 *  Método que trata las acciones a ejecutar al producirse un evento
	 *
	 *@param  c  Es el evento que se ha producido (o botón que se ha pulsado)
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
