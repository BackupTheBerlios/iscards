package comunicacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import configuracion.*;
import java.util.Vector;
import interfaz.*;

/**
 *  Clase que construye y configura la ventana de usuarios conectados
 *
 *@author     Manuel Domingo Mora, Jesús Patiño y Francisco Javier Arellano
 *@version    2.0, revisada y mejorada por Enrique Martorano
 */

public class VentanaPrincipal extends Container {


     protected int alto=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
     protected int ancho=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
     JLabel labelFondo = new JLabel();
     JButton bConectar = new JButton("CONECTAR");
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

	private Interfaz interfaz;


	/**
	 *  Constructor de la GUI
	 *
	 *@param  c      Le inserto el controlador del programa
	 *@param  n      Nombre del usuario del programa
	 *@param  padre  el padre desde el que se llama a esta clase
	 *@param  in     la interfaz padre desde la que se llama a esta clase
	 */
	public VentanaPrincipal(Controlador c, String n, JFrame padre, Interfaz in) {
		//this.setName("Genesis On Line");
                this.setSize(ancho, alto);


		controlador = c;
		nick = n;
		interfaz = in;
                in.inhabilitaPanel();/***********************añadido***/
                ventPadre = padre;

                cerrarVentAuto = false;



                this.controlador.setmiNickdeControlador(n);

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


	/**
	 *  Funcion para obtener el nombre de usuario con el que esta conectado
	 *
	 *@return    String con el nombre del usuario
	 */
	public synchronized String getNick() {
		return nick;
	}


	/**
	 *  Función que construye la ventana
	 */
	public void construirVentPrincipal() {
		configurarComponentes();
	}


	/**
	 *  Función que configura los distintos componentes de la ventana
	 */
	public void configurarComponentes() {

          this.setLayout(null);
          this.setSize(ancho, alto);

          this.setLocation(0, 0);

          labelFondo.setBounds(ancho/4,alto/50,ancho,alto);
         // labelFondo.setBounds(new Rectangle((int) (ancho / 3.5), 2 * (alto / 6), ancho / 2, alto / 4));

          bConectar.setBounds(new Rectangle((int) (1.9 * (ancho / 5)), (int) (3.25 * (alto / 4)), (int) (ancho / 7.2), alto / 30));
          bConectar.setBorder(null);

          //imagenes de componentes
          labelFondo.setIcon(new ImageIcon("../imagenes/genesisOnLine.jpg"));

          bConectar.setIcon(new ImageIcon("../imagenes/botonConectar.jpg"));

       //   bConectar.addMouseListener(new VentanaPrincipal_bConectar_mouseAdapter(this));
          bConectar.addActionListener(new OyenteBotones());

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

                if (nomeven.equals("CONECTAR")
                /*
                 *  &&
                 *  (!nick.equals( usuarioSeleccionado))
                 */
                                && (usuarioSeleccionado != null)) {
                        int longCadena = usuarioSeleccionado.length();
                        String aux = usuarioSeleccionado.substring(longCadena - 5, longCadena);
                        if (aux.equals("LIBRE")) {
                                controlador.activarPrivado(nick, usuarioSeleccionado.substring(0, longCadena - 8));
                                interfaz.setmiNickdeInterfaz(nick);
                                interfaz.setnickDelOponenteInterfaz(usuarioSeleccionado.substring(0, longCadena - 8));

                               // interfaz.habilitaPanel();

                              }

                            }
                            //***********Esto lo he añadido aqui para que al hacerpruebas pudiera salir
                            //***********de la ventana ppal.
                            interfaz.habilitaPanel();
                            interfaz.getContentPane().remove(0);






        }



      }


	}