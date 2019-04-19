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
}

const showHumanRegister = () => {
    document.getElementById('human-register').style.display = 'block';
    document.getElementById('company-register').style.display = 'none';
}

const showCompanyRegister = () => {
    document.getElementById('human-register').style.display = 'none';
    document.getElementById('company-register').style.display = 'block';
}
