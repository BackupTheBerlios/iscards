package genesis.noticias;

/**
 *  Error que se produce al intentar añadir una noticia que ya existe.
 *
 *@author    Laura García
 */
public class NoticiaExistenteException extends Exception {

        // Identificador de la noticia que se intentó añadir
	private int id;


	/**
	 *  Constructor de la clase.
	 *
	 *@param  mensaje        Información sobre el error
	 *@param  id		 Identificador de la noticia que se intentó añadir 
	 */
	public NoticiaExistenteException(String mensaje, int id) {
		super(mensaje);
		this.id = id;
	}


	/**
	 *  Obtiene el identificador de la noticia que se intentó añadir
	 *
	 *@return    Identificador
	 */
	public int getId() {
		return this.id;
	}
}
