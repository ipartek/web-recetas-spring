	var ingredientes = [];
	var ingrediente_seleccionado;
	var receta_id = $("#id").val(); //campo hidden del formulario
	var posicion;
	
	$(function() {
		console.info('ready recetas.js');
		cargar_ingredientes();
	});
	
	function cargar_ingredientes(){
		console.info('cargando ingredientes...');
		
		var url = "/formacion/api/receta/" + receta_id + "/ingrediente/";
		
		$.getJSON(url, function(data) {
			console.debug('%o', data);
			$.each( data, function(i,v){
				ingredientes.push(v);
				refrescar_lista();
			});
			//end $.each
		});
		// end $.getJSON
		
	};
	
	function refrescar_lista(){
		var lista = $('#list_ingredientes');
		var contenido = "";
		var li = '<li>##nombre## ' +
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
		
		//registrar click ingrediente seleccionado
		$('#list_ingredientes li').click(function(){
			posicion = $(this).index();
			ingrediente_seleccionado = ingredientes[posicion];
			console.debug('ingrediente seleccionado %o', ingrediente_seleccionado);
		});
		
	};
	
	function show_modal( id ){
		$("#" + id).modal();
		//$("#modal_eliminar_ingrediente_nombre").text(ingrediente_seleccionado.nombre);
	}
	
	function eliminar_ingrediente(){
		
		var url = "/formacion/api/receta/" + receta_id + "/ingrediente/" + ingrediente_seleccionado.id + "";

		$.ajax({
			"url" : url,
			"type" : "DELETE",
			"success" : function(data) {
				console.info("elimino ingrediente %o", ingrediente_seleccionado);
				ingredientes.splice(posicion,1);
				refrescar_lista();
				
			},
			
			"error" : function(result) {
				console.error("Este callback maneja los errores",
						result);
				if(result.mensaje != null){
					$msj.html(result.mensaje);
				}
			}
		});
	}
		
	function insertar_ingrediente(){
	
		var url = "/formacion/api/receta/" + receta_id + "/ingrediente";
		
		var inputNombre = $("#form1_nombre").val();
		var inputCantidad = $("#form1_cantidad").val();
		var inputGluten = $("#form1_gluten").prop("checked");
    	
		var formData = {
				nombre : inputNombre,
				gluten : inputGluten,
				cantidad : inputCantidad
	    	};
		
		$.ajax(url, {
			"type" : "POST",
			"data" : JSON.stringify(formData),
			"processData" : false,
			"contentType" : "application/json",
			"success" : function(result) {
				console.log("Llego el contenido %o y no hubo error", result);
				
				if(result.mensaje == null){
					console.log('refrescar listado de ingredientes');
					
					//refrescar lista
					ingredientes.push(result);
					refrescar_lista();					
					
 					//cerrar modal
 					$('#modal_ingrediente').modal('hide');
 					
 					
				}else{
					console.log('Hay mensaje para el usuario');
					$msj.html(result.mensaje);
				}
			},
			
			"error" : function(result) {
				console.error("Este callback maneja los errores",
						result);
				if(result.mensaje != null){
					$msj.html(result.mensaje);
				}
			}
		});
		
	}
	
	
	
	