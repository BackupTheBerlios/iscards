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
      <title>P�gina principal</title>
      <link rel="STYLESHEET" type="text/css" href="../Centro_data/genesis.css">
   </head>
   <body background="../imagenes/fondo222.jpg">  
	 
<h1 align="center" class="titulo">B�SQUEDA DE CARTAS</h1>
	 <ul>
	    <li><a href="BuscarCartas.jsp#directa">B�squeda directa</a></li>
	    <li><a href="BuscarCartas.jsp#codigo">B�squeda por c�digo</a></li>
	    <li><a href="BuscarCartas.jsp#nombre">B�squeda por nombre</a></li>
	 </ul>
	 
	 <!-- B�squeda inmediata --> 
	 
<h2 id="directa" class="titulo4">B�squeda directa</h2>
<p class="texto3">Empieza seleccionando la raza:</p>
	 <% String raza = request.getParameter("raza"); %>
         <form action="BuscarCartas.jsp#directa" name="formRaza" method="GET">
	    <p>
	       <select name="raza" class="texto1">
		  <option value="�ngeles" class="texto1"<%= seleccionarDefecto(raza, "�ngeles") %>>�ngeles</option>
		  <option value="Demonios" class="texto1"<%= seleccionarDefecto(raza, "Demonios") %>>Demonios</option>
		  <option value="Humanos" class="texto1"<%= seleccionarDefecto(raza, "Humanos") %>>Humanos</option>
		  <option value="Inmortales" <%= seleccionarDefecto(raza, "Inmortales") %>>Inmortales</option>
	       </select>
	       <input type="submit" class="texto1" value="Seleccionar"/>
	 </form>
	 </p>
	 <% if (raza != null) { 
	       String color = new String("#000000");
	       if (raza.equals("�ngeles")) {
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
	 
	 <!-- B�squeda por c�digo -->
	 <hr/>
	 
	    
<h2 id="codigo" class="titulo4">B�squeda por c�digo</h2>
<p class="texto3">Si te sabes el c�digo de la carta, introd�celo en el 
  siguiente campo:</p>
<p> 
<form action="MostrarCarta.jsp" method="GET" name="busquedaCodigo" class="texto3">
  C�digo: 
  <input type="text" name="carta"/>
		  <input type="submit" name="buscarPorCodigo" value="Buscar"/ class="texto1">
		  <br>
		  Ej: H1, A2, etc...
	       </form>
	    </p>

	 <!-- B�squeda por nombre -->
	 <hr/>
	    
<h2 id="nombre" class="titulo4">B�squeda por nombre</h2>
	    
<p> 
<form action="BuscarNombre.jsp" method="GET" name="busquedaNombre" class="texto3">
  Buscar cartas cuyo nombre contenga: 
  <input type="text" name="nombre" />
		  <input type="submit" value="Buscar" / class="texto1">
	       </form>	 
	    </p>	 
   </body>
</html>
