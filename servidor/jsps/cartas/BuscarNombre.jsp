<html>
   <%@page import="genesis.cartas.*, java.util.*" %>
   <%
      String nombre = request.getParameter("nombre");
      if (nombre == null || nombre.trim().equals("")) {
   %>
	 <jsp:forward page="BuscarCartas.jsp" />
   <% } %>
   <head>
      <link rel="stylesheet" type="text/css" href="../Centro_data/genesis.css"/> 
      <title>Búsqueda por nombre</title>
   <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>
   
<body background="../imagenes/fondo222.jpg">
<h1 class="titulo4">Búsqueda por nombre</font></h1>
      <% 
	 ArrayList cartas = GestorCartas.getGestorCartas().getMedianteNombre(nombre); 
	 int numResultados = cartas.size();
	 if (numResultados == 0) {
      %>
	    
<p class="texto3"><b>No se han encontrado resultados.</b></p>
      <% } else { 
	       String terminaPlural = (numResultados>1) ? "s" : "";
	       String cadResultados = "" + numResultados + " resultado" +
		     terminaPlural + " encontrado" + terminaPlural + ".";
	    %>
	       <p><b><font color="#FFFFFF"><%= cadResultados %></font></b></p>
	       <p>
		  <table border = "1" width="100%">
	    
	    <%
	       Iterator it = cartas.iterator();
	       while (it.hasNext()) {
		  Carta cartaActual = (Carta)it.next();  
	    %>
		     <tr>
			<%
			   String color = new String("#000000");
			   String raza = cartaActual.getRaza();
			   if (raza.equals("Ángeles")) color = "#606060";
			   else if (raza.equals("Demonios")) color = "#702020";
			   else if (raza.equals("Humanos")) color = "#207020";
			   else if (raza.equals("Inmortales")) color = "#401060";
			%>
			<td bgcolor="<%= color %>">
			   <b class="texto"><%= cartaActual.getCodigo() %></b> - 
			   <a href="MostrarCarta.jsp?carta=<%=cartaActual.getCodigo()%>" class="tabla">
			      <b><%= cartaActual.getNombre() %> </b>
			   <br/>
			   <%= raza %> - <%= cartaActual.getTipo() %>
			   </a> 
			</td>
		     </tr>

	       
	       
	    <% } %>
      <% } %>
      </table>
      <p><a href="BuscarCartas.jsp">Repetir búsqueda</a></p>
      
   </body>
</html>
