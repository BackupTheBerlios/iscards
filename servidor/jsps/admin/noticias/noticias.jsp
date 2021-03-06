<%@ page import="java.util.*,genesis.noticias.*" %>

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="mostrarNoticias.css"/>
	        <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
		<title>Noticias</title>
	</head>
	<body>
	       <%
		  String numPagina = (String) request.getParameter("pag");
		  if (numPagina == null || numPagina.equals("")) {
		     numPagina = "0";
		  }
		  int numPag = Integer.parseInt(numPagina);
		  int tamPagina = 10;
		  		  
		  int primera = numPag * tamPagina;

		  Collection listaNoticias = GestorNoticias.getGestorNoticias().getNoticias(primera, tamPagina);
		  Iterator it = listaNoticias.iterator();

		  int numNoticias = GestorNoticias.getGestorNoticias().getNumeroNoticias();
		  int numTotalPaginas = ((numNoticias-1) / tamPagina) + 1;
		  
		  boolean pedirConfirmacion = true;
		  boolean confirmarBorrarSeleccionados = true;

		  
	       %>	
	
		<!-- Cabecera -->
	
		<table class="cabecera" width="100%">
				 <tr>
		  		 <td class="titulo">
				   <p class="titulo" align="center">
				   	Configuraci&oacute;n de noticias
				   </p>
				 </td>
			 </tr>
		</table>
	  
		<!-- N�mero de noticias -->
	  
		<p align="center" class="descripcion">N&uacute;mero de noticias: <font class="numeroNoticias">
			<%= numNoticias %></font></p>
	  	
	        <p align="right" class="descripcion"> <b>P&aacute;gina <%=numPag +1%> / <%= numTotalPaginas %></b></p>
		
			
		<p><a href="./nuevaNoticia.html" class="annadir">A&ntilde;adir nueva noticia</a></p>
		<!-- Lista de noticias -->
	       
	        <% if (confirmarBorrarSeleccionados) { %>	
		<form action="borrarSeleccionados.jsp" method="post" onSubmit="return confirm('Se borrar�n TODAS las noticias seleccionadas � Desea Continuar?')">
	       <% } else { %>
		 <form action="borrarSeleccionados.jsp" method="post">
	       <% } %>
			<table width="100%" border="0">
				<tr>
					<th class="encabezado">
						<p align="center">
							Sel.
						</p>
					</th>
					<th class="encabezado">
							Tipo
					</th>
					<th class="encabezado">
							T&iacute;tulo
					</th>
					<th class="encabezado">
							Fecha
					</th>
					<th class="encabezado">
							Borrar
					</th>
				</tr>				
			      <%
				 while (it.hasNext()) {
				    Noticia actual = (Noticia)it.next();
			      %>
				<tr>
				
					<td class="noticia">
						<p align="center">
						<input type="checkbox" name="id" value="<%=actual.getId()%>"/>
						</p>
					</td>
					<td class="noticia">
					     <p align="center" class="descripcion">
					     <%= actual.getTipo() %>
					     </p>
					</td>
					<td class="noticia" width="100%">
						<a href="./editarNoticia.jsp?id=<%=actual.getId()%>" class="noticia">
						   <%= actual.getTitulo() %>
						</a>
					</td>
					<td class="noticia">
						<p class="descripcion">
						   <nobr>
						   <%= actual.getFecha() %>
						   </nobr>
						</p>						
					</td>
					<td class="noticia">
					     <% if (pedirConfirmacion) { %>
					     	<a href="confirmarBorrar.jsp?id=<%=actual.getId() %>" class="noticia">
					     <% } else { %>
					     	<a href="borrarNoticia.jsp?id=<%=actual.getId() %>" class="noticia">
					     <% } %>
											     
							Borrar
						</a>
					</td>
				</tr>
				<%
				    } 
				%>
			</table>	  
			<table width="100%">
			   <tr>
			      <td align="left" width="50%">
				 <% if (numPag > 0) { %>
				    <a href="noticias.jsp?pag=<%= numPag-1 %>">&lt;&lt; Anterior</a>
				 <% } %>
			      </td>
			      <td align="right" width="50%">
				 <% if (numPag < numTotalPaginas-1) { %>
				    <a href="noticias.jsp?pag=<%= numPag+1 %>">Siguiente &gt;&gt;</a>
				 <% } %>
			      </td>
			   </tr>
			</table>
			<input type="submit" value="Borrar seleccionados"/>
		</form>
			  

	</body>
</html>
