<%@ include file="../includes/header.jsp" %> 

<a href="receta">Volver</a>

<h1>${receta.nombre}</h1>

${msg}

	<form action="upload" method="post" enctype="multipart/form-data">
	
		<label for="imagen">Selecciona una imagen</label>
		<input type="file" id="image_file"name="imagen">
		<input type="hidden" name="idReceta" value="${receta.id}">
		<input type="hidden" name="rutaId" value="receta/edit/${receta.id}">
		<input id="subir-foto" type="submit" value="Subir nueva foto">
	<div id="preview"></div>
	</form>
	<script>
	document.getElementById('image_file').onchange = function(e){
		console.log('imagen seleccionada');
		var content = "";
		var file = e.target.files[0];
		var isValid = true;
		if(file.size > (1024*1024)){
			content += '<p style="color:red">El tama�o no puede superar 1MB</p>'
			isValid = false;
		}
		if( file.type !== 'image/jpeg'||file.type !== 'image/jpg'){
			content += '<p style="color:red">Solo se pueden subir imagenes en formato JPG</p>'
				isValid = false;
		}
		if(isValid){
		var reader = new FileReader();
		reader.onload = function (event){
		
		var image = new Image();
		image.src = event.target.result;
		image.width = 250;
		preview.appendChild(image);
		}
		reader.readAsDataURL(file);
		}else{
			document.getElementById('subir-foto').style.display = "none";
		}

		var preview = document.getElementById('preview');
		 content += "<p>" + file.name + " " + Math.round(file.size/1024) + "kb </p>";
		preview.innerHTML = content;
	}
	</script>

	<form:form action="receta/crear" modelAttribute="receta" >
	
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

<c:if test="${not empty imagenes}">
		<div class="panel panel-success">
			<div class="panel-heading">Imagenes</div>
  			<div class="panel-body">
			<div class="row">
				<c:forEach items="${imagenes}" var="i">
					<div class="col-md-4">
						<button type="button" class="btn btn-danger eliminar-imagen">
							<a href="receta/${receta.id}/eliminarImagen/${i.id}">Eliminar imagen</a>
						</button>
						<img class="tamImg img-thumbnail"  alt="imagen-${i.id}" src="http://localhost:8080/uploads/${i.url}">					
					</div>	
				</c:forEach>
			</div>
			</div>
		</div>
</c:if>
 
<h2>Listado Ingredientes</h2>
<ol id="list_ingredientes">
</ol>
 

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#modal_crear">
  A�adir ingrediente
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
        <button id="btn_guardar_ingrediente" type="button" onclick="anadir_ingrediente()" class="btn btn-primary">Guardar</button>
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
        <button id="btn-modificar-ingrediente" type="button" onclick="modificar_ingrediente()" class="btn btn-warning" data-dismiss="modal">Modificar</button>
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
        <p>�Desea eliminar el ingrediente <b id="texto-eliminar-ing"></b>?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
        <button id="btn-eliminar-ingrediente" type="button" onclick="eliminar_ingrediente()" class="btn btn-danger" data-dismiss="modal">Si estoy seguro</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- End Modal Eliminar Ingrediente -->

<input type="button" value="toast" onClick="show_toast('lorem ipsum...')"/>

<%@ include file="../includes/scripts.jsp" %>

<script src="resources/js/recetas.js"></script>

<script src="resources/js/toast.js"></script>

<%@ include file="../includes/footer.jsp" %> 
