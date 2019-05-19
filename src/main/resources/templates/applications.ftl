<#include "_html.ftl">

<#macro body>
<div class="row">
    <div class="col-1"></div>
    <div class="col-2">
    </div>
    <div class="col-6">
        <#if applications?has_content>
            <#list applications as application>
                <div class="card">
                    <div class="card-body">
                        <p class="card-text">Applicant: <a href=#>${application.profile.firstName}</a></p>
                        <p class="card-text">Job: <a href=#>${application.job.position}</a></p>
                        <p class="card-text">Email: ${application.profile.email}</p>
                        <p class="card-text">Phone number: ${application.profile.number}</p>
                    </div>
                    <div class="card-footer text-muted">
                        ${application.publishedAt}
                    </div>
                </div>
            </#list>
        </#if>
    </div>
</div>
</#macro>
<@page></@page>