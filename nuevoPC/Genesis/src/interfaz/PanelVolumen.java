package interfaz;

import audio.*;import Music.NativeFmod.*;

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
class PanelVolumen
		 extends JPanel {

	//Barra de volumen
	JSlider barraSlid = new JSlider();
	JProgressBar barraProg = new JProgressBar();

	//Para activar/desactivar los efectos de sonido
	JCheckBox efectosSonido = new JCheckBox();
	//Para activar/desactivar la musica de fondo
	JCheckBox musicaFondo;

	//Atributo que almacena el nivel de volumen, es un entero entre 0 - 100
	private int volumenActual;

	//Atributos que controlan es estado actual de los efectos y la musica
	private boolean efectosSonidoActivado;
	private boolean musicaFondoActivada;

	private char tipo;
        private Interfaz interfaz;

	private GridLayout gridLayout1 = new GridLayout();
	private Border border1;


	/**
	 *  Constructor for the PanelVolumen object
	 *
	 *@param  tip  Description of Parameter
	 */
	public PanelVolumen(char tip,Interfaz i) {
		volumenActual = 0;
		tipo = tip;
                interfaz=i;
                //bug!!
                //probar que tal funciona esto. similar con el otro JChexBox
                //musicaFondo = new JCheckBox("",interfaz.getPartida().getMusicaDeFondo().preguntaSonando());
                musicaFondo = new JCheckBox("", true);

		//inicialemente todos los efectos y sonidos están desactivados
		efectosSonidoActivado = false;
		musicaFondoActivada = false;

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


	//metodo que devuelve el valor de efectos sonido activado, true = activado
	/**
	 *  Gets the EfectosSonidoActivado attribute of the PanelVolumen object
	 *
	 *@return    The EfectosSonidoActivado value
	 */
	public boolean getEfectosSonidoActivado() {
		return efectosSonidoActivado;
	}


	//metodo que devuelve el valor de la musica de fondo activada, true = activada
	/**
	 *  Gets the MusicaFondoActivada attribute of the PanelVolumen object
	 *
	 *@return    The MusicaFondoActivada value
	 */
	public boolean getMusicaFondoActivada() {
		return musicaFondoActivada;
	}


	//activar o desactivar los efectos de sonido
	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void efectosSonido_actionPerformed(ActionEvent e) {
		//getItemSelectable();
		efectosSonidoActivado = !efectosSonidoActivado;
//    System.out.println("ACTIVADOS efectos de sonido" + efectosSonidoActivado);
		/////KIKE! activar o desactivar efectos segun el booleano

	}


	//activar o desactivar la música de fondo
	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void musicaFondo_actionPerformed(ActionEvent e) {
          musicaFondoActivada = !musicaFondoActivada;
          //System.out.println("ACTIVADOS efectos de sonido" + efectosSonidoActivado);
          //KIKE! activar o desactivar musica segun el booleano
          //interfaz.getPartida().getMusicaDeFondo().muteMusica();}
          if (Fmod.FSOUND_IsPlaying(1))
            interfaz.getPartida().getMusicaDeFondo().stopMusiquita();
          else interfaz.getPartida().getMusicaDeFondo().playMusiquita();
        }


 void BotonExplorador_mouseClicked(MouseEvent e) {

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
//    border1 = BorderFactory.createMatteBorder(6, 6, 6, 6, Color.red);
		this.setLayout(gridLayout1);
		this.setOpaque(false);
		barraSlid.setBackground(null);
		barraProg.setBackground(Color.black);

		//inicialemente en el 100
		barraSlid.setValue(100);
                barraProg.setValue(100);
		barraSlid.setOpaque(false);
		//pinto las rallitas
		barraSlid.setPaintTicks(true);
		barraSlid.setMajorTickSpacing(20);
		barraSlid.setMinorTickSpacing(5);

		//para pintar los numeros, de 0-100
		//barraSlid.setPaintLabels(true);

		barraSlid.addChangeListener(
					new ChangeListener() {
						public void stateChanged(ChangeEvent evt) {
							//actualizo la barra de progreso
							barraProg.setValue(barraSlid.getValue());
							//volumen actual es un entero entre 0 y 100
							volumenActual = barraSlid.getValue();
							////////KIKE!!! ACTUALIZAR AKI EL VOLUMEN
                                                        interfaz.getPartida().getMusicaDeFondo().setVolumenMusica(volumenActual);
//        System.out.println("Volumen Actual: " + volumenActual);
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
		musicaFondo.setText("Música de Fondo");
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

		musicaFondo.addMouseListener(new fondo_BotonExplorador3_mouseAdapter(this));
		efectosSonido.addMouseListener(new fondo_BotonExplorador2_mouseAdapter(this));

		//añado componentes
		gridLayout1.setColumns(2);
		gridLayout1.setHgap(40);
		gridLayout1.setRows(2);
		gridLayout1.setVgap(4);
		this.add(efectosSonido, null);
		this.add(barraSlid, null);
		this.add(musicaFondo, null);
		this.add(barraProg, null);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  args  Description of Parameter
	 */
/*	public static void main(String args[]) {
		JFrame frame = new JFrame("Tutorial de Java, Swing");
		frame.getContentPane().add(new PanelVolumen('D'),
				BorderLayout.CENTER);
		frame.setSize(500, 100);
		frame.setVisible(true);
	}*/

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

