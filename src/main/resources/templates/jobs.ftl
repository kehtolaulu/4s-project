<#include "_html.ftl">

<#macro body>
    <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
            <div class="card text-center">

                <div class="card-body">
                    <@security.authorize access="hasRole('ROLE_HUMAN')">
                        <a href="#" class="btn btn-link">Saved jobs</a>
                        |
                        <a href="#" class="btn btn-link">Applied jobs</a>
                    </@security.authorize>
                    <@security.authorize access="hasRole('ROLE_COMPANY')">
                        <a href="/jobs/create" class="btn btn-link">Create a job</a>
                        |
                        <a href="/applications" class="btn btn-link">Applications</a>
                        |
                        <a href="#" class="btn btn-link">Our jobs</a>
                    </@security.authorize>
                </div>

            </div>
        </div>
        <div class="col-1"></div>
    </div>
    <div class="row">
        <div class="col-1"></div>
        <div class="col-2">
        </div>
        <div class="col-6">
        <#if jobs?has_content>
            <#list jobs as job>
            <div class="card text-center">
                <div class="card-body">
                    <h5 class="card-title"><a href="/jobs/${job.id}" class="card-link">${job.position}</a></h5>
                    <p class="card-text">${job.company.name}</p>
                    <p class="card-text">${job.jobFunctions}
                    </p>
                </div>
                <div class="card-footer text-muted">
                    ${job.publishedAt}
                </div>
            </div>
            </#list>
        </#if>
        </div>
    </div>
</#macro>
<@page></@page>