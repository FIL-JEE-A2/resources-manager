<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	<h2>Liste des utilisateurs</h2>
	<!-- Add button -->
	<a
		class="btn btn-default"
		href="add-user"
		role="button"><i class="fa fa-plus-circle"></i>  Ajouter un utilisateur</a>
	<!-- Display users -->
	<table class="table">
		<thead>
			<tr>
				<th>Login</th>
				<th>Email</th>
				<th>Nom</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Fake</td>
				<td>email@test.com</td>
				<td>MICHEL</td>
				<td>
					<!-- Details -->
					<a
						class="btn btn-default"
						href="display-user"
						role="button"><i class="fa fa-info-circle"></i></a>
					<!-- Modify user -->
					<a
						class="btn btn-default"
						href="modify-user"
						role="button"><i class="fa fa-pencil"></i></a>
					<!-- Remove user -->
					<a
						class="btn btn-default"
						href="remove-user"
						role="button"><i class="fa fa-trash-o"></i></a>
				</td>
			</tr>
	</table>

</div>