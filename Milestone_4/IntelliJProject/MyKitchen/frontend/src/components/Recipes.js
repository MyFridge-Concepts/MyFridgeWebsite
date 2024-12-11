// components/Recipes.js
import React from 'react';

function Recipes() {
    const recipes = [
        { title: 'Spaghetti Carbonara', rating: '⭐⭐⭐⭐' },
        { title: 'Chicken Curry', rating: '⭐⭐⭐' },
        { title: 'Vegetable Stir Fry', rating: '⭐⭐⭐⭐⭐' }
    ];

    return (
        <section className="container my-4">
            <h2>Top Recipes</h2>
            <div className="row">
                {recipes.map((recipe, index) => (
                    <div className="col-md-4" key={index}>
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">{recipe.title}</h5>
                                <p className="card-text">{recipe.rating}</p>
                                <a href="#" className="btn btn-primary">View Recipe</a>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </section>
    );
}

export default Recipes;