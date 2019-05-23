const showFirst = () => {
    document.getElementById('first').style.display = 'block';
    document.getElementById('second').style.display = 'none';
    document.getElementById('third').style.display = 'none';
};

const showSecond = () => {
    document.getElementById('first').style.display = 'none';
    document.getElementById('second').style.display = 'block';
    document.getElementById('third').style.display = 'none';
};

const showThird = () => {
    document.getElementById('first').style.display = 'none';
    document.getElementById('second').style.display = 'none';
    document.getElementById('third').style.display = 'block';
};

showFirst();