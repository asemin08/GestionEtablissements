<%--
  Created by IntelliJ IDEA.
  User: Allan
  Date: 23/04/2021
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!doctype html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <title>Menu Gestion Etablissement</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
</head>
<body>

<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom bg-primary text-white">
    <div class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
        &nbsp;
    </div>

    <div class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <h1>Menu de l'application Gestion Etablissement</h1>
    </div>

    <div class="col-md-3 text-end">
        <span><c:out value="${utilisateur.firstname}"/></span>
        <span><c:out value="${utilisateur.lastname}"/></span>
        <span><c:out value="${utilisateur.role}"/></span>
        <a href="/GestionEtablissement_presentation_war/login" class="btn btn-light">Déconnexion</a>
    </div>
</header>

<main class="container">
        <div class="row mt-5">
            <div class="list-group">
                <a href="AjouterEtudiant" class="list-group-item list-group-item-action">Créer un étudiant</a>
                <a href="GererEtudiant" class="list-group-item list-group-item-action">Gérer les étudiant</a>
                <a href="AjouterCours" class="list-group-item list-group-item-action">Gérer les cours</a>
                <a href="Statistiquebarchart" class="list-group-item list-group-item-action">Statistique barchart(JFREEchart)</a>
                <a href="Statistiquepiechart" class="list-group-item list-group-item-action">Statistique piechart(JFREEchart)</a>
                <a href="Statistique" class="list-group-item list-group-item-action">Statistique (chartjs non fonctionnel)</a>
                <a href="AjouterNote" class="list-group-item list-group-item-action">Gérer les notes</a>
                <c:if test="${utilisateur.role eq 'DIRECTOR' }">
                    <a href="ListeEtudiant" class="list-group-item list-group-item-action">Lister les étudiant</a>
                </c:if>
            </div>

        </div>
    </div>
</main>

</body>
</html>
