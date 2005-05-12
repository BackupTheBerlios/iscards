package editor;


import java.awt.event.*;
import java.util.LinkedList;

import usuario.Usuario;

/**
 * <p>Título: </p>
 * <p>Descripción: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Empresa: </p>
 * @author sin atribuir
 * @version 1.0
 */

public class BarajasDisponiblesImp extends BarajasDisponiblesGUI{

  private EditorBarajasImp padre;
  private Usuario usuario;
  private Object[] barajasArray;



  public BarajasDisponiblesImp(EditorBarajasImp panelPadre,Object[] barajasA) {


    padre = panelPadre;
    usuario = panelPadre.getUsuario();

   // barajasArray =barajasA;



    for (int i=0;i<barajasA.length;i++){
      dlmBarajas.add(i,barajasA[i].toString());
    }
    //this.listaBarajas.setModel(dlmBarajas);
    //scroll.getViewport().add(listaBarajas, null);

  }



  void botonAceptar_actionPerformed(ActionEvent e){

    if (barajasArray.length!=0){
    //poner por defecto la 1ª


    padre.habilitaPanel();
    padre.getContentPane().remove(this);

    }
    else {
      //no hay barajas en el panel
    }


  }


  void botonCancelar_actionPerformed(ActionEvent e){
    padre.habilitaPanel();
    padre.getContentPane().remove(this);

  }



}