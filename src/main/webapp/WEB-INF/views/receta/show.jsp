<%@ include file="../includes/header.jsp" %> 

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
                <img class="img-responsive" src="${receta.imagen}" alt="Imagen de la receta presentada en plato">
            </div>
            
            <div class="col-md-4">
                <img class="img-responsive" src="${receta.usuario.imagen}" alt="Imagen del cocinero">
                <span>${receta.usuario.nombre}</span>
                <div id="likeDetalle" onClick="like(${receta.id})">
					<div class="icono">
						<i class="fa fa-thumbs-o-up" aria-hidden="true"></i>
						<span id="like${receta.id}" class="likes_number">${receta.likes}</span>
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
			<div class="col-md-6">   
                <h3>Ingredientes</h3>
                <ol>
               		<c:forEach items="${receta.ingredientes}" var="i">
                		<li>${i.nombre} - ${i.cantidad}</li>
                	</c:forEach>
                </ol>
            </div>
       
            <div class="col-lg-6">
                <h3>Imágenes</h3>
            
            	<ul class="list_galeria_recetas">
               		<c:forEach items="${imagenes}" var="im">
                		<li><img class="img-responsive portfolio-item thumb" src="${im.url}" alt="Imagen de la receta presentada en plato"></li>
                	</c:forEach>
                </ul>

				
        </div>
        <!-- /.row -->

        <hr>


<%@ include file="../includes/scripts.jsp" %>

<script src="resources/js/recetas.js"></script>

<script src="resources/js/toast.js"></script>

<%@ include file="../includes/footer.jsp" %> 
