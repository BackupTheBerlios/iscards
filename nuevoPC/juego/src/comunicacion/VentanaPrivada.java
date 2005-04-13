package comunicacion;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

/**
 * Clase que construye y configura la ventana privada
 * @author Manuel Domingo Mora, Jesús Patiño y Francisco Javier Arellano
 * @version 1.0
 */

public class VentanaPrivada
    extends JFrame {
  /**
   * Paneles que componen la ventana
   */
  private JPanel panelContenedor, panel1, panel2;

  /**
   * Boton enviar
   */
  private JButton benviar;

  /**
   * Campo de texto desde el que envio los mensajes
   */
  private JTextField enviar;

  /**
   * Area de texto en el que va apareciendo la conversación que llevo
   */
  private JTextArea converprivadas;

  /**
   * Nombre de usuario
   */
  private String nombreUsuario;

  /**
   * Nombre del usuario con el que se tiene el privado
   */
  private String nomOtroUsuario;

  /**
   * Controlador
   */
  private Controlador controler;

  /**
   * Constructor de la ventana privada
   * @param nomusuario Nombre de usuario al que pertenece
   * @param nomprivado Nombre del usuario con el que quiero tener la conversación
   * @param controlador Controlador
   */
  public VentanaPrivada(String nomusuario, String nomprivado,
                        Controlador controlador) {
    super("- Usuario: " + nomusuario + " - Conectado con : " + nomprivado);
    nombreUsuario = nomusuario;
    nomOtroUsuario = nomprivado;
    construirGUI();
    setSize(350, 300);
    setLocation(394,0);
    setVisible(true);
    controler = controlador;
  }

  /**
   * Función que configura la ventana de privados
   */
  public void construirGUI() {
    configurarComponentes();
  }

  /**
   * Función que configura los componentes de la ventana
   */
  public void configurarComponentes() {
    //dimensiones
    Dimension dimen = new Dimension(200, 15);
    benviar = new JButton("Enviar");
    benviar.addActionListener(new OyenteBoton());
    enviar = new JTextField();
    enviar.setMaximumSize(dimen);
    enviar.addKeyListener(new OyenteTeclado());
    converprivadas = new JTextArea();
    converprivadas.setColumns(100);
    converprivadas.setRows(10);
    converprivadas.setEditable(false);

    panelContenedor = (JPanel)this.getContentPane();
    panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));
    panel1 = new JPanel();
    panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
    dimen.setSize(200, 30);
    panel1.setSize(dimen);
    panel2 = new JPanel();
    panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
    Border borde;
    borde = BorderFactory.createEtchedBorder();
    panel2.setBorder(borde);

    panel1.add(new JScrollPane(converprivadas));
    panelContenedor.add(panel1);
    panel2.add(enviar);
    panel2.add(benviar);
    panelContenedor.add(panel2);
    this.addWindowListener(new OyenteVentana());
  }

  /**
   * Devuelve el nombre del usuario de esta ventana
   * @return String con el nombre del usuario
   */
  public synchronized String getNameUser() {
    return nombreUsuario;
  }

  /**
   * Devuelve el nombre del usuario con el que se conversa en el privado
   * @return String con el nombre del otro usuario
   */
  public synchronized String getNameOtherUser() {
    return nomOtroUsuario;
  }

  /**
   * Añade mensaje a conversaciones
   * @param mensaje String a mostrar por pantalla
   */
  public synchronized void addMensaje(String mensaje) {
    converprivadas.append(mensaje + "\n");
  }

  /**
   * Clase interna para capturar evento del los botones
   */
  class OyenteBoton
      implements ActionListener {
    /**
     * Captura u oye el evento emitido por el boton enviar
     * @param e Evento a capturar
     */
    public void actionPerformed(ActionEvent e) {
      if (! (enviar.getText()).equals("")) {
        controler.enviarPrivadoAServidor(nombreUsuario, nomOtroUsuario,
                                         enviar.getText());
        enviar.setText("");
       
       // enviar.setText(controler.getEvento());
      }
    }
  }

  /**
   * Clase interna para capturar eventos del teclado
   */
  class OyenteTeclado
      extends KeyAdapter {
    /**
     * Captura u oye el evento emitido al pulsar ENTER para enviar el mensaje
     * @param e Evento a capturar
     */
    public void keyPressed(KeyEvent e) {
      int x = e.getKeyCode();
      if ( (x == KeyEvent.VK_ENTER) && (! (enviar.getText()).equals(""))) {
        controler.enviarPrivadoAServidor(nombreUsuario, nomOtroUsuario,
                                         enviar.getText());
        enviar.setText("");
        
        //controler.enviarEvento(enviar.getText());
        //enviar.setText("");
      }
    }
  }

  /**
   * Clase interna para capturar eventos de los botones del borde de la ventana
   */
  class OyenteVentana
      extends WindowAdapter {
    /**
     * Captura u oye el evento emitido al cerrar la ventana
     * @param e Evento a capturar
     */
    public void windowClosing(WindowEvent e) {
      //controler.desactivarPrivado(nombreUsuario, nomOtroUsuario);
      //Desconectamos el usuario del servidor
      controler.borrarUser(nombreUsuario);
      controler.desconectar(nombreUsuario);

    }
  }

}
