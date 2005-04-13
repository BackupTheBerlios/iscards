//A MODIFICAR POR TONY

package interfaz;

import cartas.*;
import motorJuego.*;
import usuario.*;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.awt.event.*;
import java.util.Vector;

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
	private Vector lista1,lista2,lista3;
	private CMazo mazo;
	private CPartida partida;
	private Usuario usuario;
	JPanel panelChat = new JPanel();
	JPanel panelMarcador = new JPanel();
	JPanel panelEstado = new JPanel();
	JPanel panelSalir = new JPanel();
	JPanel juegoMesaOp = new JPanel();
	JPanel juegoMesaJug = new JPanel();
	JPanel juegoManoJug = new JPanel();

	JButton botonSalir = new JButton();
	JButton botonExplorador = new JButton();
	JButton botonAceptarExplorador = new JButton();

	JLabel jLabel1 = new JLabel();
	JLabel jLabel3 = new JLabel();
	JLabel jLabel4 = new JLabel();
	JLabel jLabel5 = new JLabel();
	JLabel jLabel6 = new JLabel();
	JLabel jLabel7 = new JLabel();
	JLabel jLabel8 = new JLabel();
	JLabel jLabel10 = new JLabel();
	JLabel jLabel11 = new JLabel();
	JLabel labelTablero = new JLabel();
	JLabel textoEstado = new JLabel();

	MiTextArea areaEscritura = new MiTextArea("../imagenes/chat/fondo.jpg");
	MiTextArea areaCharla = new MiTextArea("../imagenes/chat/fondo.jpg");


    String avatar = "../imagenes/Escudo_Genesis.jpg";
    ImageIcon avatarIcon = new ImageIcon(avatar);
    ImageIcon avatarIcon2 = new ImageIcon(avatar);
	int cont;
    //variables que almacenan la dimension de la pantalla
    int altoPantalla = (int) Toolkit.getDefaultToolkit().getScreenSize().
                       getHeight();
    int anchoPantalla = (int) Toolkit.getDefaultToolkit().getScreenSize().
                        getWidth();
    int unidadAlto = altoPantalla / 40;
    int unidadAncho = anchoPantalla / 100;



    /** Creates a new instance of Interfaz */
    public Interfaz(char tipo,CPartida partida, JFrame p, Usuario usu) {
    	super("Interfaz");
        getContentPane().setLayout(null);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.padre=p;
        this.partida = partida;
        this.usuario = usu;

	    if (partida.getMazo()==null) System.out.println("Esto esta vacio");
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

//********************************************
//********************************************
        //*********
        //En lugar de cargar lista3... tiene que cargar partida.getMesa().getMano() , y asi con lista 1 y 2
        //para que cargue bien los vectores que crea la partida
        //*********
/*
        lista1 = new Vector();
        lista1.add(new CCriatura(1,10,10,10,10,"prueba","h1","prueba","Criatura","hola","e",true));
        lista1.add(new CCriatura(1,10,10,10,10,"prueba","h2","prueba","Criatura","hola","e",true));
        lista1.add(new CCriatura(1,10,10,10,10,"prueba","h3","prueba","Criatura","hola","e",true));
*/
	lista1 = partida.getMesa().getJugador2().getVectorCriaturas(); //mesa enemiga
        lista2 = partida.getMesa().getJugador1().getVectorCriaturas();;      //mi mesa
        lista3 = partida.getMano().getCartas();		//mi mano

		//mientras el mazo no sea vacio robamos cartas de él
        if (partida.getMazo()!=null){

        	for(int j=0;j<8;j++){
        		System.out.println(j);
        		CACarta c = (CACarta) (partida.getMazo().robaCarta() );

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

        mEne = new Dibujo(new Rectangle(0,0,(int)w,(int) h/3),lista1,this,false,tipo,partida.getMazo());
       // getContentPane().add(dib);
        labelTablero.add(mEne);

        mMia = new Dibujo(new Rectangle(0,(int)h/3,(int)w,(int)h/3),lista2,this,false,tipo,partida.getMazo());
       // getContentPane().add(dib2);
        labelTablero.add(mMia);

        mMano = new Dibujo(new Rectangle(0,(int)(2*(h/3)),(int)w,((int)h/3) - 60),lista3,this,true,tipo,partida.getMazo());
       // getContentPane().add(dib2);
        labelTablero.add(mMano);
//********************************************
//********************************************
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

	panelChat.setLayout(null);
    panelChat.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        //***************************************************************
         //**********ZONA DE NUESTRO AVATAR*******************************
          //***************************************************************
           //inicializar el botón que cambia el avatar
           botonExplorador.setText("Cambiar Imagen");
        botonExplorador.setBounds(new Rectangle(unidadAncho * 76,
                                                unidadAlto * 34,
                                                unidadAncho * 20,
                                                unidadAlto * 2));
        botonExplorador.setFont(new java.awt.Font("Lucida Bright", Font.ITALIC,
                                                  12));
        botonExplorador.setForeground(Color.green);
        botonExplorador.setBorderPainted(false);
        botonExplorador.setContentAreaFilled(false);
        //oyente del boton explorador
        botonExplorador.addMouseListener(new fondo_BotonExplorador_mouseAdapter(this));

        //inicializar la etiqueta donde esta el avatar
        jLabel11.setBounds(unidadAncho * 76, unidadAlto * 11, unidadAncho *20,
                           unidadAlto * 12);
        //redimensionar la imagen si es muy grande
        ImageIcon avatarIconAux = new ImageIcon();
        if (avatarIcon.getIconWidth() > jLabel11.getWidth()) {
            avatarIconAux = new ImageIcon(
                    avatarIcon.getImage().getScaledInstance(jLabel11.getWidth(),
                    -1, Image.SCALE_DEFAULT));
            avatarIcon = avatarIconAux;
        }
        //asignar la imagen a la etiqueta
        jLabel11.setIcon(avatarIcon);
        repaint();

        //***************************************************************
         //**********ZONA DEL AVATAR DEL CONTRARIO************************
          //***************************************************************
        //inicializar la etiqueta donde esta el avatar
        jLabel10.setBounds(unidadAncho * 79, unidadAlto * 25, unidadAncho * 14,
                           unidadAlto * 9);
        //redimensionar la imagen si es muy grande
        ImageIcon avatarIconAux2 = new ImageIcon();
        if (avatarIcon.getIconWidth() > jLabel10.getWidth()) {
            avatarIconAux2 = new ImageIcon(
                    avatarIcon.getImage().getScaledInstance(jLabel10.getWidth(),
                    -1, Image.SCALE_DEFAULT));
            avatarIcon = avatarIconAux2;
        }
        //asignar la imagen a la etiqueta
        jLabel10.setIcon(avatarIcon);
        repaint();

        /****************************************************************
         //**********ZONA DE ESCRITURA USUARIO****************************
           //***************************************************************/
          //inicializar el area de escritura
          areaEscritura.setBounds(unidadAncho * 0, unidadAlto * 0,
                                  unidadAncho * 60, unidadAlto * 4);
        //activar la opcion de partir las lineas si son muy largas
        areaEscritura.setLineWrap(true);
        //parte por los limites de palabras
        areaEscritura.setWrapStyleWord(true);
        //introducir el textArea en un scrollPane
        JScrollPane panelEscritura = new JScrollPane(areaEscritura);
        //panelEscritura.setVerticalScrollBarPolicy(
        //JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelEscritura.setBounds(unidadAncho * 8, unidadAlto * 31,
                                 unidadAncho * 64, unidadAlto * 4);
        areaEscritura.addKeyListener(new Oyente(this));

        //***************************************************************
         //**********ZONA CHARLA DE USUARIOS******************************
         //***************************************************************
          //inicializa area de charla
          areaCharla.setBounds(unidadAncho * 0, unidadAlto * 0,
                               unidadAncho * 60, unidadAlto * 17);
        //el area de charla no puede ser editado por el usuario
        areaCharla.setEditable(false);
        //activar la opcion de partir las lineas si son muy largas
        areaCharla.setLineWrap(true);
        //parte por los limites de palabras
        areaCharla.setWrapStyleWord(true);
        //inicializar el scrollPane
        JScrollPane panelCharla = new JScrollPane(areaCharla);
        //la barra baja automaticamente
        panelCharla.setAutoscrolls(true);
        panelCharla.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //panelCharla.setHorizontalScrollBarPolicy(
        //    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelCharla.setForeground(Color.black);
        //inicializar esquina inferior derecha
        JLabel etiquetaAux = new JLabel();
        ImageIcon iconoAux = new ImageIcon("../imagenes/chat/fondo.jpg");
        etiquetaAux.setIcon(iconoAux);
        panelCharla.setCorner(JScrollPane.LOWER_RIGHT_CORNER,
                              etiquetaAux);
        //inicializar limites del panel de charla
        panelCharla.setBounds(unidadAncho * 8, unidadAlto * 11,
                              unidadAncho * 64, unidadAlto * 19);
        panelCharla.setBorder(BorderFactory.createEtchedBorder(Color.black,
                Color.black));

        panelChat.add(botonExplorador, null);
        panelChat.add(jLabel10);
        panelChat.add(jLabel11);
        panelChat.add(panelCharla, BorderLayout.CENTER);
        panelChat.add(panelEscritura);

    panelMarcador.setLayout(null);
    panelMarcador.setSize(Toolkit.getDefaultToolkit().getScreenSize());

    botonSalir.setBackground(Color.black);
    //botonSalir.setBounds(new Rectangle(100, 250, 100,50 ));
    botonSalir.setBounds(new Rectangle(0, 0, 100,25 ));
    botonSalir.setFont(new java.awt.Font("Serif", 3, 25));
    botonSalir.setForeground(Color.orange);
    botonSalir.setBorderPainted(false);
    botonSalir.setContentAreaFilled(false);
    botonSalir.setText("Salir");
    botonSalir.addMouseListener(new Fondo_botonSalir_mouseAdapter(this));

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

    panelSalir.add(botonSalir, null);

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

  void botonSalir_mouseClicked(MouseEvent e) {
	padre.setEnabled(true);
	padre.show();
	this.dispose();
  }

  void botonSalir_mouseEntered(MouseEvent e) {
    botonSalir.setForeground(Color.white);
  }

  void botonSalir_mouseExited(MouseEvent e) {
    botonSalir.setForeground(Color.orange);
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


  void BotonExplorador_mouseClicked(MouseEvent e) {
        //Cuando se pulsa el boton, aparece un browser
        JLabel jLabel8 = new JLabel();
        jLabel8.setBounds(550, 150, 200, 300);
        jLabel8.setBackground(Color.BLUE);
        JFileChooser navegador = new JFileChooser("c:/");
        navegador.setSize(400, 300);
        Container parent = botonAceptarExplorador.getParent();
        int choice = navegador.showSaveDialog(parent);
        if (choice == JFileChooser.APPROVE_OPTION) {
            String filename = navegador.getSelectedFile().getAbsolutePath();
            //introduzco una imagen en el chat
            ImageIcon avatarIcon = new ImageIcon(filename);
            //redimensionar la imagen si es muy grande
            ImageIcon avatarIconAux = new ImageIcon(filename);
            if (avatarIcon.getIconWidth() > jLabel10.getWidth()) {
                avatarIconAux = new ImageIcon(
                        avatarIcon.getImage().getScaledInstance(jLabel10.
                        getWidth(),
                        -1, Image.SCALE_DEFAULT));
                avatarIcon = avatarIconAux;
            }
            jLabel10.setIcon(avatarIcon);
            repaint();
        }
    }

    void BotonExplorador_mouseEntered(MouseEvent e) {
        botonExplorador.setForeground(Color.white);
    }

    void BotonExplorador_mouseExited(MouseEvent e) {
        botonExplorador.setForeground(Color.yellow);
    }

    //Método que actualiza la pantalla cuando se pulsa intro
    //en el area de escritura del chat
    public void actualizar() {
        StringBuffer pruebaBuffer = new StringBuffer(areaEscritura.getText());
        if (cont==0){
        areaCharla.append("<" + usuario.getNombreUsuario() + ">" + pruebaBuffer.toString() +
                          "\n");
        areaEscritura.setText("");
        cont=1;
        }
        else{
          pruebaBuffer.deleteCharAt(0);
        areaCharla.append("<" + usuario.getNombreUsuario() + ">" + pruebaBuffer.toString() +
                          "\n");
        areaEscritura.setText("");
        }

    }

}






class Fondo_botonSalir_mouseAdapter extends java.awt.event.MouseAdapter {
  Interfaz adaptee;

  Fondo_botonSalir_mouseAdapter(Interfaz adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.botonSalir_mouseClicked(e);
  }
  public void mouseEntered(MouseEvent e) {
    adaptee.botonSalir_mouseEntered(e);
  }
  public void mouseExited(MouseEvent e) {
    adaptee.botonSalir_mouseExited(e);
  }
}



class fondo_BotonExplorador_mouseAdapter extends java.awt.event.MouseAdapter {
    Interfaz adaptee;
    fondo_BotonExplorador_mouseAdapter(Interfaz adaptee) {
        this.adaptee = adaptee;
    }

    public void mouseClicked(MouseEvent e) {
        adaptee.BotonExplorador_mouseClicked(e);
    }

    public void mouseEntered(MouseEvent e) {
        adaptee.BotonExplorador_mouseEntered(e);
    }

    public void mouseExited(MouseEvent e) {
        adaptee.BotonExplorador_mouseExited(e);
    }
}


//Clase para escribir en el areaCharla cuando pulsamos intro
class Oyente extends KeyAdapter {
    Interfaz miFondo;
    public Oyente(Interfaz f) {
        miFondo = f;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            miFondo.actualizar();
        }
    }
}


//Clase que permite poner una imagen como fondo de un JTextArea
class MiTextArea extends JTextArea {
    private ImageIcon im;
    public MiTextArea(String imagen) {
        im = new ImageIcon(imagen);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                0.3F);
        g2.setComposite(ac);
        g2.clipRect(0, 0, this.getWidth(), this.getHeight());
        g2.scale(2, 2);
        g2.drawImage(im.getImage(), 0, 0, im.getIconWidth(), im.getIconHeight(), null);
    }

    public void update(Graphics g) {
        super.update(g);
    }
}

//Clase que permite poner una imagen como fondo de un JLabel
class MiJLabel extends JLabel {
    private ImageIcon im;
    public MiJLabel(String imagen) {
        im = new ImageIcon(imagen);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                0.3F);
        g2.setComposite(ac);
        g2.clipRect(0, 0, this.getWidth(), this.getHeight());
        g2.scale(2, 2);
        g2.drawImage(im.getImage(), 0, 0, im.getIconWidth(), im.getIconHeight(), null);
    }

    public void update(Graphics g) {
        super.update(g);
    }
}