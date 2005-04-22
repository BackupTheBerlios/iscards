import coleccion.Coleccion;
import usuario.Usuario;
//import coleccion.*;

import java.awt.event.*;
import javax.swing.*;
import panelesInfo.*;
import padrePaneles.*;


public class SolicitarNombreImp extends SolicitarNombreGUI{

  /**
   * Variable que almacenará al padre (LoginImp) de este JFrame
   */
  private LoginImp padre;

  /**
   * Variable para almacenar el nombre introducido del nuevo usuario
   */
  private String nombre;

  /**
   * Variable para guardar la coleccion de todas las cartas
   */
  private Coleccion coleccion;

  /**
   * Constructora de la clase
   */
  public SolicitarNombreImp(LoginImp p, Coleccion col){
    coleccion = col;
    padre = p;
    textNombre.setText("");
  }
  /**
   * Función que devuelve el nombre del usuario inscrito
   * @return nombre del nuevo usuario
   */

  public void inhabilitaPanel(){

  }

  public void habilitaPanel(){

}



  public String getNombre(){
    return nombre;
  }

  /**
   * Función que controla la acción al pulsar el botón Aceptar
   * @param e
   */
  void botonAceptar_actionPerformed(ActionEvent e) {
    nombre = textNombre.getText();
    //impedimos que se escriba un nombre vacio
    if(nombre.equals("")){

      this.repaint();
     // this.setEnabled(false);

      //necesario eliminar el panel para eliminar los botones de detras
      padre.remove(this);
      padre.getContentPane().add(new PanelGenerico("../imagenes/panelesInfo/NombreVacio.jpg",padre),0);


    }
     //creamos un nuevo usuario con ese nombre y lo añadimos a la dlm de la lista de usuarios
    else{
      //controlamos que el nombre no esté repetido
      if(padre.getLista().contains(nombre)){

        this.repaint();
       // this.setEnabled(false);
        padre.remove(this);
        padre.getContentPane().add(new PanelGenerico("../imagenes/panelesInfo/NombreRepetido.jpg",padre),0);

      }
      else{
        //creamos el usuario y devolvemos el control al padre (LoginImp)
        Usuario usuario = new Usuario(nombre, coleccion, false);
		usuario.creaColeccionUsuarioDefecto();
        padre.getLista().addElement(nombre);
        padre.habilitaPanel();
        //this.hide();
        this.setVisible(false);
      }
    }
  }

  /**
   * Función que controla el action event de Cancelar
   * @param e
   */
  void botonCancelar_actionPerformed(ActionEvent e){
    padre.habilitaPanel();
    //this.hide();
    this.setVisible(false);
  }
}
