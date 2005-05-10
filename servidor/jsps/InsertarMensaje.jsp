<%@ page language="java" import="java.util.*, genesis.foro.*" %>
<html><head><link rel="STYLESHEET" type="text/css" href="Centro_data/genesis.css"><title>Untitled Document</title><meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>
<body background="imagenes/fondo222.jpg">
<h1><center>
    <p class="titulo">Foro -- Insertando un nuevo mensaje</p>
  </center>
</h1>
<%
	String ant_texto = request.getParameter("texto");
	FiltroMensajes filtro = new FiltroMensajes();
	String texto = filtro.filtrar(ant_texto);
%>
<table align="center" cellpadding="2" cellspacing="2" border="1" width="80%">
  <tr> 
    <th class="texto4">Mensaje:</th>
    <td class="texto6"><%= texto %>&nbsp;</td>
  </tr>
</table>
<%
	ArrayList mensajes = MensajesBD.getGestorMensajes().getMensajes2();				
        int idMensaje;
	System.out.println("Hay " + mensajes.size() + " mensajes");
	if (mensajes.size() == 0) {
	   idMensaje = 1;
	} else {
	   idMensaje = ((Mensaje)mensajes.get(mensajes.size() - 1)).getIdMensaje() + 1;
	}
	Mensaje mensaje = new Mensaje  (
		idMensaje,
		(String)session.getAttribute("nickReg"),
        request.getParameter ("texto"));
	int rowsAffected = MensajesBD.getGestorMensajes().insertarMensaje(mensaje,request.getParameter("idtemita"));
	if (rowsAffected == 3) {
%>
                <center>
                    <h2 class="texto5">Se ha añadido a la Base de Datos</h2>
                </center>
<%
            }
            else {
%>
                <center>
                    <h2 class="texto2">No se ha podido Añadir el mensaje</h2>
                </center>
<%
            }
%>
        <hr>
        <center>
            <b class="titulo2"><a href="Foro.jsp">Volver al Foro</a></b>
        </center>
    </body>
</html>
