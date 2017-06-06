<%@ include file="../includes/header.jsp"%>

<input type="hidden" id="id_receta" value="${receta.id}">

<!-- Portfolio Item Heading -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">${receta.nombre}</h1>
	</div>
</div>
<!-- /.row -->

<!-- Portfolio Item Row -->
<div class="row">

	<div class="col-md-8">
		<img class="img-responsive" src="${receta.imagen}"
			alt="Imagen de la receta presentada en plato">
	</div>

	<div class="col-md-4">
		<img class="img-responsive" src="${receta.usuario.imagen}"
			alt="Imagen del cocinero"> <span>${receta.usuario.nombre}</span>
		<div id="likeDetalle" onClick="like(${receta.id})">
			<div class="icono">
				<i class="fa fa-thumbs-o-up" aria-hidden="true"></i> <span
					id="like${receta.id}" class="likes_number">${receta.likes}</span>
			</div>
		</div>
	</div>
</div>

<hr>

<div class="row">
	<div class="col-md-12">
		<h2>Descripción</h2>
		<p>${receta.descripcion}</p>
	</div>
</div>

<hr>

<div class="row">
	<div class="col-md-4">
		<h3>Ingredientes</h3>
		<ol>
			<c:forEach items="${receta.ingredientes}" var="i">
				<li>${i.nombre}- ${i.cantidad}</li>
			</c:forEach>
		</ol>
	</div>

	<div class="col-lg-8">

		<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
  <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
	  <c:forEach items="${imagenes}" var="i">
	  		<li data-target="#carousel-example-generic" data-slide-to="${i.id}"></li>
	  </c:forEach>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner" role="listbox">
	     <div class="item active">
		      <img src="${receta.imagen}" alt="imagen de ${receta.nombre}">
		      <div class="carousel-caption">
		        ${receta.nombre}
		      </div>
	      </div>
  			<c:forEach items="${imagenes}" var="i">
  			 	<div class="item">
				 	<img src="${i.url}" alt="imagen de ${receta.nombre}" style="width:100%;">
				 	<div class="carousel-caption">${receta.nombre}</div>
				 </div>
			</c:forEach>

  </div>

  <!-- Controls -->
  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>





	</div>
	<!-- /.row -->

	<hr>


	<%@ include file="../includes/scripts.jsp"%>

	<script src="resources/js/recetas.js"></script>

	<script src="resources/js/toast.js"></script>

	<%@ include file="../includes/footer.jsp"%>