<%@page import="java.util.*,genesis.noticias.*"%>

<html>
   <head>
   </head>
   <body>
      <% ArrayList noticias = GestorNoticias.getGestorNoticias().getNoticias2(); %>
      <%= noticias.size() %>
   </body>
</html>

