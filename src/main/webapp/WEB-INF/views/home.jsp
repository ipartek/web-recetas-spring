<%@ include file="includes/header.jsp" %> 


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
					<button class="btn btn-default" onclick="guardarStorage('${r.nombre}', 'receta/edit/${r.id}' )">
						<a href="receta/edit/${r.id}"  >Ver detalle</a>
					</button>
				</div>
			</div>
		</div>
	</c:forEach>

</div>

<div id="widget">
	<h3>Recetas visitadas</h3>
	<ol id="list">
	</ol>
</div>



<%@ include file="includes/footer.jsp"%>