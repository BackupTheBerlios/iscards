package cartas;


import eventos.*;

import java.awt.*;

/**
 * <p>T�tulo: GENESIS</p>
 * <p>Descripci�n: Clase abstracta que extenderan todos los objetos de tipo carta</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Tony
 * @version 1.0
 */
public class CCriatura extends CACarta{
	
	/*nuevos atributos a�adidos a la clase*/
    private int vida;
    private int ataque;
    private int defensa;
	private Color color;


    public CCriatura(int niv, int cost, int punt,int at, int def, String cod, String nom, String idR, String idT, String coment, String hab, boolean b){
    	nivel=niv;
    	coste=cost;
    	puntos=punt;
    	ataque = at;
    	defensa = def;
    	codigo=cod;
    	nombre=nom;
    	idRaza=idR;
    	idTipo=idT;
    	comentarios=coment;
    	habilidades=hab;
    	imagen = "../Cartas/" + idRaza + "/Criaturas/" + nombre + ".jpg";
		//estado=true;
    	bajada = b;
    	/*la vida es complementaria al nivel*/
    	if (nivel==3) vida=1;
    	else if (nivel==2) vida=2;
    	else vida=3;
    	//el color no lo inicializamos
  }


    public boolean ejecuta() {
    //puede tener hab especiales ************************
        return true;
    }

    public void ataca(int posicionCarta){
      EventoAtaque e = new EventoAtaque(getCodigo(), String.valueOf(posicionCarta));
    }

    public void defiende(int posicionCarta, CACarta cartaContrario, int posicionCartaContrario){
      EventoDefensa e = new EventoDefensa(getCodigo(), String.valueOf(posicionCarta),
                                          cartaContrario.getCodigo(),
                                          String.valueOf(posicionCartaContrario));

      //////////se modifican los colores de las dos cartas
      /*
       carta.setColor();
       cartaContrario.setColor();

       */
    }

    public boolean restaVida(int da�o){
      vida = vida - da�o;
      if(vida < 0) return false; //ha muerto
      else return true;          //aun vive
    }

    public String getDir() {
    	return dirD;
    }

    public String dame_clips(){
    String j = " (jugador PC)";
    String a = " (ataque " + this.getAtaque()+")";
    String d = " (defensa " + this.getDefensa()+")";
    String v = " (vidas " + this.getVida()+")";
    String c = " (coste " + this.getCoste() + ")";
    String p = " (puntos " + this.getPuntos() + ")";
    String t = " (tipo criatura)";
    String e;
    if (this.getEstado()==false)
    {
      e = " (estado enderezado)";
    }
    else{
      e = " (estado girada)";
    }
    String cod = " (codigo " +this.getCodigo() + ")";
    String lugar;
    if (this.isBajada()==false)
    {
      lugar = " (lugar mano)";
    }
    else{
      lugar = " (lugar mesa)";
    }
    /*confirmar si el orden importa en clips*/
    return("(carta" + j + a + d + v + c + p + t + e + cod + lugar + ")");
  }


   /**
   * Funci�n que devuelve la vida
   * @return vida de la criatura
   */
  public int getVida(){
    return vida;
  }

  /**
   * Funci�n que devuelve el ataque
   * @return puntuaci�n de ataque
   */
  public int getAtaque(){
    return ataque;
  }

  /**
   * Funci�n que devuelve la defensa
   * @return puntuaci�n de defensa
   */
  public int getDefensa(){
    return defensa;
  }
  
  /**
   * Funci�n que devuelve el color
   * @return color de asociacion
   */
  public Color getColor(){
    return color;
  }

  /**
   * Funci�n que cambia la vida de la carta
   * @param v valor nuevo de la vida
   */
  public void setVida(int v){
	vida = v;
  }

  /**
   * Funci�n que cambia el ataque
   * @param at valor nuevo de ataque
   */
  public void setAtaque(int at){
  	ataque = at;
  }

  /**
   * Funci�n que cambia el valor de la defensa
   * @param def valor nuevo de la defensa
   */
  public void setDefensa(int def){
	defensa = def;
  }
  
   /**
   * Funci�n que cambia el color
   * @param def nuevo color
   */
  public void setColor(Color c){
        color = c;
  }
}