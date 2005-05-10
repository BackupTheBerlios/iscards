<%@ page language="java" import="java.util.*, genesis.usuarios.*" %>

<html><head><link rel="STYLESHEET" type="text/css" href="../Centro_data/genesis.css"><title>Untitled Document</title><meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>
<body background="../imagenes/fondo222.jpg">
<h1><center>
    <font color="#FFFFFF">Usuarios -- Borrando el usuario </font><%= request.getParameter ("nick") %>
</center></h1>
<%
Usuario usuario = (Usuario) UsuariosBD.getGestorUsuarios().getUsuario(request.getParameter("nick"));
        if (usuario != null){
%>
            <table align="center" cellpadding="2" cellspacing="2" border="1" width="80%">
                <tr>
                    
    <th><font color="#FFFFFF">Nick:</font></th>
                    <td>
                    <%= usuario.getNick () %></td>
                </tr>
                <tr>
                    
    <th><font color="#FFFFFF">Password:</font></th>
                    <td>
                    <%= usuario.getPassword() %></td>
                </tr>
                <tr>
                    
    <th><font color="#FFFFFF">Nombre:</font></th>
                    <td>
                    <%= usuario.getNombre () %></td>
                </tr>
                <tr>
                    
    <th><font color="#FFFFFF">Email:</font></th>
                    <td>
                    <%= usuario.getEmail () %></td>
                </tr>
                <tr>
                    
    <th><font color="#FFFFFF">Sexo:</font></th>
                    <td>
                    <%= usuario.getSexo () %></td>
                </tr>
                <tr>
                    
    <th><font color="#FFFFFF">Puntos:</font></th>
                    <td>
                    <%= usuario.getPuntos () %></td>
                </tr>
            </table>
            <br>
            <center>
                <b><a href="Borrar.jsp?nick=<%= usuario.getNick () %>">Borrar el usuario <%= request.getParameter ("nick") %></a></b>
            </center>
<%
        }
        else {
%>
            <center>
                <h2><font color="#cc0000">No se pudo encontrar el usuario <%= request.getParameter ("nick") %></font></h2>
            </center>
<%
        }
%>
        <hr>
        <center>
            <b><a href="../Centro.jsp">Volver a la Página Inicial [el usuario no se borrará]</a></b>
        </center>
    </body>
</html>
