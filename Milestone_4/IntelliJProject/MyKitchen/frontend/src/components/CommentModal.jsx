import React from 'react';

function CommentModal({ onClose }) {
    const handleSubmit = (e) => {
        e.preventDefault();
        // Handle comment posting logic here
        // Possibly call a backend endpoint with comment data
        onClose();
    };

    return (
        <div className="modal fade show" style={{display:'block'}} tabIndex="-1" role="dialog">
            <div className="modal-dialog" role="document">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">Add a Comment</h5>
                        <button type="button" className="btn-close" onClick={onClose} aria-label="Close"></button>
                    </div>
                    <div className="modal-body">
                        <form onSubmit={handleSubmit}>
                            <div className="mb-3">
                                <label htmlFor="commentText" className="form-label">Your Comment</label>
                                <textarea className="form-control" id="commentText" rows="3" required></textarea>
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-secondary" onClick={onClose}>Cancel</button>
                                <button type="submit" className="btn btn-primary">Post Comment</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default CommentModal;
