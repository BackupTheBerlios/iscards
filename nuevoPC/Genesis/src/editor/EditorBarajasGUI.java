package editor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import padrePaneles.*;


/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
public abstract class EditorBarajasGUI extends PadrePaneles {
	/**
	 *  Description of the Field
	 */
	protected JLabel labelImagen = new JLabel();
	/**
	 *  Description of the Field
	 */
	//protected JComboBox comboNombreCarta = new JComboBox();
	/**
	 *  Description of the Field
	 */
	protected JList listaSeleccionadas = new JList();
	/**
	 *  Description of the Field
	 */
	protected JList listaDisponibles = new JList();
	/**
	 *  Description of the Field
	 */
	protected int alto = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	/**
	 *  Description of the Field
	 */
	protected int ancho = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	/**
	 *  Description of the Field
	 */
	protected JLabel textoNumeroCartas = new JLabel();
	/**
	 *  Description of the Field
	 */
	protected JLabel textoRaza = new JLabel();
	/**
	 *  Description of the Field
	 */
	protected JButton botGuardar = new JButton();
	/**
	 *  Description of the Field
	 */

        protected JButton botAniadir = new JButton();


	protected JLabel textoBarajaCargada = new JLabel();
	private JPanel panelFondo = new JPanel();
	private JScrollPane jScrollPane1 = new JScrollPane();
	private JScrollPane jScrollPane2 = new JScrollPane();
	private JLabel labelFondo = new JLabel();
	private JButton botCargar = new JButton();
	private JButton botGuardarComo = new JButton();
	private JButton botSalir = new JButton();
	private JButton botAyuda = new JButton();
	private JButton botNuevaBaraja = new JButton();



	/**
	 *  Constructor for the EditorBarajasGUI object
	 */
	public EditorBarajasGUI() {
		//dibujamos el cursor
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "img");
		this.setCursor(puntero);

		try {
			jbInit();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 *  Description of the Method
	 */
	public void habilitaPanel() {

		this.panelFondo.setEnabled(true);
		labelImagen.setEnabled(true);
		//comboNombreCarta.setEnabled(true);
		this.jScrollPane1.setEnabled(true);
		this.jScrollPane1.getVerticalScrollBar().setEnabled(true);
		jScrollPane1.getHorizontalScrollBar().setEnabled(true);
		this.jScrollPane2.setEnabled(true);
		this.jScrollPane2.getVerticalScrollBar().setEnabled(true);
		jScrollPane2.getHorizontalScrollBar().setEnabled(true);
		listaSeleccionadas.setEnabled(true);
		listaDisponibles.setEnabled(true);
		this.labelFondo.setEnabled(true);
		textoNumeroCartas.setEnabled(true);
		textoRaza.setEnabled(true);
		botCargar.setEnabled(true);
		botGuardar.setEnabled(true);
		botGuardarComo.setEnabled(true);
		botSalir.setEnabled(true);
		botAyuda.setEnabled(true);
		botNuevaBaraja.setEnabled(true);
		textoBarajaCargada.setEnabled(true);
		this.setEnabled(true);

	}


	/**
	 *  Description of the Method
	 */
	public void inhabilitaPanel() {

		//comboNombreCarta.setEnabled(false);
		jScrollPane1.setEnabled(false);
		jScrollPane1.getVerticalScrollBar().setEnabled(false);
		jScrollPane1.getHorizontalScrollBar().setEnabled(false);
		jScrollPane2.setEnabled(false);
		jScrollPane2.getVerticalScrollBar().setEnabled(false);
		jScrollPane2.getHorizontalScrollBar().setEnabled(false);
		listaSeleccionadas.setEnabled(false);
		listaDisponibles.setEnabled(false);
		labelFondo.setEnabled(false);
		textoNumeroCartas.setEnabled(false);
		textoRaza.setEnabled(false);
		botCargar.setEnabled(false);
		botGuardar.setEnabled(false);
		botGuardarComo.setEnabled(false);
		botSalir.setEnabled(false);
		botAyuda.setEnabled(false);
		botNuevaBaraja.setEnabled(false);
		textoBarajaCargada.setEnabled(false);
		this.panelFondo.setEnabled(false);
		labelImagen.setEnabled(false);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	//abstract void jComboNombreCarta_actionPerformed(ActionEvent e);


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	abstract void listSeleccionadas_mouseClicked(MouseEvent e);


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	abstract void listDisponibles_mouseClicked(MouseEvent e);









	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	//abstract void botAyuda_mouseClicked(MouseEvent e);






	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botCargar_mouseEntered(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botGuardar_mouseEntered(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botGuardarComo_mouseEntered(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botAyuda_mouseEntered(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botAcerca_mouseEntered(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botSalir_mouseEntered(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/punteroAct.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botSalir_mouseExited(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botAcerca_mouseExited(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botAyuda_mouseExited(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botGuardarComo_mouseExited(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botGuardar_mouseExited(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	void botCargar_mouseExited(MouseEvent e) {
		ImageIcon cursor = new ImageIcon("../imagenes/cursores/puntero.gif");
		Image image = cursor.getImage();
		Cursor puntero = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(8, 8), "img");
		this.setCursor(puntero);

	}


	/**
	 *  Description of the Method
	 *
	 *@exception  Exception  Description of Exception
	 */
	private void jbInit() throws Exception {

		this.setUndecorated(true);
		this.setSize(ancho, alto);

		panelFondo.setSize(ancho, alto);
		panelFondo.setLayout(null);

		labelFondo.setBounds(0, 0, ancho, alto);
		labelFondo.setIcon(new ImageIcon("../imagenes/EditorBarajasGUI/fondoEditorBarajas.jpg"));

		botCargar.setIcon(new ImageIcon("../imagenes/EditorBarajasGUI/CargarBaraja.jpg"));
    botCargar.addActionListener(new EditorBarajasGUI_botCargar_actionAdapter(this));
		botCargar.addMouseListener(new EditorBarajasGUI_botCargar_mouseAdapter(this));
		botGuardar.setIcon(new ImageIcon("../imagenes/EditorBarajasGUI/GuardarBaraja.jpg"));
    botGuardar.addActionListener(new EditorBarajasGUI_botGuardar_actionAdapter(this));
		botGuardar.addMouseListener(new EditorBarajasGUI_botGuardar_mouseAdapter(this));
		botGuardarComo.setIcon(new ImageIcon("../imagenes/EditorBarajasGUI/GuardarBarajaComo.jpg"));
    botGuardarComo.addActionListener(new EditorBarajasGUI_botGuardarComo_actionAdapter(this));
		botGuardarComo.addMouseListener(new EditorBarajasGUI_botGuardarComo_mouseAdapter(this));
		botSalir.setIcon(new ImageIcon("../imagenes/EditorBarajasGUI/Salir.jpg"));
    botSalir.addActionListener(new EditorBarajasGUI_botSalir_actionAdapter(this));
		botSalir.addMouseListener(new EditorBarajasGUI_botSalir_mouseAdapter(this));
		botAyuda.setIcon(new ImageIcon("../imagenes/EditorBarajasGUI/Ayuda.jpg"));
    botAyuda.addActionListener(new EditorBarajasGUI_botAyuda_actionAdapter(this));
		botAyuda.addMouseListener(new EditorBarajasGUI_botAyuda_mouseAdapter(this));
		botNuevaBaraja.setIcon(new ImageIcon("../imagenes/EditorBarajasGUI/NuevaBaraja.jpg"));
    botNuevaBaraja.addActionListener(new EditorBarajasGUI_botNuevaBaraja_actionAdapter(this));
		botNuevaBaraja.addMouseMotionListener(new EditorBarajasGUI_botAcerca_mouseMotionAdapter(this));
		botNuevaBaraja.addMouseListener(new EditorBarajasGUI_botAcerca_mouseAdapter(this));
    botAniadir.setIcon(new ImageIcon("../imagenes/EditorBarajasGUI/aniadirCarta.jpg"));


		/*
		 *  botCargar.setBounds(new Rectangle(ancho/6, 6*(alto/7), (int)(ancho/6.85), (int)(alto/27)));
		 *  botGuardar.setBounds(new Rectangle((int)(4.5*(ancho/7)),6*(alto/7), (int)(ancho/6.85), (int)(alto/27) ));
		 *  botGuardarComo.setBounds(new Rectangle((int)(1.9*(ancho/5)),6*(alto/7), (int)(ancho/5), (int)(alto/27) ));
		 *  botAyuda.setBounds(new Rectangle(ancho/6, (int) (6.35*(alto/7)), (int)(ancho/6.85), (int)(alto/27) ));
		 *  botAcerca.setBounds(new Rectangle((int)(ancho/2.5), (int) (6.35*(alto/7)),(int)(ancho/6.85), (int)(alto/27) ));
		 *  botSalir.setBounds(new Rectangle((int)(4.5*(ancho/7)),(int) (6.35*(alto/7)), (int)(ancho/6.85), (int)(alto/27) ));
		 */
		botCargar.setBounds(new Rectangle(ancho / 6, (int) (6.09 * (alto / 7)), (int) (ancho / 6.85), (int) (alto / 27)));
		botCargar.setBorder(null);
		botGuardar.setBounds(new Rectangle((int) (4.5 * (ancho / 7)), (int) (6.09 * (alto / 7)), (int) (ancho / 6.85), (int) (alto / 27)));
		botGuardar.setBorder(null);
		botGuardarComo.setBounds(new Rectangle((int) (1.9 * (ancho / 5)), (int) (6.09 * (alto / 7)), (int) (ancho / 5.2), (int) (alto / 27)));
		botGuardarComo.setBorder(null);
		botAyuda.setBounds(new Rectangle(ancho / 6, (int) (6.44 * (alto / 7)), (int) (ancho / 6.85), (int) (alto / 27)));
		botAyuda.setBorder(null);
		botNuevaBaraja.setBounds(new Rectangle((int) (ancho / 2.5), (int) (6.44 * (alto / 7)), (int) (ancho / 6.85), (int) (alto / 27)));
		botNuevaBaraja.setBorder(null);
		botSalir.setBounds(new Rectangle((int) (4.5 * (ancho / 7)), (int) (6.44 * (alto / 7)), (int) (ancho / 6.85), (int) (alto / 27)));
		botSalir.setBorder(null);
                botAniadir.setBounds(new Rectangle((int) (4.6 * (ancho / 7)), (int) (5.1 * (alto / 7)), (int) (ancho / 5.2), (int) (alto / 25)));
                botAniadir.setBorder(null);


		textoRaza.setBounds(2 * (ancho / 7), alto / 7, ancho / 8, alto / 20);
		textoRaza.setFont(new java.awt.Font("Serif", 3, 22));
		textoRaza.setBackground(Color.gray);
		textoRaza.setOpaque(true);

		textoNumeroCartas.setBounds(2 * (ancho / 7), alto / 5, ancho / 15, alto / 20);
		textoNumeroCartas.setFont(new java.awt.Font("Serif", 3, 22));
		textoNumeroCartas.setBackground(Color.gray);
		textoNumeroCartas.setOpaque(true);

		listaSeleccionadas.addMouseListener(new EditorBarajasGUI_listSeleccionadas_mouseAdapter(this));
		listaSeleccionadas.setBackground(Color.gray);
		jScrollPane1.setBounds(new Rectangle((int) (ancho / 10.3), (int) (alto / 2.5), ancho / 5, alto / 3));
		jScrollPane1.getViewport().add(listaSeleccionadas, null);

		listaDisponibles.addMouseListener(new EditorBarajasGUI_listDisponibles_mouseAdapter(this));
		listaDisponibles.setBackground(Color.gray);
		jScrollPane2.setBounds(new Rectangle((int) (3.75 * (ancho / 10.3)), (int) (alto / 2.5), ancho / 5, alto / 3));
		jScrollPane2.getViewport().add(listaDisponibles, null);

		labelImagen.setFont(new java.awt.Font("Serif", 3, 15));
		labelImagen.setBounds((int) (4.22 * (ancho / 7)), (int) (0.65 * (alto / 5)), (int) ((ancho / 3.29)), (int) (alto / 1.72));
		labelImagen.setBackground(Color.DARK_GRAY);
		labelImagen.setOpaque(true);

		//comboNombreCarta.addActionListener(new EditorBarajasGUI_jComboNombreCarta_actionAdapter(this));
		//comboNombreCarta.setFont(new java.awt.Font("Serif", 3, 20));
		//comboNombreCarta.setBounds((int) (4.3 * (ancho / 7)), alto / 7, (int) ((ancho / 3.5)), alto / 25);

		textoBarajaCargada.setFont(new java.awt.Font("Serif", 3, 20));
		textoBarajaCargada.setBounds((int) (ancho / 2.25), (int) (4.8 * (alto / 6)), ancho / 5, alto / 22);
		textoBarajaCargada.setBackground(Color.gray);

		textoBarajaCargada.setOpaque(true);
		textoBarajaCargada.setText("Ninguna");



		panelFondo.add(textoBarajaCargada, null);
		//panelFondo.add(comboNombreCarta, null);
		panelFondo.add(labelImagen, null);
		panelFondo.add(jScrollPane2, null);
		panelFondo.add(jScrollPane1, null);
		panelFondo.add(textoNumeroCartas, null);
		panelFondo.add(textoRaza, null);
		panelFondo.add(botCargar, null);
		panelFondo.add(botGuardar, null);
		panelFondo.add(botGuardarComo, null);
		panelFondo.add(botAyuda, null);
		panelFondo.add(botNuevaBaraja, null);
		panelFondo.add(botSalir, null);
                panelFondo.add(botAniadir,null);

		panelFondo.add(labelFondo, null);

		this.getContentPane().add(panelFondo, null);
	}

  abstract void botNuevaBaraja_actionPerformed(ActionEvent e);

  abstract void botAyuda_actionPerformed(ActionEvent e);

  abstract void botCargar_actionPerformed(ActionEvent e);

  abstract void botGuardar_actionPerformed(ActionEvent e) ;

  abstract void botGuardarComo_actionPerformed(ActionEvent e);

  abstract void botSalir_actionPerformed(ActionEvent e);
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class EditorBarajasGUI_botCargar_mouseAdapter extends java.awt.event.MouseAdapter {


	EditorBarajasGUI adaptee;


	/**
	 *  Constructor for the EditorBarajasGUI_botCargar_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	EditorBarajasGUI_botCargar_mouseAdapter(EditorBarajasGUI adaptee) {
		this.adaptee = adaptee;
	}



	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botCargar_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botCargar_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class EditorBarajasGUI_botGuardar_mouseAdapter extends java.awt.event.MouseAdapter {


	EditorBarajasGUI adaptee;


	/**
	 *  Constructor for the EditorBarajasGUI_botGuardar_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	EditorBarajasGUI_botGuardar_mouseAdapter(EditorBarajasGUI adaptee) {
		this.adaptee = adaptee;
	}




	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botGuardar_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botGuardar_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class EditorBarajasGUI_botGuardarComo_mouseAdapter extends java.awt.event.MouseAdapter {


	EditorBarajasGUI adaptee;


	/**
	 *  Constructor for the EditorBarajasGUI_botGuardarComo_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	EditorBarajasGUI_botGuardarComo_mouseAdapter(EditorBarajasGUI adaptee) {
		this.adaptee = adaptee;
	}




	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botGuardarComo_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botGuardarComo_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class EditorBarajasGUI_botAyuda_mouseAdapter extends java.awt.event.MouseAdapter {


	EditorBarajasGUI adaptee;


	/**
	 *  Constructor for the EditorBarajasGUI_botAyuda_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	EditorBarajasGUI_botAyuda_mouseAdapter(EditorBarajasGUI adaptee) {
		this.adaptee = adaptee;
	}




	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botAyuda_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botAyuda_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class EditorBarajasGUI_botSalir_mouseAdapter extends java.awt.event.MouseAdapter {


	EditorBarajasGUI adaptee;


	/**
	 *  Constructor for the EditorBarajasGUI_botSalir_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	EditorBarajasGUI_botSalir_mouseAdapter(EditorBarajasGUI adaptee) {
		this.adaptee = adaptee;
	}



	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botSalir_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botSalir_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class EditorBarajasGUI_botAcerca_mouseAdapter extends java.awt.event.MouseAdapter {


	EditorBarajasGUI adaptee;


	/**
	 *  Constructor for the EditorBarajasGUI_botAcerca_mouseAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	EditorBarajasGUI_botAcerca_mouseAdapter(EditorBarajasGUI adaptee) {
		this.adaptee = adaptee;
	}





	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseEntered(MouseEvent e) {
		adaptee.botAcerca_mouseEntered(e);
	}


	/**
	 *  Description of the Method
	 *
	 *@param  e  Description of Parameter
	 */
	public void mouseExited(MouseEvent e) {
		adaptee.botAcerca_mouseExited(e);
	}
}

/**
 *  Description of the Class
 *
 *@author    Chris Seguin
 */
class EditorBarajasGUI_botAcerca_mouseMotionAdapter extends java.awt.event.MouseMotionAdapter {


	EditorBarajasGUI adaptee;


	/**
	 *  Constructor for the EditorBarajasGUI_botAcerca_mouseMotionAdapter object
	 *
	 *@param  adaptee  Description of Parameter
	 */
	EditorBarajasGUI_botAcerca_mouseMotionAdapter(EditorBarajasGUI adaptee) {
		this.adaptee = adaptee;
	}

}

class EditorBarajasGUI_botNuevaBaraja_actionAdapter implements java.awt.event.ActionListener {
  EditorBarajasGUI adaptee;

  EditorBarajasGUI_botNuevaBaraja_actionAdapter(EditorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botNuevaBaraja_actionPerformed(e);
  }
}

class EditorBarajasGUI_botAyuda_actionAdapter implements java.awt.event.ActionListener {
  EditorBarajasGUI adaptee;

  EditorBarajasGUI_botAyuda_actionAdapter(EditorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botAyuda_actionPerformed(e);
  }
}

class EditorBarajasGUI_botCargar_actionAdapter implements java.awt.event.ActionListener {
  EditorBarajasGUI adaptee;

  EditorBarajasGUI_botCargar_actionAdapter(EditorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botCargar_actionPerformed(e);
  }
}

class EditorBarajasGUI_botGuardar_actionAdapter implements java.awt.event.ActionListener {
  EditorBarajasGUI adaptee;

  EditorBarajasGUI_botGuardar_actionAdapter(EditorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botGuardar_actionPerformed(e);
  }
}

class EditorBarajasGUI_botGuardarComo_actionAdapter implements java.awt.event.ActionListener {
  EditorBarajasGUI adaptee;

  EditorBarajasGUI_botGuardarComo_actionAdapter(EditorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botGuardarComo_actionPerformed(e);
  }
}

class EditorBarajasGUI_botSalir_actionAdapter implements java.awt.event.ActionListener {
  EditorBarajasGUI adaptee;

  EditorBarajasGUI_botSalir_actionAdapter(EditorBarajasGUI adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.botSalir_actionPerformed(e);
  }
}
