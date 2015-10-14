<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<h4>Bonjour ${sessionScope.user.firstName}
	${sessionScope.user.lastName}</h4>
<p>TODO : tableaux des réservations</p>
<button class="btn btn-primary" type="button">
  Vous avez des réservations <span class="badge">4</span>
</button>