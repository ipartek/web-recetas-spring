var ingredientes =  [];
var ingrediente_seleccionado;
var posicion;
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
					'onClick="show_modal(\'modal-elimnar\')">' +
				  	'<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>'+
				'</button>'+			
				"</li>";
	
	$.each(ingredientes, function(i,v){
		
		contenido += li.replace("##nombre##", v.nombre);
		
	});	
	
	lista.html(contenido);
	//registar click ingrediente seleccionado
	$("#list_ingredientes li").click(function(){
	    posicion=$(this).index();
		ingrediente_seleccionado =ingredientes[posicion];
		console.debug('ingrediente seleccionado %o', ingrediente_seleccionado);
		
	});
}

function show_modal(id){
	$("#"+id).modal();
	
}

function eliminar_ingrediente(){
	var id_receta = $("#id").val();
	
	url = "/formacion/api/receta/"+receta_id+"/ingrediente/"+ ingrediente_seleccionado.id;
	console.info('boton pulsado')
	console.info("llamada Ajax");
	$.ajax({
		 url: url,
		 "type": "delete",
		 success: function( data ){
			 console.info("Borroooooooooo ingrediente: %o" ,ingrediente_seleccionado )
			 ingredientes.splice(posicion, 1);
			 refrescar_lista();
		 }
	});
	
}
