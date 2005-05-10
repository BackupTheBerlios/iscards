package genesis.noticias;

import java.sql.Date;

/**
 *  Cada objeto de esta clase agrupa los elementos que conforman
 *  una noticia en la base de datos.
 *
 *@author    Laura Garcia
 */
public class Noticia {

        private int id;
        private String titulo;	    // título de la noticia
        private String tipo;	    // tipo: Aviso, Novedad, etc...
        private String contenido;   // cuerpo de la noticia
        private Date fecha;	    // fecha de creación


        /**
         *  Constructor para el objeto Noticia
         *
         *@param  tit   Título de la noticia
         *@param  tip   Tipo de la noticia: Aviso, Novedad, etc..
         *@param  cont  Cuerpo de la noticia
         *@param  fech  Fecha de creación
         */
        public Noticia(String tit, String tip, String cont, Date fech) {
	        this.id = 0;
                this.titulo = tit;
                this.tipo = tip;
                this.contenido = cont;
                this.fecha = fech;
        }


        /**
         *  Constructor para el objeto Noticia
         *
	 *@param  id    Código identificador de la noticia
         *@param  tit   Título de la noticia
         *@param  tip   Tipo de la noticia: Aviso, Novedad, etc..
         *@param  cont  Cuerpo de la noticia
         *@param  fech  Fecha de creación
         */
        public Noticia(int nId, String tit, String tip, String cont, Date fech) {
	        this.id = nId;
                this.titulo = tit;
                this.tipo = tip;
                this.contenido = cont;
                this.fecha = fech;
        }
	
        /**
         *  Obtiene el título de la noticia
         *
         *@return    título
         */
        public String getTitulo() {
                return this.titulo;
        }


        /**
         * Obtiene el tipo de la noticia
         *
         *@return    tipo
         */
        public String getTipo() {
                return this.tipo;
        }


        /**
         * Obtiene el contenido de la noticia
         *
         *@return    cadena con el cuerpo de la noticia
         */
        public String getContenido() {
                return this.contenido;
        }


        /**
         * Fecha de la noticia
         *
         *@return    fecha
         */
        public Date getFecha() {
                return this.fecha;
        }

	public int getId() {
	   return this.id;
	}

}
