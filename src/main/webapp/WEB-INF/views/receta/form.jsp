<%@ include file="../includes/header.jsp"%>

<a class="btn btn-primary" href="receta"> <i
	class="fa fa-arrow-circle-left" aria-hidden="true"></i></a>

<h1>${receta.nombre}</h1>

${msg}



<form:form modelAttribute="receta">
	<div class="row">
		<div class="col-md-6">
			<form:hidden path="id" id="id" />

			<div class="form-group">
				<form:label path="nombre">Nombre</form:label>
				<form:input class="form-control" path="nombre" id="nombreReceta" />
				<br>
				<form:errors path="nombre" cssStyle="color:red;" />
				<p id="msgNombreUsuario"></p>

			</div>
			<div class="form-group">
				<form:label path="imagen">Imagen</form:label>
				<form:input class="form-control" path="imagen" id="imagen" />
				<br>
				<form:errors path="imagen" cssStyle="color:red;" />

			</div>
			<div class="form-group">
				<form:label path="descripcion">Descripcion</form:label>
				<form:textarea class="form-control" rows="8" cols="80"
					id="descripcion" path="descripcion" />

				<form:errors path="descripcion" cssStyle="color:red;" />
			</div>
			<c:if test="${receta.id == -1}">
				<div class="form-group">
					<form:select class="form-control" path="usuario.id">
						<c:forEach items="${usuarios}" var="usuario">
							<form:option value="${usuario.id}">
							${usuario.nombre}
						</form:option>
						</c:forEach>
					</form:select>
				</div>
			</c:if>

		</div>

		<c:if test="${receta.id != -1}">
			<div class="col-md-6">
				<br> <img width="100%" height="300px" class="img-rounded"
					src="${receta.imagen}">
			</div>
		</c:if>

	</div>
	<!--  <div class="row"> -->

	<div class="row">
		<div class="col-md-6">
			<c:choose>
				<c:when test="${receta.id == -1}">
					<form:button onclick="crearNuevaReceta()" class="btn btn-success"
						type="submit">Crear</form:button>
				</c:when>
				<c:otherwise>
					<form:button class="btn btn-warning" type="submit">Modificar</form:button>
				</c:otherwise>

			</c:choose>
			<c:if test="${receta.id != -1}">
				<a class="btn btn-danger" href="receta/delete/${receta.id}">Eliminar</a>
			</c:if>
		</div>
</form:form>

<c:if test="${receta.id != -1}">
	<div class="col-md-6">
		<h2>Cocinero ${receta.usuario.nombre}</h2>
		<img src="${receta.usuario.imagen}" class="tamImg img-circle">
		<h3>Cambiar de Cocinero</h3>
		<form:form action="receta/edit-usuario" modelAttribute="receta">
			<form:hidden path="id" id="id" />
			<form:hidden path="nombre" id="nombre" />
			<form:hidden path="imagen" id="imagen" />
			<form:hidden path="descripcion" id="descripcionS" />
			<form:select class="form-control" path="usuario.id">
				<c:forEach items="${usuarios}" var="usuario">
					<form:option value="${usuario.id}">
							${usuario.nombre}
						</form:option>
				</c:forEach>
			</form:select><br/>
			<form:button class="btn btn-warning"
				type="submit">Cambiar cocinero</form:button>
		</form:form>
	</div>
</c:if>
</div>










<c:if test="${receta.id != -1}">
	<h2>Listado Ingredientes</h2>
	<div class="list-group">
		<c:forEach items="${receta.ingredientes}" var="ingrediente">

			<a class="list-group-item"
				href="receta/${receta.id}/edit/ingrediente/${ingrediente.id}">${ingrediente.nombre}
				- ${ingrediente.cantidad} </a>
			<a class="btn-sm btn-danger"
				href="receta/${receta.id}/delete/ingrediente/${ingrediente.id}">
				Eliminar </a>
			<br />
			<br />

		</c:forEach>
	</div>


	<h3 style="color: red;">
		<a class="btn btn-success" href="receta/${receta.id}/add/ingrediente/">Añadir
			ingrediente a la receta</a>
	</h3>
</c:if>


<%@ include file="../includes/footer.jsp"%>
