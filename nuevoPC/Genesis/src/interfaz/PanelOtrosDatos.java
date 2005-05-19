package interfaz;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

class PanelOtrosDatos
    extends JPanel {
  private char tipo;
  GridLayout gridLayout1 = new GridLayout();
JLabel vacio = new JLabel();
JLabel NumConjurosCementerio = new JLabel();
JLabel NumHechizosCementerio = new JLabel();
JLabel NumCriaturasCementerio = new JLabel();
JLabel nombreCementerio = new JLabel();
JLabel numConjurosMazo = new JLabel();
JLabel numHechizosMazo = new JLabel();
JLabel numCriaturasMazo = new JLabel();
JLabel nombreMazo = new JLabel();
JLabel nombreConjuros = new JLabel();
JLabel nombreHechizos = new JLabel();
JLabel nombreCriaturas = new JLabel();
TitledBorder titledBorder1;

  private int anchoColumna, altoColumna;
  ImageIcon im2;
  public PanelOtrosDatos(char tip) {
    tipo = tip;
    im2 = new ImageIcon("../imagenes/fondoGris6.jpg");
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {

    Color color = Color.red;
    switch (tipo) {
      //angeles
      case 'A':
        color = new Color(40, 140, 140);
        break;
        //demonios
      case 'D':
        color = new Color(190, 0, 40);
        break;
        //inmortales
      case 'I':
        color = new Color(144, 0, 164); //color
        break;
        //humanos
      case 'H':
        color = new Color(0, 150, 90);
        break;
    }
    titledBorder1 = new TitledBorder("");

    this.setLayout(gridLayout1);
    this.setOpaque(false);
    vacio.setFont(new java.awt.Font("SansSerif", 3, 16));
    vacio.setForeground(color);
    vacio.setOpaque(false);
    vacio.setText("NÚMERO");
    vacio.setHorizontalAlignment(SwingConstants.CENTER);

    NumConjurosCementerio.setHorizontalAlignment(SwingConstants.CENTER);
    NumConjurosCementerio.setText("0");
    NumConjurosCementerio.setOpaque(false);
    NumConjurosCementerio.setForeground(color);
    NumConjurosCementerio.setFont(new java.awt.Font("SansSerif", 3, 16));

    NumHechizosCementerio.setHorizontalAlignment(SwingConstants.CENTER);
    NumHechizosCementerio.setText("0");
    NumHechizosCementerio.setOpaque(false);
    NumHechizosCementerio.setForeground(color);
    NumHechizosCementerio.setFont(new java.awt.Font("SansSerif", 3, 16));

    NumCriaturasCementerio.setHorizontalAlignment(SwingConstants.CENTER);
    NumCriaturasCementerio.setText("0");
    NumCriaturasCementerio.setOpaque(false);
    NumCriaturasCementerio.setForeground(color);
    NumCriaturasCementerio.setFont(new java.awt.Font("SansSerif", 3, 16));

    nombreCementerio.setHorizontalAlignment(SwingConstants.CENTER);
    nombreCementerio.setText("Cementerio");
    nombreCementerio.setOpaque(false);
    nombreCementerio.setForeground(color);
    nombreCementerio.setFont(new java.awt.Font("SansSerif", 3, 16));

    numConjurosMazo.setHorizontalAlignment(SwingConstants.CENTER);
    numConjurosMazo.setText("0");
    numConjurosMazo.setOpaque(false);
    numConjurosMazo.setForeground(color);
    numConjurosMazo.setFont(new java.awt.Font("SansSerif", 3, 16));

    numHechizosMazo.setHorizontalAlignment(SwingConstants.CENTER);
    numHechizosMazo.setText("0");
    numHechizosMazo.setOpaque(false);
    numHechizosMazo.setForeground(color);
    numHechizosMazo.setFont(new java.awt.Font("SansSerif", 3, 16));

    numCriaturasMazo.setHorizontalAlignment(SwingConstants.CENTER);
    numCriaturasMazo.setText("0");
    numCriaturasMazo.setOpaque(false);
    numCriaturasMazo.setForeground(color);
    numCriaturasMazo.setFont(new java.awt.Font("SansSerif", 3, 16));
    numCriaturasMazo.setBorder(this.titledBorder1);

    nombreMazo.setHorizontalAlignment(SwingConstants.CENTER);
    nombreMazo.setText("Mazo");
    nombreMazo.setOpaque(false);
    nombreMazo.setForeground(color);
    nombreMazo.setFont(new java.awt.Font("SansSerif", 3, 16));

    nombreConjuros.setHorizontalAlignment(SwingConstants.CENTER);
    nombreConjuros.setText("Conjuros");
    nombreConjuros.setOpaque(false);
    nombreConjuros.setForeground(color);
    nombreConjuros.setFont(new java.awt.Font("SansSerif", 3, 16));

    nombreHechizos.setHorizontalAlignment(SwingConstants.CENTER);
    nombreHechizos.setText("Hechizos");
    nombreHechizos.setOpaque(false);
    nombreHechizos.setForeground(color);
    nombreHechizos.setFont(new java.awt.Font("SansSerif", 3, 16));

    nombreCriaturas.setHorizontalAlignment(SwingConstants.CENTER);
    nombreCriaturas.setText("Criaturas");
    nombreCriaturas.setOpaque(false);
    nombreCriaturas.setForeground(color);
    nombreCriaturas.setFont(new java.awt.Font("SansSerif", 3, 16));



     NumConjurosCementerio.setBorder(this.titledBorder1);
     NumHechizosCementerio.setBorder(this.titledBorder1);
     NumCriaturasCementerio.setBorder(this.titledBorder1);
     nombreCementerio.setBorder(this.titledBorder1);
     numConjurosMazo.setBorder(this.titledBorder1);
     numHechizosMazo.setBorder(this.titledBorder1);
     numCriaturasMazo.setBorder(this.titledBorder1);
     nombreMazo.setBorder(this.titledBorder1);
     nombreConjuros.setBorder(this.titledBorder1);
     nombreHechizos.setBorder(this.titledBorder1);
     nombreCriaturas.setBorder(this.titledBorder1);
     vacio.setBorder(this.titledBorder1);

    this.setBorder(titledBorder1);
    this.setOpaque(false);

    gridLayout1.setColumns(4);
    gridLayout1.setHgap(4);
    gridLayout1.setRows(3);
    gridLayout1.setVgap(4);

    this.add(vacio, null);
    this.add(nombreCriaturas, null);
    this.add(nombreHechizos, null);
    this.add(nombreConjuros, null);
    this.add(nombreMazo, null);
    this.add(numCriaturasMazo, null);
    this.add(numHechizosMazo, null);
    this.add(numConjurosMazo, null);
    this.add(nombreCementerio, null);
    this.add(NumCriaturasCementerio, null);
    this.add(NumHechizosCementerio, null);
    this.add(NumConjurosCementerio, null);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    anchoColumna = (int)vacio.getBounds().getWidth();
    altoColumna =  (int)vacio.getBounds().getHeight();
    g.drawImage(im2.getImage(),
                gridLayout1.getHgap(),
                gridLayout1.getHgap(),
                anchoColumna,
                altoColumna, null);
    g.drawImage(im2.getImage(),
                gridLayout1.getHgap(),
                gridLayout1.getHgap() * 2 + altoColumna,
                anchoColumna,
                altoColumna, null);

    g.drawImage(im2.getImage(),
                gridLayout1.getHgap(),
                gridLayout1.getHgap() * 3 + altoColumna * 2,
                anchoColumna,
                altoColumna, null);


    //criatura
    g.drawImage(im2.getImage(),
                gridLayout1.getHgap() * 2 + anchoColumna,
                gridLayout1.getHgap(),
                anchoColumna,
                altoColumna, null);
    //hechizo
    g.drawImage(im2.getImage(),
                gridLayout1.getHgap() * 3 + anchoColumna * 2,
                gridLayout1.getHgap(),
                anchoColumna,
                altoColumna, null);
    //conjuro
    g.drawImage(im2.getImage(),
                gridLayout1.getHgap() * 4 + anchoColumna * 3,
                gridLayout1.getHgap(),
                anchoColumna,
                altoColumna, null);

  }

}