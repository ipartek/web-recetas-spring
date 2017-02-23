<%@ include file="../includes/header.jsp" %> 

<a href="receta">Volver</a>

<h1>Formulario Receta</h1>

${msg}

<form:form action="receta/crear" modelAttribute="receta">

	<form:input path="id" readonly="true"/><br>
	
	<form:label path="nombre">Nombre</form:label>
	<form:input path="nombre"/><br>	
	<form:errors path="nombre" cssStyle="color:red;"/>
	<br>
	
	<form:label path="imagen">Imagen</form:label>
	<form:input path="imagen"/><br>	
	<form:errors path="imagen" cssStyle="color:red;"/>
	<br>
	
	<form:label path="descripcion">Descripcion</form:label>
	<form:textarea rows="8" cols="80" path="descripcion"/><br>	
	<form:errors path="descripcion" cssStyle="color:red;"/>
	<br>
	
	<c:if test="${receta.id != -1}">
		<span>Usuario de la Receta: ${receta.usuario.nombre}</span>
		<br><br>
		<img class="tamImg" src="${receta.usuario.imagen}"/><br><br>
		
		<form:label path="usuario">Cambiar Usuario:</form:label>
	</c:if>
	
	<c:if test="${receta.id == -1}">
		<form:label path="usuario">Usuario:</form:label>
	</c:if>
		
	<form:select path="usuario.id">
		<c:forEach items="${usuarios}" var="u">
			<form:option value="${u.id}">${u.nombre}</form:option>
		</c:forEach>
	</form:select>
	

	
	<br><br>
	

	<c:choose>
		<c:when test="${receta.id == -1}">
			<form:button type="submit">Crear</form:button>
		</c:when>
		<c:otherwise>
			<form:button type="submit">Modificar</form:button>
		</c:otherwise>
	</c:choose>
	
</form:form>

<c:if test="${receta.id != -1}">
	<br>
	<a style="color:red;" href="receta/delete/${receta.id}">Eliminar</a>

	<h2>Listado Ingredientes
		<span>
			<a href="receta/${receta.id}/recuperar/ingredientes">[ Añadir Ingredientes ]</a>
		</span>
	</h2>
	
		<ol>
			<c:forEach items="${receta.ingredientes}" var="ingrediente">
				<li>${ingrediente.nombre} - ${ingrediente.cantidad}
					<span style="color:red;">
						<a href="receta/${receta.id}/delete/ingrediente/${ingrediente.id}">[ Eliminar ]</a>
					</span>
					
					<span>
						<a href="receta/${receta.id}/edit/ingrediente/${ingrediente.id}">[ Modificar cantidad ]</a>
					</span>
				</li>
			</c:forEach>
		</ol>
</c:if>	


<%@ include file="../includes/footer.jsp" %> 