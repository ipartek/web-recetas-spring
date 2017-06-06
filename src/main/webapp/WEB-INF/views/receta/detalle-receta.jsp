<%@ include file="../includes/header.jsp"%>

<!-- <a href="receta">Volver</a> -->
<div id="contenedor_show">


	<div id="img-ppal">
		<img alt="Fotografia de la receta ${receta.nombre}"
			src="${receta.imagen}" class="img-rounded" id="receta-foto">
		<div class="avatar pulse" onClick="like(${receta.id})">
			<div class="icono">
				<span class="fa fa-heart" aria-hidden="true"
					aria-label="pulsa en el corazon si te gusta la receta"
					title="pulsa en el corazon si te gusta la receta" role="button"></span>
				<span class="sr-only">Contador que muestra cuanta gente a
					realizado "Me gusta" en esta receta</span> <span id="like${receta.id}"
					class="likes_number">${receta.likes}</span>
			</div>
		</div>
		<div id="cocinero">
			<h2 class="display-4">${receta.usuario.nombre}</h2>
			<img src="${receta.usuario.imagen}"
				alt="imagen de ${receta.usuario.imagen}" class="tamImg img-circle">
		</div>
		<h1>${receta.nombre}</h1>

	</div>
	<div class="row" id="ing-imgs">
		<div class="col-md-4" >
			<h2>Lista de ingredientes</h2>
			<ul class="list-group" id="ingredientes">
				<c:forEach items="${ingredientes}" var="ingrediente">
					<li>${ingrediente.nombre}</li>
				</c:forEach>
			</ul>
		</div>
		<div class="col-md-2" id="div">
		
		</div>
		<div class="col-md-6" id="galeria">
			<div>
				<ul id="galeria">
				<h2>Imagenes</h2>
					<c:forEach items="${receta.imagenes}" var="i">

						<li class="tamLiImg"><img
							class="img-responsive img-thumbnail" alt="${i.nombre}"
							src="http://localhost:8080/uploads/${i.nombre}"></li>
					</c:forEach>
				</ul>
			</div>
		</div>

	</div>
	<div id="descripcion">
		<h2>Descripcion</h2>
		<p id="text_area">${receta.descripcion}</p>
	</div>
</div>

<%@ include file="../includes/scripts.jsp"%>
<script src="resources/js/likes.js"></script>
<!-- 

<script src="resources/js/toast.js"></script>
 -->

<%@ include file="../includes/footer.jsp"%>
