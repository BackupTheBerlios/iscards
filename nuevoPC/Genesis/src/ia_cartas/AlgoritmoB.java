package ia_cartas;
import java.util.*;
import cartas.*;

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
public class AlgoritmoB {
// se supone que se usa desde el punto de vista del que defiende
	// tiene que estar ordenada de mayor a menor ataque
	private Vector atacan;
	// tiene que estar ordenada de menor a mayor defensa
	private Vector defienden;
	private Vector solucion;


	//recibe los vectores con las cartas en mesa de los dos jugadores
	/**
	 *  Constructor for the AlgoritmoB object
	 *
	 *@param  a  Description of Parameter
	 *@param  d  Description of Parameter
	 */
	public AlgoritmoB(Vector a, Vector d) {
		atacan = a;
		defienden = d;
	}


	/**
	 *  Gets the Solucion attribute of the AlgoritmoB object
	 *
	 *@return    The Solucion value
	 */
	public Vector getSolucion() {
		solucion = new Vector();
		/*
		 *  si el numero de criaturas que defienden en este turno de defensa es 0 y
		 *  recibe un ataque no hay solucion posible,el jugador PC habra perdido la
		 *  partida
		 */
		if (defienden.size() == 0) {
			return null;
		}
		else {
			/*
			 *  defendemos como maximo al mismo numero de atacantes que de defensores
			 *  en cada turno de defensa
			 */
			if (atacan.size() > defienden.size()) {
				atacan.setSize(defienden.size());
			}
			for (int i = 0; i < atacan.size(); i++) {
				if (atacan.elementAt(i) != null) {
					/*
					 *  para cada atacante debemos elegir cual es su mejor defensor
					 */
					/*
					 *  PRIMERO INTENTAMOS EMPAREJAR CON UN DEFENSOR QUE LO MATE
					 */
					if (hay_mejor((CCriatura) atacan.elementAt(i))) {
						/*
						 *  emparejamos atacante defensor
						 */
						solucion.addElement((CCriatura) atacan.elementAt(i));
						solucion.addElement((CCriatura) defienden.elementAt(0));
						/*
						 *  borramos al defensor para que no defienda 2 veces de momento
						 */
						defienden.remove((CCriatura) defienden.elementAt(0));
					}
					/*
					 *  SI NO INTENTAMOS IGUALARLE PARA QUEDAR EN TABLAS
					 */
					else if (hay_igual((CCriatura) atacan.elementAt(i)) != null) {
						/*
						 *  emparejamos atacante defensor
						 */
						solucion.addElement((CCriatura) atacan.elementAt(i));
						solucion.addElement(hay_igual((CCriatura) atacan.elementAt(i)));
						/*
						 *  borramos al defensor para que no defienda 2 veces de momento
						 */
						defienden.remove(hay_igual((CCriatura) atacan.elementAt(i)));
					}
					/*
					 *  SI NO DEBEREMOS SACRIFICAR A NUESTRA PEOR CRIATURA
					 */
					else {
						solucion.addElement((CCriatura) atacan.elementAt(i));
						/*
						 *  sacamos el indice de la ultima carta defensora no nula
						 */
						boolean encontrado = false;
						int cont = 0;
						while ((cont < defienden.size()) && (!encontrado)) {
							if (defienden.elementAt(cont) == null) {
								encontrado = true;
							}
							else {
								cont++;
							}
						}
						/*
						 *  restamos 1 a cont pq tendra almacenada la posicion del primer null
						 */
						solucion.addElement((CCriatura) defienden.elementAt(cont - 1));
						/*
						 *  borramos al defensor para que no defienda 2 veces de momento
						 */
						defienden.remove((CCriatura) defienden.elementAt(cont - 1));
					}
				}
			}
			return solucion;
		}
	}


	/**
	 *  Gets the Atacantes attribute of the AlgoritmoB object
	 *
	 *@return    The Atacantes value
	 */
	public Vector getAtacantes() {
		return atacan;
	}


	/**
	 *  Gets the Defensores attribute of the AlgoritmoB object
	 *
	 *@return    The Defensores value
	 */
	public Vector getDefensores() {
		return defienden;
	}


	/**
	 *  Description of the Method
	 */
	public void resuelve() {
		ordenaAtacantes();
		ordenaDefensores();
		int numAtacantes = atacan.size();
		// numero de cartas giradas para atacar
		int numDefensores = defienden.size();
		// numero de cartas que tengo para defender
		// int numDefensores = defienden.size();
		int indiceAtacantes = 0;
		int indiceDefensores = 0;
		// boolean asignado = false;
		boolean resuelto = false;
		//busca todos los defensores
		while (indiceAtacantes <= numAtacantes) {
			// busca un defensor concreto para el atacante que se pida
			CCriatura atacante = (CCriatura) atacan.get(indiceAtacantes);
			// primero busca la menor carta que gane al atacante
			while ((!resuelto) && (indiceDefensores <= numDefensores)) {
				CCriatura defensor = (CCriatura) defienden.get(indiceDefensores);
				if ((defensor.getAtaque() > atacante.getDefensa()) &&
						(defensor.getDefensa() > atacante.getAtaque())) {
					Pares p = new Pares(atacante.getCodigo(), defensor.getCodigo());
					resuelto = true;
					defienden.remove(indiceDefensores);
					numDefensores--;
				}
				else {
					indiceDefensores++;
				}
			}
			// sino se ha encontrado una carta propia que gane, se busca una que
			// lo empate
			if (!resuelto) {
				indiceDefensores = 0;
				while ((!resuelto) && (indiceDefensores <= numDefensores)) {
					CCriatura defensor = (CCriatura) defienden.get(indiceDefensores);
					if ((defensor.getAtaque() == atacante.getDefensa()) &&
							(defensor.getDefensa() == atacante.getAtaque())) {
						Pares p = new Pares(atacante.getCodigo(), defensor.getCodigo());
						resuelto = true;
						defienden.remove(indiceDefensores);
						numDefensores--;

					}
					else {
						indiceDefensores++;
					}
				}
				// si no hay ninguna carta que gane o empate, se asigna la peor carta
				if (!resuelto) {
					CCriatura defensor = (CCriatura) defienden.get(0);
					Pares p = new Pares(atacante.getCodigo(), defensor.getCodigo());
					resuelto = true;
					defienden.remove(0);
					numDefensores--;
				}
			}
			indiceAtacantes++;
		}
	}


	/**
	 *  Metodo que recoje las cartas que están atacando *
	 */
	/*
	 *  ORDENO LOS ATACANTES POR LA PROPIEDAD ATAQUE
	 */
	public void ordenaAtacantes() {
		Vector v = atacan;
		Vector vaux = new Vector();
		/*
		 *  En el vector auxiliar solo almacenamos las cartas en mesa del
		 *  altacante que estuvieran giradas
		 */
		for (int i = 0; i < v.size(); i++) {
			if (v.elementAt(i) != null) {
				if (((CACarta) v.elementAt(i)).getEstado() == false) {
					vaux.add(v.elementAt(i));
				}
			}
		}
		//Actualizamos el vector de atacantes solo con los que realmente atacan
		v = vaux;
		//v_Atacantes = vaux;
		for (int i = 0; i < v.size() - 1; i++) {
			for (int j = i + 1; j < v.size(); j++) {
				// tomamos los puntos de la primera carta
				int ataque1 = ((CCriatura) v.elementAt(i)).getAtaque();
				// tomamos los puntos de la carta con la que comparamos
				int ataque2 = ((CCriatura) v.elementAt(j)).getAtaque();
				if (ataque2 > ataque1) {
					CACarta aux = (CCriatura) v.elementAt(i);
					v.setElementAt((CCriatura) v.elementAt(j), i);
					v.setElementAt(aux, j);

				}
			}
		}
		atacan = v;
		/*
		 *  System.out.println("atacantes");
		 *  for(int k=0;k<atacan.size();k++){
		 *  if (atacan.elementAt(k)!=null){
		 *  System.out.println(((CCriatura)atacan.elementAt(k)).dame_clips());
		 *  }
		 *  }
		 */
	}


	/**
	 *  En este caso, en lugar de ordenar solo los que están girados ordenamos
	 *  todos los elementos que están en la mesa del pc*
	 */
	/*
	 *  ORDENO LOS DEFENSORES POR LA PROPIEDAD DEFENSA
	 */
	public void ordenaDefensores() {
		Vector v = defienden;
		if (defienden.size() != 0) {
			Vector v_def1 = new Vector();
			Vector v_def2 = new Vector();
			/*
			 *  Defienden todos aunque se supone que primero defienden los enderezados
			 *  y luego los girados
			 *  OBS: DE HECHO SE SUPONE QUE PRIMERO DEFIENDEN LOS ENDEREZADOS Y LUEGO
			 *  DEFIENDEN TANTO LOS GIRADOS COMO LOS ENDEREZADOS QUE HAN PASADO A GIRADOS
			 *  TRAS ESTE TURNO COMO DEFENSORES
			 */
			/*
			 *  primero cogemos los enderezados
			 */
			for (int i = 0; i < v.size(); i++) {
				if (v.elementAt(i) != null) {
					if (((CACarta) v.elementAt(i)).getEstado() == false) {
						v_def1.add(v.elementAt(i));
					}
				}
			}
			/*
			 *  los ordenamos
			 */
			for (int i = 0; i < v_def1.size() - 1; i++) {
				for (int j = i + 1; j < v_def1.size(); j++) {
					// tomamos los puntos de la primera carta
					int defensa1 = ((CCriatura) v_def1.elementAt(i)).getDefensa();
					// tomamos los puntos de la carta con la que comparamos
					int defensa2 = ((CCriatura) v_def1.elementAt(j)).getDefensa();
					if (defensa2 > defensa1) {
						CACarta aux = (CCriatura) v_def1.elementAt(i);
						v_def1.setElementAt((CCriatura) v_def1.elementAt(j), i);
						v_def1.setElementAt(aux, j);
					}
				}
			}
			/*
			 *  luego cogemos los girados
			 */
			for (int i = 0; i < v.size(); i++) {
				if (v.elementAt(i) != null) {
					if (((CACarta) v.elementAt(i)).getEstado() == true) {
						v_def2.add(v.elementAt(i));
					}
				}
			}
			/*
			 *  los ordenamos
			 */
			for (int i = 0; i < v_def2.size() - 1; i++) {
				for (int j = i + 1; j < v_def2.size(); j++) {
					// tomamos los puntos de la primera carta
					int defensa1 = ((CCriatura) v_def2.elementAt(i)).getDefensa();
					// tomamos los puntos de la carta con la que comparamos
					int defensa2 = ((CCriatura) v_def2.elementAt(j)).getDefensa();
					if (defensa2 > defensa1) {
						CACarta aux = (CCriatura) v_def2.elementAt(i);
						v_def2.setElementAt((CCriatura) v_def2.elementAt(j), i);
						v_def2.setElementAt(aux, j);
					}
				}
			}
			/*
			 *  reiniciamos v
			 */
			v = new Vector();
			/*
			 *  añadimos a v los enderezados
			 */
			for (int i = 0; i < v_def1.size(); i++) {
				if (v_def1.elementAt(i) != null) {
					v.addElement(v_def1.elementAt(i));
				}
			}
			/*
			 *  añadimos a v los girados
			 */
			for (int i = 0; i < v_def2.size(); i++) {
				if (v_def2.elementAt(i) != null) {
					v.addElement(v_def2.elementAt(i));
				}
			}
			defienden = v;

			/*
			 *  mostramos el cvector v
			 */
			/*
			 *  System.out.println("defensores:");
			 *  for(int k=0;k<v.size();k++){
			 *  if (v.elementAt(k)!=null){
			 *  System.out.println(((CACarta)v.elementAt(k)).dame_clips());
			 *  }
			 *  }
			 */
		}
		else {
			//System.out.println("No hay defensores, la IA a muerto");
		}
	}


	/**
	 *  Description of the Method
	 *
	 *@param  c  Description of Parameter
	 *@return    Description of the Returned Value
	 */
	public boolean hay_mejor(CCriatura c) {
		/*
		 *  como los defensores estan ordenados coge el mas fuerte para defenderle
		 *  (el mas fuerte estara en la posicion 0 del vector de defensores)
		 */
		if (c.getAtaque() < ((CCriatura) defienden.elementAt(0)).getDefensa()) {
			return true;
		}
		else {
			return false;
		}
	}


	/*
	 *  ojo que este ya que busca la criatura la devuelve si la encuentra
	 */
	/**
	 *  Description of the Method
	 *
	 *@param  c  Description of Parameter
	 *@return    Description of the Returned Value
	 */
	public CCriatura hay_igual(CCriatura c) {
		int j = 0;
		boolean encontrado = false;
		CCriatura aux = null;
		while ((j < defienden.size()) && (!encontrado)) {
			if (defienden.elementAt(j) != null) {
				if (c.getAtaque() == ((CCriatura) defienden.elementAt(j)).getDefensa()) {
					aux = (CCriatura) defienden.elementAt(j);
				}
			}
			j++;
		}
		return aux;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  criatura  Description of Parameter
	 */
	public void quitaAtacante(CCriatura criatura) {
		atacan.remove(criatura);
	}
}
