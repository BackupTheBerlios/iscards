/** Este paquete contiene las clases necesarias para gestionar la información de una baraja almacanada en un dispositivo móvil
 */
package MazoMovil;

import java.util.Hashtable;
import java.util.Enumeration;
import java.lang.Integer;

/**Esta clase maneja un conjunto de cartas del tipo CartaMovil y permite la sincronización y la coherencia
 *de los datos almacenados en registros persistentes y los manejados en la aplicación
 */
public class BarajaMovil{
	/**Es el conjunto de las cartas que forman la baraja
    */
	private Hashtable baraja;
	/**Es el encargado de leer, y actualizar los registros persistentes
    */
	private GestorRMS gestor;
    /**Es la versión de esta baraja. Corresponde a la versión de la carta más moderna
    */
    private int version;
    /**Este atributo guarda el número de cartas que hay en la baraja (no el número de cartas distintas)
     */
    private int numeroCartas;
    private String raza;
    private String propietario;
	
	/** Es el contructor de la nueva baraja.
     *<p>Según el valor del parámetro creará una baraja vacía o una con los valores almacenados en los registros persistentes.
     *Esto es debido a que si la aplicación está intentando recibir los datos desde el PC, no habrá ninguna carta almacenada en
     *los registros persistentes
     *@param vacia Indica si debe crear una baraja vacía o formada por las cartas almacenadas en el dispositivo móvil
     */
	public BarajaMovil(boolean vacia,String raza,String propietario){
            gestor=new GestorRMS();
            version=-1;
            if (!vacia)
                try {
                    numeroCartas=gestor.leerNumeroCartas();
                    System.out.println("El numero de cartas es:"+numeroCartas);
                    baraja=gestor.leerBaraja();//Aquí pediria al gestor de RMS que leyera toda la baraja
                    version=gestor.leerVersion();
                    this.raza=gestor.leerRaza();
                    this.propietario=gestor.leerPropietario();
                }
                catch (Exception e){
                    System.out.println("Error al leer las cartas en el constructor de BarajaMovil");
                    System.out.println(e.toString());
                }
            else 
            	baraja=new Hashtable();
                this.raza=raza;
                this.propietario=propietario;
	}

    /**Busca la carta indicada por el parámetro
     *@param codigo Es el código de identificación de la carta buscada
     *@return Devuelve la carta que se buscaba en el caso de estar en la baraja y null en casso contrario
     */
	public CartaMovil buscaCarta(int codigo){
        return ((CartaMovil)baraja.get(new Integer(codigo)));
    }
    
    /**Este método es el accesor del atributo numeroCartas
     *@return el número de cartas esta baraja
     */
    public int getNumeroCartas(){
        return numeroCartas;
    }

    /** Añade una nueva carta a la baraja y de los registros presistentes.<p>
     *Si una carta igual ya está en la baraja aumenta la cardinalidad de la carta pero no añade otro objeto a la Hashtable (más que nada porque no se puede ;-p)
     *@param nueva Es la carta que se va a añadir a la baraja
     */
    public void añadeCarta(CartaMovil nueva){
        try{
            if ((nueva.getRaza().equals(raza)) || (nueva.getNivel().equals("3"))){
                Integer codigo=new Integer(nueva.getCodigoID());
                if (baraja.containsKey(codigo)){
                    ((CartaMovil)baraja.get(codigo)).aumentaNumero();
                }
                else {
                    baraja.put(new Integer(nueva.getCodigoID()),nueva);
                    if (nueva.getversionBaraja()>version) 
                        version=nueva.getversionBaraja();
                }
                numeroCartas++;
                gestor.guardarBaraja(this);                
            }
        } catch (Exception e){
            System.out.println("Error al añadir la carta en añadeCarta");
            e.printStackTrace();
        }
    }

    /**Quita la carta especificada de la baraja y los registros persistentes teniendo en cuenta que puede estar repetida
     *@param codigo El código de la carta a eliminar
     */
    public void eliminaCarta(int codigo){
        try{

            Integer cod=new Integer(codigo);
            if (((CartaMovil)baraja.get(cod)).disminuyeNumero())
                baraja.remove(new Integer(codigo));
            numeroCartas--;
            gestor.guardarBaraja(this);
        } catch(Exception e){
            System.out.println("Error al eliminar la carta en eliminaCarta");
            e.printStackTrace();
        }
    }
    /** Accesor del atributo version
     * @return Devuelve la versión de baraja
     */        
    public int getversionBaraja(){
        return version;
    }
    
    public String getRaza(){
        return raza;
    }
    
    public String getPropietario(){
        return propietario;
    }

    /**Devuelve una enumeración (el equivalente de J2ME al iterator de Java) de las cartas de la baraja.
     *<p>Si la carta está repetida solo devuelve un objeto CartaMovil con el atributo cantidad al valor apropiado.
     *@return todas las cartas de la baraja. Si están repetidas <code>cartas.getCantidad()>1</code>
     */
    public Enumeration enumeraCartas(){
        return baraja.elements();
    }
    
    public void muestra(){
        Enumeration e=baraja.elements();
        CartaMovil c;
        while (e.hasMoreElements()){
            c=((CartaMovil)e.nextElement());
            System.out.println("de esta hay "+c.getCantidad()+"  "+c.toShortString());
        }
        System.out.println("****************************************************************");
    }
    
        
}
