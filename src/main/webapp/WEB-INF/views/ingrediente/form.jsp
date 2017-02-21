<%@ include file="../includes/header.jsp" %> 

<a href="ingrediente">Volver</a>

<h1>Formulario</h1>

<form:form action="ingrediente/crear" modelAttribute="ingrediente">

	<form:input path="id" readonly="true"/><br>
	<form:input path="nombre"/><br>	
	<form:errors path="nombre" cssStyle="color:red;"/>
	<br>
	
	
	<form:label path="gluten">¿ Contiene Glutten ?</form:label>
	<form:checkbox path="gluten"/><br>	
	
	
	<form:button type="submit">Crear</form:button>

</form:form>

<c:if test="${ingrediente.id != -1}">
	<br>
	<a style="color:red;" href="ingrediente/delete/${ingrediente.id}">Eliminar</a>

</c:if>

<%@ include file="../includes/footer.jsp" %> 