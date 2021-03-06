<%@ include file="../includes/header.jsp" %> 

<a href="receta">Volver</a>

<h1>${receta.nombre}</h1>

${msg}



	<form:form action="receta/crear" modelAttribute="receta">
	
		<div class="row">
			<div class="col-md-6">
				<form:hidden path="id"/><br>
				
				<form:label path="nombre">Nombre</form:label>
				<form:input path="nombre"/><br>	
				<form:errors path="nombre" cssStyle="color:red;"/>
				<br>
				
				<form:label path="imagen">Imagen</form:label>
				<form:input path="imagen"/><br>	
				<form:errors path="imagen" cssStyle="color:red;"/>
				<br>
				
				<form:label path="descripcion">Descripcion</form:label>
				<form:textarea rows="8" cols="80" path="descripcion"/><br>	
				<form:errors path="descripcion" cssStyle="color:red;"/>
			</div>	
			
			<div class="col-md-6">
				<img class="img-responsive" src="${receta.imagen}">	
			</div>
			
		
		</div> <!--  <div class="row"> -->
		
		<div class="row">
			<div class="col-md-6">
				<c:choose>
					<c:when test="${receta.id == -1}">						
							<form:button type="submit">Crear</form:button>							
					</c:when>
					<c:otherwise>						
						<form:button type="submit">Modificar</form:button>						
					</c:otherwise>
					
				</c:choose>
			</div>
		
			<div class="col-md-6">
				<h2>Cocinero ${receta.usuario.nombre}</h2>
				<img src="${receta.usuario.imagen}" class="tamImg img-circle">
				<h3>Cambiar de Cocinero</h3>
				<form:select path="usuario.id">
					<c:forEach items="${usuarios}" var="usuario">
						<form:option value="${usuario.id}">
							${usuario.nombre}
						</form:option>
					</c:forEach>
				</form:select>				
			</div>	
		</div>
		
		
	</form:form>


<c:if test="${receta.id != -1}">
						<button type="button" class="btn btn-danger">
						<a style="color:red;" href="receta/delete/${receta.id}">Eliminar</a>
						</button>
					</c:if>





<h2>Listado Ingredientes</h2>
<ol id="list_ingredientes">
<c:forEach items="${receta.ingredientes}" var="ingrediente">
	<li id="ingrediente${ingrediente.id}">
		<!-- <a href="receta/${receta.id}/edit/ingrediente/${ingrediente.id}">${ingrediente.nombre}</a> - ${ingrediente.cantidad}  -->
		${ingrediente.nombre} - <span id="cantidadIng">${ingrediente.cantidad}</span> 
		<button type="button" class="btn btn-default" title="Bot�n para modificar ingrediente ${ingrediente.nombre}" onclick="modificar_ingrediente(${ingrediente.id}, '${ingrediente.nombre}', '${ingrediente.cantidad}', ${ingrediente.gluten})">
	  		<span class="glyphicon glyphicon-pencil"></span>
		</button>
			
		<span style="color:red;">
			<button type="button" class="btn btn-default" title="Bot�n para eliminar ingrediente ${ingrediente.nombre}" onclick="show_modal_eliminar_ingrediente(${ingrediente.id},'${ingrediente.nombre}')">
	  			<span class="glyphicon glyphicon-trash"></span>
			</button>
		</span>
	</li>
</c:forEach>
</ol>


<!-- Modal Eliminar Ingrediente -->
<div id="modal-elimnar" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<p>� Desea eliminar el ingrediente <b id="modal_eliminar_ingrediente_nombre"></b> ?</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
				<button type="button" class="btn btn-danger" data-dismiss="modal">Si estoy seguro</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- End: Modal Eliminar Ingrediente -->


<!-- Modal Modificar Ingrediente -->
<div id="modal-modificar" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-body">
				<h4 class="modal-title" id="myModalLabel">Modificar ingrediente <b id="modal_modificar_ingrediente_nombre"></b></h4>
				<form id="modificar-ingrediente" class="form-horizontal" action="#">
			
					<input id="id_receta" type="hidden" value="${receta.id}">
					
										
					<!-- cantidad -->
					<div class="form-group">
						<label for="form1_cantidad" class="col-sm-2 control-label">Cantidad:</label>
						<div class="col-sm-10">
							<input id="form2_cantidad" class="form-control" type="text" value="" placeholder="Si no se indica nada, a ojimetro...">
						</div>
					</div>
					
					
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
				<button id="btn_modificar_ingrediente" type="button" class="btn btn-primary" data-dismiss="modal">Modificar Ingrediente</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- End: Modal Modificar Ingrediente -->


<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#modal_ingrediente">A�adir ingrediente</button>

<!-- Modal -->
<div class="modal fade" id="modal_ingrediente" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Nuevo ingrediente</h4>
			</div>
			<div class="modal-body">
				
				<!-- mensajes para el usuario -->
				<div id="form1_msg"></div><br>
				
				<form id="crear-ingrediente" class="form-horizontal" action="#">
				
					<input id="id_receta" type="hidden" value="${receta.id}">
					
					<!-- Nombre Ingrediente -->
					<div class="form-group">
						<label for="form1_nombre" class="col-sm-2 control-label">Nombre:</label>
						<div class="col-sm-10">
							<input id="form1_nombre" class="form-control" type="text" value="" placeholder="Obligatorio" required pattern="[A-Za-z]{2}" autofocus>
						</div>
					</div>
										
					<!-- cantidad -->
					<div class="form-group">
						<label for="form1_cantidad" class="col-sm-2 control-label">Cantidad:</label>
						<div class="col-sm-10">
							<input id="form1_cantidad" class="form-control" type="text" value="" placeholder="Si no se indica nada, a ojimetro...">
						</div>
					</div>
					
					
					<label for="form1_gluten">�Contiene gluten?</label>
					<input id="form1_gluten" type="checkbox" checked>
					
				</form>
 			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
				<button id="btn_guardar_ingrediente" type="button" class="disabled btn btn-primary">Guardar Ingrediente</button>
			</div>
		</div>
	</div>
</div>

<%@ include file="../includes/footer.jsp" %> 