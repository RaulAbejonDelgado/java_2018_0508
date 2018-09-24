<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">

		<a
			class="navbar-brand"
			href="#"><fmt:message key="lista.repr"></fmt:message></a>

		<button
			class="navbar-toggler"
			type="button"
			data-toggle="collapse"
			data-target="#navbarResponsive"
			aria-controls="navbarResponsive"
			aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div
			class="collapse navbar-collapse"
			id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active">
					<!-- Usuario logueado (formulario b�squeda de videos) --> 
					<c:if test="${not empty sessionScope.usuario}">

						<div class="nav-user">
							<i class="fas fa-user mr-2"></i><span class="text-white">${sessionScope.usuario.nombre}</span> 
							<a
								href="backoffice/">Backoffice</a> <a href="logout"><i class="fas fa-power-off"></i></a>
						</div>

						<!-- formulario Crear Video -->
						<form
							action="inicio"
							method="post"
							class="form-inline ">
							<input
								name="id"
								class="form-control mr-sm-2"
								type="text"
								placeholder="ID 11 caracerteres"
								title="11 caracteres"
								required
								pattern=".{11,11}"> <input
								name="nombre"
								class="form-control mr-sm-2"
								type="text"
								placeholder="Nombre minimo 2 letras"
								required
								pattern=".{2,125}">
							<button
								class="btn btn-outline-info my-2 my-sm-0"
								type="submit">A�adir</button>
						</form>
					</c:if> <!-- Usuario no logueado (formulario login) --> <c:if test="${empty sessionScope.usuario}">

						<!-- Trigger the modal with a button -->
						<button
							type="button"
							class="btn btn-info btn-lg"
							data-toggle="modal"
							data-target="#modal-login-form">Open Modal</button>

						<%@ include file="modal-login-form.jsp"%>
					</c:if>
				</li>
			</ul>
		</div>
		<!-- ./collapse-navbar -->


	</div>

	<!-- ./container -->
</nav>
