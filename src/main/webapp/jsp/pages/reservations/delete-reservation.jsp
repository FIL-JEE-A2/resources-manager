<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	<c:if test="${reservationDeleted}">
		<div
			class="alert alert-success"
			role="alert">Réservation de ${reservationDeletedResourceName} du ${reservationDeletedStart} au ${reservationDeletedStop} supprimée</div>
	</c:if>
	<c:if test="${reservationDeleteError}">
		<div
			class="alert alert-danger"
			role="alert">
			<strong>Erreur à la suppression de la réservation</strong><br>${reservationDeleteErrorMessage}</div>
	</c:if>
	<c:if test="${!reservationDeleted}">
		<p>Voulez vous vraiment supprimer la réservation de ${reservationToDelete.resource.name} du ${reservationToDelete.reservationStartLabel} au ${reservationToDelete.reservationStopLabel}</p>
	</c:if>
	<div>
		<a
			class="btn btn-default"
			href="reservations"
			role="button">Retour</a>
		<c:if test="${!reservationDeleted}">
			<a
				class="btn btn-danger"
				href="delete-reservation?id=${reservationToDelete.id}&delete=true"
				role="button">Supprimer</a>
		</c:if>
	</div>
</div>