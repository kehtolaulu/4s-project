<#include "_html.ftl">

<#macro navbar></#macro>

<#macro body>
<div class="row">
    <div class="col-2"></div>
    <div class="col-8">
        <div class="card text-center">
            <div class="card-body">
                <button type="button" class="btn btn-link" onclick="showHumanRegister()">Register as a
                    person</button> |
                <button type="button" class="btn btn-link" onclick="showCompanyRegister()">Register as a
                    company</button>
            </div>
        </div>
    </div>
    <div class="col-2"></div>
</div>
<!-- HUMAN REGISTER -->
<div id="human-register">
    <div class="row">
        <div class="col-2"></div>
        <div class="col-8">
            <div class="card">
                <div class="card-body">
                    <form action="/register/profile" method="post">
                        <div class="form-row">
                            <div class="col-1"></div>
                            <div class="form-group col-5">
                                <input name='firstName' type="text" class="form-control" id="firstname"
                                       placeholder="First name" required>
                            </div>
                            <div class="form-group col-5">
                                <input name='lastName' type="text" class="form-control" id="lastname"
                                       placeholder="Last name" required>
                            </div>
                            <div class="col-1"></div>
                        </div>
                        <div class="form-row">
                            <div class="col-1"></div>
                            <div class="form-group col-5">
                                <input name='location' type="text" class="form-control" id="location"
                                       placeholder="Location" required>
                            </div>
                            <div class="form-group col-5">
                                <input name='birthDate' type="date" class="form-control" id="birthDate"
                                       value="1995-02-02" required>
                            </div>
                            <div class="col-1"></div>
                        </div>
                        <div class="form-row">
                            <div class="col-1"></div>
                            <div class="form-group col-5">
                                <input name='email' type="email" class="form-control" id="email" placeholder="Email"
                                       required>
                            </div>
                            <div class="form-group col-5">
                                <input name='phoneNumber' type="text" class="form-control" id="phoneNumber"
                                       placeholder="Phone number" required>
                            </div>
                            <div class="col-1"></div>
                        </div>
                        <div class="form-row">
                            <div class="col-1"></div>
                            <div class="form-group col-5">
                                <input name="password" type="password" class="form-control" id="humanPassword"
                                       placeholder="Password" required>
                                <div id="password_msg"></div>
                            </div>
                            <div class="form-group col-5">
                                <input type="password" class="form-control" id="repeat_password"
                                       placeholder="Repeat password" required onblur="repeatPassword()">
                                <div id="repeat_password_msg"></div>
                            </div>
                            <div class="col-1"></div>
                        </div>
                        <div class="row">
                            <div class="col-5"></div>
                            <div class="col-2">
                                <button type="submit" class="btn btn-primary btn-block" id="submit">Submit</button>
                            </div>
                            <div class="col-5"></div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-2"></div>
        </div>
    </div>
</div>
<!-- COMPANY REGISTER -->
<div id="company-register">
    <div class="row">
        <div class="col-2"></div>
        <div class="col-8">
            <div class="card">
                <div class="card-body">
                    <form action="/register/company" method="post">
                        <div class="form-row">
                            <div class="col-1"></div>
                            <div class="form-group col-5">
                                <input name='name' type="text" class="form-control" id="companyName"
                                       placeholder="Company name" required>
                            </div>
                            <div class="form-group col-5">
                                <input name='location' type="text" class="form-control" id="companyLocation"
                                       placeholder="Location" required>
                            </div>
                            <div class="col-1"></div>
                        </div>
                        <div class="form-row">
                            <div class="col-1"></div>
                            <div class="form-group col-5">
                                <input name='email' type="email" class="form-control" id="companyEmail" placeholder="Email"
                                       required>
                            </div>
                            <div class="form-group col-5">
                                <input name='phoneNumber' type="text" class="form-control" id="companyNumber"
                                       placeholder="Phone number" required>
                            </div>
                            <div class="col-1"></div>
                        </div>
                        <div class="form-row">
                            <div class="col-1"></div>
                            <div class="form-group col-5">
                                <input name="password" type="password" class="form-control" id="companyPassword"
                                       placeholder="Password" required>
                                <div id="password_msg"></div>
                            </div>
                            <div class="form-group col-5">
                                <input type="password" class="form-control" id="repeat_password"
                                       placeholder="Repeat password" required onblur="repeatPassword()">
                                <div id="repeat_password_msg"></div>
                            </div>
                            <div class="col-1"></div>
                        </div>
                        <div class="row">
                            <div class="col-5"></div>
                            <div class="col-2">
                                <button type="submit" class="btn btn-primary btn-block" id="submit">Submit</button>
                            </div>
                            <div class="col-5"></div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-2"></div>
        </div>
    </div>
</div>
</#macro>
<@page></@page>