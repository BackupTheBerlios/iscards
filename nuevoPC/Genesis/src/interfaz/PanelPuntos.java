package interfaz;
import javax.swing.*;
import java.awt.*;

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class PanelPuntos
		 extends JPanel {
	//private int ancho;
	//private int alto;
	JTextArea txaCementerio = new JTextArea();
	//iniX, iniY, altoNombre, anchoNombre);
	JTextArea txaMano = new JTextArea();
	JTextArea txaMesa = new JTextArea();
	JTextArea txaMazo = new JTextArea();
	JTextArea txaCementerioPuntos = new JTextArea();
	//iniX, iniY, altoNombre, anchoNombre);
	JTextArea txaManoPuntos = new JTextArea();
	JTextArea txaMesaPuntos = new JTextArea();
	JTextArea txaMazoPuntos = new JTextArea();

	private int puntosMano;
	private int puntosMazo;
	private int puntosMesa;
	private int puntosCementerio;
	private char tipo;
	private int ancho, alto1;


	/**
	 *  Constructor for the PanelPuntos object
	 *
	 *@param  pMano        Description of Parameter
	 *@param  pMazo        Description of Parameter
	 *@param  pMesa        Description of Parameter
	 *@param  pCementerio  Description of Parameter
	 *@param  t            Description of Parameter
	 *@param  wi           Description of Parameter
	 *@param  he           Description of Parameter
	 */
	public PanelPuntos(int pMano, int pMazo, int pMesa, int pCementerio, char t,
			int wi, int he) {
		super();
		setBackground(new Color(0));
		tipo = t;
		puntosMano = pMano;
		puntosMazo = pMazo;
		puntosMesa = pMesa;
		puntosCementerio = pCementerio;
		ancho = wi;
		alto1 = he;
		try {
			inicializacion();
		}
		catch (Exception e) {
			e.printStackTrace();

		}
		this.setVisible(true);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  nMano        Description of Parameter
	 *@param  nMazo        Description of Parameter
	 *@param  nMesa        Description of Parameter
	 *@param  nCementerio  Description of Parameter
	 */
	public void actualizaMarcador(int nMano, int nMazo, int nMesa, int nCementerio) {
		puntosMano = nMano;
		puntosMazo = nMazo;
		puntosMesa = nMesa;
		puntosCementerio = nCementerio;

	}


	/**
	 *  Description of the Method
	 */
	public void inicializacion() {
		int alto = alto1;
		int iniX = 0;
		int iniY = 0;
		int anchoBarra = (ancho / 4);
		int altoEstadistico = alto / 4;
		int anchoNombre = (ancho * 3) / 4;
		int altoNombre = altoEstadistico / 2;
		//para el nombre y el campo del numero tb
		//txaCementerio.setAlignmentX(iniX);
		//txaCementerio.setAlignmentY(iniY);

		//Cementerio
		txaCementerio.setFont(new Font("SansSerif", 3, 18));
		//cambiar fuente en proporcion
		txaCementerio.setBounds(new Rectangle(iniX, iniY, anchoNombre, altoNombre));
		txaCementerio.setText("Cementerio");
		txaCementerio.setBackground(null);

		//Mano
		txaMano.setFont(new Font("SansSerif", 3, 18));
		//cambiar fuente en proporcion
		txaMano.setBounds(new Rectangle(iniX, iniY + altoEstadistico, anchoNombre,
				altoNombre));
		txaMano.setText("Mano");
		txaMano.setBackground(null);

		//Mesa
		txaMesa.setFont(new Font("SansSerif", 3, 18));
		//cambiar fuente en proporcion
		txaMesa.setBounds(new Rectangle(iniX, iniY + altoEstadistico * 2,
				anchoNombre, altoNombre));
		txaMesa.setText("Mesa");
		txaMesa.setBackground(null);

		//Mazo
		txaMazo.setFont(new Font("SansSerif", 3, 18));
		//cambiar fuente en proporcion
		txaMazo.setBounds(new Rectangle(iniX, iniY + altoEstadistico * 3,
				anchoNombre, altoNombre));
		txaMazo.setText("Mazo");
		txaMazo.setBackground(null);

		//Puntos cementerio
		txaCementerioPuntos.setFont(new Font("SansSerif", 3, 18));
		//cambiar fuente en proporcion
		txaCementerioPuntos.setBounds(new Rectangle(iniX + anchoBarra,
				iniY + altoNombre, anchoBarra,
				altoNombre));
		//Puntos mano
		txaManoPuntos.setFont(new Font("SansSerif", 3, 18));
		//cambiar fuente en proporcion
		txaManoPuntos.setBounds(new Rectangle(iniX + anchoBarra,
				iniY + altoEstadistico + altoNombre,
				anchoBarra, altoNombre));
		//Puntos mesa
		txaMesaPuntos.setFont(new Font("SansSerif", 3, 18));
		//cambiar fuente en proporcion
		txaMesaPuntos.setBounds(new Rectangle(iniX + anchoBarra,
				iniY + (altoEstadistico * 2) +
				altoNombre, anchoBarra, altoNombre));
		//Puntos mazo
		txaMazoPuntos.setFont(new Font("SansSerif", 3, 18));
		//cambiar fuente en proporcion
		txaMazoPuntos.setBounds(new Rectangle(iniX + anchoBarra,
				iniY + altoEstadistico * 3 +
				altoNombre, anchoBarra, altoNombre));

		//mostrar puntos
		txaCementerioPuntos.setText(Integer.toString(puntosCementerio));
		txaManoPuntos.setText(Integer.toString(puntosMano));
		txaMazoPuntos.setText(Integer.toString(puntosMazo));
		txaMesaPuntos.setText(Integer.toString(puntosMesa));

		Color color = Color.red;

		switch (tipo) {
			//angeles
			case 'A':
				color = new Color(160, 160, 160);
				break;
			//demonios
			case 'D':
				color = new Color(190, 0, 40);
				break;
			//inmortales
			case 'I':
				color = new Color(144, 0, 164);
				//color
				break;
			//humanos
			case 'H':
				color = new Color(0, 150, 90);
				break;
		}

		txaCementerioPuntos.setEditable(false);
		txaManoPuntos.setEditable(false);
		txaMazoPuntos.setEditable(false);
		txaMesaPuntos.setEditable(false);
		txaCementerio.setEditable(false);
		txaMano.setEditable(false);
		txaMesa.setEditable(false);
		txaMazo.setEditable(false);
		txaCementerioPuntos.setEditable(false);
		txaManoPuntos.setEditable(false);
		txaMesaPuntos.setEditable(false);
		txaMazoPuntos.setEditable(false);

		txaCementerio.setEnabled(false);
		txaMano.setEnabled(false);
		txaMesa.setEnabled(false);
		txaMazo.setEnabled(false);
		txaCementerioPuntos.setEnabled(false)	;
		txaManoPuntos.setEnabled(false);
		txaMesaPuntos.setEnabled(false);
		txaMazoPuntos.setEnabled(false);


		txaCementerioPuntos.setForeground(color);
		txaManoPuntos.setForeground(color);
		txaMazoPuntos.setForeground(color);
		txaMesaPuntos.setForeground(color);
		txaCementerio.setForeground(color);
		txaMano.setForeground(color);
		txaMesa.setForeground(color);
		txaMazo.setForeground(color);
		txaCementerioPuntos.setForeground(color);
		txaManoPuntos.setForeground(color);
		txaMesaPuntos.setForeground(color);
		txaMazoPuntos.setForeground(color);

		txaCementerioPuntos.setOpaque(false);
		txaManoPuntos.setOpaque(false);
		txaMazoPuntos.setOpaque(false);
		txaMesaPuntos.setOpaque(false);
		txaCementerio.setOpaque(false);
		txaMano.setOpaque(false);
		txaMesa.setOpaque(false);
		txaMazo.setOpaque(false);
		txaCementerioPuntos.setOpaque(false);
		txaManoPuntos.setOpaque(false);
		txaMesaPuntos.setOpaque(false);
		txaMazoPuntos.setOpaque(false);

		this.setLayout(null);
		this.setBackground(null);
		this.setOpaque(false);
		this.add(txaCementerio, null);
		this.add(txaMano);
		this.add(txaMesa);
		this.add(txaMazo);
		this.add(txaCementerioPuntos);
		this.add(txaManoPuntos);
		this.add(txaMesaPuntos);
		this.add(txaMazoPuntos);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  g  Description of Parameter
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//llamamos al paintComponent del JPanel
		int iniX = 0;
		int iniY = 0;
		int alto = alto1;
		int anchoBarra = (ancho / 4);
		int altoEstadistico = alto / 4;
		int anchoNombre = (ancho * 3) / 4;
		int altoNombre = altoEstadistico / 2;
		//para el nombre y el campo del numero tb
		//txaCementerio.setAlignmentX(iniX);
		//txaCementerio.setAlignmentY(iniY);

		//Barra de puntos
		int puntos = puntosCementerio + puntosMesa + puntosMazo + puntosMano;
		int x = iniX + anchoNombre;
		int y = iniY;
		int uni = 1;
		int aux = 1;

		int casilla = alto / puntos;

		if (alto < puntos * 3) {
			casilla = 3;
		}

		alto /= casilla;

		for (int i = 0; i < ((alto) * puntosCementerio) / puntos; i++) {
			switch (tipo) {
				//angeles
				case 'A':
					g.setColor(new Color(50, 50, 50));
					//color
					break;
				//demonios
				case 'D':
					g.setColor(new Color(100, 0, 30));
					//color
					break;
				//inmortales
				case 'I':
					g.setColor(new Color(30, 0, 100));
					//color
					break;
				//humanos
				case 'H':
					g.setColor(new Color(0, 50, 0));
					//color
					break;
			}
			g.fillRect(x, y, anchoBarra, casilla - 1);
			y = y + casilla;
		}

		//dependiendo del tipo en el que estemos usamos unos colores u otros
		for (int i = 0; i < ((alto) * puntosMano) / puntos; i++) {
			switch (tipo) {
				//angeles
				case 'A':
					g.setColor(new Color(100, 100, 100));
					//color
					break;
				//demonios
				case 'D':
					g.setColor(new Color(130, 0, 60));
					//color
					break;
				//inmortales
				case 'I':
					g.setColor(new Color(85, 0, 144));
					//color
					break;
				//humanos
				case 'H':
					g.setColor(new Color(0, 90, 0));
					//color
					break;
			}
			g.fillRect(x, y, anchoBarra, casilla - 1);
			y = y + casilla;
		}

		for (int i = 0; i < ((alto) * puntosMesa) / puntos; i++) {
			switch (tipo) {
				//angeles
				case 'A':
					g.setColor(new Color(160, 160, 160));
					//color
					break;
				//demonios
				case 'D':
					g.setColor(new Color(190, 0, 40));
					//color
					break;
				//inmortales
				case 'I':
					g.setColor(new Color(144, 0, 164));
					//color
					break;
				//humanos
				case 'H':
					g.setColor(new Color(0, 150, 90));
					//color
					break;
			}
			g.fillRect(x, y, anchoBarra, casilla - 1);
			y = y + casilla;
		}

		for (int i = 0; i < ((alto) * puntosMazo) / puntos; i++) {
			switch (tipo) {
				//angeles
				case 'A':
					g.setColor(Color.white);
					//color
					break;
				//demonios
				case 'D':
					g.setColor(new Color(255, 0, 40));
					//color
					break;
				//inmortales
				case 'I':
					g.setColor(new Color(180, 130, 240));
					//color
					break;
				//humanos
				case 'H':
					g.setColor(new Color(0, 240, 150));
					//color
					break;
			}
			g.fillRect(x, y, anchoBarra, casilla - 1);
			y = y + casilla;
		}
	}

}


