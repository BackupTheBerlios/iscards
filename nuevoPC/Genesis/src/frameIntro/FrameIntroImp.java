
package frameIntro;

import coleccion.*;
import usuario.*;
import configuracion.*;
import editor.*;
import panelesInfo.*;
import comunicacion.*;
import audio.*;
import login.*;


import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: Implementación del interfaz gráfico del frame de inicio</p>
 *  <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Rui Miguel Alonso
 *@version    1.0
 */

public class FrameIntroImp extends FrameIntroGUI {

	int ancho = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	int alto = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	/**
	 *  coleccion de cartas del juego "coleccion.car" donde están todas las
	 *  cartas disponibles durante el juego
	 */
	private Coleccion coleccion;

	/**
	 *  usuario registrado en el juego para la partida
	 */
	private Usuario usuario;
	
	private ConfiguracionImp conf;


	/**
	 *  Constructora de la clase
	 *
	 *@param  usu  usuario que ha iniciado la partida
	 *@param  col  coleccion de las cartas
	 */
	public FrameIntroImp(Usuario usu, Coleccion col) {
		coleccion = col;
		usuario = usu;
		
		this.addWindowFocusListener(new java.awt.event.WindowFocusListener(){
				public void windowGainedFocus(WindowEvent e){
					if (conf!=null){
						conf.setPartida(null);
						conf=null;
					}
					System.gc();		
					System.runFinalization();					
				}
				public void windowLostFocus(WindowEvent e) {					
				}

			});
	}



	/**
	 *  Función actionPerformed del botón 1 Jugador
	 *
	 *@param  e
	 */
	void boton1Jugador_actionPerformed(ActionEvent e) {
		//mostramos el frame de Configuración de la partida
		conf = new ConfiguracionImp(this, coleccion, usuario, false);
		System.gc();
		System.runFinalization();
		LoginImp.setGestorAudio(new GestorAudio("efecto","click.wav"));
		conf.show();
		this.dispose();
	}


	/**
	 *  Función actionPerformed del botón Juego en Red
	 *
	 *@param  e
	 */

	void botonJuegoRed_actionPerformed(ActionEvent e) {

		//mostramos el frame de Configuración de la partida, que sera distinto del de un jugador
		try {
			ConfiguracionImp conf2 = new ConfiguracionImp(this, coleccion, usuario, true);
			conf2.show();
		}
		catch (Exception e2) {
			e2.printStackTrace();
		}

		//Activamos el chat
		/*
		 *  GestorUsuarios gestorUsuarios = new GestorUsuarios();
		 *  Controlador controlador = new Controlador(gestorUsuarios, usuario);
		 *  GUI ventana = new GUI(controlador,this);
		 */
	}


	/**
	 *  Función actionPerformed del botón Demo
	 *
	 *@param  e
	 */
	void botonDemo_actionPerformed(ActionEvent e) {
		//mostramos el frame de Configuración de la partida
		ConfiguracionImp conf = new ConfiguracionImp(this, coleccion, usuario, false);
		conf.show();
	}


	/**
	 *  Función actionPerformed del botón Editar barajas
	 *
	 *@param  e
	 */
	void botonEditar_actionPerformed(ActionEvent e) {

		EligeRazaGUI panelE = new EligeRazaGUI(coleccion, usuario,this);
		panelE.show();
	}


	/**
	 *  Función actionPerformed del botón mostrar Ayuda
	 *
	 *@param  e
	 */
	void botonAyuda_actionPerformed(ActionEvent e) {
		Runtime r = Runtime.getRuntime();
		Process p = null;
		File f=new File(".");
		String ruta=f.getAbsolutePath();
		char[] sinBarras=new char[ruta.length()];
		for (int i =0; i<ruta.length();i++){
			if (ruta.charAt(i)=='\\'){
				sinBarras[i]='/';
			}
			else
				sinBarras[i]=ruta.charAt(i);			
		}
		
		String nuevaRuta=new String(sinBarras);
		int ultimaVez=nuevaRuta.lastIndexOf('/');
		nuevaRuta=nuevaRuta.substring(0,ultimaVez);
		ultimaVez=nuevaRuta.lastIndexOf('/');
		nuevaRuta=nuevaRuta.substring(0,ultimaVez);
		try {
			p = r.exec("EXPLORER file://"+nuevaRuta+"/manual/ayuda/genesis.htm");
		}
		catch (Exception q) {
			JOptionPane.showMessageDialog(null, q.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		/*
		 *  AyudaGUI panelA = new AyudaGUI();
		 *  panelA.setSize(ancho,alto);
		 *  panelA.show();
		 */
	}


	/**
	 *  Función actionPerformed del botón mostrar Reglas
	 *
	 *@param  e
	 */
	void botonReglas_actionPerformed(ActionEvent e) {
		Runtime r = Runtime.getRuntime();
		Process p = null;
		File f=new File(".");
		String ruta=f.getAbsolutePath();
		char[] sinBarras=new char[ruta.length()];
		for (int i =0; i<ruta.length();i++){
			if (ruta.charAt(i)=='\\'){
				sinBarras[i]='/';
			}
			else
				sinBarras[i]=ruta.charAt(i);			
		}
		
		String nuevaRuta=new String(sinBarras);
		int ultimaVez=nuevaRuta.lastIndexOf('/');
		nuevaRuta=nuevaRuta.substring(0,ultimaVez);
		ultimaVez=nuevaRuta.lastIndexOf('/');
		nuevaRuta=nuevaRuta.substring(0,ultimaVez);
		try {
			p = r.exec("EXPLORER file://"+nuevaRuta+"/manual/reglas/genesis.htm");
		}
		catch (Exception q) {
			JOptionPane.showMessageDialog(null, q.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		/*
		 *  PanelReglas panelR =new PanelReglas();
		 *  panelR.show();
		 */
	}


	/**
	 *  Función actionPerformed del botón Salir
	 *
	 *@param  e
	 */
	/*
	 *  void botonSalir_actionPerformed(ActionEvent e) {
	 *  }
	 */
	void botonEnviar_actionPerformed(ActionEvent e) {
//  	ServidorBTPC serv=new ServidorBTPC(coleccion,usuario);
//  	serv.iniciaServidor();
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonRecibir_actionPerformed(ActionEvent e) {
//  	ClienteBTPC cliente=new ClienteBTPC(coleccion,usuario);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonAcepta_actionPerformed(ActionEvent e) {
		this.dispose();
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonAcepta_mouseEntered(MouseEvent e) {

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botonAcepta_mouseExited(MouseEvent e) {

	}


}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class botonAcepta_mouseAdapter extends java.awt.event.MouseAdapter {


	FrameIntroImp adaptee;


	/**
	 *  Constructor for the botonAcepta_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	botonAcepta_mouseAdapter(FrameIntroImp adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botonAcepta_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botonAcepta_mouseExited(e);
	}
}


/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class frameIntroImp_botonAcepta_actionAdapter implements ActionListener {


	FrameIntroImp adaptee;


	/**
	 *  Constructor for the frameIntroImp_botonAcepta_actionAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	frameIntroImp_botonAcepta_actionAdapter(FrameIntroImp adaptee) {
		this.adaptee = adaptee;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void actionPerformed(ActionEvent e) {
		adaptee.botonAcepta_actionPerformed(e);
	}

}
