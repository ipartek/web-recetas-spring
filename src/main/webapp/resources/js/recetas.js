function gestion_recetas(){
	
		console.info('add_ingrediente');
		$msj = $("#form1_msg");
		var disabled;
		var flag = false;

		
		$("#form1_nombre").keyup(function(){
			var filter = $("#form1_nombre").val();
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
				var li = "<li id='##id-ingrediente2##-list'>"+
							"##nombre## - <span id='ingrediente-cantidad-##id-ingrediente3##'>##cantidad##</span>"+ 
					      	"<span style='color:red;'>" +
					      	'<button type="button" class="btn btn-default" onclick="eliminar_ingrediente(##id-ingrediente##, \'##nombre2##\', ##id-receta##)">'+
					      		'<span class="glyphicon glyphicon-trash"></span>'+
							'<button type="button" class="btn btn-default" onclick="editar_ingrediente(##id-ingrediente4##, \'##nombre3##\', \'##cantidad2##\', ##id-receta2##)">'+
								'<span class="glyphicon glyphicon-pencil"></span>'+
							'</button>'+
							'</button>'+
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
							liAppend = liAppend.replace("##nombre2##", data.nombre);
							liAppend = liAppend.replace("##nombre3##", data.nombre);
							liAppend = liAppend.replace("##cantidad##", data.cantidad);		
							liAppend = liAppend.replace("##cantidad2##", data.cantidad);	
							liAppend = liAppend.replace("##id-ingrediente##", data.id);	
							liAppend = liAppend.replace("##id-ingrediente2##", data.id);	
							liAppend = liAppend.replace("##id-ingrediente3##", data.id);
							liAppend = liAppend.replace("##id-ingrediente4##", data.id);
							liAppend = liAppend.replace("##id-receta##", id_receta);
							liAppend = liAppend.replace("##id-receta2##", id_receta);
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
			var id_receta = $("#id").val();
			

	       $("#form1_nombre").autocomplete({
	    	   source: function( request, response ) {
	    	        $.ajax( {
	    	          url: "/formacion/api/receta/"+id_receta+"/ingrediente?disponibles=true&filter="+$("#form1_nombre").val(),
	    	          dataType: "json",
	    	          success: function( data ) {
	    	        	  nombres = [];
	    	        	  $.each(data, function(i,v){
	    	        		  nombres.push(v.nombre);
	    	        	  });
	    	        	  response( nombres );
	    	        	  console.log(nombres);
	    	          },
						error: function(data) {
							console.error("Este callback maneja los errores", data);

						}	
	    	        } );
	    	      }
	    	    } );
	}

function eliminar_ingrediente ( id_ingrediente, nombre, id_receta ){
	console.info('eliminando: INGREDIENTE:ID:' + id_ingrediente +' NOMBRE: '+ nombre + ' RECETA ID : '+id_receta);
	$("#modal-eliminar").modal();
	$("#modal_eliminar_ingrediente_nombre").text(nombre);
	$("#dismiss-delete").click(function(){
		$("#confirm-delete").unbind();
	})
	$("#confirm-delete").click(function(){
		
		$.ajax({
		    url: "/formacion/api/receta/"+id_receta+"/ingrediente/"+id_ingrediente,
		    type: 'DELETE',
		    success: function(result) {
		        console.info('eliminado INGREDIENTE:ID:' + id_ingrediente +' NOMBRE: '+ nombre + ' RECETA ID : '+id_receta)
		        var css_id_ingrediente = "#" + id_ingrediente + '-list';
		        $(css_id_ingrediente).remove();
		    }
		});
	})
	
	
}

function editar_ingrediente (id_ingrediente, nombre, cantidad, id_receta){
	console.info('editando: INGREDIENTE:ID:' + id_ingrediente +' NOMBRE: '+ nombre + ' RECETA ID : '+id_receta + ' CANTIDAD :' + cantidad);
	$("#modal-editar").modal();
	$("#modal-ingrediente-editar-nombre").text(nombre);
	//$("#modal-ingrediente-editar-cantidad").val(cantidad);
	$("#modal-ingrediente-editar-cantidad").val($("#ingrediente-cantidad-"+id_ingrediente).text());
	$("#dismiss-edit").click(function(){
		$("#confirm-edit").unbind();
	})
	$("#confirm-edit").click(function(){
		$.ajax({
			url: "/formacion/api/receta/"+id_receta+"/ingrediente/",
			"type": "put",
			"contentType": "application/json",
			"dataType": "json",
			"data": JSON.stringify(
						{									
							"id": id_ingrediente,
							"nombre": nombre,
							"cantidad" : $("#modal-ingrediente-editar-cantidad").val()
						}),
			"success": function(data) {			
				console.log("Llego el contenido %o", data);		
				var css_cantidad_ingrediente = "#ingrediente-cantidad-" + data.id;
		        $(css_cantidad_ingrediente).text(data.cantidad);
				
			},
			"error": function(result) {
				console.error("Este callback maneja los errores", result);
				
			}			
		});
	})
}

	