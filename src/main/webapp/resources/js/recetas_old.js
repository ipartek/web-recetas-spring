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
				var li = "<li>"+
							"<a href='##enlace##'>##nombre## - ##cantidad##</a>"+ 
					      	"<span style='color:red;'>" +
								"<a href='#'>[ Eliminar ]</a>"+
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
							liAppend = liAppend.replace("##cantidad##", data.cantidad);							
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
		
		
		/* autocomplete */		
		
		console.log('autocomplete ingrediente disponibles');
		url = "/formacion/api/receta/"+$("#id").val()+"/ingrediente?disponibles=true"
		$("#form1_nombre").autocomplete({
		     		
			source: function( request, response ) {
		        $.ajax( {
		          url: url,
		          dataType: "json",		          
		          success: function( data ) {
		        	var array_solo_nombres = [];
		        	$.each(data, function(i, v){
		        		array_solo_nombres.push(v.nombre);
		        	});	
		            response( array_solo_nombres );
		          }
		        } );
		      },
			
		      minLength: 2
	    });

	}

var ingrediente_seleccionado;

function show_modal_eliminar_ingrediente( id , nombre ){
	
	ingrediente_seleccionado.id = id;
	ingrediente_seleccionado.nombre = nombre;
	
	console.info('eliminar_ingrediente %o', ingrediente_seleccionado);
	$("#modal-elimnar").modal();
	$("#modal_eliminar_ingrediente_nombre").text(nombre);
	
};

function eliminar_ingrediente(){
	
	$("#btn_eliminar_ingrediente").click(function(){
		var id_receta = $("#id").val();
		
		url = "/formacion/api/receta/"+id_receta+"/ingrediente/"+ id;
		console.info('boton pulsado')
		console.info("llamada Ajax");
		$.ajax({
			 url: url,
			 "type": "delete",
			 success: function( data ){
				 console.info("Borroooooooooo ingrediente: " +id )
				 $("#ingrediente"+id+"_list").remove();
				 
			 }
			
		});
	});
	
	
	
	
	
}
	