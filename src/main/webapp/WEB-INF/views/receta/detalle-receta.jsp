<%@ include file="../includes/header.jsp" %> 

<!-- <a href="receta">Volver</a> -->

<div id="img-ppal">
	
	
	<img  alt="Fotografia principal de la receta ${receta.nombre}" src="${receta.imagen}">
	<div class="avatar pulse" onClick="like(${receta.id})">
		<div class="icono">
			<span class="fa fa-heart" aria-hidden="true" aria-label="pulsa en el corazon si te gusta la receta" title="pulsa en el corazon si te gusta la receta" role="button"></span>
			<span class="sr-only">Contador que muestra cuanta gente a realizado "Me gusta" en esta receta</span>
			<span id="like${receta.id}" class="likes_number">${receta.likes}</span>
		</div>
	</div>
	<div class="usuario">
		<img class="img-thumbnail img-circle img-responsive" alt="Fotografia del cocinero ${receta.usuario.nombre}" src="${receta.usuario.imagen}">
	</div>
</div>

<div id="detalle-receta" class="row">

	<h1 class="titulo-receta">${receta.nombre}</h1>
	
	<div class="col-md-6">
		
		<h2 class="titulo">Ingredientes:</h2>
		
		<ol>
		<!-- 
			<c:forEach items="${receta.ingredientes}" var="i">
				<li>${i.nombre}</li>	
			</c:forEach>
		 -->
		 
			<c:forEach items="${ingredientes}" var="i">
				<li>${i.nombre} - ${i.cantidad}</li>	
			</c:forEach>
		</ol>
		
		<h2 class="titulo">Descripción:</h2>
		
		<div class="textarea-descripcion"><p>${receta.descripcion}<p></div>
		
	</div>
	<div class="col-md-6 galeria">
		<c:forEach items="${receta.imagenes}" var="i">
			<div class="col-xs-12 col-md-4">
				<img class="img-responsive img-rounded img-thumbnail tamImgGallery" alt="imagen ${i.nombre}" src="http://localhost:8080/uploads/${i.nombre}">
			</div>
		</c:forEach>

	</div>
	
</div>
<%@ include file="../includes/scripts.jsp"%>

<script src="resources/js/likes.js"></script>

<!-- 
<script src="resources/js/recetas.js"></script>
<script src="resources/js/toast.js"></script>
 -->
 
<%@ include file="../includes/footer.jsp" %> 



