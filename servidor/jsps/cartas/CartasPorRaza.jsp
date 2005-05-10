<html>
   <%@ page import="genesis.cartas.*, java.util.*" %>
   <%
      String codigoRaza = request.getParameter("raza");
      HashMap hashRaza = GestorCartas.getGestorCartas().getIDs(codigoRaza);
   %>

   <% if (hashRaza.isEmpty()) { %>
      
      <head>
         <link rel="STYLESHEET" type="text/css" href="../Centro_data/genesis.css">
      </head>
      <body background="../imagenes/fondo222.jpg">
	 <h1><center><br><br><br>ERROR: La raza no existe o no contiene ninguna carta</center></h1>
	 <p><a href="../Centro.jsp">Volver a la página principal</a></p>
      </body>
      
   <% } else { %>

   <head>
      <link rel="STYLESHEET" type="text/css" href="../Centro_data/genesis.css">
   </head>
   <body background="../imagenes/fondo222.jpg">
    <table width="100%">
      <tr>
        <td bgcolor="#808000" class="texto4"><b>Nombre</b></td>
        <td bgcolor="#808000" class="texto4"><b>Tipo</b></td>
        <td bgcolor="#808000" class="texto4"><b>Ataque</b></td>
        <td bgcolor="#808000" class="texto4"><b>Defensa</b></td>
        <td bgcolor="#808000" class="texto4"><b>Coste</b></td>
        <td bgcolor="#808000" class="texto4"><b>Vida</b></td>
        <td bgcolor="#808000" class="texto4"><b>Nivel</b></td>
        <td bgcolor="#808000" class="texto4"><b>Puntos</b></td>
      </tr>
      <% 	Carta carta;
	     int codigo;
	     Iterator it = hashRaza.entrySet().iterator();
	     while (it.hasNext()) {
		Map.Entry entrada = (Map.Entry) it.next();
	        String cod =  entrada.getKey().toString();
            carta = GestorCartas.getGestorCartas().getMedianteID(cod);
      %>
      <tr>
        <td class="texto"><%=carta.getNombre() %></td>
        <td class="texto"><%=carta.getTipo() %></td>
        <td class="texto"><%= carta.getAtaque() %></td>
        <td class="texto"><%= carta.getDefensa() %></td>
        <td class="texto"><%= carta.getCoste() %></td>
        <td class="texto"><%= carta.getVida() %></td>
        <td class="texto"><%= carta.getNivel() %></td>
        <td class="texto"><%=carta.getPuntos()%></td>      
      </tr>
      <% } %>
    </table>
   </body>
   <% } %>
</html>