package CartasMovil;

import MazoMovil.*;
import javax.microedition.lcdui.*;
import java.util.Enumeration;

/**
 *  Esta clase proporciona los servicios necesarios para la consulta de la
 *  baraja de cartas, la selección de las cartas que elija el usuario y el
 *  envío de estas cartas por Bluetooth (a través de la clase ServidorBT).
 *
 *@author    Genesis
 */
public class ConsultorBaraja {
	/**
	 *  Este atributo guarda la interfaz gráfica que está mostrando en este
	 *  momento el Consultor.
	 */
	private ConsultorBarajaGUI gui;
	/**
	 *  Esta es la baraja que vamos a mostrar y de la que el usuario va a elegir
	 *  cartas y enviar a otro usuario.
	 */
	private BarajaMovil baraja;
	/**
	 *  En este atributo contiene el índice de la carta que se está mostrando
	 *  ahora en la pantalla modo extendido.
	 */
	private int indiceActual;
	/**
	 *  Este atributo indica si está en modo extendido o en modo contacto.
	 */
	private boolean modoExtendido;
	/**
	 *  Representa el objeto que escucha los eventos que se producen en esta
	 *  clase. Es el MIDlet.
	 */
	private CommandListener padre;
	/**
	 *  Este array tiene los códigos de las cartas que tiene la baraja para poder
	 *  hacer la búsqueda en la baraja.
	 */
	private int[] claves;
	/**
	 *  Este array indica si las cartas están o no seleccionadas. Las cartas
	 *  están en el mismo orden que en el array claves.
	 */
	private boolean[] seleccionados;
	/**
	 *  Este comando indica al CommandListener que debe actualizar el entorno
	 *  gráfico debido a un cambio de modo compacto a modo extendido.
	 */
	public static final Command ACTUALIZARGUI = new Command("Reflejar", Command.ITEM, 1);
	/**
	 *  Este comando indica al CommandListener que ha acabado el envío de las
	 *  cartas por Bluetooth
	 */
	public static final Command FINENVIO = new Command("Envío terminado", Command.ITEM, 1);


	/**
	 *  Crea un objeto de la clase ConsultorBaraja, iniciando los valores de
	 *  padre, leyendo la baraja del RMS...
	 *
	 *@param  padre  Es el CommandListener ue va a escuchar los eventos que se
	 *      produzcan en esta clase
	 */
	public ConsultorBaraja(CommandListener padre) {
		baraja = new BarajaMovil(false, null, null);
		modoExtendido = false;
		indiceActual = 0;
		gui = new ConsultorBarajaGUI(this);
		this.padre = padre;
		claves = actualizaClaves();
		gui.muestraCompacto(preparaNombres());
		gui.muestraExtendido(baraja.buscaCarta(claves[indiceActual]).toString(), seleccionados[indiceActual]);
	}


	/**
	 *  Sets the Seleccionados attribute of the ConsultorBaraja object
	 *
	 *@param  s  The new Seleccionados value
	 */
	public void setSeleccionados(boolean[] s) {
		for (int i = 0; i < seleccionados.length; i++) {
			seleccionados[i] = s[i];
		}
	}


	/**
	 *  Gets the GUI attribute of the ConsultorBaraja object
	 *
	 *@return    The GUI value
	 */
	public Displayable getGUI() {
		if (modoExtendido) {
			return gui.getUIExtendido();
		}
		else {
			return gui.getUICompacto();
		}
	}


	/**
	 *  Indica al padre que ha terminado el envío de cartas por Bluetooth o que
	 *  el usuario quiere volver atrás
	 */
	public void finalizar() {
		padre.commandAction(FINENVIO, getGUI());
		System.gc();
	}


	/**
	 *  Realiza las operaciones necesarias para cambiar de modo, cambia de modo e
	 *  indica al padre que lo ha hecho
	 */
	public void cambiarModo() {
		if (modoExtendido) {
			gui.getUICompacto().setSelectedFlags(seleccionados);
			gui.getUICompacto().setSelectedIndex(indiceActual, seleccionados[indiceActual]);
		}
		else {
			boolean[] s = new boolean[seleccionados.length];
			gui.getUICompacto().getSelectedFlags(s);
			setSeleccionados(s);
		}
		modoExtendido = !modoExtendido;
		CartaMovil c = baraja.buscaCarta(claves[indiceActual]);
		if (c.getCantidad() > 1) {
			gui.muestraExtendido(c.toString() + "\n(Repetida)", seleccionados[indiceActual]);
		}
		else {
			gui.muestraExtendido(c.toString(), seleccionados[indiceActual]);
		}
		padre.commandAction(ACTUALIZARGUI, getGUI());
	}


	/**
	 *  Realiza los cambios necesarios para pasar de una carta a la anterior en
	 *  modo extendido
	 */
	public void anterior() {
		if (indiceActual > 0) {
			indiceActual--;
			CartaMovil c = baraja.buscaCarta(claves[indiceActual]);
			if (c.getCantidad() > 1) {
				gui.muestraExtendido(c.toString() + "\n(Repetida)", seleccionados[indiceActual]);
			}
			else {
				gui.muestraExtendido(c.toString(), seleccionados[indiceActual]);
			}
		}
		else {
			padre.commandAction(PantallaModoExtendido.VIBRA, getGUI());
		}
	}


	/**
	 *  Realiza los cambios necesarios para pasar de una carta a la siguiente en
	 *  modo extendido
	 */
	public void siguiente() {
		if (indiceActual < seleccionados.length - 1) {
			indiceActual++;
			CartaMovil c = baraja.buscaCarta(claves[indiceActual]);
			if (c.getCantidad() > 1) {
				gui.muestraExtendido(c.toString() + "\n(Repetida)", seleccionados[indiceActual]);
			}
			else {
				gui.muestraExtendido(c.toString(), seleccionados[indiceActual]);
			}
		}
		else {
			padre.commandAction(PantallaModoExtendido.VIBRA, getGUI());
		}
	}


	/**
	 *  Pone a true la entrada correspondiente del array seleccionados.
	 */
	public void seleccionaActual() {
		seleccionados[indiceActual] = !seleccionados[indiceActual];
		CartaMovil c = baraja.buscaCarta(claves[indiceActual]);
		if (c.getCantidad() > 1) {
			gui.muestraExtendido(c.toString() + "\n(Repetida)", seleccionados[indiceActual]);
		}
		else {
			gui.muestraExtendido(c.toString(), seleccionados[indiceActual]);
		}
	}


	/**
	 *  Inicia el envío de las cartas seleccionadas por Bluetooth
	 */
	public void enviarCartas() {
		int i;
		int j;
		int numSelec = 0;
		for (i = 0; i < seleccionados.length; i++) {
			if (seleccionados[i]) {
				numSelec++;
			}
		}
		if (numSelec == 0) {
			Display d = ((InicioMidlet) padre).getDisplay();
			Alert a = new Alert("NINGUNA CARTA SELECCIONADA", "Por favor, elija alguna carta antes de oulsar Enviar", null, AlertType.WARNING);
			a.setTimeout(Alert.FOREVER);
			d.setCurrent(a, getGUI());
			return;
		}
		CartaMovil[] aEnviar = new CartaMovil[numSelec];
		j = 0;
		for (i = 0; i < claves.length; i++) {
			if (seleccionados[i]) {
				aEnviar[j] = baraja.buscaCarta(claves[i]);
				j++;
			}
		}
		ServidorBT servidor = new ServidorBT(padre, aEnviar);
		padre.commandAction(ACTUALIZARGUI, servidor.getGUI());
		servidor.iniciaServidor();
	}


	/**
	 *  Confirma qué cartas de las seleccionadas ha podido recibir el receptor y
	 *  las elimina de la baraja.
	 *
	 *@param  recibidas  Es un array con los códigosID de las cartas recibidas.
	 */
	public void confirmaRecibidas(int[] recibidas) {
		for (int i = 0; i < recibidas.length; i++) {
			baraja.eliminaCarta(recibidas[i], false);
		}
		;
		baraja.guardaRMS();
		claves = actualizaClaves();
		indiceActual = 0;
		gui.muestraCompacto(preparaNombres());
		CartaMovil c = baraja.buscaCarta(claves[indiceActual]);
		if (c.getCantidad() > 1) {
			gui.muestraExtendido(c.toString() + "\n(Repetida)", seleccionados[indiceActual]);
		}
		else {
			gui.muestraExtendido(c.toString(), seleccionados[indiceActual]);
		}
		System.gc();
	}


	/**
	 *  Ordena tanto al modo compacto como al modo extendido actualizar los
	 *  valores que aparecen por pantalla.
	 */
	public void actualizaGUI() {
		claves = actualizaClaves();
		gui.muestraCompacto(preparaNombres());
		gui.muestraExtendido(baraja.buscaCarta(claves[indiceActual]).toString(), seleccionados[indiceActual]);
	}


	/**
	 *  Prepara los nombres correctos que deben mostrarse en el modo compacto,
	 *  indicando si la carta está repetida o no.
	 *
	 *@return    Es el array de las cadenas que deben mostrarse en el modo
	 *      compacto.
	 */
	private String[] preparaNombres() {
		int j;
		int i;
		i = 0;
		String[] nombres = new String[baraja.getNumeroCartas()];
		Enumeration e = baraja.enumeraCartas();
		CartaMovil c;
		while (e.hasMoreElements()) {
			c = ((CartaMovil) e.nextElement());
			for (j = 0; j < c.getCantidad(); j++) {
				nombres[i] = c.toShortString();
				if (j > 0) {
					nombres[i] = nombres[i] + "\n(Repetida)";
				}
				i++;
			}
		}
		return nombres;
	}


	/**
	 *  Actualiza el array de las claves cuando se ha producido algún cambio en
	 *  la información de la baraja.
	 *
	 *@return    Es el nuevo valor de las claves.
	 */
	private int[] actualizaClaves() {
		int j;
		int i;
		i = 0;
		seleccionados = new boolean[baraja.getNumeroCartas()];
		int[] aux = new int[baraja.getNumeroCartas()];
		Enumeration e = baraja.enumeraCartas();
		CartaMovil c;
		while (e.hasMoreElements()) {
			c = ((CartaMovil) e.nextElement());
			for (j = 0; j < c.getCantidad(); j++) {
				aux[i] = c.getCodigoID();
				seleccionados[i] = false;
				i++;
			}
		}
		return aux;
	}
}
