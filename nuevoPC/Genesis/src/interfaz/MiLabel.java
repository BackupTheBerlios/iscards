package interfaz;

import javax.swing.*;
import java.awt.*;

class MiLabel
    extends JLabel {
  private char tipo;
  ImageIcon fondo;
  boolean hayNuevoFondo = false;
  public MiLabel(char tip) {
    tipo = tip;
  }

  public void paintComponent(Graphics g) {
    ImageIcon tribalMesa, tribalMano;
    int diferencia = 20;
    if (!hayNuevoFondo) {
      switch (tipo) {
        case 'A': {
          fondo = new ImageIcon("../imagenes/fondos/fondo_¡ngeles3.jpg");
          break;
        }
        case 'H': {
          fondo = new ImageIcon("../imagenes/fondos/fondo_Humanos3.jpg");
          break;
        }
        case 'I': {
          fondo = new ImageIcon("../imagenes/fondos/fondo_Inmortales3.jpg");
          break;
        }
        default: {
          fondo = new ImageIcon("../imagenes/fondos/fondo_Demonios3.jpg");
          break;
        }
      }
    }


      switch (tipo) {
        case 'A': {
          tribalMesa = new ImageIcon(
              "../imagenes/separador/tribal¡ngeles_v7.gif");
          tribalMano = new ImageIcon(
              "../imagenes/separador/tribal¡ngelesMano_v5.gif");
          diferencia = 16;

          break;
        }
        case 'H': {
          tribalMesa = new ImageIcon(
              "../imagenes/separador/tribalHumanos_v10.gif");
          tribalMano = new ImageIcon(
              "../imagenes/separador/tribalHumanosMano_v1.gif");
          diferencia = 28;
          break;
        }
        case 'I': {
          tribalMesa = new ImageIcon(
              "../imagenes/separador/tribalInmortales_v5.gif");
          tribalMano = new ImageIcon(
              "../imagenes/separador/tribalInmortalesMano_v4.gif");
          break;
        }
        default: {
          tribalMesa = new ImageIcon(
              "../imagenes/separador/tribalDemonios_v9.gif");
          tribalMano = new ImageIcon(
              "../imagenes/separador/tribalDemoniosMano_v1.gif");
          diferencia = 30;
          break;
        }
      }

        g.drawImage(fondo.getImage(), 0, 0, null);
        g.drawImage(tribalMesa.getImage(), 0,
                    (int) ( (this.getSize().getHeight() / 3) - 50),
                    (int)this.getSize().getWidth(), 50, null);

        g.drawImage(tribalMano.getImage(), 0,
                    ( (2 * (int) (this.getSize().getHeight() / 3)) - diferencia -
                     (15 - diferencia / 2)),
                    (int)this.getSize().getWidth(), diferencia, null);

      }
      public void setFondo(ImageIcon nuevoFondo) {
        fondo = nuevoFondo;
        hayNuevoFondo = true;
        this.repaint();

      }
    }
