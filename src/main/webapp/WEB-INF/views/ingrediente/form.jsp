<%@ include file="../includes/header.jsp"%>

<a class="btn btn-primary" href="ingrediente"><i
	class="fa fa-arrow-circle-left" aria-hidden="true"></i></a>

<h1>Formulario</h1>

<div class="row">
	<div class="col-md-6">
		<form:form modelAttribute="ingrediente">
			<c:if test="${ingrediente.id != -1}">
				<div class="form-group">
					<form:label path="id">#ID</form:label>
					<form:input class="form-control" path="id" readonly="true" />
				</div>
			</c:if>
			<div class="form-group">
				<form:label path="nombre">Nombre</form:label>
				<form:input class="form-control" path="nombre" />
				<form:errors class="form-control" path="nombre"
					cssStyle="color:red;" />
			</div>

			<div class="form-group">
				<form:label path="gluten">¿ Contiene Glutten ?</form:label>
				<form:checkbox class="form-control" path="gluten" />
			</div>

			<div class="form-group">
				<c:if test="${ingrediente.id == -1}">
					<form:button class="btn btn-success" type="submit">Crear</form:button>
				</c:if>
				<c:if test="${ingrediente.id != -1}">
					<form:button class="btn btn-warning" type="submit">Modificar</form:button>
				</c:if>
			</div>
		</form:form>
	</div>
</div>


<c:if test="${ingrediente.id != -1}">
	<br>
	<a class="btn btn-danger" href="ingrediente/delete/${ingrediente.id}">Eliminar</a>

</c:if>

<%@ include file="../includes/footer.jsp"%>
