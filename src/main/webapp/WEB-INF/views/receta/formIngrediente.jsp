<%@ include file="../includes/header.jsp" %> 

<a href="receta">Volver</a>

<h1>Modificar ${ingrediente.nombre} de ${receta.nombre}</h1>

${msg}

<c:if test="${!ingrediente.gluten}">Gluten Free</c:if>
<c:if test="${ingrediente.gluten}">*Atención contiene Gluten</c:if>

<form:form 
           action="receta/${receta.id}/edit/ingrediente"  
           modelAttribute="ingrediente">

	<form:hidden path="id"/>	
	<form:hidden path="gluten"/>	
	<form:label path="cantidad">Cantidad:</form:label>
	<form:input path="cantidad"/>	
	<br>
	<br>
	<form:button>Modificar</form:button>

</form:form>


<%@ include file="../includes/footer.jsp" %>





