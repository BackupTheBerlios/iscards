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
      <title>P�gina principal</title>
      <link rel="STYLESHEET" type="text/css" href="../Centro_data/genesis.css">
   </head>
   
<body background="../imagenes/fondo222.jpg">
<h1 align="center" class="titulo">B�SQUEDA DE CARTAS</h1>
<table width="75%" align="center" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><a href="bdir.jsp#directa">Busqueda directa</a></td>
    <td><a href="braza.jsp#codigo">B�squeda por c�digo</a></td>
    <td><a href="bnom.jsp#nombre">B�squeda por nombre</a></td>
  </tr>
</table>
<hr/>
<img src="../imagenes/cartas2.gif">

<!-- B�squeda inmediata -->
<h2 id="directa" align="center" class="titulo4">&nbsp;</h2>
</body>
</html>
