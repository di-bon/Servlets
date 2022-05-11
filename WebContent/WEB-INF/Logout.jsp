<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href='css/bootstrapcss/bootstrap.min.css' rel='stylesheet' type='text/css' />
	<script src='js/jquery-3.6.0.min.js' type='text/javascript'></script>
	<script src='js/bootstrapjs/bootstrap.bundle.min.js' type='text/javascript'></script>
<title>Logout</title>
</head>
<body>

	<script>
		$(document).ready( function () {
			$('.toast').toast('show');
			
			$(document).on('click', '#button-logout', function () {
				modalconfirm = new bootstrap.Modal(document.getElementById('modalconfirm'), {
					backdrop: 'static',
					keybord: false,
				})
			})
			
			logoutform = document.getElementById('logoutform');
			
			$(document).on('click', '#button-logout', function () {
				modalconfirm.show();
			})
			
			$(document).on('click', '#confirm-logout', function () {
				logoutform.submit();
				modalconfirm.dismiss();
			})
		})
	</script>

	<!-- Toast -->
	<!-- For Overlay -->
	<div style="position: relative;">
		<!-- Flexbox container for aligning the toasts: position-fixed class do the overlay -->
		<div aria-live="polite" aria-atomic="true" class="d-flex justify-content-center align-items-center w-100">
		
			<!-- Container for stacking the toasts (if you show more than one) -->
			<div class="toast-container">
				<div class="toast" data-bs-delay="5000" data-autohide="false" role="alert" aria-live="assertive" aria-atomic="true">
			  		<div class="toast-header">
			    		<!-- img src="..." class="rounded me-2" alt="..." -->
			    		<strong class="me-auto">Login effettuato con successo!</strong>
			    		<!-- <small>popup</small> -->
			    		<button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
				  	</div>
				  	<!-- <div id="already-shown" style="display: none;"></div> -->
				</div>
			</div>
		</div>
	</div>

	<!-- Modal for logout confirm -->
	<div id="modalconfirm" class="modal" tabindex="-1" role="dialog" data-bs-backdrop="static" data-bs-keyboard="false" >>
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Confirm action</h5>
	        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      </div>
	      <div class="modal-body">
	        <p>Do you really want to logout?</p>
	        <form id=logoutform action="/Servlets/Loginator" method="post">
				<div>
					<input type="hidden" name="logout" value="yes" />
					<input type="hidden" name="source" value="logout.jsp" />
				</div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button id="confirm-logout" type="button" class="btn btn-secondary">Yes</button>
	        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">No</button>
	      </div>
	    </div>
	  </div>
	</div>

	<div class="row d-flex justify-content-center" style="margin-top: 35px">
		<div class="col-md-6" style="text-align: center;">
			<div>
				<h2>Welcome, ${name}!</h2>
			</div>
			<div>
				<!-- <form action="/Servlets/Loginator" method="post"> -->
					<!-- <input type="hidden" name="logout" value="yes" />
					<input type="hidden" name="source" value="logout.jsp" /> -->
				<input type="button" id="button-logout" class="btn btn-primary" value="logout" />
				<!-- </form> -->
			</div>
		</div>
	</div>
</body>
</html>