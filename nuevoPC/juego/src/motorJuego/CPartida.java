package motorJuego;

import cartas.*;

import java.util.Vector;
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
   * Variable para almacenar el mazo del cementerio del jugador
   */
  CMazo cementerio;

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

//  CGestor gestor;

  /**
   * Constructora de la clase
   * @param m
   * @param ma
   * @param cem
   * @param vCriatJugador
   * @param vHechJugador
   * @param vConjJugador
   * @param m2
   * @param vCriatContrario
   * @param vHechContrario
   * @param vConjContrario
   */
  public CPartida(CMano m, CMazo ma, CMazo cem, Vector vCriatJugador, Vector vHechJugador, Vector vConjJugador,
                  CMano m2, Vector vCriatContrario, Vector vHechContrario, Vector vConjContrario){
    mano = m;
    mano2 = m2;
    mazo = ma;
    cementerio = cem;
    mesa = new CMesa(vCriatJugador, vHechJugador, vConjJugador,
    				 vCriatContrario, vHechContrario, vConjContrario);

    turnoJugador = "jugador1";

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

        //miramos todas las cartas nuestras con las que atacamos
        if ( ( (CACarta) mesa.getJugador1().getVectorCriaturas().get(i)).
            getEstado()) {
          vectorCriaturasDefensa.add(mesa.getJugador1().getVectorCriaturas().
                                     get(i));
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
        for (int i = 0; i < mesa.getJugador1().getVectorCriaturas().size(); i++) {
          vectorCriaturasDefensa.add(mesa.getJugador1().getVectorCriaturas().
                                     get(i));
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
   * Función que devuelve la mano del jugador
   * @return
   */
  public CMano getMano2(){
    return mano2;
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
}