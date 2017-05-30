	
	
	$(function() {
		console.info('ready search-receta.js');
	});
		
	
	
	/*** Autocomplete ***/
	
	function autocomplete(){
		console.log ('autocomplete recetas');
		$("#buscar_receta").autocomplete({
			source: function( request, response){
				var url = "/formacion/api/receta/" + $idReceta + "/ingrediente?disponibles=true&filter=" + $("#form1_nombre").val().trim() + "";
				//console.log("url para autocomplete: %s", url);
				
				$.ajax( {
					"url" : url,
					"type" : "GET",
					"dataType": "json",					
					"success" : function(data) {
						
						var ingredientes_disponibles = [];
						
						$.each(data, function(i, v){
							ingredientes_disponibles.push(v.nombre)	;
						});
						
						response (ingredientes_disponibles);
						
						console.log("Llego el contenido %o y no hubo error", ingredientes_disponibles);
						
					}
					
				});
			},
			
			minLength: 2
			
		});
	}