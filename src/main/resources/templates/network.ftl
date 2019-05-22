<#include "_html.ftl">

<#macro body>
<div class="row">
    <div class="col-3">
        <div class="row">
            <div class="col-2"></div>
            <div class="card">
                <button type="button" class="btn btn-link">First degree connections</button>
                <button type="button" class="btn btn-link">Second degree connections</button>
                <button type="button" class="btn btn-link">Third degree connections</button>
            </div>
        </div>
    </div>
    <div class="col-8">
        <div class="row">
            <div class="card" style="width: 13rem;">
                <img src="0.jpeg" class="card-img-top">
                <div class="card-body">
                    <h5 class="card-title">Hello World</h5>
                    <p class="card-text">Position in Company</p>
                </div>
            </div>
        </div>
    </div>
</div>
</#macro>
<@page></@page>