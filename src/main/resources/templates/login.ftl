<#assign  security=JspTaglibs["http://www.springframework.org/security/tags"] />
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

<div class="login-wrapper">
    <div class="row form-login">
        <div class="card">
            <div class="card-body">
                <div class="col-sm-12">
                    <form method="post" action="/login">
                        <div class="form-group row">
                            <label for="username"><b>Username</b></label>
                            <input type="text" class="form-control" placeholder="Enter Username" id="username" name="username"
                                   required>
                        </div>
                        <div class="form-group row">
                            <label for="password"><b>Password</b></label>
                            <input type="password" class="form-control" placeholder="Enter Password" id="password" name="password"
                                   required>
                        </div>
                        <div class="form-check row">
                            <label>
                                <input type="checkbox" class="form-check-input" checked="checked" name="remember">
                                Remember
                                me
                            </label>
                        </div>
                        <div class="row">
                            <button type="submit" class="btn btn-primary">Login</button>
                        </div>
                    </form>
                </div>
            </div>
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