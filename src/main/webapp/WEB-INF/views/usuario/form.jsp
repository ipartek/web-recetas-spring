<%@ include file="../includes/header.jsp" %> 
<%@ include file="../includes/nav.jsp" %> 

<a href="usuario">Volver</a>

<h1>Formulario</h1>

<div class="row">
	<div class="col-md-6">
		<form:form action="usuario/crear" modelAttribute="usuario">
		
			<form:hidden path="id" readonly="true"/><br>
			
			<form:label path="nombre">Nombre</form:label>
			<form:input class="form-control" path="nombre"/><br>	
			<form:errors path="nombre" cssStyle="color:red;"/>
			<br>
			
			<form:label path="email">Email</form:label>
			<form:input class="form-control" path="email"/><br>	
			<form:errors path="email" cssStyle="color:red;"/>
			<br>
			
			<form:label path="password">Password</form:label>
			<form:password class="form-control" showPassword="true" path="password"/><br>	
			<form:errors path="password" cssStyle="color:red;"/>
			<br>
			
			<form:label path="imagen">Imagen</form:label>
			<form:input class="form-control" path="imagen"/><br>	
			<form:errors path="imagen" cssStyle="color:red;"/>
			<br>
			
			<c:choose>
				<c:when test="${usuario.id == -1}">
					<form:button class="btn btn-success" type="submit">Crear</form:button>
				</c:when>
				<c:otherwise>
					<form:button class="btn btn-success" type="submit">Modificar</form:button>
				</c:otherwise>
			</c:choose>
		
		</form:form>
		
		<c:if test="${usuario.id != -1}">
			<br>
			<a style="color:red;" href="usuario/delete/${usuario.id}"><button class="btn btn-danger">Eliminar</button></a>
			<br>
		</c:if>
	</div> <!-- <div class="col-md-6"> -->
	
	<div class="col-md-6">
		<c:if test="${usuario.id != -1}">
			
			<h2>Listado de Recetas del usuario: ${usuario.nombre}
				<span>
					<a href="#"><button class="btn btn-primary">Añadir Recetas</button></a>
				</span>
			</h2>
			
				<ol>
					<c:forEach items="${recetasUsuario}" var="r">
						<li> 
							<img class="tamImg img-thumbnail" alt="foto ${r.nombre}" src="${r.imagen}">  ${r.nombre}
						</li>
					</c:forEach>
				</ol>
		</c:if>
	</div> <!-- <div class="col-md-6"> -->
</div> <!-- <div class="row"> -->

<%@ include file="../includes/footer.jsp" %> 