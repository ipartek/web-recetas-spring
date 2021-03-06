<%@ include file="../includes/header.jsp" %> 

<a href="receta">Volver</a>

<h1>${receta.nombre}</h1>

${msg}

	<form action="upload" method="post" enctype="multipart/form-data">
		<div class="box__input">
			<input type="hidden" name="ruta" value="receta/edit/${receta.id}">
			<input type="hidden" name="id" value="${receta.id}">
			
			<label for="imagen">Selecciona una imagen</label>
			<input id="image_file" type="file" name="imagen">
			<br>
			<input type="submit" value="Subir Nueva Foto">
		
		</div>
		<div id="preview"></div>
	</form>


	<script>
		
	document.getElementById("image_file").onchange = function(e){
		console.log('imagen seleccionada');
		var file = e.target.files[0];
		
		var preview = document.getElementById("preview");
		var content ="";
		
		//validacion
		var isvalid = true;
		if ( file.size > (1024 * 1024 * 1) ){ //1Mb
			content += '<p style="color:red">No puede ser superior a 1Mb</p>';
			isvalid = false;
		}
		
		var mime_types = ['image/jpeg', 'image/jpg']
		if (mime_types.indexOf(file.type) == -1) {
			content += '<p style="color:red">Solo se pueden subir fotos con formato .jpeg</p>';
			isvalid = false;
		}
		
		
		
		if(isvalid){
			var reader = new FileReader();
			reader.onload = function (event) {
				var image = new Image();
				image.src = event.target.result;
				image.width = 250;
				preview.appendChild(image);
			};
			reader.readAsDataURL(file);
		}
		
		content += "<p>" + file.name + " " + Math.round(file.size / 1024) + "kb </p>";
		preview.innerHTML = content;
		
	};
		
	</script>



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
			
			<div>
				<ul id="galeria">
					<c:forEach items="${receta.imagenes}" var="i">
					
						<li class="tamLiImg">
							<img class="img-responsive img-thumbnail" alt="${i.nombre}" src="http://localhost:8080/uploads/${i.nombre}">
						</li>
			
					</c:forEach>
				</ul>
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

</ol>


<!-- Modal Eliminar Ingrediente -->
<div id="modal-eliminar" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">      
      <div class="modal-body">
        <p>�Desea eliminar el ingrediente <b id="modal_eliminar_ingrediente_nombre"></b> ?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
        <button onClick="eliminar_ingrediente();" type="button" class="btn btn-danger" data-dismiss="modal">Si estoy seguro</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- End: Modal Eliminar Ingrediente -->


<!-- Modal Modificar Ingrediente -->
<div id="modal-modificar" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">      
      <div class="modal-header">
		<h4>Modificar Ingrediente <b id="modal_eliminar_ingrediente_nombre"></b> </h4>
	</div>
      
      <div class="modal-body">
      	<br>
		<form id="crear-ingrediente" class="form-horizontal" action="#">
	        <!-- cantidad -->
			<div class="form-group">
				<label for="form2_cantidad" class="col-sm-2 control-label">Cantidad:</label>
				<div class="col-sm-10">
					<input id="form2_cantidad" class="form-control" type="text" value="" placeholder="Si no se indica nada, a ojimetro...">
				</div>
			</div>
		</form>
		<br>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
        <button onClick="modificar_ingrediente();" type="button" class="btn btn-primary'" data-dismiss="modal">Modificar Ingrediente</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- End: Modal Eliminar Ingrediente -->

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
				<!-- <button id="btn_guardar_ingrediente" type="button" class="disabled btn btn-primary">Guardar Ingrediente</button> -->
				<button id="btn_guardar_ingrediente" type="button" class="disabled btn btn-primary" onclick="insertar_ingrediente()">Guardar Ingrediente</button>
			</div>
		</div>
	</div>
</div>


<%@ include file="../includes/scripts.jsp" %>

<script src="resources/js/recetas.js"></script>
<script src="resources/js/toast.js"></script>

<%@ include file="../includes/footer.jsp" %> 



