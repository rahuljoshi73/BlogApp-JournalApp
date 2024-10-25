document.addEventListener('DOMContentLoaded', function () {
    const content = document.getElementById('content');
    const journalEntryTemplate = document.getElementById('journal-entry-template').content;

    document.getElementById('login-link').addEventListener('click', showLoginForm);
    document.getElementById('register-link').addEventListener('click', showRegisterForm);
    document.getElementById('logout-link').addEventListener('click', logout);
    document.getElementById('home-link').addEventListener('click', fetchJournalEntries);

    function showLoginForm() {
        content.innerHTML = `
            <h2>Login</h2>
            <form id="login-form">
                <input type="text" id="login-username" placeholder="Username" required>
                <input type="password" id="login-password" placeholder="Password" required>
                <button type="submit">Login</button>
            </form>
        `;

        document.getElementById('login-form').addEventListener('submit', login);
    }

    function showRegisterForm() {
        content.innerHTML = `
            <h2>Register</h2>
            <form id="register-form">
                <input type="text" id="register-username" placeholder="Username" required>
                <input type="email" id="register-email" placeholder="Email" required>
                <input type="password" id="register-password" placeholder="Password" required>
                <button type="submit">Register</button>
            </form>
        `;

        document.getElementById('register-form').addEventListener('submit', register);
    }

    function login(event) {
        event.preventDefault();
        const username = document.getElementById('login-username').value;
        const password = document.getElementById('login-password').value;

        fetch('/user/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        })
            .then(response => {
                if (response.ok) {
                    alert('Login successful!');
                    fetchJournalEntries();
                    document.getElementById('logout-link').style.display = 'block';
                    document.getElementById('login-link').style.display = 'none';
                } else {
                    alert('Login failed. Please try again.');
                }
            });
    }

    function register(event) {
        event.preventDefault();
        const username = document.getElementById('register-username').value;
        const email = document.getElementById('register-email').value;
        const password = document.getElementById('register-password').value;

        fetch('/public/create-user', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ userName: username, email, password })
        })
            .then(response => {
                if (response.ok) {
                    alert('Registration successful! Please log in.');
                    showLoginForm();
                } else {
                    alert('Registration failed. Please try again.');
                }
            });
    }

    function fetchJournalEntries() {
        fetch('/journal')
            .then(response => response.json())
            .then(entries => {
                content.innerHTML = '';
                entries.forEach(entry => {
                    const entryClone = document.importNode(journalEntryTemplate, true);
                    entryClone.querySelector('.entry-title').textContent = entry.title;
                    entryClone.querySelector('.entry-content').textContent = entry.content;
                    entryClone.querySelector('.entry-date').textContent = new Date(entry.date).toLocaleString();
                    content.appendChild(entryClone);
                });
            });
    }

    function logout() {
        fetch('/user/logout', { method: 'POST' })
            .then(() => {
                alert('Logged out successfully!');
                document.getElementById('logout-link').style.display = 'none';
                document.getElementById('login-link').style.display = 'block';
                content.innerHTML = '';
            });
    }
});
