<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	<h2>Liste des utilisateurs</h2>
	<!-- Add button -->
	<a
		class="btn btn-default"
		href="add-user"
		role="button"><i class="fa fa-plus-circle"></i> Ajouter un
		utilisateur</a>
	<!-- Display users -->
	<table class="table">
		<thead>
			<tr>
				<th>Login</th>
				<th>Email</th>
				<th>Téléphone</th>
				<th>Nom Prénom</th>
				<th>Administrateur</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach
				items="${userList}"
				var="user">
				<tr>
					<td>${user.login}</td>
					<td>${user.mail}</td>
					<td>${user.phone}</td>
					<td>${user.firstName}${user.lastName}</td>
					<td><input
						type="checkbox"
						disabled="disabled"
						${user.admin ? 'checked="checked"' : ''}></td>
					<td>
						<!-- <a
						class="btn btn-default"
						href="display-user?id=${user.id}"
						role="button"><i class="fa fa-info-circle"></i></a> --> <a
						class="btn btn-default"
						href="modify-user?id=${user.id}"
						role="button"><i class="fa fa-pencil"></i></a> <a
						class="btn btn-default"
						href="delete-user?id=${user.id}"
						role="button"><i class="fa fa-trash-o"></i></a>
					</td>
				</tr>
			</c:forEach>
	</table>

</div>