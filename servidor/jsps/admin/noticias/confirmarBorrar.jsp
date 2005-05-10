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

		<table align="center">
		  <tr>
		     <th class="encabezado" colspan="2" align="center">
			¿Desea borrar esta noticia?
		     </th>
		  </tr>
		  <tr>
		     <td align="center" class="noticia">
			<a class="annadir" href="borrarNoticia.jsp?id=<%=id%>">S&iacute;</a>
		     </td>
		     <td align="center" class="noticia">
			<a class="annadir" href="noticias.jsp">No</a>
		     </td>
		  </tr>
		</table>
      
   </body>
</html>
