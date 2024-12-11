// components/NavBar.js
import React from 'react';

function NavBar() {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="container">
                <a className="navbar-brand" href="/">Recipe Finder</a>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav ms-auto">
                        <li className="nav-item">
                            <a className="nav-link" href="/">My Fridge</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/">Upload Ingredients</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/">Add Recipe</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    );
}

export default NavBar;