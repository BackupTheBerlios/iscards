<%@ page language="java" import="java.util.*, genesis.foro.*" %>
<% if (session.getAttribute("modoAdministrador") == null) { %>
   <jsp:forward page="Foro.jsp"/>
<% } %>
<html>
<head>
<title>Untitled Document</title>
<link rel="STYLESHEET" type="text/css" href="Centro_data/genesis.css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script type="text/javascript">
   /*
    * Función validaFormulario: Devuelve true si los datos
    * del formulario son válidos. False en caso contrario.
    */
   function validaFormulario(formulario) {
       var valido = true;
       var error = "";
   
       if (formulario.texto.value == "") {
	 		valido = false;  
	 		error = "Debes introducir un mensaje";
       }  
  
       if (!valido) {
	  		alert(error);
	  		return false;
       } else {
	  		return true;
       }
   }
    
</script>
</head>
<body background="imagenes/fondo222.jpg">
	  <%
	    int idMensaje = Integer.parseInt(request.getParameter("idmensaje"));
	  
	    Mensaje mensaje = MensajesBD.getGestorMensajes().getMensaje(idMensaje);
	   
	    String cont = mensaje.getTexto();
	    StringBuffer sb = new StringBuffer();
	    
	    int inicio = 0, indice;
	    while ((indice = cont.indexOf("<br/>")) > -1) {
	       sb.append(cont.substring(0, indice) + "\n");
	       cont = cont.substring(indice + 5, cont.length());
	       inicio = indice + 5;
	    }
	    
	    sb.append(cont);
	    String contenido = sb.toString();
	    
	  %>
<form method="post" action="ModificarMensaje.jsp" onSubmit="return validaFormulario(this)">
        
  <table align="center" cellpadding="2" cellspacing="2" width="50%" >
    <tr> 
      <th> <font color="#FFFFFF">Texto</font><font color="#660033">:</font></th>
      <td>
        <textarea name="texto" rows="10" style="width:350px" maxlength="1000"><%=contenido%></textarea>
      </td>
    </tr>
  </table>
        <br>
        <center>
            <input type="hidden" name="idmensaje" value="<%=idMensaje%>">
            <input type="submit" value="Enviar">
        </center>
        <hr>
        
    <p>&nbsp;</p>
      <p align="center"><a href="Foro.jsp">Volver a la p&aacute;gina principal 
        del foro</a></p>
  
        </form>


</body>
</html>
