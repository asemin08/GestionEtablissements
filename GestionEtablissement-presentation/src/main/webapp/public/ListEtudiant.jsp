<%--
  Created by IntelliJ IDEA.
  User: Allan
  Date: 23/04/2021
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!doctype html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <title>Liste Etudiants</title>
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
            <h1>Liste des étudiants</h1>
        </a>
    </div>

    <div class="col-md-3 text-end">
        <span><c:out value="${utilisateur.firstname}"/></span>
        <span><c:out value="${utilisateur.lastname}"/></span>
        <span><c:out value="${utilisateur.role}"/></span>
        <a href="/GestionEtablissement_presentation_war/login" class="btn btn-light">Déconnexion</a>
    </div>
</header>

<div class="table-responsive">
    <div class="col-md-12 row text-center">
        <span class="text-success"><c:out value="${info}"/></span>
        <span class="text-danger"><c:out value="${error}"/></span>
    </div>
    <table class="table table-striped table-sm">
        <thead>
        <tr>
            <th scope="col">Nom</th>
            <th scope="col">Prénom</th>
            <th scope="col">Email</th>
            <th scope="col">Adresse</th>
            <th scope="col">Téléphone</th>
            <th scope="col">Date de naissance</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var = "etudiant" items="${etudiantList}">
            <tr>
                <th><c:out value = "${etudiant.firstname}"/></th>.
                <th><c:out value = "${etudiant.lastname}"/></th>
                <th><c:out value = "${etudiant.mailAddress}"/></th>
                <th><c:out value = "${etudiant.address}"/></th>
                <th><c:out value = "${etudiant.phoneNumber}"/></th>
                <th><c:out value = "${etudiant.dateOfBirth}"/></th>
                <th><a href="DeleteEtudiant?id=<c:out value = "${etudiant.id}"/>&redirect=false"><button type="button" class="btn btn-danger">Supprimer</button></a></th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>