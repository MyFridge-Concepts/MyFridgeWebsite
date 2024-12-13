
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
        posts: string[]; // Array of liked recipe IDs
    };
    posts: {
        recipes: string[]; // Array of uploaded recipe IDs
        posts: string[]; // Array of uploaded recipe IDs
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
export type INewRecipe = {
    userId: string;
    file: File[]; // Array of uploaded files
    difficulty: number;
    dish: string;
    description: string;
    instructions: string;
    cookTime: number;
    prepTime: number;
    serving: number;
    imageUrl: string;
    tags: string;
    recipeId: string;
};

// Update Post Type
export type IUpdateRecipe = {
    postId: string;
    imageId: string;
    imageUrl: URL | string; // URL with a string fallback
    file: File[]; // Array of uploaded files
    difficulty: number;
    dish: string;
    description: string;
    instructions: string;
    cookTime: number;
    prepTime: number;
    serving: number;
    tags: string;
    recipeId: string;
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
        posts: string[]; // Array of liked recipe IDs
    };
    posts: {
        recipes: string[]; // Array of uploaded recipe IDs
        posts: string[]; // Array of uploaded recipe IDs
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

export type IRecipeMetadata = {
    location?: string;
    tags?: string[];
};
