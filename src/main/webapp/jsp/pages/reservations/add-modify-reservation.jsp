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
		$('#dateDebutReservation')
				.data("DateTimePicker")
				.date(
						new Date(
								parseInt('${previousReservation.reservationStartMillis}')));
		$('#dateFinReservation')
				.data("DateTimePicker")
				.date(
						new Date(
								parseInt('${previousReservation.reservationStopMillis}')));
	});
</script>

<div>
	<c:if test="${reservationAdded}">
		<div
			class="alert alert-success"
			role="alert">Vous avez r�serv� ${reservationAddedResourceName}
			de ${reservationAddedStart} � ${reservationAddedStop}</div>
	</c:if>
	<c:if test="${reservationModified}">
		<div
			class="alert alert-success"
			role="alert">R�servation pour ${reservationModifiedResourceName}
			modifi�e</div>
	</c:if>
	<c:if test="${reservationAddError}">
		<div
			class="alert alert-danger"
			role="alert">
			<strong>Erreur lors de la r�servation de
				${reservationAddedResourceName}</strong><br>${reservationAddErrorMessage}</div>
	</c:if>
	<c:if test="${reservationModifyError}">
		<div
			class="alert alert-danger"
			role="alert">
			<strong>Erreur � la modification de la r�servation de
				${reservationModifiedResourceName}</strong><br>${reservationModifyErrorMessage}</div>
	</c:if>
	<h2>${modifyReservation?'Modifier':'Cr�er'} la r�servation</h2>
	<form
		id="addmodifyreservation"
		method="post"
		action="">
		<div class="form-group">
			<label for="resource">Ressource</label> <br> <select
				name="selectedResource">
				<c:forEach
					items="${resourceList}"
					var="resource">
					<option
						value="${resource.id}"
						${(resource.id==previousReservation.resource.id)?'selected="selected"':''}>${resource.name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="reservationStart">D�but de la R�servation</label>
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
			<label for="reservationStop">Fin de la R�servation</label>
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
						class="btn btn-default">${modifyReservation?'Modifier':'Cr�er'}</button>
				</div>
			</div>
		</div>
	</form>
</div>