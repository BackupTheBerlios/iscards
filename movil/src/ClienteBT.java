/*
 *  ClienteBT.java
 *
 *  Created on 18 de enero de 2005, 17:31
 */
package CartasMovil;

import javax.microedition.io.*;
import javax.bluetooth.*;
import MazoMovil.*;
import java.io.*;
import javax.microedition.lcdui.*;
import java.util.Vector;
import javax.microedition.rms.*;

/**
 *  Esta clase representa un cliente de Bluetooth dise�ado para el intercambio
 *  de cartas del juego G�nesis. En esta aplicacion el cliente siempre
 *  coincide con el receptor de las cartas
 *
 *@author    Genesis
 */
public class ClienteBT {

	/**
	 *  Representa el dispositivo local de esta aplicaci�n. Es decir el
	 *  dispositivo que va a recibir las cartas.
	 */
	private LocalDevice dispLocal;
	/**
	 *  Representa la clase que descubre dispositivos/servicios Bluetooth
	 */
	private DiscoveryAgent da;
	/**
	 *  Representa la conexi�n que est� abierta con el dispositivo que nos env�a
	 *  las cartas.
	 */
	private StreamConnection conexion;
	/**
	 *  Vector en el que se almacenan los dispositivos encontrados por el
	 *  DiscoveryAgent.
	 */
	private Vector dispEncontrados;
	/**
	 *  Vector en el que se almacenan los servicios que ofrece un dispositivo
	 */
	private Vector serviciosEncontrados;
	/**
	 *  Representa el objeto que se encarga de escuchar los eventos que ocurren
	 *  en la clase ClienteBT.<br>
	 *  En nuestro caso es el MIDlet de la aplicaci�n
	 */
	private CommandListener cl;
	/**
	 *  Display en el que se muestran las GUI
	 */
	private Display display;
	/**
	 *  GUI que se presenta al usuario durante la ejecuci�n del Cliente Bluetooth
	 */
	private ClienteGUI gui;
	/**
	 *  Canal de entrada de datos. Est� abierto sobre la conexi�n que se ha
	 *  establecido previamente
	 */
	private DataInputStream dis;
	/**
	 *  Canal de salida de datos. Est� abierto sobre la conexi�n que se ha
	 *  establecido previamente
	 */
	private DataOutputStream dos;
	/**
	 *  Usuario que est� intentando recibir cartas
	 */
	private String usuario;
	/**
	 *  Conjunto de cartas que se reciben
	 */
	private CartaMovil[] cartas;

        /*deberiamos pensar si propietario== usuario....*/
        private String Propietario=null;


	/**
	 *  Constructor de la clase ClienteBT.<br>
	 *
	 *
	 *@param  cl       es el objeto que se va a encargar de crear escuchar los
	 *      eventos producidos en este objeto
	 *@param  display  es el display en que se va a mostrar la GUI y los mensajes
	 *      de error que se produzcan durante el establecimiento de la conexi�n
	 *      BT y la recepcion
	 *@param  usuario  es el due�o de la baraja de cartas a la que se van a
	 *      a�adir las cartas recibidas. Cuando se vaya a recibir de un PC, este
	 *      par�metro ser� null, ya que no hay ninguna baraja en el m�vil
	 */
	public ClienteBT(CommandListener cl, Display display, String usuario) {
		this.cl = cl;
		this.display = display;
		this.usuario = usuario;
		gui = new ClienteGUI(cl, this, display);
		display.setCurrent(gui);
		dispEncontrados = new Vector();
		serviciosEncontrados = new Vector();
		buscaDisp();
	}


	/**
	 *  Este m�todo se encarga de buscar los dispositivos que est�n alrdedor de
	 *  nuestro dispLocal.<br>
	 *  Para ello hace uso del DiscoveryAgent da.<br>
	 *  Si aparece alg�n error durante este proceso mostrar� una pantalla de
	 *  alarma informando del mismo.
	 */
	public void buscaDisp() {
		dispEncontrados.removeAllElements();
		serviciosEncontrados.removeAllElements();
		try {
			dispLocal = LocalDevice.getLocalDevice();
			dispLocal.setDiscoverable(DiscoveryAgent.GIAC);
			da = dispLocal.getDiscoveryAgent();
			da.startInquiry(DiscoveryAgent.GIAC, new Listener());
		}
		catch (Exception e) {
			gui.mostrarAlarma(0, display, e);
			return;
		}
	}


	/**
	 *  Intenta iniciar un comunicaci�n con el dispositivo seleccionado por el
	 *  usuario. Si aparece alg�n error durante este proceso mostrar� una
	 *  pantalla de alarma informando del mismo.
	 *
	 *@param  indice  El dispositivo con el intetamos conectar.
	 */
	public void conecta(int indice) {
		if (indice == -1 || indice >= dispEncontrados.size()) {
			gui.mostrarAlarma(0, display, null);
			return;
		}
		else {
			serviciosEncontrados.removeAllElements();
			//Buscamos el servicio de puerto serie en el dispositivo seleccionado
			RemoteDevice dispRemoto = (RemoteDevice) dispEncontrados.elementAt(indice);
			try {
				//Buscamos en el puerto serie 0x1101
				da.searchServices(null, new UUID[]{new UUID(0xABBA)}, dispRemoto, new Listener());
			}
			catch (BluetoothStateException be) {
				//TODO mostrar el mensaje de que se no est� disponible este dispositivo en estos momentos.
				gui.mostrarAlarma(0, display, be);
			}
		}
	}


	/**
	 *  M�todo que se encarga de canalizar toda la informaci�n que se
	 *  env�a/recibe en la conexi�n.<br>
	 *  Se env�a el usuario al servidor, luego se recibe el tipo de mensaje y a
	 *  partir de ah� sigue todo el proceso.<br>
	 *  <I>Es �ste y no los m�todos de dentro de �l o los que se llaman desde
	 *  estos, el que lleva el try/catch, ya que as� si hay alg�n error en
	 *  cualquier momento no se ejecuta nada de ese c�digo y nos aseguramos de
	 *  que la conexi�n quede cerrada y los DataStream tambien.</I>
	 */
	public void procesarInformacion() {
		try {
			enviarUsuario();
			recibirTipoMensaje();
		}
		catch (Exception e) {
			gui.mostrarAlarma(0, display, e);
		}
	}


	/**
	 *  Env�a al servidor las claves de las cartas que el ReceptorBaraja ha
	 *  podido a�adir a la baraja.
	 *
	 *@param  claves      Son los c�digos de identificaci�n de las cartas que se
	 *      han podido a�adir a la baraja.
	 *@throws  Exception  Lanza cualquier excepci�n que se produzca durante la
	 *      transmisi�n
	 */
	public void enviaConfirmadas(int[] claves) throws Exception {
		dos.writeInt(claves.length);
		for (int i = 0; i < claves.length; i++) {
			dos.writeInt(claves[i]);
		}
		dos.flush();
	}


	/**
	 *  Cierra la conexi�n con el servidor.
	 *
	 *@throws  Exception  Lanza cualquier excepci�n que se produzca durante la
	 *      transmisi�n
	 */
	public void cierraConexion() throws Exception {
		dis.close();
		dos.close();
		conexion.close();
	}


	/**
	 *  Se encarga de enviar al servidor el nombre del usuario que va a recibir
	 *  (null en caso de que se reciba del PC)
	 *
	 *@throws  Exception  lanza cualquier excepcion que se produzca durante la
	 *      emisi�n de este mensaje.
	 */
	private void enviarUsuario() throws Exception {
		ServiceRecord sr = ((ServiceRecord) serviciosEncontrados.elementAt(0));
		String URL = sr.getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
		conexion = (StreamConnection) Connector.open(URL);
		dos = conexion.openDataOutputStream();
		dis = conexion.openDataInputStream();
		//Escribimos datos en el stream
		dos.writeUTF(usuario);
		dos.flush();
	}


        /**
         *  Se encarga de leer el tipo de mensaje que le env�a el servidor.<br>
         *  Puede ser RECHAZADO, MOVIL o PC(en un futuro)<br>
         *  Si es RECHAZADO, cierra la conexi�n y avisa al usuario de que ha sido
         *  rechazado por el servidor.<br>
         *  Si es MOVIL, lee el n�mero de cartas a recibir y las recibe.
         *
         *  Si es PC_NUEVO crea un nuevo RMS, lee el propietario de la baraja,
         *  el numero de cartas a recibir y las recibe
         *
         *  Si es PC_A�ADE estamos en el mismo caso que MOVIL
         *
         *@throws  Exception  lanza cualquier excepci�n que se produzca en la
         *      ejecuci�n del m�todo
         */
        private void recibirTipoMensaje() throws Exception {
                String tipo = dis.readUTF();
                if (tipo.equals("RECHAZADO")) {
                        dos.close();
                        dis.close();
                        conexion.close();
                        gui.mostrarAlarma(1, display, null);
                }
                else if (tipo.equals("MOVIL") ){
                        //si esto no funciona, comprobar que las cartas no se machacan cuando se
                        //a�aden varias veces desde el PC
                        int nCartas = dis.readInt();
                        leerCartas(nCartas);
                        cl.commandAction(new CommandCartas("CARTASRECIBIDAS", cartas), gui);
                }
                else if (tipo.equals("PC")) {
                	if (RecordStore.//Lo q sea para ver si existe)
                		{//Si existe
	                        int nCartas = dis.readInt();
	                        leerCartas(nCartas);
	                        cl.commandAction(new CommandCartas("CARTASRECIBIDAS", cartas), gui);
	                     }else{
	                        //fijamos el atributo propietario;
	                        propietario= dis.readUTF();
	                        int nCartas = dis.readInt();
	                        leerCartas(nCartas);
	                        cl.commandAction(new CommandCartas("CARTASRECIBIDAS", cartas), gui);
	                        cl.commandAction(new CommandPropietario("PROPIETARIO", propietario), gui);
                    	}
                	}
                }
	}


	/**
	 *  Este m�todo se encarga de leer el n�mero de cartas que se le indica.<br>
	 *  Las cartas que lee son almacenadas en el atributo cartas[].
	 *
	 *@param  nCartas     Es el n�mero de cartas que vamos a recibir.
	 *@throws  Exception  Lanza cualquier excepci�n que se produzca durante la
	 *      recepci�n
	 */
	private void leerCartas(int nCartas) throws Exception {
		cartas = new CartaMovil[nCartas];
		int j;
		int id;
		int version;
		String[] info = new String[8];
		for (int i = 0; i < nCartas; i++) {
			id = dis.readInt();
			version = dis.readInt();
			for (j = 0; j < 8; j++) {
				info[j] = dis.readUTF();
			}
			cartas[i] = new CartaMovil(id, version, info);
		}
	}


	/**
	 *  Clase interna que implementa el interfaz DiscoveryListener y que se
	 *  encarga de definir el comportamiento a la hora de buscar servicios y
	 *  dispositivos.
	 *
	 *@author    Genesis
	 */
	public class Listener implements DiscoveryListener {

		/**
		 *  Define el comportamiento a seguir al encontrar un dispositivo, en
		 *  nuestro caso, a�adirlo al vector dispEncontrados.
		 *
		 *@param  dispositivoRemoto  Description of Parameter
		 *@param  clase              Description of Parameter
		 */
		public void deviceDiscovered(RemoteDevice dispositivoRemoto, DeviceClass clase) {
			dispEncontrados.addElement(dispositivoRemoto);
		}


		/**
		 *  Define el comportamiento a seguir al terminar de buscar dispositivos, en
		 *  nuestro caso, mostar los dispositivos encontrados o avisar de que no hay
		 *  ninguno.
		 *
		 *@param  completado  Description of Parameter
		 */
		public void inquiryCompleted(int completado) {
			if (dispEncontrados.size() == 0) {
				gui.ningunDisp();
			}
			else {
				gui.mostrarDispositivos(dispEncontrados);
			}
		}


		/**
		 *  Define el comportamiento a seguir al encontrar un servicio, en nuestro
		 *  caso, a�adirlo al vector serviciosEncontrados.
		 *
		 *@param  transID     Description of Parameter
		 *@param  servRecord  Description of Parameter
		 */
		public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
			for (int i = 0; i < servRecord.length; i++) {
				ServiceRecord record = servRecord[i];
				serviciosEncontrados.addElement(record);
			}
		}


		/**
		 *  Define el comportamiento a seguir al terminar de buscar servicios, en
		 *  nuestro caso, procesar la informaci�n o avisar de que ese dispositivo no
		 *  tiene servicios a nuestro gusto y mostrar los dispositivos de nuevo.
		 *
		 *@param  transID   Description of Parameter
		 *@param  respCode  Description of Parameter
		 */
		public void serviceSearchCompleted(int transID, int respCode) {
			//Si encontramos un servicio, lo usamos para mandar el mensaje(todos los
			//servicios que hemos buscado son de puerto serie)
			if (serviciosEncontrados.size() > 0) {
				procesarInformacion();
			}
			else {
				gui.mostrarAlarma(2, display, null);
				gui.mostrarDispositivos(dispEncontrados);
			}
		}
	}

}
