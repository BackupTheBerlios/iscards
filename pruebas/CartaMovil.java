package MazoMovil;

/** Esta clase representa las cartas que manejamos en el dispositivo m�vil
 */

public class CartaMovil{
        /** Codigo de identificaci�n de la carta*/
	private int codigoID;
        /** Version de la baraja a la que pertenece esta carta*/
	private int versionBaraja;
        /** Informaci�n completa de la carta. Detallamos la informaci�n que contiene cada indice del array:<p>
         *  [0] Nombre<p>
         *  [1] Raza<p>
         *  [2] Ataque<p>
         *  [3] Defensa<p>
         *  [4] Caracter�sticas<p>
         *  [5] Historia<p>
         *  [6] Nivel
         */
	private String[] informacion;
        
	/**Indica cuantas cartas de este tipo est�n en la baraja. Actualizado autom�ticamente al a�adir o eliminar la carta de la baraja
	 */
    private int cantidad;

    /** Crea una carta nueva de acuerdo a la informaci�n de los par�metros
     * @param cod Es el c�digo de identificaci�n de la carta a crear
     * @param ver Es la versi�n de la baraja de la carta a crear
     * @param info Es la informaci�n completa de la carta a crear. Consultar la definici�n del atributo informacion para conocer la informaci�n que contienen sus diferentes �ndices
     */
	public CartaMovil(int cod,int ver, String[] info){
		codigoID=cod;
		versionBaraja=ver;
                cantidad=1;
		informacion=new String[info.length];
		for (int i=0; i< info.length;i++)
			informacion[i]=info[i];
	}
        
    /** Accesor del atributo codigoID
     * @return Devuelve el codigo de identificaci�n de la carta.
     */
    public int getCodigoID(){
        return codigoID;
    }
    /** Accesor del atributo versi�nBaraja
     * @return Devuelve la versi�n de baraja de la carta.
     */        
    public int getversionBaraja(){
        return versionBaraja;
    }
    
    public String getNombre(){
        return informacion[0];
    }
    
    /** Accesor del atributo cantidad
     * @return Devuelve cu�ntas cartas como esta hay en la baraja.
     */        
    public int getCantidad(){
        return cantidad;
    }
    
    public String getNivel(){
        return informacion[6];
    }
    
    public String getRaza(){
        return informacion[1];
    }
    
    /**Aumenta en uno la cardinalidad de la carta
     */
    public void aumentaNumero(){
        cantidad++;
    }

    /**Disminuye en uno la cardinalidad de la carta
     */
    public boolean disminuyeNumero(){
        if (cantidad==1) return true;
        else {
            cantidad--;
            return false;
        }
    }
    
    public String[] getInfoCompleta(){
        return informacion;
    }
	
    /** Genera el texto completo de la carta para la vista en modo extendido
     * @return La informaci�n de la carta formateada para mostrarla al usuario en formato extendido
     */
	public String toString(){		
		return ("C�digo: "+ codigoID+
				".\nBaraja versi�n: "+versionBaraja+
				".\nNombre: "+informacion[0]+
				".\nRaza: "+informacion[1]+
				".\nAtaque/Defensa: "+informacion[2]+"/"+informacion[3]+
				".\nCaracteristicas: "+informacion[4]+
				".\nHistoria: "+informacion[5]+
				".\nNivel: "+informacion[6]
				);
	}

    /** Genera el texto de la carta para la vista en modo compacto
     * @return La informaci�n de la carta formateada para mostrarla al usuario en formato compacto
     */
	public String toShortString(){
		return ("Nombre: "+ informacion[0]+
				".\nRaza: "+informacion[1]+
				".\nAtaque/Defensa: "+informacion[2]+"/"+informacion[3]);
	}
	
    /** Compara esta carta con la pasada como par�metro
     *@param o Es la carta a comparar
     *@return true si son la misma carta y false en caso contrario
     */
    public boolean equals(Object o){
        return (((CartaMovil)o).getCodigoID()==codigoID);
    }
}
