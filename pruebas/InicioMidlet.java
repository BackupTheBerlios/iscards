/*
 * InicioMidlet.java
 *
 * Created on 15 de diciembre de 2004, 15:29
 */
package CartasMovil;

import MazoMovil.*;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import java.util.Enumeration;

/**
 *
 * @author  Javi
 * @version
 */
public class InicioMidlet extends MIDlet implements CommandListener{
    
    private Display display;
    //private Command iniciar=new Command("Iniciar",Command.ITEM,1);
    private ConsultorBaraja consultor;
    private Command inicio=new Command("inicio",Command.ITEM,1);
    private Command salta=new Command("salta",Command.ITEM,1);
    private TextBox t;
    
    public void startApp() {
        display=Display.getDisplay(this);
        t=new TextBox("hola",null,200,TextField.ANY);
        //consultor=new ConsultorBaraja(this);
        //display.setCurrent(consultor.getGUI());
        t.addCommand(inicio);
        t.addCommand(salta);
        display.setCurrent(t);
        t.setCommandListener(this);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
    public void commandAction(Command c, Displayable d){
        if (c.equals(ConsultorBaraja.ACTUALIZARGUI)){
            display.setCurrent(d);
        }
        else if (c.equals(ConsultorBaraja.FINENVIO)){
            display.setCurrent(new Alert("Yujuuuuu","S'acabó",null,AlertType.CONFIRMATION));
        }
        else if (c.equals(PantallaModoExtendido.VIBRA)){
            display.vibrate(100);
        }
        else if (c.getLabel().equals("inicio")){
            BarajaMovil b=new BarajaMovil(true,"Marikitas","javi");
            String[] inf=new String[7];
            inf[0]="Julandras";
            inf[1]="Marikitas";
            inf[2]="8";
            inf[3]="3";
            inf[4]="Es gay";
            inf[5]="vio a su madre desnuda en la ducha de pequeño";
            inf[6]="2";        
            CartaMovil c1=new CartaMovil(1,1,inf);
            inf[0]="pepito";
            inf[1]="bombero-torero";
            inf[2]="2";
            inf[3]="7";
            inf[4]="va de pueblo en pueblo con su espectaculo";
            inf[5]="quiso ser como jesulin";
            inf[6]="3";
            CartaMovil c2=new CartaMovil(2,1,inf);
            inf[0]="C3PO";
            inf[1]="androide";
            inf[2]="4";
            inf[3]="7";
            inf[4]="es bantante cansino";
            inf[5]="le acompaña un enano cabezon que silva";
            inf[6]="3";
            CartaMovil c3=new CartaMovil(3,1,inf);
            inf[0]="Jesús Vázquez";
            inf[1]="Marikitas";
            inf[2]="14";
            inf[3]="9";
            inf[4]="hace muchas cosas como presentar programas de la tele, llegó a sacar un disco... y además en un icono entre los maricas españoles";
            inf[5]="esta es la prueba definitiva del modo extendido, tengo que probar si dibuja bien los limites de la pantalla y para eso hace falta escribir mucho. Sin tele y sin cerveza homer pierde la cabeza, sin tele y sin cerveza homer pierde la cabeza";
            inf[6]="1";        
            CartaMovil c4=new CartaMovil(4,1,inf);
            b.añadeCarta(c1);
            System.out.println("he añadido la 1ª");
            b.añadeCarta(c2);
            System.out.println("he añadido la 2ª");
            b.añadeCarta(c3);
            System.out.println("he añadido la 3ª");
            b.añadeCarta(c1);
            System.out.println("he añadido la 4ª");
            b.añadeCarta(c4);
            System.out.println("he añadido la 5ª");
            b.añadeCarta(c4);
            System.out.println("he añadido la 6ª");
            b.añadeCarta(c4);
            System.out.println("he añadido la 7ª");
            consultor=new ConsultorBaraja(this);
            display.setCurrent(consultor.getGUI());
        }
        else if (c.getLabel().equals("salta")){
            System.out.println("hola");
            consultor=new ConsultorBaraja(this);
            display.setCurrent(consultor.getGUI());
            
        }        
    }
}
