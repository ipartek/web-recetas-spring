function like(id_receta){
		console.log('click para hacer like en receta %s', id_receta);
		$span = $("#like" + id_receta);
		
		//Llamada a la API
		
		var url = "/formacion/api/receta/" + id_receta + "/likes/";
		$.ajax(url, {
			"type" : "put",
			"success" : function(result) {
				console.log("Llego el contenido %o y no hubo error", result);
				$span.text(result.likes);
			},
			"error" : function(result) {
				console.error("Este callback maneja los errores", result);
			}
		});

		console.log('finalizado like');
	}