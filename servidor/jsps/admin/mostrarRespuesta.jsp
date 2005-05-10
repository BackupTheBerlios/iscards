<%@taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<html>

	<head>
	<title>Opciones para: ${nombre}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link rel="stylesheet" type="text/css" href="mostrarGrupo.css"/>
	</head>

	<body>

		<table class="cabecera" width="100%">
			 <tr><td class="titulo">
			   <p class="titulo" align="center">Configuración de: 
			   <font color="#0000FF">
			   	${requestScope.nombre}
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
		
		<ul>
			<c:forEach var="actual" items="${requestScope.informe}">
				<c:choose>
					<c:when test="${actual.error}">
						<li class="opcionError">
							Error en el valor introducido en <strong>${actual.nombre}</strong>
							<ul>
								<li class="descripcionTipo">
									${actual.descripcionTipo}
								</li>
							</ul>
							 
						</li>
					</c:when>
					
					<c:otherwise>
						<li class="opcionCambiada">
							${actual.nombre} cambiada correctamente
						</li>
					</c:otherwise>
				</c:choose>
			</li>			
			</c:forEach>
		</ul>
	
		<p class="secuencia">
		<a href="MostrarOpcionCompuesta?path=${requestScope.path}">Volver a ${requestScope.nombre}</a>
		</p>
	</body>

</html>