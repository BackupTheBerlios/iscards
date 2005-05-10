<%@ page language="java" import="java.util.*, genesis.foro.*" %>
<% if (session.getAttribute("modoAdministrador") == null) { %>
   <jsp:forward page="Foro.jsp"/>
<% } %>
<html><head><link rel="STYLESHEET" type="text/css" href="Centro_data/genesis.css"><title>Untitled Document</title><meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>
<body background="imagenes/fondo222.jpg">
<h1><center>
    <font color="#FFFFFF">Foro -- Insertando un nuevo mensaje</font>
  </center>
</h1>
<%
	String ant_texto = request.getParameter("texto");
	FiltroMensajes filtro = new FiltroMensajes();
	String texto = filtro.filtrar(ant_texto);
%>
<table align="center" cellpadding="2" cellspacing="2" border="1" width="80%">
  <tr> 
    <th><font color="#FFFFFF">Identificador:</font></th>
    <td><font color="#FFFFFF"><%= request.getParameter("idmensaje") %>&nbsp;</font></td>
  </tr>
   
  <tr> 
    <th><font color="#FFFFFF">Mensaje:</font></th>
    <td><font color="#FFFFFF"><%= texto %>&nbsp;</font></td>
  </tr>
</table>
<%

	MensajesBD mensajesBD = MensajesBD.getGestorMensajes(); 

	int idMensaje = Integer.parseInt(request.getParameter("idmensaje")); 

	

	Mensaje antiguoMensaje = mensajesBD.getMensaje(idMensaje);

	Mensaje nuevoMensaje = new Mensaje(antiguoMensaje.getIdMensaje(), 
	       antiguoMensaje.getNick(),
	       texto);
	
            if (mensajesBD.modificarMensaje(nuevoMensaje)) {
%>
                <center>
                    <h2><font color="#337733">Mensaje modificado correctamente</font></h2>
                </center>
<%
            }
            else {
%>
                <center>
                    <h2><font color="#cc0000">No se ha podido Modificar el mensaje</font></h2>
                </center>
<%
            }
%>
        <hr>
        <center>
            <b><a href="Foro.jsp">Volver al Foro</a></b>
        </center>
    </body>
</html>
