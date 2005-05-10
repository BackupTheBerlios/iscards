<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<html>
	
	<head>
	</head>
	<body>
		<c:out value="Hallo!"/>
		2 + 2 = ${2+2}
		<c:out value="${param['path']}"/>
		<c:choose>
			<c:when test="${param['path'] == null}">
				Es nulo
			</c:when>	
			<c:otherwise>
				No es nulo
			</c:otherwise>
		</c:choose>	
	</body>
</html>