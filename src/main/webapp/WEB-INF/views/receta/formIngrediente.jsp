<%@ include file="../includes/header.jsp" %> 

<a href="receta/edit/${receta.id}">Volver</a>


<c:if test="${ingredientes == null}">

	<h1>Modificar ${ingrediente.nombre} de ${receta.nombre}</h1>
	
	${msg}<br>
	
	<c:if test="${!ingrediente.gluten}">Gluten Free</c:if>
	<c:if test="${ingrediente.gluten}">***Atencion contiene Gluten***</c:if>
	
	<form:form action="receta/${receta.id}/edit/ingrediente" method="post" modelAttribute="ingrediente">
		<form:hidden path="id"/>
		<form:hidden path="gluten"/>
		<form:hidden path="nombre"/>
		<form:label path="cantidad">Modifica la cantidad</form:label>
		<form:input path="cantidad"/>
		<br><br>
		<form:button>Modificar</form:button>
	
	</form:form>
</c:if>

<c:if test="${ingredientes != null}">

	<h1>Añadir ingredientes en la receta: ${receta.nombre}</h1>
	
	${msg}<br><br>
	
	<form:form action="receta/${receta.id}/add/ingrediente" method="post" modelAttribute="ingrediente">
		<form:label path="id">Selecciona ingrediente</form:label>
		<form:select multiple="false" path="id">
		
			<c:forEach items="${ingredientes}" var="i">
				<form:option value="${i.id}">${i.nombre}</form:option>
			</c:forEach>
		</form:select>
		<br>
		<form:label path="cantidad">Cantidad</form:label>
		<form:input path="cantidad"/>
		<br><br>
		<form:button>Añadir</form:button>
 	</form:form>
 	
</c:if>

<%@ include file="../includes/footer.jsp" %> 