<%@ include file="includes/header.jsp"%>

<!-- Main component for a primary marketing message or call to action -->
<div class="jumbotron">
      
<div class="row">

	<c:forEach items="${recetas}" var="r">
		<div class="col-xs-12 col-md-4">
			<div class="card hovercard">
				<img class="img-responsive img-rounded img-thumbnail tamImg"
					alt="imagen ${r.nombre}" src="${r.imagen}">

				<div class="avatar pulse" onClick="like(${r.id})">
					<div class="icono">
						<span class="fa fa-heart" aria-hidden="true" aria-label="pulsa en el corazon si te gusta la receta" title="pulsa en el corazon si te gusta la receta" role="button"></span>
						<span class="sr-only">Contador que muestra cuanta gente a realizado "Me gusta" en esta receta</span>
						<span id="like${r.id}" class="likes_number">${r.likes}</span>
					</div>
				</div>
				<div class="info">
					<div class="title">${r.nombre}</div>
				</div>
				<div class="bottom">
					<button class="btn btn-default">
						<a onclick="guardar('${r.nombre}')" href="receta/show/${r.id}">Ver detalle</a>
					</button>
				</div>
			</div>
		</div>
	</c:forEach>

</div>

<div id="widget_consultadas">
	<div id="ocultar"><span class="glyphicon glyphicon-chevron-right"></span></div>
	<h3>Ultimas Recetas Consultadas</h3>
	<ol id="lista_consultadas">

	</ol>
</div>


</div> <!--  <div class="jumbotron"> -->
	
<%@ include file="includes/scripts.jsp"%>

<script src="resources/js/likes.js"></script>
<script src="resources/js/local-storage.js"></script>

<%@ include file="includes/footer.jsp"%>