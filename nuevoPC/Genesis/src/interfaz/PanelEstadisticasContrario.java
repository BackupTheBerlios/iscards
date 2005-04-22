package interfaz;
import java.awt.*;
import javax.swing.*;
/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
public class PanelEstadisticasContrario extends JPanel {
	JTextField totalAtaque = new JTextField();
	JTextField nomDefensa = new JTextField();
	JTextField totalDefensa = new JTextField();
	JTextField nomAtaque = new JTextField();
	private int ancho, alto, unidad;
	private char tipo;


	/**
	 *  Constructor for the PanelEstadisticasContrario object
	 *
	 *@param  tip   Description of Parameter
	 *@param  anch  Description of Parameter
	 *@param  alt   Description of Parameter
	 */
	public PanelEstadisticasContrario(char tip, int anch, int alt) {
		ancho = anch;
		alto = alt;
		tipo = tip;
		unidad = ancho / 6;
		try {
			jbInit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 *  Description of the Method
	 *
	 *@exception  Exception  Description of Exception
	 */
	private void jbInit() throws Exception {
		this.setLayout(null);
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

		nomAtaque.setFont(new java.awt.Font("SansSerif", 3, 16));
		nomAtaque.setForeground(color);
		nomAtaque.setBorder(null);
		//nomAtaque.setDebugGraphicsOptions(0);
		nomAtaque.setOpaque(false);
		nomAtaque.setEditable(false);
		nomAtaque.setEnabled(false);
		nomAtaque.setText("ATAQUE");
		nomAtaque.setHorizontalAlignment(SwingConstants.CENTER);
		nomAtaque.setBounds(new Rectangle(0, 0, unidad * 2, alto));

		totalAtaque.setBounds(new Rectangle(unidad * 2, 0, unidad, alto));
		totalAtaque.setText("0");
		totalAtaque.setEditable(false);
		totalAtaque.setEnabled(false);
		totalAtaque.setOpaque(false);
		//otalAtaque.setDebugGraphicsOptions(0);
		totalAtaque.setForeground(color);
		totalAtaque.setBorder(null);
		totalAtaque.setFont(new java.awt.Font("SansSerif", 3, 16));

		nomDefensa.setBounds(new Rectangle(unidad * 3, 0, unidad * 2, alto));
		nomDefensa.setHorizontalAlignment(SwingConstants.CENTER);
		nomDefensa.setText("DEFENSA");
		nomDefensa.setEditable(false);
		nomDefensa.setEnabled(false);
		nomDefensa.setOpaque(false);
		//nomDefensa.setDebugGraphicsOptions(0);
		nomDefensa.setForeground(color);
		nomDefensa.setBorder(null);
		nomDefensa.setFont(new java.awt.Font("SansSerif", 3, 16));

		totalDefensa.setBounds(new Rectangle(unidad * 5, 0, unidad, alto));
		totalDefensa.setText("0");
		totalDefensa.setHorizontalAlignment(SwingConstants.LEADING);
		totalDefensa.setEditable(false);
		totalDefensa.setEnabled(false);
		totalDefensa.setOpaque(false);
		//totalDefensa.setDebugGraphicsOptions(0);
		totalDefensa.setForeground(color);
		totalDefensa.setBorder(null);
		totalDefensa.setFont(new java.awt.Font("SansSerif", 3, 16));
		this.setBorder(null);
		this.setOpaque(false);
		this.add(totalDefensa, null);
		this.add(nomDefensa, null);
		this.add(totalAtaque, null);
		this.add(nomAtaque, null);

	}

}
