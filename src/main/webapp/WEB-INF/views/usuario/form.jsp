<%@ include file="../includes/header.jsp"%>

<a class="btn btn-primary" href="usuario"> <i
	class="fa fa-arrow-circle-left" aria-hidden="true"></i>
</a>

<c:if test="${not empty usuario.nombre}">
	<h1>${usuario.nombre}</h1>
</c:if>

<c:if test="${empty usuario.nombre}">
	<h1>Crear nuevo usuario</h1>
</c:if>

${msg}

<div class="row">
	<div class="col-md-6">
		<form:form action="usuario/crear" modelAttribute="usuario">
			<div class="form-group">
				<form:label path="id">#ID: </form:label>
				<form:input class="form-control" path="id" readonly="true" />
				<br>
			</div>
			<div class="form-group">
				<form:label path="nombre">Nombre: </form:label>
				<form:input class="form-control" path="nombre" />
				<br>
				<form:errors path="nombre" cssStyle="color:red;" />
				<br>
			</div>
			<div class="form-group">
				<form:label path="email">Email: </form:label>
				<form:input class="form-control" path="email"
					value="${usuario.email}" />
				<br> <br>
			</div>
			<div class="form-group">
				<form:label path="imagen">URL de la imagen: </form:label>
				<form:input class="form-control" path="imagen"
					value="${usuario.imagen}" />
				<br>
			</div>
			<br>


			<c:choose>
				<c:when test="${usuario.id == -1}">
					<form:button style="display:inline" class="btn btn-success"
						type="submit">Crear</form:button>
				</c:when>
				<c:otherwise>
					<form:button style="display:inline" class="btn btn-warning"
						type="submit">Modificar</form:button>
				</c:otherwise>
			</c:choose>

		</form:form>
		<c:if test="${usuario.id != -1}">
			<a class="btn btn-danger" href="usuario/delete/${usuario.id}">Eliminar</a>
		</c:if>
	</div>
	<c:if test="${not empty usuario.nombre}">
		<div class="col-md-6">
			<div>
				<img class="tamImg2 " class="form-control" tag="imagenUsuario"
					src="${usuario.imagen}" />
			</div>
			<div style="margin-left: 20%">
				<h2>Recetas</h2>
				<c:if test="${empty usuario.recetas}">
					<p>Todavía no tiene recetas</p>
				</c:if>

				<c:if test="${not empty usuario.recetas}">
					<div class="list-group">
						<c:forEach items="${usuario.recetas}" var="receta">
							<a class="list-group-item" href="receta/edit/${receta.id}">${receta.nombre}</a>
						</c:forEach>
					</div>
				</c:if>
			</div>

		</div>
	</c:if>



</div>
<!-- <div class="row"> -->

