<html>
   <%@ page import="java.io.*, genesis.cartas.*" %>
   <head>
      <title>Subir cartas</title>
   </head>
   
<body background="../imagenes/fondo222.jpg">
<%
	 String nombreArchivo = request.getParameter("fichero");
	 out.println(nombreArchivo);
	 
	 FileInputStream is = new FileInputStream(nombreArchivo);

	 LectorCartas lectorCartas = new LectorCartas(is);

	 
      %>
<p> Seleccione las cartas a añadir: </p>
      <form action="SubidaDeCartas.jsp" method="GET" enctype="multipart/form-data"> 
	 <input type="hidden" name="fichero" value="<%= nombreArchivo%>" />
	 <table border="1" width="100%">
	    <tr>
	       <th width="50">Subir</th>
	       <th align="left">Código y descripción</th>
	    </tr>

	    <%
	       Carta actual = lectorCartas.siguienteCarta();
	       while (actual != null) {
	    %> 
	       <tr>
		  <td align="center"><input type="checkbox" name="<%=actual.getCodigo() %>" checked></td>
		  <td><%=actual.getCodigo() %> - <%= actual.getNombre() %>
	    <%
		  if (GestorCartas.getGestorCartas().getMedianteID(actual.getCodigo()) != null) {
		     out.println("</br><b>¡Ojo! " + actual.getCodigo() + " ya existe en la base de datos</b>");
		  }
	    %>
	       </td></tr>
	    <%
		  actual = lectorCartas.siguienteCarta();
	       }
	    %>
	    
	 </table>
	 <p>
	 <input type="submit" name="enviar" value="Añadir cartas seleccionadas"/>
      </form>
      <%
	 is.close();
      %>
     <hr/>
   </body>
</html>
