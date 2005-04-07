
/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: Juego de cartas de Rol</p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Enrique Martorano
 *@version    1.0
 */

public class HiloMusica implements Runnable {
	/**
	 *  esta clase la utiliza gestorAudio y no deben crearse usarse por separado. gestorAudio tiene los metodos suficientes
	 *  para gestionar la musica del juego. no es recomendable crear objetos de esta clase, ya lo hace gestorAudio
	 */
	public Thread t = new Thread(this);
	private GestorAudio padre;


	/**
	 *  Constructor for the hiloMusica object
	 *
	 *@param  ga  recibimos al padre, (un objeto de tipo gestorAudio), porque fue al crear ese objeto cuando decidimos
	 *si queriamos sonar un mp3 o un efecto. esta informacion esta guardada en el atributo "tipo".
	 */
	public HiloMusica(GestorAudio ga) {
		padre = ga;
	}
        
        private int convierteAEntero(String s){
            if (s.compareTo("jules.mp3")==0) return 0;
            if (s.compareTo("canyon.mid")==0) return 1;
            System.err.print("nooooooo");
            /*else */return 2;
        }


	/**
	 *  cuando un objeto hiloMusica se lanza, se ejecuta automaticamente ( con el metodo start() ) el metodo run()
	 *  es decir, NO llamar NUNCA explicitamente al metodo run()
	 */
	public void run() {

		// Ejecución del thread una vez creado. dependiendo del atributo tipo del padre, haremos una
		//cosa u otra dentro del run

		if (padre.getTipo() == "musica fondo") {
                    
			padre.openMusiquita(convierteAEntero(padre.getNombre()));
			while (true) {
				padre.playMusiquita();
				try {
					t.sleep(10);
				}
				catch (InterruptedException e) {
				}
			}
		}
		if (padre.getTipo() == "efecto") {
                    System.err.println("nombre: "+padre.getNombre());
                    System.err.println("tipo: "+padre.getTipo());
			padre.openEfecto(convierteAEntero(padre.getNombre()));
			while (true) {
				padre.play_or_pause_Efecto();
				try {
					t.sleep(10);
				}
				catch (InterruptedException e) {
				}
			}

		}
	}
}
