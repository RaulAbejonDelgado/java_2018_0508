</div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
    <!-- DataTables.net -->
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    
    <script>
    
    /* custom javascript*/
    
    //habilitar datatables
    

		$(document).ready(function() {
		$('#tablaOrdenable').DataTable();
		});
    
	</script>
	
	<script>
			function confirmar(e) {

				if (confirm('�Estas seguro que quieres eliminar?')) {
					console.log('confirmado eliminar')

				} else {
					//prevenir el evento por defecto de <a href=''>
					e.preventDefault();
				}

			}
		</script>
   
   
</body>

</html>