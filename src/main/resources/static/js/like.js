const likeSkill = (skillId) => {
    let likes = document.getElementById(`likes-${skillId}`);
    let data = {
        method: 'POST',
        credentials: 'include',
        headers: { 'Content-type': 'application/x-www-form-urlencoded' },
    };
    fetch(`${window.location.pathname}/skills/${skillId}/like`, data).then(_ => console.log("OK"));

};

