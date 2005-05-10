<%@ page language="java" import="java.util.*, genesis.foro.*" %>
<html>
<head><link rel="STYLESHEET" type="text/css" href="Centro_data/genesis.css">
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>
<body background="imagenes/fondo222.jpg">

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
		ArrayList temas = TemasBD.getGestorTemas().getTemas2();		
		int indiceTema;
		if (temas.size() == 0) {
		   indiceTema = 1;
		} else {
		   indiceTema = ((Tema)temas.get(temas.size() - 1)).getIdTema() + 1;
		}
		Tema tema = new Tema  (
			indiceTema,
            request.getParameter ("titulo"),
			0);
        int rowsAffected = TemasBD.getGestorTemas().insertarTema(tema);
		
	       	       
	       ArrayList mensajes = MensajesBD.getGestorMensajes().getMensajes2();				
	       int indiceMensaje;
	       if (mensajes.size() == 0) {
		  indiceMensaje = 1;
	       } else {
		  indiceMensaje = ((Mensaje)mensajes.get(mensajes.size() - 1)).getIdMensaje() + 1;
	       }
		String ant_texto = request.getParameter("texto");
		FiltroMensajes filtro = new FiltroMensajes();
	    String texto = filtro.filtrar(ant_texto);
		Mensaje mensaje = new Mensaje  (
			indiceMensaje,
			(String)session.getAttribute("nickReg"),
             texto);
		int rowsAffected2 = MensajesBD.getGestorMensajes().insertarMensaje(mensaje,tema.getIdTemaString());
		
		if (rowsAffected == 2){
			if (rowsAffected2 == 3){	            
%>
				<center>
                    <h2 class="texto5">Se ha añadido a la Base de Datos</h2>
                </center>
<%
            }
            else {
%>
				<center>
                    <h2 class="texto2">No se ha podido añadir el mensaje</h2>
                </center>
<%
            }
		}
		else{
%>
			<center>
            	<h2 class="texto2">No se ha podido añadir el tema</h2>
            </center>
<%
		}
%>
        <hr>
        <center>
            <b class="titulo2"><a href="Foro.jsp?indice=0">Volver al Foro</a></b>
        </center>
    </body>
</html>
