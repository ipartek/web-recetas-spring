
	</div> <!--  <div class="jumbotron"> -->

 </div> <!-- /container -->

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
 <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
 <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
	
<script>

	//recuperar idioma seleccionado del usuario
	var language = '${pageContext.response.locale}';
	console.debug("idioma seleccionado: " + language);
		
	//idiomas para datatables
	var castellano = {
		    "sProcessing":     "Procesando...",
		    "sLengthMenu":     "Mostrar _MENU_ registros",
		    "sZeroRecords":    "No se encontraron resultados",
		    "sEmptyTable":     "Ning�n dato disponible en esta tabla",
		    "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
		    "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
		    "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
		    "sInfoPostFix":    "",
		    "sSearch":         "Buscar:",
		    "sUrl":            "",
		    "sInfoThousands":  ",",
		    "sLoadingRecords": "Cargando...",
		    "oPaginate": {
		        "sFirst":    "Primero",
		        "sLast":     "�ltimo",
		        "sNext":     "Siguiente",
		        "sPrevious": "Anterior"
		    },
		    "oAria": {
		        "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
		        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
		    }
		};
	
	var ingles = {
		    "sEmptyTable":     "No data available in table",
		    "sInfo":           "Showing _START_ to _END_ of _TOTAL_ entries",
		    "sInfoEmpty":      "Showing 0 to 0 of 0 entries",
		    "sInfoFiltered":   "(filtered from _MAX_ total entries)",
		    "sInfoPostFix":    "",
		    "sInfoThousands":  ",",
		    "sLengthMenu":     "Show _MENU_ entries",
		    "sLoadingRecords": "Loading...",
		    "sProcessing":     "Processing...",
		    "sSearch":         "Search:",
		    "sZeroRecords":    "No matching records found",
		    "oPaginate": {
		        "sFirst":    "First",
		        "sLast":     "Last",
		        "sNext":     "Next",
		        "sPrevious": "Previous"
		    },
		    "oAria": {
		        "sSortAscending":  ": activate to sort column ascending",
		        "sSortDescending": ": activate to sort column descending"
		    }
		};
	
	var euskara  = {
		    "sProcessing":     "Prozesatzen...",
		    "sLengthMenu":     "Erakutsi _MENU_ erregistro",
		    "sZeroRecords":    "Ez da emaitzarik aurkitu",
		    "sEmptyTable":     "Taula hontan ez dago inongo datu erabilgarririk",
		    "sInfo":           "_START_ -etik _END_ -erako erregistroak erakusten, guztira _TOTAL_ erregistro",
		    "sInfoEmpty":      "0tik 0rako erregistroak erakusten, guztira 0 erregistro",
		    "sInfoFiltered":   "(guztira _MAX_ erregistro iragazten)",
		    "sInfoPostFix":    "",
		    "sSearch":         "Aurkitu:",
		    "sUrl":            "",
		    "sInfoThousands":  ",",
		    "sLoadingRecords": "Abiarazten...",
		    "oPaginate": {
		        "sFirst":    "Lehena",
		        "sLast":     "Azkena",
		        "sNext":     "Hurrengoa",
		        "sPrevious": "Aurrekoa"
		    },
		    "oAria": {
		        "sSortAscending":  ": Zutabea goranzko eran ordenatzeko aktibatu ",
		        "sSortDescending": ": Zutabea beheranzko eran ordenatzeko aktibatu"
		    }
		};
	
	
	//Seleccionar recurso de idioma
	var idioma = castellano;	
	switch(language) {
    case "eu":
    	idioma = euskara;	
        break;
    case "en":
    	idioma = ingles;	
        break;
    
	}
	
	
	//Esperara a que todo el DOM este cargado
	$(document).ready(function() {
		
		console.log('Documento Ready');		
		
	    $('.tablePlugin').DataTable({
	    		language: idioma	    
	    });
	    
	    
	    //Detectamos click en boton para añadir ingrediente
	    //mostramos Modal para rellenar y luego enviamos por ajax
	    //si todo va bien refrescamos la lista
	    
	    add_ingrediente();
	    
	    //detectar cambio foco en input 
	    /*
	    var inputUsuario = document.getElementById("nombreUsuario");
	    inputUsuario.value = "Pepe";
	    */
	    
	    //.text   = text
	    //.val    = value
	    //.html   = InnerHTML
	    
	    //$("h1").text("YUjuuuuuuuuuuuuu");
	    
	    //$("#nombreUsuario").val("Pepe");
	    
	    var iUsuario = $("#nombreUsuario"); 
	    var msgUSuario = $("#msgNombreUsuario");
	    
	    iUsuario.blur(function(){
	    	
	    	var valor = iUsuario.val();
	    	console.log('input usuario ha perdido focus value=' + valor);
	    	
	    	
	    	if ( valor.length > 2 ){	    		
	    		msgUSuario.css('color','grey');
	    		msgUSuario.text('comprobando nombre....');
	    		
	    		console.log('llamada Ajax al servidor');
	    		
	    		$.ajax("testCheckUser",{
	    			"type": "post",
	    			"encoding":"UTF-8",
	    			"data": { nombre: valor },
	    			"success": function(result) {
	    				console.log("Llego el contenido y no hubo error", result);
	    				msgUSuario.text("");
	    				msgUSuario.html(result);			
	    				
	    			},
	    			"error": function(result) {
	    				console.error("Este callback maneja los errores", result);
	    			}	    			
	    		});
	    		
	    		
	    		
	    	}else{	    	
	    		msgUSuario.css('color','red');
	    		msgUSuario.text('Por favor escribe un nombre mas largo');
	    	}
	    	
	    	
	    	
	    	
	    });
	    
	    
	    
	    
	});//$(document).ready
	
	function like(id_receta){
		console.log('click para hacer like en receta %s', id_receta);
		$span = $("#like" + id_receta);
		
		//Llamada a la API
		
		var url = "/formacion/api/receta/" + id_receta + "/likes/";
		$.ajax(url, {
			"type" : "put",
			"success" : function(result) {
				console.log("Llego el contenido %o y no hubo error", result);
				$span.text(result.likes);
			},
			"error" : function(result) {
				console.error("Este callback maneja los errores", result);
			}
		});

		console.log('finalizado like');
	}
	
	
	function add_ingrediente(){
		console.info('add_ingrediente');
		$msj = $("#form1_msg");
		var disabled;
		var flag = false;
		$idReceta = $('#id_receta').val();
		
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
		
		
		
		$("#btn_guardar_ingrediente").click(function(){
			
			disabled = $(this).hasClass("disabled");
			console.log('btn_guardar_ingrediente clicked, disabled: ' + disabled);
							
			if ( disabled ){
				$msj.html('Nombre Ingrdiente es obligatorio(mínimo 2 letras)');
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
				
				$.ajax(url, {
					"type" : "POST",
					"data" : JSON.stringify(formData),
					"processData" : false,
					"contentType" : "application/json",
					"success" : function(result) {
						console.log(
								"Llego el contenido %o y no hubo error",
								result);
						
					},
					
					"error" : function(result) {
						console.error("Este callback maneja los errores",
								result);
					}
				});
			}
			
		});
		
	}
	
</script>

</body>
</html>