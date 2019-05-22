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
        people: [],
        filter: {
            name: ''
        }
    },
    methods: {
        search() {
            let response = fetch('/people/search' + new GetParams(this.filter), {
                method: 'GET',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                credentials: 'include'
            })
                .then(r => r.json())
                .then(people => {
                    console.log(people);
                    this.people = people;
                });
        }
    }
});

Vue.component('person-item', {
    props: ['person'],
    template:
    `<div class="row">
        <div class="card" style="width: 13rem;">
            <div class="card-body">
                <h5 class="card-title"><a :href="'/profile/' + person.id" class="card-link">{{person.firstName}} {{person.lastName}}</a></h5>
                <p class="card-text">{{person.location}}</p>
            </div>
        </div>
     </div>`
});