import React from 'react';

function SearchBar() {
    return (
        <section className="sticky-search">
            <div className="container">
                <form className="d-flex justify-content-center" action="#" method="get">
                    <input className="form-control form-control-lg w-75" type="search" placeholder="Search for recipes..." aria-label="Search" />
                    <button className="btn btn-primary ms-2" type="submit">Search</button>
                </form>
            </div>
        </section>
    );
}

export default SearchBar;
