package audio;
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
            /*if (s.compareTo("jules.mp3")==0) return 0;
            if (s.compareTo("canyon.mid")==0) return 1;*/
            if (s.compareTo("applause2_x.wav")==0) return 1;
            if (s.compareTo("caidaDeEnergia_x.wav")==0) return 2;
            if (s.compareTo("click.wav")==0) return 3;            
            if (s.compareTo("explosion_x.wav")==0) return 4;
            if (s.compareTo("implosion2.wav")==0) return 5;
            if (s.compareTo("lucha1.wav")==0) return 6;
            if (s.compareTo("luchaEspadas.wav")==0) return 7;
            if (s.compareTo("manwah.wav")==0) return 8;
            if (s.compareTo("muerte2.wav")==0) return 9;
            if (s.compareTo("muerte_humano0.wav")==0) return 10;
            if (s.compareTo("n_lvl.wav")==0) return 11;
            if (s.compareTo("poder.wav")==0) return 12;
            if (s.compareTo("risaDemonio.wav")==0) return 13;
            if (s.compareTo("rugido.wav")==0) return 14;
            if (s.compareTo("sword04.wav")==0) return 15;
            if (s.compareTo("thunder.wav")==0) return 16;             
            if (s.compareTo("turn.wav")==0) return 17;
            if (s.compareTo("underwater.wav")==0) return 18;     
            if (s.compareTo("war2_x.wav")==0) return 19;

            System.err.print("nooooooo");
            /*else */return 30;
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
				catch (InterruptedException e) {}
			}
		}
		if (padre.getTipo() == "efecto") {
                    System.err.println("nombre: "+padre.getNombre());
                    System.err.println("tipo: "+padre.getTipo());
			        padre.openEfecto(convierteAEntero(padre.getNombre()));
   			while (true) {
						  padre.play_or_pause_Efecto();
                          padre.spectrum();
						  try {
						       t.sleep(10);
						  }catch (InterruptedException e) {}
			}
		}
		
		if (padre.getTipo() == "secuencia") {
                    System.err.println("nombre: "+padre.getNombre());
                    System.err.println("tipo: "+padre.getTipo());
                    padre.openSequencia(2);
			while (true) {
				//padre.play_or_pause_Efecto();
                                padre.play_or_pause_Sequencia();
                                padre.spectrum();
				try {
					t.sleep(10);
				}
				catch (InterruptedException e) {}
			}

		}
		
		
		
		
		
		
		
		
		
		
	}
}
