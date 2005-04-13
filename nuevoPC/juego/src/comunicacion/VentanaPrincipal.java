package comunicacion;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import configuracion.*;
import java.util.Vector;

/**
 * Clase que construye y configura la ventana de usuarios conectados
 * @author Manuel Domingo Mora, Jesús Patiño y Francisco Javier Arellano
 * @version 2.0
 */


 public class VentanaPrincipal extends JFrame {

  /**
   * Distintos paneles que utilizo en la ventana
   */
  private JPanel panelContenedor, panel1, panel2, panel3;

  /**
   * Lista de usuarios que aparece en la ventana
   */
  private JList usuarios;

  /**
   * Etiqueta de texto con el texto USUARIOS
   */
  private JLabel lusuarios;

  /**
   * Etiqueta de texto con el nick del usuario
   */
  private JLabel lnick;

  /**
   * Etiqueta de texto con el nick del usuario
   */
  private JLabel lnick2;

  /**
   * Botones
   */
  private JButton bconectar;

  /**
   *
   */
  private Controlador controlador;

  /**
   * Nombre del usuario
   */
  private String nick;

  /**
   * Ventana padre
   */
  private JFrame ventPadre;

  /**
   * Variable para poder cerrar la ventana cuando pulse sobre el boton
   * conectar y no me desconecte el usuario
   */
  private boolean cerrarVentAuto;

  /**
   * Constructor de la GUI
   * @param c Le inserto el controlador del programa
   * @param n Nombre del usuario del programa
   */
  public VentanaPrincipal(Controlador c, String n, JFrame padre) {
    super("Genesis On Line");
    controlador = c;
    nick = n;
    construirVentPrincipal();
    setSize(300, 220);
    setVisible(true);
    controlador.ejecutarComunicacion(this);
    ventPadre = padre;
    cerrarVentAuto = false;
  }

  /**
   * Función que construye la ventana
   */
  public void construirVentPrincipal() {
    configurarComponentes();
  }

  /**
   * Función que configura los distintos componentes de la ventana
   */
  public void configurarComponentes() {
    //dimensiones
    Dimension dimen = new Dimension(150, 15);
    Font fuente1=new Font("fuente1",1,14);
    bconectar = new JButton("CONECTAR");
    bconectar.addActionListener(new OyenteBotones());
    lnick = new JLabel("Usuario "+nick);
    lnick.setFont(fuente1);
    lnick2 = new JLabel ("Bienvenido a Genesis online");
    lnick2.setFont(fuente1);
    lusuarios = new JLabel("         USUARIOS ONLINE:");
    lusuarios.setFont(fuente1);

    //muestra los usuarios en una lista
    usuarios = new JList(controlador.getNicks(nick));
    panelContenedor = (JPanel)this.getContentPane();
    panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));
    panel1 = new JPanel();
    panel2 = new JPanel();

    panelContenedor.add(lnick);
    panelContenedor.add(panel2);
    panelContenedor.add(lnick2);
    panelContenedor.add(lusuarios);
    panelContenedor.add(new JScrollPane(usuarios));
    panel1.add(bconectar);
    panelContenedor.add(panel1);
    this.addWindowListener(new OyenteVentana());
  }

  public synchronized JFrame getVentanaPadre()
  {
    return ventPadre;
  }

  /**
   * Funcion para obtener el nombre de usuario con el que esta conectado
   * @return String con el nombre del usuario
   */
  public synchronized String getNick() {
    return nick;
  }

  /**
   * Actualiza la lista de usuarios
   */
  public synchronized void ActualizarListaUsuarios() {
    usuarios.setModel(controlador.getNicks(nick));
  }

  /**
   * Le dice a la ventana que se va ha cerrar automaticamente, para que el usuario no
   * se desconecte del servidor
   * @param cerrarVent true si se va ha cerrar automaticamente
   */
  public synchronized void setCerrarVentanaAuto(boolean cerrarVent)
  {
    cerrarVentAuto=cerrarVent;
  }

  /**
   * Clase interna para capturar los eventos de los botones
   */
  class OyenteBotones
      implements ActionListener {

    /**
     * Captura u oye el evento emitido al pulsar un botón
     * @param evento Evento a capturar
     */
    public void actionPerformed(ActionEvent evento) {

      //GestorUsuarios gestorUsers=controlador.getGestorUsuarios();
      //Vector usuariosEnJuego= gestorUsers.getUsersJugando();
      //nombre del evento seleccionado
      String nomeven = (String) evento.getActionCommand();
      String usuarioSeleccionado = (String) usuarios.getSelectedValue();
      
      if (nomeven.equals("CONECTAR") /*&&
          (!nick.equals( usuarioSeleccionado))*/
          && (usuarioSeleccionado!=null))
          {
    		int longCadena = usuarioSeleccionado.length();
    		String aux = usuarioSeleccionado.substring(longCadena-5,longCadena);
          if (aux.equals("LIBRE")) {
        	controlador.activarPrivado(nick,usuarioSeleccionado.substring(0,longCadena-8) );
       // configuracionImp config=new configuracionImp(ventPadre);
       // config.show();
       		}
      	  }
    }
  }

  /**
   * Clase interna para capturar los eventos de la ventana
   */
  class OyenteVentana
      extends WindowAdapter {
    /**
     * Captura u oye el evento emitido al cerrar la ventana y borra el usuario de la lista
     * @param e Evento a capturar
     */
    public void windowClosing(WindowEvent e)
    {
      if (!cerrarVentAuto) //Si se cierra automaticamente no desconectar el usuario
      {
        controlador.borrarUser(nick);
        controlador.desconectar(nick);
      }
    }
  }
}
