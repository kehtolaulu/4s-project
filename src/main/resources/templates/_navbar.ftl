<#macro navbar>
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
                    <a href="/home" class="nav-link">Home</a>
                </li>
                <@security.authorize access="hasRole('ROLE_HUMAN')">
                    <li class="nav-item">
                        <a href="#" class="nav-link">My network</a>
                    </li>
                </@security.authorize>
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
                        <a class="dropdown-item" href="/logout">Log out</a>
                    </div>
                </li>
            </ul>
            <div class="col-2"></div>
        </div>
    </nav>
</div>
</#macro>
