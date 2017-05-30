<%@ include file="includes/header.jsp" %> 

<div class="row">
	<div id="resultadoBusqueda" class="col-xs-12 col-md-4">
	</div>
</div>
<div class="row">

	<c:forEach items="${recetas}" var="r">
		<div class="col-xs-12 col-md-4">
			<div class="card hovercard">
				<img class="img-responsive img-rounded img-thumbnail tamImg"
					alt="imagen ${r.nombre}" src="${r.imagen}">

				<div class="avatar pulse" onClick="like(${r.id})">
					<div class="icono">
						<i class="fa fa-heart" aria-hidden="true"></i>
						<span id="like${r.id}" class="likes_number">${r.likes}</span>
					</div>
				</div>
				<div class="info">
					<div class="title">${r.nombre}</div>
				</div>
				<div class="bottom">
					<button class="btn btn-default">
						<a href="receta/edit/${r.id}" onclick="guardarListaUltimasRecetas('${r.nombre}',' http://localhost:8080/formacion/receta/edit/${r.id}')">Ver detalle</a>
					</button>
				</div>
			</div>
		</div>
	</c:forEach>

</div>

<div id="widget">
	<div id="barra"><i class="fa fa-caret-square-o-left fa-2x" aria-hidden="true"></i></div>
	<div id="contenido">
		<h3>Listado ultimas recetas</h3>
		<ol id="listaUltimasRecetas"></ol>
	</div>
</div>

<script src="resources/js/localStorage.js"></script>
<%@ include file="includes/footer.jsp"%>