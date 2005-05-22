package comunicacion;

import configuracion.*;
import interfaz.*;
import eventos.*;
import panelesInfo.PanelGenerico;
import motorJuego.CPartidaRed;

import java.io.*;
import java.net.Socket;
import java.util.*;
import javax.swing.*;

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

	private Socket socket;


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
		socket=controlador.getSocket();
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
			BufferedReader in = new BufferedReader(new InputStreamReader((controlador.getSocket()).getInputStream()));

			// Lee lineas y las envia para su difusion
			user = ventPrinc.getNick();
			System.out.println("soy "+user);

			while (!socket.isClosed()) {
				try{
				s = in.readLine().trim();
				}
				catch (NullPointerException nexc){
					s="Error en la aplicacion";
				}
				//Creo nuevo usuario
				if (s.charAt(0) == 'N' && s.charAt(1) == 'U') {
					System.out.println ("Recibo "+s);
					st = new StringTokenizer(s.substring(2), "#");
					nomUsuario = st.nextToken();
					String pass=st.nextToken();
					gestorUsuarios.registrarUser(nomUsuario);
					ventPrinc.ActualizarListaUsuarios();
				}
				//Elimino un usuario
				else if (s.charAt(0) == 'D' && s.charAt(1) == 'U') {
					st = new StringTokenizer(s.substring(2), "#");
					nomUsuario = st.nextToken();
					gestorUsuarios.removeUser(nomUsuario);
					gestorUsuarios.removeUserJugando(nomUsuario);
					ventPrinc.ActualizarListaUsuarios();
					controlador.removePrivado(nomUsuario);
					ArrayList vprivadas = controlador.getPrivados();
					VentanaPrivada vPAux;
					for (int i = 0; i < vprivadas.size(); i++) {
						vPAux = (VentanaPrivada) vprivadas.get(i);
						if ((nomUsuario.equals(vPAux.getNameOtherUser()))) {
							vPAux.addMensaje("Administrador: El usuario " + nomUsuario +
									" ha abandonado la conversación.");
							Interfaz inter=controlador.getInterfaz();
							inter.iluminaConexion();
							if (!((CPartidaRed)inter.getPartida()).getFinPartida()){
              					inter.inhabilitaPanel();
								inter.getContentPane().add(new PanelGenerico("../imagenes/HasGanado.jpg",inter),0);
							}
							inter.repaint();
						}
					}

				}
				//Crea las correspondientes ventanas de conversación privada
				else if (s.charAt(0) == 'N' && s.charAt(1) == 'P') {
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
						vPrivada1 = new VentanaPrivada(nomUsuario,
								nomUsuarioPrivado, controlador);
						controlador.addPrivado(vPrivada1);

					}
					if (nomUsuarioPrivado.equals(user)) {
						/*
						 *  VentanaPrivada
						 */
						vPrivada2 = new VentanaPrivada(nomUsuarioPrivado,
								nomUsuario, controlador);
						controlador.addPrivado(vPrivada2);
;
					}
					if (nomUsuario.equals(user) | nomUsuarioPrivado.equals(user)) {
						ventPrinc.setCerrarVentanaAuto(true);
						/*
						 *  Le decimos a la ventana principal que se
						 *  va ha cerrar automaticamente cuando pulsemos sobre el botón conectar
						 */
                         ventPrinc.hide();
						// Y la cerramos
					}

				}
				//Recibo un mensaje privado y lo reparto entre ambos usuarios
				//  if(controlador.getInterfaz().activaPrivado){
				else if (s.charAt(0) == 'M' && s.charAt(1) == 'P') {
					st = new StringTokenizer(s.substring(2), "#");
					String nomUsuarioPrivado = st.nextToken();
					nomUsuario = st.nextToken();


					//bug!! se queda colgado si el mensaje es vacio
					//posible solucion, insertada por kike 5-mayo
					if (st.hasMoreTokens()){
						mensaje = st.nextToken();
						ArrayList vprivadas = controlador.getPrivados();
						VentanaPrivada vPAux;
						for (int i = 0; i < vprivadas.size(); i++) {
							vPAux = (VentanaPrivada) vprivadas.get(i);
							//Un usuario
							if ((nomUsuario.equals(vPAux.getNameUser()))
								&& (nomUsuarioPrivado.equals(vPAux.getNameOtherUser()))) {

								vPAux.addMensaje("<" + nomUsuario + ">" + mensaje);

								String mensajeGuarro = "";

							}
							//Otro usuario
							if ((nomUsuario.equals(vPAux.getNameOtherUser()))
									&& (nomUsuarioPrivado.equals(vPAux.getNameUser()))) {
								vPAux.addMensaje("<" + nomUsuario + ">" + mensaje);

								String mensajeGuarro = "";
								controlador.getInterfaz().iluminaConexion();
							}
						}
					}

				}
				//Recibo un mensaje evento
				else if (s.charAt(0) == 'E' && s.charAt(1) == 'S') {
					st = new StringTokenizer(s.substring(2), "#");
					nomUsuario = st.nextToken();
					String evento = st.nextToken();
					//Si el nombre de usuario corresponde con el del evento, entonces ese
					//evento va dirijido a aqui
					if (nomUsuario.equals(user)) {
						Eventos ev=ConversorEventos.pasaAEvento(evento);
						controlador.getInterfaz().getPartida().actualizaPorEvento(ev);
					}
				}
				// Desconecto un cliente
				else if (s.charAt(0) == 'D' && s.charAt(1) == 'I') {
					nomUsuario = s.substring(2);
					if (user.equals(nomUsuario)) {
						break;
					}
				}
				else if (s.startsWith("RD")/*charAt(0) == 'R' && s.charAt(1) == 'D'*/) {
					nomUsuario = s.substring(3);
					gestorUsuarios.removeUser(nomUsuario);
					gestorUsuarios.removeUserJugando(nomUsuario);
					System.out.println("ha caido el rival de:"+nomUsuario);
					ventPrinc.ActualizarListaUsuarios();
					if (user.equals(nomUsuario)) {
						System.out.println("Soy el rival de "+nomUsuario);
						ArrayList vprivadas=controlador.getPrivados();
						VentanaPrivada vPAux;
						for (int i = 0; i < vprivadas.size(); i++) {
							vPAux = (VentanaPrivada) vprivadas.get(i);
							if ((nomUsuario.equals(vPAux.getNameUser()))) {
								vPAux.addMensaje("Administrador: Ha caido la conexión de tu rival");
								Interfaz inter=controlador.getInterfaz();
								inter.iluminaConexion();
								if (!((CPartidaRed)inter.getPartida()).getFinPartida()){
									inter.inhabilitaPanel();
									inter.getContentPane().add(new PanelGenerico("../imagenes/HasGanado.jpg",inter),0);
								}
								inter.repaint();
							}
						}
						break;
					}
				}
				else if (s.startsWith("NNU")){
					st = new StringTokenizer(s.substring(3), "#");
					nomUsuario = st.nextToken();
					System.out.println ("Recibo un aviso de usuario o cont incorrecta para:"+s+"\nel usuario es:"+nomUsuario);
					if (nomUsuario.equals(user)){
						controlador.getGestorUsuarios().removeUser(user);
						ventPrinc.ActualizarListaUsuarios();
						Interfaz inter=controlador.getInterfaz();
						inter.iluminaConexion();
						inter.remove(ventPrinc);
						inter.inhabilitaPanel();
						inter.getContentPane().add(new PanelGenerico("../imagenes/Incorrecto.jpg",inter),0);
						controlador.desconectar(user);
						inter.iniciaJuegoRed(inter);
						//inter.repaint();
					}
				}


			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "El servidor se ha caido en comunicacion.java",
					"Error en la conexión",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
            ventPrinc.hide();
            controlador.getInterfaz().botonSalir_actionPerformed(null);
            
			if (vPrivada1 != null) {
				vPrivada1.dispose();
			}
			if (vPrivada2 != null) {
				vPrivada2.dispose();
			}
		}
	}

}
