<%@page import="genesis.noticias.*" %>

<html>
   <head>
		<link rel="stylesheet" type="text/css" href="mostrarNoticias.css"/>
		<title>Noticias</title>
   </head>
   <body>
	       <%
		  String idCad = (String)request.getParameter("id");
		  int id = Integer.parseInt(idCad);

		  Noticia noticia = GestorNoticias.getGestorNoticias().getMedianteId(id);
		  

	       %>	       
   
	       <!-- Cabecera -->
			
		<table class="cabecera" width="100%">
				 <tr>
		  		 <td class="titulo">
				   <p class="titulo" align="center">
				   	Borrar:
					<font color="#884400"><%= noticia.getTitulo() %></font>
				   </p>
				   
				 </td>
			 </tr>
		</table>

		<p>&nbsp;</p>

		<table>
		     <tr>
			<th class="encabezado">T&iacute;tulo</th>
			<td class="noticia" width="100%"><%= noticia.getTitulo() %></td>
		     </tr>
		     <tr>
			<th class="encabezado">Tipo</th>
			<td class="noticia" width="100%"><%= noticia.getTipo() %></td>
		     </tr>
		     <tr>
			<th class="encabezado">Fecha</th>
			<td class="noticia" width="100%"><%= noticia.getFecha() %></td>
		     </tr>
		     <tr>
			<th class="encabezado">Descripci&oacute;n</th>
			<td class="noticia" width="100%"><%= noticia.getContenido() %></td>
		     </tr>
		</table>

		<p>&nbsp;</p>

	       <%
		try {
		   GestorNoticias.getGestorNoticias().borrarNoticia(id);
	       %>
	        <p class="correcto" align="center">Se ha borrado la noticia correctamente</p>
	       <% } catch (Exception e) { %>
	        <p class="error" align="center">Error: <%= e.getMessage() %></p>
	       <% } %>
	        <p align="center"><a href="noticias.jsp">Volver a la lista de noticias</a></p>	

      
   </body>
</html>
