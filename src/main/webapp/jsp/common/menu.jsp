<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="panel panel-default">
	<div class="panel-heading">Utilisateur</div>
	<div class="panel-body">
		<p>
			<strong>${sessionScope.user.firstName} ${sessionScope.user.lastName}
			</strong><br>${sessionScope.user.login}<br> <br> <a href="disconnect">Se
				d�connecter</a>
		</p>
	</div>
</div>
<div class="panel panel-default">
	<div class="panel-heading">Menu</div>
	<div class="panel-body">
		<ul class="nav nav-pills nav-stacked">
			<li role="presentation" class="active"><a href="#">Home</a></li>
			<li role="presentation"><a href="#">Profile</a></li>
			<li role="presentation"><a href="#">Messages</a></li>
		</ul>
	</div>
</div>
