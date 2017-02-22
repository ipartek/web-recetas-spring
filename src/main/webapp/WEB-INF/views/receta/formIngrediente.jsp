<%@ include file="../includes/header.jsp" %> 

<a href="receta">Volver</a>

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



<form:form action="receta/${receta.id}/add/ingredientes" method="post" modelAttribute="ingredientes">
	<form:hidden path="id"/>
	<form:hidden path="gluten"/>
	<form:hidden path="nombre"/>
	<form:label path="cantidad">Modifica la cantidad</form:label>
	<form:input path="cantidad"/>
	<br><br>
	<form:button>Modificar</form:button>

</form:form>



seleccion ingediente
<select name="idIngrediente">
	<option value="1">Cebolla</option>
	<option value="2">Patata</option>
	<option value="24">Ajos</option>

</select>


<%@ include file="../includes/footer.jsp" %> 