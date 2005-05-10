<%@ page language="java" import="java.util.*, genesis.foro.*" %>

<% if (session.getAttribute("modoAdministrador") == null) { %>
   <jsp:forward page="Foro.jsp"/>
<% } %>
<html>
<head><link rel="STYLESHEET" type="text/css" href="Centro_data/genesis.css">
<title>Cerrar Tema</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>
<body background="imagenes/fondo222.jpg">
<h1><center>
    <font color="#FFFFFF">Foro - Cerrando tema</font>
  </center>
</h1>
<p align="center">Cerrando tema: <%= request.getParameter("idtema") %></p>

<%
             TemasBD temasBD = TemasBD.getGestorTemas(); 

	     int idTema = Integer.parseInt(request.getParameter("idtema"));
	     
	     if (temasBD.cerrarTema(idTema)) {
%>
                <center>
                    <h2><font color="#337733">Se ha cerrado el tema correctamente</font></h2>
                </center>
<% } else { %>
                <center>
                    <h2><font color="#cc0000">No se ha podido cerrar el tema</font></h2>
                </center>
<% } %>
        <hr>
        <center>
            <b><a href="Foro.jsp">Volver al Foro</a></b>
        </center>
    </body>
</html>
