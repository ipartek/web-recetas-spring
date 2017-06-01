window.onload = function() {
	console.info('Cargada ventana con script de local storage');
	cargarListaUltimasRecetas();
	desplegar();
};

var storage = localStorage;

function guardarListaUltimasRecetas(nombreReceta, urlReceta) {
	var oReceta;
	var oReceta2;
	
	for(var i=1; i<6 ; i++) {
		console.info(i);
		oReceta = JSON.parse(storage.getItem("claveReceta#"+i));
		if (oReceta != null) {
			console.info("Existe en lista el elemento -> " + oReceta['data-name'], oReceta['data-url']);
			if (nombreReceta == oReceta['data-name'] ) {
				console.info("la receta clicada ya esta guardada en localStorage");
				oReceta2 = storage.getItem("claveReceta#"+i);
				for (j=i; j <6; j++){
					oReceta = storage.getItem("claveReceta#"+(j+1));
					if (oReceta != null )
						storage.setItem("claveReceta#"+j, oReceta);
					else {
						storage.setItem("claveReceta#"+j, oReceta2);
						break;
					}
				}
				
				break;
			}
		} else {
			var newOReceta = JSON.stringify({ "data-name": nombreReceta, "data-url": urlReceta});
			storage.setItem("claveReceta#"+i, newOReceta);
			break;
		} 
		if (i == 5){
			for(var j=2; j<6; j++) {
				oReceta2 = storage.getItem("claveReceta#"+j);
				storage.setItem("claveReceta#"+(j-1), oReceta2);
			}
			var newOReceta = JSON.stringify({ "data-name": nombreReceta, "data-url": urlReceta});
			storage.setItem("claveReceta#"+i, newOReceta);
		}
	}
}

function cargarListaUltimasRecetas() {
	var oReceta;
	for(var i=1; i<6 ; i++) {
		console.info(i);
		oReceta = JSON.parse(storage.getItem("claveReceta#"+i));
		if (oReceta != null) {
			$('#listaUltimasRecetas').prepend('<li><a href="' + oReceta['data-url'] + '">' + oReceta['data-name']+ '</a></li>');
			console.info(oReceta['data-name'], oReceta['data-url']);
		} else {
			break;
		}	
	}
}

function desplegar() {
	$('#barra').click(function() {
		$('#widget').toggleClass("show" , 1000);
		if( $(this).find('.fa').hasClass("fa-caret-square-o-left")) {
			$('.fa-caret-square-o-left').removeClass( "fa-caret-square-o-left" ).addClass( "fa-caret-square-o-right" );
		} else {
			$('.fa-caret-square-o-right').removeClass( "fa-caret-square-o-right" ).addClass( "fa-caret-square-o-left" );
		}
	  });
}