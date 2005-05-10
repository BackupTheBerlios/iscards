<%@ page language="java" import="java.util.*, genesis.foro.*" %>
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

       if (formulario.titulo.value == "") {
	  		valido = false;
			error = "Debes introducir un titulo";
       }  

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
<form method="post" action="InsertarTema.jsp" onSubmit="return validaFormulario(this)">
	<table align="center" cellpadding="2" cellspacing="2" width="50%" >
    	<tr> 
      		<th class="texto4">T&iacute;tulo:</th>
      		<td>
        		<input name="titulo" type="text" size="54" maxlength="60">
      		</td>
    	</tr>
    	<tr> 
	    	<th class="texto4">Texto:</th>
      		<td>
		        <textarea name="texto" rows="10" style="width:350px" maxlength="1000"></textarea>
		    </td>
	    </tr>
	</table>
	<br>
    <center>
		<input name="pagemode" type="hidden" value="submit">
        <input type="submit" value="Enviar">
	</center>
    <hr>
    <center>
    	<b class="titulo2"><a href="Foro.jsp">Volver a la página principal del foro</a></b> 
  	</center>
</form>
</body>
</html>