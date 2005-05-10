<%@page import="genesis.cartas.*, java.util.*, org.apache.commons.fileupload.*" %>
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
		<br/> 

		<%
		  DiskFileUpload upload = new DiskFileUpload();
		  List elementos = upload.parseRequest(request);

		  FileItem item = (FileItem) elementos.get(0);

		  LectorCartas lectorCartas = new LectorCartas(item.getInputStream());

		  ArrayList cartas = new ArrayList();
		  Carta nueva = lectorCartas.siguienteCarta();
		  while (nueva != null) {
		     cartas.add(nueva);
		     nueva = lectorCartas.siguienteCarta();
		  }

		  session.setAttribute("listaCartas", cartas); 
		
		
		%>

		<p class="descripcion">Se han encontrado <%= cartas.size() %> cartas. Seleccione las que desea subir</p>
		
		<form action="subiendoCartas.jsp" method="post">
			<table width="100%">
			    <tr>
				<th class="encabezado">
					A&ntilde;adir
				</th>
				<th class="encabezado" width="100%">
				    C&oacute;digo - Nombre
				</th>
			    </tr>
			    <%
			      Iterator it = cartas.iterator();
			      while (it.hasNext()) {
				 Carta actual = (Carta) it.next();
			    %>
			    
			    <tr>
			    
				<td class="cuerpo_sin_puntos" align="center">
				    <input type="checkbox" checked name="<%= actual.getCodigo() %>"/>
				</td>
				<td class="cuerpo_sin_puntos">
				    <%= actual.getCodigo() %> - <%= actual.getNombre() %>
				    <% 
				      Carta posible = GestorCartas.getGestorCartas().getMedianteID(actual.getCodigo());
				      if (posible != null) {
				    %>
			    
				       <font class="advertencia"><br/>Advertencia! Ya existe en la base de datos con el nombre: 
				       <i><%= posible.getNombre() %></i><br/></font>
				    <% } %>
				</td>
			    </tr>

			    <% } %>

			    
			</table>
		<p align="center"><input type="submit" value="Aceptar"/></p>
		</form>
			  

	</body>
</html>
