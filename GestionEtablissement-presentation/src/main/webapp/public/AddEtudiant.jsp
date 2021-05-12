<%--
  Created by IntelliJ IDEA.
  User: Allan
  Date: 23/04/2021
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!doctype html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <title>Add etudiant</title>
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
            <h1>Formulaire pour la création d'un étudiant</h1>
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


    <div id="form-content" class="row mt-5 justify-content-md-center">
        <div class="col-md-4  bg-info rounded">
            <form method="post" action="AjouterEtudiant">
                <div class="col-md-12 row text-center">
                    <h1>Etudiant</h1>
                </div>
                <div class="col-md-12 row  mt-2">
                    <div class="col-md-6">
                        <label for="nom" class="mb-2">Nom : </label>
                    </div>
                    <div class="col-md-6">
                        <c:if test = "${nomError == 'valid'}">
                            <input type="text" class="form-control is-valid" name="nom" id="nom" placeholder="nom" value="<c:out value="${nom}"></c:out>">
                        </c:if>
                        <c:if test = "${nomError == true}">
                            <input type="text" class="form-control is-invalid" name="nom" id="nom" placeholder="nom" value="<c:out value="${nom}"></c:out>">
                        </c:if>
                        <c:if test = "${nomError != true and nomError != 'valid'}">
                            <input type="text" class="form-control" name="nom" id="nom" placeholder="nom">
                        </c:if>
                    </div>
                </div>
                <div class="col-md-12 row  mt-2">
                    <div class="col-md-6">
                        <label class="mb-2">Prénom : </label>
                    </div>
                    <div class="col-md-6">
                        <c:if test = "${prenomError == 'valid'}">
                            <input type="text" class="form-control is-valid" name="prenom" id="prenom" placeholder="prenom" value="<c:out value="${prenom}"></c:out>">
                        </c:if>
                        <c:if test = "${prenomError == true}">
                            <input type="text" class="form-control is-invalid" name="prenom" id="prenom" placeholder="prenom" value="<c:out value="${prenom}"></c:out>">
                        </c:if>
                        <c:if test = "${prenomError != true and prenomError != 'valid'}">
                            <input type="text" class="form-control" name="prenom" id="prenom" placeholder="prenom">
                        </c:if>
                    </div>
                </div>
                <div class="col-md-12 row  mt-2">
                    <div class="col-md-6">
                        <label class="mb-2">Addresse : </label>
                    </div>
                    <div class="col-md-6">
                        <c:if test = "${addresseError == 'valid'}">
                            <input type="text" class="form-control is-valid" name="addresse" id="addresse" placeholder="addresse" value="<c:out value="${addresse}"></c:out>">
                        </c:if>
                        <c:if test = "${addresseError == true}">
                            <input type="text" class="form-control is-invalid" name="addresse" id="addresse" placeholder="addresse" value="<c:out value="${addresse}"></c:out>">
                        </c:if>
                        <c:if test = "${addresseError != true and addresseError != 'valid'}">
                            <input type="text" class="form-control" name="addresse" id="addresse" placeholder="addresse">
                        </c:if>
                    </div>
                </div>
                <div class="col-md-12 row  mt-2">
                    <div class="col-md-6">
                        <label class="mb-2">Téléphone : </label>
                    </div>
                    <div class="col-md-6">
                        <c:if test = "${telError == 'valid'}">
                            <input type="text" class="form-control is-valid" name="tel" id="tel" placeholder="tel" value="<c:out value="${tel}"></c:out>">
                        </c:if>
                        <c:if test = "${telError == true}">
                            <input type="text" class="form-control is-invalid" name="tel" id="tel" placeholder="tel" value="<c:out value="${tel}"></c:out>">
                        </c:if>
                        <c:if test = "${telError != true and telError != 'valid'}">
                            <input type="text" class="form-control" name="tel" id="tel" placeholder="tel">
                        </c:if>                    </div>
                </div>
                <div class="col-md-12 row  mt-2">
                    <div class="col-md-6">
                        <label class="mb-2">Email : </label>
                    </div>
                    <div class="col-md-6">
                        <c:if test = "${emailError == 'valid'}">
                            <input type="email" class="form-control is-valid" name="email" id="email" placeholder="email" value="<c:out value="${email}"></c:out>">
                        </c:if>
                        <c:if test = "${emailError == true}">
                            <input type="email" class="form-control is-invalid" name="email" id="email" placeholder="email" value="<c:out value="${email}"></c:out>">
                        </c:if>
                        <c:if test = "${emailError != true and emailError != 'valid'}">
                            <input type="email" class="form-control" name="email" id="email" placeholder="email">
                        </c:if>
                    </div>
                </div>
                <div class="col-md-12 row  mt-2">
                    <div class="col-md-6">
                        <label class="mb-2">Date de naissance : </label>
                    </div>
                    <div class="col-md-6">
                        <c:if test = "${dateError == 'valid'}">
                            <input type="date" class="form-control is-valid" name="date" id="date" placeholder="date" value="<c:out value="${date}"></c:out>">
                        </c:if>
                        <c:if test = "${dateError == true}">
                            <input type="date" class="form-control is-invalid" name="date" id="date" placeholder="date" value="<c:out value="${date}"></c:out>">
                        </c:if>
                        <c:if test = "${dateError != true and dateError != 'valid'}">
                            <input type="date" class="form-control" name="date" id="date" placeholder="date">
                        </c:if>
                    </div>
                </div>
                <div class="col-md-12 row mt-2">
                    <div class="col-md-6">
                        <label class="mb-2">Mot de passe : </label>
                    </div>
                    <div class="col-md-6">
                        <c:if test = "${passwordError == 'valid'}">
                            <input type="password" class="form-control is-valid" name="password" id="password" placeholder="password" value="<c:out value="${password}"></c:out>">
                        </c:if>
                        <c:if test = "${passwordError == true}">
                            <input type="password" class="form-control is-invalid" name="password" id="password" placeholder="password" value="<c:out value="${password}"></c:out>">
                        </c:if>
                        <c:if test = "${passwordError != true and passwordError != 'valid'}">
                            <input type="password" class="form-control" name="password" id="password" placeholder="mot de passe" required>
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