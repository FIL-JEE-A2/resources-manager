<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib
	uri="http://java.sun.com/jsp/jstl/core"
	prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/jsp/common/html-head.jsp" />
<body>
	<div class="single-content">
		<c:if test="${!created}">
			<div
				class="alert alert-danger"
				role="alert">
				<strong>Attention</strong><br>La création de données de
				démonstration pourrait altérer ou supprimer les données existantes.<br>Elle
				ne doit pas être lancée deux fois sur une même base de donnée.<br>Être
				vous sûr de vouloir vraiment créer les données de démonstration ?<br>
			</div>
			<form
				action=""
				method="post">
				<div class="text-center">
					<button
						type="submit"
						class="btn btn-danger"
						name="create"
						value="ok">
						<i class="glyphicon glyphicon-circle-arrow-right"></i> Créer
					</button>
				</div>
			</form>
		</c:if>
		<c:if test="${created}">
			<div
				class="alert alert-success"
				role="alert">
				<strong>Application de démonstration initialisée</strong><br>Les
				données de démonstration ont été initialisées.
			</div>
			<div class="text-center">
				<a
					class="btn btn-default"
					href="home"
					role="button">C'est parti !</a>
			</div>
		</c:if>
	</div>
</body>
</html>