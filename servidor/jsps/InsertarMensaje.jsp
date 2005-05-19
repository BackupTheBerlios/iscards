<%@ page language="java" import="java.util.*, genesis.foro.*" %>
<html><head><link rel="STYLESHEET" type="text/css" href="file:///C|/hlocal/jakarta-tomcat-5.5.7/webapps/genesis/Centro_data/genesis.css"><title>Untitled Document</title><meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>
<body background="file:///C|/hlocal/jakarta-tomcat-5.5.7/webapps/genesis/imagenes/fondo222.jpg">
<h1><center>
    <p class="titulo">Foro - Insertando un nuevo mensaje</p>
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
	int idTema = Integer.parseInt(request.getParameter("idtemita")); 
	
		ArrayList mensajes = MensajesBD.getGestorMensajes().getMensajes2();				
		Mensaje m=(Mensaje)mensajes.get(mensajes.size()-1);
		System.out.println("mensaje" + m.getIdTema());
		Mensaje mensaje = new Mensaje  (
		m.getIdMensaje()+1,
		(String)session.getAttribute("nickReg"), // Nick del autor
	        request.getParameter ("texto"),		 // Texto del mensaje
		new java.sql.Date(new java.util.Date().getTime()),
							 // Fecha del mensaje
	        idTema					 // Tema identificador del mensaje
        );
        
        boolean exito = MensajesBD.getGestorMensajes().insertarMensaje(mensaje);
	if (exito) {
%>
                <center>
                    <h2 class="texto5">Mensaje añadido correctamente</h2>
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
            <b class="titulo2"><a href="Foro.jsp?indice=0">Volver al Foro</a></b>
        </center>
    </body>
</html>
