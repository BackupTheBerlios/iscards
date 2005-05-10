   /*
    * Funci�n validaForm: Devuelve true si los datos
    * del formulario son v�lidos. False en caso contrario.
    */
   function validaForm(formulario) {
       var valido = true;
       var error = "";


       // Expresi�n regular para los correos electr�nicos
       re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/

       if (!re.test(formulario.email.value)) {
	  valido = false;
	  error = "La direcci�n de correo electr�nico no es v�lida";
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

