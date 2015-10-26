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
						<div class="filterName">Ressource</div>
						<input
							type="text"
							id="resource"
							name="resource"
							class="filterField"
							placeholder="Nom de la Ressource"
							value="${filterResource}" >
					</th>
					<th>
						<div class="filterName">Début</div>
						<select
							name="dateStartOperator"
							class="filterFieldList" >
							<option value=">=" ${(filterDateStartOperator=='>=')?'selected="selected"':'' }>>=</option>
							<option value="<=" ${(filterDateStartOperator=='<=')?'selected="selected"':'' }><=</option>
						</select>
						<input
							type="date"
							id="dateStart"
							name="dateStart"
							class="filterFieldDate"
							value="${filterDateStart}" >
					</th>
					<th>
						<div class="filterName">Fin</div>
						<select
							name="dateStopOperator"
							class="filterFieldList" >
							<option value=">=" ${(filterDateStopOperator=='>=')?'selected="selected"':'' }>>=</option>
							<option value="<=" ${(filterDateStopOperator=='<=')?'selected="selected"':'' }><=</option>
						</select>
						<input
							type="date"
							id="dateStop"
							name="dateStop"
							class="filterFieldDate"
							value="${filterDateStop}" >
					</th>
					<th>
						<div class="filterName">Utilisateur</div>
						<input
							type="text"
							id="userFirstName"
							name="userFirstName"
							class="filterFieldDouble"
							placeholder="Prénom"
							value="${filterUserFirstName}" >
						<input
							type="text"
							id="userLastName"
							name="userLastName"
							class="filterFieldDouble"
							placeholder="Nom"
							value="${filterUserLastName}" >
					</th>
					<th></th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach
					items="${reservationList}"
					var="reservation">
					<tr class="rowListReserv">
						<td class="fieldListReserv">${reservation.resource.name}</td>
						<td class="fieldListReserv">${reservation.reservationStartLabel}</td>
						<td class="fieldListReserv">${reservation.reservationStopLabel}</td>
						<td class="fieldListReserv">${reservation.user.firstName} ${reservation.user.lastName}</td>
						<td class="actionsListReserv">
							<a
							class="btn btn-default"
							href="delete-reservation?id=${reservation.id}"
							role="button"><i class="fa fa-trash-o"></i></a>
						</td>
					</tr>
				</c:forEach>
		</table>
	</form>
</div>