//A MODIFICAR POR TONY

package interfaz;

import cartas.CMazo;
import cartas.CACarta;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.LinkedList;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: Clase que implementa al componente gráfico que representa a un dibujo</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Tony
 * @version 1.0
 */

public class Dibujo extends JPanel{
    
     private Carta bicho,bicho2;
     private Rectangle bordes;
     private LinkedList l;
     private Interfaz inter;
     private boolean mano;
     private int pcx,pcy;
     
  
    /** Creates a new instance of Dibujo */
    public Dibujo(Rectangle bounds,LinkedList lista,Interfaz in,boolean bajable,char tipo,CMazo m) {
        
        setLayout(null);
        setBounds(bounds);
        setOpaque(false);
     
        setBackground(new Color(0,1,0) );
        bordes = bounds;
        bordes.setSize( (int) bordes.getWidth()-5,(int) bordes.getHeight()-1);
        bordes.setLocation(0,0);
        l = lista;
        mano = bajable;
        inter = in;
        if (mano) this.add(new Mazo(m,tipo,20,20,l,in) );
        
		pcx = 100;
		pcy = 30;       
        for(int i=0;i<lista.size();i++){
            bicho2=new Carta((CACarta) lista.get(i),(int)bordes.getWidth(),(int) bordes.getHeight(),in,i,!bajable,pcx,pcy);
            this.add(bicho2);
            pcx = (pcx + 20) % (int)bordes.getWidth();
            
        }
        
       	this.repaint();
    }
    
    
    public void paint(Graphics g){
    
	    if(mano){
    		for(int i=0;i<l.size();i++){
        		if( ((CACarta)l.get(i)).isBajada()){
        			int tam = this.getComponentCount();   
              		if (i<tam) this.remove(i+1);
              		l.remove(i);
             	}
            }
      	    for(int i=this.getComponentCount()-1;i<l.size();i++){
          //   if( ((CACarta)l.get(i)).isBajada()){
                bicho2=new Carta((CACarta) l.get(i),(int)bordes.getWidth(),(int) bordes.getHeight(),inter,i,true,pcx,pcy);
                this.add(bicho2);
                pcx = (pcx + 20) % ((int)bordes.getWidth()-50);
             
           //  }
        	}    
    	}
    	else{
    		for(int i=this.getComponentCount();i<l.size();i++){
            	if( ((CACarta)l.get(i)).isBajada()){
                	bicho2=new Carta((CACarta) l.get(i),(int)bordes.getWidth(),(int) bordes.getHeight(),inter,i,true,pcx,pcy);
                	this.add(bicho2);
                	pcx = (pcx + 20) % (int)bordes.getWidth();
             	}
        	}
     	}
        paintChildren(g);
    	Graphics2D graf = (Graphics2D) g;
       	graf.setColor(Color.BLUE);
       	graf.draw(bordes);
    }
}
