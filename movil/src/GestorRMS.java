/*
 *  GestorRMS.java
 *
 *  Created on 15 de diciembre de 2004, 17:07
 */
package MazoMovil;

import java.util.Hashtable;
import java.util.Enumeration;
import java.io.*;
import javax.microedition.rms.*;
/**
 *  Clase que se encarga de manejar todo el almacenamiento persistente
 *
 *@author    Genesis
 */
public class GestorRMS {
	private RecordStore rs;
	private Hashtable bar;
	private int version;
	private String raza;


	/**
	 *  Constructor de la clase. No realiza ningún trabajo
	 */
	public GestorRMS() {
		try {
			rs = RecordStore.openRecordStore("baraja", false);
			rs.closeRecordStore();
		}
		catch (RecordStoreNotFoundException e) {
			try {
				forzar();
			}
			catch (Exception ex) {
				System.out.println("Hay algún fallo al forzar");
				ex.printStackTrace();
			}
		}
		catch (Exception e) {
		}
	}


	/**
	 *  Clase que se encarga de leer el número de cartas almacenadas en la
	 *  memoria del móvil
	 *
	 *@return                El número de cartas de la baraja almacenada en
	 *      memoria
	 *@exception  Exception  Description of Exception
	 *@throws  Propaga       cualquier excepción que se produzca manejando los
	 *      registros persistentes. Así mantenemos la coherencia entre los datos
	 *      almacenados y los datos en la aplicación
	 */
	public int leerNumeroCartas() throws Exception {
		rs = RecordStore.openRecordStore("baraja", false);
		byte[] registro = new byte[500];
		int nCartas = 0;
		ByteArrayInputStream bais = new ByteArrayInputStream(registro);
		DataInputStream dis = new DataInputStream(bais);
		dis.reset();
		rs.getRecord(1, registro, 0);
		nCartas = dis.readInt();
		bais.reset();
		bais.close();
		dis.close();
		registro = null;
		rs.closeRecordStore();
		return nCartas;
	}


	/**
	 *  Lee la versión de la baraja del almacenamiento persistente.
	 *
	 *@return                La versión de la baraja almacenada en memoria
	 *@exception  Exception  Description of Exception
	 *@throws  Propaga       cualquier excepción que se produzca manejando los
	 *      registros persistentes. Así mantenemos la coherencia entre los datos
	 *      almacenados y los datos en la aplicación
	 */
	public int leerVersion() throws Exception {
		rs = RecordStore.openRecordStore("baraja", false);
		byte[] registro = new byte[500];
		int version = 0;
		ByteArrayInputStream bais = new ByteArrayInputStream(registro);
		DataInputStream dis = new DataInputStream(bais);
		dis.reset();
		rs.getRecord(2, registro, 0);
		version = dis.readInt();
		bais.reset();
		dis.close();
		bais.close();
		rs.closeRecordStore();
		return version;
	}


	/**
	 *  Lee la raza a la que pertenece la baraja
	 *
	 *@return                La raza de la baraja almacenada en memoria
	 *@exception  Exception  Description of Exception
	 *@throws  Propaga       cualquier excepción que se produzca manejando los
	 *      registros persistentes. Así mantenemos la coherencia entre los datos
	 *      almacenados y los datos en la aplicación
	 */
	public String leerRaza() throws Exception {
		rs = RecordStore.openRecordStore("baraja", false);
		byte[] registro = new byte[500];
		String raza = "ninguna";
		ByteArrayInputStream bais = new ByteArrayInputStream(registro);
		DataInputStream dis = new DataInputStream(bais);
		dis.reset();
		rs.getRecord(3, registro, 0);
		raza = dis.readUTF();
		bais.reset();
		dis.close();
		bais.close();
		rs.closeRecordStore();
		return raza;
	}


	/**
	 *  Lee el nombre del propietario de la baraja.
	 *
	 *@return                El nombre del propietario de la baraja.
	 *@exception  Exception  Description of Exception
	 *@throws  Propaga       cualquier excepción que se produzca manejando los
	 *      registros persistentes. Así mantenemos la coherencia entre los datos
	 *      almacenados y los datos en la aplicación
	 */
	public String leerPropietario() throws Exception {
		rs = RecordStore.openRecordStore("baraja", false);
		byte[] registro = new byte[500];
		String propietario = "ninguno";
		ByteArrayInputStream bais = new ByteArrayInputStream(registro);
		DataInputStream dis = new DataInputStream(bais);
		dis.reset();
		rs.getRecord(4, registro, 0);
		propietario = dis.readUTF();
		bais.reset();
		dis.close();
		bais.close();
		rs.closeRecordStore();
		return propietario;
	}


	/**
	 *  Guarda todos los datos de la baraja en el almacenamiento persistente
	 *
	 *@param  b              Es la baraja que vamos a guardar
	 *@exception  Exception  Description of Exception
	 *@throws  Propaga       cualquier excepción que se produzca manejando los
	 *      registros persistentes. Así mantenemos la coherencia entre los datos
	 *      almacenados y los datos en la aplicación
	 */
	public void guardarBaraja(BarajaMovil b) throws Exception {
		try {
			RecordStore.deleteRecordStore("baraja");
		}
		catch (Exception e) {
			System.out.println("PETA AL BORRAR");
			e.printStackTrace();
		}
		rs = RecordStore.openRecordStore("baraja", true);
		guardaNumeroCartas(b.getNumeroCartas());
		guardaVersionBaraja(b.getversionBaraja());
		guardaRaza(b.getRaza());
		guardaPropietario(b.getPropietario());

		CartaMovil c;
		int i;
		int j;
		String[] inf = new String[7];
		Enumeration e = b.enumeraCartas();
		while (e.hasMoreElements()) {
			c = ((CartaMovil) e.nextElement());
			for (i = 0; i < c.getCantidad(); i++) {
				guardaCarta(c);
			}
		}
		rs.closeRecordStore();
	}


	/**
	 *  Lee todos los datos de una baraja guardada en el almacenamiento
	 *  persitente
	 *
	 *@return                Devuelve todas las cartas que había almacenadas en
	 *      la memoria persistente en un HashTable
	 *@exception  Exception  Description of Exception
	 *@throws  Propaga       cualquier excepción que se produzca manejando los
	 *      registros persistentes. Así mantenemos la coherencia entre los datos
	 *      almacenados y los datos en la aplicación
	 */
	public Hashtable leerBaraja() throws Exception {
		int nCartas = leerNumeroCartas();
		byte[] registro = new byte[500];
		ByteArrayInputStream bais = new ByteArrayInputStream(registro);
		DataInputStream dis = new DataInputStream(bais);
		dis.reset();
		bar = new Hashtable(nCartas);
		int j;
		int codID;
		int vCarta;
		String[] info = new String[8];
		CartaMovil c;
		rs = RecordStore.openRecordStore("baraja", false);

		for (int i = 5; i < 5 + nCartas; i++) {
			dis.reset();
			rs.getRecord(i, registro, 0);
			codID = dis.readInt();
			vCarta = dis.readInt();
			for (j = 0; j < 8; j++) {
				info[j] = dis.readUTF();
			}
			c = new CartaMovil(codID, vCarta, info);
			añadirABaraja(c);
		}
		rs.closeRecordStore();
		return bar;
	}



	/**
	 *  Guarda el número de cartas de la baraja en el almacenamiento persistente
	 *
	 *@param  n              Es el número de cartas que tiene la baraja.
	 *@exception  Exception  Description of Exception
	 *@throws  Propaga       cualquier excepción que se produzca manejando los
	 *      registros persistentes. Así mantenemos la coherencia entre los datos
	 *      almacenados y los datos en la aplicación
	 */
	private void guardaNumeroCartas(int n) throws Exception {
		byte[] registro;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeInt(n);
		dos.flush();
		registro = baos.toByteArray();
		rs.addRecord(registro, 0, registro.length);
		baos.close();
		dos.close();
	}


	/**
	 *  Guarda la versión de la baraja en el almacenamiento persistente
	 *
	 *@param  v              es la versión de la baraja
	 *@exception  Exception  Description of Exception
	 *@throws  Propaga       cualquier excepción que se produzca manejando los
	 *      registros persistentes. Así mantenemos la coherencia entre los datos
	 *      almacenados y los datos en la aplicación
	 */
	private void guardaVersionBaraja(int v) throws Exception {
		byte[] registro;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeInt(v);
		dos.flush();
		registro = baos.toByteArray();
		rs.addRecord(registro, 0, registro.length);
		dos.close();
		baos.close();
	}


	/**
	 *  Guarda en el almacenamiento persistente la raza de la baraja
	 *
	 *@param  r              Es la raza de la baraja
	 *@exception  Exception  Description of Exception
	 *@throws  Propaga       cualquier excepción que se produzca manejando los
	 *      registros persistentes. Así mantenemos la coherencia entre los datos
	 *      almacenados y los datos en la aplicación
	 */
	private void guardaRaza(String r) throws Exception {
		byte[] registro;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeUTF(r);
		dos.flush();
		registro = baos.toByteArray();
		rs.addRecord(registro, 0, registro.length);
		dos.close();
		baos.close();
	}


	/**
	 *  Guarda el nombre del propietario de la baraja en el almecenamiento
	 *  persistente.
	 *
	 *@param  p              El nombre del propietario
	 *@exception  Exception  Description of Exception
	 *@throws  Propaga       cualquier excepción que se produzca manejando los
	 *      registros persistentes. Así mantenemos la coherencia entre los datos
	 *      almacenados y los datos en la aplicación
	 */
	private void guardaPropietario(String p) throws Exception {
		byte[] registro;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.writeUTF(p);
		dos.flush();
		registro = baos.toByteArray();
		rs.addRecord(registro, 0, registro.length);
		baos.close();
		dos.close();
	}


	/**
	 *  Guarda la información de una carta en el almacenamiento persistente
	 *
	 *@param  c              Es la carta a guardar
	 *@exception  Exception  Description of Exception
	 *@throws  Propaga       cualquier excepción que se produzca manejando los
	 *      registros persistentes. Así mantenemos la coherencia entre los datos
	 *      almacenados y los datos en la aplicación
	 */
	private void guardaCarta(CartaMovil c) throws Exception {
		byte[] registro;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		String[] inf;
		int j;
		dos.writeInt(c.getCodigoID());
		dos.writeInt(c.getversionBaraja());
		inf = c.getInfoCompleta();
		for (j = 0; j < 8; j++) {
			dos.writeUTF(inf[j]);
		}
		dos.flush();
		registro = baos.toByteArray();
		int sig = rs.addRecord(registro, 0, registro.length);
		dos.close();
		baos.close();
	}


	/**
	 *  Añade una carta que hemos leído del RMS a un HashTable. Usado dentro del
	 *  método leerBaraja().
	 *
	 *@param  nueva  Es la carta que se va a añadir.
	 */
	private void añadirABaraja(CartaMovil nueva) {
		Integer codigo = new Integer(nueva.getCodigoID());

		if (bar.containsKey(codigo)) {
			((CartaMovil) bar.get(codigo)).aumentaNumero();
		}
		else {
			bar.put(new Integer(nueva.getCodigoID()), nueva);
			if (nueva.getversionBaraja() > version) {
				version = nueva.getversionBaraja();
			}
		}
	}


	/**
	 *  Description of the Method
	 *
	 *@exception  Exception  Description of Exception
	 */
	private void forzar() throws Exception {
		System.out.println("Estamos forzando");
		bar = new Hashtable();
		String[] inf = new String[8];
		inf[0] = "Alma del Limbo";
		inf[1] = "Inmortal";
		inf[2] = "8";
		inf[3] = "6";
		inf[4] = "Humana convertida en inmortal, no dudará en atacar al oponente con crueles técnicas.";
		inf[5] = "Hace tiempo que perdí algo que tú aún conservas: el dolor. Eso te hace débil";
		inf[6] = "2";
		inf[7] = "5";
		CartaMovil c1 = new CartaMovil(1, 1, inf);
		inf[0] = "Dios de lo Terrenal";
		inf[1] = "Inmortal";
		inf[2] = "11";
		inf[3] = "8";
		inf[4] = "De alma inmortal, este dios tiene características terrenales que le acercan a la raza humana.";
		inf[5] = "No conozco la piedad...si te enfrentas a mi, lucharás contra mi lado inmortal.";
		inf[6] = "3";
		inf[7] = "7";
		CartaMovil c2 = new CartaMovil(2, 1, inf);
		inf[0] = "Diosa de lo Elemental";
		inf[1] = "Inmortal";
		inf[2] = "5";
		inf[3] = "3";
		inf[4] = "Puede alterar las propiedades de aire, tierra, fuego y agua... dotándolas de poderes inimaginables.";
		inf[5] = "El enemigo no soy yo... es todo lo que está a tu alrededor.";
		inf[6] = "1";
		inf[7] = "3";
		CartaMovil c3 = new CartaMovil(3, 1, inf);
		inf[0] = "Diosa del Ejército";
		inf[1] = "Inmortal";
		inf[2] = "4";
		inf[3] = "3";
		inf[4] = "De inteligencia prodigiosa, esta diosa descubrirá tácticas para sorprender al enemigo durante la batalla.";
		inf[5] = "Grandes guerras se ganaron, con el único arma de la razón.";
		inf[6] = "1";
		inf[7] = "3";
		CartaMovil c4 = new CartaMovil(4, 1, inf);
		inf[0] = "Espíritu del Dragón";
		inf[1] = "Inmortal";
		inf[2] = "17";
		inf[3] = "12";
		inf[4] = "Aunque el más mínimo toque puede destruirlo, su ataque es insuperable en el mundo inmortal. Ayudará a incrementar el ataque de tu ejército.";
		inf[5] = "Un ser débil con un ataque infernal.";
		inf[6] = "3";
		inf[7] = "9";
		CartaMovil c5 = new CartaMovil(5, 1, inf);
		inf[0] = "Inmortal Errante";
		inf[1] = "Inmortal";
		inf[2] = "10";
		inf[3] = "4";
		inf[4] = "Su ferocidad en la lucha proviene del odio hacia el resto de razas. Ataca sin piedad bajo el amparo de la inmortalidad.";
		inf[5] = "El principio de mi odio es también el de tu peor pesadilla.";
		inf[6] = "2";
		inf[7] = "5";
		CartaMovil c6 = new CartaMovil(6, 1, inf);

		inf[0] = "Miserum Híbrido";
		inf[1] = "Inmortal";
		inf[2] = "9";
		inf[3] = "6";
		inf[4] = "Sus ataques son maleficios hacia el enemigo, que se convierten en realidad en pocas horas.";
		inf[5] = "Pronunció unas palabras en una lengua imposible de descifrar... similares a un canto fúnebre.";
		inf[6] = "2";
		inf[7] = "5";
		CartaMovil c7 = new CartaMovil(7, 1, inf);

		c1.aumentaNumero();
		c5.aumentaNumero();
		c5.aumentaNumero();
		bar.put(new Integer(c1.getCodigoID()), c1);
		bar.put(new Integer(c2.getCodigoID()), c2);
		bar.put(new Integer(c4.getCodigoID()), c4);
		bar.put(new Integer(c3.getCodigoID()), c3);
		bar.put(new Integer(c5.getCodigoID()), c5);
		bar.put(new Integer(c6.getCodigoID()), c6);
		bar.put(new Integer(c7.getCodigoID()), c7);

		rs = RecordStore.openRecordStore("baraja", true);

		guardaNumeroCartas(10);
		guardaVersionBaraja(1);
		guardaRaza("Inmortal");
		guardaPropietario("Sergio");
		Enumeration e = bar.elements();
		CartaMovil c;
		while (e.hasMoreElements()) {
			c = ((CartaMovil) e.nextElement());
			for (int j = 0; j < c.getCantidad(); j++) {
				guardaCarta(c);
			}
		}
		rs.closeRecordStore();
	}
}
