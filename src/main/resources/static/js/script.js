const repeatPassword = () => {
    let btn = document.getElementById('submit');
    let message = document.getElementById('repeat_password_msg');
    if (document.getElementById('password').value !== document.getElementById('repeat_password').value) {
        message.innerHTML = 'Passwords don\'t match!';
        btn.disabled = true;
        return false;
    } else {
        message.innerHTML = '';
        btn.disabled = false;
        return true;
    }
};

const showHumanRegister = () => {
    document.getElementById('human-register').style.display = 'block';
    document.getElementById('company-register').style.display = 'none';
};

const showCompanyRegister = () => {
    document.getElementById('human-register').style.display = 'none';
    document.getElementById('company-register').style.display = 'block';
};

const newComment = () => {
    let text = $('#commentForm').val();
    if (text === '') {
        alert('too empty comment!')
    } else {
        $.ajax({
            url: window.location.pathname + '/comments',
            type: 'POST',
            data: {
                'text': text
            },
            success: (comment) => {
                console.log(comment);
                $('#no-comments').remove();
                let list = $('#comments-list');
                list.append(
                    `<div id="comment-item-${comment.id}" class="card">
                        <div class="card-body">
                            <p class="card-text" id="commentAuthor"><a href=#>${comment.author.name}</a></p>
                            <p class="card-text" id="commentContent">${comment.content}</p>
                      </div>
                      <div class="card-footer text-muted">
                        ${comment.publishedAt}
                       </div>
                       </div>
                     </div>`
                );
            }
        });
    }
    $('#commentForm').val('');
};

