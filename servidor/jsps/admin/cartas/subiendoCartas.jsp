<%@page import="genesis.cartas.*, java.util.*, org.apache.commons.fileupload.*, java.io.*" %>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="subirCartas.css"/>
	        <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
		<title>Noticias</title>
	</head>
	<body>
	
		<!-- Cabecera -->
	
		<table class="cabecera" width="100%">
				 <tr>
		  		 <td class="titulo">
				   <p class="titulo" align="center">
					A&ntilde;adir/Modificar cartas
				   </p>
				 </td>
			 </tr>
		</table>

	
	       <% 
	       
		 ArrayList listaCartas = (ArrayList) session.getAttribute("listaCartas"); 
	       %>
		
		<br/> 
			<table width="50%" align="center">
			<%
			   Iterator it = listaCartas.iterator();
			   String verbo = "";
			   boolean error = false;
			   while (it.hasNext()) {
			      Carta actual = (Carta)it.next();
			      String codigo = request.getParameter(actual.getCodigo());
			      
			      if (codigo != null && codigo.equals("on")) {
				      try {
					 if (GestorCartas.getGestorCartas().getMedianteID(actual.getCodigo()) != null) {
					    verbo = "Actualizar";
					    GestorCartas.getGestorCartas().actualizarCarta(actual);
					 } else {
					    verbo = "A&ntilde;adir";
					    GestorCartas.getGestorCartas().añadirCarta(actual);
					 } 
				      } catch (Exception e) {
					 error = true;
				      }

			      
			%>
			
			    <tr>
				<td class="cuerpo_sin_puntos" width="100%">
					<%= actual.getCodigo() %> - <%= verbo %> <%= actual.getNombre() %>
				</td>
				<th class="encabezado">
				    <%= (error ? "ERROR" : "OK") %>
				</th>
			    </tr>
			<% 	} 
			    } %>
			</table>
			  
		<p align="center" class="descripcion"><a href="subirCartas.html">Volver a a&ntilde;adir</a></p>

	</body>
</html>
