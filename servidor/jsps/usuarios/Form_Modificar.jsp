<%@ page language="java" import="java.util.*, genesis.usuarios.*" %>

<html><head><link rel="STYLESHEET" type="text/css" href="../Centro_data/genesis.css"><title>Untitled Document</title><meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script type="text/javascript">
   /*
    * Función validaForm: Devuelve true si los datos
    * del formulario son válidos. False en caso contrario.
    */
   function validaForm(formulario) {
       var valido = true;
       var error = "";


       // Expresión regular para los correos electrónicos
       re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/

       if (!re.test(formulario.email.value)) {
	  valido = false;
	  error = "La dirección de correo electrónico no es válida";
       }
       
     
       if (formulario.nombre.value == "") {
	  valido = false;
	  error = "Debes de introducir un nombre";
       }  
	   
	   if (formulario.password.value == "") {
	  valido = false;
	  error = "Debes de introducir una contraseña";
       }  

  
       if (!valido) {
	  alert(error);
	  return false;
       } else {
	  return true;
       }
   }
    
</script>

</head><body background="../imagenes/fondo222.jpg">


        <h1 class="titulo"><center>
    Usuarios - Modificando el usuario <%= request.getParameter ("nick") %>
</center></h1>
<%
       Usuario usuario = (Usuario) UsuariosBD.getGestorUsuarios().getUsuario(request.getParameter("nick"));
        if (usuario != null){
%>
            <form method="post" action="Modificar.jsp" onSubmit="return validaForm(this)">
            <input name="nick" type="hidden" value="<%= usuario.getNick () %>">
            <table align="center" cellpadding="2" cellspacing="2" border="1" width="80%">
                <tr>
                    
      <th class="texto4">Password:</th>
                    <td>
                   <p class="texto6"> <input name="password" type="password" value="<%= usuario.getPassword() %>" size="20"></p>
				   </td>
                </tr>
                <tr>
                    
      <th class="texto4">Nombre:</th>
                    <td>
                    <p class="texto6"><input name="nombre" type="text" value="<%= usuario.getNombre () %>" size="20"></p>
					</td>
                </tr>
                <tr>
                    
      <th class="texto4">Email:</th>
                    <td>
                    <p class="texto6"><input name="email" type="text" value="<%= usuario.getEmail () %>" size="20"></p>
					</td>
                </tr>
                <tr>
                    
      <th class="texto4">Sexo:</th>
                    <td>
		    <% String sexo = usuario.getSexo(); %>
		    <input type="radio" name="sexo" value="H" <%=sexo.equals("H") ? "CHECKED" : ""%>>Hombre 
			<input type="radio" name="sexo" value="M" <%=sexo.equals("M") ? "CHECKED" : ""%>>Mujer </td>
                </tr>
				
				 </tr>
			<tr>
                <th class="texto4">Avatar:</th>
                <td class="texto">
				¿Quieres modificar tu avatar?
				<select name="modif">
          				<option value="si" class="texto1">SI</option>
          				<option value="no" class="texto1">NO</option>						
        		</select>
				<%
					if (request.getParameter("modif").equals("si")){
				%>
					<select name="select">
					<% 	ArrayList nombres_cartas = GestorCartas.getGestorCartas().getNombres();
						for (Iterator iterator = nombres_cartas.iterator(); iterator.hasNext(); ) {
    	                	    String nombre = (String) iterator.next ();
					%>
        
          				<option><%=nombre%></option>
          			<%
						}
					%>
        			</select>
				<%
				}else{
					if (request.getParameter("modif").equals("no")){
				%>
					<select name="select" disabled>
					<% 	ArrayList nombres_cartas = GestorCartas.getGestorCartas().getNombres();
						for (Iterator iterator = nombres_cartas.iterator(); iterator.hasNext(); ) {
    	                	    String nombre = (String) iterator.next ();
					%>
        
          				<option><%=nombre%></option>
          			<%
						}
					%>
        			</select>
				<%
					}
				}
				%>

      </td>
            </tr>
            </table>
            <br>
            <center>
                <input name="pagemode" type="hidden" value="submit">
                <input align="center" type="submit" value="Modificar el usuario <%= request.getParameter ("nick") %>">
            </center>
<%
        }
        else {
%>
            <center>
                <h2 class="texto2">No se pudo encontar el usuario  <%= request.getParameter ("nick") %></h2>
            </center>
<%
        }
%>
        </form>
		<hr>
        <center>
            <b class="titulo2"><a href="../Centro.jsp">Volver a la Página Inicial [el usuario no se modificará]</a></b>
        </center>
    </body>
</html>
