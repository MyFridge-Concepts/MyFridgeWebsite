// components/SearchBar.js
import React from 'react';

function SearchBar() {
    return (
        <div className="sticky-search">
            <div className="container">
                <form className="d-flex">
                    <input className="form-control" type="search" placeholder="Search for recipes..." aria-label="Search" />
                    <button className="btn btn-primary" type="submit">Search</button>
                </form>
            </div>
        </div>
    );
}

export default SearchBar;