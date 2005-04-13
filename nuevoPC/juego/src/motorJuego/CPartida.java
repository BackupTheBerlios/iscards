package motorJuego;

import coleccion.Coleccion;
import eventos.*;
import cartas.*;

import java.awt.*;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;

import java.io.FileInputStream;


import javax.swing.*;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: Juego de cartas de Rol</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Pedro Antonio y Rui Miguel
 * @version 1.0
 */

public class CPartida {

  /**
   * variable que indica que jugador está en su turno de partida
   */
  String turnoJugador;

  /**
   * variable que indica en que fase del turno se encuentra el jugador actual
   */
  int turnoPartida;

  /**
   * Variable para almacenar la mesa de juego
   */
  CMesa mesa;

  /**
   * Variable para almacenar la mano del jugador
   */
  CMano mano;

  /**
   * Variable para almacenar la mano del jugador contrario
   */
  CMano mano2;

  /**
   * Variable para almacenar el mazo del jugador
   */
  CMazo mazo;

  /**
   * Variable para almacenar el mazo del contrario
   */
  CMazo mazo2;

  /**
   * Variable para almacenar el mazo del cementerio del jugador
   */
  CMazo cementerio;

  /**
   * Variable para almacenar el mazo del cementerio del contrario
   */
  CMazo cementerio2;

  /**
   * Variable para almacenar las criaturas que pueden defender
   */
  Vector vectorCriaturasDefensa = new Vector();


  /**
   * Variable para almacenar las criaturas que estan atacando
   */
  Vector vectorCriaturasAtaque = new Vector();

 /**
   * Variable para almacenar el número de criaturas que estan defendiendo
   */
  int numCriaturasDefendiendo = 0;

  /**
   * Variable para saber si se esta defendiendo y falta seleccionar la criatura
   * a defender
   */
  boolean seleccionandoDefensa = false;

  /**
   * Variable para que carta esta defendiendo
   */
  CACarta cartaSeleccionadaDefensa;

  /**
   * tabla Hash con las cartas disponibles de la baraja
   */
  private Hashtable tablaCartasBaraja;

  /**
   * version de las cartas de las barajas del usuario
   */
  private int version;

  /**
   * raza de la carta 0=Angeles, 1=Demonios, 2=Humanos, 3=Inmortales
   */
  private int raza;

  /**
   * coleccion.car con la colección de todas las cartas del juego
   */
  private Coleccion coleccion;




//  CGestor gestor;

  /**
   * Constructora de la clase
   * @param m
   * @param ma
   */
  public CPartida(String nombreBaraja, Coleccion c){
    coleccion = c;
    //cargamos la baraja seleccionada
    this.cargarBarajaJuego(nombreBaraja);

 	//barajeamos el mazo
    this.barajearMazo(mazo);

	cementerio = new CMazo();
	cementerio2 = new CMazo();

    //inicializamos la mano del jugador para la partida
  	mano = new CMano();
//   	mano =  iniciarMano(mazo);
    //mano del jugador contrario
    mano2 = new CMano();
//	mano2 = iniciarMano(mazo2);

	//creamos la mesa de juego
    mesa = new CMesa();

    //inicializamos el tablero con las cartas iniciales (5 cartas de criatura aleatorias de nivel3...)
  	this.inicializarTablero();

    turnoJugador = "jugador1";
    turnoPartida = 5;//estamos en defensa para probar
    defensa();

    // Fases del turno del jugador
//    while(no fin de partida){
  		switch(turnoPartida){
			case 1: this.roba();
			case 2: this.girar();
			case 3: this.invocacion();
//			case 4: this.conjurosHechizos();
//			case 5: this.ataqueDefensa();
		}

    	this.pasaTurnoJugador();
//	}
  }


   /**
    * Función que lee de un archivo una cantidad de carácteres pedida
    * @param longitud cantidad de carácteres que hay que leer
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
    * Función que descodifica los bytes leidos
    * @param fraseBytes bytes leidos
    * @return String frase descodificada
    */
   private String descodificar(byte[] fraseBytes){
     String frase = "";
     int i=0;
     while (i<fraseBytes.length){
       char a;
       if(fraseBytes[i]<0)
         //carácter espepecial (ñ, á, é, í, ó, ú, ...)
         a = (char) (256 + fraseBytes[i] + 2);
       else
         a = (char)(fraseBytes[i] + 2);
       frase=frase+a;
       i++;
     }
     return frase;
   }

   /**
    * Función que carga una baraja determinada del usuario
    * @param fichBaraja
    */
   private void cargarBarajaJuego(String fichBaraja){
     try {
       FileInputStream archivo1 = new FileInputStream("../barajas/" + fichBaraja + ".bar");
       //variable para controlar los bytes que se deben leer
       int numeroDeBytesALeer = archivo1.read();
       //esto no sería necesario ya que el nombre ya lo sabemos, se puede controlar que coincidan por si hay error
       String nombreUsuario = descodificar(leerFrase(numeroDeBytesALeer, archivo1));
       version = archivo1.read();
       //leemos el nombre de la raza
       numeroDeBytesALeer = archivo1.read();
       String razaNom = descodificar(leerFrase(numeroDeBytesALeer, archivo1));
       if (razaNom.equals("Ángeles"))
         raza = 0;
       else if (razaNom.equals("Demonios"))
         raza = 1;
       else if (razaNom.equals("Humanos"))
         raza = 2;
       else if (razaNom.equals("Inmortales"))
         raza = 3;
       numeroDeBytesALeer = archivo1.read();
       tablaCartasBaraja = new Hashtable();
       while (numeroDeBytesALeer >= 0) { // i==-1 es fin de fichero
         String cantidad = descodificar(leerFrase(numeroDeBytesALeer,archivo1));
         numeroDeBytesALeer = archivo1.read();
         String codigoCarta = descodificar(leerFrase(numeroDeBytesALeer, archivo1));
         String nombre = coleccion.pedirCarta(codigoCarta).getNombre();
         tablaCartasBaraja.put(nombre, new Integer(cantidad));
         numeroDeBytesALeer = archivo1.read();
       }
       archivo1.close();
       //creamos el mazo de cartas de la baraja
       mazo = new CMazo();
       Enumeration keysCartasDelMazo = tablaCartasBaraja.keys();
       String barajaValue;
       while (keysCartasDelMazo.hasMoreElements()){
         String nombreCarta = (String)keysCartasDelMazo.nextElement();
         int repeticiones = ((Integer)tablaCartasBaraja.get(nombreCarta)).intValue();
         //crear la carta y añadirla al mazo, mirando si una carta tiene varias repeticiones añadirlas
         String codigo;
         for(int i=1; i<=repeticiones; i++){
		   codigo = coleccion.pedirCodigo(nombreCarta);
           CACarta carta = coleccion.pedirCarta(codigo);
           CACarta c = carta.dameUnaCopia();
           mazo.anadeCarta(c) ;
         }
       }
     }
     catch (Exception error) {
       //mostramos con un JOptionPane el error producido
       JOptionPane.showMessageDialog(new JOptionPane(), error.getMessage(), "Error cargar baraja",
                                     JOptionPane.ERROR_MESSAGE);
     }
   }

  /**
   * Función que barajea el mazo del jugador
   * @param m mazo a barajear
   */
  private void barajearMazo(CMazo m){
    CMazo mazoAux = m;
    //cogemos las cartas aleatorias del mazo
    int indiceAleatorio;
    int cant = m.getCartas().size();
    for(int i=0; i<cant; i++){
      indiceAleatorio = new Double(Math.random() * m.getCartas().size()).intValue();
      CACarta carta = (CACarta)m.getCartas().get(indiceAleatorio);
      m.getCartas().remove(indiceAleatorio);
      mazoAux.anadeCarta(carta);
    }
    m = mazoAux;
  }

  /**
   * Función que inicializa la mano del jugador, cogiendo 8 cartas para empezar
   * @param m mazo del que se roban las cartas
   */
  private CMano iniciarMano(CMazo m){
    //cogemos 8 cartas del mazo para la mano del jugador
    if(m!=null)
    	mano.roba(m);
    return mano;
  }

  /**
   * Función para inicializar el tablero de juego cargando las cartas en el mismo
   */
  private void inicializarTablero(){
    int indiceAleatorio;
    int i=0;
    int j=0;
    //iniciamos la mesa solamente 5 cartas (si el mazo no tiene suficientes cartas dejamos de buscar)
    while(i<5 && j<=mazo.getCartas().size()){
      //cogemos cartas del mazo aleatoriamente
      indiceAleatorio = new Double(Math.random() * mazo.getCartas().size()).intValue();
      CACarta carta = (CACarta)mazo.getCartas().get(indiceAleatorio);
      // pero solo las criaturas de nivel 3
      if(carta.getNivel() == 3){
        if(carta.getIdTipo().equals("Criatura")){
          mesa.getJugador1().getVectorCriaturas().add(carta);
          carta.baja();
          mazo.getCartas().remove(indiceAleatorio);
          i++;
        }
      }
      j++;
    }
  }

  /**
   * Función que roba una carta del mazo
   */
  public void roba(){
    int numCartas = mano.getCartas().size();
    if(numCartas < 8) {
      mano.getCartas().add(mazo.robaCarta());
    }
    turnoPartida = 2;
  }

  /**
   * Función que gira las criaturas para poder utilizarlas
   */
  public void girar(){
    // En principio giro todas las criaturas;
    int i;
    int salir =JOptionPane.showConfirmDialog(new JOptionPane(), "Desea salir?", "GENESIS version_1.0",
                                             JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    while(salir == 1){
      i = JOptionPane.showConfirmDialog(new JOptionPane(), "Seleccione carta a girar",
                                            "Girar Cartas",JOptionPane.DEFAULT_OPTION);
      if(i <= 8 && i >=1){
        // for(int i=0; i<=mesa.jugador1.vectorCriaturas.size(); i++){
        ((CACarta)(mesa.jugador1.vectorCriaturas.get(i))).setEstado(true);
        //}
      }
      salir =JOptionPane.showConfirmDialog(new JOptionPane(), "¿Desea girar otra carta?", "GENESIS version_1.0",
                                           JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
    turnoPartida = 3;
  }


  /**
   * Función que invoca criaturas de la mano a la mesa si el mana disponible es suficiente
   */
  public void invocacion(){
    int manaDisp = mesa.jugador1.manaDisponible;
    int i;
    int salir =JOptionPane.showConfirmDialog(new JOptionPane(), "¿Desea invocar carta?", "GENESIS version_1.0",
                                             JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
    while(salir == 1){
    	i =(int)JOptionPane.showConfirmDialog(new JOptionPane(), "Seleccione carta a invocar",
                                            "Girar Cartas",JOptionPane.DEFAULT_OPTION);
      	// En principio invoco a todas las criaturas para las que tenga maná
        //  for (int i=0; i<=mano.getCartas().size(); i++){
        if( i <= 8 && i >= 1){
        	CACarta cartaInvocada = (CACarta)mano.getCartas().get(i);
        	if (cartaInvocada.getIdTipo().equals("criatura")){
        		if(cartaInvocada.getCoste() <= manaDisp){
            		mesa.jugador1.vectorCriaturas.add(cartaInvocada);
	            	((CCriatura)mesa.jugador1.vectorCriaturas.get(i)).setEstado((true));
    	        	manaDisp = manaDisp -
                	((CCriatura)(mesa.jugador1.vectorCriaturas.get(i))).getCoste();
          		}
        	}

        	// Hago lo mismo con los conjuros
        	//for (int i=0; i<=mano.getCartas().size(); i++){
        	// CACarta cartaInvocada = (CACarta)mano.getCartas().get(i);
        	if (cartaInvocada.getIdTipo().equals("conjuro")){
	        	if(cartaInvocada.getCoste() <= manaDisp){
    	        	mesa.jugador1.vectorConjuros.add(cartaInvocada);
	    	        ((CCriatura)mesa.jugador1.vectorConjuros.get(i)).setEstado((true));
    	    	    manaDisp = manaDisp -
	                ((CConjuro)(mesa.jugador1.vectorConjuros.get(i))).getCoste();
        	    	//debo ejecutar el conjuro seleccionado. Todavía no válido Conjuros/hechizos
                	/*
                 	((CConjuro)(mesa.jugador1.vectorConjuros.get(i))).ejecuta();
                 	if (((CConjuro)(mesa.jugador1.vectorConjuros.get(i))).getDuracion() == 1){
                   	this.añadeCementerio(((CConjuro)(mesa.jugador1.vectorConjuros.get(i))));
                   	mesa.jugador1.vectorConjuros.remove(i);
                 	}
	                 */
	            }
        	}
    	}
    	salir =JOptionPane.showConfirmDialog(new JOptionPane(), "¿Desea invocar otra criatura?",
                                                    "GENESIS version_1.0", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
   	}
   	turnoPartida = 4;
 }


  /**
   * Función que invoca conjuros y/o hechizos
   */
  public void conjurosHechizos(){
    int manaDisp = mesa.jugador1.manaDisponible;
    // En principio lanzo todos los hechizos que me permita el maná
    for(int i = 0; i <= mano.getCartas().size(); i++){
      CACarta cartaInvocada = (CACarta)mano.getCartas().get(i);
      if(cartaInvocada.getCoste() <= manaDisp){
        mesa.jugador1.vectorHechizos.add(cartaInvocada);
        ((CACarta)mesa.jugador1.vectorHechizos.get(i)).setEstado((true));
        manaDisp = manaDisp - ((CACarta)(mesa.jugador1.vectorHechizos.get(i))).getCoste();
      }
    }
    turnoPartida = 5;
  }

  /**
   * Función que manda atacar a la carta que se le pasa como parámetro
   * @param carta
   */
  public void ataque(){
	//mandar cartas que quedan por defender mediante eventoAtaque (al girar)
	//nos devuelve la asignacion de la defensa mediante eventoDefensa (asociandoCartas al girar)
	//mostramos asignaciones

	/********************************
	//lanzamos Hechizos
		//espera hasta pulsar terminar
		//pasamos hechizos mediante eventoHechizos

	//El rival lanza hechizos
		//nos pasa hechizos mediante eventoHechizos

	//mostramos asignaciones de los hechizos
	********************************/

	//resolucion (y mandar los resultados)
  }

  /**
   * Función que manda atacar a la carta que se le pasa como parámetro
   * @param carta
   */
  public void defensa(){
    vectorCriaturasAtaque = new Vector();
    for (int i = 0; i < mesa.getJugador2().getVectorCriaturas().size(); i++) {
      //miramos todas las cartas nuestras con las que atacamos
      if (! ( (CACarta) mesa.getJugador2().getVectorCriaturas().get(i)).
          getEstado()) {
        vectorCriaturasAtaque.add(mesa.getJugador2().getVectorCriaturas().get(i));
      }
    }
    while (vectorCriaturasAtaque.size() > 0) {
      numCriaturasDefendiendo = 0;
      vectorCriaturasDefensa = new Vector();
      for (int i = 0; i < mesa.getJugador1().getVectorCriaturas().size(); i++) {
        if (((CACarta) mesa.getJugador1().getVectorCriaturas().get(i)).getEstado()) {
          //miramos todas las cartas nuestras que estan enderezadas
          vectorCriaturasDefensa.add(mesa.getJugador1().getVectorCriaturas().get(i));
        }
      }
      if (vectorCriaturasDefensa.size() != 0) {
        //defendemos con las enderezadas
        while (vectorCriaturasDefensa.size() > numCriaturasDefendiendo &&
               vectorCriaturasAtaque.size() > 0) {
          //tenemos el acabarDefensa enable==false;

        }
        //acabarDefensa enable==true;

        //al pulsar terminar EventoDefender = new EventoDefender();
      }
      else {
        //defendemos con las giradas
        for (int i = 0; i < mesa.getJugador1().getVectorCriaturas().size(); i++) {
          vectorCriaturasDefensa.add(mesa.getJugador1().getVectorCriaturas().get(i));
        }
        while (vectorCriaturasDefensa.size() > numCriaturasDefendiendo &&
               vectorCriaturasAtaque.size() > 0) {
          //tenemos el acabarDefensa enable==false;

        }
        //acabarDefensa enable==true;

        //al pulsar terminar EventoDefender = new EventoDefender();
      }
    }
    //cambio de turno para el jugador atacante y para nosotros
    //atacante:  turno = 0;
    //nosotro: turno = 1; o turno++;
  }

  /**
   * Función que pasa el turno al jugador rival
   */
  public void pasaTurnoJugador(){
    turnoJugador = "jugador2";
  }

  /**
   * Función que devuelve la mano del jugador
   * @return
   */
  public CMano getMano(){
    return mano;
  }

  /**
   * Función que devuelve la mano del contrario
   * @return
   */
  public CMano getMano2(){
    return mano2;
  }

  /**
   * Función que devuelve el mazo del jugador
   * @return
   */
  public CMazo getMazo(){
    return mazo;
  }

  /**
   * Función que devuelve el mazo del contrario
   * @return
   */
  public CMazo getMazo2(){
    return mazo2;
  }

  /**
   * Función que devuelve si se esta seleccionando la defensa de una criatura
   * @return
   */
  public CMesa getMesa(){
    return mesa;
  }

  /**
   * Función que devuelve la posicion en el vector de las criaturas, -1 si no pertenece o no esta
   * @return
   */
  public int getPosicionCriatura(CACarta carta, boolean perteneceContrario){
    if (perteneceContrario){
      if (mesa.getJugador2().getVectorCriaturas()==null)
        System.out.println("nuuuuuuuuuuuull criaturas contrario");
      else
        return mesa.getJugador2().getVectorCriaturas().indexOf(carta);
    }
    else{
      if (mesa.getJugador1().getVectorCriaturas()==null)
        System.out.println("nuuuuuuuuuuuull criaturas propias");
      else
        return mesa.getJugador1().getVectorCriaturas().indexOf(carta);
    }
    return 0;
  }

  /**
   * Función que devuelve la mano del jugador
   * @return
   */
  public int getTurnoPartida(){
    return turnoPartida;
  }

  /**
   * Función que devuelve el vector con las crituras que pueden defender
   * @return
   */
  public Vector getVectorCriaturasDefensa(){
    return vectorCriaturasDefensa;
  }

  /**
   * Función que devuelve el vector con las crituras que pueden defender
   * @return
   */
  public Vector getVectorCriaturasAtaque(){
    return vectorCriaturasAtaque;
  }

  /**
   * Función que devuelve el numero de criaturas que estan defendiendo a otras
   * @return
   */
  public int getNumCriaturasDefendiendo(){
    return numCriaturasDefendiendo;
  }

  /**
   * Función que actualiza el numero de criaturas que estan defendiendo a otras
   * @return
   */
  public void añadeUnoNumCriaturasDefendiendo(){
    numCriaturasDefendiendo ++;
  }

  /**
   * Función que actualiza el numero de criaturas que estan defendiendo a otras
   * @return
   */
  public void quitaUnoNumCriaturasDefendiendo(){
    numCriaturasDefendiendo --;
  }

  /**
   * Función que actualiza si se esta seleccionando la defensa de una criatura
   * @return
   */
  public void setSeleccionandoDefensa(boolean seleccion){
    seleccionandoDefensa = seleccion;
  }

  /**
   * Función que devuelve si se esta seleccionando la defensa de una criatura
   * @return
   */
  public boolean getSeleccionandoDefensa(){
    return seleccionandoDefensa;
  }

  /**
   * Función que actualiza si se esta seleccionando la defensa de una criatura
   * @return
   */
  public void setCartaSeleccionadaDefensa(CACarta cartaSeleccion){
    cartaSeleccionadaDefensa = cartaSeleccion;
  }

  /**
   * Función que devuelve si se esta seleccionando la defensa de una criatura
   * @return
   */
  public CACarta getCartaSeleccionadaDefensa(){
    return cartaSeleccionadaDefensa;
  }

  /**
   * Función que añade una carta al cementerio
   * @param carta
   */
  public void añadeCementerio(CACarta carta){
    cementerio.anadeCarta(carta);
  }

  /**
   *Funcion que actualiza el estado de la partida mediante un evento que recibe
   * y que puede estar originado por el otro jugador o por la inteligencia
   *artificial del juego
   *@param e Eventos
   */
  public void actualizaPorEvento(Eventos e){
  	if(e.getTipoEvento()=="bajada"){
    	EventoBajada eb =(EventoBajada)e;		
      	System.out.println("Busca la carta con posicion "+eb.getPosicion()+" y codigo "+eb.getCodigo()+" para aplicar el cambio de tipo "+eb.getTipoEvento());
      	//copiamos la carta que estaba en la mano
      	CACarta c = (CACarta)(mano2.getCartas().elementAt(Integer.parseInt(eb.getPosicion())));
    	//eliminamos la carta de la mano
      	mano2.getCartas().removeElementAt(Integer.parseInt(eb.getPosicion()));
      	//bajamos la carta a la mesa y la colocaremos en su vector correspondiente
      	/*EL PUTO RUY QUIERE QUE CAMBIE ESTO CON EL JODIDO "ID_TIPO", VERLO*/
      	if (c instanceof CCriatura){
        	mesa.getJugador2().getVectorCriaturas().addElement(c);
      	}
      	else if(c instanceof CHechizo){
        	mesa.getJugador2().getVectorHechizos().addElement(c);
      	}
      	else if(c instanceof CConjuro){
        	mesa.getJugador2().getVectorConjuros().addElement(c);
      	}
    }
    else if(e.getTipoEvento()=="ataque"){
      	EventoAtaque ea =(EventoAtaque)e;	
      	System.out.println("Busca la carta con posicion "+ea.getPosicion()+" y codigo "+ea.getCodigo()+" para aplicar el cambio de tipo "+ea.getTipoEvento());
      	/*cambiamos el estado; de enderezada a girada o de girada a enderezada*/
      	/*LOS HECHIZOS Y CONJUROS DE MOMENTO NO TIENEN LA OPCION DE GIRARSE*/
      	boolean estadoant = ((CACarta)(mesa.getJugador2().getVectorCriaturas().elementAt(Integer.parseInt(ea.getPosicion())))).getEstado();
      	((CACarta)(mesa.getJugador2().getVectorCriaturas().elementAt(Integer.parseInt(ea.getPosicion())))).setEstado(!estadoant);
    }
    else if(e.getTipoEvento()=="defensa"){
      	Color c;
      	/*para no repetir colores he intentar que se utilicen colores que no
       	molesten con el fondo de las pantallas hemos incluido estas comparaciones*/
      	if(((EventoDefensa)e).getPosicion().equals("0")){
        	c = Color.orange;
      	}
      	else if(((EventoDefensa)e).getPosicion().equals("1")){
        	c = Color.pink;
      	}
      	else if(((EventoDefensa)e).getPosicion().equals("2")){
        	c = Color.white;
      	}
      	else if(((EventoDefensa)e).getPosicion().equals("3")){
        	c = Color.yellow;
      	}
      	else if(((EventoDefensa)e).getPosicion().equals("4")){
        	c = Color.cyan;
      	}
      	else if(((EventoDefensa)e).getPosicion().equals("5")){
        	c = Color.gray;
      	}
      	else if(((EventoDefensa)e).getPosicion().equals("6")){
        	c = Color.magenta;
      	}
      	//sino forzamos a que sea la 8ªposicion del vector
      	else{
        	c = Color.green;
      	}
      	/*una vez seleccionado el color se los incluimos a las 2 cartas para
       	emparejar atacante/defensor*/
      	//atacante en la mano del jugador 1
      	((CCriatura)(mesa.getJugador1().getVectorCriaturas().elementAt(Integer.parseInt(((EventoDefensa)e).getPosicion())))).setColor(c);
      	//defensor en la mano del jugador 2
      	((CCriatura)(mesa.getJugador2().getVectorCriaturas().elementAt(Integer.parseInt(((EventoDefensa)e).getPosicion2())))).setColor(c);
      	System.out.println("El atacante con posicion "+(((EventoDefensa)e).getPosicion())+" y su defensor con posicion "+(((EventoDefensa)e).getPosicion2())+" tienen el color "+c);
    }
    else if(e.getTipoEvento()=="cambio turno"){
    	/*OBS TODO ESTO ESTA HECHO CON LOS TURNOS PROVISIONALES Y HAY QUE HABLAR
    	 CON TONY PARA CONFIRMAR*/
    	/*si recibe un evento de cambio de turno pasara la partida al turno 
    	 *correspondiente, que sera el siguiente por defecto*/
    	if ((this.turnoPartida>=0)&(this.turnoPartida<5)){
    		this.turnoPartida++;
    	}
    	/*despues del turno de ataque se cambia al otro jugador y se pasa al 
    	turno de defensa de el*/
    	else if (this.turnoPartida==5){
    		//pasamos al turno de defensa del contrincante
    		this.turnoPartida=0;
    		//ponemos que es el turno del contrincante
    		this.turnoJugador="jugador1";	
    	}
  	}
  }
}