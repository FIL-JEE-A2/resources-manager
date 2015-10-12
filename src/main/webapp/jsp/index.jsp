<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/style/bootstrap.css">
<body>
	<%
		String contextPath = request.getContextPath();
		out.println(contextPath);
	%>
	<h2>Hello World!</h2>
	<p>Test de JSP redirigée avec FrontController -
		${pageContext.request.contextPath}</p>
	<c:set var="a" value="coucou" />
	<span style="color: #ff0000">${a}</span>
</body>
</html>
