<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page session="false" %>

<html>
<head>
	<title><spring:message code="nav.app.name" text="Web App"/></title>
	<base href="/formacion/" />
	
	<link href="http://getbootstrap.com/dist/css/bootstrap.min.css" rel="stylesheet">
	<link href="https://cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css" rel="stylesheet">
	<!-- Custom -->
	<link href="resources/css/custom.css?d=201702288v1" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	
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
        
      

   
	
	
	
	
	
	
	
	