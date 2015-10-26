<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div>
	<h2>Liste des ressources</h2>
	<!-- Add button -->
	<a
		class="btn btn-default"
		href="add-resource"
		role="button"><i class="fa fa-plus-circle"></i> Ajouter une
		ressource</a>
	<!-- Display users -->
	<table class="table">
		<thead>
			<tr>
				<th>Nom</th>
				<th>Type</th>
				<th>Description</th>
				<th>Localisation</th>
				<th>Responsable</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach
				items="${resourceList}"
				var="resource">
				<tr>
					<td>${resource.name}</td>
					<td>${resource.resourceType.type}</td>
					<td>${resource.description}</td>
					<td>${resource.localisation}</td>
					<td>${resource.manager.firstName} ${resource.manager.lastName}</td>
					<td>
						<!-- <a
						class="btn btn-default"
						href="display-user?id=${user.id}"
						role="button"><i class="fa fa-info-circle"></i></a> --> <a
						class="btn btn-default"
						href="modify-resource?id=${resource.id}"
						role="button"><i class="fa fa-pencil"></i></a> <a
						class="btn btn-danger"
						href="delete-resource?id=${resource.id}"
						role="button"><i class="fa fa-trash-o"></i></a>
					</td>
				</tr>
			</c:forEach>
	</table>

</div>