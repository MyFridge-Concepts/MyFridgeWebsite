import React from 'react';

function UploadIngredientModal({ onClose }) {
    const handleSubmit = (e) => {
        e.preventDefault();
        // Handle ingredient upload logic here
        // Possibly call a backend endpoint with form data
        onClose();
    };

    return (
        <div className="modal fade show" style={{display:'block'}} tabIndex="-1" role="dialog">
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">Upload Ingredient</h5>
                        <button type="button" className="btn-close" onClick={onClose} aria-label="Close"></button>
                    </div>
                    <div className="modal-body">
                        <form onSubmit={handleSubmit}>
                            <div className="mb-3">
                                <label htmlFor="ingredientCategory" className="form-label">Category</label>
                                <input type="text" className="form-control" id="ingredientCategory" required />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="ingredientName" className="form-label">Name</label>
                                <input type="text" className="form-control" id="ingredientName" required />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="ingredientServingSize" className="form-label">Average Serving Size</label>
                                <input type="text" className="form-control" id="ingredientServingSize" required />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="ingredientCalories" className="form-label">Calories</label>
                                <input type="number" className="form-control" id="ingredientCalories" required />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="ingredientCarbs" className="form-label">Carbs (g)</label>
                                <input type="number" step="0.1" className="form-control" id="ingredientCarbs" required />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="ingredientProtein" className="form-label">Protein (g)</label>
                                <input type="number" step="0.1" className="form-control" id="ingredientProtein" required />
                            </div>
                            <div className="mb-3">
                                <label htmlFor="ingredientSugar" className="form-label">Sugar (g)</label>
                                <input type="number" step="0.1" className="form-control" id="ingredientSugar" required />
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-secondary" onClick={onClose}>Cancel</button>
                                <button type="submit" className="btn btn-primary">Add Ingredient</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default UploadIngredientModal;
