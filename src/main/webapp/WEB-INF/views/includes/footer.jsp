		</div> <!-- <div class="jumbotron"> -->
	</div> <!-- /container -->

	<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
	
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
		
		//detectar cambio foco en input con javascript
		/*var inputUsuario = document.getElementById("nombreUsuario");
		inputUsuario.value = "Pepe";*/
		
		//detectar cambio foco en input con ajax
		//$("#nombreUsuario").val("Pepe");
		
		// cambia el texto de todos los h1
		//$("h1").text("YUjuuuuuuuuuuuu");
		
		//declarar una variable de una seleccion
		var iUsuario = $("#nombreUsuario");
		var msgUsuario = $("#msgNombreUsuario");
		
		//Cuando salga del input hay que comprobar el nombre del usuario --> se usa .blur()
		iUsuario.blur(function(){
			var valor = $(this).val();
				
			console.log('input usuario ha perdido focus value = ' + valor);
			
			if (valor.length > 2){
				
				msgUsuario.css('color', 'grey');
				msgUsuario.text('comprobando nombre...');
				console.log(valor);
				console.log('llamada Ajax al servidor');
				
				
				$.ajax("testCheckUser", {
					//TODO por get
					"type" : "post", // usualmente post o get
					//"contentType" : "application/x-www-form-urlencoded; charset=UTF-8",
					"encoding" : "UTF-8",
					"data" : {nombre: valor},
					"success" : function(result) {
						console.log("Llego el contenido y no hubo error", result);
						msgUsuario.text("");
						msgUsuario.html(result);
					},
					"error" : function(result) {
						console.error("Este callback maneja los errores", result);
					}
				});
				
			/*} else {
				msgUsuario.css('color', 'red');
				msgUsuario.text('Por favor escribe un nombre mas largo');
			*/
			}

		});

				$('.tablePlugin').DataTable({
					language : idioma
				});

			});//$(document).ready
</script>
	
</body>
</html>