<%@page import="genesis.configuracion.*, java.util.*" %>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<c:set var="nombre" value="${requestScope.nombre}"/>

<html>
<head>
<title>Opciones para: ${nombre}</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" type="text/css" href="mostrarGrupo.css"/>
</head>

<body text="#000000">

	  <table class="cabecera" width="100%">
	  		 <tr><td class="titulo">
			   <p class="titulo" align="center">Configuración de: 
			   <font color="#0000FF">
			   	${nombre}
			   	</font>
			   </p>
			 </td></tr>
			 <tr><td class="secuencia">
			   <p class="secuencia">
			   	Ir a: 
			   	 <a href="MostrarOpcionCompuesta">G&eacute;nesis</a>
			   	 <c:forEach var="actual" items="${requestScope.opcionDescompuesta}">
			   	 	> <a href="MostrarOpcionCompuesta?path=${actual.enlace}">${actual.opcion}</a>
			   	 </c:forEach>
			   </p> 
			 </td></tr>
	  </table>
	  
	  
	  <p class="descripcion">
	  	
	  </p>

	  
	  <table border="0" width="100%">

		<c:forEach var="actual" items="${requestScope.compuestas}">

			<%-- Imprimimos las opciones compuestas --%>
			

	  		<tr>
				<td class="opcionCompuesta">
					<p>
						<font class="nombre">
						<a href="MostrarOpcionCompuesta?path=${actual.enlace}">
							${actual.nombre}...
						</a>
						</font>
					 </p>
				</td>
			</tr>
			<tr>
				 <td colspan="3">
				  	 <p class="descripcion">
				  	 	${actual.descripcion}
					 </p>
			  	 	<p>&nbsp;</p>
				 </td>
			</tr>
			
		</c:forEach>
		




	  <%-- Repetir para cada fila --%>
	  <form action="ProcesarRespuestas" method="POST">
	  	<input type="hidden" value="${requestScope.path}" name="path"/>
	  
	  	<c:forEach var="actual" items="${requestScope.simples}">
	  
	  		<tr>
				<td class="opcionSimple">
					<p>
					   <font class="nombre">
					   	${actual.nombre}
					   </font> 
					   &nbsp;
					  <!-- Añadir el nombre lógico de la acción -->
					  <input type="text" size="30"
					  	value="${actual.valor}" 
					  	name="${actual.nombre}"/>
					  
					   <font class="tipo">
					   		<acronym title="${actual.descripcionTipo}">
					   		 [${actual.tipo}]
					   		</acronym>
					   		 
					   </font> 
					 </p>
				</td>
			</tr>
			<tr>
				 <td colspan="3">
				  	 <p class="descripcion">
				  	 		${actual.descripcion}
					 </p>
			  	 	<p>&nbsp;</p>
				 </td>
			</tr>
			
		</c:forEach>
			
	  <!-- Fin de repetir para cada fila -->
	  
		  <tr>	
		  	<td>  
		  		<input type="submit" value="Aceptar"/>
		  		<input type="reset" value="Borrar" />
		  	</td>
		  </tr>
			
	  </form>
	</table>

</body>
</html>
