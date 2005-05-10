/*
 *  ContextListener.java
 *
 *  Created on 9 de diciembre de 2004, 23:27
 */
package genesis.bd;

import genesis.foro.*;
import javax.servlet.*;

/**
 * Esta clase recibe los eventos de creación y liberación de la
 * aplicación Web. Implementa para ello la clase <tt>ServletContextListener</tt>
 *@author    Javi
 *@see	     javax.servlet.ServletContextListener
 */
public final class ContextListener implements ServletContextListener {

        /**
         * Este método es llamado cuando se lanza la aplicación web en
         * el servidor de aplicaciones. Se encarga de crear los gestores
         * de usuarios y de noticias y de registrarlos en las variables de
         * entorno <tt>BaseDatosUsuarios</tt> y <tt>BaseDatosNoticias</tt>
         * respectivamente.
         *
         * @param  servletContextEvent  Información sobre el evento.
         */
        public void contextInitialized(ServletContextEvent servletContextEvent) {
                ServletContext servletContext = servletContextEvent.getServletContext();
                try {
                        // Creamos el gestor de usuarios y del foro y los registramos
                        // en las variable entorno correspondientes
/*                        TemasBD temasBD = new TemasBD();
                        MensajesBD mensajesBD = new MensajesBD();
                        MensajeTemaBD mensaje_temaBD = new MensajeTemaBD();
                        servletContext.setAttribute("BaseDatosMensajes", mensajesBD);
                        servletContext.setAttribute("BaseDatosTemas", temasBD);
                        servletContext.setAttribute("BaseDatosMensaje_Tema", mensaje_temaBD);*/
                                  }
                catch (Exception e) {
                        servletContext.log("No se pudo crear el atributo BaseDatos: " + e.getMessage());
                }
        }


        /**
         * Se encarga de deregistrar las variables <tt>BaseDatosUsuarios</tt> y
         * <tt>BaseDatosNoticias</tt>, cuando el servidor de aplicaciones ordena
         * la finalización de esta aplicación web. También libera la conexión con
         * la base de datos.
         *
         *@param  servletContextEvent  Description of Parameter
         */
        public void contextDestroyed(ServletContextEvent servletContextEvent) {
                ServletContext servletContext = servletContextEvent.getServletContext();
                // Obtenemos los gestores de usuarios y del foro
/*                MensajesBD mensajesBD = (MensajesBD) servletContext.getAttribute("BaseDatosMensajes");
                TemasBD temasBD = (TemasBD) servletContext.getAttribute("BaseDatosTemas");
                MensajeTemaBD mensaje_temaBD = (MensajeTemaBD) servletContext.getAttribute("BaseDatosMensaje_Tema");*/
                // Liberamos sus conexiones con la base de datos
             /*   mensajesBD.close();
                temasBD.close();
                mensaje_temaBD.close();*/
                    // Y eliminamos las variables de entorno.
/*                servletContext.removeAttribute("BaseDatosMensajes");
                servletContext.removeAttribute("BaseDatosTemas");
                servletContext.removeAttribute("BaseDatosMensaje_Tema");*/
            }

}
