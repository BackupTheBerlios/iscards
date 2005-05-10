<%@ page language="java" import="java.util.*, genesis.foro.*" %>
<html><head><link rel="STYLESHEET" type="text/css" href="Centro_data/genesis.css">
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"></head>
<body background="imagenes/fondo222.jpg">

<table width="100%">
<tbody><tr>
	<td>
		<div align="center" class="titulo">FORO</div>
<%
        boolean administrador;
        if (session.getAttribute("modoAdministrador") != null)
	    administrador = true;
	 else
	    administrador = false;
            
	String ind=request.getParameter("indice");
	if (ind == null || ind.equals("")) {
	   ind = "0";
	}
	
	int indi = (int) new Integer(ind).intValue();
	
	    ArrayList Todostemas = TemasBD.getGestorTemas().getTemas2();
	    ArrayList temas = TemasBD.getGestorTemas().getTemas(ind);		
            int tam = Todostemas.size(); 
			System.out.println(tam + "temas");
			int paginas = (tam/8) + 1;
			System.out.println(paginas + "paginas temas");
	if (session.getAttribute("nickReg") == null){
%>

      		<table width="100%">
		<tr>
			<td><% if (!administrador) { %><a href="NoLogeado.htm">Nuevo Tema</a><% } %></td>
			<td align="right" class="texto4">Pagina <%= indi/8 +1%> / <%= paginas%></td>
		</tr>	
		</table>
		<br>
<%
	}
	else {	
%>	
		<table width="100%">
		<tr>
			<td><% if (!administrador) { %><a href="nuevoTemaMensaje.jsp">Nuevo Tema</a><% } %><br></td>
			<td align="right" class="texto4"> Pagina <%= indi/8 +1%> / <%= paginas%></td>
		</tr>	
		</table>
		<br>
<%
	}
%>

		<table align="center" border="1" cellpadding="2" cellspacing="2" width="80%" class=foro>
        <tbody>

	<tr>
                <td class="texto4">Estado</td>
		<% if (administrador) { %><td><font color="#FFFFFF"></font></td><% } %>
                <td class="texto4">Titulo del Tema</td>
				<td class="texto4">Mensajes</td>
				<td class="texto4">Visitas</td>
				<td class="texto4">Fecha</td>
                <% if (administrador) { %><td>Opciones</td><% } %>
           	</tr>
<%

            	if (temas != null) {
                	if (temas.size () > 0) {	
					for (Iterator iterator = temas.iterator(); iterator.hasNext(); ) {
    	                    Tema tema = (Tema) iterator.next ();
%>        	
			<tr>
			<td width=8%>
				<% int t = tema.getEstado(); %>
				<% if (t==0) {%> 
                        <img src="imagenes/candadoUnlock.gif" width="16" height="16"> 
						<% }else if (t==1){%> 
                        <img src="imagenes/candadoLock.gif" width="16" height="16">
						<% }else if (t==2){%> 
                        <img src="imagenes/encuesta_grafico.jpg" width="14" height="14">
						<%}%>
						</td>			
				<% if (administrador) { 
				    if (t == 0) { %>
				       <td><a href="CerrarTema.jsp?idtema=<%=tema.getIdTemaString()%>">Cerrar</a></td>
				    <% } else { %>
				       <td><a href="AbrirTema.jsp?idtema=<%=tema.getIdTemaString()%>">Abrir</a></td>
				    <% } %>
				   
				<% } %>
						<td width=46% class="texto6">
							<a href="Mensajes.jsp?idtema=<%= tema.getIdTemaString()%>&ind_m=0"><%= tema.getTitulo()%></a> &nbsp;</td>								
						<td width=9% align="right" class="texto6">
						<%
							ArrayList mensajes_temas=MensajeTemaBD.getGestorMensajeTema().getMensajes(tema.getIdTemaString());
							int cont_mensajes = mensajes_temas.size();
						%>
							<p class="texto6"><%=cont_mensajes %>&nbsp;</p>
						</td>
						<td width=8% align="right" class="texto6">
							<%=tema.getCont_visitas()%> &nbsp;</td>
						<td width=14% align="right" class="texto6">
							<%=tema.getFechaString()%> &nbsp;</td>	
						<% if (administrador) { %>
						   <td><nobr><a href="BorrarTema.jsp?idtema=<%=tema.getIdTemaString()%>">Borrar</a> / Editar</nobr></td>
						<% } %>
						</tr>

<%		  }
                    
                }
            }
%>
</tbody></table>
      <%
	 System.out.println("paginas vale " + paginas);
	 System.out.println("indi vale " + indi);
		if (paginas>1){

		if (indi < 8){
%>
      <div align="right" class="titulo2"><a href="Foro.jsp?indice=<%= indi+8%>">Siguiente</a> </div>
        <%
		}else{
			if (indi+8 > tam-1){
%>
        <div align="left" class="titulo2"><a href="Foro.jsp?indice=<%= indi-8%>">Anterior</a> </div>
        <%			}
			else{
%>
        
      	<table width="100%">
	<tr>
		<td class="titulo2"><a href="Foro.jsp?indice=<%= indi-8%>">Anterior</a></td>
		<td align="right" class="titulo2"><a href="Foro.jsp?indice=<%= indi+8%>">Siguiente</a></td> 
	</tr>
	</table>
      
		<%
			}
		}
		}
%>

    </td>	
</tr></tbody>
</table>
</html>
