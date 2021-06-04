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
    <title>add cours</title>
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
            <h1>Formulaire pour la gestion d'un cours</h1>
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


    <div id="form-cours" class="row mt-5 justify-content-md-center">
        <div class="col-md-4  bg-info rounded">

            <form method="post" action="AjouterCours">

                <div class="col-md-12 row text-center">
                    <h1>Ajouter un cours</h1>
                </div>

                <div class="col-md-12 row mt-2">
                    <div class="col-md-6">
                        <label class="mb-2">théme du cours : </label>
                    </div>
                    <div class="col-md-6">
                        <c:if test = "${coursError == 'valid'}">
                            <input type="text" class="form-control is-valid" name="cours" id="cours" placeholder=" théme du cours" value="<c:out value="${cours}"></c:out>">
                        </c:if>
                        <c:if test = "${coursError == true}">
                            <input type="text" class="form-control is-invalid" name="cours" id="cours" placeholder="théme du cours" value="<c:out value="${cours}"></c:out>">
                        </c:if>
                        <c:if test = "${coursError != true and coursError != 'valid'}">
                            <input type="text" class="form-control" name="cours" id="cours" placeholder="théme du cours">
                        </c:if>
                    </div>
                </div>

                <div class="col-md-12 row mt-2">
                    <div class="col-md-6">
                        <label class="mb-2">Nombre d'heures : </label>
                    </div>
                    <div class="col-md-6">
                        <c:if test = "${nbhourError == 'valid'}">
                            <input type="number" step="any" class="form-control is-valid" name="nbhour" id="nbhour" placeholder="nombre d'heures" value="<c:out value="${nbhour}"></c:out>">
                        </c:if>
                        <c:if test = "${nbhourError == true}">
                            <input type="number" step="any" class="form-control is-invalid" name="nbhour" id="nbhour" placeholder="nombre d'heures" value="<c:out value="${nbhour}"></c:out>">
                        </c:if>
                        <c:if test = "${nbhourError != true and nbhourError != 'valid'}">
                            <input type="number" step="any" class="form-control" name="nbhour" id="nbhour" placeholder="nombre d'heures">
                        </c:if>
                    </div>
                </div>

                <div class="row mt-4">
                    <div class="col text-center">
                        <input type="submit" class="btn btn-outline-success mb-2"  value="ajouter" id="ajouterCours">
                    </div>
                </div>

                <div class="col-md-12 row text-center">
                    <span class="text-success"><c:out value="${infocours}"/></span>
                    <span class="text-danger"><c:out value="${errorcours}"/></span>
                </div>

            </form>
        </div>
    </div>


    <div id="form-associer" class="row mt-5 justify-content-md-center">
        <div class="col-md-4  bg-info rounded">

            <form method="post" action="AssocierEtudiantCours">
                <div class="col-md-12 row text-center">
                    <h1>Associer un étudiant a un cours : </h1>
                </div>


                <div class="col-md-12 row mt-2">
                    <div class="col-md-6">
                        <label class="mb-2">Choisir un etudiant : </label>
                    </div>
                    <div class="col-md-6">
                        <select name="etudiantSelect" id="etudiantSelect" class="form-select">
                            <c:forEach var = "etudiant" items="${etudiantList}">
                                <option value="<c:out value = "${etudiant.id}"/>"> <c:out value = "${etudiant.firstname}"/> <c:out value = "${etudiant.lastname}"/></option>
                            </c:forEach>
                        </select>
                    </div>
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
                </div>
                <div class="row mt-4">
                    <div class="col text-center">
                        <input type="submit" class="btn btn-outline-warning mb-2"  value="associer" id="associer">
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
