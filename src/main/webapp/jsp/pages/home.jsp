<%@ page
		language="java"
		contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
<%@ taglib
		uri="http://java.sun.com/jsp/jstl/core"
		prefix="c"%>
<h4>
	Bonjour ${sessionScope.user.firstName} ${sessionScope.user.lastName}
</h4>
<a href="reservations">
	<button class="btn btn-primary" type="button">
		Vous avez <span class="badge">${nbReservations}</span> réservations
	</button>
</a>

<hr>

<h5>Vos 3 dernières réservations :</h5>

<table class="table">
	<thead>
	<tr>
		<th>Ressource réservée</th>
		<th>Début</th>
		<th>Fin</th>
		<th></th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${reservationList}" var="reservation">
		<tr>
			<td>${reservation.resource.name}</td>
			<td>${reservation.reservationStartLabel}</td>
			<td>${reservation.reservationStopLabel}</td>
			<td>
				<a class="btn btn-default" href="delete-reservation?id=${reservation.id}" role="button">
					<i class="fa fa-trash-o"></i>
				</a>
			</td>
		</tr>
	</c:forEach>
</table>

<a href="add-reservation">
	<button class="btn btn-default" type="button">
		<i class="fa fa-plus-circle"></i> Reserver une ressource
	</button>
</a>
