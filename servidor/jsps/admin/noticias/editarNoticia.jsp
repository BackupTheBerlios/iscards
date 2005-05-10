<%@ page import="genesis.noticias.*" %>
<%!
   private String compruebaIgual(String lista, String elegido) {
      if (lista.equals(elegido)) return "SELECTED";
      else return "";
   }
%>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="mostrarNoticias.css"/>
		<title>Noticias</title>
	</head>
	<body>
	
		<!-- Cabecera -->
		<%
		  String idCad = (String) request.getParameter("id");
		  int id = Integer.parseInt(idCad);
		  
		  Noticia noticia = GestorNoticias.getGestorNoticias().getMedianteId(id);
		 
		  String cont = noticia.getContenido();
		  StringBuffer sb = new StringBuffer();
		  
		  int inicio = 0, indice;
		  while ((indice = cont.indexOf("<br/>")) > -1) {
		     sb.append(cont.substring(0, indice) + "\n");
		     cont = cont.substring(indice + 5, cont.length());
		     inicio = indice + 5;
		  }
		  
		  sb.append(cont);
		  String contenido = sb.toString();
		  
		%>
	
		<table class="cabecera" width="100%">
				 <tr>
		  		 <td class="titulo">
				   <p class="titulo" align="center">
				   	Modificar: <font color="#884400"><%=  noticia.getTitulo() %></font>
				   </p>
				   
				 </td>
			 </tr>
		</table>

		<form action="modificarNoticia.jsp" method="POST">	  	
			<p class="descripcion">
				<b>T&iacute;tulo:</b>
				<input type="text" size="80" maxlength="100" name="titulo" value="<%= noticia.getTitulo() %>"/>
			</p>
			<p class="descripcion">
				Tipo:
				<select name="tipo">
					<option <%= compruebaIgual("Aviso", noticia.getTipo()) %>>Aviso</option>
					<option <%= compruebaIgual("Novedad", noticia.getTipo()) %>>Novedad</option>
					<option <%= compruebaIgual("Información", noticia.getTipo()) %>>Información</option>
				</select>
				
			</p>
			<p class="descripcion">
				Descripci&oacute;n:<br/>
				<textarea name="contenido" ROWS="10" COLS="70"><%= contenido %></textarea>
			</p>

			<input type="hidden" name="fecha" value="<%= noticia.getFecha().getTime() %>"/>
			<input type="hidden" name="id" value="<%= id %>"/>
			
			<input type="submit" value="Modificar"/>
			<input type="reset" value="Borrar"/>
				
		</form>
			  

	</body>
</html>
