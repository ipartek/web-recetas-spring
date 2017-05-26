<%@ include file="../includes/header.jsp" %> 

<a href="receta">Volver</a>

<h1>${receta.nombre}</h1>

${msg}


	<form action="upload" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="id" value="${receta.id}">
		<label for="imagen">Selecciona una imagen...</label>
		<input type="file" name="imagen">
		
		<input type="submit" value="añadir nueva foto">
	</form>

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

<div class="margenarriba row">
<c:if test="${not empty imagenes}">
	<c:forEach items="${imagenes}" var="img">
		<div class="imagengaleria col-md-3">
			<img src="http://localhost:8080/uploads/${img.url}" class="tamImg img-thumbnail">
			<span class="spangaleria"><a class="margenbotoneliminar btn btn-default" href="receta/${receta.id}/eliminarImagen/${img.id}" role="button"><i class="fa fa-times" aria-hidden="true"></i></a></span>
		</div>
	</c:forEach>
</c:if>
</div>


<h2>Listado Ingredientes</h2>
<ol id="list_ingredientes">

</ol>

<!--  Modal Eliminar Ingrediente -->
<div id="modal-eliminar" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-body">
      	<p>¿Desea eliminar el ingrediente?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
        <button type="button" onclick="eliminar_ingrediente()" class="btn btn-danger" data-dismiss="modal">Si, estoy seguro</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--  END: Modal Eliminar Ingrediente -->



<!--  Modal Modificar Ingrediente -->
<div id="modal-modificar" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="ingredientemodificar">Modificar Ingrediente</h4>
      </div>
      <div class="modal-body">
      	<label for="cantidad_ingrediente_modificar">Cantidad: </label>
      	<input type="text" id="cantidad_ingrediente_modificar"/>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
        <button type="button" onclick="modificar_ingrediente()" class="btn btn-danger" data-dismiss="modal">Si, estoy seguro</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--  END: Modal Modificar Ingrediente -->



<!-- Button trigger modal -->
<button type="button" class="btn btn-default" data-toggle="modal" data-target="#modal_ingrediente">
  Añadir Ingrediente
</button>

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
        <button onclick="anadir_ingrediente()" type="button" class="btn btn-primary">Guardar</button>
      </div>
    </div>
  </div>
</div>

<input type="button" value="toast" onClick="show_toast('lorem ipsum....')">
<div id="toast"></div>
<style>
	@keyframes show {
    	0% {
    		bottom: -100px;
    	}
    	100% {
    		bottom: 75px;
    	}
	}	
		
	
	#toast{
		    width: 40%;
		    min-height: 35px;
		    padding: 10px 20px;
		    background-color: #000;
		    color: #FFF;
		    font-size: 1.3em;
		    text-align: center;
		    opacity: 0.8;
		    margin: 0 auto;
		    position: fixed;
		    bottom: -100px;
		    right: 20px;
		   /* 
		    animation:show 2s; 
		    animation-fill-mode: forwards;
		    */
	}
	
	
</style>
<script>
	var toastWidget = (function () {
		//private 
        var duration = 3000;
 
        function showPrivate(text) {
        	console.debug('toastWidget:init(%s) %s', duration, text );
        }
 
        //public
        return {
        	show: showPrivate
        };
 
    })();
	//usarlo	
	toastWidget.show( 'pepe' );	
	
	//No se puede cambiar es privado
	toastWidget.duration = 1000;
	toastWidget.show( 'pepe2' );
	
	
	
	
	
	function show_toast( texto ){
		console.debug('show_toast: ' + texto);
		var toast = document.getElementById('toast');
		toast.style.animation = "show 2s";
		toast.style.animationDirection = "normal";
		toast.style.animationFillMode = "forwards";
		
		toast.innerHTML = texto;
		
		setTimeout(function(){
			toast.style.animation = "none";
		 }, 3000);
		
	};
</script>

<%@ include file="../includes/footer.jsp" %> 