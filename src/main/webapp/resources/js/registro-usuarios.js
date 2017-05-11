
var iUsuario = $("#nombreUsuario");
var msgUSuario = $("#msgNombreUsuario");

iUsuario.blur(function() {

	var valor = iUsuario.val();
	console.log('input usuario ha perdido focus value=' + valor);

	if (valor.length > 2) {
		msgUSuario.css('color', 'grey');
		msgUSuario.text('comprobando nombre....');

		console.log('llamada Ajax al servidor');

		$.ajax("testCheckUser", {
			"type" : "post",
			"encoding" : "UTF-8",
			"data" : {
				nombre : valor
			},
			"success" : function(result) {
				console.log("Llego el contenido y no hubo error", result);
				msgUSuario.text("");
				msgUSuario.html(result);

			},
			"error" : function(result) {
				console.error("Este callback maneja los errores", result);
			}
		});

	} else {
		msgUSuario.css('color', 'red');
		msgUSuario.text('Por favor escribe un nombre mas largo');
	}

});
