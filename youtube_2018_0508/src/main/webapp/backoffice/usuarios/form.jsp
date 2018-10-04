<%@page import="com.andrea.perez.controller.back.BackofficeUsuarioController"%>
<%@page import="com.andrea.perez.pojo.Usuario"%>
<%@include file="../includes/header.jsp"%>    
<%@include file="../includes/aside.jsp"%> 

<div id="page-wrapper">

<%@include file="../includes/alert.jsp"%>
	<div class="row">
		<div class="col-lg-12">
		
		<div class="row justify-content-center align-items-center" style="min-height:470px;">
      
      	<div class="col-6">	
      		<h1>Editar Usuario</h1>
      		
      		<form method="post" action="usuarios?op=${BackofficeUsuarioController.OP_GUARDAR}">
      			<div class="form-group">
      		 		<label for="id">Id</label>
	      			<input type="text" class="form-control" name="id" id="id" value="${(usuario.id==-1)?'': usuario.id }" readonly>
	      		</div>
	      		
      		 	<div class="form-group">
      		 		<label for="nombre">Nombre</label>
	      			<input type="text" name="nombre" class="form-control" id="nombre" value="${usuario.nombre }" autofocus>
	      		</div>
	      		<div class="form-group">
	      			<label for="contrasena">Contraseņa</label>
	      			<input type="password" name="contrasena" class="form-control" id="contrasena" value="${usuario.contrasena }">
	      		</div>
	      		<div class="form-group">
	      			<label for="rol">Rol</label>
	      			<select name="rol" class="form-control">	      					      				
	      				<option value="${Usuario.ROL_USER}"  ${(usuario.rol == Usuario.ROL_USER)?'selected':'' }>Normal</option>
				   		<option value="${Usuario.ROL_ADMIN}"  ${(usuario.rol == Usuario.ROL_ADMIN)?'selected':'' }>Administrador</option>
	      			</select>
	      		</div>
	      		<input type="submit" value="${(usuario.id == -1)?'Crear':'Modificar'}" class="btn btn-primary btn-block">
				<c:if test="${usuario.id > 0 }">
					<a href="usuarios?id=${usuario.id}&op=<%=BackofficeUsuarioController.OP_ELIMINAR%>" onclick="confirmar(event)" class="btn btn-danger btn-block">Eliminar(Modal confirmar)</a>
				</c:if>
      		</form>
      	</div>
      
      </div><!-- .row -->
		
		
		<!-- row -->
		
		</div>
		<!-- /col-lg-12 -->
	</div>
	<!-- /row -->	
</div>






<%@include file="../includes/footer.jsp"%>