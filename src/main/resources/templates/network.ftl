<#include "_html.ftl">

<#macro import>
    <script src="/js/network.js" defer></script>
</#macro>

<#macro body>
<div class="row">
    <div class="col-3">
        <div class="row">
            <div class="col-2"></div>
            <div class="card">
                <button type="button" class="btn btn-link" onclick="showFirst()">First degree connections</button>
                <button type="button" class="btn btn-link" onclick="showSecond()">Second degree connections</button>
                <button type="button" class="btn btn-link" onclick="showThird()">Third degree connections</button>
            </div>
        </div>
    </div>
    <div class="col-8" id="first">
        <div class="row">
            <#list first as profile>
                <div class="card" style="width: 13rem;">
                    <div class="card-body">
                        <h5 class="card-title"><a href="/profile/${profile.id}">${profile.firstName} ${profile.lastName}</a></h5>
                        <p class="card-text">${profile.location}</p>
                    </div>
                </div>
            </#list>
        </div>
    </div>
    <div class="col-8" id="second">
        <div class="row">
            <#list second as profile>
                <div class="card" style="width: 13rem;">
                    <div class="card-body">
                        <h5 class="card-title"><a href="/profile/${profile.id}">${profile.firstName} ${profile.lastName}</a></h5>
                        <p class="card-text">${profile.location}</p>
                    </div>
                </div>
            </#list>
        </div>
    </div>
    <div class="col-8" id="third">
        <div class="row">
            <#list third as profile>
                <div class="card" style="width: 13rem;">
                    <div class="card-body">
                        <h5 class="card-title"><a href="/profile/${profile.id}">${profile.firstName} ${profile.lastName}</a></h5>
                        <p class="card-text">${profile.location}</p>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</div>
</#macro>
<@page></@page>