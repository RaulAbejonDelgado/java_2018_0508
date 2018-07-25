<!doctype html>

<html lang="es">
<head>
	<meta charset="utf-8">
	
	<title>Gestion Libros -- Conversor</title>
	<meta name="description" content="App Web Java 3.0 para Gestionar Prestamo de Libros">
	<meta name="author" content="Adrian Perozzo">
	
	<link rel="stylesheet" href="css/styles.css?v=1.0">

</head>

<body>

	<a href="index.jsp" class="btn btn-danger">Volver a casa</a>
	
	<h1>Conversor</h1>
	
	<div class="container">
		<section class="contain">	
		<div class="conversor">
			<h2>De Metros a Pies:</h2>
			<form action="conversor" method="POST">
				<input type="text" name="metros" placeholder="Introduce el numero de metros...">
				<input type="hidden" name="formulario" value="1">
				<br>
				<input class="btn btn-gestion-libros" type="submit" value="Convertir">
			</form>
		</div>
		
		<div class="conversor">
			<h2>De Pies a Metros:</h2>
			<form action="conversor" method="POST">
				<input type="text" name="pies" placeholder="Introduce el numero de pies...">
				<input type="hidden" name="formulario" value="2">
				<br>
				<input class="btn btn-gestion-libros" type="submit" value="Convertir">
			</form>
		</div>
		<p>${msg}</p>
		</section>
		<section class="contain">
			<div>
				<h2 class="conversion">${conversion}</h2>
			</div>
		</section>
	</div>
	
	
</body>

</html>