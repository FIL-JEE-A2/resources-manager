<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<script type="text/javascript">
	$(function() {
		$('#dateDebutReservation').datetimepicker();
		$('#dateFinReservation').datetimepicker();
		$('#dateDebutReservation').data("DateTimePicker").date(
				new Date(parseInt('${reservationStartMillis}')));
		$('#dateFinReservation').data("DateTimePicker").date(
				new Date(parseInt('${reservationStopMillis}')));
	});
</script>

<div>
	<c:if test="${reservationAdded}">
		<div
			class="alert alert-success"
			role="alert">Vous avez réservé ${reservationAddedResourceName}
			de ${reservationAddedStart} à ${reservationAddedStop}</div>
	</c:if>
	<c:if test="${reservationAddError}">
		<div
			class="alert alert-danger"
			role="alert">
			<strong>Erreur lors de la réservation de
				${reservationAddedResourceName}</strong><br>${reservationAddErrorMessage}</div>
	</c:if>
	<c:if test="${reservationResearchError}">
		<div
			class="alert alert-danger"
			role="alert">${reservationResearchErrorMessage}</div>
	</c:if>
	<h2>Créer une réservation</h2>
	<form
		id="addmodifyreservation"
		method="post"
		action="">
		<div class="form-group">
			<label for="resource">Type de ressource</label> <br> <select
				name="selectedResourceType"
				class="form-control">
				<c:forEach
					items="${resourceTypeList}"
					var="resourceType">
					<option
						value="${resourceType.id}"
						${(resourceType.id==selectedResourceType)?'selected="selected"':''}>${resourceType.type}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="reservationStart">Début de la réservation</label>
			<div
				class='input-group date'
				id="dateDebutReservation">
				<input
					type="text"
					class="form-control"
					id="reservationStart"
					name="reservationStart"
					value="${previousReservation.reservationStart}"> <span
					class="input-group-addon"> <span
					class="glyphicon glyphicon-calendar"></span>
				</span>
			</div>
		</div>
		<div class="form-group">
			<label for="reservationStop">Fin de la réservation</label>
			<div
				class='input-group date'
				id="dateFinReservation">
				<input
					type="text"
					class="form-control"
					id="reservationStop"
					name="reservationStop"
					value="${previousReservation.reservationStop}"> <span
					class="input-group-addon"> <span
					class="glyphicon glyphicon-calendar"></span>
				</span>
			</div>
		</div>
		<!-- Button add/cancel -->
		<div class=text-right>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<a
						class="btn btn-default"
						href="reservations"
						role="button">Retour</a>
					<button
						type="submit"
						class="btn btn-default">Rechercher les ressources
						disponibles</button>
				</div>
			</div>
		</div>
		<c:if test="${resourceSearched}">
			<h2>Ressources disponibles</h2>
			<table class="table">
				<thead>
					<tr>
						<th>Nom</th>
						<th>Type</th>
						<th>Description</th>
						<th>Localisation</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach
						items="${freeResourceList}"
						var="resource">
						<tr>
							<td>${resource.name}</td>
							<td>${resource.resourceType.type}</td>
							<td>${resource.description}</td>
							<td>${resource.localisation}</td>
							<td>
								<button
									type="submit"
									class="btn btn-default"
									name="reservedResource"
									value="${resource.id}">
									<i class="glyphicon glyphicon-circle-arrow-right"></i> Réserver
								</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</form>
</div>