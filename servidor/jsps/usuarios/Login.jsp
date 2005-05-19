<%@ page language="java" import="java.util.*, genesis.usuarios.*" %>

<html><head><link rel="STYLESHEET" type="text/css" href="../Centro_data/genesis.css">
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<body background="../imagenes/fondo222.jpg">

<h1 class="titulo"><center>
	Usuarios - Logeando un usuario
</center></h1>
        <table align="center" cellpadding="2" cellspacing="2" border="1" width="80%" >
            <tr>
               	<th class="texto4">Nick:</th>
                <td class="texto6"><%= request.getParameter ("nick")%>&nbsp;</td>
            </tr>
            <tr>
              	<th class="texto4">Password:</th>
                
    <td class="texto6">******* (Oculta por seguridad)</td>
            </tr>
        </table>
<%

            Usuario usu = (Usuario) UsuariosBD.getGestorUsuarios().getUsuario(request.getParameter("nick"));
            if (usu != null){
                if(((String)usu.getPassword()).equals(request.getParameter("password"))){
%>
                <%session.setAttribute("nickReg",request.getParameter("nick"));%>
                <center>
                    <h2 class="texto5">Bienvenido <%= request.getParameter ("nick") %></h2>
                </center>
               	<p align="center" class="titulo2">
			   		<b><a href="Form_Modificar.jsp?nick=<%= usu.getNick () %>">Modificar datos del usuario</a></b>
				</p>
               
<%
                }
                else {
%>
                <center>
                    <h2 class="texto2">Password incorrecta</font></h2>
                </center>
<%
                }
            }else{
%>
                <center>
                    <h2 class="texto2">Nombre de usuario incorrecto</h2>
                </center>
<%          }
%>
        <hr>
        <center>
            <b class="titulo2"><a href="../Centro.jsp">Volver a la Página Inicial</a></b>
        </center>
    </body>
</html>
