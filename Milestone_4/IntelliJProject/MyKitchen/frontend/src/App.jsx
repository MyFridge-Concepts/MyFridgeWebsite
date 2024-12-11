import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import NavBar from './components/NavBar';
import SearchBar from './components/SearchBar';
import Recipes from './components/Recipes';
import SocialFeed from './components/SocialFeed';
import LoginModal from './components/LoginModal';
import UploadIngredientModal from './components/UploadIngredientModal';
import CommentModal from './components/CommentModal';
import UserProfile from './pages/UserProfile';
import Settings from './pages/Settings';

function App() {
    const [userLoggedIn, setUserLoggedIn] = useState(false);
    const [showLoginModal, setShowLoginModal] = useState(false);
    const [showUploadModal, setShowUploadModal] = useState(false);
    const [showCommentModal, setShowCommentModal] = useState(false);

    const handleLogin = () => {
        setUserLoggedIn(true);
        setShowLoginModal(false);
    };

    const handleSignUp = () => {
        setUserLoggedIn(true);
        setShowLoginModal(false);
    };

    const handleProfileClick = () => {
        if (!userLoggedIn) {
            setShowLoginModal(true);
        }
        // If logged in, NavBar profile icon leads to /userprofile via NavBar logic
    };

    const handleUploadIngredients = () => {
        if (!userLoggedIn) return;
        setShowUploadModal(true);
    };

    const handleAddComment = () => {
        if (!userLoggedIn) return;
        setShowCommentModal(true);
    };

    return (
        <Router>
            <NavBar
                userLoggedIn={userLoggedIn}
                onProfileClick={handleProfileClick}
            />
            <SearchBar />
            <div className="container my-4" style={{marginTop:'140px'}}>
                <Routes>
                    <Route
                        path="/"
                        element={
                            <>
                                <Recipes userLoggedIn={userLoggedIn} />
                                <SocialFeed userLoggedIn={userLoggedIn} onAddComment={handleAddComment} />
                            </>
                        }
                    />
                    <Route path="/userprofile" element={<UserProfile userLoggedIn={userLoggedIn}/>} />
                    <Route path="/settings" element={<Settings userLoggedIn={userLoggedIn}/>} />
                </Routes>
            </div>

            {showLoginModal && <LoginModal onClose={() => setShowLoginModal(false)} onLogin={handleLogin} onSignUp={handleSignUp} />}
            {showUploadModal && <UploadIngredientModal onClose={() => setShowUploadModal(false)} />}
            {showCommentModal && <CommentModal onClose={() => setShowCommentModal(false)} />}

        </Router>
    );
}

export default App;
