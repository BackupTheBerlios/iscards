package CartasMovil;

import MazoMovil.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.Enumeration;

/**
 *  Description of the Class
 *
 *@author    Genesis
 */
public class InicioMidlet extends MIDlet implements CommandListener {

	private Display display;
	private ConsultorBaraja consultor;
	private ReceptorBaraja receptor;
	private Command salir = new Command("Salir", Command.EXIT, 1);
	private List opciones;
	private BarajaMovil baraja;


	/**
	 *  Gets the Display attribute of the InicioMidlet object
	 *
	 *@return    The Display value
	 */
	public Display getDisplay() {
		return display;
	}


	/**
	 *  Gets the Opciones attribute of the InicioMidlet object
	 *
	 *@return    The Opciones value
	 */
	public List getOpciones() {
		return opciones;
	}


	/**
	 *  Description of the Method
	 */
	public void startApp() {
		display = Display.getDisplay(this);
		opciones = new List("Génesis", List.IMPLICIT);
		opciones.append("Mostrar baraja", null);
		opciones.append("Recibir cartas", null);
		opciones.addCommand(salir);
		opciones.setCommandListener(this);
		opciones.setFitPolicy(Choice.TEXT_WRAP_ON);
		display.setCurrent(opciones);
	}


	/**
	 *  Description of the Method
	 */
	public void pauseApp() {
	}


	/**
	 *  Description of the Method
	 *
	 *@param  unconditional  Description of Parameter
	 */
	public void destroyApp(boolean unconditional) {
	}


	/**
	 *  Description of the Method
	 *
	 *@param  c  Description of Parameter
	 *@param  d  Description of Parameter
	 */
	public void commandAction(Command c, Displayable d) {
		if (c.equals(ConsultorBaraja.ACTUALIZARGUI)) {
			display.setCurrent(d);
		}
		else if (c.equals(ConsultorBaraja.FINENVIO)) {
			consultor = new ConsultorBaraja(this);
			display.setCurrent(opciones);
		}
		else if (c.equals(ReceptorBaraja.FINRECEPCION)) {
			consultor = new ConsultorBaraja(this);
			consultor.actualizaGUI();
			display.setCurrent(consultor.getGUI());
		}
		else if (c.equals(PantallaModoExtendido.VIBRA)) {
			display.vibrate(100);
			display.flashBacklight(100);
		}
		else if (c == opciones.SELECT_COMMAND && d == opciones) {
			switch (opciones.getSelectedIndex()) {
				case 0:
				{
					consultor = new ConsultorBaraja(this);
					display.setCurrent(consultor.getGUI());
					break;
				}
				case 1:
				{
					receptor = new ReceptorBaraja(this, display);
					display.setCurrent(receptor);
					break;
				}
				default:
					break;
			}
		}
		else if (c == salir) {
			System.gc();
			destroyApp(false);
			notifyDestroyed();
		}
		else if (c.equals(ServidorGUI.volver)) {
			System.gc();
			display.setCurrent(consultor.getGUI());
		}
		else if (c.getLabel().equals("RECEPCION")) {
			consultor.confirmaRecibidas(((CommandIndices) c).getClaves());
		}
		else if (c.equals(ClienteGUI.volver)) {
			try {
				receptor.getCliente().cierraConexion();
			}
			catch (Exception e) {
			}
			;
			System.gc();
			display.setCurrent(opciones);
		}
		else if (c.getLabel().equals("CARTASRECIBIDAS")) {
			receptor.añadeYConfirma(((CommandCartas) c).getCartas());
		}
		else if (c == receptor.SELECT_COMMAND && d == receptor) {
			if (receptor.getSelectedIndex() == 0) {
				receptor.recibir();
			}
			else {
				Alert alerta = new Alert("EN CONSTRUCCIÓN", "Esta parte de la aplicación pertenece a la próxima iteración", null, AlertType.INFO);
				alerta.setTimeout(Alert.FOREVER);
				display.setCurrent(alerta, display.getCurrent());
			}
		}
		else if (d == receptor && c.getLabel().equals("Volver")) {
			System.gc();
			display.setCurrent(opciones);
		}
	}
}
