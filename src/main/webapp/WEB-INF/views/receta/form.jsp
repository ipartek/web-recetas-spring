<%@ include file="../includes/header.jsp" %> 

<a href="receta">Volver</a>

<h1>${receta.nombre}</h1>

${msg}

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

<form action="upload" method="post" enctype="multipart/form-data">
	
		<label for="imagen">Selecciona una imagen</label>
		<input type="file" id="image_file" name="imagen">
		<input type="hidden" name="idReceta" value="${receta.id}">
		<input type="hidden" name="rutaId" value="receta/edit/${receta.id}">
		<input type="submit" value="Subir nueva foto">
		<div id="preview"></div>
	</form>
	
<script>

	document.getElementById("image_file").onchange = function(e){
		console.debug('Imagen seleccionada');
		var file = e.target.files[0];
		var content = "";
		var preview = document.getElementById("preview");
		
		
		//validacion
		isvalid = true;
		 if (file.size > (1024 * 1024)) { // 1MB
			 content += '<p style="color:red;">No puede ser de tamaño superior a 1Mb</p>';
			 isvalid = false;
		 }
		
		var mime_types = ['image/jpeg','image/jpg'];
		if (mime_types.indexOf(file.type) == -1) {
			content += '<p style="color:red;">Solo se pueden subir fotor con formato .jpeg</p>';
			isvalid = false;
		}
		if (isvalid) {
			var reader = new FileReader();
			reader.onload = function (event) {
				var image = new Image();
				image.src = event.target.result;
				image.width = 250;
				preview.appendChild(image);
			
			};
			reader.readAsDataURL(file);
		}
		content +="<span>" + file.name + " " + Math.round(file.size /1024) + "Kb</span><br>";
		preview.innerHTML = content;
	}


</script>

<c:if test="${not empty imagenes}">
		<br>
		<h2>Listado de imagenes</h2>
		<div class="row">
		<c:forEach items="${imagenes}" var="i">
			<div class="centrar col-md-4">
						<img class="tamImg img-thumbnail"  alt="imagen" src="http://localhost:8080/uploads/${i.url}">			
				<button type="button" class="adiosimagen btn btn-danger">
					<a style="color:white;text-decoration:none;" href="receta/${receta.id}/eliminarImagen/${i.id}"><i class="fa fa-times" aria-hidden="true"></i></a>
				</button>
			</div>	
		</c:forEach>
		</div>
</c:if>

<h2>Listado Ingredientes</h2>
<ol id="list_ingredientes">
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
        <p>¿Desea eliminar el ingrediente <b id="texto-eliminar-ing"></b>?</p>
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
<div id="toast">TEXTO</div>
<style>
	#toast{
		width: 50%;
		min-height: 35px;
		padding: 5px 20px;
		background-color: #000;
		color: #FFF;
		font-size: 1.3em;
		text-align:center;
		opacity: 0.8;
		margin: 0 auto;
		border-radius: 15px;
		position: fixed;
		bottom: -35px;
		left: 28%;
		/*
		animation: show 2s;
		animation-fill-mode: forwards;
		*/
	}

	@keyframes show {
		0% {
			bottom: -35px;
		}
		100% {
			bottom: 100px;
		}
	}
	
</style>
<script>
	var toastWidget = {
		
		duration: 3000,
		
		show: function(text){
			console.debug('toastWidget:init(%s) %s',this.duration, text );
		}
		
	};

	//usarlo
	
	toastWidget.show('pepe');
	
	toastWidget.duration = 1000;
	toastWidget.show('pepe2');
	
	function show_toast(texto){
		console.debug('show_toast: ' + texto);
		var toast = document.getElementById('toast');
		toast.style.animation = "show 2s";
		toast.style.animationFillMode = "forwards";
		toast.innerHTML = texto;
		setTimeout(function(){
			toast.style.animation = "none";
		}, 3000);
	}

</script>
<%@ include file="../includes/footer.jsp" %> 