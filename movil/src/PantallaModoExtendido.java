package CartasMovil;

import javax.microedition.lcdui.*;
import java.util.*;

/**
 *  Esta clase permite mostrar el texto completo de una carta, mostrándolo si
 *  está seleccionada. Nos las apañamos para que no corte las palabras y que
 *  permita subir y bajar el texto si no cabe entera
 *
 *@author    Genesis
 */
public class PantallaModoExtendido extends Canvas {
	/**
	 *  Este es el texto que vamos a mostrar
	 */
	private String texto;

	/**
	 *  Ancho de la pantalla en pixels
	 */
	private int w;
	/**
	 *  Altura de la pantalla en pixels
	 */
	private int h;
	/**
	 *  Fuente con la que escribimos el mensaje
	 */
	private Font f;
	/**
	 *  Ancho de la fuente con la que escribimos
	 */
	private int fh;
	/**
	 *  Altura de la fuente con la que escribimos
	 */
	private int fw;

	private int x0 = 0, y0 = 0;

	/**
	 *  Número de caracteres que caben en la pantalla (a lo ancho, en una línea)
	 */
	private final int ANCHOPANTALLA;
	/**
	 *  Clase que se encarga de gestionar los eventos
	 */
	private CommandListener padre;
	/**
	 *  Vector que contiene el texto formateado en lineas distintas
	 */
	private Vector listaLineas;
	/**
	 *  Vector el nombre del tipo de fuente que lengthcorresponde a cada linea en
	 *  la pantalla (cursiva, negrita, normal)
	 */
	private String[] fuente;
	/**
	 *  Indica cual es la primera línea que mostramos por pantalla. Se actualiza
	 *  al movernos hacia arriba o hacia abajo
	 */
	private int primeraLinea;
	private boolean seleccionado, entraCompleto, topeAbajo;
	/**
	 *  Comando que se usa para indicar al padre que debe suministrarnos la carta
	 *  anterior en la baraja.
	 */
	public static final Command ANTERIOR = new Command("Anterior", Command.SCREEN, 1);
	/**
	 *  Comando que se usa para indicar al padre que debe suministrarnos la carta
	 *  siguiente en la baraja.
	 */
	public static final Command SIGUIENTE = new Command("Siguiente", Command.SCREEN, 1);
	/**
	 *  Comando que se usa para indicar al padre que debe marcar (o desmarcar,
	 *  según su estado actual) la carta que estamos mostrando.
	 */
	public static final Command botSeleccion = new Command("Seleccionado", Command.SCREEN, 1);
	/**
	 *  Comando que se usa para indicar al padre que hemos llegado al fin de la
	 *  baraja y que no se puede pasar más adelante.
	 */
	public static final Command VIBRA = new Command("Vibración", Command.SCREEN, 1);


	/**
	 *  Constructor de la clase. Inicia los valores necesarios para mostrar
	 *  correctamente las cartas en modo extendido.
	 *
	 *@param  padre  Es el objeto que se encargará de tratar los eventos que se
	 *      produzcan.
	 */
	public PantallaModoExtendido(CommandListener padre) {
		super();
		this.padre = padre;
		setCommandListener(padre);
		texto = "";
		seleccionado = false;
		ANCHOPANTALLA = (this.getWidth() / 7) + 1;
		listaLineas = new Vector();
		if (f == null) {
			f = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
			w = this.getWidth();
			h = this.getHeight();
			fh = f.getHeight();
		}
	}


	/**
	 *  Método que se encarga de dibujar el texto de la carta en la pantalla.
	 *
	 *@param  t     Texto a mostrar
	 *@param  s     Valor que indica si la carta está seleccionada.
	 *@param  cabe  Valor que indica si el texto de la carta cabe entero en la
	 *      pantalla o si tenemos que usar las flechas para desplazarnos por él.
	 *      En caso de no saber si entra o no debe ser true.
	 */
	public void dibuja(String t, boolean s, boolean cabe) {
		if (texto.compareTo(t) != 0) {
			texto = t;
			preparaTexto();
			topeAbajo = false;
		}
		entraCompleto = cabe;
		seleccionado = s;
		if (entraCompleto) {
			primeraLinea = 0;
		}
		repaint();
	}


	/**
	 *  Método que se encarga de definir las acciones a realizar al ser pulsada
	 *  una tecla en concreto.
	 *
	 *@param  key  Es la tecla que se ha pulsado
	 */
	public void keyPressed(int key) {
		if (getGameAction(key) == Canvas.UP) {
			if (!entraCompleto && primeraLinea > 0) {
				primeraLinea--;
			}
			repaint();
		}
		else if (getGameAction(key) == Canvas.DOWN) {
			if (!entraCompleto && topeAbajo) {
				primeraLinea++;
			}
			repaint();
		}
		else if (getGameAction(key) == Canvas.FIRE) {
			padre.commandAction(botSeleccion, this);
		}
		else if (getGameAction(key) == Canvas.LEFT) {
			padre.commandAction(ANTERIOR, this);
		}
		else if (getGameAction(key) == Canvas.RIGHT) {
			padre.commandAction(SIGUIENTE, this);
		}
	}


	/**
	 *  Método que se encarga de actualizar los gráficos que se están mostrando
	 *
	 *@param  g  Description of Parameter
	 */
	protected void paint(Graphics g) {
		int y = fh;
		if (!seleccionado) {
			g.setColor(255, 255, 255);
		}
		else {
			g.setColor(255, 0, 0);
		}

		g.fillRect(0, 0, w, h);
		g.setColor(0, 0, 0);
		g.setFont(f);
		topeAbajo = false;

		g.translate(-x0, -y0);
		String linea;
		for (int i = primeraLinea; i < listaLineas.size(); i++) {
			linea = ((String) listaLineas.elementAt(i));
			if (fuente[i].equals("cursiva")) {
				f = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
				g.setFont(f);
			}
			else if (fuente[i].equals("negrita")) {
				f = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
				g.setFont(f);
			}
			else {
				f = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
				g.setFont(f);
			}
			g.drawString(linea, 0, y, Graphics.BASELINE | Graphics.LEFT);
			y += fh;
		}
		if (y > h) {
			if (primeraLinea == 0) {
				entraCompleto = false;
			}
			topeAbajo = true;
		}
	}


	/**
	 *  Método que se encarga de dar formato al texto. Por una parte separa el
	 *  texto en líneas y, por otra, asigna a cada línea la fuente que le
	 *  corresponde (normal, negrita o cursiva).
	 */
	private void preparaTexto() {
		int y = fh;
		listaLineas.removeAllElements();
		String linea;
		int ultima = 0;
		int sigSalto = texto.indexOf('\n');
		while (ultima + ANCHOPANTALLA < texto.length() || (sigSalto - ultima < ANCHOPANTALLA && sigSalto != -1)) {
			if (sigSalto - ultima < ANCHOPANTALLA && sigSalto > 0) {
				listaLineas.addElement(texto.substring(ultima, sigSalto));
				ultima = sigSalto;
			}
			else {
				int sigEspacio = texto.indexOf(' ', ultima);
				int anteriorEsp = 0;
				while (sigEspacio < ultima + ANCHOPANTALLA && sigEspacio > 0 && anteriorEsp != sigEspacio) {
					anteriorEsp = sigEspacio;
					sigEspacio = texto.indexOf(' ', sigEspacio + 1);
				}
				listaLineas.addElement(texto.substring(ultima, anteriorEsp));
				ultima = anteriorEsp + 1;
			}
			y += fh;
			sigSalto = texto.indexOf('\n', ultima + 1);
		}
		listaLineas.addElement(texto.substring(ultima));
		fuente = new String[listaLineas.size()];
		boolean mitologico = false;
		for (int i = 0; i < listaLineas.size(); i++) {
			linea = ((String) listaLineas.elementAt(i));
			if (linea.startsWith("\nTexto mitológico:")) {
				mitologico = true;
			}
			else if (linea.startsWith("\nNivel:")) {
				mitologico = false;
			}
			if (linea.startsWith("\n(Repetida)")) {
				fuente[i] = "negrita";
			}
			else if (mitologico) {
				fuente[i] = "cursiva";
			}
			else {
				fuente[i] = "normal";
			}
		}

	}
}
