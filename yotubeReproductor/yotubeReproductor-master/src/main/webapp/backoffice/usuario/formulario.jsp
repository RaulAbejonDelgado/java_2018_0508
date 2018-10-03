
<%@page import="com.ipartek.formacion.youtube.Usuario"%>
<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/nav.jsp" %>

<div id="page-wrapper">
	<div class="row">

		<div class="col-lg-12">
			<h1 class="page-header">${(usuarios.id == -1)?'Crear Usuario': usuario.nombre }</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">

		<form action="usuarios" method="post">


			<div class="form-group">
				<label for="id">Id</label> <input type="text" class="form-control"
					name="id" id="id" value="${usuario.id}" readonly>
			</div>

			<div class="form-group">
				<label for="nombre">Nombre</label> <input type="text"
					class="form-control" name="nombre" id="nombre"
					value="${usuario.nombre}" autofocus>
			</div>

			<div class="form-group">
				<label for="password">Contraseņa</label> <input type="password"
					class="form-control" name="password" id="password"
					value="${usuario.password}">
			</div>

			<div class="form-group">
				<label for="rol">Rol</label> <select name="rol" class="form-control">
					<option value="${Usuario.ROL_USER}">Normal</option>
					<option value="${Usuario.ROL_ADMIN}">Administrador</option>
				</select>
			</div>

			<input type="submit"
				value="${(usuario.id == -1)?'Crear': 'Modificar'}"
				class="btn btn-primary btn-block">
			<c:if test="${usuario.id > 0 }">
				<a href="usuario?id=${usuario.id}&op0=45" onclick="confirmar(event)"
					class="btn btn-danger btn-block">Eliminar(Confirmar)</a>
			</c:if>
		</form>

		
</div>
</div>


<%@ include file="../includes/footer.jsp" %>