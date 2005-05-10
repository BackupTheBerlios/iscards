<%@ page language="java" import="java.util.*, genesis.usuarios.*" %>
<html>
<head>
   <link rel="STYLESHEET" type="text/css" href="Centro_data/genesis.css">
   <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<body background="imagenes/fondo222.jpg">
        <h1 class="titulo"><center>Ranking por Puntos</center></h1>
        <table align="center" cellpadding="2" cellspacing="2" border="1" width="80%">
            <tr>
                <th class="texto3">Nick</th>
               <th class="texto3">Puntos</th>
            </tr>
<%
            ArrayList usuarios = UsuariosBD.getGestorUsuarios().getRanking();
            if (usuarios != null) {
                if (usuarios.size () > 0) {
                    for (Iterator iterator = usuarios.iterator(); iterator.hasNext(); ) {
                        Usuario usuario = (Usuario) iterator.next ();
%>
                        <tr>
                            <td class="texto">
                                <%= usuario.getNick () %> &nbsp;</font></td>
                            <td class="texto">
                                <%= usuario.getPuntos () %> &nbsp;</td>
                        </tr>
<%
                    }
                }
            }
%>
        </table>
        <center>
            <b><a href="Centro.jsp">Volver a la Página Inicial</a></b>
        </center>
  </body>
</html>
