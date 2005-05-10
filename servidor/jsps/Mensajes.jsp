<%@ page language="java" import="java.util.*, genesis.foro.*" %>
<html><head><link rel="STYLESHEET" type="text/css" href="Centro_data/genesis.css">
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>
<body background="imagenes/fondo222.jpg">
<table width="100%" height="147">
  <tbody> 
  <tr>
	<td height="188"> 
      <div align="center" class="titulo">FORO</div>
<%
        boolean administrador;
        if (session.getAttribute("modoAdministrador") != null)
	    administrador = true;
	 else
	    administrador = false;
	String indi_m=request.getParameter("ind_m");
	System.out.println("indi_m vale " + indi_m);
	if (indi_m == null || indi_m.equals("")) {
	   indi_m = "0";
	}

    String id_t = request.getParameter("idtema");
	ArrayList mensajes_temas = MensajeTemaBD.getGestorMensajeTema().getMensajes(id_t);
	ArrayList men_tem = MensajeTemaBD.getGestorMensajeTema().getMensajes2(id_t,indi_m);
	int tam = mensajes_temas.size(); 
	int paginas_mens = tam/8 + 1;
	int indice_m = (int) new Integer(indi_m).intValue();
	if (session.getAttribute("nickReg") == null){
%>	
	    <table width="100%">
			<tr>
				<td class="titulo2"><%if(!administrador){%><a href="NoLogeado.htm">Nuevo Mensaje</a><%}%><br>
				</td>
				<td align="right" class="texto4">Pagina <%= indice_m/8 +1%> / <%= paginas_mens%></td>
			</tr>	
		</table>
		<br>
<%
	}
	else {	
%>	
		<table width="100%">
		<tr>
			<td class="titulo2"><%if(!administrador){%><a href="nuevoMensaje.jsp?idt=<%= request.getParameter("idtema")%>">Nuevo Mensaje</a><%}%><br>
			</td>
			<td align="right" class="texto4">Pagina <%= indice_m/8 +1%> / <%= paginas_mens%></td>
		</tr>	
		</table>
		<br>
<%
	}
%>
    <table align="center" border="1" cellpadding="2" cellspacing="2" width="80%">
    <tbody>
		<tr>
        	<td width = 15% class="texto4">Nick</td>
            <td width = 70% class="texto4">Texto</td>
			<td width = 15% class="texto4">Fecha</td>
        </tr>
<%
	if (men_tem != null) {
		if (men_tem.size () > 0) {
			for (Iterator iterator = men_tem.iterator(); iterator.hasNext(); ) {
    	    	Mensaje mensaje = (Mensaje) iterator.next ();
%>
				<tr>
					<td width=15%>
						<table>
							<tr>
								<td class="texto6"><%= mensaje.getNick()%></td>
							</tr>
						</table>
					</td>			
					<td width=70% class="texto6"><%= mensaje.getTexto()%></td>
					<td width=15% class="texto6"><%= mensaje.getFechaString()%></td>											
				</tr>
			<%if (administrador) { %>
			      <tr>
				 <td colspan="3" align="right">
				    <a href="BorrarMensaje.jsp?idmensaje=<%=mensaje.getIdMensaje()%>">Borrar Mensaje</a>
				    / 
				    <a href="EditarMensaje.jsp?idmensaje=<%=mensaje.getIdMensaje()%>">Editar Mensaje</a>
				 </td>
			      </tr>
			<% } %>

<%					
			}
		}
	}
	Tema tema = TemasBD.getGestorTemas().getTema(request.getParameter("idtema"));
	tema.setCont_visitas();			
	int i = TemasBD.getGestorTemas().modificarTema(tema);
%>
	</tbody>
	</table>
<%
	if (paginas_mens>1){
		if (indice_m < 8){
%>
			<div align="right" class="titulo2"><a href="Mensajes.jsp?ind_m=<%= indice_m+8%>&idtema=<%=id_t%>">Siguiente</a> </div>
<%
		}
		else{
			if (indice_m+8 > tam-1){
%>
        		<div align="left" class="titulo2"><a href="Mensajes.jsp?ind_m=<%= indice_m-8%>&idtema=<%=id_t%>">Anterior</a> </div>
<%			
			}
			else{
%>
				<table width="100%">
					<tr>
        				<div align="left" class="titulo2">
							<a href="Mensajes.jsp?ind_m=<%= indice_m-8%>&idtema=<%=id_t%>">Anterior</a>
						</div>
      					<div align="right" class="titulo2">
							<a href="Mensajes.jsp?ind_m=<%= indice_m+8%>&idtema=<%=id_t%>">Siguiente</a>
						</div>
					</tr>
				</table>
<%
			}
		}
	}
%>
	<p align="center" class="titulo2"><a href="Foro.jsp?indice=0">Volver a la p&aacute;gina principal del foro</a></p>
    </td>	
</tr>
</tbody>
</table>
</html>
