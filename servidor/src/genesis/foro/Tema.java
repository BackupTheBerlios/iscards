package genesis.foro;

import java.sql.Date;

/**
 *  <p>
 *
 *  Title: Tema
 *
 *  Description: Implementa la clase que describe los temas del foro
 *
 *  Copyright: Copyright (c) Genesis
 *
 *  Company: Genesis
 *
 *  @author: Laura Díaz, Conchi Fernandez, Laura García, Inés González, Sergio Somovilla
 *
 *  @version    3.01
 */

public class Tema {
        private int idTema;
        private String titulo;
        private int estado;
        private Date fecha;
        private int cont_visitas;


        /**
         *  Constructor for the Tema object
         *
         *@param  id      Identificador del tema
         *@param  titulo  Titulo del tema
         *@param  fecha   Fecha de creación del tema
         *@param  cont    Contador de visitas del tema
         */
        public Tema(int id, String titulo, int cont) {
                this.idTema = id;
                this.titulo = titulo;
                this.estado = 0;
                this.fecha = new Date(0000,00,00);
                this.cont_visitas = cont;
        }

        public Tema(int id, String titulo ,int estado, Date fecha,int cont) {
                this.idTema = id;
                this.titulo = titulo;
                this.estado = estado;
                this.fecha = fecha;
                this.cont_visitas = cont;
        }


        /**
         *  Gets the IdTema attribute of the Tema object
         *
         *@return    The IdTema value
         */
        public int getIdTema() {
                return idTema;
        }

        /**
         *  Gets the IdTema attribute of the Tema object
         *
         *@return    The IdTema value
         */
        public String getIdTemaString() {
          String idS;
          idS = new Integer(this.idTema).toString();
          return idS;
        }


        /**
         *  Gets the Titulo attribute of the Tema object
         *
         *@return    The Titulo value
         */
        public String getTitulo() {
                return titulo;
        }

        // Devolvemos el indice que está a true

        /**
         *  Gets the Estado attribute of the Tema object
         *
         *@return    The Estado value
         */
        public int getEstado() {
          return estado;
        }


        /**
         *  Gets the Fecha attribute of the Tema object
         *
         *@return    The Fecha value
         */
        public Date getFecha() {
                return fecha;
        }

        public String getFechaString() {
          String f;
          f = fecha.toString();
          return f;
        }


        /**
         *  Gets the Cont_visitas attribute of the Tema object
         *
         *@return    The Cont_visitas value
         */
        public int getCont_visitas() {
                return cont_visitas;
        }

        /**
         *  Gets the Cont_visitas attribute of the Tema object
         *
         *@return    The Cont_visitas value
         */
        public void setCont_visitas() {
                this.cont_visitas = cont_visitas + 1;
        }
}
