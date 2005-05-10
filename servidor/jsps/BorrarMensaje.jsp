<%@ page language="java" import="java.util.*, genesis.foro.*" %>

<% if (session.getAttribute("modoAdministrador") == null) { %>
   <jsp:forward page="Foro.jsp"/>
<% } %>
<html>
<head><link rel="STYLESHEET" type="text/css" href="Centro_data/genesis.css">
<title>Borrar Mensaje</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>
<body background="imagenes/fondo222.jpg">
<h1><center>
    <font color="#FFFFFF">Foro - Borrar Mensaje</font>
  </center>
</h1>
<p align="center">Borrar mensaje: <%= request.getParameter("idmensaje") %></p>

<%
	     MensajesBD mensajesBD = MensajesBD.getGestorMensajes();

	     System.out.println("IDMENSAJE VALE: \"" + request.getParameter("idmensaje") + "\"");
	     int idMensaje = Integer.parseInt(request.getParameter("idmensaje"));
	     
	     if (mensajesBD.borrarMensaje(idMensaje)) {
%>
                <center>
                    <h2><font color="#337733">Mensaje borrado</font></h2>
                </center>
<% } else { %>
                <center>
                    <h2><font color="#cc0000">No se ha podido borrar el mensaje</font></h2>
                </center>
<% } %>
        <hr>
        <center>
            <b><a href="Foro.jsp">Volver al Foro</a></b>
        </center>
    </body>
</html>
