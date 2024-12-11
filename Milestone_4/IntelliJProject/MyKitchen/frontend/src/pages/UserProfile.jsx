// UserProfile.jsx
import React from 'react';

function UserProfile({ userLoggedIn }) {
    if (!userLoggedIn) {
        return <div className="login-required">Please log in to view your profile.</div>;
    }
    return <div className="container"><h1>User Profile</h1></div>;
}

export default UserProfile;
