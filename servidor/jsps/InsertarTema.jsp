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
		int indiceTema;
		boolean mensajeAnadido = false;
		Tema tema = new Tema(0,			    // Identificador (no se usa)
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
		   Mensaje mensaje = new Mensaje  (
			   0,					  // Identificador (no se usa)
			   (String)session.getAttribute("nickReg"),  // nick del autor
			   texto,					  // Contenido del mensaje
			   new java.sql.Date(new java.util.Date().getTime()),
								     // Fecha
			   indiceTema				  // Tema al que pertenece
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
