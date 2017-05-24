<%@ include file="../includes/header.jsp" %> 

<a href="receta">Volver</a>

<h1>${receta.nombre}</h1>

${msg}



	<form:form action="receta/crear" modelAttribute="receta">
	
		<div class="row">
			<div class="col-md-6">
				<form:hidden id="id_receta" path="id"/><br>
				
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
						<a style="color:white;" href="receta/delete/${receta.id}">Eliminar</a>
						</button>
					</c:if>





<h2>Listado Ingredientes</h2>
<ol id="list_ingredientes">
<c:forEach items="${receta.ingredientes}" var="ingrediente">
	<li id="${ingrediente.id}ing">
		${ingrediente.nombre} - ${ingrediente.cantidad}
		<button type="button" title="Boton para modificar el ingrediente ${ingrediente.nombre}" onclick="modificar_ingrediente(${ingrediente.id}, '${ingrediente.nombre}', '${ingrediente.cantidad}')" class="btn btn-default" aria-label="Left Align">
		  <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
		</button> 
		<span style="color:red;">
			<button  type="button" title="Boton para eliminar el ingrediente ${ingrediente.nombre}" onclick="eliminar_ingrediente(${ingrediente.id}, '${ingrediente.nombre}')" class="btn btn-default" aria-label="Left Align">
			  <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
			</button>
		</span>
	</li>
</c:forEach>
</ol>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#modal_crear">
  Añadir ingrediente
</button>

<!-- Modal Crear-->
<div class="modal fade" id="modal_crear" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Nuevo Ingrediente</h4>
      </div>
      <div class="modal-body">
      	<div id="form1_msg"></div>
        <form action="#" class="form-horizontal">
        	
	        	<!-- nombre ingredientes -->
	        	 <div class="form-group">        	
	        	 	<label for="form1_nombre" class="col-sm-2 control-label">Ingrediente</label>
	        	 	<div class="col-sm-10 has-success">	
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

<!-- Modal Modificar -->
<div id="modal-modificar" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 id="titulo_modal_mod" class="modal-title"></h4>
      </div>
      <div class="modal-body">
      	<div id="form2_msg"></div>
        <form action="#" class="form-horizontal">
      	 
	        	 <!-- cantidad  -->
	        	 <div class="form-group">        	
	        	 	<label for="form2_cantidad" class="col-sm-2 control-label">Cantidad</label>
	        	 	<div class="col-sm-10">	
	        			<input type="text" value=""
	        				   class="form-control" 
	        				   id="form2_cantidad" 
	        				   placeholder="Si no pones nada a Ojimetro">
	        		</div>	
	        	 </div>
        	
        	</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Salir</button>
        <button id="btn-modificar-ingrediente" type="button" class="btn btn-warning" data-dismiss="modal">Modificar</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- End Modal Modificar -->

<!-- Modal Eliminar Ingrediente -->
<div id="modal-eliminar" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-body">
        <p>¿Desea eliminar el ingrediente <b id="modal_eliminar_ing_nombre"></b>?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
        <button id="btn-eliminar-ingrediente" type="button" class="btn btn-danger" data-dismiss="modal">Si estoy seguro</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- End Modal Eliminar Ingrediente -->

<%@ include file="../includes/footer.jsp" %> 