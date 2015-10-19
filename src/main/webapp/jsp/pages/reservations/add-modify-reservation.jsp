<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
	
<script type="text/javascript">
	$(function () {
 		$('#dateDebutReservation').datetimepicker();
 		$('#dateFinReservation').datetimepicker();
 	});
</script>
	
<div>
	<c:if test="${reservationAdded}">
		<div
			class="alert alert-success"
			role="alert">Vous avez réservé ${reservationAddedResourceName} de ${reservationAddedStart} à ${reservationAddedStop}</div>
	</c:if>
	<c:if test="${reservationModified}">
		<div
			class="alert alert-success"
			role="alert">Réservation pour ${reservationAddedResourceName} modifiée</div>
	</c:if>
	<c:if test="${reservationAddError}">
		<div
			class="alert alert-danger"
			role="alert">
			<strong>Erreur lors de la réservation de ${reservationAddedResourceName}</strong><br>${reservationAddErrorMessage}</div>
	</c:if>
	<c:if test="${reservationModifyError}">
		<div
			class="alert alert-danger"
			role="alert">
			<strong>Erreur à la modification de la réservation de ${reservationAddedResourceName}</strong><br>${reservationModifyErrorMessage}</div>
	</c:if>
	<h2>${modifyReservation?'Modifier':'Ajouter'} une réservation</h2>
	<form
		id="addmodifyreservation"
		method="post"
		action="">
		<div class="form-group">
			<label for="resource">Ressource</label> <br> <select
				name="resource">
				<c:forEach
					items="${resourceList}"
					var="resource">
					<option
						value="${resource.id}"
						${(resource.id==previousResource.resource.id)?'selected="selected"':''}>${resource.name}</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
             <div class='input-group date' id="dateDebutReservation">
				<label for="reservationStart">Début de la Réservation</label> 
				<input
					type="text"
					class="form-control"
					id="reservationStart"
					name="reservationStart"
					value="${previousReservation.reservationStart}">
                <span class="input-group-addon">
                   <span class="glyphicon glyphicon-calendar"></span>
                </span>
             </div>
		</div>
		<div class="form-group">
             <div class='input-group date' id="dateFinReservation">
				<label for="reservationStop">Fin de la Réservation</label> 
				<input
					type="text"
					class="form-control"
					id="reservationStop"
					name="reservationStop"
					value="${previousReservation.reservationStop}">
                <span class="input-group-addon">
                   <span class="glyphicon glyphicon-calendar"></span>
                </span>
             </div>
		</div>
		<!-- Button add/cancel -->
		<div class=text-right>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<a
						class="btn btn-default"
						href="resources"
						role="button">Retour</a>
					<button
						type="submit"
						class="btn btn-default">${modifyReservation?'Modifier':'Ajouter'}</button>
				</div>
			</div>
		</div>
	</form>
</div>