<%@page import="java.util.*,genesis.noticias.*" %>

<html>
   <head>
		<link rel="stylesheet" type="text/css" href="mostrarNoticias.css"/>
		<title>Noticias</title>
   </head>
   <body>
			
		<table class="cabecera" width="100%">
				 <tr>
		  		 <td class="titulo">
				   <p class="titulo" align="center">
				   	Borrar noticias
				   </p>
				 </td>
			 </tr>
		</table>

		<p>&nbsp;</p>
	       
		<table width="50%" align="center">
	   
		<%
		  String[] param = request.getParameterValues("id");
		  if (param == null) {
		     %><p class="descripcion" align="center">No se ha marcado ninguna noticia!</p> <%
		  } else {

		  for (int i = 0; i < param.length; i++) {
	       %>
		     <tr> <td class="noticia" width="100%">
	       <%
		     int indice = Integer.parseInt(param[i]);
	        %>
		     Borrando noticia: <%= indice %></td>
		     <th class="encabezado">
	       <%
		     try {
			GestorNoticias.getGestorNoticias().borrarNoticia(indice);
			out.println("OK");
		     } catch (Exception e) {
			out.println("Error");
		     }
	       %>
		     </th></tr>
		   
	        <%
		  }
		  }
		%>
		   
		</table>

	        <p align="center"><a href="noticias.jsp">Volver a la lista de noticias</a></p>	


      
   </body>
</html>
