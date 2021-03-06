package comunicacion;

import configuracion.*;
import interfaz.*;
import motorJuego.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;

/**
 *  Clase que construye y configura la ventana de usuarios conectados
 *
 *@author     Manuel Domingo Mora, Jes�s Pati�o y Francisco Javier Arellano
 *@version    2.0, revisada y mejorada por Enrique Martorano
 */

public class VentanaPrincipal extends Container {


     protected int alto=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
     protected int ancho=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
     JLabel labelFondo = new JLabel();
     JButton bConectar = new JButton("");
     JScrollPane scrollP = new JScrollPane();




	/**
	 *  Distintos paneles que utilizo en la ventana
	 */
	//private JPanel panelContenedor, panel1, panel2, panel3;

	/**
	 *  Lista de usuarios que aparece en la ventana
	 */
	protected JList usuarios= new JList();

	/**
	 *  Etiqueta de texto con el texto USUARIOS
	 */
	//private JLabel lusuarios;

	/**
	 *  Etiqueta de texto con el nick del usuario
	 */
	private JLabel lnick= new JLabel();


	/**
	 */
	private Controlador controlador;

	/**
	 *  Nombre del usuario
	 */
	private String nick;



	/**
	 *  Ventana padre
	 */
	private JFrame ventPadre;

	/**
	 *  Variable para poder cerrar la ventana cuando pulse sobre el boton
	 *  conectar y no me desconecte el usuario
	 */
	private boolean cerrarVentAuto;

        private String contrase�a;

	private Interfaz interfaz;

	private JButton bSalir = new JButton();


	/**
	 *  Constructor de la GUI
	 *
	 *@param  c      Le inserto el controlador del programa
	 *@param  n      Nombre del usuario del programa
	 *@param  padre  el padre desde el que se llama a esta clase
	 *@param  in     la interfaz padre desde la que se llama a esta clase
	 */
	public VentanaPrincipal(Controlador c, String n,String contra, JFrame padre, Interfaz in) {
		//this.setName("Genesis On Line");
                this.setSize(ancho, alto);

                contrase�a= contra;
		controlador = c;
		nick = n;
		interfaz = in;
                in.inhabilitaPanel();/***********************a�adido***/
                ventPadre = padre;

                cerrarVentAuto = false;



                this.controlador.setmiNickdeControlador(n);
                this.controlador.setmiContrase�adeControlador(contrase�a);

                ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
                Image image = cursor.getImage();
                Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(0,0), "img");
                this.setCursor(puntero);

		construirVentPrincipal();

		controlador.ejecutarComunicacion(this);
        ventPadre = padre;
		cerrarVentAuto = false;


	}


	/**
	 *  Le dice a la ventana que se va ha cerrar automaticamente, para que el
	 *  usuario no se desconecte del servidor
	 *
	 *@param  cerrarVent  true si se va ha cerrar automaticamente
	 */
	public synchronized void setCerrarVentanaAuto(boolean cerrarVent) {
		cerrarVentAuto = cerrarVent;
	}


	/**
	 *  Gets the VentanaPadre attribute of the VentanaPrincipal object
	 *
	 *@return    The VentanaPadre value
	 */
	public synchronized JFrame getVentanaPadre() {
		return ventPadre;
	}

	public Interfaz getInterfaz(){
		return interfaz;
	}

	/**
	 *  Funcion para obtener el nombre de usuario con el que esta conectado
	 *
	 *@return    String con el nombre del usuario
	 */
	public synchronized String getNick() {
		return nick;
	}


	/**
	 *  Funci�n que construye la ventana
	 */
	public void construirVentPrincipal() {
		configurarComponentes();
	}

		void bSalir_actionPerformed(ActionEvent e){
			controlador.desconectar(nick);
			 interfaz.getPartida().finalizaPartida();
		}

		void bSalir_mouseEntered(MouseEvent e) {
		  ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
		 Image image = cursor.getImage();
		 Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
		 this.setCursor(puntero);

		}


		  void bSalir_mouseExited(MouseEvent e) {
		    ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
		    Image image = cursor.getImage();
		    Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image , new Point(8,8), "img");
		    this.setCursor(puntero);

		}
	/**
	 *  Funci�n que configura los distintos componentes de la ventana
	 */
		public void configurarComponentes() {

      			   this.setLayout(null);
       			   this.setSize(ancho, alto);
			   this.setLocation(0, 0);

          			   labelFondo.setBounds(ancho/4,alto/50,ancho,alto);

    			   bConectar.setBounds((int)((ancho/4)+50), (int) (3.25 * (alto / 4)), 150, 25);
       			   bConectar.setBorder(null);

        			  bSalir.setBounds((int)(ancho/2-50), (int) (3.25 * (alto / 4)), 150, 25);
     			     bSalir.setBorder(null);

        			  //imagenes de componentes
         			 labelFondo.setIcon(new ImageIcon("../imagenes/genesisOnLine.jpg"));

     			     bConectar.setIcon(new ImageIcon("../imagenes/botonConectar.jpg"));
     			     bSalir.setIcon(new ImageIcon("../imagenes/salir2.jpg"));


        			  bConectar.addActionListener(new OyenteBotones());
       			   bSalir.addActionListener(new VentanaPrincipal_bSalir_actionAdapter(this));

   			       usuarios = new JList(controlador.getNicks(nick));

         				 scrollP.setBounds((int)(1.5*(ancho/5)),alto/3,300,340);
         				 scrollP.getViewport().add(usuarios, null);

       			   lnick.setBounds(500,150,100,30);
          			lnick.setFont(new java.awt.Font("Serif", 3, 20));
       			   lnick.setText(nick);


		         // bConectar.addActionListener(new OyenteBotones());

       			   this.add(lnick);
       			   this.add(scrollP,null);
        			  this.add(bConectar, null);
      			    this.add(bSalir,null);
      			    this.add(labelFondo, null);

         			// this.addWindowListener(new OyenteVentana());
	}



	/**
	 *  Actualiza la lista de usuarios
	 */
	public synchronized void ActualizarListaUsuarios() {
		usuarios.setModel(controlador.getNicks(nick));
	}












        void bConectar_mouseEntered(MouseEvent e) {



        }

        void bConectar_mouseExited(MouseEvent e) {



       }









      class VentanaPrincipal_bConectar_mouseAdapter extends java.awt.event.MouseAdapter {


              VentanaPrincipal adaptee;


              /**
               *  Constructor for the PanelGenerico_botonAceptar_mouseAdapter object
               *
               *@param  adaptee  Description of Parameter
               */
              VentanaPrincipal_bConectar_mouseAdapter(VentanaPrincipal adaptee) {
                      this.adaptee = adaptee;
              }


              /**
               *  Description of the Method
               *
               *@param  e  Description of Parameter
               */
              public void mouseEntered(MouseEvent e) {
                      adaptee.bConectar_mouseEntered(e);
              }


              /**
               *  Description of the Method
               *
               *@param  e  Description of Parameter
               */
              public void mouseExited(MouseEvent e) {
                      adaptee.bConectar_mouseExited(e);
              }

      }

      class OyenteBotones implements ActionListener {


        public void actionPerformed (ActionEvent evento){
        //GestorUsuarios gestorUsers=controlador.getGestorUsuarios();
                //Vector usuariosEnJuego= gestorUsers.getUsersJugando();
                //nombre del evento seleccionado
                String nomeven = (String) evento.getActionCommand();
                String usuarioSeleccionado = (String) usuarios.getSelectedValue();

                if (nomeven.equals("") && (usuarioSeleccionado != null)) {
                        int longCadena = usuarioSeleccionado.length();
                        String aux = usuarioSeleccionado.substring(longCadena - 5, longCadena);
                        if (aux.equals("LIBRE")) {
                                controlador.activarPrivado(nick, usuarioSeleccionado.substring(0, longCadena - 8));
                                interfaz.setmiNickdeInterfaz(nick);
                                interfaz.setnickDelOponenteInterfaz(usuarioSeleccionado.substring(0, longCadena - 8));
	                            interfaz.habilitaPanel();
	                            interfaz.getContentPane().remove(0);
	                            int caraOCruz=new Double(Math.random()*2).intValue();
	                            boolean empiezo;
	                            if (caraOCruz>=1)
	                            	empiezo=true;
	                            else
	                            	empiezo=false;
	                            if (empiezo){
	                            	((CPartidaRed)interfaz.getPartida()).setTurnoJugador("jugador1");
	                            	((CPartidaRed)interfaz.getPartida()).enviaInicioPartida(false);
	                            	interfaz.getPartida().notifica();
	                            }
	                            else{
	                            	((CPartidaRed)interfaz.getPartida()).setTurnoJugador("jugador2");
	                            	((CPartidaRed)interfaz.getPartida()).enviaInicioPartida(true);
	                            	interfaz.getPartida().notifica();
	                            }


                               // interfaz.habilitaPanel();

                              }

                            }
                            //***********Esto lo he a�adido aqui para que al hacerpruebas pudiera salir
                            //***********de la ventana ppal.
        }


      }
	  class VentanaPrincipal_bSalir_actionAdapter implements java.awt.event.ActionListener {
   		   private VentanaPrincipal adaptee;

     		 VentanaPrincipal_bSalir_actionAdapter(VentanaPrincipal adaptee) {
    		    this.adaptee = adaptee;
   		   }
   		   public void actionPerformed(ActionEvent e) {
       			 adaptee.bSalir_actionPerformed(e);
    		  }
  	    }


	class VentanaPrincipal_bSalir_mouseAdapter extends java.awt.event.MouseAdapter {
     		   private VentanaPrincipal adaptee;

      		  VentanaPrincipal_bSalir_mouseAdapter(VentanaPrincipal adaptee) {
        			  this.adaptee = adaptee;
        		}
     		   public void mouseEntered(MouseEvent e) {
    		      adaptee.bSalir_mouseEntered(e);
   		     }
   		     public void mouseExited(MouseEvent e) {
    		      adaptee.bSalir_mouseExited(e);
     		   }
      		}



	}