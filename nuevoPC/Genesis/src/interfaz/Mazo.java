//A MODIFICAR POR TONY

package interfaz;

import cartas.*;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: Clase que implementa al componente gráfico que representa al
 *  mazo de cartas del juego</p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Tony
 *@version    1.0
 */

public class Mazo extends JComponent {

	/**
	 *  Method Mazo
	 */
	private CMazo mazo;
	private BufferedImage imagen;
	private int X, Y;
	private double escala;
	private Vector mano;
	private Interfaz abuelo;



	/**
	 *  Constructor for the Mazo object
	 *
	 *@param  m     Description of Parameter
	 *@param  tipo  Description of Parameter
	 *@param  px    Description of Parameter
	 *@param  py    Description of Parameter
	 *@param  l     Description of Parameter
	 *@param  in    Description of Parameter
	 */
	public Mazo(CMazo m, char tipo, int px, int py, Vector l, Interfaz in) {

		mazo = m;
		X = px;
		Y = py;
		escala = 0.2;
		mano = l;
		abuelo = in;
		String dir;

		switch (tipo) {

			case 'A':
				dir = "Ángeles";
				break;
			case 'D':
				dir = "Demonios";
				break;
			case 'H':
				dir = "Humanos";
				break;
			case 'I':
				dir = "Inmortales";
				break;
			default:
				dir = "Demonios";

		}

		try {

			imagen = ImageIO.read(new File("../Cartas/" + dir + "/" + dir + ".jpg"));
			this.setSize(imagen.getWidth(), imagen.getHeight());
			this.setBounds(X, Y, (int) (imagen.getWidth() * escala), (int) (imagen.getHeight() * escala));

		}
		catch (IOException e) {
			System.out.println(e);
		}

		this.addMouseListener(
					new javax.swing.event.MouseInputAdapter() {
						public void mousePressed(MouseEvent e) {

						}


						public void mouseDragged(MouseEvent e) {

						}


						public void mouseReleased(MouseEvent e) {

							if (e.getButton() == e.BUTTON1 && (abuelo.getPartida().getTurnoPartida() == 1)) {
//                System.out.println(mano.size());
								if (mano.size() < 8 && (mazo.getTamano()) > 0) {
									CACarta car = (CACarta) mazo.robaCarta();
									if (car != null) {
										mano.add(car);
										abuelo.ponTextoEstado("Carta robada!!!");
									}
								}
								abuelo.repaint();
//                System.out.println("despues "+mano.size());

							}
							else if (e.getButton() == e.BUTTON3) {

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
		graf.drawImage(imagen, null, 0, 0);
	}
}
