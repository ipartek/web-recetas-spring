//idiomas para datatables
	var castellano = {
		    "sProcessing":     "Procesando...",
		    "sLengthMenu":     "Mostrar _MENU_ registros",
		    "sZeroRecords":    "No se encontraron resultados",
		    "sEmptyTable":     "Ningï¿½n dato disponible en esta tabla",
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
		        "sLast":     "ï¿½ltimo",
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
	
	
$(document).ready(function() {
	 $('.tablePlugin').DataTable({
 		language: idioma	    
	 });	   
});	

	