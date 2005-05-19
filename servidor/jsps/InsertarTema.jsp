<%@ page language="java" import="java.util.*, genesis.foro.*" %>
<html>
<head><link rel="STYLESHEET" type="text/css" href="file:///C|/hlocal/jakarta-tomcat-5.5.7/webapps/genesis/Centro_data/genesis.css">
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>
<body background="file:///C|/hlocal/jakarta-tomcat-5.5.7/webapps/genesis/imagenes/fondo222.jpg">

        <h1 class="titulo"><center>Foro -- Insertando un nuevo tema</center></h1>
        <table align="center" cellpadding="2" cellspacing="2" border="1" width="80%">
            <tr>
                <th class="texto4">Titulo:</th>
                <td class="texto6"><%= request.getParameter ("titulo") %></td>
            </tr>
	    	<tr>
				<th class="texto4">Texto:</th>
       			<td class="texto6"><%= request.getParameter ("texto") %></td>
	    	</tr>
         </table>
<%
		int indiceTema;
		boolean mensajeAnadido = false;
		ArrayList temas = TemasBD.getGestorTemas().getTemas2();		
		Tema t=(Tema)temas.get(temas.size()-1);
		System.out.println("tema" + t.getIdTema());
		Tema tema = new Tema(t.getIdTema()+1,			   
			request.getParameter ("titulo"),    // Título
			Tema.TEMA_ABIERTO,		    // Estado
			new java.sql.Date(new java.util.Date().getTime()),
							    // Fecha
			0				    // Contador de visitas
		      );
	       indiceTema = TemasBD.getGestorTemas().insertarTema(tema);
		
	        if (indiceTema != -1) {
		   String ant_texto = request.getParameter("texto");
		   FiltroMensajes filtro = new FiltroMensajes();
		   String texto = filtro.filtrar(ant_texto);
		   ArrayList mensajes = MensajesBD.getGestorMensajes().getMensajes2();				
			Mensaje m=(Mensaje)mensajes.get(mensajes.size()-1);
			System.out.println("mensaje" + m.getIdTema());
		   Mensaje mensaje = new Mensaje  (
			   m.getIdMensaje()+1,					
			   (String)session.getAttribute("nickReg"),  // nick del autor
			   texto,					  // Contenido del mensaje
			   new java.sql.Date(new java.util.Date().getTime()),
								     // Fecha
			   t.getIdTema()+1				  // Tema al que pertenece
			);

		   mensajeAnadido = MensajesBD.getGestorMensajes().insertarMensaje(mensaje);
	       }
		
	    if (mensajeAnadido){	            
%>
	    	<center>
                    <h2 class="texto5">Tema y mensaje añadidos correctamente</h2>
                </center>
<%
            }
            else {
	       if (indiceTema != -1) {
%>
				<center>
                    <h2 class="texto2">No se ha podido añadir el mensaje</h2>
                </center>
<%
	       } else {
%>
			<center>
            	<h2 class="texto2">No se ha podido añadir el tema</h2>
            </center>
<%
	       }
	    }
%>
        <hr>
        <center>
            <b class="titulo2"><a href="Foro.jsp?indice=0">Volver al Foro</a></b>
        </center>
    </body>
</html>
