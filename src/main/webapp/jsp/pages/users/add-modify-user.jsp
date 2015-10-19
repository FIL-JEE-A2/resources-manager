<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	<c:if test="${userAdded}">
		<div
			class="alert alert-success"
			role="alert">Utilisateur ${userAddedName} ajouté</div>
	</c:if>
	<c:if test="${userModified}">
		<div
			class="alert alert-success"
			role="alert">Utilisateur ${userModifiedName} modifié</div>
	</c:if>
	<c:if test="${userAddError}">
		<div
			class="alert alert-danger"
			role="alert">
			<strong>Erreur à l'ajout de l'utilisateur</strong><br>${userAddErrorMessage}</div>
	</c:if>
	<c:if test="${userModifyError}">
		<div
			class="alert alert-danger"
			role="alert">
			<strong>Erreur à la modification de l'utilisateur</strong><br>${userModifyErrorMessage}</div>
	</c:if>
	<h2>Ajouter un utilisateur</h2>
	<form
		method="post"
		action="">
		<fieldset>
			<legend>Général</legend>
			<div class="form-group">
				<label for="userFirstName">Nom</label> <input
					type="text"
					class="form-control"
					id="userLastName"
					name="userLastName"
					placeholder="Nom"
					value="${previousUser.lastName}">
			</div>
			<div class="form-group">
				<label for="userFirstName">Prénom</label> <input
					type="text"
					class="form-control"
					id="userFirstName"
					name="userFirstName"
					placeholder="Prénom"
					value="${previousUser.firstName}">
			</div>
		</fieldset>
		<fieldset>
			<legend>Contact</legend>
			<div class="form-group">
				<label for="userEmail">Email</label> <input
					type="email"
					class="form-control"
					id="userEmail"
					name="userEmail"
					placeholder="Email"
					value="${previousUser.mail}">

			</div>
			<div class="form-group">
				<label for="userPhone">Numéro de téléphone</label> <input
					type="text"
					class="form-control"
					id="userPhone"
					name="userPhone"
					placeholder="Numéro de téléphone"
					value="${previousUser.phone}">
			</div>
		</fieldset>
		<fieldset>
			<legend>Connexion</legend>
			<div class="form-group">
				<label for="userFirstName">Nom d'utilisateur</label> <input
					type="text"
					class="form-control"
					id="userLogin"
					name="userLogin"
					placeholder="Login"
					value="${previousUser.login}">
				<p class="help-block">Identifiant utilisé pour se connecter</p>
			</div>
			<div class="form-group">
				<label for="userPassword">Mot de passe</label> <input
					type="password"
					class="form-control"
					id="userPassword"
					name="userPassword"
					placeholder="Mot de passe"
					value="${previousUser.password}">
			</div>
			<div class="form-group">
				<label for="userIsAdmin">Administrateur </label>
				<div>
					<label class="radio-inline"><input
						type="radio"
						name="userIsAdmin"
						value="true"
						${previousUser.admin ? 'checked="checked"' : ''}>Oui</label> <label
						class="radio-inline"><input
						type="radio"
						name="userIsAdmin"
						value="false"
						${previousUser==null || !previousUser.admin ? 'checked="checked"' : ''} />
						Non</label>
				</div>
				<p class="help-block">Un utilisateur administrateur pourra
					ajouter des ressources, des types et des utilsateur</p>
			</div>
		</fieldset>
		<!-- Button add/cancel -->
		<div class=text-right>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<a
						class="btn btn-default"
						href="list-user"
						role="button">Retour</a>
					<button
						type="submit"
						class="btn btn-default">${modifyUser?'Modifier':'Ajouter'}</button>
				</div>
			</div>
		</div>
	</form>
</div>