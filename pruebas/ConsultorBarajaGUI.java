package CartasMovil;

import javax.microedition.lcdui.*;
import MazoMovil.*;

public class ConsultorBarajaGUI implements CommandListener{
    
    private List lista;
    private PantallaModoExtendido cajaTexto;
    private Command volver;
    private Command enviar;
    private Command cambiarModo;
    private ConsultorBaraja consultor;
    
    
    public ConsultorBarajaGUI(ConsultorBaraja cons) {
        consultor=cons;
        lista=new List("PRUEBA 1ª ITERACIÓN",List.MULTIPLE);
        cajaTexto=new PantallaModoExtendido(this);
        volver=new Command("Volver",Command.EXIT,1);
        enviar=new Command("Enviar",Command.ITEM,1);
        cambiarModo=new Command("Cambiar Modo",Command.ITEM,1);
        lista.addCommand(volver);
        lista.addCommand(enviar);
        lista.addCommand(cambiarModo);       
        cajaTexto.addCommand(volver);
        cajaTexto.addCommand(enviar);
        cajaTexto.addCommand(cambiarModo);
        lista.setCommandListener(this);
        cajaTexto.setCommandListener(this);        
    }
    
    public void muestraCompacto(String[] nombres){
        lista.deleteAll();
        for (int i=0;i<nombres.length;i++){
            lista.append(nombres[i],null);
        }
    }
    
    public void muestraExtendido(String texto,boolean seleccionada){
        cajaTexto.dibuja(texto,seleccionada,true);
    }
    
    public List getUICompacto(){
        return lista;
    }
    
    public PantallaModoExtendido getUIExtendido(){
        return cajaTexto;
    }
        
    
    public void commandAction(Command c, Displayable d){
        if (c==volver){
            consultor.finalizar();
        }
        else if (c==enviar){
            if (d.equals(lista)){
                boolean[] s=new boolean[lista.size()];
                lista.getSelectedFlags(s);
                consultor.setSeleccionados(s);
            }
            consultor.enviarCartas();
        }
        else if (c==cambiarModo){
            consultor.cambiarModo();
        }
        else if (c==PantallaModoExtendido.ANTERIOR){
            consultor.anterior();
        }
        else if (c==PantallaModoExtendido.SIGUIENTE){
            consultor.siguiente();
        }
        else if (c==PantallaModoExtendido.botSeleccion){
            consultor.seleccionaActual();
        }
    }
    
}
