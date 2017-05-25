	var ingredientes = [];
	var ingrediente_seleccionado;
	var receta_id = $("#id").val(); //campo hidden del formulario
	var posicion;
	
	$(function() {
		console.info('ready recetas.js');
		cargar_ingredientes();
		gestion_recetas();
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
		var li = '<li>##nombre## - ##cantidad## ' +
					'<button type="button" title="Botón para modificar ingrediente" class="btn btn-default btn-modificar-ingrediente" >' +
				  		'<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>'+
				  	'</button>'+				
				  	'<button type="button" title="Botón para eliminar ingrediente" class="btn btn-default btn-eliminar-ingrediente" >' +
				  		'<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>'+
				  	'</button>'+
			"</li>";
		
		$.each(ingredientes, function(i,v){
			contenido += li.replace("##nombre##", v.nombre).replace("##cantidad##", v.cantidad);
		});
		
		lista.html(contenido);
		
		//registrar click ingrediente seleccionado
		$('#list_ingredientes li .btn-eliminar-ingrediente').click(function(){
			posicion = $(this).parent().index();
			ingrediente_seleccionado = ingredientes[posicion];
			console.debug('ingrediente seleccionado %o', ingrediente_seleccionado);
			
			$('#modal_eliminar_ingrediente_nombre').text(ingrediente_seleccionado.nombre);
			$("#modal-eliminar").modal();
		});
		
		//registrar click ingrediente seleccionado
		$('#list_ingredientes li .btn-modificar-ingrediente').click(function(){
			posicion = $(this).parent().index();
			ingrediente_seleccionado = ingredientes[posicion];
			console.debug('ingrediente seleccionado %o', ingrediente_seleccionado);
			
			$('#form2_cantidad').val(ingrediente_seleccionado.cantidad);
			$("#modal-modificar").modal();
		});
		
	};
	
/*	function show_modal( id ){
				
		if(id = 'modal-modificar'){
			$('#form2_cantidad').val(ingrediente_seleccionado.cantidad);
		}
		
		$("#" + id).modal();
		//$("#modal_eliminar_ingrediente_nombre").text(ingrediente_seleccionado.nombre);
	}
*/
	
	function eliminar_ingrediente(){
		
		var url = "/formacion/api/receta/" + receta_id + "/ingrediente/" + ingrediente_seleccionado.id + "";

		$.ajax({
			"url" : url,
			"type" : "DELETE",
			"success" : function(data) {
				console.info("elimino ingrediente %o", ingrediente_seleccionado);
				ingredientes.splice(posicion,1);
				refrescar_lista();
				
				show_toast('El ingrediente se ha eliminado correctamente');
			},
			
			"error" : function(result) {
				console.error("Este callback maneja los errores",
						result);
				if(result.mensaje != null){
					$msj.html(result.mensaje);
					show_toast(result.mensaje);
				}else{
					show_toast('No se ha podido modificar. Intentelo de nuevo.');
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
 					
 					show_toast('El ingrediente se ha insertado correctamente');
 					
				}else{
					console.log('Hay mensaje para el usuario');
					$msj.html(result.mensaje);
					show_toast(result.mensaje);
				}
			},
			
			"error" : function(result) {
				console.error("Este callback maneja los errores",
						result);
				if(result.mensaje != null){
					$msj.html(result.mensaje);
					show_toast(result.mensaje);
				}else{
					show_toast('No se ha podido insertar. Compruebe que los datos son correctos.');
				}
			}
		});
		
	}
	
	function modificar_ingrediente(){
				
		var url = "/formacion/api/receta/" + receta_id + "/ingrediente";

		var formData = {
				id : ingrediente_seleccionado.id,
				nombre : ingrediente_seleccionado.nombre,
				gluten : ingrediente_seleccionado.gluten,
				cantidad : $('#form2_cantidad').val()
	    	};
		
		$.ajax(url, {
			"type" : "PUT",
			"data" : JSON.stringify(formData),
			"processData" : false,
			"contentType" : "application/json",
			"success" : function(result) {
				console.log("Llego el contenido %o y no hubo error", result);
				
				if(result.mensaje == null){
					console.log('refrescar listado de ingredientes');
					
					//refrescar lista
					ingredientes.splice(posicion, 1, result);
					refrescar_lista();					
					
 					//cerrar modal
 					$('#modal_ingrediente').modal('hide');
 					
 					show_toast('El ingrediente se ha modificado correctamente');
 					
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
					show_toast(result.mensaje);
				}else{
					show_toast('No se ha podido modificar. Intentelo de nuevo.');
				}
			}
		});
		
	}
	
	function gestion_recetas(){
		console.info('add_ingrediente');
		$msj = $("#form1_msg");
		var disabled;
		var flag = false;
		$idReceta = $('#id_receta').val();

		$("#form1_nombre").keyup(function(){
			var longitud = $(this).val().trim().length
			
			console.log('pulsada tecla, longitud nombre %s', longitud);
			if ( longitud >= 2 && longitud <=255){
				disabled = true;
				$("#btn_guardar_ingrediente").removeClass("disabled");				
				$(this).parent().addClass("has-success");
				if ( !flag ){
					$(this).after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
					flag = true;
				}				
			}else{
				disabled = false;				
				flag=false;
				$("#btn_guardar_ingrediente").addClass("disabled");
				$(this).parent().removeClass("has-success");				
				$(this).siblings().remove();
			}						
		}); //#form1_nombre
		
		
		/*** Autocomplete ***/
		
		console.log ('autocomplete ingrediente disponibles');
		$("#form1_nombre").autocomplete({
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
	