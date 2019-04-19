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

const human = document.getElementById('human-register');
const company = document.getElementById('company-register');

const showHumanRegister = () => {
    human.style.display = 'block';
    company.style.display = 'none';
}

const showCompanyRegister = () => {
    human.style.display = 'none';
    company.style.display = 'block';
}