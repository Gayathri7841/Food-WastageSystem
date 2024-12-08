document.addEventListener('DOMContentLoaded', function () {
    // Logout button functionality
    const logoutButton = document.getElementById('logoutBtn');

    logoutButton.addEventListener('click', function (event) {
        event.preventDefault();
        window.location.href = '/showRecipientLoginForm'; // Redirect to the login page
    });
});
