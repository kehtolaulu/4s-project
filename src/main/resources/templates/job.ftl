<#-- @ftlvariable name="applied" type="java.util.List<app.entities.Job>" -->
<#include "_html.ftl">

<#macro body>
    <div class="row">
        <div class="col-1"></div>
        <div class="col-2">
        </div>
        <div class="col-6">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">${job.position}</h5>
                    <p class="card-text" id="company">${job.company.name}</p>
                    <p class="card-text" id="seniority">Seniority level: ${job.seniorityLevel}</p>
                    <p class="card-text" id="industry">Industry: ${job.industry.name}</p>
                    <p class="card-text" id="employmentType">Employment type: ${job.employmentType}</p>
                    <#if job.company.description?has_content>
                    <p class="card-text" id="about">About company:
                        ${job.company.description}
                    </p>
                    </#if>
                    <p class="card-text" id="jobFunctions">Job functions:
                        ${job.jobFunctions}</p>
                    <@security.authorize access="hasRole('ROLE_HUMAN')">
                        <button type="button" class="btn btn-light">Save</button>
                    <#if applied??>
                        <#if applied?seq_contains(job)>
                            You have applied.
                        <#else>
                        <form action="/jobs/${job.id}/apply" method="post">
                            <button type="submit" class="btn btn-primary">Apply</button>
                        </form>
                        </#if>
                    <#else>
                        <form action="/jobs/${job.id}/apply" method="post">
                            <button type="submit" class="btn btn-primary">Apply</button>
                        </form>
                    </#if>
                    </@security.authorize>
                </div>
                <div class="card-footer text-muted">
                    ${job.publishedAt}
                </div>
            </div>
        </div>
    </div>
</#macro>
<@page></@page>