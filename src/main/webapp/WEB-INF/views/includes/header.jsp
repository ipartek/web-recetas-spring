<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page session="false" %>

<html lang="es">
<head>
	<meta charset="UTF-8">
	<title><spring:message code="nav.app.name" text="Web App"/></title>
	<base href="/formacion/" />
	
	<link href="http://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css" rel="stylesheet">
	
	<!-- Custom -->
	<link href="resources/css/custom.css" rel="stylesheet">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" crossorigin="anonymous">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

	
</head>
<body>

	
	 <div class="container">

      <!-- Static navbar -->
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><spring:message code="nav.app.name" text="Web App"/></a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
          
          <!-- menu izquierda -->
            <ul class="nav navbar-nav">
              <li class="">
              	<a href="usuario"><spring:message code="nav.usuario" text="Usuario"/></a>
              </li>
              <li>
              	<a href="receta"><spring:message code="nav.recetas" text="Recetas"/></a>
              </li>
              <li>
              	<a href="ingrediente"><spring:message code="nav.ingrdientes" text="Ingredientes"/></a>
              </li>        
            </ul>
          <form class="navbar-form navbar-left">
	        <div class="input-group">
	        <span class="input-group-addon"><label for="buscadorReceta"><span class="glyphicon glyphicon-search"></span>&nbsp; Buscador:</label></span>
	          <input type="text" id="buscadorReceta" class="form-control" size= 55 placeholder="Buscar una receta">
	          
	        </div>
	        
	
	      </form>
          <!-- menu derecha -->  
            <ul class="nav navbar-nav navbar-right">
              <li class="active">
              	 <a href="${requestScope['javax.servlet.forward.request_uri']}?locale=es">ES</a>
              	</li>
              <li>
              	<a href="${requestScope['javax.servlet.forward.request_uri']}?locale=eu">EU</a>
              </li>
              <li>
              	<a href="${requestScope['javax.servlet.forward.request_uri']}?locale=en">EN</a>
              </li>
            </ul>
            
            
                        
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
        
      

   
	
	
	
	
	
	
	
	