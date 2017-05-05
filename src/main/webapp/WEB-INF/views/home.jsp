<%@ include file="includes/header.jsp" %> 


<div class="row">
	<c:forEach items="${recetas}" var="r">
		<div class="col-xs-6 col-md-4 custom">
			<!--  <a href="receta/edit/${r.id}">
				<img class="image-menu"  alt="imagen ${r.nombre}" src="${r.imagen}">			
			</a>
			<div class="titulo-receta">${r.nombre}</div>-->
			<div class="card hovercard">
				<img class="img-responsive img-rounded img-thumbnail tamImg"
					alt="imagen ${r.nombre}" src="${r.imagen}">

				<div class="avatar pulse">
					<div class="icono">
						<i class="fa fa-heart" aria-hidden="true"></i><span id="likes">${r.likes}</span>
					</div>
				</div>
				<div class="info">
					<div class="title">${r.nombre}</div>
				</div>
				<div class="bottom">
					<button class="btn btn-default">
						<a href="receta/edit/${r.id}">Ver detalle</a>
					</button>
				</div>
			</div>
		</div>
	</c:forEach>
</div>

<%@ include file="includes/footer.jsp" %>