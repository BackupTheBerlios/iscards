package audio;

/*
 *  primero, instalar nativefmod
 *  -> WINDOWS INSTALLATION for NATIVEFMOD
 *  The installation is very simple :
 *  - Copy NativeFmod.dll, NativeFmodDyn.dll and fmod.dll in the folder : jre/bin/
 *  - Copy NativeFmodApi_vx.xx.jar in the folder : jre/lib/ext
 *  Rem: libNativeFmodDyn.so is only used with multiple instance of Fmod.
 *  You don't need these files if
 *  you don't use these classes : FMOD_INSTANCE and FmodDyn.
 *  Rem: jre is the Java Runtime environment folder (ex: C:\java\j2re1.4.2_07) If you use the Java SDK (Software Development Kit), the jre folder is sdk/jre (ex: C:\java\j2sdk1.4.2_03\jre)
 *
 *  segundo: instalar fmod:
 *  Copy the FMOD.DLL file out of the /api directory and into the directory
 *  where your executable will be. If they are in the same directory then it will work as expected.
 *  si no hacemoe ésto, obtendremos un mensaje q dice
 *  "this application has failed to start because fmod.dll was nor found.re-installing the application must fix this problem"
 */

//NativeFmod import to use the Fmod API

import Music.NativeFmod.*;

/*
 *
 */
/**
 *  modo de uso:
 *  para conseguir un sonido en el juego, solo es necesario crear un objeto (en el lugar exacto donde quieres que empiece a sonar)
 *  de tipo gestorAudio, pasandole 2 parametros. el primero es un string que le indica si se trata de
 *  una cancion mp3 o un efecto de sonido (como una explosion). el segundo es un int, para cuando tengamos mas
 *  efectos y canciones. para parar el sonido, tienes distintas opciones. la mas rapida es cargarse el objeto creado
 *
 *  ejemplos: desde cualquier clase hacer
 *  gestorAudio ga=new gestorAudio("musica fondo", 1);
 *  gestorAudio ga2=new gestorAudio("efecto", 1);
 *
 *@author    Enrique Martorano
 */
public class GestorAudio {

	//tipo puede ser "musica fondo" o "efecto"
	private String tipo;

	//el nombre del archivo que queremos sonar
	private String nombre;

	//el hilo bajo el que sonará el archivo en concreto
	private HiloMusica hM = new HiloMusica(this);
	//these object store a pointer to the musics that are loaded
	static FMUSIC_MODULE sequence = null;
	static FSOUND_STREAM stream = null;
	static FSOUND_SAMPLE sample = null;
	//nombre de los archivos de musica o efectos
	static String VsequenceName[] = /*{"Media/canyon.mid"};*/{"","","../Media/efectos/caidaDeEnergia_x.wav"};
	static String VsampleName[] = {"(este es el cero)","../Media/efectos/applause2_x.wav","../Media/efectos/caidaDeEnergia_x.wav","../Media/efectos/click.wav",
                                       "../Media/efectos/explosion_x.wav","../Media/efectos/implosion2.wav","../Media/efectos/lucha1.wav",
                                       "../Media/efectos/luchaEspadas.wav","../Media/efectos/manwah.wav","../Media/efectos/muerte2.wav",
                                       "../Media/efectos/muerte_humano0.wav","../Media/efectos/n_lvl.wav","../Media/efectos/poder.wav",
                                       "../Media/efectos/risaDemonio.wav","../Media/efectos/rugido.wav","../Media/efectos/sword04.wav",
                                       "../Media/efectos/thunder.wav","../Media/efectos/turn.wav","../Media/efectos/underwater.wav",
                                       "../Media/efectos/war2_x.wav"};
	static String VstreamName[] = {"../Media/sebnem1y2.wav","../Media/Sephiroth.mp3"};
	//to know if an audio file is loaded
	static boolean sequenceLoaded = false;
	static boolean sampleLoaded = false;
	static boolean streamLoaded = false;


        //atributo para solo inicializar una vez fmod (la primera, con la musica de fondo)
        private boolean inicializado;


	/**
	 *  Constructor for the gestorAudio object
	 *
	 *@param  t  indica si se trata de una cancion mp3 o un efecto de sonido
	 *@param  nombreArchivo  es el archivo que haremos sonar
	 */
	public GestorAudio(String t, String nombreArchivo) {
		//initFmod();
		if (t == "musica fondo") {
			tipo = "musica fondo";
            nombre = nombreArchivo;
            System.err.println("musica fondo detectada");
			//cuando ya sabemos qué tipo de archivo queremos, lanzamos un nuevo hilo
			lanzarHilo(nombreArchivo);
		}
		if (t == "efecto") {
			nombre = nombreArchivo;
			tipo = "efecto";
			System.err.println("efecto detectado");
			lanzarHilo(nombreArchivo);
		}
		if (t == "secuencia") {
			nombre = nombreArchivo;
			tipo = "secuencia";
			System.err.println("secuencia detectada");
			lanzarHilo(nombreArchivo);
		}
                if (t == "opciones") {
                        if (nombreArchivo.compareTo("inicia Fmod")==0){this.iniciaFmod();}
			if (nombreArchivo.compareTo("mute")==0){this.muteMusica();}
                        if (nombreArchivo.compareTo("volumenUp")==0){
                            Fmod.FSOUND_SetVolume( Fmod.FSOUND_ALL,Fmod.FSOUND_GetVolume(1)+50);
                        }
                        if (nombreArchivo.compareTo("volumenDown")==0){
                            Fmod.FSOUND_SetVolume( Fmod.FSOUND_ALL,Fmod.FSOUND_GetVolume(1)-50);
                        }

		}
	}




	/**
	 *  Gets the Tipo attribute of the gestorAudio object
	 *
	 *@return    The Tipo value
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 *  Gets the Nombre attribute of the gestorAudio object
	 *
	 *@return    The Nombre value
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 *  inicializa el sistema de audio
	 */
	public void iniciaFmod() {
		try {
			initFmod();
		}
		catch (Exception e) {
			System.err.println("no es posible abrir fmod");
		}

	}



	/**
	 * lanza el hilo a ejecucion
	 *
	 *@param  nombre  nombre de la cancion o efecto que queremos lanzar
	 */
	public void lanzarHilo(String nombre) {
            hM.t.setPriority(hM.t.MIN_PRIORITY);
            hM.t.start();

	}


	/**
	 *  destruye el hilo de la musica
	 */
	public void cerrarMusicaFondo() {

		hM.t.destroy();
	}


	/**
	 *  cierra el sistema de audio
	 */
	public static void cerrarFmod() {
		//close music loaded
		closeMusic();
		Fmod.FSOUND_Close();
	}



	/**
	 *  prepara el efecto de audio y lo abre
         * @param  numero el numero del efecto que queremos abrir. ver la tabla en el
         * metodo convierteAEntero de la clase HiloMusica
	 */
	public static void openEfecto(int numero) {
        try{
          sampleLoaded = true;
         /* sample = Fmod.FSOUND_Sample_Load(0, VsampleName[numero],
                                           Fmod.FSOUND_NORMAL, 0, 0);
          Fmod.FSOUND_PlaySound(0, sample);*/
         sample = Fmod.FSOUND_Sample_Load(Fmod.FSOUND_FREE, VsampleName[numero],
                                           Fmod.FSOUND_STEREO, 0, 0);
          if (sample==null) System.err.println("error null en sampleload");
       //   Fmod.FSOUND_PlaySound(1, sample);
        }catch(Exception e){e.printStackTrace();}

	}

	/**
	 *  cierra el efecto de audio
	 */
	public static void cerrarEfecto() {
		sampleLoaded = false;
		Fmod.FSOUND_Sample_Free(sample);
	}

        /**
         *  play efecto de sonido
         */
        public static void playEfecto() {
          System.err.println("VOLUMEN CANAL UNO "+Fmod.FSOUND_GetVolume(1));
          System.err.println("VOLUMEN CANAL CERO "+Fmod.FSOUND_GetVolume(0));
          Fmod.FSOUND_PlaySound(Fmod.FSOUND_FREE, sample);
        }



	/**
	 *  play-pause del efecto de audio abierto
	 */
	public static void play_or_pause_Efecto() {
		if (sampleLoaded) {
			Fmod.FSOUND_SetPaused(0, !Fmod.FSOUND_GetPaused(0));
		}
	}


    public static void openSequencia(int numero) {
        sequenceLoaded = true;
        sequence = Fmod.FMUSIC_LoadSong(VsequenceName[numero]);
        Fmod.FMUSIC_SetLooping(sequence, false);
        Fmod.FMUSIC_PlaySong(sequence);
    }


    public static void play_or_pause_Sequencia() {
        if(sequenceLoaded)Fmod.FMUSIC_SetPaused(sequence, !Fmod.FMUSIC_GetPaused(sequence));
    }


	/**
	 *   abrimos el archivo mp3
         * @param  numero el numero de la cancion que queremos abrir. ver la tabla en el
         * metodo convierteAEntero de la clase HiloMusica
	 */
	public static void openMusiquita(int numero) {
		streamLoaded = true;
		stream = Fmod.FSOUND_Stream_Open(VstreamName[numero], Fmod.FSOUND_NORMAL, 0, 0);
	}



	/**
	 *  play archivo mp3
	 */
        public static void playMusiquita() {
          Fmod.FSOUND_Stream_Play(1, stream);
        }


	/**
	 *  funcion mute
	 */
	public static void muteMusica() {
		Fmod.FSOUND_SetMute(1, !Fmod.FSOUND_GetMute(1));
	}


        /**
         *  funcion que fija el volumen de la musica entre 0 y 100
         * @param  volumen entero de 0(sin volumen) a 100(volumen maximo)
         * la funcion setVolumen de Fmod trabaja con un rango de 0 a 255. este metodo covierte
         * el parametro pasado, con rango de 0-100, al rango fmod  0-255
         */
        public static void setVolumenMusica(int volumen){
          int volumen255= (255*volumen)/100;
          boolean b =Fmod.FSOUND_SetVolume(1, volumen255);
        }
        
        /**
         *  funcion que devuelve el volumen de la musica entre 0 y 100
         * @return  volumen entero de 0(sin volumen) a 100(volumen maximo)
         * la funcion getVolumen de Fmod trabaja con un rango de 0 a 255. este metodo covierte
         * el parametro pasado, con rango de 0-100, al rango fmod  0-255
         */
        public static int getVolumenMusica(){
          int b =Fmod.FSOUND_GetVolume(1);          
          return ((b/255)*100);
        }
        



	/**
	 *  chequeo del sistema de audio
	 */
	private static void initFmod() {
		boolean inicializado = Fmod.FSOUND_Init(44100, 32, 0);
	}

	/**
	 *  cierra la musica que esté sonando en este momento a la fuerza
	 */
	public static void closeMusic() {
		if (sequenceLoaded) {
			//libera memoria reservada para una cancion y la elimina del sistema Fmusic
			sequenceLoaded = !Fmod.FMUSIC_FreeSong(sequence);
		}
		if (sampleLoaded) {
			//Removes a sample from memory and makes its slot available again.
			Fmod.FSOUND_Sample_Free(sample);
			sampleLoaded = false;
		}
		if (streamLoaded) {
			// Stops a stream from playing.
			Fmod.FSOUND_Stream_Stop(stream);
			streamLoaded = !Fmod.FSOUND_Stream_Close(stream);
		}
		//chequeamos para ver si todo ha ido bien
	}

//Fmod.FMUSIC_SetMasterVolume();


	/**
	 *  pause archivo mp3
	 */
	public static void pauseMusiquita() {
		if (streamLoaded) {
			Fmod.FSOUND_SetPaused(1, !Fmod.FSOUND_GetPaused(1));
		}
	}



	/**
	 *  parar reproduccion archivo mp3
	 */
	public static void stopMusiquita() {
		streamLoaded = false;
		Fmod.FSOUND_Stream_Stop(stream);
	}

    /**
	 *  muestra el espectro de la señal
	 */
    public static void spectrum() {
        Fmod.FSOUND_DSP_SetActive(Fmod.FSOUND_DSP_GetFFTUnit(), true);
    }

}
