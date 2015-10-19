<%@ page
        language="java"
        contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<%@ taglib
        uri="http://java.sun.com/jsp/jstl/core"
        prefix="c" %>
<div>
    <!-- Affichage des erreurs -->
    <c:if test="${error}">
        <div class="alert alert-danger" role="alert">
            <strong>
                Erreur à
                <c:if test="${modification}">la modification</c:if>
                <c:if test="${ajout}">l'ajout</c:if>
                du type de ressource :
            </strong>
            <br>${errorMessage}
        </div>
    </c:if>

    <!-- Afichage du succes -->
    <c:if test="${success}">
        <div class="alert alert-success" role="alert">
            Le type de ressource ${typeName} à été
            <c:if test="${ajout}">ajouté</c:if>
            <c:if test="${modification}">modifié</c:if>
        </div>
    </c:if>

    <h2>
        <c:if test="${ajout}">Ajouter</c:if>
        <c:if test="${modification}">Modifier</c:if>
        un type de ressource
    </h2>

    <c:if test="${noId}">
        <div class="alert alert-danger" role="alert">
            <strong>Le type de ressource est introuvable.</strong> Veuillez réessayer.
        </div>
    </c:if>
    <c:if test="${!noId}">
        <form method="post" action="">
            <fieldset>
                <legend>Général</legend>
                <div class="form-group">
                    <label for="typeName">Nom</label>
                    <input type="text" class="form-control" id="typeName" name="typeName" placeholder="Nom"
                           value="${resourceType.type}">
                </div>
            </fieldset>
            <fieldset>
                <legend>Ressources liées</legend>
                <c:if test="${resourceType.resources != null}">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Nom</th>
                            <th>Localisation</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${resourceType.resources}" var="resource">
                            <tr>
                                <td>${resource.name}</td>
                                <td>${resource.localisation}</td>
                                <td>${resource.description}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${resourceType.resources == null}">
                    <p>Aucune ressource actuellement ratachée à ce type.</p>
                </c:if>
            </fieldset>
            <!-- Button add/cancel -->
            <div class=text-right>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <a class="btn btn-default" href="list-resource-type" role="button">Retour</a>
                        <button type="submit" class="btn btn-default">${modification?'Modifier':'Ajouter'}</button>
                    </div>
                </div>
            </div>
        </form>
    </c:if>
</div>