package CartasMovil;

import MazoMovil.*;
import javax.microedition.lcdui.*;
import java.util.Enumeration;

public class ConsultorBaraja{

    private ConsultorBarajaGUI gui;
    private BarajaMovil baraja;
    private int indiceActual;
    private boolean modoExtendido;
    private CommandListener padre;
    public static final Command ACTUALIZARGUI=new Command("Reflejar",Command.ITEM,1);
    public static final Command FINENVIO=new Command("Envío terminado",Command.ITEM,1);
    private int[] claves;
    private boolean[] seleccionados;
    
    public ConsultorBaraja(CommandListener padre){
        baraja=new BarajaMovil(false,null,null);
        modoExtendido=false;
        indiceActual=0;
        gui=new ConsultorBarajaGUI(this);
        this.padre=padre;
        claves=actualizaClaves();
        gui.muestraCompacto(preparaNombres());
        gui.muestraExtendido(baraja.buscaCarta(claves[indiceActual]).toString(),seleccionados[indiceActual]);
    }
    
    public Displayable getGUI(){
        if (modoExtendido)
        	return gui.getUIExtendido();
        else return gui.getUICompacto();
    }
    
    private String[] preparaNombres(){
        int j,i;
        i=0;
        String[] nombres=new String[baraja.getNumeroCartas()];
        Enumeration e=baraja.enumeraCartas();
        CartaMovil c;
        while (e.hasMoreElements()){
            c=((CartaMovil)e.nextElement());
            for (j=0;j<c.getCantidad();j++){
                nombres[i]=c.toShortString();
                if (j>0)
                    nombres[i]=nombres[i]+"(Repetida)";
                i++;
            }
        }
        return nombres;
    }
    
    private int[] actualizaClaves(){
        int j,i;
        i=0;
        seleccionados=new boolean[baraja.getNumeroCartas()];
        int[] aux=new int[baraja.getNumeroCartas()];
        Enumeration e=baraja.enumeraCartas();
        CartaMovil c;
        while (e.hasMoreElements()){
            c=((CartaMovil)e.nextElement());
            for (j=0;j<c.getCantidad();j++){
                aux[i]=c.getCodigoID();
                seleccionados[i]=false;
                i++;
            }
        }
        return aux;
    }
    
    public void finalizar(){
        padre.commandAction(FINENVIO,getGUI());
        System.gc();        
    }
    
    public void setSeleccionados(boolean[] s){
        for (int i=0;i<seleccionados.length;i++)
            seleccionados[i]=s[i];
    }
    
    public void cambiarModo(){
        if (modoExtendido){
            gui.getUICompacto().setSelectedFlags(seleccionados);
        }
        else{
            boolean[] s=new boolean[seleccionados.length];
            gui.getUICompacto().getSelectedFlags(s);
            setSeleccionados(s);
        }
        modoExtendido=!modoExtendido;
        gui.muestraExtendido(baraja.buscaCarta(claves[indiceActual]).toString(),seleccionados[indiceActual]);
        padre.commandAction(ACTUALIZARGUI,getGUI());
    }
    
    public void anterior(){
        if (indiceActual>0){
            indiceActual--;
            gui.muestraExtendido(baraja.buscaCarta(claves[indiceActual]).toString(),seleccionados[indiceActual]);
        }
        else
            padre.commandAction(PantallaModoExtendido.VIBRA,getGUI());        
    }
    
    public void siguiente(){
        if (indiceActual<seleccionados.length-1){
            indiceActual++;
            gui.muestraExtendido(baraja.buscaCarta(claves[indiceActual]).toString(),seleccionados[indiceActual]);
        }
        else
            padre.commandAction(PantallaModoExtendido.VIBRA,getGUI());
    }
    
    public void seleccionaActual(){
        seleccionados[indiceActual]=!seleccionados[indiceActual];
        gui.muestraExtendido(baraja.buscaCarta(claves[indiceActual]).toString(),seleccionados[indiceActual]);
    }
    
    public void enviarCartas(){
        for (int i=0;i<claves.length;i++)
            if (seleccionados[i])
                baraja.eliminaCarta(claves[i]);
        claves=actualizaClaves();
        indiceActual=0;
        gui.muestraCompacto(preparaNombres());
        gui.muestraExtendido(baraja.buscaCarta(claves[indiceActual]).toString(),seleccionados[indiceActual]);
        System.gc();
    }

}
