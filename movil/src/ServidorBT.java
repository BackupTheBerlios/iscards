package CartasMovil;

import javax.microedition.io.*;
import javax.bluetooth.*;
import MazoMovil.*;
import java.io.*;
import javax.microedition.lcdui.*;

/**
 *  Esta clase proporciona la funcionalidad necesaria para manejar el Servidor
 *  de Blueetooth de nuestra aplicación.
 *
 *@author    Genesis
 */
public class ServidorBT implements Runnable {
	/**
	 *  Es una variable de sincronización
	 */
	public boolean aceptado;
	/**
	 *  Es una variable de sincronización
	 */
	public boolean listo;
	/**
	 *  Es un array con las cartas que recibe el receptor para comprobar si las
	 *  nuestras son correctas.
	 */
	public int[] recibidas;
	/**
	 *  Es el StreamConnectorNotifier que acepta las conexiones en esta
	 *  aplicacion. Es decir el que ejerce de servidor.
	 */
	private StreamConnectionNotifier servidor;
	/**
	 *  Es el dispositivo Bluetooth del móvil que hace de servidor.
	 */
	private LocalDevice dispLocal;
	/**
	 *  Es la conexión con el cliente
	 */
	private StreamConnection conexion;
	/**
	 *  Es el dispositivo del cliente
	 */
	private RemoteDevice dispRemoto;
	/**
	 *  Es la variable que indica si ya ha terminado la acción del servidor, ya
	 *  sea por algún error o porque el usuario vuelve atrás.
	 */
	private boolean terminado;
	/**
	 *  Es el canal de entrada de datos
	 */
	private DataInputStream dis;
	/**
	 *  Es el canal de salida de datos
	 */
	private DataOutputStream dos;
	/**
	 *  Es el array de cartas que pretendemos transmitir, si somos un movil
	 */
	private CartaMovil[] cartas;
        /**
         *  Es la baraja completa que pretendemos transmitir, si somos un PC
         */
        private BarajaMovil barajaMovil;

	/**
	 *  Es el objeto que escucha los eventos que se produzcan en esta clase.
	 */
	private CommandListener cl;
	/**
	 *  Es la interfaz gráfica para nuestra servidor
	 */
	private ServidorGUI gui;
        /**
         *  Es el atributo que nos permite diferenciar entre el envio a PC o a movil
         */
        private String selector;


	/**
	 *  Crea un nuevo servidor de Bluetooth
	 *  Este es el primer constructor que habia
         *
	 *@param  cl      Es el Listener que tratará los eventos que aparezcan en
	 *      este objeto
	 *@param  cartas  Es el array de cartas que pretendemos enviar
	 */
	public ServidorBT(CommandListener cl, CartaMovil[] cartas) {
		try {
                        selector="MOVIL";
			dispLocal = LocalDevice.getLocalDevice();
			dispLocal.setDiscoverable(DiscoveryAgent.GIAC);
    			this.cartas = cartas;
			this.cl = cl;
			gui = new ServidorGUI(this);
		}
		catch (Exception e) {
			//mostrar error al intentar descubrir el dispositivo local
		}
	}

        /**
         *  Crea un nuevo servidor de Bluetooth
         * Este segundo constructor se usara si enviamos al PC
         *
         *@param  cl      Es el Listener que tratará los eventos que aparezcan en
         *      este objeto
         *@param  baraja  Es la baraja completa que se le pasa al movil
         */
        public ServidorBT(CommandListener cl, BarajaMovil bm) {
                try {
                        selector="PC";
                        dispLocal = LocalDevice.getLocalDevice();
                        dispLocal.setDiscoverable(DiscoveryAgent.GIAC);
                        //esto esta mal
                        //necesitamos hacer un metodo que a this.cartas le meta el array de cartas
                        // deberia de estar en la clase BarajaMovil
                        this.cartas = null;
                        this.barajaMovil=bm;
                        this.cl = cl;
                        gui = new ServidorGUI(this);
                }
                catch (Exception e) {
                        //mostrar error al intentar descubrir el dispositivo local
                }
	}

	/**
	 *  Gets the mand attribute of the ServidorBT object
	 *
	 *@return    The mand value
	 */
	public CommandListener getCommandListener() {
		return cl;
	}


	/**
	 *  Gets the GUI attribute of the ServidorBT object
	 *
	 *@return    The GUI value
	 */
	public ServidorGUI getGUI() {
		return gui;
	}


	/**
	 *  Es el método que inicia la actividad del servidor
	 */
	public void iniciaServidor() {
		Thread hilo = new Thread(this);
		hilo.start();
	}


	/**
	 *  Main processing method for the ServidorBT object
	 */
	public void run() {
		String nombre = "Servicio de envio de cartas para Genesis";
		UUID uuid = new UUID(0xABBA);
		terminado = false;

		try {
			servidor = (StreamConnectionNotifier) Connector.open("btspp://localhost:" + uuid.toString() + ";name=" + nombre);

			ServiceRecord rec = dispLocal.getRecord(servidor);
			//Rellenamos el BluetoothProfileDescriptionList usando el SerialPort version 1
			DataElement e1 = new DataElement(DataElement.DATSEQ);
			DataElement e2 = new DataElement(DataElement.DATSEQ);
			e2.addElement(new DataElement(DataElement.UUID, new UUID(0x1101)));
			//agregamos el puerto serie
			e2.addElement(new DataElement(DataElement.INT_8, 1));
			//version 1
			e1.addElement(e2);
			//agregamos al service record el BluetoothProfileDescriptionList
			rec.setAttributeValue(0x0009, e1);
		}
		catch (Exception e) {
			//Mostrar mensaje de error al intentar inicializar el servidor
			terminado = true;
		}

		String usuario;

		while (!terminado) {
			conexion = null;
			dispRemoto = null;
			recibidas = null;
			gui.esperandoDispositivo();
			try {
				conexion = servidor.acceptAndOpen();
				dispRemoto = RemoteDevice.getRemoteDevice(conexion);

				dis = conexion.openDataInputStream();
				dos = conexion.openDataOutputStream();

				usuario = dis.readUTF();

				aceptado = false;
				listo = false;
				if (usuario != null) {
					gui.pedirConfirmacion(usuario);
				}
				else {
					listo = true;
				}
				while (!listo) {
				}
				;
				listo = false;
				if (!aceptado) {
					dos.writeUTF("RECHAZADO");
					dos.flush();
					dis.close();
					dos.close();
					conexion.close();
				}
				else {
					//TODO diferenciar el usuario es null
                                        //la conexion ha sido aceptada: diferenciamos si somos un movil o el PC
                                        if (selector.compareTo("MOVIL")){
                                          dos.writeUTF("MOVIL");
                                          dos.writeInt(cartas.length);
                                          for (int i = 0; i < cartas.length; i++) {
                                            enviaCarta(i);
                                          }
                                          dos.flush();
                                          recibidas = recibirCartasConfirmadas();
                                          cl.commandAction(new CommandIndices("RECEPCION", recibidas), gui);
                                          gui.mostrarConfirmadas(recibidas, cartas);
                                          terminado = true;
                                        }
                                        if (selector.compareTo("PC")){
                                          //somos el PC, luego enviamos solo los codigos de las cartas
                                          dos.writeUTF("PC");
                                          dos.writeInt(cartas.length);
                                          for (int i = 0; i < cartas.length; i++) {
                                            enviaCodigoCarta(i);
                                          }
                                          dos.flush();
                                          recibidas = recibirCartasConfirmadas();
                                          cl.commandAction(new CommandIndices("RECEPCION", recibidas), gui);
                                          gui.mostrarConfirmadas(recibidas, cartas);
                                          terminado = true;
                                        }

				}
			}
			catch (IOException ioe) {
				String m = "Se ha producido un error en la transmisión debido a la pérdida de conexión con el receptor de las cartas";
				Alert a = new Alert("ERROR", m, null, AlertType.ERROR);
				a.setTimeout(Alert.FOREVER);
				Display d = ((InicioMidlet) cl).getDisplay();
				d.setCurrent(a, ((InicioMidlet) cl).getOpciones());
				terminado = true;

			}
			catch (Exception e) {
				String m = "Se ha producido un error durante la transmisión de los datos\n" + e.getClass().getName() + "\n" + e.getMessage();
				Alert a = new Alert("ERROR", m, null, AlertType.ERROR);
				a.setTimeout(Alert.FOREVER);
				Display d = ((InicioMidlet) cl).getDisplay();
				d.setCurrent(a, ((InicioMidlet) cl).getOpciones());
				//Mostrar mensaje de error durante la transmision
				terminado = true;
			}
		}
	}


	/**
	 *  Este método envía la carta con índice i en el array de cartas que vamos a
	 *  enviar
	 *
	 *@param  i              el índice de la carta que vamos a enviar
	 *@exception  Exception  Lanza una excepción si falla algo en el envío
	 */
	private void enviaCarta(int i) throws Exception {
		CartaMovil c = (CartaMovil) cartas[i];
		dos.writeInt(c.getCodigoID());
		dos.writeInt(c.getversionBaraja());
		String[] info = c.getInfoCompleta();
		for (int j = 0; j < info.length; j++) {
			dos.writeUTF(info[j]);
		}
	}
        /**
         *  Este método envía el codigo de la carta con índice i en el array de cartas que vamos a
         *  enviar
         *
         *@param  i              el índice de la carta que vamos a enviar
         *@exception  Exception  Lanza una excepción si falla algo en el envío
         */
        private void enviaCodigoCarta(int i) throws Exception {
                CartaMovil c = (CartaMovil) cartas[i];
                dos.writeInt(c.getCodigoID());
	}


	/**
	 *  Recibe un array con los códigos de las cartas que ha poddido recibir el
	 *  cliente
	 *
	 *@return                devuelve el array de códigos de cartas que ha podido
	 *      leer el cliente
	 *@exception  Exception  Lanza una excepción si se produce algún fallo
	 *      durante la recepción
	 */
	private int[] recibirCartasConfirmadas() throws Exception {
		int cuantas = dis.readInt();
		int[] aux = new int[cuantas];
		for (int i = 0; i < cuantas; i++) {
			aux[i] = dis.readInt();
		}
		return aux;
	}

}
