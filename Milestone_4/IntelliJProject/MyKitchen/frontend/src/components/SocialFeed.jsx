import React, { useState, useEffect } from 'react';

function SocialFeed({ userLoggedIn, onAddComment }) {
    const [isLoading, setIsLoading] = useState(false);
    const [comments, setComments] = useState([
        {
            user: "User123",
            time: "3 days ago",
            text: "Just tried the Spaghetti Carbonara recipe, and it was amazing!"
        }
    ]);

    // Mock function to simulate loading more comments
    function generateMockComment() {
        return {
            user: "NewUser",
            time: "Just now",
            text: "This is a newly loaded comment!"
        };
    }

    const loadMoreComments = () => {
        if (!userLoggedIn) return;
        setIsLoading(true);
        setTimeout(() => {
            const newComments = [];
            for (let i=0; i<3; i++){
                newComments.push(generateMockComment());
            }
            setComments(prev => [...prev, ...newComments]);
            setIsLoading(false);
        }, 1000);
    };

    useEffect(() => {
        const onScroll = () => {
            if (isLoading || !userLoggedIn) return;
            if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight - 50) {
                loadMoreComments();
            }
        };

        window.addEventListener('scroll', onScroll);
        return () => window.removeEventListener('scroll', onScroll);
    }, [isLoading, userLoggedIn]);

    if (!userLoggedIn) {
        return <div className="login-required">Please log in to view the social feed.</div>;
    }

    return (
        <section className="my-4" id="socialFeedSection">
            <h2 className="mb-3">Social Feed</h2>
            <div className="mb-3 text-end">
                <button id="addCommentBtn"
                        className={`btn btn-outline-success ${!userLoggedIn?'disabled-button':''}`}
                        title={!userLoggedIn?"Log in to add comments":""}
                        onClick={onAddComment}>
                    <i className="bi bi-pencil-square"></i> Add Comment
                </button>
            </div>
            <div className="row social-feed-row">
                {comments.map((c, index) => (
                    <div className="col-md-6" key={index}>
                        <div className="card mb-3">
                            <div className="card-header d-flex align-items-center">
                                <img src="images/user1.jpg" alt={c.user} className="rounded-circle me-2" width="40" height="40"/>
                                <strong>{c.user}</strong>
                                <small className="text-muted ms-auto">{c.time}</small>
                            </div>
                            <div className="card-body">
                                <p className="card-text">{c.text}</p>
                            </div>
                            <div className="card-footer d-flex align-items-center">
                                <button className="btn btn-outline-primary btn-sm like-button">
                                    <i className="bi bi-hand-thumbs-up"></i> Like
                                </button>
                                <span className="ms-2 like-count">0 Likes</span>
                                <button className="btn btn-outline-secondary btn-sm ms-auto">
                                    <i className="bi bi-chat-dots"></i> Comment
                                </button>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
            <div className="loading-spinner" id="loadingSpinner" style={{display: isLoading ? 'block' : 'none'}}>
                <div className="spinner-border text-primary" role="status">
                    <span className="visually-hidden">Loading...</span>
                </div>
            </div>
            <div className="text-center my-4">
                <a href="socials.html" className="btn btn-outline-primary btn-lg">Show More Socials</a>
            </div>
        </section>
    );
}

export default SocialFeed;
