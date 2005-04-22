package interfaz;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

class PanelOtrosDatos
    extends JPanel {
  private char tipo;
  GridLayout gridLayout1 = new GridLayout();
  JTextField vacio = new JTextField();
  JTextField NumConjurosCementerio = new JTextField();
  JTextField NumHechizosCementerio = new JTextField();
  JTextField NumCriaturasCementerio = new JTextField();
  JTextField nombreCementerio = new JTextField();
  JTextField numConjurosMazo = new JTextField();
  JTextField numHechizosMazo = new JTextField();
  JTextField numCriaturasMazo = new JTextField();
  JTextField nombreMazo = new JTextField();
  JTextField nombreConjuros = new JTextField();
  JTextField nombreHechizos = new JTextField();
  JTextField nombreCriaturas = new JTextField();
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
        color = new Color(160, 160, 160);
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
    if(tipo == 'A'){vacio.setForeground(new Color(70,70,100));}
    vacio.setOpaque(false);
    vacio.setEditable(false);
    vacio.setText("NÚMERO");
    vacio.setHorizontalAlignment(SwingConstants.CENTER);

    NumConjurosCementerio.setHorizontalAlignment(SwingConstants.CENTER);
    NumConjurosCementerio.setText("0");
    NumConjurosCementerio.setOpaque(false);
    NumConjurosCementerio.setEditable(false);
    NumConjurosCementerio.setForeground(color);
    NumConjurosCementerio.setFont(new java.awt.Font("SansSerif", 3, 16));

    NumHechizosCementerio.setHorizontalAlignment(SwingConstants.CENTER);
    NumHechizosCementerio.setText("0");
    NumHechizosCementerio.setOpaque(false);
    NumHechizosCementerio.setEditable(false);
    NumHechizosCementerio.setForeground(color);
    NumHechizosCementerio.setFont(new java.awt.Font("SansSerif", 3, 16));

    NumCriaturasCementerio.setHorizontalAlignment(SwingConstants.CENTER);
    NumCriaturasCementerio.setText("0");
    NumCriaturasCementerio.setOpaque(false);
    NumCriaturasCementerio.setEditable(false);
    NumCriaturasCementerio.setForeground(color);
    NumCriaturasCementerio.setFont(new java.awt.Font("SansSerif", 3, 16));

    nombreCementerio.setHorizontalAlignment(SwingConstants.CENTER);
    nombreCementerio.setText("Cementerio");
    nombreCementerio.setOpaque(false);
    nombreCementerio.setEditable(false);
    nombreCementerio.setForeground(color);
       if(tipo == 'A'){nombreCementerio.setForeground(new Color(70,70,100));}
    nombreCementerio.setEnabled(true);
    nombreCementerio.setFont(new java.awt.Font("SansSerif", 3, 16));

    numConjurosMazo.setHorizontalAlignment(SwingConstants.CENTER);
    numConjurosMazo.setText("0");
    numConjurosMazo.setOpaque(false);
    numConjurosMazo.setEditable(false);
    numConjurosMazo.setForeground(color);
    numConjurosMazo.setFont(new java.awt.Font("SansSerif", 3, 16));

    numHechizosMazo.setHorizontalAlignment(SwingConstants.CENTER);
    numHechizosMazo.setText("0");
    numHechizosMazo.setOpaque(false);
    numHechizosMazo.setEditable(false);
    numHechizosMazo.setForeground(color);
    numHechizosMazo.setFont(new java.awt.Font("SansSerif", 3, 16));

    numCriaturasMazo.setHorizontalAlignment(SwingConstants.CENTER);
    numCriaturasMazo.setText("0");
    numCriaturasMazo.setOpaque(false);
    numCriaturasMazo.setEditable(false);
    numCriaturasMazo.setForeground(color);
    numCriaturasMazo.setFont(new java.awt.Font("SansSerif", 3, 16));

    nombreMazo.setHorizontalAlignment(SwingConstants.CENTER);
    nombreMazo.setText("Mazo");
    nombreMazo.setOpaque(false);
    nombreMazo.setEditable(false);
    nombreMazo.setForeground(color);
       if(tipo == 'A'){nombreMazo.setForeground(new Color(70,70,100));}
    nombreMazo.setFont(new java.awt.Font("SansSerif", 3, 16));

    nombreConjuros.setHorizontalAlignment(SwingConstants.CENTER);
    nombreConjuros.setText("Conjuros");
    nombreConjuros.setOpaque(false);
    nombreConjuros.setEditable(false);
    nombreConjuros.setForeground(color);
       if(tipo == 'A'){nombreConjuros.setForeground(new Color(70,70,100));}
    nombreConjuros.setFont(new java.awt.Font("SansSerif", 3, 16));

    nombreHechizos.setHorizontalAlignment(SwingConstants.CENTER);
    nombreHechizos.setText("Hechizos");
    nombreHechizos.setOpaque(false);
    nombreHechizos.setEditable(false);
    nombreHechizos.setForeground(color);
       if(tipo == 'A'){nombreHechizos.setForeground(new Color(70,70,100));}
    nombreHechizos.setFont(new java.awt.Font("SansSerif", 3, 16));

    nombreCriaturas.setHorizontalAlignment(SwingConstants.CENTER);
    nombreCriaturas.setText("Criaturas");
    nombreCriaturas.setOpaque(false);
    nombreCriaturas.setEditable(false);
    nombreCriaturas.setForeground(color);
       if(tipo == 'A'){nombreCriaturas.setForeground(new Color(70,70,100));}
    nombreCriaturas.setFont(new java.awt.Font("SansSerif", 3, 16));



    this.setBorder(titledBorder1);
    this.setOpaque(false);

    //this.setRequestFocusEnabled(true);
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
//    System.out.println("Bounds: " + this.getBounds().getWidth());
  //  System.out.println("numeroColumnas: " + gridLayout1.getColumns());
    //System.out.println("getHgap: " + gridLayout1.getHgap());

    anchoColumna = (int)vacio.getBounds().getWidth();/* (int) (this.getBounds().getWidth() /
                          gridLayout1.getColumns()) -
        ( (int) gridLayout1.getHgap() * 2); // * this.gridLayout1.getColumns());*/

//    System.out.println("anchoColumna: " + anchoColumna);

    altoColumna =  (int)vacio.getBounds().getHeight();/*( (int)this.getBounds().getHeight() /
                   (int) gridLayout1.getRows()) - (int)
        ( (int) gridLayout1.getRows() *
         ( (int) gridLayout1.getHgap() - 2));*/

//    System.out.println("altoColumna : " + altoColumna);

    g.drawImage(im2.getImage(),
                gridLayout1.getHgap(),
                gridLayout1.getHgap(),
                anchoColumna,
                altoColumna, null);
/*    System.out.println("ini => x: " + gridLayout1.getHgap() + " ; y: " +
                   gridLayout1.getHgap()+ " ; ancho: " +
                   anchoColumna+" ; alto: "+altoColumna);*/

    //mano
    g.drawImage(im2.getImage(),
                gridLayout1.getHgap(),
                gridLayout1.getHgap() * 2 + altoColumna,
                anchoColumna,
                altoColumna, null);

/*    System.out.println("Mano => x: " + gridLayout1.getHgap() + " ; y: " +
                       (gridLayout1.getHgap() * 2 + altoColumna )+ " ; ancho: " +
                       anchoColumna+" ; alto: "+altoColumna);*/
    //cementerio
    g.drawImage(im2.getImage(),
                gridLayout1.getHgap(),
                gridLayout1.getHgap() * 3 + altoColumna * 2,
                anchoColumna,
                altoColumna, null);

/*    System.out.println("Cementerio => x: " + gridLayout1.getHgap() + " ; y: " +
                   (gridLayout1.getHgap() * 3 + altoColumna *2)+ " ; ancho: " +
                   anchoColumna+" ; alto: "+altoColumna);*/

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