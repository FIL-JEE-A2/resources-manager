<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<div class="panel panel-default">
	<div class="panel-heading">Utilisateur</div>
	<div class="panel-body">
		<p>
			<strong>${sessionScope.user.firstName}
				${sessionScope.user.lastName} </strong> (${sessionScope.user.login})<br>
			<br> <a href="disconnect">Se déconnecter</a>
		</p>
	</div>
</div>
<div class="panel panel-default">
	<div class="panel-heading">Menu</div>
	<div class="panel-body">
		<!-- TODO : set the active tab -->
		<ul class="nav nav-pills nav-stacked">
			<li
				role="presentation"
				${homeCategory?'class="active"':''}><a href="home">Accueil</a></li>
			<li
				role="presentation"
				${reservationCategory?'class="active"':''}><a href="reservations">Réservations</a></li>
			<c:if test="${sessionScope.user.admin}">
				<li
					role="presentation"
					${ressourceCategory?'class="active"':''}><a href="resources">Ressources</a></li>
				<li
					role="presentation"
					${userCategory?'class="active"':''}><a href="list-user">Utilisateurs</a></li>
				<li
					role="presentation"
					${ressourceTypeCateogory?'class="active"':''}><a href="list-resource-type">Types
						de ressources</a></li>
			</c:if>
		</ul>
	</div>
</div>
