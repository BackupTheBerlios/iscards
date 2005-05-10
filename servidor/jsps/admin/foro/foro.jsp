<html>
	<head>
		<link rel="stylesheet" type="text/css" href="foro.css"/>
	        <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=ISO-8859-1">
		<title>Opciones de foro</title>
	</head>
	<body>
	
		<!-- Cabecera -->

	       <%
		  session.setAttribute("modoAdministrador", "Nuevo");
	       %>
		
		<table class="cabecera" width="100%">
				 <tr>
		  		 <td class="titulo">
				   <p class="titulo" align="center">
				       Opciones de Foro	
				   </p>
				 </td>
			 </tr>
		</table>
	  
	       <p class="descripcion">Pulsando en el siguiente enlace entrar&aacute;s en el foro como <b>Administrador</b>. Adem&aacute;s de
	       poder visualizar los mensajes, podrás abrir y cerrar temas, borrarlos y editarlos, as&iacute; como
	       editar y borrar los mensajes de cada tema.</p> 

	       <p align="center"><a href="/genesis/Foro.jsp" class="annadir"><b>Entrar en el foro</b></a></p>

	</body>
</html>

