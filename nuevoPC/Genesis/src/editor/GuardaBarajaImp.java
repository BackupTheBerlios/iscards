package editor;


import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import usuario.*;




public class GuardaBarajaImp extends GuardaBarajaGUI {

  EditorBarajasImp padre;
  Usuario usuario;



  public GuardaBarajaImp(EditorBarajasImp panelPadre,Usuario usu) {

    padre = panelPadre;
    usuario = usu;

  }


  void botonAceptar_actionPerformed(ActionEvent e){

    String nombreFichero = textNombre.getText().toString();
    if (nombreFichero.endsWith(".bar")) {
      nombreFichero = nombreFichero.substring(0, nombreFichero.length() - 4);
      }
    if (!nombreFichero.endsWith("_" + usuario.getNombreUsuario())) {
      nombreFichero = nombreFichero + "_" + usuario.getNombreUsuario();
      }

    if (!usuario.getListaBarajas().contains(nombreFichero)){

      if (!nombreFichero.equals("_" + usuario.getNombreUsuario())) {
      //guardamos la baraja en .bar
      padre.guardarBarajaUsuario(nombreFichero);
      //guardamos la tabla de disponibles en usuario y en .rep
      padre.guardarColeccionUsuario();
      //guardamos el nombre de la baraja en la lista de barajas del usuario y en .conf
      padre.guardarListaBarajasUsuario(nombreFichero);

      padre.habilitaPanel();
      padre.repaint();
      padre.remove(this);


    }

      else {
       JOptionPane.showMessageDialog(new JOptionPane(),
       "No se ha salvado la baraja, el nombre no es aceptado",
       "AVISO", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    else{
          JOptionPane.showMessageDialog(new
                 JOptionPane(),
                 "No se ha salvado la baraja, ya existe una baraja con ese nombre",
                  "AVISO",
                  JOptionPane.
                  INFORMATION_MESSAGE);
    }

  }

  void botonCancelar_actionPerformed(ActionEvent e){
    padre.habilitaPanel();
    this.setVisible(false);


  }

}