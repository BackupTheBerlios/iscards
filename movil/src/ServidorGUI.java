package CartasMovil;

import javax.microedition.lcdui.*;
import MazoMovil.*;

/**
 *  Esta clase proporciona una interfaz gr�fica para la clase ServidorBT
 *
 *@author    Genesis
 */
public class ServidorGUI extends List implements CommandListener {
	/**
	 *  Es el servidor del que este objeto es interfaz gr�fica
	 */
	private ServidorBT serv;
	/**
	 *  Es el Command pulsado cuando el usuario acepta una conexi�n
	 */
	private Command aceptar;
	/**
	 *  Es el Command pulsado cuando el usuario rechaza una conexi�n
	 */

	private Command rechazar;
	/**
	 *  Es el Command que el usuario pulsa para volver atr�s.
	 */
	public static final Command volver = new Command("Volver", Command.BACK, 1);


	/**
	 *  Crea un nuevo objeto ServidorGUI
	 *
	 *@param  serv  servidor del que este objeto va a ser interfaz gr�fica
	 */
	public ServidorGUI(ServidorBT serv) {
		super("Env�o", List.IMPLICIT);
		setFitPolicy(Choice.TEXT_WRAP_ON);
		this.serv = serv;
		aceptar = new Command("Aceptar", Command.OK, 1);
		rechazar = new Command("Rechazar", Command.CANCEL, 1);
		addCommand(volver);
		setCommandListener(this);
	}


	/**
	 *  M�todo que cambia el aspecto de la interfaz para mostrar un mensaje de
	 *  espera de dispositivo
	 */
	public void esperandoDispositivo() {
		deleteAll();
		setTitle("Env�o");
		setTicker(new Ticker("Esperando algun dispositivo..."));
	}


	/**
	 *  M�todo que cambia la interfaz para pedir confirmaci�n sobre un intenta de
	 *  conexi�n
	 *
	 *@param  usuario  El usuario que intenta conectarse a nuestro servidor.
	 */
	public void pedirConfirmacion(String usuario) {
		deleteAll();
		setTicker(null);
		setTitle("Confirme");
		append("El usuario " + usuario + " intenta recibir las cartas.\n�Permitir env�o?", null);
		removeCommand(volver);
		addCommand(rechazar);
		addCommand(aceptar);
	}


	/**
	 *  M�todo que muestra las cartas que se han recibido finalmente.
	 *
	 *@param  recibidas  El array de c�digos de las cartas recibidas
	 *@param  todas      El array con todas las cartas que se intentaban enviar y
	 *      que usamos para mostrar la informaci�n de aquellas cartas cuyo c�digo
	 *      se ha recibido.
	 */
	public void mostrarConfirmadas(int[] recibidas, CartaMovil[] todas) {
		deleteAll();
		boolean enc;
		removeCommand(rechazar);
		setTitle("Las cartas enviadas son");
		int j;
		int nCartas = todas.length;
		for (int i = 0; i < recibidas.length; i++) {
			j = 0;
			enc = false;
			while (j < nCartas || !enc) {
				if (recibidas[i] == todas[j].getCodigoID()) {
					append((i + 1) + "� carta enviada:", null);
					append(todas[j].toShortString(), null);
					enc = true;
				}
				j++;
			}
		}
	}


	/**
	 *  M�todo que se encarga de escuchar los eventos que se produzcan en este
	 *  objeto
	 *
	 *@param  c  El Command que se ha activado
	 *@param  d  La pantalla en la que ha aparecido el command que se ha activado
	 */
	public void commandAction(Command c, Displayable d) {
		if (c == volver) {
			serv.getCommandListener().commandAction(volver, this);
		}
		else if (c == aceptar) {
			if (getTitle().startsWith("Conf")) {
				serv.listo = true;
				serv.aceptado = true;
			}
			else {
				serv.getCommandListener().commandAction(volver, this);
			}
		}
		else if (c == rechazar) {
			serv.listo = true;
			serv.aceptado = false;
		}
	}

}
