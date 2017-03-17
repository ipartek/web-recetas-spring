$("#nombreUsuario").blur(function() {
	$("msgNombreUsuario").css("fontSize", "4px");
	console.log("El usuario " + $(this).val() + " ha perdido el foco");
	if ($("#nombreUsuario").val().length > 2) {
		console.log("Llamada ajax al servidor");
		$.ajax("api/receta/testCheckReceta", {
			"type" : "get", // usualmente post o get
			"data" : {
				nombre : $("#nombreReceta").val()
			},
			"datatype" : 'json',
			"success" : function(result, textStatus, xhr) {
				console.log("Llego el contenido y no hubo error", result);
				successReceta(xhr);
			},
			"error" : function(result) {
				console.error("Este callback maneja los errores", result);
			}
		});
	} else {
		$("#msgNombreUsuario").css("color", "red");
		$("#msgNombreUsuario").text("Escribe un nombre mas largo");
	}
});


function crearNuevaReceta() {
	$.ajax("api/receta/", {
		"type" : "post", // usualmente post o get
		"data" : {
			id: $("#id").val(),
			nombre : $("#nombreReceta").val(),
			imagen : $("#imagen").val(),
			decscripcion : $("#descripcion").val()
		},
		"datatype" : 'json',
		"success" : function(result, textStatus, xhr) {
			console.log("Llego el contenido y no hubo error", result);
			successReceta(xhr);
		},
		"error" : function(result) {
			console.error("Este callback maneja los errores", result);
		}
	});
}

function successReceta(xhr) {
	if (xhr.status == 204) {
		$("#msgNombreUsuario").css("color", "grey");
		$("#msgNombreUsuario").css("te", "grey");
		$("#msgNombreUsuario").text(
				"El nombre " + $("#nombreUsuario").val() + " es valido");
	} else if (xhr.status == 200) {
		$("#msgNombreUsuario").css("color", "red");
		$("#msgNombreUsuario").text(
				"El nombre " + $("#nombreUsuario").val() + " no es valido");
		$("#nombreUsuario").val(" ");
	}
	return result;
}

