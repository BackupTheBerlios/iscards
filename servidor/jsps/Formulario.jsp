<html>
<%@ page language="java" import="java.util.*, genesis.cartas.*" %>
<head>
<title>Untitled Document</title>
<link rel="STYLESHEET" type="text/css" href="Centro_data/genesis.css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
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

       if (formulario.nick.value == "") {
	 valido = false;  
	 error = "Debes de introducir un nick";
       }  
  
       if (!valido) {
	  alert(error);
	  return false;
       } else {
	  return true;
       }
   }
    
</script>
</head>

<body background="imagenes/fondo222.jpg">
<form method="post" action="usuarios\Insertar.jsp" onSubmit="return validaForm(this)">
        <table align="center" cellpadding="2" cellspacing="2" width="50%" >
            <tr>
                
      <th class="texto4">Nick:</th>
                <td class="texto"><input name="nick" type="text" size="20"></td>
            </tr>
            <tr>
                <th class="texto4"> Password:</th>
                <td class="texto"><input name="password" type="password" size="20"></td>
            </tr>
            <tr>
                <th class="texto4">Nombre:</th>
                <td class="texto"><input name="nombre" type="text" size="20"></td>
            </tr>
            <tr>
                <th class="texto4">Email:</th>
                <td class="texto"><input name="email" type="text" size="20"></td>
            </tr>
            <tr>
                
      <th height="23" class="texto4">Sexo:</th>
                <td class="texto">
		  <input type="radio" name="sexo" value="H" checked class="texto">
        Hombre 
        <input type="radio" name="sexo" value="M" class="texto">
        Mujer</td>
            </tr>
			<tr>
                <th class="texto4">Avatar:</th>
                <td class="texto">
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
      </td>
            </tr>
        </table>
        <br>
        <center>
            <input name="pagemode" type="hidden" value="submit">
            <input type="submit" class="texto1" value="Añadir el nuevo usuario">
        </center>
        <hr>
        <center>
    <b><a href="Centro.jsp" class="texto">Volver a la Página Inicial [el 
    usuario no se añadirá]</a></b> 
  </center>
        </form>

</body>
</html>
