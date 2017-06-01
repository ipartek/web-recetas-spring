	/*
	 * Busscador en la barra de navegacion para buscar recetas mediante la API 
	 * Busca recetas que coincidan su nombre, minimo 3 letras
	 * Ejemplo:: http://localhost:8080/formacion/api/receta/?filter=anc
	 * 
	 */
	
	"use-strict"
	
	$(function() {
		console.debug('search-receta.js ready');
		
		/*** Autocomplete ***/
		$( "#buscar_receta" ).autocomplete({
			source: function( request, response){
								
				var url = "/formacion/api/receta/?filter=" + $("#buscar_receta").val().trim() + "";
				
				$.ajax( {
					"url" : url,
					"type" : "GET",
					"dataType": "json",					
					"success" : function(data) {
						
						//recetas = data;
						var aString = [];
	
						$.each(data, function(i, v){
							aString.push({'label': v.nombre, 'value': v.id})	;
						});
						
						response (aString);
						
						console.log("Llego el contenido %o y no hubo error", aString);
			            
					}
					
				});
				
			},
		      minLength: 3,
		      select: function( event, ui ) {
		        console.debug( "Selected: " + ui.item.label + " con id: " + ui.item.value);
		        		        
		        window.location.href = 'receta/edit/' + ui.item.value + '';
		      }
		    });
		
	});
		
