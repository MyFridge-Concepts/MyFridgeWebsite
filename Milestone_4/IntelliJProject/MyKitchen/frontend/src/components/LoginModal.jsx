import React from 'react';

function LoginModal({ onClose, onLogin, onSignUp }) {
    const handleLoginSubmit = (e) => {
        e.preventDefault();
        onLogin();
    };

    const handleSignUpSubmit = (e) => {
        e.preventDefault();
        onSignUp();
    };

    return (
        <div className="modal fade show" style={{display:'block'}} tabIndex="-1">
            <div className="modal-dialog modal-dialog-centered">
                <div className="modal-content">
                    <div className="modal-header">
                        <h5 className="modal-title">Login / Sign Up</h5>
                        <button type="button" className="btn-close" aria-label="Close" onClick={onClose}></button>
                    </div>
                    <div className="modal-body">
                        <ul className="nav nav-tabs" id="authTab" role="tablist">
                            <li className="nav-item">
                                <button className="nav-link active" data-bs-toggle="tab" data-bs-target="#login-tab-pane">Login</button>
                            </li>
                            <li className="nav-item">
                                <button className="nav-link" data-bs-toggle="tab" data-bs-target="#signup-tab-pane">Sign Up</button>
                            </li>
                        </ul>
                        <div className="tab-content p-3" id="authTabContent">
                            <div className="tab-pane fade show active" id="login-tab-pane">
                                <form onSubmit={handleLoginSubmit}>
                                    <div className="mb-3">
                                        <label htmlFor="loginEmail" className="form-label">Email</label>
                                        <input type="email" className="form-control" id="loginEmail" required/>
                                    </div>
                                    <div className="mb-3">
                                        <label htmlFor="loginPassword" className="form-label">Password</label>
                                        <input type="password" className="form-control" id="loginPassword" required/>
                                    </div>
                                    <button type="submit" className="btn btn-primary w-100">Login</button>
                                </form>
                            </div>
                            <div className="tab-pane fade" id="signup-tab-pane">
                                <form onSubmit={handleSignUpSubmit}>
                                    <div className="mb-3">
                                        <label htmlFor="signupName" className="form-label">Name</label>
                                        <input type="text" className="form-control" id="signupName" required/>
                                    </div>
                                    <div className="mb-3">
                                        <label htmlFor="signupEmail" className="form-label">Email</label>
                                        <input type="email" className="form-control" id="signupEmail" required/>
                                    </div>
                                    <div className="mb-3">
                                        <label htmlFor="signupPassword" className="form-label">Password</label>
                                        <input type="password" className="form-control" id="signupPassword" required/>
                                    </div>
                                    <button type="submit" className="btn btn-success w-100">Sign Up</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}
export default LoginModal;
