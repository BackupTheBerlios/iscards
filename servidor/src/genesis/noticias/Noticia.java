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
        private String titulo;	    // t�tulo de la noticia
        private String tipo;	    // tipo: Aviso, Novedad, etc...
        private String contenido;   // cuerpo de la noticia
        private Date fecha;	    // fecha de creaci�n


        /**
         *  Constructor para el objeto Noticia
         *
         *@param  tit   T�tulo de la noticia
         *@param  tip   Tipo de la noticia: Aviso, Novedad, etc..
         *@param  cont  Cuerpo de la noticia
         *@param  fech  Fecha de creaci�n
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
	 *@param  id    C�digo identificador de la noticia
         *@param  tit   T�tulo de la noticia
         *@param  tip   Tipo de la noticia: Aviso, Novedad, etc..
         *@param  cont  Cuerpo de la noticia
         *@param  fech  Fecha de creaci�n
         */
        public Noticia(int nId, String tit, String tip, String cont, Date fech) {
	        this.id = nId;
                this.titulo = tit;
                this.tipo = tip;
                this.contenido = cont;
                this.fecha = fech;
        }
	
        /**
         *  Obtiene el t�tulo de la noticia
         *
         *@return    t�tulo
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
