<html>
   <%@ page import="genesis.cartas.*, java.util.*" %>
   <%!
      private String seleccionarDefecto(String seleccionada, String raza) {
	 if (seleccionada == null) 
	    return "";
	 else if (seleccionada.equals(raza))
	    return "SELECTED";
	 else
	    return "";
      }
   %>
   <head>
      <title>Página principal</title>
      <link rel="STYLESHEET" type="text/css" href="../Centro_data/genesis.css">
   </head>
   
<body background="../imagenes/fondo222.jpg">
<h1 align="center" class="titulo">BÚSQUEDA DE CARTAS</h1>
<table width="75%" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><a href="bdir.jsp#directa">Busqueda directa</a></td>
    <td><a href="braza.jsp#codigo">Búsqueda por código</a></td>
    <td><a href="bnom.jsp#nombre">Búsqueda por nombre</a></td>
  </tr>
</table>
<hr/>
<img src="../imagenes/cartas2.gif">

<!-- Búsqueda inmediata -->
<h2 id="directa" align="center" class="titulo4">&nbsp;</h2>
</body>
</html>
