	var storage = localStorage;
	var recetas_consultadas = [];
	
	$(function() {
		console.info('ready local storage.js');
		verTodos();
	});
		
	function guardar(nombre) {
		
		//comprobar que indice de la receta hay que incluir
		for (var i = 1; i < 6; i++) {
			var clave = 'receta' + i;
			var valor = storage.getItem(clave);
						
			if (valor == null){
				if (recetas_consultadas.indexOf(nombre) == -1){
					storage.setItem('receta' + i, nombre);
					break;
				}
			}else if (clave == 'receta5'){

				if (recetas_consultadas.indexOf(nombre) == -1){
					for (var j = 2; j < 6; j++) {
						clave = 'receta' + j;
						valor = storage.getItem(clave);
						
						storage.setItem('receta' + (j-1), valor);
					}
					
					storage.setItem(clave, nombre);
				}
			}
		}
		
		console.debug('Valor guardado ' + clave + '=' + valor);
		verTodos();
	}
	
	function eliminar(clave) {
		storage.removeItem(clave);
		console.debug('Valor eliminado con clave ' + clave);
	}
	
	function verTodos() {
		
		for (var i = 0; i < storage.length; i++) {
			var clave = storage.key(i);
			
			if(clave.match(/receta\d/) != null){
				var valor = storage.getItem(clave);
			
				recetas_consultadas.push(valor);
			
				console.debug('Valor obtenido ' + clave + '=' + valor);
			}
		}
		
		console.debug ('%o', recetas_consultadas);
		refrescar_lista (recetas_consultadas);
	}
	
	function compruebaCompatibilidad() {
		if (window.sessionStorage && window.localStorage) {
			console.debug('Tu navegador acepta almacenamiento local');
		} else { 
			console.debug('Lo siento, pero tu navegador no acepta almacenamiento local'); 
		}
	}
	
	function refrescar_lista(recetas_consultadas){
		
		var contenido = "";
		for (var i = 0; i < recetas_consultadas.length; i++) {
			contenido += '<li>' + recetas_consultadas[i] + '</li>';
		}
		
		document.getElementById('lista_consultadas').innerHTML = contenido;
		
	}
	