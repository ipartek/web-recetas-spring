<%@ include file="../includes/header.jsp" %> 

<a href="receta">Volver</a>

<h1>Formulario</h1>

<form:form action="receta/crear" modelAttribute="receta">

	<form:label path="id">Id</form:label>
	<form:input path="id" readonly="true"/><br>
	<form:label path="nombre">Nombre</form:label>
	<form:input path="nombre"/><br>
	<form:errors path="nombre" cssStyle="color:red;"/>
	<br>
	<form:label path="imagen">Imagen</form:label>
	<form:input path="imagen"/><br>
	<form:label path="descripcion">Descripcion</form:label>
	<form:input path="descripcion"/><br>
	<form:button type="submit">Crear</form:button>

</form:form>

<c:if test="${receta.id != -1}">
	<br>
	<a style="color:red;" href="receta/delete/${receta.id}">Eliminar</a>

</c:if>

<%@ include file="../includes/footer.jsp" %> 