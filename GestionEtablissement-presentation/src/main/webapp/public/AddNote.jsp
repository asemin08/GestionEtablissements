<%--
  Created by IntelliJ IDEA.
  User: Allan
  Date: 11/05/2021
  Time: 02:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!doctype html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <title>add note</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>

<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom bg-primary text-white">
    <div class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
        &nbsp;
    </div>

    <div class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <a href="Accueil" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
            <h1>Formulaire pour la gestion des notes</h1>
        </a>
    </div>

    <div class="col-md-3 text-end">
        <span><c:out value="${utilisateur.firstname}"/></span>
        <span><c:out value="${utilisateur.lastname}"/></span>
        <span><c:out value="${utilisateur.role}"/></span>
        <a href="/GestionEtablissement_presentation_war/login" class="btn btn-light">Déconnexion</a>
    </div>
</header>
<main class="container">


    <div id="form-note" class="row mt-5 justify-content-md-center">
        <div class="col-md-4  bg-info rounded">

            <form method="post" action="AjouterNote">
                <div class="col-md-12 row text-center">
                    <h1>Ajouter les notes</h1>
                </div>
                <div class="col-md-12 row mt-2">
                    <div class="col-md-6">
                        <label class="mb-2">Choisir un cours : </label>
                    </div>
                    <div class="col-md-6">
                        <select name="coursSelect" id="coursSelect" class="form-select">
                            <c:forEach var ="cours" items="${coursList}">
                                <option value="<c:out value = "${cours.id}"/>"> <c:out value = "${cours.courseSubject}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-6 mt-2">
                        <label class="mb-2">Notes: </label>
                    </div>
                    <div class="col-md-6 mt-2">
                        <c:if test = "${noteError == 'valid'}">
                            <input type="number" step="any" class="form-control is-valid" name="note" id="note" placeholder="note" value="<c:out value="${note}"></c:out>">
                        </c:if>
                        <c:if test = "${noteError == true}">
                            <input type="number" step="any" class="form-control is-invalid" name="note" id="note" placeholder="note" value="<c:out value="${note}"></c:out>">
                        </c:if>
                        <c:if test = "${noteError != true and noteError != 'valid'}">
                            <input  class="form-control" type="number" step="any" class="form-control" name="note" id="note" placeholder="note">
                        </c:if>
                    </div>
                </div>
                <div class="col-md-12 row mt-2">
                    <div class="col-md-6">
                        <label class="mb-2">Choisir un étudiant : </label>
                    </div>
                    <div class="col-md-6">
                        <select name="etudiantSelect" id="etudiantSelect" class="form-select">
                            <c:forEach var = "etudiant" items="${etudiantList}">
                                <option value="<c:out value = "${etudiant.id}"/>"> <c:out value = "${etudiant.firstname}"/> <c:out value = "${etudiant.lastname}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-6 mt-2">
                        <label class="mb-2">Appréciations: </label>
                    </div>
                    <div class="col-md-6 mt-2">
                        <c:if test = "${appreciationError == 'valid'}">
                            <input type="text"  class="form-control is-valid" name="appreciation" id="appreciation" placeholder="Appréciations" value="<c:out value="${appreciation}"></c:out>">
                        </c:if>
                        <c:if test = "${appreciationError == true}">
                            <input type="text" class="form-control is-invalid" name="appreciation" id="appreciation" placeholder="Appréciations" value="<c:out value="${appreciation}"></c:out>">
                        </c:if>
                        <c:if test = "${appreciationError != true and appreciationError != 'valid'}">
                            <input type="text" class="form-control"  class="form-control" name="appreciation" id="appreciation" placeholder="Appréciations">
                        </c:if>
                    </div>
                </div>
                <div class="row mt-4">
                    <div class="col text-center">
                        <input type="submit" class="btn btn-outline-success mb-2"  value="Ajouter" id="ajouter">
                    </div>
                </div>
                <div class="col-md-12 row text-center">
                    <span class="text-success"><c:out value="${info}"/></span>
                    <span class="text-danger"><c:out value="${error}"/></span>
                </div>
            </form>
        </div>
    </div>
</main>
</body>
</html>
