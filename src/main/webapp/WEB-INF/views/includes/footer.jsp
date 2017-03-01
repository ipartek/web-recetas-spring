
	</div> <!--  <div class="jumbotron"> -->

 </div> <!-- /container -->

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
 <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
 <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>

<script>
	$(document).ready(function() {
		
	    $('.tablePlugin').DataTable({
	    		language: {
	    		    "sProcessing":     "Procesando...",
	    		    "sLengthMenu":     "Mostrar _MENU_ registros",
	    		    "sZeroRecords":    "No se encontraron resultados",
	    		    "sEmptyTable":     "Ningún dato disponible en esta tabla",
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
	    		        "sLast":     "Último",
	    		        "sNext":     "Siguiente",
	    		        "sPrevious": "Anterior"
	    		    },
	    		    "oAria": {
	    		        "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
	    		        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
	    		    }
	    		}
	    
	    });
	    
	    
	});//$(document).ready
	
</script>

</body>
</html>