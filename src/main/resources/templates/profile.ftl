<#-- @ftlvariable name="profile" type="app.entities.Profile" -->

<#include "_html.ftl">

<#macro import>
    <script src="/js/like.js" defer></script>
</#macro>

<#macro body>
    <div class="row">
        <div class="col-2"></div>
        <div class="col">
            <div class="row">
                <div class="col-2">
                    <div class="row">
                        <div class="card" style="width: 13rem;">
                            <img src="0.jpeg" class="card-img-top">
                            <div class="card-body">
                                <h5 class="card-title">${profile.name}</h5>
                                <p class="card-text">${profile.location}</p>
                                <#if profile.status?has_content>
                                    <p class="card-text">${profile.status}</p>
                                <#else>
                                    <p class="card-text">Open to offers</p>
                                </#if>
                                <#if profile.id == user.id>
                                    <button class="btn btn-primary btn-block" type="button" data-toggle="modal"
                                            data-target="#editModal">Edit
                                    </button>
                                </#if>
                            </div>
                        </div>
                <#if profile.id == user.id>
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenu2"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Add profile section
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenu2">
                            <button class="dropdown-item" type="button" data-toggle="modal"
                                    data-target="#skillsModal">Skills
                            </button>
                            <button class="dropdown-item" type="button" data-toggle="modal"
                                    data-target="#eduModal">Education
                            </button>
                            <button class="dropdown-item" type="button" data-toggle="modal"
                                    data-target="#workModal">Work experience
                            </button>
                        </div>
                    </div>
                <#else>
                    <@security.authorize access="hasRole('ROLE_HUMAN')">
                        <#if !contacts?seq_contains(profile)>
                        <form action="/profile/${profile.id}/add" method="post">
                            <button class="btn btn-primary" type="submit">Add to contact</button>
                        </form>
                        </#if>
                    </@security.authorize>
                </#if>
                    </div>
                </div>
                <div class="col-8">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Skills</h5>
                            <#if profile.skills?has_content>
                                <#list profile.skills as skill>
                                    <p class="card-text">
                                        ${skill.getName()}
                                            <button id="span-${skill.id}" onclick="likeSkill(${skill.id})">+</button>
                                        <span id="likes-${skill.id}">${skill.likedBy?size}</span>
                                    </p>
                                </#list>
                            <#else>
                            <p class="card-text">
                                No skills yet.
                            </p>
                            </#if>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Education</h5>
                            <#if profile.educations?has_content>
                                <#list profile.educations as education>
                                    <p class="card-text">${education.startedAt}-${education.finishedAt}
                                        , ${education.school} -
                                        ${education.degree}, ${education.field}</p>
                                </#list>
                            <#else>
                                <p class="card-text">No education yet.</p>
                            </#if>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Work experience</h5>
                            <#if profile.workExperiences?has_content>
                                <#list profile.workExperiences as work>
                            <p class="card-text">${work.startedAt}-${work.finishedAt}, ${work.position}
                                in ${work.company}</p>
                                </#list>
                            <#else>
                                <p class="card-text">No work experience yet.</p>
                            </#if>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Interests</h5>
                            <#if profile.interests?has_content>
                                <#list profile.interests as interest>
                                <p class="card-text"><a href="/company/${interest.id}">${interest.name}</a></p>
                                </#list>
                            <#else>
                                <p class="card-text">No interests yet.</p>
                            </#if>
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
                    <form id="editForm" method="post" action="/profile/${user.id}/edit">
                        <input type="text" class="form-control" id="location" name="location" placeholder="Location"
                               value="${profile.location}" required>
                        <br>
                        <select class="custom-select" id="status" name="status" required>
                            <option selected disabled value="0">Choose status</option>
                            <option value="1">Actively applying</option>
                            <option value="2">Casually looking</option>
                            <option value="3">Open to offers</option>
                            <option value="4">Not open to offers</option>
                        </select>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" form="editForm">Save changes</button>
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
                    <form action="/profile/${user.id}/add_skill" method="post" id="skillForm">
                    <input type="text" class="form-control" id="skill-name" name="skillName"
                           placeholder="For example: Java" required>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type=submit class="btn btn-primary" form="skillForm">Save changes</button>
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
                    <form id="eduForm" action="/profile/${user.id}/add_edu" method="post">
                        <div class="row">
                            <div class="col">
                                <input type="number" class="form-control" id="start" name="startedAt" placeholder="2017" required>
                            </div>
                            <div class="col">
                                <input type="number" class="form-control" id="finish" name="finishedAt" placeholder="2021" required>
                            </div>
                        </div>
                        <br>
                        <input type="text" class="form-control" id="school" name="school" placeholder="School" required>
                        <br>
                        <input type="text" class="form-control" id="field" name="field" placeholder="Field" required>
                        <br>
                        <input type="text" class="form-control" id="degree" name="degree" placeholder="Degree" required>
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
                    <form id="workForm" action="/profile/${user.id}/add_work" method="post">
                        <div class="row">
                            <div class="col">
                                <input type="number" class="form-control" id="start" name="startedAt" placeholder="2017" required>
                            </div>
                            <div class="col">
                                <input type="number" class="form-control" id="finish" name="finishedAt" placeholder="2021" required>
                            </div>
                        </div>
                        <br>
                        <input type="text" class="form-control" id="company" name="company" placeholder="Company" required>
                        <br>
                        <input type="text" class="form-control" id="position" name="position" placeholder="Position" required>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" form="workForm">Save changes</button>
                </div>
            </div>
        </div>
    </div>
</#macro>
<@page></@page>