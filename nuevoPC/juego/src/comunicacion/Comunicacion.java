package comunicacion;

/**
 * <p>Título: </p>
 * <p>Descripción: </p>
 * @author sin atribuir
 * @version 1.0
 */

import java.io.*;
import java.util.*;
import javax.swing.*;
import configuracion.*;

public class Comunicacion
    extends Thread {

  /**
   * Ventana principal del programa
   */
  private VentanaPrincipal ventPrinc;

  /**
   * Ventana padre
   */
  private JFrame ventPadre;

  /**
   * Controlador del programa
   */
  private Controlador controlador;

  /**
   * Cola de eventos
   */
	private LinkedList colaEventos;

  /**
   * Constructor de la clase comunicacion, la cual espera mensajes del servidor
   * @param controler Le inserto el controlador del programa
   * @param vPrinc Le inserto la ventana principal del programa
   */
  public Comunicacion(Controlador controler, VentanaPrincipal vPrinc) {
    ventPrinc = vPrinc;
    controlador = controler;
    colaEventos=new LinkedList();
  }

  /**
   * Metodo run de la superclase Thread con el que en un hilo aparte de ejecucion espero mensajes del servidor
   */
  public void run() {
    try {
      GestorUsuarios gestorUsuarios = controlador.getGestorUsuarios();
      String user, s, nomUsuario, mensaje = null;
      StringTokenizer st;
      BufferedReader in = new BufferedReader(new InputStreamReader( (
          controlador.getSocket()).getInputStream()));
      // Lee lineas y las envia para su difusion
      user = ventPrinc.getNick();
      while (true) {
        s = in.readLine().trim();
        System.out.println("Mensaje del servidor: "+s);
        //Creo nuevo usuario
        if (s.charAt(0) == 'N' && s.charAt(1) == 'U') {
          st = new StringTokenizer(s.substring(2), "#");
          nomUsuario = st.nextToken();
          gestorUsuarios.registrarUser(nomUsuario);
          ventPrinc.ActualizarListaUsuarios();
        }

        //Elimino un usuario
        if (s.charAt(0) == 'D' && s.charAt(1) == 'U') {
          st = new StringTokenizer(s.substring(2), "#");
          nomUsuario = st.nextToken();
          gestorUsuarios.removeUser(nomUsuario);
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
                /*&& (nomUsuarioPrivado.equals(vPAux.getNameUser()))*/) {
              vPAux.addMensaje("Administrador: El usuario " + nomUsuario +
                               " ha abandonado la conversación.");
            }
          }
        }


        //Crea las correspondientes ventanas de conversación privada
        if (s.charAt(0) == 'N' && s.charAt(1) == 'P') {
          st = new StringTokenizer(s.substring(2), "#");
          nomUsuario = st.nextToken();
          String nomUsuarioPrivado = st.nextToken();//Si el nombre del usuario q establece la comunicación
          //o el nombre del usuario con el que se quiere conectar coinciden con el nick de este cliente
          //se abre una nueva ventana de conversación privada
          gestorUsuarios.addUserJugando(nomUsuario);
          gestorUsuarios.addUserJugando(nomUsuarioPrivado);
          ventPrinc.ActualizarListaUsuarios();
          if (nomUsuario.equals(user)) {
            VentanaPrivada vPrivada1 = new VentanaPrivada(nomUsuario,
                nomUsuarioPrivado, controlador);
           controlador.addPrivado(vPrivada1);
//           configuracionImp config1=new configuracionImp(ventPrinc.getVentanaPadre());
//           config1.show();
          }
          if (nomUsuarioPrivado.equals(user)) {
            VentanaPrivada vPrivada2 = new VentanaPrivada(nomUsuarioPrivado,
                nomUsuario, controlador);
            controlador.addPrivado(vPrivada2);
//            configuracionImp config2=new configuracionImp(ventPrinc.getVentanaPadre());
//            config2.show();
         }
          if (nomUsuario.equals(user) | nomUsuarioPrivado.equals(user))
          {
          ventPrinc.setCerrarVentanaAuto(true); /*Le decimos a la ventana principal que se
              va ha cerrar automaticamente cuando pulsemos sobre el botón conectar*/
          ventPrinc.dispose(); // Y la cerramos
        	}
        }

        //Deshabilita la conversacion privada
        //NO SE NECESITA, SE HACE DIRECTAMENTE AL BORRAR EL USUARIO
        if (s.charAt(0) == 'D' && s.charAt(1) == 'P') {
          //st = new StringTokenizer(s.substring(2), "#");
          //String nomUsuarioPrivado = st.nextToken();
          //nomUsuario = st.nextToken();
          //controlador.removePrivado(nomUsuario);
          /*ArrayList vprivadas = controlador.getPrivados();
          VentanaPrivada vPAux;
          for (int i = 0; i < vprivadas.size(); i++) {
            vPAux = (VentanaPrivada) vprivadas.get(i);
            if ((nomUsuario.equals(vPAux.getNameOtherUser()))
                && (nomUsuarioPrivado.equals(vPAux.getNameUser()))) {
              vPAux.addMensaje("Administrador: El usuario " + nomUsuario +
                               " ha abandonado la conversación.");
            }
          }*/
        }

        //Recibo un mensaje privado y lo reparto entre ambos usuarios
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
            if ( (nomUsuario.equals(vPAux.getNameUser()))
                && (nomUsuarioPrivado.equals(vPAux.getNameOtherUser()))) {
              vPAux.addMensaje(nomUsuario + ": " + mensaje);
            }
            //Otro usuario
            if ( (nomUsuario.equals(vPAux.getNameOtherUser()))
                && (nomUsuarioPrivado.equals(vPAux.getNameUser()))) {
              vPAux.addMensaje(nomUsuario + ": " + mensaje);
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
          if (nomUsuario.equals(user)){
          	System.out.println("Ha llegado el evento: "+evento);
          	colaEventos.add(evento);
          }
        }

        // Desconecto un cliente
        if (s.charAt(0) == 'D' && s.charAt(1) == 'I') {
          nomUsuario = s.substring(2);
          if (user.equals(nomUsuario))
            break;
        }

      }
    }
    catch (Exception e) {
      JOptionPane.showMessageDialog(null, "El servidor se ha caido",
                                    "Error en la conexión",
                                    JOptionPane.ERROR_MESSAGE);
      //System.exit(-1);
    }
  }
  
  public String getEvento()
  	{
  		String resultado=colaEventos.getFirst().toString();
  		//colaEventos.removeFirst();
  		return resultado;
	}
}
