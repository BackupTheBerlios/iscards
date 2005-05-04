package ia_cartas;
import jess.*;
import java.util.*;
import cartas.*;
import motorJuego.*;
import panelesInfo.PanelGenerico;
import eventos.*;
import javax.swing.*;


/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
public class Defender {
	/*
	 *  atributos de la clase
	 */
	private CPartida partida;
	private String archivo;

// private Rete rete;

	/*
	 *  constructor
	 */
	/**
	 *  Constructor for the Defender object
	 *
	 *@param  p  Description of Parameter
	 */
	public Defender(CPartida p) {
		partida = p;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  atacantesTratados  Description of Parameter
	 *@param  turnoDefensa       Description of Parameter
	 *@return                    Description of the Returned Value
	 */
	public Vector eventos(Vector atacantesTratados, int turnoDefensa) {
		/*
		 *  inicializamos el vector a devolver
		 */
		Vector v_Eventos = new Vector();
		/*
		 *  LA IDEA ES QUE A PARTIR DE AHORA SI QUE PODRAN DEFENDER MENOS DEFENSORES
		 *  QUE ATACANTES, AUNQUE LA DEFENSA TENGA QUE REALIZARSE EN VARIOS TURNOS
		 */
		Vector v_Atacantes = ((partida.getMesa()).getJugador1()).getVectorCriaturas();
		Vector v_Defensores = ((partida.getMesa()).getJugador2()).getVectorCriaturas();
		AlgoritmoB ab = new AlgoritmoB(v_Atacantes, v_Defensores);
		int turno = turnoDefensa;
		boolean nomuerto = true;
		int defendidos = 0;
		//ordena y selecciona tanto los atacantes como los defensores
		ab.ordenaAtacantes();
		//ahora ademas quitamos los atacantes que ya hayamos defendido
		for (int i = 0; i < atacantesTratados.size(); i++) {
			ab.quitaAtacante((CCriatura) (atacantesTratados.elementAt(i)));
		}
		ab.ordenaDefensores();
		while ((defendidos < ab.getAtacantes().size()) && (nomuerto)) {
			Vector solucion = ab.getSolucion();
			//solucion.size devuelve el numero de atacantes defendidos en este turno
			if (solucion != null) {
				//if (solucion!=null){
				for (int i = 0; i < (solucion.size()); i = i + 2) {
					/*
					 *  Emparejamos atacante defensor enviando un evento de defensa con los
					 *  datos necesarios de las dos cartas implicadas el segundo y el cuarto
					 *  elemento son los indices de las correspondientes cartas en sus vectores
					 */
					int indice1 = ((partida.getMesa()).getJugador1()).getVectorCriaturas().
							indexOf(((CACarta) (solucion.elementAt(i))));
					int indice2 = ((partida.getMesa()).getJugador2()).getVectorCriaturas().
							indexOf(((CACarta) (solucion.elementAt(i + 1))));
					EventoDefensa evento = new EventoDefensa(((CACarta) (
							solucion.elementAt(i))).getCodigo(), String.valueOf(indice1),
							((CACarta) (solucion.elementAt(i + 1))).getCodigo(),
							String.valueOf(indice2), turno);
					v_Eventos.addElement(evento);
				}
				//se añade al contador de defendidos los que hayan sido defendidos en este turno
				defendidos = defendidos + solucion.size() / 2;
			}
			/*
			 *  sino no hace nada
			 */
			else {

				this.partida.setFinPartida(true);
                          //falta inhabilitar q se puedan mover las cartas
                          //La idea es q una vez cerrado el dialogo q aparece en el panel generico,
                          //aparezcamos en la pantalla de juego y si queremos salir, salgamos.
                          //Si salimos del sistema a lo bestia, el chat puede tener problemas, a parte de
                          //q nos corta la conexion con el q estemos hablando.




				nomuerto = false;

			}
			turno++;
		}
		if (nomuerto = false) {

			//lo notificamos y tal con una ventanita que nos haga estefi
                        //NOTA DE ESTEFI :no tengo muy claro q ventanita es aqui. Si es la de q
                        //has perdido,hay q tener en cuenta q se ha quitado el System.exit(0) del IF anterior,
                        //asi q pasa por aqui cuando gana tb.
                        //para insertar aqui la ventana de haber perdido,descomentar lo siguiente:
                        /********************
                         *
                         * this.partida.getInterfaz().inhabilitaPanel();
                         * this.partida.getInterfaz().repaint();
                         * this.partida.getInterfaz().getContentPane().add(new PanelGenerico("../imagenes/HasPerdido.jpg",this.partida.getInterfaz()),0);
                         *
                         */



                    }
		/*
		 *  si devolvemos los eventos de todas formas se deberia ver como antes de
		 *  rendirse la maquina pierde todas sus cartas
		 */
		return v_Eventos;
	}
}

