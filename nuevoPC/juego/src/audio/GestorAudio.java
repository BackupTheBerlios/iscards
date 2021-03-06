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
 *  si no hacemoe �sto, obtendremos un mensaje q dice
 *  "this application has failed to start because fmod.dll was nor found.re-installing the application must fix this problem"
 */

//NativeFmod import to use the Fmod API

import Music.NativeFmod.FMUSIC_MODULE;
import Music.NativeFmod.FSOUND_SAMPLE;
import Music.NativeFmod.FSOUND_STREAM;
import Music.NativeFmod.Fmod;

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

	//el hilo bajo el que sonar� el archivo en concreto
	private HiloMusica hM = new HiloMusica(this);
	//these object store a pointer to the musics that are loaded
	static FMUSIC_MODULE sequence = null;
	static FSOUND_STREAM stream = null;
	static FSOUND_SAMPLE sample = null;
	//nombre de los archivos de musica o efectos
	static String VsequenceName[] = /*{"Media/canyon.mid"};*/{"","","../Media/efectos/caidaDeEnergia_x.wav"};
	static String VsampleName[] = {"","../Media/efectos/turn.wav","../Media/efectos/caidaDeEnergia_x.wav","../Media/efectos/click.wav",
                                       "../Media/efectos/lucha1.wav","../Media/efectos/muerte_humano0.wav","../Media/efectos/muerte2.wav",
                                       "../Media/efectos/n_lvl.wav","../Media/efectos/poder.wav","../Media/efectos/risaDemonio.wav",
                                       "../Media/efectos/applause2_x.wav","../Media/efectos/explosion_x.wav","../Media/efectos/implosion2.wav",
                                       "../Media/efectos/luchaEspadas.wav","../Media/efectos/manwah.wav","../Media/efectos/risaDemoniowav.wav",
                                       "../Media/efectos/rugido.wav","../Media/efectos/sword04.wav","../Media/efectos/thunder.wav",
                                       "../Media/efectos/underwater.wav","../Media/efectos/war2_x.wav"};
	static String VstreamName[] = {"../Media/Sebnem1y2.wav","../Media/jules.mp3"};
	//to know if an audio file is loaded
	static boolean sequenceLoaded = false;
	static boolean sampleLoaded = false;
	static boolean streamLoaded = false;


	/**
	 *  Constructor for the gestorAudio object
	 *
	 *@param  t  indica si se trata de una cancion mp3 o un efecto de sonido
	 *@param  nombreArchivo  es el archivo que haremos sonar
	 */
	public GestorAudio(String t, String nombreArchivo) {
		initFmod();
		if (t == "musica fondo") {
			tipo = "musica fondo";
            nombre = nombreArchivo;
            System.err.println("musica fondo detectada");
			//cuando ya sabemos qu� tipo de archivo queremos, lanzamos un nuevo hilo
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
			System.exit(1);
		}

	}


	
	/**
	 * lanza el hilo a ejecucion
	 *
	 *@param  nombre  nombre de la cancion o efecto que queremos lanzar
	 */
	public void lanzarHilo(String nombre) { 
            hM.t.setPriority(hM.t.MAX_PRIORITY);
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
		System.out.println();
		//close music loaded
		closeMusic();
		System.out.print("Closing Fmod...");
		Fmod.FSOUND_Close();
		System.out.println("OK");
	}


	
	/**
	 *  prepara el efecto de audio y lo abre
	 */
	public static void openEfecto(int numero) {
        System.out.println("abriendo el efecto "+numero);
		sampleLoaded = true;
		sample = Fmod.FSOUND_Sample_Load(0, VsampleName[numero], Fmod.FSOUND_NORMAL, 0, 0);
		Fmod.FSOUND_PlaySound(0, sample);
            
	}
           
	/**
	 *  cierra el efecto de audio
	 */
	public static void cerrarEfecto() {
		sampleLoaded = false;
		Fmod.FSOUND_Sample_Free(sample);
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
    	System.out.println("abriendo la sequencia "+numero);
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
	 */
	public static void openMusiquita(int numero) {
		streamLoaded = true;
		System.out.println("abriendo la musica "+numero);
		stream = Fmod.FSOUND_Stream_Open(VstreamName[numero], Fmod.FSOUND_NORMAL, 0, 0);
	}


	
	/**
	 *  play archivo mp3
	 */
	public static void playMusiquita() {
		    //System.out.println("playing the song ");
		   
		Fmod.FSOUND_Stream_Play(1, stream);
	}


	/**
	 *  funcion mute
	 */
	public static void muteMusica() {
		Fmod.FSOUND_SetMute(1, !Fmod.FSOUND_GetMute(1));
	}


	
	/**
	 *  chequeo del sistema de audio
	 */
	private static void initFmod() {
		System.out.println("\nInitialization of Fmod & NativeFmod : ");
		System.out.print("NativeFmod Jar version...");
		System.out.println((float) Fmod.NATIVEFMOD_VERSION);
		System.out.print("NativeFmod Library version...");
		System.out.println((float) Fmod.NATIVEFMOD_GetVersion());
		System.out.print("Fmod Library version...");
		System.out.println((float) Fmod.FSOUND_GetVersion());
		System.out.print("Fmod version supported by NativeFmod (and higher) ...");
		System.out.println((float) Fmod.FMOD_VERSION);
		//inicializamos fmod
		System.out.print("inicializando Fmod...");
		boolean inicializado = Fmod.FSOUND_Init(44100, 32, 0);
		if (inicializado) {
			System.out.println("OK");
		}
		else {
			System.err.println("FALLO");
		}
	}

	/**
	 *  cierra la musica que est� sonando en este momento a la fuerza
	 */
	public static void closeMusic() {
		System.out.print("Closing music loaded...");
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
		if (!sequenceLoaded && !sampleLoaded && !streamLoaded) {
			System.out.println("OK");
		}
		else {
			System.err.println("FAILED");
		}
	}



	/**
	 *  pause archivo mp3
	 */
	public static void pauseMusiquita() {
		if (streamLoaded) {
			Fmod.FSOUND_SetPaused(1, !Fmod.FSOUND_GetPaused(1));
		}
		System.out.println("pause musiquita");
	}


	
	/**
	 *  parar reproduccion archivo mp3
	 */
	public static void stopMusiquita() {
		streamLoaded = false;
		Fmod.FSOUND_Stream_Stop(stream);
		System.out.println(" stop musiqita");
	}
        
    /**
	 *  muestra el espectro de la se�al
	 */ 
    public static void spectrum() {
    	System.out.println("\n\nCPU Usage = "+Fmod.FSOUND_GetCPUUsage());
        Fmod.FSOUND_DSP_SetActive(Fmod.FSOUND_DSP_GetFFTUnit(), true);
    }


	
	/**
	 *  muestra info del archivo mp3 actualmente en reproduccion por la salida standart
	 */
	public static void infoMusiquita() {

		System.out.println("Stream information :");
		System.out.println("Stream length (ms) : " + (Fmod.FSOUND_Stream_GetLengthMs(stream)));
		System.out.println("Stream position (ms) : " + (Fmod.FSOUND_Stream_GetTime(stream)));
		System.out.println("Stream length (byte) : " + (Fmod.FSOUND_Stream_GetLength(stream)));
		System.out.println("Stream position (byte) : " + (Fmod.FSOUND_Stream_GetPosition(stream)));
		System.out.println("Stream current position: " + (Fmod.FSOUND_GetCurrentPosition(1)));
		System.out.println("Stream is playing : " + (Fmod.FSOUND_IsPlaying(1)));
		System.out.println("Stream playing loop mode : " + (Fmod.FSOUND_GetLoopMode(1)));
                System.out.println("canal tocando : " + (Fmod.FSOUND_GetChannelsPlaying()));
                System.out.println("Stream playing volumen : " + (Fmod.FSOUND_GetVolume(0) + "\n"));
	}



}

/*las de la clase interfaz
Estefania dice:
todas las q son ../imagenes
Estefania dice:
les sobra un punto del ppio
Estefania dice:
y luego hay una clase carta
Estefania dice:
(creo q es esa
Estefania dice:
q en lugar de ser ../../Cartas... las rutas
Estefania dice:
son ../Cartas*/
