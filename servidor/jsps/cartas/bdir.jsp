<html>
<head>
 <%@ page import="genesis.cartas.*, java.util.*" %>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
 <link rel="STYLESHEET" type="text/css" href="../Centro_data/genesis.css">
</head>
<%!
      private String seleccionarDefecto(String seleccionada, String raza) {
	 if (seleccionada == null) 
	    return "";
	 else if (seleccionada.equals(raza))
	    return "SELECTED";
	 else
	    return "";
      }
   %>

<body bgcolor="#FFFFFF" text="#000000" background="../imagenes/fondo222.jpg">
<h2 id="directa" align="center" class="titulo4">Búsqueda directa</h2>
<p class="texto3" align="center">Empieza seleccionando la raza:</p>
	 <% String raza = request.getParameter("raza"); %>
         <form action="bdir.jsp#directa" name="formRaza" method="GET">
	    <p align="center">
	       <select name="raza" class="texto1">
		  <option value="Ángeles" class="texto1"<%= seleccionarDefecto(raza, "Ángeles") %>>Ángeles</option>
		  <option value="Demonios" class="texto1"<%= seleccionarDefecto(raza, "Demonios") %>>Demonios</option>
		  <option value="Humanos" class="texto1"<%= seleccionarDefecto(raza, "Humanos") %>>Humanos</option>
		  <option value="Inmortales" <%= seleccionarDefecto(raza, "Inmortales") %>>Inmortales</option>
	       </select>
	       <input type="submit" class="texto1" value="Seleccionar"/>
	 </form>
	 </p>
	 <% if (raza != null) { 
	       String color = new String("#000000");
	       if (raza.equals("Ángeles")) {
		  color = "#CCCCCC";	  
	       } else if (raza.equals("Demonios")) {
		  color = "#800000";
	       } else if (raza.equals("Humanos")) {
		  color = "#008000";
	       } else if (raza.equals("Inmortales")) {
		  color = "#500090";
	       }	   
	 %>
	    
	    
<p class="texto3" align="center">Y ahora selecciona una carta de la raza <b><%= raza %></b>:</p>
	    <form action="MostrarCarta.jsp" method="GET" name="formCarta">
		<p align="center">
	       <select name="carta" class="texto1">
	       <% HashMap hashMap = GestorCartas.getGestorCartas().getIDs(raza);
		  Iterator it = hashMap.entrySet().iterator();
		  while (it.hasNext()) { 
		     Map.Entry entradaActual = (Map.Entry) it.next(); %>
			<option value="<%= entradaActual.getKey() %>">
			   <%=entradaActual.getKey() %> - <%= entradaActual.getValue() %>
			</option>
	       <% } %>
	       </select>
	       <input type="submit" name="seleccionarCarta" class="texto1" value="Buscar" />
		   </p>
	    </form>
	 <% } %>
	 

	 
	    
<h2 id="codigo" align="center" class="titulo4">&nbsp;</h2>
</body>
</html>
