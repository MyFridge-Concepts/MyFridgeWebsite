import {User} from "firebase/auth";

export type ExpandedUser = {
    id: string;
    first_name: string;
    last_name: string;
    username: string;
    email: string;
    pfp: string;
    bio: string;
    isPrivate: boolean;
    isVerified: boolean;
    isAdministrator: boolean;
    followers: string[];
    following: string[];
    likedPosts: { recipes: string[]; posts: string[] };
    posts: { recipes: string[]; posts: string[] };
    myFridge: { ingredientId: string | null; unit: string; qty: number }[];
    pfpid: string;
}


// Define the types for the context
export interface AuthContextType {
    user: IUser;
    isAuthenticated: boolean;
    isLoading: boolean;
    checkAuthUser: () => Promise<boolean>;
}


// Navigation Link Type
export type INavLink = {
    imgURL: string;
    route: string;
    label: string;
};

// User Update Type
export type IUpdateUser = {
    id: string; // User's unique identifier
    first_name: string; // First name
    last_name: string; // Last name
    username: string; // Username
    email: string; // Email address
    pfp: string; // Profile picture URL
    bio: string; // Bio of the user
    isPrivate: boolean; // Privacy setting
    isVerified: boolean; // Verification status
    isAdministrator: boolean; // Admin status
    followers: string[]; // Array of follower IDs
    following: string[]; // Array of following IDs
    likedPosts: {
        recipes: string[]; // Array of liked recipe IDs
        posts: string[]; // Array of liked post IDs
    };
    posts: {
        recipes: string[]; // Array of uploaded recipe IDs
        posts: string[]; // Array of uploaded post IDs
    };
    myFridge: {
        ingredientId: string | null; // Ingredient ID
        unit: string; // Unit
        qty: number; // Quantity
    }[];
    pfpid: string; // Profile picture ID
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
    first_name: string; // First name
    last_name: string; // Last name
    username: string; // Username
    email: string; // Email address
    pfp: string; // Profile picture URL
    bio: string; // Bio of the user
    isPrivate: boolean; // Privacy setting
    isVerified: boolean; // Verification status
    isAdministrator: boolean; // Admin status
    followers: string[]; // Array of follower IDs
    following: string[]; // Array of following IDs
    likedPosts: {
        recipes: string[]; // Array of liked recipe IDs
        posts: string[]; // Array of liked post IDs
    };
    posts: {
        recipes: string[]; // Array of uploaded recipe IDs
        posts: string[]; // Array of uploaded post IDs
    };
    myFridge: {
        ingredientId: string | null; // Ingredient ID
        unit: string; // Unit
        qty: number; // Quantity
    }[];
    pfpid: string; // Profile picture ID
};

// New User Interface
export interface INewUser {
    email: string; // Required email address
    password: string; // Password
    first_name: string; // Optional full name
    last_name: string; // Optional full name
    username: string; // Optional username
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
