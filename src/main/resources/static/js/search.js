class GetParams {
    constructor(obj) {
        this.params = obj;
    }

    valueOf() {
        return '?' + Object.keys(this.params)
            .map(k => `${encodeURIComponent(k)}=${encodeURIComponent(this.params[k])}`)
            .join('&');
    }
}

const app = new Vue({
    el: '#app',
    data: {
        filter: {
            position: '',
            industry: '',
            employment: ''
        },
        jobs: []
    },
    methods: {
        search() {
            let response = fetch('/search/jobs' + new GetParams(this.filter), {
                method: 'GET',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                credentials: 'include'
            })
                .then(r => r.json())
                .then(jobs => {
                    console.log(jobs);
                    this.jobs = jobs;
                });
        }
    }
});

Vue.component('job-item', {
    props: ['job'],
    template:
    `<div class="card text-center">
            <div class="card-body">
                <h5 class="card-title"><a :href="'/jobs/' + job.id" class="card-link">{{job.position}}</a></h5>
                <p class="card-text">{{job.company.name}}</p>
                <p class="card-text">{{job.jobFunctions}}
                </p>
            </div>
        <div class="card-footer text-muted">
            {{job.publishedAt}}
        </div>
      </div>`
});
