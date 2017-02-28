
<!-- 
		Gestion de Mensajes para el usuario.<br>
		Debemos enviar un atributo 'msg' desde el Servlet.<br>
 --> 

	<c:if test="${msg != null}">
		<div class="alert ${msg.clase} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			${msg.descripcion}
		</div>
	</c:if>
	
