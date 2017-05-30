	
$(document).ready(function() {  
	var nombre = document.getElementById('nombre').value;
 	var id = document.getElementById('id').value;

		if (window.sessionStorage && window.localStorage) { 
			 console.info('Tu navegador acepta almacenamiento local');
			 guardar(nombre, id)
	 } 
	 else { 
		 console.info('Lo siento, pero tu navegador no acepta almacenamiento local'); 
	 }  
		
		
		function guardar(nombre, id) {
			 var recetita = { "nombre": nombre, "id": id };
			 localStorage.setItem(localStorage.length, JSON.stringify(recetita));
			 console.info("localstorage saved")
			 console.info('Valor guardado ' + nombre + '=' + id);
		}
		
		function eliminar(clave) {
			 storage.removeItem(clave);
			 console.info('Valor eliminado con clave ' + clave);
		}
		function verTodos() {
			 for (var i=0; i < storage.length; i++) {
				 var clave = storage.key(i);
				 var valor = storage.getItem(clave);
				 console.info('Valor obtenido ' + clave + '=' + valor);
			 }
		}
});
	