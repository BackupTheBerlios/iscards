<%@ page language="java" import="java.util.*, genesis.usuarios.*, genesis.cartas.*" %>
<html>
<head>
<link rel="STYLESHEET" type="text/css" href="../Centro_data/genesis.css">
</head>
<body background="../imagenes/fondo222.jpg">

        <h1 class="titulo"><center>
    Usuarios -- Insertando un nuevo usuario
</center></h1>
        <table align="center" cellpadding="2" cellspacing="2" border="1" width="80%">
            <tr>
                
    <th class="texto4">Nick:</th>
                <td class="texto6"><%= request.getParameter ("nick") %>&nbsp;</td>
            </tr>
            <tr>
                
    <th class="texto4">Password:</th>
                <td class="texto6"><%= request.getParameter ("password") %>&nbsp;</td>
            </tr>
            <tr>
                
    <th class="texto4">Nombre:</th>
                <td class="texto6"><%= request.getParameter ("nombre") %>&nbsp;</td>
            </tr>
            <tr>
                
    <th class="texto4">Email:</th>
                <td class="texto6"><%= request.getParameter ("email") %>&nbsp;</td>
            </tr>
            <tr>
                
    			<th class="texto4">Sexo:</th>
                <td class="texto6"><%= request.getParameter ("sexo") %>&nbsp;</td>
            </tr>
			
			<tr>
                
    			<th class="texto4">Avatar:</th>
                <td class="texto6">
				<%
					String nombre = request.getParameter ("avatar");
					String raza_tipo = GestorCartas.getGestorCartas().getRazaTipoMedianteNombre(nombre);
					System.out.println(avatar);
					System.out.println(raza_tipo);
					String filepath = "../cartas/imagenes" + raza_tipo + nombre + ".jpg";
				%>
				<img src=<%=filepath%>>&nbsp;</td>
            </tr>
			
			
         </table>
<%
            UsuariosBD usuariosBD = UsuariosBD.getGestorUsuarios(); 
            Usuario usuario = new Usuario  (
                request.getParameter ("nick"),
                request.getParameter ("password"),
                request.getParameter ("nombre"),
                request.getParameter("email"),
                request.getParameter("sexo"),
				request.getParameter("avatar"));
            int rowsAffected = usuariosBD.insertarUsuario(usuario);
            if (rowsAffected == 1) {
%>
                <center>
                    <h2 class="texto5">Se ha añadido a la Base de Datos</h2>
                </center>
<%
            }
            else {
%>
                <center>
                    <h2 class="texto2">No se ha podido Añadir el registro</h2>
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