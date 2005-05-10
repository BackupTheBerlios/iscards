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

