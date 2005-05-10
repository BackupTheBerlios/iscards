<html>
   <%@ page import="genesis.cartas.*, java.util.*" %>
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
   <head>
      <title>Página principal</title>
      <link rel="STYLESHEET" type="text/css" href="../Centro_data/genesis.css">
   </head>
   <body background="../imagenes/fondo222.jpg">  
	 
<h1 align="center" class="titulo">BÚSQUEDA DE CARTAS</h1>
	 <ul>
	    <li><a href="BuscarCartas.jsp#directa">Búsqueda directa</a></li>
	    <li><a href="BuscarCartas.jsp#codigo">Búsqueda por código</a></li>
	    <li><a href="BuscarCartas.jsp#nombre">Búsqueda por nombre</a></li>
	 </ul>
	 
	 <!-- Búsqueda inmediata --> 
	 
<h2 id="directa" class="titulo4">Búsqueda directa</h2>
<p class="texto3">Empieza seleccionando la raza:</p>
	 <% String raza = request.getParameter("raza"); %>
         <form action="BuscarCartas.jsp#directa" name="formRaza" method="GET">
	    <p>
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
	    
	    
<p class="texto3">Y ahora selecciona una carta de la raza</font> <b><font color="<%=color%>"><%= raza %></b>:</p>
	    <form action="MostrarCarta.jsp" method="GET" name="formCarta">
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
	    </form>
	 <% } %>
	 
	 <!-- Búsqueda por código -->
	 <hr/>
	 
	    
<h2 id="codigo" class="titulo4">Búsqueda por código</h2>
<p class="texto3">Si te sabes el código de la carta, introdúcelo en el 
  siguiente campo:</p>
<p> 
<form action="MostrarCarta.jsp" method="GET" name="busquedaCodigo" class="texto3">
  Código: 
  <input type="text" name="carta"/>
		  <input type="submit" name="buscarPorCodigo" value="Buscar"/ class="texto1">
		  <br>
		  Ej: H1, A2, etc...
	       </form>
	    </p>

	 <!-- Búsqueda por nombre -->
	 <hr/>
	    
<h2 id="nombre" class="titulo4">Búsqueda por nombre</h2>
	    
<p> 
<form action="BuscarNombre.jsp" method="GET" name="busquedaNombre" class="texto3">
  Buscar cartas cuyo nombre contenga: 
  <input type="text" name="nombre" />
		  <input type="submit" value="Buscar" / class="texto1">
	       </form>	 
	    </p>	 
   </body>
</html>
