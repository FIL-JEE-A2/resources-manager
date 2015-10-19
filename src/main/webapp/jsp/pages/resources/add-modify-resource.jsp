<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	<c:if test="${resourceAdded}">
		<div
			class="alert alert-success"
			role="alert">Ressource ${resourceAddedName} ajout�e</div>
	</c:if>
	<c:if test="${resourceModified}">
		<div
			class="alert alert-success"
			role="alert">Ressource ${resourceModifiedName} modifi�e</div>
	</c:if>
	<c:if test="${resourceAddError}">
		<div
			class="alert alert-danger"
			role="alert">
			<strong>Erreur � l'ajout de la resource</strong><br>${resourceAddErrorMessage}</div>
	</c:if>
	<c:if test="${resourceModifyError}">
		<div
			class="alert alert-danger"
			role="alert">
			<strong>Erreur � la modification de la ressource</strong><br>${resourceModifyErrorMessage}</div>
	</c:if>
	<h2>${modifyResource?'Modifier':'Ajouter'}uneressource</h2>
	<form
		id="addmodifyresource"
		method="post"
		action="">
		<div class="form-group">
			<label for="resourceName">Nom</label> <input
				type="text"
				class="form-control"
				id="resourceName"
				name="resourceName"
				placeholder="Nom de la ressource"
				value="${previousResource.name}">
		</div>
		<div class="form-group">
			<label for="resourceDescription">Description</label>
			<textarea
				form="addmodifyresource"
				class="form-control"
				rows="4"
				name="resourceDescription"
				id="resourceDescription">${previousResource.description}</textarea>
		</div>
		<div class="form-group">
			<label for="managerUser">Responsable de la ressource</label> <br>
			<select name="managerUser">
				<c:forEach
					items="${userList}"
					var="user">
					<option
						value="${user.id}"
						${(user.id==previousResource.manager.id)?'selected="selected"':''}>${user.firstName}
						${user.lastName} (${user.login})</option>
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="resourceLocalisation">Localisation</label> <input
				type="text"
				class="form-control"
				id="resourceLocalisation"
				name="resourceLocalisation"
				placeholder="Bureau 123, Bat 3..."
				value="${previousResource.localisation}">
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
						class="btn btn-default">${modifyResource?'Modifier':'Ajouter'}</button>
				</div>
			</div>
		</div>
	</form>
</div>