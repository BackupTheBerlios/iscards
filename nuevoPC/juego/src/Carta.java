//A MODIFICAR POR TONY

//package interfaz;

//import cartas.*;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.awt.event.MouseEvent;
import java.lang.Math;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: Clase que implementa al componente gráfico que representa a una carta en el juego</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Tony
 * @version 1.0
 */

class Carta extends JComponent{
	
	private boolean girado;
	private BufferedImage imagen;
	private int posX;
	private int posY;
	private int dX,dY;
    private double escala;
    private CACarta carta;
    private int limiteX,limiteY,index;
    private boolean bajado;
	private Interfaz inter;

public Carta(CACarta direccion,int lX,int lY,Interfaz in,int indice,boolean bajada,int x,int y){
		
		super();
		posX = x;
		posY = y;
        carta = direccion;
		escala = 0.3;
		limiteX = lX;
        limiteY = lY;
        index = indice;
        inter = in;
        bajado = bajada;
       
               
		try{
			System.out.println(carta.getImagen());
            imagen = ImageIO.read(new File(carta.getImagen()));              
            this.setSize(imagen.getWidth(),imagen.getHeight());
            this.setBounds(posX,posY, (int)(imagen.getWidth()*escala), (int)(imagen.getHeight()*escala) );
                 
        }
        catch(IOException e){
            System.out.println(e);
        }	
        
        if (imagen == null){
        	try{
                System.out.println("../Cartas/" + carta.getIdRaza() + "/" + carta.getIdRaza() + "_no_disponible.jpg");
                imagen = ImageIO.read(new File("../Cartas/" + carta.getIdRaza() + "/" + carta.getIdRaza() + "_no_disponible.jpg"));                
                this.setSize(imagen.getWidth(),imagen.getHeight());
                this.setBounds(posX,posY, (int)(imagen.getWidth()*escala), (int)(imagen.getHeight()*escala) );
                 
        	}
        	catch(IOException e){
            	System.out.println(e);
        	}
        }
        
        girado = false;	
       
        
        
        this.addMouseListener(new javax.swing.event.MouseInputAdapter()
		{
        	public void mousePressed(MouseEvent e) {
       	 		dX = e.getX();
				dY=  e.getY();
        		repaint();
    		};

    		public void mouseDragged(MouseEvent e) {
        	
        		System.out.println("esto hace algo");
                           
    		};

    		public void mouseReleased(MouseEvent e) {
    			
            	if(e.getButton() == e.BUTTON1){
                	arrastra(e);
        			if ( (e.getX() == dX) && (e.getY() == dY ) ) {
        				if(!carta.isBajada()){
                    		carta.baja();
                        	inter.baja();
                        	repaint();
                    	}
                    	else rota();
    				}
              	} 
                else if(e.getButton() == e.BUTTON3){
                	amplia();
                        
                }
    		};

    	});
			
}	
	    
public void paint(Graphics g){
       
      
            
        Graphics2D graf = (Graphics2D) g;
        double x = escala;
        double y = escala;
        graf.scale(x,y);
        if(girado) {
        	graf.rotate(Math.PI/2,0,0);
            graf.translate(0, -(imagen.getHeight()) );
        }
        graf.drawImage(imagen,null,0 ,0);
       
    //    graf.setColor(Color.BLUE);
    //    graf.draw3DRect(0,0,200,200,true);
    }
    
public void rota(){
		girado = !girado;
		if (girado){
			this.setSize((int)(imagen.getHeight()*escala),(int)(escala*imagen.getWidth()));
			this.setBounds(posX,posY,(int)(imagen.getHeight()*escala),(int)(escala*imagen.getWidth()));
   		}
		else{
			this.setSize((int)(escala*imagen.getWidth()),(int)(imagen.getHeight()*escala));
            this.setBounds(posX,posY,(int)(escala*imagen.getWidth()),(int)(imagen.getHeight()*escala));
		}
		repaint();
    }
		
public void arrastra(MouseEvent e){
		posX = posX + e.getX() - dX;
		posY=  posY + e.getY() - dY;
                
                if( posX > (limiteX - (int)(imagen.getWidth()*escala))  ){
                    posX = limiteX - (int)(imagen.getWidth()*escala);
                }
                
                if( posX < 0  ){
                    posX = 0;
                }
                
                if( posY < 0  ){
                    posY = 0;
                }
                
                if( posY > (limiteY - (int)(imagen.getHeight()*escala))  ){
                    posY = limiteY - (int)(imagen.getHeight()*escala);
                }
                
	
		if (girado){
			this.setSize((int)(imagen.getHeight()*escala),(int)(escala*imagen.getWidth()));
			this.setBounds(posX,posY,(int)(imagen.getHeight()*escala),(int)(escala*imagen.getWidth()));
   		}
		else{
			this.setSize((int)(escala*imagen.getWidth()),(int)(imagen.getHeight()*escala));
                        this.setBounds(posX,posY,(int)(escala*imagen.getWidth()),(int)(imagen.getHeight()*escala));
		}
		//repaint();
	}
        
	public void amplia(){
       
    	final JFrame ampliacion = new JFrame("bicho"){
        	public void paint(Graphics g){
            	Graphics2D gr = (Graphics2D) g; 
                gr.drawImage(imagen,null,0,30);
           	}
   		   
       	};
       
        ampliacion.getContentPane().setLayout(null);
   
   		//hacemos que la carta se cierre al pulsar en ella
		ampliacion.addMouseListener(new java.awt.event.MouseAdapter()
		{
			public void mouseClicked(MouseEvent e){
				ampliacion.dispose();
			};
			
		});
		ampliacion.setUndecorated(true);
        ampliacion.setBackground(Color.black);
        ampliacion.setResizable(false);
        ampliacion.setVisible(true);
        ampliacion.setSize(imagen.getWidth(),imagen.getHeight() + 30);      
   }

//public String getNombre(){
//    return carta.getDir();    
//}
        
}