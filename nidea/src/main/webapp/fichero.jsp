<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="includes/header.jsp" %>
<%@include file="includes/navbar.jsp" %>

	<main class="container" role="main">
	
		<h1><i class="far fa-file-alt"></i> Formulario para subir ficheros</h1>
		
		
		<div class="row justify-content-center">
			<div class="col col-md-6">
			
				<!-- Formulario -->
				<form action="subida-fichero" method="post" enctype="multipart/form-data" >
				
					<div class="custom-file mb-3 mt-3">
					  <input type="file" id="fichero" name="fichero" class="custom-file-input">
					  <label class="custom-file-label" for="fichero">Selecciona Fichero</label>
					</div>
								
					<input type="submit" value="Subir Fichero" class="btn btn-outline-primary btn-block">
				</form>
			
			</div>
		</div>
		
		<h2>TODOs</h2>
		<ul class="list-group">
  			<li class="list-group-item">Mostrar imagen subida con etiqueta IMG </li>
  			<li class="list-group-item">Mostrar Tamaño bytes imagen subida</li>
  			<li class="list-group-item">Permitir solo extensiones .PNG, .JPG y JPEG</li>
  			<li class="list-group-item">¿ Que pasa si pesa mas de 2MB?</li>
  		</ul>	
		
	
	</main>

<%@include file="includes/footer.jsp" %>