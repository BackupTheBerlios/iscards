//A MODIFICAR POR TONY

package interfaz;

import cartas.*;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.Vector;
import java.awt.event.MouseEvent;
import java.lang.Math;

/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: Clase que implementa al componente gráfico que representa a
 *  una carta en el juego</p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Tony
 *@version    1.0
 */

public class Carta extends JComponent {

	private boolean girado;
	private BufferedImage imagen;
	private int posX;
	private int posY;
	private int dX, dY;
	private double escala;
	private CACarta carta;
	private int limiteX, limiteY;
	private boolean bajado;
	private Interfaz inter;
	
	private final int anchoMarco=30;
	private final int medioMarco;


	/**
	 *  Constructor for the Carta object
	 *
	 *@param  direccion  Description of Parameter
	 *@param  lX         Description of Parameter
	 *@param  lY         Description of Parameter
	 *@param  in         Description of Parameter
	 *@param  g          Description of Parameter
	 *@param  bajada     Description of Parameter
	 *@param  x          Description of Parameter
	 *@param  y          Description of Parameter
	 */
	public Carta(CACarta direccion, int lX, int lY, Interfaz in, boolean g,
			boolean bajada, int x, int y) {

		super();
		posX = x;
		posY = y;
		carta = direccion;
		escala = 0.3;
		limiteX = lX;
		limiteY = lY;
		inter = in;
		bajado = bajada;
		girado = g;
		direccion.setGrafico(this);
		medioMarco=anchoMarco/2;
	

		try {
			imagen = ImageIO.read(new File(carta.getImagen()));
			if (girado) {
				this.setSize((int) ((imagen.getHeight()+anchoMarco) * escala),
						(int) (escala * (imagen.getWidth()+anchoMarco)));
				this.setBounds(posX, posY, (int) ((imagen.getHeight()+anchoMarco) * escala),
						(int) (escala * (imagen.getWidth()+anchoMarco)));
			}
			else {
				this.setSize((int) (escala * (imagen.getWidth()+anchoMarco)),
						(int) ((imagen.getHeight()+anchoMarco) * escala));
				this.setBounds(posX, posY, (int) (escala * (imagen.getWidth()+anchoMarco)),
						(int) ((imagen.getHeight()+anchoMarco) * escala));
			}
		}
		catch (IOException e) {
			System.out.println(e);
		}

		if (imagen == null) {
			try {
				imagen = ImageIO.read(new File("../Cartas/" + carta.getIdRaza() +
						"/" + carta.getIdRaza() +
						"_no_disponible.jpg"));
				this.setSize(imagen.getWidth()+anchoMarco, imagen.getHeight()+anchoMarco);
				this.setBounds(posX, posY, (int) (imagen.getWidth() * escala)+anchoMarco,
						(int) (imagen.getHeight() * escala)+anchoMarco);

			}
			catch (IOException e) {
				System.out.println(e);
			}
		}
		this.addMouseListener(
					new javax.swing.event.MouseInputAdapter() {
						public void mousePressed(MouseEvent e) {
							dX = e.getX();
							dY = e.getY();
							repaint();
						}


						public void mouseDragged(MouseEvent e) {

						}

                        public void mouseEntered(MouseEvent e) {
                        	if (inter.getPartida().getMano().getCartas().contains(carta)){
			                    setToolTipText("ATAQUE= " + ((CCriatura)carta).getAtaque() +
        	                               " || DEFENSA= " + ((CCriatura)carta).getDefensa() +
                                           " || VIDA= " + ((CCriatura)carta).getVida()+
                                           " || MANA= "+((CCriatura)carta).getCoste());
                        		
                        	}
                        	else{
			                    setToolTipText("ATAQUE= " + ((CCriatura)carta).getAtaque() +
        	                               " || DEFENSA= " + ((CCriatura)carta).getDefensa() +
                                           " || VIDA= " + ((CCriatura)carta).getVida());
                        	}
                        }

						public void mouseReleased(MouseEvent e) {

							if (e.getButton() == e.BUTTON1) {
								arrastra(e);
								if ((e.getX() == dX) && (e.getY() == dY) && dentroLimites(dX,dY)) {
									//si es el turno del jugador puede modificar el tablero
									if (inter.getPartida().getTurnoJugador().equals("jugador1")) {
										//si la carta está en la mano
										if (!carta.isBajada()) {
											//si es el turno de bajada
											if (inter.getPartida().getTurnoPartida() == 2) {
												int disp = inter.getPartida().getMesa().getJugador1().getManaDisponible();
												int usado = inter.getPartida().getMesa().getJugador1().getManaUsado();
												if (disp >= carta.getCoste()) {
													int nuevoManaDisp = disp - carta.getCoste();
													int nuevoManaUsado = usado + carta.getCoste();
													inter.getPartida().getMesa().getJugador1().setManaDisponible(nuevoManaDisp);
													inter.getPartida().getMesa().getJugador1().setManaUsado(nuevoManaUsado);
													inter.setTextoManas("Mana Disponible " + nuevoManaDisp);
													inter.setTextoManasGastados("Mana Gastado " + nuevoManaUsado);
													inter.ponTextoEstado("Carta bajada a mesa!!!");
													carta.baja();
													inter.baja();
													repaint();
													  if((inter.getPartida().getMesa().getJugador1().getVectorCriaturas().size()==7)||
													     (inter.getPartida().getMano().getCartas().size() == 0)){
													      //no deja bajar mas de 7 criaturas
													      inter.getPartida().pasaTurnoPartida("jugador1");
														  inter.ponTextoEstado("No puedes bajar más cartas");	      
													  }
													  //pasa de turno obligatotiamente si no quedan manas para bajar cartas
													  else if(inter.getPartida().getMesa().getJugador1().getManaDisponible()==0){
													  		inter.getPartida().pasaTurnoPartida("jugador1");
													  		inter.ponTextoEstado("No queda mas Mana");
													  	}
													
												}
												else {
													inter.ponTextoEstado("No hay mana suficiente!!!");
												}
											}
										}
										//si la carta está en la mesa
										else {
											//la carta debe ser una criatura para atacar o defender
											if (carta.getIdTipo().equals("Criatura")) {
												//si estamos en el turno de ataque
												if (inter.getPartida().getTurnoPartida() == 3) {
													int posCarta = inter.getPartida().getPosicionCriatura(carta, false);
													//si se encuentra la carta la mesa del jugador 1
													if (posCarta != -1) {
														((CCriatura) carta).ataca(posCarta);
														//manda evento ataque
														rota();
														inter.ponTextoEstado("Criatura atacando!!!");
													}
												}
												//si estamos en el turno de defensa
												else if (inter.getPartida().getTurnoPartida() == 5) {
//****************************
													if (!inter.getPartida().getSeleccionandoDefensa()) {
														//si estamos seleccionando defensores nuestros
														int posCarta = inter.getPartida().getPosicionCriatura(carta, false);
														//si se encuentra la carta
														if (posCarta != -1) {
															if (inter.getPartida().getVectorCriaturasDefensa().contains(carta)) {
																//lo tenemos en una tablahash los enfrentamientos
																if (!inter.getPartida().getEnfrentamientos().containsKey(carta)) {
 																//esta disponible
																	inter.getPartida().setSeleccionandoDefensa(true);
																	inter.getPartida().setCartaSeleccionadaDefensa(carta);
																	if (carta.getEstado())
																		rota();																																		
																	((CCriatura)carta).setColor(inter.getPartida().getColorActual());
																	inter.ponTextoEstado("Seleccionando defensor");
																}
/*																else {
																	//si esta como key  => esta emparejada
																	//inter.getPartida().quitaUnoNumCriaturasDefendiendo();
																	//como no esta disponible, al value lo añadimos a vectorCriaturasAtaque
																	CACarta cartaEnAtaque = (CACarta) inter.getPartida().getEnfrentamientos().get(carta);

																	//inter.getPartida().getEnfrentamientos().remove(carta);

																	//desasignamos en lo grafico en cada carta
																	//*****************************************************
																	//hay que desasignar la defensa
																	int posCartaContrario = inter.getPartida().getPosicionCriatura(cartaEnAtaque, true);
																	((CCriatura) carta).defiende(posCarta, cartaEnAtaque, posCartaContrario);
																}*/
															}
														}
													}
													//si seleccionamos a los atacantes del contrario en los enfrentamientos
													else {
														//podemos seleccionar las cartas del contrario pero sin girarlas (cambia colores)
														if (carta.getIdTipo().equals("Criatura") &&
																inter.getPartida().getVectorCriaturasAtaque().contains(carta)) {
															//quitar de vectorCriaturasAtaque la carta
															boolean estaba = inter.getPartida().getVectorCriaturasAtaque().remove(carta);
															int posCartaContrario = inter.getPartida().getPosicionCriatura(carta, true);
															if (posCartaContrario != -1) {
																CACarta cartaEnDefensa = inter.getPartida().getCartaSeleccionadaDefensa();
																int posCarta = inter.getPartida().getPosicionCriatura(cartaEnDefensa, false);
																//desasignamos en lo grafico en cada carta
																//*****************************************************
																//hay que desasignar la defensa
																//manda evento defensa y actualiza colores para asociar
																((CCriatura) cartaEnDefensa).defiende(posCarta, carta, posCartaContrario);
																//lo metemos en una tablahash los enfrentamientos
																inter.getPartida().getEnfrentamientos().put(cartaEnDefensa, carta);
																inter.getPartida().setSeleccionandoDefensa(false);
																inter.getPartida().añadeUnoNumCriaturasDefendiendo();
																((CCriatura)carta).setColor(inter.getPartida().getColorActual());
																inter.getPartida().pasaSiguienteColor();

																inter.ponTextoEstado("Defensa asignada!!!");
																if (inter.getPartida().vectorCriaturasDefensa.size() <= inter.getPartida().numCriaturasDefendiendo || inter.getPartida().vectorCriaturasAtaque.size() <= 0){
																	inter.getPartida().notifica();
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
							else if (e.getButton() == e.BUTTON3) {
								inter.setEnabled(false);
								amplia();

							}
						}

					});
	}


	/**
	 *  Description of the Method
	 *
	 *@param  g  Description of Parameter
	 */
	public void paint(Graphics g) {

		Graphics2D graf = (Graphics2D) g;
		double x = escala;
		double y = escala;
		graf.scale(x, y);
		if (girado) {
			graf.rotate(Math.PI / 2, 0, 0);
			graf.translate(0, -(imagen.getHeight()+anchoMarco*2*escala));
		}
		if (this.carta instanceof CCriatura) {
			Color aux = graf.getColor();
			graf.setColor(((CCriatura) carta).getColor());
			graf.fillRect(0, 0, imagen.getWidth()+anchoMarco,imagen.getHeight()+anchoMarco);
		}
		graf.drawImage(imagen, null, medioMarco,medioMarco);
		

	}


	/**
	 *  Description of the Method
	 */
	public void rota() {
		girado = !girado;
		carta.setEstado(!girado);
			if (girado) {
				this.setSize((int) ((imagen.getHeight()+anchoMarco) * escala),
						(int) (escala * (imagen.getWidth()+anchoMarco)));
				this.setBounds(posX, posY, (int) ((imagen.getHeight()+anchoMarco) * escala),
						(int) (escala * (imagen.getWidth()+anchoMarco)));
			}
			else {
				this.setSize((int) (escala * (imagen.getWidth()+anchoMarco)),
						(int) ((imagen.getHeight()+anchoMarco) * escala));
				this.setBounds(posX, posY, (int) (escala * (imagen.getWidth()+anchoMarco)),
						(int) ((imagen.getHeight()+anchoMarco) * escala));
			}
		
		repaint();
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void arrastra(MouseEvent e) {
		posX = posX + e.getX() - dX;
		posY = posY + e.getY() - dY;

		if (posX > (limiteX - (int) (imagen.getWidth() * escala))) {
			posX = limiteX - (int) (imagen.getWidth() * escala);
		}

		if (posX < 0) {
			posX = 0;
		}

		if (posY < 0) {
			posY = 0;
		}

		if (posY > (limiteY - (int) (imagen.getHeight() * escala))) {
			posY = limiteY - (int) (imagen.getHeight() * escala);
		}

			if (girado) {
				this.setSize((int) ((imagen.getHeight()+anchoMarco) * escala),
						(int) (escala * (imagen.getWidth()+anchoMarco)));
				this.setBounds(posX, posY, (int) ((imagen.getHeight()+anchoMarco) * escala),
						(int) (escala * (imagen.getWidth()+anchoMarco)));
			}
			else {
				this.setSize((int) (escala * (imagen.getWidth()+anchoMarco)),
						(int) ((imagen.getHeight()+anchoMarco) * escala));
				this.setBounds(posX, posY, (int) (escala * (imagen.getWidth()+anchoMarco)),
						(int) ((imagen.getHeight()+anchoMarco) * escala));
			}
		//repaint();
	}


	/**
	 *  Description of the Method
	 */
	public void amplia() {

		final JFrame ampliacion =
			new JFrame("bicho") {
				public void paint(Graphics g) {
					ImageIcon cursor;
					String raza = carta.getIdRaza();
					if(raza.equals("Ángeles")){
						//skin de angeles
						cursor = new ImageIcon("../imagenes/cursores/ángeles.gif");
														
					}
					else if(raza.equals("Demonios")){
						//skin de demonios
						cursor = new ImageIcon("../imagenes/cursores/demonios.gif");
						
					}
					else if(raza.equals("Humanos")){
						//skin de humanos
						cursor = new ImageIcon("../imagenes/cursores/humanos.gif");					
					}
					else if(raza.equals("Inmortales")){
						//skin de inmortales
						cursor = new ImageIcon("../imagenes/cursores/inmortales.gif");
					}
					else
						cursor = new ImageIcon();
					Image image = cursor.getImage();
					Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(9, 3), "img");
					this.setCursor(puntero);
					
					
					Graphics2D gr = (Graphics2D) g;
					gr.drawImage(imagen, null, 0, 30);
					inter.ponTextoEstado("Ampliación");
				}

			};

		ampliacion.getContentPane().setLayout(null);

		//hacemos que la carta se cierre al pulsar en ella
		ampliacion.addMouseListener(
					new java.awt.event.MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							inter.setEnabled(true);
							ampliacion.dispose();
						}

					});
		ampliacion.setUndecorated(true);
		ampliacion.setBackground(Color.black);
		ampliacion.setResizable(false);
		ampliacion.setVisible(true);
		ampliacion.setSize(imagen.getWidth(), imagen.getHeight() + 30);
	}
	
	private boolean dentroLimites(int puntX,int puntY){
		if (girado){
			
		}
		else{
			
		}
		return true;
	}


}
