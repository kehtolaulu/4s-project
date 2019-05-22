<#include "_html.ftl">

<#macro import>
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js" defer></script>
    <script src="/js/search_people.js" defer></script>
</#macro>

<#macro body>
<div class="container-fluid" id="app">
    <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
            <input type="text" class="form-control" id="search" placeholder="Search..." v-model="filter.name">
            <button class="btn btn-outline-success my-2 my-sm-1" type="submit" v-on:click="search()">Search</button>
        </div>
    </div>
    <person-item
            v-for="person in people"
            v-bind:key="person.id"
            v-bind:person="person"
    ></person-item>
</div>
</#macro>
<@page></@page>