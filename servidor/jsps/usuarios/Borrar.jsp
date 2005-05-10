<%@ page language="java" import="java.util.*, genesis.usuarios.*" %>
<html>
<head>
<link rel="STYLESHEET" type="text/css" href="../Centro_data/genesis.css">
<title>Usuarios -- Borrando un usuario</title>
</head>
<body background="../imagenes/fondo222.jpg">
<h1>
<center>
    <p class="titulo">Usuarios -- Borrando el usuario <%= request.getParameter ("nick") %></p>
</center>
</h1>
<%
UsuariosBD usuariosBD = (UsuariosBD) application.getAttribute ("BaseDatosUsuarios");
        Usuario usuario = (Usuario) usuariosBD.getUsuario (request.getParameter ("nick"));
        if (usuario != null){
%>

         <table align="center" cellpadding="2" cellspacing="2" border="1" width="80%" >
            <tr>
                
    <th><font color="#FFFFFF">Nick:</font></th>
                <td><%= request.getParameter ("nick") %></td>
            </tr>
         </table>

<%
            int rowsAffected = usuariosBD.borrarUsuario (request.getParameter ("nick"));
            if (rowsAffected == 1) {
%>
                <center>
                    <h2><font color="#337733">Se ha borrado el usuario <%= request.getParameter ("nick") %></font></h2>
                </center>
<%
            }
            else {
%>
                <center>
                    <h2><font color="#cc0000">No se pudo borrar el usuario  <%= request.getParameter ("nick") %></font></h2>
                </center>
<%
            }
        }
        else {
%>
            <center>
                <h2><font color="#cc0000">No se encontró el usuario  <%= request.getParameter ("nick") %></font></h2>
            </center>
<%
        }
%>
        <hr>
        <center>
            <b><a href="..\Centro.jsp">Volver a la Página Inicial</a></b>
        </center>
    </body>
    <%session.setAttribute("nickReg",null);%>
</html>
