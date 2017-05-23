var ingredientes =  [];
var ingrediente_seleccionado;

var receta_id = $("#id").val(); //campo hidden del formulario

$(function() {
	console.info('ready recetas.js');
	cargar_ingredientes();
});



function cargar_ingredientes(){
	console.info('cargando ingredientes....');
	
	url = "/formacion/api/receta/"+receta_id+"/ingrediente/";
	$.getJSON( url, function(data){
		console.debug('%o', data);
		$.each(data, function(i,v){
			ingredientes.push(v);
			refrescar_lista();			
		});
		//end $.each		
	});
	//end $.getJSON			
};

function refrescar_lista(){
	var lista = $("#list_ingredientes");
	var contenido = "";
	var li = '<li>##nombre##' +
				'<button type="button" title="Botón para eliminar ingrediente" ' +
					'class="btn btn-default"' + 
					'onClick="show_modal("eliminar")">' +
				  	'<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>'+
				'</button>'+			
				"</li>";
	
	$.each(ingredientes, function(i,v){
		
		contenido += li.replace("##nombre##", v.nombre);
		
	});	
	
	lista.html(contenido);
}

