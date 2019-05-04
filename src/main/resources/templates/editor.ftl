<#include "_html.ftl">

<#macro navbar>
        <div class="nav-wrapper fixed-top">

            <nav class="navbar navbar-light bg-light navbar-expand navbar-fixed-top">
                <div class="col-1"></div>
                <nav class="navbar navbar-light bg-light justify-content-end">
                    <form class="form-inline">
                        <a class="navbar-brand">Editor</a>
                    </form>
                </nav>
                <div class="collapse navbar-collapse justify-content-end" id="navbarCollapse">
                    <button class="btn btn-primary" form="article" type="submit">Publish</button>
                </div>
                <div class="col-1"></div>
            </nav>
        </div>
</#macro>

<#macro body>
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
</#macro>
<@page></@page>