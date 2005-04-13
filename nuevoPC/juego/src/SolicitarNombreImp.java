
import coleccion.Coleccion;
import usuario.Usuario;

import java.awt.event.*;
import javax.swing.*;

/**
 * <p>Título: GENESIS</p>
 * <p>Descripción: Implementación del interfaz gráfico de la inscripción de un nuevo usuario</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Empresa: </p>
 * @author Rui Miguel Alonso
 * @version 1.0
 */

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
    padre.setEnabled(false);
    textNombre.setText("");
  }
  /**
   * Función que devuelve el nombre del usuario inscrito
   * @return nombre del nuevo usuario
   */
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
    if(nombre.equals(""))
      JOptionPane.showMessageDialog(new JOptionPane(), "Escriba un nombre de usuario",
                                    "Nombre incorrecto: vacio", JOptionPane.INFORMATION_MESSAGE);
    //creamos un nuevo usuario con ese nombre y lo añadimos a la dlm de la lista de usuarios
    else{
      //controlamos que el nombre no esté repetido
      if(padre.getLista().contains(nombre)){
        JOptionPane.showMessageDialog(new JOptionPane(), "Escriba otro nombre de usuario",
                                      "Nombre incorrecto: repetido", JOptionPane.INFORMATION_MESSAGE);
        textNombre.setText(null);

      }
      else{
        //creamos el usuario y devolvemos el control al padre (LoginImp)
        Usuario usuario = new Usuario(nombre, coleccion, false);
        usuario.creaColeccionUsuarioDefecto();
        padre.setEnabled(true);
        padre.getLista().addElement(nombre);
        padre.show();
        this.dispose();
      }
    }
  }

  /**
   * Función que controla el action event de Cancelar
   * @param e
   */
  void botonCancelar_actionPerformed(ActionEvent e){
    padre.setEnabled(true);
    padre.show();
    this.dispose();
  }
}