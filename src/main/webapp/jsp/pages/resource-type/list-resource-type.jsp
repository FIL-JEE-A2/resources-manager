<%@ page
        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"%>
<%@ taglib
        uri="http://java.sun.com/jsp/jstl/core"
        prefix="c"%>
<%--
  Created by IntelliJ IDEA.
  User: valentin
  Date: 19/10/15
  Time: 12:03
--%>
<div>
  <h2>Liste des utilisateurs</h2>
  <!-- Add button -->
  <a
          class="btn btn-default"
          href="add-resource-type"
          role="button"><i class="fa fa-plus-circle"></i> Ajouter un
    type de ressource</a>
  <!-- Display users -->
  <table class="table">
    <thead>
    <tr>
      <th>Type</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach
            items="${resourceTypeList}"
            var="rt">
    <tr>
      <td>${rt.type}</td>
      <td>
        <a class="btn btn-default" href="modify-resource-type?id=${rt.id}" role="button">
          <i class="fa fa-pencil"></i>
        </a>
        <a class="btn btn-default" href="delete-resource-type?id=${rt.id}" role="button">
          <i class="fa fa-trash-o"></i>
        </a>
      </td>
    </tr>
    </c:forEach>
  </table>
</div>