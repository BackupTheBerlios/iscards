package comunicacion;

import java.io.*;
import java.util.*;
import javax.swing.*;
import configuracion.*;
import interfaz.*;

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
public class Comunicacion extends Thread {

	/**
	 *  Ventana principal del programa
	 */
	private VentanaPrincipal ventPrinc;

	/**
	 *  Ventana privada 1
	 */
	private VentanaPrivada vPrivada1;

	/**
	 *  Ventana privada 2
	 */
	private VentanaPrivada vPrivada2;

	/**
	 *  Ventana padre
	 */
	private JFrame ventPadre;

	/**
	 *  Controlador del programa
	 */
	private Controlador controlador;


	/**
	 *  Constructor de la clase comunicacion, la cual espera mensajes del
	 *  servidor
	 *
	 *@param  controler  Le inserto el controlador del programa
	 *@param  vPrinc     Le inserto la ventana principal del programa
	 */
	public Comunicacion(Controlador controler, VentanaPrincipal vPrinc) {
		ventPrinc = vPrinc;
		controlador = controler;
	}


	/**
	 *  Metodo run de la superclase Thread con el que en un hilo aparte de
	 *  ejecucion espero mensajes del servidor
	 */
	public void run() {
		try {
			GestorUsuarios gestorUsuarios = controlador.getGestorUsuarios();
			String user;
			String s;
			String nomUsuario;
			String mensaje = null;
			StringTokenizer st;
			BufferedReader in = new BufferedReader(new InputStreamReader((
					controlador.getSocket()).getInputStream()));
			// Lee lineas y las envia para su difusion
			user = ventPrinc.getNick();
			System.out.println("***************************************" + "Run???");

			while (true) {
				s = in.readLine().trim();
				//System.out.println("Mensaje del servidor: "+s);
				//Creo nuevo usuario
				if (s.charAt(0) == 'N' && s.charAt(1) == 'U') {
					st = new StringTokenizer(s.substring(2), "#");
					nomUsuario = st.nextToken();
					gestorUsuarios.registrarUser(nomUsuario);
					ventPrinc.ActualizarListaUsuarios();
				}
				System.out.println("***************************************" + "NU");

				//Elimino un usuario
				if (s.charAt(0) == 'D' && s.charAt(1) == 'U') {
					st = new StringTokenizer(s.substring(2), "#");
					nomUsuario = st.nextToken();
					gestorUsuarios.removeUser(nomUsuario);
					gestorUsuarios.removeUserJugando(nomUsuario);
					ventPrinc.ActualizarListaUsuarios();
					//String nomUsuarioPrivado=user;
					//En caso de que el usuario que se elimina estuviese hablando con
					//nosotros nos informa de que ha abandonado la comunicación.
					controlador.removePrivado(nomUsuario);
					ArrayList vprivadas = controlador.getPrivados();
					VentanaPrivada vPAux;
					for (int i = 0; i < vprivadas.size(); i++) {
						vPAux = (VentanaPrivada) vprivadas.get(i);
						if ((nomUsuario.equals(vPAux.getNameOtherUser()))
						/*
						 *  && (nomUsuarioPrivado.equals(vPAux.getNameUser()))
						 */
								) {
							vPAux.addMensaje("Administrador: El usuario " + nomUsuario +
									" ha abandonado la conversación.");
//************************************
//************************************
							//bug!!! el servidor no puede acceder al componenete de texto de maria
							// controlador.getInterfaz().getAreaCharla().AccessibleJTextComponent.insertTextAtIndex(0,"textito");
							// bug!! textito ha de reemplazarse por algo como lo suigiente
							//  .setText("Administrador: El usuario " + nomUsuario + " ha abandonado la conversación.");
//************************************
//************************************
						}
					}
					System.out.println("***************************************" + "DU");

				}

				//Crea las correspondientes ventanas de conversación privada
				if (s.charAt(0) == 'N' && s.charAt(1) == 'P') {
					st = new StringTokenizer(s.substring(2), "#");
					nomUsuario = st.nextToken();
					String nomUsuarioPrivado = st.nextToken();
					//Si el nombre del usuario q establece la comunicación
					//o el nombre del usuario con el que se quiere conectar coinciden con el nick de este cliente
					//se abre una nueva ventana de conversación privada
					gestorUsuarios.addUserJugando(nomUsuario);
					gestorUsuarios.addUserJugando(nomUsuarioPrivado);
					ventPrinc.ActualizarListaUsuarios();
					if (nomUsuario.equals(user)) {
						/*
						 *  VentanaPrivada
						 */
						vPrivada1 = new VentanaPrivada(nomUsuario,
								nomUsuarioPrivado, controlador);
						controlador.addPrivado(vPrivada1);
//           configuracionImp config1=new configuracionImp(ventPrinc.getVentanaPadre());
//           config1.show();
					}
					if (nomUsuarioPrivado.equals(user)) {
						/*
						 *  VentanaPrivada
						 */
						vPrivada2 = new VentanaPrivada(nomUsuarioPrivado,
								nomUsuario, controlador);
						controlador.addPrivado(vPrivada2);
//            configuracionImp config2=new configuracionImp(ventPrinc.getVentanaPadre());
//            config2.show();
					}
					if (nomUsuario.equals(user) | nomUsuarioPrivado.equals(user)) {
						ventPrinc.setCerrarVentanaAuto(true);
						/*
						 *  Le decimos a la ventana principal que se
						 *  va ha cerrar automaticamente cuando pulsemos sobre el botón conectar
						 */
						ventPrinc.dispose();
						// Y la cerramos
					}
					System.out.println("***************************************" + "NP");

				}
				//Recibo un mensaje privado y lo reparto entre ambos usuarios
				//  if(controlador.getInterfaz().activaPrivado){
				if (s.charAt(0) == 'M' && s.charAt(1) == 'P') {
					st = new StringTokenizer(s.substring(2), "#");
					String nomUsuarioPrivado = st.nextToken();
					nomUsuario = st.nextToken();
					mensaje = st.nextToken();
					ArrayList vprivadas = controlador.getPrivados();
					VentanaPrivada vPAux;
					for (int i = 0; i < vprivadas.size(); i++) {
						vPAux = (VentanaPrivada) vprivadas.get(i);
						//Un usuario
						if ((nomUsuario.equals(vPAux.getNameUser()))
								&& (nomUsuarioPrivado.equals(vPAux.getNameOtherUser()))) {

							vPAux.addMensaje("<" + nomUsuario + ">" + mensaje);
//****************************
							String mensajeGuarro = "";
							/*
							 *  mensajeGuarro = controlador.getInterfaz().textoDelPrivado;
							 *  controlador.getInterfaz().getAreaCharla().append("<" + nomUsuario + ">" + mensajeGuarro + "\n");
							 */
//**************************
						}
						//Otro usuario
						if ((nomUsuario.equals(vPAux.getNameOtherUser()))
								&& (nomUsuarioPrivado.equals(vPAux.getNameUser()))) {
							vPAux.addMensaje("<" + nomUsuario + ">" + mensaje);
//****************************
							String mensajeGuarro = "";
							/*
							 *  mensajeGuarro = controlador.getInterfaz().textoDelPrivado;
							 *  controlador.getInterfaz().getAreaCharla().append("<" + nomUsuario + ">" + mensajeGuarro + "\n");
							 */
//**************************
						}
					}
				}

				//Recibo un mensaje evento
				if (s.charAt(0) == 'E' && s.charAt(1) == 'S') {
					st = new StringTokenizer(s.substring(2), "#");
					nomUsuario = st.nextToken();
					String evento = st.nextToken();
					//Si el nombre de usuario corresponde con el del evento, entonces ese
					//evento va dirijido a aqui
					if (nomUsuario.equals(user)) {
						controlador.addEventoACola(evento);
					}
				}

				// Desconecto un cliente
				if (s.charAt(0) == 'D' && s.charAt(1) == 'I') {
					nomUsuario = s.substring(2);
					if (user.equals(nomUsuario)) {
						break;
					}
				}

			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "El servidor se ha caido en comunicacion.java",
					"Error en la conexión",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			ventPrinc.dispose();
			if (vPrivada1 != null) {
				vPrivada1.dispose();
			}
			if (vPrivada2 != null) {
				vPrivada2.dispose();
			}
			//System.exit(-2);
		}
	}

}
