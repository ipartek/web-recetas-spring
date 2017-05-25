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
				nombre = $("#form1_nombre").val();
				var id_receta = $("#id_receta").val();
				var url  = "/formacion/api/receta/"+id_receta+"/ingrediente";
				var form = $("#formulario_nuevo_ingrediente");
				var li = "<li id=\"##ID_ING##ing\">"+
							"##nombre## - ##cantidad## "+
							"<button type=\"button\" title=\"Boton para modificar el ingrediente "+ nombre +"\" onclick=\"modificar_ingrediente(##ID_ING3## , '##nombre3##', '##cantidad2##')\" class=\"btn btn-default\" aria-label=\"Left Align\">" +
							  "<span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span>" +
							"</button>"+ 
					      	"<span style='color:red;'>" +
					      	"<button  type=\"button\" title=\"Boton para eliminar el ingrediente "+ nombre +"\" onclick=\"eliminar_ingrediente( ##ID_ING2## , '##nombre2##')\" class=\"btn btn-default\" aria-label=\"Left Align\">"+
							  "<span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span>"+
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
							liAppend = liAppend.replace("##nombre2##", data.nombre);
							liAppend = liAppend.replace("##nombre3##", data.nombre);
							liAppend = liAppend.replace("##ID_ING##", data.id);
							liAppend = liAppend.replace("##ID_ING2##", data.id);
							liAppend = liAppend.replace("##ID_ING3##", data.id);
							liAppend = liAppend.replace("##cantidad##", data.cantidad);
							liAppend = liAppend.replace("##cantidad2##", data.cantidad);
							liAppend = liAppend.replace("##enlace##", "http://localhost:8080/formacion/receta/"+ id_receta +"/edit/ingrediente/" + data.id);
							$("#list_ingredientes").append( liAppend );
							
							//limpiar campos
							$msj.html("");
							$("#form1_nombre").val("");
							$("#form1_cantidad").val("");
							
							//cerrar Modal
							$('#modal_crear').modal('hide');
							
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

	    $("#form1_nombre").autocomplete({
	      source: function( request, response ){
	    	  var id_receta = $("#id_receta").val();

	    	  var url  = "/formacion/api/receta/"+id_receta+"/ingrediente?disponibles=true&filter="+$("#form1_nombre").val();
	    	  $.ajax( {
	    		  url: url,
	    		  dataType: "json",
	              success: function( data ) {
	 
	            	var array_solo_nombres = [];
	            	$.each(data, function(index, ing){
	            		array_solo_nombres.push(ing.nombre);
	            	});
	                response( array_solo_nombres );
	              }
	    	 });
	    	 minLenght: 2
	      },
	     
	    });   
	    
	    
	
		  
	}

function eliminar_ingrediente( id , nombre ){
	console.debug('Eliminar_ingrediente:' + id + " " + nombre);
	$modal = $("#modal-eliminar");
	$('#modal_eliminar_ing_nombre').text(nombre);
	$modal.modal();
	
	$("#btn-eliminar-ingrediente").click(function(){
		
		var id_receta = $("#id_receta").val();
		var url  = "/formacion/api/receta/"+id_receta+"/ingrediente/" +id;
		console.debug(url);				
		$.ajax( url, {
			"type": "delete",
			"success": function(data) {				
				console.log("Eliminado");
				$("#"+id+"ing").remove();
			},
			"error": function(result) {
				console.error("No se ha podido eliminar");
			}			
		});
	});
}

var ingrediente_seleccionado;

function modificar_ingrediente( id , nombre , cantidad ){
	console.debug('Modificar_ingrediente:' + id + " " + nombre);
	ingrediente_seleccionado.id = id;
	ingrediente_seleccionado.nombre = nombre;
	$modal = $("#modal-modificar");
	$('#titulo_modal_mod').text("Modificando ingrediente " + nombre);
	//$('#form2_nombre').val(nombre);
	$('#form2_cantidad').val(cantidad);
	$modal.modal();
	
	$("#btn-modificar-ingrediente").click(function(){
		var cantidad_nueva = $("#form2_cantidad").val();
		var id_receta = $("#id_receta").val();
		var url  = "/formacion/api/receta/"+id_receta+"/ingrediente/";
		console.debug(url);				
		$.ajax( url, {
			"type": "put",
			"contentType": "application/json",
			"dataType": "json",
			"data": JSON.stringify(
					{	
						"id": id,
						"nombre": nombre,
						"cantidad": cantidad_nueva
					}),
			"success": function(data) {				
				console.log("Modificado");
				$("#"+id+"ing").html(nombre+ " - " + data.cantidad +" "+
				"<button type=\"button\" title=\"Boton para modificar el ingrediente "+ nombre +"\" onclick=\"modificar_ingrediente("+ id + " ,'"+nombre+"', '"+cantidad_nueva+"')\" class=\"btn btn-default\" aria-label=\"Left Align\">" +
				  "<span class=\"glyphicon glyphicon-pencil\" aria-hidden=\"true\"></span>" +
				"</button>"+ 
		      	"<span style='color:red;'> " +
		      	"<button  type=\"button\" title=\"Boton para eliminar el ingrediente "+ nombre +"\" onclick=\"eliminar_ingrediente( "+ id + " ,'"+nombre+"')\" class=\"btn btn-default\" aria-label=\"Left Align\">"+
				  "<span class=\"glyphicon glyphicon-trash\" aria-hidden=\"true\"></span>"+
				"</button>"+
			   	"</span>");
			},
			"error": function(result) {
				console.error("No se ha podido eliminar");
			}			
		});
	}); 
}
	

	