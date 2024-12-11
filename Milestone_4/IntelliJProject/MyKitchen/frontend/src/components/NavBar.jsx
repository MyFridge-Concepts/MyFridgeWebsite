import React from 'react';
import { Link } from 'react-router-dom';

function NavBar({ userLoggedIn, onProfileClick }) {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light fixed-top shadow-sm">
            <div className="container">
                {/* Profile Icon */}
                {userLoggedIn ? (
                    <Link className="navbar-brand d-flex align-items-center" to="/userprofile" aria-label="Profile">
                        <i className="bi bi-person-circle fs-3"></i>
                    </Link>
                ) : (
                    <a className="navbar-brand d-flex align-items-center" href="#!" onClick={onProfileClick} aria-label="Profile">
                        <i className="bi bi-person-circle fs-3"></i>
                    </a>
                )}

                <Link className="navbar-brand ms-2" to="/">Recipe Finder</Link>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse justify-content-end" id="navbarNav">
                    <button className={`btn btn-primary me-2 ${!userLoggedIn ? 'disabled-button':''}`} title={!userLoggedIn?"Log in to view your fridge":""}>My Fridge</button>
                    <button className={`btn btn-secondary me-2 ${!userLoggedIn ? 'disabled-button':''}`} title={!userLoggedIn?"Log in to upload ingredients":""}>Upload Ingredients</button>
                    <button className={`btn btn-success me-2 ${!userLoggedIn ? 'disabled-button':''}`} title={!userLoggedIn?"Log in to add recipes":""}>+ Add Recipe</button>
                    <Link className={`btn btn-info me-2 ${!userLoggedIn ? 'disabled-button':''}`} to={userLoggedIn?"/settings":"#"} title={!userLoggedIn?"Log in to access settings":""}>Settings</Link>
                </div>
            </div>
        </nav>
    );
}

export default NavBar;
