package motorJuego;

import eventos.*;
import cartas.*;
import interfaz.*;

import java.util.Vector;
import java.util.Hashtable;
import java.lang.Thread;
import java.awt.Color;


public interface Partida extends Runnable{
	public void actualizaPorEvento(Eventos e);
	public void añadeCementerio(CACarta carta);
	public void añadeUnoNumCriaturasDefendiendo();		
	public void añadeVectorBajadas(CACarta c);
	public void finalizaPartida();
	public CACarta getCartaSeleccionadaDefensa() ;
	public CMazo getCementerio();
	public Color getColorActual();
	public Hashtable getEnfrentamientos();
	public Thread getHilo();
	public Interfaz getInterfaz ();
	public CMano getMano();
	public CMazo getMazo();
	public CMesa getMesa();
	public int getNumCriaturasDefendiendo();
	public int getPosicionCriatura(CACarta carta, boolean perteneceContrario);
	public boolean getSeleccionandoDefensa();
	public int getTurnoPartida();
	public String getTurnoJugador();	
	public Vector getVectorCriaturasAtaque();
	public Vector getVectorCriaturasDefensa();
	public void notifica();
	public void pasaSiguienteColor();
	public void pasaTurnoPartida(String jug);
	public void quitaUnoNumCriaturasDefendiendo();
	public void setCartaSeleccionadaDefensa(CACarta c);
	public void setFinPartida(boolean b);
	public void setInterfaz(Interfaz i);
	public void setSeleccionandoDefensa(boolean b);
	

}