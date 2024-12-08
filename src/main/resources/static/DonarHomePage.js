document.addEventListener('DOMContentLoaded', function () {
    // Get the logout button
    const logoutButton = document.getElementById('logoutBtn');

    // Redirect to /showLoginForm when the logout button is clicked
    logoutButton.addEventListener('click', function () {
        window.location.href = '/showLoginForm';  // Redirect to the login page
    });
});
