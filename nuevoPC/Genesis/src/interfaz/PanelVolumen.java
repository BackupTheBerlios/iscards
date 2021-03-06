package interfaz;

import login.*;

import java.awt.*;
import java.awt.AWTEvent;

import javax.swing.*;
import javax.swing.event.*;

import java.util.EventObject;
import java.lang.*;
import java.awt.event.*;
import javax.swing.border.*;

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
public class PanelVolumen
		 extends JPanel {

	//Barra de volumen
	JSlider barraSlid = new JSlider();
	JProgressBar barraProg = new JProgressBar();

	//Para activar/desactivar los efectos de sonido
	JCheckBox efectosSonido = new JCheckBox();
	//Para activar/desactivar la musica de fondo
	JCheckBox musicaFondo = new JCheckBox();

	//Atributo que almacena el nivel de volumen, es un entero entre 0 - 100
	private static int volumenActual=100;

	//Atributos que controlan es estado actual de los efectos y la musica
	private static boolean efectosSonidoActivado=true;
	private static boolean musicaFondoActivada=true;

	private char tipo;

	private GridLayout gridLayout1 = new GridLayout();
	private Border border1;


	/**
	 *  Constructor for the PanelVolumen object
	 *
	 *@param  tip  Description of Parameter
	 */
	public PanelVolumen(char tip) {
		tipo = tip;

		//inicialemente todos los efectos y sonidos est�n desactivados

		try {
			jbInit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	////////KIKE! metodos de acceso por si los necesitas

	//metodo que devuelve el voulumen actual
	/**
	 *  Gets the VolumenActual attribute of the PanelVolumen object
	 *
	 *@return    The VolumenActual value
	 */
	public int getVolumenActual() {
		return volumenActual;
	}

	/**
	 *  M�todo que devuelve el valor de efectos sonido activado, true = activado
	 *
	 *@return    The EfectosSonidoActivado value
	 */
	public static boolean getEfectosActivados(){
		return efectosSonidoActivado;
	}


	/**
	 *  metodo que devuelve el valor de la musica de fondo activada, true = activada
	 *
	 *@return    The MusicaFondoActivada value
	 */
	public boolean getMusicaFondoActivada() {
		return musicaFondoActivada;
	}


	/**
	 * activar o desactivar los efectos de sonido
	 *
	 *@param  e  Description of Parameter
	 */
	void efectosSonido_actionPerformed(ActionEvent e) {
		efectosSonidoActivado = !efectosSonidoActivado;

	}


	/**
	 *activar o desactivar la m�sica de fondo
	 *
	 *@param  e  Description of Parameter
	 */
	void musicaFondo_actionPerformed(ActionEvent e) {
		musicaFondoActivada = !musicaFondoActivada;
		LoginImp.getGestorAudio().pauseMusiquita();
	}
	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void BotonExplorador_mouseEntered(MouseEvent e) {
		musicaFondo.setForeground(Color.white);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void BotonExplorador_mouseExited(MouseEvent e) {
		Color color = Color.red;
		switch (tipo) {
			//angeles
			case 'A':
				color = new Color(70, 70, 100);
				break;
			//demonios
			case 'D':
				color = new Color(190, 0, 40);
				break;
			//inmortales
			case 'I':
				color = new Color(144, 0, 164);
				//color
				break;
			//humanos
			case 'H':
				color = new Color(0, 150, 90);
				break;
		}
		musicaFondo.setForeground(color);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void BotonExplorador2_mouseEntered(MouseEvent e) {
		efectosSonido.setForeground(Color.white);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void BotonExplorador2_mouseExited(MouseEvent e) {
		Color color = Color.red;
		switch (tipo) {
			//angeles
			case 'A':
				color = new Color(70, 70, 100);
				break;
			//demonios
			case 'D':
				color = new Color(190, 0, 40);
				break;
			//inmortales
			case 'I':
				color = new Color(144, 0, 164);
				//color
				break;
			//humanos
			case 'H':
				color = new Color(0, 150, 90);
				break;
		}
		efectosSonido.setForeground(color);
	}


	/**
	 *  Description of the Method
	 *
	 *@exception  Exception  Description of Exception
	 */
	private void jbInit() throws Exception {
		this.setLayout(gridLayout1);
		this.setOpaque(false);
		barraSlid.setBackground(null);
		barraProg.setBackground(Color.black);
		barraProg.setValue(volumenActual);

		//inicialemente en el 100
		
		barraSlid.setValue(volumenActual);
		barraSlid.setOpaque(false);
		//pinto las rallitas
		barraSlid.setPaintTicks(true);
		barraSlid.setMajorTickSpacing(20);
		barraSlid.setMinorTickSpacing(5);



		barraSlid.addChangeListener(
					new ChangeListener() {
						public void stateChanged(ChangeEvent evt) {
							//actualizo la barra de progreso
							barraProg.setValue(barraSlid.getValue());
							//volumen actual es un entero entre 0 y 100
							volumenActual = barraSlid.getValue();
							////////KIKE!!! ACTUALIZAR AKI EL VOLUMEN
							LoginImp.getGestorAudio().setVolumenMusica(volumenActual);
						}
					}
				);
		this.setOpaque(false);
		;
		this.setBorder(border1);

		//CheckBox de efectos de sonido
		efectosSonido.setFont(new java.awt.Font("Serif", 3, 20));
		efectosSonido.setBorderPainted(true);
		efectosSonido.setHorizontalAlignment(SwingConstants.CENTER);
		efectosSonido.setText("Efectos de Sonido");
		efectosSonido.addActionListener(
					new java.awt.event.ActionListener() {
						public void actionPerformed(ActionEvent e) {
							efectosSonido_actionPerformed(e);
						}
					});
		efectosSonido.setBackground(null);

		//CheckBox de musica de fondo
		musicaFondo.setFont(new java.awt.Font("Serif", 3, 20));
		musicaFondo.setBorderPainted(true);
		musicaFondo.setHorizontalAlignment(SwingConstants.CENTER);
		musicaFondo.setText("M�sica de Fondo");
		musicaFondo.addActionListener(
					new java.awt.event.ActionListener() {
						public void actionPerformed(ActionEvent e) {
							musicaFondo_actionPerformed(e);
						}
					});

		Color color = Color.red;

		switch (tipo) {
			//angeles
			case 'A':
				color = new Color(70, 70, 100);
				break;
			//demonios
			case 'D':
				color = new Color(190, 0, 40);
				break;
			//inmortales
			case 'I':
				color = new Color(144, 0, 164);
				//color
				break;
			//humanos
			case 'H':
				color = new Color(0, 150, 90);
				break;
		}
		barraSlid.setForeground(color);
		barraProg.setForeground(color);
		efectosSonido.setForeground(color);
		musicaFondo.setForeground(color);

		musicaFondo.setOpaque(false);
		efectosSonido.setOpaque(false);
		
		musicaFondo.setSelected(musicaFondoActivada);
		efectosSonido.setSelected(efectosSonidoActivado);

		musicaFondo.addMouseListener(new fondo_BotonExplorador3_mouseAdapter(this));
		efectosSonido.addMouseListener(new fondo_BotonExplorador2_mouseAdapter(this));

		//a�ado componentes
		gridLayout1.setColumns(2);
		gridLayout1.setHgap(40);
		gridLayout1.setRows(2);
		gridLayout1.setVgap(4);
		this.add(efectosSonido, null);
		this.add(barraSlid, null);
		this.add(musicaFondo, null);
		this.add(barraProg, null);
	}




}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class fondo_BotonExplorador3_mouseAdapter
		 extends java.awt.event.MouseAdapter {


	PanelVolumen adaptee;


	/**
	 *  Constructor for the fondo_BotonExplorador3_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	fondo_BotonExplorador3_mouseAdapter(PanelVolumen adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.BotonExplorador_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.BotonExplorador_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class fondo_BotonExplorador2_mouseAdapter
		 extends java.awt.event.MouseAdapter {


	PanelVolumen adaptee;


	/**
	 *  Constructor for the fondo_BotonExplorador2_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	fondo_BotonExplorador2_mouseAdapter(PanelVolumen adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.BotonExplorador2_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.BotonExplorador2_mouseExited(e);
	}
	
}

