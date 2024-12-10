import {
    browserLocalPersistence,
    createUserWithEmailAndPassword, onAuthStateChanged,
    setPersistence,
    signInWithEmailAndPassword, User,
    UserCredential
} from "firebase/auth";
import { addDoc, collection, doc, getDoc, setDoc } from "firebase/firestore";
import {ExpandedUser, IUser} from "@/types";
import { auth, avatars, database } from "@/lib/firebase/config.ts";
import {Link, useNavigate} from "react-router-dom";



// Create a new user and save details in Firestore
export const createUserAccount = async (userData: any) => {
    const { email, password, first_name, last_name, username } = userData;
    try {
        // Create user in Firebase Auth
        const userCredential = await createUserWithEmailAndPassword(auth, email, password);
        const user = userCredential.user;

        // Save additional user info in Firestore
        const userDocRef = doc(database, "User", user.uid);
        await setDoc(userDocRef, {
            id: user.uid,
            email,
            first_name,
            last_name,
            username,
            bio: "",
            pfp: "",
            isPrivate: false,
            isVerified: false,
            isAdministrator: false,
            followers: [],
            following: [],
            likedPosts: { recipes: [], posts: [] },
            posts: { recipes: [], posts: [] },
            myFridge: [{ ingredientId: null, unit: "", qty: 0 }],
        });

        return user;
    } catch (error) {
        console.error("Error creating user:", error);
        throw error;
    }
};


// Sign in user
export const signInAccount = async ({ email, password }: { email: string; password: string }) => {
    try {
        const userCredential = await signInWithEmailAndPassword(auth, email, password);
        return userCredential.user;
    } catch (error) {
        console.error("Error signing in:", error);
        throw error;
    }
};

// Sign out user
export const signOutAccount = async () => {
    try {
        await auth.signOut();
    } catch (error) {
        console.error("Error signing out:", error);
        throw error;
    }
};

export async function getCurrentUser(): Promise<IUser| Error> {
    try {
        const currentUser = auth.currentUser;
        if (!currentUser) throw new Error("No user is currently signed in");

        const userDocSnap = await getDoc(doc(database, "User", currentUser.uid));
        if (!userDocSnap.exists()) throw new Error("User document not found in Firestore");

        const userData = userDocSnap.data();
        return {
            id: currentUser.uid, first_name: userData.first_name || "", last_name: userData.last_name || "",
            username: userData.username || "", email: userData.email || "", pfp: userData.pfp || "", bio: userData.bio || "",
            isPrivate: userData.isPrivate ?? false, isVerified: userData.isVerified ?? false, isAdministrator: userData.isAdministrator ?? false,
            followers: userData.followers || [], following: userData.following || [], likedPosts: userData.likedPosts || { recipes: [], posts: [] },
            posts: userData.posts || { recipes: [], posts: [] }, myFridge: userData.myFridge || [], pfpid: userData.pfpid || ""
        };
    } catch (error: unknown) {
        return error instanceof Error ? new Error(error.message) : new Error("An unknown error occurred");
    }
}

// Check if a user is authenticated and retrieve their Firestore document
export const checkAuthUser = async (): Promise<any > => {
    return new Promise((resolve, reject) => {
        onAuthStateChanged(auth, async (firebaseUser: User | null) => {
            if (firebaseUser) {
                try {
                    // Fetch user document from Firestore
                    const userDocRef = doc(database, "User", firebaseUser.uid);
                    const userDocSnap = await getDoc(userDocRef);

                    if (userDocSnap.exists()) {
                        const userData = userDocSnap.data();
                        resolve({ id: firebaseUser.uid, ...userData });
                    } else {
                        console.error("User document not found");
                        resolve(null);
                    }
                } catch (error) {
                    console.error("Error fetching user data:", error);
                    reject(error);
                }
            } else {
                // No user is signed in
                resolve(null);
            }
        });
    });
};
