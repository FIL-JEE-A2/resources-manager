<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	<c:if test="${userDeleted}">
		<div
			class="alert alert-success"
			role="alert">Utilisateur ${userDeletedName} supprimé</div>
	</c:if>
	<c:if test="${userDeleteError}">
		<div
			class="alert alert-danger"
			role="alert">
			<strong>Erreur à la suppression de l'utilisateur</strong><br>${userDeleteErrorMessage}</div>
	</c:if>
	<c:if test="${!userDeleted}">
		<p>Voulez vous vraiment supprimer l'utilisateur :
			${userToDelete.firstName} ${userToDelete.lastName}
			(${userToDelete.login})</p>
	</c:if>
	<div>
		<a
			class="btn btn-default"
			href="list-user"
			role="button">Retour</a>
		<c:if test="${!userDeleted}">
			<a
				class="btn btn-danger"
				href="delete-user?id=${userToDelete.id}&delete=true"
				role="button">Supprimer</a>
		</c:if>
	</div>
</div>