package ia_cartas;
import jess.*;
import java.util.*;
import cartas.*;
import motorJuego.*;
import eventos.*;

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
public class Atacar {
	/*
	 *  atributos de la clase
	 */
	private CPartida partida;
	private String archivo;
	private Rete rete;


	/*
	 *  constructor
	 */
	/**
	 *  Constructor for the Atacar object
	 *
	 *@param  p                  Description of Parameter
	 *@param  nivelInteligencia  Description of Parameter
	 *@param  r                  Description of Parameter
	 */
	public Atacar(CPartida p, int nivelInteligencia, Rete r) {
		partida = p;
		/*
		 *  se elige el archivo de inteligencia del nivel correspondiente
		 */
		if (nivelInteligencia == 0) {
			archivo = "../inteligencia/Nivelbasico.clp";
		}
		else if (nivelInteligencia == 1) {
			archivo = "../inteligencia/Nivelmedio.clp";
		}
		else {
			archivo = "../inteligencia/Nivelalto.clp";
		}
		rete = r;
	}


	/**
	 *  Description of the Method
	 *
	 *@return                Description of the Returned Value
	 *@exception  Exception  Description of Exception
	 */
	public Vector eventos() throws Exception {
		/*
		 *  inicializamos el vector a devolver
		 */
		Vector v_Eventos = new Vector();
		/*
		 *  reseteamos el rete
		 */
		rete.reset();
		/*
		 *  limpiamos el rete
		 */
		rete.clear();
		rete.executeCommand("(batch \"" + archivo + "\")");
		/*
		 *  aserta las cartas que hay en la mesa para decidir con cuales atacar
		 */
		Vector v_MesaPC = (((partida.getMesa()).getJugador2()).getVectorCriaturas());
		for (int i = 0; i < v_MesaPC.size(); i++) {
			/*
			 *  comparamos con null para evitar errores
			 */
			if ((CACarta) v_MesaPC.elementAt(i) != null) {
				rete.executeCommand("(assert " + ((CACarta) v_MesaPC.elementAt(i)).dame_clips() + ")");
			}
		}
		/*
		 *  aserta el numero de cartas con las que puede atacar(numero de cartas en la mesa)
		 */
		rete.executeCommand("(assert( numerocartas (ncartas " + (((partida.getMesa()).getJugador2()).getVectorCriaturas()).size() + ")))");
		/*
		 *  aserta el hecho iniciar_ataque
		 */
		rete.executeCommand("(assert( intentar_ataque ))");
		/*
		 *  hechos antes del run
		 */
		Vector v_string1 = new Vector();
		Vector v_string2 = new Vector();
		java.util.Iterator iterador1;
		iterador1 = rete.listFacts();
		while (iterador1.hasNext()) {
			String s1 = iterador1.next().toString();
			v_string1.addElement(s1);
			//System.out.println(s1);
		}
		/*
		 *  hechos despues del run
		 */
		rete.run();
		//rete.executeCommand("(run 1)");
		java.util.Iterator iterador2;
		iterador2 = rete.listFacts();
		while (iterador2.hasNext()) {
			String s2 = iterador2.next().toString();
			v_string2.addElement(s2);
			//System.out.println(s2);
		}
		/*
		 *  actualizamos cartas
		 */
		/*
		 *  empiezan en 0 estas pq ya no hay "initial fact"
		 */
		int i = 0;
		/*
		 *  los dos ultimos elementos de este String son correspondientes al
		 *  cambio en el numero de cartas y el aserto de "intentar ataque" y
		 *  estos no deben enviar eventos de ningun tipo
		 */
		while (i < v_string1.size() - 2) {
			String s1 = (String) v_string1.elementAt(i);
			String s2 = (String) v_string2.elementAt(i);
			/*
			 *  ponemos las cartas que atacaran,la carta debe girarse si ha
			 *  habido un cambio en su texto
			 */
			if (!(s1.equals(s2))) {
				/*
				 *  añadimos un evento de ataque con el codigo de la carta y con
				 *  su posicion en el vector
				 */
				/*
				 *  OBS HAY QUE MANDAR EL INDICE DEL VECTOR COMO UN STRING!!
				 */
				EventoAtaque evento = new EventoAtaque(((CACarta) (v_MesaPC.elementAt(i))).getCodigo(), String.valueOf(i));
				v_Eventos.addElement(evento);
			}
			i++;
		}
		return v_Eventos;
	}
}

