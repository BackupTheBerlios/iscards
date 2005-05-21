<%@ page language="java" import="java.util.*, genesis.usuarios.*" %>

<html><head><link rel="STYLESHEET" type="text/css" href="../Centro_data/genesis.css">
<title>Untitled Document</title><meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>
<body background="../imagenes/fondo222.jpg">
<% String n = request.getParameter("nick"); %>
<h1 class="titulo"><center>
    Usuarios - Modificando el usuario <%= request.getParameter ("nick") %>
</center></h1>
        <table align="center" cellpadding="2" cellspacing="2" border="1" width="80%">
            <tr>
                
    <th class="texto4">Password:</th>
                <td class="texto6"><%= request.getParameter ("password") %>&nbsp;</td>
            </tr>
            <tr>
                
    <th class="texto4">Nombre:</th>
                <td class="texto6"><%= request.getParameter ("nombre") %>&nbsp;</td>
            </tr>
            <tr>
                
    <th class="texto4">Email:</font></th>
                <td class="texto6"><%= request.getParameter ("email") %>&nbsp;</td>
            </tr>
            <tr>
                
    <th class="texto4">Sexo:</font></th>
                <td class="texto6"><%= request.getParameter ("sexo") %>&nbsp;</td>
            </tr>
	<tr>
	<th class="texto4">Avatar:</th>
<%
	String file;
	String avatar=null;
	if (request.getParameter("modif").equals("si")){
		if (request.getParameter("select")!=null){
			file = "../cartas/imagenes_interiores/" + GestorCartas.getGestorCartas().getIDMedianteNombre(request.getParameter ("select")) + ".jpg";
			avatar = GestorCartas.getGestorCartas().getIDMedianteNombre(request.getParameter ("select"));
%>		
			<td class="texto6"><img src=<%=file%>>&nbsp;</td>
<%
		}
		else{
%>
			<td class="texto6">&nbsp;</td>
<%
		}
	}else{
		if (request.getParameter("modif").equals("no")){
			Usuario usu = UsuariosBD.getGestorUsuarios().getUsuarios(request.getParameter("nick"));
			avatar = usu.getAvatar();
			file = "../cartas/imagenes_interiores/" + avatar + ".jpg";
%>
			<td class="texto6"><img src=<%=file%>>&nbsp;</td>
<%
		}
		else{
%>
			<td class="texto6">&nbsp;</td>
<%
		}
	}
%>
            </tr>                
        </table>
<%
        UsuariosBD usuariosBD = UsuariosBD.getGestorUsuarios(); 
        Usuario usuario = new Usuario  (
                n,
                request.getParameter ("password"),
                request.getParameter ("nombre"),
                request.getParameter("email"),
                request.getParameter("sexo")
                avatar);
        int rowsAffected = usuariosBD.modificarUsuario(usuario);
        if (rowsAffected == 1) {
%>
            <center>
                <h2 class="texto5">Se ha modificado el usuario <%= request.getParameter ("nick") %></h2>
            </center>
<%
        }
        else
        {
%>
            <center>
                <h2 class="texto2">No se pudo modificar el usuario  <%= request.getParameter ("nick") %></font></h2>
            </center>
<%
        }
   
%>
        <hr>
        <center>
            <b class="titulo2"><a href="../Centro.jsp">Volver a la Página Inicial</a></b>
        </center>
    </body>
</html>
