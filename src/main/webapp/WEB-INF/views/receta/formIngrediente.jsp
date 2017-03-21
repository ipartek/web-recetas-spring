<%@ include file="../includes/header.jsp"%>

<a class="btn btn-primary" href="receta"><i
	class="fa fa-arrow-circle-left" aria-hidden="true"></i></a>



<c:if test="${not empty ingrediente.nombre}">
	<c:set var="accion" value="add" />
	<c:set var="boton" value="Modificar" />
	<h1>Modificar ${ingrediente.nombre} de ${receta.nombre}</h1>
</c:if>

<c:if test="${ empty ingrediente.nombre}">
	<c:set var="accion" value="nuevo" />
	<c:set var="boton" value="Añadir" />
	<h1>Añadir ingrediente a receta: ${receta.nombre }</h1>
</c:if>


${msg}

<c:if test="${!ingrediente.gluten}">Gluten Free</c:if>
<c:if test="${ingrediente.gluten}">*Atención contiene Gluten</c:if>
<div class="row">
	<div class="col-md-6">
		<form:form action="receta/${receta.id}/${accion}/ingrediente"
			modelAttribute="ingrediente">

			<!-- Si hay ingredientes para seleccionar estamos añadiendo un ingrediente no incluido a la receta -->
			<c:if test="${disponibles!=null}">
				<div class="form-group">
					<form:label path="idIngrediente">Selecciona el ingrediente</form:label>
					<form:select class="form-control" path="idIngrediente">
						<c:forEach items="${disponibles}" var="d">
							<form:option value="${d.idIngrediente}"> ${d.nombre}</form:option>
						</c:forEach>
					</form:select>
				</div>

			</c:if>

			<!-- Si no hay ingredientes para seleccionar estamos modificando un ingrediente existente -->

			<c:if test="${disponibles==null}">
				<form:hidden path="idIngrediente" />

			</c:if>
			<form:hidden path="nombre" />

			<form:hidden path="gluten" />
			<div class="form-group">
				<form:label path="cantidad">Cantidad:</form:label>
				<form:input class="form-control" path="cantidad" />
				<form:errors path="cantidad" cssClass="rojo" />
			</div>
			<br>
			<br>
			<form:button class="btn btn-success">${boton}</form:button>

		</form:form>
	</div>
</div>









<%@ include file="../includes/footer.jsp"%>





