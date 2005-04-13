package comunicacion;

import javax.swing.*;

public class PanelNick extends JPanel
{
        /**
        * Campo de texto donde se introduce el nick
        */
        private JTextField nick;

        /**
        * Etiqueta de texto
        */
        private JLabel lnick;

        /**
        * Campo de texto donde se introduce la clave
        */
        private JPasswordField password;

        /**
        * Label de campo de texto donde se introduce la clave
        */
        private JLabel lpassword;

        /**
        * Constructor y configurador del cuadro de dialogo
        */
        public PanelNick()
        {
               //panel
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                nick = new JTextField(10);
                lnick = new JLabel("Login:");
                password = new JPasswordField(10);
                lpassword = new JLabel("Password:");
              // añade los componentes a panel de tamaño
                panel.add(lnick);
                panel.add(nick);
                panel.add(lpassword);
                panel.add(password);
                //añadimos el panel
                add(panel);
        }

        /**
        * Devuelve el nick
        * @return String con el nick del usuario
        */
        public String getNick()
        {
                return nick.getText();
        }

}
