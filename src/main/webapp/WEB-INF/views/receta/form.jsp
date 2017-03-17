<%@ include file="../includes/header.jsp" %> 

<a href="receta">Volver</a>

<h1>${receta.nombre}</h1>

${msg}



	<form:form modelAttribute="receta">
	
		<div class="row">
			<div class="col-md-6">
				<form:hidden path="id" id="id"/><br>
				
				<form:label path="nombre" >Nombre</form:label>
				<form:input path="nombre" id="nombreReceta"/><br>	
				<form:errors path="nombre" cssStyle="color:red;"/>
				<p id="msgNombreUsuario"></p>
				<br>
				
				<form:label path="imagen">Imagen</form:label>
				<form:input path="imagen" id="imagen"/><br>	
				<form:errors path="imagen" cssStyle="color:red;"/>
				<br>
				
				<form:label path="descripcion">Descripcion</form:label>
				<form:textarea rows="8" cols="80" id="descripcion" path="descripcion"/><br>	
				<form:errors path="descripcion" cssStyle="color:red;"/>
			</div>	
			
			<div class="col-md-6">
				<img class="img-responsive" src="${receta.imagen}">	
			</div>
			
		
		</div> <!--  <div class="row"> -->
		
		<div class="row">
			<div class="col-md-6">
				<c:choose>
					<c:when test="${receta.id == -1}">						
							<form:button onclick="crearNuevaReceta()" type="submit">Crear</form:button>							
					</c:when>
					<c:otherwise>						
						<form:button type="submit">Modificar</form:button>						
					</c:otherwise>
					
				</c:choose>
			</div>
		
			<div class="col-md-6">
				<h2>Cocinero ${receta.usuario.nombre}</h2>
				<img src="${receta.usuario.imagen}" class="tamImg img-circle">
				<h3>Cambiar de Cocinero</h3>
				<form:select path="usuario.id">
					<c:forEach items="${usuarios}" var="usuario">
						<form:option value="${usuario.id}">
							${usuario.nombre}
						</form:option>
					</c:forEach>
				</form:select>				
			</div>	
		</div>
		
		
	</form:form>


<c:if test="${receta.id != -1}">
						<button type="button" class="btn btn-danger">
						<a style="color:red;" href="receta/delete/${receta.id}">Eliminar</a>
						</button>
					</c:if>





<h2>Listado Ingredientes</h2>
<ol>
<c:forEach items="${receta.ingredientes}" var="ingrediente">
	<li>
		<a href="receta/${receta.id}/edit/ingrediente/${ingrediente.id}">${ingrediente.nombre}</a> - ${ingrediente.cantidad} 
		<span style="color:red;">
			<a href="receta/${receta.id}/delete/ingrediente/${ingrediente.id}">[ Eliminar ]</a>
		</span>
	</li>
</c:forEach>
</ol>

<h3 style="color:red;">
<a  href="receta/${receta.id}/add/ingrediente/">Añadir ingrediente a la receta</a>
</h3>


<%@ include file="../includes/footer.jsp" %> 