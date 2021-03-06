<%@page
	import="com.ipartek.formacion.prestamolibros.controller.CrudControllable"%>
<%@include file="../includes/header.jsp"%>
<%@include file="../includes/nav.jsp"%>

<div id="page-wrapper">

	<%@include file="../includes/alert.jsp"%>

	<div class="row">
		<form action="alumnos" method="post">

			<div class="form-group">
				<label for="id">ID:</label> <input type="text" class="form-control"
					id="id" name="id" value="${alumno.id }" readonly />
			</div>

			<div class="form-group">
				<label for="nombre">Nombre:</label> <input type="text"
					class="form-control" id="nombre" name="nombre"
					value="${alumno.nombre }" required autofocus />
			</div>

			<div class="form-group">
				<label for="apellidos">Apellidos:</label> <input type="text"
					class="form-control" id="apellidos" name="apellidos"
					value="${alumno.apellidos }" required autofocus />
			</div>

			<input type="hidden" name="op"
				value="<%=CrudControllable.OP_GUARDAR%>" /> 
				
			<input type="submit"
				value="${(alumno.id == -1)? 'Crear' : 'Modificar' }"
				class="btn ${(alumno.id == -1)? 'btnCrear' : 'btn-modificar'} btn-block" />

			<c:if test="${alumno.id >= 0}">
				<a
					href="alumnos?id=${alumno.id}&op=<%=CrudControllable.OP_ELIMINAR %>" onclick="confirmar(event)"
					class="btn btn-finalizar btn-block">Eliminar</a>
			</c:if>

		</form>

	</div>

</div>
<%@include file="../includes/footer.jsp"%>