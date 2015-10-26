<%@ page
        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8"%>
<%@ taglib
        uri="http://java.sun.com/jsp/jstl/core"
        prefix="c"%>
<div>
  <h2>Liste des types de ressource</h2>
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
        <a class="btn btn-danger" href="delete-resource-type?id=${rt.id}" role="button">
          <i class="fa fa-trash-o"></i>
        </a>
      </td>
    </tr>
    </c:forEach>
  </table>
</div>