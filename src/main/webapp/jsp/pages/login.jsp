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
		<c:if test="${loginFailed}">
			<div
				class="alert alert-danger"
				role="alert">Identifiant/mot de passe incorrect</div>
		</c:if>

		<c:if test="${unauthorizedAction}">
			<div
				class="alert alert-danger"
				role="alert">Vous devez vous identifier pour accéder à cette
				page.</div>
		</c:if>

		<c:if test="${loginSuccess}">
			<c:redirect url="home" />
		</c:if>


		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Se connecter</h3>
			</div>
			<div class="panel-body">
				<form
					class="form-horizontal"
					method="post"
					action="">
					<div class="form-group">
						<label
							for="userId"
							class="col-sm-2 control-label">Identifiant</label>
						<div class="col-sm-10">
							<input
								type="text"
								class="form-control"
								id="userId"
								name="userId"
								placeholder="Identifiant">
						</div>
					</div>
					<div class="form-group">
						<label
							for="userPassword"
							class="col-sm-2 control-label">Mot de passe</label>
						<div class="col-sm-10">
							<input
								type="password"
								class="form-control"
								id="userPassword"
								name="userPassword"
								placeholder="Mot de passe">
						</div>
					</div>
					<!-- <div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label> <input type="checkbox"> Se souvenir de
									moi
								</label>
							</div>
						</div>
					</div> -->
					<div class=text-right>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button
									type="submit"
									class="btn btn-default">Connexion</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>