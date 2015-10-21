<%@ page
        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<%@ taglib
        uri="http://java.sun.com/jsp/jstl/core"
        prefix="c" %>
<div>
    <c:if test="${noId}">
        <div class="alert alert-danger" role="alert">
            <strong>Impossible d'accéder au type de ressource.</strong>
        </div>
    </c:if>
    <c:if test="${!noId}">
        <!-- Affichage du succès de la suppression -->
        <c:if test="${success}">
            <div class="alert alert-success" role="alert">
                Le type de ressource ${typeName} à été supprimé
            </div>
        </c:if>

        <!-- Affichage de l'erreur lors de la suppression du type de ressource -->
        <c:if test="${error}">
            <div class="alert alert-danger" role="alert">
                <strong>Erreur à la suppression de du type de ressource</strong>
                <br>${errorMessage}
            </div>
        </c:if>

        <c:if test="${!success}">
            <p>Voulez vous vraiment supprimer le type de ressource ${typeName} ?</p>
        </c:if>
        <div>
            <a class="btn btn-default" href="list-resource-type" role="button">Retour</a>
            <c:if test="${!success}">
                <a class="btn btn-danger" href="?id=${resourceType.id}&delete=true" role="button">Supprimer</a>
            </c:if>
        </div>
    </c:if>
</div>