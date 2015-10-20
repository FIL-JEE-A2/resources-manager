<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	<h2>Liste des reservations</h2>
	<!-- Add button -->
	<a
		class="btn btn-default"
		href="add-reservation"
		role="button"><i class="fa fa-plus-circle"></i> Réserver</a>
	<!-- Display users -->
	<table class="table">
		<thead>
			<tr>
				<th>Ressource réservée</th>
				<th>Début</th>
				<th>Fin</th>
				<th>Utilisateur</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach
				items="${reservationList}"
				var="reservation">
				<tr>
					<td>${reservation.resource.name}</td>
					<td>${reservation.reservationStartLabel}</td>
					<td>${reservation.reservationStopLabel}</td>
					<td>${reservation.user.firstName} ${reservation.user.lastName}</td>
					<td>
						<!-- <a
						class="btn btn-default"
						href="display-user?id=${user.id}"
						role="button"><i class="fa fa-info-circle"></i></a> --> <a
						class="btn btn-default"
						href="modify-resource?id=${reservation.id}"
						role="button"><i class="fa fa-pencil"></i></a> <a
						class="btn btn-default"
						href="delete-resource?id=${reservation.id}"
						role="button"><i class="fa fa-trash-o"></i></a>
					</td>
				</tr>
			</c:forEach>
	</table>

</div>