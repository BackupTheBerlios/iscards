package video;


import java.io.File;
import javax.media.Player;
import javax.media.Manager;
import javax.swing.JFrame;
import javax.media.format.VideoFormat;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * <p>Título: </p>
 * <p>Descripción: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Empresa: </p>
 * @author Enrique Martorano
 * @version 1.0
 */

public class Video {

/*Lanza el video de presentacion del juego*/
  public Video() {

    try {
      VideoFormat vf= new VideoFormat("avi");
      File soundFile = new File("../Media/avis/Lit.mpg");
      Player player = Manager.createRealizedPlayer(soundFile.toURL());

      JFrame frame = new JFrame();
      frame.addKeyListener(new Oyente(frame));

      frame.getContentPane().add(player.getVisualComponent());
//    frame.getContentPane()
      player.start();
      //player.close();
      frame.setVisible(true);
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

  /**
   *  Constructor for the Oyente object
   *
   *@param  f  la interfaz donde esta este oyente
   */
  public Oyente(JFrame f) {
    frame = f;
  }


  /**
   *  acciones que ocurren cuando se pulsa escape
   *
   *@param  e  evento
   */
        public void keyPressed(KeyEvent e) {
          try {
            if ((e.getKeyCode() == KeyEvent.VK_ESCAPE))  {
              //bug! destruir el objeto
              frame.hide();//);.invalidate(); //.remove(frame);
            }
          }
          catch (Exception e1) {
            System.err.println("error en keypressed de Video.java");
            e1.printStackTrace();
          }
        }
      }
