//A MODIFICAR POR TONY

package interfaz;

import cartas.*;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.awt.event.MouseEvent;

/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: Clase que implementa al componente gráfico que representa al
 *  cementerio de cartas del juego</p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Rui
 *@version    1.0
 */

public class Cementerio extends JComponent {

	/**
	 *  Method Cementerio
	 */
	private CMazo cementerio;
	private BufferedImage imagen;
	private int X, Y;
	private double escala;


	/**
	 *  Constructor for the Cementerio object
	 *
	 *@param  c     Description of Parameter
	 *@param  tipo  Description of Parameter
	 *@param  px    Description of Parameter
	 *@param  py    Description of Parameter
	 */
	public Cementerio(CMazo c, char tipo, int px, int py) {
		cementerio = c;
		X = px;
		Y = py;
		escala = 0.2;
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
			if (!cementerio.getCartas().isEmpty()) {
				CACarta ultima;
				ultima = (CACarta) cementerio.getCartas().getLast();
				imagen = ImageIO.read(new File(ultima.getImagen()));
			}
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
							if (e.getButton() == e.BUTTON1) {
//          System.out.println(cementerio.getCartas().size());
								if (!cementerio.getCartas().isEmpty()) {
									CACarta car = (CACarta) cementerio.getCartas().getLast();
									car.getGrafico().amplia();
								}
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
