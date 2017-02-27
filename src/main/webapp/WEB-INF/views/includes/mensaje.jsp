<%@page import="com.ipartek.formacion.recetas.pojo.Mensaje"%>
<%
	/** 
		Gestion de Mensajes para el usuario.<br>
		Debemos enviar un atributo 'msj' desde el Servlet.<br>
	*/
	
	if( request.getAttribute("msj") != null ){
		Mensaje mensaje = (Mensaje)request.getAttribute("msj");
%>

	<div class="alert <%=mensaje.getClase()%> alert-dismissible" role="alert">
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  <%=mensaje.getDescripcion()%>
	</div>

<%
	} //end if	
%>
