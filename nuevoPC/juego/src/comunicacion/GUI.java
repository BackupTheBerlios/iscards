package comunicacion;

import javax.swing.*;
import configuracion.*;

public class GUI extends JFrame {

  /**
   * Constructor de la clase GUI
   * @param c Le inserto el controlador
   * @param padre Ventana padre
   */
  public GUI(Controlador c, JFrame padre) { 
 //   JFrame abuelo=padre.getPadre().show();
    PanelNick pnick = new PanelNick();//Crea el cuadro de diálogo para introducir el nick
    if (JOptionPane.showConfirmDialog(null, pnick, "Conectar",
                                      JOptionPane.OK_CANCEL_OPTION,
                                      JOptionPane.PLAIN_MESSAGE) ==
        JOptionPane.OK_OPTION) //El usuario introduce el nick y pulsa "Aceptar"
    {
      c.conectar();
      if (c.aniadirUser(pnick.getNick())) {

        VentanaPrincipal ventPrinc = new VentanaPrincipal(c, pnick.getNick(),padre);
      }
      else {
        JOptionPane.showMessageDialog(null, "Su nick ya existe." + "\n"
                                      + "Elija otro nick, por favor.",
                                      "El nick ya existe",
                                      JOptionPane.ERROR_MESSAGE);
        c.desconectar("");
        //new GUI(c);//He modificado esto para que cuando pongas un nick repetido, en lugar de
        //cerrar el programa te vuelva a salir la ventana para uqe introduzcas otro nick
      }
    }
  }


}


