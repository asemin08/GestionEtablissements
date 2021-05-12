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
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script type="text/javascript" src="<c:url value="/public/js/Chart.js" />" async defer></script>
</head>
<body>

<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom bg-primary text-white">
    <div class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
        &nbsp;
    </div>

    <div class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <a href="Accueil" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
            <h1>Classement  des étudiants par moyenne</h1>
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


    <div class="mt-5 justify-content-md-center">
        <canvas id="barChartMoyenne"></canvas>
    </div>


    <div class="mt-5 mb-5 justify-content-md-center">
        <canvas id="pieCharMoyenne"></canvas>
    </div>
</main>
</body>
</html>
