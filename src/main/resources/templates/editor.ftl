<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="/style.css">
    <title>Hello, world!</title>
</head>

<body>
<!-- navbar -->
<div class="nav-wrapper fixed-top">

    <nav class="navbar navbar-light bg-light navbar-expand navbar-fixed-top">
        <div class="col-1"></div>
        <nav class="navbar navbar-light bg-light justify-content-end">
            <form class="form-inline">
                <a class="navbar-brand">Editor</a>
            </form>
        </nav>
        <div class="collapse navbar-collapse justify-content-end" id="navbarCollapse">
            <button type="submit" class="btn btn-primary" form="article">Publish</button>
        </div>
        <div class="col-1"></div>
    </nav>
</div>
<!-- end of navbar -->

<form id="article" method="post" action="/editor">
    <div class="form-group row">
        <div class="col-1"></div>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="headline" name="headline" placeholder="Headline">
        </div>
    </div>
    <div class="form-group row">
        <div class="col-1"></div>
        <div class="col-sm-10">
            <div class="form-group">
                <textarea class="form-control" id="content" name="content" rows="20" placeholder="Write your text here..."></textarea>
            </div>
        </div>
    </div>
    <#--<button type="submit" class="btn btn-primary" form="article">Publish2</button>-->
</form>
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