function like(id_receta) {
	console.log('clik para hacer like en receta %s', id_receta);

	// llamada a la API
	var url = "/formacion/api/receta/" + id_receta + "/likes";
	$.ajax(url, {
		"type" : "put",
		"success" : function(result) {
			console.log("Llego el contenido %o", result);
			$span = $("#like" + id_receta);
			$span.text(result.likes);

		},
		"error" : function(result) {
			console.error("Este callback maneja los errores", result);
		}
	});

	console.log('finalizado like');
}