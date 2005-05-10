<html>
   <%@ page import="genesis.cartas.*, java.io.*" %>
   <%
      String codigo = request.getParameter("carta");
      Carta carta;
      if (codigo == null) {
   %>
	 <jsp:forward page="BuscarCartas.jsp" />
   <%
      } else {
	 carta = GestorCartas.getGestorCartas().getMedianteID(codigo);
      }
   %>

   <% if (carta == null) { %>
      
      <head>
	 <title>Carta no encontrada</title>
         <link rel="STYLESHEET" type="text/css" href="../Centro_data/genesis.css">
      </head>
      <body background="../imagenes/fondo222.jpg">
<h1><font color="#FFFFFF">Carta no encontrada</font></h1>

<p><font color="#FFFFFF"><a href="BuscarCartas.jsp">Repetir la búsqueda</a></font></p>
      </body>
      
   <% } else { %>
      <head>
	 <title>Información sobre <%= carta.getNombre() %> </title>
         <link rel="STYLESHEET" type="text/css" href="../Centro_data/genesis.css">
      </head>
	 <body background="../imagenes/fondo222.jpg">
	 
	 
	    <%
	       String color = new String("#000000");
	       String raza = carta.getRaza();
	       String tipo = carta.getTipo();
	       String no_disponible = null;
	       if (raza.equals("Ángeles")) { color = "#606060"; no_disponible = "Angeles_no_disponible.jpg"; }
	       else if (raza.equals("Demonios")) { color = "#702020"; no_disponible = "Demonios_no_disponible.jpg"; }
	       else if (raza.equals("Humanos")) { color = "#207020"; no_disponible = "Humanos_no_disponible.jpg"; }
	       else if (raza.equals("Inmortales")) { color = "#401060"; no_disponible = "Inmortales_no_disponible.jpg"; }
	       String filepathImg = "./imagenes/" + carta.getCodigo() + ".jpg";
	       String filepath = "c:/WebGenesis/webapps/genesis/cartas/imagenes/" + carta.getCodigo() + ".jpg";
	       File f = new File(filepath);
	    %>
	    <p align="center"><a href="BuscarCartas.jsp">Repetir búsqueda</a></p>
	    <p>&nbsp;</p>
	 
	    <table border="1" width="60%" align="center">
	    <tr>
	    <!-- Nombre de la carta -->
	    <td bgcolor="<%=color%>" colspan="2" align="center">
	    <font size="6"><b style="font-family: Book Antiqua;color:#FFCC00" ><%=carta.getNombre() %></b></font>
	    <font color="#FFCC00"><br/><%= raza %> - <%= tipo %></font>
	    </tr>
	    
	    <!-- Características -->
	    <tr>
	    <td bgcolor="<%= color%>" align="center">
	    
	    <font color="#FFCC00">
	    <% if (tipo.equals("Conjuro") || (tipo.equals("Hechizo"))) { %>
	      <p><br/>&nbsp;<b>Coste: </b> <%= carta.getCoste() %></p>
	    <% } else  if (tipo.equals("Criatura")) { %>
	      <p>
		 &nbsp;<font color="#FFCC00"><b>Ataque: </b> <%= carta.getAtaque() %></font>
		 <br/>&nbsp;<font color="#FFCC00"><b>Defensa: </b> <%= carta.getDefensa() %></font>
		 <br/>&nbsp;<font color="#FFCC00"><b>Coste: </b> <%= carta.getCoste() %></font>
		 <br/>&nbsp;<font color="#FFCC00"><b>Vida: </b> <%= carta.getVida() %></font>
	       </p>

	    <% } %>
	       <p><br/> 
		 &nbsp;<b>Nivel: </b> <%= carta.getNivel() %>
	       </p>
	       <p>
		  &nbsp;<font size="5"><b>Puntos: </b><%=carta.getPuntos()%></font>
	       </p>
           
	    </font>

	 <!-- Descripción -->
	    <p>&nbsp;</p>
	    <p class="carta">

	 <%
	    String[] cadenas = carta.getDescripMov().split("\"");
	    if (cadenas.length > 0) {
	       out.println(cadenas[0]);
	    }

	    if (cadenas.length > 1) {
	       out.println("</p><p class=\"carta\"><i>&quot;" + cadenas[1] + "&quot;</i>");
	    }
	 %>
	    </p>

	    </td>

	    <!-- Imagen -->
	    <td>
	    
       	<% 	if (f.exists()){   	%>	
	    <p><img border="0" src="<%=filepathImg%>" width="311" height="444"></td></tr>
	    <%	}
	    	else{%>
	    <p2><img border="0" src="./imagenes/<%=no_disponible%>" width="311" height="444"></td></tr>
	    <% 	} %>

       

      
	    
	    </table>
	 </body>
	 
   <% } %>
</html>
