var ingredientes = [];
var ingrediente_seleccionado;
var receta_id = $("#id").val();
var posicion;

$(function(){
	console.info('ready recetas.js');
	cargar_ingredientes();
});


function cargar_ingredientes(){
	console.info('cargando ingredientes...');
	
	url = "/formacion/api/receta/" + receta_id + "/ingrediente/";
	$.getJSON(url, function(data){
		console.debug('%o', data);
		$.each(data, function(i,v){
			ingredientes.push(v);
			refrescar_lista();
		});
		//end $.each
	});
	// end $.getJSON
}

function refrescar_lista(){
	var lista = $("#list_ingredientes");
	var contenido = "";
	var li = '<li>##nombre## - ##cantidad## '+
				'<button class="btn_modificar_ingrediente" type="button" title="Boton para Modificar el ingrediente ##nombre2##"'+ 
					'class="btn btn-default" aria-label="Left Align">'+
				  '<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>'+
				'</button> '+
				' <button class="btn_eliminar_ingrediente" type="button" title="Boton para eliminar el ingrediente ##nombre3##" '+
					'class="btn btn-default" aria-label="Left Align">'+
				  '<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>'+
				'</button>'+
		
				'</li>';
	
	//for
	$.each(ingredientes, function(i,v){
		
		contenido += li.replace("##nombre##", v.nombre);
		contenido = contenido.replace("##nombre2##", v.nombre);
		contenido = contenido.replace("##nombre3##", v.nombre);
		contenido = contenido.replace("##cantidad##", v.cantidad);
	});
	
	lista.html(contenido);
	
	//registrar click ingrediente seleccionado
	$("#list_ingredientes li .btn_eliminar_ingrediente").click(function(){
		posicion = $(this).parent().index();
		ingrediente_seleccionado = ingredientes[posicion];
		console.debug('ingrediente seleccionado %o', ingrediente_seleccionado);
		
		$('#texto-eliminar-ing').text(ingrediente_seleccionado.nombre);
		$("#modal-eliminar").modal();
	});
	
	$("#list_ingredientes li .btn_modificar_ingrediente").click(function(){
		posicion = $(this).parent().index();
		ingrediente_seleccionado = ingredientes[posicion];
		console.debug('ingrediente seleccionado %o', ingrediente_seleccionado);
		
		$('#titulo_modal_mod').text("Modificando el ingrediente " + ingrediente_seleccionado.nombre);
		$('#form2_cantidad').val(ingrediente_seleccionado.cantidad);
		$("#modal-modificar").modal();
	});
}


function eliminar_ingrediente(){
	
	var url  = "/formacion/api/receta/"+receta_id+"/ingrediente/" +ingrediente_seleccionado.id + "/";
	$.ajax( url, {
		"type": "delete",
		"success": function(data) {				
			console.info("Eliminado ingrediente %o", ingrediente_seleccionado);
			console.debug('%o', ingredientes);

			ingredientes.splice(posicion,1);
			console.debug('%o', ingredientes);
			show_toast(ingrediente_seleccionado.nombre + " eliminado correctamente");
			refrescar_lista();
		},
		"error": function(result) {
			console.error("No se ha podido eliminar");
			show_toast("Error al eliminar el " + ingrediente_seleccionado.nombre);
		}			
	});
}

function anadir_ingrediente(){
	
	var url  = "/formacion/api/receta/"+receta_id+"/ingrediente";
	
	$.ajax( url, {
		"type": "post",
		"contentType": "application/json",
		"dataType": "json",
		"data": JSON.stringify(
					{									
						"nombre": $("#form1_nombre").val(),
						"cantidad": $("#form1_cantidad").val()
					}),
		"success": function(data) {
			
			console.log("Llego el contenido %o", data);
			
			if ( data.error == undefined ){
			
				//refrescar lista
				ingredientes.push(data);
				refrescar_lista();
				show_toast(data.nombre + " añadido correctamente");
				$('#modal_ingrediente').modal('hide');
			}else{
				show_toast("El ingrediente ya existe");
				$('#modal_ingrediente').modal('hide');
			}	
			
		},
		"error": function(result) {
			console.error("Este callback maneja los errores", result);
			
		}			
	});
}

function modificar_ingrediente(){
	
		cantidad_nueva = $('#cantidad_ingrediente_modificar').val();
		var url  = "/formacion/api/receta/"+receta_id+"/ingrediente/";
		console.debug(url);				
		$.ajax( url, {
			"type": "put",
			"contentType": "application/json",
			"dataType": "json",
			"data": JSON.stringify(
					{	
						"id": ingrediente_seleccionado.id,
						"nombre": ingrediente_seleccionado.nombre,
						"cantidad": cantidad_nueva
					}),
			"success": function(data) {
				ingredientes[posicion] = data;
				//Alternativa = ingredientes.splice(posicion,1,data);
				refrescar_lista();
				console.log("Modificado");
				show_toast(ingrediente_seleccionado.nombre + " modificado correctamente");
			},
			"error": function(result) {
				console.error("No se ha podido modificar");
				show_toast("Error al modificar el " + ingrediente_seleccionado.nombre);
			}			
		
	}); 
}
