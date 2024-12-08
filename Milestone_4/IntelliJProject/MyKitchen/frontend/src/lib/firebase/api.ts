import { createUserWithEmailAndPassword, UserCredential, updateProfile } from "firebase/auth";
import {  collection, addDoc, DocumentReference } from "firebase/firestore";
import { INewUser } from "@/types";
import {auth, database} from "@/lib/firebase/config.ts";


// Function to create a new user account and add to Firestore
export async function createUserAccount(user: INewUser): Promise<UserCredential | Error> {
    // Firebase Authentication
    // Firebase Firestore

    try {
        // Create the user with email and password
        const userCredential = await createUserWithEmailAndPassword(auth, user.email, user.password);
        await updateProfile(userCredential.user, {
            displayName: user.name

        });

        // Step 3: Add user data to Firestore collection
        const userData = {
            name : user.name,               // Full name
            username: user.username ,       // Username
            email: user.email,              // Email address
            profilePic: "",                 // Placeholder for profile picture
            bio: "",                        // Default empty bio
            isPrivate: false,               // Default privacy setting
            isVerified: false,              // Default verification status
            isAdministrator: false,         // Default admin status
            followers: [],                  // Empty followers array
            following: [],                  // Empty following array
            favoriteRecipes: [],            // Empty favorite recipes array
            uploadedRecipes: [],            // Empty uploaded recipes array
            myFridge: []                  // Empty fridge data
        };

        // Add the user document to Firestore
        const userRef: DocumentReference = await addDoc(collection(database, "User"), userData);

        console.log(`User document created with ID: ${userRef.id}`);

        // Return the created user credential
        return userCredential;
    } catch (error: unknown) {
        // Type guard to safely handle the error
        if (error instanceof Error) {
            console.error("Error creating user or adding to Firestore:", error.message);
            return new Error(error.message);
        }
        return new Error("An unknown error occurred");
    }
}
