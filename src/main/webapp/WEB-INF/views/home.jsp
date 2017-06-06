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
						<span class="fa fa-heart" aria-hidden="true"></span>
						<span id="like${r.id}" class="likes_number">${r.likes}</span>
					</div>
				</div>
				<div class="info">
					<div class="title">${r.nombre}</div>
				</div>
				<div class="bottom">
					<button class="btn btn-default">
						<a href="receta/show/${r.id}" onclick="guardarListaUltimasRecetas('${r.nombre}',' http://localhost:8080/formacion/receta/show/${r.id}')">Ver detalle</a>
					</button>
				</div>
			</div>
		</div>
	</c:forEach>

</div>

<div id="widget">
	<div id="barra"><span class="fa fa-caret-square-o-left fa-2x" aria-hidden="true"></span></div>
	<div id="contenido">
		<h3>Listado ultimas recetas</h3>
		<ol id="listaUltimasRecetas"></ol>
	</div>
</div>

<%@ include file="includes/scripts.jsp"%>
 
 <script src="resources/js/likes.js"></script>
 <script src="resources/js/search-receta.js"></script>
 <script src="resources/js/local-storage.js"></script>
 
 
 <%@ include file="includes/footer.jsp"%>