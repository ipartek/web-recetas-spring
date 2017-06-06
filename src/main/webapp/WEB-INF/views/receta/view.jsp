<%@ include file="../includes/header.jsp" %>
<div class="imagen-destacada">
	<img class="destacada" alt="Imagen destacada de la receta" src="${receta.imagen}">
</div>
<h1 class="titulo-receta">${receta.nombre}</h1>
<div class="container">
  <div class="row">
   <div class="col-md-6">
      <c:if test="${not empty ingredientes}">
		<h2>Listado de ingredientes</h2>
		<ol>
			<c:forEach items="${ingredientes}" var="i">
					<li><strong>${i.nombre}</strong> - ${i.cantidad}</li>
			</c:forEach>
		</ol>
	  </c:if>
    </div>
    <div class="col-md-6">
      <c:if test="${not empty imagenes}">
		<h2>Listado de imagenes</h2>
		<div class="row">
		<c:forEach items="${imagenes}" var="i">
			<div class="centrar col-md-6">
						<img class="tamImg img-thumbnail"  alt="imagen" src="http://localhost:8080/uploads/${i.url}">
			</div>	
		</c:forEach>
		</div>
	  </c:if>
    </div>
  </div>
  <h3>Preparación</h3>
  <div class="elaboracion">
  	<p>${receta.descripcion}</p>
  </div>
 </div>
 <img class="cocinero" alt="EL cocinero de la receta ${receta.usuario.nombre}" title="${receta.usuario.nombre}" src="${receta.usuario.imagen}">

 <div id="corazoncito" class="avatar pulse" onClick="like(${receta.id})">
	<div class="icono">
		<span class="fa fa-heart" aria-hidden="true" role="button"
		aria-label="pulsa para decir que te gusta la receta"
		title="pulsa para decir que te gusta la receta"></span>
		<span class="sr-only">Dar like</span>
		<span id="like${receta.id}" class="likes_number">${receta.likes}</span>
	</div>
 </div>
<%@ include file="../includes/scripts.jsp"%>
 <script src="resources/js/likes.js"></script>
<%@ include file="../includes/footer.jsp" %>  