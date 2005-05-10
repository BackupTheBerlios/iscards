<%@ page import="genesis.usuarios.*" %>
<html>
   <head>
      <title>Página principal</title>
      <link rel="STYLESHEET" type="text/css" href="../Centro_data/genesis.css">
   </head>
   <body background="../imagenes/fondo222.jpg">
      <%
	 String nick = (String)session.getAttribute("nickReg");
	 if (nick == null) {
      %>
	    <jsp:forward page="../NoLogeado.htm"/>
      <%
	 } else {
	    UsuariosBD usuariosBD = UsuariosBD.getGestorUsuarios();
	    Usuario actual = usuariosBD.getUsuario(nick);
	    
      %>
	 <table align="center">
	    <tr>
	       <td align="right" class="texto4"><b>Nick de usuario:&nbsp;</b></td>
	       <td><%= actual.getNick() %></td>
	    </tr>
	    <tr>
	       <td align="right" class="texto4"><b>Puntos:&nbsp;</b></td>
	       <td><%= actual.getPuntos() %></td>
	    </tr>
	 </table>

	 <p>&nbsp;</p>
	 <p class="texto">Puedes descargarte cartas pulsando en el enlace indicado.</p>
	 <p class="texto">El coste de un paquete es de <b>1</b> punto</p>
	 <% float puntos = actual.getPuntos();
	 		if (puntos < 1){
	 %>
	 <h3 align="center" class="texto3">No tienes suficientes puntos para descargar un paquete</h3>
	 <%		}
	 		else{
	 %>
	 <h2 align="center"><a href="/genesis/DescargaCartas?nickReg=<%= nick %>">Descargar paquete</a></h2>
      <%	
      		}
	 }
      %>
      
      
   </body>
   
</html>
