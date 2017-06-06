<%@ include file="../includes/header.jsp" %> 
<h1 class="tituloreceta">${receta.nombre}</h1>
<div class="row filafoto">
	<div class="col-md-12">
		<img id="img_show" class="img-responsive" src="${receta.imagen}">	
	</div>
	<div class="cuadrolikes">
		<i class="corazoncito fa fa-heart fa-3x" aria-hidden="true" onClick="like(${receta.id})"></i><span id="like${receta.id}" class="numlikes">${receta.likes}</span>
	</div>
		<img src="${receta.usuario.imagen}" class="tamImg img-circle imagencocineroshow">
</div>
<hr class="margenarriba3">
<div class="margenarriba2 row">
	<div class="col-md-6">
		<h2>Listado Ingredientes</h2>
		<ol id="listashow">
			<c:forEach items="${ingredientes}" var="ing">
				<li>${ing.nombre} - ${ing.cantidad}</li>
			</c:forEach>
		</ol>	
	</div>
	<div class="col-md-6">
		<c:if test="${not empty imagenes}">
			<c:forEach items="${imagenes}" var="img">
				<div class="imagengaleria col-md-6">
					<img src="http://localhost:8080/uploads/${img.url}" class="tamImg img-thumbnail">
				</div>
			</c:forEach>
		</c:if>
	</div>
</div>
<div class="row preparacionreceta">
	<h2>Preparación</h2>
	${receta.descripcion}
</div>

<%@ include file="../includes/footer.jsp" %> 