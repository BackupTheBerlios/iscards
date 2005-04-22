package comunicacion;

import javax.swing.*;
import interfaz.Interfaz;

/**
 *  Clase principal del la interfaz gráfica
 *
 *@author     Manuel Domingo Mora, Jesús Patiño y Francisco Javier Arellano
 *@version    2.0, revisada y mejorada por Enrique Martorano
 */

public class GUI extends JFrame {

	private Interfaz interfaz;


	/**
	 *  Constructor de la clase GUI
	 *
	 *@param  c      Le inserto el controlador
	 *@param  padre  Ventana padre
	 *@param  in     Description of Parameter
	 */
	public GUI(Controlador c, JFrame padre, Interfaz in) {
		try {

			interfaz = in;
			PanelNick pnick = new PanelNick();
			//Crea el cuadro de diálogo para introducir el nick
			if (JOptionPane.showConfirmDialog(null, pnick, "Conectar",
					JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE) ==
					JOptionPane.OK_OPTION) {
				//El usuario introduce el nick y pulsa "Aceptar"

				c.conectar();
				if (c.aniadirUser(pnick.getNick())) {

					VentanaPrincipal ventPrinc = new VentanaPrincipal(c, pnick.getNick(), padre, interfaz);
				}
				else {
					JOptionPane.showMessageDialog(null, "Su nick ya existe." + "\n"
							+ "Elija otro nick, por favor.",
							"El nick ya existe",
							JOptionPane.ERROR_MESSAGE);

					//c.desconectar("");comentado por kike, 14-5-05: 11:30h.
					//new GUI(c);//He modificado esto para que cuando pongas un nick repetido, en lugar de
					//cerrar el programa te vuelva a salir la ventana para que introduzcas otro nick

				}
			}
			else {
				//no se ha pulsado aceptar; se ha pulsado la "x"o cancelar
				padre.setEnabled(true);
				padre.show();
			}

		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al conectar usuario.",
					"ERROR",
					JOptionPane.ERROR_MESSAGE);
		}
	}


	/**
	 *  Gets the Interfaz attribute of the GUI object
	 *
	 *@return    The Interfaz value
	 */
	public Interfaz getInterfaz() {
		return interfaz;
	}

}

