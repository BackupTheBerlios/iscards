package interfaz;

import java.awt.*;
import javax.swing.*;

import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.awt.event.*;
import java.util.Vector;
import motorJuego.*;
/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class PanelOpciones
		 extends JPanel {

	JButton botonCambiaFondo = new JButton();
	JButton botonCambiaMusica = new JButton();
	JButton botonAceptarExplorador = new JButton();

	private Dimension screenSize;
	private int anchoPantalla, altoPantalla;

	private ImageIcon im, im2, im3, bot;

	private PanelVolumen panelVolumen;
	private PanelPuntos panelPuntosAtaque;
	private PanelPuntos panelPuntosDefensa;
	private PanelOtrosDatos panelOtrosDatos;
	private PanelEstadisticasContrario estadisticasContrario;

	private int anchoPanelPuntos, anchoPanelVolumen;
	private int anchoConjunto, altoConjunto, posXConjunto, posYConjunto;
	private int anchoConjunto2, altoConjunto2, posXConjunto2, posYConjunto2;
	private int altoPanelPuntos, altoPanelVolumen;
	private int posXAtaque, posXDefensa, posXVolumen;
	private int posYAtaque, posYDefensa, posYVolumen;

	private JTextArea textAtaque;
	private JTextArea textDefensa;
	private JTextField nombreJugador;
	private JTextField nombreContrario;
	private JTextField nombreEstadisticas;
	private JTextField nombreConfiguracion;

	private char tipo;
	private JButton botonReglas = new JButton();
	private Interfaz interfaz;
	private Marcador marcador;


	//private PanelPuntos panelPuntos;
	/**
	 *  Constructor for the PanelOpciones object
	 *
	 *@param  tip        Description of Parameter
	 *@param  interfazz  Description of Parameter
	 *@param  marca      Description of Parameter
	 *@param  anch       Description of Parameter
	 *@param  alt        Description of Parameter
	 */
	public PanelOpciones(char tip, Interfaz interfazz, Marcador marca, int anch, int alt) {
		tipo = tip;
		interfaz = interfazz;
		marcador = marca;
		//imagenes
		im = new ImageIcon("../imagenes/fondos/fondo4.jpg");
		im2 = new ImageIcon("../imagenes/fondoGris6.jpg");
		im3 = new ImageIcon("../imagenes/fondoGris7.jpg");
		bot = new ImageIcon("../imagenes/boton.jpg");
		anchoPantalla = anch;
		altoPantalla = alt;

		//paneles ataque defensa
		anchoPanelPuntos = (anchoPantalla / 4) - anchoPantalla / 9;
		altoPanelPuntos = altoPantalla / 4;

		posXAtaque = anchoPantalla / 15;
		posYAtaque = (altoPantalla / 6) * 2;

		posXDefensa = posXAtaque + anchoPanelPuntos + (anchoPantalla / 10);
		posYDefensa = posYAtaque;
		//(el ancho de cada panel)*2 +(
		//el tamaño del marco de alrededor)*2(los de fuera) + (espacio entre los paneles))
		anchoConjunto = anchoPanelPuntos * 2 + (altoPantalla / 40) * 2 +
				(anchoPantalla / 10);

		posXConjunto = posXAtaque - (altoPantalla / 40);
		posYConjunto = posYAtaque - (altoPantalla / 40) * 5;

		altoConjunto = posYAtaque + altoPanelPuntos +
				(altoPantalla / 20) * 3 +
				altoPanelPuntos / 2 - posYConjunto + 30;

		posXVolumen = (anchoPantalla / 2) + (anchoPantalla / 20);
		posYVolumen = posYConjunto;
		altoPanelVolumen = altoPantalla / 10;
		//valores de todo el conjunto de configuracion
		posXConjunto2 = posXVolumen;
		posYConjunto2 = posYConjunto;
		anchoConjunto2 = anchoConjunto - (altoPantalla / 40);
		altoConjunto2 = altoPanelVolumen + altoPantalla / 10 + 30;

		//volumen

		anchoPanelVolumen = anchoConjunto2;

		//Panel de los puntos de ataque
		panelPuntosAtaque = new PanelPuntos(marcador.puntosAtaqueJugadorMano(),
				marcador.puntosAtaqueJugadorMazo(),
				marcador.puntosAtaqueJugadorMesa(),
				marcador.puntosAtaqueJugadorCementerio(),
				tipo, anchoPanelPuntos,
				altoPanelPuntos);
		//Panel de los puntos de defensa
		panelPuntosDefensa = new PanelPuntos(marcador.puntosDefensaJugadorMano(),
				marcador.puntosDefensaJugadorMazo(),
				marcador.puntosDefensaJugadorMesa(),
				marcador.puntosDefensaJugadorCementerio(),
				tipo, anchoPanelPuntos,
				altoPanelPuntos);
		//Panel del volumen
		panelVolumen = new PanelVolumen(tipo);
		//Panel del resto de estadisticas de jugador
		panelOtrosDatos = new PanelOtrosDatos(tipo);
		//panel con las estadisticas del contrario
		estadisticasContrario = new PanelEstadisticasContrario(tipo,
				(posXDefensa + anchoPanelPuntos - (altoPantalla / 40)), 30);
		//Text area ataque
		textAtaque = new JTextArea("ATAQUE");
		textAtaque.setEnabled(false);
		//textArea Defensa
		textDefensa = new JTextArea("DEFENSA");
		textDefensa.setEnabled(false);
		//textField jugador
		nombreJugador = new JTextField("JUGADOR");
		nombreJugador.setEnabled(false);
		//textField contrario
		nombreContrario = new JTextField("CONTRARIO");
		nombreContrario.setEnabled(false);
		//Text Fiel con el nombre "Estadisticas"
		nombreEstadisticas = new JTextField("ESTADISTICAS");
		nombreEstadisticas.setEditable(false);
		nombreEstadisticas.setEnabled(false);
		
		//Text Fiel con el nombre "configuracion"
		nombreConfiguracion = new JTextField("CONFIGURACIÓN");
		nombreConfiguracion.setEditable(false);
		nombreConfiguracion.setEnabled(false);
				
		try {
			inicializacion();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 *  Description of the Method
	 *
	 *@param  nuevaPartida  Description of Parameter
	 */
	public void actualizaOpciones(CPartida nuevaPartida) {
		marcador.actualizaMarcador(nuevaPartida);
		panelPuntosAtaque.actualizaMarcador(marcador.puntosAtaqueJugadorMano(),
				marcador.puntosAtaqueJugadorMazo(),
				marcador.puntosAtaqueJugadorMesa(),
				marcador.puntosAtaqueJugadorCementerio());
		panelPuntosDefensa.actualizaMarcador(marcador.puntosDefensaJugadorMano(),
				marcador.puntosDefensaJugadorMazo(),
				marcador.puntosDefensaJugadorMesa(),
				marcador.puntosDefensaJugadorCementerio());
		panelOtrosDatos.NumConjurosCementerio.setText(String.valueOf(
				marcador.cuentaTipoCartasCementerio().con));
		panelOtrosDatos.numConjurosMazo.setText(String.valueOf(
				marcador.cuentaTipoCartasMazo().con));
		panelOtrosDatos.NumHechizosCementerio.setText(String.valueOf(
				marcador.cuentaTipoCartasCementerio().he));
		panelOtrosDatos.numHechizosMazo.setText(String.valueOf(
				marcador.cuentaTipoCartasMazo().he));
		panelOtrosDatos.NumCriaturasCementerio.setText(String.valueOf(
				marcador.cuentaTipoCartasCementerio().cri));
		panelOtrosDatos.numCriaturasMazo.setText(String.valueOf(
				marcador.cuentaTipoCartasMazo().cri));
		estadisticasContrario.totalAtaque.setText(String.valueOf(marcador.puntosAtaqueContrincanteMesa()));
		estadisticasContrario.totalDefensa.setText(String.valueOf(marcador.puntosDefensaContrincanteMesa()));
	}


	/**
	 *  Description of the Method
	 *
	 *@param  g  Description of Parameter
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//fondo
		g.drawImage(im.getImage(), 0, 0, anchoPantalla, altoPantalla, null);

		//panel volumen boton efectos sonido
		g.drawImage(im3.getImage(), posXVolumen, posYVolumen,
				(anchoPanelVolumen / 2) - 20, (altoPanelVolumen / 2) - 2, null);
		//panel volumen boton musica fondo
		g.drawImage(im3.getImage(), posXVolumen,
				posYVolumen + altoPanelVolumen / 2 + 2,
				(anchoPanelVolumen / 2) - 20, (altoPanelVolumen / 2) - 2, null);
		//panel Volumen subir y bajar volumen
		g.drawImage(im2.getImage(), posXVolumen + anchoPanelVolumen / 2 + 15,
				posYVolumen, (anchoPanelVolumen / 2) - 10, altoPanelVolumen, null);
		int margen = altoPantalla / 40;

		//arriba
		g.drawImage(im2.getImage(), posXAtaque - margen, posYAtaque - margen,
				anchoPanelPuntos + 2 * margen, margen, null);
		//abajo
		g.drawImage(im2.getImage(), posXAtaque - margen,
				posYAtaque + altoPanelPuntos,
				anchoPanelPuntos + 2 * margen, margen, null);
		//izq
		g.drawImage(im2.getImage(), posXAtaque - margen, posYAtaque - margen,
				margen, altoPanelPuntos + 2 * margen, null);
		//der
		g.drawImage(im2.getImage(), posXAtaque + anchoPanelPuntos,
				posYAtaque - margen,
				margen, altoPanelPuntos + 2 * margen, null);

		//arriba
		g.drawImage(im2.getImage(), posXDefensa - margen, posYDefensa - margen,
				anchoPanelPuntos + 2 * margen, margen, null);
		//abajo
		g.drawImage(im2.getImage(), posXDefensa - margen,
				posYDefensa + altoPanelPuntos,
				anchoPanelPuntos + 2 * margen, margen, null);
		//izq
		g.drawImage(im2.getImage(), posXDefensa - margen, posYDefensa - margen,
				margen, altoPanelPuntos + 2 * margen, null);
		//der
		g.drawImage(im2.getImage(), posXDefensa + anchoPanelPuntos,
				posYDefensa - margen,
				margen, altoPanelPuntos + 2 * margen, null);

		//Nombre Jugador
		g.drawImage(im2.getImage(), posXAtaque - margen, posYAtaque - margen * 5,
				anchoConjunto, 30, null);
		//Nombre Contrario
		g.drawImage(im2.getImage(), posXAtaque - margen,
				posYAtaque + altoPanelPuntos + (altoPantalla / 20) * 2 +
				altoPanelPuntos / 2,
				anchoConjunto, 30, null);

		//usar los bounds del componente de mas arriba y el de mas abajo
		//arriba
		int x;

		//usar los bounds del componente de mas arriba y el de mas abajo
		//arriba
		int y;

		//usar los bounds del componente de mas arriba y el de mas abajo
		//arriba
		int w;

		//usar los bounds del componente de mas arriba y el de mas abajo
		//arriba
		int h;
		x = posXConjunto - margen * 2;
		y = posYConjunto - margen * 3;
		w = anchoConjunto + margen * 4;
		h = margen;
		g.drawImage(im2.getImage(), x, y, w, h, null);

		//abajo
		x = posXConjunto - margen * 2;
		y = posYConjunto + margen * 2 + altoConjunto;
		w = anchoConjunto + margen * 4;
		h = margen;
		g.drawImage(im2.getImage(), x, y, w, h, null);

		//izq
		x = posXConjunto - margen * 2;
		y = posYConjunto - margen * 3;
		w = margen;
		h = altoConjunto + margen * 6;
		g.drawImage(im2.getImage(), x, y, w, h, null);

		//der
		x = posXConjunto + margen + anchoConjunto;
		y = posYConjunto - margen * 3;
		w = margen;
		h = altoConjunto + margen * 6;
		g.drawImage(im2.getImage(), x, y, w, h, null);

		x = posXConjunto + anchoConjunto / 4;
		y = posYConjunto - margen * 6;
		w = anchoConjunto / 2;
		h = 40;
		g.drawImage(bot.getImage(), x, y, w, h, null);

		x = posXVolumen + anchoPanelVolumen / 2 + 20;
		y = posYVolumen + altoPanelVolumen + altoPantalla / 10;
		w = anchoPanelVolumen / 2 - 20;
		h = 30;
		g.drawImage(im3.getImage(), x, y, w, h, null);

		x = posXVolumen;
		y = posYVolumen + altoPanelVolumen + altoPantalla / 10;
		w = anchoPanelVolumen / 2 - 20;
		h = 30;
		g.drawImage(im3.getImage(), x, y, w, h, null);

		//usar los bounds del componente de mas arriba y el de mas abajo
		//arriba
		x = posXConjunto2 - margen * 2;
		y = posYConjunto2 - margen * 3;
		w = anchoConjunto2 + margen * 4;
		h = margen;
		g.drawImage(im2.getImage(), x, y, w, h, null);

		//abajo
		x = posXConjunto2 - margen * 2;
		y = posYConjunto2 + margen * 2 + altoConjunto2;
		w = anchoConjunto2 + margen * 4;
		h = margen;
		g.drawImage(im2.getImage(), x, y, w, h, null);

		//izq
		x = posXConjunto2 - margen * 2;
		y = posYConjunto2 - margen * 3;
		w = margen;
		h = altoConjunto2 + margen * 6;
		g.drawImage(im2.getImage(), x, y, w, h, null);

		//der
		x = posXConjunto2 + margen + anchoConjunto2;
		y = posYConjunto2 - margen * 3;
		w = margen;
		h = altoConjunto2 + margen * 6;
		g.drawImage(im2.getImage(), x, y, w, h, null);

		x = posXConjunto2 + anchoConjunto2 / 4;
		y = posYConjunto2 - margen * 6;
		w = anchoConjunto2 / 2;
		h = 40;
		g.drawImage(bot.getImage(), x, y, w, h, null);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  g  Description of Parameter
	 */
	public void update(Graphics g) {
		super.update(g);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
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
			if (avatarIcon.getIconWidth() > this.getWidth()) {
				avatarIconAux = new ImageIcon(
						avatarIcon.getImage().getScaledInstance(this.
						getWidth(),
						-1, Image.SCALE_DEFAULT));
				avatarIcon = avatarIconAux;
			}
			interfaz.labelTablero.setIcon(avatarIcon);

			repaint();
		}
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void BotonExplorador_mouseEntered(MouseEvent e) {
		botonCambiaFondo.setForeground(Color.white);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void BotonExplorador_mouseExited(MouseEvent e) {
		Color color = Color.red;
		switch (tipo) {
			//angeles
			case 'A':
				color = new Color(70, 70, 100);
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
		botonCambiaFondo.setForeground(color);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void BotonExplorador2_mouseClicked(MouseEvent e) {
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
		}
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void BotonExplorador2_mouseEntered(MouseEvent e) {
		botonCambiaMusica.setForeground(Color.white);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void BotonExplorador2_mouseExited(MouseEvent e) {
		Color color = Color.red;
		switch (tipo) {
			//angeles
			case 'A':
				color = new Color(70, 70, 100);
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

		botonCambiaMusica.setForeground(color);
	}


	/**
	 *  Description of the Method
	 *
	 *@exception  Exception  Description of Exception
	 */
	private void inicializacion() throws Exception {

		this.setLayout(null);
		int x = 0;
		int y = 0;
		int w = 0;
		int h = 0;
		int margen = altoPantalla / 40;
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

		//Paneles
		panelPuntosAtaque.setBounds(posXAtaque, posYAtaque, anchoPanelPuntos,
				altoPanelPuntos);
		panelPuntosDefensa.setBounds(posXDefensa, posYDefensa, anchoPanelPuntos,
				altoPanelPuntos);
		panelVolumen.setBounds(new Rectangle(posXVolumen, posYVolumen,
				anchoPanelVolumen, altoPanelVolumen));

		//Panel con las otras estadisticas
		panelOtrosDatos.setBounds(new Rectangle(posXAtaque - margen,
				posYAtaque + altoPanelPuntos +
				(altoPantalla / 20),
				anchoConjunto,
				altoPanelPuntos / 2));
		panelOtrosDatos.NumConjurosCementerio.setText(String.valueOf(
				marcador.cuentaTipoCartasCementerio().con));
		panelOtrosDatos.numConjurosMazo.setText(String.valueOf(
				marcador.cuentaTipoCartasMazo().con));
		panelOtrosDatos.NumHechizosCementerio.setText(String.valueOf(
				marcador.cuentaTipoCartasCementerio().he));
		panelOtrosDatos.numHechizosMazo.setText(String.valueOf(
				marcador.cuentaTipoCartasMazo().he));
		panelOtrosDatos.NumCriaturasCementerio.setText(String.valueOf(
				marcador.cuentaTipoCartasCementerio().cri));
		panelOtrosDatos.numCriaturasMazo.setText(String.valueOf(
				marcador.cuentaTipoCartasMazo().cri));

		//Text Area Ataque
		textAtaque.setBounds(posXAtaque - (altoPantalla / 60),
				posYAtaque - (altoPantalla / 30) * 2,
				textAtaque.getText().length() * 20,
				50);
		textAtaque.setFont(new Font("SansSerif", 3, 18));
		textAtaque.setOpaque(false);
		textAtaque.setEditable(false);
		textAtaque.setForeground(color);

		//Text Area Defensa
		textDefensa.setBounds(posXDefensa - (altoPantalla / 60),
				posYDefensa - (altoPantalla / 30) * 2,
				textDefensa.getText().length() * 20,
				50);
		textDefensa.setFont(new Font("SansSerif", 3, 18));
		textDefensa.setOpaque(false);
		textDefensa.setEditable(false);
		textDefensa.setForeground(color);

		//JTextField donde pongo el nombre del jugador
		nombreJugador.setBounds(posXAtaque - margen, posYAtaque - margen * 5,
				anchoConjunto, 30);
		nombreJugador.setFont(new Font("SansSerif", 3, 20));
		nombreJugador.setHorizontalAlignment(SwingConstants.CENTER);
		nombreJugador.setEditable(false);
		nombreJugador.setOpaque(false);
		nombreJugador.setForeground(color);
		if (tipo == 'A') {
			nombreJugador.setForeground(new Color(70, 70, 100));
		}

		//JTextField donde pongo el nombre del contrario
		nombreContrario.setBounds(posXAtaque - margen,
				posYAtaque + altoPanelPuntos +
				(altoPantalla / 20) * 2 + altoPanelPuntos / 2,
				anchoConjunto, 30);
		nombreContrario.setFont(new Font("SansSerif", 3, 20));
		nombreContrario.setHorizontalAlignment(SwingConstants.CENTER);
		nombreContrario.setEditable(false);
		nombreContrario.setOpaque(false);
		nombreContrario.setForeground(color);
		if (tipo == 'A') {
			nombreContrario.setForeground(new Color(70, 70, 100));
		}

		//estadisticas del contrario
		estadisticasContrario.setBounds(posXAtaque - margen,
				posYAtaque + altoPanelPuntos +
				(altoPantalla / 20) * 3 +
				altoPanelPuntos / 2,
				anchoConjunto, 30);

		estadisticasContrario.totalAtaque.setText(String.valueOf(marcador.puntosAtaqueContrincanteMesa()));
		estadisticasContrario.totalDefensa.setText(String.valueOf(marcador.puntosDefensaContrincanteMesa()));
		//el nombre de estadisticas
		x = posXConjunto + anchoConjunto / 4;
		y = posYConjunto - margen * 6;
		w = anchoConjunto / 2;
		h = 40;
		nombreEstadisticas.setBounds(x, y, w, h);
		nombreEstadisticas.setOpaque(false);
		nombreEstadisticas.setForeground(color);
		nombreEstadisticas.setFont(new Font("SansSerif", 3, 20));
		nombreEstadisticas.setHorizontalAlignment(SwingConstants.CENTER);
		nombreEstadisticas.setBorder(null);

		//el nombre de Configuracion
		x = posXConjunto2 + anchoConjunto2 / 4;
		y = posYConjunto2 - margen * 6;
		w = anchoConjunto2 / 2;
		h = 40;
		nombreConfiguracion.setBounds(x, y, w, h);
		nombreConfiguracion.setOpaque(false);
		nombreConfiguracion.setForeground(color);
		nombreConfiguracion.setFont(new Font("SansSerif", 3, 20));
		nombreConfiguracion.setHorizontalAlignment(SwingConstants.CENTER);
		nombreConfiguracion.setBorder(null);

		//boton para cambiar fondo
		botonCambiaFondo.setText("Cambiar Fondo");
		botonCambiaFondo.setOpaque(false);
		botonCambiaFondo.setFont(new Font("SansSerif", 3, 18));
		botonCambiaFondo.setForeground(color);
		if (tipo == 'A') {
			botonCambiaFondo.setForeground(new Color(70, 70, 100));
		}
		botonCambiaFondo.setHorizontalAlignment(SwingConstants.CENTER);
		x = posXVolumen + anchoPanelVolumen / 2 + 20;
		y = posYVolumen + altoPanelVolumen + altoPantalla / 10;
		w = anchoPanelVolumen / 2 - 20;
		h = 30;

		botonCambiaFondo.setBounds(x, y, w, h);
		botonCambiaFondo.setContentAreaFilled(false);
		botonCambiaFondo.setBorder(null);
		//oyente del boton explorador
		botonCambiaFondo.addMouseListener(new fondo_BotonExplorador_mouseAdapter(this));

//boton para cambiar Musica
		botonCambiaMusica.setText("Cambiar Musica");
		botonCambiaMusica.setOpaque(false);
		botonCambiaMusica.setFont(new Font("SansSerif", 3, 18));
		botonCambiaMusica.setForeground(color);
		if (tipo == 'A') {
			botonCambiaMusica.setForeground(new Color(70, 70, 100));
		}
		botonCambiaMusica.setHorizontalAlignment(SwingConstants.CENTER);
		x = posXVolumen;
		y = posYVolumen + altoPanelVolumen + altoPantalla / 10;
		w = anchoPanelVolumen / 2 - 20;
		h = 30;
		botonCambiaMusica.setBounds(x, y, w, h);
		botonCambiaMusica.setContentAreaFilled(false);
		botonCambiaMusica.setBorder(null);
		//oyente del boton explorador
		botonCambiaMusica.addMouseListener(new fondo_BotonExplorador2_mouseAdapter(this));

		this.add(panelPuntosAtaque, null);
		this.add(panelPuntosDefensa, null);
		this.add(panelVolumen, null);
		this.add(panelOtrosDatos, null);
		this.add(textAtaque, null);
		this.add(textDefensa, null);
		this.add(nombreJugador, null);
		this.add(nombreContrario, null);
		this.add(estadisticasContrario, null);
		this.add(nombreEstadisticas, null);
		this.add(nombreConfiguracion, null);
		this.add(botonCambiaFondo, null);
		this.add(botonCambiaMusica, null);
		this.repaint();
		panelPuntosAtaque.repaint();
		panelPuntosDefensa.repaint();
	}


	/**
	 *  Description of the Class
	 *
	 *@author    Chris Seguin
	 */
	class fondo_BotonExplorador_mouseAdapter
			 extends java.awt.event.MouseAdapter {
		PanelOpciones adaptee;


		/**
		 *  Constructor for the fondo_BotonExplorador_mouseAdapter object
		 *
		 *@param  adaptee  Description of Parameter
		 */
		fondo_BotonExplorador_mouseAdapter(PanelOpciones adaptee) {
			this.adaptee = adaptee;
		}


		/**
		 *  Description of the Method
		 *
		 *@param  e  Description of Parameter
		 */
		public void mouseClicked(MouseEvent e) {
			adaptee.BotonExplorador_mouseClicked(e);
		}


		/**
		 *  Description of the Method
		 *
		 *@param  e  Description of Parameter
		 */
		public void mouseEntered(MouseEvent e) {
			adaptee.BotonExplorador_mouseEntered(e);
		}


		/**
		 *  Description of the Method
		 *
		 *@param  e  Description of Parameter
		 */
		public void mouseExited(MouseEvent e) {
			adaptee.BotonExplorador_mouseExited(e);
		}
	}


	/**
	 *  Description of the Class
	 *
	 *@author    Chris Seguin
	 */
	class fondo_BotonExplorador2_mouseAdapter
			 extends java.awt.event.MouseAdapter {
		PanelOpciones adaptee;


		/**
		 *  Constructor for the fondo_BotonExplorador2_mouseAdapter object
		 *
		 *@param  adaptee  Description of Parameter
		 */
		fondo_BotonExplorador2_mouseAdapter(PanelOpciones adaptee) {
			this.adaptee = adaptee;
		}


		/**
		 *  Description of the Method
		 *
		 *@param  e  Description of Parameter
		 */
		public void mouseClicked(MouseEvent e) {
			adaptee.BotonExplorador2_mouseClicked(e);
		}


		/**
		 *  Description of the Method
		 *
		 *@param  e  Description of Parameter
		 */
		public void mouseEntered(MouseEvent e) {
			adaptee.BotonExplorador2_mouseEntered(e);
		}


		/**
		 *  Description of the Method
		 *
		 *@param  e  Description of Parameter
		 */
		public void mouseExited(MouseEvent e) {
			adaptee.BotonExplorador2_mouseExited(e);
		}
	}

	/*
	 *  public static void main(String[] args) {
	 *  JFrame frame = new JFrame("Opciones");
	 *  PanelOpciones panel = new PanelOpciones('D');
	 *  frame.getContentPane().add(panel, BorderLayout.CENTER);
	 *  Dimension dimensiones = Toolkit.getDefaultToolkit().getScreenSize();
	 *  double h = dimensiones.getHeight();
	 *  double w = dimensiones.getWidth() - 20;
	 *  frame.setSize( (int) w, (int) h);
	 *  frame.setVisible(true);
	 *  }
	 */
}
