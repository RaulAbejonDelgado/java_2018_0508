<c:if test="${not empty alert}">
	<div class="row mt-2">
		<div class="alert ${alert.tipo} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			${alert.texto}
		</div>
	</div>
	${sessionScope.alert=null}
</c:if>