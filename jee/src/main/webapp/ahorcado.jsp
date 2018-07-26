<!doctype html>

<html lang="es">
<head>
	<meta charset="utf-8">	
	<title>Juego del Ahorcado</title>
	<meta name="description" content="App Web Java 3.0 para gestioar préstamos de libros">
	<meta name="author" content="Adriana Prado Alonso">	
	<link rel="stylesheet" href="css/styles.css">
</head>

<body>
	<%@ include file ="includes/navbar.jsp" %>
	<h1>Juego del Ahorcado</h1>
	
	<form action="ahorcado" method="post" >
		<% char [] letras = (char[]) request.getAttribute("letras"); 
			for(int i=0;i<letras.length;i++){
				out.print("<span>"+letras[i]+" </span>");
			} %>
		<br>		
		<br>
		<input name="letra" type="text" placeholder="Introduce una letra">
		<input type="submit" value="Enviar">
		<br><br>
		<span>Fallos: ${contFallos} </span>
		<p class="text-danger">${msg}</p>
	</form>
	
	<br>
	
	<div  class="ahorcado fallo${contFallos}">
	</div>
	
	<br/>

	<a href="ahorcado?jdn=1">Jugar de Nuevo</a>
	
	<script src="js/scripts.js"></script>
</body>
</html>