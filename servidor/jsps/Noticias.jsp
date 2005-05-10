<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*, genesis.noticias.*" %>
<html>
<head>
<title>Documento sin t&iacute;tulo</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" type="text/css" href="Centro_data/genesis.css"/> 
</head>

<body bgcolor="#000000" background="imagenes/fondo222.jpg">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td width="276"><img src="imagenes/imagen_noticias_izq2.gif" width="276" height="413"></td>
    <td width="100%" valign="top"><p align="center" class="titulo">NOTICIAS</p>
	<%
      String ind_not = request.getParameter("indice_noticias");

      if (ind_not == null || ind_not.equals("")) {
	 ind_not = "0";
      }
    
      ArrayList Todasnoticias = GestorNoticias.getGestorNoticias().getNoticias2();
      ArrayList noticias = GestorNoticias.getGestorNoticias().getNoticias(ind_not);
      
      int tam_not = Todasnoticias.size();
      int paginas_not = tam_not/3;
      int indi_not = (int) new Integer(ind_not).intValue();
      %>
		

	       <table width="100%">
		<tr>
			
			<td align="right">Pagina <%= indi_not/3 +1%> / <%= paginas_not%></td>
		</tr>	
		</table>
<table width="100%" border="1">
				    
<%
	    
		
            int contador=0;
           if (noticias != null) {
                if (noticias.size () > 0) {
                    for (Iterator iterator = noticias.iterator(); iterator.hasNext(); ) {
                       Noticia noticia = (Noticia) iterator.next ();
%>
			 <tr>		
						<td width="30%"><font color="#FFFFFF">
				 		<%= noticia.getTipo () %></font></td> 
						<td width="70%">
						<table width="100%">
						<tr>
						
                <td class="texto"><b><%= noticia.getTitulo () %></b></td>
              </tr>
						<tr>
						<td class="texto"><hr><%= noticia.getContenido() %></td></tr>						
						</table>
						</td>
					</tr>
							
			<br>	
      
<%
                   }
                }
            }
%>

</table>
<table width = "100%">
<tr>
<%
		if (paginas_not+1>1){
		System.out.println(paginas_not +"paginas noticias");

		if (indi_not < 3){
				System.out.println(indi_not +"indice noticias");
%>
    <td> <div align="right" class="Menu"><a href="Noticias.jsp?indice_noticias=<%= indi_not+3%>">Siguiente</a> </div></td>
        <%
		}else{
			if (indi_not+3 > tam_not-1){
%>
<td>        <div align="left" class="Menu"><a href="Noticias.jsp?indice_noticias=<%= indi_not-3%>">Anterior</a> </div></td>
        <%			}
			else{
%>
    
		<td><a href="Noticias.jsp?indice_noticias=<%= indi_not-3%>">Anterior</a></td>
		<td align="right" class="Menu"><a href="Noticias.jsp?indice_noticias=<%= indi_not+3%>">Siguiente</a></td> 
		<%
			}
		}
		}
%>
   </td></tr></table></td>
    <td width="322"><img src="imagenes/imagen_noticias_der.gif" width="322" height="398"></td>
  </tr>
</table>


</body>
</html>
