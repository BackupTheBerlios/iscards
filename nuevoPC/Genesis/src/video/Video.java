package video;

import login.*;
import java.io.File;
import javax.media.Player;
import javax.media.Manager;
import javax.swing.JFrame;
import javax.media.format.VideoFormat;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.*;

/**
 * <p>T�tulo: </p>
 * <p>Descripci�n: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Empresa: </p>
 * @author Enrique Martorano
 * @version 1.0
 */

public class Video extends JFrame{

  //atributo que controla el video generado por la libreria jmf
  private  Player player;

  public Player Player(){return player;}

/*Lanza el video de presentacion del juego*/
  public Video() {

    try {
      VideoFormat vf= new VideoFormat("avi");
      File soundFile = new File("../Media/avis/intro.avi");
      player = Manager.createRealizedPlayer(soundFile.toURL());

      this.addKeyListener(new Oyente(this,player));
      this.setUndecorated(true);
      this.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()
                    ,(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());

      this.getContentPane().add(player.getVisualComponent());
      player.start();
      this.setVisible(true);
      this.show();
    }catch (Exception e) {
      e.printStackTrace();
    }
  }

}
/**
 *  Clase para cerrar el video si el usuario pulsa escape
 *
 *@author    Enrique Martorano
 */
class Oyente extends KeyAdapter {


  JFrame frame;
  Player player;

  /**
   *  Constructor for the Oyente object
   *
   *@param  f  la interfaz donde esta este oyente
   */
  public Oyente(JFrame f,Player p) {
    frame = f;
    player=p;
  }


  /**
   *  acciones que ocurren cuando se pulsa escape
   *
   *@param  e  evento
   */
        public void keyPressed(KeyEvent e) {
          try {
            if ((e.getKeyCode() == KeyEvent.VK_ESCAPE)||(e.getKeyCode() == KeyEvent.VK_ENTER))  {
              //bug! destruir el objeto
              player.close();
              new Genesis();
              frame.dispose();
           //   this.notifyAll();


            }
          }
          catch (Exception e1) {
            System.err.println("error en keypressed de Video.java");
            e1.printStackTrace();
          }
        }
      }
