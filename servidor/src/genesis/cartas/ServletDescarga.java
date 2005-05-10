/*
 * ServletDescarga.java 
 *
 * Created on 7 de marzo de 2005, 12:09
 */

package genesis.cartas;

/**
 *
 * @author  usuario_local
 * @version
 */

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class ServletDescarga extends HttpServlet
{

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
		
                ArrayList codigoCartas = new ArrayList();
        
                String filepath="c:/";
		String filename="prueba.paq";
                
                //Cogemos el parámetro q se pasa al servlet
                String nick = request.getParameter("nickReg");
                
		response.setContentType("application/x-download");
                response.setHeader("Content-Disposition","attachment;filename="+filename);
		

		try
		{
                    PrintWriter p = response.getWriter();
                    Random aleatorio = new Random();
                    String cartaElegida;
                   
                                    
                    //Escribimos el nick
                    p.print(nick);
                    
                   
                    // Actualizamos el nº de paquetes
                    genesis.usuarios.UsuariosBD.getGestorUsuarios().incremNPaquetes(nick, 1);
                    p.print(genesis.usuarios.UsuariosBD.getGestorUsuarios().getNPaquetes(nick));
                   
                    //Actualizamos los puntos
                    genesis.usuarios.UsuariosBD.getGestorUsuarios().decrementarPuntos(nick, 1);
                   

		    codigoCartas = GestorCartas.getGestorCartas().getCartasPorNivel(3);
		    for (int i=0; i< 3; i++){
                        cartaElegida = (String) codigoCartas.get(aleatorio.nextInt(codigoCartas.size()));
                        p.print(cartaElegida.length());
                        p.print(cartaElegida);
                    }
                
                    codigoCartas.clear();
                    codigoCartas = GestorCartas.getGestorCartas().getCartasPorNivel(2);
 
                    for (int i=0; i< 3; i++){
                        cartaElegida = (String) codigoCartas.get(aleatorio.nextInt(codigoCartas.size()));
                        p.print(cartaElegida.length());
                        p.print(cartaElegida);
                    }
                
                    /* Las cartas de nivel uno se pueden obtener una de cada 15 veces */
                    int numeroSuerte = aleatorio.nextInt(15);
                    if (numeroSuerte == 10) {
                        codigoCartas.clear();
                        codigoCartas = GestorCartas.getGestorCartas().getCartasPorNivel(1);
                    }
                    else{
                        codigoCartas.clear();
                        codigoCartas = GestorCartas.getGestorCartas().getCartasPorNivel(1);
                    }
                    cartaElegida = (String) codigoCartas.get(aleatorio.nextInt(codigoCartas.size()));
                    p.print(cartaElegida.length());
                    p.print(cartaElegida);
    	}
                
    	// exception
    	catch (Exception xe)
    	{
    		response.setContentType("text/html");

    		PrintWriter out=response.getWriter();
    		out.println(xe.getMessage());

    	}
               

    }
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        this.doGet(request, response);
    }
}


