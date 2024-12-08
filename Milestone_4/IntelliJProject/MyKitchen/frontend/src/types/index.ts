// Navigation Link Type
export type INavLink = {
    imgURL: string;
    route: string;
    label: string;
};

// User Update Type
export type IUpdateUser = {
    userId: string;
    name: string;
    bio: string;
    imageId: string;
    imageUrl: URL | string; // URL for consistency with potential string fallback
    file: File[]; // Array of File objects
};

// New Post Type
export type INewPost = {
    userId: string;
    caption: string;
    file: File[]; // Array of uploaded files
    location?: string; // Optional location
    tags?: string[]; // Tags represented as an array of strings
};

// Update Post Type
export type IUpdatePost = {
    postId: string;
    caption: string;
    imageId: string;
    imageUrl: URL | string; // URL with a string fallback
    file: File[]; // Array of uploaded files
    location?: string;
    tags?: string[];
};

// User Type
export type IUser = {
    id: string; // User's unique identifier
    name: string; // Full name of the user
    username: string; // Username
    email: string; // Email address
    imageUrl: string; // Profile picture URL
    bio: string; // Bio of the user
};

// New User Interface
export interface INewUser {
    email: string; // Required email address
    password: string; // Password
    name?: string; // Optional full name
    username?: string; // Optional username
}

// Utility Types for General Reusability
export type FileOrURL = File[] | URL | string;

// Extended Types (Optional)
export type IFileUpload = {
    file: File[];
    metadata?: Record<string, any>;
};

export type IPostMetadata = {
    location?: string;
    tags?: string[];
};
