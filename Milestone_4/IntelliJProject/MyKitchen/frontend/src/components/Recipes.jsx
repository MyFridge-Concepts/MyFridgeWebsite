import React from 'react';

function Recipes({ userLoggedIn }) {
    if (!userLoggedIn) {
        return <div className="login-required">Please log in to view recipes.</div>;
    }

    return (
        <section className="my-4" id="recipesSection">
            <h2 className="mb-3">Top Recipes</h2>
            <div className="row">
                {/* Example Recipe Cards */}
                <div className="col-md-4">
                    <div className="card">
                        <img src="images/recipe1.jpg" className="card-img-top" alt="Spaghetti Carbonara"/>
                        <div className="card-body">
                            <h5 className="card-title">Spaghetti Carbonara</h5>
                            <p className="card-text">⭐⭐⭐⭐</p>
                            <a href="#!" className="btn btn-primary">View Recipe</a>
                        </div>
                    </div>
                </div>
                {/* Add more recipe cards as needed */}
            </div>
            <div className="text-center my-4">
                <a href="recipes.html" className="btn btn-outline-primary btn-lg">Show More Recipes</a>
            </div>
        </section>
    );
}

export default Recipes;
