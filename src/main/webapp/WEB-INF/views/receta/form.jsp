<%@ include file="../includes/header.jsp" %> 
<%@ include file="../includes/nav.jsp" %> 

<a href="receta">Volver</a>

<h1>Formulario Receta</h1>

${msg}


<form:form action="receta/crear" modelAttribute="receta">
	<div class="row">
		<div class="col-md-6">
			<form:hidden path="id" readonly="true"/><br>
			
			<form:label path="nombre">Nombre</form:label>
			<form:input class="form-control" path="nombre"/><br>	
			<form:errors path="nombre" cssStyle="color:red;"/>
			<br>
			
			<form:label path="imagen">Imagen</form:label>
			<form:input class="form-control" path="imagen"/><br>	
			<form:errors path="imagen" cssStyle="color:red;"/>
			<br>
			
			<form:label path="descripcion">Descripcion</form:label>
			<form:textarea class="form-control" rows="8" cols="80" path="descripcion"/><br>	
			<form:errors path="descripcion" cssStyle="color:red;"/>
			<br>
			
			<c:if test="${receta.id != -1}">
				<h3>Usuario de la Receta: ${receta.usuario.nombre}</h3>
				<br><br>
				<img class="tamImg img-circle" src="${receta.usuario.imagen}"/><br><br>
				
				<form:label path="usuario">Cambiar Usuario:</form:label>
			</c:if>
			
			<c:if test="${receta.id == -1}">
				<form:label path="usuario">Usuario:</form:label>
			</c:if>
				
			<form:select class="form-control" path="usuario.id">
				<c:forEach items="${usuarios}" var="u">
					<form:option value="${u.id}">${u.nombre}</form:option>
				</c:forEach>
			</form:select>
			
			
			<br><br>
			
		
			<c:choose>
				<c:when test="${receta.id == -1}">
					<form:button class="btn btn-success" type="submit">Crear</form:button>
				</c:when>
				<c:otherwise>
					<form:button class="btn btn-success" type="submit">Modificar</form:button>
				</c:otherwise>
			</c:choose>
		</div> <!-- <div class="col-md-6"> -->
		
		<div class="col-md-6">
			<img class="img-responsive" src="${receta.imagen}">
		</div> <!-- <div class="col-md-6"> -->
			
	</div> <!-- <div class="row"> -->		
</form:form>


<c:if test="${receta.id != -1}">
	<a class="enlaceEliminar" href="receta/delete/${receta.id}"><button class="btn btn-danger">Eliminar</button></a>

	<hr>
	
	<h2>Listado Ingredientes
		<span>
			<a href="receta/${receta.id}/recuperar/ingredientes"><button class="btn btn-primary">Añadir Ingredientes</button></a>
		</span>
	</h2>
	
		<ol>
			<c:forEach items="${receta.ingredientes}" var="ingrediente">
				<br>
				
				<li>${ingrediente.nombre} - ${ingrediente.cantidad}
					<span style="color:red;">
						<a href="receta/${receta.id}/delete/ingrediente/${ingrediente.id}"><button class="btn btn-danger">Eliminar</button></a>
					</span>
					
					<span>
						<a href="receta/${receta.id}/edit/ingrediente/${ingrediente.id}"><button class="btn btn-primary">Modificar cantidad</button></a>
					</span>
				</li>
				
			</c:forEach>
		</ol>
</c:if>	


<%@ include file="../includes/footer.jsp" %> 