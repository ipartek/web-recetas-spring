function gestion_recetas(){
	
		console.info('add_ingrediente');
		$msj = $("#form1_msg");
		var disabled;
		var flag = false;
		
		$("#form1_nombre").keyup(function(){
			var longitud = $(this).val().length;
			
			console.log('pulsada tecla, longitud nombre %s', longitud);
			if ( longitud >= 2){
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
		});
		//form1_nombre
		
		
		
		$("#btn_guardar_ingrediente").click(function(){
			
			disabled = $(this).hasClass("disabled");
			console.log('btn_guardar_ingrediente clicked, disabled: ' + disabled);
							
			if ( disabled ){
				$msj.html('<i>*Nombre Ingrediente es obligatorio(mínimo 2 letras)</i>');
			}else{
				console.info("llamada Ajax");
				
				var id_receta = $("#id").val();
				var url  = "/formacion/api/receta/"+id_receta+"/ingrediente";
				var form = $("#formulario_nuevo_ingrediente");
				var li = "<li id='##id_ingrediente##listaing'>"+
							"<a href='##enlace##'>##nombre##</a> - ##cantidad##"+ 
							"<span style='color:red;'>"+
							"<button type='button' class='btn btn-default' title='Boton para eliminar ingrediente ##nombre1##' onclick='eliminar_ingrediente(##id_ingrediente1##,\"##nombre2##\",##iddelareceta##)' aria-label='Left Align'>"+
				  				"<span class='glyphicon glyphicon-trash' aria-hidden='true'></span>"+
							"</button>"+
						"</span>"+
						  "</li>";
								
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
							liAppend = li;
							liAppend = liAppend.replace("##nombre##", data.nombre);
							liAppend = liAppend.replace("##nombre1##", data.nombre);
							liAppend = liAppend.replace("##nombre2##", data.nombre);
							liAppend = liAppend.replace("##cantidad##", data.cantidad);
							liAppend = liAppend.replace("##id_ingrediente##", data.id);
							liAppend = liAppend.replace("##id_ingrediente1##", data.id);
							liAppend = liAppend.replace("##iddelareceta##", id_receta);
							$("#list_ingredientes").append( liAppend );
							
							//limpiar campos
							$msj.html("");
							$("#form1_nombre").val("");
							$("#form1_cantidad").val("");
							
							//cerrar Modal
							$('#modal_ingrediente').modal('hide');
							
						}else{
							$msj.html("");
							$msj.html(data.error);
						}	
						
					},
					"error": function(result) {
						console.error("Este callback maneja los errores", result);
						$msj.html("");
						$msj.html(result.responseJSON.error);
					}			
				});
			}
			
		});
		//btn_guardar_ingrediente
		
		
		    $( "#form1_nombre" ).autocomplete({
		    	source: function(request, response){
		    		var id_receta = $("#id").val();
		    		var url  = "/formacion/api/receta/"+id_receta+"/ingrediente?disponible=true";
		    		$.ajax( url, {
						dataType: "json",
						"success": function(data) {
							var array_solo_nombre = [];
							$.each(data, function(index, ing){
								array_solo_nombre.push(ing.nombre);
							});
							response(array_solo_nombre);
						}
		    		});
		    	}
		    	
		    });
	}


function eliminar_ingrediente( idingrediente , nombreingrediente , idreceta ){
	console.debug('eliminar_ingrediente ' + idingrediente + " " + nombreingrediente);
	var ventanamodal = $('#modal-eliminar');
	$('#modal_eliminar_ing_nombre').text(nombreingrediente);
	ventanamodal.modal();
	
	var url  = "/formacion/api/receta/"+ idreceta +"/ingrediente/"+ idingrediente +"/";
	console.debug(url);
	$("#borrado_seguro").click(function(){
		$.ajax( url, {
			"type": "delete",
			"contentType": "application/json",
			"success": function(data) {
				console.debug('Ingrediente Eliminado');
				$('#'+idingrediente+'listaing').remove();
			},
			"error": function(data) {
				console.debug('function error');
			}			
		});
		
	});
}

function modificar_ingrediente(idingrediente , cantidadingrediente , idreceta, nombreingrediente){
	console.debug('modificar_ingrediente ' + idingrediente);
	var ventanamodalmodif = $('#modal-modificar');
	$('#ingredientemodificar').text(nombreingrediente);
	ventanamodalmodif.modal();
	$('#cantidad_ingrediente_modificar').val(cantidadingrediente);
	
	var url  = "/formacion/api/receta/"+ idreceta +"/ingrediente/"+ idingrediente +"/";
	console.debug(url);
	$("#modificado_seguro").click(function(){
		$.ajax( url, {
			"type": "put",
			"contentType": "application/json",
			"data": JSON.stringify(
					{	
						"id": idingrediente,
						"cantidad": $("#cantidad_ingrediente_modificar").val()
					}),
			"success": function(data) {
				console.debug('Ingrediente Modificado');
				$('#'+idingrediente+'listaing').html(nombreingrediente+ " - "+data.cantidad+" "+ 
						"<span style='color:red;'>"+
						"<button type='button' class='btn btn-default' title='Boton para modificar ingrediente"+nombreingrediente+"' onclick='modificar_ingrediente("+idingrediente+","+data.cantidad+","+idreceta+","+nombreingrediente+")' aria-label='Left Align'>"+
							"<span class='glyphicon glyphicon-pencil' aria-hidden='true'></span>"+
						"</button>"+	
						"<button type='button' class='btn btn-default' title='Boton para eliminar ingrediente"+data.nombre+"' onclick='eliminar_ingrediente("+data.id+","+nombreingrediente+","+idreceta+")' aria-label='Left Align'>"+
			  				"<span class='glyphicon glyphicon-trash' aria-hidden='true'></span>"+
						"</button>"+
					"</span>");
			},
			"error": function(data) {
				console.debug('function error');
			}			
		});
		
	});
}



	