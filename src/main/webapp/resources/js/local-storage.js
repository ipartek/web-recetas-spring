	var storage = localStorage;

	$(function() {
		console.info('ready local storage.js');
	});
		
	function guardar(nombre) {
		
		//comprobar que indice de la receta hay que incluir
		for (var i = 1; i < 6; i++) {
			var clave = 'receta' + i;
			var valor = storage.getItem(clave);
			
			if (valor == null){
				storage.setItem('receta' + i, nombre);
				break;
			}else if (clave == 'receta5'){
				eliminar('receta1');
				for (var j = 2; j < 6; j++) {
					var clave = 'receta' + i;
					var valor = storage.getItem(clave);
					
					//eliminar(clave);
					//storage.removeItem('receta' + (i-1));
					storage.setItem('receta' + (i-1), valor);
				}
				
				storage.setItem(clave, nombre);
			}
		}
		
		console.debug('Valor guardado ' + clave + '=' + valor);
	}
	
	function eliminar(clave) {
		storage.removeItem(clave);
		alert('Valor eliminado con clave ' + clave);
	}
	
	function verTodos() {
		for (var i = 0; i < storage.length; i++) {
			var clave = storage.key(i);
			var valor = storage.getItem(clave);
			alert('Valor obtenido ' + clave + '=' + valor);
		}
	}
	
	function compruebaCompatibilidad() {
		if (window.sessionStorage && window.localStorage) {
			alert('Tu navegador acepta almacenamiento local');
		} else { 
			alert('Lo siento, pero tu navegador no acepta almacenamiento local'); 
		}
	}
	
			
	