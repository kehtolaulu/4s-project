<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">
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
                <input class="form-control mr-sm-2" id="search-box" type="text" placeholder="Search..."
                       aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-1" type="submit">S</button>
            </form>
        </nav>
        <div class="collapse navbar-collapse justify-content-end" id="navbarCollapse">
            <ul class="nav nav-fill nav-pills">
                <li class="nav-item">
                    <a href="#" class="nav-link">Home</a>
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
                    <a class="nav-link dropdown-toggle active" href="#" id="navbarDropdownMenuLink"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Profile
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item active" href="#">View profile</a>
                        <a class="dropdown-item" href="#">Log out</a>
                    </div>
                </li>
            </ul>
            <div class="col-2"></div>
    </nav>
</div>
<!-- end of navbar -->
<div class="row">
    <div class="col-2"></div>
    <div class="col">
        <div class="row">
            <div class="col-2">
                <div class="row">
                    <div class="card" style="width: 13rem;">
                        <img src="0.jpeg" class="card-img-top">
                        <div class="card-body">
                            <h5 class="card-title">Hello World</h5>
                            <p class="card-text">Location</p>
                            <p class="card-text">Status</p>
                            <button class="btn btn-primary btn-block" type="button" data-toggle="modal"
                                    data-target="#editModal">Edit</button>
                        </div>
                    </div>
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Add profile section
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                            <button class="dropdown-item" type="button" data-toggle="modal"
                                    data-target="#skillsModal">Skills</button>
                            <button class="dropdown-item" type="button" data-toggle="modal"
                                    data-target="#eduModal">Education</button>
                            <button class="dropdown-item" type="button" data-toggle="modal"
                                    data-target="#workModal">Work experience</button>
                        </div>
                    </div>
                    <button class="btn btn-primary">Add to contact</button>
                </div>
            </div>
            <div class="col-8">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Skills</h5>
                        <p class="card-text">Skill
                            <span id="span" onclick="alert('to do')">+</span>
                        </p>
                    </div>
                </div>
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Education</h5>
                        <p class="card-text">2017-2021, School - Degree, Field</p>
                    </div>
                </div>
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Work experience</h5>
                        <p class="card-text">2017-2021, Position in Company</p>
                    </div>
                </div>
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Interests</h5>
                        <p class="card-text"><a href=#>Company</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">Edit profile</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input type="text" class="form-control" id="location" name="location" placeholder="Location">
                <br>
                <select class="custom-select" id="status" name="status">
                    <option selected value="1">Actively applying</option>
                    <option value="2">Casually looking</option>
                    <option value="3">Open to offers</option>
                    <option value="4">Not open to offers</option>
                </select>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="skillsModal" tabindex="-1" role="dialog" aria-labelledby="skillsModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="skillsModalLabel">Skills</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input type="text" class="form-control" id="skill-name" name="skill"
                       placeholder="For example: Java">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="eduModal" tabindex="-1" role="dialog" aria-labelledby="eduModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="eduModalLabel">Education</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="eduForm">
                    <div class="row">
                        <div class="col">
                            <input type="number" class="form-control" id="start" name="finish" placeholder="2017">
                        </div>
                        <div class="col">
                            <input type="number" class="form-control" id="finish" name="finish" placeholder="2021">
                        </div>
                    </div>
                    <br>
                    <input type="start" class="form-control" id="school" name="school" placeholder="School">
                    <br>
                    <input type="start" class="form-control" id="degree" name="degree" placeholder="Degree">
                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary" form="eduForm">Save changes</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="workModal" tabindex="-1" role="dialog" aria-labelledby="workModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="workModalLabel">Work experience</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="workForm">
                    <div class="row">
                        <div class="col">
                            <input type="number" class="form-control" id="start" name="start" placeholder="2017">
                        </div>
                        <div class="col">
                            <input type="number" class="form-control" id="finish" name="finish" placeholder="2021">
                        </div>
                    </div>
                    <br>
                    <input type="text" class="form-control" id="company" name="company" placeholder="Company">
                    <br>
                    <input type="text" class="form-control" id="position" name="position" placeholder="Position">
                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary" form="workForm">Save changes</button>
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