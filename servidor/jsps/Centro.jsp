<%@ page language="java" import="java.util.*, genesis.usuarios.*,genesis.noticias.*,genesis.cartas.*" %>
<html>
<head>
<link rel="STYLESHEET" type="text/css" href="Centro_data/genesis.css">
<title>Frame</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body background="imagenes/fondo222.jpg">
<table width="100%" height="250">
  <tbody><tr>
    <td width="16%" valign="top"> 
      <table border = "0" width="145" style="border:2px solid #BBBBBB;" cellspacing="0" cellpadding="0" background="imagenes/cuadrologin.jpg" height="155">
        <tbody> 
        <tr ><td colspan="2" style="border-bottom:1px solid BBBBBB; ">
            <div align="center"><a href="Ranking.jsp">RANKING</a> </div>        	
		</td></tr>        
            <tr> 
              <td align="center" class="titulo2">Nick</td>
          <td align="center" class="titulo2">Puntos</td>
        </tr>
        <%
             ArrayList usuarios = UsuariosBD.getGestorUsuarios().getRanking(); 
			int i=0;
            if (usuarios != null) {
                if (usuarios.size () > 0) {
                    for (Iterator iterator = usuarios.iterator(); iterator.hasNext() && i<3; ) {
                        Usuario usuario = (Usuario) iterator.next ();
						i++;
		%>
        <tr> 
              
          <td> &nbsp;&nbsp;&nbsp;<%= usuario.getNick () %> </td>
              
          <td> <%= usuario.getPuntos () %> </td>
        </tr>
        <%						}
                   
                }
            }
%>
        </tbody>
      </table>
      <br>
        <table width="145" height="244" border="0" cellpadding="0" cellspacing="0" background="imagenes/cuadrologin.jpg"  style="border:2px solid #BBBBBB;">
          <tbody>
            <tr> 
              <td style="border-bottom:1px solid BBBBBB; "><center>
                  <p><a href="Noticias.jsp?indice_noticias=0">NOTICIAS</a></p>
                  </center></td>
            </tr>

            <tr> 
              <td height="181"> 
                <div align="center"> 
                  <marquee direction="up" scrollamount="2" style="font-family: arial; font-size: 8pt; text-align: center;" onMouseOver="scrollAmount='0'" onMouseOut="scrollAmount='2'">
                  <div align="center"> <img src="imagenes/3.gif" width="32" height="32"> 
                    <br>
			<%
	    ArrayList noticias = GestorNoticias.getGestorNoticias().getNoticias2(); 
		
            int contador=0;
			
           if (noticias != null) {
                if (noticias.size () > 0) {
                    for (Iterator iterator = noticias.iterator(); iterator.hasNext(); ) {
                       Noticia noticia = (Noticia) iterator.next ();
					   
%>
         <font color="#FFCC00"><b><u><%=	noticia.getTipo() + ": "%></u></b> </font><br>
		 <font color="#FFCC00"><b><%=	noticia.getTitulo()%></b> </font><br><br>
        <%
					
                   }
                }
            }
%>

                    <img src="imagenes/1.gif" width="32" height="32"> </div>
                  </marquee>
                </div></td>
            </tr>
          </tbody>
        </table> </td>
    <td width="66%" align="center" style="padding-left:20px;">
	  <table border="0" cellpadding="0" cellspacing="0" style="padding:0px;" width="200">
        <tr>
          <td width="28" valign="bottom"><img src="imagenes/esq-sup-izq.gif" width="28" height="30"></td>
          <td width="640"><img src="imagenes/superior.gif" width="557" height="30"></td>
          <td width="59" valign="bottom"><img src="imagenes/esq-sup-dcha.gif" width="59" height="30"></td>
        </tr>
        <tr>
          <td valign="top"><img src="imagenes/izq.gif" width="28" height="381"></td>
          <td background="imagenes/centro.jpg" width="557" height="381" align="center" valign="top" style="padding-top:70px ">
		  <span style="color: #333399"><font style="font-weight:bold;"></font></span>
              
            <p class="texto2">LA PROFECÍA </p>
            <br class="espacio">                          
            <p class="texto1">  El ángel caído ha descendido de los cielos. 
			<br>El universo entero se ha paralizado. Todo es guerra, sangre, 
			<br>dolor y sufrimiento.
			De entre las sombras han surgido cuatro 
			<br>potentes ejércitos: los ángeles del cielo con las fuerzas puras de 
			<br>los elementos, las legiones del infierno mostrando su fuego y
			<br> su maldad, los dioses con sus poderes sobrenaturales y los 
			<br>humanos quienes se unirán y lucharán con las armas nacidas de 
			<br>
              su desesperación. Todos ellos luchan 
              por la misma causa <br>
              : conseguir restaurar la paz. ¿A quién te unirás tú?
            </p>
			<br class="espacio">                          	
            <p class="texto2">&iexcl;COMIENZA TU AVENTURA!</p>
		  </td>
          <td><img src="imagenes/dcha.gif" width="59" height="381"></td>
        </tr>
        <tr>
          <td valign="top"><img src="imagenes/esq-inf-izq.gif" width="28" height="17"></td>
          <td><img src="imagenes/inferior.gif" width="557" height="17"></td>
          <td><img src="imagenes/esq-inf-dcha.gif" width="59" height="17"></td>
        </tr>
	    </table>
    </td>
    <td width="18%" valign="top"> 
      <% 
		if (session.getAttribute("nickReg") == null) {
	%>
      <form name="form1" method="post" action="usuarios\Login.jsp">
          <table align="center"border="0" cellpadding="2" cellspacing="2" width="144" background="imagenes/cuadrologin.jpg" height="238">
            <tbody> 
          <tr align="center"> 
                <th height="70"> 
                  <p class="titulo2">Nick</p>
              <p> 
                <input name="nick" type="text" size="16">
              </p>
              </th>
          </tr>
          <tr align="center"> 
            <th height="40"> 
              <p class="titulo2">Password<br>
                <input name="password" type="password" size="16">
              </p>
            </th>
          </tr>
          <tr>
                <th height="97"> 
                  <input name="pagemode" value="submit" type="hidden">
                  <input type="submit" name="Submit" value="Enviar"> </th>
          </tr>
        </tbody></table>
      </form>
	  <div align="center"><a href="Formulario.jsp">nuevo usuario</a></div>
<% } else {
 %>
        <h4 align="center"><a target="centro" class="Menu">Conectado como &nbsp;&nbsp;&nbsp<%=session.getAttribute("nickReg")%></a></h4>
  <%//Usuario usua = (Usuario) UsuariosBD.getGestorUsuarios().getUsuario(request.getParameter("nick"));%>	
  <div align="center"><a href="usuarios\Form_Modificar.jsp?nick=<%= session.getAttribute("nickReg")%>">Modificar</a></div>
  <div align="center"><a href="usuarios\Logout.jsp">Logout</a></div>
      <%}%>
    </td>
  </tr>
    <td height="2" width="16%"></tbody>
  </table>
  
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>
  	<p class="texto3" align="center">Si tienes alguna sugerencia o algún problema contacta con nosotros en: 
		<a href="mailto:contactagenesis@yahoo.es" ></a>
      &nbsp;<a href="mailto:contactagenesis@yahoo.es" class="A">contactagenesis@yahoo.es</a>
	</p>
	 
    </td>
  </tr>
</table>

  
</body></html>
