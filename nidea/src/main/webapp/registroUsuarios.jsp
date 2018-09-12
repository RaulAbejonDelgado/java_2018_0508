<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<div class="container">

	<fieldset>
			<legend>Registro de usuarios</legend>
			<form action="RegistroUsuarioControler" method="post">
				<div class="form-group login">
					<label for="nombreUsuario">Nombre:</label> 
			        <input class="form-control" type="text" autofocus placeholder="Escribe tu nombre" name="nombreUsuario" required="required" pattern="[a-zA-Z\s]{5,}"/>
			        <span class="invalid">*El nombre es requerido</span><br>
	
			        <label for="emailUsuario">Email: </label>
			        <input  class="form-control" type="emailUsuario" name="emailUsuario" required="required" placeholder="Introduce tu email"/>
			        <span class="invalid" >*El email es requerido</span><br>
			        
			        <label for="passUsuario">Password:</label>
			        <input class="form-control" id="pass1" type="password" name="passUsuario" required="required" placeholder="Introduce su password"/>
			        <span class="invalid" >*El password es requerido</span><br>
			        
			        
					<!-- onBlur="compruebaPassword()" -->
			        <label for="replyPassUsuario">Password:</label>
			        <input class="form-control" id="pass2" type="password"  name="replyPassUsuario" required="required" placeholder="Introduzca nuevamente su password"/>
			        <span class="invalid" >*Introduzca nuevamente el password</span><br>
		        

		        <div>
		        	<input class="form-control" type="submit" value="Enviar" id="enviar"/>
		        </div>
		       

			</form>
		</fieldset>
</div>