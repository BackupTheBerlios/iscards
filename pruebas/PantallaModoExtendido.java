package CartasMovil;

import javax.microedition.lcdui.*;
import java.util.*;

public class PantallaModoExtendido extends Canvas
{

  private String texto;
  // current message idx
  private int midx = 0;
  // graphic width and height
  private int w, h;
  // font height
  private int fh,fw;
  // font to write message on screen
  private Font f;

  private int x0=0, y0=0;
  private final int ANCHOPANTALLA;
  
  private CommandListener padre;
  private Vector listaLineas;
  private int primeraLinea;
  private boolean seleccionado,entraCompleto,topeAbajo;
  public static final Command ANTERIOR=new Command("Anterior",Command.SCREEN,1);
  public static final Command SIGUIENTE=new Command("Siguiente",Command.SCREEN,1);
  public static final Command botSeleccion=new Command("Seleccionado",Command.SCREEN,1);
  public static final Command VIBRA=new Command("Vibración",Command.SCREEN,1);
  

  
  public PantallaModoExtendido(CommandListener padre)
  {
      super();
      this.padre=padre;
      setCommandListener(padre);
      texto="";
      seleccionado=false;
      ANCHOPANTALLA=(this.getWidth()/7)+1;
      listaLineas=new Vector();
      if ( f == null ){
          f = Font.getFont(  Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_MEDIUM );
          w = this.getWidth();
          h = this.getHeight();
          fh = f.getHeight();
      }
  }

  public void dibuja(String t,boolean s,boolean cabe){
      if (texto.compareTo(t)!=0){
        entraCompleto=cabe;
        texto=t;
        preparaTexto();
        topeAbajo=false;
      }
      seleccionado=s;
      if (entraCompleto)
          primeraLinea=0;
      repaint();
  }
  
  protected void paint(Graphics g)
  {
    int y=fh;
    if (!seleccionado)
        g.setColor( 255, 255, 255 );
    else
        g.setColor( 255,0,0);
        
    g.fillRect( 0, 0, w, h );
    g.setColor( 0, 0, 0 );
    g.setFont( f );
    topeAbajo=false;

    g.translate(-x0, -y0);
    String linea;
    for (int i=primeraLinea;i<listaLineas.size();i++){
        linea=((String)listaLineas.elementAt(i));
        g.drawString( linea, 0, y, Graphics.BASELINE | Graphics.LEFT );
        y+=fh;
    }
    if (y>h){
        if (primeraLinea==0)
            entraCompleto=false;
        topeAbajo=true;
    }
  }

  
  
  private void preparaTexto(){
    int y =fh;
    listaLineas.removeAllElements();
    String linea;
    int ultima=0;
    int sigSalto=texto.indexOf('\n');
    while (ultima+ANCHOPANTALLA<texto.length() || (sigSalto-ultima<ANCHOPANTALLA && sigSalto!=-1)){
        if (sigSalto-ultima<ANCHOPANTALLA && sigSalto>0){
            listaLineas.addElement(texto.substring(ultima,sigSalto));
            ultima=sigSalto;
        }
        else{
            int sigEspacio=texto.indexOf(' ',ultima);
            int anteriorEsp=0;
            while (sigEspacio<ultima+ANCHOPANTALLA && sigEspacio>0 && anteriorEsp!=sigEspacio){
                anteriorEsp=sigEspacio;
                sigEspacio=texto.indexOf(' ',sigEspacio+1);      
            }
            listaLineas.addElement(texto.substring(ultima,anteriorEsp));
            ultima=anteriorEsp+1;
        }            
        y+=fh;
        sigSalto=texto.indexOf('\n',ultima+1);
    }
    if (y>h)
        entraCompleto=false;
    listaLineas.addElement(texto.substring(ultima));
  }
  
  
  public void keyPressed( int key ){
    if ( getGameAction( key ) == Canvas.UP ){
        if (!entraCompleto && primeraLinea>0)
            primeraLinea--;
        repaint();
    }
    else if ( getGameAction( key ) == Canvas.DOWN ){
        if (!entraCompleto && topeAbajo)
            primeraLinea++;
        repaint();
    }
    else if  ( getGameAction( key ) == Canvas.FIRE ){
        padre.commandAction(botSeleccion,this);
    }
    else if ( getGameAction( key ) == Canvas.LEFT ){
        padre.commandAction(ANTERIOR,this);
    }
    else if  ( getGameAction( key ) == Canvas.RIGHT ){
        padre.commandAction(SIGUIENTE,this);
    }
  }
}
