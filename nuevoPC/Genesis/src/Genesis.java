import login.*;
import video.Video;
import javax.swing.UIManager;
import java.awt.*;

/**
 *  <p>
 *
 *  Título: GENESIS</p> <p>
 *
 *  Descripción: Clase principal de la aplicación del Juego de cartas de Rol
 *  </p> <p>
 *
 *  Copyright: Copyright (c) 2004</p> <p>
 *
 *  Empresa: </p>
 *
 *@author     Rui Miguel Alonso
 *@version    1.0
 */

public class Genesis extends LoginImp {
	boolean packFrame = false;


	/**
	 *  Constructora de la clase para crear la aplicación
	 */
	public Genesis() {
		//Validar marcos que tienen tamaños preestablecidos
		//Empaquetar marcos que cuentan con información de tamaño preferente útil. Ej. de su diseño.

		if (packFrame) {
			this.pack();
		}
		else {
			this.validate();
		}
		//Centrar la ventana
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		this.setVisible(true);
	}


	/**
	 *  Metodo Main
	 *
	 *@param  args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
                Video mivideo=new Video();

		new Genesis();
	}
}
