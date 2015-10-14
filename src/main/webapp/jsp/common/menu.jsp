<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="panel panel-default">
	<div class="panel-heading">Utilisateur</div>
	<div class="panel-body">
		<p>
			<strong>${sessionScope.user.firstName}
				${sessionScope.user.lastName} </strong><br>${sessionScope.user.login}<br>
			<br> <a href="disconnect">Se déconnecter</a>
		</p>
	</div>
</div>
<div class="panel panel-default">
	<div class="panel-heading">Menu</div>
	<div class="panel-body">
		<ul class="nav nav-pills nav-stacked">
			<li role="presentation" class="active"><a href="home">Accueil</a></li>
			<li role="presentation"><a href="reservations">Réservations</a></li>
			<c:if test="${sessionScope.user.admin}">
				<li role="presentation"><a href="resources">Ressources</a></li>
				<li role="presentation"><a href="users">Utilisateurs</a></li>
				<li role="presentation"><a href="type-resources">Types de
						ressources</a></li>
			</c:if>
		</ul>
	</div>
</div>
