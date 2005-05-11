package genesis.noticias;

/**
 *  Error que se produce cuando se intenta acceder a una noticia que no
 *  existe,
 *
 *@author    Laura Garc�a
 */
public class NoticiaNoExistenteException extends Exception {

	private int id;


	/**
	 *  Constructora de la clase. 
	 *
	 *@param  mensaje        Informaci�n de la excepci�n
	 *@param  id             Identificador de la noticia a la que se intenta
	 *                       acceder
	 */
	public NoticiaNoExistenteException(String mensaje, int id) {
		super(mensaje);
		this.id = id;
	}



	/**
	 *  Obtiene el identificador de la carta que se intent� acceder y no exist�a
	 *
	 *@return    Identificador
	 */
	public int getId() {
		return id;
	}
}
