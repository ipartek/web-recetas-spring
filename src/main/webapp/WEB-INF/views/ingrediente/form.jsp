<%@ include file="../includes/header.jsp" %> 

<a href="ingrediente">Volver</a>

<h1>Formulario</h1>

<form:form action="ingrediente/crear" modelAttribute="ingrediente">

	<form:input path="id"/><br>
	<form:input path="nombre"/><br>
	
	<!-- 
	<form:input path="gluten"/><br>
	 -->
	
	<form:button type="submit">Crear</form:button>

</form:form>

<%@ include file="../includes/footer.jsp" %> 