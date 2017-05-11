//Detectamos click en boton para añadir ingrediente
//mostramos Modal para rellenar y luego enviamos por ajax
//si todo va bien refrescamos la lista

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
		
		
		
		$("#btn_guardar_ingrediente").click(function(){
			
			disabled = $(this).hasClass("disabled");
			console.log('btn_guardar_ingrediente clicked, disabled: ' + disabled);
							
			if ( disabled ){
				$msj.html('Nombre Ingrediente es obligatorio(mínimo 2 letras y maximo 255)');
			}else{
				console.info("llamada Ajax");
				
				var url = "/formacion/api/receta/" + $idReceta + "/ingrediente";
				console.log('url: %s', url);
				
				var inputNombre = $("#form1_nombre").val();
				var inputCantidad = $("#form1_cantidad").val();
				var inputGluten = $("#form1_gluten").prop("checked");
		    	
				
				var formData = {
						nombre : inputNombre,
						gluten : inputGluten,
						cantidad : inputCantidad
			    	};
				
				var li = "<li>" +
							"<a href='##enlaceModificar##'>##nombre##</a> - ##cantidad##<span style='color:red;'>" +
							"<a href='##enlaceBorrar##'>[ Eliminar ]</a></span>" + 
						"</li>";
				
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
/*		 					$("#list_ingredientes").append("<li>" +
		 							"<a href='receta/" + $idReceta + "/edit/ingrediente/" + result.id + "'>" + result.nombre + "</a> - " + result.cantidad +
		 							"<span style='color:red;'>" +
		 							"<a href='receta/" + $idReceta + "/delete/ingrediente/" + result.id + "'>[ Eliminar ]</a>" +
		 							"</span>" + 
		 							"</li>");
*/

							liAppend = li;
							liAppend = liAppend.replace("##nombre##", result.nombre);
							liAppend = liAppend.replace("##cantidad##", result.cantidad);
							liAppend = liAppend.replace("##enlaceModificar##", "receta/" + $idReceta + "/edit/ingrediente/" + result.id + "");
							liAppend = liAppend.replace("##enlaceBorrar##", "receta/" + $idReceta + "/delete/ingrediente/" + result.id + "");

							$("#list_ingredientes").append(liAppend);
							
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
						if(result.mensaje == null){
							$msj.html(result.mensaje);
						}
					}
				});
			}
			
		}); //#btn_guardar_ingrediente
}