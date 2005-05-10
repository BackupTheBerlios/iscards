<%@page import="genesis.noticias.*, genesis.foro.FiltroMensajes, java.text.DateFormat" %>

<html>
   <head>
		<link rel="stylesheet" type="text/css" href="mostrarNoticias.css"/>
		<title>Noticias</title>
   </head>
   <body>
	       <%
		  String idCad = (String)request.getParameter("id");
		  int id = Integer.parseInt(idCad);
		  String tituloSinFiltrar = (String)request.getParameter("titulo");
		  String tipo = (String)request.getParameter("tipo");  
		  String contenidoSinFiltrar = (String)request.getParameter("contenido");
		  long fechaTmp = Long.parseLong((String)request.getParameter("fecha"));
		  java.sql.Date fecha = new java.sql.Date(fechaTmp);

		  FiltroMensajes filtro = new FiltroMensajes();
		  String contenido = filtro.filtrar(contenidoSinFiltrar);
		  String titulo = filtro.filtrar(tituloSinFiltrar);

	       %>	       
   
	       <!-- Cabecera -->
			
		<table class="cabecera" width="100%">
				 <tr>
		  		 <td class="titulo">
				   <p class="titulo" align="center">
				   	Modificar:
					<font color="#884400"><%= titulo %></font>
				   </p>
				   
				 </td>
			 </tr>
		</table>

		<p>&nbsp;</p>

		<table>
		     <tr>
			<th class="encabezado">T&iacute;tulo</th>
			<td class="noticia" width="100%"><%= titulo %></td>
		     </tr>
		     <tr>
			<th class="encabezado">Tipo</th>
			<td class="noticia" width="100%"><%= tipo %></td>
		     </tr>
		     <tr>
			<th class="encabezado">Fecha</th>
			<td class="noticia" width="100%"><%= fecha %></td>
		     </tr>
		     <tr>
			<th class="encabezado">Descripci&oacute;n</th>
			<td class="noticia" width="100%"><%= contenido %></td>
		     </tr>
		</table>

		<p>&nbsp;</p>
	       <%
		try {
		   Noticia nueva = new Noticia(id, titulo, tipo, contenido, fecha);
		   GestorNoticias.getGestorNoticias().actualizarNoticia(nueva);
	       %>
	        <p class="correcto" align="center">Se ha modificado la noticia correctamente</p>
	       <% } catch (Exception e) { %>
	        <p class="error" align="center">Error: <%= e.getMessage() %></p>
	       <% } %>
	        <p align="center"><a href="noticias.jsp">Volver a la lista de noticias</a></p>	
      
   </body>
</html>
