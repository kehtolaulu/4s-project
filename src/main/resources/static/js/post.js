Vue.component('post-comment', {
    props: ['comment'],
    template: `
    <div id="comment-item-{{ comment.id }}" class="card">
        <div class="card-body">
            <p class="card-text" id="commentAuthor">
                <a href=#>{{ comment.author.name }}</a>
            </p>
            <p class="card-text" id="commentContent">{{ comment.content }}</p>
        </div>
        <div class="card-footer text-muted">{{ comment.publishedAt }}</div>
    </div>
  `
});

const app = new Vue({
    el: '#vueapp',
    data: {
        comments: [],
        commentInput: ''
    },
    methods: {
        createComment() {
            let data = {
                method: 'POST',
                credentials: 'include',
                headers: { 'Content-type': 'application/x-www-form-urlencoded' },
                body: `text=${this.commentInput}`
            };
            fetch(`${window.location.pathname}/comments`, data)
                .then(response => response.json())
                .then(comment => this.comments.push(comment));
            this.commentInput = '';
        },
        onCreate() {
            fetch(`${window.location.pathname}/comments`, {
                method:'GET'
            }).then(data => data.json())
                .then(comments => {
                comments.forEach(c => this.comments.push(c));
            });
        }
    }
});

app.onCreate();
