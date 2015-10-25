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
	
	<form
		id="filterReservation"
		method="post"
		action="">
		
		<!-- Add button -->
		<a
			class="btn btn-default"
			href="add-reservation"
			role="button"><i class="fa fa-plus-circle"></i> Réserver</a>
	
		<input
			type="hidden"
			id="filter"
			name="filter"
			value="True" >
		
		<button
			type="submit"
			class="btn btn-default">Filtrer</button>
							
		<!-- Display users -->
		<table class="table">
			
			<thead>
				<tr>
					<th>
						Ressource réservée
						<input
							type="text"
							id="resource"
							name="resource"
							class="filterField"
							placeholder="Nom de la Ressource" >
					</th>
					<th>
						Début
						<select
							name="dateStartOperator"
							class="filterFieldList" >
							<option value=">">></option>
							<option value=">=">>=</option>
							<option value="<"><</option>
							<option value="<="><=</option>
						</select>
						<input
							type="date"
							id="dateStart"
							name="dateStart"
							class="filterField" >
					</th>
					<th>
						Fin
						<select
							name="dateStopOperator"
							class="filterFieldList" >
							<option value=">">></option>
							<option value=">=">>=</option>
							<option value="<"><</option>
							<option value="<="><=</option>
						</select>
						<input
							type="date"
							id="dateStop"
							name="dateStop"
							class="filterField" >
					</th>
					<th>
						Utilisateur
						<input
							type="text"
							id="userFirstName"
							name="userFirstName"
							class="filterField"
							placeholder="Prénom" >
						<input
							type="text"
							id="userLastName"
							name="userLastName"
							class="filterField"
							placeholder="Nom" >
					</th>
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
							href="modify-reservation?id=${reservation.id}"
							role="button"><i class="fa fa-pencil"></i></a> <a
							class="btn btn-default"
							href="delete-reservation?id=${reservation.id}"
							role="button"><i class="fa fa-trash-o"></i></a>
						</td>
					</tr>
				</c:forEach>
		</table>
	</form>
</div>