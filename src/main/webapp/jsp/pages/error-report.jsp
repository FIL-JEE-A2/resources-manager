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
		<div
			class="alert alert-danger"
			role="alert">
			<strong>Une erreur innatendue s'est produite.</strong><br>Type
			de l'erreur : ${errorType}<br>Message de l'erreur :
			${errorMessage}
		</div>
		<div class="text-center">
			<a
				class="btn btn-default"
				href="home"
				role="button">Retourner à l'accueil</a>
		</div>
	</div>
</body>
</html>