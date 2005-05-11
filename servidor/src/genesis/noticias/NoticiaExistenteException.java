package genesis.noticias;

/**
 *  Error que se produce al intentar a�adir una noticia que ya existe.
 *
 *@author    Laura Garc�a
 */
public class NoticiaExistenteException extends Exception {

        // Identificador de la noticia que se intent� a�adir
	private int id;


	/**
	 *  Constructor de la clase.
	 *
	 *@param  mensaje        Informaci�n sobre el error
	 *@param  id		 Identificador de la noticia que se intent� a�adir 
	 */
	public NoticiaExistenteException(String mensaje, int id) {
		super(mensaje);
		this.id = id;
	}


	/**
	 *  Obtiene el identificador de la noticia que se intent� a�adir
	 *
	 *@return    Identificador
	 */
	public int getId() {
		return this.id;
	}
}
