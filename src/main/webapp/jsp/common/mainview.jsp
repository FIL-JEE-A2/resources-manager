<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="/jsp/common/html-head.jsp" />
<body>
	<div class="header bg-primary">
		<jsp:include page="/jsp/common/header.jsp" />
	</div>
	<div class="menu">
		<jsp:include page="/jsp/common/menu.jsp" />
	</div>
	<div class="main-content">
		<jsp:include page="${pageUrl}" />
	</div>
	<div class="footer bg-primary">
		<p>Ressource manager - 2015</p>
	</div>
</body>
</html>