package CartasMovil;

import javax.microedition.lcdui.*;
import MazoMovil.*;

/**
 *  Esta clase proporciona una interfaz gráfica para la clase ConsultorBaraja.
 *  <br>
 *  La interfaz tiene dos modos, compacto y extendido y permite cambiar enrte
 *  ellos y mostar opciones para volver atrás y para iniciar el envío de
 *  cartas.
 *
 *@author    Genesis
 */
public class ConsultorBarajaGUI implements CommandListener {
	/**
	 *  Esta es la lista de cartas que se muestran en modo compacto
	 */
	private List lista;
	/**
	 *  Este es el objeto ed la clase PantallaModoExtendido que muestra los datos
	 *  completos
	 */
	private PantallaModoExtendido cajaTexto;
	/**
	 *  Este Command envía la orden de volver a la pantalla inicial del Midlet
	 */
	private Command volver;
	/**
	 *  Este Command envía la orden de iniciar el envío de cartas
	 */
	private Command enviar;
	/**
	 *  Este Command envía la orden de volver de cambiar de modo compacto a
	 *  extendido y viceversa
	 */
	private Command cambiarModo;
	/**
	 *  Este atributo es la instancia de ConsultorBaraja de la que esta clase es
	 *  interfaz gráfica
	 */
	private ConsultorBaraja consultor;


	/**
	 *  Constructor de la clase. <br>
	 *  Crea un nuevo objeto de la clase ConsultorBarajaGUI, inicializando tanto
	 *  el modo compacto como el extendido y mostrando las opciones que aparecen
	 *  en ambos.
	 *
	 *@param  cons  Es el ConsultorBaraja del que este objeto es interfaz
	 *      gráfica.
	 */
	public ConsultorBarajaGUI(ConsultorBaraja cons) {
		consultor = cons;
		lista = new List("SU BARAJA", List.MULTIPLE);
		cajaTexto = new PantallaModoExtendido(this);
		volver = new Command("Volver", Command.EXIT, 1);
		enviar = new Command("Enviar", Command.SCREEN, 1);
		cambiarModo = new Command("Cambiar Vista", Command.SCREEN, 1);
		lista.addCommand(volver);
		lista.addCommand(enviar);
		lista.addCommand(cambiarModo);
		cajaTexto.addCommand(volver);
		cajaTexto.addCommand(enviar);
		cajaTexto.addCommand(cambiarModo);
		lista.setCommandListener(this);
		lista.setFitPolicy(Choice.TEXT_WRAP_ON);
		cajaTexto.setCommandListener(this);
	}


	/**
	 *  Gets the UICompacto attribute of the ConsultorBarajaGUI object
	 *
	 *@return    The UICompacto value
	 */
	public List getUICompacto() {
		return lista;
	}


	/**
	 *  Gets the UIExtendido attribute of the ConsultorBarajaGUI object
	 *
	 *@return    The UIExtendido value
	 */
	public PantallaModoExtendido getUIExtendido() {
		return cajaTexto;
	}


	/**
	 *  Inicia (o actualiza) los valores que aparecen en el modoCompacto.
	 *
	 *@param  nombres  Es el texto que debe aparecer en cada entrada del modo
	 *      compacto.
	 */
	public void muestraCompacto(String[] nombres) {
		lista.deleteAll();
		for (int i = 0; i < nombres.length; i++) {
			lista.append(nombres[i], null);
		}
	}


	/**
	 *  Inicia (o actualiza) los valores que aparecen en el modoExtendido
	 *
	 *@param  texto         Texto que debe aparecer en el modo extendido
	 *@param  seleccionada  indica si la carta está o no selecionada
	 */
	public void muestraExtendido(String texto, boolean seleccionada) {
		cajaTexto.dibuja(texto, seleccionada, true);
	}


	/**
	 *  Describe las acciones a realizar al recibir los distintos Commands.<br>
	 *  Si el Command es el de volver atrás indicamos al ConsultorBaraja que
	 *  llame a su método finalizar<br>
	 *  Si el Command es el de enviar, se crea una lista con las cartas que están
	 *  seleccionadas para el envío y se indica al consultor que inicie el envío.
	 *  <br>
	 *  Si el Command es el de cambio de modo se realizan las acciones necesarias
	 *  para cambiar el modo actual.<br>
	 *  Si el Command es alguno de los de la PantallaModoExtendido, se indica al
	 *  consultor que pase a la carta siguiente o anterior, o que seleccione la
	 *  carta actual.
	 *
	 *@param  c  El Command que se recibe
	 *@param  d  Es la pantalla en la que aparece el Command
	 */
	public void commandAction(Command c, Displayable d) {
		if (c == volver) {
			consultor.finalizar();
		}
		else if (c == enviar) {
			if (d.equals(lista)) {
				boolean[] s = new boolean[lista.size()];
				lista.getSelectedFlags(s);
				consultor.setSeleccionados(s);
			}
			consultor.enviarCartas();
		}
		else if (c == cambiarModo) {
			consultor.cambiarModo();
		}
		else if (c == PantallaModoExtendido.ANTERIOR) {
			consultor.anterior();
		}
		else if (c == PantallaModoExtendido.SIGUIENTE) {
			consultor.siguiente();
		}
		else if (c == PantallaModoExtendido.botSeleccion) {
			consultor.seleccionaActual();
		}
	}

}
