package motorJuego;

import coleccion.Coleccion;
import eventos.*;
import cartas.*;
import ia_cartas.*;
import interfaz.*;
import panelesInfo.PanelGenerico;
//////import audio.*;
import login.*;

import java.awt.*;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;
import java.lang.Thread;

import java.io.FileInputStream;

import javax.swing.*;

/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: Juego de cartas de Rol</p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Pedro Antonio y Rui Miguel
 *@version    1.0
 */

public class CPartida implements Partida {
	/**
	 *  variable que indica que jugador está en su turno de partida
	 */
	String turnoJugador;

	/**
	 *  variable que indica en que fase del turno se encuentra el jugador actual
	 */
	int turnoPartida;

	/**
	 *  Variable para almacenar la mesa de juego
	 */
	CMesa mesa;

	/**
	 *  Variable para almacenar la mano del jugador
	 */
	CMano mano;

	/**
	 *  Variable para almacenar la mano del jugador contrario
	 */
	CMano mano2;

	/**
	 *  Variable para almacenar el mazo del jugador
	 */
	CMazo mazo;

	/**
	 *  Variable para almacenar el mazo del contrario
	 */
	CMazo mazo2;

	/**
	 *  Variable para almacenar el mazo del cementerio del jugador
	 */
	CMazo cementerio;

	/**
	 *  Variable para almacenar el mazo del cementerio del contrario
	 */
	CMazo cementerio2;

	/**
	 *  Variable para saber si se esta defendiendo y falta seleccionar la
	 *  criatura a defender
	 */
	boolean seleccionandoDefensa = false;

	/**
	 *  Variable para que carta esta defendiendo
	 */
	CACarta cartaSeleccionadaDefensa;


	/**
	 *  Variable para almacenar las criaturas que pueden defender
	 */
	public Vector vectorCriaturasDefensa = new Vector();

	/**
	 *  Variable para almacenar las criaturas que estan atacando
	 */
	public Vector vectorCriaturasAtaque = new Vector();

	/**
	 *  Variable para almacenar el número de criaturas que estan defendiendo
	 */
	public int numCriaturasDefendiendo = 0;


	private Interfaz inter;
	private boolean finPartida = false;
	private Thread hiloJuego;

	/**
	 *  version de las cartas de las barajas del usuario
	 */
	private int version;

	/**
	 *  raza de la carta 0=Angeles, 1=Demonios, 2=Humanos, 3=Inmortales
	 */
	private int raza;

	/**
	 *  coleccion.car con la colección de todas las cartas del juego
	 */
	private Coleccion coleccion;

	/**
	 *  tabla Hash con las cartas disponibles de la baraja
	 */
	private Hashtable enfrentamientos;

	private Inteligencia enemigo;

	private boolean manaCargado;
	
	private final Color[] arrayColores=new Color[7];
	
	private int ultimoColor;
	
	private Vector vectorBajadas;
	


//  CGestor gestor;

	/**
	 *  Constructora de la clase
	 *
	 *@param  nombreBaraja  Description of Parameter
	 *@param  c             Description of Parameter
	 */
	public CPartida(String nombreBaraja,String nombreBaraja2, Coleccion c) {

		enfrentamientos = new Hashtable();

		enemigo = new Inteligencia();
		coleccion = c;
		//cargamos la baraja seleccionada
		mazo = cargarBarajaJuego(nombreBaraja);
		
		/*Provisional para no jugar siempre con el mismo
		 */
		 int indexBaraja=new Double(Math.random() * 4).intValue();
		 switch (indexBaraja) {
		 	case 0:
	     	 mazo2 = cargarBarajaJuego("angeles_usuario");		 	
		 	 break;
		 	case 1:
	     	 mazo2 = cargarBarajaJuego("demonios_usuario");		 	
		 	 break;
		 	case 2:
	     	 mazo2 = cargarBarajaJuego("humanos_usuario");
		 	break;
		 	default :
	     	 mazo2 = cargarBarajaJuego("inmortales_usuario");
		 	
		 }
		/*Fin de lo provisional
		 */
		 
		//barajeamos el mazo
		this.barajearMazo(mazo);
		this.barajearMazo(mazo2);

		cementerio = new CMazo();
		cementerio2 = new CMazo();

		mano = iniciarMano(mazo);
		mano2 = iniciarMano(mazo2);

		//creamos la mesa de juego
		mesa = new CMesa();

		//inicializamos el tablero con las cartas iniciales (5 cartas aleatorias de nivel3...)
		this.inicializarTablero();

		turnoJugador = "jugador1";
		turnoPartida = 0;
		//aun no hemos comenzado
		ultimoColor=0;
		
		arrayColores[0]=Color.ORANGE;
		arrayColores[1]=Color.PINK;
		arrayColores[2]=Color.WHITE;
		arrayColores[3]=Color.YELLOW;
		arrayColores[4]=Color.CYAN;
		arrayColores[5]=Color.MAGENTA;
		arrayColores[6]=Color.GREEN;

		//le pongo mucho mana,porque aun no se utiliza!!!
		mesa.getJugador2().setManaDisponible(0);
		mesa.getJugador1().setManaDisponible(0);
		mesa.getJugador2().setManaUsado(0);
		mesa.getJugador1().setManaUsado(0);
		
		vectorBajadas=new Vector();

		hiloJuego = new Thread(this);
		hiloJuego.setPriority(Thread.MIN_PRIORITY);
		hiloJuego.start();

	}





	/**
	 *  Sets the Interfaz attribute of the CPartida object
	 *
	 *@param  i  The new Interfaz value
	 */
	public void setInterfaz(Interfaz i) {

		inter = i;
	}

	public void setSeleccionandoDefensa(boolean b){
		seleccionandoDefensa=b;
	}


	public void setCartaSeleccionadaDefensa(CACarta c){
		cartaSeleccionadaDefensa=c;
	}

	/**
	 *  Función que devuelve la mano del jugador
	 *
	 *@return
	 */
	public CMano getMano() {
		return mano;
	}


	/**
	 *  Función que devuelve la mano del contrario
	 *
	 *@return
	 */
	public CMano getMano2() {
		return mano2;
	}


	/**
	 *  Función que devuelve el mazo del jugador
	 *
	 *@return
	 */
	public CMazo getMazo() {
		return mazo;
	}


	/**
	 *  Función que devuelve el mazo del contrario
	 *
	 *@return
	 */
	public CMazo getMazo2() {
		return mazo2;
	}


	/**
	 *  Gets the Cementerio attribute of the CPartida object
	 *
	 *@return    The Cementerio value
	 */
	public CMazo getCementerio() {
		return cementerio;
	}


	/**
	 *  Función que devuelve si se esta seleccionando la defensa de una criatura
	 *
	 *@return
	 */
	public CMesa getMesa() {
		return mesa;
	}


	/**
	 *  Función que devuelve la posicion en el vector de las criaturas, -1 si no
	 *  pertenece o no esta
	 *
	 *@param  carta               Description of Parameter
	 *@param  perteneceContrario  Description of Parameter
	 *@return
	 */
	public int getPosicionCriatura(CACarta carta, boolean perteneceContrario) {
		if (perteneceContrario) {
			//esta en la mesa del contrario
			return mesa.getJugador2().getVectorCriaturas().indexOf(carta);
		}
		else {
			//esta en nuestra mesa
			return mesa.getJugador1().getVectorCriaturas().indexOf(carta);
		}
	}


	/**
	 *  Función que devuelve la mano del jugador
	 *
	 *@return
	 */
	public int getTurnoPartida() {
		return turnoPartida;
	}


	/**
	 *  Función que devuelve la mano del jugador
	 *
	 *@return
	 */
	public String getTurnoJugador() {
		return turnoJugador;
	}


	/**
	 *  Función que devuelve el vector con las crituras que pueden defender
	 *
	 *@return
	 */
	public Vector getVectorCriaturasDefensa() {
		return vectorCriaturasDefensa;
	}


	/**
	 *  Función que devuelve el vector con las crituras que pueden defender
	 *
	 *@return
	 */
	public Vector getVectorCriaturasAtaque() {
		return vectorCriaturasAtaque;
	}


	/**
	 *  Función que devuelve el numero de criaturas que estan defendiendo a otras
	 *
	 *@return
	 */
	public int getNumCriaturasDefendiendo() {
		return numCriaturasDefendiendo;
	}

	/**
	 *  Función que devuelve si se esta seleccionando la defensa de una criatura
	 *
	 *@return
	 */
	public boolean getSeleccionandoDefensa() {
		return seleccionandoDefensa;
	}

	/**
	 *  Función que devuelve si se esta seleccionando la defensa de una criatura
	 *
	 *@return
	 */
	public CACarta getCartaSeleccionadaDefensa() {
		return cartaSeleccionadaDefensa;
	}



	/**
	 *  Función que devuelve si se esta seleccionando la defensa de una criatura
	 *
	 *@return
	 */
	public Hashtable getEnfrentamientos() {
		return enfrentamientos;
	}


	/**
	 *  Gets the Hilo attribute of the CPartida object
	 *
	 *@return    The Hilo value
	 */
	public Thread getHilo() {

		return hiloJuego;
	}


	/**
	 *  Main processing method for the CPartida object
	 */
	public void run() {

		/*
		 *  Bucle principal del juego. Controla los turnos en que se divide una partida
		 *  1.Enderezar 2.Robar 3.Bajar 4.Ataque/Defensa
		 */
//comenzamos la partida

		while (!finPartida) {
			System.gc();
			System.runFinalization();
			if (turnoJugador == "jugador1") {

				try {
					//hay que añadir los turnos de ataque

					switch (turnoPartida) {
						case 0:
							endereza("jugador1");
							actualizaYPasaTurno("jugador1");
							break;
						case 1:
							roba("jugador1");
							break;
						case 2:
							baja();
							//this.wait(); no se duerme :-P
							break;
						case 3:
							quitaColores();
							ataque();
							//hiloJuego.sleep(1000);
							break;
						case 4:
							turnoJugador = "jugador2";
							pasaTurnoPartida("jugador2");
							break;
						case 5:
							enfrentamientos.clear();
							if (finPartida){
								inter.inhabilitaPanel();
								inter.getContentPane().add(new PanelGenerico("../imagenes/HasPerdido.jpg",inter),0);
								inter.repaint();
    	                     }
							ponEfectoGiradas("jugador1");
							defensa();
							turnoJugador = "jugador1";
							pasaTurnoPartida("jugador1");
							if (finPartida){
								inter.inhabilitaPanel();
								inter.getContentPane().add(new PanelGenerico("../imagenes/HasPerdido.jpg",inter),0);
								inter.repaint();
    	                     }
							ponEfectoGiradas("jugador2");
							break;
					}
				}
				catch (Exception e) {

				}
			}
			else {
				//turno enemigo
				try {

					Vector movimientos;
					switch (turnoPartida) {
						case 0:
							endereza("jugador2");
							actualizaYPasaTurno("jugador2");
							break;
						case 1:
							roba("jugador2");
							break;
						case 2:
						{
							movimientos = enemigo.Soluciona(this, 3, 0, null, 0);
							//pasaTurnoPartida("jugador2");;
							for (int i = 0; i < movimientos.size(); i++) {
								this.actualizaPorEvento((Eventos) movimientos.get(i));
							}							
						}
							break;
						case 3:
							quitaColores();						
							movimientos = enemigo.Soluciona(this, 4, 0, null, 0);
							for (int i = 0; i < movimientos.size(); i++) {
								this.actualizaPorEvento((Eventos) movimientos.get(i));
							}
							break;
						case 4:
							turnoJugador = "jugador1";
							turnoPartida = 5;
							break;
						case 5:
							enfrentamientos.clear();
							int turnoDefensa = 1;
							ponEfectoGiradas("jugador2");

							Vector atacantesDefendidos = new Vector();
							movimientos = enemigo.Soluciona(this, 5, 0, atacantesDefendidos, turnoDefensa);
							/*
							 *  for (int i = 0; i < movimientos.size()-1; i++) {
							 *  this.actualizaPorEvento( (Eventos) movimientos.get(i));
							 *  }
							 *  resuelveEnfrentamiento();
							 */
							//realizara 0 movimientos cuando la inteligencia las espiche
							while ((movimientos.size() - 1 != 0) && !finPartida) {
								enfrentamientos.clear();
								/*
								 *  sacamos los atacantes que han sido defendidos, va hasta el
								 *  size-1 ya que el ultimo movimiento es un evento de cambiar turno
								 */
								for (int i = 0; i < movimientos.size() - 1; i++) {
									//almacena los atacnates defendidos en este turno
									//con las posiciones es suficiente
									CCriatura criatura = (CCriatura) (this.mesa.getJugador1().getVectorCriaturas().elementAt(Integer.parseInt(((EventoDefensa) movimientos.elementAt(i)).getPosicion())));
									atacantesDefendidos.addElement(criatura);
								}
								turnoDefensa++;
								if (movimientos.size() == 0) {
									finPartida = true;
								}
								for (int i = 0; i < movimientos.size() - 1; i++) {
									this.actualizaPorEvento((Eventos) movimientos.get(i));
								}
								resuelveEnfrentamiento();
								movimientos = enemigo.Soluciona(this, 5, 0, atacantesDefendidos, turnoDefensa);
								ponEfectoGiradas("jugador2");								
							}

							/*
							 *  jeje, pasamos a mano de turno solo cuando se han tratado todos
							 *  los movimientos de defensa de todos los turnos
							 */
								this.actualizaPorEvento((Eventos) movimientos.get(movimientos.size() - 1));
								if (finPartida){
									inter.inhabilitaPanel();
									inter.getContentPane().add(new PanelGenerico("../imagenes/HasGanado.jpg",inter),0);
									inter.repaint();
	    	                     }
								
					}
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
		}
	}


	/**
	 *  Función que actualiza el numero de criaturas que estan defendiendo a
	 *  otras
	 *
	 */
	public void añadeUnoNumCriaturasDefendiendo() {
		numCriaturasDefendiendo++;
	}


	/**
	 *  Función que actualiza el numero de criaturas que estan defendiendo a
	 *  otras
	 *
	 */
	public void quitaUnoNumCriaturasDefendiendo() {
		numCriaturasDefendiendo--;
	}


	/**
	 *  Función que añade una carta al cementerio
	 *
	 *@param  carta
	 */
	public void añadeCementerio(CACarta carta) {
		cementerio.anadeCarta(carta);
	}


	/**
	 *  Funcion que actualiza el estado de la partida mediante un evento que
	 *  recibe y que puede estar originado por el otro jugador o por la
	 *  inteligencia artificial del juego
	 *
	 *@param  e  Eventos
	 */
	public void actualizaPorEvento(Eventos e) {
		if (e.getTipoEvento() == "bajada") {
			EventoBajada eb = (EventoBajada) e;
			//copiamos la carta que estaba en la mano
			CACarta c = (CACarta) (mano2.getCartas().elementAt(Integer.parseInt(eb.getPosicion())));
			//eliminamos la carta de la mano
			mano2.getCartas().removeElementAt(Integer.parseInt(eb.getPosicion()));
			//bajamos la carta a la mesa y la colocaremos en su vector correspondiente
			/*
			 *  EL PUTO RUY QUIERE QUE CAMBIE ESTO CON EL JODIDO "ID_TIPO", VERLO
			 */
			if (c instanceof CCriatura) {
				c.baja();

				mesa.getJugador2().getVectorCriaturas().addElement(c);
			}
			else if (c instanceof CHechizo) {
				mesa.getJugador2().getVectorHechizos().addElement(c);
			}
			else if (c instanceof CConjuro) {
				mesa.getJugador2().getVectorConjuros().addElement(c);
			}
		}
		else if (e.getTipoEvento() == "ataque") {
			EventoAtaque ea = (EventoAtaque) e;
			/*
			 *  cambiamos el estado; de enderezada a girada o de girada a enderezada
			 */
			/*
			 *  LOS HECHIZOS Y CONJUROS DE MOMENTO NO TIENEN LA OPCION DE GIRARSE
			 */
			boolean estadoant = ((CACarta) (mesa.getJugador2().getVectorCriaturas().elementAt(Integer.parseInt(ea.getPosicion())))).getEstado();
			((CACarta) (mesa.getJugador2().getVectorCriaturas().elementAt(Integer.parseInt(ea.getPosicion())))).getGrafico().rota();
		}
		else if (e.getTipoEvento() == "defensa") {
			Color c;
			/*
			 *  para no repetir colores he intentar que se utilicen colores que no
			 *  molesten con el fondo de las pantallas hemos incluido estas comparaciones
			 */
			if (((EventoDefensa) e).getPosicion().equals("0")) {
				c = Color.orange;
			}
			else if (((EventoDefensa) e).getPosicion().equals("1")) {
				c = Color.pink;
			}
			else if (((EventoDefensa) e).getPosicion().equals("2")) {
				c = Color.white;
			}
			else if (((EventoDefensa) e).getPosicion().equals("3")) {
				c = Color.yellow;
			}
			else if (((EventoDefensa) e).getPosicion().equals("4")) {
				c = Color.cyan;
			}
			else if (((EventoDefensa) e).getPosicion().equals("5")) {
				c = Color.gray;
			}
			else if (((EventoDefensa) e).getPosicion().equals("6")) {
				c = Color.magenta;
			}
			//sino forzamos a que sea la 8ªposicion del vector
			else {
				c = Color.green;
			}
			/*
			 *  una vez seleccionado el color se los incluimos a las 2 cartas para
			 *  emparejar atacante/defensor
			 */
			 
			 CCriatura cartaInteligencia=((CCriatura) (mesa.getJugador2().getVectorCriaturas().elementAt(Integer.parseInt(((EventoDefensa) e).getPosicion2()))));

			//atacante en la mano del jugador 1
			((CCriatura) (mesa.getJugador1().getVectorCriaturas().elementAt(Integer.parseInt(((EventoDefensa) e).getPosicion())))).setColor(c);
			//defensor en la mano del jugador 2
			cartaInteligencia.setColor(c);
			enfrentamientos.put(mesa.getJugador2().getVectorCriaturas().elementAt(Integer.parseInt(((EventoDefensa) e).getPosicion2())),
			mesa.getJugador1().getVectorCriaturas().elementAt(Integer.parseInt(((EventoDefensa) e).getPosicion())));
			if (cartaInteligencia.getEstado())
				cartaInteligencia.getGrafico().rota();
			
		}
		else if (e.getTipoEvento() == "cambio turno") {
			/*
			 *  OBS TODO ESTO ESTA HECHO CON LOS TURNOS PROVISIONALES Y HAY QUE HABLAR
			 *  CON TONY PARA CONFIRMAR
			 */
			/*
			 *  si recibe un evento de cambio de turno pasara la partida al turno
			 *  correspondiente, que sera el siguiente por defecto
			 */
			pasaTurnoPartida("jugador2");
		}

		inter.repaint();
	}


	/**
	 *  Description of the Method
	 *
	 *@param  jug  Description of Parameter
	 */
	public synchronized void pasaTurnoPartida(String jug) {
		/*
		 *  Turnos
		 *  0=>robar;
		 *  1=>enderezar + mana
		 *  2=>bajar
		 *  3=>ataque
		 *  4=>cambio de jugador
		 *  5=>defensa + cambio
		 */
		this.turnoPartida = (this.turnoPartida + 1) % 6;
		/*
		 *  y despues de fijar el tunos pertinente y el jugador acctualiza el
		 *  texto en la interfaz
		 */
		String turno = "";
		switch (turnoPartida) {
			case 0:
				turno = "enderezar";
				break;
			case 1:
				turno = "robar";				
				break;
			case 2:
				turno = "bajar";
				break;
			case 3:
				this.notify();
				turno = "ataque";
				break;
			case 4:
				this.notify();
				turno = "defensa";
				//usuario
				break;
			case 5:
				turno = "defensa";
				//maquina o contrario
				break;
		}
		if (turnoPartida==4||turnoPartida==5){
			if (turnoPartida==4)
				inter.setTextoTurno("Turno de " + turno + " del jugador1");
			else
				inter.setTextoTurno("Turno de " + turno + " del jugador2");
		}			
		else //mostramos el texto del turno
			inter.setTextoTurno("Turno de " + turno + " del " + this.turnoJugador);
	}

	/**
	 *  Description of the Method
	 */
	private void resuelveEnfrentamiento() {
		Enumeration enumer = enfrentamientos.keys();
		while (enumer.hasMoreElements()) {
			CCriatura cartaEnDefensa = (CCriatura) (CACarta) enumer.nextElement();
			CCriatura cartaEnAtaque = (CCriatura) (CACarta) enfrentamientos.get(cartaEnDefensa);
			int ataqueA = cartaEnAtaque.getAtaque();
			int defensaA = cartaEnAtaque.getDefensa();
			int ataqueD = cartaEnDefensa.getAtaque();
			int defensaD = cartaEnDefensa.getDefensa();
			boolean muereCartaEnAtaque = false;
			boolean muereCartaEnDefensa = false;
			

			if (ataqueA > defensaD * 3) {
				muereCartaEnDefensa = !cartaEnDefensa.restaVida(3);
			}
			else if (ataqueA > defensaD * 2) {
				muereCartaEnDefensa = !cartaEnDefensa.restaVida(2);
			}
			else if (ataqueA > defensaD) {
				muereCartaEnDefensa = !cartaEnDefensa.restaVida(1);
			}
			if (ataqueD > defensaA * 3) {
				muereCartaEnAtaque = !cartaEnAtaque.restaVida(3);
			}
			else if (ataqueD > defensaA * 2) {
				muereCartaEnAtaque = !cartaEnAtaque.restaVida(2);
			}
			else if (ataqueD > defensaA) {
				muereCartaEnAtaque = !cartaEnAtaque.restaVida(1);
			}
			if (muereCartaEnDefensa) {
/*				if (PanelVolumen.getEfectosActivados())
					LoginImp.setGestorAudio(new GestorAudio("efecto","implosion2.wav"));*/

				int posCartaEnDefensa = getPosicionCriatura(cartaEnDefensa, false);
				if (posCartaEnDefensa >= 0) {
					//esta en nuestra mesa
					cementerio.anadeCarta(cartaEnDefensa);
					mesa.getJugador1().getVectorCriaturas().remove(posCartaEnDefensa);
					inter.repaint();
				}
				else {
					//esta en la mesa del contrario
					posCartaEnDefensa = getPosicionCriatura(cartaEnDefensa, true);
					cementerio2.anadeCarta(cartaEnDefensa);
					mesa.getJugador2().getVectorCriaturas().remove(posCartaEnDefensa);
					inter.repaint();
				}
			}
			if (muereCartaEnAtaque) {
/*				if (PanelVolumen.getEfectosActivados())
					LoginImp.setGestorAudio(new GestorAudio("efecto","muerte_humano0.wav"));*/
				int posCartaEnAtaque = getPosicionCriatura(cartaEnAtaque, false);
				if (posCartaEnAtaque >= 0) {
					//esta en nuestra mesa
					cementerio.anadeCarta(cartaEnAtaque);
					mesa.getJugador1().getVectorCriaturas().remove(posCartaEnAtaque);
					inter.repaint();
				}
				else {
					//esta en la mesa del contrario
					posCartaEnAtaque = getPosicionCriatura(cartaEnAtaque, true);
					cementerio2.anadeCarta(cartaEnAtaque);
					mesa.getJugador2().getVectorCriaturas().remove(posCartaEnAtaque);
					inter.repaint();
				}
			}
			try{
				this.hiloJuego.sleep(2000);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}


//++++++++++++
	/**
	 *  Description of the Method
	 *
	 *@param  jug  Description of Parameter
	 */
	private void endereza(String jug) {
		//endereza las cartas del jugador 1
		if (jug.equals("jugador1")) {
			Vector v = this.getMesa().getJugador1().getVectorCriaturas();
			if ((v != null) && (v.size() != 0)) {

				for (int i = 0; i < v.size(); i++) {
					CACarta carta = (CACarta) v.get(i);
					if (carta.getEstado() == false) {
						carta.getGrafico().rota();
					}
				}
			}
			int manaAntiguo = this.getMesa().getJugador1().getManaDisponible();
			int manaAntiguoUsado = this.getMesa().getJugador1().getManaUsado();
			this.getMesa().getJugador1().setManaDisponible(manaAntiguo + manaAntiguoUsado);
			this.getMesa().getJugador1().setManaUsado(0);
		}

		//esto endereza las cartas del jugador 2
		else if (jug.equals("jugador2")) {
			Vector v = this.getMesa().getJugador2().getVectorCriaturas();
			if ((v != null) && (v.size() != 0)) {

				for (int i = 0; i < v.size(); i++) {
					CACarta carta = (CACarta) v.get(i);
					if (carta.getEstado() == false) {
						carta.getGrafico().rota();
					}
				}
			}
			int manaAntiguo = this.getMesa().getJugador2().getManaDisponible();
			int manaAntiguoUsado = this.getMesa().getJugador2().getManaUsado();
			this.getMesa().getJugador2().setManaDisponible(manaAntiguo + manaAntiguoUsado);
			this.getMesa().getJugador2().setManaUsado(0);
		}
		ponEfectoGiradas(jug);
	}


	private synchronized void baja(){
		vectorBajadas.clear();		
		try{
			wait();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	  //espera a que baje y luego
	  //pasa de turno si no se pueden bajar mas
	}


	/**
	 *  if{ Función que lee de un archivo una cantidad de carácteres pedida
	 *
	 *@param  longitud              cantidad de carácteres que hay que leer
	 *@param  archivo               archivo del que hay que leer
	 *@return                       String con los datos leidos
	 *@exception  Exception         Description of Exception
	 *@throws  java.lang.Exception
	 */
	private byte[] leerFrase(int longitud, FileInputStream archivo) throws Exception {
		int bytes;
		int i = 0;
		byte[] frase = new byte[longitud];
		while (i < longitud) {
			bytes = archivo.read();
			if (bytes == -1) {
				//i==-1 es fin de fichero
				throw new Exception("La base de datos esta incompleta");
			}
			frase[i] = (byte) bytes;
			i++;
		}
		return frase;
	}


	/**
	 *  Función que descodifica los bytes leidos
	 *
	 *@param  fraseBytes  bytes leidos
	 *@return             String frase descodificada
	 */
	private String descodificar(byte[] fraseBytes) {
		String frase = "";
		int i = 0;
		while (i < fraseBytes.length) {
			char a;
			if (fraseBytes[i] < 0) {
				//carácter espepecial (ñ, á, é, í, ó, ú, ...)
				a = (char) (256 + fraseBytes[i] + 2);
			}
			else {
				a = (char) (fraseBytes[i] + 2);
			}
			frase = frase + a;
			i++;
		}
		return frase;
	}


	/**
	 *  Función que carga una baraja determinada del usuario
	 *
	 *@param  fichBaraja
	 *@return             Description of the Returned Value
	 */
	private CMazo cargarBarajaJuego(String fichBaraja) {
		CMazo maz = new CMazo();
		try {
			FileInputStream archivo1 = new FileInputStream("../barajas/" + fichBaraja + ".bar");
			//variable para controlar los bytes que se deben leer
			int numeroDeBytesALeer = archivo1.read();
			//esto no sería necesario ya que el nombre ya lo sabemos, se puede controlar que coincidan por si hay error
			String nombreUsuario = descodificar(leerFrase(numeroDeBytesALeer, archivo1));
			version = archivo1.read();
			//leemos el nombre de la raza
			numeroDeBytesALeer = archivo1.read();
			String razaNom = descodificar(leerFrase(numeroDeBytesALeer, archivo1));
			if (razaNom.equals("Ángeles")) {
				raza = 0;
			}
			else if (razaNom.equals("Demonios")) {
				raza = 1;
			}
			else if (razaNom.equals("Humanos")) {
				raza = 2;
			}
			else if (razaNom.equals("Inmortales")) {
				raza = 3;
			}
			numeroDeBytesALeer = archivo1.read();
			maz = new CMazo();
			String cantidad,codigoCarta,nombre;
			CACarta c,carta;
			int repeticiones;
			while (numeroDeBytesALeer >= 0) {
				// i==-1 es fin de fichero
				cantidad = descodificar(leerFrase(numeroDeBytesALeer, archivo1));
				numeroDeBytesALeer = archivo1.read();
				codigoCarta = descodificar(leerFrase(numeroDeBytesALeer, archivo1));				
				carta=coleccion.pedirCarta(codigoCarta);
				repeticiones=new Integer(cantidad).intValue();
				
				for (int i = 1; i <= repeticiones; i++) {
					c = carta.dameUnaCopia();
					maz.anadeCarta(c);
				}
				numeroDeBytesALeer = archivo1.read();
			}
			
			archivo1.close();
			System.gc();
			System.runFinalization();
		}
		catch (Exception error) {
			//mostramos con un JOptionPane el error producido
			JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error cargar baraja",
					JOptionPane.ERROR_MESSAGE);
		}
		return maz;
	}


	/**
	 *  Función que barajea el mazo del jugador
	 *
	 *@param  m  mazo a barajear
	 */
	private void barajearMazo(CMazo m) {
		CMazo mazoAux = m;
		//cogemos las cartas aleatorias del mazo
		int indiceAleatorio;
		int cant = m.getCartas().size();
		for (int i = 0; i < cant; i++) {
			indiceAleatorio = new Double(Math.random() * m.getCartas().size()).intValue();
			CACarta carta = (CACarta) m.getCartas().get(indiceAleatorio);
			m.getCartas().remove(indiceAleatorio);
			mazoAux.anadeCarta(carta);
		}
		m = mazoAux;
	}


	/**
	 *  Función que inicializa la mano del jugador, cogiendo 8 cartas para
	 *  empezar
	 *
	 *@param  m  mazo del que se roban las cartas
	 *@return    Description of the Returned Value
	 */
	private CMano iniciarMano(CMazo m) {
		//cogemos 8 cartas del mazo para la mano del jugador
		CMano hand = new CMano();
		if (m != null) {
			hand.roba(m);
		}
		return hand;
	}


	/**
	 *  Description of the Method
	 */
	private void inicializarTablero() {
		int i = 0;
		int j = 0;
		//iniciamos la mesa solamente 5 cartas (si el mazo no tiene suficientes cartas dejamos de buscar)
		while (i < 5 && j <= mazo.getCartas().size()) {
			//cogemos cartas del mazo aleatoriamente
			CACarta carta = (CACarta) mazo.getCartas().get(j);
			// pero solo las de nivel 3
			if (carta.getNivel() == 3 && carta.getIdTipo().equals("Criatura")) {
				carta.baja();
				mesa.getJugador1().getVectorCriaturas().add(carta);
				mazo.getCartas().remove(j);
				i++;
			}
			j++;
		}
		i = 0;
		j = 0;
		//ponemos 5 cartas en mesa del jugador pc
		while (i < 5 && j <= mazo2.getCartas().size()) {
			//cogemos cartas del mazo aleatoriamente
			CACarta carta = (CACarta) mazo2.getCartas().get(j);
			// pero solo las de nivel 3
			if (carta.getNivel() == 3 && carta.getIdTipo().equals("Criatura")) {
				carta.baja();
				mesa.getJugador2().getVectorCriaturas().add(carta);
				mazo2.getCartas().remove(j);
				i++;
			}
			j++;
		}
	}


	/**
	 *  Función que roba una carta del mazo
	 *
	 *@param  jug  Description of Parameter
	 */
	private synchronized void roba(String jug) {
		if (jug.equals("jugador1")) {
			if (mano.getCartas().size()==8){
				pasaTurnoPartida("jugador1");
			}
			else {
				try{
					wait();
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		else {
			while (mano2.getCartas().size() < 8) {
				mano2.roba(mazo2);
			}
			pasaTurnoPartida("jugador2");
		}
	}


	/**
	 *  Función que manda atacar a la carta que se le pasa como parámetro
	 *
	 */
	private synchronized void ataque() {
		try{
			wait();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		//mandar cartas que quedan por defender mediante eventoAtaque (al girar)
		//nos devuelve la asignacion de la defensa mediante eventoDefensa (asociandoCartas al girar)
		//mostramos asignaciones



		//resolucion (y mandar los resultados)
	}


	/**
	 *  Función que manda atacar a la carta que se le pasa como parámetro
	 *
	 */
	private void defensa() {
		ultimoColor=0;
		vectorCriaturasAtaque = new Vector();
		for (int i = 0; i < mesa.getJugador2().getVectorCriaturas().size(); i++) {
			//miramos todas las cartas nuestras con las que atacamos
			if (!((CACarta) mesa.getJugador2().getVectorCriaturas().get(i)).
					getEstado()) {
				vectorCriaturasAtaque.add(mesa.getJugador2().getVectorCriaturas().get(i));
			}
		}
		numCriaturasDefendiendo = 0;
		int cartasEnMesa=0;
		int ronda = 1;
		while ((vectorCriaturasAtaque.size()) > 0 && (!finPartida)) {
			ponEfectoGiradas("jugador1");
			vectorCriaturasDefensa = new Vector();
			enfrentamientos.clear();
			if (mesa.getJugador1().getVectorCriaturas().size() == 0) {
				finPartida = true;
			}
			else {
				if (ronda == 1) {

					for (int i = 0; i < mesa.getJugador1().getVectorCriaturas().size(); i++) {
						if (((CACarta) mesa.getJugador1().getVectorCriaturas().get(i)).getEstado()) {
							vectorCriaturasDefensa.add(mesa.getJugador1().getVectorCriaturas().get(i));
						}
					}
				}
				if (vectorCriaturasDefensa.size() != 0) {
					//defendemos con las enderezadas
					empareja();
					ronda++;

				}
				else {
					for (int i = 0; i < mesa.getJugador1().getVectorCriaturas().size(); i++) {
						vectorCriaturasDefensa.add(mesa.getJugador1().getVectorCriaturas().get(i));
					}
					
					empareja();

				}
//////////////////////////////////////////////
				resuelveEnfrentamiento();
			}
		}
	}


	/**
	 *  Description of the Method
	 *
	 *@param  jug  Description of Parameter
	 */
	private void actualizaMana(String jug) {
		if (jug.equals("jugador1")) {
			int nuevoMana = this.getMesa().getJugador1().getManaDisponible();
			if (!manaCargado) {
				int manaExtra = new Double(Math.random() * 3).intValue();
				nuevoMana = nuevoMana + manaExtra;
				manaCargado = true;
			}

			this.getMesa().getJugador1().setManaDisponible(nuevoMana);
			/*
			 *  ademas debemos actualizar el texto en la parte grafica
			 */
			inter.setTextoManas("Mana Disponible " + nuevoMana);
			inter.setTextoManasGastados("Mana Gastado 0");

		}
		else {
			int nuevoMana = this.getMesa().getJugador2().getManaDisponible();
			if (!manaCargado) {
				int manaExtra = new Double(Math.random() * 3).intValue();
				nuevoMana = nuevoMana + manaExtra;
				manaCargado = true;
			}
			this.getMesa().getJugador2().setManaDisponible(nuevoMana);
		}

	}


	/**
	 *  Description of the Method
	 *
	 *@param  jug  Description of Parameter
	 */
	private void actualizaYPasaTurno(String jug) {

		actualizaMana(jug);
		pasaTurnoPartida(jug);
		manaCargado = false;
	}
		
	public void finalizaPartida(){
		inter.botonSalir_actionPerformed(null);
		System.gc();
		System.runFinalization();
	}

	private synchronized void empareja(){
		try{
			this.wait();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		/*while (vectorCriaturasDefensa.size() > numCriaturasDefendiendo && vectorCriaturasAtaque.size() > 0) {
			//tenemos el acabarDefensa enable==false;
	
		}*/
	}
	
	private void quitaColores(){
		Vector mias=this.getMesa().getJugador1().getVectorCriaturas();
		Vector suyas=this.getMesa().getJugador2().getVectorCriaturas();
		CCriatura c;
		for (int i = 0; i<mias.size(); i++){
			c= ((CCriatura)mias.get(i));
			c.setColor(Color.BLACK);
			c.getGrafico().repaint();
			
		}
		for (int i = 0; i<suyas.size(); i++){
			c= ((CCriatura)suyas.get(i));
			c.setColor(Color.BLACK);
			c.getGrafico().repaint();
		}
	}
	
	private void ponEfectoGiradas(String jug){
		Vector criaturas=null;
		if (jug.equals("jugador1")){
			criaturas=this.getMesa().getJugador1().getVectorCriaturas();
		}
		else{
			criaturas=this.getMesa().getJugador2().getVectorCriaturas();
		}
		CCriatura c;
		for (int i = 0; i<criaturas.size(); i++){
			c= ((CCriatura)criaturas.get(i));
			if (c.getEstado()){
				c.setAtaque(c.getAtaqueInicial());
				c.setDefensa(c.getDefensaInicial());
			}
			else{
				c.setAtaque(c.getAtaqueInicial()/2);
				c.setDefensa(c.getDefensaInicial()/2);				
			}
		}
	}
	
	public void pasaSiguienteColor(){
		ultimoColor++;
	}

	public Color getColorActual(){
		return arrayColores[ultimoColor];
	}
	
    public Interfaz getInterfaz (){
      return inter;
    }
    
    public void setFinPartida(boolean b){
    	finPartida=b;
	}
	
	public synchronized void notifica(){
		this.notify();
	}

	public void añadeVectorBajadas(CACarta c){
		vectorBajadas.add(c);
	}

	
}
