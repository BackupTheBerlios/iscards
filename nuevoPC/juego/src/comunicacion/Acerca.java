package comunicacion;

import javax.swing.*;
import java.awt.event.*;
//import cliente.*;

public class Acerca extends JFrame
{
        /**
        * Panel contenedor de la ventana
        */
        private JPanel panelContenedor;

        /**
        * Panel
        */
        private JPanel panel1;

        /**
        * Panel
        */
        private JPanel panel2;

        /**
        * Boton de volver
        */
        private JButton boton;

        /**
        * Etiquetas de texto
        */
        private JLabel texto1,texto2;

        /**
        * Crea el la ventana de about y la muestra
        */
        public Acerca()
        {
                super("Version");
                mostrar();
        }

        /**
        * Muestra la ventana de about
        */
        public void mostrar()
        {
                        // Creamos el contenedor de los componentes
                                panelContenedor = (JPanel)this.getContentPane();
                                panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));
                                panel1 = new JPanel();
                                panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
                                panel2 = new JPanel();
                                panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
                                texto1=new JLabel();
                                texto2=new JLabel();
                                texto1.setText("Manuel Mora");
                                texto2.setText("@2004, version 1.0");
                                boton=new JButton("Volver");
                                boton.addActionListener(new OyenteBoton());
                                panel1.add(texto1);
                                panel1.add(texto2);
                                panel2.add(boton);
                                panelContenedor.add(panel1);
                                panelContenedor.add(panel2);
                    //Para dimensionarlo
                        setSize(220,100);
                        setVisible(true);
        }

        /**
        * Clase interna para capturar los eventos de los botones
        */
        class OyenteBoton implements ActionListener
        {
                  /**
                * Captura u oye el evento emitido por el boton volver y oculta la ventana
                * @param e Evento a capturar
                */
                public void actionPerformed(ActionEvent e)
                {
                        dispose();
                }
        }
}
