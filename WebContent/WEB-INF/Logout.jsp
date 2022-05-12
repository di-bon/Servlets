<!-- Francesco Di Bon 5BIA 12-05-2022 -->

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
			const colors = ["white", "red", "green", "yellow", "cyan"];
			count = 1;
			
			if ($('#showlogintoast').html().trim() == "true") {
				$('.toast').toast('show');
			} else {
				$('.toast').hide();
			}
			
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
			
			$(document).on('click', '#change-background', function () {
				document.body.style.backgroundColor = colors[count];
				count = count + 1;
				if (count >= colors.length) {
					count = count % colors.length;
				}
				/* console.log(count); */
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
				  	<div id="showlogintoast" style="display: none;">${showlogintoast}</div>
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
	        <form id=logoutform action="App" method="post">
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

	<!-- Navigation bar -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light"> 
		<a class="navbar-brand" href="#">${name}</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item">
				<input type="button" id="change-background" class="btn" value="Change background color" />
			</li>
		</ul>
	  </div>
	</nav>
	

	<div class="row d-flex justify-content-center" style="margin-top: 35px">
		<div class="col-md-6" style="text-align: center;">
			<div>
				<h2>Welcome, ${name}!</h2>
			</div>
			<div>Last login: ${lastlogin}</div>
			<div>
				<input type="button" id="button-logout" class="btn btn-primary" value="logout" />
			</div>
		</div>
	</div>
</body>
</html>