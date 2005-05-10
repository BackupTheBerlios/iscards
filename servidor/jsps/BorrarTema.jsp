<%@ page language="java" import="java.util.*, genesis.foro.*" %>

<% if (session.getAttribute("modoAdministrador") == null) { %>
   <jsp:forward page="Foro.jsp"/>
<% } %>
<html>
<head><link rel="STYLESHEET" type="text/css" href="Centro_data/genesis.css">
<title>Abrir Tema</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>
<body background="imagenes/fondo222.jpg">
<h1><center>
    <font color="#FFFFFF">Foro - Borrando tema</font>
  </center>
</h1>
<p align="center">Borrando tema: <%= request.getParameter("idtema") %></p>

<%
             TemasBD temasBD = TemasBD.getGestorTemas(); 

	     int idTema = Integer.parseInt(request.getParameter("idtema"));
	     
	     if (temasBD.borrarTema(idTema)) {
%>
                <center>
                    <h2><font color="#337733">El tema se ha borrado correctamente</font></h2>
                </center>
<% } else { %>
                <center>
                    <h2><font color="#cc0000">No se ha podido borrar el tema</font></h2>
                </center>
<% } %>
        <hr>
        <center>
            <b><a href="Foro.jsp">Volver al Foro</a></b>
        </center>
    </body>
</html>
