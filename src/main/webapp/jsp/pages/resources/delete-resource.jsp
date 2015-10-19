<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	<c:if test="${resourceDeleted}">
		<div
			class="alert alert-success"
			role="alert">Ressource ${resourceDeletedName} supprimée</div>
	</c:if>
	<c:if test="${resourceDeleteError}">
		<div
			class="alert alert-danger"
			role="alert">
			<strong>Erreur à la suppression de la ressource</strong><br>${resourceDeleteErrorMessage}</div>
	</c:if>
	<c:if test="${!resourceDeleted}">
		<p>Voulez vous vraiment supprimer la resource : ${resourceToDelete.name} ?</p>
	</c:if>
	<div>
		<a
			class="btn btn-default"
			href="resources"
			role="button">Retour</a>
		<c:if test="${!resourceDeleted}">
			<a
				class="btn btn-danger"
				href="delete-resource?id=${resourceToDelete.id}&delete=true"
				role="button">Supprimer</a>
		</c:if>
	</div>
</div>