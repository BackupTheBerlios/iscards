//package cartas;

/**
 * <p>T�tulo: GENESIS</p>
 * <p>Descripci�n: Clase abstracta que extenderan todos los objetos de tipo carta</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Tony
 * @version 1.0
 */
public class CCriatura extends CACarta{
    
    private int vida;
    private int ataque;
    private int defensa;
   
  
    
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
//    estado=true;
    	bajada = b;
  }
    
    
    public boolean ejecuta() {  //puede tener hab especiales
        return true;
    }
    
    public boolean ataca(){
        return true;
        
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
   * Funci�n que cambia la vida de la carta
   * @param v valor nuevo de la vida
   */
  public void setVida(int v){

  }

  /**
   * Funci�n que cambia el ataque
   * @param at valor nuevo de ataque
   */
  public void setAtaque(int at){

  }

  /**
   * Funci�n que cambia el valor de la defensa
   * @param def valor nuevo de la defensa
   */
  public void setDefensa(int def){

  }

  
  
  
}