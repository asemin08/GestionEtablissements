<%--
  Created by IntelliJ IDEA.
  User: Allan
  Date: 22/04/2021
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<!doctype html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <title>Formulaire authentification</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body  class="text-center">

<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom bg-primary text-white">

    <div class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        &nbsp;
    </div>

    <div class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <h1>Formulaire authentification</h1>
    </div>

    <div class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        &nbsp;
    </div>
</header>
<main class="container">

    <div id="form-content" class="row mt-5 justify-content-md-center">
        <div class="col-md-4  rounded">
            <form class="mt-5" id="form" name="form" method="post" action="login">
                <div class="form-floating">
                    <input type="text" name="login" id="login" class="form-control" id="floatingInput" placeholder="login">
                    <label for="floatingInput">login</label>
                </div>
                <div class="form-floating">
                    <input type="password" name="password" id="password" required class="form-control" id="floatingPassword" placeholder="mot de passe">
                    <label for="floatingPassword">mot de passe</label>
                </div>


                <button class="w-100 btn btn-lg btn-primary" type="submit">S'authentifier</button>

                <p class="text-danger"><c:out value="${error}"/></p>
            </form>
        </div>
    </div>


</main>

</body>
</html>