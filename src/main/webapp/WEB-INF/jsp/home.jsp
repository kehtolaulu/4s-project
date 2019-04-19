<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/style.css">
    <title>Hello, world!</title>
</head>

<body>
<!-- navbar -->
<div class="nav-wrapper fixed-top">
    <nav class="navbar navbar-light bg-light navbar-expand navbar-fixed-top">
        <div class="col-1"></div>

        <nav class="navbar navbar-light bg-light justify-content-end">
            <form class="form-inline">
                <a href="#" class="navbar-brand">Logo</a>
                <input class="form-control mr-sm-2" id="search-box" type="text" placeholder="Write here..."
                       aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-1" type="submit">Search</button>
            </form>
        </nav>
        <div class="collapse navbar-collapse justify-content-end" id="navbarCollapse">
            <ul class="nav nav-fill nav-pills">
                <li class="nav-item">
                    <a href="#" class="nav-link active">Home</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">My network</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">Jobs</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">Messaging</a>
                </li>
            </ul>

            <ul class="nav nav-pills justify-content-end">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        Profile
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="#">View profile</a>
                        <a class="dropdown-item" onclick="document.forms['logoutForm'].submit()">Log out</a>
                    </div>
                </li>
            </ul>
            <div class="col-2"></div>
        </div>
    </nav>
</div>
<!-- end of navbar -->
<div class="page-wrapper">
    <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
            <div class="card text-center">
                <div class="card-body">
                    <a href="/editor" class="card-link">Write an article</a>
                </div>
            </div>
            <c:forEach items="${posts}" var="p">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title"><a href="#" class="card-link">${p.headline}</a></h5>
                        <p class="card-text">Author: ${p.author.firstName} ${p.author.lastName}</p>
                    </div>
                    <div class="card-footer text-muted">
                            ${p.publishedAt}
                    </div>
                </div>
            </c:forEach>

            <form id="logoutForm" method="POST" action="/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>

</html>