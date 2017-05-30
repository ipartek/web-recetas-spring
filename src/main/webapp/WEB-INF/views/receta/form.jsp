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
	<li id="${ingrediente.id}-list">
		${ingrediente.nombre}-  <span id="ingrediente-cantidad-${ingrediente.id}">${ingrediente.cantidad}</span>
		<span style="color:red;">
			<button type="button" class="btn btn-default" onclick="eliminar_ingrediente(${ingrediente.id}, '${ingrediente.nombre}', ${receta.id})">
				<span class="glyphicon glyphicon-trash"></span>
			</button>	
		</span>
			<button type="button" class="btn btn-default" onclick="editar_ingrediente(${ingrediente.id}, '${ingrediente.nombre}', '${ingrediente.cantidad}', ${receta.id})">
				<span class="glyphicon glyphicon-pencil"></span>
			</button>
	</li>
</c:forEach>
</ol>

<!-- MODAL ELIMIAR INGREDIENTE -->
<div  id="modal-eliminar"class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
        <p>Seguro que desea eliminar el ingrediente: <b><span id="modal_eliminar_ingrediente_nombre"></span></b></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" id="dismiss-delete">No</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal" id="confirm-delete">Si, estoy seguro</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- END_:MODAL ELIMIAR INGREDIENTE -->


<!-- MODAL EDITAR INGREDIENTE -->
<div  id="modal-editar"class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Editar Ingrediente</h4>
      </div>
      <div class="modal-body">
        <h2 id="modal-ingrediente-editar-nombre"></h2>
        <input id="modal-ingrediente-editar-cantidad" type="text">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal"  id="dismiss-edit">No</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal" id="confirm-edit">Modificar</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- END_:MODAL EDITAR INGREDIENTE -->


<!-- Button trigger modal -->
<button type="button" class="btn btn-default" data-toggle="modal" data-target="#modal_ingrediente">
  Añadir Ingrediente
</button>


<div id="toast"></div>

<!-- Modal -->
<div class="modal fade" id="modal_ingrediente" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Nuevo Ingrediente</h4>
      </div>
      <div class="modal-body">
      
      		<!-- mensajes para el usuario -->	
      		<div id="form1_msg"></div>
      
        	<form action="#" class="form-horizontal">
        	
	        	<!-- nombre ingredientes -->
	        	 <div class="form-group">        	
	        	 	<label for="form1_nombre" class="col-sm-2 control-label">Nombre</label>
	        	 	<div class="col-sm-10">	
	        			<input type="text" value="" id="form1_nombre"	        			       
	        			       class="form-control"
	        			       placeholder="Obligatorio">	        				        			       
	        		</div>	
	        	 </div>
	        	 
	        	 <!-- cantidad  -->
	        	 <div class="form-group">        	
	        	 	<label for="form1_cantidad" class="col-sm-2 control-label">Cantidad</label>
	        	 	<div class="col-sm-10">	
	        			<input type="text" value=""
	        				   class="form-control" 
	        				   id="form1_cantidad" 
	        				   placeholder="Si no pones nada a Ojimetro">
	        		</div>	
	        	 </div>
        	
        	</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
        <button id="btn_guardar_ingrediente" type="button" class="disabled btn btn-primary">Guardar</button>
      </div>
    </div>
  </div>
</div>


<%@ include file="../includes/footer.jsp" %> 