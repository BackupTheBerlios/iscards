package cartas;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: Clase que implementa a los objetos de tipo carta Conjuro</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Tony
 * @version 1.0
 */

public class CConjuro extends CACarta {

  /**
   *
   */
  private int duracion;

  /**
   * Constructora de la clase Carta Conjuro
   * @param niv
   * @param cost
   * @param punt
   * @param cod
   * @param nom
   * @param idR
   * @param idT
   * @param coment
   * @param hab
   */
  public CConjuro(int niv, int cost, int punt, String cod, String nom, String idR, String idT, String coment, String hab,boolean b){

    nivel=niv;
    coste=cost;
    puntos=punt;
    codigo=cod;
    nombre=nom;
    idRaza=idR;
    idTipo=idT;
    comentarios=coment;
    habilidades=hab;
  	imagen = "../../Cartas/" + idRaza + "/Conjuros/" + nombre + ".jpg";
//    estado=true;
    bajada = b;
  }

  /** Creates a new instance of CConjuro */
  public CConjuro(int dur, String h) {
      duracion = dur;
      habilidades = h;
  }

  /**
   *
   * @param cad
   * @param posIni
   * @return
   */
  private Integer leeNum(String cad, int posIni){

       int j = 0; //posicion del proximo digito
       int i = posIni;
       char c = cad.charAt(i);
       i++;
       char[] numc = new char[3];

       numc[0] = '0';
       numc[1] = '0';
       numc[2] = '0';

       while ((c>'0') && (c<'9') && (j<3)){
          if (j !=0 ){
              numc[0] = numc[1];
              numc[1] = numc[2];
          }
           numc[2] = c;
           j++;
           c = cad.charAt(i);
           i++;
        }
       String nums = new String(numc);
       Integer valor = Integer.valueOf(nums);
       return valor;
  }

  /**
   *
   * @return
   */
  public boolean ejecuta() {
      int longitud= habilidades.length();
      char c,efecto,operador,tipo;
      int i=0;
      int aux;
      boolean error = false;
      Integer valor1 = new Integer(0);//Aqui se almacena la magnitud del efecto
      Integer valor2 = new Integer(0); //mas significativo en posiciones mas bajas

      while (i<longitud && !error){
          c=habilidades.charAt(i);
          i++;
          switch(c){
              case 'A':
              case 'P':
              case 'D':
       /*n natural , {+,-,*,/}, k natural, tipo {A,D,I,H,C =  cualquiera}
       k = cantidad de criaturas afectadas.  n =  cantidad a modificar.*/

                  efecto = c;
                  valor1 = leeNum(habilidades, i);
                  i += valor1.toString().length();

                  c=habilidades.charAt(i);
                  i++;

                 if ((c == '+') || (c == '*') || (c == '-') || (c == '/')){
                   operador = c;

                 } else error = true;

                  valor2 = leeNum(habilidades, i);
                  i += valor2.toString().length();

                  c=habilidades.charAt(i);
                   i++;

                 if ((c == 'A') || (c == 'D') || (c == 'I') || (c == 'H') || (c == 'C')){
                   tipo = c;

                 } else error = true;

              //Ejecucion de habilidad en el juego
              break;
              case 'M':
                 /*n natural , {+,-}, tipo {A,D,I,H,C =  cualquiera}
                   n =  cantidad a modificar.*/

                  efecto = c;
                  valor1 = leeNum(habilidades, i);
                  i += valor1.toString().length();
                  c=habilidades.charAt(i);
                   i++;

                 if ((c == '+') || (c == '*') || (c == '-') || (c == '/')){
                   operador = c;

                 } else error = true;

                  c=habilidades.charAt(i);
                  i++;

                  if ((c == 'A') || (c == 'D') || (c == 'I') || (c == 'H') || (c == 'C')){
                   tipo = c;

                 } else error = true;

                   //Ejecucion de habilidad en el juego

              break;

              case 'B':
              case 'R':
              /*n natural, k natural.
               n = numero de unidades. k = nivel o ataques extra o protegidos*/
                  efecto = c;

                  valor1 = leeNum(habilidades, i);
                  i += valor1.toString().length();
                  c=habilidades.charAt(i);
                  i++;

                  if (c != '*') error = true;

                  valor2 = leeNum(habilidades, i);
                  i += valor2.toString().length();
                  c=habilidades.charAt(i);
                  i++;

                   //Ejecucion de habilidad en el juego
              break;


              case 'I':
              case 'E':
              case 'Z':
              /*      tipo {A,D,I,H,C =  cualquiera} */
                  efecto = c;
                  c=habilidades.charAt(i);
                  i++;

                  if ((c == 'A') || (c == 'D') || (c == 'I') || (c == 'H') || (c == 'C')){
                   tipo = c;

                 } else error = true;

                  //ejecuta accion en el juego
              break;

              case 'Q':
              case 'G':
              case 'L':
    /* n natural.
    n = numero de unidades afectadas por la habilidad o de cartas robadas.*/
                  efecto = c;

                  valor1 = leeNum(habilidades, i);
                  i += valor1.toString().length();
                  c=habilidades.charAt(i);
                  i++;

                //ejecuta habilidad
              break;

              case 'F':
                  efecto = c;
                  c=habilidades.charAt(i);
                  i++;
              /*sin datos extra*/
              //ejecuta habilidad
              break;

              case '.':
                  if (!error) return true;
                  else return false;


              default: return false;
          }
      }
   return !error;
  }

  /**
   * Funcin que devuleve los atributos de una carta de tipo conjuro en
   * formato de texto semejante a clips para poder procesarla mediante
   * la inteligencia artificial
   * @return
   */
  public String dame_clips(){
    String j = " (jugador PC)";
    String a = " (ataque 0)";//Los conjuros no tienen estas propiedades, los
    String d = " (defensa 0)";// ponemos a 0 para mantener el formato de clips
    String v = " (vidas 0)";//
    String c = " (coste " + this.getCoste() + ")";
    String p = " (puntos " + this.getPuntos() + ")";
    String t = " (tipo conjuro)";
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
}
