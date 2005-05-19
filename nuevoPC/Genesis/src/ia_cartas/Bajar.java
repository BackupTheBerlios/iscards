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
public class Bajar {
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
	 *  Constructor for the Bajar object
	 *
	 *@param  p                  Description of Parameter
	 *@param  nivelInteligencia  Description of Parameter
	 *@param  r                  Description of Parameter
	 */
	public Bajar(CPartida p, Rete r) {
		partida = p;
		/*
		 *  se elige el archivo de inteligencia del nivel correspondiente
		 */
		if (partida.getNivelInteligencia()==0) {
			archivo = "../inteligencia/Nivelbasico.clp";
		}
		else if (partida.getNivelInteligencia()==1) {
			archivo = "../inteligencia/Nivelmedio.clp";
		}
		else if (partida.getNivelInteligencia()==2){
			archivo = "../inteligencia/Nivelalto.clp";
		}
		rete = r;
	}


	/*
	 *  metodo que soluciona la situacion de bajada en funcion de la inteligencia
	 *  selecionada,devuleve un vector de eventos de bajada que posteriormente
	 *  procesara la parte grafica
	 */
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
		int manaInicial=((partida.getMesa()).getJugador2()).getManaDisponible()+((partida.getMesa()).getJugador2()).getManaUsado();;
		/*
		 *  reseteamos el rete
		 */
		rete.reset();
		/*
		 *  limpiamos el rete
		 */
		rete.clear();
		rete.executeCommand("(batch \"" + archivo + "\")");
		rete.executeCommand("(assert(mana(numero " + ((partida.getMesa()).getJugador2()).getManaDisponible() + ")))");
		/*
		 *  aserta el numero de cartas que hay en la mesa
		 *  con esto evitamos bajar mas de las que se puedan
		 */
		rete.executeCommand("(assert( numerocartas (ncartas " + (((partida.getMesa()).getJugador2()).getVectorCriaturas()).size() + ")))");
		/*
		 *  aserta las cartas que hay en la mano
		 */
		Vector v_Mano = (partida.getMano2()).getCartas();
		for (int i = 0; i < v_Mano.size(); i++) {
			/*
			 *  comparamos con null para evitar errores
			 */
			if ((CACarta) v_Mano.elementAt(i) != null) {
				rete.executeCommand("(assert " + ((CACarta) v_Mano.elementAt(i)).dame_clips("jugador2") + ")");
			}
		}
		/*
		 *Se asertan los hechos necesarios para la Inteligencia Media y Alta y
		 *que determinan la mejor carta a bajar con respecto a la dificultad
		 */
    	if ((partida.getNivelInteligencia()== 1)|(partida.getNivelInteligencia()== 2)){
      		// hecho donde se guarda la mejor carta hasta el momento (para bajar cartas)
        	rete.executeCommand("(assert(mc(funcion 0)(codigo h)))");
    	}
		/*
		 *  aserta el hecho bajar
		 */
		rete.executeCommand("(assert (bajar_cartas))");
		/*
		 *  devuelve las cartas a bajar en la mesa y las pone
		 */
		/*
		 *  hechos antes del run
		 */
		Vector v_string1 = new Vector();
		Vector v_string2 = new Vector();
		Vector v_string3 = new Vector();
		java.util.Iterator iterador;
		iterador = rete.listFacts();
		while (iterador.hasNext()) {
			String s1 = iterador.next().toString();
			//if (s1.startsWith("(MAIN::carta")){
				v_string1.addElement(s1);
		}
		/*
		 *  hechos despues del run
		 */
		rete.run();
		java.util.Iterator iterador2;
		iterador2 = rete.listFacts();
		while (iterador2.hasNext()) {
			String s2 = iterador2.next().toString();
				v_string2.addElement(s2);
		}
		/*
		 *  actualizar manas sacandolas de los hechos finales 
		 */
		String Mana1 = ((String) v_string2.elementAt(0)).substring(20);
		//antes ponia 19 pero petaba pq metia un jodido espacio
		Mana1 = Mana1.substring(0, Mana1.length() - 2);
		/*
		 *  HABRA QUE HACER UNA CONVERSION A ENTERO
		 *  POSIBLEMENTE TENGA QUE ACTUALIZAR MANUALMENTE LOS MANAS UTILIZADOS
		 */
		((partida.getMesa()).getJugador2()).setManaDisponible(Integer.parseInt(Mana1));
		((partida.getMesa()).getJugador2()).setManaUsado(manaInicial-Integer.parseInt(Mana1));
		/*
		 *  actualizamos cartas
		 */
		/*
		 *  i es 2 pq los primeros hechos son el numero de manas y el de cartas
		 */
		int i = 2;
		while (i < v_string1.size()-2) {
			String s1 = (String) v_string1.elementAt(i);
			String s2 = (String) v_string2.elementAt(i);
			/*
			 *  ponemos las cartas bajadas,la carta debe bajarse si ha habido un
			 *  cambio en su texto
			 */
			if (!(s1.equals(s2))) {
				/*
				 *  añadimos un evento de bajada con el codigo de la carta y con
				 *  su posicion en el vector
				 */
				EventoBajada evento = new EventoBajada(((CACarta) (v_Mano.elementAt(i-2))).getCodigo(), String.valueOf(i-2));
				v_Eventos.addElement(evento);
				System.out.println("Lanza el evento de bajada: "+((CACarta) (v_Mano.elementAt(i-2))).getCodigo()+" "+String.valueOf(i-2));
			}
			i++;
		}
		return v_Eventos;
	}
}

