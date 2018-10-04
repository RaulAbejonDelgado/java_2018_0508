<%@page import="com.ipartek.formacion.youtube.pojo.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../includes/header.jsp" %>

	<%@ include file="../includes/nav.jsp" %>
	<div id="wrapper">

        <%@ include file="../includes/sidebar.jsp" %>

        <div id="page-wrapper">
        	<div class="row">
        		<div class="col-md-12">
        			<h2>${(usuario.id == 0)?'Crear usuario':usuario.nombre}</h2>
        			<!-- Tratamiento de las alertas -->
					<c:if test="${not empty alert}">
						<div class="alert alert-${alert.tipo} alert-dismissible" role="alert">
							<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
							<strong>${alert.texto}</strong>
						</div>
					</c:if>
        		</div>
        	</div>
        	<div class="row m-auto">
        		<div class="col-md-6">
        			<form action="backoffice/usuario" method="post">        		
	        			<div class="form-group">
	        				<input name="id" type="text" class="form-control" value="${usuario.id}" readonly/>
	        			</div>
	        			<div class="form-group">
	        				<label for="nombre">Nombre:</label>
	        				<input name="nombre" type="text" class="form-control" value="${usuario.nombre}" autofocus/>
	        			</div>
	        			<div class="form-group">
	        				<label for="contrasenya">Contraseña:</label>
	        				<input name="contrasenya" type="text" class="form-control" value="${usuario.contrasenya}"/>
	        			</div>
	        			<div class="form-group">
	        				<label for="rol">Rol:</label>
	        				<select name="rol" class="form-control">
	        					<option value="<%=Usuario.ROL_USER%>">Normal</option>
	        					<option value="<%=Usuario.ROL_ADMIN%>">Administrador</option>
	        				</select>
	        			</div>
	        			<input type="submit" value="${(usuario.id == 0)?'Crear':'Modificar'}" class="btn btn-primary btn-block" />
	        			<c:if test="${usuario.id>0}">
	        				<span data-toggle="modal" data-target="#modal-eliminar" class="btn btn-danger btn-block">Eliminar</span>
	        			</c:if>
	        		</form>
        		</div>
        	</div>
        	<!-- /row -->
        </div>
	</div>
	
	<div id="modal-eliminar" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header bg-pika-red">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">Cuidadin</h4>
	      </div>
	      <div class="modal-body">
	        	<p>¿Estas seguro de querer eliminar este video?</p>
		       <a id="btnEliminar" href="backoffice/usuario?id=${usuario.id}&op=77" class="btn btn-outline-info btn-outline-pika-red">Eliminar <i class="fas fa-trash-alt"></i></a>
		       <button type="button" class="btn btn-outline-info btn-outline-pika-red" data-dismiss="modal">Cerrar</button>
	      </div>
	      <div class="modal-footer bg-pika-red"></div>
	    </div>
	  </div>
	</div>
<%@ include file="../includes/footer.jsp" %>
