//package coleccion;

//import cartas.*;


import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.File;
import java.net.URL;
import javax.swing.*;
import java.util.Hashtable;


/**
 * <p>T�tulo: GENESIS</p>
 * <p>Descripci�n: Juego de cartas de Rol</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Miguel Cayeiro y Rui Miguel
 * @version 1.0
 */

public class Coleccion {

  /**
   * Variable en la cual se guardaran las cartas de la colecci�n a partir de los c�digos
   */
  private Hashtable BBDDCartas;

  /**
   * Variable en la cual se guardaran los c�digos de las cartas a partir de los nombres
   */
  private Hashtable BBDDCodigos;

  /**
   * Version de las cartas del juego
   */
  private int version;

  /**
   * Constructora de la clase Coleccion de cartas
   */
  public Coleccion() {
    //creamos una nueva tabla para la base de datos de las cartas
    BBDDCartas = new Hashtable();
    //creamos una nueva tabla para la base de datos de los c�digos
    BBDDCodigos = new Hashtable();
    cargarCartas();
  }

  /**
   * Funci�n que lee de un archivo una cantidad de car�cteres pedida
   * @param longitud cantidad de car�cteres que hay que leer
   * @param archivo archivo del que hay que leer
   * @return String con los datos leidos
   * @throws java.lang.Exception
   */
  private byte[] leerFrase(int longitud, FileInputStream archivo) throws Exception{
    int bytes;
    int i=0;
    byte[] frase=new byte[longitud];
    while(i<longitud){
      bytes= archivo.read();
      if (bytes==-1) //i==-1 es fin de fichero
        throw new Exception("La base de datos esta incompleta");
      frase[i]=(byte)bytes;
      i++;
    }
    return frase;
  }

  /**
   * Funci�n que descodifica los bytes leidos
   * @param fraseBytes bytes leidos
   * @return String frase descodificada
   */
  private String descodificar(byte[] fraseBytes){
    String frase = "";
    int i=0;
    while (i<fraseBytes.length){
      char a;
      if(fraseBytes[i]<0)
        //car�cter espepecial (�, �, �, �, �, �, ...)
        a = (char) (256 + fraseBytes[i] + 2);
      else
        a = (char)(fraseBytes[i] + 2);
      frase=frase+a;
      i++;
    }
    return frase;
  }

  /**
   * Funci�n que carga las cartas del archivo "Coleccion.car"
   */
  private void cargarCartas(){//a�adir la coleccion
    try {
      FileInputStream archivo1 = new FileInputStream("./documentos/Coleccion.car");
      //continuamos una colecci�n antigua luego hay que borrar lo que tenemos ahora mismo
      version = archivo1.read();
      int numeroDeBytesALeer; //variable para controlar los bytes que se deben leer
      numeroDeBytesALeer = archivo1.read();
      while (numeroDeBytesALeer >= 0) { // i==-1 es fin de fichero
        String codigo = descodificar(leerFrase(numeroDeBytesALeer, archivo1));

        numeroDeBytesALeer = archivo1.read();
        String raza = descodificar(leerFrase(numeroDeBytesALeer, archivo1));

        numeroDeBytesALeer = archivo1.read();
        String tipo = descodificar(leerFrase(numeroDeBytesALeer, archivo1));

        numeroDeBytesALeer = archivo1.read();
        String nombre = descodificar(leerFrase(numeroDeBytesALeer, archivo1));

        //el nivel al leerlo se descodifica solo
        Integer nivel = new Integer(archivo1.read());

        numeroDeBytesALeer = archivo1.read();
        String puntosEnLetra = descodificar(leerFrase(numeroDeBytesALeer, archivo1));
        Integer puntos = new Integer(puntosEnLetra);

        //el ataque al leerlo se descodifica solo
        Integer ataque = new Integer(archivo1.read());
        //la defensa al leerlo se descodifica solo
        Integer defensa = new Integer(archivo1.read());
        //el coste al leerlo se descodifica solo
        Integer coste = new Integer(archivo1.read());
        //la vida al leerlo se descodifica solo
        Integer vida = new Integer(archivo1.read());

        //los numeros de letras que hay que leer de descripMov tambi�n est�n codificado
        numeroDeBytesALeer = archivo1.read();
        numeroDeBytesALeer = (new Integer(descodificar(leerFrase(numeroDeBytesALeer,
            archivo1))).intValue());
        String comentarios = descodificar(leerFrase(numeroDeBytesALeer, archivo1));

        //los numeros de letras que hay que leer de descripHab tambi�n est�n codificado
        numeroDeBytesALeer = archivo1.read();
        numeroDeBytesALeer = (new Integer(descodificar(leerFrase(numeroDeBytesALeer,
            archivo1))).intValue());
        String habilidades = descodificar(leerFrase(numeroDeBytesALeer, archivo1));

        //creamos la nueva carta leida, dependiendo del tipo de carta y la a�adimos
        CACarta carta;
        if (tipo.equals("Conjuro")){
          carta = new CConjuro(nivel.intValue(), coste.intValue(), puntos.intValue(), codigo, nombre, raza, tipo,
                               comentarios, habilidades,false);
        }
        else if (tipo.equals("Criatura")){
          carta = new CCriatura(nivel.intValue(), coste.intValue(), puntos.intValue(), ataque.intValue(), defensa.intValue(),
                               codigo, nombre, raza, tipo, comentarios, habilidades,false);
        }
        else if (tipo.equals("Hechizo")){
          carta = new CHechizo(nivel.intValue(), coste.intValue(), puntos.intValue(), nombre, codigo, raza, tipo,
                               comentarios, habilidades,false);
        }
       else
          throw new Exception("No existe ese tipo");
        anadir(carta);
        numeroDeBytesALeer = archivo1.read();
      }
      archivo1.close();
    }
    catch (Exception error) {
      //mostramos con un JOptionPane el error producido
      JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error",
                                    JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Funci�n que a�ade una carta a la base de datos
   * @param c
   */
  private void anadir(CACarta c){
    BBDDCartas.put(c.getCodigo(), c);
    BBDDCodigos.put(c.getNombre(),c.getCodigo());
  }

  /**
   * Funci�n que devuelve el nombre de una carta segun su c�digo
   * @param codigo
   * @return
   * @throws java.lang.Exception
   */
  public CACarta pedirCarta(String codigo) throws Exception{
    if (!BBDDCartas.containsKey(codigo))
      //no se puede devolver la carta porque no existe la c�digo en la base de datos
      throw new Exception("Ese c�digo no existe");
    return (CACarta) BBDDCartas.get(codigo);
  }

  /**
   * Funci�n que devuelve el c�digo de una carta segun su nombre
   * @param nombre
   * @return
   * @throws java.lang.Exception
   */
  public String pedirCodigo(String nombre) throws Exception{
    if (!BBDDCodigos.containsKey(nombre))
      //no se puede devolver el c�digo de la carta porque no existe el nombre en la base de datos
      throw new Exception("Ese nombre no existe");
    return (String) BBDDCodigos.get(nombre);
  }

  /**
   * Funci�n que devuelve la versi�n de la colecci�n de cartas
   * @return
   */
  public int getVersion(){
    return version;
  }
}