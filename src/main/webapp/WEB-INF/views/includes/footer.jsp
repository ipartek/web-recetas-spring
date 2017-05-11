
	</div> <!--  <div class="jumbotron"> -->

 </div> <!-- /container -->

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
 <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
 <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
 <script>
	//recuperar idioma seleccionado del usuario
	var language = '${pageContext.response.locale}';
	console.debug("idioma seleccionado: " + language);
		
 </script>
 <script src="resources/js/datatables-custom.js"></script>
 <script src="resources/js/registro-usuario.js"></script>
 <script src="resources/js/likes.js"></script>
 <script src="resources/js/recetas.js"></script>

<script>

	//Esperara a que todo el DOM este cargado
	$(document).ready(function() {
		
		console.log('Documento Ready');		
		
		gestion_recetas();

	});//$(document).ready
	
</script>

</body>
</html>