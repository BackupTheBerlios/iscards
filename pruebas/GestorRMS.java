/*
 * GestorRMS.java
 *
 * Created on 15 de diciembre de 2004, 17:07
 */
package MazoMovil;

import java.util.Hashtable;
import java.util.Enumeration;
import java.io.*;
import javax.microedition.rms.*;
/**
 *
 * @author  Javi
 */
public class GestorRMS {
    
    private RecordStore rs;
    private Hashtable bar;
    private int version;
    private String raza;
    
    /** Creates a new instance of GestorRMS */
    public GestorRMS() {
    }
    
    public int leerNumeroCartas() throws Exception{
        rs=RecordStore.openRecordStore("baraja",false);
        byte[] registro=new byte[500];
        ByteArrayInputStream bais=new ByteArrayInputStream(registro);
        DataInputStream dis=new DataInputStream(bais);
        registro=rs.getRecord(1);
        int nCartas=dis.readInt();
        System.out.println("Las cartas q lee el metodo son:"+nCartas);
        bais.reset();
        dis.close();
        bais.close();
        rs.closeRecordStore();
        return nCartas;
    }
    
    public int leerVersion() throws Exception{
        rs=RecordStore.openRecordStore("baraja",false);
        byte[] registro=new byte[500];
        ByteArrayInputStream bais=new ByteArrayInputStream(registro);
        DataInputStream dis=new DataInputStream(bais);
        registro=rs.getRecord(2);
        int version=dis.readInt();
        bais.reset();
        dis.close();
        bais.close();
        rs.closeRecordStore();
        return version;
    }
    
    public String leerRaza() throws Exception{
        rs=RecordStore.openRecordStore("baraja",false);
        byte[] registro=new byte[500];
        ByteArrayInputStream bais=new ByteArrayInputStream(registro);
        DataInputStream dis=new DataInputStream(bais);
        registro=rs.getRecord(3);
        String raza=dis.readUTF();
        bais.reset();
        dis.close();
        bais.close();
        rs.closeRecordStore();
        return raza;
    }
    
    public String leerPropietario() throws Exception{
        rs=RecordStore.openRecordStore("baraja",false);
        byte[] registro=new byte[500];
        ByteArrayInputStream bais=new ByteArrayInputStream(registro);
        DataInputStream dis=new DataInputStream(bais);
        registro=rs.getRecord(4);
        String propietario=dis.readUTF();
        bais.reset();
        dis.close();
        bais.close();
        rs.closeRecordStore();
        return propietario;
    }
    
    
    public void guardarBaraja(BarajaMovil b) throws Exception{
        try{
            RecordStore.deleteRecordStore("baraja");
        }catch (Exception e){System.out.println("PETA AL BORRAR");}
        rs=RecordStore.openRecordStore("baraja",true);
        guardaNumeroCartas(b.getNumeroCartas());
        guardaVersionBaraja(b.getversionBaraja());
        guardaRaza(b.getRaza());
        guardaPropietario(b.getPropietario());

        CartaMovil c;
        int i,j;
        String[] inf=new String[7];
        Enumeration e=b.enumeraCartas();
        while (e.hasMoreElements()){
            c=((CartaMovil)e.nextElement());
            for (i=0;i<c.getCantidad();i++)
                guardaCarta(c);
        }
        rs.closeRecordStore();
        
    }
    
    
    public Hashtable leerBaraja() throws Exception{
        int nCartas=leerNumeroCartas();
        raza=leerRaza();
        version=leerVersion();
        rs=RecordStore.openRecordStore("baraja",false);
        byte[] registro=new byte[500];
        ByteArrayInputStream bais=new ByteArrayInputStream(registro);
        DataInputStream dis=new DataInputStream(bais);
        bar=new Hashtable(nCartas);
        int j,codID,vCarta;
        String[] info=new String[7];
        CartaMovil c;
        
        for (int i=0;i<nCartas;i++){
            registro=rs.getRecord(+i);
            codID=dis.readInt();
            vCarta=dis.readInt();
            for (j=0;j<7;j++)
                info[j]=dis.readUTF();
            c=new CartaMovil(codID,vCarta,info);
            añadirABaraja(c);    
        }
        rs.closeRecordStore(); 
        Enumeration e=bar.elements();
        System.out.println("//////////////////////////////////////////////////////////////");
        while (e.hasMoreElements()){
            c=((CartaMovil)e.nextElement());
            System.out.println("de esta hay "+c.getCantidad()+"  "+c.toShortString());
        }
        System.out.println("//////////////////////////////////////////////////////////////");
           
        
        return bar;
        

        
        /*Hashtable b=new Hashtable();
        String[] inf=new String[7];
        inf[0]="Julandras";
        inf[1]="Marikitas";
        inf[2]="8";
        inf[3]="3";
        inf[4]="Es gay";
        inf[5]="vio a su madre desnuda en la ducha de pequeño";
        inf[6]="2";        
        CartaMovil c1=new CartaMovil(1,1,inf);
        inf[0]="pepito";
        inf[1]="bombero-torero";
        inf[2]="2";
        inf[3]="7";
        inf[4]="va de pueblo en pueblo con su espectaculo";
        inf[5]="quiso ser como jesulin";
        inf[6]="3";
        CartaMovil c2=new CartaMovil(2,1,inf);
        inf[0]="C3PO";
        inf[1]="androide";
        inf[2]="4";
        inf[3]="7";
        inf[4]="es bantante cansino";
        inf[5]="le acompaña un enano cabezon que silva";
        inf[6]="3";
        CartaMovil c3=new CartaMovil(3,1,inf);
        inf[0]="Jesús Vázquez";
        inf[1]="Marikitas";
        inf[2]="14";
        inf[3]="9";
        inf[4]="hace muchas cosas como presentar programas de la tele, llegó a sacar un disco... y además en un icono entre los maricas españoles";
        inf[5]="esta es la prueba definitiva del modo extendido, tengo que probar si dibuja bien los limites de la pantalla y para eso hace falta escribir mucho. Sin tele y sin cerveza homer pierde la cabeza, sin tele y sin cerveza homer pierde la cabeza";
        inf[6]="1";        
        CartaMovil c4=new CartaMovil(4,1,inf);
        c1.aumentaNumero();
        b.put(new Integer(c1.getCodigoID()),c1);
        b.put(new Integer(c2.getCodigoID()),c2);
        b.put(new Integer(c4.getCodigoID()),c4);
        b.put(new Integer(c3.getCodigoID()),c3);
        return b;*/
    }

    private void añadirABaraja(CartaMovil nueva){
        Integer codigo=new Integer(nueva.getCodigoID());
        if (bar.containsKey(codigo)){
            ((CartaMovil)bar.get(codigo)).aumentaNumero();
        }
        else {
            bar.put(new Integer(nueva.getCodigoID()),nueva);
            if (nueva.getversionBaraja()>version) 
                version=nueva.getversionBaraja();
        }
        CartaMovil c;
        Enumeration e=bar.elements();
        while (e.hasMoreElements()){
            c=((CartaMovil)e.nextElement());
            System.out.println("de esta hay "+c.getCantidad()+"  "+c.toShortString());
        }        
    }
    
    private void guardaNumeroCartas(int n) throws Exception{
        byte[] registro;
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(baos);
        dos.writeInt(n);
        dos.flush();
        registro=baos.toByteArray();
        rs.addRecord(registro,0,registro.length);
        dos.close();
        baos.close();
    }

    private void guardaVersionBaraja(int v) throws Exception{
        byte[] registro;
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(baos);
        dos.writeInt(v);
        dos.flush();
        registro=baos.toByteArray();
        rs.addRecord(registro,0,registro.length);
        dos.close();
        baos.close();
    }    
    
    private void guardaRaza(String r) throws Exception{
        byte[] registro;
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(baos);
        dos.writeUTF(r);
        dos.flush();
        registro=baos.toByteArray();
        rs.addRecord(registro,0,registro.length);
        dos.close();
        baos.close();        
    }

    private void guardaPropietario(String p) throws Exception{
        byte[] registro;
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(baos);
        dos.writeUTF(p);
        dos.flush();
        registro=baos.toByteArray();
        rs.addRecord(registro,0,registro.length);
        dos.close();
        baos.close();        
    }
    
    private void guardaCarta(CartaMovil c) throws Exception{
        byte[] registro;
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        DataOutputStream dos=new DataOutputStream(baos);
        String[] inf;
        int j;
        dos.writeInt(c.getCodigoID());
        dos.writeInt(c.getversionBaraja());
        inf=c.getInfoCompleta();
        for (j=0;j<7;j++)
            dos.writeUTF(inf[j]);
        dos.flush();
        registro=baos.toByteArray();
        rs.addRecord(registro,0,registro.length);        
        dos.close();
        baos.close();        

    }
    
    
    public void añadirCarta(CartaMovil nueva) throws Exception{
        Integer codigo=new Integer(nueva.getCodigoID());
        if (bar.containsKey(codigo)){
            ((CartaMovil)bar.get(codigo)).aumentaNumero();
        }
        else {
            bar.put(new Integer(nueva.getCodigoID()),nueva);
            if (nueva.getversionBaraja()>version) 
                version=nueva.getversionBaraja();
        }
    }

    public void eliminarCarta(int codigo) throws Exception{
        Integer cod=new Integer(codigo);
        if (((CartaMovil)bar.get(cod)).disminuyeNumero())
            bar.remove(new Integer(codigo));
    }
}
