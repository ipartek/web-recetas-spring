<%@ include file="../includes/header.jsp" %> 

<!-- <a href="receta">Volver</a> -->

<div id="img-ppal">
	<img  alt="Fotografia de la receta ${receta.nombre}" src="${receta.imagen}">
	<div class="avatar pulse" onClick="like(${receta.id})">
		<div class="icono">
			<span class="fa fa-heart" aria-hidden="true" aria-label="pulsa en el corazon si te gusta la receta" title="pulsa en el corazon si te gusta la receta" role="button"></span>
			<span class="sr-only">Contador que muestra cuanta gente a realizado "Me gusta" en esta receta</span>
			<span id="like${receta.id}" class="likes_number">${receta.likes}</span>
		</div>
	</div>
</div>


<h1>${receta.nombre}</h1>


<%@ include file="../includes/scripts.jsp"%>

<!-- 
<script src="resources/js/recetas.js"></script>
<script src="resources/js/toast.js"></script>
 -->
 
<%@ include file="../includes/footer.jsp" %> 



