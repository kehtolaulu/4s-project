<#include "_html.ftl">

<#macro body>
<div class="row">
    <div class="col-2"></div>
    <div class="col">
        <div class="row">
            <div class="col-2">
                <div class="row">
                    <div class="card" style="width: 13rem;">
                        <div class="card-body">
                            <h5 class="card-title">${company.name}</h5>
                            <p class="card-text">${company.location}</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-8">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">About</h5>
                            <#if company.description?has_content>
                                <p class="card-text">${company.description}</p>
                            <#else>
                                <p class="card-text">No description yet.</p>
                            </#if>
                            <#if can_edit>
                                <button class="btn btn-primary" type="button" data-toggle="modal"
                                        data-target="#editModal">Edit
                                </button>
                            </#if>
                            <@security.authorize access="hasRole('ROLE_HUMAN')">
                                <#if profile.interests?has_content>
                                    <#if profile.interests?seq_contains(company)>
                                    <button class="btn btn-primary"
                                            onclick="fetch('/company/${company.id}/interest',
                                                    { method: 'DELETE', credentials: 'include' })">
                                        Delete from interests
                                    </button>
                                    </#if>
                                <#else>
                                        <#if profile.interests?has_content>
                                            <#if !profile.interests?seq_contains(company)>
                                                <button class="btn btn-primary" onclick="fetch('/company/${company.id}/interest',
                                                        { method: 'POST', credentials: 'include' })">
                                                    Add to interests
                                                </button>
                                            </#if>
                                        <#else>
                                        <button class="btn btn-primary" onclick="fetch('/company/${company.id}/interest',
                                                { method: 'POST', credentials: 'include' })">
                                            Add to interests
                                        </button>
                                        </#if>
                                </#if>
                            </@security.authorize>
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
                    <form id="editForm" method="post" action="/company/${user.id}/edit">
                        <textarea type="text" class="form-control" id="about" name="about"
                                  placeholder="About"></textarea>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" form="editForm">Save changes</button>
                </div>
            </div>
        </div>
    </div>
</#macro>
<@page></@page>