package comunicacion;

import javax.swing.*;
import interfaz.Interfaz;
import padrePaneles.*;
import java.awt.event.*;
import panelesInfo.PanelGenerico;

/**
 *  Clase principal del la interfaz gráfica
 *
 *@author     Manuel Domingo Mora, Jesús Patiño y Francisco Javier Arellano
 *@version    2.0, revisada y mejorada por Enrique Martorano
 */

public class GUI extends PanelNick  {


	private Interfaz padre;
        private Controlador controlador;


	/**
	 *  Constructor de la clase GUI
	 *
	 *@param  c      Le inserto el controlador
	 *@param  padre  Ventana padre
	 *@param  in     Description of Parameter
	 */
  public GUI(Controlador c, Interfaz padrePanel) {

     padre = padrePanel;
     controlador = c;

     }

 void textNombre_keyPressed(KeyEvent e){
   if (e.getKeyCode()==KeyEvent.VK_ENTER){

     botonAceptar_actionPerformed(null);


   }
 }
  void botonAceptar_actionPerformed(ActionEvent e) {

    String nombre = textNombre.getText().toString();
    String contraseña = textContra.getText().toString();


   //impedimos que se escriba un nombre vacio
   if(nombre.equals("")){

     this.repaint();

     //necesario eliminar el panel para eliminar los botones de detras
     padre.remove(this);
     padre.getContentPane().add(new PanelGenerico("../imagenes/panelesInfo/NombreVacio.jpg",padre),0);


   }

   else{

     try{
        controlador.conectar();
        }
        catch (Exception e1) {      	
          JOptionPane.showMessageDialog(null, "Error al conectar usuario.",
                          "ERROR",
                          JOptionPane.ERROR_MESSAGE);
          this.setVisible(false);
          return;
        }


	controlador.setmiContraseñadeControlador(contraseña);
     //controlamos que el nombre no esté repetido
     if(!controlador.aniadirUser(nombre)){

       this.repaint();
       padre.remove(this);
       padre.getContentPane().add(new PanelGenerico("../imagenes/panelesInfo/NombreRepetido.jpg",padre),0);

     }
     else {
       //creamos el usuario y devolvemos el control al padre (Interfaz)


       padre.inhabilitaPanel();
       this.repaint();
       padre.remove(this);
       padre.getContentPane().add(new VentanaPrincipal(controlador, nombre,contraseña,
             padre, padre),0);


      /*   VentanaPrincipal ventPrinc = new VentanaPrincipal(controlador, nombre,
             padre, padre);
       }
*/
    }

   }

}


	 void botonCancelar_actionPerformed(ActionEvent e){
	   //padre.habilitaPanel();
	   padre.botonSalir_actionPerformed(null);
	   this.setVisible(false);
	 }


}

