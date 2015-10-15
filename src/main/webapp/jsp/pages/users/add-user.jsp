<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
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
					placeholder="Nom">
			</div>
			<div class="form-group">
				<label for="userFirstName">Prénom</label> <input
					type="text"
					class="form-control"
					id="userFirstName"
					name="userFirstName"
					placeholder="Prénom">
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
					placeholder="Email">

			</div>
			<div class="form-group">
				<label for="userPhone">Numéro de téléphone</label> <input
					type="text"
					class="form-control"
					id="userPhone"
					name="userPhone"
					placeholder="Numéro de téléphone">
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
					placeholder="Login">
				<p class="help-block">Identifiant utilisé pour se connecter</p>
			</div>
			<div class="form-group">
				<label for="userPassword">Mot de passe</label> <input
					type="password"
					class="form-control"
					id="userPassword"
					name="userPassword"
					placeholder="Mot de passe">
			</div>
		</fieldset>
		<!-- Button add/cancel -->
		<div class=text-right>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<a
						class="btn btn-default"
						href="list-user"
						role="button">Annuler</a>
					<button
						type="submit"
						class="btn btn-default">Ajouter</button>
				</div>
			</div>
		</div>
	</form>
</div>