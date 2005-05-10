<html>
   <%@ page import="genesis.cartas.*, java.io.*" %>
   <%
      String nombreArchivo = request.getParameter("fichero");
      if (nombreArchivo == null) {
   %>
	 <jsp:forward page="SubirCartas.html"/>
   <%
      }
   %>
   <head>
      <title>Cartas subidas</title>
   </head>
   <body>
      <%
	 FileInputStream is = new FileInputStream(nombreArchivo);
	 LectorCartas lectorCartas = new LectorCartas(is);
	 GestorCartas gc = GestorCartas.getGestorCartas();

	 Carta actual = lectorCartas.siguienteCarta();
	 while (actual != null) {
	    if (gc.getMedianteID(actual.getCodigo()) != null) {
	       try {
	         gc.actualizarCarta(actual);
	         out.println(actual.getCodigo() + " actualizada<br>");
	       } catch (Exception e) {
		  out.println("No se pudo actualizar " + actual.getCodigo() + 
		     " : " + e.getMessage() + "<br>");
	       }
	    } else {
	       try {
	         gc.añadirCarta(actual);
	         out.println(actual.getCodigo() + " añadida<br>");
	       } catch (Exception e) {
		  out.println("No se pudo añadir " + actual.getCodigo() + 
		     " : " + e.getMessage() + "<br>");
	       }
	    }
	    
	    actual = lectorCartas.siguienteCarta();
	 }
	 
      %> 
      
   </body>
</html>
