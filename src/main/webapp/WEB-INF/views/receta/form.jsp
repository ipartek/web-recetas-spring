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
	
	<form:label path="">Usuario</form:label>
	<input type="text" value="4" />**	
	<form:errors path="" cssStyle="color:red;"/>
	<br>

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

</c:if>


<h2>Listado Ingredientes</h2>

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
	


<%@ include file="../includes/footer.jsp" %> 