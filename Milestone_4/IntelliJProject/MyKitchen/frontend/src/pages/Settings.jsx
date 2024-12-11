// Settings.jsx
import React from 'react';

function Settings({ userLoggedIn }) {
    if (!userLoggedIn) {
        return <div className="login-required">Please log in to access settings.</div>;
    }
    return <div className="container"><h1>Settings Page</h1></div>;
}

export default Settings;
