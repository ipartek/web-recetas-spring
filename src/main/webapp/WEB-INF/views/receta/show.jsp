<%@ include file="../includes/header.jsp" %> 

<div class="row">
	<div class="col-md-12">
		<img id="img_show" class="img-responsive" src="${receta.imagen}">	
	</div>
		<img src="${receta.usuario.imagen}" class="tamImg img-circle imagencocineroshow">
</div>

<h1>${receta.nombre}</h1>

<div class="margenarriba row">
	<div class="col-md-6">
		<h2>Listado Ingredientes</h2>
		<ol id="list_ingredientes"></ol>
	</div>
	<div class="col-md-6">
		<c:if test="${not empty imagenes}">
			<c:forEach items="${imagenes}" var="img">
				<div class="imagengaleria col-md-6">
					<img src="http://localhost:8080/uploads/${img.url}" class="tamImg img-thumbnail">
					<span class="spangaleria"><a class="margenbotoneliminar btn btn-danger" href="receta/${receta.id}/eliminarImagen/${img.id}" role="button"><i class="fa fa-times" aria-hidden="true"></i></a></span>
				</div>
			</c:forEach>
		</c:if>
	</div>
</div>

<form:form action="receta/crear" modelAttribute="receta">
	
		<div class="row">
			<div class="col-md-12">
				<form:hidden path="id"/><br>	
				<form:label path="descripcion">Descripcion</form:label>
				<form:textarea rows="8" cols="80" path="descripcion"/><br>	
				<form:errors path="descripcion" cssStyle="color:red;"/>
			</div>	
		</div> <!--  <div class="row"> -->
</form:form>

<%@ include file="../includes/footer.jsp" %> 