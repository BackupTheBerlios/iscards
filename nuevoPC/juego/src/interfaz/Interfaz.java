//A MODIFICAR POR TONY

package interfaz;

import cartas.CMazo;
import cartas.CCriatura;
import cartas.CACarta;
import motorJuego.*;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.awt.event.*;
import java.util.LinkedList;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: Clase que implementa al componente gráfico que representa a una carta en el juego</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Tony
 * @version 1.0
 */

public class Interfaz extends JFrame{
    
   private Dibujo mEne,mMia,mMano;
   
   private JFrame padre;
   private JTabbedPane jTabbedPane1 = new JTabbedPane();
   private JPanel panelJuego = new JPanel();
  JLabel jLabel1 = new JLabel();
  JPanel panelChat = new JPanel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel labelTablero = new JLabel();
  JPanel panelMarcador = new JPanel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JButton BotonSalir = new JButton();
  JPanel panelEstado = new JPanel();
  JLabel textoEstado = new JLabel();
  JPanel panelSalir = new JPanel();
  JPanel juegoMesaOp = new JPanel();
  JPanel juegoMesaJug = new JPanel();
  JPanel juegoManoJug = new JPanel();

  private LinkedList lista1,lista2,lista3;
  private CMazo mazo;
  private CPartida partida;
    
    
    /** Creates a new instance of Interfaz */
    public Interfaz(char tipo,CMazo nmazo, JFrame p) {
    	super("Interfaz");
        getContentPane().setLayout(null);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        padre=p;
/*        manoJuego = new CMano();
	    cementerio = new CMazo();
    	criatJugador = new Vector();
    	hechJugador = new Vector();
    	conjJugador = new Vector();
    	criatContrario = new Vector();
    	hechContrario = new Vector();
    	conjContrario = new Vector();

        partida = new CPartida(null,null,null,null,null,null,null,null,null,null);
*/        
       // this.show();
	    mazo = this.barajearMazo(nmazo);
        if (mazo==null) System.out.println("Esto esta vacio");
        else System.out.println("OK");
      	setUndecorated(true);
     
    	try {
        	cambia_skin(tipo);
         	jbInit();
      	}
      	catch(Exception e) {
        	e.printStackTrace();
      	}
        
        setVisible(true);
       

//***********************************        
        lista1 = new LinkedList();
        lista1.add(new CCriatura(1,10,10,10,10,"prueba","h1","prueba","Criatura","hola","e",false));
        lista1.add(new CCriatura(1,10,10,10,10,"prueba","h2","prueba","Criatura","hola","e",false));
        lista1.add(new CCriatura(1,10,10,10,10,"prueba","h3","prueba","Criatura","hola","e",false));
//***********************************        
        lista2 = new LinkedList();
         
        lista3 = new LinkedList();
        
		//mientras el mazo no sea vacio robamos cartas de él
        if (mazo!=null){
        
        	for(int j=0;j<8;j++){
        		System.out.println(j);
        		CACarta c = (CACarta) (mazo.robaCarta() );
        
        		if (c!=null) lista3.add(c);
        		else System.out.println("mierda");
        	}
        }
        else{
       		lista3.add(new CCriatura(1,10,10,10,10,"prueba","d1","prueba","Criatura","hola","e",false));
        	lista3.add(new CCriatura(1,10,10,10,10,"prueba","d2","prueba","Criatura","hola","e",false));
        	lista3.add(new CCriatura(1,10,10,10,10,"prueba","d3","prueba","Criatura","hola","e",false));
        	lista3.add(new CCriatura(1,10,10,10,10,"prueba","d4","prueba","Criatura","hola","e",false));
        	lista3.add(new CCriatura(1,10,10,10,10,"prueba","d5","prueba","Criatura","hola","e",false));
       } 
       
       
        Dimension dimensiones = Toolkit.getDefaultToolkit().getScreenSize();
        double h = dimensiones.getHeight();
        double w = dimensiones.getWidth() - 20;
        
        mEne = new Dibujo(new Rectangle(0,0,(int)w,(int) h/3),lista1,this,false,tipo,mazo);
       // getContentPane().add(dib);
        labelTablero.add(mEne);
        
        mMia = new Dibujo(new Rectangle(0,(int)h/3,(int)w,(int)h/3),lista2,this,false,tipo,mazo);
       // getContentPane().add(dib2);
        labelTablero.add(mMia);
        
        mMano = new Dibujo(new Rectangle(0,(int)(2*(h/3)),(int)w,((int)h/3) - 60),lista3,this,true,tipo,mazo);
       // getContentPane().add(dib2);
        labelTablero.add(mMano);
       
    }
    
   
 
//Adapta el fondo y puntero del raton al tipo de raza elegido
   private void cambia_skin(char tipo){

     ImageIcon cursor;
     switch (tipo){
       case 'A':{
         //skin de angeles
             ImageIcon imagenSkin=new ImageIcon("../imagenes/fondos/fondo_Ángeles.jpg");
             labelTablero.setIcon(imagenSkin);

             cursor=new ImageIcon("../imagenes/cursores/ángeles.gif");

         break;
       }
       case 'D':{
         //skin de demonios
             ImageIcon imagenSkin=new ImageIcon("../imagenes/fondos/fondo_Demonios.jpg");
             labelTablero.setIcon(imagenSkin);

             cursor=new ImageIcon("../imagenes/cursores/demonios.gif");

         break;
       }
       case 'H':{
         //skin de humanos
             ImageIcon imagenSkin=new ImageIcon("../imagenes/fondos/fondo_Humanos.jpg");
             labelTablero.setIcon(imagenSkin);

              cursor=new ImageIcon("../imagenes/cursores/humanos.gif");

         break;
       }
       case 'I':{
         //skin de inmortales
             ImageIcon imagenSkin=new ImageIcon("../imagenes/fondos/fondo_Inmortales.jpg");
             labelTablero.setIcon(imagenSkin);

             cursor=new ImageIcon("../imagenes/cursores/inmortales.gif");
         break;
       }
       default: {
         cursor=new ImageIcon ();
         break;
       }
     }

      Image image=cursor.getImage();
      Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(0,0), "img");
      this.setCursor(puntero);

   }

   private void jbInit() throws Exception {


      this.getContentPane().setLayout(null);
    jTabbedPane1.setForeground(Color.black);
    jTabbedPane1.setBounds(new Rectangle(5, 5, 5, 5));


    this.getContentPane().setLayout(null);

    panelJuego.setLayout(null);
    panelJuego.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    this.getContentPane().setBackground(Color.black);
//    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setFont(new java.awt.Font("Dialog", 3, 15));

    juegoMesaOp.setBounds(new Rectangle(0, 0,
                                         (int)panelJuego.getSize().getWidth(),
                                         (int)panelJuego.getSize().getHeight()/3));
    juegoMesaOp.setOpaque(false);
    juegoMesaOp.setLayout(null);

    juegoMesaJug.setMinimumSize(new Dimension(0, 0));
    juegoMesaJug.setBounds(new Rectangle(0,  (int)panelJuego.getSize().getHeight()/3,
                                          (int)panelJuego.getSize().getWidth(),
                                          (int)panelJuego.getSize().getHeight()/3));

    juegoMesaJug.setOpaque(false);
    juegoMesaJug.setLayout(null);


    juegoManoJug.setBounds(new Rectangle(0,  2*(int)panelJuego.getSize().getHeight()/3,
                                          (int)panelJuego.getSize().getWidth(),
                                          (int)panelJuego.getSize().getHeight()/3));

    juegoManoJug.setOpaque(false);
    juegoManoJug.setLayout(null);


    this.getContentPane().add(panelEstado, null);
    this.getContentPane().add(jTabbedPane1, null);
    this.getContentPane().add(panelSalir, null);


     //modificacion de skins segun el tipo de raza

    labelTablero.setBounds(new Rectangle(new Point(0,0),Toolkit.getDefaultToolkit().getScreenSize()));


    //fondos de madera de chat y marcador
    ImageIcon imagen=new ImageIcon("../imagenes/fondos/fondo.jpg");
    jLabel1.setIcon(imagen);
    jLabel1.setBounds(new Rectangle(new Point(0,0),Toolkit.getDefaultToolkit().getScreenSize()));


	jLabel3.setIcon(imagen);
    jLabel3.setBounds(new Rectangle(new Point(0,0),Toolkit.getDefaultToolkit().getScreenSize()));



      //titulo genesis
/*    ImageIcon imagen2=new ImageIcon("g.gif");
      jLabel6.setIcon(imagen2);
      jLabel6.setBounds(new Rectangle(88, 27, 177, 172));


      ImageIcon imagen3=new ImageIcon("enesis.gif");
      jLabel5.setIcon(imagen3);
      jLabel5.setBounds(new Rectangle(260, 50, 298, 133));


      jLabel4.setIcon(imagen2);
      jLabel4.setBounds(new Rectangle(88, 27, 177, 172));

      jLabel7.setIcon(imagen3);
      jLabel7.setBounds(new Rectangle(260, 50, 298, 133));
*/

	panelChat.setLayout(null);
    panelChat.setSize(Toolkit.getDefaultToolkit().getScreenSize());

    panelMarcador.setLayout(null);
    panelMarcador.setSize(Toolkit.getDefaultToolkit().getScreenSize());

    BotonSalir.setBackground(Color.black);
    //BotonSalir.setBounds(new Rectangle(100, 250, 100,50 ));
    BotonSalir.setBounds(new Rectangle(0, 0, 100,25 ));
    BotonSalir.setFont(new java.awt.Font("Serif", 3, 25));
    BotonSalir.setForeground(Color.orange);
    BotonSalir.setBorderPainted(false);
    BotonSalir.setContentAreaFilled(false);
    BotonSalir.setText("Salir");
    BotonSalir.addMouseListener(new Fondo_BotonSalir_mouseAdapter(this));

    panelSalir.setBounds(new Rectangle((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-100, 0,
                                       100, 25));
    panelSalir.setLayout(null);
    panelSalir.setBackground(Color.BLACK);



    panelEstado.setBounds(new Rectangle(0,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-30,
                                      (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 30));
    panelEstado.setLayout(null);
    panelEstado.setBackground(Color.BLACK);

    ponTextoEstado(" ¡¡Bienvenido a Génesis!!");

    textoEstado.setBounds(new Rectangle(125, 0,
                                        (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 25));


  /*  this.getContentPane().add(panelEstado, null);
    this.getContentPane().add(jTabbedPane1, null);
*/


    jTabbedPane1.add(panelMarcador,    "MARCADOR");
    jTabbedPane1.add(panelJuego,    "JUEGO");
    jTabbedPane1.add(panelChat,   "CHAT");

    jTabbedPane1.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-20,
                          (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()-35);
    jTabbedPane1.setBackground(Color.white);



    panelJuego.add(juegoMesaOp, null);
    panelJuego.add(juegoMesaJug, null);
    panelJuego.add(juegoManoJug, null);
    panelJuego.add(labelTablero, null);


    panelChat.add(jLabel3, null);
    panelChat.add(jLabel4,null);
    panelChat.add(jLabel7,null);

    panelSalir.add(BotonSalir, null);

    panelEstado.add(textoEstado, null);

    panelMarcador.add(jLabel6, null);
    panelMarcador.add(jLabel5, null);
    panelMarcador.add(jLabel1, null);
//    this.getContentPane().add(panelSalir, null);

   }


   //Escribe lo pasado por parametro en el label de Estado
   public void ponTextoEstado(String cadena){

     textoEstado.setText("COMENTARIOS: "+cadena);
     textoEstado.setForeground(Color.WHITE);
     textoEstado.setFont(new java.awt.Font("Serif", 3, 25));

   }

  void BotonSalir_mouseClicked(MouseEvent e) {
	padre.setEnabled(true);
	padre.show();
	this.dispose();
  }

  void BotonSalir_mouseEntered(MouseEvent e) {
    BotonSalir.setForeground(Color.white);
  }

  void BotonSalir_mouseExited(MouseEvent e) {
    BotonSalir.setForeground(Color.orange);
  }


public void  baja(){
  
    for(int i = 0;i<lista3.size();i++){
    	if (lista3.size() != 0) {
        	if ( ((CACarta)lista3.get(i)).isBajada() ){
            	lista2.add(lista3.get(i));
        	}
        }
    }
    repaint();
}

  public CPartida getPartida(){
  	return partida;	
  }
  
  private CMazo barajearMazo(CMazo mazoJuego){
    CMazo mazoAux = new CMazo();
    //cogemos las cartas aleatorias del mazo
    int indiceAleatorio;
    Double aleat;
    int cant = mazoJuego.getCartas().size();
    for(int i=0; i<cant; i++){
      indiceAleatorio = new Double(Math.random() * mazoJuego.getCartas().size()).intValue();
      CACarta carta = (CACarta)mazoJuego.getCartas().get(indiceAleatorio);
      mazoJuego.getCartas().remove(indiceAleatorio);
      mazoAux.anadeCarta(carta);

    }
    return mazoAux;
  }

}



class Fondo_BotonSalir_mouseAdapter extends java.awt.event.MouseAdapter {
  Interfaz adaptee;

  Fondo_BotonSalir_mouseAdapter(Interfaz adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.BotonSalir_mouseClicked(e);
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.BotonSalir_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.BotonSalir_mouseExited(e);
  }
}



