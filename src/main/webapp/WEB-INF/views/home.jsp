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
					<button class="btn btn-default">
						<a href="receta/edit/${r.id}" onclick="guardarStorage('${r.nombre}', 'receta/edit/${r.id}' )">Ver detalle</a>
					</button>
				</div>
			</div>
		</div>
	</c:forEach>

</div>


<div id="cajita" class="cajaultimasrecetas">
	<div id="flechilla" class="flechita"><i class="iconito fa fa-caret-square-o-right" aria-hidden="true"></i></div>
	<div class="">
		<h3 class="margenizda">Ultimas 5 recetas visitadas</h3>
		<ul id="list">
		</ul>
	</div>
</div>



<%@ include file="includes/footer.jsp"%>