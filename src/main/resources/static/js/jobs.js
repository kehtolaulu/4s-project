const showAllJobs = () => {
    document.getElementById('jobs').style.display = 'block';
    document.getElementById('saved').style.display = 'none';
    document.getElementById('applied').style.display = 'none';
    document.getElementById('applications').style.display = 'none';
    document.getElementById('ours').style.display = 'none';

};

const showSavedJobs = () => {
    document.getElementById('jobs').style.display = 'none';
    document.getElementById('saved').style.display = 'block';
    document.getElementById('applied').style.display = 'none';
    document.getElementById('applications').style.display = 'none';
    document.getElementById('ours').style.display = 'none';
};

const showAppliedJobs = () => {
    document.getElementById('jobs').style.display = 'none';
    document.getElementById('saved').style.display = 'none';
    document.getElementById('applied').style.display = 'block';
    document.getElementById('applications').style.display = 'none';
    document.getElementById('ours').style.display = 'none';
};

const showOurJobs = () => {
    document.getElementById('jobs').style.display = 'none';
    document.getElementById('saved').style.display = 'none';
    document.getElementById('applied').style.display = 'none';
    document.getElementById('applications').style.display = 'none';
    document.getElementById('ours').style.display = 'block';
};
showAllJobs();
